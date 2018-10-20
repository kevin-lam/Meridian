package kevinlamcs.android.com.meridian.ui.article.listing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.ScrollView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kevinlamcs.android.com.meridian.data.ArticleRepository;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseViewModel;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;
import kevinlamcs.android.com.meridian.util.listener.PermissionListener;
import kevinlamcs.android.com.meridian.util.log.ApplicationLogger;

public class ArticleListingViewModel extends BaseViewModel implements PermissionListener {

    private final ArticleRepository repo;
    private MutableLiveData<String> section = new MutableLiveData<>();
    private MutableLiveData<Boolean> refresh = new MutableLiveData<>();


    @Inject
    public ArticleListingViewModel(ArticleRepository repo) {
        this.repo = repo;
        section.setValue("home");
    }

    @Override
    public void onPermissionGranted() {
        loadBySection(section.getValue());
    }

    public void loadBySection(String section) {
        getCompositeDisposable().add(
                repo.getRemoteArticlesBySection(section)
                        .doOnError(error -> ApplicationLogger.e(error, this.getClass().getSimpleName(),
                                error.getMessage()))
                        .map(topStoriesResponse -> topStoriesResponse.getResults())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(listing -> stopRefresh())
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            articles -> {
                                repo.clearLocalArticles();
                                repo.addLocalArticles(articles);
                            }
                        )
        );
    }

    public void startRefresh() {
        refresh.setValue(true);
    }

    public void stopRefresh() {
        refresh.setValue(false);
    }

    public LiveData<List<Article>> getArticles() {
        return repo.getLocalArticlesAll();
    }

    public MutableLiveData<Boolean> getRefresh() {
        return refresh;
    }
    // Need to cover getArticles by section, topic
}
