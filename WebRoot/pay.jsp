<%@ include file="common/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>支付中心 —— 图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/paystyle.css">
<link href="<%=basePath%>css/homepage.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />
<link rel="stylesheet" href="<%=basePath %>css/homepage.css" />
<script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>js/jquery.qrcode.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath%>js/top_area.js?1"></script>

<style>
.nav_dropdown ul {
	list-style-type: none;
	text-align: left;
	left: -999em;
	position: absolute;
	padding: 0px;
	margin: 0px;
	z-index: 100
}

.nav_dropdown ul li {
	float: left;
	background: #fff;
	padding: 0px;
	margin: 0px
}

.nav_dropdown:hover ul {
	left: auto;
	padding: 0px;
	margin: 0px
}

.p_details {
	cursor: pointer;
}
</style>
</head>

<body>
	<div class="home_title" id="home_title">
		<div class="container">
			<ul class="left">
				<c:if test="${loginUser==null }">
					<li>Hi！欢迎来图灵买</li>
					<li><a href="/login.html">请登录</a></li>
					<li><a href="/register.json">快速注册</a></li>
				</c:if>
				<c:if test="${loginUser!=null }">

					<li>Hi，${loginUser.xm } 欢迎登录</li>
					<li><a href="/login/out.html">退出</a></li>
				</c:if>
			</ul>
			<ul class="right">

				<li><a href="<%=basePath%>find/find_main.html">我的订单</a></li>
				<li><a href="/user/member_info.html">个人中心</a></li>
				<li><a href="/cart.html"><span
						class="iconfont icon-gouwuche"
						style="display:inline-block;font-size:16px;color:#fff"></span> 采购车
				</a></li>
				<li></li>

				<li class="nav_dropdown"><a href="#" style="padding-right:0px"><span
						class="iconfont icon-weixin"
						style="display:inline-block;font-size:14px;color:#fff;margin-right:3px"></span>关注图灵买<span
						class="iconfont icon-xiala"></span></a>
					<ul>
						<li><img src="http://www.tulingbuy.com:80/images/2d_img.jpg"
							width="100" height="100" /></li>
					</ul></li>
				<li><a class="head-top"
					href="http://kefu.qycn.com/vclient/chat/?websiteid=112607"
					target=_blank title="客服"> <span class="iconfont icon-kefu"
						style="display:inline-block;font-size:14px;color:#fff;margin-right:3px"></span>客服中心
				</a></li>
			</ul>
		</div>
	</div>
	<div class="pay_title">
		<div class="pay">
			<span><img src="<%=basePath%>img/paylogo.png" class="left"></span>收银台
		</div>
	</div>
	<!-- pay start -->
	<c:if test="${orderdetial.order_number!=null }">
		<div class="pay_top">
			<div class="pay">
				<div class="left">
					<p>
						订单编号：<span id="order_number">${orderdetial.order_number }</span>
					</p>
					<p>
						订单类型：<span>线上支付</span>
					</p>
					<!-- 订单详情（预留） -->
					<%-- 					<c:if test="${ order_detail_list!=null}">
					<p>订单明细：</p>
					
					<c:forEach items="${ order_detail_list}" var="item" varStatus="state">
					<p>
					<span>${item.product_name }</span>
					<span>${item.product_count }</span>
					</p>
					</c:forEach>
					

					</c:if> --%>
				</div>
				<div class="right">
					<p class="paymoney">
						应付金额：<span>￥${orderdetial.payment_money}</span>
					</p>
					<a class="p_details"> 商品详情<i class="iconfont icon-xiala"></i>
					</a>
				</div>
			</div>
		</div>
		<c:if test="${ order_detail_list!=null}">
			<div class="pay_details" style="display:none;">
				<div class="pay">
					<div>
						收货地址：<span>${ orderdetial.shr_dz}</span> 收货人：<span>${orderdetial.shr_xm }</span>
						联系电话：<span>${orderdetial.shr_dh }</span>
					</div>

					<div>
						<p class="left">商品清单：</p>
						<p class="left">
							<c:forEach items="${order_detail_list }" var="odetail"
								varStatus="status">
					${odetail.product_name } * ${odetail.product_count }
					<c:if test="${status.index<(fn:length(order_detail_list)-1) }">
									<br>
								</c:if>
							</c:forEach>
						</p>

					</div>

				</div>
			</div>
		</c:if>
		<div class="pay_main">
			<div class="pay">
				<div class="pay_menu">
					<ul>
						<li class="item active">微信支付 <span> <img
								src="<%=basePath %>img/wxpay/tag.png">
						</span>
						</li>
						<li class="item" style="display:none">储蓄卡</li>
						<li class="item" style="display:none">信用卡</li>
						<li class="item" style="display:none">第三方支付</li>
					</ul>
				</div>
				<div class="pay_menudiv">
					<div class="menuitem">
						<div class="menutitle" style="display:none;">
							<img src="<%=basePath %>img/wxpay/WePayLogo.png"> <span><img
								src="<%=basePath %>img/wxpay/tagH.png"></span> <span>亿万用户的选择，更快更安全</span>
							<p>
								实付：<span>78.40</span>元
							</p>
						</div>
						<div class="menutitle">
							<img src="<%=basePath %>img/wxpay/WePayLogo.png">
						</div>
						<div class="Codediv">
							<p></p>
							<br />
							<div id="QR_Code"></div>
							<input id="str" type="hidden" value="${QRStr}" /> <img
								src="<%=basePath %>img/wxpay/text.png">
						</div>
					</div>
					<div class="menuitem" style="display:none;">
						<a href="#"><img src="img/95566.png"></a> <a href="#"><img
							src="img/95588.png"></a> <a href="#"><img
							src="img/95599.png"></a> <a href="#"><img
							src="img/95533.png"></a> <a href="#"><img
							src="img/95595.png"></a> <a href="#"><img
							src="img/95555.png"></a> <a href="#"><img
							src="img/95558.png"></a> <a href="#"><img
							src="img/95580.png"></a> <a href="#"><img
							src="img/95577.png"></a> <a href="#"><img
							src="img/95568.png"></a> <a href="#"><img
							src="img/95559.png"></a> <a href="#"><img
							src="img/95561.png"></a> <a href="#"><img
							src="img/95508.png"></a> <a href="#"><img
							src="img/95528.png"></a> <a href="#"><img
							src="img/pinganyinhang_xin.jpg"></a>
					</div>
					<div class="menuitem" style="display:none;">
						<a href="#"><img src="img/95566.png"></a> <a href="#"><img
							src="img/95588.png"></a> <a href="#"><img
							src="img/95599.png"></a> <a href="#"><img
							src="img/95533.png"></a> <a href="#"><img
							src="img/95595.png"></a> <a href="#"><img
							src="img/95555.png"></a> <a href="#"><img
							src="img/95558.png"></a> <a href="#"><img
							src="img/95580.png"></a> <a href="#"><img
							src="img/95577.png"></a> <a href="#"><img
							src="img/95568.png"></a> <a href="#"><img
							src="img/95559.png"></a> <a href="#"><img
							src="img/95561.png"></a> <a href="#"><img
							src="img/95508.png"></a> <a href="#"><img
							src="img/95528.png"></a> <a href="#"><img
							src="img/pinganyinhang_xin.jpg"></a>
					</div>
					<div class="menuitem" style="display:none;">
						<a href="#"><span></span>支付宝</a> <a href="#"><span></span>付财通</a>
						<a href="#"><span></span>易宝支付</a> <a href="#"><span></span>快钱</a>
					</div>
				</div>
				<!-- 				<div class="pay_next">
					<a href="#">下一步</a>
				</div> -->
			</div>


		</div>
	</c:if>
	<!-- pay end -->
