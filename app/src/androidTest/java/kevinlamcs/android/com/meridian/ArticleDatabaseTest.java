package kevinlamcs.android.com.meridian;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import kevinlamcs.android.com.meridian.data.local.AppDatabase;
import kevinlamcs.android.com.meridian.data.local.dao.ArticleDao;
import kevinlamcs.android.com.meridian.data.model.api.Article;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@RunWith(AndroidJUnit4.class)
public class ArticleDatabaseTest {

    private ArticleDao dao;
    private AppDatabase db;

    public static List<Article> doSynchronous(LiveData<List<Article>> liveData) throws InterruptedException {

        Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<List<Article>> observer = new Observer<List<Article>>() {

            @Override
            public void onChanged(@Nullable List<Article> articles) {
                data[0] = articles;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        return (List<Article>) data[0];
    }

    @Before
    public void openDb() {

        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dao = db.articleDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testListArticles() throws InterruptedException {
        assertThat(doSynchronous(dao.list()).size(), is(0));
    }

    @Test
    public void testInsertArticle() throws InterruptedException {

        Article article = new Article();
        article.setTitle("Test title");
        article.setAuthor("By testAuthor");

        dao.insert(article);
        assertThat(doSynchronous(dao.list()).size(), is(not(0)));
    }

    @Test
    public void testInsertArticles() throws InterruptedException {

        Article article1 = new Article();
        article1.setTitle("Test1");
        article1.setAuthor("By author1");
        Article article2 = new Article();
        article2.setTitle("Test2");
        article2.setAuthor("By author2");
        List<Article> articles = Arrays.asList(article1, article2);
        dao.insertAll(articles);
        assertThat(doSynchronous(dao.list()).size(), is(not(0)));
    }

    @Test
    public void testListArticleByTopic() throws InterruptedException {

        List<String> articleTopic = Arrays.asList("Politics");
        Article article = new Article();
        article.setTitle("Test");
        article.setAuthor("By author");;
        article.setDescriptionFacet(articleTopic);

        dao.insert(article);
        assertThat(doSynchronous(dao.listByTopic("%Politics%")).size(), is(not(0)));
    }

    @Test
    public void testListArticleBySection() throws InterruptedException {

        Article article = new Article();
        article.setTitle("Test");
        article.setAuthor("By author");;
        article.setSection("Books");

        dao.insert(article);
        assertThat(doSynchronous(dao.listBySection("Books")).size(), is(not(0)));
    }

    @Test
    public void testClear() throws InterruptedException {
        Article article = new Article();
        article.setTitle("Test");
        article.setAuthor("By author");

        dao.insert(article);
        dao.clear();
        assertThat(doSynchronous(dao.list()).size(), is(0));
    }
}
