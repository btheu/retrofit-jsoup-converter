package btheu.retrofit.converter.jsoup;

import org.junit.Test;

import btheu.jsoupmapper.JSoupSelect;
import btheu.jsoupmapper.JSoupText;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * 
 * @author Benoit Theunissen
 *
 */
@Slf4j
public class JSoupConverterTest {

    @Test
    public void test() {

        RestAdapter build = new RestAdapter.Builder()
                .setConverter(new JSoupConverter())
                .setEndpoint("http://www.google.com").build();

        GoogleApi create = build.create(GoogleApi.class);

        log.info("Result {}", create.getIndexPage().getSentence());

    }

    public static interface GoogleApi {

        @GET("/")
        public Page getIndexPage();

    }

    @Data
    public static class Page {

        @JSoupSelect("#prm")
        @JSoupText
        public String sentence;

    }
}
