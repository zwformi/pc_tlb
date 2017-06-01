package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 商品信息 实体类
 * @author wangpeng
 *
 */
public class Product {

	private Integer id;   				//主键ID
	private Integer product_items_id;
	private String type_ids;		//商品类型（以,分割）
	private Integer brand_id;			//品牌ID
	private String xh;              //型号
	private String pz;              //配置
	private String name;			//商品名称
	private String sub_title;		//商品副标题
	private String img_url;			//商品图片
	private Integer count;				//商品总库存
	
	private Integer overplus=0;   				//库存余量
	private Integer out_count=0;				//商品售出数量
	
	private double price_old;		//商品原价
	private double price_new;		//商品现价
	private String content;			//商品描述
	private Integer sortid;				//排序ID（从大到小）
	private Integer is_yj;				//是否允许议价（0不允许，1允许）
	private Integer is_cx;				//是否促销（0不是，1是）
	private Integer is_ms;				//是否秒杀（0不是，1是）
	private Date ms_begintime;		//秒杀开始时间
	private Integer is_wx;				//是否推荐到微信（0不推荐，1推荐）
	private Integer is_pc;              //PC是否可见（0不可见，1可见）
	private Integer is_red;				//是否热卖推荐（0不推荐，1推荐）
	private Integer delete_flag = 0;		//是否上架（0上架状态，1下架状态）
	private Integer special_code = 0;		//是否内购产品（1：是）
	public Integer getproduct_items_id() {
		return product_items_id;
	}
	public void setproduct_items_id(Integer product_items_id) {
		this.product_items_id = product_items_id;
	}
	public Integer getOverplus() {
		return this.count;
	}
	public Integer getOut_count() {
		return out_count;
	}
	public void setOut_count(Object out_count) {
		if(out_count==null||out_count.equals("")){
			this.out_count=0;
		}else{
			this.out_count = Integer.parseInt(out_count.toString());
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_ids() {
		return type_ids;
	}
	public void setType_ids(String type_ids) {
		this.type_ids = type_ids;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getImg_url() {
		if(img_url==null||img_url.equals("")){
			return "/images/no_pic.png";
		}else{
			return img_url;
		}
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getPrice_old() {
		return price_old;
	}
	public void setPrice_old(double price_old) {
		this.price_old = price_old;
	}
	public double getPrice_new() {
		return price_new;
	}
	public void setPrice_new(double price_new) {
		this.price_new = price_new;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	public Integer getIs_yj() {
		return is_yj;
	}
	public void setIs_yj(Integer is_yj) {
		this.is_yj = is_yj;
	}
	public Integer getIs_cx() {
		return is_cx;
	}
	public void setIs_cx(Integer is_cx) {
		this.is_cx = is_cx;
	}
	public Integer getIs_ms() {
		return is_ms;
	}
	public void setIs_ms(Integer is_ms) {
		this.is_ms = is_ms;
	}
	public boolean getFlag_begin() {
		try{
			return this.ms_begintime.getTime()<=new Date().getTime();
		}catch(Exception ex){
			return false;
		}
	}
	public long getLeftTime() {
		long lefttime = 0;
		try{
			lefttime = this.ms_begintime.getTime()-new Date().getTime();
		}catch(Exception ex){		}
		return lefttime;
	}
	public Date getMs_begintime() {
		return ms_begintime;
	}
	public void setMs_begintime(Date ms_begintime) {
		this.ms_begintime = ms_begintime;
	}
	public String getFmt_ms_begintime() {
		try{
			return DateUtils.date2str(ms_begintime);
		}catch(Exception ex){
			return "";
		}
	}
	
	public Integer getIs_wx() {
		return is_wx;
	}
	public void setIs_wx(Integer is_wx) {
		this.is_wx = is_wx;
	}
	
	public Integer getIs_pc() {
		return is_pc;
	}
	public void setIs_pc(Integer is_pc) {
		this.is_pc = is_pc;
	}
	public Integer getIs_red() {
		return is_red;
	}
	public void setIs_red(Integer is_red) {
		this.is_red = is_red;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPz() {
		return pz;
	}
	public void setPz(String pz) {
		this.pz = pz;
	}
		
	public String getRepcontent() {
		String content_="";
		if(null!=content){
			content_ = content.replace(" src=\"/", " src=\"http://img.tulingbuy.com/");
		}
		return content_;
	}
	public Integer getSpecial_code() {
		return special_code;
	}
	public void setSpecial_code(Integer special_code) {
		this.special_code = special_code;
	}
}