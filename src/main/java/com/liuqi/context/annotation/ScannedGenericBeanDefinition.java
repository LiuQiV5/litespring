package com.liuqi.context.annotation;

import com.liuqi.beans.factory.annotation.AnnotatedBeanDefinition;
import com.liuqi.beans.factory.support.GenericBeanDefinition;
import com.liuqi.core.type.classreading.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;


    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();

        this.metadata = metadata;

        setBeanClassName(this.metadata.getClassName());
    }


    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }
}
