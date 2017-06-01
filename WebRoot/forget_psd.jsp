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
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/forget_psd.js?date=<%=new Date()%>"></script>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="forget_bj">
 <h3>忘记密码</h3>
 <dl>
   <dt>
     <i class="fa fa-user"></i>
     <input class="forget_box" placeholder="请输入您的手机号" name="phone" id="phone" type="text" maxlength="11">
   </dt>
   <dt>
     
  
       <i class="fa fa-shield" style="top:17px;"></i>
      <input class="forget_box200" type="text" placeholder="图形验证码" name="text" id="VerifyCode" maxlength="4" style="width:100px;dispaly:inline-block;">
      <img onclick="refreshCode();" id="verifycodeimg" style="dispaly:inline-block;" width="100px" height="30px" src="<%=basePath%>register/verifycode.json"/>
      <a onclick="refreshCode();" href="javascript:;">刷新</a>
   </dt>
   <dt>
     <i class="fa fa-shield" style="top:17px;"></i>
     <input class="forget_box200" placeholder="请输入验证码" name="code" id="code" type="text" maxlength="6">
     <a href="javascript:;" id="fsyzm">获取验证码</a>
   </dt>
   <dd><input class="forget_btn" id="forget_btn" type="button" value="下一步"></dd>
 </dl>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
<script>
	function refreshCode(){
		$("#verifycodeimg").prop("src","<%=basePath%>register/verifycode.json?date="+new Date().getTime());		
	}


</script>
</body>
</html>