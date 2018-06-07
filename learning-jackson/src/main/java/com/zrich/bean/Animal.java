package com.zrich.bean;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author ZhenFuZheng
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes({@JsonSubTypes.Type(value = Cat.class, name = "cat"), @JsonSubTypes.Type(value = Dog.class, name = "dog")})
public class Animal {
    private String name;

    private Size size;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("size", size)
                .toString();
    }
}
