package com.liuqi.test.v2;

import com.liuqi.context.ApplicationContext;
import com.liuqi.context.support.ClassPathXmlApplicationContext;
import com.liuqi.dao.v2.AccountDao;
import com.liuqi.dao.v2.ItemDao;
import com.liuqi.service.v2.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petstore = (PetStoreService) ctx.getBean("petstore");

        Assert.assertNotNull(petstore.getAccountDao());
        Assert.assertNotNull(petstore.getItemDao());

        Assert.assertEquals("liuqi",petstore.getOwner());
        Assert.assertEquals(2,petstore.getVersion());

        Assert.assertTrue(petstore.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petstore.getItemDao() instanceof ItemDao);

        Assert.assertTrue(petstore.getAccountDao().getItemDao() instanceof ItemDao);
    }
}
