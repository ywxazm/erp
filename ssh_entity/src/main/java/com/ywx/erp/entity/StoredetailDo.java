package com.ywx.erp.entity;

import java.io.Serializable;

/**
 * 仓库库存实体类
 * @author Administrator *
 */
public class StoredetailDo implements Serializable {
	private Long uuid;//编号
	private Long storeuuid;//仓库编号
	private Long goodsuuid;//商品编号
	private Long num;//数量

	public Long getUuid() {		
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getStoreuuid() {		
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	public Long getGoodsuuid() {		
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	public Long getNum() {		
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StoredetailDo that = (StoredetailDo) o;

		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (storeuuid != null ? !storeuuid.equals(that.storeuuid) : that.storeuuid != null) return false;
		if (goodsuuid != null ? !goodsuuid.equals(that.goodsuuid) : that.goodsuuid != null) return false;
		return num != null ? num.equals(that.num) : that.num == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (storeuuid != null ? storeuuid.hashCode() : 0);
		result = 31 * result + (goodsuuid != null ? goodsuuid.hashCode() : 0);
		result = 31 * result + (num != null ? num.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StoredetailDo{" +
				"uuid=" + uuid +
				", storeuuid=" + storeuuid +
				", goodsuuid=" + goodsuuid +
				", num=" + num +
				'}';
	}
}
