package com.liuqi.test;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.BeanDefinitionStoreException;
import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.service.PetStoreService;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {

    @Test
    public  void testGetBean(){

        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition bd = factory.getBeanDefinition("petstore");

        assertEquals("com.liuqi.service.PetStoreService",bd.getBeanClassName());

        PetStoreService petstore = (PetStoreService)factory.getBean("petstore");

        assertNotNull(petstore);
    }

    @Test
    public void testInvalidBean(){

        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");

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
            new DefaultBeanFactory("xxxxxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }

        Assert.fail("expect BeanDefinitionStoreException");
    }
}