package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.Product;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.ProductService;

/**
 * 商品跳转处理逻辑
 * @author wangpeng
 */
@Controller
@RequestMapping("/buy")
public class ProductControl {
	@Resource
	private ProductService productService;
	
	/**
	 * 到达商品列表页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toProduct(HttpServletRequest request) {
		return productService.toProductList(request);
	}
	
	/**
	 * 获取商品类型列表信息-ajax
	 */
	@RequestMapping(value = "/gettypes",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryProductTypeList(HttpServletRequest request) {
		return productService.queryProductTypeList(request);
	}
	
	/**
	 * 获取商品总数信息-ajax
	 */
	@RequestMapping(value = "/getcount",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProductCount(HttpServletRequest request) {
		return productService.getProductCount(request);
	}
	
	/**
	 * 获取商品列表信息-ajax
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProduct(HttpServletRequest request) {
		return productService.getProductList(request);
	}
	
	/**
	 * 到达商品详情页
	 */
	@RequestMapping(value = "/buy_show",method = RequestMethod.GET)
	public String toDetails(HttpServletRequest request) {
		return productService.toProductDetail(request);
	}
	
	@RequestMapping(value="/redlist",method=RequestMethod.GET)
	public @ResponseBody List<Product> getRedList(HttpServletRequest req){
		return productService.getRedList(req);
	}
	
	
	/**
	 * 到达特供商品列表详情页
	 */
	@RequestMapping(value = "/special_list",method = RequestMethod.GET)
	public String toSpecialList(HttpServletRequest request,HttpSession session) {
		try{
			if(session.getAttribute("loginUser")!=null){
				UserInfo user = (UserInfo)session.getAttribute("loginUser");
				if(user.getSpecial_code()==1)
				productService.toSpecialProductList(request,session);
				
			}
			
		}catch(Exception e){
			request.setAttribute("message", "查询特供商品列表出现错误..");
			return "error";
			
		}
		return "special_list";
	}
	
	/**
	 * 到达特供商品详情页
	 */
	@RequestMapping(value = "/special_detail",method = RequestMethod.GET)
	public String toSpecialDetails(HttpServletRequest request,HttpSession session) {
		
		try{
			if(session.getAttribute("loginUser")!=null){
				UserInfo user = (UserInfo)session.getAttribute("loginUser");
				if(user.getSpecial_code()==1)
					return productService.toSpecialProductDetail(request, session);
				
			}
			
		}catch(Exception e){
			request.setAttribute("message", "查询特供商品出现错误..");
			return "error";
			
		}
		return "special_detail";
	}

	
	/**
	 * 获取特供商品总数-ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/get_special_count",method = RequestMethod.POST)
	public Map<String,Object> getSpecialProductCount(HttpServletRequest request,HttpSession session) {
		try{
				
			
			if(session.getAttribute("loginUser")!=null){
				String type_ = request.getParameter("type");//商品类别
				String brand_ = request.getParameter("brand");//商品品牌
				String size_=request.getParameter("size");//尺寸
				String color_=request.getParameter("color");//尺寸
				String vision_=request.getParameter("vision");//尺寸
				String zxsyh_ = request.getParameter("zxsyh");//只显示有货
				String where = request.getParameter("where");//查询条件
				
				int zxsyh = Integer.parseInt(zxsyh_);
				int brand = Integer.parseInt(brand_);
				UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
				return productService.getSpecialProductCount(loginUser.getOwning_company(),type_,brand,zxsyh,where);
				
			}else{
				Map<String,Object> resultMap = new HashMap<String, Object>();
				resultMap.put("error", true);
				resultMap.put("message", "尚未登陆..");
				return resultMap;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		return null;
	}
	
	/**
	 * 获取特供商品列表信息-ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/get_special_list",method = RequestMethod.POST)
	public Map<String,Object> getSpecialProduct(HttpServletRequest request,HttpSession session) {
		try{
			
			if(session.getAttribute("loginUser")!=null){
				UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
				/*********分页使用 begin*********/
				int page_index=0;//默认从第一页开始
				int page_size = 8;
				String page = request.getParameter("page_index");
				if(page!=null&&!"".equals(page)){
					page_index = Integer.parseInt(page);
				}
				int begin_index = page_index*page_size;
				/*********分页使用 end*********/
				String type_ = request.getParameter("type");//商品类别
				String brand_ = request.getParameter("brand");//商品品牌
				int brand = Integer.parseInt(brand_);
				
				String zxsyh_ = request.getParameter("zxsyh");//只显示有货
				int zxsyh = Integer.parseInt(zxsyh_);
				
				String orderby = request.getParameter("orderby");//排序字段
				String where = request.getParameter("where");//查询条件
				return productService.getSpecialProductList(loginUser.getOwning_company(),type_,brand, zxsyh,begin_index, page_size,orderby,where);
				
			}else{
				Map<String,Object> resultMap = new HashMap<String, Object>();
				resultMap.put("error", true);
				resultMap.put("message", "尚未登陆..");
				return resultMap;
				
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}
		return null;
	}
}