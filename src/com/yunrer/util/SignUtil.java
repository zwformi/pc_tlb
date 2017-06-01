package com.yunrer.util;

public class SignUtil {
	public static String getSignedStr(String baseStr,String key){
		StringBuffer stringSignTemp = new StringBuffer(baseStr.toString()+"&key="+key);
		return MD5Util.MD5(stringSignTemp.toString()).toUpperCase();
	}
	
}
