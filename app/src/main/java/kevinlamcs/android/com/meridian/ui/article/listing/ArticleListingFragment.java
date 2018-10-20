package kevinlamcs.android.com.meridian.ui.article.listing;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

    @BindView(R.id.articles_swipe_to_refresh)
    SwipeRefreshLayout articleSwipeToRefresh;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.article_listing, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_articles_refresh:
                articleListingViewModel.startRefresh();
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        setupSwipeRefreshLayout();
    }

    private void setupRecyclerView() {
        articleRecyclerView.setLayoutManager(linearLayoutManager);
        articleRecyclerView.setAdapter(articleListingAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_article_entry));
        articleRecyclerView.addItemDecoration(divider);
        articleRecyclerView.addOnScrollListener(preloader);
    }

    private void setupSwipeRefreshLayout() {
        articleSwipeToRefresh.setOnRefreshListener(this::refresh);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tearDownRecyclerView();
    }

    private void tearDownRecyclerView() {
        articleRecyclerView.setLayoutManager(null);
    }

    @Override
    public void subscribeToViewModelChanges() {
        articleListingViewModel.getArticles()
            .observe(this, articleListingAdapter::setArticles);
        articleListingViewModel.getRefresh().observe(this, shouldRefresh -> articleSwipeToRefresh.setRefreshing(shouldRefresh));
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (firstTimeCreated(savedInstanceState)) {
            refresh();
        }
    }

    private void refresh() {
        requestPermission(loadArticleRationale, articleListingViewModel, Manifest.permission.INTERNET);
    }
}
