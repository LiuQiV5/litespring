package com.liuqi.core.type.classreading;

import com.liuqi.core.annotation.AnnotationAttributes;
import com.liuqi.core.type.ClassMetadata;

import java.util.Set;

public interface AnnotationMetadata extends ClassMetadata {

    Set<String> getAnnotationTypes();


    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
