<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="Keywords"
	content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description"
	content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<meta name="baidu-site-verification" content="4cxqzes1K1" />
<title>图灵云</title>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css" />
<link rel="stylesheet" href="<%=basePath %>css/yun_page.css" />
<link rel="stylesheet" href="<%=basePath %>css/cs.css" />
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />

<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath%>js/yun_page.js?1"></script>

</head>

<body>
	<!-- top -->
	<jsp:include page="/yun/yun_top.jsp"></jsp:include>
	

	<!--banner-->
	<div class="register_bj"
		style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
		<dl class="login_bj"
			style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
			<div class="home_banner">
				<span><iframe src="/ad/banner.json?typeid=4" width="1920" height="495"
						frameborder="0"></iframe></span>
			</div>
		</dl>
	</div>
	<!-- 方格模块 -->
	<div class="home_main" style="height:120px;">
		<div class="container" >
			<ul>
				<li><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target="_blank"> <i class="iconfont icon-kefu"></i>
						<p class="left">
							我要问<br> <span>询问产品、服务、软件、价格</span>
						</p>
				</a></li>
				<li><a href="/buy.html"> <i class="iconfont icon-caigou"></i>
						<p>
							我要采购<br> <span>采购产品、软件、服务</span>
						</p>
				</a></li>
				<li><a href="/service.html"> <i class="iconfont icon-fuwu"></i>
						<p>
							贴心服务<br> <span>售后服务、咨询服务、产品服务</span>
						</p>
				</a></li>
				<li class="last_li"><a href="/article/3.json"> <i class="iconfont icon-0048jihedingdan"></i>
						<p>
							解决方案<br> <span>集成、虚拟化、数据备份、办公软件</span>
						</p>
				</a></li>
			</ul>
		</div>
	</div>
	
	<!-- service -->
	<div class="text_container">
		<div class="title">
		<p>我们可以提供哪些服务</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-vipdakehu" style="color:#FFF68F"></i>
		<p>一对一专属客服经理</p>
		<p>第一时间答疑解惑</p>
		</div>
		
		
	</div>
	
	<!--详细介绍-->
	<div class="func_intro">
		<!-- 背景div -->
		<div class="background"></div>
	
		<div class="inner">
		<div class="top_part">
			<h2 class="title">在云端继续你的业务</h2>
			<h3 class="des_text">不管你处于什么行业，图灵云已定制多套解决方案，助你轻松跨入“互联网+”时代</h3>
		</div>
		<div class="left_part">
			<ul class="group">
			</ul>
		</div>
		<div class="right_part">
			<ul class="func_boxlist">
				<li></li>
				<li></li>
				</ul>
			<div class="intro_info">
				<div class="title"></div>
				<div class="text"></div>
				<div  class="details_btn"><a>查看详情</a></div>
			</div>
		</div>
		</div>
	
	</div>
	
	<!-- feature-boxes -->
	<div class="feature-boxes">
		<ul class="feature-types">
		</ul>
		<ul class="feature-box">
		</ul>
	</div>
	
	
		<!-- 合作伙伴 -->
	<div class="text_container">
		<div class="title">
		<p>合作伙伴</p>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		<div class="card">
		<i class="iconfont icon-1png"></i>
		</div>
		
		
	</div>
	<jsp:include page="/yun/detail-banner.jsp"></jsp:include>
	<jsp:include page="/yun/yun_model1.jsp"></jsp:include>
	
	<div class="clear"></div>
	<!--20160810-->
	<jsp:include page="/bottom.jsp"></jsp:include>

        <a class="radius" onclick="doPost(4);">立即购买</a>
<script type="text/javascript">
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?f7000683e1a3d9f334072a297bb880ee";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s); 
		})();
		
		function doPost(resource){
			var _html ='立即购买'+
			   '<form action="" name="carts" id="carts" method="post" style="display:none;">'
   			   +'<input name="products" type="checkbox" value="286-1-0" checked="checked">'
	           +'<input name="resource" value="'+resource+'">'
	           +'<input name="product_counts" class="buy_box number"  value="1" autocomplete="off" readonly="readonly">'
	           +'<input name="button"  class="btn_jie add" type="button" id="button" value="+">' 	
        	   +'<input name="content" type="text" readonly="readonly" class="upload-path">'  	
        	   +'<input name="file_url" type="text" readonly="readonly" class="upload-path">'
        	   +'<input name="file_url2" type="text" readonly="readonly" class="upload-path">'
        	   +'<input name="file_url3" type="text" readonly="readonly" class="upload-path">'
        	   +'</form>';
			$(".radius").html(_html);
	    	$("#carts").attr("action","<%=basePath%>order/gwcdd.html");//设置请求地址
	    	$("#carts").submit();
		}
</script>



</body>
</html>
