package kevinlamcs.android.com.meridian.ui.article.item;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.article.display.ArticleFragment;
import kevinlamcs.android.com.meridian.ui.main.MainActivity;

public class ArticleListingItemViewModel {

    private MutableLiveData<Article> article = new MutableLiveData<>();

    public ArticleListingItemViewModel() {
    }

    public MutableLiveData<Article> getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article.setValue(article);
    }

    public void displayArticle(View view) {
        ArticleFragment fragment = ArticleFragment.newInstance(article.getValue());
        MainActivity mainActivity = (MainActivity) view.getContext();
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
