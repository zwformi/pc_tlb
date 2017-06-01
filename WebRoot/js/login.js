$(document).ready(function(){
	//到达注册页
	$("#register").click(function(){
		document.location.href="/register.json";
	});
	
	//到达个人中心页
	$("#togrzx").click(function(){
		document.location.href="/find/find_main.html";
	});
	
	//退出登录
	$("#outlogin").click(function(){
		document.location.href="/login/out.html";
	});
	
	//登陆操作
	$("#login").click(function(){
		var flag= true;
		//一般登录的时候不做判断，以下语句在登录时使用
		/*应该加上判断是否为手机号码的语句
		 * var patten = /^1[3,5,7,8]\d{9}$/;
		 * if语句里添加else if(patten.test($("#phone").val())){
		 * 		flag=false;
		 * 		layer.msg('您输入的手机号码有误'，{shift:6});
		 * 		$("#phone").css("border-color","C");
		 * }
		 */
		if(flag){
			if($("#phone").val()==""){
				flag=false;
				layer.msg('输入登录的手机号码', {shift: 6});
	    		$("#phone").css("border-color","C");
			}else{
				$("#phone").css("border-color","#d7d7d7");
			}
			if($("#password_str").val()==""&&flag){
				flag=false;
				layer.msg('请输入登录密码', {shift: 6});
	    		$("#password_str").css("border-color","#e12f23");
			}else{
				$("#password_str").css("border-color","#d7d7d7");
			}
		}
	    if(flag){
				//开始提交登陆
	    		var password_str_ = $.md5($("#password_str").val());
				var userinfo = {'password_str':password_str_,'phone':$("#phone").val()};
				var rememberPassword=0;
				if($("#rememberPassword").is(':checked')) {//是否选择--选中
					rememberPassword=1;
				}
				$.post('/login.json?rememberPassword='+rememberPassword,userinfo,function(data){
					if(data.error){
						alert(data.message);
					}else{
						if(data.count>0){
							//layer.msg('登陆成功！');
							location.href="/"+data.url;
						}
					}
				});
	    }
	});	
});