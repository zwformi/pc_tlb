$(document).ready(function(){
	//下一步 1发送验证码
	$("#next_1").click(function(){
		var reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
		if($("#phone").val()==""){
			layer.msg("请输入新的手机号码！");
		}else if(!reg_phone.test($("#phone").val())){
			$("#phone").css("border-color","#e12f23");
			layer.msg("手机号码填写错误，请重新填写！");
		}else if($("#phone").val()==$("#old_phone").val()){
			$("#phone").css("border-color","#e12f23");
			layer.msg("与原来的手机号码相同，请重新填写！");
		}else if(!!$("#VerifyCode").val()==false){
				$("#VerifyCode").css("border-color","#e12f23");
				layer.msg("请填写图形验证码！");
				return_val = false;;
			}
		else
		{
			fsyzm(1);
		}
	});
	
	//下一步 2修改
	$("#next_2").click(function(){
		var phone = $("#phone").val();
		var code = $("#CODE").val();
		if($("#CODE").val()==""){
			layer.msg("请输入接收的的验证码！");
		}else{
			$.post('phone_update.json?NEWPHONE='+phone+'&CODE='+code,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						var uinfo={"phone":phone};
						doAsync(uinfo);
						document.location.href="change_phone03.jsp";
					}else{
						layer.msg("修改手机号码出现未知错误，请重试");
					}
				}
			});
		}
	});
	//发送验证码
	function fsyzm(op){
		//发送验证码
		var phone = $("#phone").val();
		$.post('phone_fsyzm.json',{'phone':phone,'VerifyCode':$("#VerifyCode").val()},function(data){
			if(data.error){
				layer.msg(data.message);
				refreshCode();
			}else{
				if(data.yzm>0){
					if(op==1){
						document.location.href="change_phone02.jsp";
					}else{
						layer.msg("获取验已成功发送到您填写的手机，请注意查收");
					}
				}else{
					layer.msg("获取验证码失败，请重试");
				}
			}

		});
		return true;
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
            $(o).css("background-color","#e12f23");
            $(o).css("border-color","#e12f23");      
            $(o).val("重新发送");
            wait = 60;
        } else {
        	if(wait==60){
        		flg = fsyzm();
        	}
        	if(flg){
	            $(o).unbind('click');
	            $(o).val("(" + wait + "S)");
	            $(o).css("background-color","#b0b0b0");
	            $(o).css("border-color","#b0b0b0");
	            wait--;
	            setTimeout(function() {
	                time(o)
	            },1000);
            }
        }
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
		alert(JSON.stringify(postdata))
	}
});