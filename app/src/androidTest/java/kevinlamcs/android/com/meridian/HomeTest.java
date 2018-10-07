package kevinlamcs.android.com.meridian;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kevinlamcs.android.com.meridian.ui.main.MainActivity;
import kevinlamcs.android.com.meridian.ui.article.ArticleListingFragment;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void clickArticleEntryDisplaysArticle() {
        ArticleListingFragment fragment = ArticleListingFragment.newInstance();
        mainActivityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container, fragment)
                .commit();
        onData(withId(R.id.articles))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.article))
                .check(matches(isDisplayed()));
    }
}
