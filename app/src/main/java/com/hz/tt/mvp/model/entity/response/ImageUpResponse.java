package com.hz.tt.mvp.model.entity.response;

/**
 * Created by Administrator on 2018-02-11.
 */

public class ImageUpResponse {
    /**
     * {"code":"1000","message":"","data":"{\"url\":\"/static/images/1518344807435.jpg\"}"}
     * code : 1000
     * message :
     * data : {"url":"/static/images/1518344807435.jpg"}
     */

    private String code;
    private String message;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
