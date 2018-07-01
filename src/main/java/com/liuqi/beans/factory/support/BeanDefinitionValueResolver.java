package com.liuqi.beans.factory.support;

import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.beans.factory.config.RuntimeBeanReference;
import com.liuqi.beans.factory.config.TypedStringValue;

public class BeanDefinitionValueResolver {

    private final BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory factory) {
        this.beanFactory = factory;
    }


    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;

        }else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else{
            //TODO
            throw new RuntimeException("the value " + value +" has not implemented");
        }
    }
}
