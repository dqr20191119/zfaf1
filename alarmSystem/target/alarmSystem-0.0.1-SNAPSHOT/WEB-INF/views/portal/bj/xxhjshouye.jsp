<!DOCTYPE html>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">

<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	Map map = new HashMap();
	map.put("orgCode", user.getOrgCode());
	map.put("orgName", user.getOrgName());
	map.put("cusNumber", user.getCusNumber());
	map.put("userId", user.getUserId());
	map.put("userName", user.getUserName());
	map.put("realName", user.getRealName());
	map.put("policeNo", user.getPoliceNo());
	map.put("dprtmntCode", user.getDprtmntCode());
	map.put("dprtmntName", user.getDprtmntName());
	map.put("roles", user.getRoles());
	map.put("orgClassKey", user.getOrgClassKey());
	map.put("userLevel", user.getUserLevel());
	map.put("isSpecialPolice", user.getIsSpecialPolice());
	request.setAttribute("map", new JSONObject(map));
	request.setAttribute("jyId", user.getCusNumber());
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${map.orgName}信息汇聚</title>



<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/cui/cui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/css/dialog.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/css/zfxx_dialog.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/css/gerenminjing.css">

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/bj-cui/css/menhu_new.css" />
<style type="text/css">
.title {
	font-size: 25px;
	color: white;
}

body{
color: #696969;
}
</style>
</head>

<body>

	<cui:dialog id="dialog" autoOpen="false" iframePanel="true"
		reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<div class="container menhu">
		<header class="perspective">
			
			<div class="header">
				<div class="header-content">
					<div class="header-item"   >
						<span class="icon iconfont icon-supervise"></span>
						 <span class="icon iconfon" style="cursor: pointer;" onclick="openZnafpt()">返回</span>
					</div>
					<div class="header-item date">
						<span class="icon iconfont icon-datepiceker"></span> <span
							class="title">${dqrq}</span>
					</div>
					<div class="header-item">
						<span class="icon iconfont icon-police2"></span> <span
							class="title" id="dqyh1">当前用户：</span>
					</div>
					<div class="header-item dropdow" style="display:none;">
						<span class="icon iconfont icon-xialadown"></span>
					</div>
					<div class="header-item">
						<span class="icon iconfont icon-system-setting" title="退出系统"
							style="cursor: pointer;" onClick="syLogout();"></span>
					</div>
				</div>
			</div>
		</header>
		<div class="center">
			<div class="logo-wrapper little">
				<img src="${ctx}/static/bj-cui/img/menhu/logo_${map.orgCode}.png"
					alt="${map.orgName}智能安防平台 " class="logo">
			</div>

			<div class="content-wrapper menhu-subsystem">

				<div class="content-slide">
					<!-- 【信息汇聚 _菜单】 start -->
						<div class="content">
							<ul class="system">
								<sec:authorize url="/zhdd/sy/xxhj/wlryxx">
								 <li class="system-item" onClick="openMenuDialog(this, event,'wlry')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-psycho.png" alt="外來人员"><span
                                               class="title">外来人员信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/wlclxx"> 
								<li class="system-item" onClick="toForeignCarList1()">
	                                    <div class="system-content ">
	                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-public.png" alt="外来车俩统计"><span
	                                               class="title">外来车俩信息</span></div>
	                                    </div>
		                         </li>
		                         </sec:authorize>
		                         <sec:authorize url="/zhdd/sy/xxhj/xwzcxx">
								<li class="system-item" onClick="openMenuDialog(this, event,'xwzc1')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-clothes.png" alt="行为侦测统计"><span
                                               class="title">行为侦测信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/mjjcxx">
								 <li class="system-item" onClick="openMenuDialog(this,event,'mjkgjl')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-risk.png" alt="民警进出记录"><span
                                               class="title">民警进出信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/rlsbxx">
								<li class="system-item" onClick="openMenuDialog(this,event,'rlsb')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-remote.png" alt="警情统计"><span
                                               class="title">人脸识别信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/mjkqjl">
								<li class="system-item" onClick="openMenuDialog(this,event,'mjkq')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-data.png" alt="警情统计"><span
                                               class="title">民警考勤记录</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/zfdmjl">
								<li class="system-item" onClick="openMenuDialog(this, event,'zfdmDetails')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-deskdop.png" alt="警情统计"><span
                                               class="title">罪犯点名记录</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/dbswxx">
								<li class="system-item" onClick="openMenuDialog(this, event, 'swdbgd')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-monitor.png" alt="事物督办统计"><span
                                               class="title">督办事务信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/xxhj/jqczxx">
                                 <li class="system-item" onClick="openMenuDialog(this, event,'alarmRecord')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-name.png" alt="警情统计"><span
                                               class="title">警情处置信息</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
					              <%-- <li class="system-item" onClick="toPoliceList2()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-psycho.png" alt="民警信息统计"><span
					                      class="title">民警信息统计</span></div>
					                </div>
					              </li> --%>
                                <%-- <li class="system-item" onClick="toPrisonerList()">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-psycho.png" alt="罪犯信息统计"><span
                                                  class="title">罪犯信息统计</span></div>
                                    </div>
                                </li> --%>
                               
                                
                                
                               
                                
                               
                                
                                
 								
 								
							</ul>
						</div>
					<!-- 【信息汇聚 _菜单】 end -->

					<div class="content">
						<ul class="system">

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 隐藏于 -->
	<div style="display: none;">
		<!-- 用户所属监狱代码 -->
		<input type="hidden" id="cusNumber" value="${map.cusNumber}"></input>

		<!-- 用户所属部门代码 -->
		<input type="hidden" id="dprtmntCode" value="${map.dprtmntCode}"></input>
	</div>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/spon_base64.js"></script>

	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/zfxx.js" type="text/javascript"></script>
	<!-- app js define start  -->
	<script src="${ctx}/static/js/scripts/common.js"></script>
	<script src="${ctx}/static/resource/style/js/function.js"></script>
	<script src="${ctx}/static/js/scripts/prettify.js"></script>
	<!-- app js define  end  -->
	<script type="text/javascript"
		src="${ctx}/static/bj-cui/js/jyshouye.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/module/video/js/videoPlanTimer.js"></script>
	<script src="${ctx}/static/js/callback/callback.js"></script>
	<jsp:include page="../../include/videoInclude.jsp"></jsp:include>
	<jsp:include page="../../include/messageInclude.jsp"></jsp:include>

	<script src="${ctx}/static/system/videoClient.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/module/video/js/hz.mask.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/module/video/js/playbackVideo.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<!-- 4g执法 start-->
