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
<title>${PRODUCT.name }</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/img.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.tabs.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/buy_show.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
<style type="text/css">
	.text_top{vertical-align:top; 
	    overflow:hidden;  
	    width:80px;
	    max-width:150px;
	    white-space:nowrap;  
	    text-overflow:ellipsis;  
	    -o-text-overflow:ellipsis;  
	    -icab-text-overflow: ellipsis;  
	    -khtml-text-overflow: ellipsis;  
	    -moz-text-overflow: ellipsis;  
	    -webkit-text-overflow: ellipsis; 
	 }
	 .allparams{border:1px solid #acacac;width:100%;}
	 .allparams tr th{border-bottom:1px solid #acacac;background-color:#efefef;vertical-align:top;width:30%; }
	  .allparams tr td{border-bottom:1px solid #acacac;width:60%;}
	  .zoom-desc img{
	  	margin-top:5px;
	  }
</style>
</head>

<body>
<jsp:include page="ntop.jsp"></jsp:include>
<div class="zi_main" style="margin-top:0px;">
<!--left-->
 <div class="main_right1" style="width:1300px;border:0px">
  <input type="hidden" id="brandid" value="${PRODUCT.brand_id}" }/>
   <dl class="product_left"> 
    <dt class="zoom-small-image">  	  
      <a href='http://img.tulingbuy.com${PRODUCTIMAGES==null||PRODUCTIMAGES.size()==0? PRODUCT.img_url:PRODUCTIMAGES.get(0).img_url }' target="_blank" class = 'cloud-zoom' id='zoom1' rel="adjustX:10, adjustY:-4">
        <img src="http://img.tulingbuy.com${PRODUCTIMAGES==null||PRODUCTIMAGES.size()==0? PRODUCT.img_url:PRODUCTIMAGES.get(0).img_url }" onload="DrawImage(this,400,400)" />
      </a>
    </dt> 
    <dd class="zoom-desc">    	
      <%-- <a href='http://img.tulingbuy.com${PRODUCT.img_url }' class='cloud-zoom-gallery' rel="useZoom: 'zoom1', smallImage: 'http://img.tulingbuy.com${PRODUCT.img_url }'"><img class="zoom-tiny-image" src="http://img.tulingbuy.com${PRODUCT.img_url }" /></a> --%>
      <c:forEach items="${PRODUCTIMAGES }" var="PRODUCTIMAGE">
       		<a href='http://img.tulingbuy.com${PRODUCTIMAGE.img_url }' class='cloud-zoom-gallery' rel="useZoom: 'zoom1', smallImage: 'http://img.tulingbuy.com${PRODUCTIMAGE.img_url }'"><img class="zoom-tiny-image" src="http://img.tulingbuy.com${PRODUCTIMAGE.img_url }" /></a>
        </c:forEach>
   	</dd>
  </dl>
  <div class="product_right" style="float:left">
    <h2><span id="p_title">${PRODUCT.name }</span><br/>
      <div class="product_right_1">${PRODUCT.sub_title }</div><br/>
      <div class="proudct_right_price">      	
      	<ul>
      	<li>
      		<c:if test="${PRODUCT.is_ms==0 }">图灵买</c:if><c:if test="${PRODUCT.is_ms==1 }">秒杀</c:if>价：
      	</li>
      	<li class="proudct_right_price_item" >
      		<strong id="sale_price" style="text-decoration:line-through;color:#acacac;">￥<fmt:formatNumber type="number" value="${PRODUCT.price_new }" pattern="0.00" maxFractionDigits="2"/></strong>
      	</li>      	
      	</ul>
      	<ul>
      	<li>
      		内购价格：
      	</li>
      	<li class="proudct_right_price_item" >
      		<strong id="sale_price" >￥<fmt:formatNumber type="number" value="${special_price}" pattern="0.00" maxFractionDigits="2"/></strong>
      	</li>      	
      	</ul>
      	
      	
      </div>
    </h2>
    <div class="right_lists">
    	<span>配&nbsp;&nbsp;送&nbsp;&nbsp;至：<strong>浙江省</strong>，支持货物自提。
    	</span>
    </div>
    <div class="right_lists product_right_line">
    	<span>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：该商品为图灵买自营商品，由图灵买发货并提供售后服务。</span>    	
    </div>
    
	    <div class="right_lists">    		
	    	<c:if test="${fn:length(PRODUCTITEMS)>0}">
	    	<dt>选择尺寸：</dt>
	    	<ul id="product_size">
	    	<c:forEach items="${PRODUCTITEMS }" var="ITEMINFO" varStatus="status">	    	
	    	    <c:if test="${status.index==0}">
	    			<li class="list_Selected" id="size_${ITEMINFO.product_size}" onclick=selectsize(this)>${ITEMINFO.product_sizename}</li>
	    		</c:if>
	    		<c:if test="${status.index>0&&PRODUCTITEMS[status.index-1].product_size!=ITEMINFO.product_size}">
	    			<li id="size_${ITEMINFO.product_size}" onclick="selectsize(this)">${ITEMINFO.product_sizename}</li>
	    		</c:if>
	    		<input value="${ITEMINFO.product_sizename}!^${ITEMINFO.product_colorname}!^${ITEMINFO.product_versionsname}/${ITEMINFO.product_memoriesname}!^${ITEMINFO.product_marketprice}!^${ITEMINFO.product_saleprice}!^${ITEMINFO.stocks}!^${ITEMINFO.product_items_id}" id="line_${status.index}" type="hidden">	    		
	    	</c:forEach>	    		
	    	</ul>
	      </c:if>
	    </div>
	
	    <div class="right_lists">
	    	<c:if test="${fn:length(PRODUCTITEMS)>0}">
	    	<dt>选择颜色：</dt>
	    	<ul id="product_color">	    		
	    		
	    	</ul>
	    	</c:if>
	    </div>
	    <script>
	    
	    </script>
	    <div class="right_lists">
	    	<c:if test="${fn:length(PRODUCTITEMS)>0}">
	    	<dt>选择配置：</dt>
	    	<ul id="product_peizhi">	    		
	    	</ul>
	    	</c:if>
	    </div>    
    
    
    <div class="show_buy" id="djs">
    <c:if test="${PRODUCT.is_ms==0 }">
      购买数量：<input class="btn_jie" id="btn_jian" type="button" value="-">
      <input name="textfield" type="text" class="buy_box" autocomplete="off" id="count" value="0">
      <input class="btn_jie" type="submit" name="button" id="btn_jia" value="+">
&nbsp;库存：<span id="countname" >${PRODUCT.count}</span><input type="hidden" id="count" value="${PRODUCT.count }"/>
    </c:if>
    </div>
    <div class="show_btn">
    	<input type="hidden" id="is_ms_" value="${PRODUCT.is_ms }"/>
    	<c:if test="${PRODUCT.is_ms==1 }">
				<input type="hidden" id="flag_begin_" value="${PRODUCT.flag_begin }"/>
				<input type="hidden" id="leftTime_" value="${PRODUCT.leftTime }"/>
	    		<c:if test="${PRODUCT.flag_begin}">
	    			<c:if test="${PRODUCT.count<=0}">
		    			<input class="red_btn" name="input" type="button" value="~抢光了~" />
	    			</c:if>
	    			<c:if test="${PRODUCT.count>0}">
	    				<input class="red_btn on" id="ljqg" product_id="${PRODUCT.id }"  isms="${PRODUCT.is_ms }" name="input" type="button" value="立即抢购" /><br/>库存：${PRODUCT.count }
	    			</c:if>
	    		</c:if>
	    		<c:if test="${!PRODUCT.flag_begin}">
	    			<input class="red_btn off" id="ljqg" product_id="${PRODUCT.id }"  isms="${PRODUCT.is_ms }" name="input" type="button" value="秒杀未开始" />
	    		</c:if>
	    	</c:if>
	    	<c:if test="${PRODUCT.is_ms!=1 }">
	    		<c:if test="${PRODUCT.count<=0}">
	    			<input class="red_btn" name="input" type="button" value="加入采购车" />
    			</c:if>
    			<c:if test="${PRODUCT.count>0}">
    				<input class="red_btn" id="addcart" product_id="${PRODUCT.id }" product_items_id="0" name="input" type="button" value="加入采购车" />
    			</c:if>
	    	</c:if>
    </div>
  </div>
  
  <div class="product_right" style="float:right;width:190px">
  	  <p><img id="brandidimg"/></p>
  	  <P style="line-height:30px"><span class="gaoliang">自营商品，100%品质保证</span></P>
  	  <p style="line-height:30px"><span class="bt_buyshow">
  	  								<span class="iconfont icon-shouji" style="color:#4489FD;display:inline-block;font-size:22px"></span>400-826-4755</span></p>
  	  <p style="line-height:30px">
  	  		<a href="http://kefu.qycn.com/vclient/chat/?websiteid=112607" target=_blank  title="图灵买客服">
  	  		<span class="bt_buyshow"><span class="iconfont icon-kefu" style="display:inline-block;font-size:20px;color:#D74340;"></span><span style="padding:0px 17px">在线客服</span></span></a></p>
  </div>
  
  <div style="clear:both; height:50px;"></div>
  <!--描述-->
  <div class="p_cont demo1">
      <ul class="tab_menu">
        <li class="current"><a href="javascript:">详细信息</a></li><li><a href="javascript:">规格参数</a></li>
      </ul>
      <div class="tab_box">
         <div class="p_zi" style="padding:10px 20%;">
           ${PRODUCT.repcontent }
         </div>
         <div class="p_zi hide">
         	<c:if test="${fn:length(PRODUCTPARAMLIST)>0}">
	         	<table border="0" cellspacing="0" cellpadding="10" class="allparams">
	           	<c:forEach items="${PRODUCTPARAMLIST }" var="PRODUCTPARAM">
		     		<tr>
			           <th><strong>${PRODUCTPARAM.par_name }：</strong></th>
			           <td>${PRODUCTPARAM.par_value }</td>
			        </tr>
	           </c:forEach>
	           </table>
           </c:if> 
           <c:if test="${fn:length(PRODUCTPARAMLIST)<=0}">
           		没有规格参数
           </c:if>
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
