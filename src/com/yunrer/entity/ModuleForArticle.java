package com.yunrer.entity;

public class ModuleForArticle {
	private Integer id;
	private String module_name;
	private String creat_time;
	private String module_url;
	private Integer belongto_tf;
	private Integer deletion_code;
	private Integer sortid;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}
	public String getModule_url() {
		return module_url;
	}
	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}
	public Integer getBelongto_tf() {
		return belongto_tf;
	}
	public void setBelongto_tf(Integer belongto_tf) {
		this.belongto_tf = belongto_tf;
	}
	public Integer getDeletion_code() {
		return deletion_code;
	}
	public void setDeletion_code(Integer deletion_code) {
		this.deletion_code = deletion_code;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	
	
}
