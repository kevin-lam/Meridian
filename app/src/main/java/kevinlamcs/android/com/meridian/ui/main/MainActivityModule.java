package kevinlamcs.android.com.meridian.ui.main;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.ui.browser.BrowserClient;
import kevinlamcs.android.com.meridian.ui.browser.ChromeClient;

@Module
public class MainActivityModule {

    @Provides
    public BrowserClient provideBrowserClient(MainActivity activity) {
        return new ChromeClient(activity);
    }
}
