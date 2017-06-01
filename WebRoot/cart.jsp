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
<title>采购车</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_file.js"></script>
<script src="<%=basePath %>js/cart.js"></script>
<%-- <script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script> --%>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">

	var special_code = 0;
	$(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_demand.json", swf: "/js/uploader/uploader.swf",btntext:"上传附件" });
    });
    $(document).ready(function(){    
	 $("#xuqiu_1").hide();
	  $("#xuqiu_2").hide();
	  $("#xuqiu").on("click",function(){
		if($(this).is(':checked')) {//是否选择--选中
			 if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/8./i)=="8."){
			   $("#xuqiu_1").show();
			 } 
			 else
			 {
			   $("#xuqiu_1").show();
			   $("#xuqiu_2").show();
			 }
		}
		if(!$(this).is(':checked')) {//是否选择--全不选
			$("#xuqiu_1").hide();
	  		$("#xuqiu_2").hide();
		}
		$(document.body).find('*').trigger('resize');
	});
	


	});

</script>
<style type="text/css">
	.upload-path{width:360px;height:30px;border:1px solid #dedede;float:left;margin-left:10px;margin-right:0px;}
</style>

</head>

<body>
<jsp:include page="ctop.jsp">
	<jsp:param name="step" value="1" />
</jsp:include>
<div class="zi_main">
<!--left-->

 <!--right-->
 <div class="main_right1" style="width:1300px;">
  <dl class="cart_cont">
     <dt><span><span class="iconfont icon-caigou" style="display:inline-block;font-size:20px;color:#D74340"></span>我的采购车</span></dt>
     <dd>
     <form action="" name="carts"  id="carts" method="post">
      <table class="cart_table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
          	<th width="70px" class="tableleft">选择</th>
            <th width="600px">商品</th>
            <th width="150px" id="singlePrice">单价(元)</th>
            <c:if test="${ SHOPPINGCARTLIST!=null && fn:length(SHOPPINGCARTLIST)>0}">
			<th width="75px" id="tulingbuyPrice">图灵买价</th>
			<th width="75px" id="internalPrice">内购特价</th>
			</c:if>
            <c:forEach items="${SHOPPINGCARTLIST }" var="product" varStatus="vstatus">
            	<c:if test="${ product.special_code == 1}">
            	<script>
            	$("#singlePrice").remove();
            	if(special_code!=1){
            	special_code =1;
					            	
            	}
            	
            	</script>
            	</c:if>
				<c:if test="${ product.special_code != 1 && vstatus.index == (fn:length(SHOPPINGCARTLIST)-1)}">
            	<script>
            	if(special_code!=1){
            	$("#tulingbuyPrice").remove();
            	$("#internalPrice").remove();
            	}
            	</script>
            	</c:if>
            </c:forEach>
           
            <th width="130px">数量</th>
            <th width="130px">小计(元)</th>
            <th width="110px">库存状态</th>
            <th width="70px" class="tableright">操作</th>
          </tr>
        </thead>
        <tbody id="contents">
            
        	<c:forEach items="${SHOPPINGCARTLIST }" var="product">
        	
	          <tr class="product" product_id="${product.product_id}" product_items_id="${product.product_items_id}" id="id_${product.product_id}_${product.product_items_id}">
	          	<td class="select" >...</td>
	            <td style="text-align:left;">
	            <a
	            <c:if test="${product.special_code!=1 }">
	            href="/buy/buy_show.json?ID=${product.product_id}&ITEM=${product.product_items_id }"
	            </c:if>
	            <c:if test="${product.special_code==1 }">
	            href="/buy/special_detail.html?ID=${product.product_id}&ITEM=${product.product_items_id }"
	            </c:if>
	             style="display: flex;" target= _blank>
	            	<div style="display: inline-block;">
	            	<img class="pro_img" src="http://img.tulingbuy.com${product.product_img_url}" width="80" height="80" style="margin-right:10px;">
	            	</div>
	            	<div style="display: inline-block;height:80px;">
	            	 <c:if test="${product.special_code == 1}">
	            	<div style="margin-top:20px;margin-left:5px;border-radius:8px;width:50px;height:20px;background-color:rgb(194,47,39);line-height:20px;color:#fff;padding:0px 5px;">内购商品</div>
	            	<span style="line-height:20px;">${product.product_name}${product.peizhi}</span>
	            	</c:if>	           	
	            	 <c:if test="${product.special_code != 1}">
	            	<span>${product.product_name}${product.peizhi}</span>
	            	</c:if>	
<%-- 	            	<c:if test="${product.special_code != 1 && fn:length(product.peizhi)<=2}">
	            	<span>${product.product_name}</span>
	            	</c:if>	 --%>
	            	</div>
	            	
	            	</a></td>

	            	<td id ="prize_show${product.product_id }"><span class="prize_show" >失效</span></td>
	            	<td><span class="prize">失效</span></td>
	            	<c:if test="${product.special_code == 0}">
	            	<script>
	            	if(special_code==0)
	            		$("#prize_show${product.product_id }").remove();
	            	</script>
	            	</c:if>	

	            <td class="count">
	            	<div class="count on" style="display:none;">
	                	<input class="btn_jie minus" type="button" value="-"><input class="buy_box number" name="product_counts" value="${product.product_count }" autocomplete="off" readonly="readonly" /><input class="btn_jie add" type="button" name="button" id="button" value="+">
	                	<input class="number_max" value="0" type="hidden"/>
	                </div>
	                <div class="count off"><span>商品已失效</span></div>
	            </td>
	            <td><span style="font-size:18px;"><font class="min_total">0.0</font></span></td>
	            <td class="kczt"><span>失效</span></td>
	            <td><a href="javascript:void(0)" class="delete"><span>删除</span></a></td>
	          </tr>
          </c:forEach>
          <c:if test="${SHOPPINGCARTLIST== null || fn:length(SHOPPINGCARTLIST) == 0}">
   			<tr><td  colspan="7"><span>~~啥都没有哦~~</span></td></tr>
   		 </c:if>
        </tbody>
        <tr class = "selectall1">
        	<td><input type="checkbox" id="selectall" AUTOCOMPLETE="off" style="margin:0px 5px 0px 25px"/>全选</td>
        	<td colspan="6" style="text-align:right;padding-right:20px; align="right" >总价(不含运费)：￥<span style="margin: 0px;color:red;font-size:18px;display:inline-block;line-height:25px;font-weight:700;"><font id="total">0.00</font></span></td>
        </tr>
        
        <tr>        	
        	<td colspan="8" style="text-align:left;font-weight:700;padding-left:10px">
        	 	<input type="checkbox" id="xuqiu" AUTOCOMPLETE="off" style="margin:0px 5px 0px 30px"/>如您还有其他采购需求，请您在下方描述您的采购信息，包括采购数量、品牌、规格、参数、等要求，我们将帮您轻松完成采购！        	
        	</td>
        </tr>
        
        <tr id="xuqiu_1">
        	<td>需求描述(选填)</td>
        	<td colspan="7" style="text-align:left;padding-left:10px"><textarea name="content" id="content" style="width:95%;"></textarea></td>
        </tr>
        <tr height="100" id="xuqiu_2">
        	<td>需求描述(选填)</td>
        	<td style="text-align:left;">
        		<input  id="file_url" name="file_url" type="text" readonly="readonly"  class="upload-path" />
        		<div class="upload-img" ></div>
        		<input  id="file_url2" name="file_url2" type="text" readonly="readonly"  class="upload-path" />
        		<div class="upload-img" ></div>
        		<input  id="file_url3" name="file_url3" type="text" readonly="readonly"  class="upload-path" />
        		<div class="upload-img" ></div>
        	</td>
        	<td colspan="5">最多可上传3个附件，且每个附件小于4M 格式为excel/word/jpg/png/jpeg/rar/zip/txt 文件</td>
        </tr>
       </table>
       </form>
       <div class="cart_bottom">
         <input class="cart_btn" type="submit" name="button2" value="订单确认" />
       </div>
     </dd>
  </dl>
 </div>

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
