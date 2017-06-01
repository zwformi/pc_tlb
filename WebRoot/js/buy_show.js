$(document).ready(function(){
	$('.demo1').Tabs();
	//商品图片加载错误事件
	$(".product_left").find("img").on("error",function(){
		$(this).attr("src","/images/no_pic.png");
		$(this).parent().attr("href","/images/no_pic.png");
		$(this).parent().attr("rel","useZoom: 'zoom1', smallImage: '/images/no_pic.png'");
	});
	
	 //更新购物车数量
	
	 function loadCarts(){
	    	$.post('/user/getCartCount.json',function(data){
	    		if(data.error){
	    			//layer.msg(data.message);
	    		}else{
	    			$("#cartcount1").html(data.count);
	    		}
	    	});
	    }
	 
	//加事件
	$("#btn_jia").click(function(){
		var num = $("#count").val();
		var max_num = $("#countname").text();
		if(Number(num)<Number(max_num)){
			$("#count").val(Number(num)+1);
		}else{
			$("#count").val(max_num);
		}
	});
	
	//减事件
	$("#btn_jian").click(function(){
		var num = $("#count").val();
		if(Number(num)>1){
			$("#count").val(Number(num)-1);
		}else{
			$("#count").val(0);
		}
	});
	
	//数量的输入事件
	$("#count").blur(function(){
		var num = $(this).val();
		var max_num = $("#countname").text();
		if(Number(num)>Number(max_num)){
			$(this).val(max_num);
		}
		if(Number(num)<=0){
			$(this).val(0);
		}
	});
	
	//加入购物车事件
    $("#addcart").click(function(){
    	var product_id = $(this).attr("product_id");
    	var product_items_id=$(this).attr("product_items_id");
    	var max_num = Number($("#countname").text());
    	var count =$("#count").val();
    	var cart ={'product_id':product_id,'count':count,'product_items_id':product_items_id};
    	if(count>0 && max_num>0){
    	$.post('/cart.json',cart,function(data){
			if(data.error){
				layer.msg(data.message);
			}else{
				if(data.count>0){
					layer.msg('加入成功',{icon: 1,time: 1000,shade:0.1});
					loadCarts();
				}else{
					layer.msg("加入采购车失败，请重试！");
				}
			}
		});
    	}else if(max_num<=0)
    		layer.msg("商品库存不足.",{icon: 3,time: 1000,shade:0.1});
    	else if (count<=0)
    		layer.msg("请检查商品数量.",{icon: 3,time: 1000,shade:0.1});
    });
    
  //秒杀事件
    $("#ljqg").click(function(){
    	if($(this).is('.on')){
    		var isms_ = $(this).attr("isms");
	    	var product_id_ = $(this).attr("product_id");
	    	location.href="/order.html?isms="+isms_+"&product_id="+product_id_;
    	}
    });
    
    var leftTime;
	if($("#is_ms_").val()==1&&$("#flag_begin_").val()=="false"){
		var interval = 1000; //1秒刷新一次
	    leftTime=$("#leftTime_").val();
		window.setInterval(function(){ShowCountDown('djs');}, interval);         
	}
    function ShowCountDown(divname) 
	{ 
    	if($("#flag_begin_").val()=="false"){
			leftTime=leftTime-interval; 
			var leftsecond = parseInt(leftTime/1000); 
			var day1=Math.floor(leftsecond/(60*60*24)); 
			var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
			var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
			var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
			var cc = document.getElementById(divname); 
			var h = (hour+"").length==1?"0"+hour:hour;
			var m = (minute+"").length==1?"0"+minute:minute;
			var s = (second+"").length==1?"0"+second:second;
			cc.innerHTML = "秒杀倒计时:"+h+":"+m+":"+s; 
			if(hour==0&&minute==0&&second==0){
				$("#flag_begin_").val("true");
				$("#ljqg").val("立即抢购");
				$("#ljqg").removeClass("off");
				$("#ljqg").addClass("on");
			}
    	}
	}
});
