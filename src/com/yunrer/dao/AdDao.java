package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Ad;

@Repository("AdDao")
public class AdDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询广告图片列表
	 */
	public List<Ad> queryAdList(int typeid,int count) {
		String sql = "select name,img_url,url from tbl_ad where typeid = ? order by sortid desc limit 0,? ";
		Object[] params = new Object[] { typeid ,count};
		List<Ad> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Ad>(Ad.class));
		return list;
	}
	
}