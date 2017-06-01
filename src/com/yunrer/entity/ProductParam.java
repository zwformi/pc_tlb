package com.yunrer.entity;


/**
 * 产品参数信息 实体类
 * @author wangpeng
 *
 */
public class ProductParam {

	private Integer id;
	private Integer product_id;//商品ID
	private String par_name;//参数名称
	private String par_value;//参数值
	private Integer sortid=0;//排序ID，（从大到小）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public String getPar_name() {
		return par_name;
	}
	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}
	public String getPar_value() {
		return par_value;
	}
	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
}