package kevinlamcs.android.com.meridian.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kevinlamcs.android.com.meridian.di.scope.PerActivity;
import kevinlamcs.android.com.meridian.ui.main.MainActivity;

@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = {FragmentBuilder.class})
    abstract MainActivity bindMainActivity();
}
