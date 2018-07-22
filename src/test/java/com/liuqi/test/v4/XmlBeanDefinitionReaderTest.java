package com.liuqi.test.v4;

import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.io.Resource;
import com.liuqi.stereotype.Component;
import org.junit.Before;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest extends BeanDefinitionScanner {

    DefaultBeanFactory factory = null;

    XmlBeanDefinitionReader reader = null;

    Resource resource = null;

    String annotation = null;

    @Before
    public void setp(){
        factory = new DefaultBeanFactory();

        reader = new XmlBeanDefinitionReader(factory);

        resource = new ClassPathResource("petstore-v4.xml");

        reader.loadBeanDefinition(resource);

        annotation = Component.class.getName();
    }

    @Test
    public void testParseScanedBean(){

        testParseScanedBean(factory,annotation);
    }
}
