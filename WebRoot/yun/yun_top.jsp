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
<style>
	.top_sub>ul>li a:hover, .top_sub>ul>li a.hover{
	color: #0099cc;
	}
	.side-bar a:hover,#phone_number:hover {
    background-color: #0099cc;
}

</style>

<div class="home_title" id="home_title">
        <div class="container">
            <ul class="left">
            <c:if test="${loginUser==null }">
                <li>
                    Hi！欢迎访问图灵云
                </li>
                <li>
                    <a href="/login.html">请登录</a>
                </li>
                <li>
                    <a href="/register.json" target="_blank">快速注册</a>
                </li>
              </c:if>
              <c:if test="${loginUser!=null }">
             
               <li> Hi，${loginUser.xm } 欢迎登录图灵云</li>
                <li><a href="/login/out.html">退出</a></li>
                </c:if>
            </ul>
            <ul class="right">
            
				<li><a href="<%=basePath%>find/find_main.html">我的订单</a></li>
                <li>
                    <a href="/user/member_info.html">个人中心</a>
                </li>                
                <li>
                    <a href="/cart.html"><i class="iconfont icon-gouwuche" style="display:inline-block;font-size:16px;color:#fff"></i>
						采购车
					</a>						
                </li>
                <li>
                    
                </li>                
                
				<li class="nav_dropdown"><a href="#"   style="padding-right:0px"><i class="iconfont icon-weixin" style="display:inline-block;font-size:14px;color:#fff;margin-right:3px"></i>关注图灵买<i class="iconfont icon-xiala"></i></a>
				    <ul>
				    	<li><img src="http://www.tulingbuy.com:80/images/2d_img.jpg" width="100" height="100"/></li>					    
				    </ul>
				</li>
                <li><a class="head-top" href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank  title="客服">
					<i class="iconfont icon-kefu" style="display:inline-block;font-size:14px;color:#fff;margin-right:3px"></i>客服中心</a></li>
            </ul>
        </div>
    </div>


    <div class="home_top" style="height:62px;">
        <div class="container">
            <a href="/" class="left" style="display:block;height:62px;"><img src="/img/logo4.png"></a>
            <ul id="home_topnav"  class="left">
                
                <li>
                  <a href="/buy.html" class="" >首页</a>
                    <div class="top_sub" style="display: none;">
                        <ul id="productList">
                            
                        </ul>
                    </div>
                <li>
                <li >
                    <a href="/article/1.json">商城首页</a>
                    <div class="top_sub" style="display: none;">
                        <ul id="itList">
                            
                        </ul>
                    </div>
               </li>
               <li>
                    <a href="/yun/yun.jsp">应用市场</a>
                </li>
               <li>
                    <a href="/xiaoshou.jsp">案例介绍</a>
                </li>
                <li>
                    <a href="/article/2.json">关于我们</a>
                </li>
                <li>
                    <a href="/article/2.json"></a>
                </li>
               </ul>
		<c:if test="${loginUser==null }">		
            <a href="/register.json" class="right signin">
                <p>快速注册</p>
                <span>现注册享双倍积分</span>
            </a>
            </c:if>
           
        </div>
    </div>
    
    <div class="side-bar"> 
	<p><a href="/find/one_demand.html"  ><span class="iconfont icon-shangchuan" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>一键采购</a></p> 
	<p><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank >
			<span class="iconfont icon-kefu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>客服咨询
	</a></p>
	<p><a href="/buy.html" >
			<span class="iconfont icon-1348weituogoumai" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>我要采购
	</a></p>
	
	<p><a href="/user/member_info.html" ><span class="iconfont icon-huiyuankehu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>图灵买会员</a></p>	
	<p><a href="/cart.html" ><span class="iconfont icon-gouwuche" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>采购车</a></p> 
	<p><a id="phone_number" href="#" ><span class="iconfont icon-dianhua" style="display:inline-block;font-size:18px;color:#fff;margin-right:3px"></span>400-826-4755</a></p> 
</div>
    
   <script>
   	  	//样式切换
   	  	$(document).ready(
   	  	function(){
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
   	  	});
	    
   </script>
   
