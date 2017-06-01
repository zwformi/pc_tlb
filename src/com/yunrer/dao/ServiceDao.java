package com.yunrer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserService;
import com.yunrer.util.FormatTime;

@Repository("ServiceDao")
public class ServiceDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	
	/**
	 * 查询最新一条服务单(F_开头)
	 */
	public UserService queryLastService() {
		StringBuffer sql = new StringBuffer("select * from tbl_service where  (add_time between ? and ?) and service_number like 'F_%' order by service_number desc,add_time desc limit 1");
		Object[] params = new Object[] { FormatTime.getFormartDate()+" 00:00:00",FormatTime.getFormartDate()+" 23:59:59"};
		List<UserService> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserService>(UserService.class));
		if(list!=null && list.size()>0)
		return list.get(0);
		else return null;
	}
	
	/**
	 * 查询用户的服务单
	 */
	public List<UserService> queryService(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_service where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserService> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserService>(UserService.class));
		return list;
	}
	
	/**
	 * 查询用户的服务单详情
	 */
	public UserService queryServiceDetail(String service_number) {
		StringBuffer sql = new StringBuffer("select * from tbl_service where service_number = ?");
		Object[] params = new Object[] {service_number};
		List<UserService> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserService>(UserService.class));
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 新增用户服务单
	 */
	public int addService(UserService service) {
		StringBuffer sql = new StringBuffer("insert into tbl_service(service_number,order_number,order_detail_id,user_id,lxr,phone,content,add_time,address,owning_company) values(?,?,?,?,?,?,?,?,?,?)");
		Object[] params = new Object[] {service.getService_number(),service.getOrder_number(),service.getOrder_detail_id(),service.getUser_id(),service.getLxr(),service.getPhone(),service.getContent(),service.getAdd_time(),service.getAddress(),service.getOwning_company()};
		return jdbcTemplate.update(sql.toString(),params);
	}
	
	/**
	 * 查询用户服务单数量
	 * @param user_id
	 * @return
	 */
	public int getServiceCount(int user_id,int owning_company){
		StringBuffer sql = new StringBuffer("select count(1) from tbl_service where user_id = ? or (owning_company = ? and is_public = 1)");
		Object[] params = new Object[] { user_id ,owning_company};
		return jdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
		
	}
	/**
	 * 查询用户的服务单
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserService> queryPagingServiceList(int user_id,int owning_company,int size,int page) {
		int num = size*page;
		StringBuffer sql = new StringBuffer("select * from tbl_service where (user_id = ? or (owning_company = ? and is_public = 1)) order by add_time desc,service_number desc limit ?,?");
		Object[] params = new Object[] { user_id,owning_company,num,size };
		List<UserService> list = jdbcTemplate.query(sql.toString(), params,
				new RowMapper() {
			public Object mapRow(ResultSet rs, int paramInt)
					throws SQLException {
				UserService service = new UserService();
				service.setAdd_time(rs.getDate("add_time"));
				service.setAddress(rs.getString("address"));
				service.setComment(rs.getString("comment")==null?"未填写":rs.getString("comment"));
				service.setContent(rs.getString("content"));
				service.setLxr(rs.getString("lxr"));
				if(rs.getObject("order_detail_id")!=null)
				service.setOrder_detail_id(rs.getInt("order_detail_id"));
				service.setOrder_number(rs.getString("order_number"));
				service.setPhone(rs.getString("phone"));
				service.setService_number(rs.getString("service_number"));
				if(rs.getObject("state")!=null)
				service.setState(rs.getInt("state"));
				if(rs.getObject("user_id")!=null)
				service.setUser_id(rs.getInt("user_id"));
				return service;
					
			}
		});
		return list;
	}
	
}