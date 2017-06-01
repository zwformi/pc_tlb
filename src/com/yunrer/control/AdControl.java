package com.yunrer.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunrer.entity.Ad;
import com.yunrer.service.AdService;

/**
 * 广告图片管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/ad")
@SuppressWarnings("unchecked")
public class AdControl {

	@Resource
	private AdService adService;
	
	/**
	 * 查询首页banner大图
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/banner", method = RequestMethod.GET)
	public String queryAdList(HttpServletRequest request) {
		try{
			
			int typeid = request.getParameter("typeid")==null?1:Integer.parseInt(request.getParameter("typeid"));
			int count = request.getParameter("count")==null?10:Integer.parseInt(request.getParameter("count"));
			List<Ad> list = adService.queryAdList(typeid,count);
			request.setAttribute("BANNERS", list);
		}
		catch(Exception e){
			System.err.println("---------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------");
			
		}finally{
			return "banner";	
		}
		
		
	}
	
}