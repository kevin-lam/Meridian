package kevinlamcs.android.com.meridian.ui.article;

import android.arch.lifecycle.MutableLiveData;

import kevinlamcs.android.com.meridian.data.model.api.Article;

public class ArticleListingItemViewModel {

    public final MutableLiveData<String> title = new MutableLiveData<>();
    public final MutableLiveData<String> description = new MutableLiveData<>();
    public final MutableLiveData<String> publishDate = new MutableLiveData<>();

    private final Article article;

    public ArticleListingItemViewModel(Article article) {
        this.article = article;
    }
}
