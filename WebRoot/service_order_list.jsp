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
<title>售后服务---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/service_order_list.js?date=<%=new Date()%>"></script>
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
		<div class="member_center" >
 <div class="member_content" style="min-height:535px;">
     <h3>
       <a class="select" href="/service.html">售后服务</a>
       <a href="/service/tostation.html">维修站查询</a>
       <a href="/service/mffw.html">免费服务</a>
     </h3>
      <div class="order_top">
       <strong>可申请售后的商品信息</strong>
       <span><input class="red_btn" style="margin-top:-10px;" type="button" value="添加服务单" onClick="location='/add_service.jsp'"/></span>
     </div>
     <div style="padding:10px 15px 40px 15px;">
       <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0" style="border:none">
           <c:forEach items="${ORDERINFOLIST }" var="ORDERINFO">
            <tr style="background-color:#b22734;">
              <td style="color:#fff;"><span>合同号：${ORDERINFO.order_number }</span></td>
              <td style="color:#e2e0e0;"><span>合同日期：<fmt:formatDate value="${ORDERINFO.order_time }" type="both"/></span></td>
              <%--<td><span>合同金额：￥<fmt:formatNumber type="number" value="${ORDERINFO.payment_money }" pattern="0.00" maxFractionDigits="2"/></span></td> --%>
              <td style="color:#e2e0e0;border-right:1px solid #b22734;">
              	<span>合同来源
					<c:if test="${ORDERINFO.order_source==0 }">采购</c:if>
	              	<c:if test="${ORDERINFO.order_source==1 }">秒杀</c:if>
	              	<c:if test="${ORDERINFO.order_source==2 }">立即购买</c:if>
				</span>
			  </td>
			   <%--
              <td align="center">
                 <span><a  style="color:#7fa9ee" href="/service/toorderproduct.html?order_number=${ORDERINFO.order_number }">申请售后</a></span>
              </td>
               --%>
            </tr>
            <tr>
            	<td colspan="3" style="padding:0px;margin:0px;border:1px solid #b22734;border-top:none;">
            		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			         <thead>
			           <tr>
			             <th style="background-color:#faf9f4;color:#666666;border-right:none;">商品</th>
			             <th style="background-color:#faf9f4;border-right:none;">名称</th>
			              <th style="background-color:#faf9f4;border-right:none;">类型</th>
			             <th style="background-color:#faf9f4;border-right:none;">品牌</th>
			             <th style="background-color:#faf9f4;border-right:none;">型号</th>
			             <th style="background-color:#faf9f4;border-right:none;">售后</th>
			           </tr>
			          </thead>
			          <tbody class="load_product" order_number="${ORDERINFO.order_number }">
			            <tr>
			            	<td colspan="6">正在加载可申请售后的商品...</td>
			            </tr>
			          </tbody>
			        </table>
            	</td>
            </tr>
            <tr><td colspan="3" height="10" style="border:none;"></td></tr>
            </c:forEach>
            <c:if test="${ORDERINFOLIST== null || fn:length(ORDERINFOLIST) == 0}">
            	<tr><td colspan="4">您还没有订单合同申请售后服务</td></tr>
            </c:if>
       </table>
     </div>
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>

