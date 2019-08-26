package com.tyiroad.tyiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tyiroad.tyiroad.db.dbhelper.DBHelper;

/**
 * Created by 张成昆 on 2019-6-29.
 */
@DatabaseTable(tableName = DBHelper.TABLE_FILTER_QUALITY)
public class QualityInfo {

    /**
     * Xmmc : 项目名称guid
     * Fbgc : 分部工程guid
     * Fxgc : 分项工程guid
     * Gcbw : 工程部位
     * Jcr : 登录名
     * Jcdw : 登录用户单位
     * JcxmList : [{"Guid":"id1,id2","lsHg":"1,0"}]
     * Bhgsm : 不合格说明
     * PIC : []
     * Dlwz :  地理位置
     */

    @DatabaseField(generatedId = true)
    private int QUID;//自增编号
    @DatabaseField
    private String Xmmc;//项目名称
    @DatabaseField
    private String XmmcID;//项目名称id


    @DatabaseField
    private String Fbgc;//分部工程
    @DatabaseField
    private String FbgcID;//分部工程guid



    @DatabaseField
    private String Fxgc;//分项工程
    @DatabaseField
    private String FxgcID;//分项工程guid



    @DatabaseField
    private String Gcbw;//工程部位



    @DatabaseField
    private String JLSJ;//记录的时间




    @DatabaseField
    private String Jcr;//登录名
    @DatabaseField
    private String Jcdw;//登录用户单位
    @DatabaseField
    private String Bhgsm;//不合格说明
    @DatabaseField
    private String Dlwz;//地理位置
    @DatabaseField
    private String CSXMID;//细目ID
    @DatabaseField
    private String JCXM;//ONE
    @DatabaseField
    private String JCFWZ;//TWO
    @DatabaseField
    private String lsHg;//是否合格
    @DatabaseField
    private String PIC;//病害图片 多个逗号隔开



    @DatabaseField
    private String JCFW;
    @DatabaseField
    private String HGDS;//病害图片 多个逗号隔开

    public String getJCFW() {
        return JCFW;
    }

    public void setJCFW(String JCFW) {
        this.JCFW = JCFW;
    }

    public String getHGDS() {
        return HGDS;
    }

    public void setHGDS(String HGDS) {
        this.HGDS = HGDS;
    }

    public String getCSXMID() {
        return CSXMID;
    }

    public void setCSXMID(String CSXMID) {
        this.CSXMID = CSXMID;
    }

    public String getJCFWZ() {
        return JCFWZ;
    }

    public void setJCFWZ(String JCFWZ) {
        this.JCFWZ = JCFWZ;
    }

    public String getJCXM() {
        return JCXM;
    }

    public void setJCXM(String JCXM) {
        this.JCXM = JCXM;
    }

    public String getXmmcID() {
        return XmmcID;
    }

    public void setXmmcID(String xmmcID) {
        XmmcID = xmmcID;
    }

    public String getFbgcID() {
        return FbgcID;
    }

    public void setFbgcID(String fbgcID) {
        FbgcID = fbgcID;
    }

    public String getFxgcID() {
        return FxgcID;
    }

    public void setFxgcID(String fxgcID) {
        FxgcID = fxgcID;
    }



    public String getJLSJ() {
        return JLSJ;
    }

    public void setJLSJ(String JLSJ) {
        this.JLSJ = JLSJ;
    }

    public int getQUID() {
        return QUID;
    }

    public void setQUID(int QUID) {
        this.QUID = QUID;
    }

    public String getXmmc() {
        return Xmmc;
    }

    public void setXmmc(String xmmc) {
        Xmmc = xmmc;
    }

    public String getFbgc() {
        return Fbgc;
    }

    public void setFbgc(String fbgc) {
        Fbgc = fbgc;
    }

    public String getFxgc() {
        return Fxgc;
    }

    public void setFxgc(String fxgc) {
        Fxgc = fxgc;
    }

    public String getGcbw() {
        return Gcbw;
    }

    public void setGcbw(String gcbw) {
        Gcbw = gcbw;
    }

    public String getJcr() {
        return Jcr;
    }

    public void setJcr(String jcr) {
        Jcr = jcr;
    }

    public String getJcdw() {
        return Jcdw;
    }

    public void setJcdw(String jcdw) {
        Jcdw = jcdw;
    }

    public String getBhgsm() {
        return Bhgsm;
    }

    public void setBhgsm(String bhgsm) {
        Bhgsm = bhgsm;
    }

    public String getDlwz() {
        return Dlwz;
    }

    public void setDlwz(String dlwz) {
        Dlwz = dlwz;
    }



    public String getLsHg() {
        return lsHg;
    }

    public void setLsHg(String lsHg) {
        this.lsHg = lsHg;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }
}
