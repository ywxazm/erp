package com.ywx.erp.entity;

import java.io.Serializable;
import java.util.List;

public class RoleDo implements Serializable {

    private Integer uuid;               //角色id
    private String name;                //角色名称
    private List<MenuDo> menuDoList;    //角色下可查看菜单

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDo> getMenuDoList() {
        return menuDoList;
    }

    public void setMenuDoList(List<MenuDo> menuDoList) {
        this.menuDoList = menuDoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDo roleDo = (RoleDo) o;

        if (uuid != null ? !uuid.equals(roleDo.uuid) : roleDo.uuid != null) return false;
        if (name != null ? !name.equals(roleDo.name) : roleDo.name != null) return false;
        return menuDoList != null ? menuDoList.equals(roleDo.menuDoList) : roleDo.menuDoList == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (menuDoList != null ? menuDoList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", menuDoList=" + menuDoList +
                '}';
    }
}
