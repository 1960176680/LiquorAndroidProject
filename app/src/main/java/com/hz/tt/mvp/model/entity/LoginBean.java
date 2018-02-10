package com.hz.tt.mvp.model.entity;

/**
 * Created by Administrator on 2018-02-10.
 */

public class LoginBean extends BaseBean{
    private String userName;
    private String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
