package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.DmbDao;
import com.yunrer.entity.Dmb;

@Service("DmbService")
public class DmbService {
	
	@Resource
	private DmbDao dmbDao;

	/**
	 * 根据代码表类型查询代码表列表
	 */
	public void queryDmbList(HttpServletRequest request) {
		List<Dmb> list = dmbDao.queryDmbList(1);
		request.setAttribute("list_hysx", list);
		
		List<Dmb> list2 = dmbDao.queryDmbList(2);
		request.setAttribute("list_ssbm", list2);
	}
	
}
