package com.liuqi.context.support;

import com.liuqi.core.io.FileSystemResource;
import com.liuqi.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configfile) {
        super(configfile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
