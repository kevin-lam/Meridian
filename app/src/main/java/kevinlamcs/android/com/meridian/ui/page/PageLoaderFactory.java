package kevinlamcs.android.com.meridian.ui.page;

import java.util.HashMap;
import java.util.Map;

public class PageLoaderFactory {

    private static PageLoaderFactory pageLoaderFactory;
    private Map<String, PageLoader> pageLoaders;

    private PageLoaderFactory() {
        pageLoaders = new HashMap<>();
    }

    public static PageLoaderFactory getInstance() {
        if (pageLoaderFactory == null) {
            pageLoaderFactory = new PageLoaderFactory();
        }
        return pageLoaderFactory;
    }

    public PageLoader create(String loadType) {
        return getLoader(loadType).newInstance();
    }

    public PageLoader getLoader(String loadType) {
        return pageLoaders.get(loadType);
    }

    public void setLoader(String loadType, PageLoader loader) {
        pageLoaders.put(loadType, loader);
    }
}
