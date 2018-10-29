package kevinlamcs.android.com.meridian.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kevinlamcs.android.com.meridian.data.local.dao.ArticleDao;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.data.model.api.Multimedia;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
