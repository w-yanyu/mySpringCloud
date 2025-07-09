package org.remote.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Objects;

public class AnnotationUtils {

    public static <T extends Annotation> String getMethodNameFromAnnotation(Method method, Class<T> annotationClass, String attributeName) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(annotationClass);
        if (StringUtils.isEmpty(attributeName)) {
            throw new IllegalArgumentException("parameter attributeName is empty");
        }

        String methodName = method.getName();
        Object attribute = getAnnotationAttribute(method, annotationClass, attributeName);
        if (attribute != null) {
            methodName = attribute.toString();
        }
        return methodName;
    }

    public static <T extends Annotation> String getBeanNameFromClass(Class<?> clazz, Class<T> annotationClass, String attributeName) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(annotationClass);
        if (StringUtils.isEmpty(attributeName)) {
            throw new IllegalArgumentException("parameter attributeName is empty");
        }

        String className = clazz.getSimpleName();
        Object attribute = getAnnotationAttribute(clazz, annotationClass, attributeName);
        if (attribute != null) {
            className = attribute.toString();
        }
        return StringUtils.determineBeanNameFromClass(className);
    }

    private static <T extends Annotation> Object getAnnotationAttribute(AnnotatedElement annotatedElement, Class<T> annotationClass, String attributeName) {
        if (annotatedElement.isAnnotationPresent(annotationClass)) {
            Annotation annotation = annotatedElement.getAnnotation(annotationClass);
            try {
                Object attributeValue = annotation.getClass().getMethod(attributeName).invoke(annotation);
                if (attributeValue != null && !attributeValue.toString().isEmpty()) {
                    return attributeValue;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
