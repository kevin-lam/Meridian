package kevinlamcs.android.com.meridian;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kevinlamcs.android.com.meridian.ui.article.ArticleListingFragment;
import kevinlamcs.android.com.meridian.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    UiDevice device;
    ArticleListingFragment fragment;

    @Before
    public void setup() {
        startArticleListingFragment();
        startUiAutomator();
    }

    private void startArticleListingFragment() {
        fragment = ArticleListingFragment.newInstance();
        mainActivityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container, fragment)
                .commit();
    }

    private void startUiAutomator() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void articleListingNotEmptyOnStart() throws InterruptedException {
        UiObject articleEntry = device.findObject(new UiSelector().resourceId("R.id.article"));
        articleEntry.waitForExists(3000);

        RecyclerView recyclerView = mainActivityTestRule.getActivity().findViewById(R.id.articles);
        assertThat(recyclerView.getAdapter().getItemCount(), is(not(equalTo(0))));
    }

    @Test
    public void clickArticleEntryDisplaysArticle() {
        onData(withId(R.id.articles))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.article))
                .check(matches(isDisplayed()));
    }
}
