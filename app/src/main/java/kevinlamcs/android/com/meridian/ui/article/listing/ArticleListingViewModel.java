package kevinlamcs.android.com.meridian.ui.article.listing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kevinlamcs.android.com.meridian.data.ArticleRepository;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import kevinlamcs.android.com.meridian.ui.base.BaseViewModel;
import kevinlamcs.android.com.meridian.util.livedata.SingleLiveEvent;
import kevinlamcs.android.com.meridian.util.permission.PermissionListener;
import kevinlamcs.android.com.meridian.util.log.ApplicationLogger;

public class ArticleListingViewModel extends BaseViewModel implements PermissionListener {

    private final ArticleRepository repo;
    private MutableLiveData<String> section = new SingleLiveEvent<>();
    private MutableLiveData<Boolean> refresh = new SingleLiveEvent<>();

    @Inject
    public ArticleListingViewModel(ArticleRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onPermissionGranted() {
        loadBySection(section.getValue());
    }

    public void loadBySection(String section) {
        getCompositeDisposable().add(
                repo.getRemoteArticlesBySection(section)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::onLoadError)
                        .doOnSuccess(this::onLoadSuccess)
                        .observeOn(Schedulers.io())
                        .map(topStoriesResponse -> topStoriesResponse.getResults())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            articles -> {
                                repo.clearLocalArticles();
                                repo.addLocalArticles(articles);
                            }, error -> {}
                        )
        );
    }

    private void onLoadError(Throwable error) {
        ApplicationLogger.e(error, this.getClass().getSimpleName());
        stopRefresh();
    }

    private void onLoadSuccess(TopStoriesResponse response) {
        stopRefresh();
    }

    public void sectionSelected(String section) {
        this.section.setValue(section);
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

    public MutableLiveData<String> getSection() {
        return section;
    }
}
