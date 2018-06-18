package com.liuqi.beans.factory.support;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.BeanDefinitionStoreException;
import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.beans.factory.config.ConfigurableBeanFactory;
import com.liuqi.context.support.DefaultSingletonBeanRegistry;
import com.liuqi.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {


    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {

        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }

    @Override
    public Object getBean(String beanID) {

        BeanDefinition bd = this.beanDefinitionMap.get(beanID);

        if (bd == null) {
            throw new BeanCreationException("Bean  Definition does not exist");
        }

        if(bd.isSingleton()){
            Object bean = this.getSingleton(beanID);
            if(bean == null){
                bean = createBean(bd);
                this.registerSingleton(beanID, bean);
            }
            return bean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd) {
        ClassLoader cl = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
