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
<title>账户安全---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
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
   <div class="member_content">
     <h3><strong>账户安全</strong></h3>
     <div class="member_ts">
     温馨提示： <br>
     1、登录手机用于登录，并展示在您的联系方式中； <br>
     2、一个图灵买帐号，只能对应绑定一个登录手机； <br>
     3、定期修改登录密码，可保证账户的安全性； </div>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%">登录手机号：</th>
                 <td width="77%">${loginUser.phone }<a style="display:inline-block; margin-left:50px;" href="change_phone.jsp">修改注册手机</a></td>
               </tr>
               <tr>
                 <th width="23%">登录密码&nbsp;：</th>
                 <td width="77%">**********<a style="display:inline-block; margin-left:78px;" href="change_psd.jsp">修改登录密码</a></td>
               </tr>
               <%--
               <tr>
                 <th>登录邮箱：</th>
                 <td>476***@qq.com <a style="display:inline-block; margin-left:30px;"  href="change_email.asp">修改登录邮箱</a></td>
               </tr>
               <tr>
                 <th>接收短信：</th>
                 <td>&nbsp;&nbsp;<img src="images/open.png" style="vertical-align:middle;"/></td>
               </tr>
               <tr>
                 <th>接收邮件：</th>
                 <td>&nbsp;&nbsp;<img src="images/closed.png" style="vertical-align:middle;"/></td>
               </tr>
                --%>
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

