<%@ include file="common/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>css/homepage.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/topstyle.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/icon.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath%>js/top_area.js?1"></script>

<div class="home_title" id="home_title">
        <div class="container">
            <ul class="left">
            <c:choose>
             <c:when test="${loginUser == null}">
                <li style="float:left;margin-left:5px;">Hi！欢迎来图灵买</li>
                <li style="float:left;margin-left:5px;"><a href="/login.html">请登录</a></li>
                <li style="float:left;margin-left:5px;"><a href="/register.json">快速注册</a></li>
              </c:when>
              <c:otherwise>

             
               <li> Hi，${loginUser.xm } 欢迎登录</li>
                <li><a href="/login/out.html">退出</a></li>

               </c:otherwise> 
               </c:choose>
                
            </ul>
            <ul class="right">
            <li><a href="<%=basePath %>find/find_main.html">我的订单</a></li>
                <li>
                    <a href="/user/member_info.html">个人中心</a>
                </li>
                <li>
                    <a href="/member_news.jsp">我的消息</a>
                </li>
                
                <li>
                    |
                </li>
                <li>
                    <a href="#"><i class="iconfont icon-dianhua" style=""></i>400-826-4755</a>
                </li>
                <li><a class="head-top" href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank  title="客服">
					<i class="iconfont icon-kefu" style="display:inline-block;font-size:14px;color:#666;margin-right:3px"></i>客服中心</a></li>
				<li class="nav_dropdown"><a href="#"><i class="iconfont icon-weixin" style="display:inline-block;font-size:14px;margin-right:3px"></i>关注图灵买<i class="iconfont icon-xiala" ></i></a>
				    <ul>
				    	<li><img src="http://www.tulingbuy.com:80/images/2d_img.jpg" width="100" height="100"/></li>					    
				    </ul>
				</li>
                <!-- <li>
                    <a href="#"><i class="xtopfont icon-search"></i></a>
                </li> -->
            </ul>
        </div>
    </div>


    <div class="home_top" style="height:62px;">
        <div class="container">
            <a href="/" class="left" style="display:block;"><img src="/img/special_logo.png"></a>
            <ul id="home_topnav" class="left">
                
                <li>
                  <a href="/buy/special_list.html" class="" >内购产品</a>
                    <div class="top_sub" style="display: none;">
                        <ul id="specialList">
                            
                        </ul>
                    </div>
                <li>
               <!--  <li >
                    <a href="/article/1.json">企业服务</a>
                    <div class="top_sub" style="display: none;">
                        <ul id="itList">
                            
                        </ul>
                    </div>
               </li>
               <li>
                    <a href="/xiaoshou.jsp">解决方案</a>
                </li>
                 <li >
                    <a href="/article/gsdsjlb1.json">图灵快报</a>
               </li>
                <li>
                    <a href="/article/2.json">关于我们</a>
                </li> -->
            </ul>
            <c:if test="${loginUser!=null }">
             <div class="head-topright">
				<a href="/cart.html" target=_blank  title="采购车">
				<span class="bt_buyshow"><span class="iconfont icon-gouwuche" style="display:inline-block;font-size:20px;color:#D74340"></span>
					采购车
					<div id="cartcount1"></div>
					<tt style="display:inline-block;margin-left:10px">></tt>
				</span>
				</a>
			</div>
			</c:if>
        </div>
    </div>

<div class="mallCategory">	
		<div class="catList">			
			<ul class="clearfix">				
			</ul>
		</div>
		
		
		
		<div class="cat-subcategory">
			<div class="shadow">
				
				
			</div>
		</div>
	
	</div>
	
<div class="side-bar"> 
	<p><a href="/find/one_demand.html"  class="zhankai" ><span class="iconfont icon-shangchuan" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>一键采购</a></p> 
	<p><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank class="zhankai">
			<span class="iconfont icon-kefu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>客服咨询
	</a></p>
	<p><a href="/buy.json" class="zhankai">
			<span class="iconfont icon-1348weituogoumai" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>我要采购
	</a></p>
	
	<p><a href="/user/member_info.html" ><span class="iconfont icon-huiyuankehu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>图灵买会员</a></p>	
	<p><a href="/cart.html" ><span class="iconfont icon-gouwuche" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>采购车</a></p> 
	<p><a href="#" style="font-size:12px"><span class="iconfont icon-dianhua" style="display:inline-block;font-size:18px;color:#fff;margin-right:3px"></span>400-826-4755</a></p> 
</div>