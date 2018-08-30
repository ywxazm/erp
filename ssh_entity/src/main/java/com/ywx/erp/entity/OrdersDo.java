package com.ywx.erp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单实体类
 * @author Administrator *
 */
public class OrdersDo implements Serializable {
	
	/** 未审核 */
	public static final String STATE_CREATE = "0";
	/** 已审核 */
	public static final String STATE_CHECK = "1";
	/** 已确认 */
	public static final String STATE_START = "2";
	/** 已入库 */
	public static final String STATE_END = "3";
	
	/** 采购订单 */
	public static final String TYPE_IN = "1";
	/** 销售订单 */
	public static final String TYPE_OUT = "2";
	
	private Long uuid;//编号
	private java.util.Date createtime;//生成日期
	private java.util.Date checktime;//审核日期
	private java.util.Date starttime;//确认日期
	private java.util.Date endtime;//入库或出库日期
	private String type;//1:采购 2:销售
	private Long creater;//下单员
	private Long checker;//审核员
	private Long starter;//采购员
	private Long ender;//库管员
	private Long supplieruuid;//供应商或客户
	private Double totalmoney;//合计金额
	private String state;//采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库
	private Long waybillsn;//运单号
	private List<OrderdetailDo> orderDetailDos;//订单明细

	public List<OrderdetailDo> getOrderDetailDos() {
		return orderDetailDos;
	}
	public void setOrderDetailDos(List<OrderdetailDo> orderDetailDos) {
		this.orderDetailDos = orderDetailDos;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public java.util.Date getCreatetime() {		
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public java.util.Date getChecktime() {		
		return checktime;
	}
	public void setChecktime(java.util.Date checktime) {
		this.checktime = checktime;
	}
	public java.util.Date getStarttime() {		
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {		
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public String getType() {		
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getCreater() {		
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Long getChecker() {		
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	public Long getStarter() {		
		return starter;
	}
	public void setStarter(Long starter) {
		this.starter = starter;
	}
	public Long getEnder() {		
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getSupplieruuid() {		
		return supplieruuid;
	}
	public void setSupplieruuid(Long supplieruuid) {
		this.supplieruuid = supplieruuid;
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
	public Long getWaybillsn() {		
		return waybillsn;
	}
	public void setWaybillsn(Long waybillsn) {
		this.waybillsn = waybillsn;
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
		if (checker != null ? !checker.equals(ordersDo.checker) : ordersDo.checker != null) return false;
		if (starter != null ? !starter.equals(ordersDo.starter) : ordersDo.starter != null) return false;
		if (ender != null ? !ender.equals(ordersDo.ender) : ordersDo.ender != null) return false;
		if (supplieruuid != null ? !supplieruuid.equals(ordersDo.supplieruuid) : ordersDo.supplieruuid != null)
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
		result = 31 * result + (checker != null ? checker.hashCode() : 0);
		result = 31 * result + (starter != null ? starter.hashCode() : 0);
		result = 31 * result + (ender != null ? ender.hashCode() : 0);
		result = 31 * result + (supplieruuid != null ? supplieruuid.hashCode() : 0);
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
				", checker=" + checker +
				", starter=" + starter +
				", ender=" + ender +
				", supplieruuid=" + supplieruuid +
				", totalmoney=" + totalmoney +
				", state='" + state + '\'' +
				", waybillsn=" + waybillsn +
				", orderDetailDos=" + orderDetailDos +
				'}';
	}
}
