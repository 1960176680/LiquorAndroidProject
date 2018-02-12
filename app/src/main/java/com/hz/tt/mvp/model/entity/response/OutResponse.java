package com.hz.tt.mvp.model.entity.response;

/**
 * Created by Administrator on 2018-02-12.
 */

public class OutResponse {
//    {"errorCode":"2001","errorMsg":"outRecordDate字段必须输入，不允许为空"}
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

    private String errorCode;
}
