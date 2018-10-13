package kevinlamcs.android.com.meridian.data.remote;

import io.reactivex.Maybe;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTimesApiHelper {
    @GET("{section}.json")
    Maybe<TopStoriesResponse> listBySection(@Path("section") String section);
}
