package com.hz.tt.mvp.model.entity.response;


/**
 * Created by AMing on 15/12/23.
 * Company RongCloud
 */
public class RegisterResponse {
    /**
     * //    {"errorCode":"2000","errorMsg":"该用户名已经被注册"}
     * errorCode : 2000
     * errorMsg : 该用户名已经被注册
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



}
