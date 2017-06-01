package com.yunrer.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ShoppingCart;
import com.yunrer.util.SkuSqlUtil;

@Repository("ShoppingCartDao")
public class ShoppingCartDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询用户购物车信息
	 * @param user_id 用户ID
	 * @return
	 */
	public List<ShoppingCart> queryShoppingCartList(int user_id) {
		Object[] params = new Object[] {user_id};
		String sql= "select " +
				"cat.id," +
				"cat.user_id," +
				"cat.product_id," +
				"cat.product_name,"+
				"cat.product_img_url," +
				"cat.product_count," +
				"p.special_code special_code," +
				SkuSqlUtil.makeSkuSql(8,"tps","peizhi")+","+
				"IFNULL(cat.product_items_id,'0') product_items_id " +                              
				"from tbl_shopping_cart cat "+
				"left join  tbl_product p on cat.product_id = p.id " +
				"left JOIN tbl_product_skus tps on tps.id=cat.product_items_id and tps.product_id=cat.product_id  " +
				"where cat.user_id = ? and cat.is_wx = 0 order by cat.create_time desc";
		List<ShoppingCart> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<ShoppingCart>(ShoppingCart.class));
		return list;
	}
	
	/**
	 * 查询用户购物车数量
	 * @param user_id 用户ID
	 * @return
	 */
	public int queryCartCount(int user_id) {
		Object[] params = new Object[] {user_id};
		String sql = "select IFNULL(sum(product_count),0) from tbl_shopping_cart where user_id = ? and is_wx = 0";
		int count = jdbcTemplate.queryForObject(sql,Integer.class,params);
		return count;
	}
	
	/**
	 * 将商品加入用户购物车-新商品
	 * @return
	 */
	public int addShoppingCart(ShoppingCart cart) {
		try {
			Object[] params = new Object[] {cart.getUser_id(),cart.getProduct_id(),cart.getProduct_name(),cart.getProduct_img_url(),cart.getProduct_count(),new Date(),cart.getproduct_items_id()};
			String sql = "insert into tbl_shopping_cart(user_id,product_id,product_name,product_img_url,product_count,create_time,product_items_id,is_wx) values(?,?,?,?,?,?,?,0)";
			int count_= jdbcTemplate.update(sql,params);
			return count_;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("加入购物车出现错误！");
		}
	}
	
	/**
	 * 购物车内的商品数量(加减)
	 * @param cart 
	 * @param count  数量   加则 正数  减则负数
	 * @return
	 */
	public int updateShoppingCart(ShoppingCart cart,int count) {
		try {
			Object[] params = new Object[] {count,new Date(),cart.getUser_id(),cart.getProduct_id(),cart.getproduct_items_id()};
			String sql = "update tbl_shopping_cart set product_count=product_count+?,create_time=? where user_id = ? and product_id = ?  and product_items_id =? and is_wx = 0";
			int count_= jdbcTemplate.update(sql,params);
			return count_;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("更新商品数量出现错误！");
		}
	}
	
	/**
	 * 更新购物车内的商品数量
	 * @param cart 
	 * @param count  数量   加则 正数  减则负数
	 * @return
	 */
	public int modifyShoppingCart(ShoppingCart cart,int count) {
		try {
			Object[] params = new Object[] {count,new Date(),cart.getUser_id(),cart.getProduct_id(),cart.getproduct_items_id()};
			String sql = "update tbl_shopping_cart set product_count=?,create_time=? where user_id = ? and product_id = ?  and product_items_id =? and is_wx = 0";
			int count_= jdbcTemplate.update(sql,params);
			return count_;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("更新商品数量出现错误！");
		}
	}
	
	/**
	 * 查询商品是否在购物车内 ,如果在购物车内 返回true   不在购物车内返回false
	 * @return
	 */
	public boolean isinShoppingCart(ShoppingCart cart) {
		Object[] params = new Object[] {cart.getUser_id(),cart.getProduct_id(),cart.getproduct_items_id()};
		String sql = "select id out_count from tbl_shopping_cart where user_id = ? and product_id = ? and product_items_id=? and is_wx = 0";
		List<ShoppingCart> list = jdbcTemplate.query(sql,params,
				new BeanPropertyRowMapper<ShoppingCart>(ShoppingCart.class));
		if(list!=null&&!list.isEmpty()&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 从购物车内移除商品
	 * @param cart
	 * @return
	 */
	public void delShoppingCart(ShoppingCart cart) {
		try {			
			Object[] params = new Object[] {cart.getUser_id(),cart.getProduct_id()};
			String where=cart.getproduct_items_id()==0?" is null or  product_items_id=0) ":" ='"+String.valueOf(cart.getproduct_items_id())+"')";
			String sql = "delete from tbl_shopping_cart where user_id = ? and product_id = ? and (product_items_id"+where+"  and is_wx = 0";
			jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("从购物车内移除商品出现错误！");
		}
	}
	
	/**
	 * 批量从购物车内移除商品
	 * @param cart
	 * @return
	 */
	public void delShoppingCarts(int user_id,String product_id) {
		try {
			Object[] params = new Object[] {user_id};
			String sql = "delete from tbl_shopping_cart where user_id = ? and product_id in ("+product_id+") and is_wx = 0";
			jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("从购物车内批量移除商品出现错误！");
		}
	}
	
	/**
	 * 查询购物车内商品总数
	 * @return
	 */
	public int getCartCount(ShoppingCart cart) {
		Object[] params = new Object[] {cart.getUser_id()};
		String sql = "select sum(count) count_ from tbl_shopping_cart where user_id = ? and is_wx = 0";
		int count = jdbcTemplate.queryForObject(sql, params, Integer.class);
		return count;
	}
	
}