package com.ywx.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class EmpDo implements Serializable {

    private Long uuid;          //编号
    private String username;    //登录名
    @JSONField(serialize = false)       //fastJson提供的不进行序列化
    private String pwd;         //登录密码
    private String name;        //真实姓名
    private Long gender;        //性别
    private String email;       //邮箱
    private String tele;        //联系电话
    private String address;     //联系地址
    private Date birthday;      //生日
    private DepDo depDo;        //部门编号

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpDo empDo = (EmpDo) o;

        if (!uuid.equals(empDo.uuid)) return false;
        if (!username.equals(empDo.username)) return false;
        if (!pwd.equals(empDo.pwd)) return false;
        if (!name.equals(empDo.name)) return false;
        if (!gender.equals(empDo.gender)) return false;
        if (!email.equals(empDo.email)) return false;
        if (!tele.equals(empDo.tele)) return false;
        if (!address.equals(empDo.address)) return false;
        if (!birthday.equals(empDo.birthday)) return false;
        return depDo.equals(empDo.depDo);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + pwd.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + tele.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + depDo.hashCode();
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
                '}';
    }
}
