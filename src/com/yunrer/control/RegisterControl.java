package com.yunrer.control;

import java.io.OutputStream;
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

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.SmsSenderUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.VerifyCodeDao;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.DmbService;
import com.yunrer.service.UserInfoService;
import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.IpUtil;
import com.yunrer.util.MD5Util;
import com.yunrer.util.MD5Utils;
import com.yunrer.util.VerifyCodeUtils;
import com.yunrer.util.XmlUtil;

/**
 * 跳转到注册页
 * @author wangpeng
 */
@Controller
@RequestMapping("/register")
public class RegisterControl {

	@Resource
	private UserInfoService userinfoService;
	@Resource
	private DmbService dmbService;
	@Resource
	private VerifyCodeDao verifyDao;
	/**
	 * 跳转到注册页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toRegister(HttpServletRequest request) {
		//查询代码表
		dmbService.queryDmbList(request);
		return "register";
	}
	

	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 * @param session
	 */
    @RequestMapping(value ="/verifycode",method=RequestMethod.GET)
    public void verifycode(HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	try{
    		OutputStream os = response.getOutputStream();
    		String code = VerifyCodeUtils.generateVerifyCode(4, null);
    		session.setAttribute("VerifyCode", code);
    		VerifyCodeUtils.outputImage(150, 50, os, code);
    	}catch(Exception e){
    		System.out.println("<<<<<<<<<<<<<<<");
    		System.out.println("验证码生成出错...");
    		System.out.println(">>>>>>>>>>>>>>>");
    	}
    	
    }
    
	
    /**
	 * 发送短信验证码
	 * @param session
	 * @param UserInfo
	 * @return
	 */
	@RequestMapping(value = "/fsyzm",method = RequestMethod.POST)
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
	 * 用户注册
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doRegister(HttpSession session,HttpServletRequest request,UserInfo userinfo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(null!=session.getAttribute("YZM")){
				String code = request.getParameter("CODE");
					String yzm =userinfo.getPhone()+"-"+ code;
					if(yzm.equals(session.getAttribute("YZM").toString())){
						//判断该手机号码是否已注册
						boolean flag = userinfoService.isExtis(userinfo.getPhone());
						if(flag){//手机号码已注册
							resultMap.put("error", true);
							resultMap.put("message", "该手机号码已注册！");
						}else{
							//到图灵云接口上注册
							Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
							String Path = map.get("registerPath").toString();
							String Port = map.get("registerPort").toString();
							String Interface = map.get("registerInterface").toString();
							String requesturl = "http://"+Path+":"+Port+"/"+Interface;
							Map<String,String> paramsMap = new HashMap<String, String>();
							Map<String,String> userMap = new HashMap<String, String>();
							userMap.put("Mobile",userinfo.getPhone());
							userMap.put("Password", userinfo.getPassword_text());
							userMap.put("Name", userinfo.getXm());
							userMap.put("ServerName", map.get("hostName").toString());
							String data = JSONUtils.toJSONString(userMap);
							String md5Str = MD5Util.MD5(data+map.get("privateToken"));
							paramsMap.put("data",data );
							paramsMap.put("checkstring", md5Str);
							String result = ConnectionUtil.postRequest(requesturl, paramsMap);
							Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
							if(resMap.get("ResultCode").toString().equals("1")){
								//注册用户
								userinfo.setSso_id(resMap.get("ResultData").toString());
								int id = userinfoService.addUserInfo(userinfo);
								if(id>0){//注册成功
									resultMap.put("count", 1);
									resultMap.put("id", id);
								}else{
									resultMap.put("error", true);
									resultMap.put("message", "注册用户出现错误，请重试！");
								}
								
							}else{
								resultMap.put("error", true);
								resultMap.put("message", resMap.get("ResultMessage"));
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
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	/**
	 * 代用户注册
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value="/register4user")
	@ResponseBody
	public Map<String,Object> doRegisterByManager(HttpSession session,HttpServletRequest request,UserInfo userinfo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			//判断该手机号码是否已注册
			boolean flag = userinfoService.isExtis(userinfo.getPhone());
			if(flag){//手机号码已注册
				resultMap.put("error", true);
				resultMap.put("message", "该手机号码已注册！");
			}else{

				//到图灵云接口上注册
				Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
				String Path = map.get("registerPath").toString();
				String Port = map.get("registerPort").toString();
				String Interface = map.get("registerInterface").toString();
				String requesturl = "http://"+Path+":"+Port+"/"+Interface;
				Map<String,String> paramsMap = new HashMap<String, String>();
				Map<String,String> userMap = new HashMap<String, String>();
				userMap.put("Mobile",userinfo.getPhone());
				userMap.put("Password", userinfo.getPassword_text());
				userMap.put("Name", userinfo.getXm());
				userMap.put("ServerName", map.get("hostName").toString());
				String data = JSONUtils.toJSONString(userMap);
				String md5Str = MD5Util.MD5(data+map.get("privateToken"));
				paramsMap.put("data",data );
				paramsMap.put("checkstring", md5Str);
				String result = ConnectionUtil.postRequest(requesturl, paramsMap);
				Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
				if(resMap.get("ResultCode").toString().equals("1")){
					//注册用户
					userinfo.setSso_id(resMap.get("ResultData").toString());
					int id = userinfoService.addUserInfo(userinfo);
					if(id>0){//注册成功
						resultMap.put("count", 1);
						resultMap.put("id", id);
					}else{
						resultMap.put("error", true);
						resultMap.put("message", "注册用户出现错误，请重试！");
					}
					
				}else{
					resultMap.put("error", true);
					resultMap.put("message", resMap.get("ResultMessage"));
				}
				
			
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
}