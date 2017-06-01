$(document).ready(function($){
	//确认需求事件
    $("#but_sub").on("click",function(){
    	if($("#title").val()==""){
    		layer.msg("请填写需求标题！");
    	}else{
			var order = {'title':$("#title").val(),'content':$("#content_sub").val(),'demand_file':$("#file_url").val()};
			$.post('/order/one.json',order,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.success=="true"){
						layer.msg('需求单提交成功，<br/>即将返回...',{icon: 1,time: 3000,shade:0.1},function(){
								location.href="/find/find_demand.html";
						});
					}else{
						layer.msg("需求单提交失败，请重试！");
					}
				}
			});
    	}
    });
});
