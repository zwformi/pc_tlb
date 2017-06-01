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
<title>会员登录---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link href="<%=basePath%>css/homepage.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/topstyle.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/jquery.min.js"></script>
<script language="javascript" src="<%=basePath %>js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.md5.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script src="<%=basePath %>/js/login.js?date=<%=new Date()%>"></script>

<style>
.myfoot{
text-align:center;
	display:block;
	width:700px;
	margin:0 auto 0 auto;
	position: absolute;
    left: 50%;
    -ms-transform: translate(-50%,-50%);
    -moz-transform: translate(-50%,-50%);
    -o-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%); 
}
.myfoot li{
	font-size:14px;
	color:#ccc;
	display:inline-block;
	margin:5px;
}
#sign_window{
background-color: #fff;
position:absolute;
right:5%;
top:130px;
z-index: 3;
border: none;
}
</style>
</head>

<body style="background-color:#FAFAFA;height:auto;min-height:500px;">
<!-- top -->
  <div class="home_top" style="height:62px;">
        <div class="container">
            <a href="/"><img src="#" class="left" id="logo_main" width="240px" height="62px"></a>
            <ul id="home_topnav" class="left">
                
                <li>
                  <a href="#" class="" >欢迎登录</a>
                   
                <li>
              
               </ul>
		<c:if test="${loginUser==null }">		
            <a href="/register.json" class="right signin">
                <p>快速注册</p>
                <span>现注册享双倍积分</span>
            </a>
            </c:if>
        </div>
    </div>
<div class="register_bj" style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
 <dl class="login_bj" style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
 <div class="home_banner" >
   <span><iframe src="/ad/banner.json" width="1920" height="495" frameborder="0"></iframe></span>
 </div>
   <dd id="sign_window"  >
     <h3 >
        <span>会员登录</span>       
     </h3>
     <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          <th width="23%">用户名：</th>
          <td width="77%"><input class="login_box" type="text" name="phone" id="phone" placeholder="手机号码"></td>
        </tr>
        <tr>
          <th>密码：</th>
          <td><input class="login_box"  name="password_str" id="password_str" type="password" placeholder="密码"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <label><input class="input_align_5" type="checkbox" id="rememberPassword" name="rememberPassword">自动登录</label>
            <span style="margin-left:125px;"><a href="forget/step1.json" style="color:#666">忘记密码?</a></span>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="login_btn" type="submit" name="button"  id="login" value="登 录"></td>
        </tr>
      </table>
   </dd>
 </dl>
  <div style="clear:both;"></div>
</div>
<script type="text/javascript">
if(localStorage.getItem("logo")){
  $("#logo_main").attr("src",localStorage.getItem("logo"));
}
</script>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
