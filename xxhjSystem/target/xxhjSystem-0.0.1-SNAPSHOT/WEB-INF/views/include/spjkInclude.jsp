<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<sec:authorize url="/zhdd/sy/zhdd/aqfk">
<li>
		<a href="javascript:void(0);">
			安全防控
		</a>
		<iframe class="main-iframe"></iframe>
	<ul>
		
		<!-- 【视频监控 - 菜单】 start -->
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/spjk">
		<li>
				<a href="javascript:void(0);">视频监控</a>
				<b class="leftArrow"></b>
				<iframe class="sub-iframe"></iframe>
			<ul>
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/spjk/sdy">
				<li id="spya" onclick="toAqfkDisplay('spya')">
					<a href="#">视频调阅</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/spjk/splx">
				<li id="splx" onclick="toAqfkDisplay('splx')">
					<a href="#">视频轮巡</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/spjk/sphf">
				<li id="sphf" onclick="toAqfkDisplay('sphf')">
					<a href="#">视频回放</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/spjk/lxsxt">
				<li id="offLineCamera" onclick="toAqfkDisplay('offLineCamera')">
					<a href="#">离线摄像头</a>
				</li>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize>
		
		
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/szdw">
		<li id="powerNetwork" onclick="toAqfkDisplay('powerNetwork')">
			<a href="#">数字电网</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/mjkz">
		<li id="realTimeTalk" onclick="toAqfkDisplay('doorInfo')">
			<a href="javascript: void(0)">门禁控制</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/ssdj">
		<li id="aqfk_realTimeTalk" onclick="openAqfkDialog(event,'realTimeTalk')">
			<a href="javascript: void(0)">实时对讲</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/gbkz">
		<li>
			<a href="javascript:void(0);">广播控制</a>
			<b class="leftArrow"></b>
			<iframe class="sub-iframe"></iframe>
			<ul>
			
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/gbkz/gbbf">
				<li id="aqfk_broadcastPlay" onclick="toAqfkDisplay('broadcastPlay')">
					<a href="#">广播播放</a>
				</li>
				</sec:authorize >
				<sec:authorize url="/zhdd/sy/zhdd/aqfk/gbkz/gbbfjl">
				<li id="aqfk_broadcastRecord" onclick="openAqfkDialog(event,'broadcastRecord')">
					<a href="javascript: void(0)">广播播放记录</a>
				</li>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize>
		
		<sec:authorize url="/zhdd/sy/zhdd/aqfk/gzwx">
		<li id="zhxt_deviceRecord" onclick="openZhxtDialog(event,'deviceRecord')">
			<a href="javascript:void(0);">故障维修</a>
		</li>
		</sec:authorize>
	</ul>
</li>
</sec:authorize>
<script>
function openAqfkDialog(event,name) {
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
	if (name == 'jdjc') {
		url = jsConst.basePath+'/monitor/jdjc';
		w = 1000;
		h = 700;
	}else if (name == 'jddlb') {
		url = jsConst.basePath+'/monitor/jddlb';
		w = 1000;
		h = 700;
	}else if (name == 'realTimeTalk') {
		url = jsConst.basePath+'/realTimeTalk/openDialog';
		w = 1000;
		h = 600;
	}else if (name == 'group') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/groupManage/index';
	}else if (name == 'screenSwitch') {
		url = jsConst.basePath+'/screenSwitch/openDialog';
	}else if (name == 'wldctb-bj') {
		url = jsConst.basePath+'/inspect/editDialog';
	}else if (name == 'wldctb-lb') {
		url = jsConst.basePath+'/inspect/inspectListDialog';
		w = 1000;
		h = 700;
	}else if (name == 'wldctb-sp') {
		url = jsConst.basePath+'/inspect/checkDialog';
		w = 1000;
		h = 700;
	}else if (name == 'wldctb-hz') {
		url = jsConst.basePath+'/inspect/recordDialog';
		w = 1000;
		h = 700;
	}else if (name == 'bddctb-bj') {
		url = jsConst.basePath+'/inspectlocal/editDialog';
	}else if (name == 'bddctb-sp') {
		url = jsConst.basePath+'/inspectlocal/checkDialog';
	}else if (name == 'bddctb-hz') {
		url = jsConst.basePath+'/inspectlocal/recordDialog';
	}else if (name == 'tbzg-fq') {
		url = jsConst.basePath+'/xxyp/change/launchDialog';
	}else if (name == 'tbzg-zg') {
		url = jsConst.basePath+'/xxyp/change/changeDialog';
	}else if (name == 'tbzg-sp') {
		url = jsConst.basePath+'/xxyp/change/checkDialog';
	}else if (name == 'tbzg-hz') {
		url = jsConst.basePath+'/xxyp/change/recordDialog';
	}else if (name == 'rcs') {
		url = jsConst.basePath+'/rcs/openDialog';
		w = 1000;
		h = 600;
	}else if (name == 'search') {
		url = jsConst.basePath+'/rcs/toIndex';
		w = 1000;
		h = 600;
	}else if (name == 'doorDemo') {
		url = jsConst.basePath+'/doorlinkage/openDoorDemoDialog';
		w = 1000;
		h = 600;
	}else if (name == 'rcsYjyaDemo') {
		url = jsConst.basePath+'/rcs/openYjyaDemoDialog';
		w = 1000;
		h = 600;
	} else if (name == 'broadcastRecord') {
        url = jsConst.basePath + '/broadcastRecord/openDialog';
		w = 1000;
		h = 600;
    } else if (name == "roamRecord") {//巡视记录
        url = jsConst.basePath + '/roamRecord/openDialog';
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
		title : $("#aqfk_" + name + " a").text(),
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
		debugger;
		// panel.panel("refresh", jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		console.log("aqfkInclude.jsp toAqfkDisplay spya jsConst.USER_LEVEL = " + jsConst.USER_LEVEL);
		if(jsConst.USER_LEVEL==1){//省局
			console.log("aqfkInclude.jsp toAqfkDisplay spya 刷新省局视频菜单");
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/prov/spya");
		}else{
			console.log("aqfkInclude.jsp toAqfkDisplay spya 刷新监狱视频菜单");
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
	// 广播播放
	else if (name == "broadcastPlay") {
		panel.panel("refresh",jsConst.basePath+"//broadcastLinkage/controlBroadcastTree");
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

/**
 * 跳转到垦华运维平台页面
 */
function openKhywpt() {
	var url = "http://210.192.10.242:9113/ssoservice/login?isAutoLogin=true&username_auto=admin&password_auto=jwcsfl3936&service=http://210.192.10.242:8890";
	window.open(url, "_blank");
}
</script>