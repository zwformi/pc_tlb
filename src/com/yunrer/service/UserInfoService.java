package com.yunrer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.CommonMd5;
import com.yunrer.dao.ApplicationDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.Application;
import com.yunrer.entity.UserClient;
import com.yunrer.entity.UserFeedback;
import com.yunrer.entity.UserInfo;
import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.MD5Util;
import com.yunrer.util.XmlUtil;

@Service("UserInfoService")
public class UserInfoService {
	
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private ApplicationDao  appDao;

	/**
	 * 查询手机号码是否已注册
	 */
	public boolean isExtis(String phone) {
		return userInfoDao.isExtis(phone);
	}
	
	/**
	 * 根据手机号查询用户信息
	 */
	public UserInfo queryUserInfo(String phone) {
		UserInfo user = new UserInfo();
		user.setPhone(phone);
		user = userInfoDao.queryUserInfo(user);
		return user;
	}
	
	/**
	 * 根据sso_id户信息
	 */
	public UserInfo queryUserBySsoId(String sso_id) {
		UserInfo user = new UserInfo();
		user.setSso_id(sso_id);
		user = userInfoDao.queryUserBySsoId(user);
		return user;
	}
	
	/**
	 * 通过用户名密码获得用户信息
	 */
	public int loginUser(UserInfo userinfo,HttpSession session) {
		UserInfo user = userInfoDao.queryUserInfo(userinfo);//登陆
		if(user!=null){//用户登录成功
			
			//判断密码是否正确
			String db_password =user.getPassword_str();
			CommonMd5 md5 = new CommonMd5();
			if(md5.deCodeStringBykey(db_password,"passwordkey").equals(userinfo.getPassword_str())){
				session.setAttribute("loginUser", user);
				return 1;
			}else{//用户名或密码错误
				return -1;
			}
		}else{//用户名或密码错误
			return -1;
		}
	}
	
	/**
	 * 查询用户客服信息
	 */
	public UserClient queryUserClient(int client_id,HttpSession session) {
		return userInfoDao.queryUserClient(client_id);
	}
	
	/**
	 * 用户注册
	 */
	public int addUserInfo(UserInfo userinfo){
		int id = userInfoDao.addUserInfo(userinfo);
		return id;
	}
	
	/**
	 * 修改用户手机号码
	 */
	public int updatePhone(String value,int user_id,String sso_id){
		
		int count =0;
		try{
		//到图灵云接口上修改
		Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
		String Path = map.get("registerPath").toString();
		String Port = map.get("registerPort").toString();
		String Interface = map.get("updateInterface").toString();
		String requesturl = "http://"+Path+":"+Port+"/"+Interface;
		Map<String,String> paramsMap = new HashMap<String, String>();
		Map<String,String> userMap = new HashMap<String, String>();
		userMap.put("Mobile", value);
		userMap.put("ServerName", map.get("hostName").toString());
		String data = JSONUtils.toJSONString(userMap);
		String md5Str = MD5Util.MD5(data+map.get("privateToken"));
		paramsMap.put("data",data );
		paramsMap.put("systemuserid",sso_id );
		paramsMap.put("checkstring", md5Str);
		String result = ConnectionUtil.postRequest(requesturl, paramsMap);
		Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
		if(resMap.get("ResultCode").toString().equals("1")){
			count = userInfoDao.updateInfo("phone",value,user_id);
		}else{
			count = 0;
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return count;

	}
	
	/**
	 * 修改用户基本信息
	 * @param userinfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userinfo){
		int count =0;
		try{
		//到图灵云接口上修改
		Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
		String Path = map.get("registerPath").toString();
		String Port = map.get("registerPort").toString();
		String Interface = map.get("updateInterface").toString();
		String requesturl = "http://"+Path+":"+Port+"/"+Interface;
		Map<String,String> paramsMap = new HashMap<String, String>();
		Map<String,String> userMap = new HashMap<String, String>();
		userMap.put("Name", userinfo.getXm());
		userMap.put("ServerName", map.get("hostName").toString());
		String data = JSONUtils.toJSONString(userMap);
		String md5Str = MD5Util.MD5(data+map.get("privateToken"));
		paramsMap.put("data",data );
		paramsMap.put("systemuserid",userinfo.getSso_id() );
		paramsMap.put("checkstring", md5Str);
		String result = ConnectionUtil.postRequest(requesturl, paramsMap);
		Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
		if(resMap.get("ResultCode").toString().equals("1")){
			count = userInfoDao.updateUserInfo(userinfo);
		}else{
			count = 0;
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return count;
	}
	
	/**
	 * 修改图像
	 */
	public int updateImg(String value,int user_id){
		int count = userInfoDao.updateInfo("headimgurl",value,user_id);
		return count;
	}
	
	/**
	 * 修改密码
	 */
	public int updatePsd(String value,String password_text,UserInfo user){
		int count = 0;
		//到图灵云上注册信息
		try{
		Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
		String Path = map.get("registerPath").toString();
		String Port = map.get("registerPort").toString();
		String Interface = map.get("passwordResetInterface").toString();
		String requesturl = "http://"+Path+":"+Port+"/"+Interface;
		Map<String,String> userMap = new HashMap<String, String>();
		userMap.put("servername",map.get("hostName").toString());
		userMap.put("systemuserid", user.getSso_id());
		userMap.put("password",password_text);
		String md5Str = MD5Util.MD5(user.getSso_id()+map.get("privateToken"));
		userMap.put("checkstring", md5Str);
		String result = ConnectionUtil.postRequest(requesturl, userMap);
		Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
		if(resMap.get("ResultCode").toString().equals("1")){
			count = userInfoDao.updateInfo("password_str",value,user.getUser_id());
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return count;
	}
	
	/**
	 * 保存用户留言
	 * @param UserFeedback
	 * @return
	 */
	public int saveUserFeedback(HttpSession session,HttpServletRequest request){
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		String message = request.getParameter("message");
		
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setUser_id(userinfo.getUser_id());
		userFeedback.setMessage(message);
		userFeedback.setCreate_time(new Date());
		int count = userInfoDao.saveUserFeedback(userFeedback);
		
		return count;
	}
	
	/**
	 * 修改用户申请状态及角色所属公司
	 * 	 
	 * */
	@SuppressWarnings("finally")
	@Transactional
	public Map<String,Object> acceptUser(HttpServletRequest request,HttpSession session,Application application){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			int num = 0;
			num = appDao.modifyApplication(application);
			if(application.getStatus()==20)
			num = num * userInfoDao.updateInfo("owning_company", application.getOwning_company()+"", application.getUser_id());

			if(num>0){
				map.put("rescode", 1);
			}else{
				map.put("rescode", 0);
				map.put("resMsg", "操作失败！");
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			return map;
		}	
	}
	
	/**
	 * 修改用户某一字段的信息
	 */
	@SuppressWarnings("finally")
	public Map<String,Object> modifyUserInfo(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			
			Integer user_id = Integer.parseInt(request.getParameter("user_id"));
			String param = request.getParameter("param");
			String value = request.getParameter("value");
			int num = userInfoDao.updateInfo(param, value, user_id);
			if(num > 0 ){
				map.put("rescode", 1);
				
			}else{
				map.put("rescode", 0);
				map.put("resMsg", "修改操作失败！");
			}
		}catch(Exception e ){
			e.getStackTrace();
		}finally{
			if(map.isEmpty()){
				map.put("rescode", 0);
				map.put("resMsg", "操作异常..");
				
			}
			return map;
		}
	}
}
