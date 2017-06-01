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
<title>服务单---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
</head>

<body>
<jsp:include page="xtop.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center" >
 <div class="member_content" style="min-height:auto;">
     <h3>
<!--        <a href="/find/find_demand.html">需求单</a> -->
       <a href="/find/find_contract.html">订单合同</a>
       <a class="select" href="/find/find_service.html">服务单</a>
     </h3>
     <div class="order_cont" style="min-height:395px;">
       <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0">
         <thead>
           <tr>
             <th width="15%">服务单号</th>
             <th width="10%">联系人</th>
             <th width="10%">联系电话</th>
             <th width="10%">发布时间</th>
             <th width="10%">服务状态</th>
             <th width="20%">服务备注</th>
             <th width="10%">操作</th>
           </tr>
          </thead>
          <tbody>
          <c:forEach items="${SERVICELIST }" var="SERVICEA">
            <tr>
              <td><span>${SERVICEA.service_number }</span></td>
              <td><span>${SERVICEA.lxr }</span></td>
              <td><span>${SERVICEA.phone }</span></td>
              <td><span><fmt:formatDate value="${SERVICEA.add_time }" type="both"/></span></span></td>
              <td><span>
              	<c:if test="${SERVICEA.state==0 }">待服务</c:if>
         			<c:if test="${SERVICEA.state==1 }">服务完成</c:if>
         			<c:if test="${SERVICEA.state==2 }">已安排</c:if>
         			<c:if test="${SERVICEA.state==3 }">服务中</c:if>
         			<c:if test="${SERVICEA.state==4 }">已取消</c:if>
         			<c:if test="${SERVICEA.state==5 }">已受理</c:if>
         		</span></td>
              <td style="text-align:center;">${SERVICEA.comment }</td>
              <td style="padding-right:2%;">
               <span>
                 <a href="/find/find_edit_service.html?service_number=${SERVICEA.service_number }">查看</a>
               </span>
              </td>
            </tr>
            </c:forEach>
            <c:if test="${SERVICELIST== null || fn:length(SERVICELIST) == 0}">
            	<tr><td colspan="7">您还没有任何的服务单</td></tr>
            </c:if>
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

