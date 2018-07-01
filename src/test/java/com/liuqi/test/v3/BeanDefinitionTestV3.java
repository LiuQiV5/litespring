package com.liuqi.test.v3;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.ConstructorArgument;
import com.liuqi.beans.factory.config.RuntimeBeanReference;
import com.liuqi.beans.factory.config.TypedStringValue;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BeanDefinitionTestV3 {

    private DefaultBeanFactory factory = null;

    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
    }

    @Test
    public void testGetBeanDefinition(){
        BeanDefinition bd = factory.getBeanDefinition("petstore");

        ConstructorArgument constructorArgument = bd.getConstructorArgument();

        List<ConstructorArgument.ValueHolder> argumentValues = constructorArgument.getArgumentValues();

        Assert.assertEquals(3,argumentValues.size());

        Assert.assertTrue(argumentValues.get(0).getValue() instanceof RuntimeBeanReference);

        Assert.assertTrue(argumentValues.get(1).getValue() instanceof RuntimeBeanReference);

        Assert.assertTrue(argumentValues.get(2).getValue() instanceof TypedStringValue);

    }

}
