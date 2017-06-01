<%@ include file="../common/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="<%=basePath%>css/yun_page.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/icon.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<style media="screen" type="text/css">
	.detail-div{
		width:100%;
	}
</style>
<script src="<%=basePath %>js/layer/layer.js"></script>




	<div class="detail-div">
	<div class="detail-banner" style="background:#0099CC">
	<div class="inner-bg">
		<div class="left-content" data-animation="fade-in" data-timeout="400">
		<div class="title"><h2>电商解决方案</h2></div>
		<div class="content"><p>安全省心 全面护航</p></div>
		<div class="banner-btn"><a>联系我们</a></div>
	</div>
 	<div class="right-pic" ><img src="/images/bg1.png" height="250px"></div> 
	</div>

	</div>
		<div class="banner_btm">
			<ul>
				<li>
				<a href="" style="border-left:1px solid #C0C0C0">
						<p class="title">
							从容应对高并发
						</p>
						<i class="iconfont icon-kefu"></i>
						<p class="content">使用break-word时，是将强制换行。中文没有任何问题，英文语句也没问题。但是对于长串的英文，就不起作用。</p>
						<p class="btn"><span>→</span></p>
						<p class="content2">keep-all，是指Chinese, Japanese, and Korean不断词。即只用此时，不用word-wrap，中文就不会换行了。（英文语句正常。）</p>
						
				</a>
				</li>
				<li>
		<a href="">
						<p class="title">
							有效抵御黑产刷单
						</p>
						<i class="iconfont icon-caigou"></i> 
						<p class="content">用break-word时，是将强制换行。</p>
						<p class="btn"><span>→</span></p>
						<p class="content2">anese, and Korean不断词。即只用此时，不用wor</p>
						
				</a></li>
				<li>
				<a href=""> 
						<p class="title">
							购物无需等待
						</p>
						<i class="iconfont icon-fuwu"></i>
						<p class="content">用break-word时，是将强制换行。</p>
						<p class="btn"><span>→</span></p>
						<p class="content2">anese, and Korean不断词。即只用此时，不用wor</p>
				</a></li>
				<li>
				<a href="">
						<p class="title">
							紧跟行业发展潮流
						</p>
						 <i class="iconfont icon-0048jihedingdan"></i>
						 <p class="content">用break-word时，是将强制换行。</p>
						<p class="btn"><span>→</span></p>
						<p class="content2">anese, and Korean不断词。即只用此时，不用wor</p>
				</a></li>
			</ul>
		</div>
	</div>
	
    
   <script>
   	  	//样式切换
   	  	var html_ = "";
   	  	var href = ["","","",""];
   	  	var title=["从容应对高并发","有效抵御黑产刷单","购物无需等待","紧跟行业发展潮流"];
   	  	var content=["可弹性伸缩的IT资源，客户可根据实际业务需求灵活调配，轻松迎接业务高峰，从容应对高并发流量",
   	  				"依托亿级黑产大数据库，提供防恶意注册、登录、活动防刷等服务，高效识别、打击恶意刷单行为",
   	  				"低成本、高性能、可扩展的海量存储服务，配合多节点全网覆盖的网络加速服务，有效提升用户购物体验",
   	  				"结合行业发展趋势，提供多样化的产品与服务，如面对电商娱乐化，提供专业的视频、音频、图片处理服务"];
   	  	var iconfont = ["icon-kefu","icon-caigou","icon-fuwu","icon-0048jihedingdan"];
   	  	var content2 = ["同时提供黑石物理机和云服务器，可内网互通",
   	  					"大禹和天御可以提供针对DDOS，CC，刷单的全方位防护",
   	  					"HttpDNS可绕过LocalDNS解析，实现域名防劫持",
   	  					"内容4"];
   	  					for(var i=0;i<4;i++){
   	  				if(i==0){
   	  					html_ += "<ul><li><a  href=\""+href[i]+"\" style=\"border-left:1px solid #C0C0C0\">";
   	  				}else{
   	  					html_ += "<li><a  href=\""+href[i]+"\">";
   	  				}
    	  				html_ += "<p class=\"title\">"+title[i]+"</p>"+"<i class=\"iconfont "+iconfont[i]+"\"></i>"	+" <p class=\"content\">"+content[i]+"</p>"+"<p class=\"btn\">>></p>"+"<p class=\"content2\">"+content2[i]+"</p></a></li>";
    	  			}
   	  	
   	  	$(document).ready(
   	  	function(){
   	  		
   	  			html_ +="</ul>";
   	  			$(".contain").html(html_);
   	  			var left = 960-document.body.scrollWidth/2;
		   	  	$(".home_banner a").css("left",-left);
			    $(".home_top").mouseenter(function(){
			        $(this).css({"background":"#0099cc","color":"#FFFFFF"}).find("img").eq(0).attr("src","<%=basePath%>img/logo3.png");
			        $("#home_topnav>li").children("a").css("color","#FFFFFF");
			        $(".signin").css({"background":"#0087b4"}).children().css("color","#FFFFFF");
			    }).mouseleave(function(){
			        $(this).css({"background":"#FFFFFF","color":"#373737"}).find("img").eq(0).attr("src","<%=basePath%>img/logo4.png");
			        $("#home_topnav>li").children("a").css("color","#373737");
			        $(".signin").css({"background":"#EDEDED"}).children().css("color","#373737");
			    });
			    $(".home_banner a").css("left",-left);

				$("li").hover(function(){
					$(this).find(".content").hide();
					$(this).find(".content2").show();
					$(this).find(".btn").show();
				},function(){
					$(this).find(".content").show();
					$(this).find(".content2").hide();
						$(this).find(".btn").hide();
				});

	

});
				
	    
    </script>
   
