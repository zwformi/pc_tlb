package com.yunrer.dao;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.entity.SysStringMap;


@Repository("StringMapDao")
public class StringMapDao {

	@Resource 
	private JdbcTemplate template;
	
	public List<SysStringMap> getStringMap(String objecttypename , String filedname){
		 List<SysStringMap> list = new ArrayList<SysStringMap>();
		String sql ="select id,value,name,pinyin,objecttypename,filedname from sys_stringmap where ObjectTypeName = ? and FiledName = ? order by value,name";
		Object[] params = new Object[]{objecttypename,filedname};
		list = template.query(sql, params,new BeanPropertyRowMapper<SysStringMap>(SysStringMap.class));
		return list;
		
	}
	public SysStringMap getName(String objecttypename , String filedname,int value){
		List<SysStringMap> list = new ArrayList<SysStringMap>();
		String sql ="select id,value,name,pinyin,objecttypename,filedname from sys_stringmap where ObjectTypeName = ? and FiledName = ? and Value= ?";
		Object[] params = new Object[]{objecttypename,filedname,value};
		list = template.query(sql, params,new BeanPropertyRowMapper<SysStringMap>(SysStringMap.class));
		if(list!=null && list.size()>0)
			return list.get(0);
	 return null;
	}
	
}
