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
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	String date = simpleDateFormat.format(new Date());
	String STime = date + " 00:00:00";
	String ETime = date + " 23:59:59";
%>
<html>

<head>
	<meta charset="utf-8">

	<title>${map.orgName}智能安防平台 </title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/mhsy.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/zfxx_dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/gerenminjing.css">
  	
  	
	  	
	<!-- coral4 css start  --><%-- 
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" /> --%>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/moxing.css" />
	<!-- coral4 css  end  -->
	
	<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
	<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<%-- 	
	<!-- app css define start -->
	<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
	
	<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
	
	<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" /> --%>
  	
 <style type="text/css">
 	#swsb li{
 		width: 580px;
 		margin-left: 195px;
 	}
 
 </style> 	
  	
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

			<div data-options="region:'west'" style="width:450px;">
				<div class="left">
					<div class="left-top">
						<h3 style="margin-top:-30px;">今日值班</h3>
						<div id="tabs1">
							<ul>
								<li><a href="#fragment-1">指挥中心</a></li>
							</ul>
							<div id="fragment-1">
								<ul>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('总值班长');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">
												指挥长
												<!-- <a>更多+</a> -->
												<span><a href="javascript: void(0);" onClick="showJyldTodayDutyList('指挥长')">更多+</a></span>
											</p>
											
											<!-- <h3>生物识别<span><a href="javascript: void(0);" onClick="openMenuDialog(this, event, 'swsb')">更多+</a></span></h3> -->
											<p class="dutyNme" id="zzbzDutyList">
												<a></a>&nbsp;
												<a></a>
											</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('值班主任');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">
												值班长
												<span><a href="javascript: void(0);" onClick="showJyldTodayDutyList('值班长')">更多+</a></span>
											</p>
											<p class="dutyNme" id="zbzrDutyList">
												<a></a>&nbsp;
												<a></a>
											</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('指挥中心');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">
												值班员
												<span><a href="javascript: void(0);" onClick="showJyldTodayDutyList('值班员')">更多+</a></span>
											</p>
											<p class="dutyNme" id="zhzxDutyList">
												<a></a>&nbsp;
												<a></a>&nbsp;
												<a></a>&nbsp;
												<a></a>
											</p>
										</div>
									</li>
										<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
												<span style="color: white;">
												在监警察
												</span>
												<p class="dutyNme"  id="policeCountInPrisonjy">0</p>
												
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<span style="color: white;">在册警察</span>
											<p class="dutyNme" style="cursor: pointer;" onClick="showPoliceCountZaiCe()" id="policeCountZaiCe">0</p>
										</div>
										
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<span style="color: white;">备勤警察</span>
											<p class="dutyNme"  id="policeCountBeiQin">0</p>
										</div>
									</li>
									
								</ul>
							</div>
							<div id="fragment-2">
								<ul>
									
								</ul>
							</div>
						</div>
					</div>
					<div class="left-bottom" style="background: none;">
						<!-- 查询分类 -->
						<input id="mjtype"/>
						<!-- 查询内容 -->
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
									<div class="custodyPerson alertShow" id="zyjwzx">
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
										<p class="custodyTitle alertType">监外就诊</p>
										  <p class="" style="" id="today10_modify">0</p>  
									</div>
								</li>
								<!-- <li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSyrs">
										<p class="custodyTitle alertType">邪教犯</p>
										 <p class="" style="" id="today31_modify">0</p> 
									</div>
								</li>
								<li>
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
									<div class="custodyPerson alertShow" id="zfJwzx">
										<p class="custodyTitle alertType">在押</p>
										 <p class="" style="cursor: pointer;color:red" id="today9_modify">0</p> 
									</div>
								</li>
								<!-- <li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJwzx">
										<p class="custodyTitle alertType">监外就诊</p>
										 <p class="" style="cursor: pointer;color:red" id="today10_modify">0</p> 
									</div>
								</li> -->
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfWcjy">
										<p class="custodyTitle alertType">新收押罪犯总</p>
										 <p class="" style="cursor: pointer;color:red" id="today37_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTxlj">
										<p class="custodyTitle alertType">新收押男犯</p>
										<p class="" style="" id="today50_modify">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSfrs">
										<p class="custodyTitle alertType">新收押女犯</p>
										<p class="" style="" id="today51_modify">0</p> 
									</div>
								</li>
								<!-- <li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="ZfQsrs">
										<p class="custodyTitle alertType">其他外出</p>
										 <p class="" style="text-align:center;color:red;" id="ZfQsrs">0</p> 
									</div>
								</li>
								<li>
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
			
			
			<!-- <div data-options="region:'east'" style="width:350px;">
				<div class="right"  >
					<div class="on-duty right" style="height:640px;">
						<h2 style="margin-top:-27px;">一日执勤</h2>
						<div class="tolist" id="tolistDiv">
							<div class="tolist-item status">
										今日事务 10
							</div>
							<div class="tolist-item status">
								已执行 2
							</div>
							<div class="tolist-item status">
								未执行 7
							</div>
							<div class="tolist-item status">
								异常 1
							</div>
						</div>
						<ul class="duty-content">
							<div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0">
							<div id="yrzqUl" class="mCSB_container" style="position:relative; top:0; left:0;" dir="ltr">
							
							</div>
							</div>
							
						</ul>
					</div>
				</div>
			</div> -->
			<div data-options="region:'center'" class="main"  >
				<div class="top" id="top"  style="height: 60%;width: 100%;  ">
					<h3><span>湖南地图</span><span style="margin-left: 44%;position: absolute;top: 0px;left: 10px;">警囚态势</span></h3>
					<div id = "app" class="top-box"  style="width: 40%;height: 600px;margin-left:-120px;"></div>
					<div class="top-box top-right" style=" width:58% ;margin-left:8%">
						<div id ="ssjlChart" style="width:100%;height:100%; ">
						
						</div>
						
					
						<!-- <div class="todo_List">
									<ul class="theader clearfix">
										<li style="width:39%;text-align:right;">监狱/监区</li>
										<li style="width:18%;text-align:right;">警察&nbsp;&nbsp;&nbsp;&nbsp;</li>
										<li style="width:18%;text-align:right;">罪犯&nbsp;&nbsp;&nbsp;</li>
										<li style="width:25%;text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;警囚比</li>
										
									</ul>
									<div class="tcontent">
										<ul id="swsb">
											<li>
												<span>一监区</span>
												<span id="policeCountInPrisonArea1">0</span>
												<span id="criminal1">0/<i>0</i></span>
											</li>
										</ul>
									</div>
								</div> -->
					</div>
				</div>
				<div class="center-box clearfix" style="width:103%;margin-top:2px;">
					<div class="center-left" style="width:30%;">
						<h3>风险概况<span></span></h3>
						<div id="ltChart" class="ltChart top-box" style="padding-bottom: 50px;padding-right: 150px;
						margin-left:20px;background-color:transparent;width: 600px;height:500px"></div>
					</div>
					<div class="center-middle" style="width:40%;  ">
						<h3>实时警情<span></h3>
						<div class="real-time" style="background-color:transparent;">
						
									<ul class="alarm">
										<li class="alarm-item">
											<img class="alarm-img" src="${ctx}/static/bj-cui/img/emergency.png" alt="一级警情">
											<div class="alarm-info">
												<p class="alarm-mount emergency" id="lev_1" style="cursor: pointer;" onClick="openMenuDialog(this, event,'alarmRecord1')">0</p>
												<p class="alarm-title"  >一级警情</p>
											</div>
										</li>
										<li class="alarm-item">
											<img class="alarm-img" src="${ctx}/static/bj-cui/img/danger.png" alt="二级警情">
											<div class="alarm-info">
												<p class="alarm-mount danger" id="lev_2" style="cursor: pointer;"onClick="openMenuDialog(this, event,'alarmRecord2')">0</p>
												<p class="alarm-title"  >二级警情</p>
											</div>
										</li>
										<li class="alarm-item">
											<img class="alarm-img" src="${ctx}/static/bj-cui/img/warning.png" alt="三级警情">
											<div class="alarm-info">
												<p class="alarm-mount warning" id="lev_3" style="cursor: pointer;" onClick="openMenuDialog(this, event,'alarmRecord3')">0</p>
												<p class="alarm-title"  >三级警情</p>
											</div>
										</li>
										<li class="alarm-item">
												<img class="alarm-img sd" src="${ctx}/static/bj-cui/img/sdian.png" alt="电网">
												<div class="alarm-info">
													<p class="alarm-mount dwzt" id="dwCount" onClick="openSzdw()">0</p>
													<p class="alarm-title">电网</p>
												</div>
											</li>
										
											<li class="alarm-item">
												<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/device.png" alt="设备故障">
												<div class="alarm-info">
													<p class="alarm-mount common" id="shebeiyichang" style="cursor: pointer;" onClick="showSbyc();">0</p>
													<p class="alarm-title">设备异常</p>
												</div>
											</li>
											<li class="alarm-item">
													<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/car.png" alt="外来车辆">
													<div class="alarm-info">
														<p class="alarm-mount common" id="nowForeignCar">0</p>
														<p class="alarm-title">外来车辆</p>
													</div>
												</li>
												<li class="alarm-item">
													<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/foreigner.png" alt="外来人员">
													<div class="alarm-info">
														<p class="alarm-mount common" id="nowFPerson" style="cursor: pointer">0</p>
														<p class="alarm-title">外来人员</p>
													</div>
												</li>
												<li class="alarm-item">
														<img class="alarm-img" src="${ctx}/static/bj-cui/img/xwzc.png" alt="行为侦测">
														<div class="alarm-info">
															<p class="alarm-mount common" id="xwzc" style="cursor: pointer" onClick="openMenuDialog(this, event,'xwzc')">0</p>
															<p class="alarm-title">行为侦测</p>
														</div>
													</li>
												<%--
												<li class="alarm-item">
													<img class="alarm-img" src="${ctx}/static/bj-cui/img/ffsj.png" alt="非法手机">
													<div class="alarm-info">
														<p class="alarm-mount common"  id="ffsj" style="cursor: pointer"
															onclick="openFfsjtj()">0</p>
														<p class="alarm-title">非法手机</p>
													</div>
												</li> --%>
									
													<li class="alarm-item">
															<img class="alarm-img" src="${ctx}/static/bj-cui/img/ljff.png" alt="劳动工具">
															<div class="alarm-info">
																<p class="alarm-mount common" id="toolNum">0</p>
																<p class="alarm-title">劳动工具</p>
															</div>
														</li>
														<li class="alarm-item">
															<img class="alarm-img" src="${ctx}/static/bj-cui/img/mgc.png" alt="安检">
															<div class="alarm-info">
																<p class="alarm-mount common" id="securityCheckCount">0</p>
																<p class="alarm-title">安检</p>
															</div>
														</li>

														<li class="alarm-item">
															<img class="alarm-img" src="${ctx}/static/bj-cui/img/doorstate.png" alt="门禁">
															<div class="alarm-info">
																<p class="alarm-mount common" id="doorStateNum">0</p>
																<p class="alarm-title">门禁</p>
															</div>
														</li>
									</ul>
							
	
								</div>
					</div>
					<div class="center-right" style="width:25%;background-color: transparent; ">
						<h3>设备统计</h3>
						<div class="wrapper" style="padding-top: 0px;overflow-y:scroll;width: 440px;height: 400px;background:none;">
							<div class="device-wrapper" style="background-color: transparent">
								<div class="device-nav" id="sbtj_div"></div>
								<ul class="device-nav" id="sbtj"></ul>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
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
		//获取用户信息是异步的，会造成，所以这一块先进行赋值
		
		jsConst.ORG_CODE = "<%=user.getOrgCode()%>";					// 机构代码
		jsConst.CUS_NAME = "<%=user.getOrgName()%>";				// 机构名称
		jsConst.CUS_NUMBER = "<%=user.getCusNumber()%>";			// 机构代码
		jsConst.PRISON_ORG_CODE = "<%=user.getCusNumber()%>";			// 监狱机构代码
		jsConst.ROOT_ORGA_CODE = "<%=user.getCusNumber()%>";			// 当前选中监狱编号
	    jsConst.ROOT_ORGA_NAME = "<%=user.getOrgName()%>";			// 当前选中监狱名称
		jsConst.USER_ID = "<%=user.getUserId()%>";						// 用户ID
		jsConst.USER_NAME = "<%=user.getUserName()%>";					// 用户名
		jsConst.REAL_NAME = "<%=user.getRealName()%>";					// 用户真实姓名
		jsConst.POLICE_CODE = "<%=user.getPoliceNo()%>";				// 警员编号
		jsConst.DEPARTMENT_ID = "<%=user.getDprtmntCode()%>";			// 部门Code
		jsConst.DEPARTMENT_NAME = "<%=user.getDprtmntName()%>";			// 部门名称
		jsConst.ROLE_LIST = "<%=user.getRoles()%>";
		jsConst.USER_LEVEL = "<%=user.getUserLevel()%>";		// 用户等级
		jsConst.LOGIN_USER_KEY = "<%=user.getPoliceNo()%>";					// 登录用户标识(前面用userId的数据有问题的改库)
		jsConst.SPECIAL_POLICE = "<%=user.getIsSpecialPolice()%>";				// 是否特警队员
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
<script src="${ctx }/static/js/map/echarts.min.js"></script>
<script src="${ctx }/static/js/map/index.js"></script>
<script type="text/javascript">
	var proMap = getMap('${ctx }/static/js/map/hunan2.json', 'app');

