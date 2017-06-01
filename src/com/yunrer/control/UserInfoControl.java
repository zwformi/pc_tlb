package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.CommonMd5;
import com.yunrer.entity.Ad;
import com.yunrer.entity.Application;
import com.yunrer.entity.UserAddress;
import com.yunrer.entity.UserClient;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserInvoices;
import com.yunrer.service.AdService;
import com.yunrer.service.ApplicationService;
import com.yunrer.service.DmbService;
import com.yunrer.service.ShoopingCartService;
import com.yunrer.service.UserAddressService;
import com.yunrer.service.UserCompanyService;
import com.yunrer.service.UserInfoService;
import com.yunrer.service.UserInvoicesService;
import com.yunrer.service.UserMessageService;

/**
 * 会员中心处理器
 * @author wangpeng
 */
@Controller
@RequestMapping("/user")
public class UserInfoControl {

	@Resource
	private DmbService dmbService;
	@Resource
	private UserInfoService userinfoService;
	@Resource
	private UserCompanyService userCompanyService;
	@Resource
	private UserAddressService userAddressService;
	@Resource
	private UserInvoicesService userInvoicesService;
	@Resource
	private ShoopingCartService shoopingCartService;
	@Resource
	private AdService adService;
	@Resource
	private UserMessageService userMessageService;
	@Resource
	private ApplicationService appService;
	/**
	 * 跳转到账户信息-账户资料页
	 * @return
	 */
	@RequestMapping(value = "/member_info",method = RequestMethod.GET)
	public String toUserInfo(HttpServletRequest request) {
		//查询代码表
		dmbService.queryDmbList(request);
		return "member_info";
	}
	/**
	 * 跳转到联系人组
	 * @return
	 */
	@RequestMapping(value = "/member_info_contact",method = RequestMethod.GET)
	public String toContact(HttpServletRequest request) {
		return "member_info_contact";
	}
	
	/**
	 * 跳转到-右侧信息页
	 * @return
	 */
	@RequestMapping(value = "/right_info",method = RequestMethod.GET)
	public String toRightInfo(HttpServletRequest request,HttpSession session) {
		//查询未读消息数量
		if (session.getAttribute("loginUser") != null) {
			UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
			int count = userMessageService.queryUserMessageCount(loginUser.getUser_id());
			request.setAttribute("umCount", count);
		}
		//查询采购车数量
		shoopingCartService.queryCartCount(request, session);
		//查询专属客服
		UserInfo userinfo = (UserInfo)session.getAttribute("loginUser");
		if(userinfo.getClient_id()!=0){//如果设置了专属客服，及客服ID不等于0就查询专属客服信息
			UserClient userClient = userinfoService.queryUserClient(userinfo.getClient_id(), session);
			request.setAttribute("USERCLIENT", userClient);
		}
		//查询广告
		int typeid = 3;
		int count = 3;
		List<Ad> list = adService.queryAdList(typeid,count);
		request.setAttribute("ADIMGS", list);
		return "right_info";
	}
	
