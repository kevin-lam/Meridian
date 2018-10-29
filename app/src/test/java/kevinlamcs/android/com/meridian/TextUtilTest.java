package kevinlamcs.android.com.meridian;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import kevinlamcs.android.com.meridian.util.TextUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
public class TextUtilTest {

    @Test
    public void testCapitalize() {
        assertThat(TextUtil.capitalize("not capitalized yet"), is(equalTo("Not Capitalized Yet")));
    }

    @Test
    public void testToUpperCaseAt() {
        assertThat(TextUtil.toUpperCaseAt(10, "capitalized"), is(equalTo("capitalizeD")));
    }


    @Test
    public void testToLowerCaseAt() {
        assertThat(TextUtil.toLowerCaseAt(10, "CAPITALIZED"), is(equalTo("CAPITALIZEd")));
    }

    @Test
    public void testRemoveLast() {
        assertThat(TextUtil.removeLast(".", "remove.this"), is(equalTo("removethis")));
    }

    @Test
    public void testRemoveNonAlphaNumeric() {
        assertThat(TextUtil.removeNonAlphaNumeric("Sentence /full of punctu@t!on..."), is(equalTo("Sentencefullofpunctuton")));
    }

}
