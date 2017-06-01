package com.yunrer.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yunrer.common.CommonMd5;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.MD5;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.UserInfo;

/**
 * 用户中心拦截器
 */
public class UserInteceptor implements HandlerInterceptor {
	
	@Resource
	private UserInfoDao userInfoDao;
	
	/**
	 * 拦截器的前端，执行控制器之前所要处理的方法，
	 * 通常用于权限控制、日志，其中，Object o表示下一个拦截器；
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) 
		throws Exception {
		HttpSession session = request.getSession(true);
		
		try{
			if (session.getAttribute("loginUser") == null) {
				UserInfo autoUser =null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("userinfo")) {
							String des_str = cookie.getValue();
							
							CommonMd5 commonMd5 = new CommonMd5();
							String str = commonMd5.deCodeStringBykey(des_str, "loginkey");
							String[] str_arr = str.split(",");
							
							UserInfo userInfo = new UserInfo();
							userInfo.setPhone(str_arr[0]);
							autoUser = userInfoDao.queryUserInfo(userInfo);
							
							if (autoUser != null && autoUser.getPassword_str() != null) {
								//待加密字符串
								String wait_str = str_arr[0] + commonMd5.deCodeStringBykey(autoUser.getPassword_str(),"passwordkey") + commonMd5.getKey("loginkey");			
								//MD5加密字符串
								String md5_str = MD5.GetMD5Code(wait_str);
								
								//自动登录成功
								if(md5_str.equals(str_arr[1])){
									session.setAttribute("loginUser", autoUser);
								}
							}
						}
					}
				}
				if(autoUser==null){
					String html_url=request.getServletPath();
					String  uri=request.getRequestURI();
					String  param=request.getQueryString();
					String path = "www.tulingbuypc.cc";
					String url=path+html_url+"?"+param;
					session.setAttribute("url",url);
					if(html_url.equals("/index.html")){
						return true;
					}else{
						response.sendRedirect("/login.json");
						return false;
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 控制器的方法已经执行完毕，转换成视图之前的处理；
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	/**
	 * 视图已处理完后执行的方法，通常用于释放资源；
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn) 
		throws Exception {
		
	}
}