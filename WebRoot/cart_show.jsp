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
<title>提交需求信息</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>/js/cart_show.js?1"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style type="text/css">
	.cart_show_table td{padding:0px 15px;}
	.address_div{display:block;height:36px;line-height:36px;width:100%;border-bottom:1px dotted #efefef;font-size:14px;color:#373737;}
	.address_div:hover{background:#FFF0E8;color:#373737;}
	.address_div.active{background:#FFF0E8;height:35px;border:1px solid #FF4400;font-size:15px;font-weight:bold;}
	.address_div i{color:red;font-size:20px;} 
	.address_div input{vertical-align:middle;height:30px;margin-left:30px;}
	.edit_address{float:right;margin-right:30px;color:#4599cc;cursor:pointer;}
	.cart_blue{color:#4599cc;font-size:13px;}
	.cart_blue:hover{text-decoration:underline;color:#4599cc;}
	.key_point{color:#999;font-size:bold;}
</style>
</head>

<body>
<jsp:include page="ctop.jsp">
	<jsp:param name="step" value="2" />
</jsp:include>
<div class="zi_main">
<!--left-->
 
 <!--right-->
 <div class="main_right1" style="width:1300px;">
  <dl class="cart_cont">
     <dt><span><span class="iconfont icon-caigou" style="display:inline-block;font-size:20px;color:#D74340"></span>填写并核对<c:if test="${resource==2}">需求</c:if></span>信息</dt>
     <dd>
       <table class="cart_show_table" width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr><td><strong>收货人信息</strong></td></tr>
       <tr>         
         <td class="tdbootom">
         	<c:if test="${USERADDRESSLIST==null|| fn:length(USERADDRESSLIST) == 0 }">无</c:if>
         	<c:forEach items="${USERADDRESSLIST}" var="USERADDRESS">
	       		<a class="address_div">
	       			<input  autocomplete="off" type="radio" z_id="${USERADDRESS.id}" is_default="${USERADDRESS.is_default}" xm="${USERADDRESS.consignee_name }" dh="${USERADDRESS.phone }" ssx="${USERADDRESS.ssx }" xxdz="${USERADDRESS.address }" dz="${USERADDRESS.ssx } ${USERADDRESS.address }" yb="${USERADDRESS.post_code }" <c:if test="${USERADDRESS.is_default==1 }">checked</c:if> name="address_select" value="1"/>
	       			&nbsp;<i  class="iconfont icon-dingwei"></i>&nbsp;${USERADDRESS.ssx }&nbsp;${USERADDRESS.address }&nbsp;(${USERADDRESS.consignee_name }&nbsp;收)&nbsp;<span class="key_point"><i class="iconfont icon-dianhua"></i>&nbsp;${USERADDRESS.phone }&nbsp;<i class="iconfont icon-email"></i>&nbsp;${USERADDRESS.post_code }</span>&nbsp;&nbsp;&nbsp;<span class="edit_address cart_blue">修改</span>
	       		</a>
	       		</c:forEach>
         	<a id="add_address" href="javascript:void(0)" class="cart_blue">添加地址</a>
         </td>
       </tr>
              
       <tr><td><strong>发票信息</strong></td></tr>
       <tr>         
         <td class="tdbootom">
         	<c:if test="${USERINVOICES==null }">无 <a href="/user/member_invoice.html" target="_blank" class="cart_blue">修改</a><br/></c:if>
         	<c:if test="${USERINVOICES!=null }">
			         类型-${USERINVOICES.type } <a href="/user/member_invoice.html" target="_blank" class="cart_blue">修改</a><br/>
			         单位名称-${USERINVOICES.dwmc } 
			     <c:if test="${USERINVOICES.type=='增值发票' }">
				     <br/>纳税人识别号-${USERINVOICES.nsrsbh } <br/>
				         注册地址-${USERINVOICES.zcdz }  <br/>
				         注册电话-${USERINVOICES.zcdh }  <br/>
				         开户行-${USERINVOICES.khh }  <br/>
				         银行对公账号-${USERINVOICES.yhdgzh } </td>
			     </c:if>
         	</c:if>
       </tr>
       <tr><td><strong>送货清单</strong></td></tr>
       <tr>
       <td class="tdbootom"><input type="radio" name="shipping_methods" value="1" checked="checked"/>快递或物流&nbsp;&nbsp;
       <input type="radio" name="shipping_methods" value="2"/>自提&nbsp;&nbsp;</td>
       </tr>
        <tr><td><strong>安装服务</strong></td></tr>
       <tr>
       <td><input type="radio" value="1" name="install_service" />需要&nbsp;&nbsp;
       <input type="radio" value="0" name="install_service" checked="checked"/>不需要&nbsp;&nbsp;</td>
       </tr>
       </table>
     </dd>
     <dd>
      <table class="cart_table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
            <th width="850px" class="tableleft" style="text-align:left;padding-left:20px">商品</th>
            <th width="150px">数量</th>
            <th width="150px">单价(元)</th>
            <th width="150px"  class="tableright">小计(元)</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${od_list }" var="od">
	          <tr class="product" product_id="${od.product_id}">
	            <td style="text-align:left;padding-left:10px">
	            <a href="/buy/buy_show.json?ID=${od.product_id}" target= _blank>
	            	<div style="float:left;">
	            	<img class="img" src="http://img.tulingbuy.com${od.product_imgurl}" width="80" height="80"style="margin-right:10px">
	            	</div>
	            	<div style="float:left;height:80px;">
	            	 <c:if test="${od.special_code == 1}">
	            	<div style="margin:20px 0 0 5px;border-radius:8px;width:50px;height:20px;background-color:rgb(194,47,39);line-height:20px;color:#fff;padding:0px 5px;">内购商品</div>
	            	<span style="line-height:20px;">${od.product_name}${od.pz}</span>
	            	</c:if>	           	
	            	 <c:if test="${od.special_code == 0}">
	            	<span>${od.product_name}${od.pz}</span>
	            	</c:if>	
	            	</a></td>
	            <td>×${od.product_count }</td>
	            <td>￥<fmt:formatNumber type="number" value="${od.product_unit_price }" pattern="0.00" maxFractionDigits="2"/></span></td>
	            <td><span style="font-size:14px;">￥<fmt:formatNumber type="number" value="${od.product_unit_price*od.product_count }" pattern="0.00" maxFractionDigits="2"/></span></td>
	            </tr>
	        </c:forEach>
         </tbody>
         <tfoot>
         	<tr>
         	   <td></td>
         	   <td></td>
	        	<td style="text-align:right;">
	        		<p><c:if test="${resource==2}">商品</c:if>总额：</p>
	        		<p><c:if test="${resource==2}">件数小计：</c:if></p>
	        		<p style="border-top:1px solid #cdcdcd;line-height:30px;width:150px"><c:if test="${resource==2}">商品</c:if>实付：</p>
	        	</td>
	        	<td style="text-align:right;" >
	        		<p>￥<fmt:formatNumber type="number" value="${total_payment_money }" pattern="0.00" maxFractionDigits="2"/></p>
	        		<p><c:if test="${resource==2}">×${total_count }件</c:if></p>
	        		<p style="border-top:1px solid #cdcdcd;line-height:30px;width:150px"><span style="line-height:30px;color:red">￥</span><span style="font-size:18px;font-weight:bold;line-height:30px;color:red"><fmt:formatNumber type="number" value="${total_payment_money }" pattern="0.00" maxFractionDigits="2"/></span></p>
	        	</td>
	        </tr>
	        <c:if test="${resource==2}">
	        <c:if test="${(demand_file3==null&&demand_file3=='') }">
	        <tr>
	        	<td colspan="5" style="text-align:left;padding-left:10px;">其他需求${demand_file3 }</td>
	        </tr>
	        </c:if>
	        <c:if test="${(demand_file==null&&demand_file=='')&&(demand_file2==null&&demand_file2=='')&&(demand_file3==null&&demand_file3=='') }">
			<tr>
	        	
	        	<td colspan="5" style="text-align:left;padding-left:10px;">
	        		<input id="demand_file" name="demand_file" type="hidden" value="${demand_file }"/>
	        		<input id="demand_file2" name="demand_file2" type="hidden" value="${demand_file2 }"/>
	        		<input id="demand_file3" name="demand_file3" type="hidden" value="${demand_file3 }"/>
	        		需求附件：
	        		<c:if test="${(demand_file==null||demand_file=='')&&(demand_file2==null||demand_file2=='')&&(demand_file3==null||demand_file3=='') }">
	        			无
	        		</c:if>
	        		<c:if test="${demand_file!=null&&demand_file!='' }">
	        			<br/>${demand_file } <a href="${demand_file }" target="_blank">查看附件</a>
	        		</c:if>
	        		<c:if test="${demand_file2!=null&&demand_file2!='' }">
	        			<br/>${demand_file2 } <a href="${demand_file2 }" target="_blank">查看附件2</a>
	        		</c:if>
	        		<c:if test="${demand_file3!=null&&demand_file3!='' }">
	        			<br/>${demand_file3 } <a href="${demand_file3 }" target="_blank">查看附件3</a>
	        		</c:if>
	        	</td>
	        </tr>
	        </c:if>
	        <c:if test="${(content==null&&content=='') }">
	        <tr>
	        	<td colspan="3" style="text-align:left;padding-left:10px;">
	        		<input id="content" name="content" type="hidden" value="${content }"/>
	        		描述：${content }
	        	</td>
	        </tr>
	        </c:if> 
	       </c:if>
         </tfoot>
       </table>
       <div class="cart_bottom">
       	 <input type="hidden" id="resource" value="${resource }"/>
    		<input type="hidden" id="product_counts" value="${product_counts }"/>
    		<input type="hidden" id="owning_company" value="${loginUser.owning_company }"/>
    		<input type="hidden" id="product_ids" value="${product_ids }"/>
    		<input type="hidden" id="product_item_ids" value="${product_item_ids }"/>
    		<input type="hidden" id="demand_file" value="${demand_file }"/>
    		<input class="cart_pay" type="button"   id="button_pay" value="立即支付"/>
         	<c:if test="${resource==2|| resource==4 }"><input class="cart_btn" type="button" name="button2" id="button2" value="提交需求"></c:if>
         	</div>

       </div>
     </dd>
  </dl>
 </div>

 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>

<script>
$(document).ready(function(){
	$(".address_div").click(function(){
		if($(this).find("input[type=radio]").prop('checked')==false){
			$(this).find("input[type=radio]").prop('checked',check);
			$(this).siblings().find("i").hide();
			$(this).find("i").show();
			$(this).addClass("active").siblings().removeClass("active");
		}
	});
})

	
</script>
</body>
</html>