$.parseDone(function() {
	// 加载实时警情数据
	loadRealTimeJqData();
	/* initUserInfo(); */
	/* var panel = $("#layout1").layout("panel", "center");
	panel.panel("refresh", "${ctx}/portal/planeMap"); */
	queryShebeiyichang();
	
	//行为侦测
	searchXwzcCount();
	
	// 加载今日值班数据
	loadTodayDutyData();
	
	// 加载生物识别数据
	loadSwsbData();
	
	// 查询罪犯数量
	queryCriminal()
	
	//早晚点名
	//refreshZwdm();
	
	//雷达图展示
	initLeftChart();
	
	//加载今日压犯情况
	loadJryfqk();
	//设备统计
	queryTechnicalDeviceCount();
	//加载实时警力图
	initSssjlChart();
	
});

var urlfhs = "${ctx}/portal/zhshouye";

var intervaLoadRealTimeJqData=setInterval(function(){
	loadRealTimeJqData();
},120000);

function sjchang(){
	 
	 $.ajax({
		type : "post",
		url : '${ctx}/wghgl/yrzq/getsjzs?type=1',
		dataType : "json",
		success : function(data) {
			 console.log(data);
			 //sbtj
			 $("#sbtj").html("");
			 var jg = "";
			  for(var i = 0;i<data.length;i++){
				 var zc = data[i].zc;
				 var yc = data[i].yc;
				 var tb = data[i].tb;
				 var name = data[i].name;
				 jg = jg+"<li class=\"device-item\">"+
	                "<span class=\"iconfont "+tb+"\">"+
	               " </span>"+
	                "<div class=\"content\">"+name+"&nbsp;<span class=\"all\">"+zc+
	               "   </span>"+
	                "  <span class=\"split\">/</span>"+
	               "   <span class=\"done\">"+yc+"</span>"+
	              "  </div>"+
	             " </li>";
			 } 
			  $("#sbtj").html(jg);
		},	error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(errorThrown);
			console.log("获取异常");
		} 
	}); 
} 

