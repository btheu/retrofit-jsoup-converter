package btheu.retrofit.converter.jsoup;

import java.io.IOException;
import java.lang.reflect.Type;

import btheu.jsoupmapper.JSoupMapper;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * 
 * @author Benoit Theunissen
 *
 */
public class JSoupConverter implements Converter {

    @Override
    public Object fromBody(TypedInput body, Type type)
            throws ConversionException {
        try {
            return new JSoupMapper().map(body.in(), type);
        } catch (IOException e) {
            throw new RuntimeException("Error occurs during conversion", e);
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        throw new RuntimeException(
                "Conversion from Object to body can't be done for JSoupConvertor");
    }

}
