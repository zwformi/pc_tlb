package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.StringMapDao;
import com.yunrer.entity.SysStringMap;

@Service("StringMapService")
public class StringMapService {

	@Resource
	private StringMapDao stringMapDao;
	
	public List<SysStringMap> getStringmap(HttpServletRequest request){
		String filedname = request.getParameter("filedname")==null?null:request.getParameter("filedname");
		String objecttypename = request.getParameter("objecttypename")==null?null:request.getParameter("objecttypename");
		if(filedname!=null && !filedname.equals("") && objecttypename!=null && !objecttypename.equals("")){
			return stringMapDao.getStringMap(objecttypename, filedname);
			
		}
		return null;
		
		
	}
}
