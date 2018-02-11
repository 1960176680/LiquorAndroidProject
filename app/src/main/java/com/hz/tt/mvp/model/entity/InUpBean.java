package com.hz.tt.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-02-11.
 */
@Entity
public class InUpBean {
    @Id
    private Long id;
    private String recordCode;
    private String category;
    private String country;
    private String origin;
    private String volume;
    private String productiveYear;
    private int countNum;
    private String intoDate;
    private String intoCustomer;
    private String photo;
    private String remark;
    private String position;
    @Generated(hash = 477518180)
    public InUpBean(Long id, String recordCode, String category, String country,
            String origin, String volume, String productiveYear, int countNum,
            String intoDate, String intoCustomer, String photo, String remark,
            String position) {
        this.id = id;
        this.recordCode = recordCode;
        this.category = category;
        this.country = country;
        this.origin = origin;
        this.volume = volume;
        this.productiveYear = productiveYear;
        this.countNum = countNum;
        this.intoDate = intoDate;
        this.intoCustomer = intoCustomer;
        this.photo = photo;
        this.remark = remark;
        this.position = position;
    }

    @Generated(hash = 732495409)
    public InUpBean() {
    }
    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getProductiveYear() {
        return productiveYear;
    }

    public void setProductiveYear(String productiveYear) {
        this.productiveYear = productiveYear;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public String getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(String intoDate) {
        this.intoDate = intoDate;
    }

    public String getIntoCustomer() {
        return intoCustomer;
    }

    public void setIntoCustomer(String intoCustomer) {
        this.intoCustomer = intoCustomer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
