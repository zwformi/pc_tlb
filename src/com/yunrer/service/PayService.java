package com.yunrer.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunrer.util.*;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;

import com.yunrer.dao.OrderDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.UserInfo;

@Service("PayService")
public class PayService {

	@Resource
	private OrderDao orderDao;

	PropertiesReadUtil weixinConfig=new PropertiesReadUtil("/resources/weixinPayConfig.properties");

	public PayService() throws IOException {
	}

	// 生成二维码字符串（新）
	@SuppressWarnings("rawtypes")
	public String createString(HttpServletRequest request)
			throws JDOMException, IOException {
		String order_number = request.getParameter("order_number");
		if (order_number != null && order_number.length() == 26) {
			OrderInfo info = orderDao
					.queryOrder_Detail(order_number);

			if (info != null) {

				// 账号信息
				String appid = weixinConfig.getProperty("weixinPay.APP_ID"); // appid
				String appsecret = weixinConfig.getProperty("weixinPay.APP_SECRET"); // appsecret
				String mch_id = weixinConfig.getProperty("weixinPay.MCH_ID"); // 商业号
				String key = weixinConfig.getProperty("weixinPay.API_KEY"); // key


				String nonce_str = PayCommonUtil.getNonceString(32);

				String order_price = "" + (int) (info.getPayment_money() * 100); // 价格，单位是分
				String body = "图灵买商城微信支付"; // 商品名称
				String out_trade_no = info.getOrder_number(); // 订单号

				// 获取发起电脑 ip
				// String spbill_create_ip = info.getIp();
				String spbill_create_ip = weixinConfig.getProperty("weixinPay.ServerIpAddress");
				// 回调接口
				String notify_url = weixinConfig.getProperty("weixinPay.NOTIFY_URL");
				String trade_type = "NATIVE";

				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				packageParams.put("appid", appid);
				packageParams.put("mch_id", mch_id);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("body", body);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", order_price);
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);
				packageParams.put("trade_type", trade_type);
				String sign = PayCommonUtil.createSign("UTF-8", packageParams,
						key);
				packageParams.put("sign", sign);

				String requestXML = PayCommonUtil.getRequestXml(packageParams);
				String resXml = HttpUtil.postData(weixinConfig.getProperty("weixinPay.UFDODER_URL"),
						requestXML);
				Map map = XMLUtils.doXMLParse(resXml);
				String urlCode ="";
				if(map!=null &&  map.get("code_url")!=null){
					urlCode = map.get("code_url").toString();
					return urlCode;
					}
					
			}
		}

		return null;

	}

	// 接受回调
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void weixin_notify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtils.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		String key = weixinConfig.getProperty("weixinPay.API_KEY"); // key

		System.out.println(packageParams);
		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				/********************执行自己的业务逻辑************************/
				String mch_id = (String) packageParams.get("mch_id");
				String openid = (String) packageParams.get("openid");
				String is_subscribe = (String) packageParams
						.get("is_subscribe");
				String out_trade_no = (String) packageParams
						.get("out_trade_no");

				String total_fee = (String) packageParams.get("total_fee");
				// 修改订单状态为已支付
				
				OrderInfo  orderinfo = orderDao.queryOrder_Detail(out_trade_no);
				if (out_trade_no != null && out_trade_no.length() == 26)
					orderDao.modifyOrder(orderinfo.getOrder_source()==4?1:4, out_trade_no);
				System.out.println("mch_id:" + mch_id);
				System.out.println("openid:" + openid);
				System.out.println("is_subscribe:" + is_subscribe);
				System.out.println("out_trade_no:" + out_trade_no);
				System.out.println("total_fee:" + total_fee);

				/**********************执行自己的业务逻辑**********************/

				System.out.println("支付成功");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>"
						+ "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				System.out
						.println("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>"
						+ "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>"
						+ "</xml> ";
			}
			// 处理业务完毕
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			System.out.println("通知签名验证失败");
		}

	}

	// 将订单信息存放到session中
	public void setPaySession(HttpServletRequest request, HttpSession session) {
		String order_number = request.getParameter("order_number");
		if (order_number != null && order_number.length() == 26) {
			OrderInfo info = orderDao.queryOrder_Detail(order_number);
			if (info != null) {
				session.removeAttribute("orderdetial");
				session.setAttribute("orderdetial", info);
				List<OrderDetails> OrderDetailsList = orderDao.queryOrderDetailList(order_number);
				if(OrderDetailsList.size()>0)
					session.setAttribute("order_detail_list", OrderDetailsList);
				else session.removeAttribute("order_detail_list");

			}else{
				session.removeAttribute("orderdetial");
				session.removeAttribute("order_detail_list");
			}
		}else{
			session.removeAttribute("orderdetial");
			session.removeAttribute("order_detail_list");
		}
	}
	
	public Map<String,Object> queryInfo(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String order_number = request.getParameter("order_number");
		if(order_number!=null && !order_number.equals("")){
			OrderInfo info = orderDao.queryOrder_Detail(order_number);
			if(info!=null){
				if(info.getOrder_state()==4||info.getOrder_state()==1){
				map.put("res_code", 1);
				map.put("message", "付款成功");
			}else{
				map.put("res_code", 2);
				map.put("message", "订单未付款");
				
			}
			
				
			}else{
				map.put("res_code", 0);
				map.put("message", "订单不存在");
				
			}
			
		}
		return map;
	}
	
	
}
