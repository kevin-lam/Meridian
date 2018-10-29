package kevinlamcs.android.com.meridian.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParameterInterceptor implements Interceptor {

    Map<String, String> parameters = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        HttpUrl oldUrl = oldRequest.url();
        HttpUrl newUrl = createUrlFromParameters(parameters, oldUrl.newBuilder());
        Request newRequest = oldRequest.newBuilder().url(newUrl).build();
        return chain.proceed(newRequest);
    }

    private HttpUrl createUrlFromParameters(Map<String, String> parameters, HttpUrl.Builder builder) {
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            builder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public static class Builder {

        private ParameterInterceptor interceptor = new ParameterInterceptor();

        public Builder addParameter(String key, String value) {
            interceptor.addParameter(key, value);
            return this;
        }

        public ParameterInterceptor build() {
            return interceptor;
        }
    }
}
