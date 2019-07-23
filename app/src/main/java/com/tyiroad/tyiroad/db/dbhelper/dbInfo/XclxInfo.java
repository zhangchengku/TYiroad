package com.tyiroad.tyiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by 张成昆 on 2019-7-16.
 */

public class XclxInfo {
    @DatabaseField(generatedId=true)
    private int XCLXID;//工程路段自增id

    @DatabaseField
    private String TEXT;

    @DatabaseField
    private String VALUE;

    @DatabaseField
    private String QDZH;

    @DatabaseField
    private String ZDZH;

    @DatabaseField
    private String SJ;//终点桩号

    public int getXCLXID() {
        return XCLXID;
    }

    public void setXCLXID(int XCLXID) {
        this.XCLXID = XCLXID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getZDZH() {
        return ZDZH;
    }

    public void setZDZH(String ZDZH) {
        this.ZDZH = ZDZH;
    }

    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }
}
