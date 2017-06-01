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
<title>发票信息---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/member.js?date=<%=new Date()%>"></script>
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
     <h3><strong>发票信息</strong></h3>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th align="left"><strong style="display:block; text-align:left; padding-left:20px;">类型和抬头</strong></th>
                 <td>&nbsp;</td>
            </tr>
               <tr>
                 <th width="23%"><span class="red_zi">* </span>发票类型：</th>
                 <td width="77%">
                   <label><input class="input_align_5" type="radio" name="type" value="普通发票" id="RadioGroup1_0">普通发票</label>
                   <label><input name="type" type="radio" class="input_align_5" id="RadioGroup1_1" value="增值发票" checked="CHECKED">增值发票</label>
                   <script type="text/javascript">
						$("input[name='type'][value='${USERINVOICES.type}']").attr("checked",true); 
					</script>
                 </td>
               </tr>
               <tr>
                 <th><strong style="display:block; text-align:left; padding-left:20px;">发票信息</strong></th>
                 <td>&nbsp;</td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>单位名称：</th>
                 <td><input class="member_box" type="text" name="dwmc" id="dwmc" value="${USERINVOICES.dwmc }"></td>
               </tr>
               <tbody id="zpinfo" <c:if test="${USERINVOICES.type=='普通发票'}">style="display:none;"</c:if>>              
               		<tr>
	                 <th><span class="red_zi">* </span>纳税人识别号：</th>
	                 <td><input class="member_box" type="text" name="nsrsbh" id="nsrsbh" value="${USERINVOICES.nsrsbh }"></td>
	               </tr>
	               <tr>
	                 <th><span class="red_zi">* </span>注册地址：</th>
	                 <td><input class="member_box" type="text" name="zcdz" id="zcdz" value="${USERINVOICES.zcdz }"></td>
	               </tr>
	               <tr>
	                 <th><span class="red_zi">* </span>注册电话：</th>
	                 <td><input class="member_box" type="text" name="zcdh" id="zcdh" value="${USERINVOICES.zcdh }"></td>
	               </tr>
	               <tr>
	                 <th><span class="red_zi">* </span>开户行：</th>
	                 <td><input class="member_box" type="text" name="khh" id="khh" value="${USERINVOICES.khh }"></td>
	               </tr>
	               <tr>
	                 <th><span class="red_zi">* </span>银行对公账号：</th>
	                 <td><input class="member_box" type="text" name="yhdgzh" id="yhdgzh" value="${USERINVOICES.yhdgzh }"></td>
	               </tr>
               </tbody>
               <tr>
                 <th><strong style="display:block; text-align:left; padding-left:20px;">收票人信息</strong></th>
                 <td>地址不对，我要<a href="/user/member_address.html">修改</a></td>
               </tr>
               <tr>
                 <th>收票人姓名：</th>
                 <td>${USERADDRESS.consignee_name }</td>
               </tr>
               <tr>
                 <th>收票人手机：</th>
                 <td>${USERADDRESS.phone }</td>
               </tr>
               <tr>
                 <th>邮编：</th>
                 <td>${USERADDRESS.post_code }</td>
               </tr>
               <tr>
                 <th>详细地址：</th>
                 <td>${USERADDRESS.address }</td>
               </tr>
               <tr>
                 <th>&nbsp;</th>
                 <td>&nbsp;
                 <input class="red_btn" name="input" type="button" id="save_invoice" value="保存设置" /></td>
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
