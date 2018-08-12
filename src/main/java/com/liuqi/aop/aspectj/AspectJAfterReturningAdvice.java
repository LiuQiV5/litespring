package com.liuqi.aop.aspectj;

import com.liuqi.aop.AspectJExpressionPointcut;
import com.liuqi.aop.config.AspectInstanceFactory;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice{

    public AspectJAfterReturningAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, AspectInstanceFactory aspectInstanceFactory){
        super(adviceMethod,pointcut,aspectInstanceFactory);
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        Object o = mi.proceed();
        //例如：调用TransactionManager的commit方法
        this.invokeAdviceMethod();
        return o;
    }

}
