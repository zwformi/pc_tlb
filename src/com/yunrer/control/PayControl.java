package com.yunrer.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.PayService;
import com.yunrer.util.IpUtil;
import com.yunrer.util.QRStrUtil;

/***
 * 
 * @author gfzhang
 *
 */

@Controller
@RequestMapping("/pay")
public class PayControl {

	@Resource
	private PayService ps;
	
	@RequestMapping(value = "/wxpay")
	public void callBackPay(HttpServletRequest request,HttpServletResponse response) {
		try {
			ps.weixin_notify(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping
	public String toPay(HttpServletRequest request, HttpSession session)
			throws Exception {
			session.setAttribute("QRStr",ps.createString(request));
			ps.setPaySession(request, session);
		return "pay";
	}
	
	@ResponseBody
	@RequestMapping(value="getstate",method=RequestMethod.POST)
	public Map<String,Object> getState(HttpServletRequest request)
			throws Exception {
		Map<String,Object> map = ps.queryInfo(request);
		return map;
	}

}
