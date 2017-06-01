
$(document).ready(function($){
	window.onload=function(){
		$(".selectall1").click();
	};
	
	//商品图片加载错误事件
	$(".cart_table").find("img.pro_img").on("error",function(){
		$(this).attr("src","/images/no_pic.png");
	});
	
	

  
	  $('.cart_table tbody tr.product td ').mouseover(function() {
      $(this).parent().children().addClass("cart_new");
  });
	  
	  
	  $('.cart_table tbody tr.product td ').mouseout(function() {
		  if($(this).closest("tr.product").hasClass("new")){
			  $(this).parent().children().addClass("cart_new");
		  }else{
	      $(this).parent().children().removeClass("cart_new");
	      
	      }
	  });
  
	
	
	//验证商品价格及数量
	validate();
	
	//加事件
	$(".cart_table").find(".product").find(".add").click(function(){
		var num = $(this).prev().val();
		var max_num = $(this).next().val();
		if(Number(num)<Number(max_num)){
			$(this).prev().val(Number(num)+1);
			//判断是否为选中状态的商品
			var $checkbox = $(this).closest("tr.product").find(".select").find("input");
			var $prize = $(this).closest("tr.product").find(".prize").find("input");
			if($checkbox.is(':checked')) {//是否选择--选中
				//计算总价
				var $total_prize = $("#total");
				$total_prize.html((parseFloat($total_prize.html())+parseFloat($prize.val())).toFixed(2));//赋值总价格
			}
			$checkbox.val($(this).closest("tr.product").attr("product_id")+"-"+$(this).prev().val()+"-"+$(this).closest("tr.product").attr("product_items_id"));
			//小计   单价乘以数量
			var dgl = $(this).prev().val();
			var $price_new = $prize.val();
			var mintotal = (parseFloat(dgl)*parseFloat($price_new)).toFixed(2);
			$(this).closest("tr.product").find(".min_total").html(mintotal);
			prodcutNumChange($(this).prev());
		}
		event.stopPropagation();

	});
	
	//减事件
	$(".cart_table").find(".product").find(".minus").click(function(){
		var num = $(this).next().val();
		if(Number(num)>1){
			$(this).next().val(Number(num)-1);
			//判断是否为选中状态的商品
			var $checkbox = $(this).closest("tr.product").find(".select").find("input");
			var $prize = $(this).closest("tr.product").find(".prize").find("input");
			if($checkbox.is(':checked')) {//是否选择--选中
				//计算总价
				var $total_prize = $("#total");
				$total_prize.html((parseFloat($total_prize.html())-parseFloat($prize.val())).toFixed(2));//赋值总价格
			}
			$checkbox.val($(this).closest("tr.product").attr("product_id")+"-"+$(this).next().val()+"-"+$(this).closest("tr.product").attr("product_items_id"));
			
			//小计   单价乘以数量
			var dgl = $(this).next().val();
			var $price_new = $prize.val();
			var mintotal = (parseFloat(dgl)*parseFloat($price_new)).toFixed(2);
			$(this).closest("tr.product").find(".min_total").html(mintotal);
			prodcutNumChange($(this).next());
		}
		event.stopPropagation();

	});
	
	//商品数量改变事件
	function prodcutNumChange(e){
		//".product.new input[name=\"product_counts\"]"
			var _tr = $(e).parent().parent().parent();
			var _product_id = _tr.attr("product_id");
			var _product_items_id = _tr.attr("product_items_id");
			var _change_num  =  $(e).val();
			var postdata = {"product_id":_product_id,"product_items_id":_product_items_id,"count":_change_num};
			console.log(postdata)
			$.post('/cart/modifycart.json',postdata,function(data){
				if(data.error)
				layer.alert(data.message);
				
			},'json');
	}
	
	//选择事件
	$(".cart_table").find(".product").on("click", function (){
		//选择数量
		var count = $(this).closest("tr.product").find(".number").val();
		var $total_prize = $("#total");
		var prize = $(this).closest("tr.product").find(".prize").find("input").val();

		if($(this).hasClass("new")) {//是否选择--选中
			$(this).removeClass("new");
			
			$total_prize.html((parseFloat($total_prize.html())-parseFloat(prize)*Number(count)).toFixed(2));//赋值总价格
			//将全选按钮设置为未选中
			$(this).find(":input:first").prop("checked", false).addClass("cart_new");
			$(this).find("#products").prop("checked",false);
			$(".selectall1").find("#selectall").prop("checked",false).removeClass("new");
			
		}else{
			//计算总价
			$(this).addClass("new").children().addClass("cart_new");
			$total_prize.html((parseFloat($total_prize.html())+parseFloat(prize)*Number(count)).toFixed(2));//赋值总价格
			$(this).find(":input:first").prop("checked",true);
			
			

			
			

			
		 }
	});

	
	
	//全选事件
	$(".selectall1").on("click", function(){
		var $total_prize = $("#total");
		var zj=0.00;
			if($(".selectall1").find("#selectall").hasClass("new")){//是否选择--选中
				//全部选中

				$(".cart_table").find(".product").removeClass("new");
				$(".cart_table").find(".product").find(".select").find("input").each(function(){
					
					
					
					
					$(this).prop("checked", false).closest("tr.product").children().removeClass("cart_new");;
				});
				
				//计算总价
				$(".selectall1").find("#selectall").prop("checked",false).removeClass("new");
				//计算总价
				$(".cart_table").find(".product").removeClass("new");
				
				$total_prize.html("0.00");//赋值总价格
				
			}else{
				$(".cart_table").find(".product").addClass("new");
				
				$(".selectall1").find("#selectall").prop("checked",true).addClass("new");
				
				$(".cart_table").find(".product").find(".select").find("input").each(function(){
				$(this).parent().parent().children().addClass("cart_new");
				var count = $(this).closest("tr.product").find(".count").find(".number").val();
				var prize = $(this).closest("tr.product").find(".prize").find("input").val();
				zj =parseFloat(zj)+parseFloat(prize)*Number(count);
				$(this).prop("checked", true);
				
			});
			
			//计算总价
				$total_prize.html(zj.toFixed(2));//赋值总价格
				}
		
	});
	
	//购物车移除
	$(".cart_table").find(".product").find(".delete").click(function(){
		var $this = $(this);
		var product_id = $this.closest("tr.product").attr("product_id");
		var product_items_id=$this.closest("tr.product").attr("product_items_id");
		var cart ={'product_id':product_id,'product_items_id':product_items_id};
		
		$.post('/cart/del.json',cart,function(data){
			if(data.error){
				layer.msg(data.message);
			}else{
				var $checked_ = $this.closest("tr.product").find(".select").find("input");
				if($checked_.is(':checked')) {//删除的商品是否被选中-选中
					var count = $this.closest("tr.product").find(".count").find(".number").val();
					
					var $total_prize = $("#total");
					var prize = $this.closest("tr.product").find(".prize").find("input").val();
					
					//计算总价
					$total_prize.html((parseFloat($total_prize.html())-parseFloat(prize)*Number(count)).toFixed(2));//赋值总价格
				 }
				//移除显示商品
				$this.closest("tr.product").remove();
				//判断购物车内是否有商品
				var $products_ = $(".cart_table").find(".product");
				if($products_.attr("product_id")==undefined){
					var htm_ = "<tr><td class=\"cart_table\" style=\"height:80px\" colspan=\"7\">~~啥都没有哦~~</td></tr>";
    				$("#contents").html(htm_);

				}
			}
		});
		event.stopPropagation();
	});
	
	//确认需求事件
    $(".cart_bottom").on("click",'.cart_btn',function(){
		var $total_prize = $("#total");
    	if($total_prize.text()=="0.00"){
    		layer.msg("没有选择任何的商品！");
    	}else{
	    	$("#carts").attr("action","order/gwcdd.html");//设置请求地址
	    	$("#carts").submit();
    	}
    });
});

