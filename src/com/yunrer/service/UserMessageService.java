package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.UserMessageDao;
import com.yunrer.entity.UserMessage;

@Service("UserMessageService")
public class UserMessageService {

	@Resource
	private UserMessageDao userMessageDao;
	
	/**
	 * 查询用户消息列表
	 */
	public List<UserMessage> queryUserMessageList(int user_id) {
		List<UserMessage> list = null;
		try {
			list = userMessageDao.queryUserMessageList(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询用户消息未读数量
	 */
	public int queryUserMessageCount(int user_id) {
		int count = 0;
		try {
			count = userMessageDao.queryUserMessageCount(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询用户消息实体
	 */
	public UserMessage queryUserMessage(HttpServletRequest request) {
		UserMessage um = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			um = userMessageDao.queryUserMessage(id);
			//更新消息状态
			userMessageDao.modifyUserMessage(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return um;
	}
}