package com.liuqi.test.v5;

import com.liuqi.aop.AspectJExpressionPointcut;
import com.liuqi.aop.aspectj.AspectJAfterReturningAdvice;
import com.liuqi.aop.aspectj.AspectJBeforeAdvice;
import com.liuqi.aop.config.AspectInstanceFactory;
import com.liuqi.aop.framework.AopConfig;
import com.liuqi.aop.framework.AopConfigSupport;
import com.liuqi.aop.framework.CglibProxyFactory;
import com.liuqi.beans.factory.BeanFactory;
import com.liuqi.service.v5.PetStoreService;
import com.liuqi.tx.TransactionManager;
import com.liuqi.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CglibAopProxyTest  extends AbstractV5Test{

    private  AspectJBeforeAdvice beforeAdvice = null;
    private  AspectJAfterReturningAdvice afterAdvice = null;
    private  AspectJExpressionPointcut pc = null;
    private BeanFactory beanFactory = null;
    private AspectInstanceFactory aspectInstanceFactory = null;

    private TransactionManager tx;

    @Before
    public  void setUp() throws Exception{
        MessageTracker.clearMsgs();

        tx = new TransactionManager();
        String expression = "execution(* com.liuqi.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beanFactory = this.getBeanFactory("petstore-v5.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);

        beforeAdvice = new AspectJBeforeAdvice(
                getAdviceMethod("start"),
                pc,
                aspectInstanceFactory);

        afterAdvice = new AspectJAfterReturningAdvice(
                getAdviceMethod("commit"),
                pc,
                aspectInstanceFactory);

    }

    @Test
    public void testGetProxy(){

        AopConfig config = new AopConfigSupport();

        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new PetStoreService());


        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);

        PetStoreService proxy = (PetStoreService)proxyFactory.getProxy();

        proxy.placeOrder();


        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));

        proxy.toString();
    }

}
