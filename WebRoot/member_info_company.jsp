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
<title>公司信息---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
<link href="<%=basePath %>fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>js/member.js?date=<%=new Date()%>"></script>
	<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
	<script src="<%=basePath%>js/uploader/webuploader_companyimg.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?3"></script>
	<script type="text/javascript" src="<%=basePath %>js/stringmap.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<script type="text/javascript">
		var my_user_id="${loginUser.user_id}";
		$(document).ready(function() {
 			createimgdiv();
	    	//初始化上传控件
	        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_company.json", swf: "<%=basePath%>js/uploader/uploader.swf" });
				//未绑定公司时的提示
	
	
	
	
		if((eval("${loginUser.owning_company==null}") || eval("${loginUser.owning_company==0}" ))&& eval("${loginUser.is_apply!=1}")){
			$(".member_zi").hide();
			layer.confirm('您尚未绑定公司', {
			     btn: ['绑定','创建'],time:0,shade:0.1,closeBtn: 0
			  },function(index){
					//iframe层
					layer.open({
					  type: 2,
					  title: '加入公司',
					  shadeClose: false,
					  shade: 0.1,
					  area: ['500px', '450px'],
					  content: '<%=basePath%>join_company.jsp',
					  cancel: function(index){ window.location.reload(); return true; } 
					}); 
					layer.close(index);
			     },function(index){
						$(".member_zi").show();		
			}); 
		}else{
		if(eval("${loginUser.is_apply==1}")&&(eval("${loginUser.owning_company==0}")||eval("${loginUser.owning_company==null}"))){
			$(".member_zi").html('<div class="card"><i class="iconfont icon-1png"></i><p>正在申请中，请耐心等待..</p></div>');
		
		}else{
			$("span[class=\"red_zi\"]").hide();
			$(".member_zi").find("input[type=\"text\"]").attr({"readonly":true});
			$(".member_zi").find("textarea:first").attr({"readonly":true});
			$(".member_zi").find("textarea:first").css({"background-color":"#ffffff"});
			$(".member_zi").find("select").attr({"disabled":true});
			$("#save_company").hide();
			$("#declare").html("<th>*特别声明：</th><td >公司信息无法修改，如需修改请联系客服！</td>");
  			$("#uploder").hide();
		}
		
		}
		});
		
		var staff_list = getStringmap("user_company", "staffs_number") ;
		function createimgdiv(){
			$("a[rel=example_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image：' + (currentIndex + 1) + ' / ' + currentArray.length + '</span><strong>'+(title.length ? ' &nbsp; ' + title : '') + '</strong>';
				}
			});
		}
		
		

</script>
<style type="text/css">
	#imgboxs .imgbox{width:100px;height:100px;margin-right:10px;margin-bottom:10px;float:left;border:2px solid #edece7;text-align:center;background-color:#fff;position: relative}
	#imgboxs .imgbox .delete{cursor:pointer;position:absolute;bottom:0px;left:0px;width:100%;height:25px;line-height:25px;border:1px solid #000;color:#fff;background-color:#323231;
	filter:alpha(opacity=40); 
	-moz-opacity:0.40; 
	opacity:0.40; }
	 .card {
		width:200px;
		height:200px;
		text-align:center;
		display:inline-block;
		position:absolute;
		z-index：3;
		left:50%;
		top:30%;
		
	}
	.card >i {
		margin:100px 0 70px 0;
		display:inline-block;
		font-size:80pt;
		color:#ea6561;	
	}

	.card>p{
		font-size:13pt;
		color:#989898;
		
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
   <%--<div class="warning_zi"><i class="fa fa-warning"></i>系统通知：新版切换必看 3大问题答疑！ <a href="member_news.asp">点此查看</a></div> --%>
   <div class="member_content">
     <h3>
					<a href="/user/member_info.html?page=member_info">账号资料</a> 
					<a href="/user/member_info_head.html?page=member_info">头像照片</a> 
					<a class="select" href="/user/member_info_company.html?page=member_info">公司信息</a>
					<c:if test="${loginUser.owning_company!=0 && loginUser.owning_company!=null}">
   					<a href="/user/member_info_contact.html?page=member_info_contact">联系人${loginUser.is_manager==1?"管理":""}</a>
      				 </c:if>
     </h3>
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%"><span class="red_zi">* </span>公司名称：</th>
                 <td width="77%"><input class="member_box" type="text" name="gsmc" id="gsmc" value="${USERCOMPANY.gsmc }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>公司注册资金：</th>
                 <td><input class="member_box" type="text" name="zczj" id="zczj" value="${USERCOMPANY.zczj }"> 
                   万元人民币</td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>营业执照注册号：</th>
                 <td><input class="member_box" type="text" name="yyzzzch" id="yyzzzch" value="${USERCOMPANY.yyzzzch }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>法定代表人：</th>
                 <td><input class="member_box" type="text" name="fddbr" id="fddbr" value="${USERCOMPANY.fddbr }"></td>
               </tr>
                       
               <tr>
                 <th><span class="red_zi">* </span>员工人数：</th>
                 <td><select class="member_box" type="text" name="staffs_number" id="staffs_number" group="user_company"  ></select></td>
              <script>
              		loadSelect();
               		$("#staffs_number").val("${USERCOMPANY.staffs_number }");
              </script>
               </tr>
               <tr >
               		<th>企业资质：</th>
               		<td id="uploder"><div class="upload-box upload-img" ></div></td>
               </tr>
               <tr>
               <td></td>
               <td ><ul   id="thelist" class="uploader-list"  width="800px" style="margin:0 auto;" >
              
               
               
               </ul></td>
               
               
               </tr>
               
               <tr>
                 <th></th>
                 <td>
                   <div width="100%" id="imgboxs">
                   		<c:forEach items="${COMPANYIMGLIST }" var="COMPANYIMG">
                   			<div class="imgbox" imgid="${COMPANYIMG.id }">
	                           <a href="${COMPANYIMG.imgurl }" rel="example_group" target="_blank">
	                             <img src="${COMPANYIMG.imgurl }" width="100" height="100"/>
	                           </a>
	                        </div>
                   		</c:forEach>
                   </div>
                </td>
               </tr>
               
       
               
               <tr>
                 <th><span class="red_zi">* </span>办公地址：</th>
                 <td><input style="width:480px;margin:0;padding:0 10px;" class="member90_box" type="text" name="bgdz" id="bgdz" value="${USERCOMPANY.bgdz }"></td>
               </tr>
               
                              <tr>
                 <th><span class="red_zi">* </span>公司介绍：</th>
                 <td>
                   <textarea style="width:480px;height:200px;padding:10px;" name="gsjs" id="gsjs">
                   
                   </textarea>
                   <script>
	                 	$("#gsjs").val("${USERCOMPANY.gsjs}"); 
	                 </script>
                 </td>
               </tr>
               <tr id="declare" style="color:red;">
                 <th>*特别声明：</th>
            	 <td >公司信息添加后无法修改，请认真填写！</td>
               </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td><input class="red_btn" name="" type="button" id="save_company" value="确认提交" user_id="${loginUser.user_id }"></td>
                 <td><input class="red_btn" name="" type="hidden" id="upload" value="确认提交"></td>
               </tr>
          </table>
       </li>
     </ul>
   </div>
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
