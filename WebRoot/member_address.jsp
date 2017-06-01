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
<title>收货信息---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
<script src="<%=basePath %>js/member.js?date=<%=new Date()%>"></script>
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_companyimg.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>

<script src="<%=basePath %>js/shenshixian.js" ></script>
<style type="text/css">
	.address_css{border:1px solid #cccccc;font-size:12px;}
	.address_css tr th{text-align:left;padding-left:5px;}
	.address_css tr td{text-align:left;padding-left:5px;border-bottom:1px solid #eeeeee;}
</style>
<script type="text/javascript">
	$(document).ready(function($){
		//加载省市县三级联动
	    areaSelect.createSelect();  
    });
</script>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
		<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center" >
   <div class="member_content">
     <h3><strong>收货地址</strong></h3>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%"><span class="red_zi">* </span>收件人：</th>
                 <td width="77%"><input class="member_box" type="text"  autocomplete="off" name="consignee_name" id="consignee_name" value="${USERADDRESS.consignee_name }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>所在地区：</th>
                 <td id="shengshixian"></td>
               </tr>
              <!--  <tr>
                 <th></th>
                 <td><span id="shengshixian_str"></span></td>
               </tr> -->
               <tr>
                 <th><span class="red_zi">* </span>详细地址：</th>
                 <td><input class="member90_box" type="text" name="address" id="address"   autocomplete="off" value="${USERADDRESS.address }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>手机号码：</th>
                 <td><input class="member_box" type="text" name="phone" id="phone"  autocomplete="off" value="${USERADDRESS.phone }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>邮编：</th>
                 <td><input class="member_box" type="text" name="post_code" id="post_code"  autocomplete="off" value="${USERADDRESS.post_code }"></td>
               </tr>
               <tr>
                 <th></th>
                 <td><input type="checkbox" value="1" name="is_default" id="is_default" autocomplete="off" />设置为默认收货地址</td>
               </tr>
               <tr>
                 <th>&nbsp;</th>
                 <td>&nbsp;<input type="hidden" name="id" id="id" value="0" autocomplete="off"/>
                 <input class="red_btn" name="input" type="button" id="save_address" value="保存" /></td>
               </tr>
          </table>
       </li>
       <li style="padding-top:30px;">
       		<table width="100%" cellpadding="0" cellspacing="0" class="address_css" >
       			<tr style="background-color:#eeeeee; ">
	       			<th width="100">收货人</th>
	       			<th width="200">所在地区</th>
	       			<th>详细地址</th>
	       			<th width="100">邮编</th>
	       			<th width="200">电话</th>
	       			<th width="200">操作</th>
	       			<th width="100" style="text-align:center;">默认</th>
       			</tr>
	       		<c:forEach items="${USERADDRESSLIST}" var="USERADDRESS">
	       		<tr>
	       			<td>${USERADDRESS.consignee_name }</td>
	       			<td>${USERADDRESS.ssx }</td>
	       			<td>${USERADDRESS.address }</td>
	       			<td>${USERADDRESS.post_code }</td>
	       			<td>${USERADDRESS.phone }</td>
	       			<td id="${USERADDRESS.id }" is_default="${USERADDRESS.is_default }"><a class="update">修改</a> <a class="delete" >删除</a></td>
	       			<td style="text-align:center;">
	       				<c:if test="${USERADDRESS.is_default==1 }">是</c:if>
	       			</td>
	       		</tr>
	       		</c:forEach>
	       		<c:if test="${USERADDRESSLIST==null|| fn:length(USERADDRESSLIST) == 0 }">
	       			<tr>
	       				<td colspan="7">您还没有任何的收货地址，赶紧添加吧</td>
	       			</tr>
	       		</c:if>
       		</table>	
       </li>
     </ul>
   </div>
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
