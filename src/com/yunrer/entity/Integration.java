package com.yunrer.entity;

import java.util.Date;

public class Integration {
		private Integer id;//积分记录id
		private Integer user_id;//用户id
		private Integer total_integration;//总积分
		private Date last_modified_time;//最后修改时间.

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUser_id() {
			return user_id;
		}
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public Integer getTotal_integration() {
			return total_integration;
		}
		public void setTotal_integration(Integer total_integration) {
			this.total_integration = total_integration;
		}
		public Date getLast_modified_time() {
			return last_modified_time;
		}
		public void setLast_modified_time(Date last_modified_time) {
			this.last_modified_time = last_modified_time;
		}
		
}
