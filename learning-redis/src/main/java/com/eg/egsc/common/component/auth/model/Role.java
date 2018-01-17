package com.eg.egsc.common.component.auth.model;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String name;

    public Role() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
