package com.zrich.bean;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Cat extends Animal {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(super.toString())
                .append("type", type)
                .toString();
    }
}
