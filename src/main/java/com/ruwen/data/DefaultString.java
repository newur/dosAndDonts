package com.ruwen.data;

/**
 * Created by ruwen on 27.03.17.
 */
public class DefaultString {

    private String value = "default";

    public DefaultString() {
        System.out.println("create new Default String");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
