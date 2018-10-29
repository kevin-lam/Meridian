package kevinlamcs.android.com.meridian;

import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import kevinlamcs.android.com.meridian.data.model.api.TopStoriesResponse;
import kevinlamcs.android.com.meridian.data.remote.NYTimesApiHelper;
import kevinlamcs.android.com.meridian.data.remote.NYTimesArticleSource;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SmallTest
@RunWith(JUnit4.class)
public class ApiHelperTest {

    MockWebServer server;
    NYTimesArticleSource articleSource;

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start();

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        articleSource = new NYTimesArticleSource(retrofit.create(NYTimesApiHelper.class));
    }

    @Test
    public void testGetArticlesBySection() throws IOException {
        TestObserver<TopStoriesResponse> testObserver = new TestObserver<>();

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/articles.json"));

        server.enqueue(response);

        // Call API
        articleSource.getBySection("home").subscribe(testObserver);
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
    }

    private String getJson(String path) throws IOException {
        URL uri = this.getClass().getClassLoader().getResource(path);
        File file = new File(uri.getPath());
        byte[] fileBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(fileBytes);
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}

