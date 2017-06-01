<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String order_number = request.getParameter("order_number");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="Keywords" content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description" content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<title>订单合同---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/raty/jquery.raty.min.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<script src="<%=basePath%>js/order_show.js"></script>
<input id="type" type="hidden" value="order">
<input id="state1" type="hidden" value="${ORDER.order_state }">
<script type="text/javascript">
$(document).ready(function($){

	   //需要转换的字段
  	var order_stateList = getStringmap('order_info','order_state');
  	var shipping_methodsList = getStringmap('order_info','shipping_methods');
  	var installation_serviceList = getStringmap('order_info','installation_service');
     
  	$("span[name=order_state]").html(order_stateList["${ORDER.order_state}"]);
  	$("span[name=shipping_methods]").html(shipping_methodsList["${ORDER.shipping_methods}"]);
	$("span[name=installation_service]").html(installation_serviceList["${ORDER.installation_service}"]);
	
		//隐藏收货信息时的布局
		if("${ORDER.order_source}"==4){
			$(".show").css("width","49%");
			
		}
	
	//商品图片加载错误事件
 		$("#download_con").click(function(){
			var form=$("<form>");//定义一个form表单
			form.attr("style","display:none");
			form.attr("target","");
			form.attr("method","post");
			form.attr("action","/download/contract.json?order_number=<%=order_number%>&type=order");
			var input1=$("<input>");
			input1.attr("type","hidden");
			input1.attr("name","exportData");
			input1.attr("value",(new Date()).getMilliseconds());
			
			$("body").append(form);//将表单放置在web中
			form.append(input1);

			form.submit();//表单提交
			     });   
			     
		$("img.pro_img").on("error",function(){
			$(this).attr("src","/images/no_pic.png");
		});
		
		//签收
		$("#but_qs").click(function(){
			layer.confirm('确认要执行签收操作吗？<br/>签收请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
           		var id=$("#order_id").val();
				$.post("<%=basePath%>order/qs.json",{"id":id},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("签收成功！",function(){
							location.reload();
						});
					}
		        });
			});
		});
		
		//取消订单
		$("#but_qxdd").click(function(){
			layer.confirm('确认要执行取消订单操作吗？<br/>确认取消请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
           		var id=$("#order_id").val();
				$.post("<%=basePath%>order/qxdd.json",{"id":id,"order_number":"<%=order_number%>"},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("取消成功！",function(){
							location.reload();
						});
					}
		        });
			});
		});
		
		//签收并发起实施
		$("#but_qsss").click(function(){
			layer.confirm('确认要执行签收后发起实施操作吗？<br/>如果是请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
           		var id=$("#order_id").val();
				$.post("<%=basePath%>order/qsss.json",{"id":id},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("签收成功，发起实施服务成功！",function(){
							location.reload();
						});
					}
		        });
			});
		});
		
		//确认实施完成
		$("#but_qrsswc").click(function(){
			layer.confirm('确认要执行实施完成操作吗？<br/>如果是请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
           		var id=$("#order_id").val();
				$.post("<%=basePath%>order/qrsswc.json",{"id":id},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("操作实施完成成功！",function(){
							location.reload();
						});
					}
		        });
			});
		});
		
		//去评价
      	$.fn.raty.defaults.path = '<%=basePath%>js/raty/img';
      	$('#score_div').raty({ score: 5 });
      	
      	$("#but_tjpj").click(function(){
      		var score_ = $("#score_div").find("input[name='score']").val();
      		 var pjnr_ = $("#pjnr").val();
      		 if(pjnr_==""){
      		 	layer.msg("请输入评价内容！");
      		 }else{
      		 	//提交评价
           		var order_id=$("#order_id").val();
				$.post("/order/pj.json",{"order_id":order_id,"score":score_,"content":pjnr_},function(data){
					if(data.error){
						layer.msg(data.message);
					}else if(data.count>0){
						layer.alert("评价成功！",function(){
							parent.location.reload();
						});
					}
		        });
			}
      	});
	});
</script>
</head>

