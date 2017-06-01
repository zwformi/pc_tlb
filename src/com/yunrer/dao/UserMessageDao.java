package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserMessage;

@Repository("UserMessageDao")
public class UserMessageDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询用户消息列表
	 */
	public List<UserMessage> queryUserMessageList(int user_id) {
		Object[] params = new Object[] { user_id };
		String sql = "select * from tbl_user_message where user_id = ? and date_sub(curdate(), INTERVAL 30 DAY) <= date(add_time) order by add_time desc";
		List<UserMessage> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<UserMessage>(UserMessage.class));
		return list;
	}
	
	/**
	 * 查询用户消息未读数量
	 */
	public int queryUserMessageCount(int user_id) {
		String sql = "select count(id) from tbl_user_message where user_id = ? and date_sub(curdate(), INTERVAL 30 DAY) <= date(add_time) and is_read = 0";
		Object[] params = new Object[] { user_id };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询用户消息实体
	 */
	public UserMessage queryUserMessage(int id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_message where id = ?");
		Object[] params = new Object[] { id };
		List<UserMessage> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserMessage>(UserMessage.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 更新用户消息状态
	 */
	public void modifyUserMessage(int id) {
		String sql = "update tbl_user_message set is_read = 1 where id = ?";
		Object[] params = new Object[] { id };
		jdbcTemplate.update(sql, params);
	}
}