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

import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.UserCompany;
import com.yunrer.service.FindService;
import com.yunrer.service.OrderService;
import com.yunrer.service.PostService;
import com.yunrer.service.UserCompanyService;

/**
 * 我要查处理逻辑
 * @author wangpeng
 */
@Controller
@RequestMapping("/find")
public class FindControl {
	@Resource
	private FindService findService;
	@Resource 
	private OrderService service;
	@Resource
	private PostService postService;
	@Resource
	private UserCompanyService userCompanyService;
	
	/**
	 * 到达我要查 - 首页
	 */
	@RequestMapping(value = "/find_main",method = RequestMethod.GET)
	public String toFind_main(HttpServletRequest request,HttpSession session) {
		try{
			return findService.toFind_main(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【我要查】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 到达我要查 - 需求单页
	 */
	@RequestMapping(value = "/find_demand",method = RequestMethod.GET)
	public String toFind_demand(HttpServletRequest request,HttpSession session) {
//		try{
//			return findService.queryOrder_Xuqiu(request, session);
//		}catch(Exception ex){
//			request.setAttribute("error", true);
//			request.setAttribute("message", "【我要查-需求单】出现系统错误！");
//			return "error";
//		}
		return "find_demand_page";
	}
	
	/**
	 * 到达我要查 - 需求单-详情页
	 */
	@RequestMapping(value = "/find_edit_demand",method = RequestMethod.GET)
	public String toFind_edit_demand(HttpServletRequest request,HttpSession session) {
		try{
			return findService.queryOrder_Xuqiu_Detail(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【我要查-需求单】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 到达我要查 - 一键需求发布页
	 */
	@RequestMapping(value = "/one_demand",method = RequestMethod.GET)
	public String toone_demand(HttpServletRequest request,HttpSession session) {
		try{
			return "one_demand";
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【一键需求】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 到达我要查 - 服务单页
	 */
	@RequestMapping(value = "/find_service",method = RequestMethod.GET)
	public String toFind_service(HttpServletRequest request,HttpSession session) {
//		try{
//			return findService.queryFind_service(request, session);
//		}catch(Exception ex){
//			request.setAttribute("error", true);
//			request.setAttribute("message", "【我要查-服务单】出现系统错误！");
//			return "error";
//		}
		return "find_service_page";
	}
	
	/**
	 * 到达我要查 - 服务单-详情页
	 */
	@RequestMapping(value = "/find_edit_service",method = RequestMethod.GET)
	public String toFind_edit_service(HttpServletRequest request,HttpSession session) {
		try{
			return findService.queryFind_service_Detail(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【我要查-服务单】出现系统错误！");
			return "error";
		}
	}
	
	/**
	 * 到达我要查 - 订单合同页
	 */
	@RequestMapping(value = "/find_contract",method = RequestMethod.GET)
	public String toFind_contract(HttpServletRequest request,HttpSession session) {
//		try{
//			return findService.queryOrderList(request, session);
//		}catch(Exception ex){
//			request.setAttribute("error", true);
//			request.setAttribute("message", "【我要查-订单合同】出现系统错误！");
//			return "error";
//		}
		return "find_contract_page";
	}
	
	/**
	 * 到达我要查 - 订单合同 - 商品清单详情页
	 */
	@RequestMapping(value = "/find_contract_show",method = RequestMethod.GET)
	public String toOrderProduct(HttpServletRequest request,HttpSession session) {
		try{
			String order_number= request.getParameter("order_number");
			List<?> odsm = postService.getPostData(order_number);
			//传输到前台-订单进度信息
			request.setAttribute("ORDERSMLIST", odsm);
			return findService.queryOrder_Detail(request, session);
		}catch(Exception ex){
			request.setAttribute("error", true);
			request.setAttribute("message", "【我要查-订单合同】出现系统错误！");
			return "error";
		}
	}
	/**
	 * 获得订单数量（分页）
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/getcount",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCount(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		int num =findService.getOrderCount(request,session);
		if(num>0){
			map.put("resMsg", "success");
			map.put("count", num);
		}else
		{
			map.put("resMsg", "fail");
			map.put("message", "获取数据为空");
		}
		}catch(Exception e){
			map.put("resMsg", "error");
			map.put("message", "数据获取异常");
			
		}finally{
			
			return map;
		}
	}

	/**
	 * 
	 * 获得各订单列表（分页--外部）
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/paginglist",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getOrderPagingList(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		List<?> list = findService.queryOrderListByPaging(request, session);
		if(list!=null&&list.size()>0){
			map.put("resMsg", "success");
			map.put("list", list);
		}else
		{
			map.put("resMsg", "fail");
			map.put("message", "获取数据为空");
		}
		}catch(Exception e){
			map.put("resMsg", "error");
			map.put("message", "数据获取异常");
			
		}finally{
			
			return map;
		}

	}
	/**
	 * 根据订单/需求单/服务单id获取相应的详情信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/orderdetail",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getDetailPagingList(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		List<?> list = findService.queryOrderDetailListByPaging(request);
		if(list!=null&&list.size()>0){
			map.put("resMsg", "success");
			map.put("list", list);
		}else
		{
			map.put("resMsg", "fail");
			map.put("message", "获取数据为空");
		}
		}catch(Exception e){
			map.put("resMsg", "error");
			map.put("message", "数据获取异常");
			
		}finally{
			
			return map;
		}
	}
	
	/**
	 * 根据公司名称返回图灵云主机url
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/companyurl",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCompanyUrl(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		UserCompany usercompany = null;
		try{
			String gsmc = request.getParameter("gsmc");
			if(gsmc!=null && !gsmc.equals(""))
				usercompany = userCompanyService.getCompanyByName(gsmc);	

		}catch(Exception e){
			e.getStackTrace();
		}
		if(usercompany!=null){
			resultMap.put("resultCode",1);
			resultMap.put("resultMsg","获取成功");
			resultMap.put("gsmc", usercompany.getGsmc());
			resultMap.put("requestUrl", usercompany.getRequest_url());
		}else{
			resultMap.put("resultCode",0);
			resultMap.put("resultMsg","获取失败");
		}
		return resultMap;
			
		
	}
}