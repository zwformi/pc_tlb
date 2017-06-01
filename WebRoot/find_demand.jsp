<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="Keywords" content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description" content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>我要查---需求单---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">
 <div class="member_content" style="min-height:auto;">
     <h3>
       <a class="select" href="/find/find_demand.html">需求单</a>
       <a href="/find/find_contract.html">订单合同</a>
       <a href="/find/find_service.html">服务单</a>
     </h3>

     <!-- 主要内容 -->
     <div class="order_cont" style="min-height:395px;">
		<br>
         <c:forEach items="${XUQIULIST}" var="XUQIU" varStatus="status">
         <c:if test="${ status.index == 0}">
         <table class="table_show1" width="100%" border="0" cellspacing="0" cellpadding="0">
	       	  <thead>
	           <tr>
	             <th width="250" style="text-align:center;padding:0;">生成时间：<fmt:formatDate value="${XUQIU.order_time}" type="both"/></span></th>
	             <th width="350" style="text-align:center;padding:0;">需求单号：<span>${XUQIU.order_number}</span></th>
	             <th width="150" style="text-align:center;padding:0;">
			      <span class="iconfont icon-dianpu" style="color:#E3393C"></span>
							<c:if test="${XUQIU.order_source==0 }"><span>采购车</span></c:if>
			              	<c:if test="${XUQIU.order_source==1 }"><span>微信</span></c:if>
			              	<c:if test="${XUQIU.order_source==2 }"><span>一键需求</span></c:if>
				</th>	        
	            <th  style="text-align:center;padding:0;"><c:if test="${XUQIU.xm!=null }">  <span class="iconfont icon-profile" style="color:#E3393C"></span> ${XUQIU.xm } </c:if>  </th>
	              		
	           </tr>
	          </thead>
			</table>
 			<table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">
		       <tr>
	              <td width="80">
		       	  	  
		       	  	<img onerror="onerror=null;src='<%=basePath%>images/default_product.png'" src="http://img.tulingbuy.com${XUQIU.product_imgurl }">
		       	  	
	
		       	  </td>
	              <td width="320">
	              	${XUQIU.product_name }${XUQIU.pz}              
	              </td>
	              <td class=" table_right" width="50">×${XUQIU.product_count }</td>
	              <td width="100"class="table_right"><span>￥<fmt:formatNumber type="number" value="${XUQIU.payment_money }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <td width="150"class="table_right" style="text-align:center;">
	              	<span class="iconfont icon-kuaidi" style="color:#E3393C;display:inline-block"></span>
	              	<c:if test="${XUQIU.order_state==4 }">待报价</c:if>
	              	<c:if test="${XUQIU.order_state==5 }"><font style="color: #D74340">已报价</font></c:if>
	              	<c:if test="${XUQIU.order_state==6 }">已关闭</c:if>
	              	<c:if test="${XUQIU.order_state==7 }">已接受报价</c:if>
	              	<c:if test="${XUQIU.order_state==8 }">已生成合同</c:if>
	              	</span>
				  </td>
	              <td align="center">
	                 <span><a style="color:#7fa9ee" href="/find/find_edit_demand.html?order_number=${XUQIU.order_number}"><i class="fa fa-file-text-o"></i> 订单详情</a></span>
	              </td>
	            </tr>
			</c:if>
			
			<c:if test="${status.index>0 && XUQIULIST[status.index-1].order_number!=XUQIU.order_number}">
           <table class="table_show1" width="100%" border="0" cellspacing="0" cellpadding="0">
	       	  <thead>
	           <tr>
	             <th width="250" style="text-align:center;padding:0;">生成时间：<span><fmt:formatDate value="${XUQIU.order_time}" type="both"/></span></th>
	             <th width="350" style="text-align:center;padding:0;">需求单号：<span>${XUQIU.order_number}</span></th>
	             <th width="150" style="text-align:center;padding:0;">
			      <span class="iconfont icon-dianpu" style="color:#E3393C"></span>
							<c:if test="${XUQIU.order_source==0 }"><span>采购车</span></c:if>
			              	<c:if test="${XUQIU.order_source==1 }"><span>微信</span></c:if>
			              	<c:if test="${XUQIU.order_source==2 }"><span>一键需求</span></c:if>
				</th>	       

				           
	            <th style="text-align:center;"><c:if test="${XUQIU.xm!=null }">  <span class="iconfont icon-profile" style="color:#E3393C"></span> ${XUQIU.xm } </c:if>  </th>
	              			
	            </tr>
	          </thead>
			</table>
 			<table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">
		       <tr>
	              <td width="80">
		       	  	 
		       	  	  	<img onerror="onerror=null;src='<%=basePath%>images/default_product.png'"  src="http://img.tulingbuy.com${XUQIU.product_imgurl }">
		       	  	
		       	  </td>
	              <td width="320">
	              	${XUQIU.product_name }${XUQIU.pz}              
	              </td>
	              <td class=" table_right" width="50">×${XUQIU.product_count }</td>
	              <td width="100"class="table_right"><span>￥<fmt:formatNumber type="number" value="${XUQIU.payment_money }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <td width="150"class="table_right" style="text-align:center;">
	              	<span class="iconfont icon-kuaidi" style="color:#E3393C;display:inline-block;" ></span>
	              	<c:if test="${XUQIU.order_state==4 }">待报价</c:if>
	              	<c:if test="${XUQIU.order_state==5 }"><font style="color: #D74340">已报价</font></c:if>
	              	<c:if test="${XUQIU.order_state==6 }">已关闭</c:if>
	              	<c:if test="${XUQIU.order_state==7 }">已接受报价</c:if>
	              	<c:if test="${XUQIU.order_state==8 }">已生成合同</c:if>
	              	</span>
				  </td>
	              <td align="center">
	                 <span><a style="color:#7fa9ee" href="/find/find_edit_demand.html?order_number=${XUQIU.order_number}"><i class="fa fa-file-text-o"></i> 订单详情</a></span>
	              </td>
	            </tr>
	       </c:if>
	       <c:if test="${status.index>0 && XUQIULIST[status.index-1].order_number==XUQIU.order_number}">
            <tr>
              <td class="table_line" width="60">
	       	  	    
		       	  	  	<img onerror="onerror=null;src='<%=basePath%>images/default_product.png'"  src="http://img.tulingbuy.com${XUQIU.product_imgurl }">
		       	  	
	       	  </td>
              <td class="table_line" width="320">
              	${XUQIU.product_name } ${XUQIU.pz}             
              </td>
              <td class="table_line table_right" width="50">×${XUQIU.product_count }</td>
              <td class="table_right"></td>
              <td class="table_right"></td>
              <td></td>
				
            </tr>
            </c:if>	  
	       <c:if test="${status.index<fn:length(XUQIULIST) -1 && XUQIULIST[status.index+1].order_number!=XUQIU.order_number}">
            	
            	
            	</table>
        	</table>
            	<br/>
            </c:if>
            
            </c:forEach>
            <c:if test="${ORDERINFOLIST== null || fn:length(ORDERINFOLIST) == 0}">
            <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">		       
            	<tr><td colspan="7">您还没有需求单</td></tr>
            </table>
            </c:if>
      
     </div>
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
