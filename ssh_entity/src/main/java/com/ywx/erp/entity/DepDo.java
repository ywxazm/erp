package com.ywx.erp.entity;

import java.io.Serializable;

public class DepDo implements Serializable {


    private Long uuid;      //部门ID
    private String name;    //部门名称
    private String tele;    //部门电话

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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

        if (!uuid.equals(depDo.uuid)) return false;
        if (!name.equals(depDo.name)) return false;
        return tele.equals(depDo.tele);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + tele.hashCode();
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
