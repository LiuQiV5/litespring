package com.liuqi.test.v4;

import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.context.annotation.ClassPathBeanDefinitionScanner;
import com.liuqi.stereotype.Component;
import org.junit.Before;
import org.junit.Test;

public class ClassPathBeanDefinitionScannerTest extends BeanDefinitionScanner{

    DefaultBeanFactory factory = null;

    String basePackages = "com.liuqi.service.v4,com.liuqi.dao.v4";

    ClassPathBeanDefinitionScanner scanner = null;

    String annotation = null;

    @Before
    public void setp(){
        factory = new DefaultBeanFactory();

        scanner = new ClassPathBeanDefinitionScanner(factory);

        scanner.doScan(basePackages);

        annotation = Component.class.getName();
    }

    @Test
    public void testParseScanedBean(){

        testParseScanedBean(factory,annotation);
    }
}
