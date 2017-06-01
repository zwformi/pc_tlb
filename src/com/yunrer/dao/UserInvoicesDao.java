package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserInvoices;

@Repository("UserInvoicesDao")
public class UserInvoicesDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询发票信息实体
	 */
	public UserInvoices queryInvoices(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_invoices where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserInvoices> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserInvoices>(UserInvoices.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 新增发票信息
	 */
	public int addInvoices(UserInvoices userInvoices) {
		StringBuffer sql = new StringBuffer("insert into tbl_user_invoices(user_id,type,dwmc,nsrsbh,zcdz,zcdh,khh,yhdgzh) values(?,?,?,?,?,?,?,?)");
		Object[] params = new Object[] { userInvoices.getUser_id(),userInvoices.getType(),userInvoices.getDwmc(),userInvoices.getNsrsbh(),userInvoices.getZcdz(),userInvoices.getZcdh(),userInvoices.getKhh(),userInvoices.getYhdgzh()};
		return jdbcTemplate.update(sql.toString(),params);
	}
	
	/**
	 * 修改发票信息
	 */
	public int updateInvoices(UserInvoices userInvoices) {
		StringBuffer sql = new StringBuffer("update tbl_user_invoices set type=?,dwmc=?,nsrsbh=?,zcdz=?,zcdh=?,khh=?,yhdgzh=? where user_id = ? and id = ?");
		Object[] params = new Object[] { userInvoices.getType(),userInvoices.getDwmc(),userInvoices.getNsrsbh(),userInvoices.getZcdz(),userInvoices.getZcdh(),userInvoices.getKhh(),userInvoices.getYhdgzh(),userInvoices.getUser_id(),userInvoices.getId()};
		return jdbcTemplate.update(sql.toString(),params);
	}
}