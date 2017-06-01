package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductType;

@Repository("ProductTypeDao")
public class ProductTypeDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据上级ID获取下级分类信息
	 */
	public List<ProductType> queryProductTypeList(int product_level) {
		String sql = "select * from tbl_product_type where  product_level= ?  and delete_flag = 0 order by sortid desc,id desc";
		Object[] params = new Object[] { product_level};
		List<ProductType> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	/**
	 * 查询所有商品分类
	 */
	public List<ProductType> queryProductTypeList() {
		String sql = "select * from tbl_product_type where delete_flag = 0   order by product_level asc, sub_parentid,sortid desc";
		List<ProductType> list = jdbcTemplate.query(sql, 
		new BeanPropertyRowMapper<ProductType>(ProductType.class));		
		return list;
	}
	
	/**
	 * 查询特供商品分类
	 * @return
	 */

	public List<ProductType> querySpecialProductTypeList(int owning_company) {
		String sql = "CALL getSpecialTypeList(?)";
		List<ProductType> list = jdbcTemplate.query(sql, new Object[]{owning_company},
				new BeanPropertyRowMapper<ProductType>(ProductType.class));		
		return list;
	}
	
	public ProductType queryProductById( int product_id){
		
		String sql = "select * from tbl_product_type where delete_flag = 0 and id = ? limit 1";
		List<ProductType> list = jdbcTemplate.query(sql,new Object[] {product_id},
		new BeanPropertyRowMapper<ProductType>(ProductType.class));		
		if(list!=null && list.size()>0)
			return list.get(0);
		else return null;
	}
	
	
}