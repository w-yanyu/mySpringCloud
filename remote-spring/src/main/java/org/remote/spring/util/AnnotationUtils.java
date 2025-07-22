package org.remote.spring.util;

import java.lang.annotation.Annotation;

public class AnnotationUtils {
    public static boolean isInterfaceWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return clazz.isInterface() && clazz.isAnnotationPresent(annotationClass);
    }

    public static boolean implementsInterfaceAndHasAnnotation(Class<?> clazz, Class<?> interfaceClass, Class<? extends Annotation> annotationClass) {
        if (clazz == null || interfaceClass == null || annotationClass == null) {
            return false;
        }

        boolean implementsInterface = interfaceClass.isAssignableFrom(clazz);

        boolean hasAnnotation = clazz.isAnnotationPresent(annotationClass);

        return implementsInterface && hasAnnotation;
    }
}
