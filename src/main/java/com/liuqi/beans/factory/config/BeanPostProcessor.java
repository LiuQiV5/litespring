package com.liuqi.beans.factory.config;

import com.liuqi.beans.BeansException;

public interface  BeanPostProcessor {

    Object beforeInitialization(Object bean, String beanName) throws BeansException;


    Object afterInitialization(Object bean, String beanName) throws BeansException;
}
