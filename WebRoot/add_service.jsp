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
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/add_service.js?date=<%=new Date()%>"></script>
<style type="text/css">
	.pro_img{border:1px solid #cecece;}
</style>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
 <jsp:include page="left_menu.jsp"></jsp:include>
 <!--center-->
<div class="member_center">
 <div class="member_content" style="min-height:auto;">
      <h3>
       <a class="select" href="/service.html">售后服务</a>
       <!-- <a href="/service/tostation.html">维修站查询</a> -->
       <a href="/service/mffw.html">免费服务</a>
     </h3>
     <div class="order_top">
       <strong>新增服务单</strong>
     </div>
     <div style="padding:10px 0px 40px 15px;">
       <table width="100%" border="0" cellspacing="10" cellpadding="0">
       <c:if test="${ORDERDETAIL!=null }">
       <tr>
         <td colspan="2">
         	<img class="pro_img" src="http://img.tulingbuy.com${ORDERDETAIL.product_imgurl }" width="50" height="50" align="absmiddle" >&nbsp;${ORDERDETAIL.product_name }
         </td>
       </tr>
       </c:if>
       <tr>
         <td width="46%">联系人：<input style="width:230px;" name="lxr" type="text" class="service_box" id="lxr" value="${loginUser.xm }" /></td>
         <td width="54%">联系电话：<input style="width:240px;" name="phone" type="text" class="service_box" id="phone" value="${loginUser.phone }" /></td>
       </tr>
       <tr>
         <td colspan="2">&nbsp;&nbsp;&nbsp;地址：<input style="width:559px;" name="address" type="text" class="service_box" style="width:95%;" id="address" value="${loginUser.gsmc }" /></td>
       </tr>
       <tr>
         <td colspan="2"><textarea class="service_h_box" name="content" id="content">品牌：&#xd型号：&#xd设备出现的问题：&#xd</textarea></td>
       </tr>
       <tr>
         <td colspan="2" style="text-align:center;">
           <input name="order_number" id="order_number" value="${order_number }" type="hidden"/>
           <input name="order_detail_id" id="order_detail_id" value="${order_detail_id }" type="hidden"/>
           <input class="red_btn" name="but_sub" id="but_sub" type="button" value="提交">
         </td>
       </tr>
       </table>
     </div>
   </div>
   
</div>
<!--right-->

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
