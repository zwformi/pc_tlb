package com.yunrer.control;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.SmsSenderUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.VerifyCodeDao;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.UserInfoService;
import com.yunrer.util.IpUtil;

/**
 * 跳转到登陆页
 * @author wangpeng
 */
@Controller
public class PhoneControl {
	@Resource
	private UserInfoService userinfoService;
	@Resource
	private VerifyCodeDao verifyDao;
	
	/**
	 * 发送验证码
	 * @param session
	 * @param UserInfo
	 * @return
	 */
	@RequestMapping(value = "/phone_fsyzm",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doFsyzm(HttpSession session,HttpServletRequest request) {
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
				}else if(userinfoService.isExtis(phone)){//手机号码已注册
					resultMap.put("error", true);
					resultMap.put("message", "该号码已注册！");
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
	 * 用户修改手机号码
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/phone_update",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doRegister(HttpSession session,HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
				if(null!=session.getAttribute("YZM")){
					String code = request.getParameter("CODE");
					String newphone = request.getParameter("NEWPHONE");
						String yzm =newphone+"-"+ code;
						if(yzm.equals(session.getAttribute("YZM").toString())){
							boolean flag = userinfoService.isExtis(newphone);
							if(flag){//手机号码已注册
								resultMap.put("error", true);
								resultMap.put("message", "该号码已注册,请返回修改！");
							}else{
								//修改用户手机号码
								int count = userinfoService.updatePhone(newphone,userinfo.getUser_id(),userinfo.getSso_id());
								if(count>0){//修改成功
									userinfo.setPhone(newphone);
									resultMap.put("count", count);
								}else {
									resultMap.put("error", true);
									resultMap.put("message", "修改手机号码出现未知错误");
								}
							}
						}else{
							resultMap.put("error", true);
							resultMap.put("message", "验证码输入错误...");
						}
				}else{
					resultMap.put("error", true);
					resultMap.put("message", "验证码未获取...");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
}