<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="zi_main">
<!--left-->
		<jsp:include page="left_menu.jsp"></jsp:include>
		<!-- center -->
		<div class="member_center">

 <div class="member_content" style="min-height:auto;">
      <h3>
      <!--  <a href="/find/find_demand.html">需求单</a> -->
       <a class="select" href="/find/find_contract.html">订单合同</a>
       <a href="/find/find_service.html">服务单</a>
     </h3>

      <div class="order_block  contract">
     
  	<c:if test="${ORDER.order_state!=9&&ORDER.order_source!=4 }">
	<!--    进度条 -->
   	<div class="order_process" >

  
   	<!-- 图标本体 -->
	<div class="node wait" style="margin-left:70px;">
	<span class="iconfont icon-jiudianjingquwanlexiangqingyeicon12 node_icon icon1"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">提交订单</li>
	<li class="txt3">${ORDER.fmt_order_time}<br/>
	</li>
	
	</ul>
	</div>
	
	
	<!-- 箭头 -->
	<div class="proce wait" >
	<span class="iconfont icon-shenglvehao proce1 procebar"></span>
	<span class="iconfont icon-shenglvehao proce1 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>

	
	<div class="node wait" >
	<span class="iconfont icon-xiaoshouchuku node_icon icon2"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">商品出库</li>
	<li class="txt3"></li>
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce2 procebar"></span>
	<span class="iconfont icon-shenglvehao proce2 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-daishouhuo node_icon icon3"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">等待收货</li>
	<li class="txt3"><br/>
	</li>
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce3 procebar"></span>
	<span class="iconfont icon-shenglvehao proce3 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-daipingjia node_icon icon4"></span>
	<ul>
	<li class="txt1">&nbsp;&nbsp;</li>
	<li class="txt2">等待评价</li>
	<li class="txt3"><br/>
	</li>
	
	</ul>
	</div>
	
	<!-- 箭头 -->
	<div class="proce wait">
	<span class="iconfont icon-shenglvehao proce4 procebar"></span>
	<span class="iconfont icon-shenglvehao proce4 procebar"></span>
	<ul>
		<li class="txt1"></li>
	</ul>
	</div>
	
	   	<!-- 图标本体 -->
	<div class="node wait">
	<span class="iconfont icon-wancheng node_icon icon5"></span>
	<ul>
	<li class="txt1">&nbsp;</li>
	<li class="txt2">完成</li>
	<li class="txt3">${ORDERSM.fmt_add_time}<br/>
	</li>
	
	</ul>
	</div>

	
	
	</div>
	
   
   
    <div class="trackrcol">
	<div class="track_left" style="padding:20px;position:relative;">
		<div class="abstract_head">
		现在状态:
		</div>
		
		<div style="margin-top:100px;line-height:50%;text-align:center;height:40%;color:#EA6561;"><p style="vertical-align:middle;"><h2 id="statechange1">${ORDER.order_state }</h2></div>
		
	    <c:if test="${ORDER.order_state>0 && not empty ORDER.order_state && not empty ORDER.demand_file}"><div class="download" style="text-align:center;">	<a href="http://img.tulingbuy.com${ORDER.demand_file }" target="_blank"><span class="iconfont icon-OAbangong"></span>合同扫描件下载</a></div></c:if> 
 		<c:if test="${ORDER.order_state>0 && not empty ORDER.order_state && empty ORDER.demand_file}"><div class="download" style="text-align:center;"><a id="download_con" ><span class="iconfont icon-OAbangong"></span>电子合同下载</a></div></c:if> 
		
		<c:if test="${ORDER.order_state==0}"><div class="download" style="text-align:center;"><a href="<%=basePath%>pay.html?order_number=${ORDER.order_number}"><span class="iconfont icon-0049weifudingdan"></span>立即支付</a></div></c:if>
