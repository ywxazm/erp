package com.ywx.erp.entity;

import java.io.Serializable;

/**
 * 仓库库存实体类
 * @author Administrator *
 */
public class StoredetailDo implements Serializable {

	public static final String STATE_NOT_OUT = "0";	//未出库
	public static final String STATE_OUT = "1";		//已出库

	private Integer uuid;			//编号
	private Integer storeuuid;		//仓库编号
	private String storename;	//仓库名称
	private Integer goodsuuid;		//商品编号
	private String goodsname;	//商品名称
	private Integer num;			//数量

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Integer getStoreuuid() {
		return storeuuid;
	}

	public void setStoreuuid(Integer storeuuid) {
		this.storeuuid = storeuuid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StoredetailDo that = (StoredetailDo) o;

		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (storeuuid != null ? !storeuuid.equals(that.storeuuid) : that.storeuuid != null) return false;
		if (storename != null ? !storename.equals(that.storename) : that.storename != null) return false;
		if (goodsuuid != null ? !goodsuuid.equals(that.goodsuuid) : that.goodsuuid != null) return false;
		if (goodsname != null ? !goodsname.equals(that.goodsname) : that.goodsname != null) return false;
		return num != null ? num.equals(that.num) : that.num == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (storeuuid != null ? storeuuid.hashCode() : 0);
		result = 31 * result + (storename != null ? storename.hashCode() : 0);
		result = 31 * result + (goodsuuid != null ? goodsuuid.hashCode() : 0);
		result = 31 * result + (goodsname != null ? goodsname.hashCode() : 0);
		result = 31 * result + (num != null ? num.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StoredetailDo{" +
				"uuid=" + uuid +
				", storeuuid=" + storeuuid +
				", storename='" + storename + '\'' +
				", goodsuuid=" + goodsuuid +
				", goodsname='" + goodsname + '\'' +
				", num=" + num +
				'}';
	}
}
