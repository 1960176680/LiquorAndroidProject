package com.hz.tt.mvp.ui.view;
import android.widget.Button;
import android.widget.EditText;

public interface IRegisterAtView {

    EditText getEtNickName();

    EditText getEtPhone();

    EditText getEtPwd();

    EditText getEtVerifyCode();

    Button getBtnSendCode();
}
