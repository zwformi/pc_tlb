package com.yunrer.control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserMessage;
import com.yunrer.service.UserMessageService;

/**
 * 系统消息
 * @author zhanglei
 */
@Controller
@RequestMapping("/um")
public class UserMessageControl {

	@Resource
	private UserMessageService userMessageService;
	
	/**
	 * 查询用户消息列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String queryUserMessageList(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("loginUser") != null) {
			UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
			List<UserMessage> list = userMessageService.queryUserMessageList(loginUser.getUser_id());
			request.setAttribute("umList", list);
		}
		return "member_news";
	}
	
	/**
	 * 查询用户消息实体
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String queryUserMessage(HttpServletRequest request) {
		UserMessage um = userMessageService.queryUserMessage(request);
		request.setAttribute("um", um);
		List<String> list=new ArrayList<String>();
		String ht=um.getContent();
		Pattern p = Pattern.compile("[XH]{1}[_]{1}\\d{24}");
		 Matcher m = p.matcher(ht);
		 while(m.find()){
			 String str=m.group();
			 list.add(str);
		 }
		 if(list.size()==1){
			 ht=list.get(0);
			 request.setAttribute("ht", ht);
		 }
		 if(list.size()==2){
			 ht=list.get(1);
			 request.setAttribute("ht", ht);
		 }
		 if(um.getContent().contains("已报价")){
			 request.setAttribute("url", "/find/find_edit_demand.html?order_number="+ht);
		 }
		 if(um.getContent().contains("已发货")||um.getContent().contains("合同")){
			 request.setAttribute("url", "/find/find_contract_show.html?order_number="+ht);
		 }
		 
			
		
		
		return "member_news_show";
	}
}