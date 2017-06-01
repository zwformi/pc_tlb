<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
	layer.config({
	    extend: '<%=basePath%>js/layer/extend/layer.ext.js'
	});
	$(document).ready(function($){
		//用户头像加载错误事件
		$("img.head_img_").on("error",function(){
			$(this).attr("src","/images/default_img.png");
		});
		
		//问题反馈
         $("#wtfk").click(function(){
        	layer.prompt({title: '使用问题反馈', formType: 2,value:''}, function(text){
	          		layer.confirm('确认要提交本次留言吗？', {icon: 3}, function(index){
	           		$.post("<%=basePath%>user/savefeedback.json",{"message":text},function(data){
				    	if(data.error){
	           				layer.msg(data.message);
	           			}else{
				    		layer.msg("您的留言已提交成功，感谢您的反馈！");
				    	}
				    });
			    });
		    });
         });
         
         $("#wtfk").mouseover(function(){
         	$(this).css("background-image","url(<%=basePath%>images/zxly2.png)");
         });
         $("#wtfk").mouseout(function(){
         	$(this).css("background-image","url(<%=basePath%>images/zxly.png)");
         });
     });
</script>
<style>
	#wtfk{cursor:pointer;position:fixed;bottom:40px;right:10px;
			background-image:url(<%=basePath%>images/zxly.png);background-repeat:no-repeat;
			width:66px;height:63px;}
</style>
<div class="member_right">
   <div class="member_r_info">
      <dl>
        <dt>
           <span><a href="#"><img src="<%=basePath %>images/head_bj.png" width="110" height="110"/></a></span>
           <img class="head_img_" src="${loginUser.headimgurl_page }" width="110" height="110"/>
         </dt>
         <dd>
           <strong>${loginUser.xm }&nbsp;<a href="/login/out.html">退出</a></strong>
           <span>${loginUser.gsmc }</span>
         </dd>
       </dl>
       <ul>
       	  <a href="/um/list.html">
	          <li>
	             <strong><i class="fa fa-file-text-o"></i></strong>
	             <span>我的消息<b>${umCount }</b></span>
	          </li>
          </a>
          <a href="/cart.html">
          <li>
             <strong><i class="fa fa-cart-plus"></i></strong>
             <span>采购车<b id="cartcount">${CARTCOUNT }</b></span>
          </li>
          </a>
       </ul>
       <div class="member_kf">
         <h3>我的专属客服</h3>
         <ol>
           <li></li>
           <c:if test="${loginUser.client_id!=0 }">
	           <li> <span><a href="#"><i class="fa fa-street-view"></i> ${USERCLIENT.name }</a></span>
	             <p>
	               工号：${USERCLIENT.gh }<br/>
	               电话：${USERCLIENT.phone }<br/>
	               QQ：${USERCLIENT.qq }<br/>
	               固定电话：${USERCLIENT.telphone }<br/>
	               邮箱：${USERCLIENT.email }</p>
	           </li>
           </c:if>
           <c:if test="${loginUser.client_id==0 }">
	           <li> <span><a href="#"><i class="fa fa-street-view"></i> 未分配</a></span>
	           		<p>暂未给您分配客服，<br/>
	               		请联系管理员</p>
	           </li>
           </c:if>
         </ol>
       </div>
       <div class="member_tel" style="margin-top:10px;">
          <h3>咨询电话</h3>
          <strong>400- 826- 4755<br/><span style="font-size:12px;color:#888888;font-weight:normal;">工作日 9:00-18:00</span></strong>
       </div>
    </div>
    <div class="member_ad">
    <c:forEach items="${ADIMGS }" var="ADIMG" varStatus="statu">
      	<a href="${ADIMG.url }" target="_blank"><img src="http://img.tulingbuy.com${ADIMG.img_url }" width="190" /></a>
    </c:forEach>
    </div>
    <div id="wtfk" ></div>
 </div>
