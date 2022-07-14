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

			<div data-options="region:'west'" style="width:355px;">
				<div class="left">
					<div class="left-top">
						<h3>今日值班</h3>
						<div id="tabs1">
							<ul>
								<li><a href="#fragment-1">指挥中心</a></li>
								<li><a href="#fragment-2">监区值班</a></li>
							</ul>
							<div id="fragment-1">
								<ul>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('总值班长');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">
												总值班长
												<!-- <a>更多+</a> -->
												<span><a href="javascript: void(0);" onClick="showJyldTodayDutyList('总值班长')">更多+</a></span>
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
												值班主任
												<span><a href="javascript: void(0);" onClick="showJyldTodayDutyList('值班主任')">更多+</a></span>
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
												指挥中心
												<span><a href="javascript: void(0);" onClick="showZhzxTodayDutyList()">更多+</a></span>
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
											<p class="dutyTitle">
												在监民警
												<!-- <span><a href="javascript: void(0);">更多+</a></span> -->
											</p>
											<p class="dutyNme" id="policeCountInPrison">0</p>
										</div>
									</li>
								</ul>
							</div>
							<div id="fragment-2">
								<ul>
									<%-- <li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('一监区值班人员');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('一监区值班人员');">
												一监区
												<span><a href="javascript: void(0);">更多+</a></span>
											</p>
											<p class="dutyNme">
												<a>李洁</a>&nbsp;
												<a>王尼亚</a>&nbsp;
												<a>何梦肖迪</a>
											</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('二监区值班人员');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('二监区值班人员');">
												二监区
												<span><a href="javascript: void(0);">更多+</a></span>
											</p>
											<p class="dutyNme">
												<a>李华楠</a>&nbsp;
												<a>张焰</a>&nbsp;
												<a>王晶</a>
											</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('三监区值班人员');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('三监区值班人员');">
												三监区
												<span><a href="javascript: void(0);">更多+</a></span>
											</p>
											<p class="dutyNme">
												<a>张海娜</a>&nbsp;
												<a>王嘉炜</a>&nbsp;
												<a>岳明</a>
											</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('四监区值班人员');" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('四监区值班人员');">
												四监区
												<span><a href="javascript: void(0);">更多+</a></span>
											</p>
											<p class="dutyNme">
												<a>刘丽丽</a>&nbsp;
												<a>李瑶</a>&nbsp;
												<a>郭颖</a>
											</p>
										</div>
									</li> --%>
								</ul>
							</div>
						</div>
					</div>
					<div class="left-bottom">
						<!-- 查询分类 -->
						<input id="mjtype"/>
						<!-- 查询内容 -->
						<input id="mjzf" type="text" />
						
						<h3>今日押犯情况</h3>
						<div class="realTimeAlert">
							<h4>在册罪犯:<span class="num" id="zfZcrs">0</span></h4>
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJhzs">
										<p class="custodyTitle alertType">解回再审</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfJhzs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfYwjy">
										<p class="custodyTitle alertType">狱外寄押</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfYwjy">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTtrs">
										<p class="custodyTitle alertType">脱逃人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfTtrs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDcrs">
										<p class="custodyTitle alertType">调出人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfDcrs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSyrs">
										<p class="custodyTitle alertType">收押人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfSyrs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSwrs">
										<p class="custodyTitle alertType">死亡人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfSwrs">0</p> -->
									</div>
								</li>

							</ul>
						</div>
						<div class="realTimeOffense">
							<h4><span>在押:</span><span class="num" id="zfZyrs">0</span></h4>
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJwzx">
										<p class="custodyTitle alertType">监外执行</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfJwzx">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfWcjy">
										<p class="custodyTitle alertType">住院人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfWcjy">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTxlj">
										<p class="custodyTitle alertType">离监探亲</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfTxlj">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSfrs">
										<p class="custodyTitle alertType">释放人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfSfrs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="ZfQsrs">
										<p class="custodyTitle alertType">遣送人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="ZfQsrs">0</p> -->
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDrrs">
										<p class="custodyTitle alertType">调入人数</p>
										<!-- <p class="custodyNum alertNum" style="cursor: pointer;" id="zfDrrs">0</p> -->
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div data-options="region:'east'" style="width:277px;">
				<div class="right">
					<div class="on-duty right" style="height:640px;">
						<h2>一日执勤</h2>
						<div class="tolist" id="tolistDiv">
							<!-- <div class="tolist-item status">
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
							</div> -->
						</div>
						<ul class="duty-content">
							<div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0">
							<div id="yrzqUl" class="mCSB_container" style="position:relative; top:0; left:0;" dir="ltr">
							
							</div>
							</div>
							<!-- <li class="yrzq finished">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">8:00-9:00</p>
									<p class="text">督办整改问题</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq finished notice">
								<div class="info-icon">
									<span class="iconfont icon-talk"></span>
								</div>
								<div>
									<p class="time">9:00-10:00</p>
									<p class="text">个别谈话教育</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq finished">
								<div class="info-icon">
									<span class="iconfont icon-video"></span>
								</div>
								<div>
									<p class="time">10:00-10:30</p>
									<p class="text">视频轮巡监督</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq finished active">
								<div class="info-icon">
									<span class="iconfont icon-mental"></span>
								</div>
								<div>
									<p class="time">10:30-11:00</p>
									<p class="text">心理矫治教育</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-knock-off"></span>
								</div>
								<div>
									<p class="time">11:00-12:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon" style="border-color: #e60012">
									<span class="iconfont icon-knock-off"  style="color: #e60012"></span>
								</div>
								<div>
									<p class="time" style="color: #e60012">12:40-12:30</p>
									<p class="text" style="color: #e60012">罪犯收工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">15:00-17:00</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">17:00-18:00</p>
									<p class="text">罪犯收工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">18:00-19:00</p>
									<p class="text">罪犯晚餐</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">22:00-23:00</p>
									<p class="text">点名</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li> -->
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center'" class="main">
				<div class="top" id="top">
					<h3><span>风险概况</span><span>实时警情</span></h3>
					<div id="ltChart" class="ltChart top-box"></div>
				
          			<div class="top-box top-right">
							<div class="real-time">
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
												<img class="alarm-img sd" src="${ctx}/static/bj-cui/img/sdian.png" alt="电网状态">
												<div class="alarm-info">
													<p class="alarm-mount dwzt">正常</p>
													<p class="alarm-title">电网状态</p>
												</div>
											</li>
										<%--<li class="alarm-item">
												<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/watch.png" alt="智能钥匙">
												<div class="alarm-info">
													<p class="alarm-mount common" id="smartKey" style="cursor: pointer;" onClick="openMenuDialog(this, event,'znys')">22</p>
													<p class="alarm-title">智能钥匙</p>
												</div>
											</li>--%>
											<li class="alarm-item">
												<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/device.png" alt="设备故障">
												<div class="alarm-info">
													<p class="alarm-mount common" id="shebeiyichang">0</p>
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
												<li class="alarm-item"><img class="alarm-img"
									src="${ctx}/static/bj-cui/img/ffsj.png" alt="非法手机">
									<div class="alarm-info">
										<p class="alarm-mount common"  id="ffsj" style="cursor: pointer"
											onclick="openFfsjtj()">0</p>
										<p class="alarm-title">非法手机</p>
									</div></li>
													<li class="alarm-item">
															<img class="alarm-img" src="${ctx}/static/bj-cui/img/ljff.png" alt="劳具已发放">
															<div class="alarm-info">
																<p class="alarm-mount common" id="toolNum">0</p>
																<p class="alarm-title">劳具已发放</p>
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
															<img class="alarm-img" src="${ctx}/static/bj-cui/img/doorstate.png" alt="门禁状态">
															<div class="alarm-info">
																<p class="alarm-mount common" id="doorStateNum">0</p>
																<p class="alarm-title">门禁状态</p>
															</div>
														</li>
									</ul>
							
	
								</div>
					</div>
				</div>
				<div class="center-box clearfix">
					<div class="center-left" style="width:349.99px;">
						<!-- <h3>早晚点名</h3>
						<div class="left-box" >
							<div class="tcontent">
								<ul id="zwdm">
								</ul>
							</div>
						</div> -->
						<h3>事务督办<span><a href="javascript: void(0);" onClick="openMenuDialog(this, event, 'swdbgd')">更多+</a></span></h3>
						<div class="left-box" >
							<div class="top-box top-right">
								<div class="todo_List">
									<ul class="theader clearfix">
										<li style="width:36%;">填报时间</li>
										<!-- <li>事务类型</li> -->
										<li style="width:36%;">事务内容</li>
										<li style="width:28%;">事物状态</li>
									</ul>
									<div class="tcontent">
										<ul id="swdb">
										
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="center-middle">
						<h3>生物识别<span><a href="javascript: void(0);" onClick="openMenuDialog(this, event, 'swsb')">更多+</a></span></h3>
						<div class="middle-box">
							<div class="top-box top-right">
								<div class="todo_List">
									<ul class="theader clearfix">
										<li>监区</li>
										<li>民警</li>
										<li>罪犯/重要罪犯</li>
									</ul>
									<div class="tcontent">
										<ul id="swsb">
											<li>
												<span>一监区</span>
												<span id="policeCountInPrisonArea1">0</span>
												<span id="criminal1">0/<i>0</i></span>
											</li>
											<li>
												<span>二监区</span>
												<span id="policeCountInPrisonArea2">0</span>
												<span id="criminal2">0/<i>0</i></span>
											</li>
											<li>
												<span>三监区</span>
												<span id="policeCountInPrisonArea3">0</span>
												<span id="criminal3">0/<i>0</i></span>
											</li>
											<li>
												<span>四监区</span>
												<span id="policeCountInPrisonArea4">0</span>
												<span id="criminal4">0/<i>0</i></span>
											</li>
											<li>
												<span>五监区</span>
												<span id="policeCountInPrisonArea5">0</span>
												<span id="criminal5">0/<i>0</i></span>
											</li>
											<li>
												<span>六监区</span>
												<span id="policeCountInPrisonArea6">0</span>
												<span id="criminal6">0/<i>0</i></span>
											</li>
											<li>
												<span>七监区</span>
												<span id="policeCountInPrisonArea7">0</span>
												<span id="criminal7">0/<i>0</i></span>
											</li>
											<li>
												<span>八监区</span>
												<span id="policeCountInPrisonArea8">0</span>
												<span id="criminal8">0/<i>0</i></span>
											</li>
											<li>
												<span>九监区</span>
												<span id="policeCountInPrisonArea9">0</span>
												<span id="criminal9">0/<i>0</i></span>
											</li>
											<li>
												<span>十监区</span>
												<span id="policeCountInPrisonArea10">0</span>
												<span id="criminal10">0/<i>0</i></span>
											</li>
											<li>
												<span>十一监区</span>
												<span id="policeCountInPrisonArea11">0</span>
												<span id="criminal11">0/<i>0</i></span>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="center-right" style="width:530.98px;">
						<h3>监内动态<span><a href="javascript: void(0);" onclick="openMenuDialog(this, event, 'yrzq')">更多+</a></span></h3>
						<div class="right-box">
							<div class="tcontent">
								<ul id="autoScrollR">
								</ul>
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
	// 加载实时警情数据
	loadRealTimeJqData();
	/* initUserInfo(); */
	/* var panel = $("#layout1").layout("panel", "center");
	panel.panel("refresh", "${ctx}/portal/planeMap"); */
