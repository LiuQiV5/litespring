package com.liuqi.test;

import com.liuqi.context.ApplicationContext;
import com.liuqi.context.support.ClassPathXmlApplicationContext;
import com.liuqi.context.support.FileSystemXmlApplicationContext;
import com.liuqi.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testGetBean(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService store = (PetStoreService) ctx.getBean("petstore");
        Assert.assertNotNull(store);
    }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("E:\\ideaworkspace\\litespring\\src\\test\\resources\\petstore-v1.xml");
        PetStoreService store = (PetStoreService) ctx.getBean("petstore");
        Assert.assertNotNull(store);
    }

}
