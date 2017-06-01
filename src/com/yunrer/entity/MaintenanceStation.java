package com.yunrer.entity;


/**
 * 维修站 实体类
 * @author wangpeng
 *
 */
public class MaintenanceStation {

	private Integer id;
	private String name;//维修站名称
	private String address;//维修站地址
	private String lxr;//维修站联系人
	private String phone;//维修站联系电话
	private Integer type_id;// 品牌ID
	private String type_name;// 品牌名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
}