//	queryShebeiyichang();
	
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
	
});

var urlfhs = "${ctx}/portal/zhshouye";
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
	queryPoliceCountInPrison();
}

/**
 * 指挥中心值班数据
 */
function loadZhzxTodayDutyData() {
	// 总值班长数据
	loadJyldDutyList($("#zzbzDutyList"), "总值班长");
	
	// 值班主任数据
	loadJyldDutyList($("#zbzrDutyList"), "值班主任");
	
	// 指挥中心数据
	loadZhzxDutyList($("#zhzxDutyList"), "");
}

/**
 * 监狱领导
 */
function loadJyldDutyList(obj, orderName) {
	$.ajax({
		type : "post",
        url : "${ctx}/zbgl/todayDuty/getJyldTodayDuty",
        data: {
        	categoryName: "监狱领导",
        	modeName: "监狱领导",
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
	var categoryName = new Base64().multiEncode("监狱领导", 2);
	// 值班模板名称
	var modeName = new Base64().multiEncode("监狱领导", 2);
	
	$("#dialogId_rightHomeMenu").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : orderName + '今日值班列表',
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/zbgl/todayDuty/toIndex?categoryName=" + categoryName + "&modeName=" + modeName + "&orderName=" + new Base64().multiEncode(orderName, 2)
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
			refreshSwdb();
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
	            	cusNumber: jsConst.CUS_NUMBER
	            },
				dataType : "json",
				success : function(data) {
	                if (data.policeCountInPrison == 0) {
	                    $("#policeCountInPrison").html(data.policeCountInPrison);
	                } else {
	                    $("#policeCountInPrison").empty();
	                    $("#policeCountInPrison").append("<a href='javascript:void(0);' onclick='toPoliceInPrisonList()'>"+ data.policeCountInPrison + "</a>");
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
		
		
		//shebeiyichang     设备异常数据获取并且展示展示
		function queryShebeiyichang(){
			 
		 	 $.ajax({
				type : "post",
				url : '${ctx}/wghgl/yrzq/getYwyth?type=1',
				dataType : "json",
				success : function(data) {
					 
					 for(var i = 0;i<data.length;i++){
						 var dada = data[i].value;
						 $("#shebeiyichang").text(dada);
					 }
					 
				},	error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
					console.log(errorThrown);
					console.log("获取异常");
				} 
		 	}); 
		} 	

		
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
						url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + deptId + "&cusNumber=" + jsConst.CUS_NUMBER+"&policeNoList="+policeNoLists
					});
					
					$("#dialog").dialog("open");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					// jsConst.CUS_NUMBER_FLAG = "";
				}
			});
			
			
			
			
			
			
		}
		/**
		 * 查询在监民警数量
		 */
		function queryPoliceCountInPrisonArea() {
            $.ajax({
                type : "post",
                url : "${ctx}/common/all/jq",
                async:false,
                dataType : "json",
                success : function(data) {
                    console.info(data);
                   if(data.length>0) {
                        var divN="";
                        for(var i=0; i<data.length; i++) {
                            divN = divN + "<li><span>"+data[i].NAME+"</span><span id='policeCountInPrisonArea"+i+"'></span><span id='criminal"+i+"'></span></li>";
                        }
                    }
                    $("#swsb").empty();
                    $("#swsb").append(divN);
					 for(var i=0;i<data.length;i++){
                        queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea"+i+"']"), data[i].NAME,data[i].CODE);
                        queryCriminalName($("span[id='criminal"+i+"']"), data[i].NAME,data[i].CODE);
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
                }
            });
			/*queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea1']"), "一监区","11058001");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea2']"), "二监区","11058002");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea3']"), "三监区","11058003");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea4']"), "四监区","11058004");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea5']"), "五监区","11058005");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea6']"), "六监区","11058006");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea7']"), "七监区","11058007");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea8']"), "八监区","11058008");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea9']"), "九监区","11058009");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea10']"), "十监区","11058010");
			queryPoliceCountInPrisonAreaByAreaName($("span[id='policeCountInPrisonArea11']"), "十一监区","11058011");*/
		}
		
		/**
		 * 查询在监民警数量
		 */
		function queryPoliceCountInPrisonAreaByAreaName(obj, deptName, deptId) {
			$.ajax({
				type : "post",
	            url : "${ctx}/common/all/getPoliceCountInPrison",
	            data: {
	            	deptName: deptName
	            },
				dataType : "json",
				success : function(data) {
					var policeNoList = data.policeNoList;
	                if (data.policeCountInPrison == 0) {
	                    $(obj).html(data.policeCountInPrison);
	                } else {
	                    $(obj).empty();
	                    var policeNoLists="";
	        			for(var i=0;i<policeNoList.length;i++){
	        				var policeNoListMap = policeNoList[i];
	        				if(i>=policeNoList.length-1){
	        					policeNoLists = policeNoLists+policeNoListMap.POLICE_NO;
	        				}else{
	        					policeNoLists = policeNoLists+policeNoListMap.POLICE_NO+",";
	        				}
	        				
	        			}
	        			 
	                    
	                    $(obj).append("<a href='javascript:void(0);' onclick='toPoliceInPrisonAreaList(\""+deptName+"\",\""+deptId+"\",\""+policeNoLists+"\")'>"+ data.policeCountInPrison + "</a>");
	                }
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
			/*queryCriminalName($("span[id='criminal1']"), "一监区","11058001");
			queryCriminalName($("span[id='criminal2']"), "二监区","11058002");
			queryCriminalName($("span[id='criminal3']"), "三监区","11058003");
			queryCriminalName($("span[id='criminal4']"), "四监区","11058004");
			queryCriminalName($("span[id='criminal5']"), "五监区","11058005");
			queryCriminalName($("span[id='criminal6']"), "六监区","11058006");
			queryCriminalName($("span[id='criminal7']"), "七监区","11058007");
			queryCriminalName($("span[id='criminal8']"), "八监区","11058008");
			queryCriminalName($("span[id='criminal9']"), "九监区","11058009");
			queryCriminalName($("span[id='criminal10']"), "十监区","11058010");
			queryCriminalName($("span[id='criminal11']"), "十一监区","11058011");*/
		}
		function queryCriminalName(obj, deptName,deptCode) {
			$.ajax({
				type : "post",
	            url : "${ctx}/criminal/getCriminalCount",
	            data: {
	            	deptName: deptName
	            },
				dataType : "json",
				success : function(data) {
	                $(obj).empty();
	                $(obj).append("<a href='javascript:void(0);' onclick='toCriminalList(\""+deptCode+"\",\""+deptName+"\")'>"+ data.criminalCount + "/0" + "</a>");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
		
		/**
		 * 显示罪犯数据列表
		 */
		function toCriminalList(deptCode,deptName) {
			 
			
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
				url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&drpmntName=" +encodeURI(encodeURI(prisonAreaName)) + "&cusNumber=" + cusNumber + "&prsnrIdntyOrName=" + prsnrIdntyOrName
			});
			$("#dialog").dialog("open");
		}
		
		
		
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

/**
 * 加载门禁状态
 */
function doorStateCount() {
    // var a =jsConst.CUS_NUMBER;
    // alert(a);
    $.ajax({

        type : "post",
        url : "${ctx}/doorstate/all/getDooOpenNum",
        data : {
            cusNumber:jsConst.CUS_NUMBER
        },
        dataType : "json",
        success : function(data) {

            if (data.openNum == 0) {
                $("#doorStateNum").html(data.openNum);
            } else {
                $("#doorStateNum").empty();
                $("#doorStateNum").append("<a href='javascript:void(0);' onclick='toDoorStateList()'>"+ data.openNum + "</a>");
            }
        },
    });
}

/**
 * 工具发放
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
					title : '安检结果',
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
		  
		 
		   
		  
		 
		   
		   
		   
		 
		   
	</script>
</body>

</html>

