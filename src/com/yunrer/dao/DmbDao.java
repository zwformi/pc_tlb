package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Dmb;

@Repository("DmbDao")
public class DmbDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据代码表类型查询代码表列表
	 */
	public List<Dmb> queryDmbList(int typeid) {
		String sql = "select * from tbl_dmb where typeid = ?  and isdelete = 0 order by sortid desc,id asc";
		Object[] params = new Object[] { typeid};
		List<Dmb> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<Dmb>(Dmb.class));
		return list;
	}
	
	
}