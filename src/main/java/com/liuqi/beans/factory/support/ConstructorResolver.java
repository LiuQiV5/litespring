package com.liuqi.beans.factory.support;

import com.liuqi.beans.BeanDefinition;
import com.liuqi.beans.ConstructorArgument;
import com.liuqi.beans.SimpleTypeConverter;
import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 查找合适的构造器，并进行自动注入
 */
public class ConstructorResolver {

    protected final Log logger = LogFactory.getLog(getClass());


    private final AbstractBeanFactory beanFactory;

    public ConstructorResolver(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        //可用的构造器
        Constructor<?> constructorToUse = null;
        //可用的参数
        Object[] argsToUse = null;
        //class对象
        Class<?> beanClass = null;
        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());

        } catch (ClassNotFoundException e) {
            throw new BeanCreationException( bd.getID(), "Instantiation of bean failed, can't resolve class", e);
        }

        //查出该bean定义的构造器
        Constructor<?>[] candidates  = beanClass.getConstructors();

        BeanDefinitionValueResolver valueResolver =
                new BeanDefinitionValueResolver(this.beanFactory);

        //构造器参数
        ConstructorArgument cargs = bd.getConstructorArgument();

        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        //查出匹配的构造器
        for (int i=0;i<candidates.length;i++) {

            Class<?> [] parameterTypes = candidates[i].getParameterTypes();

            //先判断构造器参数数量是否一致
            if(parameterTypes.length != cargs.getArgumentValues().size()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];

            //判断参数类型是否一致
            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);

            if(result) {
                constructorToUse = candidates[i];
                break;
            }
        }

        //找不到一个合适的构造函数
        if(constructorToUse == null){
            throw new BeanCreationException( bd.getID(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException( bd.getID(), "can't find a create instance using "+constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes, List<ConstructorArgument.ValueHolder> argumentValues, Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {

        for (int j=0;j<parameterTypes.length;j++) {

            ConstructorArgument.ValueHolder valueHolder = argumentValues.get(j);

            Object resolvedValue  = valueResolver.resolveValueIfNecessary(valueHolder.getValue());

            try {
                Object convertObject = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[j]);
                argsToUse[j] = convertObject;
            } catch (Exception e) {
                logger.error(e.getMessage());
                return false;
            }
        }

        return true;
    }
}
