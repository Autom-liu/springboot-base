package com.edu.scnu.common.query;

import com.edu.scnu.common.base.BaseFunction;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class LambdaColumnCache {

    private static final Map<String, String> methodToColumnMap = new ConcurrentHashMap<>();

    private static final Map<String, List<String>> classToFieldListMap = new ConcurrentHashMap<>();

    public static <T> String get(BaseFunction<T, ?> bfcs) {
        try {
            Method writeReplace = bfcs.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(bfcs);
            String implClass = serializedLambda.getImplClass();
            String className = implClass.replace("/", ".");
            String methodName = serializedLambda.getImplMethodName();
            String fullName = className + "." + methodName;
            return methodToColumnMap.computeIfAbsent(fullName, (key) -> getFromFullName(className, methodName));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFromFullName(String clsName, String methodName){
        try {
            Class<?> cls = Class.forName(clsName);
            String fieldName = getFieldNameFromMethod(methodName);
            Field field = cls.getDeclaredField(fieldName);
            Column column = field.getAnnotation(Column.class);
            if (column == null) {
                throw new IllegalArgumentException(field.getName());
            }
            return column.name();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFieldNameFromMethod(String methodName) throws Exception {
        if (methodName.startsWith("get")) {
            return StringUtils.uncapitalize(methodName.substring(3));
        } else if (methodName.startsWith("is")) {
            return StringUtils.uncapitalize(methodName.substring(2));
        } else {
            throw new IllegalArgumentException(methodName);
        }
    }


    public static <T> List<String> get(Class<T> entityClazz) {
        String clazzName = entityClazz.getName();
        return classToFieldListMap.computeIfAbsent(clazzName, (key) ->
            Arrays.stream(entityClazz.getDeclaredFields()).map(field -> field.getAnnotation(Column.class))
                    .filter(anno -> anno != null).map(Column::name).collect(Collectors.toList())
        );
    }
}
