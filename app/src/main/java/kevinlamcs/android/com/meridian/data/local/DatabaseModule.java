package kevinlamcs.android.com.meridian.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.data.local.dao.ArticleDao;
import kevinlamcs.android.com.meridian.di.scope.ArticleDatabaseScope;
import kevinlamcs.android.com.meridian.util.AppConstants;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@ArticleDatabaseScope String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @ArticleDatabaseScope
    String provideArticleDatabaseName() {
        return AppConstants.ARTICLE_DATABASE_NAME;
    }

    @Provides
    @Singleton
    ArticleDao provideArticleDao(AppDatabase database) {
        return database.articleDao();
    }

}
