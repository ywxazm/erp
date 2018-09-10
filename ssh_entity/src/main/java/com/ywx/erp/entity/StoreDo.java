package com.ywx.erp.entity;

import java.io.Serializable;

/**
 * 仓库实体类
 */
public class StoreDo implements Serializable {

	private Integer uuid;//编号
	private String name;//名称
	private Integer empuuid;//库管员工编号
	private String empname;//库管员工姓名

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StoreDo storeDo = (StoreDo) o;

		if (uuid != null ? !uuid.equals(storeDo.uuid) : storeDo.uuid != null) return false;
		if (name != null ? !name.equals(storeDo.name) : storeDo.name != null) return false;
		if (empuuid != null ? !empuuid.equals(storeDo.empuuid) : storeDo.empuuid != null) return false;
		return empname != null ? empname.equals(storeDo.empname) : storeDo.empname == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (empuuid != null ? empuuid.hashCode() : 0);
		result = 31 * result + (empname != null ? empname.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StoreDo{" +
				"uuid=" + uuid +
				", name='" + name + '\'' +
				", empuuid=" + empuuid +
				", empname='" + empname + '\'' +
				'}';
	}
}
