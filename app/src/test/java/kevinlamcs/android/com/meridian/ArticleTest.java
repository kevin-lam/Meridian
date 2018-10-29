package kevinlamcs.android.com.meridian;

import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.data.model.api.Multimedia;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
@SmallTest
public class ArticleTest {

    @Test
    public void testEquals() {

        Article article1 = new Article();
        Article article2 = new Article();

        List<String> descriptionFacet1 = Arrays.asList("World", "Politics");
        List<String> descriptionFacet2 = new ArrayList<>(descriptionFacet1);

        Multimedia multimedia1 = new Multimedia();
        Multimedia multimedia2 = new Multimedia();

        multimedia1.setUrl("TestUrl");
        multimedia2.setUrl("TestUrl");

        multimedia1.setHeight(10);
        multimedia2.setHeight(10);

        multimedia1.setWidth(10);
        multimedia2.setWidth(10);

        article1.setTitle("TestTitle");
        article2.setTitle("TestTitle");

        article1.setSection("TestSection");
        article2.setSection("TestSection");

        article1.setAuthor("TestAuthor");
        article2.setAuthor("TestAuthor");

        article1.setDescriptionFacet(descriptionFacet1);
        article2.setDescriptionFacet(descriptionFacet2);

        assertThat(article1.equals(article2), is(true));
    }
}
