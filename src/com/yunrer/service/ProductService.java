package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yunrer.dao.ProductBrandDao;
import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ProductParamDao;
import com.yunrer.dao.ProductTypeDao;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductImage;
import com.yunrer.entity.ProductParam;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.ProductItems;
import com.yunrer.entity.SysStringMap;
import com.yunrer.entity.UserInfo;

@Service("ProductService")
public class ProductService {
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProductParamDao productParamDao;
	
	@Resource
	private ProductTypeDao productTypemDao;
	
	@Resource
	private ProductBrandDao productBrandDao;

	/**
	 * 到达商品列表页 处理逻辑
	 */
	public String toProductList(HttpServletRequest request) {
		//查询热卖推荐 -4条
		List<Product> productRedList =  productDao.queryProductRedList1(4);
		request.setAttribute("PRODUCTREDLIST",productRedList);
		String typeid_ = request.getParameter("typeid");
		if(typeid_=="")
			typeid_="1";
		request.setAttribute("TYPEID", typeid_);//保存类别ID
		//查询一级大类
		List<ProductType> productTypeList = productTypemDao.queryProductTypeList(0);
		request.setAttribute("PRODUCTTYPELIST",productTypeList);
		List<SysStringMap> StringMapList=productDao.QueryPublicStringMap();
		request.setAttribute("STRINGMAPLIST",StringMapList);
		if(typeid_ !=null && !"".equals(typeid_ )){
		String [] typeid_2=typeid_.split(",");
			switch(typeid_2.length){
			case 0:break;
			case 1:
				ProductType type_11 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				request.setAttribute("TYPE", type_11.getName());//保存1级大类
				break;
			case 2:
				ProductType type_21 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				ProductType type_22 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[1]));
				request.setAttribute("TYPE", type_21.getName());//保存1级大类
				request.setAttribute("TYPENAME", type_22.getName());//保存2级大类
				break;
			case 3:
				ProductType type_31 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				ProductType type_32 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[1]));
				ProductType type_33 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[2]));
				request.setAttribute("TYPE", type_31.getName());//保存1级大类
				request.setAttribute("TYPENAME", type_32.getName());//保存2级大类
				request.setAttribute("THIRDTYPENAME", type_33.getName());//保存2级大类
				break;
			default:break;
			}
		}
		//查询品牌
		List<ProductBrand> productBrandList = productBrandDao.queryProductBrandList();
		request.setAttribute("PRODUCTBRANDLIST",productBrandList);
		
		return "buy_list";//商品列表页
	}
	
	/**
	 * 热卖推荐
	 */
	public List<Product> getRedList(HttpServletRequest request){
		int num = request.getParameter("count")==null?4:Integer.parseInt(request.getParameter("count"));
		List<Product> productRedList =  productDao.queryProductRedList1(num);
		if (productRedList == null )return null;
		return productRedList;
	}
	
	
	/**
	 * 根据上级类别id获取下级类别-ajax
	 */
	public Map<String,Object> queryProductTypeList(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			int parentId = Integer.parseInt(request.getParameter("parentId"));
			List<ProductType> productTypeList = productTypemDao.queryProductTypeList(parentId);
			resultMap.put("PRODUCTTYPELIST", productTypeList);
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 获得pc商品数量-ajax
	 */
	public Map<String,Object> getProductCount(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String type_ = request.getParameter("type");//商品类别
		String brand_ = request.getParameter("brand");//商品品牌
		String size_=request.getParameter("size");//尺寸
		String color_=request.getParameter("color");//尺寸
		String vision_=request.getParameter("vision");//尺寸
		String zxsyh_ = request.getParameter("zxsyh");//只显示有货
		String where = request.getParameter("where");//查询条件
		
		int zxsyh = Integer.parseInt(zxsyh_);
		int brand = Integer.parseInt(brand_);
		if(type_=="")
		{
			type_="1";
		}
		int total_count =0;//商品数量
		//产品列表信息-根据品牌查询
		try{
		total_count = productDao.queryProductcount(type_,brand,zxsyh,where,size_,color_,vision_);
		}
		catch(Exception  e)
		{
			String s=e.getMessage();
			String s1=s;
		}
		resultMap.put("count", total_count);
		return resultMap;
	}
	/**
	 * 获得pc商品列表-ajax
	 */
	public Map<String,Object> getProductList(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
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
			String size_=request.getParameter("size");//尺寸
			String color_=request.getParameter("color");//尺寸
			String vision_=request.getParameter("vision");//尺寸
			if(type_==""){
				type_="1";
			}
				
			List<Product> productList =  productDao.queryProductList(type_,brand, zxsyh,begin_index, page_size,orderby,where);
			resultMap.put("PRODUCTLIST",productList);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "获取商品信息失败");
		}
		return resultMap;
	}
	
	/**
	 * 获得特供商品数量-ajax
	 */
	public Map<String,Object> getSpecialProductCount(int owning_comapny,String type_,int brand,int zxsyh,String where) {
		Map<String,Object> resultMap = new HashMap<String,Object>();

		if(type_=="")
			type_="1";
		int total_count =0;//商品数量
		//产品列表信息-根据品牌查询
		try{
		total_count = productDao.querySpecialProductcount(owning_comapny,type_,brand,zxsyh,where);
		}
		catch(Exception  e)
		{
			System.out.println(e.getMessage());
		}
		resultMap.put("count", total_count);
		return resultMap;
	}
	/**
	 * 获得特供商品列表-ajax
	 */
	public Map<String,Object> getSpecialProductList(int owning_company , String type_,int brand,int zxsyh,int begin_index,int page_size,String orderby,String where) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{

			if(type_=="")
				type_="1";
			List<Product> productList =  productDao.querySpecialProductList(owning_company,type_,brand, zxsyh,begin_index, page_size,orderby,where);
			resultMap.put("PRODUCTLIST",productList);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "获取商品信息失败");
		}
		return resultMap;
	}

	/**
	 * 到达商品详情页处理逻辑
	 */
	public String toProductDetail(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("ID"));//商品ID
		int item_id = request.getParameter("ITEM")==null ||
					  request.getParameter("ITEM").equals("")?
					  0:Integer.parseInt(request.getParameter("ITEM"));
		//产品详情
		Product product = productDao.queryProduct(id);
		if(product==null || product.getSpecial_code()==1){//产品不存在或为内购产品id
			return "error404";//产品不存在页面
		}else{
			//查询一级大类
			
			int product_out_count = productDao.queryProductCount(id);//查询产品的销出数量
			product.setOut_count(product_out_count);//保存售出数量
			request.setAttribute("PRODUCT",product);
			
			//获得商品图片相册
			List<ProductImage> productImages = productDao.queryProductImagesList(id,item_id);
			if (productImages == null || productImages.size()==0)
				productImages = productDao.queryProductImagesList(id,0);
				
			request.setAttribute("PRODUCTIMAGES",productImages);
			//查询产品参数
			List<ProductParam> productParam = productParamDao.queryProductParamList(id);
			request.setAttribute("PRODUCTPARAMLIST",productParam);
			
			List<ProductItems> productitem=productDao.QueryProductItemsList(id);
			request.setAttribute("PRODUCTITEMS", productitem);
			
			return "buy_show";//产品详情页
		}
	}
	
	/**
	 * 到达特供商品列表页 处理逻辑
	 */
	public void toSpecialProductList(HttpServletRequest request,HttpSession session) {
		String typeid_ = request.getParameter("typeid");
		if(typeid_=="" || typeid_==null)
			{
			//查询特工商品的第一个大类
			UserInfo userinfo = (UserInfo) session.getAttribute("loginUser");
			List<ProductType> list = productTypemDao.querySpecialProductTypeList(userinfo.getOwning_company());
			if(list!=null && list.size()>0)
				typeid_ = list.get(0).getId().toString();
			
			}
		request.setAttribute("TYPEID", typeid_);//保存类别ID
		//查询一级大类
		List<ProductType> productTypeList = productTypemDao.queryProductTypeList(0);
		request.setAttribute("PRODUCTTYPELIST",productTypeList);
		List<SysStringMap> StringMapList=productDao.QueryPublicStringMap();
		request.setAttribute("STRINGMAPLIST",StringMapList);
		if(typeid_ !=null && !"".equals(typeid_ )){
		String [] typeid_2=typeid_.split(",");
			switch(typeid_2.length){
			case 0:break;
			case 1:
				ProductType type_11 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				request.setAttribute("TYPE", type_11.getName());//保存1级大类
				break;
			case 2:
				ProductType type_21 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				ProductType type_22 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[1]));
				request.setAttribute("TYPE", type_21.getName());//保存1级大类
				request.setAttribute("TYPENAME", type_22.getName());//保存2级大类
				break;
			case 3:
				ProductType type_31 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[0]));
				ProductType type_32 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[1]));
				ProductType type_33 = productTypemDao.queryProductById(Integer.parseInt(typeid_2[2]));
				request.setAttribute("TYPE", type_31.getName());//保存1级大类
				request.setAttribute("TYPENAME", type_32.getName());//保存2级大类
				request.setAttribute("THIRDTYPENAME", type_33.getName());//保存2级大类
				break;
			default:break;
			}
		}
		//查询品牌
		List<ProductBrand> productBrandList = productBrandDao.queryProductBrandList();
		request.setAttribute("PRODUCTBRANDLIST",productBrandList);
	}
	
	/**
	 * 到达特供商品详情页处理逻辑
	 */
	public String toSpecialProductDetail(HttpServletRequest request,HttpSession session) {

			
			UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
			int id = Integer.parseInt(request.getParameter("ID"));//商品ID
			int item_id = request.getParameter("ITEM")==null ||
					request.getParameter("ITEM").equals("")?
							0:Integer.parseInt(request.getParameter("ITEM"));
			//产品详情
			Product product = productDao.queryProduct(id);

			if(product==null){
				return "error404";//产品不存在页面
			}else if(product.getSpecial_code()==0){
				request.setAttribute("message", "商品【"+product.getName()+"】<br>不属于内购产品");
				return "error";
			}else{
				//查询一级大类
				
				int product_out_count = productDao.queryProductCount(id);//查询产品的销出数量
				product.setOut_count(product_out_count);//保存售出数量
				request.setAttribute("PRODUCT",product);
				
				//获得商品图片相册
				List<ProductImage> productImages = productDao.queryProductImagesList(id,item_id);
				if (productImages == null || productImages.size()==0)
					productImages = productDao.queryProductImagesList(id,0);
				
				request.setAttribute("PRODUCTIMAGES",productImages);
				//查询产品参数
				List<ProductParam> productParam = productParamDao.queryProductParamList(id);
				request.setAttribute("PRODUCTPARAMLIST",productParam);
				
				List<ProductItems> productitem=productDao.QueryProductItemsList(id);
				request.setAttribute("PRODUCTITEMS", productitem);
				if(session.getAttribute("loginUser")!=null){
					Double special_price = productDao.getSpecialPrice(loginUser.getOwning_company(), id);
					request.setAttribute("special_price", special_price);
					return "special_detail";
				}
				else 
					 return "buy_show";//产品详情页
			}

		
	}
	
}
