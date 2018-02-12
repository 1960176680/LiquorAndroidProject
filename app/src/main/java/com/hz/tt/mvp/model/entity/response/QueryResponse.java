package com.hz.tt.mvp.model.entity.response;

/**
 * {"data":"{\"position\":\"不知道\",\"category\":\"红酒\",\"remark\":\"不清楚\",\"productiveYear\":\"2016\",\"volume\":\"2L\",\"origin\":\"非洲\",\"recordCode\":\"123456789\",\"intoDate\":\"Feb 12, 2018 11:14:08 AM\",\"countNum\":12,\"country\":\"美国\"}","errorCode":"1000","errorMsg":"查询成功。"}
 */

public class QueryResponse {
    /**
     * data : {"position":"不知道","category":"红酒","remark":"不清楚","productiveYear":"2016","volume":"2L","origin":"非洲","recordCode":"123456789","intoDate":"Feb 12, 2018 11:14:08 AM","countNum":12,"country":"美国"}
     * errorCode : 1000
     * errorMsg : 查询成功。
     */

    private String data;
    private String errorCode;
    private String errorMsg;





    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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
