package kevinlamcs.android.com.meridian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import kevinlamcs.android.com.meridian.util.converter.StringListTypeConverter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(JUnit4.class)
public class StringListTypeConverterTest {

    @Test
    public void testFromArrayList() {
        List<String> list = Arrays.asList("item1", "item2");
        assertThat(StringListTypeConverter.fromArrayList(list), is(equalTo("[\"item1\",\"item2\"]")));
    }

    @Test
    public void testFromString() {
        String listString = "[\"item1\",\"item2\"]";
        assertThat(StringListTypeConverter.fromString(listString), is(not(empty())));
    }
}
