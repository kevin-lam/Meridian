package kevinlamcs.android.com.meridian.data.local;

import android.arch.lifecycle.LiveData;

import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Article;

public interface LocalArticleSource {
    LiveData<List<Article>> getAll();
    LiveData<List<Article>> getByTopic(String topic);
    void add(Article article);
    void addAll(List<Article> articles);
    void clear();
}
