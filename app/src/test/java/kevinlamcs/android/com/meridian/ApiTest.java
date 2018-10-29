package kevinlamcs.android.com.meridian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import kevinlamcs.android.com.meridian.data.remote.NYTimesApiHelper;
import kevinlamcs.android.com.meridian.data.remote.NYTimesArticleSource;
import kevinlamcs.android.com.meridian.util.AppConstants;
import kevinlamcs.android.com.meridian.util.ParameterInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(JUnit4.class)
public class ApiTest {
    NYTimesArticleSource articleSource;

    @Before
    public void setup() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ParameterInterceptor.Builder()
                        .addParameter(AppConstants.TOP_STORIES_API_KEY_HEADER, BuildConfig.TOP_STORIES_API_KEY)
                        .build()
                )
                .build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.TOP_STORIES_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        articleSource = new NYTimesArticleSource(retrofit.create(NYTimesApiHelper.class));
    }

    @Test
    public void testGetArticlesBySection() {
        TestObserver<TopStoriesResponse> testObserver = articleSource.getBySection("home").test();
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }
}
