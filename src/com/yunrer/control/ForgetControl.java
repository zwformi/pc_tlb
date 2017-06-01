package com.yunrer.control;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.CommonMd5;
import com.yunrer.common.SmsSenderUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.VerifyCodeDao;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.UserInfoService;
import com.yunrer.util.IpUtil;

/**
 * 找回密码
 */
@Controller
@RequestMapping("/forget")
public class ForgetControl {
	
	@Resource
	private UserInfoService userinfoService;
	
	@Resource
	private VerifyCodeDao verifyDao;
	
	@RequestMapping(value = "/step1", method = RequestMethod.GET)
	public String toForgetPsd(HttpSession session, HttpServletResponse response) {
		return "forget_psd";
	}
	
	@RequestMapping(value = "/step2", method = RequestMethod.GET)
	public String toForgetPsd02(HttpSession session, HttpServletResponse response) {
		if (null != session.getAttribute("CHECK") && null != session.getAttribute("PHONE")) {
			return "forget_psd02";
		} else {
			return "redirect:/";
		}
	}
	
	/**
	 * 发送验证码
	 */
	@RequestMapping(value = "/fsyzm",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doFsyzm(HttpSession session,
			HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(session.getAttribute("VerifyCode")!=null){
			String verifycode = request.getParameter("VerifyCode").toLowerCase();
			String verifycode1 =((String)session.getAttribute("VerifyCode")).toLowerCase();
			if(verifycode.equals(verifycode1)&&verifycode!=null&&verifycode1!=null){
 			try{
				String phone = request.getParameter("phone");
				//判断该手机号码格式
				Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
				Matcher m = p.matcher(phone);
				if(!m.matches()){//手机号码格式判断
					resultMap.put("error", true);
					resultMap.put("message", "手机号格式不正确！");
				}else if(!userinfoService.isExtis(phone)){//手机号码已注册
					resultMap.put("error", true);
					resultMap.put("message", "该号码不存在！");
				}else{
					boolean sffsyzm=true;
					if(null!=session.getAttribute("YZMHQSJ")){
						long currentSjc = System.currentTimeMillis();
						if(Long.parseLong(session.getAttribute("YZMHQSJ").toString())>=currentSjc-(60*1000)){
							sffsyzm=false;
						}
					} 
					if(sffsyzm){//判断验证发送时间是否间隔有1分钟以上
						int yzm = Utils.GetSixRandomNumber();//获得验证码
						System.out.println("----------------验证码："+yzm);
						//发送短信验证码
						if(SmsSenderUtils.sendYzm(phone, yzm+",30")){
							session.removeAttribute("VerifyCode");
							session.setAttribute("PHONE", phone);// 存储手机号
							session.setAttribute("YZM", phone+"-"+yzm+"");//存储验证码
							verifyDao.insetData(IpUtil.getIpAddress(request), phone, yzm+"");
							resultMap.put("yzm", 1);
						}else{
							resultMap.put("error", true);
							resultMap.put("message", "短信验证码发送失败！");
						}
						session.setAttribute("YZMHQSJ", System.currentTimeMillis());//存储获取验证码的时间
					}else{
						resultMap.put("error", true);
						resultMap.put("message", "验证码发送太频繁<br/>请稍后重试...");
					}
				}
		
			
			}catch(Exception e){
				resultMap.put("error", true);
				resultMap.put("message", "发送验证码出现错误...");
			}
			
			}else{
				
				resultMap.put("error", true);
				resultMap.put("message", "图形验证码错误，请重试...");
				
			}
		}
 		else{	
 			resultMap.put("yzm", 0);
			resultMap.put("message", "请手动刷新图形验证码...");
		}
 		return resultMap;
	
	}
	
	/**
	 * 下一步验证
	 */
	@RequestMapping(value = "/submit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doSubmit(HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (null != session.getAttribute("YZM") && null != session.getAttribute("PHONE")) {
				String code = session.getAttribute("PHONE")+"-"+request.getParameter("code");
				if (code.equals(session.getAttribute("YZM").toString())) {
					session.setAttribute("CHECK", true);

					resultMap.put("error", false);
					resultMap.put("message", "验证通过");
				} else {
					resultMap.put("error", true);
					resultMap.put("message", "验证码输入错误...");
				}
			} else {
				resultMap.put("error", true);
				resultMap.put("message", "验证码未获取...");
			}
		} catch (Exception e) {
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/reset",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doReset(HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (null != session.getAttribute("CHECK") && null != session.getAttribute("PHONE")) {
				boolean check = Boolean.parseBoolean(session.getAttribute("CHECK").toString());
				String phone = session.getAttribute("PHONE").toString();
				if (check) {
					String newpsd = request.getParameter("password_str");
					CommonMd5 md5 = new CommonMd5();
					// 密码加密
					String password = md5.enCodeStringByKey(newpsd, "passwordkey");
					String password_text = request.getParameter("password_text");
					UserInfo user = userinfoService.queryUserInfo(phone);
					
					int count = userinfoService.updatePsd(password,password_text,user );
					
					if (count > 0) {
						resultMap.put("error", false);
						resultMap.put("message", "密码重置成功，请妥善保管");
						
						session.setAttribute("CHECK", false);
					}
				} else {
					resultMap.put("error", true);
					resultMap.put("message", "非法操作");
				}
			} else {
				resultMap.put("error", true);
				resultMap.put("message", "非法操作");
			}
		} catch (Exception e) {
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
}