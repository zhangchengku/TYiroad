package com.tyiroad.tyiroad.log.organization;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-15.
 */

public class ChildrenBean {
    private String GYDWID;
    private String GYDWCODE;
    private String GYDWMC;
    private String PARENTID;
    private List<ChildrenBean> children;

    public String getGYDWID() {
        return GYDWID;
    }

    public void setGYDWID(String GYDWID) {
        this.GYDWID = GYDWID;
    }

    public String getGYDWCODE() {
        return GYDWCODE;
    }

    public void setGYDWCODE(String GYDWCODE) {
        this.GYDWCODE = GYDWCODE;
    }

    public String getGYDWMC() {
        return GYDWMC;
    }

    public void setGYDWMC(String GYDWMC) {
        this.GYDWMC = GYDWMC;
    }

    public String getPARENTID() {
        return PARENTID;
    }

    public void setPARENTID(String PARENTID) {
        this.PARENTID = PARENTID;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }
}
