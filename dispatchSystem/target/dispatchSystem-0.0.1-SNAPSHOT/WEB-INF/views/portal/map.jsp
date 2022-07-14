<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<title>test演示1</title>
<!-- coral4 css start  -->
<link rel="stylesheet" href="${ctx}/static/cui/cui.min.css"/>
<!-- coral4 css  end  -->
<style>
html, body {
	height: 100%;
	width: 100%;
}
.nav {
	padding-left: 0;
    list-style: none;
    cursor: pointer;
}
  .navbar-nav {
    margin: 0;
}
  .caret {
    display: inline-block;
    width: 0;
    height: 0;
    margin-left: 2px;
    vertical-align: middle;
    border-top: 4px dashed;
    border-right: 4px solid transparent;
    border-left: 4px solid transparent;
}
  b, strong {
    font-weight: bold;
}
  a:hover,
  a:focus {
	text-decoration: none;
}
  .navbar-nav > li > .dropdown-menu {
    margin-top: 0;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
  .dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    display: none;
    float: left;
    min-width: 160px;
    padding: 5px 0;
    margin: 2px 0 0;
    font-size: 14px;
    text-align: left;
    list-style: none;
    background-clip: padding-box;
    border: 1px solid rgba(0, 0, 0, .15);
    border-radius: 4px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
}
.navbar-nav .small {
    font-size: 12px;
    color: #d20000;
    position: absolute;
    top: 16px;
    right: 0;
    display: block;
}
  .dropdown-menu > li > a {
    display: block;
    padding: 3px 20px;
    clear: both;
    font-weight: normal;
    line-height: 1.42857143;
    white-space: nowrap;
}
  .navbar-nav > li {
	float: left;
	margin-left: 10px;
}
  .nav > li > a {
    position: relative;
	display: block;
    padding: 10px 15px;
}
  .navbar-nav > li > a {
    padding-top: 15px;
    padding-bottom: 15px;
    line-height: 20px;
}
  .container > .navbar-header,
  .container-fluid > .navbar-header,
  .container > .navbar-collapse,
  .container-fluid > .navbar-collapse {
    margin-left: 0;
}
  .navbar-default {
	background-color: #333;
}
  .navbar-left {
	float: left;
}
  .navbar-default .navbar-nav > li > a:hover,
  .navbar-default .navbar-nav > li > a:focus {
	color: white
}
  .navbar-fixed-top {
	top: 0;
    border-width: 0 0 1px;
}
  .navbar-fixed-top, .navbar-fixed-bottom {
    right: 0;
    left: 0;
    z-index: 1030;
}
  .navbar-default .navbar-nav > li > a {
	color: white;
	text-decoration: none;
}
  .nav > li {
	position: relative;
    display: block;
    font-size: 16px;
}
  .dropdown-menu > li > a,
  .dropdown-menu > li > a:hover,
  .dropdown-menu > li > a:focus {
	color: white;
	text-decoration: none;
}
.menu-iframe {
	position: absolute;
    top: 100%;
    left: 0;
    z-index: 100;
    display: none;
    float: left;
    min-width: 160px;
}
.coral-layout,
.coral-panel-body,
.coral-panel.coral-layout-panel  {
	overflow: visible;
}
.map{
	position:absolute;
	top:0;
	bottom:30px;
	left:0;
	right:0;
}
</style>
<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/point.css" type="text/css" rel="stylesheet" />
<!-- app css define end -->

<!-- coral4 js start -->
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/cui/cui.js"></script>
<!-- coral4 js end -->

<!-- app js define start  -->
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<!-- app js define  end  -->

<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script src="${ctx}/static/js/echarts.min.js"></script>
<script>
	jsConst.basePath = "${ctx}/";
	initUserInfo();
</script>

<script type="text/javascript" src = "${ctx}/static/js/common/ajaxCommon.js"></script>
<!-- Begin add by linhe 2018-01-11 -->
<script src="${ctx}/static/resource/map/cm7.js"></script>
<script src="${ctx}/static/resource/map/cm7_sample_util.js"></script>
<script src="${ctx}/static/resource/map/map_3d.js"></script>
<script src="${ctx}/static/resource/map/custom_arrow.js"></script>
<script src="${ctx}/static/js/map/prisonmap.js"></script>
<!-- end add by linhe for 3d map 2018-1-11 -->
</head>
<body>
<!-- 监控视频弹窗 -->
<cui:dialog id="showRouteVideoDialog" resizable="false" subTitle ="监控视频"  width="400" height="400"  iframePanel="true" autoOpen="false" onOpen="map.stopPlay" onClose="map.contiunePlay" position="map.videoPostion">
  <div id="videoPlay_map">视频</div>
