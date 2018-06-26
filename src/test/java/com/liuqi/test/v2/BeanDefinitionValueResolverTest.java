package com.liuqi.test.v2;

import com.liuqi.beans.factory.config.RuntimeBeanReference;
import com.liuqi.beans.factory.config.TypedStringValue;
import com.liuqi.beans.factory.support.BeanDefinitionValueResolver;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.dao.v2.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanDefinitionValueResolverTest {

    private DefaultBeanFactory factory = null;

    private XmlBeanDefinitionReader reader = null;

    private BeanDefinitionValueResolver resolver = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();

        reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));

        resolver = new BeanDefinitionValueResolver(factory);
    }

    @Test
    public void testResolveRuntimeBeanReference(){

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");

        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);

        Assert.assertTrue(value instanceof AccountDao);
    }

    @Test
    public void testResolveTypedStringValue(){
        TypedStringValue stringValue = new TypedStringValue("test");

        Object value = resolver.resolveValueIfNecessary(stringValue);

        Assert.assertNotNull(value);

        Assert.assertEquals("test",value);
    }
}
