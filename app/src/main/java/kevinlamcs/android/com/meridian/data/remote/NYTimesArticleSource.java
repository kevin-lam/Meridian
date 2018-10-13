package kevinlamcs.android.com.meridian.data.remote;

import javax.inject.Inject;

import io.reactivex.Maybe;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;

public class NYTimesArticleSource implements RemoteArticleSource {

    private final NYTimesApiHelper nyTimesApiHelper;

    @Inject
    public NYTimesArticleSource(NYTimesApiHelper nyTimesApiHelper) {
        this.nyTimesApiHelper = nyTimesApiHelper;
    }

    @Override
    public Maybe<TopStoriesResponse> getBySection(String section) {
        return nyTimesApiHelper.listBySection(section);
    }
}