/**
 * 查询技防设备数量总信息
 */
function queryTechnicalDeviceCount() {
	var cusNumber = jsConst.CUS_NUMBER;
	var url = "${ctx}/xxhj/jfsb/listDeviceMasterInfo?cusNumber=" + cusNumber + "&typeIndc=1";
	
	$.ajax({
		type : "post",
		url : url,
		dataType : "json",
		success : function(data) {
			console.log(data);
			if (data) {
				loadTechnicalDeviceCount(data);
			}
		}
	});
}

/**
 * 加载技防设备统计信息
 */
	function loadTechnicalDeviceCount(data) {
	
		console.log(data);
	 //sbtj
	 $("#sbtj").html("");
	 var jg = "";
	  for(var i = 0;i<data.length;i++){
		
		 //设备名称
		 var sbmc = data[i].DMA_DEVICE_NAME;
		 //总数
		 var zs = data[i].DMA_TOTAL_COUNT;
		 var  typeclass = "";
		 //单位
		 var unit = "";
		 //摄像机
		 if (data[i].DMA_DEVICE_IDNTY == 1) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-camera\" style=\"left:5px\"></span>";
			} 
		 //网络报警
		 else if (data[i].DMA_DEVICE_IDNTY == 2) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-alarm\" style=\"left:5px\"></span>";
			} 
		 //数字电网
		 else if (data[i].DMA_DEVICE_IDNTY == 3) { 
				unit = "段";
				typeclass = "<span class=\"iconfont icon-electron\" style=\"left:5px\"></span>";
			} 
		 //周界震动报警
		 else if (data[i].DMA_DEVICE_IDNTY == 4) {
			 continue;
				unit = "段";
			} 
		 //周界红外报警
		 else if (data[i].DMA_DEVICE_IDNTY == 5) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-pcm\" style=\"left:5px\"></span>";
			} 
		 //门禁
		 else if (data[i].DMA_DEVICE_IDNTY == 6) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-limit\" style=\"left:5px\"></span>";
			} 
		 //对讲(主机)
		 else if (data[i].DMA_DEVICE_IDNTY == 7) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-talk1\" style=\"left:5px\"></span>";
			} 
		 //对讲（分级）
		 else if (data[i].DMA_DEVICE_IDNTY == 8) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-talk1\" style=\"left:5px\"></span>";
			} 
		//广播
		 else if (data[i].DMA_DEVICE_IDNTY == 9) {
				unit = "个";
				typeclass = "<span class=\"iconfont icon-broadcast\" style=\"left:5px\"></span>";
			} 
		 
		 jg = jg+"<li class=\"device-item\">"+
           "<span class=\"iconfont\">"+
          " </span>"+typeclass+
           "<div class=\"content\">"+sbmc+"&nbsp;<span class=\"split\">&nbsp;</span><span class=\"all\">"+zs+
          "   </span>"+
          "   <span class=\"done\">"+unit+"</span>"+
          "   <span class=\"done\" style=\"color:#00ff21\">(正常)</span><br>"+
          "  <span class=\"all\"> 0</span>"+
          "   <span class=\"done\">"+unit+"</span>"+
          "   <span class=\"done\" style=\"color:red\">(异常)</span>"+
         "  </div>"+
        " </li>";
	 } 
	  $("#sbtj").html(jg);
	  
	  return;
	var table = $("#technicalDeviceCount table");
	var tr = $("<tr id='title'></tr>");
	tr.append('<td width="150" class="one">设备名称</td>');
	tr.append('<td width="100" class="one">单位</td>');
	tr.append('<td width="100" class="one">总数</td>');
	tr.append('<td width="100" class="one">故障</td>');
	table.append(tr);
	
	/* var unit = "";
	for (var i = 0; i < data.length; i++) {
		 
		var tr = $("<tr></tr>");
			tr.append("<td>"+data[i].DMA_DEVICE_NAME+"</td>");
			
		if (data[i].DMA_DEVICE_IDNTY == 1) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 2) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 3) { 
				unit = "对";
			} else if (data[i].DMA_DEVICE_IDNTY == 4) {
				unit = "段";
			} else if (data[i].DMA_DEVICE_IDNTY == 5) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 6) {
				unit = "点";
			} else if (data[i].DMA_DEVICE_IDNTY == 7) {
				unit = "区";
			}
		tr.append("<td>"+unit+"</td>");
		
		if (data[i].DMA_TOTAL_COUNT != 0) {
			
			tr.append("<td><a href='javascript:void(0);' onclick='methodControler("+ data[i].DMA_DEVICE_IDNTY
					+ ",0,this);' id='one_"+data[i].DMA_DEVICE_IDNTY+"'>"+data[i].DMA_TOTAL_COUNT+"</a></td>");
		} else {
			tr.append("<td >"+data[i].DMA_TOTAL_COUNT+"</td>");
		}
		
		if (data[i].DMA_ABNORMAL_COUNT != 0) {
			
			tr.append("<td ><a href='javascript:void(0);'onclick='methodControler("
					+ data[i].DMA_DEVICE_IDNTY+ ",1,this);' id='two_"+ data[i].DMA_DEVICE_IDNTY+ "'>"
					+data[i].DMA_ABNORMAL_COUNT+"</a></td>");

		} else {
			tr.append("<td >"+data[i].DMA_ABNORMAL_COUNT+"</td>");
		}
		table.append(tr);
	}  */
	$("#sbtj_div").append(table);   
} 

/**
 * 加载实时警情数据
 */
function loadRealTimeJqData() {
	// 1、2、3级警情
	queryAlarmLevRecord();
	
	// 外来车
	showPeopleAndCarCount();
	//外来人员
	showPeopleAndCarCount1();	
	// 安检
	showSecurityCheckCount();
	// 门禁状态
	doorStateCount();
	//劳动工具数量
	toolCount();
	//电网数量
	showPowerNetworkCount();
}

/**
 * 加载今日值班数据
 */
function loadTodayDutyData() {
	// 指挥中心值班
	loadZhzxTodayDutyData();
	
	// 监区值班
	loadJqTodayDutyData();
	
	// 在监民警数量
	 if(jsConst.USER_LEVEL== 1) {
		queryPoliceCountInPrison();
	} 
	
	//监狱账号统计在监民警数量
	queryPoliceCountInPrisonjy();
	
	//在册民警数量
	queryPoliceCountZaiCe();
	//备勤警察数据
	queryPoliceCountBaiQin();
}

/**
 * 指挥中心值班数据
 */
function loadZhzxTodayDutyData() {
	// 总值班长数据
	/* loadJyldDutyList($("#zzbzDutyList"), "总值班长"); */
	loadJyldDutyList($("#zzbzDutyList"), "指挥长");
	// 值班主任数据
	/* loadJyldDutyList($("#zbzrDutyList"), "值班主任"); */
	loadJyldDutyList($("#zbzrDutyList"), "值班长");
	// 指挥中心数据
	/* loadZhzxDutyList($("#zhzxDutyList"), ""); */
	loadJyldDutyList($("#zhzxDutyList"), "值班员");
}

