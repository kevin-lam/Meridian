package kevinlamcs.android.com.meridian.ui.article.item;

import android.arch.lifecycle.MutableLiveData;
import android.support.transition.Fade;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.view.View;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.article.display.ArticleFragment;
import kevinlamcs.android.com.meridian.ui.article.listing.ArticleListingFragment;
import kevinlamcs.android.com.meridian.ui.main.MainActivity;

public class ArticleListingItemViewModel {

    private MutableLiveData<Article> article = new MutableLiveData<>();
    private MutableLiveData<String> transitionName = new MutableLiveData<>();

    public ArticleListingItemViewModel() {
    }

    public MutableLiveData<Article> getArticle() {
        return article;
    }

    public MutableLiveData<String> getTransitionName() {
        return transitionName;
    }

    public void setArticle(Article article) {
        this.article.setValue(article);
    }

    public void setTransitionName(String name) {
        this.transitionName.setValue(name);
    }

    public void displayArticle(View view) {

        MainActivity mainActivity = (MainActivity) view.getContext();
        ArticleFragment detailsFragment = ArticleFragment.newInstance(article.getValue(), transitionName.getValue());
        ArticleListingFragment itemFragment = (ArticleListingFragment) mainActivity.getSupportFragmentManager().findFragmentByTag(ArticleListingFragment.TAG);

        doOpenArticleTransition(detailsFragment, itemFragment);

        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view.findViewById(R.id.article_entry), transitionName.getValue())
                .replace(R.id.main_content_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    private void doOpenArticleTransition(Fragment newFragment, Fragment oldFragment) {
        Transition openArticleTransition = TransitionInflater.from(oldFragment.getContext()).inflateTransition(R.transition.transition_article);
        newFragment.setSharedElementEnterTransition(openArticleTransition);
        newFragment.setReturnTransition(openArticleTransition);
        oldFragment.setExitTransition(new Fade());
    }
}
