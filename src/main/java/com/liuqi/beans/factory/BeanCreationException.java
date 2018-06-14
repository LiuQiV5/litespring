package com.liuqi.beans.factory;

import com.liuqi.beans.definition.BeansException;

public class BeanCreationException extends BeansException {

    private String beanName;

    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg,Throwable cause) {
        super(msg,cause);
    }

    public BeanCreationException(String beanName,String msg) {
        super("Error Creating Bean with name '"+ beanName +"';"+msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName,String msg,Throwable cause) {
        this(beanName,msg);
        initCause(cause);
    }

    public String getBeanName() {
        return  this.beanName;
    }
}
