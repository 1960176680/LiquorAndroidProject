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
    private String recordName;
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
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getIntoCustomer() {
        return this.intoCustomer;
    }
    public void setIntoCustomer(String intoCustomer) {
        this.intoCustomer = intoCustomer;
    }
    public String getIntoDate() {
        return this.intoDate;
    }
    public void setIntoDate(String intoDate) {
        this.intoDate = intoDate;
    }
    public int getCountNum() {
        return this.countNum;
    }
    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }
    public String getProductiveYear() {
        return this.productiveYear;
    }
    public void setProductiveYear(String productiveYear) {
        this.productiveYear = productiveYear;
    }
    public String getVolume() {
        return this.volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }
    public String getOrigin() {
        return this.origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getRecordName() {
        return this.recordName;
    }
    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    public String getRecordCode() {
        return this.recordCode;
    }
    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1306707550)
    public InUpBean(Long id, String recordCode, String recordName, String category,
            String country, String origin, String volume, String productiveYear,
            int countNum, String intoDate, String intoCustomer, String photo,
            String remark, String position) {
        this.id = id;
        this.recordCode = recordCode;
        this.recordName = recordName;
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

}
