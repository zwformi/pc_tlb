$(document).ready(function(){
	
	//注册事件
	$("#register_btn").click(function(){
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
				//判断是否已同意协议
				if($("#checkboxty").is(':checked')) {//是否选择--选中
					//开始提交注册
	    			var password_str_ = $.md5($("#password_str").val());
					var userinfo = {'openid':$("#openid").val(),'xm':$("#xm").val(),'password_str':password_str_,'e_mail':$("#e_mail").val(),'gsmc':$("#gsmc").val(),'hysx':$("#hysx").val(),'ssbm':$("#owning_department").find("option:selected").text(),"owning_department":$("#owning_department").val(),'phone':$("#phone").val(),'CODE':$("#code").val(),'password_text':$("#password_str").val(),'owning_company':owning_company};
					$.post('register.json',userinfo,function(data){
						if(data.error){
							layer.msg(data.message);
						}else{
							if(data.id>0){
								userinfo.user_id=data.id;
								doAsync(userinfo);
								layer.msg('恭喜您，注册成功，即将进入登录页！',{icon: 1,time: 3000,shade:0.1},function(){
									location.href="/login.html";
								});
							}else{
								layer.msg("对不起，注册失败，请重新注册");
							}
						}
					});
				}else{
					layer.msg("请同意我们的注册协议，再注册");
				}
			}
	    }
	});	

	
	var wait=60;
	$("#fsyzm").click(function(){
		time(this)
	});

	
	//发送验证码
	function fsyzm(){
		//发送验证码
	

		var phone = $("#phone").val();
			if(phone!=""){
				var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
				if(!reg_phone.test($("#phone").val())){
					$("#phone").css("border-color","#e12f23");
					layer.msg("手机号码填写错误，请重新填写！");
					return false;
				}else if($("#verifycode").val().length<4){
						$("#verifycode").css("border-color","#e12f23");
						layer.msg('请检查验证码输入');
						return false;
						}else{

							$.post('register/fsyzm.json',{'phone':phone,'VerifyCode':$("#verifycode").val()},function(data){
								if(data.error){
									layer.msg(data.message);
									refreshCode();
								}else{
									if(data.yzm>0){
										layer.msg('验证码已发送，请注意查收');
									}else{
										layer.msg(data.message);
									}
								}
							});
						
				}
			}else{
				$("#phone").css("border-color","#e12f23");
				layer.msg("请填写您的手机号码！");
				return false;
			}
			return true;
			
		
	}

	
	$("#submitcode").click(function(){
		if($("#verifycode").val().length<4){
				layer.msg('请检查验证码输入');		
		}else{
			$.post('/register/checkcode.json',{"VerifyCode":$("#verifycode").val()},function(data){
				if(data.rescode==0){
					layer.msg(data.message);
				}else if(data.rescode==1){
					layer.msg('请注意查收手机验证码')
					$('tr[name="yzm"]').show();
					$('tr[name="verifycode"]').hide("slow");
					$('#fsyzm').attr("flag",true);
					$("#fsyzm").click();
					
				}
			})
		}		
	})
	
		
	function time(o) {
		var flg = true;
        if (wait == 0) {
        	$(o).bind("click", function()
        	{
        		time(o)
        	});
            //$(o).css("background-color","#e12f23");
            //$(o).css("border-color","#e12f23");      
            $(o).html("获取验证码");
            wait = 60;
        } else {
        	if(wait==60){
        		flg = fsyzm();
        	}
        	if(flg){
	            $(o).unbind('click');
	            $(o).html("(" + wait + "S)");
	            //$(o).css("background-color","#b0b0b0");
	            //$(o).css("border-color","#b0b0b0");
	            wait--;
	            setTimeout(function() {
	                time(o)
	            },1000);
            }
        }
    }

});

//同步
function doAsync(userinfo){
	var postdata = {};
	var content={};
	/******去掉多余属性*************/
	delete userinfo["CODE"];
	delete userinfo["ssbm"];
	delete userinfo["password_str"];
	if(userinfo.owning_department=="")	
	delete userinfo["owning_department"];
	/******去掉多余属性*************/
	content.table="tbl_user_info";
	content.data=userinfo;
	content.name="TLM_CONTACT_CHANGED";
	postdata.name="TLM_CONTACT_CHANGED";
	postdata.content=JSON.stringify(content);
	$.post('/async.json',postdata,function(){});
}