package com.yunrer.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.ShoopingCartService;

/**
 * 订单处理逻辑
 * @author wangpeng
 */
@Controller
@RequestMapping("/cart")
public class CartControl {
	@Resource
	private ShoopingCartService shoopingCartService;
	
	/**
	 * 到达购物车页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toShoopingCartr(HttpServletRequest request,HttpSession session) {
		return shoopingCartService.toCart(request, session);
	}
	
	/**
	 * 添加商品到购物车-ajax
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addShoopingCart(HttpServletRequest request,HttpSession session) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return shoopingCartService.addCart(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "加入购物车或更新数量出现错误！");
		}
		return resultMap;
	}
	
	/**
	 * 修改商品数量-ajax
	 */
	@RequestMapping(value="/modifycart",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> modifyShoopingCart(HttpServletRequest request,HttpSession session) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return shoopingCartService.modifyCartCount(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "加入购物车或更新数量出现错误！");
		}
		return resultMap;
	}
	
	/**
	 * 验证购物车商品信息  -是否失效-库存-价格  -ajax
	 */
	@RequestMapping(value = "/validate",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> validateShoopingCartProduct(HttpServletRequest request,HttpSession session) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return shoopingCartService.validateProductList(request,session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "购物车出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 将商品从购物车移除-ajax
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delShoopingCart(HttpServletRequest request,HttpSession session) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return shoopingCartService.delCart(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误！");
		}
		return resultMap;
	}
	

}