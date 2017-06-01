<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="Keywords" content="图灵买,IT服务平台
,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description" content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>企业内购---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath %>js/special_buy.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/queryString.js?date=<%=new Date()%>"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style type="text/css">
	.add_cart{cursor: pointer;}
	.buy_tj h3{text-align:left;overflow:hidden;}
</style>
</head>

<body>
<jsp:include page="stop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
 
<!--center-->
<div class="member_center" style="width:1300px">

 <c:if test="${TYPE == null or TYPE.equals('') }">
 <div id="advfind" >
  	   <p class="advhead">   
  	   <span>商品类别</span>
  	   <span>></span>
  	   <span>电脑整机</span>

  	   </p>
  	   </div>
   </c:if>
    <!--我要买-->
 <c:if test="${TYPE!=null and !TYPE.equals('')}">
 <div id="advfind" >
 
  	   <p class="advhead">
  	   
  	   <span>商品类别</span>
  	   <span>></span>
  	   <span>${TYPE == null?"":TYPE }</span>
  	  
  	   <c:if test="${TYPENAME !=null and !TYPENAME.equals('') }">
  	   >
  	 
  	   <span>${TYPENAME == null?"":TYPENAME}</span>
  	    </c:if>
	<c:if test="${THIRDTYPENAME !=null and !THIRDTYPENAME.equals('') }">
  	   >
  	 
  	   <span>${THIRDTYPENAME == null?"":THIRDTYPENAME}</span>
  	    </c:if>
  	   </p>
  	   
  	   <div><span>品牌:</span>
  	   <ul style="border-left:1px solid #ddd;">
	  	   <c:forEach items="${PRODUCTBRANDLIST }" var="PRODUCTBRAND">
	  	       <c:if test="${PRODUCTBRAND.id!=9 }">
	  	   			<li class="brandcss"  onclick="selectbrand(this)" type_id="${PRODUCTBRAND.id }"><img src="/upload/product_img/type${PRODUCTBRAND.id }.jpg"></li>
	  	   		</c:if>
	  	   </c:forEach>
  	   </ul>
  	   </div>
  	   <c:if test="${TYPEID.indexOf('1')!=-1}">
  	   <div><span>尺寸:</span>
  	   
	  	   <ul>
	  	   		 <li onclick="selectstringmap(this,'size')" sid="size0" class="defaultli">全部</li>
	  	   		  <c:forEach items="${STRINGMAPLIST }" var="STRINGMAP">
	  	   		  
		  	       <c:if test="${STRINGMAP.filedname=='Product_Size' }">
		  	   			<li onclick="selectstringmap(this,'size')" sid=${STRINGMAP.value}>${STRINGMAP.name }</li>
		  	   		</c:if>
	  	   		</c:forEach>
	  	   </ul>
  	   </div>
  	   <div><span>颜色:</span>
  	   	<ul>
  	   		<li onclick="selectstringmap(this,'color')" sid="color0" class="defaultli">全部</li>
  	   		<c:forEach items="${STRINGMAPLIST }" var="STRINGMAP">
	  	   		  
		  	       <c:if test="${STRINGMAP.filedname=='Product_Color' }">
		  	   			<li onclick="selectstringmap(this,'color')" sid=${STRINGMAP.value}>${STRINGMAP.name }</li>
		  	   		</c:if>
	  	   		</c:forEach>
  	   	</ul>
  	   	</div>
  	   <div><span>版本:</span>
  	   <ul>
  	   		<li onclick="selectstringmap(this,'vision')" sid="vision0" class="defaultli">全部</li>
  	   		<c:forEach items="${STRINGMAPLIST }" var="STRINGMAP">
	  	   		  
		  	       <c:if test="${STRINGMAP.filedname=='Product_Versions' }">
		  	   			<li onclick="selectstringmap(this,'vision')" sid=${STRINGMAP.value}>${STRINGMAP.name }</li>
		  	   		</c:if>
	  	   		</c:forEach>
  	   	</ul>
		</div>
		</c:if>
  </div>
  </c:if>
 <div class="buy_search" style="display:none">
  <h3><input type="hidden" value="${TYPEID }" id="typeid" autocomplete="off"/>
  <input type="hidden" value="0" id="brand" autocomplete="off"/>
  <input type="hidden" value=" out_count1 desc " id="orderby" autocomplete="off"/>
  <input type="hidden" value="0"  id="size" autocomplete="off"/>
  <input type="hidden" value="0"  id="color" autocomplete="off"/>
  <input type="hidden" value="0"  id="vision" autocomplete="off"/>
  
  
  
  
  
  我要买:</h3><span class="fa fa-angle-right"></span>
  <dl>
    <dt><font class="pro_type_selectname">${TYPENAME }</font><span class="iconfont icon-xiala" style="margin:0px;float:right"></span></dt>
    <dd>
    	<a class="type_do" tid="0">所有类型</a>
      	<c:forEach items="${PRODUCTTYPELIST }" var="PRODUCTTYPE">
       		<a class="type_do" tid="${PRODUCTTYPE.id}">${PRODUCTTYPE.name }</a>
        </c:forEach>
    </dd>
  </dl>
  <div id="child_types"></div>
  <span class="fa fa-angle-right"></span>
  <dl>
    <dt>请选择<span class="iconfont icon-xiala" style="margin:0px;float:right"></span></dt>
    <dd>
    	<a class="brand_do" brand="0">所有品牌</a>
      <c:forEach items="${PRODUCTBRANDLIST }" var="PRODUCTBRAND">
       		<a class="brand_do" brand="${PRODUCTBRAND.id}">${PRODUCTBRAND.name }</a>
        </c:forEach>
    </dd>
  </dl>
 </div>
 <!--产品列表-->
 <div class="buy_cont">
   <div class="buy_find">
      <span><a class="select" style="cursor: pointer;" id="orderbyxl">销量</a><a style="cursor: pointer;" id="orderbyjg">价格</a></span>
      <strong><input class="buy_sbox" name="" type="text" id="where" placeholder="输入商品名称搜索" ><input class="buy_sbtn" name="" id="search_but" type="button"  value="确定"></strong>
      <u><label><input name="" id="zxsyh" type="checkbox" value="">仅显示有货</label></u>
   </div>
   <ul class="buy_plist">
     <div style="clear:both;"></div>
   </ul>
   <!--page-->
   <div class="zi_page" id="pagination" style="border:0px;">
     
   </div>
   <div style="clear:both; height:10px;"></div>
   
 </div>
</div>
<!--right-->

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
<script>
	$('#where').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			//回车执行查询
			$('#search_but').click();
		}
	});
	/* $(function(){
	//默认点击第一个大类
	if((!!getQueryString("typeid"))==false){
	
		var url = $("#specialList li").find("a:first").attr('href');
		if(!!url)
			window.location.href = url;
	}
	
	}) */
</script>