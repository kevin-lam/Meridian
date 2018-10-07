package kevinlamcs.android.com.meridian.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kevinlamcs.android.com.meridian.di.scope.ViewModelScope;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingViewModel;
import kevinlamcs.android.com.meridian.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScope(ArticleListingViewModel.class)
    abstract ViewModel bindArticleListingViewModel(ArticleListingViewModel articleListingViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
