package com.liuqi.beans.factory.support;

import com.liuqi.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanID);

    void registerBeanDefinition(String beanID,BeanDefinition bd);
}
