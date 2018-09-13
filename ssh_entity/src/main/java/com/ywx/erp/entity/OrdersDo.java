package com.ywx.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.ywx.erp.common.BaseConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * @author Administrator *
 */
public class OrdersDo implements Serializable {
	
	private Integer uuid;			//编号
	@JSONField(format= BaseConstants.DATEFORMAT)
	private Date createtime;		//生成日期
	@JSONField(format=BaseConstants.DATEFORMAT)
	private Date checktime;			//审核日期
	@JSONField(format=BaseConstants.DATEFORMAT)
	private Date starttime;			//确认日期
	@JSONField(format=BaseConstants.DATEFORMAT)
	private Date endtime;			//入库或出库日期
	private String type;			//1:采购 2:销售
	private Integer creater;		//下单员
	private String createrName;		//下单员
	private Integer checker;		//审核员
	private String checkerName;		//审核员
	private Integer starter;		//采购员
	private String starterName;		//采购员
	private Integer ender;			//库管员
	private String enderName;		//库管员
	private Integer supplieruuid;	//供应商或客户
	private String supplierName;	//供应商或客户
	private Double totalmoney;		//合计金额
	private String state;			//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
	private Integer waybillsn;		//运单号
	private List<OrderdetailDo> orderDetailDos;//订单明细

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public Integer getStarter() {
		return starter;
	}

	public void setStarter(Integer starter) {
		this.starter = starter;
	}

	public String getStarterName() {
		return starterName;
	}

	public void setStarterName(String starterName) {
		this.starterName = starterName;
	}

	public Integer getEnder() {
		return ender;
	}

	public void setEnder(Integer ender) {
		this.ender = ender;
	}

	public String getEnderName() {
		return enderName;
	}

	public void setEnderName(String enderName) {
		this.enderName = enderName;
	}

	public Integer getSupplieruuid() {
		return supplieruuid;
	}

	public void setSupplieruuid(Integer supplieruuid) {
		this.supplieruuid = supplieruuid;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getWaybillsn() {
		return waybillsn;
	}

	public void setWaybillsn(Integer waybillsn) {
		this.waybillsn = waybillsn;
	}

	public List<OrderdetailDo> getOrderDetailDos() {
		return orderDetailDos;
	}

	public void setOrderDetailDos(List<OrderdetailDo> orderDetailDos) {
		this.orderDetailDos = orderDetailDos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrdersDo ordersDo = (OrdersDo) o;

		if (uuid != null ? !uuid.equals(ordersDo.uuid) : ordersDo.uuid != null) return false;
		if (createtime != null ? !createtime.equals(ordersDo.createtime) : ordersDo.createtime != null) return false;
		if (checktime != null ? !checktime.equals(ordersDo.checktime) : ordersDo.checktime != null) return false;
		if (starttime != null ? !starttime.equals(ordersDo.starttime) : ordersDo.starttime != null) return false;
		if (endtime != null ? !endtime.equals(ordersDo.endtime) : ordersDo.endtime != null) return false;
		if (type != null ? !type.equals(ordersDo.type) : ordersDo.type != null) return false;
		if (creater != null ? !creater.equals(ordersDo.creater) : ordersDo.creater != null) return false;
		if (createrName != null ? !createrName.equals(ordersDo.createrName) : ordersDo.createrName != null)
			return false;
		if (checker != null ? !checker.equals(ordersDo.checker) : ordersDo.checker != null) return false;
		if (checkerName != null ? !checkerName.equals(ordersDo.checkerName) : ordersDo.checkerName != null)
			return false;
		if (starter != null ? !starter.equals(ordersDo.starter) : ordersDo.starter != null) return false;
		if (starterName != null ? !starterName.equals(ordersDo.starterName) : ordersDo.starterName != null)
			return false;
		if (ender != null ? !ender.equals(ordersDo.ender) : ordersDo.ender != null) return false;
		if (enderName != null ? !enderName.equals(ordersDo.enderName) : ordersDo.enderName != null) return false;
		if (supplieruuid != null ? !supplieruuid.equals(ordersDo.supplieruuid) : ordersDo.supplieruuid != null)
			return false;
		if (supplierName != null ? !supplierName.equals(ordersDo.supplierName) : ordersDo.supplierName != null)
			return false;
		if (totalmoney != null ? !totalmoney.equals(ordersDo.totalmoney) : ordersDo.totalmoney != null) return false;
		if (state != null ? !state.equals(ordersDo.state) : ordersDo.state != null) return false;
		if (waybillsn != null ? !waybillsn.equals(ordersDo.waybillsn) : ordersDo.waybillsn != null) return false;
		return orderDetailDos != null ? orderDetailDos.equals(ordersDo.orderDetailDos) : ordersDo.orderDetailDos == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
		result = 31 * result + (checktime != null ? checktime.hashCode() : 0);
		result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
		result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (creater != null ? creater.hashCode() : 0);
		result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
		result = 31 * result + (checker != null ? checker.hashCode() : 0);
		result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
		result = 31 * result + (starter != null ? starter.hashCode() : 0);
		result = 31 * result + (starterName != null ? starterName.hashCode() : 0);
		result = 31 * result + (ender != null ? ender.hashCode() : 0);
		result = 31 * result + (enderName != null ? enderName.hashCode() : 0);
		result = 31 * result + (supplieruuid != null ? supplieruuid.hashCode() : 0);
		result = 31 * result + (supplierName != null ? supplierName.hashCode() : 0);
		result = 31 * result + (totalmoney != null ? totalmoney.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (waybillsn != null ? waybillsn.hashCode() : 0);
		result = 31 * result + (orderDetailDos != null ? orderDetailDos.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "OrdersDo{" +
				"uuid=" + uuid +
				", createtime=" + createtime +
				", checktime=" + checktime +
				", starttime=" + starttime +
				", endtime=" + endtime +
				", type='" + type + '\'' +
				", creater=" + creater +
				", createrName='" + createrName + '\'' +
				", checker=" + checker +
				", checkerName='" + checkerName + '\'' +
				", starter=" + starter +
				", starterName='" + starterName + '\'' +
				", ender=" + ender +
				", enderName='" + enderName + '\'' +
				", supplieruuid=" + supplieruuid +
				", supplierName='" + supplierName + '\'' +
				", totalmoney=" + totalmoney +
				", state='" + state + '\'' +
				", waybillsn=" + waybillsn +
				", orderDetailDos=" + orderDetailDos +
				'}';
	}
}
