package com.liuqi.support;

import com.liuqi.beans.definition.BeanDefinition;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.BeanDefinitionStoreException;
import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {

    public static final  String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    private  final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configfile) {
        loadBeanDefinition(configfile);
    }

    private void loadBeanDefinition(String configfile) {
        InputStream is = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();

            is = cl.getResourceAsStream(configfile);

            SAXReader reader = new SAXReader();

            Document doc = reader.read(is);

            Element root = doc.getRootElement();//beans

            Iterator<Element> iter = root.elementIterator();

            while (iter.hasNext()) {
                Element ele = (Element)iter.next();

                String id = ele.attributeValue(ID_ATTRIBUTE);

                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);

                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);

                this.beanDefinitionMap.put(id,bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML Document",e);
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {

        return  this.beanDefinitionMap.get(beanID);
    }

    @Override
    public Object getBean(String beanID) {

        BeanDefinition bd = this.beanDefinitionMap.get(beanID);

        if(bd == null) {
            throw new BeanCreationException("Bean  Definition does not exist");
        }

        ClassLoader cl = ClassUtils.getDefaultClassLoader();

        String beanClassName = bd.getBeanClassName();

        try {

            Class<?> clz = cl.loadClass(beanClassName);

            return  clz.newInstance();

        } catch (Exception e) {
            throw new BeanCreationException("create Bean for " + beanClassName +" failed",e);
        }
    }
}
