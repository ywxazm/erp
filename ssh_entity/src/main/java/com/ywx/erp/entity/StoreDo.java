package com.ywx.erp.entity;

import java.io.Serializable;

/**
 * 仓库实体类
 * @author Administrator *
 */
public class StoreDo implements Serializable {
	private Long uuid;//编号
	private String name;//名称
	private Long empuuid;//员工编号

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
	public Long getEmpuuid() {		
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StoreDo storeDo = (StoreDo) o;

		if (uuid != null ? !uuid.equals(storeDo.uuid) : storeDo.uuid != null) return false;
		if (name != null ? !name.equals(storeDo.name) : storeDo.name != null) return false;
		return empuuid != null ? empuuid.equals(storeDo.empuuid) : storeDo.empuuid == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (empuuid != null ? empuuid.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StoreDo{" +
				"uuid=" + uuid +
				", name='" + name + '\'' +
				", empuuid=" + empuuid +
				'}';
	}
}
