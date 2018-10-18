package kevinlamcs.android.com.meridian.ui.browser;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import kevinlamcs.android.com.meridian.R;

public class ChromeClient implements BrowserClient {

    private final Context context;
    private CustomTabsIntent.Builder builder;

    public ChromeClient(Context context) {
        this.context = context;
        setup();
    }

    private void setup() {
        setupConfig();
    }

    private void setupConfig() {
        builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary))
                .enableUrlBarHiding()
                .setShowTitle(false);
    }

    @Override
    public void load(String url) {
        builder.build().launchUrl(context, Uri.parse(url));
    }
}
