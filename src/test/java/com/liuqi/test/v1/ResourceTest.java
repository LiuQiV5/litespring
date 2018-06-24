package com.liuqi.test.v1;

import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.io.FileSystemResource;
import com.liuqi.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception{
        Resource r = new ClassPathResource("petstore-v1.xml");

        InputStream is = null;
        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if(is!=null) {
                is.close();
            }
        }
    }

    @Test
    public void testFileSystemResource()throws Exception{
        Resource r = new FileSystemResource("E:\\idea_workspace\\litespring\\src\\test\\resources\\petstore-v1.xml");

        InputStream is = null;
        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if(is!=null) {
                is.close();
            }
        }
    }
}
