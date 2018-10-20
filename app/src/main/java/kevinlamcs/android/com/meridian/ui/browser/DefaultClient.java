package kevinlamcs.android.com.meridian.ui.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

public class DefaultClient implements BrowserClient {

    private Context context;

    public DefaultClient(Context context) {
        this.context = context;
    }

    @Override
    public void load(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (browserAvailable(browserIntent)) {
            context.startActivity(browserIntent);
        }
    }

    private boolean browserAvailable(Intent intent) {
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);
        return !(resolveInfoList == null || resolveInfoList.isEmpty());
    }
}
