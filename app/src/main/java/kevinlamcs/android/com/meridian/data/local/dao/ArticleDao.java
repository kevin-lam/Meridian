package kevinlamcs.android.com.meridian.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Article;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<Article> articles);

    @Query("SELECT * FROM articles")
    LiveData<List<Article>> list();

    // String topic parameter must be in the form '%topic%'
    @Query("SELECT * FROM articles WHERE articles.des_facet LIKE :topic OR articles.per_facet LIKE :topic OR articles.geo_facet LIKE :topic OR articles.org_facet LIKE :topic")
    LiveData<List<Article>> listByTopic(String topic);

    @Query("DELETE FROM articles")
    void clear();
}
