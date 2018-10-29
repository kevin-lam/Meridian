package kevinlamcs.android.com.meridian.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import kevinlamcs.android.com.meridian.MeridianApp;
import kevinlamcs.android.com.meridian.data.remote.NetworkModule;
import kevinlamcs.android.com.meridian.di.builder.ActivityBuilder;
import kevinlamcs.android.com.meridian.di.module.AppModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuilder.class, AppModule.class})
public interface AppComponent {

    void inject(MeridianApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
