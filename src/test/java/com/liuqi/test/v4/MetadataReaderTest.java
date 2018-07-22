package com.liuqi.test.v4;

import com.liuqi.core.annotation.AnnotationAttributes;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.type.ClassMetadata;
import com.liuqi.core.type.classreading.AnnotationMetadata;
import com.liuqi.core.type.classreading.MetadataReader;
import com.liuqi.core.type.classreading.SimpleMetadataReader;
import com.liuqi.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/liuqi/service/v4/PetStoreService.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
        //注意：不需要单独使用ClassMetadata
        //ClassMetadata clzMetadata = reader.getClassMetadata();
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        //注：下面对class metadata的测试并不充分
        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("com.liuqi.service.v4.PetStoreService", amd.getClassName());

    }
}
