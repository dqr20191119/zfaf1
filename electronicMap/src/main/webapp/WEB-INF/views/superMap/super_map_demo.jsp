<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimum-scale=1.0,maximum-scale=1.0"/>
<meta name="format-detection" content="telephone=no">
<meta name="x5-fullscreen" content="true">
<meta http-equiv="Access-Control-Allow-Origin" content="*"/>

<title>矢量</title>
<link href="${ctx}/static/resource/SuperMap/Build/Cesium/Widgets/widgets.css" rel="stylesheet">
<script src="${ctx}/static/resource/SuperMap/js/jquery.min.js"></script>
<!-- <script src="${ctx}/static/resource/SuperMap/Build/Cesium/Cesium.js"></script> -->

<script type="text/javascript" src="${ctx}/static/resource/SuperMap/js/require.min.js" data-main="${ctx}/static/resource/SuperMap/js/main"></script>
<style>
html, body, #cesiumContainer {
	width: 100%; height: 100%; margin: 0; padding: 0;
	overflow: hidden;
	background: #000;
	color: #eee;
	font-family: sans-serif;
	font-size: 10pt;
	-webkit-overflow-scrolling: touch;
}
</style>
</head>
<body>
	<div id="cesiumContainer"></div>
</body>

<script type="text/javascript">
function onload(Cesium) {
	//初始化viewer部件
	var viewer = new Cesium.Viewer('cesiumContainer');
	/* viewer.imageryLayers.addImageryProvider(new Cesium.BingMapsImageryProvider({
		url : 'https://dev.virtualearth.net',
		mapStyle : Cesium.BingMapsStyle.AERIAL,
		key : URL_CONFIG.BING_MAP_KEY
	})); */
	var scene = viewer.scene;
	var widget = viewer.cesiumWidget;

	try{
		//打开所发布三维服务下的所有图层
		var promise = scene.open('http://210.0.100.193:8090/iserver/services/3D-20190224/rest/realspace');
		Cesium.when.all(promise,function(layers){
		viewer.scene.globe.depthTestAgainstTerrain = false;
		//设置图层的阴影模式
		scene.camera.setView({
			//将经度、纬度、高度的坐标转换为笛卡尔坐标
			destination : new Cesium.Cartesian3.fromDegrees(115.00691786995017, 30.999038305768546, 366),
				orientation : {
					heading : 4.844795866469065,
					pitch : -0.58125995096984,
					roll :1.2504663970958063e-11
				}
			});
		},function(e){
		    if (widget._showRenderLoopErrors) {
		        var title = '加载SCP失败，请检查网络连接状态或者url地址是否正确？';
		        widget.showErrorPanel(title, undefined, e);
		    }
		});
	} catch(e){
		if (widget._showRenderLoopErrors) {
		    var title = '渲染时发生错误，已停止渲染。';
		    widget.showErrorPanel(title, undefined, e);
		}
	}
}
</script>
</html>