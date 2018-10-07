package kevinlamcs.android.com.meridian.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.ui.base.BaseActivity;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingFragment;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void attachFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content_container, ArticleListingFragment.newInstance(), ArticleListingFragment.TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
