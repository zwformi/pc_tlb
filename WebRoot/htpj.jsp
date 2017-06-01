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
<title>订单评价---图灵买tbl_order_evaluate</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/raty/jquery.raty.min.js"></script>
<script type="text/javascript">
	 $(function() {
      	$.fn.raty.defaults.path = '<%=basePath%>js/raty/img';
      	$('#score_div').raty({ score: 5 });
      	
      	$("#but_tjpj").click(function(){
      		var score_ = $("#score_div").find("input[name='score']").val();
      		 var pjnr_ = $("#pjnr").val();
      		 if(pjnr_==""){
      		 	layer.msg("请输入评价内容！");
      		 }else{
      		 	//提交评价
           		var order_id=$("#order_id").val();
				$.post("<%=basePath%>order/pj.json",{"order_id":order_id,"score":score_,"content":pjnr_},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("评价成功！",function(){
							parent.location.reload();
						});
					}
		        });
			}
      	});
     });
</script>
</head>

<body>
	<table cellspacing="0" border=0 cellpadding="0" width="100%">
		<tr style="height:50px;line-height:50px;">
			<td style="width:50px;text-align:right;">评分：</td>
			<td id="score_div" style="padding-top:7px;"></td>
		</tr>
		<tr>
			<td style="vertical-align: top;text-align:right;">评语：</td>
			<td>
				<textarea style="width:430px;height:150px;" id="pjnr"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right;padding-right:15px">
				<input id="order_id" type="hidden" value="${param.order_id }"/>
				<input type="button" value="提交评价" name="input" id="but_tjpj" class="red_btn">
			</td>
		</tr>
	</table>
</body>
</html>
