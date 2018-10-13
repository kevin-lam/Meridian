package kevinlamcs.android.com.meridian;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Multimedia;
import kevinlamcs.android.com.meridian.util.converter.MultimediaListTypeConverter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class MultimediaListTypeConverterTest {

    @Test
    public void testFromArrayList() {
        Multimedia multimedia = new Multimedia();
        multimedia.setUrl("https://www.google.com");
        multimedia.setHeight(720);
        multimedia.setWidth(1080);
        multimedia.setFormat("Standard Thumbnail");
        multimedia.setType("image");
        multimedia.setSubtype("photo");
        multimedia.setCaption("");
        multimedia.setCopyright("");

        List<Multimedia> list = Arrays.asList(multimedia);
        assertThat(MultimediaListTypeConverter.fromArrayList(list), is(equalTo(
                "[" +
                            "{" +
                                "\"url\":\"https://www.google.com\"," +
                                "\"format\":\"Standard Thumbnail\"," +
                                "\"height\":720," +
                                "\"width\":1080," +
                                "\"type\":\"image\"," +
                                "\"subtype\":\"photo\"," +
                                "\"caption\":\"\"," +
                                "\"copyright\":\"\"" +
                            "}" +
                        "]")));
    }

    @Test
     public void testFromString() {
        String listString =
                "[" +
                    "{" +
                        "\"url\":\"https://www.google.com\"," +
                        "\"format\":\"Standard Thumbnail\"," +
                        "\"height\":720," +
                        "\"width\":1080," +
                        "\"type\":\"image\"," +
                        "\"subtype\":\"photo\"," +
                        "\"caption\":\"\"," +
                        "\"copyright\":\"\"" +
                    "}" +
                "]";
        assertThat(MultimediaListTypeConverter.fromString(listString), is(not(empty())));
    }
}
