package com.liuqi.test.v3;

import com.liuqi.context.ApplicationContext;
import com.liuqi.context.support.ClassPathXmlApplicationContext;
import com.liuqi.dao.v3.AccountDao;
import com.liuqi.dao.v3.ItemDao;
import com.liuqi.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petstore = (PetStoreService) ctx.getBean("petstore");

        Assert.assertNotNull(petstore.getAccountDao());
        Assert.assertNotNull(petstore.getItemDao());

        Assert.assertEquals(1,petstore.getVersion());

        Assert.assertTrue(petstore.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petstore.getItemDao() instanceof ItemDao);

    }
}
