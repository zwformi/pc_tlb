<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
<script type="text/javascript" src="<%=basePath %>js/stringmap.js?3"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
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
	<div class="zi_main" style="width:800px;">

		<div class="member_center" style="width:800px;">
			<div class="member_content">
				<ul class="member_zi">
					<li>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="23%"><span class="red_zi">* </span>公司名称：</th>
								<td width="77%"><input class="member_box" type="text"
									name="gsmc" id="gsmc" value=""></td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>公司注册资金：</th>
								<td><input class="member_box" type="text" name="zczj"
									id="zczj" value=""> 万元人民币</td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>营业执照注册号：</th>
								<td><input class="member_box" type="text" name="yyzzzch"
									id="yyzzzch" value=""></td>
							</tr>
							<tr>
								<th><span class="red_zi">* </span>法定代表人：</th>
								<td><input class="member_box" type="text" name="fddbr"
									id="fddbr" value=""></td>
							</tr>

							<tr>
								<th><span class="red_zi">* </span>员工人数：</th>
								<td><select class="member_box" type="text"
									name="staffs_number" id="staffs_number" group="user_company"></select></td>
								<script>
              		loadSelect();
               		/* $("#staffs_number").val("${USERCOMPANY.staffs_number }"); */
              </script>
							</tr>
							<tr>
								<th>企业资质：</th>
								<td id="uploder"><div class="upload-box upload-img"></div></td>
							</tr>
							<tr>
								<td></td>
								<td><ul id="thelist" class="uploader-list" width="800px"
										style="margin:0 auto;">



									</ul></td>


							</tr>

							<tr>
								<th></th>
								<td>
									<div width="100%" id="imgboxs">
										<%-- <c:forEach items="${COMPANYIMGLIST }" var="COMPANYIMG">
											<div class="imgbox" imgid="${COMPANYIMG.id }">
												<a href="${COMPANYIMG.imgurl }" rel="example_group"
													target="_blank"> <img src="${COMPANYIMG.imgurl }"
													width="100" height="100" />
												</a>
											</div>
										</c:forEach> --%>
									</div>
								</td>
							</tr>



							<tr>
								<th><span class="red_zi">* </span>办公地址：</th>
								<td><input style="width:480px;margin:0;padding:0 10px;"
									class="member90_box" type="text" name="bgdz" id="bgdz"
									value=""></td>
							</tr>

							<tr>
								<th><span class="red_zi">* </span>公司介绍：</th>
								<td><textarea
										style="width:480px;height:200px;padding:10px;" name="gsjs"
										id="gsjs" value=""></textarea> 
                   </td>
							</tr>
							<tr id="declare" style="color:red;">
								<th>*特别声明：</th>
								<td>公司信息添加后无法修改，请认真填写！</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><input class="red_btn" name="" type="button"
									id="save_company01" value="确认提交" user_id=""></td>
								<td><input class="red_btn" name="" type="hidden"
									id="upload" value="确认提交"></td>
								<td><input type="hidden"
									id="owning_company_id" value=""></td>
							</tr>
						</table>
					</li>
				</ul>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<script type="text/javascript">
		var my_user_id="${loginUser.user_id}";
		$(document).ready(function() {
 			createimgdiv();
	    	//初始化上传控件
	        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_company.json", swf: "<%=basePath%>js/uploader/uploader.swf" });
				//未绑定公司时的提示
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
		
			//保存用户公司信息
	$("#save_company01").click(function(){
    	if($("#gsmc").val()==""){
    		layer.msg("请输入公司名称！", {shift: 6});
    	}else if($("#zczj").val()==""){
    		layer.msg("请输入注册资金！", {shift: 6});
    	}else if(!/^\+?[1-9][0-9]*$/.test($("#zczj").val())){
    		layer.msg("注册资金只能为正整数", {shift: 6});
    	}else if($("#yyzzzch").val()==""){
    		layer.msg("请输入营业执照注册号！", {shift: 6});
    	}else if($("#fddbr").val()==""){
    		layer.msg("请输入法定代表人！", {shift: 6});
    	}else if($("#staffs_number").val()==""){
    		layer.msg("请选择员工人数！", {shift: 6});
    	}else if($("#bgdz").val()==""){
    		layer.msg("请输入办公地址！", {shift: 6});
    	}else if($("#gsjs").val()==""){
    		layer.msg("请输入公司介绍！", {shift: 6});
    	}else{
    		var gsjs = $('#gsjs').val();
			var company ={"gsmc":$("#gsmc").val().replace(/\ +/g,""),"zczj":$("#zczj").val(),"yyzzzch":$("#yyzzzch").val(),"fddbr":$("#fddbr").val(),"gsjs":gsjs,"staffs_number":$("#staffs_number").val(),"bgdz":$("#bgdz").val()};
			$.post('/user/savecompany.json',company,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.id>0){
						$("#owning_company_id").val(data.id)
					    $("#upload").click(); 
						/*********同步公司信息***********/
							var _company = company;
							var postdata = {};
							var content={};
					        /*_company.user_id=963; */
							_company.id=data.id;
							/******去掉多余属性*************/
							delete _company["yyzzzch"];
							/******去掉多余属性*************/
						content.table="tbl_user_company";
						content.data=_company;
						content.name="TLM_ACCOUNT_CHANGED";
						postdata.name="TLM_ACCOUNT_CHANGED";
						postdata.content=JSON.stringify(content);
						$.post('/async.json',postdata,function(){});
						/*********同步公司信息***********/
						//同步用户
						parent.layer.msg("公司信息保存成功！");
						/****************关闭后回调*****************/
						parent.owning_company=data.id;
						parent.company_name = $("#gsmc").val().replace(/\ +/g,"");
						parent.$('#search_company').val($("#gsmc").val().replace(/\ +/g,""));
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		    			parent.layer.close(index); //执行关闭
					}else{
						layer.msg("保存公司信息出现未知错误，请重试");
					}
				}

			});


		}
	});

</script>
</body>
</html>
