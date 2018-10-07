package kevinlamcs.android.com.meridian.data.remote.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import kevinlamcs.android.com.meridian.R;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    public static final String CACHE_FILE_NAME = "http-cache";
    public static final int CACHE_SIZE = 10 * 1024 * 1024;      // 10 MiB

    private static final String CACHE_POLICY = "Cache-Control";
    private static final String CACHE_POLICY_LEGACY = "Pragma";
    private static final int MAX_AGE = 15;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(MAX_AGE, TimeUnit.MINUTES)
                .build();
        return response.newBuilder()
                .removeHeader(CACHE_POLICY_LEGACY)
                .removeHeader(CACHE_POLICY)
                .addHeader(CACHE_POLICY, cacheControl.toString())
                .build();
    }
}
