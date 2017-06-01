package com.yunrer.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/***
 * 
 * @author zgf
 *
 */

// 链接格式：weixin：//wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D

// 公众账号ID appid String(32) 是 wx8888888888888888 微信分配的公众账号ID
// 商户号 mch_id String(32) 是 1900000109 微信支付分配的商户号
// 时间戳 time_stamp String(10) 是 1414488825 系统当前时间，定义规则详见时间戳
// 随机字符串 nonce_str String(32) 是 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
// 随机字符串，不长于32位。推荐随机数生成算法
// 商品ID product_id String(32) 是 88888 商户定义的商品id 或者订单号
// 签名 sign String(32) 是 C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法

public class QRStrUtil {

	/****
	 * 二维码链接生成
	 * 
	 * @throws Exception
	 *********/

	public static String getQRString(String xmlPath) throws Exception {
		Map<String, String> xmlMap = new HashMap<String, String>();
		StringBuffer baseStr = new StringBuffer();
		String baseUrl = "weixin://swxpay/bizpayurl?";
		String timeStamp = getTimeStamp();
		String nonceStr = getNonceString(32);
		xmlMap = XmlUtil.paserXmlByDOM4J(xmlPath);
		baseStr.append("appid=" + xmlMap.get("wx_AppID"));
		baseStr.append("&mch_id=" + xmlMap.get("wx_mch_id"));
		baseStr.append("&product_id=" + "p012345678901234567890123456789p");
		baseStr.append("&time_stamp=" + timeStamp);
		baseStr.append("&nonce_str=" + nonceStr);
		String signStr = SignUtil.getSignedStr(baseStr.toString(),xmlMap.get("wx_signKey"));
		if(signStr!=null && !signStr.equals(""))
			baseStr.insert(0, "sign="+signStr.toString()+"&");
		return baseUrl+baseStr.toString();

	}

	public static String getTimeStamp() {
		StringBuffer timeStamp = new StringBuffer();
		timeStamp.append(System.currentTimeMillis());
		return timeStamp.toString().substring(0, timeStamp.length() - 3);

	}

	public static String getNonceString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();

	}
}
