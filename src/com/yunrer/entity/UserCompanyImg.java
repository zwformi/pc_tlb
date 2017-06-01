package com.yunrer.entity;

import java.util.Date;


/**
 * 用户公司资质图片信息 实体类
 * @author wangpeng
 *
 */
public class UserCompanyImg {

	private Integer id;     //注册ID
	private Integer user_id;//用户ID
	private String imgurl;//图片路径
	private Date create_time;//上传时间
	private Integer owning_company; //图片所属公司
	
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getowning_company() {
		return owning_company;
	}
	public void setowning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
}