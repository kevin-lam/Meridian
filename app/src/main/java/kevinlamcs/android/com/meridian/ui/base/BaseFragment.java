package kevinlamcs.android.com.meridian.ui.base;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import kevinlamcs.android.com.meridian.R;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    public abstract int getLayoutId();

    public abstract V getViewModel();

    public abstract void subscribeToViewModelChanges();

    public abstract void unsubscribeToViewModelChanges();

    private V viewModel;

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        injectDependencies();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribeToViewModelChanges();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToViewModelChanges();
    }

    protected void setActionBarNoTitle() {
        if (isAdded()) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        }
    }

    protected boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void onBackPressed() {
        if (isAdded()) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    public void showBackButton(boolean show) {
        if (isAdded()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(show);
        }
    }

    public void showConnectionError(View view) {
        Snackbar.make(view, R.string.snackbar_no_connection_rationale, Snackbar.LENGTH_LONG)
                .show();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (isAdded()) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }

    private void injectDependencies() {
        AndroidSupportInjection.inject(this);
    }
}
