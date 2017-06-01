package com.yunrer.entity;

public class SysStringMap {
	private Integer id;
	private String name;
	private String pinyin;
	private Integer value;
	private String objecttypename;
	private String filedname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	public Integer getvalue() {
		return value;
	}

	public void setvalue(Integer value) {
		this.value = value;
	}
	public String getobjecttypename() {
		return objecttypename;
	}

	public void setobjecttypename(String objecttypename) {
		this.objecttypename = objecttypename;
	}
	public String getfiledname() {
		return filedname;
	}

	public void setfiledname(String filedname) {
		this.filedname = filedname;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
