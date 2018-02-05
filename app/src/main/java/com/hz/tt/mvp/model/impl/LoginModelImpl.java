package com.hz.tt.mvp.model.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.antonioleiva.mvpexample.mvp.model.LoginModel;
import com.antonioleiva.mvpexample.mvp.presenter.OnLoginListener;

public class LoginModelImpl implements LoginModel {

    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error = true;
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                    return;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
