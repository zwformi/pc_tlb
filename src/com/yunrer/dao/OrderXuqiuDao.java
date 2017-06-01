package com.yunrer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderInfoXuqiu;

@Repository("OrderXuqiuDao")
public class OrderXuqiuDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询需求数量
	 * @param begin_index 
	 * @param page_size
	 * @return
	 */
	public int queryOrderXuqiucount(String order_number,int order_source,int order_state) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_order_info_xuqiu where 1=1 ";
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and order_number like ? ";
			paramlist.add(order_number+"%");
		}
		if(order_source!=-1){
			sql += " and order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=0){
			sql += " and order_state = ? ";
			paramlist.add(order_state);
		}
		
		Object[] params = paramlist.toArray();
		int total_count = jdbcTemplate.queryForObject(sql, Integer.class,params);
		return total_count;
	}
	
	/**
	 * 查询需求列表
	 * @param begin_index 
	 * @param page_size
	 * @return
	 */
	public List<OrderInfoXuqiu> queryOrderXuqiuList(String order_number,int order_source,int order_state,int pageIndex,int pageSize) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select * from tbl_order_info_xuqiu where 1=1 ";
		int start = pageIndex * pageSize;
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and order_number like ? ";
			paramlist.add(order_number+"%");
		}
		if(order_source!=-1){
			sql += " and order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=0){
			sql += " and order_state = ? ";
			paramlist.add(order_state);
		}
		paramlist.add(start);
		paramlist.add(pageSize);
		Object[] params = paramlist.toArray();
		sql += " order by order_time desc limit ?,?";
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		return list;
	}
	
	
	/**
	 * 查询需求单详情
	 */
	public OrderInfoXuqiu queryOrder_Xuqiu_Detail(int id) {
		String sql = "select * from tbl_order_info_xuqiu where id = ?";
		Object[] params = new Object[] { id};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询需求单详情
	 */
	public OrderInfoXuqiu queryOrder_Xuqiu_Detail(String order_number) {
		String sql = "select * from tbl_order_info_xuqiu where order_number = ?";
		Object[] params = new Object[] { order_number};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	

	/**
	 * 查询需求单详情列表
	 */
	public List<OrderDetailsXuqiu> queryOrderDetail_Xuqiu(String order_number) {
		String sql = "select * from tbl_order_details_xuqiu where order_number = ? order by id asc";
		Object[] params = new Object[] { order_number};
		List<OrderDetailsXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetailsXuqiu>(OrderDetailsXuqiu.class));
		return list;
	}
	
	/**
	 * 新增数据--需求单商品明细表
	 * @param od 商品明细表
	 * @return 主键ID
	 */
	public int addOrderDetail_Xuqiu(final OrderDetailsXuqiu od) {
		int id =0;
		try {
			final String sql = "insert into tbl_order_details_xuqiu (order_number,type_str,brand_str,xh,pz,product_name,product_count,create_time) values (?, ?, ?, ?, ?, ?, ?, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
					ps.setString(1, od.getOrder_number());
					
					ps.setString(2, od.getType_str());
					ps.setString(3, od.getBrand_str());
					ps.setString(4, od.getXh());
					ps.setString(5, od.getPz());
					
					ps.setString(6, od.getProduct_name());
					ps.setInt(7, od.getProduct_count());
					ps.setDate(8, new Date(od.getCreate_time().getTime()));
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				id = keyHolder.getKey().intValue();
			}
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return id;
	}
	
	/**
	 * 删除需求详情商品
	 * @param order_number 需求单号
	 * @param id    参数ID
	 * @return
	 */
	public int delOrderXuqiu(String order_number,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,order_number};
			StringBuffer sql= new StringBuffer("delete from tbl_order_details_xuqiu where id = ? and order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 修改报价状态及报价总额、报价时间
	 * @param order_number 需求单号
	 * @param total 报价总额
	 * @return
	 */
	public int updateOrderXuqiu(String order_number,double total) {
		int count =0;
		try {
			Object[] params = new Object[] {total,order_number};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 5,payment_money = ?,bj_time = now() where order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改报价状态出现错误-已报价状态！");
		}
		return count;
	}
	
	/**
	 * 生成合同修改状态为已生成合同
	 * @param order_number 需求单号
	 * @return
	 */
	public int updateOrderXuqiu_ht(String order_number) {
		int count =0;
		try {
			Object[] params = new Object[] {order_number};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 8,htsc_time = now() where order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改需求状态出现错误-生成合同状态！");
		}
		return count;
	}
	
	
	/**
	 * 关闭-需求单
	 * @param order_number 需求单号
	 * @return
	 */
	public int cancelOrderXuqiu(int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 6 where id = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	
	/**
	 * 修改数据--需求报价
	 */
	public void orderXuqiu_Bj(final List<OrderDetailsXuqiu> list){
		String sql = "update tbl_order_details_xuqiu set product_unit_price_bj = ? where order_number = ? and id = ?";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetailsXuqiu od = list.get(index);
					ps.setDouble(1, od.getProduct_unit_price_bj());
					ps.setString(2, od.getOrder_number());
					ps.setInt(3, od.getId());
				}
				public int getBatchSize() {
					return list.size();
				}
			};
			jdbcTemplate.batchUpdate(sql, setter);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("需求报价出现错误！");
		}
	}
	
	/**
	 * 删除需求单主表
	 * @param id
	 * @return
	 */
	public int deleteOrderXuqiu(int id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_info_xuqiu where id = ?"); 
			Object[] params = new Object[] { id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除需求单主表出现错误！");
		}
		return count;
	}
	
	/**
	 * 删除需求单详情清单表
	 * @param id
	 * @return
	 */
	public int deleteOrderXuqiuDetail(String  order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_details_xuqiu where order_number = ?"); 
			Object[] params = new Object[] { order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除需求单中商品清单表出现错误！");
		}
		return count;
	}
}