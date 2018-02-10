package com.hz.tt.api.okHttpUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class NetStaticShareData implements Serializable {

//   这里的静态参数是多余的，这些参数在loginActivity类已经存好了值
    public static String sitecode1;
    public static String Sitename;
    public static String empcode1;
    public static String Empname;
    public static String Lastupdatetime;
    public static String password1;
    public static String mySelf;
    public static  String bluetoothStatus;
    public static Map<String,String> Person_Number_Map;
    public static Map<String,String> Number__Person_Map;
    public static List<String> Number__Person_List_allDanhao;
    public static List<String> leaveDeliveryReasonList;
//  员工表list
    public  static List<String> Deliverylist;
    private String version;
    private String network;
    private String ip;
    private String mobile;
    private String machineextid;
    private String machineid;
    public  String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMachineextid() {
        return machineextid;
    }

    public void setMachineextid(String machineextid) {
        this.machineextid = machineextid;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public static String getSitecode() {
        return sitecode1;
    }

    public static void setSitecode(String sitecode) {
        sitecode1 = sitecode;
    }

    public static String getEmpcode() {
        return empcode1;
    }

    public static void setEmpcode(String empcode) {
        empcode1 = empcode;
    }

    public static String getPassword() {
        return password1;
    }

    public static void setPassword(String password) {
        password1 = password;
    }
    public static String getSitename() {
        return Sitename;
    }

    public static void setSitename(String sitename) {
        Sitename = sitename;
    }

    public static String getEmpname() {
        return Empname;
    }

    public static void setEmpname(String empname) {
        Empname = empname;
    }

    public static String getLastupdatetime() {
        return Lastupdatetime;
    }

    public static void setLastupdatetime(String lastupdatetime) {
        Lastupdatetime = lastupdatetime;
    }
}
