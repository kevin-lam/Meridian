package kevinlamcs.android.com.meridian.ui.article.display;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.ui.browser.BrowserClient;
import kevinlamcs.android.com.meridian.ui.browser.ChromeClient;
import kevinlamcs.android.com.meridian.ui.browser.DefaultClient;

@Module
public class ArticleModule {
    @Provides
    public BrowserClient provideBrowserClient(ArticleFragment fragment) {
        Context context = fragment.getContext();
        if (ChromeClient.isSupported(context)) {
            return new ChromeClient(context);
        } else {
            return new DefaultClient(context);
        }
    }
}
