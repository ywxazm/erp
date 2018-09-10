package com.ywx.erp.vo;

import java.io.Serializable;
import java.util.List;

public class TreeVo implements Serializable {

    private Integer id;                 //ID
    private String text;                //名称
    private boolean checked;            //是否选中
    private List<TreeVo> children;      //下级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<TreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVo> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeVo treeVo = (TreeVo) o;

        if (checked != treeVo.checked) return false;
        if (id != null ? !id.equals(treeVo.id) : treeVo.id != null) return false;
        if (text != null ? !text.equals(treeVo.text) : treeVo.text != null) return false;
        return children != null ? children.equals(treeVo.children) : treeVo.children == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TreeVo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
