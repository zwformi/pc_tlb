package com.yunrer.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.OrderDao;
import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ServiceDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.OrderSm;
import com.yunrer.entity.Product;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserService;

@Service("FindService")
@Transactional
public class FindService {
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private ServiceDao serviceDao;
	
	@Resource
	private ProductDao productDao;
	
	/**
	 * 到达我要查主页
	 * @param request
	 */
	public String toFind_main(HttpServletRequest request,HttpSession session){
		//查询热卖推荐 -2条
		List<Product> productRedList =  productDao.queryProductRedList(2);
		request.setAttribute("PRODUCTREDLIST",productRedList);
		//统计相关信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		int owning_company = (userinfo.getOwning_company()==null|| userinfo.getOwning_company()==0)?-1:userinfo.getOwning_company();
		Map map = orderDao.queryOrderCoutn_tj(userinfo.getUser_id(),owning_company);
		request.setAttribute("XGTJ",map);
		
		return "find_main";
	}
	
	/**
	 * 到达需求单列表页
	 * @param request
	 */
	public String queryOrder_Xuqiu(HttpServletRequest request,HttpSession session){
		//登陆用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		List<OrderInfoXuqiu> xuqiuList=null;
		String order_state_str = request.getParameter("order_state");
		if(order_state_str!=null&&!order_state_str.equals("")){
			int order_state = Integer.parseInt(order_state_str);
			//根据状态查询
			xuqiuList = orderDao.queryOrder_XuqiuBystate(userinfo.getUser_id(),order_state);
		}else{
			//查询全部
			xuqiuList = orderDao.queryOrder_Xuqiu(userinfo.getUser_id());
		}
		//传输到前台-订单金额
		request.setAttribute("XUQIULIST", xuqiuList);
		//订单确认页
		return "find_demand";
	}
	
	/**
	 * 到达需求单详情页
	 * @param request
	 */
	public String queryOrder_Xuqiu_Detail(HttpServletRequest request,HttpSession session){
		String order_number = request.getParameter("order_number");
		//需求详情
		OrderInfoXuqiu xuqiu = orderDao.queryOrder_Xuqiu_Detail(order_number);
		
		List<OrderDetailsXuqiu> OrderDetailsXuqiuList = orderDao.queryOrderDetail_Xuqiu(order_number);
		//传输到前台-需求详情
		request.setAttribute("XUQIU", xuqiu);
		//传输到前台-需求明细
		request.setAttribute("ORDERDETAILSXUQIULIST", OrderDetailsXuqiuList);
		//订单确认页
		return "find_edit_demand";
	}
	
	/**
	 * 到达服务单列表页
	 * @param request
	 */
	public String queryFind_service(HttpServletRequest request,HttpSession session){
		//登陆用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		List<UserService> serviceList = serviceDao.queryService(userinfo.getUser_id());
		//传输到前台-订单金额
		request.setAttribute("SERVICELIST", serviceList);
		//订单确认页
		return "find_service";
	}
	
	/**
	 * 到达服务单详情页
	 * @param request
	 */
	public String queryFind_service_Detail(HttpServletRequest request,HttpSession session){
		String service_number = request.getParameter("service_number");//服务单号
		//需求详情
		UserService userservice = serviceDao.queryServiceDetail(service_number);
		//关联的商品信息
		OrderDetails orderDetail = orderDao.queryOrderDetailDetail(userservice.getOrder_number(), userservice.getOrder_detail_id());
		//传输到前台-需求详情
		request.setAttribute("USERSERVICE", userservice);
		//传输到前台-关联的商品信息
		request.setAttribute("ORDERDETAIL", orderDetail);
		//订单确认页
		return "find_edit_service";
	}
	
	/**
	 * 查询订单合同
	 * @param request
	 */
	public String queryOrderList(HttpServletRequest request,HttpSession session){
		//登陆用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		//获得商品详细信息
		List<OrderInfo> orderInfoList = orderDao.queryOrder_List(userinfo.getUser_id());//查询订单合同列表
		
		String order_state_str = request.getParameter("order_state");
		if(order_state_str!=null&&!order_state_str.equals("")){
			int order_state = Integer.parseInt(order_state_str);
			//根据状态查询
			orderInfoList = orderDao.queryOrder_Liststate(userinfo.getUser_id(),order_state);//查询订单合同列表
		}else{
			//查询全部
			orderInfoList = orderDao.queryOrder_List(userinfo.getUser_id());//查询订单合同列表
		}
		
		request.setAttribute("ORDERINFOLIST", orderInfoList);
		return "find_contract";
	}
	
