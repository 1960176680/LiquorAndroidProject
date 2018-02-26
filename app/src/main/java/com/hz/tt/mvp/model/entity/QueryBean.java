package com.hz.tt.mvp.model.entity;

/**
 * Created by Administrator on 2018-02-12.
 */

public class QueryBean {
    private String recordCode;
    private String category;
    private String country;
    private String origin;
    private String volume;
    private String productiveYear;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIntoCustomer() {
        return intoCustomer;
    }

    public void setIntoCustomer(String intoCustomer) {
        this.intoCustomer = intoCustomer;
    }

    public String getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(String intoDate) {
        this.intoDate = intoDate;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    public String getProductiveYear() {
        return productiveYear;
    }

    public void setProductiveYear(String productiveYear) {
        this.productiveYear = productiveYear;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    private String countNum;
    private String intoDate;
    private String intoCustomer;
    private String position;
    private String photo;
    private String remark;
}
