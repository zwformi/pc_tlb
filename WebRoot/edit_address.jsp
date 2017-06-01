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
<title>添加收货信息---图灵买</title>
<link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js"></script>
<script src="<%=basePath %>js/shenshixian.js" ></script>
<style type="text/css">
	.address_css{border:1px solid #cccccc;font-size:12px;}
	.address_css tr th{text-align:left;padding-left:5px;}
	.address_css tr td{text-align:left;padding-left:5px;border-bottom:1px solid #eeeeee;}
</style>
<script type="text/javascript">
	$(document).ready(function($){
		//加载省市县三级联动
	    areaSelect.createSelect();  
	    loadOldAddress();
	    
	    //加载原先的地址信息
	    function loadOldAddress(){
				var $this = parent.$("input[name='address_select']:checked");
				var id =$this.attr("z_id");
				var sjr = $this.attr("xm");
				var szdq = $this.attr("ssx");
				var xxdz = $this.attr("xxdz");
				var yb = $this.attr("yb");
				var dh = $this.attr("dh");
				var is_default = $this.attr("is_default");
				$("#address").val(xxdz);
				$("#consignee_name").val(sjr);
				$("#phone").val(dh);
				$("#post_code").val(yb);
				$("#id").val(id);
				$("#shengshixian_str").text(szdq);
				if(is_default==1){
					$("#is_default").prop("checked",true);
				}else{
					$("#is_default").prop("checked",false);
				}
	    }
	    
	    //保存收货地址信息
	$("#save_address").click(function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
		var ssx_ok="";
    	if($("#consignee_name").val()==""){
    		layer.msg("请输入收件人！", {shift: 6});
    	}else if($("#shengshixian_str").text()==""&&($("#sheng").find("option:selected").text()=="请选择"||$("#shi").find("option:selected").text()=="请选择"||$("#quxian").find("option:selected").text()=="请选择")){
    		if($("#sheng").find("option:selected").text()=="请选择"){
	    		layer.msg("请选择所在省份！", {shift: 6});
	    	}else if($("#shi").find("option:selected").text()=="请选择"){
	    		layer.msg("请选择所在城市！", {shift: 6});
	    	}else if($("#quxian").find("option:selected").text()=="请选择"){
	    		layer.msg("请选择所在县区！", {shift: 6});
	    	}
    	}else if($("#address").val()==""){
    		layer.msg("请输入详细地址！", {shift: 6});
    	}else if($("#phone").val()==""){
    		layer.msg("请输入收件人手机号码！", {shift: 6});
    	}else if(!reg_phone.test($("#phone").val())){
    		layer.msg("手机号码格式错误，请重新输入！", {shift: 6});
    	}else if($("#post_code").val()==""){
    		layer.msg("请输入邮政编码！", {shift: 6});
    	}else{
    		var is_default=0;
    		if($("#is_default").is(':checked')){
    			is_default=1;
    		}
    		if($("#sheng").find("option:selected").text()!="请选择"&&$("#shi").find("option:selected").text()!="请选择"&&$("#quxian").find("option:selected").text()!="请选择"){
        		ssx_ok=$("#sheng").find("option:selected").text()+" "+$("#shi").find("option:selected").text()+" "+$("#quxian").find("option:selected").text();
        	}else{
        		ssx_ok=$("#shengshixian_str").text();
        	}
			var address ={"is_default":is_default,"id":$("#id").val(),"consignee_name":$("#consignee_name").val(),"ssx":ssx_ok,"address":$("#address").val(),"phone":$("#phone").val(),"post_code":$("#post_code").val()};
			$.post('/user/saveaddress.json',address,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						layer.msg("收货地址保存成功！");
						var $this_ = parent.$("input[name='address_select']:checked").parent();
						var html_="<a class=\"address_div\">"+
					       			"<input autocomplete=\"off\" type=\"radio\" z_id=\""+$("#id").val()+"\" ssx=\""+ssx_ok+"\" xxdz=\""+$("#address").val()+"\" is_default=\""+is_default+"\" xm=\""+$("#consignee_name").val()+"\" dh=\""+$("#phone").val()+"\" dz=\""+ssx_ok+" "+$("#address").val()+"\" yb=\""+$("#post_code").val()+"\" name=\"address_select\" checked value=\"1\"/>"+
					       			"&nbsp;<i  class='iconfont icon-dingwei'></i>&nbsp;"+ssx_ok+"&nbsp;"+$("#address").val()+"&nbsp;("+$("#consignee_name").val()+"&nbsp;收)&nbsp;<span class='key_point'><i class='iconfont icon-dianhua'></i>&nbsp;"+$("#phone").val()+"&nbsp;<i class='iconfont icon-email'></i>&nbsp;"+$("#post_code").val()+"</span>&nbsp;&nbsp;&nbsp;<span class='edit_address cart_blue'>修改</span>"+
					       		"</a>";
						$this_.html(html_);
						parent.window.location.reload();
					    parent.layer.close(index);
					    
					}else{
						layer.msg("保存收货地址出现未知错误，请重试");
					}
				}
			});
		}
	});
    });
</script>
</head>

<body>
<!--center-->
     <ul class="member_zi">
       <li>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                 <th width="23%"><span class="red_zi">* </span>收件人：</th>
                 <td width="77%"><input class="member_box" type="text"  autocomplete="off" name="consignee_name" id="consignee_name" value="${USERADDRESS.consignee_name }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>所在地区：</th>
                 <td id="shengshixian"></td>
               </tr>
               <tr>
                 <th></th>
                 <td><span id="shengshixian_str"></span></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>详细地址：</th>
                 <td><input class="member90_box" type="text" name="address" id="address"   autocomplete="off" value="${USERADDRESS.address }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>手机号码：</th>
                 <td><input class="member_box" type="text" name="phone" id="phone"  autocomplete="off" value="${USERADDRESS.phone }"></td>
               </tr>
               <tr>
                 <th><span class="red_zi">* </span>邮编：</th>
                 <td><input class="member_box" type="text" name="post_code" id="post_code"  autocomplete="off" value="${USERADDRESS.post_code }"></td>
               </tr>
               <tr>
                 <th></th>
                 <td><input type="checkbox" value="1" name="is_default" id="is_default" autocomplete="off" />设置为默认收货地址</td>
               </tr>
               <tr>
                 <th>&nbsp;</th>
                 <td>&nbsp;<input type="hidden" name="id" id="id" value="0" autocomplete="off"/>
                 <input class="red_btn" name="input" type="button" id="save_address" value="确定" /></td>
               </tr>
          </table>
       </li>
     </ul>
</body>
</html>
