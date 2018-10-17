package kevinlamcs.android.com.meridian.ui.article;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import io.reactivex.Observable;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.main.MainActivity;
import kevinlamcs.android.com.meridian.util.AppConstants;
import kevinlamcs.android.com.meridian.util.TimeFormatter;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;

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
