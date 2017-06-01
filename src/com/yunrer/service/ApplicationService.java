package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.ApplicationDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.Application;
import com.yunrer.entity.UserInfo;

@Service("ApplicationService")
public class ApplicationService {

	@Resource
	private ApplicationDao appDao;
	@Resource
	private UserInfoDao userDao;
	
	/**
	 * 添加/修改申请记录
	 * @param request
	 * @param a
	 * @param session
	 * @return
	 */
	@SuppressWarnings("finally")
	public Map<String,Object> doApplication(HttpServletRequest request,HttpSession session,Application a){
		int num=0;
		UserInfo uinfo = (UserInfo) session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(a.getId()==null || a.getId()==0){
				num =  appDao.addApplication(a);
				if(num>0)
					uinfo.setIs_apply(1);
				
			}
			else
				num = appDao.modifyApplication(a);
			
			
		}catch(Exception e){
			
			System.err.println(">>>>>>>>>>>>>>>>>>>>>");
			System.err.println(e.getStackTrace());
			System.err.println(">>>>>>>>>>>>>>>>>>>>>");
		}finally{
			if(num==0){

				map.put("rescode", 0);
				map.put("resMsg", "未找到记录！");
				
			}else{
				map.put("rescode", 1);
				map.put("resMsg", "操作成功！");
				
			}
				
			return map;
		}
		
	}
	
	/**
	 * 根据公司id获取公司的所有采购员信息和申请人员的信息
	 * @param owning_company
	 * @return
	 */
	@Transactional
	public Map<String,Object> getapplications(Integer owning_company){
		List<?> userlist = null;
		List<?> applist = null;
		Map<String,Object> map = new HashMap<String, Object>();
		if(owning_company!=null){
			applist = appDao.getApplications(owning_company);
			userlist = userDao.queryUserListById(owning_company);
			
		}
			map.put("rescode", 1);
			map.put("applist", applist);
			map.put("userlist", userlist);

		return map;
	}
}
