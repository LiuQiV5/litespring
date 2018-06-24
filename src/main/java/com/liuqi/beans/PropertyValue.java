package com.liuqi.beans;

public class PropertyValue {

    private final String name;

    private final Object value;

    //是否被转换过
    private boolean converted = false;

    //转换成的实例
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    //为什么这边要加synchronized关键字
    public synchronized boolean isConverted() {
        return converted;
    }

    public synchronized void setConverted(boolean converted) {
        this.converted = converted;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }
}
