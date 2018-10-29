package kevinlamcs.android.com.meridian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import kevinlamcs.android.com.meridian.util.TimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
public class TimeFormatterTest {

    private TimeFormatter formatter = new TimeFormatter();


    @Test
    public void testElapsedDateShown() {
        assertThat(formatter.elapsed(1539000000000L, 1539090000000L), is(equalTo("10/8")));
    }

    @Test
    public void testElapsedHourShown() {
        assertThat(formatter.elapsed(1539000000000L, 1539003600000L), is(equalTo("1H")));
    }

    @Test
    public void testElapsedMinuteShown() {
        assertThat(formatter.elapsed(1539000000000L, 1538999999999L), is(equalTo("0M")));
    }

    @Test
    public void testTime() {
        assertThat(formatter.time("2018-10-11T18:47:08-04:00"), is(equalTo(1539298028000L)));
    }
}
