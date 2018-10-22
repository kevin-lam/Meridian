package kevinlamcs.android.com.meridian.ui.article.listing;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import kevinlamcs.android.com.meridian.util.AppConstants;

public class ArticleListingFragment extends BaseFragment<ArticleListingViewModel> {

    public static final String TAG = ArticleListingFragment.class.getSimpleName();

    @BindView(R.id.articles)
    RecyclerView articleRecyclerView;

    @BindView(R.id.articles_swipe_to_refresh)
    SwipeRefreshLayout articleSwipeToRefresh;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_article_topics)
    DrawerLayout articleTopicDrawerLayout;

    @BindView(R.id.navigation_view_article_sections)
    NavigationView articleTopicNavigationView;

    @BindString(R.string.snackbar_load_article_rationale)
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDefaultSection();
    }

    private void setupDefaultSection() {
        articleListingViewModel.sectionSelected(AppConstants.SECTION_HOME);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_article_listing, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_articles_refresh:
                articleListingViewModel.startRefresh();
                loadArticlesBySectionWithPermission();
                return true;
            case android.R.id.home:
                articleTopicDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setSupportActionBar(toolbar);
        setupDrawer();
        setupRecyclerView();
        setupSwipeRefreshLayout();
    }

    private void setupDrawer() {
        if (isAdded()) {
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            articleTopicNavigationView.getMenu().getItem(0).setChecked(true);
            articleTopicNavigationView.setNavigationItemSelectedListener(menuItem -> {
                onNavigationItemSelected(menuItem);
                return true;
            });
        }
    }

    private void onNavigationItemSelected(MenuItem navigationItem) {
        articleListingViewModel.startRefresh();
        articleListingViewModel.sectionSelected(new ArticleSection(navigationItem.getItemId(), navigationItem.getTitle().toString())
                .toString());
        loadArticlesBySectionWithPermission();
        navigationItem.setCheckable(true);
        articleTopicDrawerLayout.closeDrawers();
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
        articleSwipeToRefresh.setOnRefreshListener(this::loadArticlesBySectionWithPermission);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tearDownDrawer();
        tearDownRecyclerView();
    }

    private void tearDownDrawer() {
        articleTopicNavigationView.setNavigationItemSelectedListener(null);
    }

    private void tearDownRecyclerView() {
        articleRecyclerView.setLayoutManager(null);
    }

    @Override
    public void subscribeToViewModelChanges() {
        articleListingViewModel.getArticles()
            .observe(this, articleListingAdapter::setArticles);
        articleListingViewModel.getRefresh().observe(this, articleSwipeToRefresh::setRefreshing);
        articleListingViewModel.getSection().observe(this, section -> loadArticlesBySectionWithPermission());
    }

    @Override
    public void unsubscribeToViewModelChanges() {
        articleListingViewModel.getArticles().removeObservers(this);
        articleListingViewModel.getRefresh().removeObservers(this);
        articleListingViewModel.getSection().removeObservers(this);
    }

    private void loadArticlesBySectionWithPermission() {
        requestPermission(loadArticleRationale, articleListingViewModel, Manifest.permission.INTERNET);
    }
}
