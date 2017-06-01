package com.yunrer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Integration;
import com.yunrer.entity.IntegrationDetail;
import com.yunrer.util.FormatTime;

/**
 * 
 * @author ZGF
 *
 */
@Repository
public class IntegrationDao {

	@Resource
	private JdbcTemplate template;
	
	public int addIntegration(int userid,int integration){
		StringBuffer sql = new StringBuffer("insert into tbl_integration (user_id,last_modified_time,total_integration) values (?,?,?)");
		Object[] params = new  Object[]{userid,FormatTime.getFormatTime(),integration};
		return template.update(sql.toString(),params);
	}
	
	public int updateIntegration(int userid,int integration){
		StringBuffer sql = new StringBuffer("update tbl_integration set last_modified_time=?,total_integration=total_integration+? where user_id=?");
		Object[] params = new  Object[]{FormatTime.getFormatTime(),integration,userid};
		return template.update(sql.toString(),params);
	}
	public List<Integration> getIntegration(int userid ){
		StringBuffer sql = new StringBuffer("select id,user_id,total_integration,last_modified_time from tbl_integration where user_id=? limit 1");
		Object[] params = new Object[]{userid};
		return  template.query(sql.toString(), params,new BeanPropertyRowMapper<Integration>(Integration.class));
	}
	
	public List<IntegrationDetail> getDetails(int integration_id,String type,String start,String end,String num){
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select i.user_id,d.id,d.integration_id,d.integration_count,d.type,d.operate_flag,d.create_time,s.text typeName,s1.text operateName from tbl_integration_details d ");
				sql.append("left join tbl_integration i on d.integration_id = i.id ");
				sql.append("left join tbl_stringmap s on d.type =s.attribute_value and s.entity_name='tbl_integration_details' and s.attribute_name ='type' ");
				sql.append(" left join tbl_stringmap s1 on d.operate_flag =s1.attribute_value and s1.entity_name='tbl_integration_details' and s1.attribute_name ='operate_flag' ");
				sql.append("where 1=1 ");
				sql.append(" and d.integration_id=? ");
				list.add(integration_id);
				if(type!=null){
					sql.append(" and type=?");
					list.add(type);
					
				}
				if(start!=null && !"".equals(start) && end!=null && !"".equals(end)){
					sql.append(" and create_time between ? and ? ");
					list.add(start);
					list.add(end);
				}
				sql.append(" order by create_time desc,operate_flag  ");
				if(num!=null && "".equals(num)){
					sql.append("  limit ? ;");
					list.add(num);
				}
				
				Object[] params =  list.toArray();
				System.out.println(sql.toString());
				return template.query(sql.toString(), params, new BeanPropertyRowMapper<IntegrationDetail>(IntegrationDetail.class));
	}
	
	public int addDetail(int integration_id,int type,int integration_count,int operate_flag){
		StringBuffer sql = new StringBuffer("insert into tbl_integration_details (integration_id,integration_count,type,operate_flag,create_time) values (?,?,?,?,?);");
		Object[] params = new Object[]{integration_id,integration_count, type, operate_flag, FormatTime.getFormatTime()};
		return template.update(
				sql.toString(),
				params);
	}

}
