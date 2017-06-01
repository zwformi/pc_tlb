<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
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
<title>一键需求---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_file.js"></script>
<script src="<%=basePath %>js/one_demand.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">
	$(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_demand.json", swf: "<%=basePath%>js/uploader/uploader.swf" });
    });
    $(document).ready(function(){
     if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/8./i)=="8."){ 
	  $("#xuqiu_2").hide();
	} 
	});
</script>
<style type="text/css">
	.upload-path{width:360px;height:30px;border:1px solid #dedede;border-right:none;float:left;margin-left:10px;margin-right:0px;}
</style>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center" >
 <div class="member_content" style="min-height:auto;">
     <h3><strong style="font-size:18px;">一键需求</strong></h3>
     <div class="order_cont" style="min-height:395px;">
       <table class="add_table" width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td colspan="2">标题 ：</td>
         </tr>
       <tr>
         <td colspan="2">
           <input name="title" type="text" class="member_box" id="title" value="">
        </td>
       </tr>
       <tr>
         <td colspan="2">
           <div style="line-height:20px; font-size:12px;">(请您为本次需求填写相关备注说明，我们将帮您轻松完成采购！)</div>
           <textarea class="service_h_box" name="content_sub" id="content_sub"></textarea>
         </td>
       </tr>
       <tr id="xuqiu_2">
         <td colspan="2">
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td style="text-align:left;width:100%;"><input  id="file_url" name="file_url" type="text" readonly="readonly"  class="upload-path" /><div class="upload-img" "></div></td>
             </tr>
             <tr>
             	<td colspan="2">说明：附件大小应小于4M ，类型为excel/word/jpg/png/jpeg/rar/zip/txt 文件</td>
             </tr>
           </table>
         </td>
       </tr>
       <%--
       <tr>
         <th width="15%"><span class="red_zi">* </span>有效日期：</th>
         <td width="85%"><input name="textfield" type="text" class="member_box100 date_ico" id="textfield" value="2015-12-10"></td>
       </tr>
        --%>
       <tr>
         <td>&nbsp;</td>
         <td><input class="red_btn" id="but_sub" name="input" type="button" value="一键发布"></td>
       </tr>
       </table>
     </div>
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
