package com.hz.tt.mvp.model.entity.response;

/**
 * Created by Administrator on 2018-02-13.
 */

public class SetPswResponse {
    /**
     * errorCode : 2000
     * errorMsg : 账号或密码不正确。
     */

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
//    {"errorCode":"2000","errorMsg":"账号或密码不正确。"}
}
