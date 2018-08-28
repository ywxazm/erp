package com.ywx.entity.sample;

import java.io.Serializable;

public class TeacherDo implements Serializable{

    private Integer tno;
    private String tname;

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherDo teacherDo = (TeacherDo) o;

        if (!tno.equals(teacherDo.tno)) return false;
        return tname.equals(teacherDo.tname);
    }

    @Override
    public int hashCode() {
        int result = tno.hashCode();
        result = 31 * result + tname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TecherDo{" +
                "tno=" + tno +
                ", tname='" + tname + '\'' +
                '}';
    }
}
