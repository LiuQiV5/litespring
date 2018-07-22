package com.liuqi.test.v4;

import com.liuqi.core.io.Resource;
import com.liuqi.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PackageResourceLoaderTest {

    /**
     * 将一个package下的class变成resource
     * @throws IOException
     */
    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.liuqi.dao.v4");
        Assert.assertEquals(2, resources.length);

    }
}
