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
<title>服务单---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">
	$(document).ready(function($){
		//商品图片加载错误事件
		$("img.pro_img").on("error",function(){
			$(this).attr("src","/images/no_pic.png");
		});
	});
</script>
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
       <a href="/find/find_demand.html">需求单</a>
       <a href="/find/find_contract.html">订单合同</a>
       <a class="select" href="/find/find_service.html">服务单</a>
     </h3>
     <div class="order_top">
       <strong>服务单详情</strong>
     </div>
     <div class="order_cont" style="min-height:395px;">
       <table class="add_table" width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr></tr>
       <tr>
         <th width="20%">服务单号 ：</th>
         <td width="80%"><label for="select">${USERSERVICE.service_number }</label></td>
       </tr>
       <tr>
         <th>关联订单合同号：</th>
         <td>${USERSERVICE.order_number }</td>
       </tr>
       <tr>
         <th>服务商品：</th>
         <td>
         	<img class="pro_img" src="http://img.tulingbuy.com${ORDERDETAIL.product_imgurl }" width="100" height="100" ><br/>${ORDERDETAIL.product_name }
         </td>
       </tr>
       <tr>
         <th>联系人：</th>
         <td>${USERSERVICE.lxr }</td>
       </tr>
       <tr>
         <th>联系电话：</th>
         <td>${USERSERVICE.phone }</td>
       </tr>
       <tr>
         <th>服务地址：</th>
         <td>${USERSERVICE.address }</td>
       </tr>
       <tr>
         <th>问题描述：</th>
         <td>${USERSERVICE.content }</td>
       </tr>
       	<tr>
         <th>发布时间：</th>
         <td><fmt:formatDate value="${USERSERVICE.add_time }" type="both"/></td>
       </tr>
       <tr>
         <th>服务备注：</th>
         <td>${USERSERVICE.comment }</td>
       </tr>
       
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

