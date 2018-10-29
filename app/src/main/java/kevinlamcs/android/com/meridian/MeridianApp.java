package kevinlamcs.android.com.meridian;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;
import kevinlamcs.android.com.meridian.di.component.DaggerAppComponent;
import kevinlamcs.android.com.meridian.util.log.ApplicationLogger;

public class MeridianApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();
        setupTimber();
        setupLeakCanary();
        setupCrashlytics();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    private void setupDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void setupTimber() {
        ApplicationLogger.init();
    }

    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void setupCrashlytics() {
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }
}
