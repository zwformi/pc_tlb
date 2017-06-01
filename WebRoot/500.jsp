<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta name="Keywords" content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
  <meta name="Description" content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>页面遇到了一些问题～　————图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/homepage.css"/>
<link href="<%=basePath%>css/500.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
</head>
<body class="notfound">
<!-- 代码 开始 -->
<div class="wrapper">
    <div class="big">页面出了点问题...</div>
    <div><a href="<%=basePath%>"><span id="second" style="font-size:30px;">5</span></a>&nbsp;秒后到首页瞧瞧,点击<a href="<%=basePath%>" >返回首页</a>.</div>
</div>
<!-- 代码 结束 -->
 <script type="text/javascript">
 $(document).ready(function(){
 var second = 5;
     setInterval(function () {
         second--;
         $("#second").text(second);
         if (second == 0) top.window.location.href = "<%=basePath%>";
     }, 1000);
 });
 
 </script>
</body>
</html>
