package com.liuqi.beans.factory;

import com.liuqi.beans.BeanDefinition;

public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanID);

    Object getBean(String beanID);
}
