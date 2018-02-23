package com.hz.tt.mvp.model.entity.request;

import android.util.Base64;

import com.google.gson.Gson;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.model.entity.SettingPswBean;
import com.hz.tt.util.Md5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2018-02-13.
 */

public class SetPswRequest {

    public SetPswRequest() {

    }
    public String getUrl(SettingPswBean bean) {
        Gson gson=new Gson();
        String meth = "WINECELLAR_MANAGER_RESET_PASSWORD"/*typeMeg*/;
        String jsonObject =gson.toJson(bean);    /*"{\"userName\":\"123456\",\"password\":\"123456\"}"requestMeg  gson.toJson(user)*/
        String appsecret = "Do&9hY%l8e";
        String md5 = "";
        String enJsonObject = "";
        try {
            enJsonObject = URLEncoder.encode(jsonObject, "UTF-8");
            md5 = Md5Util.getMD5(jsonObject + appsecret);
            md5 = Base64.encodeToString(md5.getBytes(),Base64.DEFAULT).trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = NetConstant.BASE_URL+"?msg_type=" + meth + "&data_digest=" + md5 + "&logistics_interface=" +enJsonObject+"&appkey=123456";
        return url;
    }
}