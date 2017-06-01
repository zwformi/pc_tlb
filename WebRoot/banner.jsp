<%@ include file="common/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>Untitled Document</title>
 <link href="<%=basePath%>css/homepage.css" rel="stylesheet" type="text/css">

 <link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
</head>
<body>
<!-- banner -->
<div class="home_banner"
				style="overflow:hidden;background:#fff;margin:0">
				<c:forEach items="${BANNERS }" var="ad" varStatus="status">
					<c:if test="${ad.url!=null and !ad.url.equals('#') }"><a href="${ad.url }"  target="blank"  id="pic${status.index }"
						style="display:block;">
						</c:if>
						<c:if test="${ad.url==null or ad.url.equals('#') }"><a  id="pic${status.index }"
						style="display:block;"> 
						</c:if>
					 <img
						style="left: 0px; position: relative;"
						src="http://img.tulingbuy.com${ad.img_url }"   height="495" > </a>
				</c:forEach>
				<c:forEach items="${BANNERS }" var="item" varStatus="stat">
					<c:choose>
						<c:when test="${ stat.index == 0 }">
							<div class="carousel_nav">
								<i id="item${stat.index }" class="active"></i>
						</c:when>
						<c:otherwise>
							<i id="item${stat.index }"></i>
							<c:if test="${stat.index  == fn:length(BANNERS)-1}">
							</div>
						</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		</div>
 
</div>

<script>
/****************************图片轮播*******************************************/
$(document).ready(function(){

		var carousel = null;
	    var sum = $(".home_banner>a").length;
	    var timer=0;
	    bannerClick( $(".carousel_nav i").eq(0));
	    $(".carousel_nav i").click(function(){
	    bannerClick(this);
	    });
	    
	    function bannerClick(e){
	        $(e).addClass("active").siblings().removeClass("active");
	        var i_mun1=$('.carousel_nav i').index(e);
	        $('.home_banner>a').eq(i_mun1).css({"display":"block","opacity":"0.1"}).animate({opacity:1},1000).siblings('a').css("display","none");
	        timer=i_mun1;
	    }
	    function bannerMove(){
	        carousel=setInterval(function(){
	            scroll()
	        },6000)
	    }
	    bannerMove();
	    $('.home_banner').mouseover(function(){
	        clearInterval(carousel);
	    }).mouseout(function(){
	        bannerMove();
	    });
	    function scroll(){
	        if(timer==sum-1){
	            timer=-1
	        }
	        $('.home_banner>a').eq(timer+1).css({"display":"block","opacity":"0.1"}).animate({opacity:1},1000).siblings('a').css("display","none");
	        $('.carousel_nav i').eq(timer+1).addClass('active').siblings('i').removeClass('active');
	        timer++;
	    }


});
	    
</script>
</body>
</html>
