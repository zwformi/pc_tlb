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
<title>我要查---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style type="text/css">
	.error_img{width:100%;text-align:center;padding-top:150px;}
	.error_message{width:100%;text-align:center;color:red;padding-top:20px;font-size:14px;}
</style>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
 <jsp:include page="left_menu.jsp"></jsp:include>
<div class="member_center">
 <div class="member_content" style="min-height:649px;">
       <div class="error_img"><img src="/images/error_page.png"/></div>
       <div class="error_message"><c:if test="${message==null||message=='' }">页面出现错误了，我们也不知道怎么了，非常抱歉...</c:if><c:if test="${message!=null&&message!='' }">${message }</c:if></div>
   </div>
</div>
<!--right-->

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
