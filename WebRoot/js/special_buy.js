$(document).ready(function(){
	//加载商品列表
	loadproducts();
	
    //类型点击事件
    $(".buy_search").on("click",".type_do",function(){
    	//改变选择信息
    	$(this).parent().parent().find(".pro_type_selectname").text($(this).text());
    	//改变选择的值
    	$("#typeid").val($(this).attr("tid"));
    	loadtypes($(this).attr("tid"));
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //品牌点击事件
    $(".buy_search").on("click",".brand_do",function(){
    	//改变选择信息
    	$(this).parent().parent().find(".pro_type_selectname").text($(this).text());
    	//改变选择的值
    	$("#brand").val($(this).attr("brand"));
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //销量点击事件
    $("#orderbyxl").click(function(){
    	$("#orderby").val(" out_count1 desc ");
    	$("#orderbyjg").removeClass("select");
    	$(this).addClass("select");
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //价格点击事件
    $("#orderbyjg").click(function(){
    	$("#orderby").val(" price_new asc ");
    	$("#orderbyxl").removeClass("select");
    	$(this).addClass("select");
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //搜索按钮事件
    $("#search_but").click(function(){
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //只显示有货点击事件
    $("#zxsyh").click(function(){
    	//加载商品----------------------------------------
    	loadproducts();
    });
    
    //加入购物车事件
    $(document).on("click",".add_cart",function(){
    	var product_id = $(this).attr("product_id");
    	var product_items_id = $(this).attr("product_items_id");
    	var cart ={'product_id':product_id,'count':1,'product_items_id':product_items_id};
    	$.post('/cart.json',cart,function(data){
			if(data.error){
				layer.msg(data.message);
			}else{
				if(data.count>0){
					layer.msg('加入采购车成功',{icon: 1,time: 1000,shade:0.1});
					loadCarts();//加载购物车数量
				}else{
					layer.msg("加入采购车失败，请重试！");
				}
			}
		});
    });
    
    selectbrand=function(e){	
    	$(e).parent().children().removeClass("brandcssmove");
    	$(e).parent().children().addClass("brandcss");	
    	$(e).removeClass("brandcss");
    	$(e).addClass("brandcssmove");	
    	$("#brand").val($(e).attr("type_id"));
    	loadproducts();

    };
    selectstringmap=function (e,type){
    	$(e).parent().children().removeClass("defaultli");	
    	$(e).addClass("defaultli");
    	if(type=="size"){
    		$("#size").val($(e).attr("sid"));
    		if($(e).attr("sid")=="size0"){    	
    			$("#size").val(0);
    		}
    	}
    	if(type=="color"){
    		$("#color").val($(e).attr("sid"));
    		if($(e).attr("sid")=="color0"){    			
    			$("#color").val(0);
    		}
    	}
    	if(type=="vision"){
    		$("#vision").val($(e).attr("sid"));
    		if($(e).attr("sid")=="vision0"){    			
    			$("#vision").val(0);
    		}
    	}
    	loadproducts();
    };
    
    
  //加载购物车数量
    function loadCarts(){
    	$.post('/user/getCartCount.json',function(data){
    		if(data.error){
//    			layer.msg(data.message);
    		}else{
    			$("#cartcount1").html(data.count);
    		}
    	});
    }
    
    //加载类型
    function loadtypes(parentid){
    	$("#child_types").html("");//情空之前加载的
	    	if(parentid!=0){
	    	$.post('/buy/gettypes.json?parentId='+parentid,function(data){
	    		if(data.error){
	    			layer.msg(data.message);
	    		}else{
	    			var PRODUCTTYPELIST_ = data.PRODUCTTYPELIST;
	    			if(PRODUCTTYPELIST_.length>0){
	    				var html_="<span class=\"fa fa-angle-right\"></span>"+
						  "<dl>"+
						  "  <dt><font class=\"pro_type_selectname\">请选择</font> <i class=\"fa fa-angle-down\"></i></dt>"+
						  "  <dd>"+
						  "    <a class=\"type_do\" tid=\""+parentid+"\">不限</a>"
	    				for(var i=0;i<PRODUCTTYPELIST_.length;i++){
	    					var PRODUCTTYPE = PRODUCTTYPELIST_[i];
	    					html_+="    <a class=\"type_do\" tid=\""+PRODUCTTYPE.id+"\">"+PRODUCTTYPE.name+"</a>";
	    				}
	    				html_+="  </dd>"+
						  		"</dl>";
	    				$("#child_types").append(html_);
	    			}
	    		}
	    	});
    	}
    }

 
    var pageIndex = 0;     //页面索引初始值
    var pageSize =8;     //每页显示条数初始化，修改显示条数，修改这里即可


  //加载商品入口
  function loadproducts(){
  	//显示加载层
  	layer.load();
  	var type = $("#typeid").val();
  	var brand = $("#brand").val();
  	var where = $("#where").val();
  	var size=$("#size").val();
  	var color=$("#color").val();
  	var vision=$("#vision").val();
  	var zxsyh = 0;
  	if($('#zxsyh').is(':checked')) {
  		zxsyh = 1;
  	}
  	var param ={"type":type,"brand":brand,"zxsyh":zxsyh,"where":where,"size":size,"color":color,"vision":vision};
      $.post("/buy/get_special_count.json",param,function(data){
      	$("#total").html(data.count);
      	//分页事件
          $("#pagination").pagination(data.count, {
              prev_text: "上一页",
              next_text: "下一页",
              //每页显示的条目数
              items_per_page: pageSize,
              //当前选中的页面
              current_page: pageIndex,
              //两侧显示的首尾分页的条目数。可选参数，默认是0
              num_edge_entries: 2,
              //连续分页主体部分显示的分页条目数。可选参数，默认是10
              num_display_entries: 8,
              //分页选择回调
              callback: pageSelectCallback
          });
      });
  }
  //加载商品信息-回调函数
  function pageSelectCallback(index, jq){
  	layer.closeAll();
  	layer.load();
  	$(".buy_plist").html("<div style=\"clear:both;\"></div>");//情空之前加载的
  	var type = $("#typeid").val();
  	var brand = $("#brand").val();
  	var orderby = $("#orderby").val();
  	var where = $("#where").val();
  	var size=$("#size").val();
  	var color=$("#color").val();
  	var vision=$("#vision").val();
  	var zxsyh = 0;
  	if($('#zxsyh').is(':checked')) {
  		zxsyh = 1;
  	}
  	var param ={"type":type,"brand":brand,"page_index":index,"zxsyh":zxsyh,"orderby":orderby,"where":where,"size":size,"color":color,"vision":vision};
  	$.post('/buy/get_special_list.json',param,function(data){
  		layer.closeAll('loading');
  		if(data.error){
  			layer.msg(data.message);
  		}else{
  			var PRODUCTLIST_ = data.PRODUCTLIST;
  			if(PRODUCTLIST_.length>0){
  				var html_="";
  				for(var i=0;i<PRODUCTLIST_.length;i++){
  					var PRODUCT = PRODUCTLIST_[i];
  					var subtitle = PRODUCT.sub_title==null?"&nbsp;":PRODUCT.sub_title;
  					var kc = PRODUCT.overplus>=100?"库存充足":PRODUCT.overplus<=0?"缺货":"库存 "+PRODUCT.overplus;
  					html_+="<li>"+
  						   "     <em><a target=\"_blank\" href=\"/buy/special_detail.html?ID="+PRODUCT.id+"&ITEM="+(PRODUCT.product_items_id==null?0:PRODUCT.product_items_id)+"\"><img src=\"http://img.tulingbuy.com"+PRODUCT.img_url+"\" onerror=\"nofind(this)\"/></a></em>"+
  						   "     <strong>￥"+toDecimal2(PRODUCT.price_new)+"" +
  						   		//"<u>"+yj+"</u>" +
  						   "	 </strong>"+
  						   "     <span title="+PRODUCT.name+PRODUCT.pz+">"+PRODUCT.name+PRODUCT.pz+"</span>"+
  						   "     <u>&nbsp;"+subtitle+"</u>"+
  						   "     <p>"+
  						   "       <b>"+kc+"</b>";
  					if(kc!='缺货'){
	  					if(PRODUCT.is_ms==0){  
	  					html_+="       <a class=\"add_cart\" product_id=\""+PRODUCT.id+"\" product_items_id=\""+PRODUCT.product_items_id+"\"><i class=\"fa fa-cart-plus\"></i>加入采购车</a>";
	  					}else{
	  						html_+="       <a href=\"/buy/buy_show.json?ID="+PRODUCT.id+"\">立即抢购</a>";	
	  					}
  					}else{
  						html_+="       <a style=\"color:grey;\" href=\"javascript:void(0)\"><i class=\"fa fa-cart-plus\"></i>暂时缺货</a>";
	  					
  					}
  					html_+="     </p>"+
  						   "</li>";
  				}
  				$(".buy_plist").append(html_);
  			}
  		}
  	});
  }
});
//图片加载失败
function nofind(img){
	img.src="/images/no_pic.png";
	img.onerror=null; //控制不要一直跳动
} 

//保留2位小数
function toDecimal2(x) { 
    var f = parseFloat(x); 
    if (isNaN(f)) { 
      return false; 
    } 
    var f = Math.round(x*100)/100; 
    var s = f.toString(); 
    var rs = s.indexOf('.'); 
    if (rs < 0) { 
      rs = s.length; 
      s += '.'; 
    } 
    while (s.length <= rs + 2) { 
      s += '0'; 
    } 
    return s; 
  } 

