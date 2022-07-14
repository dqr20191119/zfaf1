<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>接报系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/icon-font/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/jbsy.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/renwuguanli.css">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/icon-font/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css">
	 	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/zfxx_dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/gerenminjing.css">
	
</head>

<body>
	<div class="header">
		<div class="logo">
			<img src="${ctx}/static/bj-cui/img/renwuguanli/header_logo.png" alt="北京市监狱（戒毒）管理局 ">
		</div>
		<div class="header-content">
			<div class="header-item date">
				<span class="icon iconfont icon-datepiceker"></span>
				<span class="title">${dqrq}</span>
			</div>
			<div class="header-item">
				<span class="icon iconfont icon-police2"></span>
				<span class="title" id="dqyh">当前用户：</span>
			</div>
			<%--
			<div class="header-item dropdow">
				<span class="icon iconfont icon-xialadown">
					<div class="dropdown-content">
						<ul class="menu">
							<li class="menu-item">用户信息</li>
							<li class="menu-item">用户信息2</li>
						</ul>
					</div>
				</span>
			</div> --%>
			<div class="header-item">
				<span class="icon iconfont icon-system-setting" title="退出系统" style="cursor: pointer;" onClick="syLogout();"></span>
			</div>
		</div>
		<ul class="tolist home">
		<li class="tolist-item status home-page" onClick="toZhsy();">
				首页
			</li>
			
			<li class="tolist-item status" onclick="openAqfxypSystem()">
				安全风险研判
				 
			</li>
			
			<li class="tolist-item status">
				执勤管理
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						智能统计
						<ul>
							<li class="tolist-menuitem">
								民警信息统计
							</li>
							<li class="tolist-menuitem">
								罪犯信息统计
							</li>
							<li class="tolist-menuitem">
								警情统计
							</li>
							<li class="tolist-menuitem">
								电网状态统计
							</li>
							<li class="tolist-menuitem">
								事物督办统计
							</li>
							<li class="tolist-menuitem">
								设备统计
							</li>
							<li class="tolist-menuitem">
								外来车俩统计
							</li>
							<li class="tolist-menuitem">
								外来人员统计
							</li>
							<li class="tolist-menuitem">
								行为侦测统计
							</li>
							<!-- <li class="tolist-menuitem">
								非法手机统计
							</li> -->
							<li class="tolist-menuitem">
								劳动工具发放统计
							</li>
							<li class="tolist-menuitem">
								敏感词统计
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						一日执勤
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jhrc')">
								计划日程
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'zqgl')">
								执勤管理
							</li>
							<li class="tolist-menuitem">
								监内动态查询
							</li>
							 
						</ul>
					</li>
					<!-- <li class="tolist-menuitem">
						网格划分
						<ul>
							 
							<li class="tolist-menuitem">
								安全网格
							</li>
							<li class="tolist-menuitem">
								党建网格
							</li>
						</ul>
					</li> -->
					<li class="tolist-menuitem">
						值排班
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'lbgl')">
								类别管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'gwgl')">
								岗位管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'bcgl')">
								班次管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'mbsz')">
								模板设置
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbbp')">
								值班编排
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbcx')">
								值班查询
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班分析
							</li>
							<!-- <li class="tolist-menuitem">
								值班导入
							</li> -->
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班日志
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								交接班
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						数据接报
						<ul>
							<li class="tolist-menuitem">
								任务下发
								<ul>
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'xfrw')">
										下发任务
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jsrw')">
										接收任务
									</li>
								</ul>
							</li> 
							<li class="tolist-menuitem">
								待办事项
							</li>
							<li class="tolist-menuitem">
								报表报送
							</li>
							<li class="tolist-menuitem">
								通知公告
							</li>
						</ul>
					</li>
					 
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jdjc')">
						监督检查
					</li>
					 
				</ul>
			</li>
				
			<li class="tolist-item status">
				生物识别
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						运动轨迹
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem">
								依图运动轨迹
								 <ul class="tolist-menuitem">
								 	<li class="tolist-menuitem" onclick="openDlyz()">
								 		登陆认证
								 	</li>
								 	<li class="tolist-menuitem" onclick="openRygj()">
								 		人员轨迹
								 	</li>
								 	<li class="tolist-menuitem" onclick="openRlsb()">
								 		人脸识别
								 	</li>
								 </ul>
							</li>
							<li class="tolist-menuitem" onclick="openHkxt()">
								海康运动轨迹
							</li>
						</ul>
					</li>
					 
				</ul>
			</li>
			
			<li class="tolist-item status" onclick="openYysbxt()">
				语音识别
			</li>
			 
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
			</li>
			<li class="tolist-item status">
				移动警务
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						移动终端数量统计
					</li>
					<li class="tolist-menuitem">
						移动终端状态统计
					</li>
					<li class="tolist-menuitem">
						个别谈话情况统计
					</li>
					<li class="tolist-menuitem">
						移动端点名统计
					</li>
					<li class="tolist-menuitem">
						证据采集情况统计
					</li>
					<li class="tolist-menuitem">
						移动OA使用情况统计
					</li>
				</ul>
			</li>
			<!-- <li class="tolist-item status" id="monitor">
				首页
			</li>
			<li class="tolist-item status" id="history" onclick="openRczs()">
				日常值守
			</li>
			<li class="tolist-item status" id="analysis">
				工作日志
			</li>
			<li class="tolist-item status" id="analysis" onclick="openRwgl()">
				任务管理
			</li>
			<li class="tolist-item status" id="analysis">
				通知公告
			</li>
			<li class="tolist-item status" id="analysis">
				报表抄送
			</li>
			<li class="tolist-item status" id="analysis">
				系统维护
			</li> -->
		</ul>
	</div>
	<div class="container-box jbsy">
		<div id="layout1" class="jbxt-box">
			<div data-options="region:'center'">
				<div class="left">
					<div class="left-top">
						<h3>当前值班情况</h3>
						<div id="jbxt">
							<ul>
								<li><a href="#jbxt-1">监狱管理局</a></li>
								<li><a href="#jbxt-2">监狱</a></li>
								<li><a href="#jbxt-3">第二监狱</a></li>
							</ul>
							<div id="jbxt-1">
								<p class="zbsj">值班时间</p>
								<p class="zbsj-time">2018-04-25 06:00-2018-04-25 21:00</p>
								<ul>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">主要领导</p>
											<p class="dutyNme">邢玫</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">带班领导</p>
											<p class="dutyNme">肖博</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">指挥中心</p>
											<p class="dutyNme"><a>李秀荣</a>&nbsp;李兴华&nbsp;李兴华&nbsp;李兴华&nbsp</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">在监民警</p>
											<p class="dutyNme">650</p>
										</div>
									</li>
								</ul>
							</div>
							<div id="jbxt-2">
								<ul>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">主要领导</p>
											<p class="dutyNme">邢玫</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">带班领导</p>
											<p class="dutyNme">肖博</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">指挥中心</p>
											<p class="dutyNme">李秀荣&nbsp;李兴华&nbsp;张斌</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">在监民警</p>
											<p class="dutyNme">650</p>
										</div>
									</li>
								</ul>
							</div>
							<div id="jbxt-3">
								<ul>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">主要领导</p>
											<p class="dutyNme">邢玫</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">带班领导</p>
											<p class="dutyNme">肖博</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">指挥中心</p>
											<p class="dutyNme">李秀荣&nbsp;李兴华&nbsp;张斌</p>
										</div>
									</li>
									<li>
										<div class="info-icon dutyIcon">
											<img src="${ctx}/static/bj-cui/img/command/leader.png" />
										</div>
										<div class="dutyPerson">
											<p class="dutyTitle">在监民警</p>
											<p class="dutyNme">650</p>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="left-bottom">
						<!-- 今日押犯情况 start heqh-->
						<h3>今日押犯情况</h3>
						<div class="realTimeAlert">
							<h4>在册罪犯:<span class="num" id="today1_modify">3504</span></h4>
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJhzs">
										<p class="custodyTitle alertType">死缓</p>
										  <p class="custodyNum alertNum" style="cursor: pointer;" id="today6_modify">4</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfYwjy">
										<p class="custodyTitle alertType">无期</p>
										  <p class="custodyNum alertNum" style="cursor: pointer;" id="today7_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTtrs">
										<p class="custodyTitle alertType">三类罪犯</p>
										  <p class="custodyNum alertNum" style="cursor: pointer;" id="today8_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDcrs">
										<p class="custodyTitle alertType">三涉犯</p>
										  <p class="custodyNum alertNum" style="cursor: pointer;" id="today30_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSyrs">
										<p class="custodyTitle alertType">邪教犯</p>
										 <p class="custodyNum alertNum" style="cursor: pointer;" id="today31_modify">3444</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSwrs">
										<p class="custodyTitle alertType">危险犯</p>
										 <p class="custodyNum alertNum" style="cursor: pointer;" id="today33_modify">0</p>  
									</div>
								</li>

							</ul>
						</div>
						<div class="realTimeOffense">
							<h4><span>在押:</span><span class="num" id="today9_modify">3444</span></h4>
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfJwzx">
										<p class="custodyTitle alertType">监外就诊</p>
										 <p class="custodyNum alertNum" style="cursor: pointer;" id="today10_modify">56</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfWcjy">
										<p class="custodyTitle alertType">监外住院</p>
										 <p class="custodyNum alertNum" style="cursor: pointer;" id="today37_modify">0</p>  
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfTxlj">
										<p class="custodyTitle alertType">离监探亲</p>
										<p class="custodyNum alertNum" style="cursor: pointer;" id="today12_modify">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="zfSfrs">
										<p class="custodyTitle alertType">禁闭</p>
										<p class="custodyNum alertNum" style="cursor: pointer;" id="today28_modify">187</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow" id="ZfQsrs">
										<p class="custodyTitle alertType">其他外出</p>
										 <p class="custodyNum alertNum" style="cursor: pointer;" id="ZfQsrs">0</p> 
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow"  id="zfDrrs">
										<p class="custodyTitle alertType">隔离审查</p>
										<p class="custodyNum alertNum" style="cursor: pointer;" id="today29_modify">0</p> 
									</div>
								</li>
							</ul>
						</div>
						<!-- 今日押犯情况 end -->
						
						<%-- 以前的版本注释掉
						<div id="jryf">
							<ul>
								<li><a href="#jryf-1">全局</a></li>
								<li><a href="#jryf-2">各监狱</a></li>
							</ul>
							<div id="jryf-1" class="jryf-bottom">
								<div class="realTimeAlert">
									<h4>在册罪犯:<span class="num">9000</span></h4>
									<ul>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">在押犯人</p>
												<p class="custodyNum alertNum">7892</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">加载戒具</p>
												<p class="custodyNum alertNum">1</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">关押禁闭</p>
												<p class="custodyNum alertNum">0</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">隔离审查</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">立案侦查</p>
												<p class="custodyNum alertNum">0</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>

									</ul>
								</div>
								<div class="realTimeOffense">
									<h4><span>在押:</span><span class="num">8800</span></h4>
									<ul>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
										<li>
											<div class="info-icon custodyIcon alermIcon">
												<span class="iconfont icon-prisoner"></span>
											</div>
											<div class="custodyPerson alertShow">
												<p class="custodyTitle alertType">解回重申</p>
												<p class="custodyNum alertNum">9</p>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div id="jryf-2"  class="jryf-bottom">
									<div class="realTimeAlert">
											<h4>在册罪犯:<span class="num">9000</span></h4>
											<ul>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">在押犯人</p>
														<p class="custodyNum alertNum">7892</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">加载戒具</p>
														<p class="custodyNum alertNum">1</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">关押禁闭</p>
														<p class="custodyNum alertNum">0</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">隔离审查</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">立案侦查</p>
														<p class="custodyNum alertNum">0</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
		
											</ul>
										</div>
										<div class="realTimeOffense">
											<h4><span>在押:</span><span class="num">8800</span></h4>
											<ul>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
												<li>
													<div class="info-icon custodyIcon alermIcon">
														<span class="iconfont icon-prisoner"></span>
													</div>
													<div class="custodyPerson alertShow">
														<p class="custodyTitle alertType">解回重申</p>
														<p class="custodyNum alertNum">9</p>
													</div>
												</li>
											</ul>
										</div>
							</div>
						</div>
						
						--%>
					</div>

				</div>
				<div class="right">
					<div class="right-1">
						<h3>待办事项<span>更多+</span></h3>
						<div class="dasx">
							<div id="dbsx">
								<ul>
									<li><a href="#dbsx-1">值班日志</a></li>
									<li><a href="#dbsx-2">工作日志</a></li>
								</ul>
								<div id="dbsx-1" class="dbsx-bottom">
									<div class="top-box top-right">
										<div class="todo_List">
											<ul class="tcontent dbsx-scroll1">
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
											</ul>

										</div>
									</div>
								</div>
								<div id="dbsx-2" class="dbsx-bottom">
									<div class="top-box top-right">
										<div class="todo_List">
											<ul class="tcontent dbsx-scroll1">
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
												<li>
													<span>本单位未处理的值班记录内容1</span>
													<span>2018-0-04 10:20:11</span>
												</li>
											</ul>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="right-2">
						<h3>通知公告<span>更多+</span></h3>
						<div class="right-box dbsx-bottom">
							<div class="top-box top-right">
								<div class="todo_List">
									<ul class="tcontent dbsx-scroll1">
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
									</ul>

								</div>
							</div>
						</div>
					</div>
					<div class="right-3">
						<h3>任务安排<span>更多+</span></h3>
						<div class="right-box dbsx-bottom">
							<div class="top-box top-right">
								<div class="todo_List">
									<ul class="tcontent dbsx-scroll1">
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
										<li>
											<span>本单位未处理的值班记录内容1</span>
											<span>2018-0-04 10:20:11</span>
										</li>
									</ul>

								</div>
							</div>
						</div>
					</div>
					<div class="right-4">
						<h3>报表报送情况<span>更多+</span></h3>
						<div class="right-box dbsx-bottom">
							<div class="top-box top-right bbbs">
								<div class="todo_List">
									<ul class="tcontent dbsx-scroll1">
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
										<li>
											<span>北京市监狱 于2018-04-01 15:00 向市局报送<i>《向市局指挥中心每日报送情况工作表》</i></span>
										
										</li>
									</ul>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
		<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
		<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
		<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
		<script src="${ctx}/static/system/jsConst.js"></script>
		<script src="${ctx}/static/system/common.js"></script>
	
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
	
		<script>
			$("#layout1").layout({
				fit: true
			})
			$('#jbxt').tabs({
				heightStyle: 'fill', //属性: 值
				overflowContent: true,
				onActivate: function () { //回调事件: 方法

				}
			});
			$("#jryf").tabs({
				heightStyle: 'fill', //属性: 值
				onActivate: function () { //回调事件: 方法

				}
			});
			$("#dbsx").tabs({
				heightStyle: 'fill', //属性: 值
				onActivate: function () { //回调事件: 方法

				}
			});
			$(".dbsx-scroll1").mCustomScrollbar({
				axis: "y",
				theme: "minimal-dark",
				scrollbarPosition: "outside",
			});
			
			$(function () {
				jsConst.basePath = "${ctx}/";
				
				// 初始化登录用户信息
				initUserInfo();
			});
			
			function showDqyh() {
				$("#dqyh").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
			}
			
			function openRwgl() {
				
				var url = "${ctx}/portal/jbxt/rwgl";
				window.open(url, "_blank");
			}
			
			function openRczs() {
				
				var url = "${ctx}/portal/jbxt/rczs";
				window.open(url, "_blank");
			}
			/**
			 * 首页退出
			 */
			function syLogout() {
				$.confirm("确定要退出系统吗？", function(r) {
					if (r) {
						var ur = jsConst.basePath+'lg/loginCtrl/logout';
						$.ajax({
							type : 'post',
							url : ur,
							data : {'userId': jsConst.USER_ID  },
							dataType : 'json',
							success : function(data) {
								var user = null;
								if (data.result) {
									/* user = data.userBean; */
									window.location.href=ssoPage();
								}else{
									alert("退出失败："+data.respDesc);
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(textStatus);
							}
						});
					}
				});
			}

			/**
			 * 跳转至综合首页
			 */
			function toZhsy() {
				var url = "${ctx}/portal/zhshouye";
				window.open(url, "_self");
			}
			function openAqfxypSystem() {
				
				var url = "${ctx}/portal/aqfxyp/shouye";
				window.open(url, "_self");
			}
			function openDlyz() {
				var url = "http://192.168.9.33:11180/#/login?username=qd&password=qd";
				window.open(url, "_blank");
			}
			function openRygj() {
				var url = "http://192.168.9.33:11180/#/trace/index";
				window.open(url, "_blank");
			}
			function openRlsb() {
				var url = "http://192.168.9.33:11180/#/drawer?camera_id=6@DEFAULT";
				window.open(url, "_blank");
			}
			function openHkxt() {
				var url = "https://206.0.43.5/cas/login";
				window.open(url, "_blank");
			}
			function openOldZnafpt() {
				
				var url = "${ctx}/portal/shouye";
				window.open(url, "_self");
			}
			/**
			 * 语音识别系统
			 */
			function openYysbxt() {
				
				var url = "${ctx}/portal/yysb/shouye";
				window.open(url, "_self");
			}
			function openMenuDialog(obj, event, name) {

				var event = window.event || event;
			    //event.stopPropagation();
			    if (event && event.stopPropagation) {
			        event.stopPropagation();
			    } else {
			        window.event.cancelBubble = true;
			    }
			    //event.preventDefault();
			    var url = "";
			    var w = null;
			    var h = null;
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
			    } else if (name == 'ewdwwh') {////二维图层点位维护
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
				} else if (name == 'alarmRecord') {
					w = 1200;
					h = 800;
					url = jsConst.basePath + '/alarm/openDialog/record';
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
				} 
				else if (name == 'excel') {//excel
					url = jsConst.basePath + '/zbgl/kspb/toIndex';
				} 
				else if (name == 'pjcjl') {
					url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord';
				} else if (name == 'jcjl') {
					url = jsConst.basePath
							+ '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
							+ jsConst.CUS_NUMBER;
				} else if (name == 'jqjcjl') {
					url = jsConst.basePath
							+ '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
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
			        url = jsConst.basePath
			            + '/xxhj/zfsjsb/sblxsz/toIndex?cusNumber='
			            + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
			    } else if (name == 'sjsb') {
			        url = jsConst.basePath
			            + '/xxhj/zfsjsb/sjsb/toIndex?cusNumber='
			            + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
			    } else if (name == 'sjhz') {
			        url = jsConst.basePath
			            + '/xxhj/zfsjsb/sjsb/toSjhz?cusNumber='
			            + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
			    }
			    else if (name == 'jdjc') {
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
				}
				else if (name == 'alarmType') {
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
					url = jsConst.basePath + '/alarm/openDialog/record';
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
					url = jsConst.basePath + '/foreignPeopel/openDialog';
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
				}
				else if (name == "gzzgl") {
					url = jsConst.basePath+'/yjct/gzzgl/index';
				} else if (name == "wzgl") {
					url = jsConst.basePath+'/yjct/wzgl/index';
				} else if (name == "zjgl") {
					url = jsConst.basePath+'/yjct/zjgl/index';
				} else if (name == "fggl") {
					url = jsConst.basePath+'/yjct/fggl/index';
				} else if (name == "czffgl") {
					url = jsConst.basePath+'/yjct/czffgl/index';
				} else if (name == "yabz") {
					url = jsConst.basePath+'/yjct/yabz/index';
				} else if (name == "yasp") {
					url = jsConst.basePath+'/yjct/yasp/index';
				} else if (name == "yafb") {
					url = jsConst.basePath+'/yjct/yafb/index';
				} else if (name == "yljh") {
					url = jsConst.basePath+'/yjct/yljh/index';
				} else if (name == "ylsp") {
					url = jsConst.basePath+'/yjct/ylsp/index';
				} else if (name == "ylfb") {
					url = jsConst.basePath+'/yjct/ylfb/index';
				} else if (name == "zxyl") {
				
				} else if (name == "yljl") {
					url = jsConst.basePath+'/yjct/yjjl/toIndex?type=2';
				} else if (name == "yltj") {
					url = jsConst.basePath+'/yjct/yjjl/toTj?type=2';
				}else if (name == "czjl") {
					url = jsConst.basePath+'/yjct/yjjl/toIndex?type=1';
				} else if (name == "cztj") {
					url = jsConst.basePath+'/yjct/yjjl/toTj?type=1';
				} else if (name == "xxdy") {
					url = jsConst.basePath+'/yjct/msgsubscribe/index';
				} else if(name == "yjctSszk") {
					url = jsConst.basePath+'/yjct/sszk/toIndex';
				} else if (name == 'yrzq') {
					url = jsConst.basePath + '/wghgl/yrzq/toIndex';
				} else if (name == 'zqgl') {
					url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=zqgl';
			    }else if(name == 'swdbgd'){
			    	w = 1200;
					h = 680;
					url = jsConst.basePath + '/rwgl/rwjs/index?type=0';
			    }else if (name == 'dayly') {
					url = '${ctx}/xxyp/dayly/daylyLayout';
					w = 1000;
					h = 680;
			    }else if (name == 'xfrw') {
					w = 1200;
					h = 680;
					url = jsConst.basePath + '/rwgl/rwxf/index';
				} else if (name == 'jsrw') {
					w = 1200;
					h = 680;
					url = jsConst.basePath + '/rwgl/rwjs/index';
				} 
			    
			    if (w == null || h == null) {
			        w = 1000;
			        h = 600;
			    }
				
			    $('#dialog').html("");
				// $('#dialog').dialog("destroy");
			    $('#dialog').dialog({
			        width: w,
			        height: h,
			        title: $(obj).text(),
			        url: url
			    });
			    $("#dialog").dialog("open");
			    return;
			}
		</script>
</body>

</html>
