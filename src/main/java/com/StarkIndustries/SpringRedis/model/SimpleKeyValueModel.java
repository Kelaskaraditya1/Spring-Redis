package com.StarkIndustries.SpringRedis.model;

public class SimpleKeyValueModel {

    private String key;

    private Object value;

    private String className;

    public SimpleKeyValueModel(String key, Object value, String className) {
        this.key = key;
        this.value = value;
        this.className = className;
    }

    public SimpleKeyValueModel(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public SimpleKeyValueModel(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "SimpleKeyValueModel{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
