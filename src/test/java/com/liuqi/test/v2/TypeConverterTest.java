package com.liuqi.test.v2;

import com.liuqi.beans.SimpleTypeConverter;
import com.liuqi.beans.TypeConverter;
import com.liuqi.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TypeConverterTest {

    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3,i.intValue());

        try{
            converter.convertIfNecessary("3.1", Integer.class);
        }catch(TypeMismatchException e){
            return;
        }
        fail();
    }
    @Test
    public void testConvertStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true,b.booleanValue());

        try{
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        }catch(TypeMismatchException e){
            return;
        }
        fail();
    }

}