<script src="${ctx}/static/js/sgzf/base64.js"></script>
<script src="${ctx}/static/js/sgzf/es6-promise.min.js"></script>
<script src="${ctx}/static/js/sgzf/stomp.js"></script>
<script src="${ctx}/static/js/sgzf/tonmx_lib.min.js"></script>
<!-- 4g执法 end-->



	<script type="text/javascript">
	$(function() {
		var mma = ${map};
		initJSConstUserBean(mma);
		jsConst.basePath = "${ctx}/";
		initUserInfo1();
		
		var cusNumber = "${map.cusNumber}";
		var url;
		 
		//监狱多功能管理系统 ，只有岳阳监狱有
		if ("4312" == cusNumber) {//岳阳监狱
			$("#jydgnglxt").show();
		} else {
			$("#jydgnglxt").hide();
		}
	});
	</script>

	<script>
	
	
		function animateLeft(targetA) {
			$(targetA).css({
				"position" : "absolute"
			});
			$(targetA).animate({
				"left" : -500
			}, 1000)
		}
	
		function animateRight(targetB) {
			$(targetB).css({
				"position" : "absolute"
			});
			$(targetB).animate({
				"left" : 2000
			}, 1000)
		}
	
		function show_con() {
		
			var liTarget = $(e.currentTarget), allLi = allLiTarget, allLength = length;
			var index = $(".menhu .nav-item").index(liTarget);
			var k = 0, j = allLength;
			$(".visible").removeClass("visible");
			$(".menhu-subsystem .content").eq(index).addClass(
					"visible");
			if (index < 3) {
				for (k = 0; k <= index; k++) {
					animateLeft(allLi.eq(k))
				}
				for (j = allLength; j > index; j--) {
					animateRight(allLi.eq(j));
				}
				showSub();
				return;
			}
			for (k = 0; k < index; k++) {
				animateLeft(allLi.eq(k))
			}
			for (j = allLength; j >= index; j--) {
				animateRight(allLi.eq(j));
			}
			
			addScroll($(
			".menhu-subsystem .content .system")
			.eq(index));

		}	 
		
		
		show_con();
	
		function addScroll(target) {
			if (target.hasClass("mCustomScrollbar")) {
				target.mCustomScrollbar("destroy");
			}
			target.mCustomScrollbar({
				axis : "y",
				theme : "minimal-dark",
				scrollbarPosition : "outside",
			});
		}
		 



	 
		function showDqyh1() {
			 var userName = "<%=user.getRealName()%>";
			 var dprtmntName  = "<%=user.getDprtmntName()%>";
			 var policeNo  = "<%=user.getPoliceNo()%>";
			 $("#dqyh1").append( "<span class=\"user\">" +userName + "</span> （" +dprtmntName + "）<br>警号：" + policeNo); 

		}

       

		
        /**
         * 查询在岗民警
         */
        function toPoliceList2() {
        	// 民警编号或姓名
        	//var policeIdntyOrName = $("#mjzf").textbox("getValue");
        	
        	// 民警编号或姓名转Base64编码
        	//var policeIdntyOrNameBase64 = new Base64().multiEncode(policeIdntyOrName, 2);
        	// alert("policeIdntyOrName = " + policeIdntyOrName);
        	$.ajax({
        		type : 'post',
        		url : "${ctx}/xxhj/jnmj/queryDutyConfig",
        		data : {
        			cusNumber:jsConst.CUS_NUMBER
        		},
        		dataType : 'json',
        		success : function(data) {
        			var cusNumberFlag = "";
        			if(data) {
        				if(data.FLAG) {
        					cusNumberFlag = data.FLAG;
        				}
        			}
        			$("#dialog").dialog({
        				width : 1000, 
        				height : 800, 
        				title : '民警信息 ',
        				modal : true, 
        				autoOpen : false,
        				url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + jsConst.DEPARTMENT_ID + "&cusNumber=" + jsConst.CUS_NUMBER 
        			});
        			
        			$("#dialog").dialog("open");
        		},
        		error : function(XMLHttpRequest, textStatus, errorThrown) {
        			// jsConst.CUS_NUMBER_FLAG = "";
        		}
        	});
        } 
        
        
        /**
         * 弹出罪犯查询页面
         */
        function toPrisonerList() {
        	var cusNumber = "";
        	var prisonArea = "";
        	// 罪犯编号或姓名
        	//var prsnrIdntyOrName = $("#mjzf").textbox("getValue");
        	//prsnrIdntyOrName="";
        	var USER_LEVEL= jsConst.USER_LEVEL;
        	var prisonAreaName = "";
        	if(USER_LEVEL==3){
        		prisonAreaName = "${map.dprtmntName}";
        	}
        	/* jsConst.DEPARTMENT_ID */
        	// 罪犯编号或姓名转Base64编码
        	//var prsnrIdntyOrNameBase64 = new Base64().multiEncode(prsnrIdntyOrName, 2);
        	if(jsConst.USER_LEVEL != 1){
        		cusNumber = jsConst.CUS_NUMBER;
        		if(jsConst.USER_LEVEL == 3) {
        			prisonArea= jsConst.DEPARTMENT_ID; 
        		}
        	}
        	// alert("prsnrName = " + prsnrName);
        	$("#dialog").dialog({
        		width : 1200,
        		height : 800, 
        		title : '罪犯信息',
        		url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&cusNumber=" + cusNumber + "&drpmntName=" +encodeURI(encodeURI(prisonAreaName))
        	});
        	$("#dialog").dialog("open");
        }
        function openMenuDialog(obj, event, name) {

        	var event = window.event || event;
        	// event.stopPropagation();
        	if (event && event.stopPropagation) {
        		event.stopPropagation();
        	} else {
        		window.event.cancelBubble = true;
        	}
        	// event.preventDefault();
        	var url = "";
        	var w = null;
        	var h = null;
        	var title = null;
        	if (name == 'camera') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/jfsb/camera/list'
        	} else if (name == 'videoDevice') {
        		url = jsConst.basePath + '/sppz/videoDevice/list'
        	} else if (name == 'streamServer') {
        		url = jsConst.basePath + '/sppz/streamServer/list'
        	} else if (name == 'videoClient') {
        		url = jsConst.basePath + '/sppz/videoClient/list'
        	} else if (name == 'powerNetwork') {
        		url = jsConst.basePath + '/jfsb/powerNetwork/list'
        	} else if (name == 'physicalDevice') {
        		url = jsConst.basePath + '/wfsb/physicalDevice/list'
        	} else if (name == 'physicalDeviceName') {
        		url = jsConst.basePath + '/wfsb/physicalDeviceName/list'
        	} else if (name == 'dvcRole') {
        		url = jsConst.basePath + '/xtgl/dvcRole/index'
        	} else if (name == 'policeDevice') {
        		url = jsConst.basePath + '/wfsb/policeDevice/list'
        	} else if (name == 'broadcast') {
        		url = jsConst.basePath + '/broadcast/openDialog'
        	} else if (name == 'talkBackServer') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/talkBackServer/openDialog'
        	} else if (name == 'talkBackBase') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/talkBackBase/openDialog?type=0'
        	} else if (name == 'alertor') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/alertor/openDialog'
        	} else if (name == 'door') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/door/openDialog'
        	} else if (name == 'doorCtrl') {
        		url = jsConst.basePath + '/doorControl/openDialog'
        	} else if (name == 'screenPlan') {
        		url = jsConst.basePath + '/screenPlan/openDialog'
        	} else if (name == 'ewtcwh') {
        		url = jsConst.basePath + '/xtgl/planeLayer/index'
        	} else if (name == 'ewdwwh') {// //二维图层点位维护
        		url = jsConst.basePath + '/xtgl/planeLayerPoint/index'
        		w = 1400;
        		h = 620;
        	} else if (name == 'jswh') {
        		url = jsConst.basePath + '/xxhj/jswh/toIndex'
        		w = 1000;
        		h = 800;
        	} else if (name == 'regionDepart') {
        		url = jsConst.basePath + '/regionDepart/index';
        	} else if (name == "xxdy") {
        		url = jsConst.basePath + '/common/msgsubscribe/index';
        	} else if (name == "prisonPath") {
        		url = jsConst.basePath + '/prisonPath/openDialog';
        	} else if (name == "doorPlan") {
        		url = jsConst.basePath + '/door/plan/openDialog';
        	} else if (name == "crontab") {
        		url = jsConst.basePath + '/crontab/index';
        	} else if (name == "viewPeople") {
        		url = jsConst.basePath + '/viewPeople/index';
        	} else if (name == 'jdjc') {
        		url = jsConst.basePath + '/monitor/jdjc';
        		w = 1000;
        		h = 700;
        	} else if (name == 'jddlb') {
        		url = jsConst.basePath + '/monitor/jddlb';
        		w = 1000;
        		h = 700;
        	} else if (name == 'realTimeTalk') {
        		url = jsConst.basePath + '/realTimeTalk/openDialog';
        		w = 1000;
        		h = 600;
        	} else if (name == 'group') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/groupManage/index';
        	} else if (name == 'screenSwitch') {
        		url = jsConst.basePath + '/screenSwitch/openDialog';
        	} else if (name == 'wldctb-bj') {
        		url = jsConst.basePath + '/inspect/editDialog';
        	} else if (name == 'wldctb-lb') {
        		url = jsConst.basePath + '/inspect/inspectListDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'wldctb-sp') {
        		url = jsConst.basePath + '/inspect/checkDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'wldctb-hz') {
        		url = jsConst.basePath + '/inspect/recordDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'bddctb-bj') {
        		url = jsConst.basePath + '/inspectlocal/editDialog';
        	} else if (name == 'bddctb-sp') {
        		url = jsConst.basePath + '/inspectlocal/checkDialog';
        	} else if (name == 'bddctb-hz') {
        		url = jsConst.basePath + '/inspectlocal/recordDialog';
        	} else if (name == 'tbzg-fq') {
        		url = jsConst.basePath + '/xxyp/change/launchDialog';
        	} else if (name == 'tbzg-zg') {
        		url = jsConst.basePath + '/xxyp/change/changeDialog';
        	} else if (name == 'tbzg-sp') {
        		url = jsConst.basePath + '/xxyp/change/checkDialog';
        	} else if (name == 'tbzg-hz') {
        		url = jsConst.basePath + '/xxyp/change/recordDialog';
        	} else if (name == 'gwgl') {
        		url = jsConst.basePath + '/zbgl/gwgl/toIndex';
        	} else if (name == 'bcgl') {
        		url = jsConst.basePath + '/zbgl/bcgl/toIndex';
        	} else if (name == 'lbgl') {
        		url = jsConst.basePath + '/zbgl/lbgl/toIndex';
        	} else if (name == 'mbsz') {
        		url = jsConst.basePath + '/zbgl/mbsz/toIndex';
        	} else if (name == 'zbbp') {
        		url = jsConst.basePath + '/zbgl/zbbp/toIndex';
        	} else if (name == 'zbcx') {
        		url = jsConst.basePath + '/zbgl/zbcx/toIndex';
        	} else if (name == 'zbfx') {
        		url = jsConst.basePath + '/zbgl/zbfx/toIndex';
        	} else if (name == 'excel') {// excel
        		url = jsConst.basePath + '/zbgl/kspb/toIndex';
        	} else if (name == 'pjcjl') {
        		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord';
        	} else if (name == 'jcjl') {
        		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
        				+ jsConst.CUS_NUMBER;
        	} else if (name == 'jqjcjl') {
        		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
        				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        	} else if (name == 'jndt') {
        		url = jsConst.basePath + '/xxhj/jndt/toIndex';
        	} else if (name == 'gwgl') {
        		url = jsConst.basePath + '/zbgl/gwgl/toIndex';
        	} else if (name == 'bcgl') {
        		url = jsConst.basePath + '/zbgl/bcgl/toIndex';
        	} else if (name == 'lbgl') {
        		url = jsConst.basePath + '/zbgl/lbgl/toIndex';
        	} else if (name == 'mbsz') {
        		url = jsConst.basePath + '/zbgl/mbsz/toIndex';
        	} else if (name == 'zbbp') {
        		url = jsConst.basePath + '/zbgl/zbbp/toIndex';
        	} else if (name == 'zbcx') {
        		url = jsConst.basePath + '/zbgl/zbcx/toIndex';
        	} else if (name == 'zbfx') {
        		url = jsConst.basePath + '/zbgl/zbfx/toIndex';
        	} else if (name == 'mjczdj') {
        		url = jsConst.basePath + '/xxhj/mjczdj/openDialog';
        	} else if (name == 'jhrc') {
        		url = jsConst.basePath + '/xxhj/jhrc/toIndex';
        	} else if (name == 'cgsgxx') {
        		url = jsConst.basePath + '/xxhj/cgsgxx/toIndex';
        	} else if (name == 'sblxsz') {
        		url = jsConst.basePath + '/xxhj/zfsjsb/sblxsz/toIndex?cusNumber='
        				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        	} else if (name == 'sjsb') {
        		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toIndex?cusNumber='
        				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        	} else if (name == 'sjhz') {
        		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toSjhz?cusNumber='
        				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        	} else if (name == 'jdjc') {
        		url = jsConst.basePath + '/monitor/jdjc';
        		w = 1000;
        		h = 700;
        	} else if (name == 'jddlb') {
        		url = jsConst.basePath + '/monitor/jddlb';
        		w = 1000;
        		h = 700;
        	} else if (name == 'realTimeTalk') {
        		url = jsConst.basePath + '/realTimeTalk/openDialog';
        		w = 1000;
        		h = 600;
        	} else if (name == 'group') {
        		w = 1200;
        		h = 600;
        		url = jsConst.basePath + '/groupManage/index';
        	} else if (name == 'screenSwitch') {
        		url = jsConst.basePath + '/screenSwitch/openDialog';
        	} else if (name == 'wldctb-bj') {
        		url = jsConst.basePath + '/inspect/editDialog';
        	} else if (name == 'wldctb-lb') {
        		url = jsConst.basePath + '/inspect/inspectListDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'wldctb-sp') {
        		url = jsConst.basePath + '/inspect/checkDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'wldctb-hz') {
        		url = jsConst.basePath + '/inspect/recordDialog';
        		w = 1000;
        		h = 700;
        	} else if (name == 'bddctb-bj') {
        		url = jsConst.basePath + '/inspectlocal/editDialog';
        	} else if (name == 'bddctb-sp') {
        		url = jsConst.basePath + '/inspectlocal/checkDialog';
        	} else if (name == 'bddctb-hz') {
        		url = jsConst.basePath + '/inspectlocal/recordDialog';
        	} else if (name == 'tbzg-fq') {
        		url = jsConst.basePath + '/xxyp/change/launchDialog';
        	} else if (name == 'tbzg-zg') {
        		url = jsConst.basePath + '/xxyp/change/changeDialog';
        	} else if (name == 'tbzg-sp') {
        		url = jsConst.basePath + '/xxyp/change/checkDialog';
        	} else if (name == 'tbzg-hz') {
        		url = jsConst.basePath + '/xxyp/change/recordDialog';
        	} else if (name == 'rcs') {
        		url = jsConst.basePath + '/rcs/openDialog';
        		w = 1000;
        		h = 600;
        	} else if (name == 'alarmType') {
        		url = jsConst.basePath + '/alarmTypeAndLev/openDialog';
        	} else if (name == 'alarmPlan') {
        		url = jsConst.basePath + '/plan/openDialog'
        	} else if (name == 'affairsRecord') {
        		w = 700;
        		h = 500;
        		url = jsConst.basePath + '/deviceMaintain/openDialog/record';
        	} else if (name == 'affairsHandle') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/deviceMaintain/openDialog/handle';
        	} else if (name == 'affairsfeedBack') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/deviceMaintain/openDialog/feedback';
        	} else if (name == 'affairsOversee') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/deviceMaintain/openDialog/oversee';
        	} else if (name == 'affairsGather') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/deviceMaintain/openDialog/gather';
        	} else if (name == 'flows') {
        		url = jsConst.basePath + '/flow/list';
        	} else if (name == 'alarmRecord') {
        		w = 1200;
        		h = 800;
        		url = jsConst.basePath + "/alarm/openDialog/record?DpName=''";
        	} else if (name == 'alarmRecord1') {
        		w = 1200;
        		h = 800;
        		title = "一级警情";
        		url = jsConst.basePath + '/alarm/openDialog/record?type=1';
        	} else if (name == 'alarmRecord2') {
        		w = 1200;
        		h = 800;
        		title = "二级警情";
        		url = jsConst.basePath + '/alarm/openDialog/record?type=2';
        	} else if (name == 'alarmRecord3') {
        		w = 1200;
        		h = 800;
        		title = "三级警情";
        		url = jsConst.basePath + '/alarm/openDialog/record?type=3';
        	} else if (name == 'sporadicFlow') {
        		w = 1100;
        		h = 580;
        		url = jsConst.basePath + '/sporadicFlow/openDialog';
        	} else if (name == 'deviceRecord') {
        		w = 1100;
        		h = 600;
        		url = jsConst.basePath + '/deviceMaintain/record/openDialog';
        	} else if (name == 'faultType') {
        		url = jsConst.basePath + '/deviceFaultType/openDialog';
        	} else if (name == 'wlry') {
        		w = 1200;
        		h = 800;
        		title = "外来人员";
        		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=0';
        	} else if (name == 'wlry1') {
        		w = 1200;
        		h = 800;
        		title = "外来人员";
        		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=1';
        	} else if (name == 'wlrc') {
        		w = 1000;
        		h = 680;
        		url = jsConst.basePath + '/foreign/list';
        	} else if (name == 'wlcl') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/foreignCar/list';
        	} else if (name == 'dmls') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/callNames/openDialog/dmls';
        	} else if (name == 'fqdm') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/callNames/openDialog/fqdm';
        	} else if (name == 'rlzc') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/callNames/register/openDialog';
        	} else if (name == 'dmfq') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/callNames/master/openDialog';
        	} else if (name == 'dmjl') {
        		url = jsConst.basePath + '/callNames/master/openDialog/record';
        	} else if (name == 'zfjcj') {
        		url = jsConst.basePath + '/zfjcj/list';
        	} else if (name == "gzzgl") {
        		url = jsConst.basePath + '/yjct/gzzgl/index';
        	} else if (name == "wzgl") {
        		url = jsConst.basePath + '/yjct/wzgl/index';
        	} else if (name == "zjgl") {
        		url = jsConst.basePath + '/yjct/zjgl/index';
        	} else if (name == "fggl") {
        		url = jsConst.basePath + '/yjct/fggl/index';
        	} else if (name == "czffgl") {
        		url = jsConst.basePath + '/yjct/czffgl/index';
        	} else if (name == "yabz") {
        		url = jsConst.basePath + '/yjct/yabz/index';
        	} else if (name == "yasp") {
        		url = jsConst.basePath + '/yjct/yasp/index';
        	} else if (name == "yafb") {
        		url = jsConst.basePath + '/yjct/yafb/index';
        	} else if (name == "yljh") {
        		url = jsConst.basePath + '/yjct/yljh/index';
        	} else if (name == "ylsp") {
        		url = jsConst.basePath + '/yjct/ylsp/index';
        	} else if (name == "ylfb") {
        		url = jsConst.basePath + '/yjct/ylfb/index';
        	} else if (name == "zxyl") {

        	} else if (name == "yljl") {
        		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=2';
        	} else if (name == "yltj") {
        		url = jsConst.basePath + '/yjct/yjjl/toTj?type=2';
        	} else if (name == "czjl") {
        		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=1';
        	} else if (name == "cztj") {
        		url = jsConst.basePath + '/yjct/yjjl/toTj?type=1';
        	} else if (name == "xxdy") {
        		url = jsConst.basePath + '/yjct/msgsubscribe/index';
        	} else if (name == "yjctSszk") {
        		url = jsConst.basePath + '/yjct/sszk/toIndex';
        	} else if (name == 'yrzq') {
        		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=yzx';
        	} else if (name == 'zqgl') {
        		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=zqgl';
        	} else if (name == 'swdbgd') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/rwgl/rwjs/openDialog/index?type=swdb';
        	} else if (name == 'dayly') {
        		url = '${ctx}/xxyp/dayly/daylyLayout';
        		w = 1000;
        		h = 680;
        	} else if (name == 'xfrw') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/rwgl/rwxf/index';
        	} else if (name == 'jsrw') {
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/rwgl/rwjs/index';
        	} else if (name == 'jndtcx') {
        		url = jsConst.basePath + '/wghgl/yrzq/toList';
        	} else if (name == 'ccode') {// // 网格划分 网格管理分配格长
        		w = 1200;
        		h = 680;
        		url = jsConst.basePath + '/wghf/wgzrfp/toIndex';
        	} else if (name == 'xwzc') {
                w = 1200;
                h = 680;
        		title = "行为侦测";
        		url = jsConst.basePath + '/xwzc/toIndex?type=1';
        	} else if (name == 'xwzc1') {
        		url = jsConst.basePath + "/xwzc/toIndex?type=''";
        	} else if (name == 'mjkgjl') {
        		url = jsConst.basePath + '/xxhj/mjkgjl/toIndex';
        	} else if (name == 'swsb') {
        		// 生物识别
        		url = jsConst.basePath + '/policeLocation/openSwsbCountDialog';
        	} else if (name == "znys") {
        		// 智能钥匙
        		title = "智能钥匙";
        		url = jsConst.basePath + '/xxhj/znys/toIndex';
        	} else if (name == "mjxcjl") {
        		// 民警巡查记录
        		url = jsConst.basePath + '/xxhj/patrol/mjxcjl/toIndex';
        	} else if (name == "qjjcjl") {
        		// 清监检查
        		url = jsConst.basePath + '/wghgl/yrzq/qjjc/toIndex';
        	} else if (name == "xqdjlb") {
        		// 心情登记
        		url = jsConst.basePath + '/wghgl/yrzq/xqdjjl/toIndex';
        	} else if (name == "rlsb") {
        		url = jsConst.basePath + '/rlsb/toIndex';
        	} else if (name == "zfxsdm") {
        		// 罪犯巡视点名
        		url = jsConst.basePath + '/zfxx/zfXsdm/toIndex';
        	} else if (name == "zfzwdm") {
        		// 罪犯早晚点名
        		url = jsConst.basePath + '/zfxx/zfZwdm/toIndex';
        	} else if (name == 'mjkq') {
        		url = jsConst.basePath + '/xxhj/mjkq/toIndex';
        		// 民警考勤查询
        	} else if (name == 'wxxpg1') {
        		url = jsConst.basePath + '/aqfxyp/wxpg/toIndex?zt=0';
        		// 危险性评估
        	}else if (name == 'djwgzzwh') {
        		//党建网格组织维护
        		url = jsConst.basePath + '/djwg/zzwh/toIndex';
        	}else if (name == 'djwgcywh') {
        		//党建网格成员维护
        		url = jsConst.basePath + '/djwg/rywh/toIndex';
        	} else if (name == 'jctj') {
        		// 进出统计
        		url = jsConst.basePath + '/xxhj/jndt/jctj';
        	} else if (name == 'zfdmDetails') {
        		// 湖南罪犯点名详情
        		url = jsConst.basePath + '/zfxx/zfdm/detail';
        	} 
        	if (w == null || h == null) {
        		w = 1000;
        		h = 600;
        	}
        	if (title == null) {
        		title = $(obj).text();
        	}
        	$('#dialog').html("");
        	// $('#dialog').dialog("destroy");
        	$('#dialog').dialog({
        		width : w,
        		height : h,
        		title : title,
        		url : url
        	});
        	$("#dialog").dialog("open");
        	return;

        }
        function openZnafpt() {
        	//var dprtmntCode = jsConst.DEPARTMENT_ID;
        	//var url = "${ctx}/portal/bj/shouye";
        	// 部门是武警的时候，直接跳到综合首页
        	//if(dprtmntCode == '110537') {
        		url = "${ctx}/portal/zhshouye";
        	//}
        	window.location.href = url;
        }
        //外来车辆统计
        	function toForeignCarList1() {
        			$("#dialogId_rightHomeMenu").dialog({
        				width : 1000, //属性
        				height : 800, //属性
        				title : '外来车辆',
        				modal : true, //属性
        				autoOpen : false,
        				url : "${ctx}/foreign/openCarInfo?frType=1&date=0"
        			});
        			$("#dialogId_rightHomeMenu").dialog("open");
        		}
        //外来人员统计
        function toForeignPeopleList() {
        	$("#dialogId_rightHomeMenu").dialog({
        		width : 1000, //属性
        		height : 800, //属性
        		title : '外来人员',
        		modal : true, //属性
        		autoOpen : false,
        		url : "${ctx}/foreign/openPeopleInfo?flag=1"
        	});
        	$("#dialogId_rightHomeMenu").dialog("open");
        }
        

		
	</script>
</body>
</html>
