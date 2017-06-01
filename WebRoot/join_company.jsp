<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
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
<title>加入公司</title>
<link rel="stylesheet" href="<%=basePath%>css/cs.css" />
<link rel="stylesheet" href="<%=basePath%>css/font-awesome.css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>/js/layer/layer.js"></script>
<script src="<%=basePath%>/js/member.js?date=<%=new Date()%>"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath%>css/buy.css" />
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css" />
<style type="text/css">
body{background-color:#f0f0f0;text-align:center;overflow: hidden;}
.mytable{
width:300px;
height:300px;
margin:auto;
margin-top:50px;
}
.mytable>li{
height:50px;
}
input{
text-align:center;
}
ul>li{
margin:10px auto;

}

#company_name{
text-align:left;
padding-left:20px;
}
#search_company{
width:280px;
height:20px;
margin:0;
display: block;  
padding: 6px 12px; 
font-size: 14px; 
line-height: 1.428571429; 
color: #555555; 
vertical-align: middle; 
background-color: #ffffff; 
border: 1px solid #cccccc; 
border-radius: 4px; 
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
}

#search_company:focus,#text_area:focus{
border-color: #0099cc; 
outline: 0; 
-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, 0.6); 
box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, 0.6); }

#text_area{
-moz-box-shadow:1px 1px 0 #E7E7E7;
-moz-box-sizing:border-box;
border-color:#CCCCCC #999999 #999999 #CCCCCC;
border-style:solid;
border-width:1px;
font-family:arial,sans-serif;
font-size:13px;
height:120px;
outline-color:-moz-use-text-color;
outline-style:none;
outline-width:medium;
padding:20px;
width:260px;

}

#companylist>ul{
display: block; 
width:290px;
top:85px;
background-color:#fff;
z-index:3;
position:absolute;
padding:5px;
border: 1px solid #cccccc; 
border-radius: 4px; 
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 

}
#companylist li{
line-height:30px;
text-align:center;
display: block; 
border-radius: 4px; 
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); 
-webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; 
}
#companylist li:hover{
background-color:#0099cc;
color:#fff;

}
#btn_submit{
		display:inline-block;
 		background-color:#0099cc;
        color:#fff;
        text-align:center;
        font-size:15pt;
        line-height:50px;
        width:180px;
        height:50px;
        border-radius:25px;
        /*还可以设置50px  使其变成圆形的，还可以设置百分比  如  50% 也可以变成圆形的*/
}

    #btn_submit:hover{
    
    background-color:#0087b2;
    }
</style>
</head>

<body>
	<ul class="mytable">

	<li><input type="text" id="search_company"  placeholder="检索公司名称" ></li>
	<li id="companylist"></li>
	<li id="company_name" value="">加入：</li>
<!-- 	<li><textarea placeholder="请输入备注" id="text_area"></textarea></li> -->
	<li ><a id="btn_submit" href="javascript:;" >确认加入</a></li>
	
	
	</ul>
	

	<script>
	var companyName = '';
	$(function(){

	$("body").mousedown(function(){
		$("#companylist").find("ul").hide();
	
	})

	
	
	
	
	$("#search_company").keyup(function(){
		 	$("#companylist").find("ul").show();
			$("#companylist").html("");
			var _txt = $(this).val();
			if(_txt!="")
			$.post("/user/querycompany.json",{"params":_txt},function(data){
			if(data.rescode==1){
				var _html='<ul onmouseover="fOver()" onmouseout="fOut()">';
				var count=0;
				for(i in data.list){
				_html+='<a href="javascript:;"><li onclick="addthis(this);" value="'+data.list[i].id+'">'+data.list[i].gsmc+'</li></a>';
				}
				 _html+='</ul>';
				$("#companylist").html(_html);
				
			}else{
				var _html='<ul onmouseover="fOver()" onmouseout="fOut()">';
				_html+='<a href="javascript:;"><li onclick="createCompany();">暂无数据，点击创建公司</li></a>';
			 	_html+='</ul>';
				$("#companylist").html(_html);
			}		
			});
	})

	$("#search_company").click(function(){
			$("#companylist").find("ul").show();
			$("#companylist").html("");
			var _txt = $(this).val();
			if(_txt!="")
			$.post("/user/querycompany.json",{"params":_txt},function(data){
			if(data.rescode==1){
				var _html='<ul onmouseover="fOver()" onmouseout="fOut()">';
				for(i in data.list){
				_html+='<a href="javascript:;"><li onclick="addthis(this);" value="'+data.list[i].id+'">'+data.list[i].gsmc+'</li></a>';
				}
				 _html+='</ul>';
				$("#companylist").html(_html);
				
			}else{
				var _html='<ul onmouseover="fOver()" onmouseout="fOut()">';
				_html+='<a href="javascript:;"><li onclick="createCompany();">暂无数据，点击创建公司</li></a>';
			 	_html+='</ul>';
				$("#companylist").html(_html);
			}		
			});
		
	})
	
	
	$("#btn_submit").click(function(){
		var company_id = $("#company_name").attr("company_id");
		if(company_id==null||company_id==""){
			layer.msg("请选择一家公司加入！")
		}else{
		parent.owning_company=company_id;
		parent.company_name = companyName;
		parent.$('#search_company').val(companyName);
		parent.layer.closeAll();
		}
		
	
	});
	
	
	});
	
	function hidethis(){
		$("#companylist").find("ul").hide();	
	}
	function addthis(e){
		companyName = $(e).text();
		$("#search_company").val($(e).text());
		$("#company_name").text("加入："+$(e).text());
		$("#company_name").attr("company_id",$(e).val());
		$("#companylist").find("ul").hide();
	}
	
	function fOver(){
	$("body").unbind("mousedown");
	}
	function fOut(){
		$("body").mousedown(function(){
		$("#companylist").find("ul").hide();
	
	})
	}
	
	function createCompany(){
	parent.layer.open({
					  type: 2,
					  title: '加入公司',
					  shadeClose: false,
					  shade: 0.1,
					  area: ['850px', '600px'],
					  content: '<%=basePath%>add_company.jsp'
					}); 
	
	
	
	}
	</script>
</body>
</html>
