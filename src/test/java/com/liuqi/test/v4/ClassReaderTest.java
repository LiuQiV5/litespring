package com.liuqi.test.v4;

import com.liuqi.core.annotation.AnnotationAttributes;
import com.liuqi.core.io.ClassPathResource;
import com.liuqi.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.liuqi.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/liuqi/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        //获取类的元数据
        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("com.liuqi.service.v4.PetStoreService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnonation() throws Exception{
        ClassPathResource resource = new ClassPathResource("com/liuqi/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "com.liuqi.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("petStore", attributes.get("value"));

    }
}
