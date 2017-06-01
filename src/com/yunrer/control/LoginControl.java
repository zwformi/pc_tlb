 package com.yunrer.control;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.CommonMd5;
import com.yunrer.common.ControlUtils;
import com.yunrer.common.MD5;
import com.yunrer.entity.Ad;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.AdService;
import com.yunrer.service.UserInfoService;
import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.XMLUtils;
import com.yunrer.util.XmlUtil;

/**
 * 跳转到登陆页
 * @author wangpeng
 */
@Controller
@RequestMapping("/login")
public class LoginControl {
	@Resource
	private UserInfoService userinfoService;
	@Resource
	private AdService adService;


	/**
	 * 登陆跳转
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String toLogin(HttpSession session,HttpServletRequest request) {
		String requestUrl="";
		if(session.getAttribute("loginUser")!=null){
			return "redirect:/find/find_main.html";//如果已登录跳转到订单合同概览
		}else{
			ModelMap mmap = new ModelMap();
			try {
				
				Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
				System.out.println(map);
				UUID uuid = UUID.randomUUID();
				session.setAttribute("tmpToken", uuid.toString());
				String returnUrl = map.get("returnUrl").toString();
			    requestUrl = map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
			    requestUrl += "?tmpToken="+ uuid.toString();
			    requestUrl += "&returnUrl=http://"+ returnUrl;
			    System.out.println(requestUrl);
			    ConnectionUtil.postRequest("http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("checkAliveInterface")), null);
			} catch (Exception e) {
				e.printStackTrace();
				return "login";
			}
			return "redirect://"+requestUrl;
			
		}
	}
	
	
	/**
	 * 用户登陆
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doLogin(HttpServletResponse response, HttpSession session,HttpServletRequest request,UserInfo userinfo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String url=(String) session.getAttribute("url");
		if(url!=null)
		url=url.substring(19);
		else
			url="";
		if(url.contains("out.html")||url.contains("out.json"))
			url="";
		resultMap.put("url", url);
		try{
			CommonMd5 commonMd5 = new CommonMd5();
			int rememberPassword = 0;
			try {
				if(request.getParameter("rememberPassword") != null){
					rememberPassword = Integer.parseInt(request.getParameter("rememberPassword"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			if (rememberPassword == 1) {
				//待加密字符串
				String wait_str = userinfo.getPhone() + userinfo.getPassword_str() + commonMd5.getKey("loginkey");			
				//MD5加密字符串
				String md5_str = MD5.GetMD5Code(wait_str);
				//DES加密字符串
				String des_str = commonMd5.enCodeStringByKey(userinfo.getPhone() + "," + md5_str, "loginkey");
				
				ControlUtils.modifyCookie(response, 1, "userinfo", des_str);
			}
			
			//用户登陆 loginUser
			int flag = userinfoService.loginUser(userinfo,session);
			if(flag==1){//登陆成功
				resultMap.put("count", 1);
			}else if(flag==-1){
				resultMap.put("error", true);
				resultMap.put("message", "用户名或密码错误！");
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/callback",method=RequestMethod.GET)
	public void   callBack(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String authedtoken = request.getParameter("authedtoken");
		String tmptoken = request.getParameter("tmptoken");
		String requestUrl="";
		Map<String,String> paramsMap;
		String result="";
		try {
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			requestUrl = "http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("CheckAuthedToken"));
			paramsMap = new HashMap<String, String>();
			paramsMap.put("authedtoken", authedtoken);
			paramsMap.put("tmptoken", tmptoken);
			result = ConnectionUtil.postRequest(requestUrl, paramsMap);
			Map<String,Object> resMap  = (Map<String,Object>)JSONUtils.parse(result);
			if((Boolean)resMap.get("ValidationState"))
			{
				String sso_id = resMap.get("SystemUserId").toString();
				UserInfo uinfo = userinfoService.queryUserBySsoId(sso_id.toLowerCase());
				if(uinfo!=null){
					session.setAttribute("loginUser", uinfo);
					//登陆成功，重定向
					String url=(String) session.getAttribute("url");
					if(url!=null)
					url=url.substring(19);
					else
						url="";
					if(url.contains("out.html")||url.contains("out.json"))
						url="";
					response.sendRedirect("/"+url);
				}else{					
					UUID uuid = UUID.randomUUID();
					session.setAttribute("tmpToken", uuid.toString());
					String returnUrl = map.get("returnUrl").toString();
				    requestUrl ="http://" + map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
				    requestUrl += "?tmpToken="+ uuid.toString();
				    requestUrl += "&returnUrl=http://"+ returnUrl;
				    requestUrl += "&logout=1";
				    PrintWriter pw = response.getWriter();
				    pw.println("<!DOCTYPE html>");
				    pw.println("<html>");
				    pw.println("<head>");
				    pw.println("<title>页面跳转中...</title>");
				    pw.println("<meta charset=\"UTF-8\">");
				    pw.println("</head>");
				    pw.println("<body>");
				    pw.println("<script src=\"../js/jquery.min.js\"></script>");
				    pw.println("<script src=\"../js/layer/layer.js\"></script>");
				    pw.println("<script>");
				    pw.println("layer.msg('您的账号尚未在图灵云注册,即将跳转...',{time: 1000,shade:[1,'#09c']},function(){window.location.href='"+requestUrl+"'})");
				    pw.println("</script>");
				    pw.println("</body>");
				    pw.println("</html>");
					pw.flush();
					pw.close();
				}
			
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	@RequestMapping(value="/out",method = RequestMethod.GET)
	public String loginOut(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		String requestUrl="";
		if(session.getAttribute("loginUser")!=null){
			session.removeAttribute("loginUser");
			ControlUtils.modifyCookie(response, 0, "userinfo", "");
		}
		ModelMap mmap = new ModelMap();
		try {		
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			System.out.println(map);
			UUID uuid = UUID.randomUUID();
			session.setAttribute("tmpToken", uuid.toString());
			String returnUrl = map.get("returnUrl").toString();
		    requestUrl = map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
		    requestUrl += "?tmpToken="+ uuid.toString();
		    requestUrl += "&returnUrl=http://"+ returnUrl;
		    requestUrl += "&logout=1";
		    System.out.println(requestUrl);
		    ConnectionUtil.postRequest("http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("checkAliveInterface")), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}	
		return "redirect://"+requestUrl;
	}
	
}