package com.liuqi.aop.aspectj;

import com.liuqi.aop.Advice;
import com.liuqi.aop.AspectJExpressionPointcut;
import com.liuqi.aop.Pointcut;
import com.liuqi.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {

    protected Method adviceMethod;
    protected AspectJExpressionPointcut pointcut;
    protected AspectInstanceFactory aspectInstanceFactory;



    public AbstractAspectJAdvice(Method adviceMethod,
                                 AspectJExpressionPointcut pointcut,
                                 AspectInstanceFactory aspectInstanceFactory){

        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.aspectInstanceFactory = aspectInstanceFactory;
    }


    public void invokeAdviceMethod() throws  Throwable{

        adviceMethod.invoke(aspectInstanceFactory.getAspectInstance());
    }
    public Pointcut getPointcut(){
        return this.pointcut;
    }
    public Method getAdviceMethod() {
        return adviceMethod;
    }

    public Object getAdviceInstance() throws Exception {
        return aspectInstanceFactory.getAspectInstance();
    }
}
