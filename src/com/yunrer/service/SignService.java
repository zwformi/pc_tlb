package com.yunrer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.IntegrationDao;
import com.yunrer.dao.SignDao;
import com.yunrer.entity.Sign;
import com.yunrer.util.FormatTime;
import com.yunrer.util.MoveCharUtil;

@Service("SignService")
@Transactional
public class SignService {
	
	@Resource
	private SignDao sDao;
	public Sign getSignbyDate(int userid,String date){
		return sDao.querySignbyDate(userid, date);
	
	}
	
	public List<Sign> getSignList(int userid,int num, String start ,String end){
		if(start.length()<11)
			start+=" 00:00:00";
		if(end.length()<11)
			end+=" 23:59:59";
		return sDao.querySignInfo(userid, num, start, end);
	}
	public Map<String,Object> doSign(int userid,HttpSession session){
		List<Sign> list = new ArrayList<Sign>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Sign sign = new Sign();
		list =sDao.querySignInfo(userid, 1, FormatTime.getStartDate()+" 00:00:00", FormatTime.getFormartDate()+" 23:59:59");
		String lastDate = null;
		String currentDate = FormatTime.getFormartDate();
		if(list.size()>0){
			sign = list.get(0);
			lastDate = FormatTime.getFormartDate(sign.getLast_modified_time());
		}
		if(list.size()>0 && lastDate.equals(currentDate)){
			resultMap.put("warning",true);
			resultMap.put("message","今天您已进行了签到，同步数据成功" );
			resultMap.put("history", sign.getSign_history());
			resultMap.put("sign_count", sign.getSign_count());
			return resultMap;
		}else{
			if(list.size() == 0 || !lastDate.split("-")[1].equals(currentDate.split("-")[1])){
				//每月首次签到
				int missDays = FormatTime.getDays(currentDate, FormatTime.getStartDate())+1;
				String newSingHistory = MoveCharUtil.moveChars("", missDays);
				System.out.println(newSingHistory+"   "+missDays);
				if(sDao.insertSign(userid, newSingHistory)>0){
					session.setAttribute("signed", true);
					resultMap.put("success",true);
					resultMap.put("message","签到成功" );
					resultMap.put("history", newSingHistory);
					resultMap.put("sign_count", 1);
					return resultMap;
				}
				else{
					resultMap.put("error",true);
					resultMap.put("message","签到失败" );
					return resultMap;
				}
			}else{
				//非首签，判断是否连续签到
				int missDays = FormatTime.getDays(currentDate,lastDate);
				String newSingHistory = MoveCharUtil.moveChars(sign.getSign_history(), missDays);
			 	if(sDao.updateSign(userid, missDays>=1?false:true, newSingHistory)>0){
			 		sign = sDao.querySignInfo(userid, 1, null, null).get(0);
			 		session.setAttribute("signed", true);
			 		resultMap.put("success",true);
					resultMap.put("message","签到成功" );
					resultMap.put("history", newSingHistory);
					resultMap.put("sign_count",sign.getSign_count());
			 	}else{
			 		resultMap.put("error",true);
					resultMap.put("message","签到失败" );
			 	}
			 	return resultMap;
			}
		}
	}
	
	
}
