package btheu.retrofit.jsoup.converter;

import org.junit.Assert;
import org.junit.Test;

import btheu.jsoupmapper.JSoupMapper;
import btheu.jsoupmapper.JSoupSelect;
import btheu.jsoupmapper.JSoupText;
import btheu.retrofit.jsoup.converter.JSoupConverter;
import junit.framework.TestCase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * 
 * @author Benoit Theunissen
 *
 */
@Slf4j
public class JSoupConverterTest {

    @Test
    public void test() {

        JSoupMapper jSoupMapper = new JSoupMapper();
        jSoupMapper.setEncoding("cp1252");
        jSoupMapper.setBaseURI("https://www.google.com/");

        JSoupConverter jSoupConverter = new JSoupConverter(jSoupMapper);

        RestAdapter build = new RestAdapter.Builder().setLogLevel(LogLevel.NONE)
                .setConverter(jSoupConverter)
                .setEndpoint("https://www.google.com/").build();

        GoogleApi create = build.create(GoogleApi.class);

        String stats = create.search("retrofit").getResultStatistique();

        assertNotEmpty(stats);

        log.info("Statistics [{}]", stats);

    }

    private void assertNotEmpty(String sentence) {
        TestCase.assertNotNull(sentence);
        Assert.assertNotEquals("", sentence.trim());
    }

    public static interface GoogleApi {

        @GET("/search?hl=en&safe=off")
        @Headers({ "User-Agent:Mozilla" })
        public Page search(@Query("q") String query);

    }

    @Data
    public static class Page {

        // get the div holding statistics
        @JSoupSelect("#resultStats")
        @JSoupText
        public String resultStatistique;

    }
}
