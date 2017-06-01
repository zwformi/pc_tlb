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

import com.yunrer.service.OrderService;

/**
 * 订单和需求处理逻辑
 * @author wangpeng
 */
@Controller
@RequestMapping("/order")
public class OrderControl {
	@Resource
	private OrderService orderService;
	
	/**
	 * 到达订单确认页 -秒杀
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toProductOrder_now(HttpServletRequest request,HttpSession session) {
		try{
			return orderService.toOrder_now(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【订单】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 到达需求确认页-从购物车点击
	 */
	@RequestMapping(value = "/gwcdd",method = RequestMethod.POST)
	public String toProductOrder(HttpServletRequest request,HttpSession session) {
		try{
			return orderService.toOrder_gwc(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【需求】出现系统错误！");
			return "error";
		}
	}
	/**
	 * 生成合同单--立即支付
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="onlinepay",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> createOrder(HttpServletRequest request, HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		int resource = Integer.parseInt(request.getParameter("resource"));
			if(resource==1)//秒杀-立即抢购
				map =  orderService.addOrder_now(request, session);	
			else if(resource==2||resource==4)//购物车-提交需求
				map =  orderService.createOrderfromCart(request, session);	
			else{
				map.put("res_code", 2);
				map.put("message", "【需求】非法的参数！");
				return map;
			}
		}catch(Exception e){
			
			System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.err.println(e.getMessage());
			System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}finally{
			
			return map;
		}
	}
	
	/**
	 * 下订单或需求
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doProductOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int resource = Integer.parseInt(request.getParameter("resource"));
			if(resource==1){//秒杀-立即抢购
				return orderService.addOrder_now(request, session);
			}else if(resource==2||resource==4){//购物车-提交需求
				return orderService.addOrder_gwc(request, session);
			}else{
				resultMap.put("error", true);
				resultMap.put("message", "【需求】非法的参数！");
			}
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【需求】非法的参数！");
		}
		return resultMap;
	}
	
	
	/**
	 * 一键需求
	 */
	@RequestMapping(value = "/one",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doOneOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return orderService.addOrder_one(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【一键需求】非法的参数！");
		}
		return resultMap;
	}
	
	/**
	 * 需求单-接受报价
	 */
	@ResponseBody
	@RequestMapping(value = "/jsbj",method = RequestMethod.POST)
	public Map<String,Object> doJsbj(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.jsbjOrderXuqiu(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作需求单接受报价失败");
		}
		return resultMap;
	}
	
	/**
	 * 需求单-关闭
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelxuqiu",method = RequestMethod.POST)
	public Map<String,Object> doSite(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.cancelOrderXuqiu(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作关闭需求单失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--签收
	 */
	@ResponseBody
	@RequestMapping(value = "/qs",method = RequestMethod.POST)
	public Map<String,Object> doqsOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.qsOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作签收失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--取消订单
	 */
	@ResponseBody
	@RequestMapping(value = "/qxdd",method = RequestMethod.POST)
	public Map<String,Object> doqxOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.qxOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作取消失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--签收后实施
	 */
	@ResponseBody
	@RequestMapping(value = "/qsss",method = RequestMethod.POST)
	public Map<String,Object> doqsssOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.qsssOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作签收后并发起实施服务失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--客户确认实施完成
	 */
	@ResponseBody
	@RequestMapping(value = "/qrsswc",method = RequestMethod.POST)
	public Map<String,Object> doqrsswcOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.qrsswcOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作实施完成失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--评价
	 */
	@ResponseBody
	@RequestMapping(value = "/pj",method = RequestMethod.POST)
	public Map<String,Object> dopjOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.pjOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "评价失败");
		}
		return resultMap;
	}
	

	/**
	 * 订单合同--开通
	 */
	@ResponseBody
	@RequestMapping(value = "/kt",method = RequestMethod.POST)
	public Map<String,Object> doktOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.ktOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作开通失败");
		}
		return resultMap;
	}
	
	
}