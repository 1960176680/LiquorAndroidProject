package com.hz.tt.mvp.ui.activity;

import android.util.Base64;

import com.hz.tt.api.MyApi;
import com.hz.tt.util.Md5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ZhouWenGuang  用户登录msg_type：WINECELLAR_MANAGER_LOGIN
 * logistics_interface：{\"userName\":\"123456\",\"password\":\"123456\"}
 *
 *
 *            logistics_interface：请求报文json
                   msg_type：请求接口
                  data_digest：加密字符串（base64（md5（logistics_interface+appsecret）））
 appkey：密钥
 */
public class RegisterRequest {
    String typeMeg;
    String requestMeg;
    private String encode;
    String url;
    public RegisterRequest(){

    }
//    public RegisterRequest(String typeMeg, String requestMeg) {
//        this.typeMeg = typeMeg;
//        this.requestMeg = requestMeg;
////        try {
////            encode = URLEncoder.encode(requestMeg, "UTF-8");
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
//    }
    public String getUrl() {
        String meth = "WINECELLAR_MANAGER_LOGIN"/*typeMeg*/;
        String key = "{\"userName\":\"123456\",\"password\":\"123456\"}"/*requestMeggson.toJson(user)*/;
        String secretKey = "Do&9hY%l8e";
        String md5 = "";
        String vkey = "";
        String ckey = "";
        try {
            ckey = URLEncoder.encode(key, "UTF-8");
            vkey = key + secretKey;
            md5 = Md5Util.getMD5(vkey);
            md5 = Base64.encodeToString(md5.getBytes(), Base64.DEFAULT);
//            Log.d("test", "md5:" + md5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url = MyApi.BASE_URL+"?msg_type=" + meth + "&data_digest=" + md5 + "&logistics_interface=" + ckey+"&appkey=123456";
        return url;
    }

//    public Map<String, String> getParams() {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg_type",typeMeg);
//        map.put("msg_digest", Md5Util.getMD5(requestMeg+Constant.URL_KEY));
//        map.put("msg_interface",encode);
//        return map;
//    }
}
