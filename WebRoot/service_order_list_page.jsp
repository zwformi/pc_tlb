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
<title>订单合同---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<script type="text/javascript" src="<%=basePath %>js/dateformat.js"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style>
table tr td{
 text-align:center; 
} 

.table_show th {
text-align:center; 
padding:0;
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
	   <a class="select" href="/service.html?page=after_service">售后服务</a>
   <!--     <a href="/service/tostation.html?page=tostation">维修站查询</a> -->
       <a href="/service/mffw.html?page=free_service">免费服务</a>
     </h3>
           <div class="order_top">
       <strong>可申请售后的商品信息</strong>
       <span><input class="red_btn" style="margin-top:-10px;" type="button" value="添加服务单" onClick="location='/add_service.jsp'"/></span>
     </div>
     <div style="padding:30px 15px 40px 15px;">
       <div id="order_list">
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
	var sourcelist = getStringmap('order_info', 'order_source');
	var pageIndex = 0;     //页面索引初始值
	var pageSize =5;     //每页显示条数初始化，修改显示条数，修改这里即可
$(document).ready(function(){
	
	loadproducts();
});


//加载商品入口
function loadproducts(){
	//显示加载层
	layer.load();
    $.post("<%=basePath%>find/getcount.json",{"type":"afterservice"},function(data){
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
	var param ={"type":"afterservice","page":index,"size":pageSize};
	$.post('<%=basePath%>find/paginglist.json',param,function(data){
		layer.closeAll('loading');
		if(data.resMsg=="error"){
	    			layer.msg(data.message);
		}else{
			$('#order_list').html('');//清空、初始化
			var list =data.list;
			for(var i in list){
			var _html='<table class=" table_show1" width="100%" border="0" cellspacing="0" cellpadding="0"  >'
	       	  +'<thead>'
	           +'<tr >'
	             +'<th style="width:30%;"><span>订单编号:'+list[i].order_number+'</span></th>	'
	             + '<th style="width:30%;"><span>生成时间:'+new Date(list[i].order_time).Format("yyyy-MM-dd hh:mm:ss")+'</span></th>'
	             +'<th style="width:25%;">'
			            +' <span class="iconfont icon-dianpu" style="color:#E3393C"></span>'+sourcelist[list[i].order_source+""]+'</th>'	                        
	           + '<th style="width:15%;"><span class="iconfont icon-profile" style="float:left;color:#E3393C"></span>'+list[i].shr_xm+'</span></th>'
	           +'</tr>'
	         +' </thead>'
	      + '</table>';
	      _html += '<table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0" order_number="'+list[i].order_number+'"></table><br>';

			$('#order_list').append(_html);
		}
		}
	});
	
	 loadorderproducts();
}


 //加载商品信息
    function loadorderproducts(){
    	$("table[order_number]").each(function(){
    		var $this = $(this);
	    	var param ={"order_number":$this.attr("order_number"),"type":"order"};
	    	$.post('<%=basePath%>find/orderdetail.json',param,function(data){
	    		if(data.resMsg=="error"){
	    			layer.msg(data.message);
	    		}else{
	    			var _html='';
	    			var _data=data.list;
	    			if(_data!=null){
	    				_html+='<thead ><th >商品</th><th >名称</th><th style="width:50px;">类型</th><th style="width:50px;">品牌</th><th style="width:150px;">型号</th><th style="width:100px;">售后</th></thead>';
	    					for(i in _data){
	    						_html+=	'<tr>'
	    								+'<td style="width:100px;"><img style="width:50px;" onerror="onerror=null;src=\'/images/default_product.png\'" src="http://img.tulingbuy.com'+_data[i].product_imgurl+'"></td>'
	    								+'<td style="width:150px;">'+_data[i].product_name+'</td>'
	    								+'<td style="width:50px;">'+_data[i].type_str+'</td>'
	    								+'<td style="width:50px;">'+_data[i].brand_str+'</td>'
	    								+'<td style="width:150px;">'+_data[i].xh+'</td>'
	    								+'<td style="width:100px;"><span><a style="color:red;" href="/service/toadd.html?order_number='+_data[i].order_number+'&order_detail_id='+_data[i].id+'">申请</a></span></td>'
	    								+'</tr>';
	    						
	    					}
	    				
	    				}else{
	    					_html='<tr><td><span>暂无商品信息~~</span></td></tr>';
	    					
	    				}
	    				$this.html(_html);

	    		}
	    		});
    	});
    }
</script>
</body>
</html>

