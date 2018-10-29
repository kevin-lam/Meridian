package kevinlamcs.android.com.meridian.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import kevinlamcs.android.com.meridian.data.local.LocalArticleSource;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import kevinlamcs.android.com.meridian.data.remote.RemoteArticleSource;

public class ArticleRepository {

    private final RemoteArticleSource remoteArticleSource;
    private final LocalArticleSource localArticleSource;

    @Inject
    public ArticleRepository(LocalArticleSource localArticleSource, RemoteArticleSource remoteArticleSource) {
        this.localArticleSource = localArticleSource;
        this.remoteArticleSource = remoteArticleSource;
    }

    public Maybe<TopStoriesResponse> getRemoteArticlesBySection(String section) {
        return remoteArticleSource.getBySection(section);
    }

    public LiveData<List<Article>> getLocalArticlesAll() {
        return localArticleSource.getAll();
    }

    public LiveData<List<Article>> getLocalArticlesByTopic(String topic) {
        return localArticleSource.getByTopic(topic);
    }

    public void addLocalArticle(Article article) {
        localArticleSource.add(article);
    }

    public void addLocalArticles(List<Article> articles) {
        localArticleSource.addAll(articles);
    }

    public void clearLocalArticles() {
        localArticleSource.clear();
    }

}
