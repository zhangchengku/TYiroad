package com.tyiroad.tyiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tyiroad.tyiroad.db.dbhelper.DBHelper;

/**
 * Created by 张成昆 on 2019-7-1.
 */
@DatabaseTable(tableName = DBHelper.TABLE_FILTER_MONITORING)
public class MonitoringInfo {
    @DatabaseField(generatedId = true)
    private int MOID;//自增编号
    @DatabaseField
    private String CREATOR;//登录人
    @DatabaseField
    private String XMMC;//细目名称
    @DatabaseField
    private String XMID;//细目id
    @DatabaseField
    private String XMMS;//描述信息
    @DatabaseField
    private String SCSJ;//时间
    @DatabaseField
    private String SCDWID;//单位ID
    @DatabaseField
    private String LOCATION;//地理位置
    @DatabaseField
    private String PIC;//图片地址

    public int getMOID() {
        return MOID;
    }

    public void setMOID(int MOID) {
        this.MOID = MOID;
    }

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getXMMC() {
        return XMMC;
    }

    public void setXMMC(String XMMC) {
        this.XMMC = XMMC;
    }

    public String getXMID() {
        return XMID;
    }

    public void setXMID(String XMID) {
        this.XMID = XMID;
    }

    public String getXMMS() {
        return XMMS;
    }

    public void setXMMS(String XMMS) {
        this.XMMS = XMMS;
    }

    public String getSCSJ() {
        return SCSJ;
    }

    public void setSCSJ(String SCSJ) {
        this.SCSJ = SCSJ;
    }

    public String getSCDWID() {
        return SCDWID;
    }

    public void setSCDWID(String SCDWID) {
        this.SCDWID = SCDWID;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }
}
