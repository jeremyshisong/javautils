package hello.o2o.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shisong on 16/1/11.
 */
public class BeanCopyer {
    public static void copy(Object copy, Object paste) {
        if (copy == null || paste == null) {
            return;
        }
        Class<?> clazzOutter = copy.getClass();
        Class<?> clazzInner = paste.getClass();
        Field[] fields = clazzOutter.getDeclaredFields();

        for (Field filed : fields) {
            filed.setAccessible(true);
            String fieldName = filed.getName();
            try {
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                Method setMethod = clazzInner.getMethod(setMethodName, new Class[]{filed.getType()});
                if (setMethod != null) {
                    setMethod.invoke(paste, filed.get(copy));
                }
            } catch (Exception e) {
            }
        }
    }

}
