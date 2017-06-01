<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="Keywords"
	content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description"
	content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>账号信息---图灵买</title>
<link rel="stylesheet" href="<%=basePath%>css/cs.css" />
<link rel="stylesheet" href="<%=basePath%>css/font-awesome.css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>/js/layer/layer.js"></script>
<script src="<%=basePath%>/js/member.js?date=<%=new Date()%>"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath%>css/buy.css" />
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css" />

</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="zi_main">
		<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center" >
			<%--<div class="warning_zi"><i class="fa fa-warning"></i>系统通知：新版切换必看 3大问题答疑！ <a href="member_news.asp">点此查看</a></div> --%>
			<div class="member_content">
				<h3>
					<a class="select" href="/user/member_info.html?page=member_info">账号资料</a> <a
						href="/user/member_info_head.html?page=member_info">头像照片</a> <a
						href="/user/member_info_company.html?page=member_info">公司信息</a>
					<c:if test="${loginUser.owning_company!=0 && loginUser.owning_company!=null}">
   					<a href="/user/member_info_contact.html?page=member_info_contact">联系人${loginUser.is_manager==1?"管理":""}</a>
      				 </c:if>
				</h3>
				<ul class="member_zi">
					<li>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="23%">昵称：</th>
								<td width="77%"><input class="member_box"
									value="${loginUser.nick_name }" autocomplete="off" type="text"
									name="nick_name" id="nick_name"> <br>
									4-20个字符，可全部由字母组成，或数字、字母、&quot;_&quot;、任意两种以上组合</td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>性别：</th>
								<td><select class="member_box" name="gender" id="gender" group="user_info" autocomplete="off">

								</select></td>
								<script>
									$("#gender").val("${loginUser.gender}");
								</script>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>邮箱：</th>
								<td><input class="member_box" value="${loginUser.e_mail }"
									type="text" name="e_mail" id="e_mail" autocomplete="off"></td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>真实姓名：</th>
								<td><input class="member_box" value="${loginUser.xm }"
									type="text" name="xm" id="xm" autocomplete="off"></td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>手机号码：</th>
								<td>${loginUser.phone }&nbsp;&nbsp;&nbsp;<a
									href="/change_phone.jsp">修改</a></td>
							</tr>
							<tr>
								<th>行业属性：</th>
								<td><select class="member_box" name="hysx" id="hysx"
									autocomplete="off">
										<option value="">选择您的行业</option>
										<c:forEach items="${list_hysx}" var="hysx" varStatus="status">
											<option value="${hysx.name }">${hysx.name }</option>
										</c:forEach>
								</select> <script>
									$("#hysx").val("${loginUser.hysx}");
								</script></td>
							</tr>
							<tr>
								<th>所在部门：</th>
								<td><select class="member_box" name="owning_department" id="owning_department" group="user_info"
									autocomplete="off">
										<option value="">选择您的所属部门</option>			
								</select> <script>
									$("#owning_department").val("${loginUser.owning_department}");
								</script></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><input class="red_btn" name="" type="button" value="提交"
									id="update_info"></td>
							</tr>
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
<script>
		var my_user_id="${loginUser.user_id}";
		loadSelect();
 		$("#owning_department").val("${loginUser.owning_department}");
		$("#gender").val("${loginUser.gender}");


</script>