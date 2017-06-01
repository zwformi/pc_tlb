$(document).ready(function(){
	//加载维修站列表
	loadstations();
	
	$("#search_but").click(function(){
		loadstations();
	});
    
	//加载维修站列表
    function loadstations(){
    	layer.closeAll();
    	layer.load();
    	var keyword = $("#keyword").val();
    	var param ={"keyword":keyword};
    	$.post('/service/loadstation.json',param,function(data){
    		layer.closeAll('loading');
    		if(data.error){
    			layer.msg(data.message);
    		}else{
    			var html_="";
    			var STATIONLIST_ = data.STATIONLIST;
    			if(STATIONLIST_.length>0){
    				var html_="";
    				for(var i=0;i<STATIONLIST_.length;i++){
    					var STATION = STATIONLIST_[i];
    					html_+="<tr>"+
    						   "  <td>"+STATION.type_name+"</td>"+
					           "  <td>"+STATION.name+"</td>"+
					           "  <td><span>"+STATION.address+"</span></td>"+
					           "  <td><u>"+STATION.lxr+"</u></td>"+
					           "  <td><u>"+STATION.phone+"</u></td>"+
					           "</tr>";
    				}
    			}else{
    				html_="<tr><td colspan=\"4\">还没有任何的维修站信息...</td></tr>";
    			}
    			$("#content").html(html_);
    		}
    	});
    }
});