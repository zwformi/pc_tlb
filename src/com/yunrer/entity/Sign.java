package com.yunrer.entity;

import java.util.Date;
/**
 * 
 * @author ZGf 
 * 
 * 签到表
 */
public class Sign {
	private Integer id;//签到记录id
	private Integer user_id;//用户id
	private Integer sign_count;//累计签到次数
	private Date last_modified_time;//最后修改时间
	private String sign_history;//签到历史
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getSign_count() {
		return sign_count;
	}
	public void setSign_count(Integer sign_count) {
		this.sign_count = sign_count;
	}
	public Date getLast_modified_time() {
		return last_modified_time;
	}
	public void setLast_modified_time(Date last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	public String getSign_history() {
		return sign_history;
	}
	public void setSign_history(String sign_history) {
		this.sign_history = sign_history;
	}

	
}
