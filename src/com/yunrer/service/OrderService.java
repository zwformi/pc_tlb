package com.yunrer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.common.DateUtils;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.OrderXuqiuDao;
import com.yunrer.dao.ProductBrandDao;
import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ProductTypeDao;
import com.yunrer.dao.ServiceDao;
import com.yunrer.dao.ShoppingCartDao;
import com.yunrer.dao.UserAddressDao;
import com.yunrer.dao.UserInvoicesDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderEvaluate;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.OrderSm;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.UserAddress;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserInvoices;
import com.yunrer.entity.UserService;

@Service("OrderService")
@Transactional
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private ProductDao productDao;

	@Resource
	private ShoppingCartDao shoppingCartDao;

	@Resource
	private UserAddressDao userAddressDao;

	@Resource
	private UserInvoicesDao userInvoicesDao;

	@Resource
	private ProductBrandDao productBrandDao;

	@Resource
	private ProductTypeDao productTypeDao;

	@Resource
	private OrderXuqiuDao orderXuqiuDao;

	@Resource
	private ServiceDao serviceDao;

	/**
	 * 将list转换成需要的map 商品类型专用
	 * 
	 * @param typeList
	 * @return
	 */
	public static Map<String, String> doList2Map_type(List<ProductType> typeList) {
		Map<String, String> map = new HashMap<String, String>();
		ProductType type = null;
		for (int i = 0; i < typeList.size(); i++) {
			type = typeList.get(i);
			map.put("," + type.getId() + ",", type.getName());
		}
		return map;
	}

	/**
	 * 将list转换成需要的map 商品品牌专用
	 * 
	 * @param typeList
	 * @return
	 */
	public static Map<Integer, String> doList2Map_brand(
			List<ProductBrand> brandList) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		ProductBrand brand = null;
		for (int i = 0; i < brandList.size(); i++) {
			brand = brandList.get(i);
			map.put(brand.getId(), brand.getName());
		}
		return map;
	}

	/**
	 * 到达订单确认页-秒杀
	 * 
	 * @param request
	 */
	public String toOrder_now(HttpServletRequest request, HttpSession session) {
		int product_id = 0;
		int product_count = 1;// 订购数量,秒杀和立即购买 默认为1
		int isms = 0;
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		try {
			product_id = Integer.parseInt(request.getParameter("product_id"));// 商品ID
			isms = Integer.parseInt(request.getParameter("isms"));// 是否秒杀
		} catch (Exception ex) {
			request.setAttribute("error", true);
			request.setAttribute("message", "【订单】非法的参数！");
			return "error";
		}
		// 获得商品详细信息
		Product product = productDao.queryProduct(product_id);
		if (product == null) {
			request.setAttribute("error", true);
			request.setAttribute("message", "商品不存在或已下架！");
			return "error";
		}
		if (isms != product.getIs_ms()) {// 判断商品参数是否合法
			request.setAttribute("error", true);
			request.setAttribute("message", "【订单】您对商品参数进行<br/>了非法篡改，请立即停止！");
			return "error";
		}
		// 秒杀商品
		if (product.getIs_ms() == 1) {
			if (!product.getFlag_begin()) {// 判断秒杀是否已开始
				// 秒杀还未开始
				request.setAttribute("error", true);
				request.setAttribute("message", "【秒杀未开始】，<br/>请勿非法操作！");
				return "error";
			}
		}
		int product_out_count = productDao.queryProductCount(product_id);// 查询产品的销出数量
		product.setOut_count(product_out_count);
		if (product.getOverplus() < product_count) {// 库存不足
			// 返回库存不足页面
			request.setAttribute("error", true);
			if (product.getIs_ms() == 1) {
				request.setAttribute("message", "已经被抢光了~~！");
			} else {
				request.setAttribute("message", "【库存不足】无法下单！");
			}
			return "error";
		}

		// 封装订单详情
		List<OrderDetailsXuqiu> od_list = new ArrayList<OrderDetailsXuqiu>();
		OrderDetailsXuqiu orderDetails = new OrderDetailsXuqiu();
		orderDetails.setProduct_id(product_id);
		orderDetails.setProduct_imgurl(product.getImg_url());
		orderDetails.setProduct_name(product.getName());
		orderDetails.setProduct_count(product_count);
		orderDetails.setProduct_unit_price(product.getPrice_new());
		od_list.add(orderDetails);

		List<UserAddress> userAddressList = userAddressDao
				.queryAddressList(userinfo.getUser_id());
		UserInvoices userInvoices = userInvoicesDao.queryInvoices(userinfo
				.getUser_id());
		// 传输到前台-收货地址
		request.setAttribute("USERADDRESSLIST", userAddressList);
		// 传输到前台-发票信息
		request.setAttribute("USERINVOICES", userInvoices);
		// 传输到前台-订单类型
		request.setAttribute("resource", 1);
		// 传输到前台-订单详情
		request.setAttribute("od_list", od_list);
		// 传输到前台-订单商品数量
		request.setAttribute("total_count", 1);
		// 传输到前台-订单金额
		request.setAttribute("total_payment_money", product.getPrice_new());
		//更新库存信息
		/*productDao.updateSingleProductStock("-",product_id,0);*/
		// 订单确认页
		return "cart_show";
	}

	/**
	 * 到达需求确认页-购物车
	 * 
	 * @param request
	 */
	public String toOrder_gwc(HttpServletRequest request, HttpSession session) {
		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		String[] client_products = request.getParameterValues("products");
		String demand_file = request.getParameter("file_url");
		String demand_file2 = request.getParameter("file_url2");
		String demand_file3 = request.getParameter("file_url3");
		String content = request.getParameter("content");
		int order_source = request.getParameter("resource")==null?2:Integer.parseInt(request.getParameter("resource"));
		content = content.replace("'", "‘");
		content = content.replace("\"", "“");

		String product_ids_str = "";
		String product_counts_str = "";
		String product_item_ids = "";
		try {
			for (int i = 0; i < client_products.length; i++) {
				if (i == 0) {
					product_ids_str += client_products[i].split("-")[0];
					product_counts_str += client_products[i].split("-")[1];
					product_item_ids += client_products[i].split("-")[2];
				} else {
					product_ids_str += "," + client_products[i].split("-")[0];
					product_counts_str += ","
							+ client_products[i].split("-")[1];
					product_item_ids += "," + client_products[i].split("-")[2];
				}
			}
		} catch (Exception ex) {
			request.setAttribute("error", true);
			request.setAttribute("message", "【采购车】非法的参数！");
			return "error";
		}
		List<Product> products = productDao
				.validateProductList_do(product_ids_str,userinfo.getOwning_company());

		boolean isok = true;
		try {
			for (int i = 0; i < client_products.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(client_products[i]
							.split("-")[0])
							&& product.getproduct_items_id() == Integer
									.parseInt(client_products[i].split("-")[2])) {
						// 检验商品库存是否充足
						if (product.getOverplus() < Integer
								.parseInt(client_products[i].split("-")[1])) {
							isok = false;
							break;
						}
					}
				}

			}
			if (!isok) {// 判断商品库存是否充足
				request.setAttribute("error", true);
				request.setAttribute("message", "部分商品库存不足，<br/>请返回修改");
				return "error";
			}
		} catch (Exception ex) {
			String m = ex.getMessage();
		}
		// 封装订单详情
		List<OrderDetailsXuqiu> od_list = new ArrayList<OrderDetailsXuqiu>();
		int total_count = 0;
		double total_payment_money = 0;
		for (int i = 0; i < client_products.length; i++) {
			for (int j = 0; j < products.size(); j++) {
				Product product = products.get(j);
				if (product.getId() == Integer.parseInt(client_products[i]
						.split("-")[0])
						&& product.getproduct_items_id() == Integer
								.parseInt(client_products[i].split("-")[2])) {

					OrderDetailsXuqiu orderDetails = new OrderDetailsXuqiu();
					orderDetails.setProduct_id(product.getId());
					orderDetails.setProduct_imgurl(product.getImg_url());
					orderDetails.setProduct_name(product.getName());
					orderDetails.setProduct_count(Integer
							.parseInt(client_products[i].split("-")[1]));
					orderDetails.setProduct_unit_price(product.getPrice_new());
					orderDetails.setproduct_items_id(product
							.getproduct_items_id());
					orderDetails.setSpecial_code(product.getSpecial_code());
					od_list.add(orderDetails);
					total_count += Integer.parseInt(client_products[i]
							.split("-")[1]);
					total_payment_money += product.getPrice_new()
							* Integer
									.parseInt(client_products[i].split("-")[1]);
				}
			}
		}
		List<UserAddress> userAddressList = userAddressDao
				.queryAddressList(userinfo.getUser_id());
		UserInvoices userInvoices = userInvoicesDao.queryInvoices(userinfo
				.getUser_id());
		// 传输到前台-收货地址
		request.setAttribute("USERADDRESSLIST", userAddressList);
		// 传输到前台-发票信息
		request.setAttribute("USERINVOICES", userInvoices);
		// 传输到前台-订单类型
		request.setAttribute("resource", order_source);
		// 传输到前台-订单详情
		request.setAttribute("od_list", od_list);
		// 传输到前台-订单商品数量
		request.setAttribute("total_count", total_count);
		// 传输到前台-订单金额
		request.setAttribute("total_payment_money", total_payment_money);
		// 传输到前台-商品IDS
		request.setAttribute("product_ids", product_ids_str);
		request.setAttribute("product_item_ids", product_item_ids);
		// 传输到前台-商品订购数量S
		request.setAttribute("product_counts", product_counts_str);
		// 传输到前台-其他需求
		request.setAttribute("demand_file", demand_file);
		request.setAttribute("demand_file2", demand_file2);
		request.setAttribute("demand_file3", demand_file3);
		// 需求描述
		request.setAttribute("content", content);
		// 订单确认页
		return "cart_show";
	}

	/**
	 * 下订单-秒杀-ajax
	 * 
	 * @param request
	 */
	@Transactional
	public Map<String, Object> addOrder_now(HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		// 合同号
		String order_number = "H_" + Utils.GetOrderNumber();
		String xm = request.getParameter("xm");// 收货人姓名
		String yb = request.getParameter("yb");// 收货人邮编
		String dh = request.getParameter("dh");// 收货人电话
		String dz = request.getParameter("dz");// 收货地址
		int shipping_methods = request.getParameter("shipping_methods")==null?0:Integer.parseInt(request.getParameter("shipping_methods"));
		int install_service = request.getParameter("install_service")==null?0:Integer.parseInt(request.getParameter("install_service"));
		int owning_company = request.getParameter("owning_company")==null?0:Integer.parseInt(request.getParameter("owning_company"));
		// 订单详细信息
		List<OrderDetails> od_list = new ArrayList<OrderDetails>();
		int product_id = 0;
		try {
			product_id = Integer.parseInt(request.getParameter("product_id"));// 商品ID
		} catch (Exception ex) {
			resultMap.put("error", true);
			resultMap.put("message", "【秒杀 或 立即购买】<br/>非法的参数！");
			return resultMap;
		}
		int product_count = 1;// 订购数量,秒杀默认为1
		// 获得商品详细信息
		Product product = productDao.queryProduct(product_id);
		if (product == null) {
			// 这不是秒杀订单
			resultMap.put("error", true);
			resultMap.put("message", "商品不存在或已下架！");
			return resultMap;
		}
		if (product.getIs_ms() == 1) {
			if (!product.getFlag_begin()) {
				// 秒杀还未开始
				resultMap.put("error", true);
				resultMap.put("message", "【秒杀未开始】，<br/>请勿非法操作！");
				return resultMap;
			}
		}
		int product_out_count = productDao.queryProductCount(product_id);// 查询产品的销出数量
		product.setOut_count(product_out_count);
		if (product.getCount() < product_count) {// 库存不足
			// 返回库存不足页面
			resultMap.put("error", true);
			if (product.getIs_ms() == 1) {
				resultMap.put("message", "已经被抢光了~~");
			} else {
				resultMap.put("message", "【库存不足】无法下单！");
			}
			return resultMap;
		} else {
			try {

				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setOrder_number(order_number);
				orderDetails.setProduct_id(product_id);

				/************** 新增字段处理 begin ******************************/
				String brand_str = "无品牌";
				ProductBrand productBrand = productBrandDao
						.queryProductBrand(product.getBrand_id());
				if (null != productBrand) {
					brand_str = productBrand.getName();
				}
				List<ProductType> productTypeList = productTypeDao
						.queryProductTypeList();
				Map<String, String> typemap = OrderService
						.doList2Map_type(productTypeList);
				String type_ = product.getType_ids().substring(0,
						product.getType_ids().indexOf(",", 1));
				String type_str = typemap.get(type_) == null ? "无类别" : typemap
						.get(type_);
				orderDetails.setType_str(type_str);
				orderDetails.setBrand_str(brand_str);
				orderDetails.setXh(product.getXh());
				orderDetails.setPz(product.getPz());
				/************** 新增字段处理 end ******************************/

				orderDetails.setProduct_imgurl(product.getImg_url());
				orderDetails.setProduct_name(product.getName());
				orderDetails.setProduct_count(product_count);
				orderDetails.setProduct_unit_price(product.getPrice_new());
				orderDetails.setCreate_time(new Date());
				od_list.add(orderDetails);

				// 订单信息
				OrderInfo o = new OrderInfo();
				o.setOrder_number(order_number);
				o.setUser_id(userinfo.getUser_id());
				o.setOrder_state(0);// 未支付
				o.setOrder_time(new Date());
				o.setOrder_source(1);// 购物车来源-秒杀
				o.setShr_dh(dh);// 收货人电话
				o.setShr_dz(dz);// 收货人地址
				o.setShr_yb(yb);// 收货人邮编
				o.setShr_xm(xm);// 收货人姓名
				o.setFkfs("在线支付");
				o.setInstallation_service(install_service);
				o.setShipping_methods(shipping_methods);
				o.setOwning_company(owning_company);
				// 订单总金额
				double total = product_count * product.getPrice_new();
				o.setPayment_money(total);
				o.setIp(HTTPUtils.getIpAddr(request));
				System.out.println("orderinfo---------------" + o.toString());
				orderDao.addOrder(o);// 新增订单主表
				orderDao.addOrderDetail(od_list);// 新增订单详情
				productDao.updateProductCount(product_id, product_count);// 更新库存
				if (install_service == 1) {

					/************************ 新增服务单 start *****************************/
					UserInfo loginUser = (UserInfo) session
							.getAttribute("loginUser");
					
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
					UserService service_ = new UserService();
					service_.setAdd_time(new Date());
					service_.setContent("安装服务");
					service_.setLxr(xm);
					// int order_detail_id = 0;
					// String order_detail_id_str =
					// request.getParameter("order_detail_id");
					// if(null!=order_detail_id_str&&!"".equals(order_detail_id_str)){
					// order_detail_id = Integer.parseInt(order_detail_id_str);
					// }
					// String order_number_str =
					// "".equals(request.getParameter("order_number"))?"无关联订单":request.getParameter("order_number");
					// service_.setOrder_detail_id(order_detail_id);
					service_.setOrder_number(order_number);
					service_.setPhone(dh);
					service_.setAddress(dz);
					service_.setService_number(service_number);
					service_.setUser_id(loginUser.getUser_id());
					service_.setOwning_company(owning_company);
					service_.setOrder_detail_id(0);
					serviceDao.addService(service_);// 新增安装服务
					/************************ 新增服务单 end *****************************/
				}
				// 返回订单成功页面
				resultMap.put("success", "true");
				resultMap.put("order_number", order_number);
				return resultMap;
			} catch (Exception ex) {
				System.err
						.println("---------------------------------------------");
				System.err.println(ex.getMessage());
				System.err
						.println("---------------------------------------------");
				throw new RuntimeException("创建【秒杀 】<br/>订单出现错误！");
			}
		}
	}

	/**
	 * 提交需求单-购物车-ajax
	 * 
	 * @param request
	 */
	@Transactional
	public Map<String, Object> addOrder_gwc(HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		// 需求单号
		String order_number = Utils.GetOrderNumber();
		order_number = "X_" + order_number;
		String demand_file = request.getParameter("demand_file");// 其他需求
		String demand_file2 = request.getParameter("demand_file2");// 其他需求2
		String demand_file3 = request.getParameter("demand_file3");// 其他需求3
		String content = request.getParameter("content");// 备注
		int order_source = 0;
		int owning_company = request.getParameter("owning_company")==null?0:Integer.parseInt(request.getParameter("owning_company"));
		String xm = request.getParameter("xm");// 收货人姓名
		String yb = request.getParameter("yb");// 收货人邮编
		String dh = request.getParameter("dh");// 收货人电话
		String dz = request.getParameter("dz");// 收货地址

		String product_ids_str = request.getParameter("product_ids");// 商品IDS
																		// 以,分割
		String product_item_ids = request.getParameter("product_item_ids");// 商品IDS
																			// 以,分割
		String product_counts_str = request.getParameter("product_counts");// 商品数量S
																			// 以,分割

		String[] product_count = product_counts_str.split(",");
		String[] product_ids = product_ids_str.split(",");
		String[] product_items = product_item_ids.split(",");

		List<Product> products = productDao
				.validateProductList_do(product_ids_str,owning_company);

		boolean isok = true;
		try {
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						// 检验商品库存是否充足
						if (product.getCount() < Integer
								.parseInt(product_count[i])) {
							isok = false;
							break;
						}
					}
				}

			}
			if (!isok) {// 判断商品库存是否充足
				resultMap.put("error", true);
				resultMap.put("message", "部分商品库存不足，<br/>请返回修改");
				return resultMap;
			}
		} catch (Exception ex) {
			String m = ex.getMessage();
		}

		try {
			// 封装订单详情
			List<OrderDetailsXuqiu> od_list = new ArrayList<OrderDetailsXuqiu>();
			double total_payment_money = 0;
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						OrderDetailsXuqiu orderDetails = new OrderDetailsXuqiu();
						orderDetails.setOrder_number(order_number);
						orderDetails.setProduct_id(product.getId());
						orderDetails.setproduct_items_id(product
								.getproduct_items_id());

						/************** 新增字段处理 begin ******************************/
						List<ProductType> productTypeList = productTypeDao
								.queryProductTypeList();
						Map<String, String> typemap = OrderService
								.doList2Map_type(productTypeList);
						String type_ = product.getType_ids().substring(0,
								product.getType_ids().indexOf(",", 1) + 1);
						String type_str = typemap.get(type_) == null ? "无类别"
								: typemap.get(type_);

						List<ProductBrand> productBrandList = productBrandDao
								.queryProductBrandList();
						Map<Integer, String> brandmap = OrderService
								.doList2Map_brand(productBrandList);
						String brand_str = brandmap.get(product.getBrand_id()) == null ? "无品牌"
								: brandmap.get(product.getBrand_id());
						orderDetails.setType_str(type_str);
						orderDetails.setBrand_str(brand_str);
						orderDetails.setXh(product.getXh());
						orderDetails.setPz(product.getPz());
						/************** 新增字段处理 end ******************************/

						orderDetails.setProduct_imgurl(product.getImg_url());
						orderDetails.setProduct_name(product.getName());
						orderDetails.setProduct_count(Integer
								.parseInt(product_count[i]));
						orderDetails.setProduct_unit_price(product
								.getPrice_new());
						orderDetails.setCreate_time(new Date());
						od_list.add(orderDetails);
						total_payment_money += product.getPrice_new()
								* Integer.parseInt(product_count[i]);
					}
				}
			}
			// 订单信息
			OrderInfoXuqiu o = new OrderInfoXuqiu();
			o.setOrder_number(order_number);
			o.setUser_id(userinfo.getUser_id());
			o.setOrder_state(4);// 待报价
			o.setOrder_time(new Date());
			o.setOrder_source(order_source);// 购物车来源
			o.setDemand_file(demand_file);// 其他需求
			o.setDemand_file2(demand_file2);// 其他需求2
			o.setDemand_file3(demand_file3);// 其他需求3
			o.setContent(content);// 需求描述
			o.setOwning_company(owning_company);
			o.setDh(dh);// 收货人电话
			o.setDz(dz);// 收货人地址
			o.setYb(yb);// 收货人邮编
			o.setXm(xm);// 收货人姓名
			// 订单总金额
			double total = total_payment_money;
			o.setPayment_money(total);
			o.setIp(HTTPUtils.getIpAddr(request));

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("product_id", product.getId());
						map.put("product_items-id",
								product.getproduct_items_id());
						map.put("product_count", product_count[i]);
						// System.out.println("--------==============="+product.getproduct_items_id());
						list.add(map);
					}
				}
			}
			orderDao.addOrder_Xuqiu(o);// 新增需求单主表
			orderDao.addOrderDetail_Xuqiu(od_list);// 新增需求单详情
			productDao.updateProductCounts(list);// 批量更新销售记录
			productDao.updateProductStocks("-", list);// 批量更新库存
			// 批量删除购物车
			shoppingCartDao.delShoppingCarts(userinfo.getUser_id(),
					product_ids_str);

			// 返回订单成功页面
			resultMap.put("success", "true");
			resultMap.put("order_number", order_number);
			return resultMap;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("从采购车车提交<br/>需求出现错误！");
		}
	}

	/**
	 * 提交需求单-一键需求-ajax
	 * 
	 * @param request
	 */
	@Transactional
	public Map<String, Object> addOrder_one(HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		// 订单号
		String order_number = Utils.GetOrderNumber();
		order_number = "X_" + order_number;
		String title = request.getParameter("title");// 需求标题
		String demand_file = request.getParameter("demand_file");// 其他需求
		String content = request.getParameter("content");// 需求内容
		int owning_company = userinfo.getOwning_company()==null?0:userinfo.getOwning_company();
		try {
			// 订单信息
			OrderInfoXuqiu o = new OrderInfoXuqiu();
			o.setOrder_number(order_number);
			o.setUser_id(userinfo.getUser_id());
			o.setOrder_state(4);// 待报价
			o.setOrder_time(new Date());
			o.setOrder_source(2);// PC来源
			o.setDemand_file(demand_file);// 其他需求
			// 订单总金额
			double total = 0.0;
			o.setPayment_money(total);
			o.setIp(HTTPUtils.getIpAddr(request));
			o.setTitle(title);// 需求标题
			o.setContent(content);// 需求内容
			o.setOwning_company(owning_company);
			orderDao.addOrder_Xuqiu(o);// 新增需求单主表

			// 返回订单成功页面
			resultMap.put("success", "true");
			return resultMap;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("从一键需求 提交<br/>需求出现错误！");
		}
	}

	/**
	 * 查询订单合同
	 * 
	 * @param request
	 */
	public String queryOrderList(HttpServletRequest request, HttpSession session) {
		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		// 获得商品详细信息
		List<OrderInfo> orderInfoList = orderDao.queryOrderInfoList(userinfo
				.getUser_id());// 查询订单合同列表

		request.setAttribute("ORDERINFOLIST", orderInfoList);
		return "service_order_list";
	}

	/**
	 * 客户接受报价-需求单
	 */
	public int jsbjOrderXuqiu(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("id"));
			count = orderDao.updateOrderXuqiu_jsbj(id, userinfo.getUser_id());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 关闭-需求单
	 */
	public int cancelOrderXuqiu(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("id"));
			String order_number = request.getParameter("order_number");
			count = orderDao.cancelOrderXuqiu(id, userinfo.getUser_id());
			List<OrderDetailsXuqiu> xuqiuList = orderXuqiuDao
					.queryOrderDetail_Xuqiu(order_number);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < xuqiuList.size(); j++) {
				OrderDetailsXuqiu xuqiu = xuqiuList.get(j);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("product_id", xuqiu.getProduct_id());
				map.put("product_items-id", xuqiu.getproduct_items_id());
				map.put("product_count", xuqiu.getProduct_count());
				list.add(map);
			}
			productDao.updateProductStocks("+", list);// 批量更新库存
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 在线支付-购物车
	 * 
	 * @param request
	 */
	@Transactional
	public Map<String, Object> createOrderfromCart(HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 登陆用户信息
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		// 合同单号
		String order_number = "H_" + Utils.GetOrderNumber();
		String demand_file = request.getParameter("demand_file");// 其他需求

		String xm = request.getParameter("xm");// 收货人姓名
		String yb = request.getParameter("yb");// 收货人邮编
		String dh = request.getParameter("dh");// 收货人电话
		String dz = request.getParameter("dz");// 收货地址
		int shipping_methods = Integer.parseInt(request
				.getParameter("shipping_methods"));
		int install_service = Integer.parseInt(request
				.getParameter("install_service"));
		int order_source = request.getParameter("resource")==null?2:Integer.parseInt(request.getParameter("resource"));
		int owning_company = request.getParameter("owning_company")==null?0:Integer.parseInt(request.getParameter("owning_company"));
		String product_ids_str = request.getParameter("product_ids");// 商品IDS
																		// 以,分割
		String product_item_ids = request.getParameter("product_item_ids");// 商品IDS
																			// 以,分割
		String product_counts_str = request.getParameter("product_counts");// 商品数量S
																			// 以,分割

		String[] product_count = product_counts_str.split(",");
		String[] product_ids = product_ids_str.split(",");
		String[] product_items = product_item_ids.split(",");

		List<Product> products = productDao
				.validateProductList_do(product_ids_str,owning_company);

		boolean isok = true;
		try {
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						// 检验商品库存是否充足
						if (product.getCount() < Integer
								.parseInt(product_count[i])) {
							isok = false;
							break;
						}
					}
				}

			}
			if (!isok) {// 判断商品库存是否充足
				resultMap.put("error", true);
				resultMap.put("message", "部分商品库存不足，<br/>请返回修改");
				return resultMap;
			}
		} catch (Exception ex) {
			String m = ex.getMessage();
		}

		try {
			// 封装订单详情
			List<OrderDetails> od_list = new ArrayList<OrderDetails>();
			double total_payment_money = 0;
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						OrderDetails orderDetails = new OrderDetails();
						orderDetails.setOrder_number(order_number);
						orderDetails.setProduct_id(product.getId());
						orderDetails.setProduct_items_id(product.getproduct_items_id());
						System.out.println("item_id---"+product.getproduct_items_id());

						/************** 新增字段处理 begin ******************************/
						List<ProductType> productTypeList = productTypeDao
								.queryProductTypeList();
						Map<String, String> typemap = OrderService
								.doList2Map_type(productTypeList);
						String type_ = product.getType_ids().substring(0,
								product.getType_ids().indexOf(",", 1) + 1);
						String type_str = typemap.get(type_) == null ? "无类别"
								: typemap.get(type_);

						List<ProductBrand> productBrandList = productBrandDao
								.queryProductBrandList();
						Map<Integer, String> brandmap = OrderService
								.doList2Map_brand(productBrandList);
						String brand_str = brandmap.get(product.getBrand_id()) == null ? "无品牌"
								: brandmap.get(product.getBrand_id());
						orderDetails.setType_str(type_str);
						orderDetails.setBrand_str(brand_str);
						orderDetails.setXh(product.getXh());
						orderDetails.setPz(product.getPz());
						/************** 新增字段处理 end ******************************/

						orderDetails.setProduct_imgurl(product.getImg_url());
						orderDetails.setProduct_name(product.getName());
						orderDetails.setProduct_count(Integer
								.parseInt(product_count[i]));
						orderDetails.setProduct_unit_price(product
								.getPrice_new());
						orderDetails.setProduct_unit_price_bj(product
								.getPrice_new());
						orderDetails.setCreate_time(new Date());
						od_list.add(orderDetails);
						total_payment_money += product.getPrice_new()
								* Integer.parseInt(product_count[i]);
					}
				}
			}
			// 订单信息
			OrderInfo o = new OrderInfo();
			o.setOrder_number(order_number);
			o.setUser_id(userinfo.getUser_id());
			o.setOrder_state(0);// 未支付
			o.setOrder_time(new Date());
			o.setOrder_source(order_source);// 购物车来源
			o.setDemand_file(demand_file);// 其他需求
			o.setShr_dh(dh);// 收货人电话
			o.setShr_dz(dz);// 收货人地址
			o.setShr_yb(yb);// 收货人邮编
			o.setShr_xm(xm);// 收货人姓名
			o.setInstallation_service(install_service);
			o.setShipping_methods(shipping_methods);
			o.setOwning_company(owning_company);
			o.setFkfs("在线支付");
			// 订单总金额
			double total = total_payment_money;
			o.setPayment_money(total);
			o.setIp(HTTPUtils.getIpAddr(request));

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < product_count.length; i++) {
				for (int j = 0; j < products.size(); j++) {
					Product product = products.get(j);
					if (product.getId() == Integer.parseInt(product_ids[i])
							&& product.getproduct_items_id() == Integer
									.parseInt(product_items[i])) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("product_id", product.getId());
						map.put("product_items-id",
								product.getproduct_items_id());
						map.put("product_count", product_count[i]);
						// System.out.println("--------==============="+product.getproduct_items_id());
						list.add(map);
					}
				}
			}

			orderDao.addOrder(o);// 新增合同单主表
			orderDao.addOrderDetail(od_list);// 新增合同单详情
			productDao.updateProductCounts(list);// 批量更新销售记录
			productDao.updateProductStocks("-", list);// 批量更新库存

			if (install_service == 1) {

				/************************ 新增服务单 start *****************************/
				UserInfo loginUser = (UserInfo) session
						.getAttribute("loginUser");
				
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
				UserService service_ = new UserService();
				service_.setAdd_time(new Date());
				service_.setContent("安装服务");
				service_.setLxr(xm);
				// int order_detail_id = 0;
				// String order_detail_id_str =
				// request.getParameter("order_detail_id");
				// if(null!=order_detail_id_str&&!"".equals(order_detail_id_str)){
				// order_detail_id = Integer.parseInt(order_detail_id_str);
				// }
				// String order_number_str =
				// "".equals(request.getParameter("order_number"))?"无关联订单":request.getParameter("order_number");
				// service_.setOrder_detail_id(order_detail_id);
				service_.setOrder_number(order_number);
				service_.setPhone(dh);
				service_.setAddress(dz);
				service_.setService_number(service_number);
				service_.setUser_id(loginUser.getUser_id());
				service_.setOwning_company(owning_company);
				service_.setOrder_detail_id(0);
				serviceDao.addService(service_);// 新增安装服务
				/************************ 新增服务单 end *****************************/
			}

			// 批量删除购物车
			shoppingCartDao.delShoppingCarts(userinfo.getUser_id(),
					product_ids_str);

			// 返回订单成功页面
			resultMap.put("success", "true");
			resultMap.put("order_number", order_number);
			return resultMap;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("从采购车车提交<br/>需求出现错误！");
		}
	}

	/**
	 * 订单合同---签收
	 */
	@Transactional
	public int qsOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = "客户签收";
			count = orderDao.qsOrder(71, id);
			if (count > 0) {
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("签收");
				os.setText_sm(text_sm);
				count = orderDao.addOrderSm(os);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 订单合同---签收后实施
	 */
	@Transactional
	public int qsssOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = "客户签收，并发起实施";
			count = orderDao.qsOrder(61, id);
			if (count > 0) {
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("签收");
				os.setText_sm(text_sm);
				count = orderDao.addOrderSm(os);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	

	/**
	 * 订单合同---取消订单
	 */
	@Transactional
	public int qxOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
		UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
		int id = Integer.parseInt(request.getParameter("id"));
		String order_number = request.getParameter("order_number");
		count = orderDao.qsOrder(9,id);
		List<OrderDetails> orderList = orderDao
				.queryOrderDetailList(order_number);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < orderList.size(); j++) {
			OrderDetails od = orderList.get(j);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product_id", od.getProduct_id());
			map.put("product_items-id", od.getProduct_items_id());
			map.put("product_count", od.getProduct_count());
			list.add(map);
		}
		productDao.updateProductStocks("+", list);// 批量更新库存
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return count;
}

	/**
	 * 订单合同---实施完成
	 */
	@Transactional
	public int qrsswcOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = "客户确认实施完成";
			count = orderDao.qsOrder(72, id);
			if (count > 0) {
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("实施完成");
				os.setText_sm(text_sm);
				count = orderDao.addOrderSm(os);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 订单合同---评价
	 */
	@Transactional
	public int pjOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int order_id = Integer.parseInt(request.getParameter("order_id"));// 订单ID
			int score = Integer.parseInt(request.getParameter("score"));// 分数
			String content = request.getParameter("content");// 评价内容

			OrderEvaluate oe = new OrderEvaluate();
			oe.setContent(content);
			oe.setOrder_id(order_id);
			oe.setScore(score);
			oe.setUser_id(userinfo.getUser_id());
			count = orderDao.addOrderEvaluate(oe);
			if (count > 0) {// 修改状态
				count = orderDao.qsOrder(8, order_id);
			}
			if (count > 0) {// 写入日志
				OrderSm os = new OrderSm();
				os.setOrder_id(order_id);
				os.setType("完成评价");
				os.setText_sm("客户完成评价，分数" + score + "分");
				count = orderDao.addOrderSm(os);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 订单合同---开通
	 */
	@Transactional
	public int ktOrder(HttpServletRequest request, HttpSession session) {
		int count = 0;
		try {
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			int order_id = Integer.parseInt(request.getParameter("order_id"));// 订单ID
			
			count = orderDao.qsOrder(2, order_id);

		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
}