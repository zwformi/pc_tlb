package com.yunrer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.yunrer.common.CommonMd5;
import com.yunrer.entity.UserClient;
import com.yunrer.entity.UserFeedback;
import com.yunrer.entity.UserInfo;

@Repository("UserInfoDao")
public class UserInfoDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据用户手机号查询信息
	 */
	public UserInfo queryUserInfo(UserInfo userinfo) {
		String sql = "select u.*,"
				+ " case when a.user_id is not null then 1 end is_apply "
				+ " from tbl_user_info u "
				+ " left join tbl_application a on u.user_id = a.user_id and a.status=10 "
				+ " where u.phone = ? and u.status != 2 limit 0,1 ";
		Object[] params = new Object[] { userinfo.getPhone()};
		List<UserInfo> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
	/**
	 * 根据sso_id查询信息
	 */
	public UserInfo queryUserBySsoId(UserInfo userinfo) {
		String sql = "select u.*,"
				+ " case when a.user_id is not null then 1 end is_apply "
				+ " from tbl_user_info u "
				+ " left join tbl_application a on u.user_id = a.user_id and a.status=10 "
				+ " where u.sso_id = ? and u.status != 2 limit 0,1 ";
		Object[] params = new Object[] { userinfo.getSso_id()};
		List<UserInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询用户专属客服信息
	 */
	public UserClient queryUserClient(int client_id) {
		String sql = "select * from tbl_user_client where id = ? limit 0,1";
		Object[] params = new Object[] { client_id};
		List<UserClient> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<UserClient>(UserClient.class));
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询手机号码是否已注册
	 */
	public boolean isExtis(String phone) {
		String sql = "select * from tbl_user_info where phone = ? ";
		Object[] params = new Object[] { phone };
		List list = jdbcTemplate.queryForList(sql, params);
		if (list != null && list.size() > 0) {
			return true; 
		} else {
			return false;
		}
	}
	
	/**
	 * 新增用户信息
	 */
	public int addUserInfo(final UserInfo userinfo){
		CommonMd5 md5=new CommonMd5();
		//对密码加密
		final String password = md5.enCodeStringByKey(userinfo.getPassword_str(),"passwordkey");
		final String sql = "insert into tbl_user_info (nick_name,sex,xm,password_str,e_mail,gsmc,hysx,ssbm,headimgurl,phone,status,firstAddTime,gender,owning_department,owning_company,sso_id) values ( ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 KeyHolder keyHolder = new GeneratedKeyHolder();  
		    jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
		                ps.setString(1, userinfo.getNick_name());
	       				ps.setString(2, userinfo.getSex());
	       				ps.setString(3, userinfo.getXm());
	       				ps.setString(4, password);
	       				ps.setString(5, userinfo.getE_mail());
	       				ps.setString(6, userinfo.getGsmc());
	       				ps.setString(7, userinfo.getHysx());
	       				ps.setString(8, userinfo.getSsbm());
	       				ps.setString(9, userinfo.getHeadimgurl());
	       				ps.setString(10, userinfo.getPhone());
	       				ps.setInt(11, userinfo.getStatus()==null?0:userinfo.getStatus());
	       				ps.setDate(12, new java.sql.Date(userinfo.getFirstAddTime().getTime()));
	       				ps.setInt(13, userinfo.getGender()==null?0:userinfo.getGender());
	       				ps.setInt(14, userinfo.getOwning_department()==null?0:userinfo.getOwning_department());
	       				ps.setInt(15, userinfo.getOwning_company()==null?0:userinfo.getOwning_company());
	       				ps.setString(16, userinfo.getSso_id());
                       return ps;
		        }  
		    }, keyHolder);  
		    int generatedId =0;
		    System.out.println(keyHolder.getKey().intValue());
		    generatedId = keyHolder.getKey().intValue();  

		    return generatedId;  
	}
	
	/**
	 * 修改用户字段信息
	 */
	public int updateInfo(String parmname,String value,int user_id) {
//		String sql = "update tbl_user_info set "+parmname+" = '"+value+"' where user_id = "+user_id;
		String sql = "update tbl_user_info set " + parmname + " = ? where user_id = ? ";
		Object[] params = new Object[]{value,user_id};
		int count = jdbcTemplate.update(sql,params);
		return count;
	}
	
	/**
	 * 修改用户基本信息
	 */
	public int updateUserInfo(UserInfo userinfo) {
		Object[] params = new Object[] {
				userinfo.getNick_name(),
				userinfo.getSex(),
				userinfo.getXm(),
				userinfo.getE_mail(),
				userinfo.getHysx(),
				userinfo.getSsbm(),
				userinfo.getGender(),
				userinfo.getOwning_department(),
				userinfo.getUser_id()
			
		};
		String sql = "update tbl_user_info set nick_name=?,sex=?,xm=?,e_mail=?,hysx=?,ssbm=?,gender=?,owning_department=? where user_id = ?";
		int count = jdbcTemplate.update(sql,params);
		return count;
	}
	
	
	/**
	 * 新增用户留言
	 */
	public int saveUserFeedback(UserFeedback userFeedback){
		//对密码加密
		String sql = "insert into tbl_user_feedback (user_id,message,create_time) values ( ?, ?, ?)";
		Object[] params = new Object[] {
				userFeedback.getUser_id(),
				userFeedback.getMessage(),
				userFeedback.getCreate_time()
		};
		return jdbcTemplate.update(sql, params);
	}
	
	public List<UserInfo> queryUserListById(Integer owning_company){
		StringBuffer sql = new StringBuffer("select * from tbl_user_info where owning_company=? order by user_id asc ");
		Object[] params = new Object[]{
				owning_company
		};
		 List<UserInfo> list = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		 return list;
	}
}