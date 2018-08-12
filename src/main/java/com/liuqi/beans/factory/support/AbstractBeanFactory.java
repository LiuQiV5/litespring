package com.liuqi.beans.factory.support;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.config.ConfigurableBeanFactory;
import com.liuqi.context.support.DefaultSingletonBeanRegistry;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;
}
