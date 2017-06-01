

$(document).ready(function($){
	if($("#resource").val()=="4")
		{
			$(".cart_show_table").find("tr").each(function(index){
				if(index!=2&&index!=3)
					$(this).hide();				
			})
		
		}
	
	$('a[class="address_div"]').each(function(){
		var t = $(this);
		t.find("i").hide();
	});

	//收货人区域样式
	$('input[is_default="1"]').parent().addClass("active");
	$('input[is_default="1"]').parent().find("i").show();
	$('input[z_id]').on('click', function(){
		$('a[class="address_div active"]').removeClass("active");
		$('a[class="address_div"]').each(function(){
			var t = $(this);
			t.find("i").hide();
		});
		 var tagA =$(this).parent();
		 tagA.addClass("active");
		 tagA.find("i").show();
	});  
	
	//商品图片加载错误事件
	$(".cart_table").find(".product").find("img.img").on("error",function(){
		$(this).attr("src","/images/no_pic.png");
	});
	

	//添加地址，并默认选中
	$("#add_address").click(function(){
		layer.open({
		  title:'新增收货地址',
		  type: 2,
		  area: ['500px', '430px'],
		  fix: false, //不固定
		  maxmin: true,
		  content: '/add_address.jsp'
		});
	});
	//订单确认时-编辑地址
	$(document).on("click",".edit_address",function(){
		$(this).prev("input").prop("checked",true);
		layer.open({
		  title:'修改收货地址',
		  type: 2,
		  area: ['500px', '460px'],
		  fix: false, //不固定
		  maxmin: true,
		  content: '/edit_address.jsp'
		});
	});
	
	function setData(){
    	var resource = $("#resource").val();//订单来源-私有
    	var order;
    	var isflag=true;
    	//秒杀参数
    	if(resource!=null){
    		var product_id = $(".cart_table").find(".product").attr("product_id");//商品ID（秒杀）
    		var product_ids = $("#product_ids").val();
    		var product_item_ids = $("#product_item_ids").val();
    		var product_counts = $("#product_counts").val();
    		var demand_file = $("#demand_file").val();
    		var demand_file2 = $("#demand_file2").val();
    		var demand_file3 = $("#demand_file3").val();
    		var owning_company = $("#owning_company").val(); 
    		var content = $("#content").val();
    		var shipping_methods = $("input[name='shipping_methods' ]:checked").val();
    		var install_service  = $("input[name='install_service' ]:checked").val();
    		var xm=$("input[name='address_select']:checked").attr("xm");
    		var dh=$("input[name='address_select']:checked").attr("dh");
    		var dz=$("input[name='address_select']:checked").attr("dz");
    		var yb=$("input[name='address_select']:checked").attr("yb");
    		if(resource!=4){
    			
    			if($("input[name='address_select']:checked").val()==undefined){
        			isflag=false;
        			alert("请选择收货地址");
        		}
    			
    		}else{
    			
    			xm=dh=dz=yb="";
    		}
    		
    		order = {'xm':xm,'dz':dz,'dh':dh,'yb':yb,'resource':resource,'product_id':product_id,'product_ids':product_ids,'product_item_ids':product_item_ids,'product_counts':product_counts,'demand_file':demand_file,'demand_file2':demand_file2,'demand_file3':demand_file3,'content':content,'shipping_methods':shipping_methods,'install_service':install_service,'owning_company':owning_company};
    	}else{
    		layer.msg('页面参数错误...', {icon: 2});
    	}
    	if(isflag)
    		return order;
    	else 
    		return null;
	}
	
	//提交事件---需求
	function postData(order){
			var resource = $("#resource").val();//订单来源-私有

    		layer.msg('需求单提交中', {icon: 16,time: 180000,shade:0.3});
	    	$.post('/order.json',order,function(data){
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
	//提交事件---立即支付
	function postData2(order){
			var resource = $("#resource").val();//订单来源-私有
			var arr = {"0":"采购","1":"秒杀","2":"立即购买","4":"云服务"};
    		var te_ = arr[resource];
			layer.msg(te_+'单提交中', {icon: 16,time: 180000,shade:0.3});
			$.post('/order/onlinepay.json',order,function(data){
				if(data.error){
					layer.msg(data.message);
				}else{
					if(data.success=="true"){
						layer.msg(te_+'单提交成功，即将跳转支付',{icon: 1,time: 1000,shade:0.1},function(){
							if(data.order_number!=null && data.order_number!=''){
								location.href="/pay.html?order_number="+data.order_number;
								}
						});
					}else{
						layer.msg(te_+"单提交失败，请重试！");
					}
				}
			});
		
	}
	
	
	
    //提交订单事件
    $(".cart_bottom").find(".cart_btn").on("click",
    		function(){
    	var data  =setData();
    	
    	if(data!=null)
    	postData(data);
    });
    //立即支付
    $("#button_pay").on("click",
    		function(){
    	var data  =setData();
    	if(data!=null)
    	postData2(data);
    });

});