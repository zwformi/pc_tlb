$(document).ready(function(){
	
	$("#forget_btn").click(function(){
		var flag= true;
		
		if($("#password_str").val().length<6){
    		$("#password_str").css("border-color","#e12f23");
    		flag=false;
    		layer.msg('密码太简单，请重新设置', {
    		    shift: 6
    		});
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
		
		var password_str = $("#password_str").val();
		$("#password_str").val($.md5($("#password_str").val()));
		$("#password_str2").val($.md5($("#password_str2").val()));
		$("#password_text").val($("#password_str").val());
		
		if(flag){
			var frm=$('#forgetForm').serialize();
			$.post('/forget/reset.json',frm,function(data){
				if(data.error){
					layer.msg(data.message);
					$("#password_str").val(password_str);
					$("#password_str2").val(password_str);
					$("#password_text").val(password_str);
				}else{
					layer.alert(data.message, {icon: 1}, function(index){
						layer.close(index);
						location.href = "/index.html";
					});
				}
			});
		}
	});
	
});