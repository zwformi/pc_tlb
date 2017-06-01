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
<title>我要查---需求单---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script src="<%=basePath%>js/order_show.js"></script>
<input id="type" type="hidden" value="demand">
<input id="state1" type="hidden" value="${XUQIU.order_state }">

<script type="text/javascript">
	$(document).ready(function($){
	
		//需要转换的字段
  		var order_stateList = getStringmap('order_info_xuqiu','order_state');
	    var order_sourceList = getStringmap('order_info_xuqiu','order_source');
     
  	$("td[name=order_state]").html(order_stateList["${XUQIU.order_state}"]);
  	$("td[name=order_source]").html(order_sourceList["${XUQIU.order_source}"]);
		//商品图片加载错误事件
		$("img.pro_img").on("error",function(){
			$(this).attr("src","/images/no_pic.png");
		});
		//接受报价
		$("#but_jsbj").click(function(){
				if(confirm('确认要接受这次的报价吗？')){
           		var id=$("#order_xuqiu_id").val();
				$.post("<%=basePath%>order/jsbj.json",{"id":id},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("操作成功，请等待客服人员与您联系！",function(){
							location.reload();
						});
					}
		        });
			}
		});
		
	
		//关闭
		$("#but_cancle").click(function(){
				if(confirm('确认要关闭本需求吗？')){
           		var id=$("#order_xuqiu_id").val();
           		var order_number=$("#order_number").val();
				$.post("<%=basePath%>order/cancelxuqiu.json",{"id":id,"order_number":order_number},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("需求单成功关闭！",function(){
							location.reload();
						});
					}
		        });
			}
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
       <a class="select" href="/find/find_demand.html">需求单</a>
       <a href="/find/find_contract.html">订单合同</a>
       <a href="/find/find_service.html">服务单</a>
     </h3>
     
        <div class="order_block">
    
   
	<!--    进度条 -->
   	<div class="order_process" style="width:920px;">

   	<!-- 图标本体 -->
	<div class="node wait" style="margin-left:100px;">
	<span class="iconfont  node_icon icon1 icon-fabuxuqiu"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">提交需求</li>
	
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce1 procebar"></span>
	<span class="iconfont icon-shenglvehao proce1 procebar"></span>
	<span class="iconfont icon-shenglvehao proce1 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-dengdaibaojia node_icon icon2"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">等待报价</li>
	
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce2 procebar"></span>
	<span class="iconfont icon-shenglvehao proce2 procebar"></span>
	<span class="iconfont icon-shenglvehao proce2 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-yibaojia node_icon icon3"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">确认报价</li>
	
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce3 procebar"></span>
	<span class="iconfont icon-shenglvehao proce3 procebar"></span>
	<span class="iconfont icon-shenglvehao proce3 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-0049weifudingdan node_icon icon4"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">生成订单</li>
	<li class="txt3"><br/>
	</li>
	
	</ul>
	</div>
	
	
	</div>

	
	
	</div>
     <div class="order_cont" style="min-height:395px;">
     <div class="demand_table">
     	<table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0" >
	      <thead>
           <tr>
              <th colspan="2" style="text-align:left;">&nbsp;&nbsp;需求详情</th>
             
           
           </tr>
          </thead>
	     	 <tr>
	         	<td width="25%"><span>需求单号</span></span></td>
	         	<td>${XUQIU.order_number }</td>
	         </tr>
	         <tr>
	         	<td><span>需求来源</span></td>
	         	<td name="order_source">${XUQIU.order_source}</td>
	         </tr>
	         <tr>
	         	<td><span>状态</span></td>
	         	<td name="order_state">${XUQIU.order_state}</td>
	         </tr>
	         <tr>
	         	<td><span>时间进度</span></td>
	         	<td>
	         		<c:if test="${XUQIU.order_state==4||XUQIU.order_state==5||XUQIU.order_state==6||XUQIU.order_state==7||XUQIU.order_state==8 }">需求发布：<fmt:formatDate value="${XUQIU.order_time }" type="both"/></c:if>
	              	<c:if test="${XUQIU.order_state==5||XUQIU.order_state==6||XUQIU.order_state==7||XUQIU.order_state==8 }"><br/>系统报价：${XUQIU.fmt_bj_time }</c:if>
	              	<c:if test="${XUQIU.order_state==7||XUQIU.order_state==8 }"><br/>接受报价：${XUQIU.fmt_khqr_time }</c:if>
	              	<c:if test="${XUQIU.order_state==8 }"><br/>合同生成：${XUQIU.fmt_htsc_time }</c:if>
	         	
	         	</td>
	         </tr>
	         <c:if test="${XUQIU.order_source==0 }">
		         <tr>
		         	<td><span>需求备注</span></td>
		         	<td>${XUQIU.content }</td>
		         </tr>
			</c:if>
			<c:if test="${XUQIU.order_source==2 }">
				<tr>
		         	<td><span>需求标题</span></td>
		         	<td>${XUQIU.title }</td>
		         </tr>
		         <tr>
		         	<td><span>需求备注</span></td>
		         	<td>${XUQIU.content }</td>
		         </tr>
			</c:if>
	         <tr>
	         	<td><span>需求附件</span></td>
	         	<td>
					<c:if test="${(XUQIU.demand_file==null||XUQIU.demand_file=='')&&(XUQIU.demand_file2==null||XUQIU.demand_file2=='')&&(XUQIU.demand_file3==null||XUQIU.demand_file3=='') }">
	        			无附件
	        		</c:if>
	        		<c:if test="${XUQIU.demand_file!=null&&XUQIU.demand_file!='' }">
	        			${XUQIU.demand_file } <a href="${XUQIU.demand_file }" target="_blank">查看附件</a><br/>
	        		</c:if>
	        		<c:if test="${XUQIU.demand_file2!=null&&XUQIU.demand_file2!='' }">
	        			${XUQIU.demand_file2 } <a href="${XUQIU.demand_file2 }" target="_blank">查看附件2</a><br/>
	        		</c:if>
	        		<c:if test="${XUQIU.demand_file3!=null&&XUQIU.demand_file3!='' }">
	        			${XUQIU.demand_file3 } <a href="${XUQIU.demand_file3 }" target="_blank">查看附件3</a>
	        		</c:if>
				</td>
	         </tr>
	         </table>
	        </div>
	        
	        
	        <div class="demand_table"> 
	         <table class="order_table" width="100%"  border="0" cellspacing="0" cellpadding="0" margin="0 20px 0 0">
	         <thead>
           <tr>
              <th colspan="2" style="text-align:left;">&nbsp;&nbsp;收货信息</th> 
           </tr>
          </thead>
			<tr>
	         	<td width="25%"><span>姓名</span></td>
	         	<td>${XUQIU.xm }</td>
	        </tr>
	        <tr>
	        	<td><span>电话</span></td>
	        	<td>${XUQIU.dh }</td>
	        </tr>
	        <tr>	
	        	<td><span>邮编</span></td>
	        	<td>${XUQIU.yb }</td>
	        </tr>
	        <tr>	
	        	<td><span>地址</span></td>
	        	<td>${XUQIU.dz }</td>
	         </tr>
     	</table>
     	</div>
     	
     	<div class="demand_table">
       <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0" margin="0 20px 0 0">
         <thead>
           <tr>
             <th width="40%">商品名称</th>             
             <th width="20%">配置</th>
             <th width="6%">数量</th>
             <th width="11%">标准价</th>
             <th width="11%">报价</th>
             <c:if test="${ XUQIU.order_state>4 && XUQIU.order_state!=6}"><th width="11%">小计</th></c:if>
           </tr>
          </thead>
          <tbody>
            <c:if test="${ORDERDETAILSXUQIULIST== null || fn:length(ORDERDETAILSXUQIULIST) == 0}">
   				<tr><td  colspan="9">该需求还没有任何的报价信息...</td></tr>
   		 	</c:if>
          	<c:forEach items="${ORDERDETAILSXUQIULIST }" var="ORDERDETAILSXUQIU">
	            <tr>
	               <td ><a style="display:inline;" href="/buy/buy_show.json?ID=${ORDERDETAILSXUQIU.product_id}" target= _blank>
	            	<img class="pro_img" src="http://img.tulingbuy.com${ORDERDETAILSXUQIU.product_imgurl }" width="80" height="80" style="margin: auto 10px auto 10px;vertical-align:middle;">
	            	<span style="display:inline;margin:auto" style=>${ORDERDETAILSXUQIU.product_name }</span></a></td>
	             <td><span>${ORDERDETAILSXUQIU.pz }</span></td>
	              <td><span>${ORDERDETAILSXUQIU.product_count }</span></td>
	              <td><span>
	              		<c:if test="${ORDERDETAILSXUQIU.product_unit_price==0 }">暂无</c:if>         
	     				<c:if test="${ORDERDETAILSXUQIU.product_unit_price!=0 }">    
	              			￥<fmt:formatNumber type="number" value="${ORDERDETAILSXUQIU.product_unit_price }" pattern="0.00" maxFractionDigits="2"/>
	              		</c:if>
	              </span></td>
	              <td><span>
	              	<c:if test="${ORDERDETAILSXUQIU.product_unit_price_bj==0 }">暂无</c:if>         
	     				<c:if test="${ORDERDETAILSXUQIU.product_unit_price_bj!=0 }">    
	              			￥<fmt:formatNumber type="number" value="${ORDERDETAILSXUQIU.product_unit_price_bj }" pattern="0.00" maxFractionDigits="2"/>
	              		</c:if>
	              	</span></td>
	             <c:if test="${ XUQIU.order_state>4 && XUQIU.order_state!=6}"> <td><span>￥<fmt:formatNumber type="number" value="${ORDERDETAILSXUQIU.product_unit_price_bj*ORDERDETAILSXUQIU.product_count }" pattern="0.00" maxFractionDigits="2"/></span></td></c:if>
	            </tr>
            </c:forEach>
          </tbody>
        </table>
        </div>
       <div style="width:390px;margin:auto 65px auto auto;text-align:right" id="but_div">
        	<input id="order_xuqiu_id" type="hidden" value="${XUQIU.id}"/>
        	<input id="order_number" type="hidden" value="${XUQIU.order_number}"/>
        	<c:if test="${XUQIU.order_state==5 }"><input type="button" value="接受报价" name="input" id="but_jsbj" class="red_btn"></c:if>
        	<c:if test="${XUQIU.order_state!=6&&XUQIU.order_state!=8 }"><input type="button" value="关闭" name="input" id="but_cancle" class="red_btn"></c:if>
        </div>
     </div>
   </div>
   
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>