package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 订单信息 实体类
 * @author wangpeng
 *
 */
public class OrderInfo {

	private Integer id;//合同ID
	private String order_number;    //合同号
	private Integer user_id;//用户ID
	//合同状态(4：待配货 5：待发货 6：待收货  7：待评价，8已完成)---------------原始
	//合同状态(4：配货中 5：已发货，待签收 61：已签收，待实施  62：实施中  71：已签收，待评价   72：已实施、待评价，8已完成)------------新的
	private Integer order_state;
	private Date order_time; //合同生成时间 生效时间
	private Integer order_source;//合同来源（0：采购，1：秒杀,2:立即购买）
	private double payment_money;//合同标准总金额
	private String ip;//合同生成ip
	private String demand_file;//合同附件
	private String shr_dz;//收货人地址
	private String shr_xm;//收货人姓名
	private String shr_dh;//收货人电话
	private String shr_yb;//收货人邮编
	private String fkfs;//付款方式
	private String xuqiu_ordernumber;//需求编号
	private String ms_xm;//秒杀人姓名
	private String ms_phone;//秒杀电话号码
	private String yskzt;//应收款状态
	private String product_imgurl;
	private String product_count;
	private String product_name;
	private String pz;
	private Integer shipping_methods;
	private Integer installation_service;
	private Integer post_company;
	private String post_number;
	private String erp_number; //ERP单号
	private Integer owning_company; //订单所属公司
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Integer getOrder_source() {
		return order_source;
	}
	public void setOrder_source(Integer order_source) {
		this.order_source = order_source;
	}
	public double getPayment_money() {
		return payment_money;
	}
	public void setPayment_money(double payment_money) {
		this.payment_money = payment_money;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDemand_file() {
		return demand_file;
	}
	public void setDemand_file(String demand_file) {
		this.demand_file = demand_file;
	}
	public String getShr_dz() {
		return shr_dz;
	}
	public void setShr_dz(String shr_dz) {
		this.shr_dz = shr_dz;
	}
	public String getShr_xm() {
		return shr_xm;
	}
	public void setShr_xm(String shr_xm) {
		this.shr_xm = shr_xm;
	}
	public String getShr_dh() {
		return shr_dh;
	}
	public void setShr_dh(String shr_dh) {
		this.shr_dh = shr_dh;
	}
	public String getShr_yb() {
		return shr_yb;
	}
	public void setShr_yb(String shr_yb) {
		this.shr_yb = shr_yb;
	}
	public String getFkfs() {
		return fkfs;
	}
	public void setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}
	public String getXuqiu_ordernumber() {
		return xuqiu_ordernumber;
	}
	public void setXuqiu_ordernumber(String xuqiu_ordernumber) {
		this.xuqiu_ordernumber = xuqiu_ordernumber;
	}
	public String getMs_xm() {
		return ms_xm;
	}
	public void setMs_xm(String ms_xm) {
		this.ms_xm = ms_xm;
	}
	public String getMs_phone() {
		return ms_phone;
	}
	public void setMs_phone(String ms_phone) {
		this.ms_phone = ms_phone;
	}
	public String getYskzt() {
		return yskzt;
	}
	public void setYskzt(String yskzt) {
		this.yskzt = yskzt;
	}
	public String getProduct_imgurl() {
		return product_imgurl;
	}
	public void setProduct_imgurl(String product_imgurl) {
		this.product_imgurl = product_imgurl;
	}
	public String getProduct_count() {
		return product_count;
	}
	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getPz() {
		return pz;
	}
	public void setPz(String pz) {
		this.pz = pz;
	}
	public Integer getShipping_methods() {
		return shipping_methods;
	}
	public void setShipping_methods(Integer shipping_methods) {
		this.shipping_methods = shipping_methods;
	}
	public Integer getInstallation_service() {
		return installation_service;
	}
	public void setInstallation_service(Integer installation_service) {
		this.installation_service = installation_service;
	}
	public String getFmt_order_time() {
		if(order_time!=null){
			return DateUtils.date2str(order_time);
		}else{return "暂无";}
	}
	public Integer getPost_company() {
		return post_company;
	}
	public void setPost_company(Integer post_company) {
		this.post_company = post_company;
	}
	public String getPost_number() {
		return post_number;
	}
	public void setPost_number(String post_number) {
		this.post_number = post_number;
	}
	public String getErp_number(){
		return erp_number;	
	}
	public void setErp_number(String erp_number){
		this.erp_number = erp_number;
	}
	public Integer getOwning_company() {
		return owning_company;
	}
	public void setOwning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
		
}