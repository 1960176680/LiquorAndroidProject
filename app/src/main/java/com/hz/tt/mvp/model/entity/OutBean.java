package com.hz.tt.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-02-12.
 */
@Entity
public class OutBean {
    @Id
    private Long id;

    //    编码
    private String recordCode;
    private String operation;
    //    当前位置
    private String position;
    //    出库指令来自
    private String outRecordSource;
    //    出库指令时间
    private String outRecordDate;
    //    出库时间
    private String outDate;
    //    出库人
    private String outCustomer;
    //    接收人
    private String receiveCustomer;
//    接收时间
    private String receiveDate;
//    数量
    private String countNum;
    private String intoDate;
    private String intoCustomer;
    private String remark;

    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCountNum() {
        return this.countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    public String getReceiveDate() {
        return this.receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveCustomer() {
        return this.receiveCustomer;
    }

    public void setReceiveCustomer(String receiveCustomer) {
        this.receiveCustomer = receiveCustomer;
    }

    public String getOutCustomer() {
        return this.outCustomer;
    }

    public void setOutCustomer(String outCustomer) {
        this.outCustomer = outCustomer;
    }

    public String getOutDate() {
        return this.outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutRecordDate() {
        return this.outRecordDate;
    }

    public void setOutRecordDate(String outRecordDate) {
        this.outRecordDate = outRecordDate;
    }

    public String getOutRecordSource() {
        return this.outRecordSource;
    }

    public void setOutRecordSource(String outRecordSource) {
        this.outRecordSource = outRecordSource;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    @Generated(hash = 59216076)
    public OutBean(Long id, String recordCode, String operation, String position,
            String outRecordSource, String outRecordDate, String outDate,
            String outCustomer, String receiveCustomer, String receiveDate,
            String countNum, String intoDate, String intoCustomer, String remark,
            String status) {
        this.id = id;
        this.recordCode = recordCode;
        this.operation = operation;
        this.position = position;
        this.outRecordSource = outRecordSource;
        this.outRecordDate = outRecordDate;
        this.outDate = outDate;
        this.outCustomer = outCustomer;
        this.receiveCustomer = receiveCustomer;
        this.receiveDate = receiveDate;
        this.countNum = countNum;
        this.intoDate = intoDate;
        this.intoCustomer = intoCustomer;
        this.remark = remark;
        this.status = status;
    }

    @Generated(hash = 202240653)
    public OutBean() {
    }
}
