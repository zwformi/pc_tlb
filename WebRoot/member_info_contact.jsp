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
<title>联系人---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.md5.js"></script>
<script src="<%=basePath%>/js/layer/layer.js"></script>
<script src="<%=basePath%>/js/member.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/3c-menu.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js?date=<%=new Date()%>"></script>
<script type="text/javascript" src="<%=basePath %>js/register.js?date=<%=new Date()%>"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css"/>
<style type="text/css">
.member_zi{display: block;padding: 30px 20px;font-size: 13px;text-align: center;background-color:#fcfcfc;}
.left_area{display:inline-block;text-align:center!important;width:900px;height:500px;background-color:#fcfcfc;border:solid 3px #e0e0e0; overflow:hidden;margin:0;border-radius:5px;}
.left_area:hover{overflow-y:auto;}
.left_area::-webkit-scrollbar{background-color:#f3f3f3;border-radius:4px;width:10px;}
.left_area::-webkit-scrollbar-thumb{background-color:#dcdcdc;border-radius:4px;}
.left_area li{width:894px;height:50px;display: block;padding:5px;border-bottom: solid 3px #dcdcdc;cursor:pointer;}
.left_area li:hover{background-color:#CCE8FF;color:#000;}
.left_area li>div{display: inline-block;height:100%;vertical-align: middle;}
.left_area li>div>img{width:50px;height:50px;}
.left_area li>div>a{font-size:6pt;display:block;border-radius:10px;width:50px;height:20px;color:#fff;background-color:#EA6561;text-align:center;line-height: 20px;margin:15px 0;}
.left_area li>div>a:hover{border:solid #dcdcdc 1px;}
.div1{width:50px;float:left;margin-left:50px;}
.div2{width:100px;height:50px;padding-left:10px;text-align:left;}
.div2 span{line-height: 50px;}
.div2 i{padding:3px;}
.div3{width:80px;float:right;text-align: left;}
.user_tags{display:inline-block;width:850px;background-color:#fcfcfc;height:40px;text-align:left!important;color:#000;margin:0px!important;text-align:center;cursor: auto;}
.user_tags span{display:inline-block;line-height: 40px;cursor:pointer;}
.user_tags div{text-align:center!important;width:100px;display:inline-block;cursor: auto;background-color:#dcdcdc;color:#000; border-top-left-radius:10px;border-top-right-radius:10px;}
.user_tags div:hover{background-color:#0099cc;color:#fff;}
.active1{background-color:#0099cc!important;color:#fff!important;}
.right_area{display:inline-block;width:65%;height:500px;background-color:#fcfcfc;border:solid 1px #e0e0e0; overflow:hidden;margin:0;}
.top{width:100%;background-color:#dcdcdc;height:40px;text-align:center;color:#000;}
.top>p{line-height: 40px;}
.content{height:460px;text-align:center;}
.content>ul{width:300px;margin:auto;}
.content li{text-align:left;min-height:30px;padding-left:80px;}
.content i{font-size:15pt;padding:5px;line-height:30px;}
.content p{line-height:30px;}
.content .userimg{text-align:center;padding:50px 0;}
.content .userimg>img{width:150px;height:150px;}
.register_table th{font-size:10pt!important;}
.register_box{    width: 350px;height: 30px;border: 1px solid #ccc;padding-left: 5px; font-size: 12px;}
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
					<a  href="/user/member_info_company.html?page=member_info">公司信息</a>
					<c:if test="${loginUser.owning_company!=0 && loginUser.owning_company!=null}">
   					<a class="select" href="/user/member_info_contact.html?page=member_info_contact">联系人${loginUser.is_manager==1?"管理":""}</a>
      				 </c:if>
     </h3>
     <ul class="member_zi">
     <li>
     <div class="user_tags">
       	<div name="my_contact"><span>企业联系人</span></div>
     	 <c:if test="${loginUser.is_manager==1 }">
	     <div name="my_application"><span>申请联系人</span></div>
	     <div name="my_forbidden"><span>禁用联系人</span></div>
	     <div name="my_adduser"><span>添加联系人</span></div>
	     </c:if>
     </div>
     <ul class="left_area" name="my_contact">
     
     </ul>
     <ul class="left_area" name="my_application">
     
     </ul>
     <ul class="left_area" name="my_forbidden">
     
     </ul>
     <!-- 添加用户 begin -->
     <ul class="left_area" name="my_adduser"  style="padding-top:50px;">
     <table class="register_table" width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody>
          <tr>
            <th style="width:40%;"><span class="red_zi">* </span>用户姓名：</th>
            <td style="width:60%;"><input class="register_box" checkin="true" type="text" placeholder="注册人姓名" name="xm" id="xm" maxlength="5"></td>
          </tr>
          <tr>
            <th><span class="red_zi">* </span>密码：</th>
            <td><input class="register_box" checkin="true" type="password" placeholder="账号密码" name="password_str" id="password_str" maxlength="30"></td>
          </tr>
          <tr>
            <th><span class="red_zi">* </span>确认密码：</th>
            <td><input class="register_box" checkin="true" type="password" placeholder="确认密码" name="password_str2" id="password_str2" maxlength="30"></td>
          </tr>
          <tr>
            <th><span class="red_zi">* </span>手机号码：</th>
            <td><input class="register_box" checkin="true" type="text" placeholder="登录时使用手机号码" name="phone" id="phone" maxlength="11"></td>
          </tr>
          <tr>
            <th><span class="red_zi">* </span>企业邮箱：</th>
            <td><input class="register_box" checkin="true" type="text" placeholder="企业邮箱" name="e_mail" id="e_mail" maxlength="80"></td>
          </tr>
          <tr>
          	<th><span class="red_zi">* </span>从事行业：</th>
          	<td>
          		<select name="hysx" id="hysx" class="register_box">
				    
					    <option value="计算机">计算机</option>
				    
					    <option value="网络通信">网络通信</option>
				    
					    <option value="生成加工">生成加工</option>
				    
					    <option value="互联网/电子商务">互联网/电子商务</option>
				    
					    <option value="金融">金融</option>
				    
					    <option value="IT服务">IT服务</option>
				    
					    <option value="教育">教育</option>
				    
					    <option value="政府/公共事业">政府/公共事业</option>
				    
					    <option value="其它">其它</option>
				    
		    	</select>
          	</td>
          </tr>
          <tr>
          	<th><span class="red_zi">* </span>所属部门：</th>
          	<td>
          			<select id="owning_department" name="owning_department" group="user_info" class="register_box">
                   	<option value="1">采购部</option><option value="2">行政部</option><option value="3">财务部</option><option value="4">人事部</option><option value="5">信息技术部</option><option value="6">销售部</option><option value="7">其他部门</option><option value="8">公司管理层</option><option value="9">市场部</option></select>
          	</td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <td>
             <input class="register_btn" style="border-radius:10px;" id="btn_register" type="button" value="注册">
            </td>
          </tr>
        </tbody></table>
     </ul>
     <!-- 添加用户 end -->
   </div>
</div>
 <div style="clear:both;"></div>
</div>
<!--foot-->
<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
var userlist ={};
var applist = {};
var department_list={};
var _count = 0;

function changeTags(tagname){
	 $('div[class=\'user_tags\']').find('div[name]').click(function(){
	 	var _str = $(this).attr('name');
	 	$('div[class=\'user_tags\']').find('div[name]').removeClass('active1');
	 	$(this).addClass('active1');
	 	$('.left_area').hide();
	 	$('.left_area[name='+_str+']').show();
	 });
	  $('div[class=\'user_tags\']').find('div[name=\''+(!!tagname?tagname:"my_contact")+'\']').click();



}


$(function(){

		$(".left_area[name='my_application']").hide();
		$(".left_area[name='my_forbidden']").hide();
		$(".left_area[name='my_adduser']").hide();
department_list = getStringmap("user_info", "owning_department");
initdata();

})

function initdata(tagname){
	$.post("<%=basePath%>user/allusers.json",{"id":"${loginUser.owning_company}"},function(data){
		var _html=_temp=_cache="";
		userlist = data.userlist;
		applist = data.applist;
		if(eval("${loginUser.is_manager==1}")){
		for(i in applist){
			/* _html+='<li class="tag"><p>申请人</p></li>'; */
         	_html+='<li index="'+(i)+'" type="application" onclick="showThis(this);" onmouseover="showTips(this);">\
         	<div class="div1"><img src="http://www.tulingbuy.com/'+applist[i].headimgurl+'" onerror="this.src=\'/images/default_img.png\'"></img></div>\
         	 <div class="div2" style="width:100px;"><span><i class="iconfont icon-profile" style=""></i>'+applist[i].xm+(applist[i].is_manager==1?"<i style=\"color:#bc9547;padding:px;font-size:10pt;\" class=\"iconfont icon-vipdakehu\"></i>":"")+'</span></div>\
		         <div class="div2" style="width:150px;"><span><i class="iconfont icon-dianhua" style=""></i>'+applist[i].phone+'</span></div>\
		         <div class="div2" style="width:100px;"><span><i class="iconfont icon-gongsi" style=""></i>'+(department_list[applist[i].owning_department+""]==null?"部门未知":department_list[applist[i].owning_department+""])+'</span></div>\
		         <div class="div2" style="width:200px;"><span><i class="iconfont icon-email" style=""></i>'+(applist[i].e_mail==null||applist[i].e_mail==""?"邮箱未知":applist[i].e_mail)+'</span></div>';
  		 		if(eval("${loginUser.is_manager==1}"))
  		 		_html+='<div class="div3"><a onclick="acceptuser(this,30)">忽略</a></div>\
  		 				<div class="div3"><a style="background-color:#0099cc;" onclick="acceptuser(this,20)">允许</a></div>';
  		 				
         	_html+='</li>';
		
		
		}
		}
		for(i in userlist){
		
			/* _temp+='<li class="tag"><p>企业联系人</p></li>'; */
		if(userlist[i].status!=2){
		         _temp+='<li index="'+(i)+'"  type="contact" onclick="showThis(this);"><div class="div1"><img src="http://www.tulingbuy.com/'+userlist[i].headimgurl+'" onerror="this.src=\'/images/default_img.png\'"></img></div>\
		         <div class="div2" style="width:100px;"><span><i class="iconfont icon-profile" style=""></i>'+userlist[i].xm+(userlist[i].is_manager==1?"<i style=\"color:#bc9547;padding:px;font-size:10pt;\" class=\"iconfont icon-vipdakehu\"></i>":"")+'</span></div>\
		         <div class="div2" style="width:150px;"><span><i class="iconfont icon-dianhua" style=""></i>'+userlist[i].phone+'</span></div>\
		         <div class="div2" style="width:100px;"><span><i class="iconfont icon-gongsi" style=""></i>'+(department_list[userlist[i].owning_department+""]==null?"部门未知":department_list[userlist[i].owning_department+""])+'</span></div>\
		         <div class="div2" style="width:200px;"><span><i class="iconfont icon-email" style=""></i>'+(userlist[i].e_mail==null||userlist[i].e_mail==""?"邮箱未知":userlist[i].e_mail)+'</span></div>';
  		 		if(eval("${loginUser.is_manager==1}")&&eval("${loginUser.user_id}"!=userlist[i].user_id))
  		 		_temp+='<div class="div3"><a onclick="forbiduser(this);">禁用</a></div>';
  		 		else
  		 		_temp+='<div class="div3"><a style="display:none;"></a></div>';
         	_temp+='</li>';
		}else{
		if(eval("${loginUser.is_manager==1}")){
			 _cache+='<li index="'+(i)+'"  type="forbidden" onclick="showThis(this);"><div class="div1"><img src="http://www.tulingbuy.com/'+userlist[i].headimgurl+'" onerror="this.src=\'/images/default_img.png\'"></img></div>\
			<div class="div2" style="width:100px;"><span><i class="iconfont icon-profile" style=""></i>'+userlist[i].xm+(userlist[i].is_manager==1?"<i style=\"color:#bc9547;padding:px;font-size:10pt;\" class=\"iconfont icon-vipdakehu\"></i>":"")+'</span></div>\
		         <div class="div2" style="width:150px;"><span><i class="iconfont icon-dianhua" style=""></i>'+userlist[i].phone+'</span></div>\
		         <div class="div2" style="width:100px;"><span><i class="iconfont icon-gongsi" style=""></i>'+(department_list[userlist[i].owning_department+""]==null?"部门未知":department_list[userlist[i].owning_department+""])+'</span></div>\
		         <div class="div2" style="width:200px;"><span><i class="iconfont icon-email" style=""></i>'+(userlist[i].e_mail==null||userlist[i].e_mail==""?"邮箱未知":userlist[i].e_mail)+'</span></div>\
			 	 ';
  		 		if(eval("${loginUser.is_manager==1}"))
  		 		_cache+='<div class="div3"><a style="background-color:#0099cc;" onclick="forbiduser(this);">启用</a></div>';
		}
		}	

		
		
		}
		$(".left_area[name='my_application']").html(_html);
		$(".left_area[name='my_contact']").html(_temp);
		$(".left_area[name='my_forbidden']").html(_cache);
		if(_count==0)
		showThis($(".left_area").find("li[index]").eq(0));
		_count++;
		changeTags(tagname)
	})
}

function acceptuser(_e,_status){
var _index = Number($(_e).parent().parent().attr("index"));
var postdata={};
postdata=applist[_index];
postdata.modified_by=eval("${loginUser.user_id}");
postdata.status = _status;
delete postdata["create_time"];
delete postdata["modified_time"];

//

layer.confirm('是否同意用户&nbsp;'+postdata.xm+'&nbsp;加入?', {
  btn: ['确定','取消'], //按钮
  title:"提示"
}, function(){

$.post("<%=basePath%>user/acceptuser.json",postdata,function(data){
if(data.rescode==1){
		layer.msg("成功添加");
		//触发同步
		doAsync({"user_id":postdata.user_id,"owning_company":postdata.owning_company});
		//刷新
		initdata("my_adduser");
	}else{
		layer.msg(data.resMsg);
	}
})

});

//


}
function forbiduser(_e){
var _index = Number($(_e).parent().parent().attr("index"));
var _user=userlist[_index];
var postdata={};
postdata.user_id=_user.user_id;
if(_user.status!=2)
postdata.value=2;
else
postdata.value=1;
postdata.param="status";
layer.confirm('是否'+(postdata.value==2?"禁用&nbsp;":"启用&nbsp;")+_user.xm+'?', {
 title:"提示",
  btn: ['确定','取消'] //按钮
}, function(){
 $.post("<%=basePath%>user/modifyuserinfo.json",postdata,function(data){
		layer.msg("用户"+_user.xm+"已"+(postdata.value==2?"禁用":"启用"));
		//触发同步
		doAsync({"user_id":_user.user_id,"status":postdata.value});
		//刷新
		initdata();
})
});



}

//点击进入详情
function showThis(_e){
	var _index = Number($(_e).attr("index"));
	var _type = $(_e).attr("type");
	var _data={};
	if(_type=="application")
		_data=applist[_index];
	else
		_data=userlist[_index];
		
		var _tmp="";
		$("#userimg").html('<img src="http://www.tulingbuy.com/'+_data.headimgurl+'" onerror="this.src=\'/images/default_img.png\'"></img>');
		if(!!_data.xm)
		_tmp+='<li><p style="font-size:15pt;"><i class="iconfont icon-huiyuankehu" style=""></i>'+_data.xm+'</p></li>';
		if(!!_data.e_mail)
		_tmp+='<li><p><i class="iconfont icon-email" style=""></i>'+_data.e_mail+'</p></li>';
		if(!!_data.phone)
		_tmp+='<li><p><i class="iconfont icon-dianhua" style=""></i>'+_data.phone+'</p></li>';
		if(!!_data.owning_department)
		_tmp+='<li><p><i class="iconfont icon-gongsi" style=""></i>'+department_list[_data.owning_department+""]+'</p></li>';
		if(!!_data.create_time)
		_tmp+='<li><p><i class="iconfont icon-qingjia" style=""></i>'+getLocalTime(_data.create_time)+'</p></li>';
	$("#user_content").html(_tmp);
	$(".top").html('<p>'+_data.xm+'</p>');
}

//同步
	function doAsync(userinfo){
		var _userinfo = userinfo;
		var postdata = {};
		var content={};
		content.table="tbl_user_info";
		content.data=_userinfo;
		content.name="TLM_CONTACT_CHANGED";
		postdata.name="TLM_CONTACT_CHANGED";
		postdata.content=JSON.stringify(content);
		if(_userinfo!=null&& !$.isEmptyObject(_userinfo))
		$.post('/async.json',postdata,function(){});
	}
	//格式化时间
	function getLocalTime(nS) {       
   return new Date(parseInt(nS) ).toLocaleString().replace(/:\d{1,2}$/,' ');       
	}       
	
	//弹出tips
	function showTips(_e){
		var _index = Number($(_e).attr("index"));
		var _content = applist[_index].content;
		if(!!_content && _content.length>0)
		 	var num = layer.tips("【备注】"+_content, _e,{'time':300000});

		$(_e).mouseout(function(){
			setTimeout(function() {
				layer.close(num)
			}, 200)
		});

	}
	//代用户注册
		//注册事件
	$("#btn_register").click(function(){
		var flag= true;
		//验证必填输入
	    $(".register_main").find("input[checkin='true']").each(function(){
	    	if($(this).val()==""){
	    		$(this).css("border-color","#e12f23");
	    		flag=false;
	    	}else{
	    		$(this).css("border-color","#d7d7d7");
	    	}
	    });
	    if(flag){
	    	if($("#password_str").val().length<6){
	    		$("#password_str").css("border-color","#e12f23");
	    		flag=false;
	    		layer.msg('密码太简单，请重新设置', {
	    		    shift: 6
	    		});
	    	}
	    }
	    if(flag){//验证密码是否一致
	    	if($("#password_str").val()!=$("#password_str2").val()){
	    		layer.msg('两次密码输入不一致，请重新输入', {
	    		    shift: 6
	    		});
	    		$("#password_str").css("border-color","#e12f23");
	    		$("#password_str2").css("border-color","#e12f23");
	    		flag=false;
	    	}
	    }
	    var reg_mail =/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9_\-]+(\.[a-zA-Z0-9_\-]+)+$/;
	    if (!reg_mail.test($("#e_mail").val())) {
    		$("#e_mail").css("border-color","#e12f23");
    		flag=false;
    	}
	    if(flag){
	    	var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
	    	if (!reg_mail.test($("#e_mail").val())) {
	    		$("#e_mail").css("border-color","#e12f23");
				layer.msg("邮箱格式填写错误，请重新填写！");
	    	}else if(!reg_phone.test($("#phone").val())){
				$("#phone").css("border-color","#e12f23");
				layer.msg("手机号码填写错误，请重新填写！");
			}else{
					//开始提交注册
	    			var password_str_ = $.md5($("#password_str").val());
					var userinfo = {'openid':$("#openid").val(),'xm':$("#xm").val(),'password_str':password_str_,'e_mail':$("#e_mail").val(),'gsmc':$("#gsmc").val(),'hysx':$("#hysx").val(),'ssbm':$("#owning_department").find("option:selected").text(),"owning_department":$("#owning_department").val(),'phone':$("#phone").val(),'owning_company':eval("${loginUser.owning_company}"),'password_text':$("#password_str").val()};
					$.post('<%=basePath%>register/register4user.json',userinfo,function(data){
						if(data.error){
							layer.msg(data.message);
						}else{
							if(data.id>0){
								userinfo.user_id=data.id;
								doAsync(userinfo);
								initdata();
								layer.msg('注册成功',{icon: 1,time: 3000,shade:0.1});
							}else{
								layer.msg("对不起，注册失败，请重新注册");
							}
						}
					});
				
			}
	    }
	});	
</script>
</body>
</html>
