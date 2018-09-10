package com.ywx.erp.entity;

import java.io.Serializable;

public class GoodstypeDo implements Serializable{

    private Integer uuid;      //商品类型ID
    private String name;    //商品类型名称

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodstypeDo that = (GoodstypeDo) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GoodstypeDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
