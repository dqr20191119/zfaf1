<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
%>
<html>

<head>
	<meta charset="utf-8">
	<title>${map.orgName}智能安防平台 </title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/mhsy2.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/zfxx_dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/gerenminjing.css">
</head>

<body>
	<div style="display: none;">
	<!-- 根路径 -->
	<input id="rootPath" name="rootPath" value="${ctx}" />
	</div>
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_edit" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_List" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
	<div id="dialog_mjxx"></div>
	
	<header class="perspective">
		<jsp:include page="bjheader.jsp"></jsp:include>
 	</header>
	<div class="container-box zhihui">
		<div id="layout1">
			<div data-options="region:'west'" style="width:345px;">
				<div class="left">
					<div class="left-top">
						<h3>今日值班</h3>
						<div id="todayDuty" >
							<ul>
								
							</ul>
						</div>
						<div id="todayDutyPoliceCount">
							<ul>
								<li>
									<div class="info-icon dutyIcon">
										<img src="${ctx}/static/bj-cui/img/command/leader.png" />
									</div>
									<div class="dutyPerson">
										<p class="dutyTitle" onclick="showPoliceCountInPrison()">监区在值警察</p>
										<p class="dutyNme" id="policeCountInPrison">0</p>
									</div>
								</li>
								<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<span><a href="javascript: void(0);" onclick="showPoliceCountZaiCe()" >监区在册警察</a></span>
											<p class="dutyNme" id="policeCountZaiCe">0</p>
										</div>
										
									</li>
									<!-- <li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<span><a href="javascript: void(0);" >监区备勤警察</a></span>
											<p class="dutyNme" id="policeCountBeiQin">0</p>
										</div>
									</li> -->
									
								</li>
							</ul>
						</div>
					</div>
					<div class="left-bottom">
					   <input id="mjtype"/>
						<input id="mjzf" type="text" />
						
						<!-- 今日押犯情况 start -->
						<h3>今日押犯情况</h3>
						<div class="realTimeAlert">
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJhzs">
										<p class="custodyTitle alertType">在册</p>
										  <p class="" style="" id="today1_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJhzs">
										<p class="custodyTitle alertType">暂予监外执行</p>
										  <p class="" style="" id="today15_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfYwjy">
										<p class="custodyTitle alertType">历年在逃</p>
										  <p class="" style="" id="today17_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTtrs">
										<p class="custodyTitle alertType">提回重审</p>
										  <p class="" style="" id="today16_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDcrs">
										<p class="custodyTitle alertType">离监就医</p>
										  <p class="" style="" id="today11_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSyrs">
										<p class="custodyTitle alertType">新收押总数</p>
										 <p class="" style="" id="today31_modify">0</p> 
									</div>
								</li>
								<!-- <li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSwrs">
										<p class="custodyTitle alertType">危险犯</p>
										 <p class="" style="" id="today33_modify">0</p>  
									</div>
								</li> -->

							</ul>
						</div>
						<div class="realTimeOffense">
							<ul>
								<li>
										<div class="info-icon custodyIcon alermIcon">
											<span class="iconfont icon-prisoner"></span>
										</div>
										<div class="custodyPerson alertShow">
											<p class="custodyTitle alertType">在押</p>
											 <p class="" style="cursor: pointer;color:red" id="today9_modify">0</p> 
										</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJwzx">
										<p class="custodyTitle alertType">监外就诊</p>
										 <p class="" style="cursor: pointer;color:red" id="today10_modify">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfWcjy">
										<p class="custodyTitle alertType">保外就医</p>
										 <p class="" style="cursor: pointer;color:red" id="today51_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTxlj">
										<p class="custodyTitle alertType">严管人数</p>
										<p class="" style="" id="today29_modify">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSfrs">
										<p class="custodyTitle alertType">禁闭人数</p>
										<p class="" style="" id="today28_modify">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="ZfQsrs">
										<p class="custodyTitle alertType">刑满释放</p>
										 <p class="" style="text-align:center;color:red;" id="today22_modify">0</p> 
									</div>
								</li>
								<!-- <li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDrrs">
										<p class="custodyTitle alertType">隔离审查</p>
										<p class="" style="" id="today29_modify">0</p> 
									</div>
								</li> -->
							</ul>
						</div>
						<!-- 今日押犯情况 end -->
						
					</div>
				</div>
			</div>
			<div data-options="region:'east'" style="width:277px;">
				<div class="right">
					<div class="on-duty right">
						<h2>一日执勤</h2>
						<div class="tolist" id="tolistDiv">
							
						</div>
						<ul class="duty-content">
								<div id="yrzqUl" class="mCSB_container" style="position:relative; top:0; left:0;" dir="ltr">
									
								</div>
							
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center'" class="main">
				<div class="top" id="top">
					<h3><span>危险评估</span><span>实时警情</span></h3>
					<div id="ltChart" class="ltChart top-box"></div>

					<div class="top-box top-right">
						<div class="real-time">
							<ul class="alarm">
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/emergency.png" alt="一级警情">
									<div class="alarm-info">
										<p class="alarm-mount emergency" id="lev_1" style="cursor: pointer" onClick="openMenuDialog(this, event,'alarmRecord1')">0</p>
										<p class="alarm-title">一级警情</p>
									</div>
								</li>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/danger.png" alt="二级警情">
									<div class="alarm-info">
										<p class="alarm-mount danger" id="lev_2" style="cursor: pointer" onClick="openMenuDialog(this, event,'alarmRecord2')">0</p>
										<p class="alarm-title">二级警情</p>
									</div>
								</li>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/warning.png" alt="三级警情">
									<div class="alarm-info">
										<p class="alarm-mount warning" id="lev_3" style="cursor: pointer" onClick="openMenuDialog(this, event,'alarmRecord3')">0</p>
										<p class="alarm-title">三级警情</p>
									</div>
								</li>
								<!-- <li class="alarm-item empty">
									<img class="alarm-img sd" src="${ctx}/static/bj-cui/img/sdian.png" alt="电网状态">
									<div class="alarm-info">
										<p class="alarm-mount dwzt">正常</p>
										<p class="alarm-title">电网状态</p>
									</div>
								</li> -->
								<li class="alarm-item empty">
								</li>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/watch.png" alt="事务督办">
									<div class="alarm-info">
										<p class="alarm-mount common">0</p>
										<p class="alarm-title">事务督办</p>
									</div>
								</li>
								<%-- <li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/device.png" alt="设备异常">
									<div class="alarm-info">
										<p class="alarm-mount common" id="sbyc">0</p>
										<p class="alarm-title">设备异常</p>
									</div>
								</li>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/car.png" alt="外来车辆">
									<div class="alarm-info">
										<p class="alarm-mount common" id="nowForeignCar" style="cursor: pointer">0</p>
										<p class="alarm-title">外来车辆</p>
									</div>
								</li>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/foreigner.png" alt="外来人员">
									<div class="alarm-info">
										<p class="alarm-mount common">0</p>
										<p class="alarm-title">外来人员</p>
									</div>
								</li> --%>
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/xwzc.png" alt="行为侦测">
									<div class="alarm-info">
										<p class="alarm-mount common" id="xwzc" style="cursor: pointer" onClick="openMenuDialog(this, event,'xwzc')">0</p>
										<p class="alarm-title">行为侦测</p>
									</div>
								</li>
								<!-- 
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/ffsj.png" alt="非法手机">
									<div class="alarm-info">
										<p class="alarm-mount common" id="ffsj" style="cursor: pointer"
											onclick="openFfsjtj()">0</p>
										<p class="alarm-title">非法手机</p>
									</div>
								</li> 
								<li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/ljff.png" alt="劳动工具">
									<div class="alarm-info">
										<p class="alarm-mount common" id="toolNum">0</p>
										<p class="alarm-title">劳动工具</p>
									</div>
								</li>-->
								<%-- <li class="alarm-item">
									<img class="alarm-img" src="${ctx}/static/bj-cui/img/mgc.png" alt="安检">
									<div class="alarm-info">
										<p class="alarm-mount common" id="securityCheckCount">0</p>
										<p class="alarm-title">安检</p>
									</div>
								</li> --%>
							</ul>
						</div>
					</div>
				</div>
				<div class="center-box clearfix">
					
					<div class="center-left">
						<h3>
							<span class="zwdm zwdmA">点名记录</span>
						</h3>
						<div class="left-box">
							<ul class="zfdm" id="zfdm">
								
							</ul>
						</div>
					</div>
					
					<div class="center-right" style="width:380px;">
						<h3>进出统计<span class="more" style="cursor: pointer" onClick="openMenuDialog(this, event,'jctj')">更多+</span></h3>
						<div class="right-box jndt-top">
							<div class="todo_List">
								<ul class="theader clearfix">
									<li>姓名</li>
									<li style='width:120px;'>监区</li>
									<li style='width:80px;'>场所</li>
									<li>时间</li>
								</ul>
								<div class="jndtScroll1" style="height:200px;">
									<ul class="tcontent1" id="jctjmx">
										
									</ul>
								</div>
							</div>
						</div>
						<!--  heqh注释掉，加入监外情况
						<div class="right-box jndt-bottom">
							<div class="jctjGrid todo_List">
								<ul class="theader clearfix">
									<li>监舍号</li>
									<li>总人数</li>
									<li>监舍</li>
									<li>劳动场所</li>
								</ul>
								<div class="jndtScroll" style="height:200px">
									<ul class="tcontent1" id="zffbqk">
										
									</ul>
								</div>
							</div>
						</div>
						 -->
						 <div class="right-box jndt-bottom">
							<div class="jctjGrid todo_List">
								<ul class="theader clearfix">
									<li>单位</li>
									<li>姓名</li>
									<li>类型</li>
									<li>事由</li>
								</ul>
								<div class="jndtScroll" style="height:200px">
									<ul class="tcontent1" id="zffbqk">
										
									</ul>
								</div>
							</div>
						</div>
						 
					</div>
				</div>
			
			</div>
		</div>
	</div>

	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/charts2.js?v=2" type="text/javascript"></script>
	<%-- <script src="${ctx}/static/bj-cui/js/charts.js" type="text/javascript"></script> --%>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/zfxx.js" type="text/javascript"></script>
	<!-- app js define start  -->
	<script src="${ctx}/static/js/scripts/common.js"></script>
	<script src="${ctx}/static/resource/style/js/function.js"></script>
	<script src="${ctx}/static/js/scripts/prettify.js"></script>
	<!-- app js define  end  -->
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/videoPlanTimer.js"></script>
	<script src="${ctx}/static/js/callback/callback.js"></script>
	<jsp:include page="../../include/videoInclude.jsp"></jsp:include>
	<jsp:include page="../../include/messageInclude.jsp"></jsp:include>

	<script src="${ctx}/static/system/videoClient.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/hz.mask.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/playbackVideo.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
 
 

	<script>
		jsConst.basePath = "${ctx}/";
		jsConst.ORG_CODE = "<%=user.getOrgCode()%>";					// 机构代码
		jsConst.CUS_NAME = "<%=user.getOrgName()%>";					// 机构名称
		jsConst.CUS_NUMBER = "<%=user.getCusNumber()%>";				// 机构代码
		jsConst.PRISON_ORG_CODE = "<%=user.getCusNumber()%>";			// 监狱机构代码
		jsConst.ROOT_ORGA_CODE = "<%=user.getCusNumber()%>";			// 当前选中监狱编号
	    jsConst.ROOT_ORGA_NAME = "<%=user.getOrgName()%>";				// 当前选中监狱名称
		jsConst.USER_ID = "<%=user.getUserId()%>";						// 用户ID
		jsConst.USER_NAME = "<%=user.getUserName()%>";					// 用户名
		jsConst.REAL_NAME = "<%=user.getRealName()%>";					// 用户真实姓名
		jsConst.POLICE_CODE = "<%=user.getPoliceNo()%>";				// 警员编号
		jsConst.DEPARTMENT_ID = "<%=user.getDprtmntCode()%>";			// 部门Code
		jsConst.DEPARTMENT_NAME = "<%=user.getDprtmntName()%>";			// 部门名称
		jsConst.ROLE_LIST = "<%=user.getRoles()%>";
		jsConst.USER_LEVEL = "<%=user.getUserLevel()%>";				// 用户等级
		jsConst.LOGIN_USER_KEY = "<%=user.getPoliceNo()%>";				// 登录用户标识(前面用userId的数据有问题的改库)
		jsConst.SPECIAL_POLICE = "<%=user.getIsSpecialPolice()%>";		// 是否特警队员
	</script>
	<script src="${ctx}/static/system/common.js"></script>
	 
	<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
	<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
	<script src="${ctx}/static/js/map/prisonmap.js"></script>
	<!-- End add by linhe 2018-01-09 for request ajax and 3d map -->
	
	<!-- 4g执法 start-->
	<script src="${ctx}/static/js/sgzf/base64.js"></script>
	<script src="${ctx}/static/js/sgzf/es6-promise.min.js"></script>
	<script src="${ctx}/static/js/sgzf/stomp.js"></script>
	<script src="${ctx}/static/js/sgzf/tonmx_lib.min.js"></script>
	<!-- 4g执法 end--> 

	<!-- add by zk start -->
	<jsp:include page="../../include/videoInclude.jsp"></jsp:include>
	<!-- add by zk end -->
	<jsp:include page="../../include/messageInclude.jsp"></jsp:include>
	
	<script type="text/javascript">
	$.parseDone(function() {
		//行为侦测
		searchXwzcCount();
		
		// 加载今日值班数据
		loadTodayDutyData();
		loadRealTimeJqData();

		// 定时刷监内动态
		refreshDiv();
		setInterval("refreshDiv();",60000);
		
		showYrzqContent();//初始化一日执勤
		
		jctj();
		zfszqytj();
		

		//加载今日压犯情况
		loadJryfqk();
		//劳动工具
		toolCount();
		//设备异常
		querySbyc();
	});
	
	/**
	 * 劳动工具
	 */
	function toolCount() {
	    $.ajax({
	        type : "post",
	        url : "${ctx}/tool/all/getToolNum",
	        data : {
	            cusNumber:jsConst.CUS_NUMBER
	        },
	        dataType : "json",
	        success : function(data) {

	            if (data.toolNum == 0) {
	                $("#toolNum").html(data.toolNum);
	            } else {
	                $("#toolNum").empty();
	                $("#toolNum").append("<a href='javascript:void(0);' style='color:#00FFF6;font-size: 30px;' onclick='toToolList()'>"+ data.toolNum + "</a>");
	            }
	        },
	    });
	};
	
	/**
	 * 劳动工具列表
	 */
	function toToolList() {
	    $("#dialogId_rightHomeMenu").dialog({
	        width : 1500, //属性
	        height : 800, //属性
	        title : '劳动工具',
	        modal : true, //属性
	        autoOpen : false,
	        url : "${ctx}/tool/all/openDialog"
	    });
	    $("#dialogId_rightHomeMenu").dialog("open");
	};
	
	//设备异常
	function querySbyc() { 
	 	 $.ajax({
			type : "post",
			url :  "${ctx}/deviceMaintain/record/listAll.json?dmrCusNumber=" + jsConst.CUS_NUMBER,
			dataType : "json",
			success : function(data) {
				$("#sbyc").text(data.total);
				 
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				console.log(errorThrown);
				console.log("获取异常");
			} 
	 	}); 
	};
	
	function jctj() {
		$.ajax({
			type : 'post',
			url : jsConst.basePath + "/jctj/jcjl/getJctj",
			dataType : 'json',
			data : {
				P_pageNumber:"1",
				P_pagesize:"200"
			},
			success : function(data) {
				if (data) {
					$("#jctjmx").html("");
					for (var i = 0; i < data.length && i < 200; i++) {
						var li = $("<li></li>");
						li.append("<span style='height:24px;'>" + data[i].zfXm + "</span>");
						li.append("<span style='width:120px;'>" + data[i].jqName + "</span>");
						li.append("<span style='width:80px;'>" + data[i].cs + "</span>");
						li.append("<span>" + data[i].time + "</span>");
						$("#jctjmx").append(li);
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	};
	
	
	
	//20190923， heqh 吧进出的记录修改成外出就诊和住院数据
	function zfszqytj() {
		$.ajax({
			type : 'post',
			url : '${ctx}/rwgl/rwjs/searchJwqk.json',
			dataType : 'json',
			success : function(data) {
				$("#zffbqk").html("");
				for(var i=0; i<data.length; i++){
					var li = $("<li></li>");
					li.append("<span style='width:16%'>" + data[i].JYNAME + "</span>");
					li.append("<span style='width:26%'>" + data[i].XM + "</span>");
					li.append("<span style='width:26%'>" + data[i].FLAG + "</span>");
					li.append("<span style='width:26%'>" + data[i].SY + "</span>");
					$("#zffbqk").append(li);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
		 
	};
	
/* 	$("#mjzf").textbox({
		buttons: [{
			text: false,
			icons: "iconfont icon-sousuo",
			click: function (e, data) {
			}
		}]
	}); */
	$(".jndtScroll").mCustomScrollbar({
		axis: "y",
		theme: "minimal-dark",
		scrollbarPosition: "outside",
	});
	$(".jndtScroll1").mCustomScrollbar({
		axis: "y",
		theme: "minimal-dark",
		scrollbarPosition: "outside",
	});
	
	function loadRealTimeJqData() {
		// 1、2、3级警情
		queryAlarmLevRecord();
		
		// 外来车辆、外来人员
		//showPeopleAndCarCount();
		
		// 安检
		//showSecurityCheckCount();
	}
	
	/**
	 * 加载今日值班数据
	 */
	function loadTodayDutyData() {
		// 监区今日值班
		loadJqTodayDutyData();
		
		// 在监民警数量
		queryPoliceCountInPrison();
		
		//在册民警数量
		queryPoliceCountZaiCe();
		//备勤警察数据
		//queryPoliceCountBaiQin();
	}

	/**
	 * 加载当前用户所在监区的今日值班数据
	 */
	function loadJqTodayDutyData() {
		$.ajax({
			type : "post",
	        url : "${ctx}/zbgl/todayDuty/getCurrentDeptTodayDuty",
	        data: {},
			dataType : "json",
			success : function(data) {
	            if(data.code == 200){
	            	var deptDutyList = data.data;
	                if (deptDutyList != null && deptDutyList.length > 0) {
	                	showJqTodayDutyData(deptDutyList);
	                }
	            }else if(data.code == 500){
	    			$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	/**
	 * 功能描述：初始化今日值班列表显示
	 */
	function showJqTodayDutyData(deptDutyList) {

		// 值班人员列表容器
		var itemContainer = $("div[id='todayDuty']").find("ul:eq(0)");
		
		// 部门今日值班显示模板
		var itemTemplate = '<li style="height:60px;"><div class="info-icon dutyIcon"><img src=""></img></div>' + 
			'<div class="dutyPerson">' + 
			'<p class="dutyTitle"><span><a href="javascript: void(0);">更多+</a></span></p>' + 
			'<p class="dutyNme"></p>' + 
			'</div></li>';
		
		// 初始化部门今日值班列表
		itemContainer.html('');
		if(deptDutyList != null && deptDutyList.length > 0) {
			var size = deptDutyList.length < 4 ? deptDutyList.length : 4;
			$("#todayDuty").height(size * 60);
			$.each(deptDutyList, function (i, deptDuty) {
				var dutyJob = deptDuty.dutyJob;
				var dutyList = deptDuty.dutyList;
				
				var item = $(itemTemplate).clone();
				item.find("div[class='info-icon dutyIcon']").find("img").attr("src", "${ctx}/static/bj-cui/img/command/leader.png");
				item.find("p[class='dutyTitle']").prepend(dutyJob.cdjJobName);
				item.find("p[class='dutyTitle']").find("span:eq(0)").find("a:eq(0)").attr("onClick", "showJqDutyJobList(\'" + dutyJob.dcdDprtmntId + "\',\'" + dutyJob.id + "\',\'" + dutyJob.cdjJobName + "\')");
				
				if(dutyList != null && dutyList.length > 0) {
	                var showIndex = dutyList.length > 4 ? 4 : dutyList.length;
	                for(var j=0; j<showIndex; j++) {
						item.find("p[class='dutyNme']").append("<a href='javascript:void(0);'>"+ dutyList[j].dbdStaffName + "</a>&nbsp;");
	                }
				}
				itemContainer.append(item);
			});
		}
	}

	/**
	 * 监区今日值班列表展示
	 */
	function showJqDutyJobList(deptCode, dutyJobId, dutyJobName) {
		$("#dialogId_rightHomeMenu").dialog({
			width : 1000, //属性
			height : 800, //属性
			title : '今日值班列表-' + dutyJobName,
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/zbgl/todayDuty/toIndex?deptCode=" + deptCode + "&dutyJobId=" + dutyJobId
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}

		/**组件库初始化方法*/
		$('#layout1').layout({
			fit: true, //属性: 值
			onCreate: function () {
				//获取单个li高度
				var liHeight = $(".duty-content .yrzq").eq(0).outerHeight(true);
				$(".duty-content").mCustomScrollbar({
					axis: "y",
					theme: "minimal-dark",
					scrollbarPosition: "outside",
				});
				$(".duty-content .yrzq").css("height", liHeight);
			}
		});
		$('#layout-child').layout({
			fit: true, //属性: 值
		});
		$("#mjtype").combobox({
			value:"mj",
			data:[{
				value:"mj",
				text:"民警"
			},{
				value:"zf",
				text:"罪犯"
			}]
		})
		$("#mjzf").textbox({
			buttons: [{
				text: false,
				icons: "iconfont icon-sousuo",
				click: function (e, data) {
					toPoliceOrPrisonerList();
				}
			}]
		})

		$('#tabs1').tabs({
			heightStyle: 'fill', //属性: 值
			onActivate: function () { //回调事件: 方法
			}
		});

		$("#fragment-1").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		$("#fragment-2").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		$(".left-box").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		$(".tcontent").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		$("#todayDuty").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		/**设置右侧滚动指定距离*/
		function scrollTop(index) {
			//获取单个li高度
			var liHeight = $(".duty-content .yrzq").eq(0).outerHeight(true);
			//index为向上滚动li的个数
			$(".duty-content").mCustomScrollbar("scrollTo", liHeight * index);
		}

		/**生物识别和监内动态向上滚动的方法，每次向上滚动一条*/
		moveLY = 17, moveRY = 17;

		function autoScrollL() {
			var height = $("#autoScrollL").children("li").outerHeight() + 5;
			moveLY += height;
			$("#autoScrollL")[0].style.transform = "translateY(-" + moveLY + "px)";

		}

		function autoScrollR() {
			var height = $("#autoScrollR").children("li").height() + 5;
			moveRY += height;
			$("#autoScrollR")[0].style.transform = "translateY(-" + moveRY + "px)";
		}
		
		$(function () {
			jsConst.basePath = "${ctx}/";

			initUserInfo();
			//早晚点名
			refreshZwdm();
		});
		
		 
		function toMenuDisplay(name) {
			
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
			
			else if (name == "pjnmj") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/pjnmj");
			} else if (name == "jnmj") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/jnmj");
			} else if (name == "jqjnmj") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/jqjnmj");
			} else if (name == "pjryf") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jryf/pjryf");
			} else if (name == "jryf") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jryf/jryf");
			} else if (name == "pwfsb") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/wfsb/pwfsb");
			} else if (name == "wfsb") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/wfsb/wfsb");
			} else if (name == "pjfsb") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jfsb/pjfsb");
			} else if (name == "jfsb") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/jfsb/jfsb");
			} else if (name == "pzfjbxx") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/pzfjbxx");
			} else if (name == "zfjbxx") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/zfjbxx");
			} else if (name == "jqzfjbxx") {
				panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/jqzfjbxx");
			}
			else if (name == "alarmProcess") {
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
			}else if (name == "splx") {
				var params={
						"grpId":null
				}
				openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
			}
			else if (name == "alarmProcessMan") {
				panel.panel("refresh", jsConst.basePath
						+ "/alarm/handle/index?type=1");
			}
			else if (name == "zxyl") {
				panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=2");
			}else if (name == "sjcz") {
				panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=1");
			}
			else if (jsConst.MAP_TYPE == 0 && name != 'qywh') {
	            $.messageQueue({message: "请先切换为三维地图", cls: "warning", iframePanel: true, type: "info"});
	            return;
	        }

	        //模型维护
	        else if (name == 'mxwh') {
	            panel.panel("refresh", jsConst.basePath + "/model/index");
	        } else if (name == 'qywh') {//区域维护
	            panel.panel("refresh", jsConst.basePath + "/region/index");
	        } else if (name == 'sjwh') {//视角维护
	            panel.panel("refresh", jsConst.basePath + "/view/index");
	        } else if (name == 'dwwh') {//点位维护
	            panel.panel("refresh", jsConst.basePath + "/point/index");
	        } else if (name == 'bjtcwh') {//报警图层维护
	            panel.panel("refresh", jsConst.basePath + "/layer/index");
	        } else if (name == 'xswh') {//巡视维护
	            panel.panel("refresh", jsConst.basePath + "/route/walkManager");
	        } else if (name == 'bqwh') {//标签维护
	            panel.panel("refresh", jsConst.basePath + "/labels/labelManager");
	        } else if (name == 'jsxxdwwh') {//监舍信息点位维护
	            panel.panel("refresh", jsConst.basePath + "/point/index2");
	        }
			
			
		}
		
		//携带参数打开右侧视图 add by zk
		function openViewToRightAddParam(viewName, params) {
			var panel = $("#layout1").layout("panel", "east");
			panel.panel("refresh", '${ctx}/menubar/displayRightAddParam?viewName=' + viewName + '&params=' + encodeURI(JSON.stringify(params)));
		}
		
		function loadRightData() {

			var USER_LEVEL= jsConst.USER_LEVEL; 
			var cusNumber = jsConst.CUS_NUMBER;
			
			//判断省局监狱监狱数据查询权限
			if(USER_LEVEL == 1 || USER_LEVEL == 2) {     //如果是省局、监狱用户则加载数据
				var url="${ctx}/xxhj/sy/countSyCount";
				$.ajax({
					type : "post",
					url : url,
					data: {
						   "cusNumber" : cusNumber,
						   "cdjJobCode" : "ZYLD/DBLD/ZHZX"
						  },  
					dataType : "json",
					success : function(data) {
						
						if (data != "" && data != null) {
							//showTodayDutyPolice(data.ZYLD.staffName,data.DBLD.staffName,data.ZHZX.staffName)
							//showInPoliceCount(data.current_insidePoliceCount);
							//parsePrisonerCount(data.current_PrisonerCount);
							// showKeynotePrisoner(data.keynotePrisoner);
						} 
						//查询外来车辆和人
						// showPeopleAndCarCount();
					},
				});
			}else if(USER_LEVEL == 3) {//如果是监区，加载监区数据
				
			}
		}
		
		/**
		 * 显示今日值班民警
		 */
	 	function showTodayDutyPolice(ZYLD,DBLD,ZHZX) {
	 		
	 	 	$("#ZYLD").html(ZYLD);
	 		$("#DBLD").html(DBLD);
	 		$("#ZHZX").html(ZHZX);
	 	}
		
	 	/**
		 * 显示当前在监民警人数
		 */
		function showInPoliceCount(data){
			$("#current_insidePoliceCount").html("<a href='javascript:void(0);' style='font-size: 20px;'>388</a>");
		
			/* if((typeof data) == "string") {
				data = JSON.parse(data).obj.data;
			}
			var config = data.config;
			var count = data.INSIDE_POLICE_COUNT;
			if (count == 0) {
				
				$("#current_insidePoliceCount").empty();
				$("#current_insidePoliceCount").html("<a href='javascript:void(0);' style='font-size: 20px;'>" + count + "</a>");
			} else {
				$("#current_insidePoliceCount").empty();
				$("#current_insidePoliceCount").html("<a href='javascript:void(0);' onclick='toPoliceList("+config+")' style='margin-right: 10px; font-size: 20px;'>" + count + "</a>");
			} */
		}
	 	
		function toPoliceList(config) {
			
			if(!config){
				config = '';
			}
			var drptmntId = "";
			var cusNumber =(jsConst.CUS_NUMBER== "1100" ? "" : jsConst.CUS_NUMBER)
			
			$("#dialogId_rightHomeMenu").dialog({
				width : 1200, 
				height : 800, 
				title : '民警信息 ',
				modal : true, 
				autoOpen : false,
				url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?query=1&config="+config+"&drptmntId="+ drptmntId + "&cusNumber=" + cusNumber,
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		}
		
		/**
		 * 显示该监狱实时押犯
		 */
		function parsePrisonerCount(countList) {
			
			var allCount;                                     //罪犯总数
			var WAFCount;                                     //危安犯总数
	 		for (var i = 0; i<countList.length; i++) {
				
				var key = countList[i].LB; 
				var count =countList[i].RS; 
				
				if (key == 1) {                                   //实押罪犯总数
					allCount = count+"";
					var arry = allCount.split(""),arryHtml = [];
					for(var j in arry) {
						arryHtml.push("<span class='inside-detail'>"+arry[j]+"</span>");
					}
					// $("#inside").html(arryHtml.join(""));
					$("#inside").html(885);
					
				} else if (key == 2) {                           //实押危安犯总数
					
					WAFCount = count;
					$("#WAFCount").html(WAFCount);
					
				}else if (key == 4) {                            //加戴戒具罪犯数
					
					//$("#GDJJCount").html(count);
					$("#GDJJCount").html(0);
				
				} else if (key == 5) {                          //关押禁闭罪犯数
					
					//$("#GYJBCount").html(count);
					$("#GYJBCount").html(0);

				} else if (key == 6) {                         //隔离审查罪犯数
					
					//$("#GLSCCount").html(count);
					$("#GLSCCount").html(0);
						
				} else if (key == 7) {                         //立案侦查罪犯数
					
					// $("#LAZCCount").html(count);
					$("#LAZCCount").html(0);
				
				} else if (key == 8) {                         //解回重审罪犯数
					
					// $("#JHCSCount").html(count);
					$("#JHCSCount").html(0);
				
				} else if (key == 9) {                       //暂予监外执行罪犯数
					
					// $("#ZYJWZXCount").html(count);
					$("#ZYJWZXCount").html(0);
				
				} else if (key == 10) {                          //老病残罪犯数
					
					// $("#LBCCount").html(count);
					$("#LBCCount").html(0);
				} 
			}
		}
		
		function queryAlarmLevRecord() {
			$.ajax({
				type : "post",
				url : "${ctx}/alarm/queryJqAlarmLevRecord.json?cusNumber=" + jsConst.CUS_NUMBER+"&dprtmntId="+jsConst.DEPARTMENT_ID,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						 $("#lev_1").text(data.obj.lev_1);
						 $("#lev_2").text(data.obj.lev_2);
						 $("#lev_3").text(data.obj.lev_3);
					} else {
						//$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		};
		
		 /**
	     * 加载外来车辆外来人员数量
	     */
		function showPeopleAndCarCount() {
			
	        var beginTime = new Date();
	        beginTime.setHours(0);
	        beginTime.setMinutes(0);
	        $.ajax({
	            type : "post",
	            url : "${ctx}/common/all/getPeopleAndCarCount?cusNumber="+jsConst.CUS_NUMBER+"&beginTime="+beginTime,
	            dataType : "json",
	            success : function(data) {
	            	
	                if (data.carCount == 0) {
	                    $("#nowForeignCar").html(data.carCount);
	                } else {
	                    $("#nowForeignCar").empty();
	                    $("#nowForeignCar").append("<a href='javascript:void(0);'  onclick='toForeignCarList()'>"+ data.carCount + "</a>");
	                }
	                if (data.peopleCount == 0) {
	                    $("#nowFPerson").html(data.peopleCount);
	                } else {
	                    $("#nowFPerson").empty();
	                    $("#nowFPerson").append("<a href='javascript:void(0);'  onclick='toForeignPeopleList()'>"+ data.peopleCount + "</a>");
	                }
	            },
	        });
	    }
		 
		function toForeignCarList() {
			$("#dialogId_rightHomeMenu").dialog({
				width : 1000, //属性
				height : 800, //属性
				title : '外来车辆',
				modal : true, //属性
				autoOpen : false,
				url : "${ctx}/foreign/openCarInfo?frType=1&date=1"
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		}
		 
		 
		
		function showDqyh() {
			$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
		
		 
		 
		/**
		 * 查询民警或罪犯信息
		 */
		function toPoliceOrPrisonerList() {
			var mjtype = $("#mjtype").combobox("getValue");
			if("mj" == mjtype) {
				toPoliceList2();
			} else if("zf" == mjtype) {
				toPrisonerList();
			}
		}
		 
		function openYysbxt() {
			
			var url = "${ctx}/portal/yysb/shouye";
			window.open(url, "_self");
		}
		
		function openMjxx(type) {
			
			var url;
			if(type == 1) {
				url = "${ctx}/portal/bj/mjxx";
			} else {
				url = "${ctx}/portal/bj/zfxx";
			}
			
			$("#dialog_mjxx").dialog({
		      width: "80%",
		      height: "90%",
		      resizable: false,
		      autoDestroy: true,
		      componentCls: "custom dark", //对话框风格，引入dialog.css后需要加上此属性
		      asyncType: "get",            //ajax请求的dataType
		      autoOpen: false,
		      modal: true,
		      url: url,
		      onOpen: function() {         //对话框打开时自动触发的回调事件
		      }
		    });
			$("#dialog_mjxx").dialog("reload");
	        $("#dialog_mjxx").dialog("open")
		}

		/**
		 * 临时代码：RCS通讯融合测试
		 */
		function rcsCall(callObject) {
			var ur = '${ctx}/rcs/startCall';
			$.ajax({
				type : 'post',
				url : ur,
				data : {
					cusNumber:"1105",
					cmd:"2",
					userNum:"013917859929,013916245071",
					content:"通讯融合测试，内容：呼叫" + callObject + "",
					position:""
				},
				dataType : 'json',
				success : function(data) {
		            if(data.code == 200){
		                alert(data.message);
		            }else if(data.code == 500){
		                alert("error");
		            }
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}
		 

		
		 
		/**
		 * 查询在监民警数量
		 */
		function queryPoliceCountInPrison() {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER
	            },
				dataType : "json",
				success : function(data) {
					$("#policeCountInPrison").html(data.policeCountInPrison);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		/**
		 * 查询在册民警数量
		 */
		function queryPoliceCountZaiCe() {
			//console.log("===="+jsConst.CUS_NUMBER+"====="+jsConst.DEPARTMENT_ID )
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountZaiCe",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER,
	            },
				dataType : "json",
				success : function(data) {
					//console.log(data+"在册民警")
					console.log(data.policeCountZaiCe)
					$("#policeCountZaiCe").html(data.policeCountZaiCe);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		/**
		 * 查询备勤民警数量
		 */
		function queryPoliceCountBaiQin() {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountBeiQin",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER,
	            },
				dataType : "json",
				success : function(data) {
					//console.log(data+"在册民警")
					//console.log(data.policeCountZaiCe)
					$("#policeCountBeiQin").html(data.policeCountBeiQin);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		/**
		 * 显示在监民警数据列表
		 */
		function toPoliceInPrisonList() {
			$("#dialogId_rightHomeMenu").dialog({
				width : 1000, //属性
				height : 800, //属性
				title : '在监民警列表',
				modal : true, //属性
				autoOpen : false,
				url : "${ctx}/policeLocation/openDialog"
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		}
		function refreshZwdm() {
			$.ajax({
				type : 'post',
				url : '${ctx}/zfxx/zfZwdm/searchZwdm',
				dataType : 'json',
				success : function(data) {
					var divN = "";
					if(data.code == 200) {
						var list = data.data;
						for(var i=0; i<list.length; i++) {
							divN = divN + '<li>';
							divN = divN + '<div class="jianshe">';
							divN = divN + '<span>' + list[i].deptname + '监舍</span>';
							divN = divN + '<span style="width:85px;">';
							divN = divN + '<i class="iconfont icon-rlzq"></i>';
							divN = divN + '<i>人脸抓图</i>';
							divN = divN + '</span>';
							divN = divN + '</div>';
							divN = divN + '<div class="tip ' + list[i].corpid + '">';
							divN = divN + '<span>' + list[i].sdrs + '/' + list[i].zrs + '</span>';
							divN = divN + '<span class="iconfont icon-userIcon"></span>';
							divN = divN + '</div>';
							divN = divN + '<div class="status ' + list[i].corpid + '">';
							divN = divN + '<span class="tool-status"></span>';
							divN = divN + '<span>状态：' + list[i].dmlx + '</span>';
							divN = divN + '</div>';
							divN = divN + '</li>';
						}
					}
					$("#zfdm").html("");
					$("#zfdm").append(divN);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
		
	/* 	 
		function toPoliceList2() {
			var policeIdntyOrName = $("#mjzf").textbox("getValue");
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
						url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + jsConst.DEPARTMENT_ID + "&cusNumber=" + jsConst.CUS_NUMBER + "&policeIdntyOrName=" + policeIdntyOrName
					});
					
					$("#dialog").dialog("open");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
		
	 
		 function toPrisonerList() {
	    	var cusNumber = "";
	    	var prisonArea = "";
			var prsnrIdntyOrName = $("#mjzf").textbox("getValue");
			if(jsConst.USER_LEVEL != 1){
				cusNumber = jsConst.CUS_NUMBER;
				if(jsConst.USER_LEVEL == 3) {
					prisonArea= jsConst.DEPARTMENT_ID; 
	 			}
			}
			$("#dialog").dialog({
				width : 1200,
				height : 800, 
				title : '罪犯信息',
				url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&cusNumber=" + cusNumber + "&prsnrIdntyOrName=" + prsnrIdntyOrName
			});
			$("#dialog").dialog("open");
		} */
		
		 
		
			 /**
		     * 加载外来车辆外来人员数量
		     */
			function showSecurityCheckCount() {
		        $.ajax({
		            type : "post",
		            url : "${ctx}/common/all/getSecurityCheckCount",
		            dataType : "json",
		            success : function(data) {
		            	
		                if (data.carCount == 0) {
		                    $("#securityCheckCount").html(data.securityCheckCount);
		                } else {
		                    $("#securityCheckCount").empty();
		                    $("#securityCheckCount").append("<a href='javascript:void(0);' onclick='toSecurityCheckList()'>"+ data.securityCheckCount + "</a>");
		                }
		            },
		        });
		    }
			function toSecurityCheckList() {
				$("#dialogId_rightHomeMenu").dialog({
					width : 1000, //属性
					height : 800, //属性
					title : '结果',
					modal : true, //属性
					autoOpen : false,
					url : "${ctx}/securityCheck/openDialog"
				});
				$("#dialogId_rightHomeMenu").dialog("open");
			}
			
				
			/**
			 * 一日执勤
			 */
			function showYrzqContent() {
		        $.ajax({
		            type : "post",
		            url : "${ctx}/wghgl/yrzq/searchData.json",
		            dataType : "json",
		            success : function(data) {
		            	//alert(data.length);
		            	var jrsw=data.length;//今日事务
		            	var yzx=0;//已执行
		            	var wzx=0;//未执行
		            	var ycsw=0;//异常事务
		            	
		                var yrzqUlHtm="";
		                for(var i=0;i<data.length;i++){
		                	var startTime=$.trim(data[i].START_TIME).substring(11,16);//开始时间
		                	var endTime=$.trim(data[i].END_TIME).substring(11,16);//结束时间
		                	var state=$.trim(data[i].STATE);//任务状态l;0：待执行（默认）；1：执行中；2：已完成；
		                	var isTimeout=$.trim(data[i].IS_TIMEOUT);//是否超时；0：未超时（默认）；1：已超时；
		                	var dataType=$.trim(data[i].DATA_TYPE);//数据来源；0：本部门的计划日程1：事务督办2：任务下达3：视频督察4：设备维修5：风险防控措施
		                	var title=$.trim(data[i].TITLE);//名称
		                	var fxcjId = $.trim(data[i].FXCJ_ID)//风险采集id
		                	var tzsj = $.trim(data[i].TZSJ)//巡更通知时间
		                	var sxsj = $.trim(data[i].SXSJ);//生效时间
		                	// alert("dataType---"+$.trim(dataType)+"---"+state+"---"+isTimeout+"---"+title);
		                	if(state=='2'&&isTimeout=='0'){//灰图标、灰文字：已完成（未超时）
		                		yrzqUlHtm +=" <li class='yrzq finished' style='height: 95px;'> "+
						                	" 	<div class='info-icon' style='cursor: pointer;' > "+
						                	" 		<span class='iconfont icon-dinner'></span> "+
						                	" 	</div> "+
						                	" 	<div style='cursor: pointer;' > "+
						                	" 		<p class='time'>"+startTime+"-"+endTime+"</p> "+
						                	" 		<p class='text'>"+title+"</p> "+
						                	" 	</div> "+
						                	" 	<i class='iconfont icon-xialadown'></i> "+
						                	" </li>";
		                		yzx++;
		                	}else if(state=='1'&&isTimeout=='0'){//绿图标、绿文字：进行中
		                		yrzqUlHtm +=' <li class="yrzq finished active" style="height: 95px;" onclick="updateInfo(\''+data[i].ID+'\',\''+fxcjId+'\',\''+title+'\',\''+sxsj+'\')" > '+
											'	<div class="info-icon" style="cursor: pointer;" > '+
											'		<span class="iconfont icon-mental"></span> '+
											'	</div> '+
											'	<div style="cursor: pointer;" > '+
											'		<p class="time">'+startTime+"-"+endTime+'</p> '+
											'		<p class="text">'+title+'</p> '+
											'	</div> '+
											'	<i class="iconfont icon-xialadown"></i> '+
											' </li> ';
		                		yzx++;
		                	}else if(state!='2'&&isTimeout=='0'&&dataType=='0'){//白图标、白文字：待执行（数据来源：计划日程）
		                		yrzqUlHtm +=' <li class="yrzq plan" style="height: 95px;" onclick="updateInfo(\''+data[i].ID+'\',\''+fxcjId+'\',\''+title+'\',\''+sxsj+'\')" > '+
											'	<div class="info-icon" style="cursor: pointer;" > '+
											'		<span class="iconfont icon-knock-off"></span> '+
											'	</div> '+
											'	<div style="cursor: pointer;" > '+
											'		<p class="time">'+startTime+"-"+endTime+'</p> '+
											'		<p class="text">'+title+'</p> '+
											'	</div> '+
											'	<i class="iconfont icon-xialadown"></i> '+
											' </li> ';
		                		wzx++;
		                	}else if(state!='2'&&isTimeout=='0'&&dataType!='0'){//黄图标、黄文字：待执行（数据来源：其它模块）
		                		yrzqUlHtm +=' <li class="yrzq finished notice" style="height: 95px;" onclick="updateInfo(\''+data[i].ID+'\',\''+fxcjId+'\',\''+title+'\',\''+sxsj+'\')" > '+
											'	<div class="info-icon" style="cursor: pointer;border-color:#00fff6" > '+
											'		<span class="iconfont icon-talk" style="color:#00fff6"></span> '+
											'	</div> '+
											'	<div style="cursor: pointer;" > '+
											'		<p class="time" style="color:#00fff6">'+startTime+"-"+endTime+'</p> '+
											'		<p class="text" style="color:#00fff6">'+title+'</p> '+
											'	</div> '+
											'	<i class="iconfont icon-xialadown"></i> '+
											' </li> ';
		                		wzx++;			
		                	}else if(state!='2'&&isTimeout=='1'){//红图标、红文字：已超时（未完成）
		                		yrzqUlHtm +=' <li class="yrzq plan" style="height: 95px;" onclick="updateInfo(\''+data[i].ID+'\',\''+fxcjId+'\',\''+title+'\',\''+sxsj+'\')" > '+
											'	<div class="info-icon" style="cursor: pointer;border-color: #e60012"> '+
											'		<span class="iconfont icon-knock-off"  style="color: #e60012"></span> '+
											'	</div> '+
											'	<div style="cursor: pointer;" > '+
											'		<p class="time" style="color: #e60012">'+startTime+"-"+endTime+'</p> '+
											'		<p class="text" style="color: #e60012">'+title+'</p> '+
											'	</div> '+
											'	<i class="iconfont icon-xialadown"></i> '+
											' </li> ';
		                		ycsw++;
		                	}else if(state=='2'&&isTimeout=='1'){//红图标、灰文字：已超时（已完成，但时限超时
		                		yrzqUlHtm +=' <li class="yrzq finished" style="height:95px" > '+
											'	<div class="info-icon" style="cursor: pointer;border-color: #e60012"> '+
											'		<span class="iconfont icon-knock-off"  style="color: #e60012"></span> '+
											'	</div> '+
											'	<div style="cursor: pointer;" > '+
											'		<p class="time">'+startTime+"-"+endTime+'</p> '+
											'		<p class="text">'+title+'</p> '+
											'	</div> '+
											'	<i class="iconfont icon-xialadown"></i> '+
											' </li> ';
		                		yzx++;
		                	}
		                }
		                //alert(yrzqUlHtm);
	                	$("#yrzqUl").empty();
	                    $("#yrzqUl").append(yrzqUlHtm);
	                    
	                    
	                    var tolistDivHtml='<div class="tolist-item status" onclick="toYzrpList(\'jrsw\')"> '+
											'					今日事务 '+jrsw+' '+
											'		</div> '+
											'		<div class="tolist-item status" onclick="toYzrpList(\'yzx\')"> '+
											'			已执行 '+yzx+' '+
											'		</div> '+
											'		<div class="tolist-item status" onclick="toYzrpList(\'wzx\')"> '+
											'			未执行 '+wzx+' '+
											'		</div> '+
											'		<div class="tolist-item status" onclick="toYzrpList(\'yc\')"> '+
											'			异常 '+ycsw+' '+
											'		</div> ';
	                    $("#tolistDiv").empty();
	                    $("#tolistDiv").append(tolistDivHtml);
		            },error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("获取一日执勤数据失败");
					}
		        });
		    }
			

			function refreshDiv(){
				$.ajax({
					type : 'post',
					url : '${ctx}/wghgl/yrzq/searchYrzq.json',
					dataType : 'json',
					success : function(data) {
						var divN = "";
						for(var i=0; i<data.length; i++){
							if(data[i].CPS_LX){
								divN = divN + "<li><span><p style='font-size:1px'>"+ (data[i].START_TIME.substring(11,19)<'12:00:00'?'上午':'下午') + "</p><p style='font-size:16px'>" + data[i].START_TIME + "</p></span><span style='font-size:16px'>" + data[i].DEPART_NAME + "</span><button style='font-size:16px;width:36%' onclick=\"opensxt('"+data[i].CPS_LX+"','"+data[i].STTIME+"','"+data[i].FHTIME+"')\">" + data[i].TITLE +"</button></li>";
							}else{
								divN = divN + "<li><span><p style='font-size:1px'>"+ (data[i].START_TIME.substring(11,19)<'12:00:00'?'上午':'下午') + "</p><p style='font-size:16px'>" + data[i].START_TIME + "</p></span><span style='font-size:16px'>" + data[i].DEPART_NAME + "</span><span style='font-size:16px;width:36%;color: white;'>" + data[i].TITLE +"</span></li>";
								
							}
							}
						$("#autoScrollR").empty();
						$("#autoScrollR").append(divN);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}

			function updateInfo(id,fxcjId,title,sxsj) {
				var w = 500;
				var h = 300;
				if(fxcjId){
					h = 400;
				}
				$("#dialogId_yrzq_edit").dialog({
					width : w, //属性
					height : h, //属性
					title : '一日执勤',
					modal : true, //属性
					autoOpen : false,
					url : "${ctx}/wghgl/yrzq/toEdit?id="+id+"&fxcjId="+fxcjId+"&title="+encodeURI(encodeURI(title))+"&sxsj="+sxsj
				});
				$("#dialogId_yrzq_edit").dialog("open");
			}

			function toYzrpList(type){
				$("#dialogId_yrzq_List").dialog({
					width : 1000, //属性
					height : 700, //属性
					title : '一日执勤',
					modal : true, //属性
					autoOpen : false,
					url : "${ctx}/wghgl/yrzq/toIndex?type="+type
				});
				$("#dialogId_yrzq_List").dialog("open");
			}

			var videoClient = window.top.videoClient;
			var deskLayout = null;
			var index_2 = 0;
			function opensxt(sxt,sttime,fhtime){
				var stime = sttime;
				var etime = fhtime;
				$.ajax({
					type : 'post',
					url : '${ctx}/wghgl/yrzq/openCarame.json?xlId='+sxt,
					dataType : 'json',
					success : function(data) {
						var datalength = data.length;
						if(datalength>0){
							videoClient.setLayout(datalength);
						}
						for(var i=0; i<datalength; i++){
							var cameraId = data[i].ID;
							var cameraName = data[i].CBD_NAME;
							
							if( !_validate(stime, etime) ){
								return false;
							}
							var curLayout;
							var layout;
							deskLayout = datalength;
							curLayout = videoClient.curLayout;
							layout = {
									'layout': deskLayout,
									'last': curLayout
								};
							var index = 0;
							var videoList = new Array();
							var video = null;
							if(index_2 == deskLayout){
								index_2 = 0;
							}
							//selectedStyle(tId);
							video = {
								index : index_2,
								cameraId : cameraId,
								cameraName: cameraName,
								startTime : stime,
								endTime : etime
							};
							videoList.push(video);
							index_2++;
							//alert(JSON.stringify(videoList));
							videoClient.playbackHandle(videoList, layout);
							
							
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
				
			}
			 
			//行为侦测
			function searchXwzcCount() {
				$.ajax({
					type : "post",
					url : "${ctx}/xwzc/searchXwzcCount.json",
					dataType : "json",
					success : function(data) {
						if (data.success) {
							 $("#xwzc").text(data.obj.XWZC);
						} else {
							$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					}
				});
			}		
			
			function toDisplay(name) {
				 
				if (name == "homeMenu") {
					if (jsConst.USER_LEVEL==3) {
					} else {
						 
					}
					//如果当前为2D则展示首页
					if(jsConst.MAP_TYPE=='0'){
						init2DPage();
					}else{
						init3DPage();
					}
				
				}
			}
			
			function init2DPage(){
				//二维地图 add by zk start
				 
			   $("#layout1").layout("remove","west");
			$(".tolist .status").css("width","auto");
			$(".tolist .status").css("margin-right","12px");
			$(".tolist .status").css("padding","7px 17px");
			urlfhs = "${ctx}/portal/bj/shouye";
				var panel = $("#layout1").layout("panel", "center");
				if(jsConst.USER_LEVEL==1){
					jsConst.ROOT_ORGA_CODE = '';
					panel.panel("refresh", "${ctx}/portal/provMap");
				}else if(jsConst.USER_LEVEL==2){
					panel.panel("refresh", "${ctx}/portal/planeMap");
				}else if(jsConst.USER_LEVEL==3){
					panel.panel("refresh", "${ctx}/portal/planeMap");
				}
				
				return;  
				//二维地图 add by zk end
			}
			
			function init3DPage(){
				if(jsConst.USER_LEVEL==1){
					centerDisplay('provMap', '1');
				}else if(jsConst.USER_LEVEL==2 || jsConst.USER_LEVEL==3){
					centerDisplay('prisMap', '1');
				}
				return;
			}
			//切换center
			function centerDisplay(name, mapType) {
				// alert("centerDisplay = " + name);
				// alert("centerDisplay Const.MAP_TYPE = " + jsConst.MAP_TYPE);
				var panel = $("#layout1").layout("panel", "center");
				if (name == "provMap") {
					jsConst.ROOT_ORGA_CODE = '';//add by linhe 2018-1-10
					panel.panel("refresh", "${ctx}/portal/provMap");
				}else if (name == "prisMap") {
					if(mapType=='0'){
						panel.panel("refresh", "${ctx}/portal/planeMap");
					} else {
						panel.panel("refresh", "${ctx}/portal/prisMap");
					}			
				}
			}
	</script>
</body>

</html>
