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
<title>订单合同---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">
 
 <div class="member_content" style="min-height:535px;">
     <h3>
     <!--   <a href="/find/find_demand.html">需求单</a> -->
       <a class="select" href="/find/find_contract.html">订单合同</a>
       <a href="/find/find_service.html">服务单</a>
     </h3>
     <div style="padding:30px 15px 40px 15px;">
       
           <c:forEach items="${ORDERINFOLIST }" var="ORDERINFO" varStatus="vstatus">
           <c:if test="${vstatus.index==0}">
	           <table class="table_show1" width="100%" border="0" cellspacing="0" cellpadding="0">
		       	  <thead>
		           <tr >
		             <th width="250" style="text-align:center;padding:0;"><span>生成时间:<fmt:formatDate value="${ORDERINFO.order_time }" type="both"/></span></th>
		             <th width="350" style="text-align:center;padding:0;"><span>订单编号:${ORDERINFO.order_number }</span></th>	             
		             <th width="150" style="text-align:center;padding:0;">
				             <span class="iconfont icon-dianpu" style="color:#E3393C"></span>
								<c:if test="${ORDERINFO.order_source==0 }">采购</c:if>
				              	<c:if test="${ORDERINFO.order_source==1 }">秒杀</c:if>
				              	<c:if test="${ORDERINFO.order_source==2 }">立即购买</c:if>
							
					</th>	                         
		            <th style="text-align:center;padding:0;"><span class="iconfont icon-profile" style="color:#E3393C"></span> ${ORDERINFO.shr_xm}</th> 
		           </tr>
		          </thead>
		       </table>
		       <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">
		       <tr>
	              <td width="80">
					<img onerror="onerror=null;src='<%=basePath%>images/default_product.png'"  src="http://img.tulingbuy.com${ORDERINFO.product_imgurl }">
		       	  </td>
	              <td width="320">
	              	${ORDERINFO.product_name }${ORDERINFO.pz}              
	              </td>
	              <td class=" table_right" width="50">×${ORDERINFO.product_count }</td>
	              <td width="100"class="table_right"><span>￥<fmt:formatNumber type="number" value="${ORDERINFO.payment_money }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <td width="150"class="table_right" style="text-align:center;">
	              	<c:if test="${ORDERINFO.order_state!=0 }">
	              	<span class="iconfont icon-kuaidi" style="color:#E3393C;display:inline-block"></span>
	              	</c:if>
						<c:if test="${ORDERINFO.order_state==4 }">配货中</c:if>
		              	<c:if test="${ORDERINFO.order_state==5 }">已发货，待签收</c:if>
		              	<c:if test="${ORDERINFO.order_state==61 }">已签收，待实施</c:if>
		              	<c:if test="${ORDERINFO.order_state==62 }">实施中</c:if>
		              	<c:if test="${ORDERINFO.order_state==71 }">已签收，待评价</c:if>
		              	<c:if test="${ORDERINFO.order_state==72 }">已实施、待评价</c:if>
		              	<c:if test="${ORDERINFO.order_state==8 }">已完成</c:if>
		              	<c:if test="${ORDERINFO.order_state==10 }">已付款</c:if>
		              	<c:if test="${ORDERINFO.order_state==0 }"> <a style="color:red;" href="<%=basePath%>pay.json?order_number=${ORDERINFO.order_number}">未付款 ,立即支付</a></c:if>
				  </td>
	              
				
	              <td align="center">
	                 <span><a style="color:#7fa9ee" href="/find/find_contract_show.html?order_number=${ORDERINFO.order_number }"><i class="fa fa-file-text-o"></i> 订单详情</a></span>
	              </td>
	            </tr>
	       </c:if>
	       
           <c:if test="${vstatus.index>0 && ORDERINFOLIST[vstatus.index-1].order_number!=ORDERINFO.order_number}">
           <table class="table_show1" width="100%" border="0" cellspacing="0" cellpadding="0">
	       	  <thead>
	           <tr>
	             <th width="250" style="text-align:center;padding:0;">生成时间:<span><fmt:formatDate value="${ORDERINFO.order_time }" type="both"/></span></th>
	             <th width="350" style="text-align:center;padding:0;"><span>订单编号:${ORDERINFO.order_number }</span></th>	             
	             <th width="150" style="text-align:center;padding:0;">
			             <span class="iconfont icon-dianpu" style="color:#E3393C"></span>
							<c:if test="${ORDERINFO.order_source==0 }">采购</c:if>
			              	<c:if test="${ORDERINFO.order_source==1 }">秒杀</c:if>
			              	<c:if test="${ORDERINFO.order_source==2 }">立即购买</c:if>
						
				</th>	                         
	            <th  style="text-align:center;"><span class="iconfont icon-profile" style="color:#E3393C"></span> ${ORDERINFO.shr_xm}</th>
	           </tr>
	          </thead>
	       </table>
	       
	       <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">
	       <tr>
	       
	       	  <td  width="80">
		       	 <img onerror="onerror=null;src='<%=basePath%>images/default_product.png'"  src="http://img.tulingbuy.com${ORDERINFO.product_imgurl }">
	       	  </td>
              <td  width="320">
              	${ORDERINFO.product_name }${ORDERINFO.pz }              
              </td>
	              <td class=" table_right" width="50">×${ORDERINFO.product_count }</td>
	              <td width="100" class="table_right"><span>￥<fmt:formatNumber type="number" value="${ORDERINFO.payment_money }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <td width="150" class="table_right" style="text-align:center;">
	              	<c:if test="${ORDERINFO.order_state!=0 }">
	              	<span class="iconfont icon-kuaidi" style="color:#E3393C;display:inline-block"></span>
	              	</c:if>
						<c:if test="${ORDERINFO.order_state==4 }">配货中</c:if>
		              	<c:if test="${ORDERINFO.order_state==5 }">已发货，待签收</c:if>
		              	<c:if test="${ORDERINFO.order_state==61 }">已签收，待实施</c:if>
		              	<c:if test="${ORDERINFO.order_state==62 }">实施中</c:if>
		              	<c:if test="${ORDERINFO.order_state==71 }">已签收，待评价</c:if>
		              	<c:if test="${ORDERINFO.order_state==72 }">已实施、待评价</c:if>
		              	<c:if test="${ORDERINFO.order_state==8 }">已完成</c:if>
		              	<c:if test="${ORDERINFO.order_state==10 }">已付款</c:if>
		              	<c:if test="${ORDERINFO.order_state==0 }"><a style="color:red;" href="<%=basePath%>pay.json?order_number=${ORDERINFO.order_number}">未付款 ,立即支付</a></c:if>
			  </td>
              
				
              <td align="center">
                 <span><a style="color:#7fa9ee" href="/find/find_contract_show.html?order_number=${ORDERINFO.order_number }"><i class="fa fa-file-text-o"></i> 订单详情</a></span>
              </td>
            </tr>
	       </c:if>	       	  
           <c:if test="${vstatus.index>0 && ORDERINFOLIST[vstatus.index-1].order_number==ORDERINFO.order_number}">
            <tr>
              <td class="table_line" width="60">
	       	  	  <img onerror="onerror=null;src='<%=basePath%>img/images/default_product.png'"  src="http://img.tulingbuy.com${ORDERINFO.product_imgurl }">
	       	  </td>
              <td class="table_line" width="320">
              	${ORDERINFO.product_name } ${ORDERINFO.pz}             
              </td>
              <td class="table_line table_right" width="50">×${ORDERINFO.product_count }</td>
              <td class="table_right"></td>
              <td class="table_right"></td>
              <td></td>
				
            </tr>
            </c:if>
            <c:if test="${vstatus.index<fn:length(ORDERINFOLIST) -1 && ORDERINFOLIST[vstatus.index+1].order_number!=ORDERINFO.order_number}">
            	</table>
            	<br/>
            </c:if>
            <c:if test="${(vstatus.index+1)==fn:length(ORDERINFOLIST)}">
            	</table>
            	<br/>
            </c:if>
            
            </c:forEach>
            <c:if test="${ORDERINFOLIST== null || fn:length(ORDERINFOLIST) == 0}">
            <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">		       
            	<tr><td colspan="7">您还没有订单合同</td></tr>
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

