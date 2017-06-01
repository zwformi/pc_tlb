<%@ include file="common/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="<%=basePath%>css/homepage.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/topstyle.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/icon.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script src="<%=basePath%>js/top_area.js?1"></script>
<div class="home_title" id="home_title">
        <div class="container">
            <ul class="left">
                <c:if test="${loginUser==null }">
                <li>
                    Hi！欢迎光临
                </li>
                <li>
                    <a href="/login.html">请登录</a>
                </li>
                <li>
                    <a href="/register.json">快速注册</a>
                </li>
              </c:if>
              <c:if test="${loginUser!=null }">
             
               <li> Hi，${loginUser.xm } 欢迎登录</li>
                <li><a href="/login/out.html">退出</a></li>
                </c:if>
            </ul>
            <ul class="right">
            
				<li><a href="<%=basePath%>find/find_main.html">我的订单</a></li>
                <li>
                    <a href="/user/member_info.html">个人中心</a>
                </li>
                <li>
                    <a href="/member_news.jsp">我的消息</a>
                </li>
                <li>
                    <a href="/cart.html"><i class="iconfont icon-gouwuche" style="display:inline-block;font-size:16px;"></i>
						采购车
					<div id="cartcount1"></div></a>
                </li>
                <li>
                    |
                </li>
                <li>
                    <a href="#"><i class="iconfont icon-dianhua" style=""></i><span class="companynumber">400-826-4755</span></a>
                </li>
<!--                 <li><a class="head-top" href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank  title="客服">
					<i class="iconfont icon-kefu" style="display:inline-block;font-size:14px;margin-right:3px"></i>客服中心</a>
				</li>
				<li class="nav_dropdown"><a href="#"><i class="iconfont icon-weixin" style="display:inline-block;font-size:14px;margin-right:3px"></i>关注图灵买<i class="iconfont icon-xiala" ></i></a>
				    <ul>
				    	<li><img src="http://www.tulingbuy.com:80/images/2d_img.jpg" width="100" height="100"/></li>					    
				    </ul>
				</li> -->
            </ul>
        </div>
    </div>


    <div class="home_top" style="height:62px;">
        <div class="container">
            <a href="/"  class="left" style="display:block;"><img src="/img/logo2.png" width="240px" height="62px"></a>
            <ul id="home_topnav"  class="left">
                
                <li>
                  <a href="/buy.json" class="" >企业采购</a>
                    <div class="top_sub" style="display: none;">
                        <ul id="productList">
                            
                        </ul>
                    </div>
                <li>
                <c:if test="${loginUser.special_code==1 }">
               <li>
                  <a href="/buy/special_list.html" class="" >企业内购</a>
                </li>
               </c:if>
<!--                <li>
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
                    <a href="/article/gsdsjlb1.json">企业快报</a>
               </li>
                <li>
                    <a href="/article/2.json">关于我们</a>
                </li> -->
               </ul>
		<c:if test="${loginUser==null }">		
            <a href="/register.json" class="right signin">
                <p>快速注册</p>
                <span>现注册享双倍积分</span>
            </a>
            </c:if>
           
            
        </div>
    </div>

	
<!-- <div class="side-bar"> 
	<p><a href="/find/one_demand.html"  ><span class="iconfont icon-shangchuan" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>一键采购</a></p> 
	<p><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank >
			<span class="iconfont icon-kefu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>客服咨询
	</a></p>
	<p><a href="/buy.json" >
			<span class="iconfont icon-1348weituogoumai" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>我要采购
	</a></p>
	
	<p><a href="/user/member_info.html" ><span class="iconfont icon-huiyuankehu" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>图灵买会员</a></p>	
	<p><a href="/cart.html" ><span class="iconfont icon-gouwuche" style="display:inline-block;font-size:20px;color:#fff;margin-right:3px"></span>采购车</a></p> 
	<p><a href="#" id="phone_number"><span class="iconfont icon-dianhua" style="display:inline-block;font-size:18px;color:#fff;margin-right:3px"></span>400-826-4755</a></p> 
</div> -->