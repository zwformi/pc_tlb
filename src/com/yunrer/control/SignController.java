package com.yunrer.control;


import java.util.ArrayList;
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











import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.dao.IntegrationDao;
import com.yunrer.entity.Integration;
import com.yunrer.entity.IntegrationDetail;
import com.yunrer.entity.Sign;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.IntegrationService;
import com.yunrer.service.SignService;
import com.yunrer.util.FormatTime;



/**
 * 
 * @author ZGF
 *
 */

@Controller
@RequestMapping("/sign")
public class SignController {

	@Resource
	private SignService signService; 
	@Resource
	private IntegrationService iService;
	
	@RequestMapping(value="/dosign",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String doSign(HttpServletRequest request,HttpSession session) {
			 Map<String,Object> resultMap = new HashMap<String,Object>();
			 UserInfo user = (UserInfo)session.getAttribute("loginUser");
			 resultMap = signService.doSign(user.getUser_id(),session);
			 int type = request.getParameter("type")==null?0:Integer.parseInt(request.getParameter("type"));
			 int integration_count =-1;
			 int operate_type =-1;
			 switch(type){
			 case 10:
				 integration_count = 5;
				 operate_type =1;
				 break;
			 }
			 int integration ;
			 if(resultMap.get("success")!=null||resultMap.get("warning")!=null){
				 if(resultMap.get("success")!=null){
					iService.addIntegrations(user.getUser_id(),integration_count);
					iService.addIntegrationDetails(user.getUser_id(),type, integration_count, operate_type);
				 }
				 integration = iService.getIntegtation(user.getUser_id()).getTotal_integration();
				 resultMap.put("integration", integration);
			 }

			 //暂时用一下，序列化成json字符串
			 return JSONUtils.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/docheck",produces="text/html;charset=UTF-8",method=RequestMethod.POST )
	@ResponseBody
	public String docheck(HttpServletRequest request,HttpSession session){
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		 UserInfo user = (UserInfo)session.getAttribute("loginUser");
		 Sign sign = signService.getSignbyDate(user.getUser_id(), FormatTime.getFormartDate());
         Integration integration = iService.getIntegtation(user.getUser_id());
		if(sign!=null )
			{	
				resultMap.put("complete", true);
				resultMap.put("message", "今天您已签到");
				resultMap.put("history", sign.getSign_history());
				resultMap.put("sign_count", sign.getSign_count());
				
			}else{
				Sign s = null;
				List<Sign> list= signService.getSignList(user.getUser_id(), 1,FormatTime.getStartDate(), FormatTime.getFormartDate());
				resultMap.put("warning", true);
				resultMap.put("message", "未签到");
				if(list.size()>0){
					s = list.get(0); 
					resultMap.put("history", s.getSign_history());
					resultMap.put("sign_count", s.getSign_count());
				}
					
		}
				
		if(integration!=null)
		resultMap.put("integration", integration.getTotal_integration());
		

		return JSONUtils.toJSONString(resultMap);
	}
	
	
}