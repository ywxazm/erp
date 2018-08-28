package com.ywx.erp.entity;

import java.io.Serializable;

public class GoodsTypeDo implements Serializable{

    private Long uuid;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsTypeDo that = (GoodsTypeDo) o;

        if (!uuid.equals(that.uuid)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GoodsTypeDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
