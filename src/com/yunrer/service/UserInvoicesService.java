package com.yunrer.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yunrer.dao.UserInvoicesDao;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserInvoices;

@Service("UserInvoicesService")
public class UserInvoicesService {
	
	@Resource
	private UserInvoicesDao userInvoicesDao;

	/**
	 * 获取用户的发票信息
	 */
	public void queryInvoices(HttpSession session,HttpServletRequest request) {
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//查询发票信息
		UserInvoices userInvoices = userInvoicesDao.queryInvoices(loginUser.getUser_id());
		request.setAttribute("USERINVOICES", userInvoices);
	}
	
	/**
	 * 保存发票信息
	 */
	public int saveInvoices(HttpSession session,UserInvoices userInvoices){
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//判断是否存在发票信息
		UserInvoices userInvoices_old = userInvoicesDao.queryInvoices(loginUser.getUser_id());
		userInvoices.setUser_id(loginUser.getUser_id());
		int count=0;
		if(userInvoices_old==null){
			//新增发票信息
			count = userInvoicesDao.addInvoices(userInvoices);
		}else{
			userInvoices.setId(userInvoices_old.getId());
			//修改发票信息
			count = userInvoicesDao.updateInvoices(userInvoices);
		}
		return count;
	}
}
