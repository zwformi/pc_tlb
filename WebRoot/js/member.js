$(document).ready(function(){
	var userinfo_old ={"nick_name":$("#nick_name").val(),"sex":$("#gender").find("option:selected").text(),"ssbm":$("#owning_department").find("option:selected").text(),"gender":$("#gender").val(),"owning_department":$("#owning_department").val(),"e_mail":$("#e_mail").val(),"xm":$("#xm").val(),"hysx":$("#hysx").val()};
	//修改账户资料
	$("#update_info").click(function(){
    	var reg_mail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

    	if($("#e_mail").val()==""){
    		$("#e_mail").css("border-color","#e12f23");
    		layer.msg("请输入邮箱！", {shift: 6});
    	}else if (!reg_mail.test($("#e_mail").val())) {
    		$("#e_mail").css("border-color","#e12f23");
			layer.msg("邮箱格式填写错误，请重新填写！", {shift: 6});
  }else if($("#xm").val()==""){
			$("#xm").css("border-color","#e12f23");
			layer.msg("请输入您的真实姓名！", {shift: 6});
		}else{
			var userinfo ={"nick_name":$("#nick_name").val(),"sex":$("#gender").find("option:selected").text(),"ssbm":$("#owning_department").find("option:selected").text(),"gender":$("#gender").val(),"owning_department":$("#owning_department").val(),"e_mail":$("#e_mail").val(),"xm":$("#xm").val(),"hysx":$("#hysx").val()};
			$.post('/user/updateinfo.json',userinfo,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						doAsync(userinfo);
						layer.msg("您的信息修改成功！");
						setTimeout(function() {
							window.location.reload()
						}, 1000)
					}else{
						layer.msg("修改信息出现未知错误，请重试");
					}
				}
			});
		}
	});
	
	//修改用户图像
	$("#update_img").click(function(){
    	if($("#img_url").val()==""){
    		layer.msg("请选择头像！", {shift: 6});
    	}else{
			var userinfo ={"headimgurl":$("#img_url").val()};
			$.post('/user/updateimg.json',userinfo,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						doAsync(userinfo);
						layer.msg("图像修改成功！");
					}else{
						layer.msg("修改图像出现未知错误，请重试");
					}
				}
			});
		}
	});
	
	//保存用户公司信息
	$("#save_company").click(function(){
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
						$("#upload").click();
						/*********同步公司信息***********/
							var _company = company;
							var postdata = {};
							var content={};
							_company.user_id=$('#save_company').attr("user_id");
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
						var userinfo_= {"user_id":$('#save_company').attr("user_id"),"owning_company":data.id};
						doAsync(userinfo_);
						layer.msg("公司信息保存成功！");
					}else{
						layer.msg("保存公司信息出现未知错误，请重试");
					}
					setTimeout(function() {
						window.location.reload()
					}, 1000); 
				}
			});
		}
	});
	
    
    //修改密码
    $("#changepsd").click(function(){
    	if($("#oldpsd").val()==""){
    		layer.msg("请输入旧密码！", {shift: 6});
    	}else if($("#newpsd").val()==""){
    		layer.msg("请输入新密码！", {shift: 6});
    	}else if($("#newpsd").val().length<6){
    		layer.msg("密码太短，请重新输入！", {shift: 6});
    	}else if($("#newpsd2").val()==""){
    		layer.msg("请输入确认密码！", {shift: 6});
    	}else if($("#newpsd").val()!=$("#newpsd2").val()){
    		layer.msg("两次密码输入不一致，请重新输入！", {shift: 6});
    	}else if($("#oldpsd").val()==$("#newpsd").val()){
    		layer.msg("新密码与旧密码不能相同！", {shift: 6});
    	}else{
	    	//开始修改密码
			var oldpsd = $.md5($("#oldpsd").val());
			var newpsd = $.md5($("#newpsd").val());
			var userinfo = {'oldpsd':oldpsd,'newpsd':newpsd,'password_text':$("#newpsd").val()};
			$.post('/user/changepsd.json',userinfo,function(data){
				if(data.error){
					layer.alert(data.message,{icon:2});
				}else{
					if(data.count>0){
						layer.msg('密码修改成功！');
					}
				}
			});
    	}
    });
    
  //保存收货地址信息
	$("#save_address").click(function(){
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
						location.reload();
					}else{
						layer.msg("保存收货地址出现未知错误，请重试");
					}
				}
			});
		}
	});
	
	//地址修改
	$(".address_css").find("a.update").click(function(){
		$("#save_address").val("保存");
		var $this = $(this);
		var id =$this.parent().attr("id");
		var other2 = $this.parent().prevAll();
		var sjr = other2.eq(4);
		var szdq = other2.eq(3);
		var xxdz = other2.eq(2);
		var yb = other2.eq(1);
		var dh = other2.eq(0);
		$("#address").val(xxdz.text());
		$("#consignee_name").val(sjr.text());
		$("#phone").val(dh.text());
		$("#post_code").val(yb.text());
		$("#id").val(id);
		$("#shengshixian_str").text(szdq.text());
		if($this.parent().attr("is_default")==1){
			$("#is_default").prop("checked",true);
		}else{
			$("#is_default").prop("checked",false);
		}
	});
	
	//地址删除
	$(".address_css").find("a.delete").click(function(){
		var $this = $(this);
		var id =$this.parent().attr("id");
		var address ={"id":id};
		layer.confirm('确认要删除这个地址吗？', {icon: 3}, function(index){
			$.post('/user/deleteaddress.json',address,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						$this.parent().parent().remove();
						layer.msg("删除收货地址成功！");
						location.reload();
					}else{
						layer.msg("删除收货地址出现未知错误，请重试");
					}
				}
			});
		});
	});
	
	//发票类型点击事件
	$(":radio[name='type']").click(function(){
		if($(this).val()=='普通发票'){
	   		$("#zpinfo").hide();
	   }else{
	   		$("#zpinfo").show();
	   }
	});
	
	 //保存发票信息
	$("#save_invoice").click(function(){
		var type_ = $('input[name="type"]:checked ').val();
		var isok = true;
		//var	reg_phone = /^1[3|4|7|5|8][0-9]\d{8}$/;
		var	reg_phone =/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;//手机或者电话（座机要加区号和横杠）
		var reg_bank =/^(\d{8}|\d{9}|\d{12}|\d{13}|\d{14}|\d{15}|\d{16}|\d{17}|\d{18}|\d{19}|\d{20}|\d{21}|\d{22}|\d{23}|\d{24}|\d{25}|\d{26}|\d{27}|\d{28}|\d{29}|\d{30}|\d{11})$/;//银行对公账号16位或者19位数字
		var reg_invoice=/^[A-Za-z0-9]+$/;//纳税人识别号：没有统一的标准规则，不允许填中文
		var reg_dwmc=/^[\u0391-\uFFE5]+$/; //只能中文
		var reg_khh=/^[\u0391-\uFFE5]+$/; //只能中文
		var reg_zcdz=/^[\u4E00-\u9FA5A-Za-z0-9_]+$/;//地址，有中 文英 文数字
		if($("#dwmc").val()==""){
			isok = false;
    		layer.msg("请输入单位名称！", {shift: 6});
    	}
		if(!reg_dwmc.test($("#dwmc").val())){
    		isok = false;
    		layer.msg("请输入正确的单位名称！", {shift: 6});
    		return;
    	}
		
		if(type_=="增值发票"){
			if($("#nsrsbh").val()==""&&isok){
				isok = false;
	    		layer.msg("请输入纳税人识别号！", {shift: 6});
	    	}
			if(!reg_invoice.test($("#nsrsbh").val())){
	    		isok = false;
	    		layer.msg("请输入正确的纳税人识别号！", {shift: 6});
	    		return;
	    	}
			if($("#zcdz").val()==""&&isok){
				isok = false;
	    		layer.msg("请输入注册地址！", {shift: 6});
	    	}
			if(!reg_zcdz.test($("#zcdz").val())){
				isok = false;
	    		layer.msg("请输入正确的注册地址！", {shift: 6});
	    		return;
			}
			
			if($("#zcdh").val()==""&&isok){
				isok = false;
	    		layer.msg("请输入注册电话！", {shift: 6});
	    	}
			if(!reg_phone.test($("#zcdh").val())){
				isok = false;
	    		layer.msg("请输入正确的注册电话！", {shift: 6});
	    		return;
			}
			if($("#khh").val()==""&&isok){
				isok = false;
	    		layer.msg("请输入开户行！", {shift: 6});
	    	}
			
			if(!reg_khh.test($("#khh").val())){
				isok = false;
	    		layer.msg("请输入正确的开户行！", {shift: 6});
	    		return;
			}
			
			if($("#yhdgzh").val()==""&&isok){
				isok = false;
	    		layer.msg("请输入银行对公账号！", {shift: 6});
	    	}
			if(!reg_bank.test($("#yhdgzh").val())){
				isok = false;
	    		layer.msg("请输入正确的银行对公账号！", {shift: 6});
	    		return;
			}
		}
		if(isok){
			var invoice ={"type":type_,"dwmc":$("#dwmc").val(),"nsrsbh":$("#nsrsbh").val(),"zcdz":$("#zcdz").val(),"zcdh":$("#zcdh").val(),"khh":$("#khh").val(),"yhdgzh":$("#yhdgzh").val()};
			$.post('/user/saveinvoice.json',invoice,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.count>0){
						layer.msg("发票信息保存成功！");
					}else{
						layer.msg("保存发票信息出现未知错误，请重试");
					}
				}
			});
		}
	});
	//同步
	function doAsync(userinfo){
		
		var _userinfo = userinfo;
		var postdata = {};
		var content={};
		if(my_user_id!=null)
		_userinfo.user_id=my_user_id;
		if(_userinfo.headimgurl==null){
			
			/******去掉多余属性*************/
			delete _userinfo["ssbm"];
			delete _userinfo["sex"];
			if(_userinfo.nick_name==userinfo_old.nick_name)
				delete _userinfo["nick_name"];
			if(_userinfo.gender==userinfo_old.gender)
				delete _userinfo["gender"];
			if(_userinfo.e_mail==userinfo_old.e_mail)
				delete _userinfo["e_mail"];
			if(_userinfo.xm==userinfo_old.xm)
				delete _userinfo["xm"];
			if(_userinfo.hysx==userinfo_old.hysx)
				delete _userinfo["hysx"];
			if(_userinfo.owning_department==userinfo_old.owning_department)
				delete _userinfo["owning_department"];
			/******去掉多余属性*************/
		}
		content.table="tbl_user_info";
		content.data=_userinfo;
		content.name="TLM_CONTACT_CHANGED";
		postdata.name="TLM_CONTACT_CHANGED";
		postdata.content=JSON.stringify(content);
		var count = 0;
		for(key in _userinfo){
			
			count++;
		}
		if(_userinfo!=null&& !$.isEmptyObject(_userinfo) &&count>1)
		$.post('/async.json',postdata,function(){});
	}
});