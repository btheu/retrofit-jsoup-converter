package btheu.retrofit2.converter.jsoup;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import btheu.jsoupmapper.JSoupMapper;
import btheu.jsoupmapper.JSoupSelect;
import btheu.jsoupmapper.JSoupText;
import btheu.retrofit2.jsoup.JSoup;
import btheu.retrofit2.jsoup.converter.JSoupConverterFactory;
import junit.framework.TestCase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 
 * @author Benoit Theunissen
 *
 */
@Slf4j
public class JSoupConverterTest {

    @Test
    public void test() throws IOException {

        JSoupMapper jSoupMapper = new JSoupMapper();
        jSoupMapper.setEncoding("cp1252");
        jSoupMapper.setBaseURI("https://www.google.com/");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.google.com/")
                .addConverterFactory(JSoupConverterFactory.create()).build();

        GoogleApi create = retrofit.create(GoogleApi.class);

        String stats = create.search("retrofit").execute().body()
                .getResultStatistics();

        assertNotEmpty(stats);

        log.info("Statistics [{}]", stats);

    }

    private void assertNotEmpty(String sentence) {
        TestCase.assertNotNull(sentence);
        Assert.assertNotEquals("", sentence.trim());
    }

    public static interface GoogleApi {

        @JSoup
        @GET("/search?hl=en&safe=off")
        @Headers({ "User-Agent:Mozilla" })
        public Call<Page> search(@Query("q") String query);

    }

    @Data
    public static class Page {

        // get the div holding statistics
        @JSoupSelect("#resultStats")
        @JSoupText
        public String resultStatistics;

    }
}
