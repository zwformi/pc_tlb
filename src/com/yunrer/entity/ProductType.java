package com.yunrer.entity;


/**
 * 商品类别信息 实体类
 * @author wangpeng
 *
 */
public class ProductType {

	private Integer id;
	private String name;//类别名称
	private Integer parentid;//上级类别ID
	private String img_url;//类别图片
	private Integer sortId=1; //排序ID（从大到小）
	private Integer delete_flag=0;//删除标记
	private Integer sub_parentid;//二级类型ID
	private Integer product_level;//所属级别
	private String iconfont; //分类图标 
	
	public String getIconfont() {
		return iconfont;
	}
	public void setIconfont(String iconfont) {
		this.iconfont = iconfont;
	}
	

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
	public Integer getparentid() {
		return parentid;
	}
	public void setparentid(Integer parentid) {
		this.parentid = parentid;
	}
	
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Integer getSub_parentid() {
		return sub_parentid;
	}
	public void setSub_parentid(Integer sub_parentid) {
		this.sub_parentid = sub_parentid;
	}
	public Integer getProduct_level() {
		return product_level;
	}
	public void setProduct_level(Integer product_level) {
		this.product_level = product_level;
	}
	
	
}