package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.IntegrationDao;
import com.yunrer.entity.Integration;
import com.yunrer.entity.IntegrationDetail;

@Service("IntegrationService")
@Transactional
public class IntegrationService {
 @Resource
 private IntegrationDao iDao;
 
 	public int addIntegrations(int userid,int num){
 		if(iDao.getIntegration(userid).size()>0){
 			return iDao.updateIntegration(userid, num);
 		}
 		else
 			return iDao.addIntegration(userid, num);
 		 
 	}
 	public int addIntegrationDetails(int userid,int type, int integration_count, int operate_flag){
 		int integration_id = getIntegtation(userid).getId();

 		return iDao.addDetail(integration_id, type, integration_count, operate_flag);
 	}
 	
 	public List<IntegrationDetail> getIntegrationDetails(int userid,String type,String start,String end,String num){
 		int integration_id = getIntegtation(userid).getId();
 		return iDao.getDetails(integration_id, type, start, end, num);
 	}
 	
 	public Integration getIntegtation(int userid){
 		List<Integration> list  =iDao.getIntegration(userid);
 		Integration ig = null;
 		if(list.size()>0)
 		 ig =  list.get(0);
 		return ig;
 	}
	
}
