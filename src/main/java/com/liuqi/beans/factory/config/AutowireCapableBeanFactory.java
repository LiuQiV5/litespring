package com.liuqi.beans.factory.config;

import com.liuqi.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    public Object resolveDependency(DependencyDescriptor descriptor);
}
