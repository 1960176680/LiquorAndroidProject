/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.hz.tt.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hz.tt.liquorandroidproject.R;
import com.hz.tt.liquorandroidproject.mvp.presenter.LoginPresenter;
import com.hz.tt.liquorandroidproject.mvp.presenter.impl.LoginPresenterImpl;
import com.hz.tt.liquorandroidproject.mvp.ui.view.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
//  进度条
    private ProgressBar progressBar;

    private EditText username;
    private EditText password;
//    登录Presenter
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initActivityData();
    }

    /**
     * 初始化view
     */
    public void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);
    }

    /**
     * 初始化activity数据
     */
    public void initActivityData() {
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * 实现 LoginView
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    /**
     * 实现 LoginView
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
    /**
     * 实现 LoginView
     */
    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }
    /**
     * 实现 LoginView
     */
    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }
    /**
     * 实现 LoginView
     */
    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
