<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="Keywords"
	content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description"
	content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>注册---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css" />
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />
<script src="<%=basePath %>/js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.md5.js"></script>
<script src="<%=basePath %>/js/register.js?date=<%=new Date()%>"></script>
<style>

#companylist>ul{
display: block; 
width:345px;
background-color:#fff;
z-index:3;
position:absolute;
padding:5px;
border: 1px solid #cccccc; 
border-radius: 4px; 
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 

}
#companylist li{
line-height:30px;
text-align:center;
display: block; 
text-decoration:none;
border-radius: 4px; 
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
}
#companylist li:hover{
background-color:#0099cc;
color:#fff;
text-decoration:none;
}
#companylist a{
color:#000;
text-decoration:none;
}

</style>
</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="register_bj">
		<div class="register_main">
			<dl>
				<dt>
					<div class="register_title">
						<span><i class="fa fa-user-plus"></i>创建用户</span>
					</div>
					<table class="register_table" width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<th><span class="red_zi">* </span>真实姓名：</th>
							<td><input class="register_box" checkin="true" type="text"
								placeholder="注册人姓名" name="xm" id="xm" maxlength="5" /></td>
						</tr>
						<tr>
							<th><span class="red_zi">* </span>密码：</th>
							<td><input class="register_box" checkin="true"
								type="password" placeholder="账号密码" name="password_str"
								id="password_str" maxlength="30" /></td>
						</tr>
						<tr>
							<th><span class="red_zi">* </span>确认密码：</th>
							<td><input class="register_box" checkin="true"
								type="password" placeholder="确认密码" name="password_str2"
								id="password_str2" maxlength="30" /></td>
						</tr>
						<tr>
							<th><span class="red_zi">* </span>企业邮箱：</th>
							<td><input class="register_box" checkin="true" type="text"
								placeholder="企业邮箱" name="e_mail" id="e_mail" maxlength="80" /></td>
						</tr>
						<tr>
				         <th><span class="red_zi">* </span>公司名称：</th>
				         <td>
				         <ul class="ul_company">
				         	<li> <ul><li style="float:left;"><input class="register_box" style="width:270px!important;" checkin="true" type="text" placeholder="公司名称"  id="search_company" maxlength="100" readonly="readonly"/><input  id="owning_company"  style="display:none;"/></li>
				         	<li style="float:left;" title="添加公司">
				         	<span style="color:#fff;display:inline-block;text-align:center;width:40px;height:40px;background-color:#db433e;margin-top:4px;cursor:pointer;" onclick="addCompany();"><i class="iconfont icon-gongsi" style="font-size:20px;"></i></span>
				         	</li>
				         	<li style="float:left;margin-left:1px;" title="加入公司">
				         	<span style="color:#fff;display:inline-block;text-align:center;width:40px;height:40px;background-color:#256c94;margin-top:4px;cursor:pointer;" onclick="joinCompany();"><i class="iconfont icon-sousuokuangsousuo" style="font-size:20px;"></i></span>
				         	</li>
				         	</li>
							<li id="companylist"></li>
				         </ul>
				        
				         
				         </td>
				        </tr>
						<tr>
							<th>从事行业：</th>
							<td><select name="hysx" id="hysx" class="register_box">
									<c:forEach items="${list_hysx}" var="hysx" varStatus="status">
										<option value="${hysx.name }">${hysx.name }</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>所属部门：</th>
							<td><select id="owning_department" name="owning_department"
								group="user_info" class="register_box"></select></td>
						</tr>

						<tr>
							<th><span class="red_zi">* </span>手机号码：</th>
							<td><input class="register_box" checkin="true" type="text"
								placeholder="登录时使用手机号码" name="phone" id="phone" maxlength="11" /></td>
						</tr>
						<tr name="verifycode">
							<th><span class="red_zi">* </span>图形验证码：</th>
							<td>
								<ul>
									<li style="float:left;margin-left:5px;"><input
										class="register_box" checkin="true" type="text"
										style="width:100px;" placeholder="图形验证码" name="text"
										id="verifycode" maxlength="4" /></li>
									<li style="float:left;margin-left:5px;"><img
										onclick="refreshCode();" id="verifycodeimg" width="130px"
										src="<%=basePath%>register/verifycode.json"></li>
									<li style="float:left;margin-left:5px;"><a
										style="width:50px;" onclick="refreshCode();"
										name="refreshcode" id="refreshcode" />刷新</a></li>
									<!--             <li style="float:left;margin-left:5px;"><a  style="width:50px;" name="submitcode" id="submitcode"/>提交</a></li> -->
								</ul>




							</td>
						</tr>
						<tr name="yzm">
							<th><span class="red_zi">* </span>短信验证码：</th>
							<td><input class="register_box" checkin="true"
								style="width:100px;float:left;" type="text" placeholder="短信验证码"
								name="code" id="code" maxlength="6" /><a
								style="margin-left:20px;width:100px;display:block;float:left;"
								name="fsyzm" id="fsyzm" flag="" />获取验证码</a></td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><label><input class="input_align_5"
									type="checkbox" name="checkbox" id="checkboxty">我已阅读并同意</label>
								<a href="<%=basePath %>article/4.json" target="_blank">《图灵买服务协议》</a>
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input class="register_btn" id="register_btn"
								type="button" value="立即注册" /></td>
						</tr>
					</table>
				</dt>
				<dd>
					<span> 我已有账号了？<br> <a href="/login.jsp">直接登录</a>
					</span>
				</dd>
				<div style="clear:both;"></div>
			</dl>
		</div>
		<div style="clear:both;"></div>
	</div>
	<!--foot-->
	<jsp:include page="foot.jsp"></jsp:include> 
</body>
</html>
<script type="text/javascript">
	var owning_company=0;
	var company_name='';
	var company_data={};
	loadSelect();
	function refreshCode(){
		$("#verifycodeimg").prop("src","<%=basePath%>register/verifycode.json?date="+new Date().getTime());		
	}

	function joinCompany(){
					layer.open({
					  type: 2,
					  title: '加入公司',
					  shadeClose: false,
					  shade: 0.1,
					  area: ['500px', '450px'],
					  content: '<%=basePath%>join_company.jsp'
					}); 
	}
	function addCompany(){
					layer.open({
					  type: 2,
					  title: '创建公司',
					  shadeClose: false,
					  shade: 0.1,
					  area: ['850px', '600px'],
					  content: '<%=basePath%>add_company.jsp'
					}); 
	}

</script>
