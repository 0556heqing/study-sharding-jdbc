package com.heqing.sharding.jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 入参校验工具类
 *
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
public class ValidateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUtil.class);

    /**
     * 校验指定对象的指定字段是否为空
     *
     * @param clazz 对象类
     * @param fields 字段
     * @return 是否为null
     */
    public static boolean validateParameter(Object clazz, String... fields) {
        if (isEmpty(clazz)) {
            LOGGER.error("--->入参对象{}为空!", clazz.getClass().getName());
            return false;
        }
        try {
            for (String name : fields) {
                Method method = getMethod(clazz.getClass(), name);
                Object value = method.invoke(clazz, null);
                if (isEmpty(value)) {
                    LOGGER.error("--->{}入参字段{}为空!", clazz.getClass().getName(), name);
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 获取指定字段的get方法
     *
     * @param clazz 对象类
     * @param field 字段
     * @return 是否为null
     */
    private static Method getMethod(Class clazz, String field) {
        Method method = null;

        String methodName = "get" + field.substring(0, 1).toUpperCase()
                + field.substring(1);
        try {
            method = clazz.getMethod(methodName, new Class[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    /**
     * 判断对象是否为null
     *
     * @param object 对象
     * @return 是否为null
     */
    public static boolean isEmpty(Object object) {
        return object == null;
    }
}
