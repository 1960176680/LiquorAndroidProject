package com.hz.tt.mvp.model.entity.request;

import android.util.Base64;

import com.google.gson.Gson;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.model.entity.InUpBean;
import com.hz.tt.util.Md5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2018-02-11.
 */

public class UpInRecordRequest {
    private InBean inBean;

    public UpInRecordRequest(List<InBean> datas) {
        inBean=datas.get(0);
    }
    public String getUrl() {
        Gson gson=new Gson();
        InUpBean inUpBean=new InUpBean();
        inUpBean.setRecordCode(inBean.getCode());
        inUpBean.setCategory(inBean.getType());
        inUpBean.setCountry(inBean.getCountry());
        inUpBean.setOrigin(inBean.getBirthday());
        inUpBean.setVolume(inBean.getCapacity());
        inUpBean.setProductiveYear(inBean.getYear());
        inUpBean.setCountNum(Integer.valueOf(inBean.getNumber()));
        inUpBean.setIntoDate(inBean.getTime());
        inUpBean.setIntoCustomer(inBean.getPerson());
        inUpBean.setPhoto(inBean.getImgstr());
        inUpBean.setRemark("");
        inUpBean.setPosition(inBean.getLocation());

        String meth = "WINECELLAR_MANAGER_INTO_LIBRARY"/*typeMeg*/;
        String jsonObject =gson.toJson(inUpBean);    /*"{\"userName\":\"123456\",\"password\":\"123456\"}"requestMeg  gson.toJson(user)*/
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