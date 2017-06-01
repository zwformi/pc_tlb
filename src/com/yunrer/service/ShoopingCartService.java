package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ShoppingCartDao;
import com.yunrer.entity.Product;
import com.yunrer.entity.ShoppingCart;
import com.yunrer.entity.UserInfo;

@Service("ShoopingCartService")
@Transactional
public class ShoopingCartService {
	
	@Resource
	private ShoppingCartDao shoopingCartDao;
	
	@Resource
	private ProductDao productDao;
	
	/**
	 * 到达购物车页
	 * @param request
	 */
	public String toCart(HttpServletRequest request,HttpSession session){
		if(session.getAttribute("loginUser")==null){
			return "error";
		}else{
			//登陆用户信息
			UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
			List<ShoppingCart> shoppingCartList = shoopingCartDao.queryShoppingCartList(userinfo.getUser_id());
			request.setAttribute("SHOPPINGCARTLIST", shoppingCartList);
			//订单确认页
			return "cart";
		}
	}
	
	/**
	 * 查询采购车数量
	 * @param request
	 */
	public void queryCartCount(HttpServletRequest request,HttpSession session){
		//登陆用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		int count = shoopingCartDao.queryCartCount(userinfo.getUser_id());
		request.setAttribute("CARTCOUNT", count);
	}
	
	/**
	 * 查询采购车数量
	 * @param request
	 */
	public int getCartCount(HttpServletRequest request,HttpSession session){
		//登陆用户信息
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		int count = shoopingCartDao.queryCartCount(userinfo.getUser_id());
		return count;
	}
	
	/**
	 * 添加商品到购物车 -ajax
	 * @param request
	 */
	public Map<String,Object> addCart(HttpServletRequest request,HttpSession session){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//登陆用户信息
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登陆已失效或未登陆！");
				return resultMap;
			}else{
				UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
				int product_id = Integer.parseInt(request.getParameter("product_id"));//商品ID
				int count = Integer.parseInt(request.getParameter("count"));//商品数量
				int product_items_id=0;
				try{
					product_items_id = Integer.parseInt(request.getParameter("product_items_id"));
				}catch(Exception e){}
				
				Product product = productDao.queryProduct(product_id);//查询商品详情
				if(product==null){//判断商品是否已下架
					resultMap.put("error", true);
					resultMap.put("message", "商品不存在或已下架！");
					return resultMap;
				}else{
					ShoppingCart cart = new ShoppingCart();
					cart.setUser_id(userinfo.getUser_id());
					cart.setProduct_id(product_id);
					cart.setproduct_items_id(product_items_id);
					int return_count = 0;
					if(shoopingCartDao.isinShoppingCart(cart)){//已在购物车内
						return_count = shoopingCartDao.updateShoppingCart(cart, count);
					}else{//新加入购物车
						cart.setProduct_count(count);//数量
						cart.setProduct_name(product.getName());//商品名称
						cart.setProduct_img_url(product.getImg_url());//商品图片
						return_count = shoopingCartDao.addShoppingCart(cart);
					}
					resultMap.put("count", return_count);
					return resultMap;
				}
			}
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【错误】非法的参数！");
			return resultMap;
		}

	}
	
	/**
	 * 更新购物车商品数量 -ajax
	 * @param request
	 */
	public Map<String,Object> modifyCartCount(HttpServletRequest request,HttpSession session){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//登陆用户信息
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登陆已失效或未登陆！");
				return resultMap;
			}else{
				UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
				int product_id = Integer.parseInt(request.getParameter("product_id"));//商品ID
				int count = Integer.parseInt(request.getParameter("count"));//商品数量
				int product_items_id=0;
				try{
					product_items_id = Integer.parseInt(request.getParameter("product_items_id"));
				}catch(Exception e){}
				
				Product product = productDao.queryProduct(product_id);//查询商品详情
				if(product==null){//判断商品是否已下架
					resultMap.put("error", true);
					resultMap.put("message", "商品不存在或已下架！");
					return resultMap;
				}else{
					ShoppingCart cart = new ShoppingCart();
					cart.setUser_id(userinfo.getUser_id());
					cart.setProduct_id(product_id);
					cart.setproduct_items_id(product_items_id);
					int return_count = 0;
					if(shoopingCartDao.isinShoppingCart(cart)){//已在购物车内
						return_count = shoopingCartDao.modifyShoppingCart(cart, count);
					}else{//新加入购物车
						cart.setProduct_count(count);//数量
						cart.setProduct_name(product.getName());//商品名称
						cart.setProduct_img_url(product.getImg_url());//商品图片
						return_count = shoopingCartDao.addShoppingCart(cart);
					}
					resultMap.put("count", return_count);
					return resultMap;
				}
			}
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【错误】非法的参数！");
			return resultMap;
		}

	}
	
	/**
	 * 购物车验证商品信息-ajax
	 */
	public Map<String,Object> validateProductList(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			String ids = request.getParameter("ids");
			String subids = request.getParameter("subids");
			UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
			List<Product> productList = productDao.validateProductList(ids,subids,loginUser.getOwning_company());
			resultMap.put("PRODUCTLIST",productList);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "验证商品信息失败");
		}
		return resultMap;
	}
	
	/**
	 * 从购物车删除商品 -ajax
	 * @param request
	 */
	public Map<String,Object> delCart(HttpServletRequest request,HttpSession session){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//登陆用户信息
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登陆已失效或未登陆！");
				return resultMap;
			}else{
				UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
				int product_id = Integer.parseInt(request.getParameter("product_id"));//商品ID
				int product_items_id = Integer.parseInt(request.getParameter("product_items_id"));//商品ID
				
				ShoppingCart cart = new ShoppingCart();
				cart.setUser_id(userinfo.getUser_id());
				cart.setProduct_id(product_id);
				cart.setproduct_items_id(product_items_id);
				shoopingCartDao.delShoppingCart(cart);
				resultMap.put("count", 1);
				return resultMap;
			}
			
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "【错误】非法的参数！");
			return resultMap;
		}

	}
}