$(document).ready(function(){
	//加载商品列表
	loadorderproducts();
	
	
    //加载商品信息
    function loadorderproducts(){
    	$(".load_product").each(function(){
    		var $this = $(this);
	    	var param ={"order_number":$this.attr("order_number")};
	    	$.post('/service/getorderproduct.json',param,function(data){
	    		if(data.error){
	    			layer.msg(data.message);
	    		}else{
	    			var ORDERDETAILSLIST_ = data.ORDERDETAILSLIST;
	    			if(ORDERDETAILSLIST_.length>0){
	    				var html_="";
	    				for(var i=0;i<ORDERDETAILSLIST_.length;i++){
	    					var PRODUCT = ORDERDETAILSLIST_[i];
	    					html_+=" <tr>"+
						           "   <td style=\"border-right:none;width:52px;\"><span><img class=\"pro_img\" src=\"http://img.tulingbuy.com"+PRODUCT.product_imgurl+"\" width=\"50\" height=\"50\" onerror=\"nofind(this)\"></span></td>"+
						           "   <td style=\"border-right:none;width:250px;\"><span>"+PRODUCT.product_name+"</span></td>"+
						           "   <td style=\"border-right:none;width:70px;\"><span>"+PRODUCT.type_str+"</span></td>"+
						           "   <td style=\"border-right:none;width:70px;\"><span>"+PRODUCT.brand_str+"</span></td>"+
						           "   <td style=\"border-right:none;width:100px;\"><span>"+PRODUCT.xh+"</span></td>"+
						           "   <td style=\"border-right:none;\"><span><a  style=\"color:red;\" href=\"/service/toadd.html?order_number="+$this.attr("order_number")+"&order_detail_id="+PRODUCT.id+"\">申请</a></span></td>"+
						           " </tr>";
	    				}
	    				$this.html(html_);
	    			}
	    		}
	    	});
    	});
    }
});

//图片加载失败
function nofind(img){
	img.src="/images/no_pic.png";
	img.onerror=null; //控制不要一直跳动
} 
