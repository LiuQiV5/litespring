package com.liuqi.context.support;

import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.context.ApplicationContext;
import com.liuqi.core.io.Resource;
import com.liuqi.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configfile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(configfile);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    protected abstract Resource getResourceByPath(String path);

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
