package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;

/**
 * 用户基本信息 实体类
 * 
 * @author wangpeng
 * 
 */
public class UserInfo {

	private Integer user_id;
	private String openid;// OPENID，用户ID
	private String nick_name;// 昵称
	private String sex;// 性别
	private String xm;// 姓名
	private String password_str;// 密码
	private String e_mail;// 企业邮箱
	private String gsmc;// 公司名称
	private String hysx;// 行业属性
	private String ssbm;// 所属部门
	private String headimgurl;// 用户头像
	private String phone;// 手机号码（登陆使用）
	private  Integer status = 0;// 账户状态（0：待绑定微信，1：正常，2：已禁用）
	private Date firstAddTime = new Date(); // 首次注册时间
	private  Integer client_id;// 客服ID
	private  Integer sales_id;// 销售ID
	
	/***************整理数据新增stringmap字段****************************/
	private Integer gender; //性别 (1：男，2：女)
	private Integer owning_department; //所属部门
	private Integer owning_company; //所属公司
	private Integer is_manager; //是否管理员角色（1：是，0：否）
	private Integer is_apply; //是否已申请 （1：是，0：否）
	private String 	sso_id; //图灵云guid
	private String 	password_text; //password明文
	private Integer special_code;//特供产品用户标识（1：是，0：否）
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Integer getOwning_department() {
		return owning_department;
	}
	public void setOwning_department(Integer owning_department) {
		this.owning_department = owning_department;
	}
	
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getPassword_str() {
		return password_str;
	}

	public void setPassword_str(String password_str) {
		this.password_str = password_str;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getHysx() {
		return hysx;
	}

	public void setHysx(String hysx) {
		this.hysx = hysx;
	}

	public String getSsbm() {
		return ssbm;
	}

	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getHeadimgurl_page() {
		if (headimgurl == null || headimgurl.equals("")) {
			return "/images/default_img.png";
		} else {
			return headimgurl;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFirstAddTime() {
		return firstAddTime;
	}

	public void setFirstAddTime(Date firstAddTime) {
		this.firstAddTime = firstAddTime;
	}

	public String getFmt_create_time() {
		return DateUtils.date2str(firstAddTime);
	}
	
	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer clientId) {
		client_id = clientId;
	}
	
	public Integer getSales_id() {
		return sales_id;
	}

	public void setSales_id(Integer salesId) {
		sales_id = salesId;
	}
	public Integer getOwning_company() {
		return owning_company;
	}
	public void setOwning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
	public Integer getIs_manager() {
		return is_manager;
	}
	public void setIs_manager(Integer is_manager) {
		this.is_manager = is_manager;
	}
	public Integer getIs_apply() {
		return is_apply;
	}
	public void setIs_apply(Integer is_apply) {
		this.is_apply = is_apply;
	}
	public String getSso_id() {
		return sso_id;
	}
	public void setSso_id(String sso_id) {
		this.sso_id = sso_id;
	}
	public String getPassword_text() {
		return password_text;
	}
	public void setPassword_text(String password_text) {
		this.password_text = password_text;
	}
	public Integer getSpecial_code() {
		return special_code;
	}
	public void setSpecial_code(Integer special_code) {
		this.special_code = special_code;
	}
}