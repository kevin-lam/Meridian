package kevinlamcs.android.com.meridian.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import kevinlamcs.android.com.meridian.util.listener.PermissionListener;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    public abstract int getLayoutId();
    public abstract V getViewModel();
    public abstract void subscribeToViewModelChanges();

    private V viewModel;

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
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeToViewModelChanges();
        setupOnViewCreated();
    }

    public void setupOnViewCreated() {
    }

    public void tearDownOnViewDestroyed() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tearDownOnViewDestroyed();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
        setupOnRestoreInstanceState();
    }

    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
    }

    public void setupOnRestoreInstanceState() {
    }

    public void requestPermission(String rationale, PermissionListener permissionListener, String... requestedPermission) {
        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(requestedPermission)
                .subscribe(permission -> {
                    if (permission.granted) {
                        permissionListener.onPermissionGranted();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // TODO: Snackbar rationale
                    } else {
                    }
                });
    }

    protected boolean firstTimeCreated(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

    public void onBackPressed() {
        if (!isDetached()) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    public void showBackButton(boolean show) {
        if (!isDetached()) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(show);
            setHasOptionsMenu(true);
        }
    }

    private void injectDependencies() {
        AndroidSupportInjection.inject(this);
    }
}
