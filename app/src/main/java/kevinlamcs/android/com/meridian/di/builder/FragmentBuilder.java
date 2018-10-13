package kevinlamcs.android.com.meridian.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kevinlamcs.android.com.meridian.ui.article.ArticleFragment;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingFragment;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingModule;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {ArticleListingModule.class})
    abstract ArticleListingFragment bindArticleListingFragment();

    @ContributesAndroidInjector
    abstract ArticleFragment bindArticleFragment();
}
