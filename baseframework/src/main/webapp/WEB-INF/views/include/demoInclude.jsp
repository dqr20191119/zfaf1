<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/aqfk">
<li>
		<a href="javascript:void(0);">
			前置机Demo
		</a>
		<iframe class="main-iframe"></iframe>
	<ul>
		
		<!-- 【前置机Demo - 菜单】start -->
		<li>
				<a href="javascript:void(0);">通信融合</a>
				<b class="leftArrow"></b>
				<iframe class="sub-iframe"></iframe>
			<ul>
				<li id="demo_yytz" onclick="openDemoDialog(event, 'rcsYytzDemo')">
					<a href="#">语音通知</a>
				</li>
				<li id="demo_yjya" onclick="openDemoDialog(event, 'rcsYjyaDemo')">
					<a href="#">预警预案</a>
				</li>
			</ul>
		</li>
		<!-- 【前置机Demo - 菜单】 end -->
	</ul>
</li>
</sec:authorize>
<script>
function openDemoDialog(event,name) {
	var event = window.event || event;
	//event.stopPropagation();
	if ( event && event.stopPropagation ) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'rcsYytzDemo') {
		url = jsConst.basePath+'/rcs/openYytzDemoDialog';
		w = 1000;
		h = 600;
	} else if (name == 'rcsYjyaDemo') {
		url = jsConst.basePath+'/rcs/openYjyaDemoDialog';
		w = 1000;
		h = 600;
	}

	// $('#dialog').dialog("destroy");
	$('#dialog').html("");
	if (w == null || h == null) {
		w = 800;
		h = 600;
	}

	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#demo_" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");
	return;
}
function toAqfkDisplay(name) {
	var panel = $("#layout1").layout("panel", "east");
	if (name == "alarmProcess") {
		panel.panel("refresh", jsConst.basePath+"/alarm/open/index?type=0");
	}else if (name == "alarmProcessMan") {
		panel.panel("refresh", jsConst.basePath+"/alarm/open/index?type=1");
	}else if (name == "spya") {//视频预案
		panel.panel("refresh", jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		if(jsConst.USER_LEVEL==1){//省局
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/prov/spya");
		}else{
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		}
	}else if (name == "sphf") {
		if(jsConst.USER_LEVEL==1){//省局
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/prov/sphf");
		}else{
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/sphf");
		}
	}else if (name == "offLineCamera") {
		panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/offLineCamera");
	}else if (name == "swxs") {//三维巡视
		panel.panel("refresh",jsConst.basePath+"/portal/swxs");
	}else if (name == "powerNetwork") {//数字电网
		panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/szdw/rightPowerNetwork");		
	}else if (name == 'doorInfo'){//门禁控制树
		panel.panel("refresh", jsConst.basePath+"/doorlinkage/index");
	}
	//视频轮巡 add by zk
	else if (name == "splx") {
		var params={
			"grpId":null
		}
		openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
	}
}
/**
 * 跳转到一体化运维页面
 */
function openYthyw() {
	
	var url = "http://206.0.8.223/http/tekinfo/login/login.html?callback-url=http%3A%2F%2F206.0.8.223%2Fhttp%2FiCarrier%2Findex.html";
	window.open(url, "_blank");
}
/**
 * 跳转到无人机防空页面
 */
function openWrjfk() {
	
	var url = "http://206.0.4.6/Myrtille/?__EVENTTARGET=&__EVENTARGUMENT=&server=206.0.4.5&user=administrator&password=68555587&connect=1";
	window.open(url, "_blank");
}
//携带参数打开右侧视图 add by zk
function openViewToRightAddParam(viewName, params) {
	var panel = $("#layout1").layout("panel", "east");
	panel.panel("refresh", '${ctx}/menubar/displayRightAddParam?viewName=' + viewName + '&params=' + encodeURI(JSON.stringify(params)));
}

/**
 * 狱外押解
 */
function openYuwaiyajie() {
	var url = "yuwaiyajie://";
	window.open(url, "_self");
}
</script>