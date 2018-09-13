package com.ywx.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.ywx.erp.common.BaseConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细实体类
 */
public class OrderdetailDo implements Serializable {

	private Integer uuid;			//编号
	private Integer goodsuuid;		//商品编号
	private String goodsname;		//商品名称
	private Double price;			//价格
	private Integer num;			//数量
	private Double money;			//金额
	@JSONField(format= BaseConstants.DATEFORMAT)
	private Date endtime;			//结束日期
	private Integer ender;			//库管员
	private Integer storeuuid;		//仓库编号
	private String state;			//采购：0=未入库，1=已入库  销售：0=未出库，1=已出库
	@JSONField(serialize=false)
	private OrdersDo ordersDo;	//订单

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Integer getGoodsuuid() {
		return goodsuuid;
	}

	public void setGoodsuuid(Integer goodsuuid) {
		this.goodsuuid = goodsuuid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getEnder() {
		return ender;
	}

	public void setEnder(Integer ender) {
		this.ender = ender;
	}

	public Integer getStoreuuid() {
		return storeuuid;
	}

	public void setStoreuuid(Integer storeuuid) {
		this.storeuuid = storeuuid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public OrdersDo getOrdersDo() {
		return ordersDo;
	}

	public void setOrdersDo(OrdersDo ordersDo) {
		this.ordersDo = ordersDo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderdetailDo that = (OrderdetailDo) o;

		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (goodsuuid != null ? !goodsuuid.equals(that.goodsuuid) : that.goodsuuid != null) return false;
		if (goodsname != null ? !goodsname.equals(that.goodsname) : that.goodsname != null) return false;
		if (price != null ? !price.equals(that.price) : that.price != null) return false;
		if (num != null ? !num.equals(that.num) : that.num != null) return false;
		if (money != null ? !money.equals(that.money) : that.money != null) return false;
		if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
		if (ender != null ? !ender.equals(that.ender) : that.ender != null) return false;
		if (storeuuid != null ? !storeuuid.equals(that.storeuuid) : that.storeuuid != null) return false;
		if (state != null ? !state.equals(that.state) : that.state != null) return false;
		return ordersDo != null ? ordersDo.equals(that.ordersDo) : that.ordersDo == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (goodsuuid != null ? goodsuuid.hashCode() : 0);
		result = 31 * result + (goodsname != null ? goodsname.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (num != null ? num.hashCode() : 0);
		result = 31 * result + (money != null ? money.hashCode() : 0);
		result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
		result = 31 * result + (ender != null ? ender.hashCode() : 0);
		result = 31 * result + (storeuuid != null ? storeuuid.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (ordersDo != null ? ordersDo.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "OrderdetailDo{" +
				"uuid=" + uuid +
				", goodsuuid=" + goodsuuid +
				", goodsname='" + goodsname + '\'' +
				", price=" + price +
				", num=" + num +
				", money=" + money +
				", endtime=" + endtime +
				", ender=" + ender +
				", storeuuid=" + storeuuid +
				", state='" + state + '\'' +
				", ordersDo=" + ordersDo +
				'}';
	}
}
