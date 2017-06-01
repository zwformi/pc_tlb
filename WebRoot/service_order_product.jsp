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
<meta name="Keywords" content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description" content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>售后服务---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">
	$(document).ready(function($){
		//商品图片加载错误事件
		$("img.pro_img").on("error",function(){
			$(this).attr("src","/images/no_pic.png");
		});
	});
</script>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">
 <div class="member_content" style="min-height:auto;">
      <h3>
       <a class="select" href="/service.html">售后服务</a>
       <a href="/service/tostation.html">维修站查询</a>
     </h3>
     <div class="order_top">
       <strong>可以申请售后的商品</strong>
     </div>
     <div class="order_cont" style="min-height:395px;">
     	<table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0">
	     	 <tr>
	         	<td><span>合同号</span></span></td>
	         	<td>${ORDER.order_number }</td>
	         </tr>
	         <tr>
	         	<td><span>合同生成时间</span></td>
	         	<td><fmt:formatDate value="${ORDER.order_time }" type="both"/></td>
	         </tr>
	         <tr>
	         	<td><span>合同附件</span></td>
	         	<td>
					<c:if test="${ORDER.demand_file=='' }">
	        			无
	        		</c:if>
	        		<c:if test="${ORDER.demand_file!='' }">
	        			${ORDER.demand_file } <a href="${ORDER.demand_file }" target="_blank">查看</a>
	        		</c:if>
				</td>
	         </tr>
     	</table>
       <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0">
         <thead>
           <tr>
             <th width="23%">商品</th>
              <th>类型</th>
             <th>品牌</th>
             <th>型号</th>
             <th>配置</th>
             <th>数量</th>
             <th>售后</th>
           </tr>
          </thead>
          <tbody>
            <c:if test="${ORDERDETAILSLIST== null || fn:length(ORDERDETAILSLIST) == 0}">
   				<tr><td  colspan="4">该合同内的商品清单为空,无法申请售后服务...</td></tr>
   		 	</c:if>
          	<c:forEach items="${ORDERDETAILSLIST }" var="ORDERDETAILS">
	            <tr>
	              <td><span><img class="pro_img" src="http://img.tulingbuy.com${ORDERDETAILS.product_imgurl }" width="50" height="50" ></br>${ORDERDETAILS.product_name }</span></td>
	              <td><span>${ORDERDETAILS.type_str }</span></td>
	              <td><span>${ORDERDETAILS.brand_str }</span></td>
	              <td><span>${ORDERDETAILS.xh }</span></td>
	              <td><span>${ORDERDETAILS.pz }</span></td>
	              <td><span>${ORDERDETAILS.product_count }</span></td>
	              <td><span><a href="/service/toadd.html?order_number=${ORDER.order_number }&order_detail_id=${ORDERDETAILS.id }">申请</a></span></td>
	            </tr>
            </c:forEach>
          </tbody>
        </table>
     </div>
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
