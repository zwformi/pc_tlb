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
<title>服务单 —— 图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dateformat.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style>
table tr  td{
 text-align:center; 
} 

</style>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">
 
 <div class="member_content" style="min-height:535px;">
     <h3>
 	   <!-- <a  href="/find/find_demand.html?page=find_demand">需求单</a> -->
       <a  href="/find/find_contract.html?page=find_contact">订单合同</a>
       <a  class="select" href="/find/find_service.html?page=find_service">服务单</a>
     </h3>
     <div style="padding:30px 15px 40px 15px;">
       <div id="order_list"  class="order_cont" style="min-height:395px;">
            </div>

     </div>
   </div>
            <!--page start-->
   <div class="zi_page" id="pagination" style="border:0px;">
     
   </div>
   <div style="clear:both; height:10px;"></div>
   <!--page end  -->
</div>
 <div style="clear:both;"></div>
</div>

<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>

<script>

var statelist = getStringmap('service', 'state');
var pageIndex = 0;     //页面索引初始值
var pageSize =5;     //每页显示条数初始化，修改显示条数，修改这里即可
$(document).ready(function(){
	
	loadproducts();
});

//加载商品入口
function loadproducts(){
	//显示加载层
	layer.load();
    $.post("<%=basePath%>find/getcount.json",{"type":"service"},function(data){
    	if(data.count>0){
    	$("#total").html(data.count);
    	
    	//分页事件
        $("#pagination").pagination(data.count, {
            prev_text: "上一页",
            next_text: "下一页",
            //每页显示的条目数
            items_per_page: pageSize,
            //当前选中的页面
            current_page: pageIndex,
            //两侧显示的首尾分页的条目数。可选参数，默认是0
            num_edge_entries: 2,
            //连续分页主体部分显示的分页条目数。可选参数，默认是10
            num_display_entries:8,
            //分页选择回调
            callback: pageSelectCallback
        });
        }else
        {
        layer.closeAll();
 var _txt='<div style="width:100%;height:100%;text-align:center;color:red;"><i class="fa fa-file-text-o"></i>&nbsp;没有任何数据~~~</div>';
        $('#order_list').html(_txt);
        }
        
    });
    
}
//加载商品信息-回调函数
function pageSelectCallback(index, jq){
	layer.closeAll();
	layer.load();
	$("#order_list").html("");//情空之前加载的
	var param ={"type":"service","page":index,"size":pageSize};
	$.post('<%=basePath%>find/paginglist.json',param,function(data){
		layer.closeAll('loading');
		if(data.resMsg=="error"){
	    	layer.msg(data.message);
		}else{
			$('#order_list').html('');//清空、初始化
			var list =data.list;
			if(list!=null && list.length>0){
			var _html=' <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0">'
		            + '<thead>'
		            + '<tr>'
		            + '<th style="width:200px;">服务单号</th>'
		            + '<th style="width:100px;">联系人</th>'
		            + '<th style="width:150px;">联系电话</th>'
		            + '<th style="width:200px;">发布时间</th>'
		            + '<th style="width:100px;">服务状态</th>'
		            + '<th style="width:250px;">服务备注</th>'
		            + '<th style="width:100px;">操作</th>'
		            + '</tr>'
		            + '</thead>';
		          _html += '<tbody>';
			for(var i in list){
	      _html += ' <tr>'
             +  '<td><span>'+list[i].service_number+'</span></td>'
             +  ' <td><span>'+(list[i].lxr==null||list[i].lxr==""?"未填写":list[i].lxr)+'</span></td>'
             +  '<td><span>'+list[i].phone+'</span></td>'
             +  '<td><span>'+new Date(list[i].add_time).Format("yyyy-MM-dd")+'</span></span></td>'
             +  '<td><span>'
             +  statelist[list[i].state]
         	 +	'</span></td>'
             +  '<td style="text-align:center;">'+list[i].comment+'</td>'
             +  '<td style="padding-right:2%;">'
             +  '<span>'
             +  ' <a href="/find/find_edit_service.html?service_number='+list[i].service_number +'">查看</a>'
             +  '</span>'
             +  '</td>'
             +  '</tr>';
		}
		     _html += '</tbody>'
                   +  '</table>';
             $('#order_list').append(_html);
		}else{
		var _html=' <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>';
		_html +='<tr><td colspan="7">您还没有任何的服务单</td></tr></tbody></table>';
		$('#order_list').append(_html);
		}
		}
	});

}




 
</script>
</body>
</html>

