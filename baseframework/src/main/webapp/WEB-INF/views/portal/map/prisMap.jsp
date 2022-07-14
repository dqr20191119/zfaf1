<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Begin add by linhe 2018-01-09 -->
<script src="${ctx}/static/resource/map/cm7.js"></script>
<script src="${ctx}/static/resource/map/cm7_sample_util.js"></script>
<script src="${ctx}/static/resource/map/map_3d.js"></script>
<script src="${ctx}/static/resource/map/custom_arrow.js"></script>
<!-- End add by linhe 2018-01-09 -->

<!-- Begin add by lincoln.cheng 2020-05-29 -->
<script src="${ctx}/static/module/video/js/video-plugin-layout.js"></script>
<link href="${ctx}/static/module/video/css/video-plugin-layout.css" rel="stylesheet">
<!-- End add by lincoln.cheng 2020-05-29 -->

<div class="map" style="z-index:-1; display: block;">
	<!-- 三维地图 -->
	<div id="map3d_container" class="map3d_container">
		<object id="_g" type="application/x-cm-3d8" style="height: 100%; width: 100%;z-index:-1"></object>
	</div>
	<!-- 视频插件 -->
	<div id="signx_show" class="video_container" style="display: none;">
		123414124
	</div>
</div>
<div style="position: absolute; bottom: 10px; height: 40px;left:20px;right:20px;overflow: hidden;font-size:16px">
	<div class="l-span">
		<cui:input id="mapAreaId" type="hidden" />
		<span><a href="javascript:logoutX();"><i class="iconfont icon-tuichu"></i>退出</a></span>
		<span><a href="javascript:void(0)" id="viewMessageId"><i class="iconfont icon-daibanshixiang"></i>待办</a></span>
		<span id="glob_dep"></span>
		<span>位置：<a href="javascript:void(0)" id="locationName">新收犯监狱</a></span>
		<span>监控点：<a href="javascript:void(0)" id="monitorPoint" class="color-red" onclick="common.areaCountCameraList()"></a></span>
		<span>报警点：<a href="javascript:void(0)" id="alarmPoint" class="color-red" onclick="common.areaCountAlertorList()"></a></span>
		
		<span id="message" style="display: none"></span>
	</div>
	<div class="r-but">
		<%@ include file="toolbar.jsp"%>
	</div>
</div>
<!-- end add by linhe for 3d map 2017-11-23 -->
<!-- add by zk 2018-3-26 -->
<%@ include file="planeIn3D.jsp"%> 
<!-- add by zk 2018-3-26 -->
<script type="text/javascript">
	//初始化工具条
	function initToolbar(){
		// alert("prisMap init");
		$("#glob_dep").html(jsConst.CUS_NAME +"：<a href='javascript:void(0);'>" + jsConst.REAL_NAME+"</a>");
		//Begin add by linhe 2018-1-13 for init location name and init position menu info
		if(jsConst.USER_LEVEL==1 && jsConst.ROOT_ORGA_CODE!=null && jsConst.ROOT_ORGA_CODE!=''){//省局登录
			// initToolsText(jsConst.ROOT_ORGA_NAME);
			$('#locationName').text(jsConst.ROOT_ORGA_NAME);
		}else if((jsConst.USER_LEVEL==2 || jsConst.USER_LEVEL==3) && jsConst.CUS_NUMBER!=null && jsConst.CUS_NUMBER!=''){//监狱登录
			// initToolsText(jsConst.CUS_NAME);
			// alert(jsConst.CUS_NAME);
			$('#locationName').text(jsConst.CUS_NAME).unbind('click').bind('click', function() {
				map.viewPositionByAreaId(jsConst.CUS_NUMBER, 1, jsConst.CUS_NAME);
			});
		}
		//初始化标签
		map.findShowLabels();
		//初始化视角定位菜单按钮
		map.showViewMenu(); 
		//End add by linhe 2018-1-13 for init location name and init position menu info
	}
	initToolbar();
	//初始化位置信息
</script>




























