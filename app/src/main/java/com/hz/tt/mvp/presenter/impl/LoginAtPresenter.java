package com.hz.tt.mvp.presenter.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.CategoryBeanDao;
import com.hz.tt.greendao.gen.CountryBeanDao;
import com.hz.tt.greendao.gen.OriginBeanDao;
import com.hz.tt.greendao.gen.VolumeBeanDao;
import com.hz.tt.mvp.model.entity.CategoryBean;
import com.hz.tt.mvp.model.entity.CountryBean;
import com.hz.tt.mvp.model.entity.LiquorBean;
import com.hz.tt.mvp.model.entity.OriginBean;
import com.hz.tt.mvp.model.entity.VolumeBean;
import com.hz.tt.mvp.model.entity.cache.UserCache;
import com.hz.tt.mvp.model.entity.exception.ServerException;
import com.hz.tt.mvp.model.entity.request.LiquorRequest;
import com.hz.tt.mvp.model.entity.request.LoginRequest;
import com.hz.tt.mvp.model.entity.response.LiquorResponse;
import com.hz.tt.mvp.model.entity.response.LoginResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.ForgetKeyActivity;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.activity.RegisterActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.LoginAtView;
import com.hz.tt.util.LogUtils;
import com.hz.tt.util.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoginAtPresenter extends BasePresenter<LoginAtView> {


    public LoginAtPresenter(BaseActivity context) {
        super(context);}

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
//        NetConstant.USER_NAME=phone;
        String pwd = getView().getEtPwd().getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            mContext.speechUtil.speakXunFei("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            mContext.speechUtil.speakXunFei("密码不能为空");
            return;
        }

        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            LoginResponse response = null;
            try {
                response = gson.fromJson(newstr1, LoginResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
                loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
//                下载酒基本表
                downLiquorInfo(okHttpUtils);

                UserCache.save(phone, pwd);
//                mContext.jumpToActivityAndClearTop(MainActivity.class);
//                mContext.finish();
            } else {
                loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
        okHttpUtils.myEnqueue(new LoginRequest(phone,pwd).getUrl(),null);
    }

    /**
     *下载酒基本表
     */
    private void downLiquorInfo(OkHttpUtils okHttpUtils) {
//        {"data":"{\"category\":[{\"typeName\":\"123\",\"id\":1,\"typeCode\":\"category\"},{\"typeName\":\"½´ÏãÐÍ\",\"id\":6,\"typeCode\":\"category\"},{\"typeName\":\"酱香型\",\"id\":12,\"typeCode\":\"category\"},{\"typeName\":\"干型\",\"id\":16,\"typeCode\":\"category\"}],\"volume\":[{\"typeName\":\"123\",\"id\":4,\"typeCode\":\"volume\"},{\"typeName\":\"321\",\"id\":5,\"typeCode\":\"volume\"},{\"typeName\":\"2L\",\"id\":9,\"typeCode\":\"volume\"},{\"typeName\":\"3L\",\"id\":11,\"typeCode\":\"volume\"},{\"typeName\":\"187ml\",\"id\":19,\"typeCode\":\"volume\"}],\"origin\":[{\"typeName\":\"123\",\"id\":3,\"typeCode\":\"origin\"},{\"typeName\":\"º¼ÖÝ\",\"id\":8,\"typeCode\":\"origin\"},{\"typeName\":\"杭州\",\"id\":14,\"typeCode\":\"origin\"},{\"typeName\":\"科尔查瓜山谷\",\"id\":18,\"typeCode\":\"origin\"},{\"typeName\":\"贵州\",\"id\":20,\"typeCode\":\"origin\"},{\"typeName\":\"智利\",\"id\":21,\"typeCode\":\"origin\"}],\"errorCode\":\"\",\"errorMsg\":\"\",\"country\":[{\"typeName\":\"123\",\"id\":2,\"typeCode\":\"country\"},{\"typeName\":\"ÃÀ¹ú\",\"id\":7,\"typeCode\":\"country\"},{\"typeName\":\"ÖÐ¹ú\",\"id\":10,\"typeCode\":\"country\"},{\"typeName\":\"美国\",\"id\":13,\"typeCode\":\"country\"},{\"typeName\":\"中国\",\"id\":15,\"typeCode\":\"country\"},{\"typeName\":\"智利\",\"id\":17,\"typeCode\":\"country\"}]}","errorCode":"1000","errorMsg":"查询成功。"}
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            LiquorResponse response = null;
            JSONObject jsonObject=null;
            String code=null;
            String data=null;
            try {
                jsonObject=new JSONObject(newstr1);
                code = jsonObject.getString("errorCode");
                data= jsonObject.getString("data");
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
                loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (code.equals("1000")) {
//                下载酒基本表
//                UserCache.save(phone, pwd);
                response = gson.fromJson(data, LiquorResponse.class);
                List<CategoryBean> categoryList=response.getCategory();
                List<CountryBean> countryList=response.getCountry();
                List<OriginBean> originList=response.getOrigin();
                List<VolumeBean> volumeList=response.getVolume();

                CategoryBeanDao categoryDao = MyApp.getInstances().getDaoSession().getCategoryBeanDao();
                CountryBeanDao countryDao = MyApp.getInstances().getDaoSession().getCountryBeanDao();
                OriginBeanDao originDao = MyApp.getInstances().getDaoSession().getOriginBeanDao();
                VolumeBeanDao volumeDao = MyApp.getInstances().getDaoSession().getVolumeBeanDao();

                categoryDao.insertOrReplaceInTx(categoryList);
                countryDao.insertOrReplaceInTx(countryList);
                originDao.insertOrReplaceInTx(originList);
                volumeDao.insertOrReplaceInTx(volumeList);

                mContext.jumpToActivityAndClearTop(MainActivity.class);
                mContext.finish();
            } else {
                mContext.hideWaitingDialog();
                loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
        LiquorBean bean=new LiquorBean();
        bean.setTypeCode("all");
        okHttpUtils.myEnqueue(new LiquorRequest(bean).getUrl(),null);


    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }

    public void register(){
        mContext.jumpToActivity(RegisterActivity.class);
    }
    public void forgetKey(){
        mContext.jumpToActivity(ForgetKeyActivity.class);
    }

}
