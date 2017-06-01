package com.yunrer.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.OrderDao;
import com.yunrer.dao.UserCompanyDao;
import com.yunrer.dao.UserInvoicesDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserInvoices;
import com.yunrer.util.FormatTime;
import com.yunrer.util.RMBTransUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("OfficeHelpService")
@Transactional
		
public class OfficeHelpService {

	@Resource
	private OrderDao orderDao;
	
	@Resource
	private UserCompanyDao userCompanyDao;
	/**
	 * 合同表生成
	 */
public String editContract(String order_number,String template_name,String basePath,UserInfo userinfo,Writer output)  throws Exception{

	String templatePath = "template\\";  
  
	      
	    //Configuration用于读取ftl文件
	 Configuration configuration = new Configuration();
     configuration.setDefaultEncoding("utf-8");
    
	      //指定路径(除了文件名的部分)
	      configuration.setDirectoryForTemplateLoading(new File(basePath+templatePath));
	        
	     //封装要填充的订单数据
	     OrderInfo  orderinfo= new OrderInfo();
	     orderinfo = orderDao.queryOrder_Detail(order_number);
	     
	     //转换时间
	     String order_date =FormatTime.getFormartDateCN(orderinfo.getOrder_time());  
	 	 String sys_date = FormatTime.getFormartDateCN();
	     
	    List<OrderDetails> orderDetailsList = null;
	 	orderDetailsList = orderDao.queryOrderDetailList(order_number);
	 	
	 	//公司
	 	UserCompany company = userCompanyDao.queryCompany(userinfo.getOwning_company());
	 		
	    Double sum = orderDao.queryOrderSum(order_number); 
	    //转换数字
	    BigDecimal sumCN1 = new BigDecimal(sum);
	    String sumCN =  RMBTransUtil.number2CNMontrayUnit(sumCN1);
	    
	    
	 	Map<String,Object> map=new HashMap<String,Object>();
	     map.put("orderDetailsList",orderDetailsList);
	     map.put("order", orderinfo);
	     map.put("sum", sum);
	     map.put("sumCN", sumCN);
	     map.put("user", userinfo);
	     map.put("company", company);
	     map.put("order_date", order_date);
	     map.put("sys_date", sys_date);
	     //以utf-8的编码读取ftl文件
	     
	     Template t =  configuration.getTemplate(template_name,"utf-8");
	     
  	 try {
  	
       t.process(map, output);
  	  } catch (TemplateException e) {
  		   // TODO Auto-generated catch block
  		   e.printStackTrace();
  		  } catch (IOException e) {
  		   // TODO Auto-generated catch block
  		   e.printStackTrace();
  		  }
  		 
       return (order_number+".pdf");
	   }

}

