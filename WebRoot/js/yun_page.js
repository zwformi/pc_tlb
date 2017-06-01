
var productitems=[];
var select_size;
var select_color;
var select_peizhi;
var ptypes=[];


//详情相关变量
var typeGroup=["CRM","OA","ERP"];
var titleGroup=["CRM系统","OA系统","ERP系统"];
var textGroup=["通过强大的消息访问工具，提供随时随地访问功能，提高员工的工作效率：用户可以使用Microsoft Office Outlook®, Outlook Web Access、型号众多的移动设备甚至是普通的电话等自己早已熟悉的客户端产品随时随地以自己希望的方式访问统一消息提供的丰富的功能。","13e21412","1242424"];
var bgGroup=["/images/text1.jpg","/images/text2.jpg","/images/text3.jpg"];
var grouplist = "";

//浏览器判断
function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var isIE11 = userAgent.indexOf("rv:11.0")>-1; //是否ie11
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
    var isChrome = userAgent.indexOf("Chrome") > -1; //判断是否Chrome浏览器
    if (isIE) {
        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE55 = fIEVersion == 5.5;
        IE6 = fIEVersion == 6.0;
        IE7 = fIEVersion == 7.0;
        IE8 = fIEVersion == 8.0;
        if (IE55) {
            return "IE55";
        }
        if (IE6) {
            return "IE6";
        }
        if (IE7) {
            return "IE7";
        }
        if (IE8) {
            return "IE8";
        }
    }//isIE end
    else if (isFF) {
        return "FF";
    }
    else if (isOpera) {
        return "Opera";
    }
    else if (isChrome) {
        return "Chrome";
    }
    else if (isIE11) {
        return "IE11";
    }else{
    	return "else";
    }
}
//填充左侧目录
for(var i=0;i<typeGroup.length;i++){
	grouplist +="<li><a group_id=\""+i+"\">"+typeGroup[i]+"</a></li>";
}
$(document).ready(function(){
	/************************************************详情相关***************************/

		$(".group").html(grouplist);
		//默认点击第一个
		clickli($(".group").find("li:first").find("a"));
		
		$(".group li").children().click(function(){
			clickli($(this));
		});
		
		//填充示例图片
		
		//点击列表的方法
		function clickli(e){
			if(myBrowser()!=8){
				$(".background").animate();
				$(".background").css({"display":"block","opacity":"0.1"}).animate({opacity:1},500);
			}
			var type = $(e).attr("group_id");
	        $('.group').find('a[class="chosen"]').removeClass('chosen');
			$(e).addClass('chosen');
	 		$(".intro_info").find(".title").html("<span>"+titleGroup[type]+"</span>");
	 		$(".intro_info").find(".text").html("<p>"+textGroup[type]+"</p>");
	 		$(".background").css("background-image","url("+bgGroup[type]+")");
	 	
	 	
	};
		
		
/**************feature-boxes******************/
	
	$(function() {
	var featurelist;
	var feature1={};
	var feature2={};
	var feature3={};
	var feature4={};
	featurelist=[feature1,feature2,feature3,feature4];
	
	
	feature1.title="协作";
	feature1.icon="icon-caigou";
	feature1.color="#F6C6EF";
	feature1.context=[{iconclass:"icon-up",iconcolor:"#CC90DD",name:"我的文件",context:"手机电脑自动同步，永久保存随时随地查看文件，移动办公更轻松",url:""},{iconclass:"icon-up",iconcolor:"C1CFFA",name:"我的任务",context:"手机电脑自动同步，永久保存随时随地查看文件，移动办公更轻松",url:""},{},{},{},{},{},{},{},{}];
	feature2.title="协作";
	feature2.icon="icon-caigou";
	feature2.color="#8718B1";
	feature2.context=[{iconclass:"icon-up",iconcolor:"#CCEFDD",name:"我的文件",context:"手机电脑自动同步，永久保存随时随地查看文件，移动办公更轻松",url:"///"},{},{},{}];
	feature3.title="协作";
	feature3.icon="icon-caigou";
	feature3.color="#9076F9";
	feature3.context=[{iconclass:"icon-up",iconcolor:"#C1CFFA",name:"我的文件",context:"手机电脑自动同步，永久保存随时随地查看文件，移动办公更轻松",url:""},{}];
	feature4.title="协作";
	feature4.icon="icon-caigou";
	feature4.color="#CCB9A3";
	feature4.context=[{iconclass:"icon-up",iconcolor:"#C1CFFA",name:"我的文件",context:"手机电脑自动同步，永久保存随时随地查看文件，移动办公更轻松",url:""},{},{}];
	
	//填充数据的过程
	var html_ = "";
	var html_top = "";
	
	//填充表头数据
		$(".feature-types").html("");
	for(var i=0;i<featurelist.length;i++){
	 html_top = "<li feature-id=\'"+i+"\' class=\"feature-type\" >"+
		"<div class=\"icon-top\">"+
		"<i class=\"iconfont "+featurelist[i].icon+"\" ></i>"+
		"</div>"+
		"<span class=\"type-name\" style=\"color:\""+featurelist[i].color+"\"\">"+featurelist[i].title+"</span>"+
		"</li>";
	$(".feature-types").append(html_top);
		}

	$(".feature-types").find("li").hover(function(){
		var id  = $(this).attr("feature-id");
		$(this).css("background",featurelist[id].color);
		$(this).css("color","#fff");
		$(this).addClass("active");
	},function(){
		$(this).css("background","#fff");
		$(this).css("color","#000");
		$(this).removeClass("active");
	});
			
	//点击第一个
			clickType($(".feature-types").find("li:first"));
			
			
	
			$(".feature-types").children().click(function(){
			clickType($(this));
		});
	
	// style=\"background-color:\""+feature[j].icon-color+"\"
	// href=\""+feature[j].url+"\"
		//点击列表的方法
		function clickType(e){
			//填充内容
			
			var html_context="";
			var feature_id = Number($(e).attr("feature-id"));
			var feacol =  featurelist[feature_id].color;
			var feature =  featurelist[feature_id].context;
			
			
			for(j in feature){

			html_context += "<li href=\""+feature[j].url+"\">"+
				"<div  \" class=\"icon\" style=\"background-color:"+feature[j].iconcolor+"\">"+
				"	<i class=\"iconfont "+feature[j].iconclass+"\" ></i>"+
				"</div>"+
				"<div class=\"right-context\">"+
				"<p class=\"name\" style=\"color:"+feacol+"\">"+feature[j].name+"</p>"+
				"<p class=\"context\">"+feature[j].context+"</p>"+
				"</div>"+
				"</li>";
				
			}
	
			//切换动画效果
			if(myBrowser()!=8){
				
				
 				$(".feature-box").fadeTo(300,0,function(){
 					$(".feature-box").html("");
 					$(".feature-box").html(html_context);
 					$(".feature-box").fadeTo(500,1);
 					
 				});
 					
// 					$(".feature-box").animate();
// 					$(".feature-box").animate({opacity:"100"},1000);
 				
 				
// 				$(".background").css({"display":"block","opacity":"0.1"}).animate({opacity:1},500);
			}else{
			$(".feature-box").html(html_context);
			}
			//高度自适应
			var line = parseInt(j/3)*102;
			$(".feature-boxes").height (line+250);
			
			$(".feature-boxes").find("li").css("background-color","#fff").css("color","#000");
			//重新绑定hover
			$(".active").hover(function(){
			var id  = $(this).attr("feature-id");
			$(this).css("background",featurelist[id].color);
			$(this).css("color","#fff");
			$(this).addClass("active");
		},function(){
			$(this).css("background","#fff");
			$(this).css("color","#000");
			$(this).removeClass("active");
		});
			$(".active").removeClass("active");
			$(e).addClass("active").unbind('mouseenter').unbind('mouseleave').css("background-color",featurelist[feature_id].color).css("color","#fff");
			
		
					
// 			var type = $(e).attr("group_id");
// 	        $('.group').find('a[class="chosen"]').removeClass('chosen');
// 			$(e).addClass('chosen');
// 	 		$(".intro_info").find(".title").html("<span>"+titleGroup[type]+"</span>");
// 	 		$(".intro_info").find(".text").html("<p>"+textGroup[type]+"</p>");
// 	 		$(".background").css("background-image","url("+bgGroup[type]+")");
	 	 	
	};
	
	});

	
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
    
	  var tmpparentid="1";
	  var tmphtml="";
	  $.post('/menu.json',function(data){
	  		if(data.error){
	  			layer.msg(data.message);
	  		}else
	  		{
	  			var temp = "";
	  			ptypes=data.PRODUCTTYPELIST;
	  			//填充企业采购菜单
	  			for(var i=0;i<ptypes.length;i++)
	  		  {
	  			  var itList;
	  			  var p=ptypes[i];
	  			  if(p.parentid=="5"||p.id=="5"){
	  				//填充it服务菜单
	  				  if(p.id=="5"){
	  					temp = '<i class="iconfont icon-weixiu" style="margin-right:5px"></i>'+p.name;
	  					$("#itList").append('<li id="root_'+p.id+'"><a href="/buy.html?typeid='+p.id+'">'+temp+'</a></li>'); 
	  					temp = "";
	  				  }else
	  				  if(p.parentid=="5"){
	  					if(temp=="")
	  					    temp='<div class="top_sub_div" style=""><ul><li><a href="/buy.html?typeid='+p.parentid+','+p.id+'">'+p.name+'</a></li>';
	  					  else
	  					  {
	  						temp+='<li><a href="/buy.html?typeid='+p.parentid+','+p.id+'">'+p.name+'</a></li>';
	  					  }		
	  				  }
	  			  }else{
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
	  				  /*if(p.id=='5')
	  					  tmpname='<i class="iconfont icon-weixiu" style="margin-right:5px"></i>'+p.name;*/
	  				  if(p.id=='14')
	  					  tmpname='<i class="iconfont icon-qita" style="margin-right:5px"></i>'+p.name;
	  				  $("#productList").append('<li id="root_'+p.id+'"><a href="/buy.html?typeid='+p.id+'">'+tmpname+'</a></li>');
	  			  }
	  			  else
	  			  {
	  				  if(p.parentid==tmpparentid)
	  				  {
	  					  if(tmphtml=="")
	  					    tmphtml='<div class="top_sub_div" style=""><ul><li><a href="/buy.html?typeid='+p.parentid+','+p.id+'">'+p.name+'</a></li>';
	  					  else
	  					  {
	  						  tmphtml+='<li><a href="/buy.html?typeid='+p.parentid+','+p.id+'">'+p.name+'</a></li>';
	  					  }					  
	  				  }
	  				  else
	  				  {
	  					  tmphtml+='</ul></div>';
	  					  $("#root_"+Number(tmpparentid)).append(tmphtml);
	  					  tmpparentid=p.parentid;
	  					  tmphtml='<div class="top_sub_div" style=""><ul><li><a href="/buy.html?typeid='+p.parentid+','+p.id+'">'+p.name+'</a></li>';	
	  				  }
	  			  }
	  		  }
	  			  if(i==ptypes.length-1)
	  			  {
	  				  tmphtml+='</ul></div>';
	  				  temp += '</ul></div>';
	  				  $("#root_"+tmpparentid).append(tmphtml);
	  				$("#root_5").append(temp);
	  			  }
	  		  }
	  			$("#itList").append('<li id="root_882"><a href="/article/1.json"><i class="iconfont icon-diannao" style="margin-right:5px"></i>设备采购服务</a></li>'); 
	  			$("#itList").append('<li id="root_883"><a href="/article/1-27.json"><i class="iconfont icon-wangluo" style="margin-right:5px"></i>企业其他服务</a></li>'); 
	  			$("#itList").append('<li id="root_884"><a href="/article/1-28.json"><i class="iconfont icon-dianpu" style="margin-right:5px"></i>顶级合作伙伴</a></li>'); 
	  			$("#itList").append('<li id="root_881">&nbsp;</li>'); 
	  			$("#productList").append('<li id="root_999">&nbsp;</li>'); 	  	
	  			 controlMenu();
	  		}});
	
	  	controlMenu();
	
	  
	          
        
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
        //二级菜单栏展开
        $(".top_sub>ul>li").mouseenter(function(){
            $(this).children("div").show();
            $(".top_sub_div").height($(this).parent().parent().height());
        }).mouseleave(function(){
            $(".top_sub_div").hide();
        });
		
	};
	

	  //更新购物车数量
		loadCarts();
		 function loadCarts(){
		    	$.post('/user/getCartCount.json',function(data){
		    		if(data.error){
		    			//layer.msg(data.message);
		    		}else{
		    			$("#cartcount1").html(data.count);
		    		}
		    	});
		    	
		    }


});
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