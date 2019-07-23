package com.tyiroad.tyiroad.log.addlog;

/**
 * Created by 张成昆 on 2019-7-11.
 */

public class AddLogItem {
    private String LDID;//路段id
    private String LDMC;//路段名称
    private boolean isChcked;    //保存复选框的状态

    public String getLDID() {
        return LDID;
    }

    public void setLDID(String LDID) {
        this.LDID = LDID;
    }

    public String getLDMC() {
        return LDMC;
    }

    public void setLDMC(String LDMC) {
        this.LDMC = LDMC;
    }

    public void setIsChcked(boolean isChcked) {
        this.isChcked = isChcked;
    }

    public boolean getIsChcked() {
        return isChcked;
    }
}
