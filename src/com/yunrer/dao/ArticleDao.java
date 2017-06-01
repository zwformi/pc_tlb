package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Article;

@Repository("ArticleDao")
public class ArticleDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询文章表列表
	 */
	public List<Article> queryArticleList(int typeid) {
		Object[] params = new Object[] { typeid };
		String sql = "select id,typeid,title,create_time,article_url from tbl_article where typeid = ? order by sortid desc,id desc";
		List<Article> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<Article>(Article.class));
		return list;
	}
	
	/**
	 * 查询文章实体
	 */
	public Article queryArticle(int id) {
		StringBuffer sql = new StringBuffer("select * from tbl_article where id = ?");
		Object[] params = new Object[] { id };
		List<Article> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<Article>(Article.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
}