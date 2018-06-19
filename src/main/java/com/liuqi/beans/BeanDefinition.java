package com.liuqi.beans;

    public interface BeanDefinition {
        /**
         * 接口中定义的成员变量默认都是public static final ,都是公共的，静态的且不可变的
         */
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";
    public boolean isSingleton();
    public boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();
}
