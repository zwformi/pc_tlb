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

</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
<jsp:include page="left_menu.jsp"></jsp:include>
<!--center-->
<div class="member_center">
   <div class="member_content">
     <h3><strong>账户安全</strong></h3>
     <h4 class="member_title">修改登录密码</h4>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%"><span class="red_zi">* </span>旧密码：</th>
                 <td width="77%"><input class="member_box" type="password" name="oldpsd" id="oldpsd"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>新密码：</th>
                 <td><input class="member_box" type="password" name="newpsd" id="newpsd"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>确认新密码：</th>
                 <td><input class="member_box" type="password" name="newpsd2" id="newpsd2"></td>
               </tr>
               <tr>
                 <th>&nbsp;</th>
                 <td>&nbsp;
                 <input class="red_btn" name="input" type="button" id="changepsd" value="确认修改" /></td>
               </tr>
          </table>
       </li>
     </ul>
   </div>
</div>
<!--right-->

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.md5.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script src="<%=basePath %>js/member.js?date=<%=new Date()%>"></script>
</body>
</html>

