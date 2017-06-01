$(document).ready(function(){	
	$("#forget_btn").click(function(){
		var flag= true;
		
		if($("#phone").val() == ""){
    		$("#phone").css("border-color","#e12f23");
    		flag=false;
    		layer.msg('请输入您的手机号', {
    		    shift: 6
    		});
    	}
		if(flag){
	    	var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
	    	if(!reg_phone.test($("#phone").val())){
				$("#phone").css("border-color","#e12f23");
				flag=false;
				layer.msg("手机号码填写错误，请重新填写！");
			}else{
				$("#phone").css("border-color","#999");
			}
		}

		if(flag){
			if($("#code").val() == ""){
	    		$("#code").css("border-color","#e12f23");
	    		flag=false;
	    		layer.msg('请输入验证码', {
	    		    shift: 6
	    		});
	    	}else{
	    		$("#code").css("border-color","#999");
	    	}
		}
		
		if(flag){
			$.post('/forget/submit.json?code='+$("#code").val(),function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					location.href = "/forget/step2.json";
				}
			});
		}
	});
	
	//发送验证码
	function fsyzm(){
		var return_val = true;
		var phone = $("#phone").val();
		if(phone!=""){
			var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
			if(!reg_phone.test($("#phone").val())){
				$("#phone").css("border-color","#e12f23");
				layer.msg("手机号码填写错误，请重新填写！");
				return_val = false;
			}else if(!!$("#VerifyCode").val()==false){
				$("#VerifyCode").css("border-color","#e12f23");
				layer.msg("请填写图形验证码！");
				return_val = false;;
			}
			else{
				$.ajax({
			        type: "POST",
			        url: "/forget/fsyzm.json",
			        async: false, //设为false就是同步请求
			        cache: false,
			        data:{'phone':phone,'VerifyCode':$("#VerifyCode").val()},
			        success: function (data) {
			        	if(data.error){
							layer.msg(data.message);
							refreshCode();
							return_val = false;
						}else{
							if(data.yzm>0){
								layer.msg('验证码已发送，请注意查收');
							}else{
								layer.msg("获取验证码失败，请重试");
								return_val = false;
							}
						}
			        }
			    });
			}
		}else{
			$("#phone").css("border-color","#e12f23");
			layer.msg("请输入您的手机号");
			return_val = false;
		}
		return return_val;
	}
	
	var wait=60;
	$("#fsyzm").click(function(){
		time(this)
	});
	
	function time(o) {
		var flg = true;
        if (wait == 0) {
        	$(o).bind("click", function()
        	{
        		time(o)
        	});
            $(o).html("获取验证码");
            wait = 60;
        } else {
        	if(wait==60){
        		flg = fsyzm();
        	}
        	if(flg){
	            $(o).unbind('click');
	            $(o).html("(" + wait + "S)");
	            wait=wait-1;
	            setTimeout(function() {
	                time(o)
	            },1000);
            }
        }
    }
	
});