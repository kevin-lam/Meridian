package kevinlamcs.android.com.meridian.ui.article;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.util.AppConstants;
import kevinlamcs.android.com.meridian.util.image.GlideApp;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;
import kevinlamcs.android.com.meridian.util.image.ImageModule;

@Module
public class ArticleListingModule {

    @Provides
    ArticleListingAdapter provideArticleListingAdapter(ArticleListingFragment fragment) {
        ArticleListingAdapter adapter = new ArticleListingAdapter(fragment);
        adapter.submitList(new ArrayList<>());
        return adapter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ArticleListingFragment fragment) {
        return new LinearLayoutManager(fragment.getContext());
    }

    @Provides
    RecyclerViewPreloader<Article> provideRecyclerViewPreloader(ArticleListingAdapter adapter, ArticleListingFragment fragment, PreloadSizeProvider sizeProvider) {
        return new RecyclerViewPreloader<Article>(Glide.with(fragment), adapter, sizeProvider, AppConstants.PRELOAD_AHEAD_ITEM_COUNT);
    }
}
