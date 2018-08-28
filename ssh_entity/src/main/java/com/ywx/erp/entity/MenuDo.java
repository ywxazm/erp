package com.ywx.erp.entity;

import java.io.Serializable;
import java.util.List;

public class MenuDo implements Serializable {

    private String menuid;
    private String menuname;
    private String icon;
    private String url;
    private List<MenuDo>menus;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
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

    public List<MenuDo> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDo> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuDo menuDo = (MenuDo) o;

        if (!menuid.equals(menuDo.menuid)) return false;
        if (!menuname.equals(menuDo.menuname)) return false;
        if (!icon.equals(menuDo.icon)) return false;
        if (!url.equals(menuDo.url)) return false;
        return menus.equals(menuDo.menus);
    }

    @Override
    public int hashCode() {
        int result = menuid.hashCode();
        result = 31 * result + menuname.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + menus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MenuDo{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", menus=" + menus +
                '}';
    }
}