//验证商品价格及数量
function validate(){
	var product_ids="";
	var product_items_ids="";
	var m=0;
	$(".cart_table").find(".product").each(function(){
		var product_id = $(this).attr("product_id");
		var product_items_id=$(this).attr("product_items_id");
		if(m==0){
			product_ids+=product_id;
			product_items_ids+=product_items_id;
		}else{
			product_ids+=","+product_id;
			product_items_ids+=","+product_items_id;
		}
		m++;
	});
	var items=product_items_ids.split(",");
	var tmpitem=0;
	for(var i=0;i<items.length;i++)
	{
		if(parseInt(items[i])>0)
			tmpitem=parseInt(items[i]);
	}
	if(tmpitem==0)
		product_items_ids="0";
	if(product_ids!=""){
		var carts = {'ids':product_ids,'subids':product_items_ids};
		$.post('/cart/validate.json',carts,function(data){
			if(data.error){
				layer.msg(data.message);
			}else{
				$(".cart_table").find(".product").find(".select").html("<span class=\"no\">失效</span>");
				$(".cart_table").find(".product").find(".count.on").hide();
				if(data.PRODUCTLIST.length>0){
					for(var i=0;i<data.PRODUCTLIST.length;i++){
						var $product = data.PRODUCTLIST[i];
						
						//订购量
						var dgl = $("#id_"+$product.id+"_"+$product.product_items_id).find(".count").find(".number").val();
						var dgl_ok=0;
						if($product.overplus>=dgl){//判断购买数是否大于库存
							dgl_ok=dgl;
						}else{
							dgl_ok = $product.overplus;
						}
						if($product.overplus>0){
							$("#id_"+$product.id+"_"+$product.product_items_id).find(".select").html("<input name=\"products\" type=\"checkbox\" value=\""+$product.id+"-"+dgl_ok+"-"+$product.product_items_id+"\"/>");
						}
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".prize").html("<input type=\"hidden\" value=\""+$product.price_new.toFixed(2)+"\"/>"+$product.price_new.toFixed(2));
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".prize_show").html("<input type=\"hidden\" value=\""+$product.price_old.toFixed(2)+"\"/>"+$product.price_old.toFixed(2));
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".count.off").hide();
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".count.on").show();
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".count.on").find(".number_max").val($product.overplus);
						if($product.overplus<dgl){//判断购买数是否大于库存
							$("#id_"+$product.id+"_"+$product.product_items_id).find(".count").find(".number").val($product.overplus);
							$("#id_"+$product.id+"_"+$product.product_items_id).attr("product_count",$product.overplus);
							dgl = $product.overplus;
						}
						var kczt = $product.overplus>=100?"库存充足":$product.overplus<=0?"缺货":"库存 "+$product.overplus;
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".kczt").html("<span>"+kczt+"</span>");
						var mintotal = (parseFloat(dgl)*parseFloat($product.price_new)).toFixed(2);
						$("#id_"+$product.id+"_"+$product.product_items_id).find(".min_total").html(mintotal);
					}
				}
			}
		});
	}
	
	
	
}

