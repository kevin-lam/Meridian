package kevinlamcs.android.com.meridian.ui.article;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.ui.base.BaseFragment;

public class ArticleListingFragment extends BaseFragment<ArticleListingViewModel> {

    public static final String TAG = ArticleListingFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

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
    public void observeViewModelChanges() {

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (firstTimeCreated(savedInstanceState)) articleListingViewModel.load("FRONTPAGE");
    }
}
