package kevinlamcs.android.com.meridian.ui.article;

import android.arch.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.util.TimeFormatter;

public class ArticleListingItemViewModel {

    private final MutableLiveData<String> section = new MutableLiveData<>();
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> createdDate = new MutableLiveData<>();
    private final TimeFormatter timeFormatter;

    private Article article;

    public ArticleListingItemViewModel(TimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    public MutableLiveData<String> getSection() {
        return section;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getCreatedDate() {
        return createdDate;
    }

    public void setArticle(Article article) {
        this.article = article;
        section.setValue(article.getSection());
        title.setValue(article.getTitle());
        createdDate.setValue(timeFormatter.elapsed(article.getCreatedDate()));
    }
}
