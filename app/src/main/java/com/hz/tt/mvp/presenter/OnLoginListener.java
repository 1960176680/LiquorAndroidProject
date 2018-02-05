package com.hz.tt.mvp.presenter;

/**
 * Created by Administrator on 2017-03-07.
 */

public interface OnLoginListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
