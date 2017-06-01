<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>忘记密码---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
</head>

<body>
<form id="forgetForm">
<jsp:include page="top.jsp"></jsp:include>
<div class="forget_bj">
 <h3>重置密码</h3>
 <dl>
   <dt>
     <i class="fa fa-lock"></i>
     <input class="forget_box" placeholder="新密码" name="password_str" id="password_str" type="password">
   </dt>
   <dt>
     <i class="fa fa-lock"></i>
     <input class="forget_box" placeholder="确认密码" name="password_str2" id="password_str2" type="password">
     <input style="display:none;" class="forget_box" placeholder="确认密码" name="password_text" id="password_text" type="password">
   </dt>
   <dd><input class="forget_btn" id="forget_btn" type="button" value="确认"></dd>
 </dl>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</form>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/jquery.md5.js"></script>
<script src="<%=basePath %>js/forget_psd02.js?date=<%=new Date()%>"></script>
</body>
</html>