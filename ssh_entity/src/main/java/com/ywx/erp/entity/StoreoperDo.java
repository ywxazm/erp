package com.ywx.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.ywx.erp.common.BaseConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库操作记录实体类
 * @author Administrator *
 */
public class StoreoperDo implements Serializable {

	public static final String STATE_NOT_IN = "0";	//未入库
	public static final String STATE_IN = "1";		//已入库
	public static final String STATE_NOT_OUT = "0";	//未出库
	public static final String STATE_OUT = "1";		//已出库
	public static final String TYPE_IN = "1";		//入库
	public static final String TYPE_0UT = "2";		//出库

	private Integer uuid;		//编号
	private Integer empuuid;	//操作员工编号
	private String empname;	//操作员工名称
	@JSONField(format = BaseConstants.DATEFORMAT)
	private Date opertime;	//操作日期
	private Integer storeuuid;	//仓库编号
	private String storename;//仓库名称
	private Integer goodsuuid;	//商品编号
	private String goodsname;//商品名称
	private Integer num;		//数量
	private String type;	//1：入库 2：出库

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Integer getEmpuuid() {
		return empuuid;
	}

	public void setEmpuuid(Integer empuuid) {
		this.empuuid = empuuid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StoreoperDo that = (StoreoperDo) o;

		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (empuuid != null ? !empuuid.equals(that.empuuid) : that.empuuid != null) return false;
		if (empname != null ? !empname.equals(that.empname) : that.empname != null) return false;
		if (opertime != null ? !opertime.equals(that.opertime) : that.opertime != null) return false;
		if (storeuuid != null ? !storeuuid.equals(that.storeuuid) : that.storeuuid != null) return false;
		if (storename != null ? !storename.equals(that.storename) : that.storename != null) return false;
		if (goodsuuid != null ? !goodsuuid.equals(that.goodsuuid) : that.goodsuuid != null) return false;
		if (goodsname != null ? !goodsname.equals(that.goodsname) : that.goodsname != null) return false;
		if (num != null ? !num.equals(that.num) : that.num != null) return false;
		return type != null ? type.equals(that.type) : that.type == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (empuuid != null ? empuuid.hashCode() : 0);
		result = 31 * result + (empname != null ? empname.hashCode() : 0);
		result = 31 * result + (opertime != null ? opertime.hashCode() : 0);
		result = 31 * result + (storeuuid != null ? storeuuid.hashCode() : 0);
		result = 31 * result + (storename != null ? storename.hashCode() : 0);
		result = 31 * result + (goodsuuid != null ? goodsuuid.hashCode() : 0);
		result = 31 * result + (goodsname != null ? goodsname.hashCode() : 0);
		result = 31 * result + (num != null ? num.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StoreoperDo{" +
				"uuid=" + uuid +
				", empuuid=" + empuuid +
				", empname='" + empname + '\'' +
				", opertime=" + opertime +
				", storeuuid=" + storeuuid +
				", storename='" + storename + '\'' +
				", goodsuuid=" + goodsuuid +
				", goodsname='" + goodsname + '\'' +
				", num=" + num +
				", type='" + type + '\'' +
				'}';
	}
}
