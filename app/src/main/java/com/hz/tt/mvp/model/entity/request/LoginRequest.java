package com.hz.tt.mvp.model.entity.request;

import android.util.Base64;

import com.google.gson.Gson;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.model.entity.LoginBean;
import com.hz.tt.util.Md5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 登录请求
 * msg_type
 * data_digest
 * logistics_interface
 * appkey
 */
public class LoginRequest {
    private String userName;
    private String password;
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String getUrl() {
        Gson gson=new Gson();
        LoginBean bean=new LoginBean();
        bean.setUserName(userName);
        bean.setPassword(password);
        String meth = "WINECELLAR_MANAGER_LOGIN"/*typeMeg*/;
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


//    public Map<String, String> getParams() {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg_type","WINECELLAR_MANAGER_LOGIN");//"WINECELLAR_MANAGER_LOGIN"
//        String md5Str=Md5Util.getMD5("{\"userName\":\"123456\",\"password\":\"123456\"}"+"Do&9hY%l8e");
//        map.put("data_digest", Base64.encodeToString(md5Str.getBytes(), Base64.DEFAULT) );
//        map.put("logistics_interface","{\"userName\":\"123456\",\"password\":\"123456\"}");
//        map.put("appkey","123456");
//        return map;
//    }
}
