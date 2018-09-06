package com.ywx.erp.entity;

import java.io.Serializable;

public class SupplierDo implements Serializable {

    private Integer uuid;   //
    private String name;    //名称
    private String address; //地址
    private String contact; //联系人
    private String tele;    //电话
    private String email;   //邮件
    private Character type; //供应商、客户

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplierDo that = (SupplierDo) o;

        if (!uuid.equals(that.uuid)) return false;
        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!contact.equals(that.contact)) return false;
        if (!tele.equals(that.tele)) return false;
        if (!email.equals(that.email)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + tele.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SupplierDo{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", tele='" + tele + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
