$(document).ready(function($){

	//代码转换表
	var order_stateMap={0:'未付款',1:'已付款',4:'配货中', 5:'已发货，待签收', 61:'已签收，待实施', 62:'实施中 ', 71:'已签收，待评价', 72:'已实施、待评价',8:'已完成'}; 
	var demand_stateMap={4:'待报价',  5:'完成报价',6:'已关闭',7:'客户接收报价',8:'已生成合同'};
	
	//代码值转换
	var state =  $('#statechange1').text();
	$('#statechange1').text(order_stateMap[state]);
	
	//流程图控制
		var count = 0;
		

		state=$('#state1').val();
		if($('#type').val()=='order'){
			//订单
			if(state==0||state==1||state==2){
				count =1;
			}else if(state==4){
				count=2;
			}else if(state==5){
				count=3;
			}else if(state==61||state==62||state==71||state==72){
				count=4;
			}else if(state==8){
				count=5;
			}
		}else if($('#type').val()=='demand'){
			//需求
			if(state==4){
				count =2;
			}else if(state==5){
				count=3;
			}else if(state==7||state==8){
				count=4;
			};
		}
		for(i=1;i<count;i++){
			$(".icon"+i).parent().removeClass("wait").addClass("done");
			$(".proce"+i).parent().removeClass("wait").addClass("done");
		}
		$(".icon"+count).parent().removeClass("wait").addClass("doing");
		//填充流程图时间值
		if($("input[name='发货']")){
	$(".icon2").parent().find('ul').find('li.txt3').text($("input[name='发货']").val());
		}
		if($("input[name='签收']")){
	$(".icon3").parent().find('ul').find('li.txt3').text($("input[name='签收']").val());
}
		if($("input[name='完成评价']")){
	$(".icon4").parent().find('ul').find('li.txt3').text($("input[name='完成评价']").val());
}
});

