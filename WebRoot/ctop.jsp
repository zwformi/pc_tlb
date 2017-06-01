<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>css/homepage.css" rel="stylesheet" type="text/css">
<style type="text/css">
.stepInfo{position:relative;margin:20px auto 0 auto;width:250px;}
.stepInfo li{float:left;width:48%;height:0.15em;background:#bbb;}
.stepIco{border-radius:1em;padding:0.03em;background:#bbb;text-align:center;line-height:1.5em;color:#fff; position:absolute;width:1.4em;height:1.4em;}
.stepIco1{top:-0.7em;left:-1%;}
.stepIco2{top:-0.7em;left:48%;}
.stepIco3{top:-0.7em;left:95%;}
.stepText{color:#666;margin-top:0.2em;width:4em;text-align:center;margin-left:-1.4em;}
</style>
<div class="home_title" id="home_title">
        <div class="container">
            <ul class="left">
                
                <li><a href="/login/out.html">Hi，${loginUser.xm } 欢迎登录，退出</a></li>
                
            </ul>
            <ul class="right">
            
				<li><a href="<%=basePath%>find/find_main.html">我的订单</a></li>
                <li>
                    <a href="/user/member_info.html">个人中心</a>
                </li>
                
                <li>
                    <a href="/cart.html"><span class="iconfont icon-gouwuche" style="display:inline-block;font-size:16px;color:#F8E1E5"></span>
						采购车&nbsp;&nbsp;
					<!-- <div id="cartcount1"></div> -->
					</a>
                </li>               
                <li>
                    |
                </li>
                <li>
                    <a href="#"><i class="iconfont icon-dianhua"></i><span class="companynumber">400-826-4755</span></a>
                </li>
<!--                 <li><a class="head-top" href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank  title="客服">
					<span class="iconfont icon-kefu" style="display:inline-block;font-size:14px;color:#F8E1E5;margin-right:3px"></span>客服中心</a>
				</li>
				<li class="nav_dropdown"><a href="#"><span class="iconfont icon-weixin" style="display:inline-block;font-size:14px;color:#F8E1E5;margin-right:3px"></span>关注图灵买<span class="iconfont icon-xiala" ></span></a>
				    <ul>
				    	<li><img src="http://www.tulingbuy.com:80/images/2d_img.jpg" width="100" height="100"/></li>					    
				    </ul>
				</li> -->
                <li>
                    <a href="#"><i class="xtopfont icon-search"></i></a>
                </li>
            </ul>
        </div>
    </div>


    <div class="home_top" style="height:62px;">
        <div class="container">
            <a href="/"><img src="#" class="left" id="logo_main" width="240px" height="62px"></a>
                         
        </div>   
        <div class="head-topright1">
		<div class="stepInfo">
			<ul>
				<li></li>
				<li></li>
			</ul>
			<div class="stepIco stepIco1" id="create">1
				<div class="stepText" id="createText">采购车</div>
			</div>
			<div class="stepIco stepIco2" id="check">2
				<div class="stepText" id="checkText">核对需求</div>
			</div>
			<div class="stepIco stepIco3" id="produce">3
				<div class="stepText" id="produceText">提交订单</div>
			</div>			
		</div>
	</div>
		
    </div>

	
	

<%
  String n=request.getParameter("step");  
  %>
<input type="hidden" id="step" value="<%=n%>">
<script type="text/javascript">
if(localStorage.getItem("logo")){
  $("#logo_main").attr("src",localStorage.getItem("logo"));
}
if(localStorage.getItem("telephone")){
   $(".companynumber").html(localStorage.getItem("telephone"));
}
$(function() {
	setTimeout("changeDivStyle();", 100); // 0.1秒后展示结果，因为HTML加载顺序，先加载脚本+样式，再加载body内容。所以加个延时   
});
function changeDivStyle(){
//		var o_status = $("#o_status").val();	//获取隐藏框值
   var o_status=$("#step").val();	
	if(o_status=="1"){
		$('#create').css('background', '#DD0000');
		$('#createText').css('color', '#DD0000');
	} if(o_status=="2"){
		$('#check').css('background', '#DD0000');
		$('#checkText').css('color', '#DD0000');
	}if(o_status=="3"){
		$('#produce').css('background', '#DD0000');
		$('#produceText').css('color', '#DD0000');
	}
}
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}
//
</script>

