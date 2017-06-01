package com.yunrer.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.yunrer.entity.UserInfo;
import com.yunrer.service.OfficeHelpService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 下载功能
 * @author rujun
 */
@Controller
@RequestMapping("/download")
public class DownloadControl implements ServletContextAware{
	
	@Resource 
	private OfficeHelpService officeService;
	
	@RequestMapping(value = "/contract",method = RequestMethod.POST)
	public void downloadContract(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		String template_name="";	//模板名
		String path = request.getContextPath();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		path = servletContext.getRealPath("/");  
		try{

			//从request中获取下载类型
			String type=request.getParameter("type")==null?"":request.getParameter("type");
			String order_number = request.getParameter("order_number");
			UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
	
			//判断type的类型
			if("order".equals(type)){
				template_name="contractTemplate.ftl"; 
				
						
			}
			response.setCharacterEncoding("UTF-8");
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
	        response.setContentType("multipart/form-data");
	        //2.设置文件头：最后一个参数是设置下载文件名
	        response.setHeader("Content-disposition", "attachment;fileName=\""+URLEncoder.encode("合同_"+order_number+".doc", "UTF-8") + "\"");
	        
	        
	        try {

	            //3.通过response获取ServletOutputStream对象(out)
	           PrintWriter  out = response.getWriter();
	           officeService.editContract(order_number,template_name,path,userinfo,out);
	        
	            if (out != null) {
	                out.flush();
	                out.close();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
	
	}
	private ServletContext servletContext;  

	  
	    public void setServletContext(ServletContext servletContext) {
	        this.servletContext = servletContext;
	    }
	}

