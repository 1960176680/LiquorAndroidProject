package com.hz.tt.api.okHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-10.
 */

public class ParseJsonUtil {

    public List<String> parseLeaveDeliveryJson(String result) {
        List<String> leaveDeliveryReasonList = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = new JSONArray(jsonObject.optString("result"));
            String dataStr = jsonArray.getString(0);
            JSONObject jsonObject1 = new JSONObject(dataStr);
            String leaveDeliveryReasonStr = jsonObject1.getString("data");
            String leaveDeliveryStrList1[] = leaveDeliveryReasonStr.split("#");
            int i = 0;
            for (String tem : leaveDeliveryStrList1) {
                int a = tem.indexOf('|', 0);
                int b = tem.indexOf('|', a + 1);
                String leaveDeliveryStr = tem.substring(a + 1, b);
                leaveDeliveryReasonList.add(i + "、" + leaveDeliveryStr);
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leaveDeliveryReasonList;
    }

//    public static List<MoneyDetailBean>  parseMoneyDetailJson(Context context,String result) {
//        List<MoneyDetailBean> moneyDetailList = new ArrayList<MoneyDetailBean>();
//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            JSONArray jsonArray = new JSONArray(jsonObject.optString("result"));
//            String dataStr = jsonArray.getString(0);
//            JSONObject jsonObject1 = new JSONObject(dataStr);
//            String moneyDetailStr = jsonObject1.getString("data");
//            if (moneyDetailStr.startsWith("http")){
//                CommenUtils.showSafeToast(context,"只能选择最近几个月的时间！");
//                return moneyDetailList;
//            }
//            if(moneyDetailStr.equals("")) return moneyDetailList;
//            String moneyDetailStrList1[] = moneyDetailStr.split("#");
//
//            for (String tem : moneyDetailStrList1) {
//                String[] data=tem.split("\\|");
//                MoneyDetailBean moneyDetailBean=new MoneyDetailBean();
//                moneyDetailBean.setTradeType(data[2]+"-"+data[3]);
//                moneyDetailBean.setTradeMoney(data[4]);
//                moneyDetailBean.setTradeTime(data[0].substring(5));
//                if(data[5].contains("失败")){
//                    data[5]="失败已返款：收款用户不存在";
//                }
//
//
//                moneyDetailBean.setTradeResult(data[5]);
//                moneyDetailList.add(moneyDetailBean);
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return moneyDetailList;
//    }

    /**
     *
     * @param context
     * @param result
     * @return
     */
//    public static ArrayList<DeliveryListBean>  parseDeliveryListJson(Context context,String result) {
//        ArrayList<DeliveryListBean> deliveryListBeanList = new ArrayList<DeliveryListBean>();
//            String[] deliveryListBeanArray = result.split("#");
//
//            for (String tem : deliveryListBeanArray) {
//                String[] data=tem.split("\\|");
//                DeliveryListBean deliveryListBean=new DeliveryListBean();
//                deliveryListBean.setDanHao(data[0]);
//                if (!checkDataSignRepetition(context,new DatabaseUtil(context).open(),data[0])){
//                    deliveryListBean.setRepeat(true);
//                }else{
//                    deliveryListBean.setRepeat(false);
//                }
//
//
//
//
//                if (data.length>1){
//                    String[] location= LocationManagerUtil.locationResult.split(",");
//                    if (location!=null&&location.length==2&&data[2]!=null&&!data[2].equals("")){
//                        String[] currentLocation=data[2].split(",");
//                        float distance= AMapUtils.calculateLineDistance(new LatLng(Double.valueOf(currentLocation[1]),Double.valueOf(currentLocation[0])),new LatLng(Double.valueOf(location[1]),Double.valueOf(location[0])));
//                        if(distance>10000){
//
//                            deliveryListBean.setLongiAndlati(">10000m");
//                        }else{
//                            String[] dis=(distance+"").split("\\.");
//                            deliveryListBean.setLongiAndlati(dis[0]+"m");
//                        }
//                    }
//
//
//
////                    deliveryListBean.setLongiAndlati(data[2]==null?"":data[2]);
//                    deliveryListBean.setAddress(data[4]==null?"":data[4]);
//                }
//
//                deliveryListBeanList.add(deliveryListBean);
//
//            }
//        return deliveryListBeanList;
//    }
//    public static boolean checkDataSignRepetition(Context mContext, DatabaseUtil db, String code) {
//        List<SignData> datas = db.querySingleData(code);
//        if (datas.size() > 0) {
////            CommenUtils.toast(mContext, "单号重复无法添加数据!");
////            CommenUtils.vibrator(mContext);
////            startMedia(mContext);
//            return false;
//        }
//        return true;
//    }

}