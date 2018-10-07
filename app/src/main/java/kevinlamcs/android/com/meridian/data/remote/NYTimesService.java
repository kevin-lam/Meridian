package kevinlamcs.android.com.meridian.data.remote;

import io.reactivex.Maybe;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTimesService {
    @GET("/{section}.json")
    Maybe<TopStoriesResponse> getTopStories(@Path("section") String section);
}
