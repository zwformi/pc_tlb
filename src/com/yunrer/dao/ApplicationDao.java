package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Application;

@Repository("ApplicationDao")
public class ApplicationDao {

	@Resource
	private JdbcTemplate jdbctemplate;
	
	/**
	 * 新增申请记录
	 */
	public int addApplication(Application a){
		
		StringBuffer sql = new StringBuffer("insert into tbl_application (user_id,modified_by,owning_company,content,status,create_time) values(?,?,?,?,?,NOW())");
		Object[] params = new Object[]{a.getUser_id(),a.getModified_by(),a.getOwning_company(),a.getContent(),a.getStatus()};
		return jdbctemplate.update(sql.toString(),params);
		
		
	}
	/**
	 * 修改状态
	 */
	public int modifyApplication(Application a){
		StringBuffer sql = new StringBuffer("update tbl_application set status=?,modified_by=? where id=?");
		Object[] params = new Object[]{a.getStatus(),a.getModified_by(),a.getId()};
		return jdbctemplate.update(sql.toString(),params);
		
		
	}
	/**
	 * 根据公司id获取申请加入人员的信息
	 */
	public List<?> getApplications(Integer owning_company){
		StringBuffer sql = new StringBuffer("select a.id,a.user_id,a.modified_by,a.owning_company,a.content,a.status,a.create_time,a.modified_time,u.* from tbl_application a  INNER JOIN tbl_user_info u on a.user_id = u.user_id and a.status =10 and u.status!=2 where a.owning_company=? ");
		Object[] params = new Object[]{owning_company};
		return jdbctemplate.queryForList(sql.toString(),params);
		
		
	}
	
}
