<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="Keywords"
	content="图灵买,IT服务平台,采购,电商,互联网,苹果,联想,华为,微软,apple,ipa,iphone,lenovo,dell,huawei,系统,软件,服务器,笔记本,CRM,杭州,西湖,浙大,网新,图灵,IT买手">
<meta name="Description"
	content="图灵买是浙大网新图灵旗下一个基于互联网+的IT服务平台，让客户通过互联网能快速方便的得到图灵买的管家式服务；平台提供免费IT的咨询及规划、IT产品采购方案及报价，真正做到一站式阳光采购、送货上门、上门安装、一键呼叫服务，并为客户免费提供在线云应用服务；为杭州及周边地区的客户提供专业的IT服务。">
<meta name="baidu-site-verification" content="4cxqzes1K1" />
<title>图灵买(电脑、软件哪里买，图灵买！)</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css" />
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css" />
<link rel="stylesheet" href="<%=basePath %>css/3Dstyle.css" />
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />
<link rel="stylesheet" href="<%=basePath %>css/homepage.css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<style>
	.he_slideAllDown{
		 background:url(<%=basePath%>img/slideDown.png)\9;
	}
	.he_slideAllDown>a{
		display:block;
		width:471px;
		height:270px;
	}
</style>
</head>

<body>
	<!-- top -->
	<jsp:include page="top.jsp"></jsp:include>
	<!--banner-->
	<div class="register_bj"
		style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
		<dl class="login_bj"
			style="width:100%;height:auto;min-height:400px;margin:0;padding:0;">
			<div class="home_banner">
				<span><iframe src="/ad/banner.json" width="1920" height="495"
						frameborder="0"></iframe></span>
			</div>
		</dl>
	</div>

	<!-- <div class="home_main">
		<div class="container">
			<ul>
				<li><a
					href="http://kefu.qycn.com/vclient/chat/?websiteid=112607"
					target="_blank"> <i class="iconfont icon-kefu"></i>
						<p class="left">
							我要问<br> <span>询问产品、服务、软件、价格</span>
						</p>
				</a></li>
				<li><a href="/buy.json"> <i class="iconfont icon-caigou"></i>
						<p>
							我要采购<br> <span>采购产品、软件、服务</span>
						</p>
				</a></li>
				<li><a href="/service.html"> <i class="iconfont icon-fuwu"></i>
						<p>
							贴心服务<br> <span>售后服务、咨询服务、产品服务</span>
						</p>
				</a></li>
				<li class="last_li"><a href="/article/3.json"> <i
						class="iconfont icon-0048jihedingdan"></i>
						<p>
							解决方案<br> <span>集成、虚拟化、数据备份、办公软件</span>
						</p>
				</a></li>
			</ul>
		</div>
	</div> -->

	<div class="home_content">
		<%-- <div class="container_sub">
			<!--20160810-->
			<div class="main_body">
				<div class="content_header">
					<span>企业服务</span>
				</div>
				<div class="qyfw"
					style="background:url(<%=basePath%>img/company.jpeg);height:320px;">
					<img src="img/qyfw.png" class="qyfw_title"> 
					<div class="box qyfw_body">
            <div class="he_slideAllDown">
               <a  href="<%=basePath%>xiaoshou.jsp" target="_blank"> <img class="he_slideAllDown_img" alt="Image 01" src="<%=basePath %>img/qyfwred.png"></a>
                <!--[if !IE]><!-->
                <div class="he_slideAllDown_caption">
                    <h3 class="he_slideAllDown_caption_h">企业服务</h3>
                    <p class="he_slideAllDown_caption_p">一站式解决方案，尽在图灵买！</p>
                   	<span class="he_slideAllDown_caption_span">     立即前往   >></span>
                    <a class="he_slideAllDown_caption_a" href="<%=basePath%>xiaoshou.jsp" target="_blank"></a>
                </div>
            	<!--<![endif]-->
            </div>
        </div>
				</div>
			</div>
			<div class="main_body">
				<div class="content_header">
					<span>热卖推荐</span>
				</div>
				<div class="content_body">
					<div class="left hot_1">
						<a href="#" class="hot1big cotoly_0"> <img src="img/ThinkPad.jpg">
							<p>ThinkPad T450-20BVA03MCD</p>
						</a> <a href="#" class="hot1small cotoly_1"> <img src="img/ytT4900c.png">
							<span>扬天T4900c G3260/4GB/500GB/集显/20寸液晶</span>
						</a>
					</div>
					<div class="left hot_2">
						<a href="#" class="cotoly_2"> <img src="img/ipadpro.png">
							<p>Apple iPad Pro 9.7 英寸 32G WLAN版</p>
						</a> <a href="#" class="cotoly_3"> <img src="img/youjianbao.png">
							<p>邮件服务宝——50G超大邮箱</p>
						</a>
					</div>
					<div class="left hot_3">
						<a href="#" class="cotoly_4"> <img src="img/SurfacePro.png">
							<p>Surface Pro 4 12.3英寸(Intel i5 4G 128G)</p>
						</a>
					</div>
					<div class="left hot_4">
						<a href="#" class="cotoly_5"> <img src="img/imac.png"> <span>Apple
								iMac MK142CH/A</span>
						</a> <a href="#" class="cotoly_6"> <img src="img/iphoneSE.png"> <span>Apple
								iPhone SE 16G 移动联通电信4G手机(金色)</span>
						</a> <a href="#" class="cotoly_7"> <img src="img/ThinkPadt550.png"> <span>ThinkPad
								T550-20CKA00ECD</span>
						</a>
					</div>
				</div>
			</div>

			<!--20160810 end-->
			<div class="main_body">
				<div class="content_header">
					<span>案例介绍</span>
				</div>
				<div class="content_body case">
					<div class="left case_1">
						<div class="case_up">
							<a class="left">
								<img src="img/hk.jpg">
								<span>航空</span>
							</a>
							<a class="left">
								<img src="img/dw.png">
								<span>电网</span>
							</a>
						</div>
						<div class="case_down">
							<a class="left">
								<img src="img/ylqx.png">
								<span>医疗保健</span>
							</a>
							<a class="left">
								<img src="img/mzmj.png">
								<span>美妆</span>
							</a>
							<a class="left">
								<img src="img/jlqc.png">
								<span>汽车</span>
							</a>
						</div>
					</div>
					<div class="left case_2">
						<div class="case_up">
							<a class="left">
								<img src="img/yh.png">
								<span>银行</span>
							</a>
							<a class="left">
								<img src="img/smdq.png">
								<span>数码电器</span>
							</a>
						</div>
						<div class="case_down">
							<a class="left">
								<img src="img/myyp.png">
								<span>母婴玩具</span>
							</a>
							<a class="left">
								<img src="img/fzfs.png">
								<span>服装服饰</span>
							</a>
							<a class="left">
								<img src="img/morecase.png">
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="main_body liucheng">
				<div class="content_header">
					<span>网站指南</span>
				</div>
				<div class="content_body" style="text-align:center">
					<img src="img/liucheng.jpg" style="height:400px">
				</div>
			</div>
			<div class="main_body">
				<div class="content_header">
					<span>合作伙伴</span>
				</div>
				<div class="content_body" style="padding-left:70px">
				<div class="left">
				<img src="http://img.tulingbuy.com/upload/image/20160322162936252.jpg" alt="">
				</div>
				<div class="left left_part1">
					<p>
						<img src="upload/product_img/type7.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type8.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type10.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type11.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type12.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type13.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type140.jpg">						
					</p>
					<p>
						<img src="upload/product_img/type150.jpg">						
					</p>
				</div>
			</div>
		</div>
	</div> --%>
	<div class="clear"></div>
	<!--20160810-->
	
	<jsp:include page="foot.jsp"></jsp:include>
	<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
	url:"<%=basePath%>buy/redlist.json",
	type:"get",
	data:{count:"8"},
	dataType:"json",
	success:function(data){
	console.log(JSON.stringify(data));
	 setHotArea(data); 
	}
	
	})
	
	//企业服务
	$(".qyfw_body").mouseenter(function(){
		$(".he_slideAllDown img").animate({top:'270px'});
	}).mouseleave(function(){
		$(".he_slideAllDown img").animate({top:'0px'});
	});

});

//填充热卖推荐数据
function setHotArea(data){
	var  count =0;
	var imgPath = 'http://img.tulingbuy.com';
    $.each(data,function(i,value){
    var bq=((i==5||i==6||i==7)?"span":"p");
    var href='<%=basePath%>buy/buy_show.json?ID='+value.id+'&ITEM='+value.product_items_id;
    var tempHtml ='<img src="'+imgPath+value.img_url+'"><'+bq+'>'+value.name+'</'+bq+'>';
    $(".cotoly_"+i).eq(0).prop("href",href);
    $(".cotoly_"+i).eq(0).html(tempHtml);
    });
}

</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?f7000683e1a3d9f334072a297bb880ee";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script>

</script>


</body>
</html>
