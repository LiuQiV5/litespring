package com.liuqi.context.support;

import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String configfile) {
        super(configfile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }

}
