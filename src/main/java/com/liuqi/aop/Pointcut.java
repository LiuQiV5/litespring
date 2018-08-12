package com.liuqi.aop;

public interface Pointcut {
    MethodMatcher getMethodMatcher();
    String getExpression();
}
