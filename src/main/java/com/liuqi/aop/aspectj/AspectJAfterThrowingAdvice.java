package com.liuqi.aop.aspectj;

import com.liuqi.aop.AspectJExpressionPointcut;
import com.liuqi.aop.config.AspectInstanceFactory;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice  {



    public AspectJAfterThrowingAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut,  AspectInstanceFactory aspectInstanceFactory) {

        super(adviceMethod, pointcut, aspectInstanceFactory);
    }


    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }
        catch (Throwable t) {
            invokeAdviceMethod();
            throw t;
        }
    }

}
