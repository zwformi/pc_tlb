package com.yunrer.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.ArticleService;
import com.yunrer.service.OrderService;
import com.yunrer.service.UserServiceService;

/**
 * 贴心服务处理逻辑
 * @author wangpeng
 */
@Controller
@RequestMapping("/service")
public class UserServiceControl {
	@Resource
	private UserServiceService userServiceService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private ArticleService articleService;
	/**
	 * 到达售后服务页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toService(HttpServletRequest request,HttpSession session) {
//		try{
//			return orderService.queryOrderList(request, session);
//		}catch(Exception ex){
//			System.out.println(ex.getMessage());
//			request.setAttribute("error", true);
//			request.setAttribute("message", "【售后服务】出现系统错误！");
//			return "error";
//		}
		return "service_order_list_page";
	}
	
	
	/**
	 * 从售后到达商品清单页-------------改方法作废
	 */
	@RequestMapping(value = "toorderproduct",method = RequestMethod.GET)
	public String toOrderProduct(HttpServletRequest request,HttpSession session) {
		try{
			return userServiceService.queryOrder_Detail(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【售后服务】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 获取订单的商品清单商品清单页-ajax
	 */
	@RequestMapping(value = "getorderproduct",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getOrderProduct(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return userServiceService.queryOrder_Product(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【售后服务】获取商品清单失败！");
		}
		return resultMap;
	}
	
	/**
	 * 到达售后服务添加页页面
	 */
	@RequestMapping(value="toadd",method = RequestMethod.GET)
	public String toAddService(HttpServletRequest request,HttpSession session) {
		try{
			return userServiceService.toAddService(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【售后服务】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 查询免费服务信息单页信息
	 */
	@RequestMapping(value = "/mffw",method = RequestMethod.GET)
	public String toMffw(HttpServletRequest request) {
		return articleService.toArticleMffw(request);
	}
	
	/**
	 * 新增服务单-ajax
	 */
	@RequestMapping(value = "addservice",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addService(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = userServiceService.addService(request,session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "新增服务单出现错误！");
		}
		return resultMap;
	}
	
	/**
	 * 到达维修站页面
	 */
	@RequestMapping(value = "tostation",method = RequestMethod.GET)
	public String toStation(HttpServletRequest request,HttpSession session) {
		return "service_find";
	}
	
	/**
	 * 加载维修站列表-ajax
	 */
	@RequestMapping(value = "loadstation",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loadStation(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = userServiceService.queryMaintenanceStationList(request);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "加载维修站出现错误！");
		}
		return resultMap;
	}
	
}