package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.AdDao;
import com.yunrer.entity.Ad;

@Service("AdService")
public class AdService {

	@Resource
	private AdDao adDao;
	
	/**
	 * 查询广告图片列表
	 */
	public List<Ad> queryAdList(int typeid,int count) {
		List<Ad> list = null;
		try{
			list = adDao.queryAdList(typeid,count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}