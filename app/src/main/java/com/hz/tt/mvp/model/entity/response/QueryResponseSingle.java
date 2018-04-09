package com.hz.tt.mvp.model.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018-02-12.
 */

public class QueryResponseSingle implements Parcelable{
    private String recordCode;
    private String operation;
    private String recordName;
    private String category;
    private String country;
    private String origin;
    private String volume;
    private String productiveYear;
    private String countNum;
    private String intoDate;
    private String intoCustomer;
    private String position;
    private String outRecordSource;
    private String outRecordDate;
    private String outDate;
    private String outCustomer;
    private String receiveCustomer;
    private String photo;
    private String receiveDate;
    private String remark;

    protected QueryResponseSingle(Parcel in) {
        recordCode = in.readString();
        operation = in.readString();
        recordName = in.readString();
        category = in.readString();
        country = in.readString();
        origin = in.readString();
        volume = in.readString();
        productiveYear = in.readString();
        countNum = in.readString();
        intoDate = in.readString();
        intoCustomer = in.readString();
        position = in.readString();
        outRecordSource = in.readString();
        outRecordDate = in.readString();
        outDate = in.readString();
        outCustomer = in.readString();
        receiveCustomer = in.readString();
        photo = in.readString();
        receiveDate = in.readString();
        remark = in.readString();
    }

    public static final Creator<QueryResponseSingle> CREATOR = new Creator<QueryResponseSingle>() {
        @Override
        public QueryResponseSingle createFromParcel(Parcel in) {
            return new QueryResponseSingle(in);
        }

        @Override
        public QueryResponseSingle[] newArray(int size) {
            return new QueryResponseSingle[size];
        }
    };
    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
    public String getCountNum() {
        return countNum;
    }
    public void setCountNum(String countNum) {
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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getOutRecordSource() {
        return outRecordSource;
    }
    public void setOutRecordSource(String outRecordSource) {
        this.outRecordSource = outRecordSource;
    }
    public String getOutRecordDate() {
        return outRecordDate;
    }
    public void setOutRecordDate(String outRecordDate) {
        this.outRecordDate = outRecordDate;
    }
    public String getOutDate() {
        return outDate;
    }
    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
    public String getOutCustomer() {
        return outCustomer;
    }
    public void setOutCustomer(String outCustomer) {
        this.outCustomer = outCustomer;
    }
    public String getReceiveCustomer() {
        return receiveCustomer;
    }
    public void setReceiveCustomer(String receiveCustomer) {
        this.receiveCustomer = receiveCustomer;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return recordCode+"#"+country+"#"
                +origin+"#"+category+"#"
                +countNum+"#"+position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recordCode);
        dest.writeString(operation);
        dest.writeString(recordName);
        dest.writeString(category);
        dest.writeString(country);
        dest.writeString(origin);
        dest.writeString(volume);
        dest.writeString(productiveYear);
        dest.writeString(countNum);
        dest.writeString(intoDate);
        dest.writeString(intoCustomer);
        dest.writeString(position);
        dest.writeString(outRecordSource);
        dest.writeString(outRecordDate);
        dest.writeString(outDate);
        dest.writeString(outCustomer);
        dest.writeString(receiveCustomer);
        dest.writeString(photo);
        dest.writeString(receiveDate);
        dest.writeString(remark);
    }
}
