package kevinlamcs.android.com.meridian.data.remote;

import io.reactivex.Maybe;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;

public interface RemoteArticleSource {
    Maybe<TopStoriesResponse> getBySection(String section);
}
