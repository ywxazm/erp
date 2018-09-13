package com.ywx.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 员工
 */
public class EmpDo implements Serializable {

    private Integer uuid;          //编号
    private String username;    //登录名
    @JSONField(serialize = false)       //fastJson提供的不进行序列化
    private String pwd;         //登录密码
    private String name;        //真实姓名
    private Integer gender;        //性别
    private String email;       //邮箱
    private String tele;        //联系电话
    private String address;     //联系地址
    private Date birthday;      //生日
    private DepDo depDo;        //部门编号
    private List<RoleDo> roleDoList; //拥有角色集合

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DepDo getDepDo() {
        return depDo;
    }

    public void setDepDo(DepDo depDo) {
        this.depDo = depDo;
    }

    public List<RoleDo> getRoleDoList() {
        return roleDoList;
    }

    public void setRoleDoList(List<RoleDo> roleDoList) {
        this.roleDoList = roleDoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpDo empDo = (EmpDo) o;

        if (uuid != null ? !uuid.equals(empDo.uuid) : empDo.uuid != null) return false;
        if (username != null ? !username.equals(empDo.username) : empDo.username != null) return false;
        if (pwd != null ? !pwd.equals(empDo.pwd) : empDo.pwd != null) return false;
        if (name != null ? !name.equals(empDo.name) : empDo.name != null) return false;
        if (gender != null ? !gender.equals(empDo.gender) : empDo.gender != null) return false;
        if (email != null ? !email.equals(empDo.email) : empDo.email != null) return false;
        if (tele != null ? !tele.equals(empDo.tele) : empDo.tele != null) return false;
        if (address != null ? !address.equals(empDo.address) : empDo.address != null) return false;
        if (birthday != null ? !birthday.equals(empDo.birthday) : empDo.birthday != null) return false;
        if (depDo != null ? !depDo.equals(empDo.depDo) : empDo.depDo != null) return false;
        return roleDoList != null ? roleDoList.equals(empDo.roleDoList) : empDo.roleDoList == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tele != null ? tele.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (depDo != null ? depDo.hashCode() : 0);
        result = 31 * result + (roleDoList != null ? roleDoList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmpDo{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", tele='" + tele + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", depDo=" + depDo +
                ", roleDoList=" + roleDoList +
                '}';
    }
}
