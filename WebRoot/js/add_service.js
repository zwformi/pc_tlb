$(document).ready(function($){
	//商品图片加载错误事件
	$("img.pro_img").on("error",function(){
		$(this).attr("src","/images/no_pic.png");
	});
	//确认需求事件
    $("#but_sub").on("click",function(){
    	if($("#lxr").val()==""){
    		layer.msg("请填写联系人！");
    	}else if($("#phone").val()==""){
    		layer.msg("请填写联系电话！");
    	}else if($("#address").val()==""){
    		layer.msg("请填写地址！");
    	}else if($("#content").val()==""){
    		layer.msg("请填写设备出现的问题！");
    	}else{
			var service = {'lxr':$("#lxr").val(),'phone':$("#phone").val(),'address':$("#address").val(),'content':$("#content").val(),'order_number':$("#order_number").val(),'order_detail_id':$("#order_detail_id").val()};
			$.post('/service/addservice.json',service,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.success=="true"){
						layer.msg('服务单提交成功，<br/>即将返回...',{icon: 1,time: 3000,shade:0.1},function(){
								location.href="/find/find_service.html";
						});
					}else{
						layer.msg("需求单提交失败，请重试！");
					}
				}
			});
    	}
    });
});
