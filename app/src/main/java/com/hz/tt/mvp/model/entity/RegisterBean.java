package com.hz.tt.mvp.model.entity;

/**
 * Created by Administrator on 2018-02-10.
 */

public class RegisterBean extends BaseBean{
    private String phone;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
