<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
       
        #app {
            height: 900px;
            width: 1500px;
        }
</style>  
			<div class="map">
 			 	<div id ="body" style="position:relative;text-align: center;">
 			 	<div class="map-content">
	 			 			<!-- <ul class="map-detailImg">
	 			 				<li><img src="../static/images/red.png"/><span>已对接</span></li>
			 				  	<li><img src="../static/images/xx.png"/><span>对接中</span></li>
			 				   	<li><img src="../static/images/gray.png"/><span>准备中</span></li>
	 			 			</ul> -->
	 			 		</div>
	 			 	<div class="map-img" id="app">
	 			 		
	 			 		<!-- <img src="../static/images/dt.png" style="width: 998px;height: 845px"/> -->
			 			<!-- <ul class="map-mark">
			 				<li class="map_end" onclick="map.loadMapInfo(6501,'第一监狱')"><img src="../static/images/red.png"/><span>第一监狱</span></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>&nbsp;&nbsp;&nbsp;乌鲁木齐未成年犯管教所</span></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>女子监狱</span></li>	
			 				<li class="conduct"  onclick="map.loadMapInfo(6503,'第三监狱')"><span>第三监狱</span><img src="../static/images/xx.png"/></li>				 
			 				<li class="ready"><img src="../static/images/gray.png"/><span>第四监狱</span></li>
			 				<li class="map_end"  onclick="map.loadMapInfo(6506,'新收犯监狱')"><span>新收犯监狱</span><img src="../static/images/red.png"/></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>乌鲁木齐监狱</span></li>	
			 				
			 				
			 				<li class="ready"><img src="../static/images/gray.png"/><span>福海监狱</span></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>昌吉监狱</span></li>
			 				<li class="conduct"><img src="../static/images/xx.png"/><span>乌苏监狱</span></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>伊利监狱</span></li>
			 				<li class="ready"><img src="../static/images/gray.png"/><span>哈拉不拉监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>新源监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>库尔勒监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>吐鲁番监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>巴音郭楞监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>沙雅监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>阿克苏监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>卡尔墩监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>于田监狱</span></li>
			 				 <li class="ready"><img src="../static/images/gray.png"/><span>和田监狱</span></li>
			 				  <li class="ready"><span>克孜勒苏监狱</span><img src="../static/images/gray.png"/></li>
			 				 <li class="ready"><span>喀什女子监狱</span><img src="../static/images/gray.png"/></li>
			 				 <li class="ready"><span>喀什监狱</span><img src="../static/images/gray.png"/></li>
			 				
			 				
			 			</ul> -->
	 			 	</div>
 			 	</div>
			</div>
			<div style="position: absolute; bottom: 10px; height: 40px;left:20px;right:20px;overflow: hidden;font-size:16px">
				<div class="l-span">
						<span><a href="javascript:logoutX();">退出</a></span>
						<span id="glob_dep"></span>
						<!-- <span>位置：<a href="javascript:void(0)">新疆自治区</a></span>
						<span>监狱<a href="javascript:void(0)">24个</a></span> -->
						<span><a href="javascript:void(0)" id="viewMessageId">待办事项</a></span>
						<span id="message" style="display: none"></span>
				</div>	
				<div class="r-but">
				
					<%@ include file="toolbar.jsp"%>
				</div>	
			</div>
			<!-- end add by linhe for 3d map 2017-11-23 -->
<script src="${ctx }/static/js/map/echarts.min.js"></script>
<script src="${ctx }/static/js/map/index.js"></script>			
<script type="text/javascript">

	var proMap = getMap('${ctx }/static/js/map/hunan2.json', 'app');
	//初始化工具条
	function initToolbar(){
		$("#glob_dep").html(jsConst.CUS_NAME +"：<a href='javascript:void(0);'>" + jsConst.REAL_NAME+"</a>");
		//初始化左上角视角定位下拉菜单
		map.showPrisonMenu();//add by linhe 2018-01-09
	}
	initToolbar();
	function mapMarkHover(){
		$(".map_end").mouseover(function(){
			$(this).children("span").css("color","#003366");
			$(this).children("img").attr("src","../static/images/red_hover.png");
		});
		$(".map_end").mouseout(function(){
			$(this).children("span").css("color","#000");
			$(this).children("img").attr("src","../static/images/red.png");
		});
		
		$(".ready").mouseover(function(){
			$(this).children("span").css("color","#003366");
			$(this).children("img").attr("src","../static/images/gray_hover.png");
		});
		$(".ready").mouseout(function(){
			$(this).children("span").css("color","#000");
			$(this).children("img").attr("src","../static/images/gray.png");
		});
		
		$(".conduct").mouseover(function(){
			$(this).children("span").css("color","#003366");
			$(this).children("img").attr("src","../static/images/xx_hover.png");
		});
		$(".conduct").mouseout(function(){
			$(this).children("span").css("color","#000");
			$(this).children("img").attr("src","../static/images/xx.png");
		});
	}
	mapMarkHover()
</script>




























