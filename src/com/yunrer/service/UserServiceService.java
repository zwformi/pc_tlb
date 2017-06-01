package com.yunrer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yunrer.common.DateUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.MaintenanceStationDao;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.ServiceDao;
import com.yunrer.entity.MaintenanceStation;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserService;

@Service("UserServiceService")
public class UserServiceService {
	
	@Resource
	private MaintenanceStationDao maintenanceStationDao;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private ServiceDao serviceDao;

	/**
	 * 查询维修站列表
	 */
	public Map<String,Object> queryMaintenanceStationList(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String keyword = request.getParameter("keyword");//查询条件
		List<MaintenanceStation> maintenanceStationList =  maintenanceStationDao.queryMaintenanceStationList(keyword);
		resultMap.put("STATIONLIST",maintenanceStationList);
		return resultMap;
	}
	
	/**
	 * 到达订单合同详情页
	 * @param request
	 */
	public String queryOrder_Detail(HttpServletRequest request,HttpSession session){
		String order_number = request.getParameter("order_number");
		//需求详情
		OrderInfo order = orderDao.queryOrder_Detail(order_number);
		
		List<OrderDetails> OrderDetailsList = orderDao.queryOrderDetailList(order_number);
		//传输到前台-订单合同详情
		request.setAttribute("ORDER", order);
		//传输到前台-订单合同明细
		request.setAttribute("ORDERDETAILSLIST", OrderDetailsList);
		//订单确认页
		return "service_order_product";
	}
	
	/**
	 * 获取订单商品清单
	 * @param request
	 */
	public Map<String,Object> queryOrder_Product(HttpServletRequest request,HttpSession session){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String order_number = request.getParameter("order_number");
		List<OrderDetails> OrderDetailsList = orderDao.queryOrderDetailList(order_number);
		//传输到前台-订单合同明细
		resultMap.put("ORDERDETAILSLIST", OrderDetailsList);
		return resultMap;
	}
	
	/**
	 * 到达售后服务添加页页面
	 * @param request
	 */
	public String toAddService(HttpServletRequest request,HttpSession session){
		int order_detail_id =  Integer.parseInt(request.getParameter("order_detail_id"));
		String order_number = request.getParameter("order_number");
		
		OrderDetails orderDetail = orderDao.queryOrderDetailDetail(order_number, order_detail_id);
		request.setAttribute("order_number", order_number);
		request.setAttribute("order_detail_id", order_detail_id);
		request.setAttribute("ORDERDETAIL", orderDetail);
		return "add_service";
	}
	
	/**
	 * 新增服务单
	 */
	public Map<String,Object> addService(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
			/**********************新-服务单号 start***************************/
			UserService uservice = serviceDao.queryLastService();
			String num = "";
			if(uservice!=null && uservice.getService_number().length()>=13 &&  uservice.getService_number().length()<26){
				num = uservice.getService_number().substring(10);
				int number = Integer.parseInt(num)+1;
				if(number<10)
					num = "00"+number;
				else if(number<100)
					num = "0"+ number;
				else num=number+"";
			}else
				num="001";
				System.out.println(num);
			String service_number = "F_"+DateUtils.GetTime_YMDHmsS().substring(0, 8)+num;
			/**********************新-服务单号 end***************************/
			UserService service_ = new UserService();
			service_.setAdd_time(new Date());
			service_.setContent(request.getParameter("content"));
			service_.setLxr(request.getParameter("lxr"));
			int order_detail_id = 0;
			String order_detail_id_str = request.getParameter("order_detail_id");
			if(null!=order_detail_id_str&&!"".equals(order_detail_id_str)){
				order_detail_id = Integer.parseInt(order_detail_id_str);
			}
			String order_number_str = "".equals(request.getParameter("order_number"))?"无关联订单":request.getParameter("order_number");
			int owning_company = loginUser.getOwning_company()==null?0:loginUser.getOwning_company();
			service_.setOrder_detail_id(order_detail_id);
			service_.setOrder_number(order_number_str);
			service_.setPhone(request.getParameter("phone"));
			service_.setAddress(request.getParameter("address"));
			service_.setService_number(service_number);
			service_.setUser_id(loginUser.getUser_id());
			service_.setOwning_company(owning_company);
			serviceDao.addService(service_);
			resultMap.put("success", "true");
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【新增服务单】出现错误！");
		}
		return resultMap;
	}
}
