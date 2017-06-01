package com.yunrer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.StaticResource;

@Repository("StaticResourceDao")
public class StaticResourceDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加静态资源
	 * @param module
	 */
	public void addStaticResource(StaticResource s) {
		String sql = "insert into `tbl_staticresource` (skey,svalue,path,creat_time) values (?, ?, ?,now())";
		Object[] params = new Object[] { s.getSkey(), s.getSvalue(), s.getPath()};
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除静态资源
	 * @param ids
	 */
	public void deleteStaticResource(Object[] ids) {
		StringBuffer sql= new StringBuffer("update `tbl_staticresource` set deletion_code = 1 where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 修改静态资源
	 * @param module
	 */
	public void modifyStaticResource(StaticResource s) {
		String sql = "update `tbl_staticresource` set skey = ?, svalue = ?, path = ? where id = ?";
		Object[] params = new Object[] { s.getSkey(), s.getSvalue(), s.getPath(),s.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询静态资源
	 * @param id
	 * @return
	 */
	public StaticResource queryStaticResource(int id) {
		String sql = "select * from `tbl_staticresource` where id = ? and deletion_code = 0";
		Object[] params = new Object[] { id };
		List<StaticResource> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<StaticResource>(StaticResource.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询静态资源总数
	 * @param keyword
	 * @return
	 */
	public int queryStaticResourceCount(String keyword) {
		String sql = "select count(1) from `tbl_staticresource` where (skey like ? or svalue like ?) and deletion_code = 0";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 分页查询静态资源列表
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<StaticResource> querStaticResourceList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from `tbl_staticresource` where (skey like ? or svalue like ?) and deletion_code = 0  limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, start, pageSize };
		List<StaticResource> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<StaticResource>(StaticResource.class));
		return list;
	}
	/**
	 * 根据key获取静态资源
	 * @param keys
	 * @return
	 */
	public List<StaticResource> queryStaticResourceBykey(String[] keys) {
		StringBuffer sql =new StringBuffer("select * from `tbl_staticresource` where skey in(");//? and deletion_code = 0";
		List<String> params =new ArrayList<String>();
		for (int i = 0; i < keys.length; i++) {
			if (i != 0)
			sql.append(",");
			sql.append("?");
			params.add(keys[i]);
		}
		sql.append(") and deletion_code = 0 ");
		List<StaticResource> list = jdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper<StaticResource>(StaticResource.class));
		return list;
	}
	
}
