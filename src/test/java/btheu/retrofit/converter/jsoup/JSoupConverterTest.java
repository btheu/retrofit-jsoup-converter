package btheu.retrofit.converter.jsoup;

import org.junit.Assert;
import org.junit.Test;

import btheu.jsoupmapper.JSoupMapper;
import btheu.jsoupmapper.JSoupSelect;
import btheu.jsoupmapper.JSoupText;
import junit.framework.TestCase;
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

        JSoupMapper jSoupMapper = new JSoupMapper();
        jSoupMapper.setEncoding("cp1252");
        jSoupMapper.setBaseURI("http://www.google.com/");

        JSoupConverter jSoupConverter = new JSoupConverter(jSoupMapper);

        RestAdapter build = new RestAdapter.Builder()
                .setConverter(jSoupConverter)
                .setEndpoint("http://www.google.com/").build();

        GoogleApi create = build.create(GoogleApi.class);

        String sentence = create.getIndexPage().getSentence();

        assertNotEmpty(sentence);

        log.info("Result {}", sentence);

    }

    private void assertNotEmpty(String sentence) {
        TestCase.assertNotNull(sentence);
        Assert.assertNotEquals("", sentence.trim());
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
