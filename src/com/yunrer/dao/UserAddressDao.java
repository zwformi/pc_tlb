package com.yunrer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserAddress;

@Repository("UserAddressDao")
public class UserAddressDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询收货地址信息实体
	 */
	public UserAddress queryAddress(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_address where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserAddress> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserAddress>(UserAddress.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询收货地址信息列表
	 */
	public List<UserAddress> queryAddressList(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_address where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserAddress> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserAddress>(UserAddress.class));
		return list;
	}
	
	/**
	 * 新增收货地址信息
	 */
	public int addAddress(final UserAddress userAddress) {
		StringBuffer sql = new StringBuffer("insert into tbl_user_address(user_id,consignee_name,ssx,address,phone,post_code,is_default) values(?,?,?,?,?,?,?)");
		final String sql2 = sql.toString();
		//Object[] params = new Object[] { userAddress.getUser_id(),userAddress.getConsignee_name(),userAddress.getSsx(),userAddress.getAddress(),userAddress.getPhone(),userAddress.getPost_code(),userAddress.getIs_default()};
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		 int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql2, new String[]{"id"});//返回主键id   
	                ps.setInt(1,userAddress.getUser_id() );
	                ps.setString(2,userAddress.getConsignee_name() );
	                ps.setString(3,userAddress.getSsx() );
	                ps.setString(4,userAddress.getAddress() );
	                ps.setString(5,userAddress.getPhone() );
	                ps.setString(6,userAddress.getPost_code() );
	                ps.setInt(7,userAddress.getIs_default() );
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				return keyHolder.getKey().intValue();
			}else{
				return 0;
			}
		
	}
	
	/**
	 * 修改收货地址信息
	 */
	public int updateAddress(UserAddress userAddress) {
		StringBuffer sql = new StringBuffer("update tbl_user_address set consignee_name=?,ssx=?,address=?,phone=?,post_code=?,is_default=? where user_id = ? and id = ?");
		Object[] params = new Object[] { userAddress.getConsignee_name(),userAddress.getSsx(),userAddress.getAddress(),userAddress.getPhone(),userAddress.getPost_code(),userAddress.getIs_default(),userAddress.getUser_id(),userAddress.getId()};
		return jdbcTemplate.update(sql.toString(),params);
	}
	/**
	 * 修改默认收货地址,重置为不是默认收货地址
	 */
	public int updateAddress_deault(UserAddress userAddress) {
		StringBuffer sql = new StringBuffer("update tbl_user_address set is_default=0 where user_id = ?");
		Object[] params = new Object[] { userAddress.getUser_id()};
		return jdbcTemplate.update(sql.toString(),params);
	}
	
	/**
	 * 删除收货地址信息
	 */
	public int deleteAddress(UserAddress userAddress) {
		StringBuffer sql = new StringBuffer("delete from tbl_user_address where user_id = ? and id = ?");
		Object[] params = new Object[] {userAddress.getUser_id(),userAddress.getId()};
		return jdbcTemplate.update(sql.toString(),params);
	}
}