	/**
	 * 查询购物车商品数量-右侧信息页
	 * @return
	 */
	@RequestMapping(value = "/getCartCount",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCartCount(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//查询采购车数量
				int count = shoopingCartService.getCartCount(request, session);
				resultMap.put("count", count);
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "查询购物车出现错误...");
		}
		return resultMap;
	}
	
	
	
	
	/**
	 * 修改用户基本信息
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/updateinfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doupdateinfo(HttpSession session,HttpServletRequest request,UserInfo userinfo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
				userinfo.setUser_id(loginUser.getUser_id());
				userinfo.setSso_id(loginUser.getSso_id());
				//修改用户信息
				int count = userinfoService.updateUserInfo(userinfo);
				if(count>0){//修改成功
					/***更新用户登录信息****/
					loginUser.setNick_name(userinfo.getNick_name());
					loginUser.setSex(userinfo.getSex());
					loginUser.setE_mail(userinfo.getE_mail());
					loginUser.setXm(userinfo.getXm());
					loginUser.setHysx(userinfo.getHysx());
					loginUser.setSsbm(userinfo.getSsbm());
					loginUser.setSex(userinfo.getSex());
					loginUser.setGender(userinfo.getGender());
					loginUser.setOwning_department(userinfo.getOwning_department());
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "修改用户信息出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 跳转到账户信息-图像照片页
	 * @return
	 */
	@RequestMapping(value = "/member_info_head",method = RequestMethod.GET)
	public String toUserInfoHead(HttpServletRequest request) {
		return "member_info_head";
	}
	
	/**
	 * 修改用户头像
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/updateimg",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doupdateimg(HttpSession session,HttpServletRequest request,UserInfo userinfo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
				//修改用户信息
				int count = userinfoService.updateImg(userinfo.getHeadimgurl(), loginUser.getUser_id());
				if(count>0){//修改成功
					/***更新用户登录信息****/
					loginUser.setHeadimgurl(userinfo.getHeadimgurl());
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "修改图像出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 跳转到账户信息-公司信息页
	 * @return
	 */
	@RequestMapping(value = "/member_info_company",method = RequestMethod.GET)
	public String toUserInfoCompany(HttpSession session,HttpServletRequest request) {
		userCompanyService.queryCompany(session,request);
		return "member_info_company";
	}
	
	/**
	 * 保存用户公司信息
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/savecompany",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dosaveCompany(HttpServletRequest request,UserCompany userCompany) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
//			if(session.getAttribute("loginUser")==null){
//				resultMap.put("error", true);
//				resultMap.put("message", "登录已失效，请重新登录...");
//			}else{
				resultMap =userCompanyService.saveCompany( userCompany);
//			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 删除公司资质图片信息
	 */
	@RequestMapping(value = "/deleteCompanyImage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delCompanyImage(HttpSession session,HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//删除图片
				int count = userCompanyService.delCompanyImage(session,request);
				if(count>0){//删除成功
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "删除资质图片出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 修改密码
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/changepsd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doUpdatePassword(HttpSession session,HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
				String oldpsd = request.getParameter("oldpsd");//老密码
				String newpsd = request.getParameter("newpsd");//新密码
				//判断老密码密码是否正确
				String db_password =loginUser.getPassword_str();
				CommonMd5 md5 = new CommonMd5();
				if(md5.deCodeStringBykey(db_password,"passwordkey").equals(oldpsd)){
					//执行修改密码
					String password = md5.enCodeStringByKey(newpsd,"passwordkey");
					String password_text = request.getParameter("password_text");//明文密码
					int count = userinfoService.updatePsd(password, password_text,loginUser);
					resultMap.put("count", count);
					if(count>0){
						loginUser.setPassword_str(password);
					}
				}else{//用户名或密码错误
					resultMap.put("error", true);
					resultMap.put("message", "原密码输入错误，无法修改密码！");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 跳转到账户信息-收货地址页
	 * @return
	 */
	@RequestMapping(value = "/member_address",method = RequestMethod.GET)
	public String toUserInfoAddress(HttpSession session,HttpServletRequest request) {
		//获取已保存的收货地址列表
		userAddressService.queryAddressList(session, request);
		return "member_address";
	}
	
	/**
	 * 保存用户收货地址信息
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/saveaddress",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dosaveAddress(HttpSession session,HttpServletRequest request,UserAddress userAddress) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//保存收货地址信息
				int count = userAddressService.saveAddress(session, userAddress);
				if(count>0){//保存成功
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "保存收货地址出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 删除用户收货地址信息
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/deleteaddress",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dodeleteAddress(HttpSession session,HttpServletRequest request,UserAddress userAddress) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//删除收货地址信息
				int count = userAddressService.deleteAddress(session, userAddress);
				if(count>0){//删除成功
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "删除收货地址出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 跳转到账户信息-发票信息页
	 * @return
	 */
	@RequestMapping(value = "/member_invoice",method = RequestMethod.GET)
	public String toUserInfoInvoice(HttpSession session,HttpServletRequest request) {
		//获取已保存的发票信息
		userInvoicesService.queryInvoices(session, request);
		//获取已保存的收货地址
		userAddressService.queryAddress(session, request);
		return "member_invoice";
	}
	
	/**
	 * 保存用户发票信息
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/saveinvoice",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dosaveInvoice(HttpSession session,HttpServletRequest request,UserInvoices userInvoices) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//保存发票信息
				int count = userInvoicesService.saveInvoices(session, userInvoices);
				if(count>0){//保存成功
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "保存发票信息出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 用户留言
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/savefeedback",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dosavefeedback(HttpSession session,HttpServletRequest request,UserInvoices userInvoices) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(session.getAttribute("loginUser")==null){
				resultMap.put("error", true);
				resultMap.put("message", "登录已失效，请重新登录...");
			}else{
				//保存用户留言信息
				int count = userinfoService.saveUserFeedback(session,request);
				if(count>0){//保存成功
					resultMap.put("count", count);
				}else {
					resultMap.put("error", true);
					resultMap.put("message", "提交留言出现未知错误");
				}
			}
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 查询公司列表信息
	 */
	@RequestMapping(value = "/querycompany",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryCompany(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			List<UserCompany> list = userCompanyService.queryCompany(request);
				if(list!=null && list.size()>0){//保存成功
					resultMap.put("rescode", 1);
					resultMap.put("list", list);
				}else {
					resultMap.put("rescode", 0);
					resultMap.put("resMsg", "公司不存在");
				}
			
		}catch(Exception e){
			resultMap.put("rescode", 0);
			resultMap.put("resMsg", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 添加/修改申请记录
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "application",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doApplication(HttpServletRequest request,HttpSession session,Application application) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
				
			resultMap =  appService.doApplication(request,session, application);
				
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作申请记录出错..");
		}finally{
			return resultMap;
			
		}
	
	}
	/**
	 * 根据公司id查询所属员工 和申请记录
	 * @param request
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/allusers",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getApplications(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			Integer owning_company = Integer.parseInt(request.getParameter("id"));
			map = appService.getapplications(owning_company);
			
		}catch(Exception e){
			
			map.put("rescode", 0);
			map.put("resMsg", "数据出现错误");
			
		}finally{
			return map;
		}
	}
	
	/**
	 * 修改用户的申请状态，并修改用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/acceptuser",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> acceptUser(HttpServletRequest request,HttpSession session,Application application){
		Map<String,Object> map = new HashMap<String, Object>();
     	map=userinfoService.acceptUser(request,session,application);
		return map;
	}
	
	/**
	 * 修改用户某一字段信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modifyuserinfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> modifyUserInfo(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		map = userinfoService.modifyUserInfo(request);
		return map;
	}
}