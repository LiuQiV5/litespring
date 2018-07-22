package com.liuqi.beans.factory.annotation;

import com.liuqi.beans.factory.BeanCreationException;
import com.liuqi.beans.factory.config.AutowireCapableBeanFactory;
import com.liuqi.beans.factory.config.DependencyDescriptor;
import com.liuqi.beans.factory.support.DefaultBeanFactory;
import com.liuqi.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AutowiredFieldElement extends InjectionElement {

    boolean required;

    public AutowiredFieldElement(Field f,boolean required,AutowireCapableBeanFactory factory) {
        super(f,factory);
        this.required = required;
    }

    public Field getField(){
        return (Field)this.member;
    }
    @Override
    public void inject(Object target) {

        Field field = this.getField();
        try {

            DependencyDescriptor desc = new DependencyDescriptor(field, this.required);

            Object value = factory.resolveDependency(desc);

            if (value != null) {
                //当要被注入的元素的访问符为private时，要设为可访问才能注入
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        }
        catch (Throwable ex) {
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }
    }
}
