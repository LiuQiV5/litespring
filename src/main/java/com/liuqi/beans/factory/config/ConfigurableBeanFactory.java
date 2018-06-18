package com.liuqi.beans.factory.config;

import com.liuqi.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();
}
