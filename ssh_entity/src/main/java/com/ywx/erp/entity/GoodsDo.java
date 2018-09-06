package com.ywx.erp.entity;

import java.io.Serializable;

/*
* 商品表
* */
public class GoodsDo implements Serializable{

    private Long uuid;      //编号
    private String name;    //名称
    private String origin;  //产地
    private String producer;    //厂家
    private String unit;    //计量单位
    private Double inprice; //进货价格
    private Double outprice;    //销售价格
    private GoodstypeDo goodstypeDo;    //商品类型

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getInprice() {
        return inprice;
    }

    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }

    public Double getOutprice() {
        return outprice;
    }

    public void setOutprice(Double outprice) {
        this.outprice = outprice;
    }

    public GoodstypeDo getGoodstypeDo() {
        return goodstypeDo;
    }

    public void setGoodstypeDo(GoodstypeDo goodstypeDo) {
        this.goodstypeDo = goodstypeDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsDo goodsDo = (GoodsDo) o;

        if (uuid != null ? !uuid.equals(goodsDo.uuid) : goodsDo.uuid != null) return false;
        if (name != null ? !name.equals(goodsDo.name) : goodsDo.name != null) return false;
        if (origin != null ? !origin.equals(goodsDo.origin) : goodsDo.origin != null) return false;
        if (producer != null ? !producer.equals(goodsDo.producer) : goodsDo.producer != null) return false;
        if (unit != null ? !unit.equals(goodsDo.unit) : goodsDo.unit != null) return false;
        if (inprice != null ? !inprice.equals(goodsDo.inprice) : goodsDo.inprice != null) return false;
        if (outprice != null ? !outprice.equals(goodsDo.outprice) : goodsDo.outprice != null) return false;
        return goodstypeDo != null ? goodstypeDo.equals(goodsDo.goodstypeDo) : goodsDo.goodstypeDo == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (inprice != null ? inprice.hashCode() : 0);
        result = 31 * result + (outprice != null ? outprice.hashCode() : 0);
        result = 31 * result + (goodstypeDo != null ? goodstypeDo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GoodsDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", producer='" + producer + '\'' +
                ", unit='" + unit + '\'' +
                ", inprice=" + inprice +
                ", outprice=" + outprice +
                ", goodstypeDo=" + goodstypeDo +
                '}';
    }
}
