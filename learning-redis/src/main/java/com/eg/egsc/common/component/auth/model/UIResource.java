package com.eg.egsc.common.component.auth.model;

import java.io.Serializable;
import java.util.List;

public class UIResource implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String icon;
    private String url;
    private List<Menu> menus;

    public UIResource() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
