package com.liuqi.test.v2;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.PropertyValue;
import com.liuqi.beans.factory.config.RuntimeBeanReference;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.beans.factory.xml.XmlBeanDefinitionReader;
import com.liuqi.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BeanDefinitionTestV2 {

    private DefaultBeanFactory factory = null;

    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
    }

    @Test
    public void testGetBeanDefinition(){
        BeanDefinition bd = factory.getBeanDefinition("petstore");

        List<PropertyValue> pvs = bd.getPropertyValues();

         Assert.assertTrue(pvs.size() == 4);

        {
            PropertyValue pv = this.getPropertyValue("accountDao",pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValue("itemDao",pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    public PropertyValue getPropertyValue(String name,List<PropertyValue> pvs){
        if (pvs.size()>0 && pvs!=null) {
            for (PropertyValue pv : pvs) {
                if(name.equals(pv.getName())) {
                    return pv;
                }
            }
        }
        return null;
    }

}
