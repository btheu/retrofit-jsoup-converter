package btheu.retrofit2.jsoup.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import btheu.jsoupmapper.JSoupMapper;
import btheu.retrofit2.jsoup.JSoup;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 
 * @author NeoMcFly
 *
 */
public class JSoupConverterFactory extends Converter.Factory {

    protected final JSoupMapper jsoupMapper;

    private JSoupConverterFactory(JSoupMapper mapper) {
        this.jsoupMapper = mapper;
    }

    public static JSoupConverterFactory create() {
        return create(new JSoupMapper());
    }

    public static JSoupConverterFactory create(JSoupMapper mapper) {
        return new JSoupConverterFactory(mapper);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
            Annotation[] annotations, Retrofit retrofit) {
        if (Utils.contains(annotations, JSoup.class)) {
            return new JSoupResponseBodyConverter(this.jsoupMapper, type);
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
            Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
            Retrofit retrofit) {
        if (Utils.contains(methodAnnotations, JSoup.class)) {
            throw new RuntimeException(
                    "Convertion to body can't be done for JSoupConvertor");
        }
        return null;
    }

}
