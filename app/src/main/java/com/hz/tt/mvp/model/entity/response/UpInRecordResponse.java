package com.hz.tt.mvp.model.entity.response;

/**
 * Created by Administrator on 2018-02-11.
 */

public class UpInRecordResponse {

    /**
     * errorCode : 1000
     * errorMsg : 入库成功。
     */

    private String errorCode;
    private String errorMsg;

//    {"errorCode":"1000","errorMsg":"入库成功。"}

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
