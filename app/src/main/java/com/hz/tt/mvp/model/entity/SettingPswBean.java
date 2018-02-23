package com.hz.tt.mvp.model.entity;

/**
 * Created by Administrator on 2018-02-13.
 */

public class SettingPswBean {
    private String userName;
    private String newPassword;
    private String oldPassword;
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
