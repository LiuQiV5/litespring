package com.liuqi.test.v3;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.factory.support.ConstructorResolver;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConstructorResolverTest {

    private DefaultBeanFactory factory = null;

    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
    }

    @Test
    public void testAutowireConstructor() {
        BeanDefinition bd = factory.getBeanDefinition("petstore");

        ConstructorResolver resolver = new ConstructorResolver(factory);

        PetStoreService petStore = (PetStoreService)resolver.autowireConstructor(bd);

        // 验证参数version 正确地通过此构造函数做了初始化
        // PetStoreService(AccountDao accountDao, ItemDao itemDao,int version)
        Assert.assertEquals(1, petStore.getVersion());

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }
}
