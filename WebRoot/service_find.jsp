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
<title>维修站查询---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/service_find.js?date=<%=new Date()%>"></script>
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
		<div class="member_center" >

 <div class="member_content" style="min-height:535px;">
     <h3>
	   <a href="/service.html?page=after_service">售后服务</a>
       <a class="select"href="/service/tostation.html?page=tostation">维修站查询</a>
       <a href="/service/mffw.html?page=free_service">免费服务</a>
     </h3>
     <div style="padding:30px 15px 40px 15px;">
       <div class="seach_address" style="float:left">
         <input class="buy_sbox" name="keyword"  id="keyword" type="text" style="width:350px"><input class="buy_sbtn" name="search_but" id="search_but" type="button"  value="搜索">
       </div>
       <table class="table_show" width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
           	 <th width="14%">品牌</th>
             <th width="22%">维修站名称</th>
             <th width="31%">详细地址</th>
             <th width="14%"><u>联系人</u></th>
             <th width="19%"><u>联系电话</u></th>
           </tr>
           <tbody id="content"></tbody>
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

