package kevinlamcs.android.com.meridian.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kevinlamcs.android.com.meridian.di.scope.PerFragment;
import kevinlamcs.android.com.meridian.ui.article.ArticleFragment;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingFragment;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingModule;
import kevinlamcs.android.com.meridian.ui.article.ArticleModule;

@Module
public abstract class FragmentBuilder {

    @PerFragment
    @ContributesAndroidInjector(modules = {ArticleListingModule.class})
    abstract ArticleListingFragment bindArticleListingFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {ArticleModule.class})
    abstract ArticleFragment bindArticleFragment();
}
