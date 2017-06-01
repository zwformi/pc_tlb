package com.yunrer.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderEvaluate;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.OrderSm;

@Repository("OrderDao")
public class OrderDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增数据--需求表
	 */
	public void addOrder_Xuqiu(OrderInfoXuqiu o){
		try{
			String sql = "insert into tbl_order_info_xuqiu (dz,dh,yb,xm,order_number,user_id,order_state,order_time,order_source,payment_money,ip,demand_file,demand_file2,demand_file3,title,content,owning_company) values (?,?,?,?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
			Object[] params = new Object[] {o.getDz(),o.getDh(),o.getYb(),o.getXm(), o.getOrder_number(), o.getUser_id(), o.getOrder_state(), o.getOrder_time(), o.getOrder_source(), o.getPayment_money(), o.getIp(),o.getDemand_file(),o.getDemand_file2(),o.getDemand_file3(),o.getTitle(),o.getContent(),o.getOwning_company() };
			jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增需求单主表出现错误！");
		}
	}
	
	/**
	 * 查询需求单列表
	 */
	public List<OrderInfoXuqiu> queryOrder_Xuqiu(int user_id) {
		String sql = "select t.*,p.product_name,p.type_str,p.product_imgurl,p.product_count,p.product_unit_price,p.product_unit_price_bj,p.pz "
				+ "from tbl_order_info_xuqiu t"
				+ " left join tbl_order_details_xuqiu p "
				+ "on t.order_number = p.order_number"
				+ " where user_id = ?   order by order_time desc";
		Object[] params = new Object[] { user_id};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		return list;
	}
	

	/**
	 * 查询需求单列表-根据需求状态查询
	 */
	public List<OrderInfoXuqiu> queryOrder_XuqiuBystate(int user_id,int state) {
		String sql = "select t.*,p.product_name,p.type_str,p.product_imgurl,p.product_count,p.product_unit_price,p.product_unit_price_bj,p.pz "
				+ "from tbl_order_info_xuqiu t"
				+ " left join tbl_order_details_xuqiu p "
				+ "on t.order_number = p.order_number"
				+ " where user_id = ?  and order_state = ? order by order_time desc";
		Object[] params = new Object[] { user_id,state};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		return list;
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
	 * 取消订单时返还数据
	 */
	public void returnData(final List<OrderDetails> orderDetailsList) {
		String sql = "update tbl_product_count set count=count+? where product_id=?";		
		try {
					BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int index)
								throws SQLException {
							OrderDetails od = orderDetailsList.get(index);
							if(od.getProduct_items_id()==0){
								
							}
							ps.setInt(1, od.getProduct_count());
							ps.setInt(2, od.getProduct_id());
						}
						public int getBatchSize() {
							return orderDetailsList.size();
						}
					};
					jdbcTemplate.batchUpdate(sql, setter);
				} catch (Exception ex) {
					System.err.println("---------------------------------------------");
					System.err.println(ex.getMessage());
					System.err.println("---------------------------------------------");
					throw new RuntimeException("取消订单返还数据错误！");
				}
			}
	
		
	/**
	 * 新增数据--需求单商品明细表
	 */
	public void addOrderDetail_Xuqiu(final List<OrderDetailsXuqiu> list){
		String sql = "insert into tbl_order_details_xuqiu (order_number,type_str,brand_str,xh,pz,product_id,product_name,product_imgurl,product_count,product_unit_price,create_time,product_items_id) values (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?)";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetailsXuqiu od = list.get(index);
					ps.setString(1, od.getOrder_number());
					
					ps.setString(2, od.getType_str());
					ps.setString(3, od.getBrand_str());
					ps.setString(4, od.getXh());
					ps.setString(5, od.getPz());
					
					ps.setInt(6, od.getProduct_id());
					ps.setString(7, od.getProduct_name());
					ps.setString(8, od.getProduct_imgurl());
					ps.setInt(9, od.getProduct_count());
					ps.setDouble(10, od.getProduct_unit_price());
					ps.setDate(11, new Date(od.getCreate_time().getTime()));
					ps.setInt(12,od.getproduct_items_id());
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
			throw new RuntimeException("新增需求单明细错误！");
		}
	}
	
	/**
	 * 新增数据--合同订单表
	 */
	public void addOrder(OrderInfo o){
		try{
			String sql = "insert into tbl_order_info (order_number,user_id,order_state,order_time,order_source,payment_money,ip,demand_file,shr_dz,shr_xm,shr_dh,shr_yb,fkfs,shipping_methods,installation_service,owning_company) values (?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?,?,?,?)";
			Object[] params = new Object[] { o.getOrder_number(), o.getUser_id(), o.getOrder_state(), o.getOrder_time(), o.getOrder_source(), o.getPayment_money(), o.getIp(),o.getDemand_file(),o.getShr_dz(),o.getShr_xm(),o.getShr_dh(),o.getShr_yb(),o.getFkfs(),o.getShipping_methods(),o.getInstallation_service(),o.getOwning_company() };
			jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单主表出现错误！");
		}
	}
		
	/**
	 * 新增数据--订单商品明细表
	 */
	public void addOrderDetail(final List<OrderDetails> list){
		String sql = "insert into tbl_order_details (order_number,type_str,brand_str,xh,pz,product_id,product_items_id,product_name,product_imgurl,product_count,product_unit_price,product_unit_price_bj,create_time) values (?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,?, now())";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetails od = list.get(index);
					ps.setString(1, od.getOrder_number());
					
					ps.setString(2, od.getType_str());
					ps.setString(3, od.getBrand_str());
					ps.setString(4, od.getXh());
					ps.setString(5, od.getPz());
					
					ps.setInt(6, od.getProduct_id());
					ps.setInt(7, od.getProduct_items_id());
					ps.setString(8, od.getProduct_name());
					ps.setString(9, od.getProduct_imgurl());
					ps.setInt(10, od.getProduct_count());
					ps.setDouble(11, od.getProduct_unit_price());
					ps.setDouble(12, od.getProduct_unit_price_bj());
					System.out.println("------------------"+od.getProduct_items_id());
//					ps.setDate(11, new Date(od.getCreate_time().getTime()));
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
			throw new RuntimeException("新增订单明细错误！");
		}
	}
	
	/**
	 * 查询订单合同列表
	 */
	public List<OrderInfo> queryOrder_List(int user_id) {
		String sql = "select om.*,od.product_name,od.type_str,od.product_imgurl,od.product_count,product_unit_price,product_unit_price_bj,od.pz" +
				" from tbl_order_info om" +
				" left JOIN" +
				"		 tbl_order_details od" +
				"		 on od.order_number=om.order_number  " +
				"where user_id = ?  " +
				"order by order_time desc,om.order_number";
		Object[] params = new Object[] { user_id};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	}
	
	/**
	 * 查询订单总价
	 */
	public double queryOrderSum(String order_number) {
		String sql = "select sum(product_count*product_unit_price) from tbl_order_details where order_number=?";
		Object[] params = new Object[] { order_number};
		Double sum = jdbcTemplate.queryForObject(sql, params, Double.class);
		
		return sum;
	}
	/**
	 * 查询订单合同列表
	 */
	public List<OrderInfo> queryOrderInfoList(int user_id) {
		String sql = "select * " +
				" from tbl_order_info " +
				"where user_id = ?  " +
				"order by order_time desc,order_number";
		Object[] params = new Object[] { user_id};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	}
	
	/**
	 * 查询订单合同列表---分页
	 */
	public List<OrderInfo> queryPagingOrderList(int user_id,int owning_company,int size,int page,int order_state) {
		int index = size*page;
		String sql = "select * " +
				" from tbl_order_info " +
				" where (user_id = ? or (owning_company = ? and is_public = 1))  " ;
		if(order_state!=-1){
			if(order_state==7)
				sql+=" and order_state in (71,72)";
			else
				sql+=" and order_state="+order_state;
		}
			sql+=" order by order_time desc,order_number limit ?,?";
		Object[] params = new Object[] { user_id,owning_company,index,size};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	}
	/**
	 * 已支付及配送的订单合同---分页
	 */
	public List<OrderInfo> queryPaidOrderList(int user_id,int owning_company,int size,int page) {
		int index = size*page;
		String sql = "select * " +
				" from tbl_order_info " +
				" where order_state not in(0,9) and  order_state is not null and  (user_id = ? or (owning_company = ? and is_public = 1)) " +
				" order by order_time desc,order_number limit ?,?";
		Object[] params = new Object[] { user_id,owning_company,index,size};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	}
	
	/**
	 * 查询需求单列表---分页
	 */
	public List<OrderInfoXuqiu> queryPagingOrderXuqiuList(int user_id,int owning_company,int size,int page,int order_state) {
		int index = size*page;
		String sql = "select * " +
				" from tbl_order_info_xuqiu " +
				" where (user_id = ? or (owning_company = ? and is_public = 1)) " ;
		if(order_state!=-1)
			sql+="and order_state="+order_state;
			sql+=" order by order_time desc,order_number limit ?,?";
		Object[] params = new Object[] { user_id,owning_company,index,size};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		return list;
	}
	
	/**
	 * 查询订单合同数量---分页
	 */
	public int queryPagingOrderCount(int user_id,int owning_company,int order_state) {
		String sql = "select count(1) " +
				" from tbl_order_info " +
				" where ( user_id = ? or (owning_company = ? and is_public = 1))" ;
		if(order_state!=-1){
			if(order_state==7)
				sql+=" and order_state in (71,72)";
			else
				sql+=" and order_state="+order_state;
		}
		Object[] params = new Object[] { user_id,owning_company};
		Integer num = jdbcTemplate.queryForObject(sql, params, Integer.class);
		return num;
	}
	/**
	 * 查询已支付订单合同数量---分页
	 */
	public int queryPaidOrderCount(int user_id,int owning_company) {
		String sql = "select count(1) " +
				" from tbl_order_info " +
				" where  order_state not in (0,9) and order_state is not null and (user_id = ? or (owning_company = ? and is_public = 1) )  " ;
		Object[] params = new Object[] { user_id,owning_company};
		Integer num = jdbcTemplate.queryForObject(sql, params, Integer.class);
		return num;
	}
	
	/**
	 * 查询需求单数量---分页
	 */
	public int queryPagingOrderXuqiuCount(int user_id,int owning_company,int order_state) {
		String sql = "select count(1) " +
				" from tbl_order_info_xuqiu " +
				" where (user_id = ? or (owning_company = ? and is_public = 1))  " ;
		if(order_state!=-1)
			sql+=" and order_state="+order_state;
		Object[] params = new Object[] { user_id,owning_company};
		Integer num = jdbcTemplate.queryForObject(sql, params, Integer.class);
		return num;
	}
	
	/**
	 * 查询订单合同列表--更具状态查询
	 */
	public List<OrderInfo> queryOrder_Liststate(int user_id,int state) {
		List<OrderInfo> list=null;
		String sql="";
		if(state == 7){
			sql = "select om.*, od.* from tbl_order_info om   left join tbl_order_details od  on od.order_number=om.order_number where user_id = ?  and (order_state = ? or order_state = ?) order by order_time desc";
			Object[] params = new Object[] { user_id,71,72};
			list = jdbcTemplate.query(sql, params,
					new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		}else{
			sql = "select om.*, od.*  from tbl_order_info om left join tbl_order_details od   on od.order_number=om.order_number where user_id = ?  and order_state = ? order by order_time desc";
			Object[] params = new Object[] { user_id,state};
			list = jdbcTemplate.query(sql, params,
					new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		}
		return list;
	}
	
	/**
	 * 相关统计
	 */
	public Map queryOrderCoutn_tj(int user_id,int owning_company) {
		//合同状态(4：待配货 5：待发货 6：待收货  7：待评价，8已完成)
		String sql = "select "+
					"(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1))  and order_state = 0) ht_0,"+
					"(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 1) ht_1,"+
					"(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 4) ht_4,"+
					 "(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 5) ht_5,"+
					 "(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 61) ht_61,"+
					 "(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 62) ht_62,"+
					 "(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and (order_state = 71 or order_state = 72)) ht_7,"+
					 "(select count(1) from tbl_order_info where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 8) ht_8,"+
					//需求状态(4：待报价  5：完成报价)
					"(select count(1) from tbl_order_info_xuqiu where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 4) xq_4,"+
					"(select count(1) from tbl_order_info_xuqiu where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 5) xq_5,"+
					"(select count(1) from tbl_order_info_xuqiu where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 6) xq_6,"+
					"(select count(1) from tbl_order_info_xuqiu where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 7) xq_7,"+
					"(select count(1) from tbl_order_info_xuqiu where (user_id = ? or (owning_company = ? and is_public = 1)) and order_state = 8) xq_8"+
					 "";
		Object[] params = new Object[] { user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company,user_id,owning_company};
		Map map = jdbcTemplate.queryForMap(sql, params);
		return map;
	}
	
	/**
	 * 查询订单合同详情
	 */
	public OrderInfo queryOrder_Detail(String order_number) {
		String sql = "select * from tbl_order_info where order_number = ?";
		Object[] params = new Object[] { order_number};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询订单合同商品清单列表
	 */
	public List<OrderDetails> queryOrderDetailList(String order_number) {
		String sql = "select * from tbl_order_details where order_number = ? order by id asc";
		Object[] params = new Object[] { order_number};
		List<OrderDetails> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetails>(OrderDetails.class));
		return list;
	}
	

	/**
	 * 查询订单合同单商品清单详情
	 */
	public OrderDetails queryOrderDetailDetail(String order_number,int order_detail_id) {
		String sql = "select * from tbl_order_details where order_number = ? and id = ?";
		Object[] params = new Object[] { order_number,order_detail_id};
		List<OrderDetails> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetails>(OrderDetails.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	/**
	 * 接受报价修改状态为已接受报价
	 * @param order_number 需求单号
	 * @return
	 */
	public int updateOrderXuqiu_jsbj(int id,int user_id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,user_id};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 7,khqr_time = now() where id = ? and user_id = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改需求状态出现错误-客户已确认报价状态！");
		}
		return count;
	}
	
	/**
	 * 关闭-需求单
	 * @param order_number 需求单号
	 * @return
	 */
	public int cancelOrderXuqiu(int id,int user_id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,user_id};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 6 where id = ? and user_id =?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 订单合同修改订单状态
	 * @param id
	 * @return
	 */
	public int qsOrder(int order_state,int  id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set order_state = ? where id = ? "); 
			Object[] params = new Object[] { order_state,id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}

	
	/**
	 * 修改需求单状态
	 * @param order_number
	 * @return
	 */
	public int modifyOrderXuqiu(int State , String order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = ? where order_number=?"); 
			Object[] params = new Object[] { State,order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	/**
	 * 修改合同状态
	 * @param order_number
	 * @return
	 */
	public int modifyOrder(int State , String order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set order_state = ? where order_number=?"); 
			Object[] params = new Object[] { State,order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 新增合同操作日志
	 */
	public int addOrderSm(OrderSm os){
		int count=0;
		try{
			String sql = "insert into tbl_order_sm (order_id,type,text_sm,add_time) values (?, ?, ?, now())";
			Object[] params = new Object[] {os.getOrder_id(),os.getType(),os.getText_sm()};
			count = jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单合同操作日志出现错误！");
		}
		return count;
	}
	
	/**
	 * 查询订单合同进度
	 */
	public List<OrderSm> queryOrderSm(int order_id) {
		String sql = "select * from tbl_order_sm where order_id = ? order by add_time desc";
		Object[] params = new Object[] { order_id};
		List<OrderSm> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderSm>(OrderSm.class));
		return list;
	}
	
	/**
	 * 新增评价
	 */
	public int addOrderEvaluate(OrderEvaluate oe){
		int count=0;
		try{
			String sql = "insert into tbl_order_evaluate (order_id,user_id,score,content,add_time) values (?, ?, ? ,? , now())";
			Object[] params = new Object[] {oe.getOrder_id(),oe.getUser_id(),oe.getScore(),oe.getContent() };
			count = jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增评价信息出现错误！");
		}
		return count;
	}
	
}