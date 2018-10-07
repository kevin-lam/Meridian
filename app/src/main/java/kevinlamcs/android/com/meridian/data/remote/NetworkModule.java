package kevinlamcs.android.com.meridian.data.remote;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.BuildConfig;
import kevinlamcs.android.com.meridian.data.remote.interceptor.CacheInterceptor;
import kevinlamcs.android.com.meridian.di.module.AppModule;
import kevinlamcs.android.com.meridian.util.log.ApplicationLogger;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {AppModule.class})
public class NetworkModule {

    @Provides @Named("top_stories_api_key")
    String provideTopStoriesApiKey() {
        return BuildConfig.TOP_STORIES_API_KEY;
    }

    @Provides @Named("top_stories_url")
    String provideTopStoriesUrl() {
        return BuildConfig.TOP_STORIES_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named("top_stories_url") String baseUrl, Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, CacheInterceptor cacheInterceptor, Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }

        return builder
                .addInterceptor(cacheInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                ApplicationLogger.d(message);
            }
        });
    }

    @Provides
    @Singleton
    CacheInterceptor provideCacheInterceptor() {
        return new CacheInterceptor();
    }

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), CacheInterceptor.CACHE_FILE_NAME);
        return new Cache(httpCacheDirectory, CacheInterceptor.CACHE_SIZE);
    }
}