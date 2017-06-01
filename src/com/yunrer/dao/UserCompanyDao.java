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
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserCompanyImg;

@Repository("UserCompanyDao")
public class UserCompanyDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询公司信息实体
	 */
	public UserCompany queryCompany(int owning_company) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where id = ?");
		Object[] params = new Object[] { owning_company };
		List<UserCompany> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 新增公司信息
	 */
	public int addCompany(final UserCompany userCompany) {
		final StringBuffer sql = new StringBuffer("insert into tbl_user_company(user_id,gsmc,zczj,yyzzzch,fddbr,jyms,ygrs,bgdz,staffs_number,gsjs) values(?,?,?,?,?,?,?,?,?,?)");
		
		  KeyHolder keyHolder = new GeneratedKeyHolder();  
		    jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		              
		               PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);  
		               ps.setInt(1, userCompany.getUser_id()==null?0:userCompany.getUser_id());  
		               ps.setString(2, userCompany.getGsmc());  
		               ps.setString(3, userCompany.getZczj());  
		               ps.setString(4, userCompany.getYyzzzch());   
		               ps.setString(5, userCompany.getFddbr());  
		               ps.setString(6, userCompany.getJyms());  
		               ps.setString(7, userCompany.getYgrs());  
		               ps.setString(8, userCompany.getBgdz());  
		               ps.setInt(9, userCompany.getStaffs_number()==null?0:userCompany.getStaffs_number());  
		               ps.setString(10, userCompany.getGsjs());
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();   
		    return generatedId;  
	}
	
	/**
	 * 修改公司信息
	 */
	public int updateCompany(UserCompany userCompany) {
		StringBuffer sql = new StringBuffer("update tbl_user_company set gsmc=?,zczj=?,yyzzzch=?,fddbr=?,jyms=?,ygrs=?,bgdz=? where user_id = ? and id = ?");
		Object[] params = new Object[] { userCompany.getGsmc(),userCompany.getZczj(),userCompany.getYyzzzch(),userCompany.getFddbr(),userCompany.getJyms(),userCompany.getYgrs(),userCompany.getBgdz(),userCompany.getUser_id(),userCompany.getId() };
		return jdbcTemplate.update(sql.toString(),params);
	}
	
	/**
	 * 查询公司资质列表
	 */
	public List<UserCompanyImg> queryCompanyImgList(int owning_company) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_company_img where owning_company = ?");
		Object[] params = new Object[] { owning_company };
		List<UserCompanyImg> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserCompanyImg>(UserCompanyImg.class));
		return list;
	}
	
	
	/**
	 * 按条件检索公司信息
	 */
	public List<UserCompany> queryUserCompanyList(String queryStr) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where  gsmc like  concat('%',?,'%') order by id asc limit 10 ");
		List<UserCompany> list = jdbcTemplate.query(sql.toString(),new Object[]{queryStr},
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		return list;
	}
	
	/**
	 * 新增公司资质图片
	 */
	public int addCompanyImg(final UserCompanyImg companyImg) {
		int id =0;
		try {
			final String sql = "insert into tbl_user_company_img(user_id,imgurl,create_time,owning_company) values(?,?,now(),?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setInt(1, companyImg.getUser_id()==null?0:companyImg.getUser_id());
	                ps.setString(2, companyImg.getImgurl());
	                ps.setInt(3, companyImg.getowning_company());
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
	 * 删除公司资质图片
	 * @param user_id  用户ID
	 * @param id 图片ID
	 * @return
	 */
	public int delCompanyImg(int user_id,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,user_id};
			StringBuffer sql= new StringBuffer("delete from tbl_user_company_img where id = ? and user_id = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	public UserCompany getCompanyByName(String gsmc){
		StringBuffer sql = new StringBuffer("select id,user_id,gsmc,zczj,yyzzzch,fddbr,jyms,ygrs,bgdz,staffs_number,gsjs,request_url from tbl_user_company where gsmc = ? limit 1 ");
		List<UserCompany>  list = jdbcTemplate.query(sql.toString(), new Object[]{gsmc}, 
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class)
				);
		if(list!=null && list.size()>0)
			return list.get(0);
		else 
			return null;
	}
	
}