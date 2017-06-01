
var productitems=[];
var select_size;
var select_color;
var select_peizhi;
var ptypes=[];
$(document).ready(function(){
	//填充顶部模块
	getTopModule();
	//获取静态参数
	//getStatic();
	//切换顶部样式
	if(window.location.pathname=="/"){
		changeHometop();
		$(".home_title").css({"background": "#C22F27","color": "#fff" });	   
		$(".home_title").find("li").find("a").css({"color": "#fff" });
		$(".companynumber").css({"color": "#fff" });
	}
	if(window.location.pathname=="/buy.json"){
		$("#messageforself").show();	
		$("#buyforcart").hide();
	}
	
	/************************************************签到相关***************************/
	//初始化日历
//	setCalendar();
	
	$(".menuson li").click(function() {
		$(".menuson li.active").removeClass("active");
		$(this).addClass("active");
	});

	//页面加载时检查是否已经签到
//	$.ajax({
//     url:"/sign/docheck.html",
//     data:{},
//     type:"post",
//     async:true,
//     dataType:"json",
//     success:function(data){
//     getData(data.history);
//     if(data.complete!=null)
//     	changestyle($("#signin"));
//     if(data.integration!=null)
//     document.getElementById("integration").innerHTML=data.integration;
//      if(data.sign_count!=null)
//     document.getElementById("sign_count").innerHTML=data.sign_count;
//     },
//     error:function(){
//    	 console.log("签到服务连接失败！");
//     }
//     
//     });
	//用户头像加载错误事件
	$("img.head_img_").on("error",function(){
		$(this).attr("src","/images/default_img.png");		
	});
	
	//控制用户详情弹出层
	 $("#sub_head").mouseover(function(){
         $(".head_sub").show();
     }).mouseout(function(){
         $(".head_sub").hide();
     });

	
	var left = 960-document.body.scrollWidth/2;
	 /*****************************************企业采购menu初始化***************************************************/
	  var firstTypeFlag=true;
	  var tmpparentid="1";
	  var subparentid;
	  var tmphtml="";
	  $.post('/menu.json',function(data){
		  console.log(data);
	  		if(data.error){
	  			layer.msg(data.message);
	  		}
	  		else
	  		{	
	  			var temp = "";
	  			ptypes=data.PRODUCTTYPELIST;
	  			//填充企业采购菜单
	  			for(var i=0;i<ptypes.length;i++)
	  		  {
		  			  var itList;
		  			  var p=ptypes[i];
		  			  if(p.id!=""){		  				  
				  			  if(p.parentid=="0")
				  			  {
				  				if(firstTypeFlag){
				  					if($("#firsttype").length>0){
				  						$("#firsttype").html(p.name);
				  					}
				  				}
				  				firstTypeFlag=false;
				  				var tmpname=p.name;
				  			    tmpname='<i style="font-weight:normal"  class="'+p.iconfont+'" style="margin-right:5px"></i>'+p.name;
				  				$("#productList").append('<li ><a  href="/buy.json?typeid='+p.id+'">'+tmpname+'</a><div class="top_sub_div" style="display:none;width:700px;"><ul id="root_'+p.id+'"></ul></div></li>');
				  				  
				  			  }
				  			  else if( p.product_level == "2") 
				  			  {	//二级类	  				  
			  					      tmphtml="";
			  						  tmphtml+='<li><a href="/buy.json?typeid='+p.parentid+","+p.id+'"><span style="display:inline-block;width:175px;padding-right:10px;font-weight:bold;text-align:right;font-size:13px;">'+p.name+" > "+'</span></a><span id="sub_'+p.id+'"   style="width:510px;float:right;"></span></li>';  					  	 
				  					  $("#root_"+Number(p.parentid)).append(tmphtml);
				  				  
				  			  }
				  			 else if( p.product_level == "3") 
				  			  {	//三级类
				  				  var tmpli="<a href='/buy.json?typeid="+p.parentid+","+p.sub_parentid+","+p.id+"'>"+p.name+"</a>";
				  				
								  $("#sub_"+Number(p.sub_parentid)).append(tmpli);
				  			  }
		  		  }
	  			  
	  		  }
	  			$("#productList").append('<li id="root_999">&nbsp;</li>'); 	  	
	  			 controlMenu();
	  		}});
	
	  	controlMenu();
	  	//企业内购菜单
	  	(function(){
	  		
	  		$.post('/special_menu.json',function(data){
		  		if(data.error){
//		  			layer.msg(data.message);
		  		}else
		  		{	
		  			var temp = "";
		  			var ptypes=data.specialTypeList;
		  			//填充企业采购菜单
		  			for(var i=0;i<ptypes.length;i++)
		  		  {
		  			  var itList;
		  			  var p=ptypes[i];

		  			  if(p.parentid=="0")
		  			  {
		  				  var tmpname=p.name;
		  				  if(p.id=='1')
		  					  tmpname='<i style="font-weight:normal" class="iconfont icon-diannao" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='2')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-OAbangong" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='3')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-wangluo" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='4')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-anzhuangbaoguanli" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='14')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-qita" style="margin-right:5px"></i>'+p.name;

		  				  if(p.id=='5')
		  					  tmpname='<i class="iconfont icon-weixiu" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='272')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-riyongbaihuo" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='326')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-shenghuodianqi" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='336')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-shuma" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='100')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-bangongshebei" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='378')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-gongju" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='179')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-bangongyongpin" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='465')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-jidian" style="margin-right:5px"></i>'+p.name;
		  				  if(p.id=='236')
		  					  tmpname='<i style="font-weight:normal"  class="iconfont icon-bangongshi-copy" style="margin-right:5px"></i>'+p.name;
		  				  $("#specialList").append('<li ><a  href="/buy/special_list.html?typeid='+p.id+'">'+tmpname+'</a><div class="top_sub_div" style="display:none;width:700px;"><ul id="special_'+p.id+'"></ul></div></li>');
		  				  
		  			  }
		  			  else if( p.product_level == "2") 
		  			  {	//二级类	  				  
	  					      tmphtml="";
	  						  tmphtml+='<li><a href="/buy/special_list.json?typeid='+p.parentid+","+p.id+'"><span style="display:inline-block;width:175px;padding-right:10px;font-weight:bold;text-align:right;font-size:13px;">'+p.name+" > "+'</span></a><span id="special_sub_'+p.id+'"   style="width:510px;float:right;"></span></li>';  					  	 
		  					  $("#special_"+Number(p.parentid)).append(tmphtml);
		  				  
		  			  }
		  			 else if( p.product_level == "3") 
		  			  {	//三级类
		  				  var tmpli="<a href='/buy/special_list.json?typeid="+p.parentid+","+p.sub_parentid+","+p.id+"'>"+p.name+"</a>";
		  				
						  $("#special_sub_"+Number(p.sub_parentid)).append(tmpli);
		  			  }
		  		  }	  	
		  		}
		  		controlMenu();	
	  		});
	  	})();
	
	  
	          
        
		/****************************图片轮播*******************************************/
	    var carousel = null;
	    var sum = $(".home_banner>a").length;
	    var timer=0;
	    $(".carousel_nav i").click(function(){
	        $(this).addClass("active").siblings().removeClass("active");
	        var i_mun1=$('.carousel_nav i').index(this);
	        $('.home_banner>a').eq(i_mun1).css({"display":"block","opacity":"0.1"}).animate({opacity:1},1000).siblings('a').css("display","none");
	        timer=i_mun1;
	    });
	    function bannerMove(){
	        carousel=setInterval(function(){
	            scroll()
	        },6000)
	    }
	    bannerMove();
	    $('.home_banner').mouseover(function(){
	        clearInterval(carousel);
	    }).mouseout(function(){
	        bannerMove();
	    });
	    function scroll(){
	        if(timer==sum-1){
	            timer=-1
	        }
	        $('.home_banner>a').eq(timer+1).css({"display":"block","opacity":"0.1"}).animate({opacity:1},1000).siblings('a').css("display","none");
	        $('.carousel_nav i').eq(timer+1).addClass('active').siblings('i').removeClass('active');
	        timer++;
	    }
	
	function controlMenu(){		
		//一级菜单栏展开
        $("#home_topnav>li").mouseenter(function(){
            $(this).children("div").show(); 
        }).mouseleave(function(){ 
            $(".top_sub").hide();
        });
        
        var rootid ;
        //二级菜单栏展开
        $(".top_sub>ul>li").mouseenter(function(){
            $(this).children("div").show();
            rootid = $(this).find('ul:first').attr('id');
           var _height = (Number($(this).parent().parent().height())-20)+"px";
            $(".top_sub_div").css('min-height',_height);
           $(this).children('a').css({'background-color':'#999395'});
        }).mouseleave(function(){
            $(".top_sub_div").hide();
            $(this).children('a').css({'background-color':'#6C6669'});
        });
		
	};
	

	  //更新购物车数量
		//loadCarts();
		 function loadCarts(){
/*			 
			 if(!!$("#cartcount1"))*/
		    	$.post('/user/getCartCount.json',function(data){
		    		if(data.error){
		    			//layer.msg(data.message);
		    		}else{
		    			/*$("#cartcount1").html(data.count);*/
		    			$("#buyforcart").find("a").append('<div id="cartcount1">'+data.count+'</div></a>');
		    		}
		    	});
		    	
		    }

});
 	//样式切换
 	function changeHometop(){			
 			var left = 960-document.body.scrollWidth/2;
   	  	$(".home_banner a").css("left",-left);
	    $(".home_top").mouseenter(function(){
	        $(this).css({"background":"#D83E36","color":"#FFFFFF"});
	        $("#home_topnav>li").children("a").css("color","#FFFFFF");
	        $(".signin").css({"background":"#DE443C"}).children().css("color","#FFFFFF");
	    }).mouseleave(function(){
	        $(this).css({"background":"#FFFFFF","color":"#373737"});
	        $("#home_topnav>li").children("a").css("color","#373737");
	        $(".signin").css({"background":"#EDEDED"}).children().css("color","#373737");
	    });
	    $(".home_banner a").css("left",-left);
 	}
