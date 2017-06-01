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
<title>修改手机号---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.md5.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script src="<%=basePath %>/js/phone.js?date=<%=new Date()%>"></script>
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
     <h4 class="member_title">修改手机号码</h4>
     <dl class="member_yz">
       <dd>
         <span>1</span>
         <u>验证身份</u>
       </dd>
       <dd class="line"><i>&nbsp;</i></dd>
       <dd>
         <span>2</span>
         <u>输入手机号</u>
       </dd>
       <dd class="line"><i>&nbsp;</i></dd>
       <dd>
         <span>3</span>
         <u>发送手机验证码</u>
       </dd>
       <dd class="line"><i>&nbsp;</i></dd>
       <dd>
         <span><i class="fa fa-check"></i></span>
         <u>完成</u>
       </dd>
     </dl>
     <ul class="member_zi">
       <li>
          <span style="display:block; padding-top:30px; text-align:center; font-size:30px; font-weight:bold; color:#090;"><i class="fa fa-check"></i> 修改成功</span>
       </li>
     </ul>
   </div>
</div>
<!--right-->

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
