package com.ywx.erp.entity;

import java.io.Serializable;
import java.util.List;

public class MenuDo implements Serializable {

    private String menuid;
    private String menuname;
    private String icon;
    private String url;
    private String pid;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

        if (menuid != null ? !menuid.equals(menuDo.menuid) : menuDo.menuid != null) return false;
        if (menuname != null ? !menuname.equals(menuDo.menuname) : menuDo.menuname != null) return false;
        if (icon != null ? !icon.equals(menuDo.icon) : menuDo.icon != null) return false;
        if (url != null ? !url.equals(menuDo.url) : menuDo.url != null) return false;
        if (pid != null ? !pid.equals(menuDo.pid) : menuDo.pid != null) return false;
        return menus != null ? menus.equals(menuDo.menus) : menuDo.menus == null;
    }

    @Override
    public int hashCode() {
        int result = menuid != null ? menuid.hashCode() : 0;
        result = 31 * result + (menuname != null ? menuname.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (menus != null ? menus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuDo{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", menus=" + menus +
                '}';
    }
}
