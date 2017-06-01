package com.yunrer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.MaintenanceStation;

@Repository("MaintenanceStationDao")
public class MaintenanceStationDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据维修站
	 */
	public List<MaintenanceStation> queryMaintenanceStationList(String keyword) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select tms.*,td.name as type_name from tbl_maintenance_station tms, tbl_dmb td where tms.type_id = td.id ";
		if(!keyword.equals("")){
			sql += " and (tms.name like ? or tms.address like ? or tms.lxr like ? or tms.phone like ? ) ";
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
		}
		sql +=" order by tms.sort_id desc,tms.id desc";
		Object[] params = paramlist.toArray();
		List<MaintenanceStation> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<MaintenanceStation>(MaintenanceStation.class));
		return list;
	}
	
	
}