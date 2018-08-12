package com.liuqi.test.v5;

import com.liuqi.aop.config.AspectInstanceFactory;
import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.io.Resource;
import com.liuqi.tx.TransactionManager;

import java.lang.reflect.Method;

public class AbstractV5Test {

    protected BeanFactory getBeanFactory(String configFile){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinition(resource);
        return  defaultBeanFactory;
    }

    protected Method getAdviceMethod(String methodName) throws Exception{
        return TransactionManager.class.getMethod(methodName);
    }

    protected AspectInstanceFactory getAspectInstanceFactory(String targetBeanName){
        AspectInstanceFactory factory = new AspectInstanceFactory();
        factory.setAspectBeanName(targetBeanName);
        return factory;
    }
}
