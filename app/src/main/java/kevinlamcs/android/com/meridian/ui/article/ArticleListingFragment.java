package kevinlamcs.android.com.meridian.ui.article;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseFragment;

public class ArticleListingFragment extends BaseFragment<ArticleListingViewModel> {

    public static final String TAG = ArticleListingFragment.class.getSimpleName();

    @BindView(R.id.articles)
    RecyclerView articleRecyclerView;

    @BindString(R.string.load_article_rationale)
    String loadArticleRationale;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    ArticleListingAdapter articleListingAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    RecyclerViewPreloader<Article> preloader;

    private ArticleListingViewModel articleListingViewModel;

    public ArticleListingFragment() {
    }

    public static ArticleListingFragment newInstance() {
        ArticleListingFragment fragment = new ArticleListingFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_article_listing;
    }

    @Override
    public ArticleListingViewModel getViewModel() {
        articleListingViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListingViewModel.class);
        return articleListingViewModel;
    }

    @Override
    public void setupOnViewCreated() {
        super.setupOnViewCreated();
        articleRecyclerView.setLayoutManager(linearLayoutManager);
        articleRecyclerView.setAdapter(articleListingAdapter);
        articleRecyclerView.addOnScrollListener(preloader);
    }

    @Override
    public void tearDownOnViewDestroyed() {
        articleRecyclerView.setLayoutManager(null);
    }

    @Override
    public void subscribeToViewModelChanges() {
        articleListingViewModel.getArticles()
            .observe(this, articleListingAdapter::setArticles);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (firstTimeCreated(savedInstanceState)) {
            requestPermission(loadArticleRationale, articleListingViewModel, Manifest.permission.INTERNET);
        }
    }
}
