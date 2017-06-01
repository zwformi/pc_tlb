package com.yunrer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ModuleForArticle;

@Repository("ModuleForArticleDao")
public class ModuleForArticleDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加模块
	 * @param module
	 */
	public void addModule(ModuleForArticle module) {
		String sql = "insert into `tbl_module` (module_name,creat_time,module_url,belongto_tf,sortid) values (?, now(), ?, ?, ?)";
		Object[] params = new Object[] { module.getModule_name(), module.getModule_url(), module.getBelongto_tf(), module.getSortid() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除模块
	 * @param ids
	 */
	public void deleteModule(Object[] ids) {
		StringBuffer sql= new StringBuffer("update `tbl_module` set deletion_code = 1 where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 修改模块
	 * @param module
	 */
	public void modifyModule(ModuleForArticle module) {
		String sql = "update `tbl_module` set module_name = ?, module_url = ?, belongto_tf = ?, sortid = ? where id = ?";
		Object[] params = new Object[] { module.getModule_name(), module.getModule_url(), module.getBelongto_tf(), module.getSortid(), module.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询模块
	 * @param id
	 * @return
	 */
	public ModuleForArticle queryModule(int id) {
		String sql = "select * from `tbl_module` where id = ? and deletion_code = 0";
		Object[] params = new Object[] { id };
		List<ModuleForArticle> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ModuleForArticle>(ModuleForArticle.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询模块总数
	 * @param keyword
	 * @return
	 */
	public int queryModuleCount(String keyword) {
		String sql = "select count(1) from `tbl_module` where module_name like ? and deletion_code = 0";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 分页查询模块列表
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<ModuleForArticle> querModuleList(int type,String keyword, int pageIndex, int pageSize) {
		List<ModuleForArticle> list = new ArrayList<ModuleForArticle>();
		if(type==0)
		{
		String sql = "select * from `tbl_module` where module_name like ? and deletion_code = 0 order by sortid limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ModuleForArticle>(ModuleForArticle.class));
		}
		else{
			String sql = "select * from `tbl_module` where deletion_code = 0 order by sortid desc";
			list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<ModuleForArticle>(ModuleForArticle.class));
		}
		return list;
	}
}
