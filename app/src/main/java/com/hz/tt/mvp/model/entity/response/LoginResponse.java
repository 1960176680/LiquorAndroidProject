package com.hz.tt.mvp.model.entity.response;
/**
 * {"errorCode":"1000","errorMsg":"密码修改成功。","success":false}
 */
public class LoginResponse {
    /**
     * errorCode : 1000
     * errorMsg : 密码修改成功。
     * success : false
     */

    private String errorCode;
    private String errorMsg;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
