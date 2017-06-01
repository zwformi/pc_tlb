<%@ include file="../common/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link ref="<%=basePath%>css/yun_page.css"/>
<div class="model_solution">
	<div id="main_content">
	<div class="left">
	<div id="left_content">
	<div class="title">
	<h3>架构及解读</h3>
	<br>
	<span>快速满足细分领域的不同需求，护航产业生态发展</span>
	</div>
	<div class="tags">
	<a href="javascript:;">创新点A</a>
	<a href="javascript:;">创新点B</a>
	<a href="javascript:;">创新点C</a>
	</div>
	<div class="img">
	<img src="<%=basePath %>img/demo1.png"/></div>
	</div>
	</div>
	<div class="right">
	<div id="right_content">
	<div style="height:150px;"></div>
	<div class="r_title">
	<h3>创新点A</h3>
	</div>
	<div class="content">
		<div class="card">
			<h4>客户的主要需求</h5>
			<ul>
			<li>CRM需求一,CRM需求一</li>
			<li>CRM需求二,CRM需求一</li>
			<li>CRM需求三,CRM需求一</li>
			</ul>
		</div>
		<div class="card">
			<h4>客户的解决方案</h5>
			<ul>
			<li>CRM解决方案一,CRM解决方案一</li>
			<li>CRM解决方案二,CRM解决方案一</li>
			<li>CRM解决方案三,CRM解决方案一</li>
			</ul>
		</div>
	</div>
	</div>
	</div>
	</div>
</div>
<script type="text/javascript">
var datalist;
var data1={};
var data2={};
var data3={};
datalist=[data1,data2,data3];
var _width= window.screen.availWidth;
	var _height= window.screen.availHeight;
/* 	$(".model_solution").css({"height":_height+"px"});
	$(".model_solution").css({"width":(_width-18)+"px"}); */
	/* $(".model_solution .left").css({"width":(_width*0.7)+"px"}); */
	$(".model_solution .left").css({"height":_height+"px"});
/* 	$(".model_solution .right").css({"width":(_width*0.3)+"px"}); */
	$(".model_solution .right").css({"height":_height+"px"});
	$("#left_content").css({"margin-left":(_width*0.7-800>0?(_width*0.7-800)/2:0)+"px","margin-top":(_height-600>0?(_height-600)/2:0)+"px"});
	$("#right_content").css({"margin-left":(_width*0.3-300>0?(_width*0.3-300)/3:0)+"px","margin-top":(_height-600>0?(_height-600)/2:0)+"px"}); 
function initData(datalist){
	var tags = $(".tags").eq(0);
	$(".left .title").find("h3:first").text("电商架构及解读").siblings("span:first").text("快速满足细分领域的不同需求，护航电商生态发展");
	var _html = "";
	for(key in datalist){
		_html+='<a href="javascript:;" onclick="aClick(this);" class="" index="'+key+'">'+datalist[key].title+'</a>';
	}
	tags.html(_html);

	
}


$(function(){
	data1.title="成熟电商";
	data1.imgUrl="/img/demo1.png";
	data1.content=[{title:"客户的主要需求1",content:"物理机需求场景||物理机需求场景||物理机需求场景||混合云实现IDC和云的互通,混合云实现IDC和云的互通||全方位的安全防护，应对DDOS" },{title:"对应的解决方案1",content:"物理机需求场景||混合云实现IDC和云的互通||全方位的安全防护，应对DDOS" }];
	data2.title="初创电商";
	data2.imgUrl="/img/demo2.png";
	data2.content=[{title:"这是content标题2",content:"2内容111112||2内容22222222||2内容333333332" },{title:"这是content标题2",content:"2内容111112||2内容22222222||2内容333333332" }];
	data3.title="敏捷型";
	data3.imgUrl="/img/demo3.png";
	data3.content=[{title:"这是content标题3",content:"3内容111113||3内容22222223||3内容333333333" },{title:"这是content标题3",content:"3内容111113||3内容22222223||3内容333333333" }];
	initData(datalist);
	var target = $(".tags").find("a:first");
	aClick(target);//模拟点击
	


});

function aClick(e){
		$(e).addClass("active1").siblings("a").removeClass("active1");
		var list = datalist[Number($(e).attr("index"))];	
		$(".right .r_title").find("h3:first").text(list.title);
			var _html = "";
		for(key in list.content){
			 _html+='<div class="card">';
			 _html+='<h4>'+list.content[key].title+'</h4>';
			 _html+='<ul>';
			 var arr = list.content[key].content.split("||");
			 for(k in arr)
			 {
			 	_html +='<li>'+arr[k]+'</li>';
			 }
			 _html+='</ul>'
			 _html+='</div>';
		}
		$(".right .content").eq(0).html(_html);
		
		$(".left .img").find("img:first").animate();
		$(".left .img").find("img:first").css({"display":"block","opacity":"0.1"}).animate({opacity:1},1000);
		
		
		$(".left .img").find("img:first").attr({"src":list.imgUrl});
		
	};
	
</script>
