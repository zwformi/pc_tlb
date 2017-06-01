var GetAllData;
var productitems=[];
var select_attr=[{"select_key1":"","select_attr1":""},{"select_key2":"","select_attr2":""},{"select_key3":"","select_attr3":""},{"select_key4":"","select_attr4":""},{"select_key5":"","select_attr5":""},{"select_key6":"","select_attr6":""},{"select_key7":"","select_attr7":""},{"select_key8":"","select_attr8":""}];
var ptypes=[];
var item_attr_all=[];
var First_Click;
var qureyString;
var AttrTp="<div class='right_lists {3}'>"+
"<dt>{0}</dt>"+
"<ul id='{1}'>{2}"+	    			    	
"</ul></div>"; 
String.prototype.format = function (args) {
var result = this;
if (arguments.length > 0) {
    if (arguments.length == 1 && typeof (args) == "object") {
        for (var key in args) {
            if (args[key] != undefined) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
    }
    else {
        for (var i = 0; i < arguments.length; i++) {
            if (arguments[i] != undefined) {
                //var reg = new RegExp("({[" + i + "]})", "g");//
                var reg = new RegExp("({)" + i + "(})", "g");
                result = result.replace(reg, arguments[i]);
            }
        }
    }
}
return result;
}
$(document).ready(function(){
	 if(!GetAllData || GetAllData.length == 0) 
		 {
		   if($("#count").length>0){
			   $("#count").val(1);
		   }
           return;
		 }
	 else{
		 if(getQueryString("ITEM"))
			 {
			   var item=getQueryString("ITEM");
			   console.log(JSON.stringify(GetAllData));
			   for(var i=0;i<GetAllData.length;i++)
				  {
					  var Data=GetAllData[i][0]["Att"];
					  var item_id=GetAllData[i][1]["product_items_id"];
					  if(item_id==item)
						  {
							  $.each(select_attr,function(i,value){
								  select_attr[i]['select_key'+(i+1)]=Data['key'+(i+1)];
								  select_attr[i]['select_attr'+(i+1)]=Data['value'+(i+1)];	
			                  });
						  }
				  }
			   /*dataReady();*/
			 }
		 
	 }
	  dataReady();
	  function dataReady(){ 
		  var TplArray=[];		
		  for(var i=0;i<GetAllData.length;i++)
		  {
			  var Data=GetAllData[i][0]["Att"]; 
			  if(i==0)
				  {
				     for(var z=1;z<=8;z++)
				    	 {
				    	   if(Data["key"+z]!="")
				    		   {
				    		      select_attr[z-1]['select_key'+z]=Data['key'+z];
				    		      var Tplex={};
				    		      Tplex.t=Data["key"+z];
				    		      Tplex.id="product_attr"+z;
				    		      if(Data["value"+z]!="")
				    		    	  {
				    		    	    Tplex.value_all=[{"val":$.trim(Data["value"+z]),"ishas":1}];
				    		    	    
				    		    	  }
				    		      TplArray.push(Tplex);
				    		   }
				    	 }
				  }
			  else
				  {
				  for(var z=1;z<=8;z++)
			    	 {
					  var Tplex={};
					  console.log(TplArray.length);
					  if(z<=TplArray.length)
						  {
						  Tplex.t=TplArray[z-1]["t"];
		    		      Tplex.id=TplArray[z-1]["id"];
		    		      /*Tplex.li=TplArray[z-1]["li"];*/
		    		      Tplex.value_all=TplArray[z-1]["value_all"];
		    		      
		    		      
		    		      var flag=false;
		    		      $.each(Tplex.value_all,function(i,value){
		    		    	  if(Data["value"+z]==value.val)
		    		    		  {
		    		    		    flag=true;
		    		    		    return false;
		    		    		  }
		    		      })
		    		      if(!flag)
		    		    	  {
		    		    	   TplArray[z-1]["value_all"].push({"val":$.trim(Data["value"+z]),"ishas":1});
		    		    	   
		    		    	  }
						  }

	    		      
			    	 }				  
				  }
		  }
		/*  console.log(JSON.stringify(TplArray));*/
		  item_attr_all=TplArray;
		  if(TplArray.length>0&&GetAllData.length>2)
		  {
          created_item(TplArray);
          var First_item_ul=$("#item_content").find(".right_lists").eq(0).find("ul").find("li");
	          First_item_ul.each(function (i) {
	        	  if($(this).hasClass("list_Selected0"))
	        		  {
	        		   First_Click=$(this);
	        		   return false;
	        		  }
	          })
          if(!getQueryString("ITEM"))
          selectThis(First_Click)
          else
        	  {
        	    changed_state();
        	  }
	     }
		  else
		 {
			  changed_state();
			  }
	  }
	  
	  
	  $.ajaxSetup({
		    async : false
		});	      
	  var tmpparentid="1";
	  var tmphtml="";
	  $.post('/menu.json',function(data){
	  		if(data.error){
	  			layer.msg(data.message);
	  		}else
	  		{
	  			ptypes=data.PRODUCTTYPELIST;
	  			for(var i=0;i<ptypes.length;i++)
	  		  {
	  			  var p=ptypes[i];
	  			  if(p.parentid=="0")
	  			  {
	  				  var tmpname=p.name;
	  				  if(p.id=='1')
	  					  tmpname='<i class="iconfont icon-diannao" style="margin-right:5px"></i>'+p.name;
	  				  if(p.id=='2')
	  					  tmpname='<i class="iconfont icon-OAbangong" style="margin-right:5px"></i>'+p.name;
	  				if(p.id=='3')
	  					  tmpname='<i class="iconfont icon-wangluo" style="margin-right:5px"></i>'+p.name;
	  				if(p.id=='4')
	  					  tmpname='<i class="iconfont icon-anzhuangbaoguanli" style="margin-right:5px"></i>'+p.name;
	  				if(p.id=='5')
	  					  tmpname='<i class="iconfont icon-weixiu" style="margin-right:5px"></i>'+p.name;
	  				if(p.id=='14')
	  					  tmpname='<i class="iconfont icon-qita" style="margin-right:5px"></i>'+p.name;
	  				  $(".clearfix").append('<li class="J_MenuItem"><p class="itemCol"><a href="/buy.json?typeid='+p.id+'">'+tmpname+'</a></li>');
	  			  }
	  			  else
	  			  {
	  				  if(p.parentid==tmpparentid)
	  				  {
	  					  if(tmphtml=="")
	  					    tmphtml='<div class="entity-main"><ul class="shadow-left"><li>热门推荐</li><li><a href="/buy.json?typeid='+p.parentid+','+p.id+'">'+p.name+'</a>';
	  					  else
	  					  {
	  						  tmphtml+='<a href="/buy.json?typeid='+p.parentid+','+p.id+'">'+p.name+'</a>';
	  					  }					  
	  				  }
	  				  else
	  				  {
	  					  tmpparentid=p.parentid;
	  					  tmphtml+='</li></ul><dl class="shadow-right"><dt><b>品牌 </b></dt><dd><img src="/upload/product_img/type1.jpg"/><img src="/upload/product_img/type2.jpg"/><img src="/upload/product_img/type3.jpg"/><img src="/upload/product_img/type5.jpg"/><img src="/upload/product_img/type7.jpg"/><img src="/upload/product_img/type8.jpg"/><img src="/upload/product_img/type10.jpg"/><img src="/upload/product_img/type11.jpg"/><img src="/upload/product_img/type12.jpg"/><img src="/upload/product_img/type13.jpg"/></dd></dl></div>';
	  					  $(".shadow").append(tmphtml);
	  					  tmphtml='<div class="entity-main"><ul class="shadow-left"><li>热门推荐</li><li><a href="/buy.json?typeid='+p.parentid+','+p.id+'">'+p.name+'</a>';				  
	  				  }
	  			  }
	  			  if(i==ptypes.length-1)
	  			  {
	  				  tmphtml+='</li></ul><dl class="shadow-right"><dt><b>品牌 </b></dt><dd><img src="/upload/product_img/type1.jpg"/><img src="/upload/product_img/type2.jpg"/><img src="/upload/product_img/type3.jpg"/><img src="/upload/product_img/type5.jpg"/><img src="/upload/product_img/type7.jpg"/><img src="/upload/product_img/type8.jpg"/><img src="/upload/product_img/type10.jpg"/><img src="/upload/product_img/type11.jpg"/><img src="/upload/product_img/type12.jpg"/><img src="/upload/product_img/type13.jpg"/></dd></dl></div>';
	  				  $(".shadow").append(tmphtml);
	  			  }
	  		  }
	  		}});
	  
	  
	  		  

	  
	  
	  
	  $(".menu_type").each(function(){
			$( this ).mouseover( function(){			
				var catTop,
					borderTop = $( this ).offset().top,
					borderLeft= $( this ).offset().left,
					viewHeight = $( window ).height(),
					scrollTop = $( document ).scrollTop(),
					relaxHeight = viewHeight - ( borderTop - scrollTop );
				$( ".mallCategory" ).css( "position","absolute");
				$( ".mallCategory" ).css( "left",borderLeft);
				$( ".mallCategory" ).css( "border","1px solid #D74340");
				
				$( ".mallCategory" ).css( "top", borderTop+30 ).show();
			});
			
		   }		
		
		);
		$(".shadow .entity-main" ).each(function(index){		
			$( this ).mouseover( function(){	
				$( ".J_MenuItem" ).eq(index).addClass("movelist");			
				
			});
			$( this ).mouseleave( function(){	
				
				$( ".J_MenuItem" ).eq(index).removeClass("movelist");
				$( ".J_MenuItem" ).eq(index).addClass("movelist1");
			});
		}
		);
		$( ".J_MenuItem" ).each( function( index ){
			$( this ).mouseover( function(){			
				var catTop,
					borderTop = $( this ).offset().top,
					viewHeight = $( window ).height(),
					scrollTop = $( document ).scrollTop(),
					relaxHeight = viewHeight - ( borderTop - scrollTop );			
				
				$( ".cat-subcategory" ).show();
				$( ".shadow div" ).hide().eq( index ).show();			
				$( ".cat-subcategory" ).css( "top", (borderTop-131)-index*38 );			
				$( "span" ).show();
				$( this ).find( "span" ).hide();
			});
			
			
			$( ".mallCategory" ).mouseleave( function(){			
				$( ".cat-subcategory" ).hide();			
				$( "span" ).show();
				$(".mallCategory").hide();
			});

		});
		loadCarts();
		 function loadCarts(){
		    	$.post('/user/getCartCount.json',function(data){
		    		if(data.error){
//		    			layer.msg(data.message);
		    		}else{
		    			$("#cartcount1").html(data.count);
		    		}
		    	});
		    }
  

});

function created_item(arr){
	
	$("#item_content").empty();
    
	  $.each(arr,function(i,value){
		  var  att_all=value.value_all;
		  value.at_i="at_"+(i+1);
		  var lis="";
		  if(select_attr[i]["select_attr"+(i+1)]!="")
		  {
			  
			  var v=select_attr[i]["select_attr"+(i+1)];			 
			  $.each(att_all,function(j,value){
				  if(value["val"]==v&&value["ishas"]==0)
					  {
					     select_attr[i]["select_attr"+(i+1)]="";
					  }
			  })
		  }
		  for(var z=0;z<att_all.length;z++){

			  if(att_all[z]["ishas"]==0)
				  {
				     lis+='<li id="size_attr'+i+'_'+z+'" class="not_allowed">'+$.trim(att_all[z]["val"])+'</li>';
				  }
			  else{
				  if(select_attr[i]["select_attr"+(i+1)]=="")
				  {  
				    select_attr[i]["select_attr"+(i+1)]=att_all[z]["val"];
				  }

				  var select="";
				  if(select_attr[i]["select_attr"+(i+1)]==att_all[z]["val"])
					  {
					    select="list_Selected0";
					  }
/*				  console.log(select_attr[i]["select_attr"+(i+1)]);*/
				     lis+='<li id="size_attr'+i+'_'+z+'" class="'+select+'" onclick="selectThis(this)">'+$.trim(att_all[z]["val"])+'</li>';  
			  }
		  } 
		  value.li=lis;
		  var template= AttrTp.format(value.t,value.id,value.li,value.at_i);
		  $("#item_content").append(template);
	  })
	
	
}
function changed_state(){
	var flag=false;
	for(var i=0;i<GetAllData.length;i++)
	  {
	     var Data=GetAllData[i][0]["Att"];
	     var Other=GetAllData[i][1];
	    	   if(Data["value1"]==select_attr[0]["select_attr1"]&&
	    		  Data["value2"]==select_attr[1]["select_attr2"]&&
	    		  Data["value3"]==select_attr[2]["select_attr3"]&&
	    		  Data["value4"]==select_attr[3]["select_attr4"]&&
	    		  Data["value5"]==select_attr[4]["select_attr5"]&&
	    		  Data["value6"]==select_attr[5]["select_attr6"]&&
	    		  Data["value7"]==select_attr[6]["select_attr7"]&&
	    		  Data["value8"]==select_attr[7]["select_attr8"])  
	    		   {
	    		    flag=true;
	    		    var new_title="";
	    		    $.each(select_attr,function(j){
	    		    	if(select_attr[j]["select_attr"+(j+1)]!="")
	    		    		{
    		    		     var dian=(j==0?"":",");
                             new_title+=dian+select_attr[j]['select_key'+(j+1)]+":"+select_attr[j]["select_attr"+(j+1)];
	    		    		}
	    		    	})
	    		    $("#count").val(1);
		  			$("#countname").html(Other.stocks);
	  				$("#sale_price").html("￥"+((Other.product_saleprice)*1).toFixed(2));
	  				if(new_title!="")
	  					{
	  					$("#p_title").html(Other.itemname+"("+new_title+")");
	  					}	
	  				$("#addcart").attr("product_items_id",Other.product_items_id);
	    		   
	    		   }
	    	   if(flag) return false;

	  }
	  if(!flag)
		  {
		   $.each(select_attr,function(j){
		    	if(select_attr[j]["select_attr"+(j+1)]!="")
		    		{
		    		  select_attr[j]["select_attr"+(j+1)]="";
		    		}
		    		});
		   selectThis(First_Click);
		  }
	
	
}
function reset_zero(arr,type){
	$.each(arr,function(i,value){
        for(var z=0;z<value.value_all.length;z++)
       	 {
        	value.value_all[z].ishas=type;
       	 }
       	 })
	return arr;
}

function selectThis(sl){
	  var this_val=$(sl).html();
	  var this_key_index=$(sl).parent("ul").attr("id").replace("product_attr", "");
	  select_attr[this_key_index-1]["select_attr"+(this_key_index)]=this_val;
	  var arr=reset_zero(item_attr_all,0);
	  for(var i=0;i<GetAllData.length;i++)
		  {
		     var Data=GetAllData[i][0]["Att"];
		     var flag=false;
		     if(Data["value"+this_key_index]==this_val)
		    	 {
		    	  $.each(arr,function(i,value){
                     for(var z=0;z<value.value_all.length;z++)
                    	 {
                    	   var k=i+1;
                    	   if((value.value_all[z].val==Data["value"+k])||this_key_index==k)
                    		   {
                    		     value.value_all[z].ishas=1;  
                    		   }                    	   
                    	 }
		    	  })
		    	 }
		     
		  }
	      created_item(item_attr_all);	      
	      changed_state();	  
}

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
