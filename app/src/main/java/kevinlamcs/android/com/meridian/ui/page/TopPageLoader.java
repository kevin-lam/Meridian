package kevinlamcs.android.com.meridian.ui.page;

import javax.inject.Inject;

public class TopPageLoader implements PageLoader {

    private static final String NAME = TopPageLoader.class.getCanonicalName();

    static {
        PageLoaderFactory.getInstance().setLoader(NAME, new TopPageLoader());
    }

    @Override
    public void load() {
        //return repository.topStories();
    }

    @Override
    public PageLoader newInstance() {
        return new TopPageLoader();
    }
}
