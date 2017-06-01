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
<title>我要查---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style>
.spilled_hide{
	height:42px;
	overflow : hidden;
}
</style>
<script type="text/javascript">
	$(document).ready(function($){
		//商品图片加载错误事件
		$("img.pro_img").on("error",function(){
			$(this).attr("src","/images/no_pic.png");
		});
	});
</script>
<style type="text/css">
.mya{
margin-top:5px;
width:200px;
display:inline-block;
}
.card{
	
	width:180px;
	min-height:100px;
	background-color:#f0f0f0;
	text-align:left;

}
.card:hover{
	background-color:#eaeaea;

}
.card .left{
margin:20px 0 0 30px;
float:left;

}
.card .left>h1{
color:#D83E36;

}

.card .right{
color:#D83E36;
float:right;
margin:40px 30px 0 0;
}
.card .right>i{
font-size:30px;
}
</style>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
        <jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center" >
 <div class="member_content" style="min-height:auto;">
     <h3>
     	<a href="javascript:void(0);" class="select"><span style="font-size:13px;">订单服务预览</span></a>
     </h3>
     <div class="order_cont" style="min-height:100px;">
	
	<dl>
         <dt>我的评价</dt>
         <dd>
         <a href="/find/find_contract.html?order_state=7&page=find_evaluate" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>待评价</span>
 				<h1><b>${XGTJ.ht_7 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-daipingjia" ></i>
 				</div>
 			</div>
 			</a>
 			<a href="/find/find_contract.html?order_state=8&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>已评价</span>
 				<h1><b>${XGTJ.ht_8 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-renwu" ></i>
 				</div>
 			</div>
 			</a>
            <%-- <span>待评价订单合同: <b><a style="color:red" href="/find/find_contract.html?order_state=7" target="_blank">${XGTJ.ht_7 }</a></b> </span>
            <span>已评价订单合同: <b><a style="color:red" href="/find/find_contract.html?order_state=8" target="_blank">${XGTJ.ht_8 }</a></b> </span> --%>
         </dd>
       </dl> 

        
       <dl>
         <dt>合同提醒</dt>
         <dd>
            <a href="/find/find_contract.html?order_state=0&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>待支付</span>
 				<h1><b>${XGTJ.ht_0 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-0049weifudingdan" ></i>
 				</div>
 			</div>
 			</a>
          <a href="/find/find_contract.html?order_state=4&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>配货中</span>
 				<h1><b>${XGTJ.ht_4 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-daishouhuo" ></i>
 				</div>
 			</div>
 			</a>
          <a href="/find/find_contract.html?order_state=5&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>待签收</span>
 				<h1><b>${XGTJ.ht_5 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-1348weituogoumai" ></i>
 				</div>
 			</div>
 			</a>
          <a href="/find/find_contract.html?order_state=61&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>待实施</span>
 				<h1><b>${XGTJ.ht_61 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-weixiu" ></i>
 				</div>
 			</div>
 			</a>
          <a href="/find/find_contract.html?order_state=62&page=find_contact" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>实施中</span>
 				<h1><b>${XGTJ.ht_62 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-woyaoweixiu" ></i>
 				</div>
 			</div>
 			</a>
            <%-- <span>配货中: <b><a style="color:red" href="/find/find_contract.html?order_state=4" target="_blank">${XGTJ.ht_4 }</a></b>  </span>
            <span>待签收: <b><a style="color:red" href="/find/find_contract.html?order_state=5" target="_blank">${XGTJ.ht_5 }</a></b> </span>
            <span>待实施: <b><a style="color:red" href="/find/find_contract.html?order_state=61" target="_blank">${XGTJ.ht_61 }</a></b> </span>
            <span>实施中: <b><a style="color:red" href="/find/find_contract.html?order_state=62" target="_blank">${XGTJ.ht_62 }</a></b> </span> --%>
         </dd>
       </dl>
       <%-- <dl>
         <dt>需求提醒</dt>
         <dd>
 			     <!-- card start -->
 			<a href="/find/find_demand.html?order_state=4&page=find_demand" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>待报价 </span>
 				<h1><b>${XGTJ.xq_4 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-dengdaibaojia" ></i>
 				</div>
 			</div>
 			</a>
 			<a href="/find/find_demand.html?order_state=5&page=find_demand" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>完成报价</span>
 				<h1><b>${XGTJ.xq_5 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-yibaojia" ></i>
 				</div>
 			</div>
 			</a>
 			<a href="/find/find_demand.html?order_state=7&page=find_demand" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>已接受报价</span>
 				<h1><b>${XGTJ.xq_7 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-xuanzeyixuanze" ></i>
 				</div>
 			</div>
 			</a>
 			
 			<a href="/find/find_demand.html?order_state=8&page=find_demand" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>已生成合同</span>
 				<h1><b>${XGTJ.xq_8 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="fa fa-file-text-o" ></i>
 				</div>
 			</div>
 			</a>
 			
 			<a href="/find/find_demand.html?order_state=6&page=find_demand" target="_blank" class="mya">
 			<div class="card">
 				<div class="left">
 				<span>已关闭</span>
 				<h1><b>${XGTJ.xq_6 }</b></h1>
 				<span>项</span>
 				</div>
 				<div class="right">
 				<i class="iconfont icon-icowait" ></i>
 				</div>
 			</div>
 			</a>
 			
 			<!-- card finish -->
            <span><b><a style="color:red" href="/find/find_demand.html?order_state=4" target="_blank">${XGTJ.xq_4 }</a></b> </span>
            <span>已报价: <b><a style="color:red" href="/find/find_demand.html?order_state=5" target="_blank">${XGTJ.xq_5 }</a></b>  </span>
            <span>已接受报价: <b><a style="color:red" href="/find/find_demand.html?order_state=7" target="_blank">${XGTJ.xq_7 }</a></b>  </span>
            <span>已生成合同: <b><a style="color:red" href="/find/find_demand.html?order_state=8" target="_blank">${XGTJ.xq_8 }</a></b>  </span>
            <span>已关闭: <b><a style="color:red" href="/find/find_demand.html?order_state=6" target="_blank">${XGTJ.xq_6 }</a></b>  </span>
         </dd>
       </dl> --%>
     </div>
   </div>
   <!--热卖推荐-->
   <div class="you_like">
     <h3>热卖推荐</h3>
     <div class="rollBox"> 
      <div class="LeftBotton" onMouseDown="ISL_GoUp()" onMouseUp="ISL_StopUp()" onMouseOut="ISL_StopUp()">&lt;</div> 
       <div class="Cont" id="ISL_Cont"> 
        <div class="ScrCont"> 
         <div id="List1"> 
        <!-- 图片列表 begin --> 
           
           <c:forEach items="${PRODUCTREDLIST }" var="PRODUCTRED">
           <a target="_blank" href="/buy/buy_show.json?ID=${PRODUCTRED.id }">
		     <dl class="pic">
             <dt>
               <img src="http://img.tulingbuy.com${PRODUCTRED.img_url }" width="200" height="200" class="pro_img"/>
               <span class="spilled_hide" title="${PRODUCTRED.name }">${PRODUCTRED.name }</span>
             </dt>
             <dd>特价<b>￥<fmt:formatNumber type="number" value="${PRODUCTRED.price_new }" pattern="0.00" maxFractionDigits="2"/></b></dd>
           </dl>
           </a>
       	</c:forEach>
         <!-- 图片列表 end --> 
         </div> 
          <div id="List2"></div> 
        </div> 
      </div> 
      <div class="RightBotton" onMouseDown="ISL_GoDown()" onMouseUp="ISL_StopDown()" onMouseOut="ISL_StopDown()">&gt;</div> 
    </div>
    <script language="javascript" type="text/javascript" src="<%=basePath %>js/img_gd.js"></script>
     
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
