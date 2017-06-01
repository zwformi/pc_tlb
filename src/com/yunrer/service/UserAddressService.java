package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yunrer.dao.UserAddressDao;
import com.yunrer.entity.UserAddress;
import com.yunrer.entity.UserInfo;

@Service("UserAddressService")
public class UserAddressService {
	
	@Resource
	private UserAddressDao userAddressDao;

	/**
	 * 获取用户的收货地址信息
	 */
	public void queryAddress(HttpSession session,HttpServletRequest request) {
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//查询收货地址信息
		UserAddress userAddress = userAddressDao.queryAddress(loginUser.getUser_id());
		request.setAttribute("USERADDRESS", userAddress);
	}
	
	/**
	 * 获取用户的收货地址信息列表
	 */
	public void queryAddressList(HttpSession session,HttpServletRequest request) {
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//查询收货地址信息
		List<UserAddress> userAddressList = userAddressDao.queryAddressList(loginUser.getUser_id());
		request.setAttribute("USERADDRESSLIST", userAddressList);
	}
	
	
	/**
	 * 保存收货地址信息
	 */
	public int saveAddress(HttpSession session,UserAddress userAddress){
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//判断是否存在收货信息
		//UserAddress userAddress_old = userAddressDao.queryAddress(loginUser.getUser_id());
		userAddress.setUser_id(loginUser.getUser_id());
		
		int count=0;
		if(userAddress.getIs_default()==1){
			userAddressDao.updateAddress_deault(userAddress);
		}
		if(userAddress.getId()==0){
			//新增收货地址信息
			count = userAddressDao.addAddress(userAddress);
		}else{
			//修改收货地址信息
			count = userAddressDao.updateAddress(userAddress);
		}
		return count;
	}
	
	/**
	 * 删除收货地址信息
	 */
	public int deleteAddress(HttpSession session,UserAddress userAddress){
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		userAddress.setUser_id(loginUser.getUser_id());
		int count=0;
		//删除收货地址信息
		count = userAddressDao.deleteAddress(userAddress);
		return count;
	}
}
