package com.zrich.bean;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Dog extends Animal {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(super.toString())
                .append("color", color)
                .toString();
    }
}
