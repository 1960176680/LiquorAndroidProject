package com.hz.tt.mvp.model.entity;

/**
 * Created by Administrator on 2018-02-10.
 */

public class RegisterBean extends BaseBean{
    /**
     * //    {"userName":"123456","userPhone":"123456","password":"123456"}
     * userName : 123456
     * userPhone : 123456
     * password : 123456
     */

    private String userName;
    private String userPhone;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
