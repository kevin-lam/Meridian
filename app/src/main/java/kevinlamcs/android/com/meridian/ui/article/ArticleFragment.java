package kevinlamcs.android.com.meridian.ui.article;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.ui.base.BaseFragment;
import kevinlamcs.android.com.meridian.ui.base.BaseViewModel;

public class ArticleFragment extends BaseFragment {


    public ArticleFragment() {

    }

    public static ArticleFragment newInstance() {
        ArticleFragment fragment = new ArticleFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }

    @Override
    public void subscribeToViewModelChanges() {

    }

    @Override
    public void setUpPostViewCreated() {

    }
}