function getTopModule(){
	$.post('/module/queryList.json',{"type":1},function(data){
		  var topModuleStr=[];
          for(var i=0;i<data.length;i++){
        	  if(data[i]["belongto_tf"]!=2)
        	  topModuleStr.push('<li><a href="'+data[i]["module_url"]+'">'+data[i]["module_name"]+'</a></li>');
          }
          $("#home_topnav").append(topModuleStr.join(""));
	});
}

 /*************************************ready事件截止**********************************************/
// 
//   //签到样式修改
//    function changestyle(doc){
//        $(doc).css({"background":"#F28E8C"});
//        $(doc).removeAttr('onclick');
//        document.getElementById('signin').innerHTML = '已签到';
//        $("#signinsub").attr("index","1");
//
//       
//    }
//    //签到ajax
//    function signUp(){
//    	 $.ajax({
//     		url:"/sign/dosign.html",
//     		data:{"type":"10"},
//    		 type:"post",
//    		 dataType:"json",
//    		 success:function(data){
//     			layer.alert(data.message);
//     	if(data.success!=null||data.warning!=null){
//     		changestyle($("#signin"));
//     		if(data.integration!=null)
//     document.getElementById("integration").innerHTML=data.integration;
//      if(data.sign_count!=null)
//     document.getElementById("sign_count").innerHTML=data.sign_count;
//     		getData(data.history);
//     		
//     		
//     	}
//     },
//    	 error:function(){
//    	 layer.msg("签到异常！");
//    		 }
//     
//     	});
//     
//     };
//     
//  	//填充签到历史
//    function getData(history) {
//        if(history!=null && history!=""){
//        	var arr = history.split('');
//	        for (var i = 0; i < arr.length; i++) {
//	            if (arr[i] == "1") {
//	                   var datelist = document.getElementsByName("date");
//	                   datelist[i].style.background = "#F28E8C";
//	                   datelist[i].style.color = "#fff";
//	                  
//	            }
//	        }
//        }
//
//    }
//    
//    
//  //初始化日历控件
//  function setCalendar() {
//        //var date = new Date(date_selected);//year,month,day均为所选择的年月日
//        var date = new Date();
//        var year = date.getFullYear();
//        var month = date.getMonth() + 1;
//        month = month < 10 ? ('0' + month) : month;
//        var startDay = new Date(year, month - 1, 1).getDay();//本月第一天是星期几   0为周日   6为周六
//        if (startDay == 0) {
//            startDay = 7;
//        }
//        var nDays = new Date(year, month, 0).getDate();//本月天数  下个月的第0天即为 本月最后一天
//        var lastDays = new Date(year, month - 1, 0).getDate();//上月天数 
//        firstdate = new Date(year, month - 1, 1);//按月查询开始
//        enddate = new Date(year, month, 0, 24);//按月查询结束
//        var i = 0;
//        var n = 1;//本页所有天数 用来计算是否到第7天，是则换行
//        var num = 1;//已填写到本月的第几天的记录
//        var str = "";
//        str += "<tr>";
//        for (i = startDay - 1; i > 0; i--) {//上月的记录
//            var lastDate = lastDays - i;
//           	str += "<td>"
//              + "</td>";
//
//            if (n == 7 || n == 0) {
//                str += "</tr><tr>";
//            }
//            n++;
//        }
//        for (num = 1; num <= nDays; num++) {//本月的记录      
//           	str += "<td name='date' >"
//                     + num 
//               + "</td>";
//
//            if (n % 7 == 0) {
//                str += "</tr>";
//            }
//            n++;
//        }
//        $("#my_tbody").html(str);
//        $("#bubblemenu").css({"height":"30px"});
//        $("#my_tbody td").css({"width":"30px","height":"30px","text-align":"center"});
//		$("#my_tbody tr").css({"height":"30px"});
//		$("table th").css({"height":"30px"});
//    
//    }