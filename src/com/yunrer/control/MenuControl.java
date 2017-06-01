package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.Ad;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.UserClient;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.ShoopingCartService;
import com.yunrer.service.UserMessageService;
import com.yunrer.dao.ProductTypeDao;

/**
 * 跳转到登陆页
 * @author yangq
 */

@Controller
public class MenuControl {
	@Resource
	private ProductTypeDao productTypemDao;
	private UserMessageService userMessageService;
	private ShoopingCartService shoopingCartService;
	
	@RequestMapping(value = "/menu",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> toMenu() {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{			
			List<ProductType> productTypeList = productTypemDao.queryProductTypeList();
			resultMap.put("PRODUCTTYPELIST",productTypeList);			
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "获取商品类别失败");
		}
		return resultMap;
	}
	

	@RequestMapping(value = "/special_menu",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> toSpecialMenu(HttpSession session) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{			
			if(session.getAttribute("loginUser")!=null){
				UserInfo user = (UserInfo)session.getAttribute("loginUser");				
				List<ProductType> specialTypeList = productTypemDao.querySpecialProductTypeList(user.getOwning_company());
				resultMap.put("specialTypeList",specialTypeList);			
				
			}else{
				resultMap.put("error", true);
				resultMap.put("message", "尚未登陆，无法获取");
			}
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "获取商品类别失败");
		}
		return resultMap;
	}

}

