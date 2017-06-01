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
<title>图灵买快报---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<style type="text/css">
	.a_list{height:30px;line-height:30px;width:100%;border:none;border-bottom:1px dashed #cccccc;font-size:12px;}
	.a_list img{margin-right:5px;}
	.a_list span{float:right;color:#888888;}
	.a_list a{color:#666666;}
</style>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main0">
<!--left-->
 <div class="main_left">
   <dl class="about_menu">   	 
     <dd><a class="select" href="<%=basePath %>article/gsdsjlb1.json"><i class="fa fa-arrow-circle-o-right"></i>企业快报</a></dd>
   </dl>
 </div>
 <!--right-->
 <div class="main_right">
  <dl class="about_cont">
     <dt><span>企业快报</span></dt>
     <dd>
       <c:forEach items="${articleList_dsj}" var="articles">
     	<div class="a_list"><img src="/images/a_left.png" align="absmiddle"/><a href="<%=basePath %>article/gsdsjxq1.json?typeid=2&ID=${articles.id }" >${articles.title }</a><span>${articles.create_time_str }</span></div>
       </c:forEach>
     </dd>
  </dl>
 </div>

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
