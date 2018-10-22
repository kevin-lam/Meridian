package kevinlamcs.android.com.meridian.ui.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();
    protected abstract void attachFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (firstTimeCreated(savedInstanceState)) attachFragment();
    }

    private void injectDependencies() {
        AndroidInjection.inject(this);
    }

    private boolean firstTimeCreated(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }
}
