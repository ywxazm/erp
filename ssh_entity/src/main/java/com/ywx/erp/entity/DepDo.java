package com.ywx.erp.entity;

import java.io.Serializable;

/**
 * 部门
 */
public class DepDo implements Serializable {

    private Integer uuid;      //部门ID
    private String name;    //部门名称
    private String tele;    //部门电话

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

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepDo depDo = (DepDo) o;

        if (uuid != null ? !uuid.equals(depDo.uuid) : depDo.uuid != null) return false;
        if (name != null ? !name.equals(depDo.name) : depDo.name != null) return false;
        return tele != null ? tele.equals(depDo.tele) : depDo.tele == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tele != null ? tele.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DepDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
