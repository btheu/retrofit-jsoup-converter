package btheu.retrofit2.jsoup.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import btheu.jsoupmapper.JSoupMapper;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 
 * @author NeoMcFly
 *
 * @param <T>
 */
public class JSoupResponseBodyConverter<T>
        implements Converter<ResponseBody, T> {

    private Type type;
    private JSoupMapper jsoupMapper;

    public JSoupResponseBodyConverter(JSoupMapper jsoupMapper, Type type) {
        this.jsoupMapper = jsoupMapper;
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convert(ResponseBody value) throws IOException {
        return (T) jsoupMapper.map(value.byteStream(), type);
    }

}
