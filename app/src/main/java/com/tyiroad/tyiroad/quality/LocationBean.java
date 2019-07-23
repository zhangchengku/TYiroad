package com.tyiroad.tyiroad.quality;

/**
 * Created by 张成昆 on 2019-7-2.
 */

public class LocationBean {

    /**
     * DATA : 0
     * STATE : 1
     * MESSAGE : 操作成功
     */

    private int DATA;
    private String STATE;
    private String MESSAGE;

    public int getDATA() {
        return DATA;
    }

    public void setDATA(int DATA) {
        this.DATA = DATA;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}
