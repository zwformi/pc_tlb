package com.yunrer.util;

public class SkuSqlUtil {

	public static String makeSkuSql(int size,String alias ,String columnName){
		StringBuffer sql = new StringBuffer();
		sql.append("case when (");
		for (int i = 0; i < size; i++) {
			if(i==0){
				sql.append(" isnull("+alias+".attrVal"+(i+1)+") ");
			}
			else{
			    sql.append(" AND isnull("+alias+".attrVal"+(i+1)+") ");
			}
		}
		sql.append(") then '' else ");
		sql.append(" case when "+alias+".id is not null then concat( substring_index(concat('(',");
		for (int i = 0; i < size; i++) {
			sql.append("IF(isnull("+alias+".attrVal"+(i+1)+"),'',CONCAT("+alias+".attrName"+(i+1)+",'：',"+alias+".attrVal"+(i+1)+",'、'))");
			if(i<size-1)
				sql.append(",");
		}
		
		sql.append(",')') , '、)', 1) ,')') else '' end end "+columnName);
		if(size>0)
		return sql.toString();
		else 
			return "";
	}
	

}
