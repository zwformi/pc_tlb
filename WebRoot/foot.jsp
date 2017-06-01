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
		<p><img src="<%=basePath %>img/phone_1.png"/>
			<span style="font-size:35px;color:#fff;line-height:55px;vertical-align:top" class="companynumber">400-826-4755</span>
		</p>
<!-- 		<div class="left bottom_part1">
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
			
		</div> -->
<!-- 		<div class="left bottom_part1">
			<p><span>企业采购</span></p>
			<p>
				<ul>				
				</ul>
			</p>			
		</div> -->
	<!-- 	<div class="left bottom_part1">
			<p><span>企业服务</span></p>
			<p>
				<ul>
					<li><a href="/buy.json?typeid=5,39">维修保养</a></li>
					<li><a href="/buy.json?typeid=5,40">驻场服务</a></li>
					<li><a href="/buy.json?typeid=5,41">安装服务</a></li>
					<li><a href="/article/4-23.json">免费服务</a></li>
				</ul>
			</p>			
		</div> -->
		<!-- <div class="left bottom_part1">
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
		</div> -->
		<!-- <div class="left bottom_part1">
			<p><span>帮助中心</span></p>
			<p>
				<ul>
					<li><a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607">客服中心</a></li>
					<li><a href="/article/2-22.json">操作指南</a></li>
				</ul>
			</p>			
		</div> -->
		<!-- <div class="left bottom_part1" style="width:180px;text-align:left">
			<p><span>联系我们</span></p>
			<p>
				<ul>
					<li><a style="font-size:13px;"><span class="iconfont icon-email"></span>tulingbuy@tuling.com.cn</a></li>
					<li><a><span class="iconfont icon-zixun"></span>400-8264-755</a></li>
					<li><a><span class="iconfont icon-dingwei"></span>杭州市祥园路139号浙大网新智慧立方A座7楼</a></li>
				</ul>
			</p>			
		</div> -->
<!-- 		<div class="left bottom_part1" style="width:180px;text-align:left">
			<p><img src="http://www.tulingbuy.com:80/images/2d_img.jpg"></p>
			<p><span>微信公众号</span></p>						
		</div> -->
	</div>
</div>
</div>
<div class="home_footer" style="background:#28292F;color:#999">
		<div class="container_sub">
			<div class="copyright">				
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
  getType();
})();

function getType(){
	    $.post('/menu.json',function(data){
		  console.log(data);
	  		if(data.error){
	  			layer.msg(data.message);
	  		}
	  		else
	  		{	var temp =[];
	  			temp.push("<div class='left bottom_part1'>");
	  			temp.push("<p><span>企业采购</span></p>")
			    temp.push("<p>");
				temp.push("<ul>");				
	  			ptypes=data.PRODUCTTYPELIST;
	  			for(var i=0;i<ptypes.length;i++)
	  		    {
                    if(ptypes[i]["parentid"]=="0"){
                      temp.push("<li><a href='/buy.json?typeid="+ptypes[i]["id"]+"'>"+ptypes[i]["name"]+"</a></li>");
                    }
		  	    }
		  	    temp.push("</ul>");
				temp.push("</p>");	
				temp.push("</div>");
		  	    $(".main_body").append(temp.join(""));	 
		  	    getModuleAndArticle(); 			
	  		}
	  	  });	
	}
function getModuleAndArticle(){
       $.post('/module/queryList.json',{"type":1},function(data){
		  var footStr=[];
          for(var i=0;i<data.length;i++){
       	       var footArticlestr=[];
  	           footArticlestr.push("<div class='left bottom_part1'>");
  	           footArticlestr.push("<p><span>"+data[i]["module_name"]+"</span></p>");
  	           footArticlestr.push("<p>");
  	           footArticlestr.push("<ul>");
        	  if(data[i]["belongto_tf"]!=1)
	        	  {
	        	     var getAtricleUrl="/moduleforarticle/"+data[i]["id"]+".json";
	        	     $.ajaxSetup({   
				            async : false  
				        }); 
	        	     $.post(getAtricleUrl,function(data){
	        	        $.each(data,function(i,value){

	        	           if(!!value["article_url"]){              	        	              
	        	              footArticlestr.push("<li><a href='"+value["article_url"]+"'>"+value["title"]+"</a></li>");
	        	           }
 	        	           else{
	        	              var articleUrl = "/article/"+value["typeid"]+"-"+value["id"]+".json";
	        	              footArticlestr.push("<li><a href='"+articleUrl+"'>"+value["title"]+"</a></li>");
	        	           }
	        	        })

	        	     })
		        	footArticlestr.push("</ul>");
	     	        footArticlestr.push("</p>");
	     	        footArticlestr.push("</div>");
	     	        footStr.push(footArticlestr.join(""));
	        	  }
        	  }
        	  $(".main_body").append(footStr.join(""));
        	  var ss='<div class="left bottom_part1" style="width:180px;">'
						+'<p><img class="qrcode" src="#" width="140px" height="140px"></p>'
						+'<p><span>微信公众号</span></p>'						
					    +'</div>';
        	  $(".main_body").append(ss);
        	  getStaticForFoot();
           });

}
function getStaticForFoot(){
	if(!localStorage.getItem("logo")&&!localStorage.getItem("telephone")&&!localStorage.getItem("QRCode")&&!localStorage.getItem("copyright"))
	{
		$.post('/staticresource/queryListbykeys.json',{"keys":"logo,telephone,QRCode,copyright"},function(data){
	         console.log(data); 
	         for(var i=0;i<data.length;i++){
	        	 if(data[i]["skey"]=="logo")   
	        	   {
	        		 $("#logo_main").attr("src",data[i]["path"]);
	        		 if (window.localStorage) {
						    localStorage.setItem("logo", data[i]["path"]);	
						} 
	        	   }
	        	 if(data[i]["skey"]=="telephone"){
	        		 $(".companynumber").html(data[i]["svalue"]);
	        		    if (window.localStorage) {
						    localStorage.setItem("telephone", data[i]["svalue"]);	
						} 
	        		 
	        	 }
	        	 if(data[i]["skey"]=="QRCode"){
	        	     $(".qrcode").attr("src",data[i]["path"]);
	        	      if (window.localStorage) {
						    localStorage.setItem("QRCode", data[i]["path"]);	
						} 
	        	 }
	        	  if(data[i]["skey"]=="copyright"){
	        	     $(".copyright").html(data[i]["svalue"]);
	        	      if (window.localStorage) {
						    localStorage.setItem("copyright", data[i]["svalue"]);	
						} 
	        	 }
	         }
	         
		});
	}
	else{
/* 	    $("#logo_main").attr("src",localStorage.getItem("logo")); */
	    $(".companynumber").html(localStorage.getItem("telephone"));
	    $(".qrcode").attr("src",localStorage.getItem("QRCode"));
	    $(".copyright").html(localStorage.getItem("copyright"));
	}
}
</script>