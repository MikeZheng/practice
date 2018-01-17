package com.eg.egsc.common.component.auth.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String department;
    private List<Role> roles;
    private List<String> serviceIds;
    private List<UIResource> uiResources;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public List<UIResource> getUiResources() {
        return uiResources;
    }

    public void setUiResources(List<UIResource> uiResources) {
        this.uiResources = uiResources;
    }
}
