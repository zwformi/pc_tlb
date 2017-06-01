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
<title>需求单---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dateformat.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style>
table tr  td{
 text-align:center; 
} 

</style>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">
 
 <div class="member_content" style="min-height:535px;">
     <h3>
       <a  class="select" href="/find/find_demand.html?page=find_demand">需求单</a>
       <a  href="/find/find_contract.html?page=find_contact">订单合同</a>
       <a  href="/find/find_service.html?page=find_service">服务单</a>
     </h3>
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
var pageIndex = 0;     //页面索引初始值
var pageSize =5;     //每页显示条数初始化，修改显示条数，修改这里即可
var statelist = getStringmap('order_info_xuqiu', 'order_state');
var sourcelist = getStringmap('order_info_xuqiu', 'order_source');
var order_state =getQueryString('order_state');

$(document).ready(function(){
	if(order_state==null)
		order_state=-1;
	if(!!order_state&& order_state!=-1){
	var  index= order_state;
	var  tagA = $('a[class="select"]').eq(0);
	tagA.text(tagA.text()+"("+statelist[index+'']+")");
	}
	
	loadproducts();
});
//加载商品入口
function loadproducts(){
	//显示加载层
	layer.load();
    $.post("<%=basePath%>find/getcount.json",{"type":"xuqiu","order_state":order_state},function(data){
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
	var param ={"type":"xuqiu","page":index,"size":pageSize,"order_state":order_state};
	$.post('<%=basePath%>find/paginglist.json',param,function(data){
		layer.closeAll('loading');
		if(data.resMsg=="error"){
	    	layer.msg(data.message);
		}else{
			$('#order_list').html('');//清空、初始化
			var list =data.list;
			for(var i in list){
			var _html='<table class=" table_show1" width="100%" border="0" cellspacing="0" cellpadding="0">'
	       	  +'<thead>'
	           +'<tr >'
	            + '<th style="width:300px;"><span>生成时间:'+new Date(list[i].order_time).Format("yyyy-MM-dd hh:mm:ss")+'</span></th>'
	             +'<th style="width:400px;"><span>订单编号:'+list[i].order_number+'</span></th>	'
	             +'<th style="width:150px;">'
			            +' <span class="iconfont icon-dianpu" style="color:#E3393C"></span>&nbsp;'+sourcelist[list[i].order_source+""]+'</th>'	                         
	            +'<th style="width:130px;"><span class="iconfont icon-profile" style="float:left;color:#E3393C"></span> <span style="float:left;width:80px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;">&nbsp;'+(list[i].xm==null||list[i].xm==""?"未填写":list[i].xm)+'</span></th> '
	           +'</tr>'
	         +' </thead>'
	      + '</table>';
	      _html += '<table class="order_show table_show" width="100%" border="0" cellspacing="0" cellpadding="0">'
	      		+ '<tbody><tr>'
	    	    + '<td   style="width:600px;" class="table_right" order_number="'+list[i].order_number+'">暂无商品信息~~</td>'
	     	    + ' <td  style="width:150px;" class="table_right">￥'+list[i].payment_money+'</td>'
	            + '<td  style="width:150px;" class="table_right" >'
	            + '<span class="iconfont icon-kuaidi" style="color:#E3393C;display:inline-block"></span>';
	            if(list[i].order_state==5)
	            _html+= '<font style="color: #D74340">'+statelist[list[i].order_state]+'</font>';
	            else 
	            _html+= statelist[list[i].order_state+''];
	     	    _html+= ' </td>	'
	      	    + ' <td style="width:200px;">'
	            + '<a style="color:#7fa9ee" href="<%=basePath%>/find/find_edit_demand.html?order_number='+list[i].order_number+'"><i class="fa fa-file-text-o"></i> 订单详情</a>'
	      		+ ' </td>'
	      		+ '</tr>'       	  
	       		+'</tbody></table></br>';
	      
			$('#order_list').append(_html);
		}
		}
	 loadorderproducts();
	});
	
}

 //加载商品信息
    function loadorderproducts(){
    	$("td[order_number]").each(function(){
    		var $this = $(this);
	    	var param ={"order_number":$this.attr("order_number"),"type":"xuqiu"};
	    	$.post('<%=basePath%>/find/orderdetail.json',param,function(data){
	    		if(data.resMsg=="error"){
	    			layer.msg(data.message);
	    		}else{
	    			var _html='';
	    			var _data=data.list;
	    			if(_data!=null){
	    				_html='<table  width="100%" border="0" cellspacing="0" cellpadding="0" >';
	    					for(i in _data){
	    						if(i<_data.length-1)
	    						_html+='<tr style="background:url(/img/point.png) bottom repeat-X;"><td><img style="width:50px;" onerror="onerror=null;src=\'/images/default_product.png\'" src="http://img.tulingbuy.com'+_data[i].product_imgurl+'"></td><td style="width:100%;text-align:left;">'+(!!_data[i].xh?(_data[i].xh+_data[i].pz):(_data[i].product_name+_data[i].pz))+'</td><td>&nbsp;&nbsp;×&nbsp;&nbsp;'+_data[i].product_count+'</td></tr>' 
	    						else
	    						_html+='<tr ><td><img style="width:50px;" onerror="onerror=null;src=\'/images/default_product.png\'" src="http://img.tulingbuy.com'+_data[i].product_imgurl+'"></td><td style="width:100%;text-align:left;">'+(!!_data[i].xh?(_data[i].xh+_data[i].pz):(_data[i].product_name+_data[i].pz))+'</td><td>&nbsp;&nbsp;×&nbsp;&nbsp;'+_data[i].product_count+'</td></tr>' 
	    						
	    					}
	    					
	    				_html+='</table>';
	    				}else{
	    					_html="暂无商品信息~~"
	    					
	    				}
	    				$this.html(_html);

	    		}
	    		});
    	});
    }
</script>
</body>
</html>

