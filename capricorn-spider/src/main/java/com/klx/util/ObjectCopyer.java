package com.klx.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shisong on 16/11/30.
 */
public class ObjectCopyer {
    public static void copy(Object from, Object to) {
        if (from == null || to == null) {
            return;
        }
        Class<?> fromClass = from.getClass();
        Class<?> toClass = to.getClass();
        Field[] fields = fromClass.getDeclaredFields();

        for (Field filed : fields) {
            filed.setAccessible(true);
            String fieldName = filed.getName();
            try {
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                Method setMethod = toClass.getMethod(setMethodName, new Class[]{filed.getType()});
                setMethod.invoke(to, filed.get(from));
            } catch (Exception e) {
            }
        }


    }
}
