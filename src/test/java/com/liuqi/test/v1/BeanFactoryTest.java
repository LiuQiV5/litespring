package com.liuqi.test.v1;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.BeanDefinitionStoreException;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.io.Resource;
import com.liuqi.service.v1.PetStoreService;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;

    XmlBeanDefinitionReader reader = null;

    @Before
    public void  setUP(){
        factory = new DefaultBeanFactory();

        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public  void testGetBean(){

        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petstore");

        assertTrue(bd.isSingleton());

        assertFalse(bd.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        assertEquals("com.liuqi.service.v1.PetStoreService",bd.getBeanClassName());

        PetStoreService petstore = (PetStoreService)factory.getBean("petstore");

        assertNotNull(petstore);

        PetStoreService petstore1 = (PetStoreService)factory.getBean("petstore");

        assertTrue(petstore.equals(petstore1));
    }

    @Test
    public void testInvalidBean(){

        Resource resource = new ClassPathResource("petstore-v1.xml");

        reader.loadBeanDefinition(resource);

        try {

            factory.getBean("invalidBean");

        } catch (BeanCreationException e) {
            return;
        }

        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXML(){
        try {
            reader.loadBeanDefinition(new ClassPathResource("xxxxxx.xml"));
        } catch (BeanDefinitionStoreException  e) {
            return;
        }

        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testBeanISPrototype(){
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));

        BeanDefinition bd = factory.getBeanDefinition("prototypeBean");

        assertFalse(bd.isSingleton());

        assertTrue(bd.isPrototype());

        assertEquals(BeanDefinition.SCOPE_PROTOTYPE,bd.getScope());

        assertEquals("com.liuqi.service.v1.PetStoreService",bd.getBeanClassName());

        PetStoreService petstore = (PetStoreService)factory.getBean("prototypeBean");

        assertNotNull(petstore);

        PetStoreService petstore1 = (PetStoreService)factory.getBean("prototypeBean");

        assertFalse(petstore.equals(petstore1));
    }
}