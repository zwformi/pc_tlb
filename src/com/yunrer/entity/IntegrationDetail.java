package com.yunrer.entity;

import java.util.Date;

public class IntegrationDetail {
	private Integer id;//id
	private Integer integration_id;//积分id
	private Integer user_id;//用户id
	private Integer type;//积分类型
	private Integer integration_count;
	private Integer operate_flag;//积分操作标识
	private Date create_time;//创建时间
	private String typeName;//积分类型中文名
	private String operateName;//积分操作中文名
	
	
	
	public Integer getIntegration_count() {
		return integration_count;
	}
	public void setIntegration_count(Integer integration_count) {
		this.integration_count = integration_count;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntegration_id() {
		return integration_id;
	}
	public void setIntegration_id(Integer integration_id) {
		this.integration_id = integration_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOperate_flag() {
		return operate_flag;
	}
	public void setOperate_flag(Integer operate_flag) {
		this.operate_flag = operate_flag;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}


	
}