/**
 * 监狱领导
 */
function loadJyldDutyList(obj, orderName) {
	$.ajax({
		type : "post",
        url : "${ctx}/zbgl/todayDuty/getTodayDuty",
        data: {
        	cusNumber:"${map.cusNumber}",
        	orderName: orderName
        },
		dataType : "json",
		success : function(data) {
            if(data.code == 200){
            	var dutyList = data.data;
                if (dutyList != null && dutyList.length > 0) {
                    $(obj).empty();
                    debugger;
                var dbdStaffNames =  qcList(dutyList);
                   /*  var showIndex = dutyList.length>4 ? 4 : dutyList.length;
                    for(var i=0; i<showIndex; i++) {
                        $(obj).append("<a href='javascript:void(0);'>"+ dutyList[i].dbdStaffName + "</a>");
                        $(obj).append("&nbsp;");
                    }
                } */
                var showIndex = dbdStaffNames.length>4 ? 4 : dbdStaffNames.length;
                for(var i=0; i<showIndex; i++) {
                    $(obj).append("<a href='javascript:void(0);'>"+ dbdStaffNames[i] + "</a>");
                    $(obj).append("&nbsp;");
                }
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

function qcList(dutyList) {
  var dbdStaffNames =[];
	for(var i=0 ;i< dutyList.length ;i++){
		dbdStaffNames.push(dutyList[i].dbdStaffName);
	}
	
	var res  = [];
	var json = {};
	for (var j = 0; j < dbdStaffNames.length; j++) {
		if(!json[dbdStaffNames[j]]){
			res.push(dbdStaffNames[j]);
			json[dbdStaffNames[j]] = 1;
		}
	}
	return res;
}

/**
 * 指挥中心
 */
function loadZhzxDutyList(obj, orderName) {
	$.ajax({
		type : "post",
        url : "${ctx}/zbgl/todayDuty/getZhzxTodayDuty",
        data: {
        	categoryName: "指挥中心",
        	modeName: "指挥中心值班模板",
        	orderName: orderName
        },
		dataType : "json",
		success : function(data) {
            if(data.code == 200){
            	var dutyList = data.data;
                if (dutyList != null && dutyList.length > 0) {
                    $(obj).empty();
                    var showIndex = dutyList.length>4 ? 4 : dutyList.length;
                    for(var i=0; i<showIndex; i++) {
                        $(obj).append("<a href='javascript:void(0);'>"+ dutyList[i].dbdStaffName + "</a>");
                        $(obj).append("&nbsp;");
                    }
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
 * 指挥中心
 */
function loadJqTodayDutyData() {
	$.ajax({
		type : "post",
        url : "${ctx}/zbgl/todayDuty/getJqTodayDuty",
        data: {
        	categoryName: "监区",
        	modeName: "监区",
        	parentDeptName: "监区"
        },
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
	var itemContainer = $("div[id='fragment-2']").find("ul:eq(0)");
	
	// 部门今日值班显示模板
	var itemTemplate = '<li><div class="info-icon dutyIcon"><img src=""></img></div>' + 
		'<div class="dutyPerson">' + 
		'<p class="dutyTitle"><span><a href="javascript: void(0);">更多+</a></span></p>' + 
		'<p class="dutyNme"></p>' + 
		'</div></li>';
	
	// 初始化部门今日值班列表
	itemContainer.html('');
	if(deptDutyList != null && deptDutyList.length > 0) {
		$.each(deptDutyList, function (i, deptDuty) {
			var deptInfo = deptDuty.deptInfo;
			var dutyList = deptDuty.dutyList;
			
			var item = $(itemTemplate).clone();
			item.find("div[class='info-icon dutyIcon']").find("img").attr("src", "${ctx}/static/bj-cui/img/command/leader.png");
			item.find("p[class='dutyTitle']").prepend(deptInfo.orgName);
			item.find("p[class='dutyTitle']").find("span:eq(0)").find("a:eq(0)").attr("onClick", "showJqTodayDutyList(\'" + deptInfo.orgKey + "\',\'" + deptInfo.orgName + "\')");
			
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
 * 加载生物识别数据
 */
function loadSwsbData() {
	// 查询各监区民警数量
	queryPoliceCountInPrisonArea();
}

/**
 * 监狱领导今日值班列表展示
 */
function showJyldTodayDutyList(orderName) {
	// 值班种类名称
	//var categoryName = new Base64().multiEncode("监狱领导", 2);
	// 值班模板名称
	//var modeName = new Base64().multiEncode("监狱领导", 2);
	
	$("#dialogId_rightHomeMenu").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : orderName + '今日值班列表',
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/zbgl/todayDuty/toIndex?orderName=" + new Base64().multiEncode(orderName, 2)
	});
	$("#dialogId_rightHomeMenu").dialog("open");
}

/**
 * 指挥中心今日值班列表展示
 */
function showZhzxTodayDutyList() {
	// 值班种类名称
	var categoryName = new Base64().multiEncode("指挥中心", 2);
	// 值班模板名称
	var modeName = new Base64().multiEncode("指挥中心", 2);
	
	$("#dialogId_rightHomeMenu").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : '指挥中心今日值班列表',
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/zbgl/todayDuty/toIndex?categoryName=" + categoryName + "&modeName=" + modeName
	});
	$("#dialogId_rightHomeMenu").dialog("open");
}

/**
 * 监区今日值班列表展示
 */
function showJqTodayDutyList(deptCode, deptName) {
	// 值班种类名称
	var categoryName = new Base64().multiEncode("监区", 2);
	// 值班模板名称
	var modeName = new Base64().multiEncode("监区", 2);
	
	$("#dialogId_rightHomeMenu").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : deptName + '今日值班列表',
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/zbgl/todayDuty/toIndex?categoryName=" + categoryName + "&modeName=" + modeName + "&deptCode=" + deptCode
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
		$(".middle-scroll").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
		});
		$(".tcontent").mCustomScrollbar({
			axis: "y",
			theme: "minimal-dark",
			scrollbarPosition: "outside",
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
			
			// 初始化登录用户信息
			initUserInfo();
			videoPlanTimer.initVideoPlanTimer();
			
			// 定时刷监内动态
			refreshDiv();
			setInterval("refreshDiv();",60000);
			//事务督办
			//refreshSwdb(); 
			
			//监外就诊和住院情况 heqh
			refreshJwqk();
			showYrzqContent();//初始化一日执勤
		});
		
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
		
		function openSwdb(id){
			var url = jsConst.basePath + '/rwgl/rwjs/openDialog/index?id='+id;
			$("#dialog").dialog({
				width : 1000,
				height : 600,
				title : "事务督办",
				url : url				 
			});
			$("#dialog").dialog("open");
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
		function refreshSwdb(){
			$.ajax({
				type : 'post',
				url : '${ctx}/rwgl/rwjs/searchSwdb.json',
				dataType : 'json',
				success : function(data) {
					var divN = "";
					for(var i=0; i<data.length; i++){
						divN = divN + "<li style='height:15%;'><span style='width:36%;'><p style='font-size:16px'>" + data[i].XF_TIME + "</p></span><span style='font-size:16px;width:36%;display:inline-block;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'>" + data[i].RW_TITLE + "</span><button style='width:23%;font-size:16px;outline: none;background: #144065;border: 1px solid #00fff6;color: #fff;height: 40px;text-align: center;vertical-align: middle;border-radius: 4px;margin-left: 16px;' onclick=\"openSwdb('"+data[i].ID+"')\">待处理</button></li>";
					}
					$("#swdb").empty();
					$("#swdb").append(divN);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
		
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
					$("#inside").html(count);
					// $("#inside").html(885);
					
				} else if (key == 2) {                           //实押危安犯总数
					
					/* WAFCount = count;
					$("#WAFCount").html(WAFCount); */
					
				} else if (key == 4) {                            //加戴戒具罪犯数
					
					$("#GDJJCount").html(count);
					//$("#GDJJCount").html(0);
				
				} else if (key == 5) {                          //关押禁闭罪犯数
					
					$("#GYJBCount").html(count);
					//$("#GYJBCount").html(0);

				} else if (key == 6) {                         //隔离审查罪犯数
					
					$("#GLSCCount").html(count);
					//$("#GLSCCount").html(0);
						
				} else if (key == 7) {                         //立案侦查罪犯数
					
					$("#LAZCCount").html(count);
					// $("#LAZCCount").html(0);
				
				} else if (key == 8) {                         //解回重审罪犯数
					
					$("#JHCSCount").html(count);
					// $("#JHCSCount").html(0);
				
				} else if (key == 9) {                       //暂予监外执行罪犯数
					
					$("#ZYJWZXCount").html(count);
					// $("#ZYJWZXCount").html(0);
				
				} else if (key == 10) {                          //老病残罪犯数
					
					$("#LBCCount").html(count);
					// $("#LBCCount").html(0);
				} 
			}
		}
		
		function queryAlarmLevRecord() {
			/* $("#lev_1").text(0);
			$("#lev_2").text(0);
			$("#lev_3").text(0); */
			$.ajax({
				type : "post",
				url : "${ctx}/alarm/queryAlarmLevRecord.json?cusNumber=" + jsConst.CUS_NUMBER+"&DpName="+jsConst.PRISON_ORG_CODE,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						 $("#lev_1").text(data.obj.lev_1);
						 $("#lev_2").text(data.obj.lev_2);
						 $("#lev_3").text(data.obj.lev_3);
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
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
		 /**
	     * 加载外来车辆数量
	     */
		function showPeopleAndCarCount() {
	        $.ajax({
	            type : "post",
	            url : "${ctx}/common/all/getPeopleAndCarCount?cusNumber=" + jsConst.CUS_NUMBER,
	            dataType : "json",
	            success : function(data) {
	            	
	                if (data.carCount == 0) {
	                    $("#nowForeignCar").html(data.carCount);
	                } else {
	                    $("#nowForeignCar").empty();
	                    $("#nowForeignCar").append("<a href='javascript:void(0);' onclick='toForeignCarList()'>"+ data.carCount + "</a>");
	                }
	                /* if (data.peopleCount == 0) {
	                    $("#nowFPerson").html(data.peopleCount);
	                } else {
	                    $("#nowFPerson").empty();
	                    $("#nowFPerson").append("<a href='javascript:void(0);' onclick='toForeignPeopleList()'>"+ data.peopleCount + "</a>");
	                } */
	            },
	        });
	    }
		/**
	     * 加载电网数量
	     */
		function showPowerNetworkCount() {
	        $.ajax({
	            type : "post",
	            url : "${ctx}/jfsb/powerNetwork/findCountByPnbCusNumber?cusNumber=" + jsConst.CUS_NUMBER,
	            dataType : "json",
	            success : function(data) {
	            	if (data.success) {
						 $("#dwCount").text(data.obj);
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
	            },
	        });
	    }
		/**
	     * 打开数字电网
	     */
		function openSzdw(){
			var url = jsConst.basePath + '/jfsb/powerNetwork/list';
			$("#dialog").dialog({
				width : 1000,
				height : 600,
				title : "数字电网",
				url : url				 
			});
			$("#dialog").dialog("open");
		}
		 //加载外来人员数量
		function showPeopleAndCarCount1() {
	        $.ajax({
	            type : "post",
	            url : "${ctx}/xxhj/foreignerPeos/searchCounts",
	            dataType : "json",
	            success : function(data) {
	                if (data == 0) {
	                    $("#nowFPerson").html(data);
	                } else {
	                    $("#nowFPerson").empty();
	                    $("#nowFPerson").append("<a href='javascript:void(0);' onClick=\"openMenuDialog(this, event,'wlry1')\">"+ data + "</a>");
	                }
	                /* if (data.peopleCount == 0) {
	                    $("#nowFPerson").html(data.peopleCount);
	                } else {
	                    $("#nowFPerson").empty();
	                    $("#nowFPerson").append("<a href='javascript:void(0);' onclick='toForeignPeopleList()'>"+ data.peopleCount + "</a>");
	                } */
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
			$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME+ "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
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
		 * 语音识别系统
		 */
		function openYysbxt() {
			
			var url = "${ctx}/portal/yysb/shouye";
			window.open(url, "_self");
		}
		/**
		 * 打开民警/罪犯信息页面
		 */
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
	                	var fxcjId = $.trim(data[i].FXCJ_ID);//风险采集id
	                	var tzsj = $.trim(data[i].TZSJ);//巡更通知时间
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
		
		
		function updateInfo(id,fxcjId,title,sxsj) {
			var w = 500;
			var h = 300;
			if(fxcjId){
				h = 400;
			}
			if(title == '交班'){
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
		
		/**
		 * 查询在监民警数量
		 */
		function queryPoliceCountInPrison() {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER,
	            	zjmj:"Y"	//统计在监民警
	            },
				dataType : "json",
				success : function(data) {
					$("#policeCountInPrison111").html(data.policeCountInPrison);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		function queryPoliceCountInPrisonjy() {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER,
	            	zjmj:"Y"	//统计在监民警
	            },
				dataType : "json",
				success : function(data) {
					$("#policeCountInPrisonjy").html(data.policeCountInPrison);
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
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountZaiCe",
	            data: {
	            	cusNumber: jsConst.CUS_NUMBER,
	            	zjmj:"Y"	//统计在册民警
	            },
				dataType : "json",
				success : function(data) {
					//console.log(data+"在册民警")
					//console.log(data.policeCountZaiCe)
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
	            	zjmj:"Y"	//统计在册民警
	            },
				dataType : "json",
				success : function(data) {
					//console.log(data+"在册民警")
					//console.log(data.policeCountZaiCe)
					if(data.policeCountBeiQin==""||data.policeCountBeiQin==null){
						("#policeCountBeiQin").html(0);
					}else{
						$("#policeCountBeiQin").html(data.policeCountBeiQin);
					}
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		
		function initMessage() {
			
		}
		//报警处置 红色悬浮窗口
		function toastMsg(msg,type,special,height,width,position){
			var options = {
				 message:msg,
				 special:special||"",
	             iconCls:false,
	             height:height||"auto",
	             width: width || "auto",
	             type:type||"info",
	             position:position,
	             iframePanel:true,//覆盖object对象
			}
			if(special == "custom" && type=="danger"){
				 options.componentCls = "coral-closable";
				 options.timeOut = 0;
				 options.width = "560";
				 options.position = {
						my: "top",
						at: "top top+50",
						of: window
				}
			}
			$.message(options);
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
		
		
		//shebeiyichang     设备异常数据获取并且展示展示（heqh20190617 改成从指挥调度》》故障维护获取总数）
		function queryShebeiyichang(){ 
			var url = "";
			//雁南异常设备从运维平台取数
			if ("4303" == jsConst.CUS_NUMBER) {
				url = "${ctx}/deviceMaintain/record/findYnSbyc";
			} else {
				url = "${ctx}/deviceMaintain/record/listAll.json?dmrCusNumber=" + jsConst.CUS_NUMBER;
			}
			$.ajax({
				type : "post",
				url :  url,
				dataType : "json",
				success : function(data) {
					$("#shebeiyichang").text(data.total);
					 
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
					console.log(errorThrown);
					console.log("获取异常");
				} 
			}); 
		};

		function showSbyc() {
			$("#dialogId_rightHomeMenu").dialog({
				width : 800, //属性
				height : 500, //属性
				title : '设备异常',
				modal : true, //属性
				autoOpen : false,
				url : "${ctx}/deviceMaintain/record/openList"
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		};
		
		function initPage(){
			//填充右侧面板
			//var panel = $("#layout1").layout("panel", "east");
			//panel.panel("refresh", jsConst.basePath+"/xxhj/sy/index");
			if(jsConst.USER_LEVEL==1){
				//省局视频工具栏显示
				window.top.videoClient.setConfig(1,1,1);
				//toDisplay('homeMenu');
			}else if(jsConst.USER_LEVEL==2){
				//监狱视频工具栏显示
				window.top.videoClient.setConfig(1,1,1);
				//toDisplay('homeMenu');
			}else if(jsConst.USER_LEVEL==3){
				//监区视频工具栏隐藏
				window.top.videoClient.setConfig(1,1,1);
				// toXxhjDisplay('jqzfjbxx');
			}else{
				;
			}
			
			$.ajax({
				type : 'post',
				url : jsConst.basePath+'/xtgl/userConfig/findByUcUserId.json',
				data : { "ucUserId":jsConst.USER_ID },
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						if(data.obj){
							var userConfigId=data.obj;
							//设置地图类型全局变量
							jsConst.MAP_TYPE=userConfigId.ucMapType;
						}
						//2D
						else{
							console.log("用户未进行2D/3D地图设置,默认显示2D");
							jsConst.MAP_TYPE='0';
						}
					} else {
						$.message({ iframePanel:true, message : data.msg, cls : "warning" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.alert({ message:textStatus, title:"信息提示", iframePanel:true });
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

		/**
		 * 显示在监民警数据列表
		 */
		function toPoliceInPrisonAreaList(deptName,deptId ,policeNoLists) {
			var cusNumberFlag = "1";
			$("#dialog").dialog({
				width : 1000, 
				height : 800, 
				title : '民警信息 ',
				modal : true, 
				autoOpen : false,
				url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + deptId + "&cusNumber=" + jsConst.CUS_NUMBER+"&policeNoList="+policeNoLists
			});
			$("#dialog").dialog("open");
		}
		/**
		 * 查询在监民警数量
		 */
		 var zjmjsl = 0;
		function queryPoliceCountInPrisonArea() {
			zjmjsl = 0;
			if(jsConst.USER_LEVEL==1){
					var data=[{"NAME":"怀化监狱","CODE":"4336"},
						{"NAME":"永州监狱","CODE":"4304"},
						{"NAME":"女子监狱","CODE":"4307"},
						{"NAME":"雁南监狱","CODE":"4303"},
						{"NAME":"岳阳监狱","CODE":"4312"}
						];
					var divN="";
					for(var i=0; i<data.length; i++) {
                        divN = divN + "<li><span style=width:25%>"
                        +data[i].NAME+
                        "</span><span style=width:25% id='policeCountInPrisonArea"
                        +i+
                        "'></span><span style=width:25%  id='criminal"
                        +i+
                        "'></span><span  style=width:25% id='jqb"
                        +i+
                        "'></span></li>";
                    }
	                    $("#swsb").empty();
	                    $("#swsb").append(divN);
						 for(var i=0;i<data.length;i++){
	                        queryPoliceCountInPrisonAreaByAreaName1($("span[id='policeCountInPrisonArea"+i+"']"), data[i].NAME,data[i].CODE);
	                        queryCriminalName($("span[id='criminal"+i+"']"), data[i].NAME,data[i].CODE);
							queryPoliceCountInqjbSj($("span[id='jqb"+i+"']"), data[i].NAME,data[i].CODE);
							 
	                    }
	                }
			
			if(jsConst.USER_LEVEL==2){
				console.log("进入监狱查询")
				$.ajax({
	                type : "post",
	                url : "${ctx}/common/all/jq",
	                async:false,
	                dataType : "json",
	                success : function(data) {
	                   if(data.length>0) {
	                        var divN="";
	                        for(var i=0; i<data.length; i++) {
	                            divN = divN + "<li><span style=width:25%>"
                        +data[i].NAME+
                        "</span><span style=width:25% id='policeCountInPrisonArea"
                        +i+
                        "'></span><span style=width:25%  id='criminal"
                        +i+
                        "'></span><span  style=width:25% id='jqb"
                        +i+
                        "'></span></li>";
	                        }
	                    }
	                    $("#swsb").empty();
	                    $("#swsb").append(divN);
						 for(var i=0;i<data.length;i++){
	                        queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea"+i+"']"), data[i].NAME,data[i].CODE);
	                        queryCriminalName($("span[id='criminal"+i+"']"), data[i].NAME,data[i].CODE);
							 queryPoliceCountInqjb($("span[id='jqb"+i+"']"), data[i].NAME,data[i].CODE);
	                    }
						$("#policeCountInPrison111").text(zjmjsl);
						
						 
	                },
	                error : function(XMLHttpRequest, textStatus, errorThrown) {
	                    $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
	                }
	            });
			}
		}
		
		/**
		省局查询警囚比
		*/
		function queryPoliceCountInqjbSj(obj, deptName, deptCode){
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrisonSj",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				async:false,
				success : function(data) {
	                $(obj).empty();
					$(obj).append("<a>"+data.policeCountInPrison+"</a>");
					$(obj).append("/");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
			$.ajax({
				type : "post",
	            url : "${ctx}/criminal/getCriminalCount",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				success : function(data) {
	                //罪犯数据
	                $(obj).append("<a href='javascript:void(0);' onclick='toCriminalList(\""+deptCode+"\",\""+deptName+"\",0)'>"+ data.criminalCount + "</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
			
		}
		
		/**
		监狱监狱查询警囚比
		*/
		function queryPoliceCountInqjb(obj, deptName, deptCode){
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				async:false,
				success : function(data) {
	                $(obj).empty();
					$(obj).append("<a>"+data.policeCountInPrison+"</a>");
					$(obj).append("/");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
			$.ajax({
				type : "post",
	            url : "${ctx}/criminal/getCriminalCount",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				success : function(data) {
	                //罪犯数据
	                $(obj).append("<a href='javascript:void(0);' onclick='toCriminalList(\""+deptCode+"\",\""+deptName+"\",0)'>"+ data.criminalCount + "</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
			
		}
		
		/**
		 * 省局查询在监民警数量
		 */
		function queryPoliceCountInPrisonAreaByAreaName1(obj, deptName, deptCode) {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrisonSj",
	            data: {
	            	deptCode: deptCode
	            },
	            async:true,
				dataType : "json",
				success : function(data) {
					/*var policeNoList = data.policeNoList;
	                if (data.policeCountInPrison == 0) {
	                    $(obj).html(data.policeCountInPrison);
	                } else {
	                    $(obj).empty();
	                    var policeNoLists="";
	                    $(obj).append("<a href='javascript:void(0);' onclick='toPoliceInPrisonAreaList(\""+deptName+"\",\""+deptCode+"\",\""+policeNoLists+"\")'>"+ data.policeCountInPrison + "</a>");
	                }*/
	                //暂时隐藏钻取功能
	               // $(obj).html(data.policeCountInPrison);
	                console.log(data.policeCountInPrison);
	                $(obj).empty();
					 $(obj).append("<a>"+data.policeCountInPrison+"</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		/**
		 * 查询在监民警数量
		 */
		function queryPoliceCountInPrisonAreaByAreaName(obj, deptName, deptCode) {
			console.log("部门编号："+deptCode)
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				async:false,
				success : function(data) {
					/*var policeNoList = data.policeNoList;
	                if (data.policeCountInPrison == 0) {
	                    $(obj).html(data.policeCountInPrison);
	                } else {
	                    $(obj).empty();
	                    var policeNoLists="";
	                    $(obj).append("<a href='javascript:void(0);' onclick='toPoliceInPrisonAreaList(\""+deptName+"\",\""+deptCode+"\",\""+policeNoLists+"\")'>"+ data.policeCountInPrison + "</a>");
	                }*/
	                //暂时隐藏钻取功能
	               // $(obj).html(data.policeCountInPrison);
	                $(obj).empty();
	                zjmjsl =zjmjsl +  data.policeCountInPrison;
					$(obj).append("<a>"+data.policeCountInPrison+"</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		

		/**
		 * 查询罪犯数量
		 */
		function queryCriminal() {
			
		}
		function queryCriminalName(obj, deptName,deptCode) {
			$.ajax({
				type : "post",
	            url : "${ctx}/criminal/getCriminalCount",
	            data: {
	            	deptCode: deptCode
	            },
				dataType : "json",
				success : function(data) {
	                $(obj).empty();
	                //罪犯数据
	                $(obj).append("<a href='javascript:void(0);' onclick='toCriminalList(\""+deptCode+"\",\""+deptName+"\",0)'>"+ data.criminalCount + "</a>");
	               //$(obj).append("/");
	              // 重要罪犯数据
	              // $(obj).append("<a href='javascript:void(0);' onclick='toCriminalList(\""+deptCode+"\",\""+deptName+"\",1)'>"+ data.importCount + "</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		
		
		/**
		 * 显示罪犯数据列表
		 * @zyzf 0所有罪犯 1重要罪犯
		 */
		function toCriminalList(deptCode,deptName,zyzf) {
			var cusNumber = "";
			var prisonArea = deptCode;
	    	var prisonAreaName = deptName;
			var prsnrIdntyOrName = $("#mjzf").textbox("getValue");
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
				url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&drpmntName=" +encodeURI(encodeURI(prisonAreaName)) + "&cusNumber=" + cusNumber + "&prsnrIdntyOrName=" + prsnrIdntyOrName + "&zyzf=" + zyzf
			});
			$("#dialog").dialog("open");
		}
		
		
		
 /**
 * 安检
 */
function showSecurityCheckCount() {
    $.ajax({
        type : "post",
        url : "${ctx}/alarm/searchRecordList.json",
        dataType : "json",
        data:{
        	ardCusNumber: jsConst.CUS_NUMBER,
        	ardRemark: "生命探测仪",
        	STime: "<%=STime%>",
        	ETime: "<%=ETime%>"
        },
        success : function(data) {
        	if (!data.total || data.total == 0) {
                $("#securityCheckCount").html(0);
            } else {
                $("#securityCheckCount").empty();
                $("#securityCheckCount").append("<a href='javascript:void(0);' onclick='toSecurityCheckList()'>"+ data.total + "</a>");
            }
        }
    });
}

/**
 * 加载门禁状态
 */
function doorStateCount() {
    // var a =jsConst.CUS_NUMBER;
    // alert(a);
    $.ajax({

        type : "post",
        url : "${ctx}/doorstate/all/getDoorNumByStatus",
        data : {
            cusNumber:jsConst.CUS_NUMBER,
            status:'0'   //0开1关2超时3戒严4离线5其它
        },
        dataType : "json",
        success : function(data) {

        	if(!data.openNum){
        		data.openNum = 0;
        	}
        	$("#doorStateNum").empty();
            $("#doorStateNum").append("<a href='javascript:void(0);' onclick='toDoorStateList()'>"+ data.openNum + "</a>");
        
        },
    });
}

/**
 * 工具发放--heqh20190613改成综合业务平台中的劳动工具》》信息管理
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
                $("#toolNum").append("<a href='javascript:void(0);' onclick='toToolList()'>"+ data.toolNum + "</a>");
            }
        },
    });
}

			function toSecurityCheckList() {
				$("#dialogId_rightHomeMenu").dialog({
					width : 1000, //属性
					height : 800, //属性
					title : '安检',
					modal : true, //属性
					autoOpen : false,
					url : "${ctx}/securityCheck/openDialog"
				});
				$("#dialogId_rightHomeMenu").dialog("open");
			}

/**
 * 门禁状态列表
 */
function toDoorStateList() {
    $("#dialogId_rightHomeMenu").dialog({
        width : 1200, //属性
        height : 800, //属性
        title : '门禁状态',
        modal : true, //属性
        autoOpen : false,
        url : "${ctx}/doorstate/all/openDialog"
    });
    $("#dialogId_rightHomeMenu").dialog("open");
}
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
								divN = divN + "<li style='width:90%'><div class='jianshe'><span style = 'margin-right: 85px;'>" + list[i].dmlx  + "</span></div><div class='tip orange'><span>" + list[i].sdrs + "/"+ list[i].zrs + "</span><span class='iconfont icon-userIcon' style = 'margin-left:25px;'></span></div></li>";
							}
						}
						$("#zwdm").empty();
						$("#zwdm").append(divN);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}

			
			//初始化实时警力柱状图
			var ssjlChart;
			 function initSssjlChart(){
				 ssjlChart = echarts.init(document.getElementById('ssjlChart'));
				 var zfCountList;
				 var mjCountList;
				 var jqb;
				 $.ajax({
						type : 'post',
						url : $("#rootPath").val() + '/aqfxyp/txzs/jqb',
						dataType : 'json',
						async:false,
						success : function(data) {
							var dada = data.data;
							jqb = dada[0];
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						}
					});
				 $.ajax({
						type : 'post',
						url : $("#rootPath").val() + '/aqfxyp/txzs/ssjl',
						dataType : 'json',
						async:false,
						success : function(data) {
							var dada = data.data;
							zfCountList = dada.zfCountList;
							mjCountList = dada.mjCountList;
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						}
					});
				 var option4 = {
						    tooltip: {
						        trigger: "axis"
						    },
						    legend: {
						        data: ["实时警察力", "罪犯人数", "警囚比"],
						        textStyle: {
						            color: "rgb(191, 21, 21)",
						            fontSize: 20,
						            fontStyle: "normal",
						            fontWeight: "bold"
						        },
						        itemWidth: 30,
						        itemHeight: 20,
						        itemGap: 15
						    },
						    toolbox: {
						        feature: {
						            dataView: {
						                readOnly: false
						            }
						        }
						    },
						    xAxis: [
						        {
						            type: "category",
						            axisLine: {
						                onZero: false,
						                lineStyle: {
						                    color: "rgb(127, 255, 0)",
						                    width: 4
						                }
						            },
						            data: ["怀化监狱", "永州监狱", "雁南监狱", "女子监狱", "岳阳监狱"],
						            axisLabel: {
						                textStyle: {
						                    color: "rgb(255, 255, 255)",
						                    fontSize: 15,
						                    fontWeight: "bold"
						                }
						            },
						            splitArea: {
						                show: false
						            },
						            splitLine: {
						                show: false
						            }
						        }
						    ],
						    yAxis: [
						        {
						            type: "value",
						            axisLabel: {
						                textStyle: {
						                    color: "rgb(255, 255, 255)",
						                    fontSize: 18,
						                    fontStyle: "normal",
						                    fontWeight: "bold"
						                },
						                show: true
						            },
						            name: "警察数量",
						            nameTextStyle: {
						                color: "rgb(255, 255, 255)",
						                fontSize: 18,
						                fontWeight: "bold"
						            },
						            axisLine: {
						                lineStyle: {
						                    color: "rgb(127, 255, 0)",
						                    width: 4
						                }
						            },
						            axisTick: {
						                inside: false,
						                lineStyle: {
						                    color: "rgb(255, 255, 255)"
						                },
						                length: 5
						            },
						            splitArea: {
						                show: false
						            },
						            min: 0,
						            max: 5000,
						            splitLine: {
						                show: false
						            },
						            splitNumber: 5
						        },
						        {
						            type: "value",
						            name: "警囚比(%)",
						            min: 0,
						            max: 10,
						            nameTextStyle: {
						                fontSize: 18,
						                color: "rgb(255, 255, 255)",
						                fontWeight: "bold"
						            },
						            nameLocation: "end",
						            splitLine: {
						                show: false
						            },
						            splitArea: {
						                show: false
						            },
						            axisLine: {
						                lineStyle: {
						                    color: "rgb(127, 255, 0)",
						                    width: 4
						                },
						                show: true
						            },
						            splitNumber: 5,
						            axisLabel: {
						                textStyle: {
						                    color: "rgb(255, 255, 255)",
						                    fontSize: 18,
						                    fontWeight: "bold"
						                }
						            }
						        }
						    ],
						    series: [
						        {
						            name: "实时警察力",
						            type: "bar",
						            data: [200, 456, 356, 100, 203]
						        },
						        {
						            name: "罪犯人数",
						            type: "bar",
						            data: zfCountList,
						            yAxisIndex: 0,
						            itemStyle: {
						                normal: {
						                    label: {
						                        show: false
						                    }
						                }
						            }
						        },
						        {
						            name: "警囚比",
						            type: "line",
						            yAxisIndex: 1,
						            data: [1.23, 2.9, 2.1, 3.56, 2.4],
						            symbolSize: 5,
						            itemStyle: {
						                normal: {
						                    lineStyle: {
						                        color: "rgb(255, 0, 0)"
						                    },
						                    label: {
						                        show: true,
						                        position: "top",
						                        textStyle: {
						                            color: "rgb(255, 255, 0)",
						                            fontSize: 20
						                        }
						                    }
						                },
						                emphasis: {
						                    lineStyle: {
						                        color: "rgb(255, 0, 0)"
						                    }
						                }
						            },
						            smooth: true
						        }
						    ],
						    color: ["#ff7f50", "rgb(111, 176, 214)", "#da70d6", "#32cd32", "rgb(66, 117, 206)", "#ff69b4", "#ba55d3", "#cd5c5c", "#ffa500", "#40e0d0", "#1e90ff", "#ff6347", "#7b68ee", "rgb(63, 186, 137)", "#ffd700", "#6699FF", "#ff6666", "#3cb371", "#b8860b", "#30e0e0", "#FFFFFF"],
						    calculable: true
						};
						           
						        // 使用刚指定的配置项和数据显示图表。
						        ssjlChart.setOption(option4);
			} 
			
			 var ltChart;
		   function initLeftChart(){
		         ltChart = echarts.init(document.getElementById('ltChart'));
		         var seriesData ;
		         var radarData;
		         var zf;
		         $.ajax({
					type : 'post',
					url : $("#rootPath").val() + '/aqfxyp/txzs/searchFxgk',
					dataType : 'json',
					async:false,
					success : function(data) {
						var dada = data.data;
						radarData = dada[0];
						seriesData = dada[2];
						zf = dada[1];
				 
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
		        var option3 = {
		             grid: {
		                left: '0',
		                right: '0',
		                bottom: '0',
		                top: '0',

		                // z: 22
		            },
		          backgroundColor: 'transparent',
		             title: {
		                    text: zf,
		                    x: '35%',
		                    y: 'center',
		                    textStyle: {
		                        color: "#fff",
		                        fontSize: 64
		                    },
		                    zlevel:24
		                },
		            radar: [{
		                 title: {
		                        text: 61,
		                    },
		                indicator: radarData,
		                center: ['50%', '50%'],
		                shape: 'polygon',
		                radius: '68%',
		                nameGap: 3,
		                name: {
		                        formatter:function (value, obj) {
		                            var num = obj.num
		                            return '{value|' + value + '}'+' {num|'+ num +'}';
		                },
		                rich:{
		                    num: {
		                      color: "#ee8c04",
		                      fontSize: 26,
		                      // padding: [0, 2],
		                      align: 'center'
		                    },
		                value: {
		                    color: "#c9caca",
		                    fontSize: 26 ,
		                    align: 'center'
		                   }
		                },
		                textStyle: {
		                     color:'#72ACD1'
		                   }
		                },
		                axisLine: {
		                    lineStyle: {
		                        color: '#cc9b06'
		                    },
		                    itemStyle:{
		                    color: '#cc9b06'
		                }
		                },
		                splitArea:{
		                    areaStyle:{
		                        color:'transparent'
		                    }
		                },
		               splitLine:{
		                   lineStyle:{
		                       color: '#cc9b06'
		                   }
		               }
		             
		            }],
		            series: [{
		                type: 'radar',
		                symbolSize: 0,
		                areaStyle: {
		                    normal: {
		                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0,
		                            [{
		                                offset: 0,
		                                color: '#96750b'
		                            }, {
		                                offset: 0.5,
		                                color: '#715b0e'
		                            }, {
		                                offset: 1,
		                                color: '#171812'
		                            }], false)
		                    }
		                },
		                lineStyle: {
		                    normal: {
		                        color: '#cc9b06',
		                        type: 'solid',
		                        width: 1
		                    }
		                },
		                data: [{
		                    value: seriesData,
		                    label: {
		                        show: 'true'
		                    }
		                }]
		            }]
		        };
		        ltChart.setOption(option3);
		    }
		  
		   //添加监外情况数据  heqh 20190923
		   function refreshJwqk(){
				$.ajax({
					type : 'post',
					url : '${ctx}/rwgl/rwjs/searchJwqk.json',
					dataType : 'json',
					success : function(data) {
						var divN = "";
						for(var i=0; i<data.length; i++){
							divN = divN + "<li style='height:15%;'><span style='width:16%;'><p style='font-size:16px'>"+ data[i].JYNAME +"</p></span> <span style='width:26%;'><p style='font-size:16px'>" + data[i].XM + "</p></span><span style='width:26%;'><p style='font-size:16px'>" + data[i].FLAG + "</p></span> <span style='width:26%;'><p style='font-size:16px'>"+ data[i].SY +"</p></span></li>";
						}
						$("#swdb").empty();
						$("#swdb").append(divN);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					}
				});
			}
		   
	</script>
</body>

</html>

