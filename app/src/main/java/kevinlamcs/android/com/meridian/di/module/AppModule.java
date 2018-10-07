package kevinlamcs.android.com.meridian.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.data.local.DatabaseModule;
import kevinlamcs.android.com.meridian.data.remote.NetworkModule;

@Module(includes = {ViewModelModule.class, NetworkModule.class, DatabaseModule.class})
public class AppModule {
    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application;
    }
}
