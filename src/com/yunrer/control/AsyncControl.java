package com.yunrer.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.ConvertUtil;
import com.yunrer.util.XMLUtils;
import com.yunrer.util.XmlUtil;
import com.yunrer.util.SecurityUtil;

@Controller
@RequestMapping("async")
public class AsyncControl {

	@RequestMapping(method=RequestMethod.POST)
	public void dopost(HttpServletRequest request){
		try {
    	Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
    	String path = map.get("asyncPath").toString();
    	String port = map.get("asyncPort").toString();
    	String Interface = request.getParameter("name").equals("TLM_Account_Changed".toUpperCase())?
    			map.get("AccountChangedInterface").toString():map.get("ContactChangedInterface").toString();
    			
    	String app=map.get("app").toString();
    	String app_secret=map.get("app_secret").toString();
    	
    	String requestUrl = "http://"+path+":"+port+"/"+Interface;
    	String content=request.getParameter("content");
    	content=ConvertUtil.Ascii2Native(request.getParameter("content"));
    	Map<String,String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("name", request.getParameter("name"));
    	paramsMap.put("content", content);
    	paramsMap.put("signature", SecurityUtil.SHA1(content+app_secret));
    	paramsMap.put("app",app);
        String result = ConnectionUtil.postRequest(requestUrl, paramsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