</div>
	<div class="tracklist">
	<ul>
	<c:forEach items="${ORDERSMLIST }" var="ORDERSM" varStatus="stat">
	<li <c:if test="${stat.first}">class="first"</c:if>>
	<i class="node-icon"></i>
	<span>${ORDERSM.fmt_add_time}&nbsp;&nbsp;【${ORDERSM.type }】&nbsp;&nbsp;${ORDERSM.text_sm }<br/></span>
	<input type="hidden" name="${ORDERSM.type }" value="${ORDERSM.fmt_add_time}">
	</li>
	</c:forEach>
	</ul>
	</div>

	
	</div>
	</c:if>
	<div class="abstractrcol " >
	<div class="tracktop"><span>合同摘要</span>
	</div>
	<div  style="padding:10px;">

	<div class="order_abstract show"  style="border:0px solid #fff">
	<div class="abstract_head">
	<h2>订单信息</h2>
	</div>
	<div>
	<ul>
	<li>
	<span class="label">合同编号:</span>
	<span class="info_rcol">${ORDER.order_number}</span>
	</li>
	<li>
	<span class="label">合同生效日期：</span>
	<span class="info_rcol"><fmt:formatDate value="${ORDER.order_time }" type="both"/></span>
	</li>
	<li>
	<span class="label">对应需求单号：</span>
	<span class="info_rcol"><c:if test="${empty ORDER.xuqiu_ordernumber}">暂无</c:if>
							<c:if test="${!empty ORDER.xuqiu_ordernumber}">${ORDER.xuqiu_ordernumber}</c:if>
	</span>
	</li>

	<li>
	<span class="label">合同扫描件:</span>
	<span class="info_rcol"><c:if test="${ORDER.demand_file==null||ORDER.demand_file=='' }">
	        			无
	        		</c:if>
	        		<c:if test="${ORDER.demand_file!=null&&ORDER.demand_file!='' }">
	        			<a href="http://img.tulingbuy.com${ORDER.demand_file }" target="_blank">查看</a>
	        		</c:if></span>
	    </li>
	</ul>
	</div>
	</div>
		<div class="order_abstract show">
		<div class="abstract_head">
		<h2>配送安装信息</h2>
		</div>
		<div>
		
		<ul>
			<li>
	<span class="label">付款方式:</span>
	<span class="info_rcol">
			<c:if test="${empty ORDER.fkfs}">未指定</c:if>
			<c:if test="${!empty ORDER.fkfs}">${ORDER.fkfs}</c:if></span>
	</li>
	<li>
	<span class="label">交付状态:</span>
	<span class="info_rcol" name="order_state">${ORDER.order_state}
    </span>
    </li>
	<li>
	<span class="label">应收款状态:</span>
	<span class="info_rcol"><c:if test="${empty ORDER.yskzt}">暂无</c:if>
	<c:if test="${!empty ORDER.yskzt}">${ORDER.yskzt}</c:if>
	</span>
	</li>
		
		<li>
		<span class="label">配送方式：</span>
		<span class="info_rcol" name="shipping_methods">${ORDER.shipping_methods}
		</span>
		</li>
			<li>
			<span class="label">安装信息：</span>
			<span class="info_rcol" name="installation_service">${ORDER.installation_service}
           	</span>
	</li></ul></div>
	</div>
	<c:if test="${ORDER.order_source!=4 }">
		<div class="order_abstract">
	<div class="abstract_head">
	<h2>收货信息</h2>
	</div>
	<div>
	<ul>
	<li>
	<span class="label">收货地址：</span>   
	 <span class="info_rcol">${ORDER.shr_dz}</span>
	 </li>
	<li>
	<span class="label">收货人姓名：</span>
	<span class="info_rcol">${ORDER.shr_xm}</span>
	</li>
	<li>
	<span class="label">收货人电话：</span>
	<span class="info_rcol">${ORDER.shr_dh}</span>
	</li>
	<li>
	<span class="label">收货人邮编：</span>
	<span class="info_rcol">${ORDER.shr_yb}</span>
	
	
	</li>
	</ul>
	</div>
	</div>
	</c:if>
	</div>

	</div>
	
	        

	
	<div  style="width:930px;margin:20px auto 10px auto;min-Height:120px;border:0px" >
       <table class="order_table" width="100%" border="0" cellspacing="0" cellpadding="0">
         <thead>
           <tr>
              <th width="36%" colspan="2">商品</th>    
             <th width="16%">配置</th>
             <th width="6%">数量</th>
             <th width="10%">报价</th>
             <th width="10%">合同价</th>
             <th width="10%">小计</th>
             <c:if test="${ORDER.order_state==4 }"><th width:"11%">备货情况</th></c:if>
           </tr>
          </thead>
          <tbody>
            <c:if test="${ORDERDETAILSLIST== null || fn:length(ORDERDETAILSLIST) == 0}">
   				<tr><td  colspan="9">该合同内的商品清单为空...</td></tr>
   		 	</c:if>
          	<c:forEach items="${ORDERDETAILSLIST }" var="ORDERDETAILS">
	            <tr>
	               <td style="border-right:none;"><a style="display:inline;" href="/buy/buy_show.json?ID=${ORDERDETAILS.product_id}" target= _blank>
	            	<img class="pro_img" src="http://img.tulingbuy.com${ORDERDETAILS.product_imgurl }" width="80" height="80" style="margin: auto 10px auto 10px;vertical-align:middle;">
	            	</a></td>
	               <td  style="border-left:none;"><a style="display:inline;" href="/buy/buy_show.json?ID=${ORDERDETAILS.product_id}" target= _blank>
	            	<span style="display:inline;margin:auto" style=>${ORDERDETAILS.product_name }</span></a></td>
	             <td><span><c:if test="${empty ORDERDETAILS.xh}&&${empty ORDERDETAILS.pz}">无</c:if>  
	             ${ORDERDETAILS.xh}<c:if test="${empty ORDERDETAILS.pz}"></c:if>
	             <c:if test="${!empty ORDERDETAILS.pz}">(${fn:replace(fn:replace(ORDERDETAILS.pz, "(", ""),")","")})</c:if></span></td>
	              <td><span>${ORDERDETAILS.product_count }</span></td>
	              <td>
	              	<span>
	     				<c:if test="${ORDERDETAILS.product_unit_price_bj==0 }">暂无</c:if>         
	     				<c:if test="${ORDERDETAILS.product_unit_price_bj!=0 }">          
	              			￥<fmt:formatNumber type="number" value="${ORDERDETAILS.product_unit_price_bj }" pattern="0.00" maxFractionDigits="2"/>
	              		</c:if> 
	              	</span></td>
	              <td><span>￥<fmt:formatNumber type="number" value="${ORDERDETAILS.product_unit_price }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <td><span>￥<fmt:formatNumber type="number" value="${ORDERDETAILS.product_unit_price*ORDERDETAILS.product_count }" pattern="0.00" maxFractionDigits="2"/></span></td>
	              <c:if test="${ORDER.order_state==4 }">
	              	<td ><span>
						<c:choose>  
						    <c:when test="${fn:length(ORDERDETAILS.bhqk) > 5}">  
						        <c:out value="${fn:substring(ORDERDETAILS.bhqk, 0, 5)}..." />  
						    </c:when>
						    <c:when test="${empty bhqk}"> 
						    	<c:out value="暂无" />  
						    </c:when>
						   <c:otherwise>  
						      <c:out value="${ORDERDETAILS.bhqk}" />  
						    </c:otherwise>  
						</c:choose> 
						</span>
					</td>
				  </c:if>
	            </tr>
            </c:forEach>
          </tbody>
        </table>
        </div>
       
       <div style="width:930px;text-align:right;margin:auto auto 30px auto;min-height:30px;" id="but_div">
        	<input id="order_id" type="hidden" value="${ORDER.id}"/>
        	<c:if test="${ORDER.order_state==5 }">
        		<input type="button" value="签收不实施" name="input" id="but_qs" class="red_btn">
        		<input type="button" value="签收后实施" name="input" id="but_qsss" class="red_btn">
        	</c:if>
        	<c:if test="${ORDER.order_state==62 }">
				<input type="button" value="确认实施完成" name="input" id="but_qrsswc" class="red_btn">
			</c:if>
			<c:if test="${ORDER.order_state==71||ORDER.order_state==72 }">
					 <ul style="border:solid 1px #e0e0e0;text-align:center;">
						<li style="text-align: center;">
							<div style="text-align:center;" >
								<textarea style="width:600px;height:150px;padding:10px;margin:10px;" id="pjnr" placeholder="请输入评价"></textarea>
							</div>
						</li>
						<li style="text-align: center;">
						<div id="score_div" style="margin-right:350px;width:200px;display:inline-block;"></div>
						<div  style="text-align:right;display:inline-block;">
								<input type="button" value="提交评价" name="input" id="but_tjpj" class="red_btn" style="margin-bottom: 10px;">
							</div>
						</li>
					  </ul>
			</c:if>
			<c:if test="${ORDER.order_state==0 }">
				<input type="button" value="取消订单" name="input" id="but_qxdd" class="red_btn">
			</c:if>
        </div> 
        
        </div>
        </div>
     
   </div>
   </div>
   

<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
