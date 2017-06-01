package com.yunrer.entity;

public class StaticResource {
	private Integer id;
    private String  skey;
    private String  svalue;
    private String  path;
    private String  deletion_code;
    private String  creat_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getSvalue() {
		return svalue;
	}
	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDeletion_code() {
		return deletion_code;
	}
	public void setDeletion_code(String deletion_code) {
		this.deletion_code = deletion_code;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

}
