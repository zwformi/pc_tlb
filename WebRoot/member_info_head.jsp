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
<title>头像照片---图灵买</title>
<link rel="stylesheet" href="<%=basePath%>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath%>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<script src="<%=basePath %>/js/member.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">
	var my_user_id="${loginUser.user_id}";
	$(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_head.json", swf: "<%=basePath%>js/uploader/uploader.swf" });
        //用户头像加载错误事件
		$("img.head_imgab_").on("error",function(){
			$(this).attr("src","/images/default_img.png");
		});
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
   <%--<div class="warning_zi"><i class="fa fa-warning"></i>系统通知：新版切换必看 3大问题答疑！ <a href="member_news.asp">点此查看</a></div> --%>
   <div class="member_content">
     <h3>
					<a href="/user/member_info.html?page=member_info">账号资料</a> 
					<a class="select" href="/user/member_info_head.html?page=member_info">头像照片</a> 
					<a href="/user/member_info_company.html?page=member_info">公司信息</a>
					<c:if test="${loginUser.owning_company!=0 && loginUser.owning_company!=null}">
   					<a href="/user/member_info_contact.html?page=member_info_contact">联系人${loginUser.is_manager==1?"管理":""}</a>
      				 </c:if>
     </h3>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%">当前头像：</th>
                 <td width="77%"><img class="head_imgab_" id="img_head" src="${loginUser.headimgurl_page }" width="110" height="110"><br></td>
               </tr>
               <tr>
                 <th>选择：</th>
                 <td><input id="img_url" name="img_url" type="hidden" class="dfinput upload-path" /><div class="upload-box upload-img" style="top:-3px; left:-1px"></div></td>
               </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td><input class="red_btn" name="" type="button" id="update_img" value="修改头像"></td>
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
