package com.yunrer.entity;



/**
 * 用户收货地址信息 实体类
 * @author wangpeng
 *
 */
public class UserAddress {

	private Integer id;   				//主键ID
	private Integer user_id;			//用户ID
	private String consignee_name;			//收件人
	private String ssx;//省市县
	private String address;//详细地址
	private String phone;//手机号码
	private String post_code;//邮政编码
	private Integer is_default;//是否默认地址   1默认  0不默认
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
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	
	public String getSsx() {
		return ssx;
	}
	public void setSsx(String ssx) {
		this.ssx = ssx;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public Integer getIs_default() {
		return is_default;
	}
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
		
}