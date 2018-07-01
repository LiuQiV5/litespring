package com.liuqi.beans.factory.xml;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.ConstructorArgument;
import com.liuqi.beans.PropertyValue;
import com.liuqi.beans.factory.BeanDefinitionStoreException;
import com.liuqi.beans.factory.config.RuntimeBeanReference;
import com.liuqi.beans.factory.config.TypedStringValue;
import com.liuqi.beans.factory.support.BeanDefinitionRegistry;
import com.liuqi.beans.factory.support.GenericBeanDefinition;
import com.liuqi.core.io.Resource;
import com.liuqi.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {

    public static final  String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    BeanDefinitionRegistry registry;

    protected final Log logger = LogFactory.getLog(getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {
        InputStream is = null;
        try {
            is = resource.getInputStream();

            SAXReader reader = new SAXReader();

            Document doc = reader.read(is);

            Element root = doc.getRootElement();//beans

            Iterator<Element> iter = root.elementIterator();

            while (iter.hasNext()) {
                Element ele = (Element)iter.next();

                String id = ele.attributeValue(ID_ATTRIBUTE);

                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);

                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);

                if (ele.attribute(SCOPE_ATTRIBUTE)!=null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }

                //解析xml将constructorArgument设置进bd
                parseConstructorArgElements(ele,bd);
                //解析xml将property设置进bd
                parsePropertyElement(ele,bd);

                this.registry.registerBeanDefinition(id,bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(),e);
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

    private void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while(iter.hasNext()){
            Element ele = (Element)iter.next();
            parseConstructorArgElement(ele, bd);
        }
    }

    private void parseConstructorArgElement(Element ele, BeanDefinition bd) {

        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);
    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {

        Iterator iter= ele.elementIterator(PROPERTY_ELEMENT);
        while(iter.hasNext()){
            Element propElem = (Element)iter.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }


            Object val = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);

            bd.getPropertyValues().add(pv);
        }
    }

    private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {

        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";


        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE)!=null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) !=null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        }
        else {

            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }


}
