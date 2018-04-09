package com.hz.tt.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-02-11.
 */
@Entity
public class InBean {
    @Id
    private Long id;
    private String person;
    private String time;
    private String name;
    private String type;
    private String country;
    private String birthday;
    private String capacity;
    private String year;
    private String number;
    private String location;
    private String code;
    private String imgstr;
    private String status;
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getImgstr() {
        return this.imgstr;
    }
    public void setImgstr(String imgstr) {
        this.imgstr = imgstr;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getCapacity() {
        return this.capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPerson() {
        return this.person;
    }
    public void setPerson(String person) {
        this.person = person;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 874762354)
    public InBean(Long id, String person, String time, String name, String type,
            String country, String birthday, String capacity, String year,
            String number, String location, String code, String imgstr,
            String status) {
        this.id = id;
        this.person = person;
        this.time = time;
        this.name = name;
        this.type = type;
        this.country = country;
        this.birthday = birthday;
        this.capacity = capacity;
        this.year = year;
        this.number = number;
        this.location = location;
        this.code = code;
        this.imgstr = imgstr;
        this.status = status;
    }
    @Generated(hash = 1564616797)
    public InBean() {
    }
}
