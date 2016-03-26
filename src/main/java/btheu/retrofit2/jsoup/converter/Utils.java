package btheu.retrofit2.jsoup.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/**
 * 
 * @author NeoMcFly
 *
 */
abstract class Utils {

    public static boolean contains(Annotation[] array, Class<?> toFind) {
        for (int i = 0; i < array.length; i++) {
            if (toFind.equals(array[i].annotationType()))
                return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T retreves(Annotation[] array, Class<T> toFind) {
        for (int i = 0; i < array.length; i++) {
            if (toFind.equals(array[i].annotationType()))
                return (T) array[i];
        }
        return null;
    }

    public static Type getCallResponseType(Type returnType) {
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        return getParameterUpperBound(0, (ParameterizedType) returnType);
    }

    public static Type getParameterUpperBound(int index,
            ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (types.length <= index) {
            throw new IllegalArgumentException("Expected at least " + index
                    + " type argument(s) but got: " + Arrays.toString(types));
        }
        Type paramType = types[index];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }

}
