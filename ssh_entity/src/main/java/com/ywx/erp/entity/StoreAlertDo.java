package com.ywx.erp.entity;

import java.io.Serializable;

public class StoreAlertDo implements Serializable {

    private Integer guuid;     //商品ID
    private String gname;   //商品名称
    private String sname;   //仓库名称
    private Integer sdnum;     //库存数量
    private Integer odnum;     //订单需求数量

    public Integer getGuuid() {
        return guuid;
    }

    public void setGuuid(Integer guuid) {
        this.guuid = guuid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSdnum() {
        return sdnum;
    }

    public void setSdnum(Integer sdnum) {
        this.sdnum = sdnum;
    }

    public Integer getOdnum() {
        return odnum;
    }

    public void setOdnum(Integer odnum) {
        this.odnum = odnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreAlertDo that = (StoreAlertDo) o;

        if (guuid != null ? !guuid.equals(that.guuid) : that.guuid != null) return false;
        if (gname != null ? !gname.equals(that.gname) : that.gname != null) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (sdnum != null ? !sdnum.equals(that.sdnum) : that.sdnum != null) return false;
        return odnum != null ? odnum.equals(that.odnum) : that.odnum == null;
    }

    @Override
    public int hashCode() {
        int result = guuid != null ? guuid.hashCode() : 0;
        result = 31 * result + (gname != null ? gname.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (sdnum != null ? sdnum.hashCode() : 0);
        result = 31 * result + (odnum != null ? odnum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StoreAlertDo{" +
                "guuid=" + guuid +
                ", gname='" + gname + '\'' +
                ", sname='" + sname + '\'' +
                ", sdnum=" + sdnum +
                ", odnum=" + odnum +
                '}';
    }
}
