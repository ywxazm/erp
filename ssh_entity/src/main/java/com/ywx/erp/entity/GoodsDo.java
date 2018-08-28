package com.ywx.erp.entity;

import java.io.Serializable;

/*
* 商品表
* */
public class GoodsDo implements Serializable{

    //编号
    private Long uuid;
    //名称
    private String name;
    //产地
    private String origin;
    //厂家
    private String producer;
    //计量单位
    private String unit;
    //进货价格
    private Double inprice;
    //销售价格
    private Double outprice;
    //商品类型
    private GoodsTypeDo goodsTypeDo;

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

    public GoodsTypeDo getGoodsTypeDo() {
        return goodsTypeDo;
    }

    public void setGoodsTypeDo(GoodsTypeDo goodsTypeDo) {
        this.goodsTypeDo = goodsTypeDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsDo goodsDo = (GoodsDo) o;

        if (!uuid.equals(goodsDo.uuid)) return false;
        if (!name.equals(goodsDo.name)) return false;
        if (!origin.equals(goodsDo.origin)) return false;
        if (!producer.equals(goodsDo.producer)) return false;
        if (!unit.equals(goodsDo.unit)) return false;
        if (!inprice.equals(goodsDo.inprice)) return false;
        if (!outprice.equals(goodsDo.outprice)) return false;
        return goodsTypeDo.equals(goodsDo.goodsTypeDo);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + producer.hashCode();
        result = 31 * result + unit.hashCode();
        result = 31 * result + inprice.hashCode();
        result = 31 * result + outprice.hashCode();
        result = 31 * result + goodsTypeDo.hashCode();
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
                ", goodsTypeDo=" + goodsTypeDo +
                '}';
    }
}
