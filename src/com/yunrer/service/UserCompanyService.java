package com.yunrer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.UserCompanyDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserCompanyImg;
import com.yunrer.entity.UserInfo;

@Service("UserCompanyService")
public class UserCompanyService {
	
	@Resource
	private UserCompanyDao userCompanyDao;
	
	@Resource
	private UserInfoDao userInfoDao;

	/**
	 * 获取用户的公司信息
	 */
	public void queryCompany(HttpSession session,HttpServletRequest request) {
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//查询公司基本信息
		if(loginUser.getOwning_company()!=null && loginUser.getOwning_company()!=0){
			UserCompany userCompany = userCompanyDao.queryCompany(loginUser.getOwning_company());
			request.setAttribute("USERCOMPANY", userCompany);	
			//查询公司资质图片
			List<UserCompanyImg> CompanyImgList = userCompanyDao.queryCompanyImgList(loginUser.getOwning_company());
			request.setAttribute("COMPANYIMGLIST", CompanyImgList);
		}

		
	}
	
	/**
	 * 保存公司信息
	 */
	@Transactional
	public Map<String,Object> saveCompany(UserCompany userCompany){
     		Map<String,Object> map = new HashMap<String, Object>();
//		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//判断是否存在公司信息
		List<UserCompany> list = userCompanyDao.queryUserCompanyList("gsmc ='"+userCompany.getGsmc()+"'");
//		userCompany.setUser_id(loginUser.getUser_id());
		int id=0;
		if(list==null||list.size()==0){
				//新增公司信息
				id = userCompanyDao.addCompany(userCompany);
//			
//				/***更新用户登录信息****/
//				userInfoDao.updateInfo("owning_company", (id+""), loginUser.getUser_id());
//				userInfoDao.updateInfo("is_manager", (1+""), loginUser.getUser_id());
//				loginUser.setOwning_company(id);
//				loginUser.setIs_manager(1);
				map.put("count", 1);
				map.put("id", id);
		}else{
			map.put("error", true);
			map.put("message", "公司名称已存在..");
		}
//		
		return map;
	}
	
	/**
	 * 按条件查询公司信息
	 * @param request
	 * @return
	 */
	public List<UserCompany>   queryCompany(HttpServletRequest request){
		String queryStr = request.getParameter("params");
		List<UserCompany>  list = userCompanyDao.queryUserCompanyList(queryStr);
		return list;
	}
	
	/**
	 * 新增公司资质图片
	 */
	public int addCompanyImg(HttpSession session,HttpServletRequest request) {

		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		UserCompanyImg companyImg = new UserCompanyImg();
		companyImg.setCreate_time(new Date());
		if(loginUser!=null)
			companyImg.setowning_company(loginUser.getOwning_company());
		else
			companyImg.setowning_company(Integer.parseInt(request.getParameter("owning_company")==null?"0":request.getParameter("owning_company")));
		String imgurl = request.getAttribute("img_url").toString();
		companyImg.setImgurl(imgurl);
		
		
		
		return userCompanyDao.addCompanyImg(companyImg);
	}
	
	/**
	 * 删除公司资质图片
	 */
	public int delCompanyImage(HttpSession session,HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
			count = userCompanyDao.delCompanyImg(loginUser.getUser_id(),id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	/**
	 * 根据公司名称查询相关信息
	 * @param gsmc
	 * @return
	 */
	public UserCompany getCompanyByName(String gsmc){
		
		return userCompanyDao.getCompanyByName(gsmc);
	}
}
