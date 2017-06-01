package com.yunrer.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Sign;
import com.yunrer.util.FormatTime;

/**
 * 
 * @author ZGF
 *
 */

@Repository("SignDao")
public class SignDao {
	
	@Resource
	private JdbcTemplate template;
	/**
	 * 获取用户签到信息
	 * @param userid
	 * @return
	 */
	public  List<Sign> querySignInfo(int userid,int num,String start,String end){
		Object[] params = null;
		
		StringBuffer sql= new StringBuffer("select user_id,sign_count,sign_history,last_modified_time ");
				sql.append( "from tbl_sign where 1=1");
				sql.append( " and user_id =?");
				if(start!=null && start!=""&& end!=null && end!="" )
					sql.append( " and last_modified_time between ? and ? ");
					sql.append( " order by last_modified_time desc,id desc limit ?");
				if(start!=null && start!=""&& end!=null && end!="" )
					 params = new Object[] {userid,start,end,num};
				else
					 params = new Object[] {userid,num};
				return  template.query(sql.toString(), params,new BeanPropertyRowMapper<Sign>(Sign.class));
		
	}
	
	/**
	 * 获取某天的签到信息
	 * @param userid
	 * @param date
	 * @return
	 */
	public  Sign querySignbyDate(int userid,String date){
		Object[] params = null;
		StringBuffer sql= new StringBuffer("select user_id,sign_count,sign_history,last_modified_time ");
				sql.append( "from tbl_sign where 1=1");
				sql.append( " and user_id =?");
					sql.append( " and last_modified_time like ?");
					sql.append( "  limit 1");
					 params = new Object[] {userid,date+'%'};
				List<Sign> list = template.query(sql.toString(), params,new BeanPropertyRowMapper<Sign>(Sign.class));
				return  list.size()>0?list.get(0):null;
	}
	public int insertSign(int userid,String sign_history){
		String sql="insert into tbl_sign (user_id,sign_count,sign_history,last_modified_time) values (?,?,?,?)";
		String dateTime  =  FormatTime.getFormatTime();
		Object[] params = {
			userid,
			1,
			sign_history,
			dateTime
		};
		return  template.update(sql, params);
		
	}
	/**
	 * 
	 * @param userid 用户id
	 * @param isContinuous 签到是否连续
	 * @param sign_history 签到历史记录
	 * @return
	 */
	public int updateSign(int userid,boolean isContinuous,String sign_history){
		StringBuffer sql = new StringBuffer("update  tbl_sign set ");
			if(isContinuous)
				sql.append(" sign_count=sign_count+1,");
			else
				sql.append(" sign_count=1,");
			sql.append("sign_history=?,");
			sql.append("last_modified_time=? ");
			sql.append("where user_id=? ");
			sql.append("and last_modified_time between ? and ? ;");
		String dateTime  = FormatTime.getFormatTime();
		Object[] params = {
			sign_history,
			dateTime,
			userid,
			FormatTime.getStartDate()+" 00:00:00",
			FormatTime.getEndDate()+" 23:59:59"
		};
		return  template.update(sql.toString(), params);
	}
	
}
