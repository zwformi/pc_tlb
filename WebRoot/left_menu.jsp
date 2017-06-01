<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>css/icon.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/iconfont.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>css/left_menu.css" rel="stylesheet"
	type="text/css">



<div class="leftmenu">
	<div class="list">
		<div class="title">
			<span class="iconfont icon-chanp0101-copy"></span> <a
				href="/find/find_main.html">我要查</a>
		</div>
		<ul class="menuson">
<!-- 			<li><a href="/find/find_demand.html?page=find_demand" page="find_demand">需求单</a></li> -->
			<li><a href="/find/find_contract.html?page=find_contact" page="find_contact">订单合同</a></li>
			<li><a href="/find/find_service.html?page=find_service" page="find_service">服务单</a></li>
			<li><a href="/find/find_contract.html?page=find_evaluate&order_state=7" page="find_evaluate">待评价订单</a></li>
		</ul>
	</div>
	<div class="list">
		<div class="title">
			<span class="iconfont icon-gouwuche"></span> <a href="/buy.json">我要买</a>
		</div>
		<ul class="menuson" id="menusonfortype">
		
			<c:if test="${loginUser.special_code==1 }"><li><a href="/buy/special_list.html">企业内购</a></li></c:if>
<!-- 			<li><a href="/buy.json?typeid=1">电脑整机</a></li>
			<li><a href="/buy.json?typeid=2">办公设备</a></li>
			<li><a href="/buy.json?typeid=3">网络产品</a></li>
			<li><a href="/buy.json?typeid=4">通用软件</a></li>
			<li><a href="/buy.json?typeid=5">IT服务</a></li>
			<li><a href="/buy.json?typeid=14">其他</a></li> -->
		</ul>
	</div>
	<div class="list">
		<div class="title">
			<span class="iconfont icon-fuwu1"></span> <a href="/service.html">贴心服务</a>
		</div>
		<ul class="menuson">
			<li><a href="/service.html?page=after_service" page="after_service">售后服务</a></li>
<!-- 			<li><a href="/service/tostation.html?page=tostation" page="tostation">维修站查询</a></li> -->
			<li><a href="/service/mffw.html?page=free_service" page="free_service">免费服务</a></li>
		</ul>
	</div>
	<div class="list">
		<div class="title">
			<span class="iconfont icon-huiyuankehu"></span> <a
				href="/user/member_info.html">会员中心</a>
		</div>
		<ul class="menuson">
			<li><a href="/user/member_info.html?page=member_info" page="member_info">账户信息</a></li>
			<li><a href="/member_safe.jsp?page=member_safe" page="member_safe">账户安全</a></li>
			<li><a href="/user/member_address.html?page=member_address" page="member_address">收货地址</a></li>
			<li><a href="/user/member_invoice.html?page=member_invoice" page="member_invoice">发票信息</a></li>
			<li> <a href="/user/member_info_contact.html?page=member_info_contact" page="member_info_contact">联系人${loginUser.is_manager==1?"管理":""}</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
<script>
	$().ready(function(){
	 	var page = getQueryString("page");
	 	$(".menuson li a[page]").each(function() {
	 		if($(this).attr("page")==page){	 		
	 		$(this).addClass("active");
	 		}
	 	})
	   getTypeForLeft();
	})
	function getTypeForLeft(){
	    $.post('/menu.json',function(data){
	  		if(data.error){
	  			layer.msg(data.message);
	  		}
	  		else
	  		{	
	  			var temp = "";
	  			ptypes=data.PRODUCTTYPELIST;
	  			for(var i=0;i<ptypes.length;i++)
	  		    {
                    if(ptypes[i]["parentid"]=="0"){
                      temp+="<li><a href='/buy.json?typeid="+ptypes[i]["id"]+"'>"+ptypes[i]["name"]+"</a></li>";
                    }
		  	    }
		  	    $("#menusonfortype").append(temp);	  			
	  		}
	  	  });	
	}
</script>