</cui:dialog>
<cui:dialog id="dialog" width="60%" height="60%" title="dialog" iframePanel="true" autoOpen="false">
	<cui:input id="input" name="input"></cui:input>
</cui:dialog>
<cui:layout id="layout1" fit="true">
  <cui:layoutRegion region="north" split="true" style="height:50px;">
        <div id="navbar-collapse" class="navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
            	<li class="dropdown toggle">
                    <a href="#" data-toggle="dropdown" id="dropdownToggle2" class="dropdown-toggle">视角定位
                        <b class="caret"></b>
                    </a>
                 	<iframe id="viewMenuIframe" class="menu-iframe"></iframe>
                    <ul id="dropdownMenu2" class="dropdown-menu" style="background-color: #333;color:white">
                        <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                        <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                        <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                        <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                        <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                        <li class="menu_btn_li"><a href="#">字体图标库(ICON)</a></li>
                    </ul>
                </li>
                <li id="nav-index home" style="font-size:20px"><a href="#">Coral&nbsp;<small class="small">4.0</small></a></li>
                <li id="nav-doc"><a href="#">教程</a></li>
                <li id="nav-api"><a href="#">API</a></li>
                <li id="nav-FAQ"><a href="#">常见问题</a></li>
                <li id="nav-examples"><a href="#">案例展示</a></li>
                <li class="dropdown toggle"><a href="#" data-toggle="dropdown" class="dropdown-toggle">定制工具<b class="caret"></b></a>
                 	<iframe id="menuIframe" class="menu-iframe">
                 	</iframe>
                 <ul class="dropdown-menu" style="background-color: #333;color:white">
                    <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                    <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                    <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                    <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                    <li class="menu_btn_li"><a href="#" >字体图标库(ICON)</a></li>
                    <li class="menu_btn_li"><a href="#">字体图标库(ICON)</a></li>
                 </ul>
                </li>
                 <li id="nav-more"><a href="#">扩展</a></li>
                 <li class="dropdown toggle"><a href="#" data-toggle="dropdown" class="dropdown-toggle">三维地图<b class="caret"></b></a>
	                 <iframe id="menuIframe2" class="menu-iframe">
	                 </iframe>
	                 <ul class="dropdown-menu" style="background-color: #333;color:white;padding-left:0px;">
	                 	<li><a href="javascript:map1.edit_3d_model();" >模型管理</a></li>
	                 	<li><a href="javascript:map1.edit_3d_layer();" >报警图层管理</a></li>
                        <li><a href="javascript:map1.edit_3d_region();" >区域管理</a></li>
                        <li><a href="javascript:map1.edit_3d_view();" >视角管理</a></li>
                        <li><a href="javascript:map1.edit_3d_camera();" >点位管理</a></li>
                        <li><a href="javascript:map1.edit_3d_route();" >巡视维护</a></li>
                        <li><a href="javascript:map1.edit_3d_label();" >标签管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </cui:layoutRegion>
    <cui:layoutRegion region="east" split="true" collapsed="false" style="width:300px;">
      <p>right</p>
     </cui:layoutRegion>
    <cui:layoutRegion region="center">
      <!-- begin add by linhe for 3d map 2017-11-23-->
      <div class="map">
         <object id="_g" type="application/x-cm-3d8" style="height: 100%; width: 100%;z-index:-1"></object>
      </div>
      <div style="position:absolute;bottom:0;height:30px;border:1px solid black;width:100%;">
            <a href="javascript:fullScreen();" >全屏</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:map_3d.setMouseClickSelect();" >拾取</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_playJson();" >巡视</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_pause();" >暂停</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_stop();" >停止</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_createLine_Click();" >绘图</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_initPosition();" >大门</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_build3();" >大楼</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="javascript:btn_setImg();" >星星</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- <a href="javascript:map_3d.setGlowLine('11111111-1111-1111-1111-111111111111');" >闪烁</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
		    <a href="javascript:Coordinate();" >拾取坐标</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:btn_getObjs();" >框选</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
  <!-- end add by linhe for 3d map 2017-11-23 -->
    </cui:layoutRegion>
    <cui:layoutRegion region='south' split="false" style="height:25px" >
	  <div id="footer" class="PanelFoot">
			<h2 class="F-right">信息发展©2016版权所有</h2>
	 </div>
    </cui:layoutRegion>
