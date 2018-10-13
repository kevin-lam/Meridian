package kevinlamcs.android.com.meridian.data.local;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import kevinlamcs.android.com.meridian.data.model.api.Article;

public class RoomDatabaseArticleSource implements LocalArticleSource {

    private final AppDatabase appDatabase;

    @Inject
    public RoomDatabaseArticleSource(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public LiveData<List<Article>> getAll() {
        return appDatabase.articleDao().list();
    }

    @Override
    public LiveData<List<Article>> getByTopic(String topic) {
        return appDatabase.articleDao().listByTopic('%' + topic + '%');
    }

    @Override
    public void add(Article article) {
        appDatabase.articleDao().add(article);
    }

    @Override
    public void addAll(List<Article> articles) {
        appDatabase.articleDao().addAll(articles);
    }

    @Override
    public void clear() {
        appDatabase.articleDao().clear();
    }
}
