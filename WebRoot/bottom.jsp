<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />
<style>
.bottom_part1{
	width:100px;
	margin-left:50px;
	font-size:14px;
}
.bottom_part1 p span{
	color:#eee;	
	
}
.bottom_part1 p{	
	margin-top:15px;	
}
.bottom_part1 a{
	color:#999;
	font-size:14px;
}
.bottom_part1 a:link{
	color:#999;
}
.bottom_part1 a:hover{
	color:#fff;
}
.footer_sub{
	width:1210px;
	margin:0 auto;
}
</style>
<div class="home_footer" style="background:#3E4147">
<div class="footer_sub">
	<div class="main_body" style=" text-align: center;">	
		<p><img src="/img/phone_1.png"/>
			<span style="font-size:35px;color:#fff;line-height:55px;vertical-align:top">400-8264-755</span>
		</p>
		<div class="left bottom_part1">
			<p><span>关于我们</span></p>
			<p>
				<ul>
					<li><a href="/article/2.json">企业介绍</a></li>
					<li><a href="/article/2-58.json">认识灵灵</a></li>
					<li><a href="/article/2-62.json">加入我们</a></li>
					<li><a href="/article/4-46.json">隐私条款</a></li>
					<li><a href="/article/4-45.json">法律声明</a></li>
					<li><a href="/article/4-44.json">用户协议</a></li>
				</ul>
			</p>
			
		</div>
		<div class="left bottom_part1">
			<p><span>企业采购</span></p>
			<p>
				<ul>
					<li><a href="/buy.json?typeid=1">电脑整机</a></li>
					<li><a href="/buy.json?typeid=2">办公设备</a></li>
					<li><a href="/buy.json?typeid=3">网络产品</a></li>
					<li><a href="/buy.json?typeid=4">通用软件</a></li>
					<li><a href="/buy.json?typeid=14">其他产品</a></li>
					
				</ul>
			</p>			
		</div>
		<div class="left bottom_part1">
			<p><span>企业服务</span></p>
			<p>
				<ul>
					<li><a href="/buy.json?typeid=5,39">维修保养</a></li>
					<li><a href="/buy.json?typeid=5,40">驻场服务</a></li>
					<li><a href="/buy.json?typeid=5,41">安装服务</a></li>
					<li><a href="/article/4-23.json">免费服务</a></li>
				</ul>
			</p>			
		</div>
		<div class="left bottom_part1">
			<p><span>解决方案</span></p>
			<p>
				<ul>
					<li><a href="/xiaoshou.jsp">邮件系统</a></li>
					<li><a href="/xiaoshou.jsp">虚拟化</a></li>
					<li><a href="/xiaoshou.jsp">小型视频会议</a></li>
					<li><a href="/xiaoshou.jsp">客户关系管理</a></li>
					<li><a href="/xiaoshou.jsp">移动销售助手</a></li>
					
				</ul>
			</p>			
		</div>
		<div class="left bottom_part1">
			<p><span>帮助中心</span></p>
			<p>
				<ul>
					<li><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607">客服中心</a></li>
					<li><a href="/article/2-22.json">操作指南</a></li>
				</ul>
			</p>			
		</div>
		<div class="left bottom_part1" style="width:180px;text-align:left">
			<p><span>联系我们</span></p>
			<p>
				<ul>
					<li><a style="font-size:13px;"><span class="iconfont icon-email"></span>tulingbuy@tuling.com.cn</a></li>
					<li><a><span class="iconfont icon-zixun"></span>400-8264-755</a></li>
					<li><a><span class="iconfont icon-dingwei"></span>杭州市祥园路139号浙大网新智慧立方A座7楼</a></li>
				</ul>
			</p>			
		</div>
		<div class="left bottom_part1" style="width:180px;text-align:left">
			<p><img src="http://www.tulingbuy.com:80/images/2d_img.jpg"></p>
			<p><span>微信公众号</span></p>
						
		</div>
	</div>
</div>
</div>
<div class="home_footer" style="background:#28292F;color:#999">
		<div class="container_sub">
			<div class="copyright">				
				<br>浙公网安备33010502001326号
				| <span>浙ICP备05049824号-5</span> | 增值电信业务经营许可证编号：浙B2-20160117
				<br>图灵买 © 2015 www.tulingbuy.com 版权所有</span>
			</div>			
		</div>
</div>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?f7000683e1a3d9f334072a297bb880ee";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>