</body>
<script>
	var timer,timer2;
	var count=60; 
	var DEFAULT_VERSION = "8.0";
	var ua = navigator.userAgent.toLowerCase();
	var isIE = ua.indexOf("msie")>-1;
	var safariVersion;
	var type="canvas";

	$(document).ready(function setCoder() {
			if(isIE){
	    safariVersion =  ua.match(/msie ([\d.]+)/)[1];
	}
	if(safariVersion <= DEFAULT_VERSION ){
	    type="table";
	}
		if($("#str").val()!=null && $("#str").val()!=''){
		 $(".Codediv").eq(0).find("p").html('二维码即将在<span id="qr_second" style="font-size: 20px;color:red;padding:0 5px;">60</span>秒后过期');
		 timer = setInterval(setTimer, "1000"); 
		 timer2 = setInterval(queryState,"2000");
		$("#QR_Code").qrcode({
			render : type,
			width : 200,
			height : 200,
			text : $("#str").val()
		})

		$(".item").click(function() {
			$(this).addClass("active").siblings().removeClass("active");
			var i = $(".item").index(this);
			$(".pay_menudiv>div").eq(i).css({
				"display" : "block"
			}).siblings().css({
				"display" : "none"
			});
			$(".menuitem").find("a").removeClass("active");
		});
		$(".menuitem>a").click(function() {
			$(this).addClass("active").siblings().removeClass("active");
		})
		
		}else{
						layer.msg('二维码生成失败,即将返回..',{icon: 2,time: 2000,shade:0.1},function(){
								location.href="<%=basePath%>find/find_contract.html";
						});
		}
	});
	

	
	function setTimer(){
		count--;
		if(count>0){
		$("#qr_second").text(count);
		}else{
		clearInterval(timer);
			$("#QR_Code").html("");
			$("#QR_Code").qrcode({
			render : type,
			width : 200,
			height : 200,
			text : "weixin://wxpay/bizpayurl?pr=IbfC3E5"
		});
		$(".Codediv").eq(0).find("p").html("二维码已过期<a style='color:red;' href='javascript:void(0)' onclick='location.reload()'>刷新</a>重新获取"); 
		}
	}
	
	/*商品详情*/
	$(".p_details").click(function(){
		if($(".pay_details")[0].style.display=="none"){
			$(".pay_details").slideDown('slow');
			
		}else{
			$(".pay_details").slideUp('slow');
		}
	});	
	function queryState(){
		var ordernum = $("#order_number").text();
		$.ajax({
		url:"<%=basePath%>pay/getstate.json",
		type:"post",
		data:{"order_number":ordernum},
		dataType:"json",
		success:function(data){
			if(data.res_code==1){
				if(timer!=null){
				clearInterval(timer);
				clearInterval(timer2);
				}
			 	
						layer.msg('付款成功,即将跳转..',{icon: 1,time: 1000,shade:0.1},function(){
								location.href="<%=basePath%>find/find_contract.html";
						});
			}
		}
		});
	}
	
</script>
</html>
