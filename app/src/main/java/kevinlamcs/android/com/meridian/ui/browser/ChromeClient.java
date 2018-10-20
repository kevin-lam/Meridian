package kevinlamcs.android.com.meridian.ui.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import java.util.List;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.util.AppConstants;

public class ChromeClient implements BrowserClient {

    private static final String CHROME_PACKAGE = "com.android.chrome";

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

    public static boolean isSupported(Context context) {
        Intent serviceIntent = new Intent(AppConstants.CUSTOM_TAB_SERVICE);
        serviceIntent.setPackage(CHROME_PACKAGE);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(serviceIntent, 0);
        return !(resolveInfos == null || resolveInfos.isEmpty());
    }
}