</cui:layout>
</body>
<script type="text/javascript">
$(function(){
	map1 = {
			videoPostion:{
		    	 my: "center",//中间
		         at: "center",
	            of: window
		    },
			cusNumber:jsConst.CUS_NUMBER,
			//模型显示隐藏
			hideAreaId:null,
			hideParentAreaId:null,
			//视频巡视
			cameraTour:null,
			pointData:null,
			nowIndex:null,
			videoPlayTime:null,
			menudata2 : [{
				    "name" : "menu1",
				    "id" : "menu1",
				    "iconclass" : "coral-icon-document",
				    "url" : "xxx.html",
				    "target" : "_target",
				    "disabled" : "false",
				    "items" : [{
				        "name" : "menu11",
				        "id" : "menu11",
				        "iconclass" : "",
				        "url" : "xxx1.html",
				        "target" : "_target1",
				        "disabled" : "false",
				        "items" : []
				    },{
				        "name" : "menu12",
				        "id" : "menu12",
				        "iconclass" : "coral-icon-document",
				        "url" : "",
				        "target": "",
				        "response": "click",
				        "items" : [{
				            "name" : "menu121",
				            "id" : "menu121",
				            "iconclass" : "",
				            "url" : "http://www.baidu.com/",
				            "target": "_blank",
				            "items" : []
				        }]
				    } ]
				},{
				    "name" : "menu2",
				    "id" : "menu2",
				    "iconclass" : "coral-icon-document",
				    "url" : "",
				    "target" : "",
				    "items" : [{
				        "name" : "menu21",
				        "id" : "menu21",
				        "iconclass" : "",
				        "url" : "",
				        "target" : "",
				        "items" : []
				    }]
				}],
			url:null,
			rightPannel:$('#right_pannel'),
			init:function(){
				$(".dropdown-toggle").click(function() {
					var menu = $(this).nextAll();
					var menu_iframe = menu[0];
					var menu_ul = menu[1];
					$(".dropdown-menu").hide();
					$(".menu-iframe").hide();
					if($(menu_ul).is(':visible')){
						$(menu_ul).hide();
						$(menu_iframe).hide();
					}else{
						var width = $(menu_ul).outerWidth(),
						height = $(menu_ul).outerHeight();
						$(menu_iframe).css({
							"width": width,
							"height": height
						}).show();
						$(menu_ul).show();
					}
				})
				$(".dropdown-toggle").blur(function() {
					setTimeout(function() {
						$(".dropdown-menu").hide();
						$(".menu-iframe").hide();
					},200)
				})
				$(".dropdown-menu .menu_btn_li").click(function() {
					$("#dialog").dialog("open")
				})
				//视角定位
				//map.showViewMenu();
			},
			//模型维护
			edit_3d_model:function (){
				console.log('模型维护');
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/model/index");
			},
			//报警图层维护
			edit_3d_layer:function (){
				console.log('报警图层维护');
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/layer/index");
			},
			//区域维护
			edit_3d_region:function (){
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/region/add3DRegion");
			},
			//三维视角管理
			edit_3d_view:function (){
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/view/index");
			},
			//三维地图摄像头管理(点位管理)
			edit_3d_camera:function (){
	            var panels = $('#layout1').layout('panel', 'east');
	            panels.panel('refresh', "${ctx}/point/index");
			},
			//三维地图报警设备管理
			edit_3d_alarm:function (){
				console.log('三维地图报警设备管理');
			},
			//三维地图巡视维护
			edit_3d_route:function (){
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/route/walkManager");
				//map_3d.__g.interactMode = gviInteractMode.gviInteractNormal;				
			},
			//三维地图标签管理
			edit_3d_label:function (){
				var panels = $('#layout1').layout('panel', 'east');
				panels.panel('refresh', "${ctx}/labels/labelManager");
			},
			setRightPannel:function (url){
				map1.rightPannel.attr("src",url);
			}
			
	}
	//初始化
	map1.init();
});
  

</script>

</html>