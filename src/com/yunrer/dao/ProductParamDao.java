package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductParam;

@Repository("ProductParamDao")
public class ProductParamDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询产品参数列表
	 * @param type 类型ID
	 * @param begin_index 
	 * @param page_size
	 * @return
	 */
	public List<ProductParam> queryProductParamList(int product_id) {
		Object[] params = new Object[] {product_id};
//		String sql = "select * from tbl_product_param where product_id = ? order by sortid desc,id desc";
		String sql = "select a.id,a.product_id,a.attrKey as par_name,a.attrVal as par_value,0 as sortid from tbl_product_attrs as a "
					+" inner join tbl_product as p on p.id=a.product_id "
					+" where product_id = ? and attrVal is not null and attrVal!='' "
					+" and EXISTS(select 1 from tbl_product_attr_template as t where t.category_id=func_get_split_string(p.type_ids,',',4)) "
					+" order by id desc";
		List<ProductParam> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<ProductParam>(ProductParam.class));
		return list;
	}
	
	
}