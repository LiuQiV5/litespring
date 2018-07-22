package com.liuqi.beans.factory.annotation;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.core.type.classreading.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getMetadata();
}
