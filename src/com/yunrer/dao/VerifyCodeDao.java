package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Ad;

@Repository("VerifyCodeDao")
public class VerifyCodeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public int insetData(String ip_address ,String phone,String code ){
		
		StringBuffer sb = new StringBuffer("insert into tbl_verify_code (ip_address,phone,code) values(?,?,?)");
		Object[] params = new Object[]{ip_address,phone,code};
		return jdbcTemplate.update(sb.toString(), params);
		
	}
	
}