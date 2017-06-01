package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductBrand;

@Repository("ProductBrandDao")
public class ProductBrandDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询品牌表列表
	 */
	public List<ProductBrand> queryProductBrandList() {
		String sql = "select * from tbl_product_brand where delete_flag = 0 order by sortid desc,id desc";
		List<ProductBrand> list = jdbcTemplate.query(sql,
		new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		return list;
	}
	
	/**
	 * 查询品牌实体
	 */
	public ProductBrand queryProductBrand(int id) {
		String sql = "select * from tbl_product_brand where id = ?";
		Object[] params = new Object[] { id};
		List<ProductBrand> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
}