	/**
	 * 到达订单合同详情页
	 * @param request
	 */
	public String queryOrder_Detail(HttpServletRequest request,HttpSession session){
		String order_number = request.getParameter("order_number");
		//需求详情
		OrderInfo order = orderDao.queryOrder_Detail(order_number);
		//查询合同进度信息
		
//		List<OrderSm> orderSmList = orderDao.queryOrderSm(order.getId());
//		OrderSm os = new OrderSm();
//		
//		if(order.getOrder_state() > 0){
//			os.setAdd_time(order.getOrder_time());
//			os.setText_sm("合同产生，并开始配货");
//			os.setType("开始");
//		}else{
//			os.setAdd_time(order.getOrder_time());
//			os.setText_sm("订单已提交，等待支付");
//			os.setType("开始");
//		}
//		orderSmList.add(os);
//		//查询合同商品清单信息
		List<OrderDetails> OrderDetailsList = orderDao.queryOrderDetailList(order_number);
		
		//传输到前台-订单合同详情
		request.setAttribute("ORDER", order);
//		//传输到前台-订单进度信息
//		request.setAttribute("ORDERSMLIST", orderSmList);
		//传输到前台-订单合同明细
		request.setAttribute("ORDERDETAILSLIST", OrderDetailsList);
		//订单确认页
		return "find_contract_show";
	}
	
	/**
	 * 获取合同数量--分页
	 * @param session
	 * @return
	 */
	public int getOrderCount(HttpServletRequest request,HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		try{
		if(user!=null){
			String type= request.getParameter("type");
			int order_state= request.getParameter("order_state")==null?-1:Integer.parseInt(request.getParameter("order_state"));
			int owning_company = (user.getOwning_company()==null||user.getOwning_company()==0)?-1:user.getOwning_company();
			if(type.equals("order"))
				return orderDao.queryPagingOrderCount(user.getUser_id(),owning_company,order_state);
			else if(type.equals("xuqiu"))
				return orderDao.queryPagingOrderXuqiuCount(user.getUser_id(),owning_company,order_state);
			else if(type.equals("service"))
				return serviceDao.getServiceCount(user.getUser_id(),owning_company);
			else if(type.equals("afterservice"))
				return orderDao.queryPaidOrderCount(user.getUser_id(),owning_company);
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return 0;
		
		
	}

	/**
	 * 获取需求/合同列表--分页
	 * @param request
	 * @param session
	 * @return
	 */
	public List<?> queryOrderListByPaging(HttpServletRequest request , HttpSession session){
		List<?> list = null;
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		try{
		if(user!=null){
			String type = request.getParameter("type");
			int size = request.getParameter("size")==null?10:Integer.parseInt(request.getParameter("size"));
			int page = request.getParameter("page")==null?0:Integer.parseInt(request.getParameter("page"));
			int order_state= request.getParameter("order_state")==null?-1:Integer.parseInt(request.getParameter("order_state"));
			int owning_company = (user.getOwning_company()==null||user.getOwning_company()==0)?-1:user.getOwning_company();
			if(type.equals("order"))
				list = orderDao.queryPagingOrderList(user.getUser_id(), owning_company,size, page,order_state);
				else if(type.equals("xuqiu"))
				list = orderDao.queryPagingOrderXuqiuList(user.getUser_id(),owning_company, size, page,order_state);
				else if(type.equals("service"))
				list = serviceDao.queryPagingServiceList(user.getUser_id(),owning_company,size, page);
				else if(type.equals("afterservice"))
					list = orderDao.queryPaidOrderList(user.getUser_id(), owning_company, size, page);
		}
		}catch(Exception e){
			e.getStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取某个订单的商品明细
	 * @param request
	 * @param session
	 * @return
	 */
	public List<?> queryOrderDetailListByPaging(HttpServletRequest request){
		List<?> list = null;
		String type = request.getParameter("type");
		String order_number = request.getParameter("order_number");
		if(order_number!=null && !order_number.equals("")){
		if(type.equals("order"))
			list = orderDao.queryOrderDetailList(order_number);
		else if(type.equals("xuqiu"))
			list = orderDao.queryOrderDetail_Xuqiu(order_number);
			
		}
		return list;
		
	}
}