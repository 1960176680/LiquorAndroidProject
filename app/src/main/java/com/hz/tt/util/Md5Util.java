package com.hz.tt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * md5算法
 * Created by ZhouWenGuang
 */
public class Md5Util {
	public static String getMD5(String info)
	{
	    try
	    {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        md5.update(info.getBytes("UTF-8"));
	        byte[] encryption = md5.digest();
	        StringBuffer strBuf = new StringBuffer();
	        for (int i = 0; i < encryption.length; i++)
	        {
	            if (Integer.toHexString(0xff & encryption[i]).length() == 1)
	            {
	                strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
	            }
	            else
	            {
	                strBuf.append(Integer.toHexString(0xff & encryption[i]));
	            }
	        }
	        return strBuf.toString();
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	        return "";
	    }
	    catch (UnsupportedEncodingException e)
	    {
	        return "";
	    }
	}
	public static String md5Diagest(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(str.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				String res = Integer.toHexString(result[i] & 0xFF);
				if (res.length() == 1) {
					sb.append("0" + res); // 0~F
				} else {
					sb.append(res);
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String md5Diagest(String str, int bit) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(str.getBytes("UTF-8"));
			for (int i = 0; i < result.length; i++) {
				String res = Integer.toHexString(result[i] & 0xFF);
				if (res.length() == 1) {
					sb.append("0" + res); // 0~F
				} else {
					sb.append(res);
				}
			}
			if (bit == 16) {
				return sb.toString().substring(8, 24);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getData() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	public static String getSimData() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");//设置日期格式
		return df.format(new Date());
	}
}
