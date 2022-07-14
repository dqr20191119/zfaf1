<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
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
	<title>${map.orgName}安全风险研判</title>

	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-new.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.tooltip{
		width: 600px;
	}
	</style>
</head>

<body>
	<header class="perspective">
		<img src="${ctx}/static/bj-cui/img/command/logo_zhihui_${map.orgCode}.png" alt="指挥中心logo" class="logo">
		<div class="header-content">
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
			</div>
			 --%>
		</div>
		<ul class="tolist home">
			<li class="tolist-item status home-page" onclick="openZnafpt()">
				首页
			</li>
			
			<!-- <li class="tolist-item status">
				安全风险研判
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						一级菜单
						<ul>
							<li class="tolist-menuitem">
								二级菜单1
								<ul>
									<li class="tolist-menuitem">用户信息</li>
									<li class="tolist-menuitem">用户信息2</li>
								</ul>
							</li>
							<li class="tolist-menuitem">用户信息2</li>
						</ul>
					</li>
				</ul>
			</li> -->
			
			<li class="tolist-item status">
				评估分级指标管理
				<ul class="tolist-menu">
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'wwjgwh','五维架构维护')">
						五维架构维护
					</li>
					<li class="tolist-menuitem"  onclick="openFxpgDialog(event,'fxdjwh','风险等级维护')">
						风险等级维护
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'qzdjwh','权重等级维护')">
						权重等级维护
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'sjlywh','数据来源维护')">
						数据来源维护
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'sjfwgl','数据范围管理')">
						数据范围管理
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'fxdgl','风险点管理')">
						风险点管理
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'pgtjgl','评估条件管理')">
						评估条件管理
					</li>
					<li class="tolist-menuitem" onclick="openFxpgDialog(event,'fxgkgl','预案统一管理')">
						预案统一管理
					</li>
				</ul>
			</li>
			<li class="tolist-item status" onclick="openFxpgDialog(event, 'fxcj','风险采集管理')">
				风险采集管理
			</li>
			<!-- <li class="tolist-item status" onclick="openFxpgDialog(event,'fxgkgl','预案统一管理 ')">
					 预案统一管理
				</li> -->
			<!-- <li class="tolist-item status">
				风险分析
				<ul class="tolist-menu">
					<li class="tolist-menuitem" onclick="ryglfxfx()">
						人员管理风险分析
					</li>
					<li class="tolist-menuitem">
						地域管理风险分析
					</li>
					<li class="tolist-menuitem">
						事项管理风险分析
					</li>
					<li class="tolist-menuitem">
						物力管理风险分析
					</li>
					<li class="tolist-menuitem">
						情报管理风险分析
					</li>
				</ul>
			</li> -->
			<!-- <li class="tolist-item status">
				风险预警处置管理
			</li> -->
			<!-- <li class="tolist-item status">
				风险管控管理
			</li> -->
			 
			
<!-- 			<li class="tolist-item status" onclick="openAqfxypSystem()">
				安全风险研判
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						目标管理
					</li>
					<li class="tolist-menuitem">
						评估分级指标管理
					</li>
					<li class="tolist-menuitem">
						人员管理风险分析
					</li>
					<li class="tolist-menuitem">
						地域管理风险分析
					</li>
					<li class="tolist-menuitem">
						事项管理风险分析
					</li>
					<li class="tolist-menuitem">
						物力管理风险分析
					</li>
					<li class="tolist-menuitem">
						情报管理风险分析
					</li>
					<li class="tolist-menuitem">
						风险预警处置管理
					</li>
					<li class="tolist-menuitem">
						分析评估方法
					</li>
				</ul>
			</li> -->
			
			<!-- <li class="tolist-item status">
				网格化管理
				<ul class="tolist-menu">
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
						</ul>
					</li>
					<li class="tolist-menuitem">
						智能统计
					</li>
					<li class="tolist-menuitem">
						网格划分
					</li>
					<li class="tolist-menuitem">
						坐标定位
					</li>
					<li class="tolist-menuitem">
						物品管理
					</li>
					<li class="tolist-menuitem">
						事务督办
					</li>
					<li class="tolist-menuitem">
						综合评价
					</li>
					<li class="tolist-menuitem">
						总结报告
					</li>
					<li class="tolist-menuitem">
						问题统计汇总
					</li>
				</ul>
			</li>
				
			<li class="tolist-item status">
				生物识别
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						监室人脸点名
					</li>
					<li class="tolist-menuitem">
						人脸特征检索
					</li>
					<li class="tolist-menuitem">
						智能行为分析
					</li>
					<li class="tolist-menuitem">
						人力统计
					</li>
				</ul>
			</li>
			
			<li class="tolist-item status">
				语音识别
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						智能语音转写系统
					</li>
					<li class="tolist-menuitem">
						辅助改造应用
					</li>
					<li class="tolist-menuitem">
						通话监控管理
					</li>
					<li class="tolist-menuitem">
						通话信息侦查管理
					</li>
					<li class="tolist-menuitem">
						罪犯敏感词分析
					</li>
					<li class="tolist-menuitem">
						罪犯热词分析
					</li>
					<li class="tolist-menuitem">
						热度指数
					</li>
					<li class="tolist-menuitem">
						犯情关联分析
					</li>
					<li class="tolist-menuitem">
						重点人群画像
					</li>
					<li class="tolist-menuitem">
						关键词搜索
					</li>
					<li class="tolist-menuitem">
						多维统计
					</li>
				</ul>
			</li>
				
			<li class="tolist-item status">
				应急管理
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						应急预案
					</li>
					<li class="tolist-menuitem">
						应急调度
					</li>
					<li class="tolist-menuitem">
						应急统计
					</li>
				</ul>
			</li>
			
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						视频监控
						<ul>
							<li class="tolist-menuitem" onclick="toMenuDisplay('spya')">
								视频预案
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('splx')">
								视频轮巡
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('sphf')">
								视频回放
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'group')">
								群组管理
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('offLineCamera')">
								离线摄像头
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'jdjc')">
								监督检查
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'jddlb')">
								监督单列表
							</li>
							<li class="tolist-menuitem">
								视频巡查通报
								<ul>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'wldctb-lb')">
										巡查通报列表
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'wldctb-sp')">
										巡查通报审批
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'wldctb-hz')">
										巡查通报汇总
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem" onclick="toMenuDisplay('swxs')">
						三维巡视
					</li>
					<li class="tolist-menuitem" onclick="toMenuDisplay('powerNetwork')">
						数字电网
					</li>
					<li class="tolist-menuitem" onclick="toMenuDisplay('doorInfo')">
						门禁控制
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'realTimeTalk')">
						实时对讲
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'screenSwitch')">
						大屏预案
					</li>
				</ul>
			</li>
						
			<li class="tolist-item status">
				系统管理
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						技防设备管理
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'camera')">
								摄像机管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'videoDevice')">
								视频设备管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'streamServer')">
								流媒体服务
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'dvcRole')">
								设置摄像机权限
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'videoClient')">
								视频客户端列表
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'powerNetwork')">
								高压电网管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'alertor')">
								报警器管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'doorCtrl')">
								门禁控制器管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'door')">
								门禁管理
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						物防设备管理
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'physicalDeviceName')">
								物防设备定义
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'physicalDevice')">
								物防设施管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'policeDevice')">
								警用器材维护
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						区域维护
						<ul>
							<li class="tolist-menuitem" onclick="toMenuDisplay('qywh')">
								区域维护
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jswh')">
								监舍维护
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'regionDepart')">
								区域部门维护
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'viewPeople')">
								视角人员维护
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'prisonPath')">
								路线配置
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						二维地图维护
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'ewtcwh')">
								二维图层维护
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'ewdwwh')">
								二维点位维护
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'screenPlan')">
						大屏预案管理
					</li>
					<li class="tolist-menuitem">
						对讲管理
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'talkBackServer')">
								主机管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'talkBackBase')">
								分机管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'broadcast')">
								广播管理
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'xxdy')">
						消息订阅
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'doorPlan')">
						门禁预案
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'crontab')">
						定时任务
					</li>
				</ul>
			</li> -->
		</ul>
	</header>
	<div class="container-box">
		<div id="layout1">

			<div data-options="region:'west'" style="width:357px;">
				<div class="left">
					<div class="left-top">
						<h3>
							风险概况
						</h3>
						<div id="ltChart" class="ltChart"></div>
					</div>
					<div class="left-bottom clearfix">
						<h3>
							风险评估
						</h3>
						<div class="clearfix">
							<ul class="left-chart-box">
								<li>
									<div class="left-pie" id="left-pie1"></div>
								</li>
								<li>
									<div class="left-pie" id="left-pie2"></div>
								</li>
								<li>
									<div class="left-pie" id="left-pie3"></div>
								</li>
								<li>
									<div class="left-pie" id="left-pie4"></div>
								</li>
								<li>
									<div class="left-pie" id="left-pie5"></div>
								</li>
							</ul>
							<ul class="right-chart-box">
								<li>
									<h4>风险指向</h4>
									<div class="right-pie" id="right-pie1"></div>
								</li>
								<li>
									<h4>风险指向</h4>
									<div class="right-pie" id="right-pie2"></div>
								</li>
								<li>
									<h4>风险指向</h4>
									<div class="right-pie" id="right-pie3"></div>
								</li>
								<li>
									<h4>风险指向</h4>
									<div class="right-pie" id="right-pie4"></div>
								</li>
								<li>
									<h4>风险指向</h4>
									<div class="right-pie" id="right-pie5"></div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div data-options="region:'center'" class="main">
				<div id="layout-child">
					<div data-options="region:'center'" style="overflow:hidden">

						<a class="back hide">
							返回</a> <div class="safeTooltip">
								<!-- <ul>
									<li>
										<span class="fk safe"></span>
										<span class="title">安全</span>
									</li>
									<li>
										<span class="fk qd"></span>
										<span class="title">轻度危险</span>
									</li>
									<li>
										<span class="fk zd"></span>
										<span class="title">中度危险</span>
									</li>
									<li>
										<span class="fk zzdu"></span>
										<span class="title">重度危险</span>
									</li>
									<li>
										<span class="fk tb"></span>
										<span class="title">特别危险</span>
									</li>
								</ul> -->
					</div>
					<div class="center cls-first">
						<?xml version="1.0" encoding="iso-8859-1"?>
						<!-- Generator: Adobe Illustrator 17.0.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
						<svg version="1.1" id="svg" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
						 x="0px" y="0px" width="1920px" height="1080px" viewBox="0 0 1920 1080" style="enable-background:new 0 0 1920 1080;"
						 xml:space="preserve">
							<path style="opacity:0.9;fill:#040000;enable-background:new    ;" d="M1465.302,447.741c0,0,28.999-6.148-2.837-10.834
	L1465.302,447.741z" />
							<g style="opacity:0.3;" >
								<polygon style="fill:#00000C;" points="1244.093,266.779 1323.5,330.692 1244.093,352.931 1236.041,344.146 1301.875,328.258 
		1250.938,282.637 1171.647,306.664 1214.896,349.077 1229.313,345.588 1235.56,352.931 1211.532,360.684 1144.793,296.092 	" />
								<path style="fill:#00000C;" d="M1276.647,405.154l-43.97-37.965l101.876-29.11l100.674,81.547l-108.36,38.894l-39.408-28.432
		l-27.148,7.896c0,0,4.929-10.167,16.576-12.144c3.183-0.54,10.572-1.812,23.547,1.437c12.975,3.249,29.313,8.795,34.119,10.707
		c4.806,1.913,28.592,4.16,33.638,1.955c5.046-2.205,12.614-2.576,17.3-12.663l7.208-1.471c0,0-9.01-32.711-51.178-45.712
		l11.353-3.089l-13.696-12.574l-31.716,8.249l8.65,10.452c0,0,31.716-5.226,56.945,29.553c0,0-63.071-10.475-42.167,18.46
		c0,0,2.824,2.744,3.064,2.985c0.24,0.24-11.172-3.724-20.664-6.367c-9.49-2.643-48.656-16.699-57.305,12.014l-14.417-17.726
		l39.694-12.956L1276.647,405.154z" />
								<g>
									<g>
										<g>
											<polygon style="fill:#00000C;" points="1240.128,259.694 309.731,557.754 304.122,550.905 1231.479,251.045 				" />
											<polygon style="fill:#00000C;" points="1409.64,206.234 1250.76,255.46 1244.093,248.611 1400.992,197.584 				" />
											<polygon style="fill:#00000C;" points="331.896,573.972 397.708,670.922 414.79,662.633 405.06,649.658 397.708,652.902 
					360.73,598.119 389.201,586.946 425.603,638.124 451.913,624.428 399.293,550.1 				" />
											<polygon style="fill:#00000C;" points="408.664,684.618 504.172,824.456 579.858,796.344 494.261,681.373 464.888,691.105 
					504.893,745.167 473.538,758.141 430.649,699.033 438.938,695.309 426.324,677.409 				" />
											<polygon style="fill:#00000C;" points="1053.677,637.403 1093.082,681.854 528.68,901.224 494.261,845 				" />
											<polygon style="fill:#00000C;" points="1073.379,632.598 1112.783,674.887 1540.469,504.773 1518.365,487.474 				" />
											<polygon style="fill:#00000C;" points="1260.311,262.579 1453.491,415.391 1495.779,404.398 1440.996,361.57 1416.007,371.181 
					1285.299,269.786 1397.267,235.187 1597.655,378.87 1524.13,412.508 1581.796,457.679 1543.353,472.577 1497.821,434.133 
					1502.146,431.25 1497.461,425.483 1508.994,421.158 1537.465,443.504 1543.232,439.9 1510.436,413.229 1486.648,424.041 
					1492.415,431.25 1480.161,437.016 1540.469,484.591 1710.102,430.529 1405.917,213.563 				" />
											<path style="fill:#00000C;" d="M417.433,544.179l65.835,93.226l40.366-20.182c0,0,10.092-5.286,2.883-14.417
					c-7.209-9.131-47.094-58.627-47.094-58.627s-7.809-11.533,14.836-19.221l-4.265-6.247L417.433,544.179z" />
											<path style="fill:#00000C;" d="M610.132,557.754l-54.137-30.868l23.862,36.994l-36.762,14.176l-28.113-36.521l41.011-14.649
					l-14.341-8.177l-47.394,14.897c0,0-8.11,4.325-3.064,10.812c5.046,6.488,37.482,42.528,37.482,42.528s14.417,4.325,28.113,0
					c13.696-4.325,45.412-14.417,45.412-14.417S615.899,566.764,610.132,557.754z" />
											<path style="fill:#00000C;" d="M708.164,723.903l-30.274-86.498c0,0-0.721-9.371-22.346-3.604
					c-21.625,5.767-56.224,18.021-56.224,18.021s-12.975,10.092-3.604,23.067c9.371,12.975,54.525,69.919,54.525,69.919
					s10.35,5.046,24.045,0c13.696-5.046,16.579-6.487,16.579-6.487l-32.797-38.203l-16.579,6.128l-15.858-19.463l-14.08,5.406
					l-2.138-2.699l28.832-14.24l-6.847-9.731l27.392-9.731l22.346,26.31l-19.463,9.01l1.082,5.767l21.625,1.442
					c0,0,12.254,9.371-1.442,18.742l15.138,19.102C698.072,736.156,711.407,733.17,708.164,723.903z" />
											<path style="fill:#00000C;" d="M547.422,648.216L506.603,663.1l90.826,122.343l53.067-22.616l-85.773-110.286
					C564.721,652.541,559.557,643.767,547.422,648.216z" />
											<path style="fill:#00000C;" d="M555.35,514.865l95.144,59.107l22.35,7.208l44.691,143.443c0,0,18.381,5.228,19.822-8.198
					l-34.239-113.98l16.939-2.522L748.17,712.37c0,0,50.817,7.689,93.587-27.392l-67.277-79.77l13.936-6.247l71.12,78.449
					c0,0,30.274,1.321,41.807-22.225l-61.029-66.317l10.092-7.689l66.795,72.562c0,0,69.198-13.654,86.018-33.498L835.028,430.289
					c0,0-2.403-8.65-22.106-1.922c-17.17,5.863-69.74,23.405-82.97,27.816c-2.379,0.793-3.783,3.228-3.277,5.684
					c0.422,2.045,1.531,4.696,4.074,7.827c6.247,7.689,24.507,32.196,24.507,32.196l-23.546,11.533l-39.405-42.769
					c0,0-14.417-3.364-19.703,9.131l40.366,45.171l-9.611,3.844l-38.924-44.21c0,0-25.468-8.65-36.521,12.494l47.094,53.016
					l-10.572,7.654L577.334,511.5C577.334,511.501,564.961,503.812,555.35,514.865z" />
											<path style="fill:#00000C;" d="M828.421,406.689l34.72,41.861c0,0,18.381,2.163,18.021-9.01l-35.08-37.536L828.421,406.689z" />
											<path style="fill:#00000C;" d="M878.277,462.965l146.567,162.906l18.261-9.131l-148.009-159.54
					C895.096,457.199,883.671,452.889,878.277,462.965z" />
										</g>
									</g>
								</g>
								<path style="fill:#00000C;" d="M976.132,399.444l134.005-35.015c0,0,11.293-4.234,18.742,2.758s17.06,15.978,17.06,15.978
		s8.65,6.173-3.604,11.088c-12.254,4.915-139.401,42.856-139.401,42.856s-7.837,0.986-11.586-5.383
		C987.598,425.358,976.132,399.444,976.132,399.444z" />
								<polygon style="fill:#00000C;" points="1061.121,474.81 1036.133,481.518 1139.931,588.066 1184.142,575.858 1154.829,547.942 
		1139.451,555.845 	" />
								<path style="fill:#00000C;" d="M1178.615,448.717l-88.092,27.205c0,0-14.211,2.905-5.948,11.551s57.999,56.826,57.999,56.826
		l17.41,1.556l26.56-6.919l11.172,8.331c0,0,6.487,8.318,25.949,3.273c19.463-5.046,110.886-33.966,110.886-33.966l-37.963-37.687
		l-94.186,29.355c0,0-14.417-7.92-9.131-17.53l82.173-25.94l-35.318-33.629l-48.779,13.595l2.883,5.494l9.611-2.328l17.781,14.463
		l-33.157,6.849l-14.294-12.934l8.046-3.572L1178.615,448.717z" />
								<path style="fill:#00000C;" d="M1334.553,469.449l120.497-37.721l50.457,42.032l-150.29,48.308l-7.929-8.979
		c0,0,15.138,3.14,19.102-7.816c3.964-10.957,2.522-14.856,10.452-17.231c0,0,26.55,9.887,43.189-3.452
		s-14.717-29.678-34.178-25.113c-19.463,4.565-18.088,16.276-15.497,23.306c0,0-6.849,11.19-6.488,16.408
		s-12.254,10.623-22.346,8.822l-5.046-8.822l23.427-7.537L1334.553,469.449z" />
								<path style="fill:#00000C;" d="M1019.093,438.029l7.779,32.839l213.256-69.158l-31.564-30.273l-40.526,13.772
		c0,0,9.734,3.753,0,7.421C1158.303,396.299,1019.093,438.029,1019.093,438.029z" />
							</g>
							<polygon style="fill:#005178;" points="744.461,462.851 763.682,493.125 848.871,464.773 825.673,436.42 " />
							<polygon style="fill:#002E4B;" points="420.801,543.113 431.34,543.113 604.813,776.657 602.134,786.268 " />
							<g class="content show" id="1">
								<polygon style="fill:#B2B2B2;" points="1253.58,406.955 1252.859,407.217 1252.859,399.7 1253.58,399.444 	" />
							</g>
							<g class="content show" id="2">
								<g>
									<polygon style="fill:#00486C;" points="976.576,369.153 989.899,382.168 1110.427,349.693 1095.379,336.958 		" />
									<polygon style="fill:#09384E;" points="989.917,388.791 1110.134,356.489 1110.427,349.693 989.899,382.168 		" />
									<polygon style="fill:none;stroke:#00A0E9;stroke-width:0.2611;stroke-miterlimit:10;" points="976.576,369.153 989.899,382.168 
			1110.427,349.693 1095.379,336.958 		" />
								</g>
								<polygon style="fill:#09384E;" points="976.105,369.468 976.991,376.16 989.899,388.791 989.726,382.171 	" />
							</g>
							<line style="fill:none;stroke:#22222A;stroke-width:1.0445;stroke-miterlimit:10;" x1="1253.22" y1="412.376" x2="1266.138"
							 y2="414.199" />
							<line style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 x1="1416.719" y1="371.751" x2="1456.587" y2="403.718" />
							<polygon style="fill:#002E4B;" points="939.388,514.152 949.252,510.33 1052.636,619.107 1042.561,623.85 " />
							<g class="content show" id="3">
								<polygon style="fill:#004669;" points="778.355,584.709 786.914,563.041 786.707,562.958 778.187,584.525 	" />
							</g>
							<g class="content show" id="4">
								<path style="fill:#005178;" d="M970.557,617.18c2.188-7.66-0.375-16.387-7.214-24.57l-24.25-29.02
		c-6.344-7.592-43.669-45.564-53.931-48.869c-0.552-0.177-1.11-0.349-1.66-0.51c-10.619-3.089-21.552-3.15-30.787-0.172
		c-9.46,3.049-15.83,8.795-17.937,16.179c-2.188,7.663,0.375,16.388,7.21,24.569l52.427,60.992
		c6.349,7.593,15.496,13.595,25.759,16.9c0.547,0.176,1.105,0.347,1.656,0.507c10.623,3.09,21.558,3.15,30.792,0.173
		C962.081,630.308,968.451,624.562,970.557,617.18z M961.592,613.01c-1.261,5.88-6.19,10.481-13.881,12.963
		c-7.903,2.546-17.111,2.383-25.936-0.458c-7.994-2.573-15.084-7.21-19.975-13.063l-52.427-60.992
		c-4.772-5.711-6.773-11.775-5.636-17.073c1.266-5.877,6.197-10.481,13.887-12.963c7.897-2.547,17.112-2.383,25.936,0.458
		c7.989,2.572,43.262,39.184,48.147,45.032l24.25,29.02C960.728,601.646,962.734,607.712,961.592,613.01z" />
								<g>
									<g>
										<path style="opacity:0.3;fill:none;stroke:#FFFFFF;stroke-width:0.1945;stroke-miterlimit:10;enable-background:new    ;"
										 d="
				M953.207,632.909c-9.194,3.091-20.129,3.167-30.792,0.21c-0.553-0.152-1.113-0.317-1.662-0.486
				c-10.307-3.177-19.535-9.064-25.985-16.577l-52.817-60.688c-6.945-8.095-9.625-16.788-7.541-24.477
				c2.006-7.408,8.298-13.233,17.716-16.4c9.194-3.093,20.127-3.167,30.787-0.211c0.552,0.153,1.113,0.319,1.667,0.489
				c10.307,3.177,47.712,41.035,54.158,48.547l24.64,28.716c6.95,8.098,9.629,16.791,7.546,24.479
				C968.917,623.916,962.625,629.741,953.207,632.909z" />
										<path style="opacity:0.3;fill:none;stroke:#FFFFFF;stroke-width:0.1945;stroke-miterlimit:10;enable-background:new    ;"
										 d="
				M951.533,630.467c-8.747,2.943-19.106,2.977-29.169,0.096c-0.536-0.153-1.07-0.315-1.597-0.484
				c-8.534-2.719-16.23-7.438-21.946-13.431c-0.565-0.561-1.793-1.952-2.302-2.545l-51.47-59.117
				c-0.593-0.692-1.797-2.153-2.294-2.855c-5.009-6.662-6.907-13.651-5.345-19.889c1.736-6.904,7.554-12.34,16.383-15.311
				c8.616-2.897,18.793-2.977,28.708-0.232c0.526,0.146,1.057,0.3,1.574,0.46c9.546,2.942,46.264,40.357,52.213,47.293
				l23.663,27.575c0.798,0.928,2.49,3.011,3.119,3.959c4.63,6.439,6.348,13.153,4.845,19.168
				C966.187,622.06,960.368,627.496,951.533,630.467z" />
										<path style="opacity:0.3;fill:none;stroke:#FFFFFF;stroke-width:0.1945;stroke-miterlimit:10;enable-background:new    ;"
										 d="
				M949.866,628.024c-8.31,2.792-18.095,2.787-27.558-0.018c-0.509-0.152-1.021-0.313-1.526-0.48
				c-7.984-2.638-15.129-7.17-20.318-12.882c-0.279-0.282-1.943-2.195-2.197-2.491l-50.124-57.548
				c-0.294-0.343-1.873-2.213-2.121-2.566c-4.929-6.156-6.917-12.668-5.606-18.444c1.459-6.4,6.804-11.45,15.043-14.224
				c8.039-2.702,17.462-2.784,26.632-0.25c0.496,0.136,0.989,0.281,1.48,0.431c8.784,2.707,44.81,39.677,50.271,46.039
				l22.681,26.435c0.396,0.464,2.712,3.216,3.024,3.689c4.742,6.047,6.641,12.423,5.358,18.086
				C963.451,620.201,958.11,625.252,949.866,628.024z" />
										<path style="opacity:0.3;fill:none;stroke:#FFFFFF;stroke-width:0.1945;stroke-miterlimit:10;enable-background:new    ;"
										 d="
				M922.258,625.45c-8.028-2.474-15.181-7.023-20.15-12.812l-52.818-60.686c-4.849-5.652-6.931-11.69-5.867-17.002
				c1.188-5.893,6.054-10.557,13.71-13.135c7.862-2.644,17.078-2.596,25.94,0.135c8.023,2.472,43.358,38.996,48.323,44.783
				l24.64,28.716c4.848,5.65,6.936,11.691,5.865,17.003c-1.181,5.894-6.049,10.557-13.705,13.135
				C940.331,628.228,931.12,628.18,922.258,625.45z" />
									</g>
								</g>
							</g>
							<polygon style="fill:#09384E;" points="1026.133,431.701 1026.863,438.412 1039.475,451.339 1039.456,444.716 " />
							<path style="fill:#1A3B66;" d="M812.3,711.058" />
							<path style="fill:none;stroke:#5C6168;stroke-width:0.2611;stroke-miterlimit:10;" d="M1084.849,434.952" />
							<path style="fill:none;stroke:#5C6168;stroke-width:0.2611;stroke-miterlimit:10;" d="M939.19,481.518" />
							<polygon style="fill:#002E4B;" points="1043.212,629.477 1051.862,629.477 1051.862,626.594 1064.476,626.594 1064.476,633.441 
	1051.862,633.441 1051.502,631.64 1043.212,631.64 " />
							<polygon style="fill:#002E4B;" points="602.465,789.577 610.989,791.052 611.481,788.211 623.91,790.362 622.742,797.11 
	610.313,794.959 610.264,793.121 602.097,791.708 " />
							<g class="content show" id="5">
								<polygon style="fill:#005A84;" points="1663.332,422.057 1536.388,463.656 1544.305,468.041 1671.64,426.46 	" />
								<polygon style="fill:#32A2D5;" points="1536.388,463.656 1536.213,468.041 1543.353,472.577 1671.64,431.727 1671.64,426.46 
		1544.305,468.041 	" />
							</g>
							<path style="fill:#005A84;" d="M1115.811,486.442c0.3,0,25.165-5.509,25.165-5.509v-4.72l-28.97,6.548L1115.811,486.442z" />
							<g class="content show" id="6">
								<polygon style="fill:#002E4B;" points="1013.09,577.775 1091.224,552.654 1091.224,559.856 1017.895,582.816 	" />
								<polygon style="fill:#005A84;" points="1091.224,552.654 1085.955,548.431 1009.478,573.698 1012.918,577.775 	" />
							</g>
							<polygon style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 points="
	751.501,465.144 822.299,442.088 838.48,462.414 766.626,486.024 " />
							<path style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 d="
	M755.291,470.376c0,0,10.847-6.904,16.523,0.891c5.677,7.794-8.027,10.838-8.027,10.838" />
							<path style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 d="
	M834.002,457.37c0,0-10.46,7.477-16.547-0.001s7.433-11.255,7.433-11.255" />
							<line style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 x1="789.307" y1="452.831" x2="803.236" y2="474.328" />
							<circle style="opacity:0.39;fill:none;stroke:#FFFFFF;stroke-width:0.2611;stroke-miterlimit:10;enable-background:new    ;"
							 cx="795.835" cy="462.907" r="6.568" />
							<g class="content show" id="7">
								<polygon style="fill:#9DC8E7;" points="1195.883,333.834 1207.005,330.778 1206.743,325.281 1196.368,327.679 	" />
								<polygon style="fill:#32A2D5;" points="1172.269,308.228 1172.269,314.496 1195.883,333.834 1196.368,327.679 	" />
								<polygon style="fill:#005A84;" points="1172.269,308.228 1183.082,306.79 1197.974,319.987 1196.368,327.679 	" />
								<polygon style="fill:#32A2D5;" points="1183.082,306.79 1184.319,304.577 1172.269,308.228 	" />
								<polygon style="fill:#004669;" points="1196.368,327.679 1197.974,319.987 1206.743,325.281 	" />
								<polygon style="fill:#00486C;" points="1184.319,304.577 1183.082,306.79 1197.974,319.987 1206.743,325.281 	" />
							</g>
							<g class="content show" id="8">
								<g>
									<polygon style="fill:#32A2D5;" points="1021.467,588.977 1021.467,600.956 1036.724,615.524 1036.121,603.747 		" />
									<g>
										<polygon style="fill:#005A84;" points="1021.467,588.977 1036.121,603.747 1076.274,591.631 1063.304,576.244 			" />
										<polygon style="fill:#9DC8E7;" points="1036.133,610.839 1076.342,598.124 1076.274,591.631 1036.121,603.747 			" />
									</g>
									<polygon style="fill:#9DC8E7;" points="1076.342,598.124 1075.93,585.999 1088.796,583.544 1088.958,594.779 		" />
									<polygon style="fill:#004669;" points="1075.93,585.999 1066.247,573.712 1077.06,570.829 1088.796,583.544 		" />
									<polygon style="fill:#9DC8E7;" points="1076.274,591.631 1075.93,585.999 1066.247,573.712 1066.247,579.34 		" />
								</g>
								<polygon style="fill:#005A84;" points="1081.464,594.728 1081.464,588.501 1084.968,587.612 1084.968,593.799 	" />
							</g>
							<g class="content show" id="9">
								<polygon style="fill:#002E4B;" points="417.528,537.466 417.354,544.299 1154.814,308.551 1154.814,302.544 	" />
								<g>
									<g>
										<polygon style="fill:#B2B2B2;" points="418.254,537.229 417.526,537.473 417.68,531.41 418.407,531.172 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="509.112,508.723 508.385,508.967 508.543,502.66 509.271,502.421 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="587.529,484.097 586.803,484.342 586.968,477.795 587.695,477.556 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="662.977,460.495 662.25,460.74 662.499,453.339 663.225,453.101 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="744.529,434.808 743.801,435.052 743.967,427.52 744.694,427.282 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="863.989,397.293 863.261,397.537 863.539,388.928 864.267,388.689 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="944.745,372.34 944.017,372.585 944.113,363.112 944.839,362.875 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1030.696,344.91 1029.97,345.154 1030.34,335.486 1031.067,335.249 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1074.007,331.275 1073.279,331.519 1073.567,321.637 1074.294,321.398 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="804.418,415.952 803.692,416.196 803.908,408.033 804.636,407.794 			" />
									</g>

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="417.733" y1="536.082" x2="1155.965"
									 y2="299.528" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="417.787" y1="533.937" x2="1156.858"
									 y2="297.116" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="417.846" y1="531.72" x2="1155.541"
									 y2="295.405" />
								</g>
							</g>
							<g class="content show" id="10">
								<g>
									<polygon style="fill:#B2B2B2;" points="590.393,769.147 591.08,770.071 591.245,764.228 590.534,763.945 		" />
								</g>
								<g>
									<polygon style="fill:#B2B2B2;" points="575.391,749.187 576.077,750.112 576.243,744.269 575.531,743.985 		" />
								</g>
								<g>
									<polygon style="fill:#B2B2B2;" points="536.063,696.515 536.749,697.439 536.845,691.852 536.133,691.569 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="506.603,657.149 506.603,663.132 602.097,791.637 602.097,784.801 		" />
									<g>
										<polygon style="fill:#B2B2B2;" points="558.25,726.192 558.936,727.116 559.031,721.527 558.32,721.244 			" />
									</g>

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="602.457" y1="782.067" x2="510.209"
									 y2="659.214" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="602.457" y1="779.767" x2="511.235"
									 y2="658.259" />
								</g>
							</g>
							<g class="content show" id="11">
								<polygon style="fill:#00A0E9;" points="417.524,538.269 417.524,544.253 483.479,637.381 485.281,632.916 	" />
								<g>
									<polygon style="fill:#B2B2B2;" points="427.777,552.598 428.464,553.522 428.506,545.998 427.794,545.715 		" />
								</g>
								<g>
									<polygon style="fill:#B2B2B2;" points="443.282,574.258 443.969,575.182 444.071,567.402 443.36,567.119 		" />
								</g>

								<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="507.335" y1="659.35" x2="417.967"
								 y2="535.197" />

								<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="507.353" y1="655.707" x2="418.083"
								 y2="531.636" />
							</g>
							<g class="content show" id="12">
								<polygon style="fill:#00A0E9;" points="1208.825,364.968 1208.825,370.953 1253.308,413.945 1253.58,407.397 	" />
								<g>
									<g>
										<g>
											<polygon style="fill:#B2B2B2;" points="1243.708,397.968 1244.394,398.892 1244.683,391.787 1243.972,391.504 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#B2B2B2;" points="1227.432,382.415 1228.117,383.339 1228.394,375.869 1227.683,375.586 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1252.958,403.962 1209.391,362.213 1209.917,361.717 1253.483,403.466 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1252.958,400.209 1209.013,358.104 1209.54,357.608 1253.483,399.713 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="13">
								<g>
									<g style="enable-background:new    ;">
										<g>
											<polygon style="fill:#32A2D5;" points="1234.175,454.458 1228.922,449.597 1228.943,437.872 1234.196,442.733 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1234.196,442.733 1228.943,437.872 1238.069,436.469 1243.321,441.329 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1243.321,441.329 1243.3,453.055 1234.175,454.458 1234.196,442.733 				" />
										</g>
									</g>
								</g>
								<g>
									<g style="enable-background:new    ;">
										<g>
											<polygon style="fill:#32A2D5;" points="1247.107,472.986 1234.182,461.027 1234.236,432.174 1247.161,444.134 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1247.161,444.134 1234.236,432.174 1248.469,430.174 1261.394,442.134 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1261.394,442.134 1261.34,470.987 1247.107,472.986 1247.161,444.134 				" />
										</g>
									</g>
								</g>
								<g>
									<g style="enable-background:new    ;">
										<g>
											<polygon style="fill:#32A2D5;" points="1260.019,484.941 1247.094,472.981 1247.147,448.265 1260.072,460.225 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1260.072,460.225 1247.147,448.265 1261.381,446.264 1274.306,458.224 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1274.306,458.224 1274.252,482.94 1260.019,484.941 1260.072,460.225 				" />
										</g>
									</g>
								</g>
								<g>
									<g style="enable-background:new    ;">
										<g>
											<polygon style="fill:#32A2D5;" points="1272.934,496.894 1260.009,484.934 1260.063,456.081 1272.988,468.041 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1272.988,468.041 1260.063,456.081 1274.297,454.08 1287.221,466.041 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1287.221,466.041 1287.168,494.894 1272.934,496.894 1272.988,468.041 				" />
										</g>
									</g>
								</g>
								<g>
									<g style="enable-background:new    ;">
										<g>
											<polygon style="fill:#32A2D5;" points="1281.714,501.144 1276.461,496.285 1276.483,484.558 1281.735,489.419 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1281.735,489.419 1276.483,484.558 1285.608,483.155 1290.861,488.016 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1290.861,488.016 1290.838,499.742 1281.714,501.144 1281.735,489.419 				" />
										</g>
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#004669;" points="1236.5,438.969 1236.618,441.323 1237.821,442.545 1237.697,440.088 			" />
										<polygon style="fill:#004669;" points="1238.634,440.9 1238.752,443.253 1239.955,444.476 1239.832,442.02 			" />
										<polygon style="fill:#004669;" points="1240.956,442.604 1241.075,444.959 1242.278,446.181 1242.154,443.724 			" />
										<polygon style="fill:#004669;" points="1243.38,444.555 1243.498,446.909 1244.701,448.131 1244.578,445.675 			" />
										<polygon style="fill:#004669;" points="1236.441,442.831 1236.559,445.186 1237.762,446.407 1237.639,443.951 			" />
										<polygon style="fill:#004669;" points="1238.575,444.762 1238.694,447.115 1239.898,448.338 1239.773,445.882 			" />
										<polygon style="fill:#004669;" points="1240.898,446.466 1241.016,448.82 1242.219,450.043 1242.096,447.586 			" />
										<polygon style="fill:#004669;" points="1243.321,448.417 1243.44,450.77 1244.644,451.993 1244.519,449.537 			" />
										<polygon style="fill:#004669;" points="1236.323,446.696 1236.442,449.05 1237.644,450.272 1237.521,447.817 			" />
										<polygon style="fill:#004669;" points="1238.458,448.627 1238.576,450.98 1239.78,452.203 1239.656,449.746 			" />
										<polygon style="fill:#004669;" points="1240.78,450.331 1240.898,452.686 1242.101,453.907 1241.978,451.451 			" />
										<polygon style="fill:#004669;" points="1243.204,452.282 1243.322,454.636 1244.526,455.858 1244.402,453.401 			" />
										<polygon style="fill:#004669;" points="1236.265,450.558 1236.383,452.912 1237.587,454.134 1237.463,451.677 			" />
										<polygon style="fill:#004669;" points="1238.399,452.489 1238.517,454.842 1239.721,456.065 1239.598,453.609 			" />
										<polygon style="fill:#004669;" points="1240.721,454.193 1240.84,456.548 1242.044,457.77 1241.919,455.313 			" />
										<polygon style="fill:#004669;" points="1243.145,456.144 1243.263,458.498 1244.467,459.72 1244.343,457.264 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1236.18,454.495 1236.299,456.848 1237.501,458.071 1237.378,455.614 			" />
										<polygon style="fill:#004669;" points="1238.315,456.424 1238.433,458.779 1239.637,460.001 1239.514,457.544 			" />
										<polygon style="fill:#004669;" points="1240.637,458.13 1240.755,460.483 1241.959,461.705 1241.835,459.25 			" />
										<polygon style="fill:#004669;" points="1243.061,460.081 1243.179,462.434 1244.383,463.656 1244.259,461.2 			" />
										<polygon style="fill:#004669;" points="1236.122,458.357 1236.24,460.71 1237.444,461.933 1237.32,459.476 			" />
										<polygon style="fill:#004669;" points="1238.256,460.287 1238.375,462.641 1239.578,463.863 1239.455,461.406 			" />
										<polygon style="fill:#004669;" points="1240.578,461.992 1240.698,464.345 1241.901,465.567 1241.776,463.112 			" />
										<polygon style="fill:#004669;" points="1243.002,463.943 1243.12,466.296 1244.324,467.518 1244.2,465.063 			" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#004669;" points="1261.771,461.214 1261.889,463.568 1263.093,464.79 1262.97,462.333 			" />
										<polygon style="fill:#004669;" points="1263.906,463.145 1264.024,465.498 1265.227,466.721 1265.104,464.264 			" />
										<polygon style="fill:#004669;" points="1266.227,464.849 1266.347,467.202 1267.55,468.425 1267.426,465.968 			" />
										<polygon style="fill:#004669;" points="1268.651,466.8 1268.769,469.153 1269.973,470.376 1269.85,467.919 			" />
										<polygon style="fill:#004669;" points="1261.712,465.076 1261.83,467.43 1263.034,468.652 1262.911,466.195 			" />
										<polygon style="fill:#004669;" points="1263.848,467.007 1263.966,469.36 1265.169,470.582 1265.045,468.126 			" />
										<polygon style="fill:#004669;" points="1266.17,468.711 1266.288,471.064 1267.491,472.287 1267.368,469.831 			" />
										<polygon style="fill:#004669;" points="1268.592,470.662 1268.712,473.015 1269.915,474.238 1269.791,471.781 			" />
										<polygon style="fill:#004669;" points="1261.594,468.94 1261.714,471.295 1262.917,472.517 1262.793,470.06 			" />
										<polygon style="fill:#004669;" points="1263.73,470.871 1263.848,473.225 1265.052,474.448 1264.928,471.991 			" />
										<polygon style="fill:#004669;" points="1266.052,472.576 1266.171,474.929 1267.373,476.152 1267.25,473.695 			" />
										<polygon style="fill:#004669;" points="1268.476,474.526 1268.594,476.88 1269.797,478.103 1269.674,475.646 			" />
										<polygon style="fill:#004669;" points="1261.537,472.803 1261.655,475.157 1262.858,476.379 1262.735,473.922 			" />
										<polygon style="fill:#004669;" points="1263.671,474.734 1263.789,477.087 1264.992,478.31 1264.869,475.853 			" />
										<polygon style="fill:#004669;" points="1265.994,476.438 1266.112,478.791 1267.316,480.014 1267.192,477.557 			" />
										<polygon style="fill:#004669;" points="1268.417,478.389 1268.536,480.742 1269.738,481.965 1269.615,479.508 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1261.452,476.739 1261.571,479.093 1262.774,480.316 1262.65,477.859 			" />
										<polygon style="fill:#004669;" points="1263.587,478.669 1263.705,481.024 1264.909,482.245 1264.785,479.789 			" />
										<polygon style="fill:#004669;" points="1265.909,480.373 1266.028,482.728 1267.23,483.95 1267.107,481.493 			" />
										<polygon style="fill:#004669;" points="1268.333,482.324 1268.451,484.679 1269.654,485.9 1269.531,483.444 			" />
										<polygon style="fill:#004669;" points="1261.394,480.602 1261.512,482.955 1262.715,484.178 1262.592,481.721 			" />
										<polygon style="fill:#004669;" points="1263.528,482.531 1263.647,484.886 1264.85,486.108 1264.726,483.651 			" />
										<polygon style="fill:#004669;" points="1265.851,484.236 1265.969,486.59 1267.173,487.812 1267.049,485.355 			" />
										<polygon style="fill:#004669;" points="1268.274,486.186 1268.393,488.541 1269.595,489.763 1269.472,487.306 			" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#004669;" points="1249.215,451.767 1249.333,454.12 1250.536,455.342 1250.413,452.886 			" />
										<polygon style="fill:#004669;" points="1251.349,453.696 1251.468,456.051 1252.672,457.273 1252.547,454.816 			" />
										<polygon style="fill:#004669;" points="1253.672,455.401 1253.79,457.755 1254.994,458.977 1254.87,456.52 			" />
										<polygon style="fill:#004669;" points="1256.095,457.351 1256.214,459.706 1257.418,460.928 1257.293,458.471 			" />
										<polygon style="fill:#004669;" points="1249.156,455.627 1249.275,457.982 1250.477,459.204 1250.354,456.748 			" />
										<polygon style="fill:#004669;" points="1251.291,457.559 1251.409,459.912 1252.613,461.135 1252.49,458.678 			" />
										<polygon style="fill:#004669;" points="1253.613,459.263 1253.731,461.617 1254.935,462.839 1254.811,460.382 			" />
										<polygon style="fill:#004669;" points="1256.037,461.214 1256.155,463.568 1257.359,464.79 1257.235,462.333 			" />
										<polygon style="fill:#004669;" points="1249.039,459.493 1249.157,461.847 1250.361,463.07 1250.237,460.613 			" />
										<polygon style="fill:#004669;" points="1251.173,461.423 1251.291,463.778 1252.495,465 1252.372,462.543 			" />
										<polygon style="fill:#004669;" points="1253.495,463.128 1253.615,465.482 1254.818,466.704 1254.693,464.247 			" />
										<polygon style="fill:#004669;" points="1255.919,465.078 1256.037,467.433 1257.241,468.655 1257.117,466.198 			" />
										<polygon style="fill:#004669;" points="1248.98,463.356 1249.098,465.709 1250.302,466.931 1250.178,464.475 			" />
										<polygon style="fill:#004669;" points="1251.115,465.285 1251.234,467.64 1252.437,468.862 1252.313,466.405 			" />
										<polygon style="fill:#004669;" points="1253.438,466.99 1253.555,469.344 1254.759,470.566 1254.636,468.109 			" />
										<polygon style="fill:#004669;" points="1255.86,468.94 1255.979,471.295 1257.183,472.517 1257.058,470.06 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1248.896,467.291 1249.014,469.646 1250.218,470.868 1250.095,468.411 			" />
										<polygon style="fill:#004669;" points="1251.031,469.222 1251.149,471.575 1252.352,472.799 1252.229,470.342 			" />
										<polygon style="fill:#004669;" points="1253.352,470.927 1253.472,473.28 1254.675,474.503 1254.551,472.046 			" />
										<polygon style="fill:#004669;" points="1255.776,472.877 1255.894,475.23 1257.098,476.454 1256.974,473.997 			" />
										<polygon style="fill:#004669;" points="1248.837,471.153 1248.955,473.508 1250.159,474.73 1250.036,472.273 			" />
										<polygon style="fill:#004669;" points="1250.972,473.084 1251.091,475.438 1252.294,476.659 1252.17,474.204 			" />
										<polygon style="fill:#004669;" points="1253.295,474.789 1253.413,477.142 1254.616,478.365 1254.493,475.908 			" />
										<polygon style="fill:#004669;" points="1255.717,476.739 1255.837,479.093 1257.04,480.316 1256.916,477.859 			" />
									</g>
								</g>
								<polygon style="fill:#004669;" points="1284.935,493.386 1284.935,497.027 1287.942,496.624 1287.942,493.006 	" />
							</g>
							<g class="content show" id="14">
								<g>
									<polygon style="fill:#9DC8E7;" points="1335.295,329.935 1335.286,331.533 1325.103,332.908 1323.896,331.409 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="1331.53,331.008 1335.37,330.802 1335.393,330.366 1331.518,330.597 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="1338.225,334.113 1338.588,334.099 1338.528,332.518 1338.167,332.595 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1335.286,331.533 1338.233,334.31 1338.168,332.613 1335.293,329.883 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="1326.426,337.025 1338.34,334.15 1335.288,331.211 1324.153,332.787 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="1346.523,338.454 1346.391,348.202 1334.314,352.05 1334.053,340.736 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1335.999,343.568 1339.835,342.61 1339.765,341.668 1335.973,342.579 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1341.324,342.464 1345.161,341.506 1345.09,340.565 1341.298,341.476 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1334.055,340.731 1334.313,352.05 1324.201,341.89 1323.551,331.251 1325.906,334.213 
			1326.033,336.195 1330.654,338.845 1330.647,339.891 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="1338.58,333.905 1338.15,333.909 1342.582,335.648 1343.069,335.676 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1342.762,336.503 1344.972,338.733 1345.935,338.339 1342.838,335.093 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="1342.856,335.664 1336.607,332.908 1331.518,337.716 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="1326.021,336.212 1333.417,342.374 1338.466,336.827 1331.604,333.496 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="1335.384,329.556 1323.55,331.245 1326.408,334.867 1326.709,334.708 1324.12,331.369 
				1335.295,329.935 1338.168,332.613 1338.528,332.518 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="1326.033,336.195 1326.393,335.879 1326.267,334.147 1325.907,334.225 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="1324.73,332.16 1328.788,331.642 1328.778,331.202 1324.36,331.844 		" />
								</g>
								<g>
									<polygon style="fill:#0A2644;" points="1340.615,339.185 1341.298,338.928 1341.291,338.074 1340.607,338.332 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="1334.115,340.603 1345.543,338.644 1342.765,335.667 1331.347,337.666 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="1334.011,340.921 1346.528,338.464 1343.119,334.951 1342.817,335.11 1345.96,338.341 
				1334.099,340.541 1331.347,337.666 1330.987,337.763 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="15">
								<g>
									<g>
										<g>
											<polygon style="fill:#B2B2B2;" points="1190.141,335.679 1190.828,336.603 1191.116,329.497 1190.406,329.214 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#B2B2B2;" points="1173.865,320.125 1174.551,321.049 1174.828,313.579 1174.117,313.296 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1199.391,341.672 1155.825,299.922 1156.352,299.426 1199.917,341.177 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1199.391,337.919 1155.446,295.814 1155.973,295.319 1199.917,337.423 			" />
									</g>
								</g>
								<polygon style="fill:#00A0E9;" points="1155.259,302.679 1155.259,308.663 1199.741,351.655 1200.014,345.107 	" />
							</g>
							<g class="content show" id="16">
								<g>
									<polygon style="fill:#32A2D5;" points="1170.081,340.667 1146.248,320.293 1143.972,329.585 1169.817,353.598 		" />
									<polygon style="fill:#9DC8E7;" points="1169.817,353.598 1189.432,346.307 1189.638,336.217 1170.081,340.667 		" />
									<polygon style="fill:#32A2D5;" points="1189.638,336.217 1161.9,315.45 1146.248,320.293 1170.081,340.667 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1108.529,332.311 1108.227,344.795 1119.434,353.892 1120.59,341.299 		" />
									<polygon style="fill:#9DC8E7;" points="1119.434,353.892 1156.319,341.051 1157.679,329.711 1120.59,341.299 		" />
									<polygon style="fill:#32A2D5;" points="1108.529,332.311 1146.317,320.294 1157.685,329.416 1120.59,341.299 		" />
								</g>
							</g>
							<g class="content show" id="17">
								<g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="1176.097,358.375 1176.097,352.004 1169.088,347.122 1169.525,353.841 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1176.097,358.375 1200.198,353.719 1200.486,347.064 1176.094,351.8 				" />
										</g>
										<g>
											<polygon style="fill:#004669;" points="1200.198,347.06 1197.328,342.438 1194.495,342.89 1193.475,341.194 1169.336,347.25 
					1176.157,351.78 				" />
											<path style="fill:#00A0E9;" d="M1169.079,347.117l24.372-6.091l0.113-0.017l1.019,1.695l2.826-0.451l3.083,4.935l-24.308,4.761
					l-0.123,0.018L1169.079,347.117z M1197.248,342.623l-2.841,0.454l-1.02-1.696l-23.794,6.006l6.658,4.208l23.65-4.661
					L1197.248,342.623z" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1191.052,352.032 1195.266,351.287 1195.54,349.6 1191.328,350.344 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1184.639,353.053 1188.852,352.31 1189.126,350.621 1184.913,351.366 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1178.185,354.119 1195.232,351.33 1195.277,351.026 1178.197,353.909 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1197.684,350.447 1198.362,350.36 1198.641,348.643 1197.964,348.729 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1195.913,350.692 1196.592,350.605 1196.872,348.888 1196.193,348.974 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1197.41,353.601 1198.088,353.514 1198.367,351.797 1197.69,351.883 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1195.728,353.872 1196.406,353.786 1196.687,352.067 1196.008,352.155 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1178.168,354.127 1182.388,353.419 1182.51,351.855 1178.258,352.493 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1190.55,355.114 1194.765,354.369 1195.039,352.682 1190.825,353.425 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1184.121,356.231 1188.334,355.487 1188.61,353.799 1184.396,354.543 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1177.901,355.74 1194.969,352.927 1195.015,352.623 1177.94,355.536 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1177.637,357.359 1194.733,354.382 1194.779,354.078 1177.676,357.152 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1177.651,357.303 1181.871,356.596 1181.993,355.032 1177.906,355.697 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1199.903,346.939 1195.373,345.492 1192.557,345.325 1190.589,341.817 1173.042,345.07 
				1176.215,351.595 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1169.601,347.421 1173.129,345.325 1191.59,343.341 1192.764,345.503 1195.51,345.664 
				1199.903,346.939 1197.233,342.604 1194.398,343.079 1193.374,341.377 			" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1218.803,358.684 1219.216,359.171 1219.072,356.072 1218.668,355.941 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1223.089,362.835 1223.092,372.01 1212.622,373.537 1212.13,364.801 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1213.497,366.903 1216.206,366.383 1216.206,365.377 1213.444,365.862 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1218.765,366.14 1221.475,365.62 1221.475,364.616 1218.711,365.101 			" />
									</g>
									<g>
										<polygon style="fill:#00A0E9;" points="1206.476,343.98 1206.024,343.994 1221.185,356.568 1221.698,356.583 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1202.914,339.892 1202.947,341.573 1191.997,343.46 1190.686,341.916 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1199.105,341.263 1203.015,340.801 1203.028,340.342 1199.081,340.832 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1206.109,344.208 1206.49,344.184 1206.384,342.521 1206.006,342.612 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1202.947,341.573 1206.122,344.415 1206.008,342.631 1202.91,339.836 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1193.5,347.757 1206.231,344.243 1202.94,341.233 1190.995,343.36 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#00A0E9;" points="1202.996,339.49 1190.319,341.754 1193.422,345.486 1193.736,345.312 1190.921,341.869 
					1202.914,339.892 1206.008,342.631 1206.384,342.521 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1193.063,346.894 1193.434,346.553 1193.256,344.733 1192.878,344.825 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1191.584,342.684 1195.635,341.908 1195.614,341.444 1191.187,342.362 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1221.475,356.575 1206.045,343.989 1202.94,341.233 1197.661,342.173 1214.654,358.988 
							" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1221.475,356.575 1214.928,356.842 1209.28,361.611 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1193.413,347.262 1209.293,361.607 1214.928,356.842 1199.488,344.085 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1212.192,364.659 1222.782,362.725 1221.378,356.582 1209.2,361.645 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="1212.089,364.98 1223.089,362.835 1221.631,356.489 1221.317,356.663 1222.746,362.63 
					1212.172,364.579 1208.943,361.249 1208.545,361.366 				" />
										</g>
									</g>
									<polygon style="fill:#32A2D5;" points="1208.328,361.159 1208.953,370.99 1212.753,373.686 1212.264,364.945 		" />
								</g>
							</g>
							<g class="content show" id="18">
								<g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1543.388,333.294 1543.56,336.424 1523.768,340.323 1521.225,337.528 			" />
									</g>
									<g>
										<polygon style="fill:#00A0E9;" points="1536.461,333.109 1543.637,334.982 1543.632,334.126 1536.388,332.307 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1549.625,341.133 1550.334,341.063 1550.028,337.97 1549.331,338.165 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1543.56,336.424 1549.663,341.518 1549.335,338.2 1543.378,333.189 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1526.846,348.234 1549.854,341.191 1543.525,335.793 1521.892,340.199 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1609.595,383.808 1610.487,402.929 1587.277,411.896 1585.429,389.758 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1589.575,395.077 1596.981,392.745 1596.732,390.907 1589.406,393.142 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1599.881,392.284 1607.285,389.952 1607.037,388.115 1599.712,390.349 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1585.431,389.747 1587.276,411.896 1523.063,358.032 1520.53,337.261 1525.495,342.787 
				1525.979,346.654 1578.543,386.453 1578.655,388.503 			" />
									</g>
									<g>
										<polygon style="fill:#00A0E9;" points="1550.296,340.684 1549.452,340.74 1601.541,378.779 1602.496,378.776 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#002E4B;" points="1532.316,353.655 1531.868,354.107 1534.044,355.937 1534.481,355.402 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1535.356,356.161 1534.906,356.612 1537.083,358.444 1537.521,357.909 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1538.433,358.569 1537.985,359.02 1540.161,360.851 1540.597,360.317 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1541.545,360.974 1541.097,361.426 1543.273,363.256 1543.711,362.721 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1544.415,363.411 1543.965,363.862 1546.141,365.692 1546.579,365.158 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1547.776,366.108 1547.328,366.559 1549.504,368.39 1549.942,367.855 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1551.031,368.777 1550.583,369.229 1552.759,371.059 1553.196,370.524 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1554.446,371.519 1553.997,371.972 1556.173,373.802 1556.611,373.267 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1557.747,374.098 1557.298,374.55 1559.474,376.38 1559.912,375.845 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1561.02,376.707 1560.571,377.159 1562.747,378.989 1563.184,378.454 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1564.116,379.304 1563.668,379.756 1565.843,381.586 1566.281,381.053 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1567.426,382.042 1566.978,382.493 1569.154,384.325 1569.591,383.79 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1570.954,384.948 1570.505,385.4 1572.681,387.23 1573.119,386.695 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1574.469,387.77 1574.021,388.222 1576.197,390.052 1576.633,389.518 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#002E4B;" points="1531.082,355.793 1530.634,356.245 1532.81,358.075 1533.248,357.54 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1534.122,358.299 1533.673,358.75 1535.849,360.581 1536.287,360.047 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1537.199,360.708 1536.751,361.159 1538.927,362.989 1539.364,362.455 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1540.311,363.112 1539.863,363.564 1542.039,365.394 1542.477,364.859 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1543.181,365.548 1542.732,366 1544.908,367.83 1545.346,367.297 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1546.543,368.246 1546.094,368.697 1548.27,370.528 1548.708,369.993 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1549.798,370.915 1549.349,371.367 1551.526,373.197 1551.962,372.662 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1553.213,373.657 1552.763,374.11 1554.939,375.94 1555.377,375.405 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1556.514,376.236 1556.064,376.688 1558.24,378.519 1558.678,377.984 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1559.786,378.845 1559.338,379.296 1561.514,381.127 1561.95,380.593 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1562.883,381.442 1562.433,381.894 1564.609,383.724 1565.047,383.19 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1566.193,384.181 1565.744,384.632 1567.921,386.462 1568.357,385.928 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1569.72,387.086 1569.271,387.538 1571.447,389.368 1571.885,388.833 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1573.235,389.908 1572.787,390.361 1574.963,392.191 1575.4,391.656 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1529.623,357.466 1529.175,357.918 1531.351,359.748 1531.788,359.213 			" />
										<polygon style="fill:#002E4B;" points="1532.662,359.972 1532.214,360.423 1534.39,362.255 1534.826,361.72 			" />
										<polygon style="fill:#002E4B;" points="1535.74,362.38 1535.29,362.831 1537.467,364.663 1537.905,364.128 			" />
										<polygon style="fill:#002E4B;" points="1538.852,364.785 1538.404,365.237 1540.58,367.067 1541.017,366.534 			" />
										<polygon style="fill:#002E4B;" points="1541.721,367.222 1541.272,367.673 1543.449,369.503 1543.885,368.969 			" />
										<polygon style="fill:#002E4B;" points="1545.083,369.919 1544.635,370.371 1546.81,372.201 1547.248,371.666 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1548.339,372.588 1547.89,373.04 1550.065,374.87 1550.503,374.335 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1551.752,375.33 1551.304,375.783 1553.48,377.613 1553.917,377.078 			" />
										<polygon style="fill:#002E4B;" points="1555.053,377.909 1554.605,378.361 1556.781,380.191 1557.218,379.658 			" />
										<polygon style="fill:#002E4B;" points="1558.327,380.518 1557.877,380.97 1560.053,382.8 1560.491,382.265 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1561.422,383.116 1560.974,383.567 1563.15,385.397 1563.587,384.864 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1564.734,385.853 1564.284,386.306 1566.46,388.136 1566.898,387.601 			" />
										<polygon style="fill:#002E4B;" points="1568.26,388.759 1567.812,389.211 1569.988,391.041 1570.425,390.506 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1571.776,391.581 1571.327,392.033 1573.503,393.863 1573.941,393.329 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1527.987,359.178 1527.539,359.629 1529.715,361.459 1530.151,360.925 			" />
										<polygon style="fill:#002E4B;" points="1531.026,361.683 1530.578,362.135 1532.754,363.965 1533.19,363.432 			" />
										<polygon style="fill:#002E4B;" points="1534.104,364.091 1533.654,364.544 1535.831,366.374 1536.268,365.839 			" />
										<polygon style="fill:#002E4B;" points="1537.216,366.497 1536.768,366.948 1538.944,368.779 1539.381,368.244 			" />
										<polygon style="fill:#002E4B;" points="1540.085,368.933 1539.636,369.385 1541.813,371.215 1542.249,370.68 			" />
										<polygon style="fill:#002E4B;" points="1543.447,371.631 1542.999,372.082 1545.174,373.912 1545.612,373.378 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1546.702,374.3 1546.254,374.751 1548.429,376.581 1548.867,376.047 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1550.116,377.042 1549.668,377.493 1551.844,379.323 1552.281,378.79 			" />
										<polygon style="fill:#002E4B;" points="1553.417,379.621 1552.969,380.073 1555.145,381.904 1555.582,381.369 			" />
										<polygon style="fill:#002E4B;" points="1556.691,382.23 1556.241,382.681 1558.417,384.511 1558.855,383.977 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1559.786,384.827 1559.338,385.279 1561.514,387.109 1561.95,386.574 				" />
										</g>
										<polygon style="fill:#002E4B;" points="1563.097,387.564 1562.648,388.017 1564.824,389.847 1565.262,389.313 			" />
										<polygon style="fill:#002E4B;" points="1566.624,390.471 1566.176,390.922 1568.352,392.753 1568.788,392.218 			" />
										<g>
											<polygon style="fill:#002E4B;" points="1570.14,393.293 1569.69,393.744 1571.867,395.576 1572.305,395.041 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1601.994,380.433 1606.589,384.541 1608.427,383.655 1601.974,377.659 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1602.079,378.777 1549.492,340.727 1536.875,335.766 1536.895,340.981 1589.258,379.089 
							" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1602.079,378.777 1589.506,374.115 1580.901,383.616 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1525.958,346.691 1584.375,393.042 1593.613,381.574 1536.578,340.705 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#00A0E9;" points="1543.517,332.54 1520.529,337.249 1526.557,344.007 1527.128,343.66 1521.659,337.425 
					1543.388,333.294 1549.335,338.2 1550.028,337.97 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="1525.979,346.654 1526.647,345.993 1526.196,342.614 1525.5,342.809 			" />
									</g>
									<g>
										<polygon style="fill:#00A0E9;" points="1522.949,338.902 1530.72,335.605 1530.65,334.742 1522.187,338.327 			" />
									</g>
									<g>
										<polygon style="fill:#0A2644;" points="1598.102,385.944 1599.41,385.359 1599.296,383.685 1597.986,384.271 			" />
									</g>
									<g>
										<polygon style="fill:#00A0E9;" points="1532.714,334.276 1534.024,333.69 1533.909,332.017 1532.6,332.602 			" />
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="1583.76,405.626 1585.37,407.306 1584.553,403.389 1583.112,402.208 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="1585.535,389.489 1607.696,384.297 1601.9,378.794 1579.763,384.063 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#00A0E9;" points="1585.367,390.125 1609.607,383.832 1602.508,377.348 1601.937,377.694 1608.476,383.655 
					1585.497,389.369 1579.763,384.063 1579.069,384.293 				" />
										</g>
									</g>
								</g>
								<g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="1588.869,418.119 1587.695,403.006 1575.035,389.258 1576.451,405.384 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1588.869,418.119 1619.496,405.902 1619.287,391.01 1587.602,402.533 				" />
										</g>
										<g>
											<polygon style="fill:#1F3E60;" points="1618.613,391.122 1606.677,383.036 1600.279,385.307 1597.18,381.801 1575.669,389.452 
					1587.74,402.46 				" />
											<path style="fill:#00A0E9;" d="M1575.012,389.251l22.039-7.832l0.254-0.088l3.098,3.504l6.382-2.265l12.568,8.722
					l-31.479,11.548l-0.278,0.096L1575.012,389.251z M1606.572,383.502l-6.413,2.277l-3.103-3.508l-20.729,7.383l11.558,12.33
					l29.99-11.031L1606.572,383.502z" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1605.831,403.301 1615.314,399.756 1615.226,395.712 1605.745,399.258 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1590.999,408.14 1600.511,404.679 1600.123,400.989 1590.507,404.303 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1606.191,409.593 1615.673,406.049 1615.585,402.003 1606.104,405.548 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1591.746,415.054 1601.259,411.594 1600.871,407.903 1591.647,411.209 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1617.874,390.966 1603.445,390.983 1596.821,391.805 1589.895,391.276 1580.659,395.16 
				1587.795,402.003 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1576.358,389.734 1580.97,395.712 1590.623,391.609 1597.38,392.129 1603.836,391.323 
				1617.874,390.966 1606.529,383.464 1600.138,385.789 1597.023,382.272 			" />
									</g>
								</g>
								<g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="1562.017,428.042 1560.844,412.929 1545.43,401.011 1546.844,417.137 				" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1562.017,428.042 1589.89,417.655 1589.682,402.762 1560.751,412.456 				" />
										</g>
										<g>
											<polygon style="fill:#1F3E60;" points="1589.006,402.875 1577.071,394.789 1570.674,397.061 1567.573,393.554 1546.063,401.205 
					1560.887,412.382 				" />
											<path style="fill:#00A0E9;" d="M1545.405,401.004l22.039-7.832l0.254-0.088l3.098,3.504l6.382-2.265l12.568,8.722l-28.725,9.717
					l-0.278,0.096L1545.405,401.004z M1576.965,395.254l-6.413,2.277l-3.103-3.508l-20.729,7.383l14.312,10.498l27.235-9.2
					L1576.965,395.254z" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1576.226,415.054 1585.708,411.509 1585.62,407.465 1576.138,411.011 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1564.148,418.063 1573.66,414.602 1573.271,410.91 1563.655,414.226 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1576.585,421.345 1586.066,417.802 1585.979,413.756 1576.497,417.302 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1564.895,424.977 1574.407,421.517 1574.019,417.826 1564.795,421.132 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1588.268,402.72 1573.84,402.736 1567.214,403.559 1560.288,403.029 1551.054,406.913 
				1560.944,411.926 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="1546.752,401.487 1551.363,407.465 1561.016,403.362 1567.774,403.882 1574.229,403.077 
				1588.268,402.72 1576.923,395.218 1570.531,397.542 1567.417,394.025 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1503.385,360.854 1506.834,337.882 1513.683,341.518 1513.01,366.143 		" />
									<polygon style="fill:#9DC8E7;" points="1513.683,341.518 1513.025,366.072 1524.134,361.617 1521.929,337.882 		" />
									<polygon style="fill:#005A84;" points="1506.718,337.88 1506.718,335.648 1513.683,339.293 1513.683,341.518 		" />
								</g>
								<polygon style="fill:#005A84;" points="1513.683,341.518 1521.929,337.882 1521.681,336.327 1513.683,339.293 	" />
								<polygon style="fill:#00486C;" points="1506.718,335.7 1513.982,333.146 1521.681,336.327 1513.683,340.01 	" />
							</g>
							<g class="content show" id="19">
								<g style="enable-background:new    ;">
									<g>
										<polygon style="fill:#9DC8E7;" points="1468.808,405.43 1468.246,411.585 1459.264,414.132 1459.828,407.977 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1459.828,407.977 1459.264,414.132 1451.708,408.16 1452.272,402.005 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1468.808,405.43 1459.828,407.977 1452.272,402.005 1461.252,399.457 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="2">
								<g style="enable-background:new    ;">
									<g>
										<polygon style="fill:#9DC8E7;" points="1366.211,422.057 1365.649,428.211 1356.669,430.758 1357.231,424.605 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1357.231,424.605 1356.669,430.758 1349.112,424.785 1349.675,418.632 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1366.211,422.057 1357.231,424.605 1349.675,418.632 1358.655,416.085 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="21">
								<g style="enable-background:new    ;">
									<g>
										<polygon style="fill:#9DC8E7;" points="1470.817,440.182 1470.254,446.335 1461.274,448.882 1461.837,442.729 			" />
									</g>
									<g>
										<polygon style="fill:#32A2D5;" points="1461.837,442.729 1461.274,448.882 1444.728,435.764 1445.291,429.609 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1470.817,440.182 1461.837,442.729 1445.291,429.609 1454.271,427.061 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="22">
								<polygon style="fill:#32A2D5;" points="1271.18,295.135 1271.18,300.767 1277.764,306.664 1277.764,300.9 	" />
								<polygon style="fill:#9DC8E7;" points="1277.764,306.664 1287.219,303.013 1287.219,297.297 1277.764,300.9 	" />
								<polygon style="fill:#005A84;" points="1271.18,295.135 1277.764,293.881 1277.764,300.9 	" />
								<polygon style="fill:#004669;" points="1277.764,293.881 1287.219,297.297 1277.764,300.9 	" />
								<polygon style="fill:#00486C;" points="1287.219,297.297 1277.764,293.881 1278.775,291.348 	" />
								<polygon style="fill:#004669;" points="1278.775,291.348 1271.18,295.135 1277.764,293.881 	" />
							</g>
							<g class="content show" id="23">
								<polygon style="fill:#002E4B;" points="935.313,509.005 935.313,515.841 1253.58,413.702 1253.58,406.963 	" />
								<g>
									<g>
										<polygon style="fill:#B2B2B2;" points="936.034,508.749 935.313,509.012 935.313,502.947 936.034,502.69 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1026.982,479.561 1026.261,479.823 1026.261,473.513 1026.982,473.257 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1103.82,454.913 1103.1,455.176 1103.1,448.627 1103.82,448.371 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1180.958,430.175 1180.237,430.438 1180.237,423.889 1180.958,423.633 			" />
									</g>

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="935.483" y1="507.615" x2="1253.22"
									 y2="404.292" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="935.483" y1="505.471" x2="1253.22"
									 y2="402.148" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="935.487" y1="503.254" x2="1253.22"
									 y2="399.962" />
								</g>
							</g>
							<g class="content show" id="24">
								<polygon style="fill:#00A0E9;" points="935.313,509.819 935.313,515.802 1042.561,630.684 1042.561,623.85 	" />
								<g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1032.96,613.536 1033.647,614.46 1033.952,605.219 1033.241,604.936 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1017.166,597.037 1017.852,597.961 1018.143,588.674 1017.432,588.391 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="978.63,556.441 979.317,557.366 979.492,548.226 978.782,547.942 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="955.703,531.52 956.39,532.444 956.566,523.945 955.855,523.662 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1000.817,579.639 1001.504,580.565 1001.672,571.436 1000.961,571.153 			" />
									</g>

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="1044.268" y1="619.963" x2="936.034"
									 y2="506.328" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="1044.354" y1="616.336" x2="936.739"
									 y2="503.163" />
								</g>
							</g>
							<g class="content show" id="25">
								<polygon style="fill:#002E4B;" points="602.097,784.801 602.097,791.637 1044.635,631.227 1044.635,623.596 	" />
								<g>
									<g>
										<polygon style="fill:#B2B2B2;" points="602.818,784.546 602.097,784.808 602.097,778.742 602.818,778.486 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="692.877,751.731 692.156,751.994 692.156,745.684 692.877,745.428 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="770.606,723.398 769.885,723.66 769.885,717.112 770.606,716.855 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="845.39,696.17 844.669,696.434 844.669,689.884 845.39,689.628 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="926.225,666.728 925.504,666.991 925.504,660.442 926.225,660.185 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="1044.635,623.587 1043.914,623.85 1043.914,616.333 1044.635,616.076 			" />
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="985.588,645.092 984.867,645.354 984.867,638.805 985.588,638.549 			" />
									</g>

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="602.269" y1="783.412" x2="1044.274"
									 y2="620.926" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="602.269" y1="781.267" x2="1044.274"
									 y2="618.78" />

									<line style="fill:none;stroke:#B2B2B2;stroke-width:0.2611;stroke-miterlimit:10;" x1="602.271" y1="779.049" x2="1044.274"
									 y2="616.594" />
								</g>
							</g>
							<g class="content show" id="26">
								<g>
									<g>
										<g>
											<polygon style="fill:#9DC8E7;" points="712.45,540.234 762.597,597.931 767.366,567.607 712.165,508.243 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="762.407,597.789 798.667,584.81 803.114,558.062 767.177,567.465 			" />
									</g>
									<g>

										<rect x="761.839" y="558.089" transform="matrix(-0.4746 0.8802 -0.8802 -0.4746 1630.175 143.3365)" style="fill:#365B8A;"
										 width="20.938" height="0.223" />
									</g>
									<g>

										<rect x="792.808" y="542.269" transform="matrix(-0.4941 0.8694 -0.8694 -0.4941 1665.0139 136.0638)" style="fill:#365B8A;"
										 width="0.223" height="20.382" />
									</g>
									<g>
										<g>
											<polygon style="fill:#7BBBD2;" points="734.673,545.25 737.104,548.181 737.624,541.976 735.192,539.045 				" />
											<path style="fill:#0B2341;" d="M734.616,545.268l0.522-6.227l0.01-0.136l2.534,3.053l-0.522,6.227l-0.012,0.135L734.616,545.268
					z M735.237,539.186l-0.506,6.046l2.33,2.809l0.506-6.046L735.237,539.186z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="721.524,530.586 723.956,533.518 724.332,526.965 721.9,524.033 				" />
											<path style="fill:#0B2341;" d="M721.468,530.605l0.387-6.717l2.535,3.057l-0.385,6.716L721.468,530.605z M721.949,524.177
					l-0.368,6.391l2.328,2.806l0.367-6.39L721.949,524.177z" />
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#7BBBD2;" points="754.357,579.273 756.788,582.205 757.308,576.001 754.877,573.068 				" />
											<path style="fill:#0B2341;" d="M754.315,579.309l-0.014-0.017l0.522-6.227l0.01-0.135l2.52,3.037l0.014,0.017l-0.522,6.227
					l-0.012,0.135L754.315,579.309z M754.921,573.209l-0.506,6.046l2.33,2.809l0.506-6.046L754.921,573.209z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="747.238,571.3 749.671,574.231 750.19,568.026 747.759,565.095 				" />
											<path style="fill:#0B2341;" d="M747.196,571.335l-0.014-0.017l0.535-6.363l2.518,3.037l0.014,0.017l-0.522,6.227l-0.012,0.135
					L747.196,571.335z M747.803,565.235l-0.507,6.046l2.331,2.809l0.506-6.046L747.803,565.235z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="740.677,563.952 743.111,566.885 743.63,560.68 741.198,557.748 				" />
											<path style="fill:#0B2341;" d="M740.635,563.987l-0.014-0.017l0.522-6.227l0.012-0.135l2.518,3.036l0.014,0.017l-0.522,6.227
					l-0.01,0.136L740.635,563.987z M741.241,557.889l-0.506,6.046l2.331,2.809l0.506-6.046L741.241,557.889z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="734.036,556.512 736.467,559.445 736.987,553.24 734.554,550.309 				" />
											<path style="fill:#0B2341;" d="M733.993,556.548l-0.014-0.017l0.522-6.227l0.01-0.136l2.52,3.036l0.014,0.017l-0.522,6.227
					l-0.012,0.135L733.993,556.548z M734.6,550.449l-0.506,6.046l2.33,2.809l0.506-6.046L734.6,550.449z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="727.191,548.912 729.625,551.845 730.076,545.36 727.644,542.428 				" />
											<path style="fill:#0B2341;" d="M727.134,548.931l0.463-6.644l2.535,3.056l-0.461,6.645L727.134,548.931z M727.688,542.571
					l-0.44,6.323l2.33,2.808l0.439-6.324L727.688,542.571z" />
										</g>
										<g>
											<polygon style="fill:#7BBBD2;" points="720.886,541.849 723.319,544.78 723.695,538.227 721.263,535.295 				" />
											<path style="fill:#0B2341;" d="M720.829,541.867l0.387-6.717l2.535,3.057l-0.385,6.716L720.829,541.867z M721.31,535.44
					l-0.368,6.391l2.328,2.805l0.367-6.39L721.31,535.44z" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="802.464,557.796 767.343,567.427 712.781,508.486 746.851,501.928 756.531,511.769 
				751.773,513.086 784.197,546.96 789.619,545.644 			" />
										<path style="fill:#00A0E9;" d="M712.018,508.236l34.965-6.729l10.292,10.462l-4.767,1.32l31.816,33.24l5.409-1.312l13.504,12.773
				l-36.018,9.878L712.018,508.236z M789.505,546.074l-5.434,1.319l-33.03-34.509l4.747-1.315l-9.068-9.219l-33.174,6.386
				l53.924,58.252l34.224-9.385L789.505,546.074z" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="751.112,512.994 736.046,504.59 777.281,548.998 784.072,547.457 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="784.072,547.457 801.798,557.522 767.403,566.949 777.281,548.998 			" />
									</g>
								</g>
								<g>
									<g>
										<g>
											<path style="fill:#005A84;" d="M759.677,520.552l17.478,18.533c0.042-0.34,1.48-0.543,1.539-0.878
					c2.082-11.867-6.858-18.626-16.82-19.191C760.873,518.959,760.649,520.435,759.677,520.552z" />
										</g>
										<g id="XMLID_4_">
											<g>
												<g>
													<path style="fill:#32A2D5;" d="M760.009,517.79l-0.338,2.879c13.584,0.43,17.886,6.892,19.181,11.776
							c0.725-1.911,0.735-4.831-2.646-8.465C770.492,517.838,760.009,517.79,760.009,517.79z" />
												</g>
												<g>
													<path style="fill:#32A2D5;" d="M777.129,534.948l0.022,4.133c0,0,1.816-0.298,2.162-1.163c0,0,0.354-2.437-0.46-5.475
							C778.238,534.055,777.129,534.948,777.129,534.948z" />
												</g>
											</g>
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#005A84;" points="749.895,548.817 741.081,545.786 737.49,542.986 735.013,539.264 736.406,534.661 
					749.191,547.818 				" />
										</g>
										<g id="XMLID_3_">
											<g>
												<g>
													<path style="fill:#32A2D5;" d="M735.517,538.072c-4.105,9.467,15.734,12.504,15.734,12.504l0.189-3.338
							c-2.752-0.093-6.883-1.446-6.883-1.446C738.347,543.228,736.061,540.451,735.517,538.072z" />
												</g>
												<g>
													<path style="fill:#005A84;" d="M737.595,532.006c0,0-2.859,2.627-2.077,6.067c0.055-0.131,0.117-0.261,0.18-0.392
							c0,0,1.134-2.63,1.731-2.731L737.595,532.006z" />
												</g>
											</g>
										</g>
									</g>
								</g>
							</g>
							<g class="content show" id="27">
								<g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="681.853,468.998 685.204,473.774 685.779,462.018 682.403,457.083 				" />
										</g>
										<g>
											<g>
												<polygon style="fill:#9DC8E7;" points="680.369,475.611 684.896,479.216 684.833,468.057 680.833,461.882 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon style="fill:#9DC8E7;" points="685.331,468.742 695.444,466.459 695.797,460.201 685.727,461.739 					" />
											</g>
										</g>
										<g>
											<polygon style="fill:#00486C;" points="685.955,461.578 695.059,459.917 691.032,456.198 683.078,457.376 				" />
											<path style="fill:#00A0E9;" d="M682.403,457.083l8.756-1.298l4.739,4.376l-10.123,1.846L682.403,457.083z M690.904,456.611
					l-7.151,1.059l2.381,3.478l8.087-1.475L690.904,456.611z" />
										</g>
										<g>
											<polygon style="fill:#00A0E9;" points="685.002,467.97 697.305,465.638 695.572,464.178 685.485,466.004 				" />
										</g>
										<g>
											<polygon style="fill:#00A0E9;" points="680.833,461.882 685.326,468.82 685.484,466.022 682.276,461.52 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="672.884,495.802 720.865,552.394 722.398,525.3 705.756,508.167 708.783,507.499 
					673.659,469.874 				" />
										</g>
										<g>
											<g>
												<polygon style="fill:#9DC8E7;" points="720.865,552.394 729.296,550.125 730.778,523.422 722.398,525.3 					" />
											</g>
										</g>
										<g>
											<polygon style="fill:#005A84;" points="746.304,506.458 719.805,511.874 730.326,523.334 722.607,525.223 706.339,508.338 
					709.048,507.644 674.425,470.093 705.612,464.243 				" />
											<path style="fill:#00A0E9;" d="M705.592,508.124l2.726-0.697l-34.658-37.59l31.88-5.979l0.207-0.039l41.331,42.879l-26.518,5.42
					l10.501,11.44l-8.578,2.096L705.592,508.124z M719.052,511.63l26.479-5.412l-40.054-41.553l-30.289,5.68l34.589,37.514
					l-2.693,0.69l15.648,16.242l6.86-1.677L719.052,511.63z" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="711.279,520.045 713.93,523.342 713.779,521.52 711.128,518.224 			" />
										<path style="fill:#005A84;" d="M711.236,520.079l-0.01-0.013l-0.167-2.019l2.765,3.437l0.01,0.013l0.167,2.02L711.236,520.079z
				 M711.199,518.4l0.135,1.623l2.526,3.141l-0.135-1.623L711.199,518.4z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="711.031,524.427 713.682,527.723 713.531,525.901 710.88,522.605 			" />
										<path style="fill:#005A84;" d="M710.988,524.461l-0.01-0.013l-0.167-2.019l2.765,3.437l0.01,0.013l0.167,2.02L710.988,524.461z
				 M710.951,522.783l0.135,1.623l2.526,3.141l-0.135-1.623L710.951,522.783z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="710.783,528.809 713.434,532.106 713.283,530.284 710.633,526.988 			" />
										<path style="fill:#0B2341;" d="M710.74,528.844l-0.01-0.013l-0.167-2.019l2.765,3.437l0.01,0.013l0.167,2.02L710.74,528.844z
				 M710.702,527.165l0.135,1.623l2.526,3.141l-0.135-1.623L710.702,527.165z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="710.534,533.191 713.186,536.488 713.034,534.666 710.385,531.37 			" />
										<path style="fill:#0B2341;" d="M710.492,533.225l-0.01-0.013l-0.167-2.019l2.765,3.437l0.01,0.013l0.167,2.02L710.492,533.225z
				 M710.454,531.547l0.135,1.623l2.526,3.141l-0.135-1.623L710.454,531.547z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="710.286,537.574 712.937,540.87 712.786,539.047 710.136,535.752 			" />
										<path style="fill:#0B2341;" d="M710.243,537.608l-0.01-0.013l-0.167-2.019l2.765,3.437l0.01,0.013l0.167,2.02L710.243,537.608z
				 M710.206,535.929l0.135,1.623l2.526,3.141l-0.135-1.623L710.206,535.929z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="707.598,514.676 709.265,516.575 709.234,514.46 707.565,512.56 			" />
										<path style="fill:#005A84;" d="M707.556,514.713l-0.013-0.016l-0.035-2.289l1.769,2.014l0.014,0.016l0.035,2.286L707.556,514.713
				z M707.623,512.711l0.031,1.944l1.555,1.768l-0.03-1.942L707.623,512.711z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="707.326,519.468 708.994,521.366 708.962,519.252 707.294,517.352 			" />
										<path style="fill:#005A84;" d="M707.284,519.505l-0.013-0.016l-0.035-2.289l1.768,2.014l0.014,0.016l0.035,2.286L707.284,519.505
				z M707.351,517.502l0.031,1.944l1.555,1.768l-0.03-1.942L707.351,517.502z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="707.056,524.258 708.724,526.155 708.691,524.042 707.022,522.141 			" />
										<path style="fill:#005A84;" d="M707.013,524.295L707,524.279l-0.035-2.289l1.768,2.014l0.014,0.016l0.035,2.286L707.013,524.295z
				 M707.08,522.293l0.031,1.944l1.555,1.768l-0.03-1.942L707.08,522.293z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="706.785,529.049 708.452,530.947 708.42,528.832 706.751,526.933 			" />
										<path style="fill:#005A84;" d="M706.742,529.085l-0.013-0.014l-0.035-2.289l1.768,2.014l0.014,0.016l0.035,2.286L706.742,529.085
				z M706.808,527.083l0.031,1.944l1.555,1.768l-0.03-1.942L706.808,527.083z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="716.86,524.948 718.455,526.908 718.5,524.794 716.904,522.835 			" />
										<path style="fill:#005A84;" d="M716.816,524.983l-0.013-0.017l0.048-2.285l1.691,2.078l0.013,0.017l-0.049,2.285L716.816,524.983
				z M716.957,522.988l-0.042,1.942l1.487,1.826l0.042-1.942L716.957,522.988z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="718.245,529.361 716.729,527.34 716.6,529.45 718.118,531.47 			" />
										<path style="fill:#0B2341;" d="M716.556,529.484l-0.012-0.017l0.138-2.281l1.609,2.141l0.012,0.017l-0.138,2.281L716.556,529.484
				z M716.774,527.495l-0.118,1.939l1.416,1.883l0.117-1.939L716.774,527.495z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="717.987,533.928 716.47,531.906 716.342,534.017 717.86,536.037 			" />
										<path style="fill:#0B2341;" d="M716.296,534.051l-0.013-0.017l0.138-2.281l1.609,2.142l0.012,0.017l-0.136,2.281L716.296,534.051
				z M716.516,532.061L716.398,534l1.415,1.883l0.117-1.938L716.516,532.061z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="717.729,538.495 716.211,536.473 716.084,538.583 717.602,540.604 			" />
										<path style="fill:#0B2341;" d="M716.038,538.616l-0.013-0.017l0.138-2.281l1.609,2.142l0.013,0.017l-0.136,2.28L716.038,538.616z
				 M716.258,536.627l-0.118,1.939l1.414,1.883l0.117-1.938L716.258,536.627z" />
									</g>
									<g>
										<polygon style="fill:#7BBBD2;" points="717.469,543.06 715.952,541.04 715.824,543.151 717.342,545.171 			" />
										<path style="fill:#0B2341;" d="M715.779,543.183l-0.013-0.017l0.138-2.281l1.609,2.142l0.013,0.017l-0.138,2.281L715.779,543.183
				z M716,541.195l-0.118,1.939l1.414,1.883l0.117-1.938L716,541.195z" />
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="705.954,534.852 707.189,536.25 707.266,533.061 705.931,531.517 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="700.196,510.708 702.367,513.435 702.957,507.766 700.787,505.042 			" />
										<path style="fill:#005A84;" d="M700.154,510.742l-0.014-0.018l0.607-5.825l2.255,2.832l0.014,0.018l-0.607,5.826L700.154,510.742
				z M700.828,505.182l-0.574,5.509l2.071,2.602l0.574-5.51L700.828,505.182z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="694.293,504.094 696.461,506.821 697.052,501.154 694.882,498.428 			" />
										<path style="fill:#005A84;" d="M694.235,504.111l0.607-5.825l2.254,2.832l0.014,0.018l-0.607,5.826L694.235,504.111z
				 M694.924,498.57l-0.573,5.509l2.07,2.602l0.574-5.51L694.924,498.57z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="688.282,497.364 690.453,500.09 691.043,494.422 688.872,491.698 			" />
										<path style="fill:#005A84;" d="M688.238,497.398l-0.014-0.018l0.607-5.825l2.255,2.832l0.014,0.018l-0.607,5.826L688.238,497.398
				z M688.912,491.839l-0.573,5.509l2.071,2.602l0.574-5.51L688.912,491.839z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="682.936,491.374 685.104,494.102 685.54,488.365 683.371,485.641 			" />
										<path style="fill:#005A84;" d="M682.892,491.409l-0.014-0.017l0.448-5.898l2.258,2.836l0.013,0.017l-0.447,5.901L682.892,491.409
				z M683.414,485.785l-0.423,5.572l2.067,2.598l0.423-5.573L683.414,485.785z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="677.517,485.307 679.687,488.033 680.01,482.066 677.84,479.34 			" />
										<path style="fill:#005A84;" d="M677.474,485.341l-0.013-0.017l0.324-5.987l0.008-0.147l2.26,2.84l0.013,0.017l-0.332,6.137
				L677.474,485.341z M677.887,479.49l-0.312,5.798l2.065,2.596l0.313-5.801L677.887,479.49z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="699.61,521.077 701.78,523.803 702.371,518.136 700.2,515.411 			" />
										<path style="fill:#005A84;" d="M699.567,521.112l-0.014-0.018l0.607-5.825l2.255,2.832l0.014,0.018l-0.607,5.826L699.567,521.112
				z M700.242,515.551l-0.574,5.509l2.071,2.602l0.574-5.51L700.242,515.551z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="693.705,514.463 695.875,517.191 696.465,511.522 694.295,508.797 			" />
										<path style="fill:#005A84;" d="M693.648,514.48l0.607-5.825l2.254,2.832l0.014,0.018l-0.607,5.826L693.648,514.48z
				 M694.337,508.938l-0.573,5.509l2.07,2.602l0.573-5.51L694.337,508.938z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="687.694,507.732 689.865,510.46 690.455,504.792 688.284,502.066 			" />
										<path style="fill:#005A84;" d="M687.651,507.767l-0.014-0.018l0.607-5.825l2.255,2.832l0.014,0.018l-0.607,5.826L687.651,507.767
				z M688.326,502.208l-0.573,5.509l2.071,2.602l0.574-5.51L688.326,502.208z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="682.348,501.742 684.517,504.47 684.953,498.735 682.784,496.009 			" />
										<path style="fill:#005A84;" d="M682.305,501.778l-0.014-0.017l0.448-5.898l2.258,2.836l0.013,0.017l-0.447,5.901L682.305,501.778
				z M682.828,496.154l-0.423,5.572l2.067,2.598l0.423-5.573L682.828,496.154z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="676.93,495.675 679.1,498.403 679.422,492.434 677.253,489.709 			" />
										<path style="fill:#005A84;" d="M676.887,495.709l-0.013-0.017l0.324-5.987l0.008-0.146l2.26,2.84l0.013,0.017l-0.332,6.137
				L676.887,495.709z M677.3,489.86l-0.312,5.798l2.065,2.596l0.313-5.801L677.3,489.86z" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="732.367,508.983 698.611,471.945 705.488,464.739 745.457,506.181 			" />
									</g>
									<g>
										<polyline style="fill:#004669;" points="675.213,470.358 705.513,464.773 698.634,471.978 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#004669;" points="694.188,459.668 690.607,457.413 683.596,457.623 690.883,456.592 		" />
								</g>
							</g>
							<g class="content show" id="28">
								<g>
									<polygon style="fill:#32A2D5;" points="589.375,523.354 661.34,568.69 663.563,550.006 589.946,504.541 		" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="661.34,568.69 683.963,553.812 686.415,537.177 663.563,550.006 		" />
								</g>
								<g>
									<polygon style="fill:#365B8A;" points="654.761,564.546 661.34,568.69 663.569,549.924 657.073,545.998 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="646.146,556.681 650.671,559.724 651.384,552.596 646.859,549.555 			" />
										<path style="fill:#005A84;" d="M646.114,556.726l-0.028-0.018l0.717-7.159l0.009-0.093l4.604,3.094l0.028,0.018l-0.717,7.161
				l-0.009,0.093L646.114,556.726z M646.906,549.654l-0.701,7.001l4.421,2.972l0.701-7.002L646.906,549.654z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="636.135,550.28 640.661,553.323 641.349,546.166 636.823,543.124 			" />
										<path style="fill:#005A84;" d="M636.076,550.307l0.691-7.188l0.009-0.092l4.604,3.094l0.028,0.018l-0.692,7.189l-0.009,0.093
				L636.076,550.307z M636.869,543.223l-0.676,7.029l4.421,2.971l0.676-7.031L636.869,543.223z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="625.086,543.301 629.613,546.344 630.339,539.231 625.814,536.189 			" />
										<path style="fill:#005A84;" d="M625.056,543.347l-0.028-0.018l0.739-7.237l4.603,3.094l0.028,0.018l-0.739,7.238L625.056,543.347
				z M625.86,536.288l-0.714,6.986l4.422,2.972l0.715-6.987L625.86,536.288z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="615.694,537.565 620.22,540.606 620.908,533.45 616.381,530.408 			" />
										<path style="fill:#005A84;" d="M615.635,537.591l0.69-7.189l0.009-0.093l4.604,3.095l0.028,0.018l-0.69,7.191l-0.009,0.093
				L615.635,537.591z M616.429,530.506l-0.675,7.031l4.422,2.971l0.675-7.031L616.429,530.506z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="605.509,530.763 610.036,533.805 610.781,526.711 606.254,523.67 			" />
										<path style="fill:#005A84;" d="M605.478,530.808l-0.028-0.018l0.749-7.125l0.01-0.092l4.604,3.094l0.028,0.018l-0.749,7.126
				l-0.01,0.092L605.478,530.808z M606.3,523.768l-0.732,6.967l4.422,2.972l0.732-6.968L606.3,523.768z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="594.981,524.223 599.506,527.266 600.277,520.198 595.749,517.157 			" />
										<path style="fill:#005A84;" d="M594.95,524.269l-0.028-0.018l0.772-7.099l0.01-0.092l4.604,3.094l0.028,0.018l-0.773,7.1
				l-0.01,0.092L594.95,524.269z M595.795,517.255l-0.755,6.94l4.422,2.972l0.755-6.94L595.795,517.255z" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="648.266,544.008 652.832,546.991 653.452,539.854 648.886,536.871 			" />
										<path style="fill:#005A84;" d="M648.235,544.054l-0.028-0.018l0.623-7.17l0.008-0.093l4.645,3.034l0.028,0.018l-0.624,7.17
				l-0.008,0.093L648.235,544.054z M648.933,536.968l-0.608,7.011l4.46,2.914l0.61-7.011L648.933,536.968z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="638.171,537.736 642.739,540.72 643.332,533.556 638.766,530.573 			" />
										<path style="fill:#005A84;" d="M638.141,537.784l-0.028-0.018l0.596-7.197l0.008-0.093l4.645,3.034l0.028,0.018l-0.596,7.197
				l-0.008,0.093L638.141,537.784z M638.813,530.672l-0.583,7.037l4.46,2.913l0.583-7.039L638.813,530.672z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="627.033,530.905 631.599,533.887 632.232,526.766 627.666,523.785 			" />
										<path style="fill:#005A84;" d="M627.002,530.952l-0.028-0.018l0.636-7.153l0.008-0.093l4.645,3.032l0.028,0.018l-0.644,7.246
				L627.002,530.952z M627.712,523.882l-0.621,6.995l4.46,2.913l0.623-6.995L627.712,523.882z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="617.566,525.293 622.131,528.275 622.725,521.109 618.158,518.128 			" />
										<path style="fill:#005A84;" d="M617.535,525.34l-0.028-0.018l0.594-7.199l0.008-0.093l4.646,3.034l0.028,0.018l-0.596,7.199
				l-0.008,0.093L617.535,525.34z M618.205,518.227l-0.581,7.039l4.46,2.912l0.583-7.04L618.205,518.227z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="607.292,518.626 611.857,521.609 612.509,514.507 607.944,511.525 			" />
										<path style="fill:#005A84;" d="M607.261,518.673l-0.028-0.018l0.654-7.134l0.008-0.092l4.643,3.034l0.028,0.018l-0.654,7.134
				l-0.008,0.092L607.261,518.673z M607.99,511.622l-0.64,6.976l4.46,2.913l0.64-6.976L607.99,511.622z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="596.677,512.226 601.244,515.209 601.92,508.133 597.354,505.151 			" />
										<path style="fill:#005A84;" d="M596.649,512.272l-0.028-0.018l0.679-7.109l0.008-0.092l4.643,3.034l0.028,0.018l-0.679,7.109
				l-0.008,0.092L596.649,512.272z M597.401,505.248l-0.665,6.949l4.46,2.914l0.665-6.951L597.401,505.248z" />
									</g>
								</g>
								<g>
									<polygon style="fill:#005A84;" points="687.496,536.234 625.265,499.17 626.376,498.055 609.96,487.167 584.42,500.919 
			663.55,549.551 		" />
									<path style="fill:#00A0E9;" d="M583.638,500.898l26.344-14.183l17.007,11.28l-1.095,1.099l61.801,36.807l0.582,0.346
			l-24.737,13.759L583.638,500.898z M625.064,499.506l-0.43-0.256l1.129-1.133l-15.823-10.496l-24.738,13.321l78.36,48.157
			l23.153-12.878L625.064,499.506z" />
								</g>
								<g>
									<polygon style="fill:#FFFFFF;" points="672.233,561.527 677.521,558.037 678.903,545.151 673.625,548.456 		" />
								</g>
								<g>
									<polyline style="fill:#00486C;" points="686.652,536.163 668.688,533.937 605.058,495.848 585.192,500.965 609.863,487.537 
			625.769,498.079 624.631,499.246 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="686.713,536.204 668.729,534.07 663.679,549.049 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="663.785,549.083 668.794,533.971 668.583,533.902 663.574,549.013 		" />
								</g>
								<g>
									<path style="fill:none;stroke:#365B8A;stroke-width:0.2961;stroke-miterlimit:10;" d="M605.058,495.848" />
								</g>
								<g>
									<path style="fill:none;stroke:#365B8A;stroke-width:0.2961;stroke-miterlimit:10;" d="M609.863,487.537" />
								</g>
							</g>
							<g class="content show" id="29">
								<g>
									<g>
										<polygon style="fill:#32A2D5;" points="774.046,610.236 780.279,608.618 781.067,589.557 775.138,590.93 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="741.821,624.105 778.995,613.929 779.781,594.867 742.265,603.913 			" />
									</g>
									<g>
										<polygon style="fill:#365B8A;" points="686.276,554.071 741.821,624.105 742.243,604.305 689.472,535.529 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="742.336,603.917 779.094,594.772 775.182,590.807 780.387,589.33 723.155,527.752 
				718.428,528.83 715.091,525.818 692.414,532.453 694.266,534.528 689.472,535.529 			" />
										<path style="fill:#00A0E9;" d="M688.703,535.29l4.822-1.007l-1.839-2.061l23.515-6.831l3.338,3.014l4.734-1.08l57.881,62.193
				l-5.231,1.484l3.929,3.983l-37.642,9.366L688.703,535.29z M774.44,590.612l5.18-1.47l-56.58-60.963l-4.722,1.076l-3.335-3.011
				l-21.84,6.438l1.865,2.088l-4.767,0.995l52.219,67.717l35.875-8.925L774.44,590.612z" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#004669;" points="714.361,548.77 736.933,577.608 751.991,572.185 728.332,544.568 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="749.915,572.89 726.417,545.108 726.27,545.276 749.766,573.058 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="747.054,574.019 747.203,573.854 724.128,545.773 723.978,545.938 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="744.304,574.891 721.706,546.486 721.554,546.647 744.15,575.053 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="740.94,576.12 718.673,547.41 718.517,547.57 740.784,576.28 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="738.095,577.24 738.252,577.081 716.082,548.197 715.925,548.357 			" />
									</g>
									<g>

										<rect x="734.134" y="572.558" transform="matrix(-0.9362 0.3515 -0.3515 -0.9362 1637.9078 847.9932)" style="fill:#003351;"
										 width="15.695" height="0.223" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="746.678,567.688 746.6,567.48 732.151,565.814 732.23,566.022 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="743.72,565.071 743.643,564.863 729.424,563.11 729.501,563.319 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="726.834,560.875 740.794,562.523 740.713,562.315 726.754,560.667 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="738.202,560.283 738.124,560.074 724.208,558.222 724.286,558.43 			" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="735.473,557.912 735.396,557.703 721.66,555.716 721.738,555.926 			" />
									</g>
									<g>

										<rect x="718.928" y="550.992" transform="matrix(-0.936 0.3521 -0.3521 -0.936 1599.8209 811.2536)" style="fill:#003351;"
										 width="14.425" height="0.223" />
									</g>
									<g>
										<polygon style="fill:#003351;" points="716.755,551.102 730.186,546.253 730.11,546.043 716.679,550.893 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="772.033,602.259 775.385,601.447 775.433,598.033 772.079,598.846 		" />
									<path style="fill:#005A84;" d="M772.024,598.803l3.395-0.822l0.069-0.017l-0.049,3.528l-3.394,0.821l-0.069,0.017L772.024,598.803
			z M775.376,598.105l-3.241,0.785l-0.045,3.297l3.239-0.783L775.376,598.105z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="766.626,603.768 769.978,602.956 770.025,599.542 766.673,600.354 		" />
									<path style="fill:#005A84;" d="M766.618,600.31l3.393-0.821l0.069-0.017l-0.047,3.528l-3.394,0.821l-0.069,0.017L766.618,600.31z
			 M769.967,599.614l-3.239,0.784l-0.046,3.297l3.239-0.784L769.967,599.614z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="760.807,605.464 764.16,604.654 764.207,601.24 760.855,602.051 		" />
									<path style="fill:#005A84;" d="M760.798,602.008l3.395-0.822l0.069-0.017l-0.048,3.529l-3.394,0.821l-0.069,0.017L760.798,602.008
			z M764.15,601.311l-3.241,0.784l-0.046,3.297l3.241-0.783L764.15,601.311z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="754.592,607.114 757.944,606.303 757.99,602.889 754.638,603.702 		" />
									<path style="fill:#005A84;" d="M754.583,603.657l3.394-0.822l0.069-0.017l-0.047,3.528l-3.395,0.821l-0.069,0.017L754.583,603.657
			z M757.934,602.961l-3.239,0.785l-0.046,3.297l3.241-0.784L757.934,602.961z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="748.358,608.537 751.711,607.725 751.758,604.313 748.406,605.124 		" />
									<path style="fill:#005A84;" d="M748.349,605.081l3.395-0.822l0.069-0.017l-0.049,3.528l-3.395,0.821l-0.069,0.017L748.349,605.081
			z M751.701,604.385l-3.241,0.784l-0.046,3.297l3.241-0.784L751.701,604.385z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="771.72,607.791 775.072,606.979 775.119,603.566 771.766,604.378 		" />
									<path style="fill:#005A84;" d="M771.711,604.334l3.395-0.822l0.069-0.017l-0.049,3.528l-3.394,0.821l-0.069,0.017L771.711,604.334
			z M775.063,603.638l-3.241,0.784l-0.045,3.298l3.239-0.784L775.063,603.638z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="766.312,609.3 769.664,608.488 769.712,605.075 766.359,605.886 		" />
									<path style="fill:#005A84;" d="M766.304,605.843l3.393-0.821l0.069-0.017l-0.047,3.528l-3.394,0.821l-0.069,0.017L766.304,605.843
			z M769.654,605.145l-3.239,0.784l-0.046,3.298l3.239-0.784L769.654,605.145z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="760.494,610.998 763.846,610.186 763.894,606.772 760.541,607.584 		" />
									<path style="fill:#005A84;" d="M760.485,607.541l3.396-0.821l0.069-0.017l-0.048,3.528l-3.395,0.821l-0.069,0.017L760.485,607.541
			z M763.837,606.844l-3.241,0.784l-0.046,3.297l3.241-0.783L763.837,606.844z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="754.279,612.647 757.631,611.837 757.677,608.422 754.325,609.234 		" />
									<path style="fill:#005A84;" d="M754.271,609.19l3.394-0.822l0.069-0.017l-0.047,3.529l-3.394,0.821l-0.069,0.017L754.271,609.19z
			 M757.62,608.494l-3.239,0.785l-0.046,3.297l3.241-0.783L757.62,608.494z" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="748.045,614.07 751.397,613.259 751.444,609.845 748.092,610.657 		" />
									<path style="fill:#005A84;" d="M748.037,610.613l3.395-0.822l0.069-0.017l-0.048,3.528l-3.395,0.821l-0.069,0.017L748.037,610.613
			z M751.388,609.916l-3.241,0.784l-0.046,3.297l3.241-0.784L751.388,609.916z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="771.426,612.988 774.777,612.177 774.824,608.762 771.472,609.575 		" />
									<path style="fill:#005A84;" d="M771.416,609.532l3.395-0.822l0.069-0.017l-0.049,3.529l-3.394,0.821l-0.069,0.017L771.416,609.532
			z M774.768,608.834l-3.241,0.785l-0.045,3.297l3.239-0.783L774.768,608.834z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="766.019,614.497 769.371,613.685 769.417,610.271 766.064,611.084 		" />
									<path style="fill:#005A84;" d="M766.009,611.04l3.393-0.822l0.069-0.017l-0.047,3.528l-3.394,0.821l-0.069,0.017L766.009,611.04z
			 M769.36,610.343l-3.239,0.784l-0.046,3.297l3.239-0.784L769.36,610.343z" />
								</g>
								<g>
									<polygon style="fill:#7BBBD2;" points="760.199,616.194 763.553,615.383 763.6,611.969 760.246,612.782 		" />
									<path style="fill:#005A84;" d="M760.191,612.739l3.395-0.822l0.069-0.017l-0.048,3.528l-3.395,0.821l-0.069,0.017L760.191,612.739
			z M763.542,612.041l-3.241,0.785l-0.046,3.297l3.241-0.784L763.542,612.041z" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="753.984,617.845 757.337,617.033 757.383,613.62 754.031,614.431 		" />
									<path style="fill:#005A84;" d="M753.976,614.388l3.394-0.821l0.069-0.017l-0.047,3.528l-3.395,0.821l-0.069,0.017L753.976,614.388
			z M757.327,613.692l-3.239,0.784l-0.046,3.297l3.241-0.784L757.327,613.692z" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="747.75,619.266 751.104,618.456 751.151,615.042 747.799,615.853 		" />
									<path style="fill:#005A84;" d="M747.742,615.81l3.395-0.822l0.069-0.017l-0.048,3.529l-3.394,0.821l-0.069,0.017L747.742,615.81z
			 M751.094,615.113l-3.241,0.785l-0.046,3.297l3.241-0.783L751.094,615.113z" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="775.33,601.404 772.09,602.186 772.134,598.889 775.375,598.105 		" />
									<polygon style="fill:#005A84;" points="769.923,602.911 766.683,603.695 766.728,600.398 769.969,599.614 		" />
									<polygon style="fill:#005A84;" points="764.105,604.609 760.864,605.393 760.91,602.096 764.15,601.311 		" />
									<polygon style="fill:#005A84;" points="757.889,606.26 754.65,607.042 754.693,603.745 757.935,602.961 		" />
									<polygon style="fill:#005A84;" points="751.701,604.382 751.656,607.68 748.416,608.465 748.461,605.166 		" />
									<polygon style="fill:#005A84;" points="775.014,606.969 771.775,607.753 771.82,604.456 775.06,603.671 		" />
									<polygon style="fill:#005A84;" points="769.608,608.478 766.367,609.262 766.412,605.963 769.653,605.179 		" />
									<polygon style="fill:#005A84;" points="763.789,610.176 760.549,610.958 760.594,607.661 763.834,606.877 		" />
									<polygon style="fill:#005A84;" points="757.575,611.825 754.334,612.609 754.378,609.312 757.62,608.526 		" />
									<polygon style="fill:#005A84;" points="751.345,612.995 748.959,610.501 751.388,609.912 		" />
									<polygon style="fill:#005A84;" points="774.721,612.166 771.48,612.95 771.526,609.653 774.767,608.869 		" />
									<polygon style="fill:#005A84;" points="769.313,613.675 766.074,614.459 766.118,611.161 769.359,610.378 		" />
									<polygon style="fill:#005A84;" points="763.495,615.372 760.256,616.156 760.3,612.858 763.541,612.074 		" />
									<polygon style="fill:#005A84;" points="757.282,616.99 755.561,617.4 754.068,615.84 754.086,614.476 757.328,613.692 		" />
								</g>
							</g>
							<g class="content show" id="30">
								<polygon style="fill:#09384E;" points="1043.201,434.842 1044.085,441.536 1056.995,454.167 1056.822,447.547 	" />
								<g>
									<polygon style="fill:#00486C;" points="1043.201,434.842 1056.822,447.547 1176.565,412.287 1161.226,399.904 		" />
									<polygon style="fill:#09384E;" points="1056.995,454.167 1176.43,419.088 1176.565,412.287 1056.822,447.547 		" />
									<polygon style="fill:none;stroke:#00A0E9;stroke-width:0.2611;stroke-miterlimit:10;" points="1043.201,434.842 1056.822,447.547 
			1176.565,412.287 1161.226,399.904 		" />
								</g>
							</g>
							<g class="content show" id="31">
								<g>
									<polygon style="fill:#32A2D5;" points="778.945,609.75 823.649,659.506 825.887,634.585 780.831,584.525 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="861.944,624.283 825.931,634.149 780.982,584.546 778.316,585.247 760.056,565.007 
			769.782,562.537 773.262,566.017 799.833,559.381 		" />
									<path style="fill:#00A0E9;" d="M780.852,584.984l-2.668,0.703l-18.852-20.897l10.569-2.684l3.479,3.481l26.357-6.582l0.22-0.055
			l62.266,65.066l0.454,0.472l-36.875,10.104L780.852,584.984z M799.708,559.815l-26.566,6.634l-3.478-3.478l-8.884,2.256
			l17.667,19.585l2.665-0.701l44.948,49.602l35.148-9.63L799.708,559.815z" />
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="819.047,637.785 821.727,641.015 822.299,634.181 819.62,630.951 			" />
										<path style="fill:#005A84;" d="M819.004,637.82l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.135L819.004,637.82z M819.664,631.091l-0.56,6.675l2.577,3.107l0.559-6.675L819.664,631.091z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="811.208,629.003 813.886,632.231 814.459,625.399 811.779,622.168 			" />
										<path style="fill:#005A84;" d="M811.166,629.038l-0.014-0.017l0.573-6.856l0.01-0.135l2.766,3.334l0.014,0.017l-0.576,6.856
				l-0.012,0.135L811.166,629.038z M811.824,622.309l-0.559,6.675l2.576,3.107l0.56-6.675L811.824,622.309z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="803.982,620.91 806.66,624.14 807.233,617.306 804.553,614.076 			" />
										<path style="fill:#005A84;" d="M803.94,620.945l-0.014-0.017l0.574-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.574,6.856
				l-0.012,0.135L803.94,620.945z M804.597,614.216l-0.559,6.675l2.577,3.107l0.56-6.675L804.597,614.216z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="796.665,612.716 799.344,615.945 799.916,609.111 797.237,605.882 			" />
										<path style="fill:#005A84;" d="M796.623,612.752l-0.014-0.017l0.573-6.856l0.01-0.136l2.766,3.334l0.014,0.017l-0.576,6.856
				l-0.012,0.135L796.623,612.752z M797.281,606.022l-0.559,6.675l2.576,3.107l0.56-6.675L797.281,606.022z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="789.128,604.344 791.808,607.575 792.38,600.74 789.701,597.51 			" />
										<path style="fill:#005A84;" d="M789.085,604.38l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.135L789.085,604.38z M789.745,597.651l-0.56,6.676l2.577,3.107l0.559-6.676L789.745,597.651z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="782.183,596.565 784.863,599.795 785.434,592.961 782.756,589.731 			" />
										<path style="fill:#005A84;" d="M782.14,596.6l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.135L782.14,596.6z M782.799,589.871l-0.56,6.675l2.577,3.107l0.558-6.676L782.799,589.871z" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="817.215,649.926 819.893,653.156 820.465,646.322 817.788,643.093 			" />
										<path style="fill:#005A84;" d="M817.17,649.961l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.135L817.17,649.961z M817.831,643.233l-0.56,6.675l2.577,3.107l0.559-6.675L817.831,643.233z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="809.374,641.143 812.052,644.372 812.625,637.54 809.945,634.311 			" />
										<path style="fill:#005A84;" d="M809.332,641.179l-0.014-0.017l0.573-6.856l0.01-0.135l2.766,3.334l0.014,0.017l-0.576,6.856
				l-0.012,0.135L809.332,641.179z M809.99,634.451l-0.559,6.675l2.576,3.107l0.56-6.675L809.99,634.451z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="802.148,633.052 804.826,636.281 805.399,629.448 802.721,626.218 			" />
										<path style="fill:#005A84;" d="M802.104,633.087l-0.014-0.017l0.574-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.574,6.856
				l-0.012,0.135L802.104,633.087z M802.765,626.358l-0.56,6.675l2.577,3.107l0.558-6.675L802.765,626.358z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="794.832,624.857 797.509,628.087 798.082,621.253 795.403,618.023 			" />
										<path style="fill:#005A84;" d="M794.789,624.894l-0.014-0.017l0.573-6.856l0.01-0.136l2.766,3.334l0.014,0.017l-0.576,6.856
				l-0.012,0.135L794.789,624.894z M795.449,618.163l-0.559,6.676l2.576,3.107l0.56-6.676L795.449,618.163z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="787.298,616.42 789.978,619.65 790.549,612.816 787.871,609.587 			" />
										<path style="fill:#005A84;" d="M787.255,616.455l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.135L787.255,616.455z M787.914,609.727l-0.56,6.675l2.577,3.107l0.559-6.675L787.914,609.727z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="780.353,608.64 783.032,611.871 783.604,605.037 780.926,601.808 			" />
										<path style="fill:#005A84;" d="M780.31,608.676l-0.014-0.017l0.576-6.856l0.012-0.135l2.765,3.334l0.014,0.017l-0.573,6.856
				l-0.01,0.136L780.31,608.676z M780.969,601.948l-0.56,6.675l2.577,3.107l0.559-6.675L780.969,601.948z" />
									</g>
								</g>
								<g>
									<polygon style="fill:#00486C;" points="861.197,624.077 833.657,612.796 786.811,562.999 799.725,559.856 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#9DC8E7;" points="823.608,659.435 859.011,650.917 862.645,624.398 825.846,634.514 			" />
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#32A2D5;" points="841.312,631.339 846.549,636.593 846.84,631.444 841.689,626.246 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="846.325,639.798 857.831,628.873 846.844,631.383 					" />
											</g>
											<g>
												<polygon style="fill:#32A2D5;" points="836.082,658.609 842.806,666.334 846.907,638.969 839.649,631.68 					" />
											</g>
											<g>
												<polygon style="fill:#00486C;" points="846.876,630.955 857.047,628.651 850.928,622.652 841.043,625.171 					" />
												<path style="fill:#00A0E9;" d="M840.28,624.963l10.764-2.744l6.787,6.654l-11.078,2.508L840.28,624.963z M850.811,623.083
						l-9.004,2.296l5.19,5.148l9.265-2.098L850.811,623.083z" />
											</g>
											<g>
												<polygon style="fill:#00A0E9;" points="846.427,638.957 857.31,636.352 857.438,634.088 846.557,636.461 					" />
											</g>
											<g>
												<polygon style="fill:#00A0E9;" points="839.649,631.68 846.429,639.001 846.564,636.497 841.31,631.227 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="842.779,666.381 853.346,663.239 857.31,636.352 846.425,639.007 					" />
											</g>
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="847.979,664.885 849.258,664.47 852.86,637.418 851.6,637.755 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="838.48,661.431 839.44,662.533 843.008,635.561 841.984,634.551 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#004669;" points="825.994,634.118 833.721,612.765 833.511,612.687 825.786,634.041 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="856.216,628.401 850.507,624.578 841.746,625.392 850.805,623.082 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="32">
								<g>
									<polygon style="fill:#9DC8E7;" points="956.3,369.616 956.278,373.664 930.469,377.149 927.407,373.348 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="947.387,368.838 956.49,371.81 956.549,370.706 947.355,367.799 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="963.727,380.203 964.647,380.168 964.495,376.159 963.581,376.355 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="956.278,373.664 963.746,380.701 963.582,376.401 956.295,369.479 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="933.819,387.581 964.019,380.296 956.282,372.847 928.06,376.842 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="1037.696,439.867 1037.357,464.577 1006.749,474.328 1006.088,445.652 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1011.019,452.831 1020.744,450.402 1020.565,448.015 1010.952,450.325 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1024.516,450.034 1034.241,447.604 1034.063,445.217 1024.45,447.527 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1006.093,445.64 1006.746,474.328 928.182,399.915 926.533,372.95 932.502,380.457 
			932.823,385.48 997.472,440.858 997.457,443.509 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="964.627,379.676 963.535,379.684 1027.706,432.758 1028.938,432.828 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#002E4B;" points="940.448,394.995 939.834,395.542 942.497,398.07 943.101,397.415 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="944.17,398.461 943.556,399.008 946.217,401.537 946.823,400.881 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="947.948,401.804 947.334,402.351 949.997,404.88 950.601,404.224 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="951.773,405.146 951.16,405.692 953.821,408.221 954.427,407.566 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="955.28,408.508 954.666,409.055 957.329,411.584 957.933,410.928 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="959.403,412.246 958.789,412.793 961.452,415.322 962.058,414.666 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="963.391,415.939 962.777,416.486 965.439,419.015 966.044,418.359 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="967.577,419.74 966.965,420.286 969.626,422.814 970.232,422.16 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="971.63,423.32 971.018,423.866 973.68,426.395 974.285,425.74 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="975.646,426.935 975.032,427.482 977.695,430.011 978.299,429.356 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="979.435,430.524 978.821,431.072 981.482,433.599 982.088,432.945 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="983.488,434.31 982.875,434.858 985.536,437.385 986.142,436.731 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="987.808,438.329 987.194,438.876 989.856,441.405 990.461,440.749 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="992.118,442.24 991.505,442.786 994.167,445.315 994.772,444.661 			" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#002E4B;" points="938.691,397.654 938.079,398.202 940.74,400.729 941.346,400.075 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="942.413,401.12 941.799,401.668 944.462,404.195 945.066,403.541 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="946.192,404.463 945.579,405.011 948.24,407.538 948.846,406.884 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="950.016,407.804 949.403,408.352 952.065,410.88 952.67,410.226 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="953.523,411.167 952.91,411.715 955.572,414.243 956.178,413.588 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="957.648,414.905 957.034,415.453 959.695,417.98 960.301,417.326 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="961.634,418.598 961.021,419.146 963.683,421.673 964.288,421.019 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="965.822,422.398 965.208,422.946 967.869,425.474 968.475,424.82 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="969.875,425.978 969.262,426.526 971.924,429.054 972.529,428.4 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="973.891,429.595 973.277,430.142 975.938,432.671 976.544,432.015 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="977.678,433.184 977.064,433.73 979.727,436.259 980.331,435.604 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="981.732,436.97 981.118,437.516 983.781,440.045 984.385,439.39 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="986.051,440.988 985.438,441.536 988.1,444.063 988.705,443.409 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="990.362,444.9 989.748,445.446 992.411,447.975 993.016,447.32 			" />
									</g>
									<polygon style="fill:#002E4B;" points="936.68,399.697 936.066,400.244 938.729,402.772 939.333,402.117 		" />
									<polygon style="fill:#002E4B;" points="940.402,403.164 939.788,403.71 942.451,406.238 943.055,405.584 		" />
									<polygon style="fill:#002E4B;" points="944.18,406.505 943.568,407.053 946.229,409.581 946.835,408.927 		" />
									<polygon style="fill:#002E4B;" points="948.005,409.847 947.392,410.395 950.053,412.922 950.659,412.268 		" />
									<polygon style="fill:#002E4B;" points="951.512,413.21 950.899,413.758 953.561,416.285 954.166,415.631 		" />
									<polygon style="fill:#002E4B;" points="955.635,416.947 955.023,417.495 957.684,420.023 958.29,419.368 		" />
									<g>
										<polygon style="fill:#002E4B;" points="959.623,420.641 959.01,421.188 961.672,423.716 962.277,423.062 			" />
									</g>
									<polygon style="fill:#002E4B;" points="963.811,424.441 963.197,424.989 965.858,427.516 966.464,426.862 		" />
									<polygon style="fill:#002E4B;" points="967.864,428.021 967.25,428.569 969.912,431.096 970.517,430.442 		" />
									<polygon style="fill:#002E4B;" points="971.878,431.638 971.266,432.185 973.927,434.713 974.533,434.058 		" />
									<g>
										<polygon style="fill:#002E4B;" points="975.667,435.226 975.053,435.774 977.715,438.302 978.32,437.647 			" />
									</g>
									<polygon style="fill:#002E4B;" points="979.721,439.012 979.107,439.559 981.77,442.088 982.374,441.432 		" />
									<polygon style="fill:#002E4B;" points="984.04,443.032 983.427,443.578 986.089,446.106 986.694,445.452 		" />
									<g>
										<polygon style="fill:#002E4B;" points="988.351,446.942 987.737,447.489 990.4,450.018 991.004,449.362 			" />
									</g>
									<polygon style="fill:#002E4B;" points="934.438,401.775 933.825,402.322 936.487,404.851 937.093,404.195 		" />
									<polygon style="fill:#002E4B;" points="938.16,405.241 937.547,405.788 940.209,408.317 940.813,407.662 		" />
									<polygon style="fill:#002E4B;" points="941.938,408.584 941.326,409.131 943.987,411.66 944.593,411.004 		" />
									<polygon style="fill:#002E4B;" points="945.764,411.926 945.15,412.473 947.811,415.002 948.417,414.346 		" />
									<polygon style="fill:#002E4B;" points="949.271,415.289 948.657,415.835 951.32,418.364 951.924,417.709 		" />
									<polygon style="fill:#002E4B;" points="953.395,419.026 952.781,419.573 955.443,422.102 956.048,421.446 		" />
									<g>
										<polygon style="fill:#002E4B;" points="957.381,422.719 956.768,423.266 959.431,425.795 960.035,425.139 			" />
									</g>
									<polygon style="fill:#002E4B;" points="961.569,426.52 960.955,427.067 963.617,429.595 964.222,428.94 		" />
									<polygon style="fill:#002E4B;" points="965.622,430.1 965.009,430.647 967.67,433.176 968.276,432.52 		" />
									<polygon style="fill:#002E4B;" points="969.636,433.716 969.024,434.264 971.686,436.791 972.291,436.137 		" />
									<g>
										<polygon style="fill:#002E4B;" points="973.425,437.304 972.812,437.852 975.474,440.38 976.079,439.725 			" />
									</g>
									<polygon style="fill:#002E4B;" points="977.479,441.09 976.865,441.638 979.528,444.166 980.132,443.511 		" />
									<polygon style="fill:#002E4B;" points="981.798,445.109 981.185,445.656 983.847,448.185 984.452,447.53 		" />
									<g>
										<polygon style="fill:#002E4B;" points="986.109,449.02 985.495,449.568 988.158,452.096 988.762,451.441 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1028.162,434.925 1033.764,440.576 1036.203,439.577 1028.352,431.347 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="1028.401,432.797 963.588,379.671 947.714,372.295 947.334,379.017 1011.853,432.202 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="1028.401,432.797 1012.559,425.811 1000.73,437.385 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="932.793,385.524 1004.476,449.803 1017.272,435.744 946.947,378.637 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="956.524,368.653 926.532,372.934 933.775,382.112 934.539,381.711 927.975,373.25 
				956.3,369.616 963.582,376.401 964.495,376.159 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="932.823,385.48 933.737,384.68 933.42,380.29 932.506,380.488 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="929.522,375.254 939.795,371.608 939.77,370.491 928.585,374.452 		" />
								</g>
								<g>
									<polygon style="fill:#0A2644;" points="1022.717,441.723 1024.45,441.072 1024.431,438.906 1022.7,439.559 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="942.468,370.051 944.201,369.398 944.183,367.232 942.451,367.884 		" />
								</g>
								<g>
									<polygon style="fill:#FFFFFF;" points="1002.703,465.974 1004.647,468.265 1003.899,463.152 1002.134,461.519 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="1006.245,445.315 1035.209,440.349 1028.169,432.805 999.229,437.873 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="1005.981,446.122 1037.708,439.896 1029.064,430.989 1028.301,431.39 1036.264,439.581 
				1006.205,445.159 999.229,437.873 998.317,438.115 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="33">
								<g>
									<g>
										<g>
											<polygon style="fill:#9DC8E7;" points="932.303,407.832 939.259,405.525 939.047,390.865 932.224,392.934 				" />
										</g>
										<g>
											<polygon style="fill:#1F3E60;" points="938.32,390.682 926.397,382.67 872.181,397.98 886.703,410.425 934.261,395.788 
					931.924,392.669 				" />
											<path style="fill:#00A0E9;" d="M871.518,397.766l54.995-15.532l12.521,8.631l-6.472,2.011l2.339,3.12l-48.338,14.876
					L871.518,397.766z M931.285,392.463l6.318-1.964l-11.322-7.393l-53.437,15.085l13.999,11.784l46.779-14.396L931.285,392.463z" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="886.989,426.757 934.966,410.447 934.753,396.032 886.43,410.918 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="909.476,408.768 915.383,407.127 915.383,402.98 909.472,404.825 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="898.164,412.369 905.577,409.948 905.595,405.99 898.183,408.411 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="930.685,402.424 931.888,402.005 931.976,398.791 930.775,399.121 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="927.553,403.342 928.756,402.9 928.844,399.731 927.641,400.06 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="930.963,410.046 932.166,409.627 932.254,406.412 931.052,406.741 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="927.831,410.963 929.034,410.521 929.122,407.352 927.92,407.681 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="886.765,415.927 894.199,413.495 894.086,409.8 886.543,412.03 				" />
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="897.144,423.32 907.342,419.861 907.405,415.939 897.205,419.397 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="894.094,420.052 912.491,414.374 908.598,412.534 892.648,417.554 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="935.64,402.911 938.507,401.959 937.881,401.694 935.396,402.525 				" />
										</g>
										<g>
											<polygon style="fill:#32A2D5;" points="887.151,426.748 886.588,410.953 871.542,397.743 872.949,413.029 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="935.918,406.684 938.079,405.922 938.047,402.262 935.889,402.979 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#005A84;" points="933.642,395.552 909.745,391.032 873.471,401.548 886.854,409.868 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="937.544,390.506 919.906,388.323 913.13,390.168 931.141,392.545 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="872.876,398.204 873.279,401.374 909.236,390.872 933.594,395.544 931.33,392.532 
			912.752,390.296 919.528,388.451 937.592,390.507 926.281,383.104 		" />
								</g>
							</g>
							<g class="content show" id="34">
								<g>
									<g>
										<polygon style="fill:#32A2D5;" points="944.432,494.285 943.868,480.242 932.616,467.066 933.368,482.055 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="944.432,494.285 1006.716,474.25 1007.039,460.445 943.799,479.801 			" />
									</g>
									<g>
										<polygon style="fill:#1F3E60;" points="1006.411,460.527 995.633,452.62 989.626,454.504 986.876,451.147 933.197,467.268 
				943.928,479.738 			" />
										<path style="fill:#00A0E9;" d="M932.594,467.059l54.175-16.269l0.239-0.072l2.748,3.353l5.991-1.877l11.343,8.517l-63.05,19.385
				l-0.261,0.08L932.594,467.059z M995.519,453.049l-6.02,1.888l-2.753-3.357L933.8,467.479l10.281,11.824l61.653-18.958
				L995.519,453.049z" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="976.336,475.546 985.242,472.59 985.303,468.841 976.395,471.796 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="962.741,479.803 971.649,476.848 971.708,473.098 962.8,476.054 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="996.903,469.09 998.348,468.686 998.408,464.868 996.964,465.272 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="993.138,470.184 994.584,469.781 994.645,465.963 993.199,466.368 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="997.311,476.022 998.757,475.617 998.818,471.801 997.371,472.205 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="993.748,477.144 995.193,476.741 995.253,472.924 993.808,473.328 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="949.04,484.188 957.974,481.312 957.742,477.879 948.717,480.617 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="976.227,482.394 985.135,479.437 985.194,475.688 976.286,478.644 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="962.629,486.864 971.536,483.909 971.596,480.158 962.688,483.115 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="948.928,491.249 957.861,488.373 957.629,484.94 948.969,487.683 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#005A84;" points="1005.732,460.356 992.362,459.872 986.197,460.405 979.798,459.673 937.622,472.729 
			943.995,479.317 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="933.825,467.552 937.89,473.251 980.461,460.006 986.703,460.723 992.712,460.2 
			1005.732,460.356 995.48,453.012 989.478,454.944 986.715,451.578 		" />
								</g>
							</g>
							<g class="content show" id="35">
								<g>
									<g>
										<g>
											<polygon style="fill:#9DC8E7;" points="1035.124,511.745 1042.08,509.438 1041.869,494.778 1035.045,496.847 				" />
										</g>
										<g>
											<polygon style="fill:#1F3E60;" points="1041.14,494.596 1029.218,486.583 975.002,501.893 989.524,514.338 1037.082,499.701 
					1034.745,496.582 				" />
											<path style="fill:#00A0E9;" d="M974.339,501.679l54.995-15.532l12.521,8.631l-6.472,2.011l2.339,3.12l-48.338,14.876
					L974.339,501.679z M1034.105,496.376l6.318-1.964l-11.322-7.393l-53.437,15.085l13.999,11.784l46.779-14.396L1034.105,496.376z" />
										</g>
										<g>
											<polygon style="fill:#9DC8E7;" points="989.81,530.672 1037.786,514.36 1037.573,499.945 989.251,514.831 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1012.297,512.681 1018.203,511.04 1018.203,506.894 1012.293,508.738 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1000.984,516.282 1008.398,513.862 1008.415,509.904 1001.004,512.324 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1033.506,506.337 1034.709,505.918 1034.797,502.704 1033.595,503.034 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1030.374,507.255 1031.577,506.814 1031.665,503.645 1030.462,503.975 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1033.784,513.959 1034.987,513.54 1035.075,510.325 1033.873,510.656 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1030.652,514.877 1031.855,514.435 1031.942,511.265 1030.741,511.596 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="989.586,519.84 997.019,517.408 996.907,513.714 989.364,515.944 				" />
										</g>
										<g>
											<polygon style="fill:#005A84;" points="999.965,527.233 1010.163,523.775 1010.224,519.852 1000.026,523.31 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="996.915,523.965 1015.311,518.287 1011.419,516.447 995.469,521.467 				" />
										</g>
										<g>
											<polygon style="fill:#002E4B;" points="1038.461,506.824 1041.328,505.872 1040.701,505.608 1038.216,506.438 				" />
										</g>
										<g>
											<polygon style="fill:#32A2D5;" points="989.972,530.662 989.407,514.868 974.362,501.656 975.771,516.942 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#005A84;" points="1038.739,510.597 1040.898,509.835 1040.868,506.175 1038.709,506.892 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#005A84;" points="1036.464,499.465 1012.566,494.946 976.291,505.462 989.675,513.781 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="1040.365,494.42 1022.726,492.236 1015.951,494.082 1033.962,496.458 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="975.697,502.117 976.098,505.288 1012.056,494.785 1036.415,499.457 1034.151,496.445 
			1015.572,494.209 1022.349,492.364 1040.413,494.42 1029.102,487.017 		" />
								</g>
							</g>
							<g class="content show" id="36">
								<g>
									<polygon style="fill:#9DC8E7;" points="981.92,501.382 981.905,504.047 964.916,506.341 962.901,503.84 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="976.052,500.87 982.045,502.828 982.083,502.1 976.031,500.186 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="986.809,508.352 987.415,508.329 987.314,505.69 986.712,505.819 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="981.905,504.047 986.821,508.68 986.713,505.848 981.916,501.293 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="967.122,513.209 987,508.413 981.907,503.509 963.331,506.139 		" />
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="1035.5,547.626 1035.277,563.892 1015.129,570.31 1014.693,551.435 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1017.938,556.161 1024.339,554.561 1024.223,552.99 1017.895,554.51 		" />
								</g>
								<g>
									<polygon style="fill:#002E4B;" points="1026.824,554.319 1033.225,552.719 1033.109,551.148 1026.779,552.668 		" />
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1014.696,551.427 1015.126,570.31 963.411,521.328 962.325,503.578 966.254,508.519 
			966.465,511.825 1009.022,548.278 1009.012,550.024 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="987.402,508.005 986.683,508.01 1028.924,542.946 1029.735,542.992 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#002E4B;" points="971.485,518.089 971.081,518.449 972.833,520.113 973.231,519.682 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="973.934,520.37 973.53,520.73 975.283,522.394 975.681,521.964 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="976.422,522.571 976.018,522.93 977.77,524.595 978.168,524.164 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="978.939,524.771 978.535,525.13 980.288,526.795 980.687,526.364 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="981.248,526.984 980.844,527.344 982.597,529.008 982.995,528.578 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="983.963,529.444 983.559,529.805 985.31,531.469 985.709,531.037 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="986.587,531.875 986.184,532.236 987.935,533.899 988.334,533.469 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="989.343,534.376 988.939,534.737 990.692,536.402 991.089,535.97 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="992.011,536.733 991.608,537.094 993.36,538.758 993.758,538.328 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="994.654,539.114 994.251,539.475 996.003,541.138 996.401,540.707 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="997.147,541.477 996.745,541.837 998.496,543.501 998.895,543.069 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="999.816,543.969 999.413,544.328 1001.165,545.993 1001.564,545.562 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1002.66,546.614 1002.256,546.974 1004.009,548.638 1004.406,548.207 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1005.497,549.188 1005.094,549.548 1006.846,551.213 1007.244,550.782 			" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#002E4B;" points="970.329,519.839 969.925,520.199 971.678,521.863 972.075,521.433 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="972.778,522.121 972.374,522.481 974.127,524.146 974.525,523.714 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="975.266,524.321 974.862,524.682 976.615,526.345 977.012,525.915 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="977.783,526.521 977.379,526.882 979.132,528.545 979.53,528.114 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="980.092,528.734 979.688,529.094 981.441,530.759 981.839,530.328 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="982.807,531.195 982.403,531.555 984.156,533.219 984.553,532.789 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="985.431,533.626 985.027,533.986 986.78,535.651 987.177,535.219 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="988.187,536.128 987.783,536.487 989.536,538.152 989.934,537.721 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="990.856,538.484 990.452,538.844 992.204,540.509 992.602,540.078 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="993.499,540.864 993.095,541.225 994.846,542.888 995.245,542.458 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="995.992,543.227 995.589,543.587 997.34,545.251 997.739,544.821 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="998.66,545.719 998.256,546.079 1000.009,547.743 1000.408,547.313 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1001.504,548.364 1001.1,548.725 1002.853,550.389 1003.251,549.959 			" />
									</g>
									<g>
										<polygon style="fill:#002E4B;" points="1004.342,550.939 1003.938,551.3 1005.69,552.963 1006.088,552.532 			" />
									</g>
									<polygon style="fill:#002E4B;" points="969.005,521.184 968.601,521.545 970.354,523.208 970.751,522.777 		" />
									<polygon style="fill:#002E4B;" points="971.455,523.465 971.051,523.826 972.802,525.489 973.201,525.059 		" />
									<polygon style="fill:#002E4B;" points="973.942,525.666 973.538,526.027 975.291,527.69 975.689,527.259 		" />
									<polygon style="fill:#002E4B;" points="976.459,527.866 976.055,528.225 977.808,529.89 978.206,529.459 		" />
									<polygon style="fill:#002E4B;" points="978.767,530.079 978.365,530.44 980.116,532.103 980.515,531.673 		" />
									<polygon style="fill:#002E4B;" points="981.482,532.54 981.079,532.9 982.831,534.564 983.229,534.133 		" />
									<g>
										<polygon style="fill:#002E4B;" points="984.107,534.97 983.703,535.331 985.456,536.994 985.855,536.564 			" />
									</g>
									<polygon style="fill:#002E4B;" points="986.863,537.472 986.459,537.832 988.212,539.497 988.61,539.066 		" />
									<polygon style="fill:#002E4B;" points="989.532,539.829 989.128,540.189 990.879,541.853 991.278,541.423 		" />
									<polygon style="fill:#002E4B;" points="992.175,542.209 991.771,542.57 993.522,544.234 993.921,543.802 		" />
									<g>
										<polygon style="fill:#002E4B;" points="994.668,544.572 994.264,544.932 996.016,546.596 996.414,546.165 			" />
									</g>
									<polygon style="fill:#002E4B;" points="997.336,547.064 996.932,547.423 998.685,549.088 999.084,548.657 		" />
									<polygon style="fill:#002E4B;" points="1000.18,549.71 999.776,550.07 1001.529,551.734 1001.927,551.302 		" />
									<g>
										<polygon style="fill:#002E4B;" points="1003.018,552.283 1002.614,552.643 1004.366,554.308 1004.764,553.877 			" />
									</g>
									<polygon style="fill:#002E4B;" points="967.528,522.551 967.125,522.912 968.877,524.577 969.276,524.146 		" />
									<polygon style="fill:#002E4B;" points="969.979,524.834 969.575,525.194 971.328,526.858 971.725,526.427 		" />
									<polygon style="fill:#002E4B;" points="972.466,527.034 972.062,527.394 973.815,529.059 974.213,528.628 		" />
									<polygon style="fill:#002E4B;" points="974.984,529.233 974.58,529.594 976.332,531.258 976.73,530.828 		" />
									<polygon style="fill:#002E4B;" points="977.293,531.448 976.889,531.807 978.642,533.472 979.039,533.04 		" />
									<polygon style="fill:#002E4B;" points="980.006,533.907 979.602,534.268 981.355,535.932 981.754,535.501 		" />
									<g>
										<polygon style="fill:#002E4B;" points="982.631,536.339 982.228,536.699 983.98,538.363 984.378,537.932 			" />
									</g>
									<polygon style="fill:#002E4B;" points="985.388,538.84 984.984,539.201 986.736,540.864 987.134,540.433 		" />
									<polygon style="fill:#002E4B;" points="988.056,541.197 987.652,541.558 989.405,543.221 989.803,542.79 		" />
									<polygon style="fill:#002E4B;" points="990.699,543.577 990.295,543.937 992.048,545.602 992.446,545.171 		" />
									<g>
										<polygon style="fill:#002E4B;" points="993.192,545.939 992.788,546.3 994.541,547.965 994.94,547.533 			" />
									</g>
									<polygon style="fill:#002E4B;" points="995.861,548.431 995.457,548.792 997.209,550.455 997.607,550.025 		" />
									<polygon style="fill:#002E4B;" points="998.705,551.077 998.301,551.437 1000.052,553.102 1000.451,552.671 		" />
									<g>
										<polygon style="fill:#002E4B;" points="1001.542,553.652 1001.138,554.012 1002.891,555.676 1003.289,555.246 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#32A2D5;" points="1029.224,544.373 1032.912,548.093 1034.517,547.436 1029.35,542.018 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="1029.381,542.972 986.717,508.001 976.267,503.146 976.018,507.571 1018.489,542.58 		" />
								</g>
								<g>
									<polygon style="fill:#00486C;" points="1029.381,542.972 1018.953,538.374 1011.165,545.993 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="966.446,511.855 1013.631,554.167 1022.056,544.913 975.763,507.32 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="982.067,500.749 962.324,503.567 967.092,509.608 967.595,509.345 963.274,503.774 
				981.92,501.382 986.713,505.848 987.314,505.69 			" />
									</g>
								</g>
								<g>
									<polygon style="fill:#9DC8E7;" points="966.465,511.825 967.067,511.299 966.859,508.409 966.257,508.538 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="964.293,505.094 971.055,502.694 971.039,501.959 963.675,504.566 		" />
								</g>
								<g>
									<polygon style="fill:#0A2644;" points="1025.64,548.848 1026.779,548.419 1026.769,546.993 1025.628,547.423 		" />
								</g>
								<g>
									<polygon style="fill:#00A0E9;" points="972.814,501.669 973.955,501.239 973.943,499.814 972.804,500.243 		" />
								</g>
								<g>
									<polygon style="fill:#FFFFFF;" points="1012.465,564.812 1013.744,566.32 1013.253,562.954 1012.091,561.879 		" />
								</g>
								<g>
									<polygon style="fill:#004669;" points="1014.797,551.213 1033.862,547.944 1029.228,542.978 1010.178,546.313 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#00A0E9;" points="1014.623,551.743 1035.508,547.646 1029.818,541.782 1029.316,542.047 1034.557,547.439 
				1014.771,551.11 1010.178,546.313 1009.578,546.474 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="37">
								<polygon style="fill:#005A84;" points="1262.887,388.489 1311.275,370.894 1332.253,390.857 1283.867,406.761 	" />
								<polygon style="fill:#9DC8E7;" points="1283.867,406.761 1283.254,410.658 1331.393,394.436 1332.253,390.857 	" />
								<polygon style="fill:#9DC8E7;" points="1283.254,410.658 1262.348,392.84 1262.887,388.489 1283.867,406.761 	" />
							</g>
							<g class="content show" id="38">
								<polygon style="fill:#005A84;" points="1151.446,533.484 1151.944,527.955 1216.477,512.477 1220.542,516.462 	" />
								<polygon style="fill:#005A84;" points="1151.944,527.955 1152.726,522.138 1213.756,508.443 1216.477,512.477 	" />
								<g>
									<g>
										<polygon style="fill:#32A2D5;" points="1186.483,502.518 1187.115,511.617 1199.009,524.312 1206.037,518.234 			" />
										<polygon style="fill:#32A2D5;" points="1137.393,495.347 1137.393,502.889 1128.51,505.62 1127.798,498.377 			" />
										<polygon style="fill:#32A2D5;" points="1105.092,484.591 1103.65,495.038 1151.944,541.591 1151.944,527.955 			" />
										<polygon style="fill:#005A84;" points="1105.092,484.591 1112.007,482.762 1152.726,522.138 1153.526,529.877 			" />
										<polygon style="fill:#9DC8E7;" points="1151.944,541.591 1221.024,520.147 1220.542,516.462 1151.446,533.484 			" />
										<polygon style="fill:#005A84;" points="1142.875,468.137 1163.418,487.07 1190.629,479.42 1170.205,459.478 			" />
										<polygon style="fill:none;stroke:#00A0E9;stroke-width:0.5223;stroke-miterlimit:10;" points="1142.094,467.888 
				1163.237,488.043 1190.629,479.42 1170.205,459.478 			" />
										<polygon style="fill:#004669;" points="1166.158,464.344 1163.44,487.474 1189.205,479.093 			" />
										<polygon style="fill:#32A2D5;" points="1143.27,470.769 1143.27,475.696 1112.007,482.762 1129.636,499.814 1163.237,490.232 			
				" />
										<polygon style="fill:#9DC8E7;" points="1163.237,488.043 1163.237,505.199 1189.407,497.716 1189.407,480.457 			" />
										<polygon style="fill:#32A2D5;" points="1163.237,505.199 1141.073,484.98 1140.665,467.649 1163.237,488.043 			" />
										<polygon style="fill:none;stroke:#00A0E9;stroke-width:1.0445;stroke-miterlimit:10;" points="1220.063,511.617 
				1206.006,497.027 1186.483,502.518 1199.865,516.462 			" />
										<polygon style="fill:#005A84;" points="1220.063,511.617 1206.006,497.027 1186.483,502.518 1199.865,516.462 			" />
										<polygon style="fill:#002E4B;" points="1178.555,491.654 1178.975,500.574 1184.419,499.143 1183.661,490.603 			" />
										<polygon style="fill:#005A84;" points="1119.626,490.13 1128.36,487.391 1137.393,495.347 1127.798,498.377 			" />
									</g>
								</g>
							</g>
							<g class="content show" id="39">
								<g>
									<polygon style="fill:#00486C;" points="1057.641,488.043 1070.855,485.764 1137.892,554.135 1153.029,548.351 1174.173,567.419 
			1146.117,575.742 		" />
									<polygon style="fill:#32A2D5;" points="1057.641,488.043 1057.641,494.59 1145.795,583.896 1146.117,575.742 		" />
									<polygon style="fill:#9DC8E7;" points="1174.173,567.419 1173.573,575.742 1145.795,583.896 1146.117,575.742 		" />
								</g>
								<polygon style="fill:#002E4B;" points="1104.523,542.084 1104.523,539.203 1106.589,539.203 1106.589,544.179 	" />
							</g>
							<g class="content show" id="40">
								<g>
									<g>
										<g>
											<g>
												<polygon style="fill:#32A2D5;" points="472.599,594.314 471.888,597.259 451.765,603.636 450.186,600.346 					" />
											</g>
											<g>
												<polygon style="fill:#002E4B;" points="463.814,598.26 471.818,596.126 472.05,595.333 463.969,597.498 					" />
											</g>
											<g>
												<polygon style="fill:#32A2D5;" points="461.226,599.544 466.661,604.963 466.63,601.743 461.327,596.416 					" />
											</g>
											<g>
												<polygon style="fill:#005A84;" points="454.837,615.004 476.389,603.414 472.031,596.663 450.139,603.319 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="476.22,603.153 472.135,624.039 453.851,628.207 455.352,609.531 					" />
											</g>
											<g>
												<g>
													<polygon style="fill:#00A0E9;" points="472.927,593.652 449.62,599.894 452.33,606.096 452.955,605.933 450.618,600.371 
							472.599,594.314 476.032,600.523 476.738,600.503 						" />
												</g>
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="451.736,609.63 452.538,609.205 453.06,605.954 452.36,605.941 					" />
											</g>
											<g>
												<polygon style="fill:#002E4B;" points="452.148,601.985 459.333,599.667 459.508,598.849 451.602,601.24 					" />
											</g>
											<polygon style="fill:#32A2D5;" points="449.62,599.894 448.265,620.686 453.865,628.209 455.006,613.212 452.039,608.043 
					452.369,605.78 				" />
											<g>
												<polygon style="fill:#32A2D5;" points="470.773,637.203 492.551,633.735 473.001,608.752 454.707,615.712 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="492.646,633.456 491.907,645.868 473.198,653.448 472.605,637.036 					" />
											</g>
											<polygon style="fill:#32A2D5;" points="454.707,615.712 453.851,628.207 473.198,653.448 474.4,642.201 				" />
										</g>
										<g>
											<polygon style="fill:#005A84;" points="454.135,620.442 453.554,622.825 455.447,625.545 456.136,623.259 				" />
										</g>
										<g>
											<polygon style="fill:#005A84;" points="457.375,622.339 456.795,624.722 458.687,627.442 459.376,625.156 				" />
										</g>
										<g>
											<polygon style="fill:#005A84;" points="461.821,628.646 461.241,631.029 463.134,633.75 463.822,631.463 				" />
										</g>
									</g>
									<g>
										<polygon style="fill:#B2B2B2;" points="510.39,662.223 511.013,663.19 511.486,657.621 510.795,657.291 			" />
									</g>
									<g>
										<polygon style="fill:#9DC8E7;" points="522.012,665.081 519.815,682.882 495.28,689.884 497.029,671.574 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="499.825,674.256 507.273,672.136 507.329,670.347 499.973,672.385 			" />
									</g>
									<g>
										<polygon style="fill:#00486C;" points="516.789,660.842 491.672,630.823 482.7,628.186 482.7,628.186 504.526,659.092 			" />
									</g>
									<g>
										<polygon style="fill:#004669;" points="515.658,659.087 503.409,657.326 492.691,665.204 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="469.769,636.748 493.846,666.954 505.035,659.771 482.7,628.186 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="509.707,669.964 511.048,669.616 511.206,668.004 509.866,668.353 			" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="497.172,671.335 520.124,665.242 515.484,659.074 492.54,665.244 			" />
									</g>
									<g>
										<g>
											<polygon style="fill:#32A2D5;" points="496.914,671.883 522.021,665.074 516.296,657.765 515.696,658.004 520.973,664.725 
					497.156,671.186 492.176,664.476 491.435,664.593 				" />
										</g>
									</g>
									<polygon style="fill:#32A2D5;" points="491.098,664.171 489.896,681.877 495.478,690.182 497.233,671.862 		" />
								</g>
								<g>
									<polygon style="fill:#005A84;" points="512.807,671.919 520.255,669.8 520.312,668.011 512.956,670.048 		" />
								</g>
							</g>
							<g class="content show" id="41">
								<g>
									<g>
										<g>
											<g>
												<polygon style="fill:#005A84;" points="688.075,534.26 655.722,546.74 663.249,555.992 670.466,555.162 710.589,607.794 
						705.216,608.34 710.685,615.443 744.641,605.356 					" />
												<path style="fill:#00A0E9;" d="M745.397,605.68l-34.889,10.172l-6.028-7.829l5.368-0.545l-39.555-51.903l-7.212,0.828
						l-8.1-9.957l33.261-12.604L745.397,605.68z M663.416,555.58l7.222-0.83l40.69,53.361l-5.376,0.545l4.911,6.378l33.021-10.003
						l-55.976-70.356l-31.444,12.357L663.416,555.58z" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="707.697,633.683 741.817,623.188 745.409,605.472 711.083,615.54 					" />
											</g>
											<polygon style="fill:#32A2D5;" points="654.98,546.448 652.923,563.39 659.889,571.4 666.964,566.185 699.778,614.468 
					698.204,623.186 707.697,633.683 711.083,615.54 704.758,608.015 709.847,607.478 670.639,554.75 662.762,556.593 				" />
										</g>
										<g>
											<g>
												<polygon style="fill:#004669;" points="729.961,595.103 712.676,599.011 716.44,588.446 					" />
											</g>
											<g>
												<polygon style="fill:#005A84;" points="712.693,598.34 716.44,588.446 684.813,549.512 672.835,549.63 					" />
											</g>
											<g>
												<polygon style="fill:#004669;" points="690.07,545.757 684.464,549.445 673.14,550.005 					" />
											</g>
											<g>
												<polygon style="fill:#004669;" points="712.589,599.081 712.763,598.942 673.227,549.934 673.053,550.074 					" />
											</g>
											<g>

												<rect x="712.478" y="596.941" transform="matrix(-0.9754 0.2205 -0.2205 -0.9754 1556.5731 1020.3563)" style="fill:#365B8A;"
												 width="17.722" height="0.223" />
											</g>
											<g>
												<polygon style="fill:#004669;" points="673.168,550.112 690.329,545.712 690.274,545.496 673.112,549.896 					" />
											</g>
											<g>
												<polygon style="fill:#00486C;" points="729.961,595.103 716.44,588.446 684.464,549.445 690.302,545.605 					" />
											</g>
										</g>
										<g>
											<polygon style="fill:#005A84;" points="671.716,559.724 669.903,567.042 673.099,570.929 674.721,563.594 				" />
										</g>
										<polygon style="fill:#005A84;" points="680.837,584.44 683.36,574.403 688.093,580.738 685.567,590.903 			" />
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#005A84;" points="656.999,560.209 667.701,557.429 663.477,553.648 653.066,556.15 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="655.822,584.31 665.716,581.02 667.701,557.429 657.188,560.188 					" />
											</g>
											<g>
												<polygon style="fill:#32A2D5;" points="651.73,579.736 655.821,584.343 657.187,560.22 653.069,556.099 					" />
											</g>
										</g>
										<g>
											<g>
												<path style="fill:#00486C;" d="M658.589,559.322c0.107,0.007,0.201-0.076,0.207-0.185l0.236-3.717l5.856-1.666
						c0.067-0.02,0.118-0.072,0.136-0.139c0.018-0.067-0.001-0.139-0.05-0.187l-1.894-1.932c-0.05-0.051-0.123-0.072-0.191-0.052
						l-6.461,1.738c-0.083,0.022-0.142,0.096-0.146,0.182l-0.121,2.916c-0.004,0.108,0.079,0.198,0.185,0.205h0.003
						c0.109,0.004,0.199-0.08,0.205-0.189l0.115-2.771l6.21-1.67l1.582,1.615l-5.671,1.614c-0.08,0.024-0.136,0.094-0.143,0.177
						l-0.244,3.856C658.397,559.222,658.479,559.315,658.589,559.322L658.589,559.322z" />
											</g>
										</g>
										<g>
											<path style="fill:#00486C;" d="M664.598,557.722c0.109,0.007,0.202-0.077,0.207-0.185l0.216-3.816
					c0.007-0.109-0.077-0.202-0.185-0.207c-0.109-0.007-0.202,0.077-0.207,0.185l-0.216,3.816
					C664.408,557.623,664.49,557.716,664.598,557.722z" />
										</g>
										<g>
											<path style="fill:#00486C;" d="M662.695,555.954c0.109,0.007,0.202-0.077,0.207-0.185l0.227-3.997
					c0.007-0.109-0.077-0.202-0.185-0.207c-0.109-0.007-0.202,0.077-0.207,0.185l-0.227,3.997
					C662.504,555.854,662.588,555.947,662.695,555.954z" />
										</g>
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#005A84;" points="700.488,612.691 711.188,609.911 706.963,606.13 696.554,608.633 					" />
											</g>
											<g>
												<polygon style="fill:#9DC8E7;" points="699.31,636.791 709.204,633.502 711.188,609.911 700.676,612.67 					" />
											</g>
											<g>
												<polygon style="fill:#32A2D5;" points="695.218,632.217 699.308,636.824 700.674,612.702 696.557,608.581 					" />
											</g>
										</g>
										<g>
											<g>
												<path style="fill:#00486C;" d="M702.077,611.802c0.107,0.007,0.201-0.076,0.207-0.185l0.236-3.717l5.856-1.666
						c0.067-0.02,0.118-0.072,0.136-0.139s-0.001-0.139-0.05-0.188l-1.893-1.932c-0.05-0.051-0.123-0.072-0.191-0.052l-6.46,1.738
						c-0.083,0.022-0.142,0.096-0.146,0.182l-0.121,2.916c-0.004,0.107,0.079,0.198,0.185,0.205h0.003
						c0.109,0.004,0.201-0.08,0.205-0.189l0.115-2.771l6.21-1.67l1.582,1.615l-5.671,1.614c-0.08,0.024-0.136,0.094-0.143,0.177
						l-0.244,3.856C701.884,611.704,701.987,611.784,702.077,611.802z" />
											</g>
										</g>
										<g>
											<path style="fill:#00486C;" d="M708.085,610.203c0.109,0.007,0.202-0.077,0.207-0.185l0.216-3.816
					c0.007-0.109-0.077-0.202-0.185-0.207c-0.109-0.007-0.202,0.077-0.207,0.185l-0.216,3.816
					C707.894,610.105,707.978,610.198,708.085,610.203z" />
										</g>
										<g>
											<path style="fill:#00486C;" d="M706.183,608.435c0.109,0.007,0.202-0.077,0.207-0.185l0.227-3.997
					c0.007-0.109-0.077-0.202-0.185-0.207c-0.109-0.007-0.202,0.077-0.207,0.185l-0.227,3.997
					C705.992,608.336,706.074,608.429,706.183,608.435z" />
										</g>
									</g>
								</g>
								<g>
									<g>
										<g>
											<polygon style="fill:#005A84;" points="695.475,591.329 693.662,598.647 696.858,602.535 698.48,595.199 				" />
										</g>
									</g>
								</g>
							</g>
							<g class="content show" id="42">
								<g>
									<polygon style="fill:#32A2D5;" points="710.933,614.407 710.933,635.311 733.278,690.814 733.278,669.189 		" />
									<polygon style="fill:#9DC8E7;" points="733.278,669.189 768.599,669.189 768.599,688.171 733.278,690.814 		" />
									<polygon style="fill:#005A84;" points="710.933,613.446 733.278,614.407 748.896,659.338 733.278,669.189 		" />
									<polygon style="fill:#004669;" points="748.896,659.338 733.278,669.189 768.599,669.189 		" />
									<polygon style="fill:#004669;" points="710.933,613.446 746.974,607.2 733.278,614.407 		" />
									<polygon style="fill:#00486C;" points="746.974,607.2 733.278,614.407 748.896,659.338 768.599,669.189 		" />
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="718.434,652.85 719.733,656.305 720.9,652.38 719.601,648.924 			" />
										<path style="fill:#005A84;" d="M718.393,652.86l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.205,4.056L718.393,652.86z
				 M719.606,649.055l-1.129,3.795l1.249,3.323l1.127-3.795L719.606,649.055z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="716.426,647.394 717.725,650.849 718.892,646.923 717.592,643.468 			" />
										<path style="fill:#005A84;" d="M716.385,647.403l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.205,4.056L716.385,647.403
				z M717.596,643.599l-1.127,3.795l1.249,3.323l1.127-3.795L717.596,643.599z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="714.673,642.196 715.971,645.651 717.138,641.725 715.838,638.27 			" />
										<path style="fill:#005A84;" d="M714.632,642.205l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.206,4.056L714.632,642.205
				z M715.844,638.401l-1.127,3.795l1.249,3.323l1.129-3.795L715.844,638.401z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="712.257,636.489 713.555,639.945 714.723,636.019 713.424,632.564 			" />
										<path style="fill:#005A84;" d="M712.216,636.5l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.206,4.056L712.216,636.5z
				 M713.429,632.695l-1.129,3.795l1.249,3.323l1.129-3.795L713.429,632.695z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="720.751,647.237 722.05,650.691 723.215,646.766 721.917,643.31 			" />
										<path style="fill:#005A84;" d="M720.71,647.246l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.205,4.056L720.71,647.246z
				 M721.923,643.442l-1.129,3.795l1.249,3.323l1.127-3.795L721.923,643.442z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="718.742,641.781 720.041,645.235 721.208,641.31 719.909,637.854 			" />
										<path style="fill:#005A84;" d="M718.703,641.79l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.205,4.056L718.703,641.79z
				 M719.914,637.985l-1.127,3.795l1.249,3.323l1.127-3.795L719.914,637.985z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="716.988,636.581 718.287,640.036 719.454,636.111 718.155,632.656 			" />
										<path style="fill:#005A84;" d="M716.949,636.592l-0.004-0.01l1.205-4.056l1.345,3.576l0.004,0.009l-1.206,4.056L716.949,636.592z
				 M718.16,632.787l-1.127,3.795l1.249,3.323l1.129-3.795L718.16,632.787z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="714.573,630.876 715.871,634.33 717.038,630.405 715.74,626.951 			" />
										<path style="fill:#005A84;" d="M714.533,630.885l-0.004-0.01l1.206-4.056l1.344,3.576l0.004,0.009l-1.206,4.056L714.533,630.885z
				 M715.745,627.082l-1.129,3.795l1.249,3.323l1.129-3.795L715.745,627.082z" />
									</g>
								</g>
								<g>
									<g>
										<polygon style="fill:#005A84;" points="726.928,674.114 728.227,677.569 729.394,673.644 728.096,670.188 			" />
										<path style="fill:#005A84;" d="M726.889,674.124l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.205,4.056L726.889,674.124
				z M728.1,670.319l-1.129,3.795l1.249,3.323l1.127-3.795L728.1,670.319z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="724.921,668.658 726.219,672.113 727.385,668.188 726.086,664.732 			" />
										<path style="fill:#005A84;" d="M724.88,668.667l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.205,4.056L724.88,668.667z
				 M726.092,664.863l-1.127,3.795l1.249,3.323l1.127-3.795L726.092,664.863z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="723.167,663.46 724.465,666.915 725.633,662.99 724.334,659.534 			" />
										<path style="fill:#005A84;" d="M723.126,663.469l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.206,4.056L723.126,663.469
				z M724.337,659.665l-1.127,3.795l1.249,3.323l1.129-3.795L724.337,659.665z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="720.751,657.753 722.049,661.209 723.217,657.284 721.917,653.828 			" />
										<path style="fill:#005A84;" d="M720.71,657.764l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.206,4.056L720.71,657.764z
				 M721.923,653.959l-1.129,3.795l1.249,3.323l1.129-3.795L721.923,653.959z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="729.244,668.501 730.544,671.955 731.71,668.03 730.411,664.574 			" />
										<path style="fill:#005A84;" d="M729.205,668.51l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.01l-1.205,4.056L729.205,668.51z
				 M730.416,664.706l-1.129,3.795l1.249,3.323l1.127-3.795L730.416,664.706z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="727.236,663.045 728.535,666.499 729.702,662.574 728.403,659.12 			" />
										<path style="fill:#005A84;" d="M727.197,663.054l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.205,4.056L727.197,663.054
				z M728.408,659.249l-1.127,3.795l1.249,3.323l1.127-3.795L728.408,659.249z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="725.483,657.845 726.781,661.301 727.949,657.375 726.65,653.92 			" />
										<path style="fill:#005A84;" d="M725.443,657.856l-0.004-0.009l1.205-4.056l1.345,3.576l0.004,0.009l-1.206,4.056L725.443,657.856
				z M726.655,654.051l-1.127,3.795l1.249,3.323l1.129-3.795L726.655,654.051z" />
									</g>
									<g>
										<polygon style="fill:#005A84;" points="723.067,652.14 724.366,655.594 725.533,651.669 724.234,648.215 			" />
										<path style="fill:#005A84;" d="M723.028,652.149l-0.004-0.009l1.206-4.056l1.344,3.576l0.004,0.009l-1.206,4.056L723.028,652.149
				z M724.239,648.346l-1.129,3.795l1.249,3.323l1.129-3.795L724.239,648.346z" />
									</g>
								</g>
							</g>
						</svg>



					</div>
					<div class="center hide cls-second">
						<svg version="1.1" id="svg2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px"
						 y="0px" width="800px" height="420px" viewBox="0 0 800 420" style="enable-background:new 0 0 800 420;" xml:space="preserve">
							<style type="text/css">
								.st0 {
									fill: #707172;
								}

								.st1 {
									fill: #A0977F;
								}

								.st2 {
									fill: none;
								}

								.st3 {
									fill: #07152D;
								}

								.st4 {
									fill: #2C3848;
								}

								.st5 {
									fill: #040000;
								}

								.st6 {
									fill: #2E3946;
								}

								.st7 {
									fill: #867E80;
								}

								.st8 {
									fill: #FCF3F5;
								}

								.st9 {
									fill: #FCF3F7;
								}

								.st10 {
									fill: #807B7C;
								}

								.st11 {
									fill: #EAE9E9;
								}

								.st12 {
									fill: #999899;
								}

								.st13 {
									fill: #A2A3A0;
								}

								.st14 {
									fill: #FFFFFF;
								}

								.st15 {
									fill: #B3B3B3;
								}

								.st16 {
									fill: none;
									stroke: #FFFFFF;
									stroke-miterlimit: 10;
								}

								.st17 {
									fill: #2E3C4A;
								}

								.st18 {
									fill: #978D78;
								}

								.st19 {
									fill: none;
									stroke: #707172;
									stroke-width: 0.25;
									stroke-miterlimit: 10;
								}

								.st20 {
									fill: #707071;
								}

								.st21 {
									fill: #BABABA;
								}

								.st22 {
									fill: #727071;
								}
							</style>
							<g class="content1 show1" id="svg2-1">
								<g>
									<polygon class="st0" points="634.9,236.6 634.2,268.8 623.2,280.5 603.7,273.3 596.6,225.4 		" />
									<polygon class="st0" points="596.6,225.4 593.9,207.2 635.5,207.4 634.9,236.6 		" />
									<polygon class="st0" points="593.9,207.2 589.9,180.4 593.6,127.8 638.7,139.8 628.7,158.8 628.5,169 636.5,157.3 635.5,207.4 		
							 " />
									<polygon class="st1" points="600.1,237.9 626.5,247.8 624.3,264.9 601.9,257.7 		" />
									<line class="st2" x1="622.3" y1="206.4" x2="630.8" y2="206.4" />
									<polygon class="st1" points="628.5,169 628.7,158.8 625.3,158.2 624.3,169 624.2,193.1 619.3,206.4 628.7,206.4 626.4,169 		" />
									<polygon class="st1" points="592.7,144.6 589.9,177.9 591.8,193.6 623.2,192.5 625.3,150.4 		" />
									<polygon class="st1" points="626.5,213.2 600,217.8 628.7,230 		" />
									<rect x="619.3" y="149.3" class="st0" width="1.7" height="3.7" />
								</g>
								<path class="st0" d="M603.7,270.4l-15.9-103.8l-8.7,18.5l0.7,4.8c-2.7,2.8-9.2,11.2,2.7,18.3l9.2,62.3H603.7z" />
								<polygon class="st3" points="585.1,226.2 590,220.2 592.3,229.2 586.4,234.5 	" />
								<polygon class="st3" points="586.9,238.1 591.8,232.1 594.1,241.1 588.2,246.4 	" />
								<polygon class="st3" points="588.7,251.5 593.6,245.5 596,254.5 590,259.8 	" />
								<polygon class="st1" points="590.8,223.2 588.1,226.2 589,232.1 592.3,229.2 	" />
								<polygon class="st1" points="592.6,235.1 589.9,238 590.8,244 594.1,241.1 	" />
								<polygon class="st1" points="594.4,248.5 591.8,251.5 592.7,257.4 596,254.5 	" />
							</g>
							<g class="content1 show1" id="svg2-2">
								<polygon class="st1" points="235.5,134.7 226.5,134.7 203.1,202.1 500.5,200.1 507.3,272.2 603.7,272.2 590.5,185.1 239.7,187.2 
						 238,191.7 223,177.9 	" />
								<polygon class="st0" points="562.4,285.6 562.4,284.1 596.9,284.1 607.1,271.4 507,271.4 506.5,260.7 503.5,276.5 503.9,285.6 
						 520.1,285.6 520.1,284.1 546.1,284.1 546.1,285.6 	" />
								<polygon class="st0" points="241.7,116 209,116.1 179.1,202.5 205,202.3 227,135.6 235.9,135.5 	" />
								<polygon class="st4" points="198.6,172.3 216,123 238.8,123 237.4,131.5 224.6,131.9 207.8,179.8 	" />
								<g>
									<rect x="217.7" y="126" class="st0" width="19.7" height="0.3" />
								</g>
								<g>
									<g>

										<rect x="209.1" y="124.6" transform="matrix(0.9406 0.3395 -0.3395 0.9406 63.3613 -62.119)" class="st0" width="0.3"
										 height="50.8" />
									</g>
									<g>
										<polygon class="st0" points="209.5,174.9 202.6,168.4 202.8,168.2 209.6,174.7 			" />
									</g>
									<g>
										<polygon class="st0" points="211.9,168.3 204.9,161.7 205.1,161.5 212.1,168.1 			" />
									</g>
									<g>
										<polygon class="st0" points="214,162.2 207.1,155.7 207.2,155.5 214.1,162.1 			" />
									</g>
									<g>
										<polygon class="st0" points="215.9,156.6 209.1,150.1 209.2,149.9 216,156.4 			" />
									</g>
									<g>
										<polygon class="st0" points="218.3,150 211.4,143.4 211.6,143.3 218.4,149.7 			" />
									</g>
									<g>
										<polygon class="st0" points="220.4,143.9 213.6,137.5 213.8,137.3 220.5,143.7 			" />
									</g>
									<g>
										<polygon class="st0" points="222.4,138.1 215.6,131.7 215.8,131.5 222.5,137.9 			" />
									</g>
									<g>
										<polygon class="st0" points="224.4,133.2 217.7,126.8 217.8,126.6 224.5,133 			" />
									</g>
								</g>
								<g>
									<polygon class="st0" points="230.5,132.7 223.7,126.2 223.9,126.1 230.6,132.4 		" />
								</g>
								<g>
									<polygon class="st0" points="235.1,131.6 229.5,126.3 229.7,126.1 235.5,131.6 		" />
								</g>
								<polygon class="st0" points="216.4,195.9 219.7,201.2 214.7,201.3 	" />
								<g>
									<polygon class="st5" points="562.4,283 562.4,284.1 596.9,284.1 597.7,283 		" />
									<polygon class="st5" points="562.4,283 561.3,283 561.3,284.5 547.2,284.5 547.2,283 519.1,283 519.1,284.5 505,284.5 
							 504.5,275.5 503.5,275.5 503.9,285.6 520.2,285.6 520.2,284.1 546.1,284.1 546.1,285.6 562.4,285.6 		" />
								</g>
								<g>
									<polygon class="st6" points="536.8,281.7 529.1,281.7 528.4,276.8 536.1,276.8 		" />
									<g>
										<rect x="528.4" y="278" class="st0" width="7.9" height="0.3" />
									</g>
									<g>

										<rect x="530.2" y="279.1" transform="matrix(0.1403 0.9901 -0.9901 0.1403 734.4951 -287.4052)" class="st0"
										 width="5" height="0.3" />
									</g>
								</g>
								<polygon class="st6" points="589.4,281.7 566.2,281.7 565.5,276.8 588.7,276.8 	" />
								<g>
									<rect x="565.5" y="278" class="st0" width="23.4" height="0.3" />
								</g>
								<g>

									<rect x="567.3" y="279.1" transform="matrix(0.1399 0.9902 -0.9902 0.1399 766.5901 -324.0254)" class="st0"
									 width="5" height="0.3" />
								</g>
								<g>

									<rect x="571.4" y="279.1" transform="matrix(0.1401 0.9901 -0.9901 0.1401 770.0409 -328.1703)" class="st0"
									 width="5" height="0.3" />
								</g>
								<g>

									<rect x="575.6" y="279.1" transform="matrix(0.1403 0.9901 -0.9901 0.1403 773.5068 -332.3343)" class="st0"
									 width="5" height="0.3" />
								</g>
								<g>

									<rect x="579.9" y="279.1" transform="matrix(0.1401 0.9901 -0.9901 0.1401 777.324 -336.5565)" class="st0" width="5"
									 height="0.3" />
								</g>
								<g>

									<rect x="583.4" y="279.1" transform="matrix(0.1401 0.9901 -0.9901 0.1401 780.3294 -340.0172)" class="st0"
									 width="5" height="0.3" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-3">
								<polygon class="st1" points="199.8,216.8 181,266.4 195.2,265.9 209,217 	" />
								<polygon class="st0" points="200,217.3 208.9,217.4 212.9,202.3 178.7,202.5 157.3,265.1 176.8,276.6 201.6,276.6 201.6,269.9 
						 192.3,265.9 195.2,256.5 188.8,250.1 199.8,216.8 	" />
								<g>
									<polygon class="st4" points="179.4,231.6 177.5,236.9 184.3,243.4 184.3,243.4 186.2,238 		" />
									<polygon class="st4" points="172.9,249.5 179.8,256.1 182.1,249.6 175.2,243.1 		" />
									<polygon class="st4" points="177.4,237.2 175.3,242.9 182.2,249.4 184.2,243.6 		" />
									<polygon class="st4" points="181.8,224.9 179.5,231.3 186.3,237.8 188.5,231.4 		" />
									<polygon class="st4" points="187.6,207.8 185.7,205.9 168.4,255.1 170.1,256.5 		" />
									<polygon class="st4" points="183.9,219 181.9,224.7 188.6,231.1 190.6,225.3 		" />
									<polygon class="st4" points="172.8,249.8 170.4,256.7 177.5,262.6 179.7,256.3 		" />
									<polygon class="st4" points="187.9,208.1 186.1,212.9 192.8,219.3 194.4,214.7 		" />
									<polygon class="st4" points="186,213.2 184,218.7 190.7,225.1 192.7,219.5 		" />
								</g>
								<polygon class="st5" points="176.8,276.6 175,275.5 201.6,275.5 201.6,269.9 202.6,270.9 202.6,276.6 	" />
								<polygon class="st5" points="201.9,209.6 199.6,209.6 205.5,216.9 199.8,216.8 200.1,217.4 208.2,217.4 	" />
							</g>
							<g class="content1 show1" id="svg2-4">
								<polygon class="st1" points="209,217 260.6,216.7 247.8,265.6 194.6,265.9 	" />
								<polygon class="st0" points="207.4,263.8 207.2,265.8 192.3,265.9 201.6,269.9 207.9,276.6 216.1,276.6 216.1,275.1 238.9,275.1 
						 238.9,276.6 251.5,276.6 248.6,263.4 	" />
								<polygon class="st0" points="263.7,201.9 212.9,202.3 195.5,261.3 209.7,261.3 223.3,217.2 260,216.8 	" />
								<polygon class="st5" points="238.9,276.6 251.5,276.6 251.3,275.5 240,275.5 240,274.1 215.1,274.1 215.1,275.5 206.9,275.5 
						 207.9,276.6 216.1,276.6 216.1,275.1 238.9,275.1 	" />
								<g>
									<polygon class="st6" points="216.9,267.7 219.9,267.7 218.5,266.4 215.6,266.4 		" />
									<polygon class="st6" points="223.8,271.4 220.2,268 217.2,268 220.9,271.4 		" />
									<polygon class="st6" points="220.2,267.7 223.4,267.7 222.1,266.4 218.9,266.4 		" />
									<polygon class="st6" points="223.7,268 220.5,268 224.2,271.4 227.4,271.4 		" />
									<polygon class="st6" points="230,267.7 228.7,266.4 225.9,266.4 227.3,267.7 		" />
									<polygon class="st6" points="223.7,267.7 226.9,267.7 225.6,266.4 222.4,266.4 		" />
									<polygon class="st6" points="216.9,268 214.1,268 217.9,271.4 220.6,271.4 		" />
									<polygon class="st6" points="230.4,267.7 233,267.7 231.6,266.4 229,266.4 		" />
									<polygon class="st6" points="216.6,267.7 215.3,266.4 212.5,266.4 213.9,267.7 		" />
									<polygon class="st6" points="230.3,268 227.5,268 231.2,271.4 234,271.4 		" />
									<polygon class="st6" points="227.2,268 224,268 227.7,271.4 230.9,271.4 		" />
									<polygon class="st6" points="233.3,268 230.6,268 234.3,271.4 237.1,271.4 		" />
								</g>
								<polygon class="st5" points="251.7,217.1 245.4,209.3 239.5,209.3 245.5,216.9 219.2,217.1 219.2,217.3 246,217.1 246,217.1 	" />
								<polygon class="st7" points="219.4,208.8 203.9,261.3 208.4,261.3 222.4,261.3 234.1,217.3 224.1,208.8 	" />
								<polygon class="st8" points="219.4,208.8 203.9,261.3 207.9,261.3 223.7,208.8 	" />
								<g>
									<rect x="214.7" y="224.5" class="st0" width="4.2" height="0.3" />
								</g>
								<g>
									<rect x="208.4" y="245.6" class="st0" width="4.2" height="0.3" />
								</g>
								<g>
									<polygon class="st0" points="211.7,258.4 213.8,259.8 214.4,257.5 212.4,256.2 		" />
									<polygon class="st0" points="212.9,254.3 215,255.7 215.6,253.4 213.6,252.1 		" />
									<polygon class="st0" points="214.1,250.5 216.1,251.8 216.8,249.6 214.7,248.2 		" />
									<polygon class="st0" points="218.1,245.6 216,244.3 215.4,246.5 217.4,247.8 		" />
									<polygon class="st0" points="219.3,241.5 217.3,240.2 216.6,242.4 218.6,243.8 		" />
									<polygon class="st0" points="220.5,237.6 218.4,236.3 217.7,238.5 219.8,239.9 		" />
									<polygon class="st0" points="221.9,233.8 219.8,232.4 219.2,234.7 221.2,236 		" />
									<polygon class="st0" points="223.1,229.7 221,228.3 220.4,230.6 222.4,231.9 		" />
									<polygon class="st0" points="224.2,225.8 222.2,224.5 221.5,226.7 223.6,228 		" />
									<polygon class="st0" points="225.5,221.8 223.5,220.5 222.8,222.7 224.9,224.1 		" />
									<polygon class="st0" points="226.7,217.7 224.7,216.4 224,218.7 226.1,220 		" />
									<polygon class="st0" points="227.9,213.9 225.9,212.5 225.2,214.8 227.2,216.1 		" />
									<polygon class="st0" points="217.2,258.5 219.2,259.8 219.9,257.5 217.8,256.2 		" />
									<polygon class="st0" points="218.2,254.6 220.3,255.9 220.9,253.6 218.9,252.3 		" />
									<polygon class="st0" points="219.5,250.6 221.5,251.9 222.1,249.6 220.1,248.3 		" />
									<polygon class="st0" points="220.6,246.5 222.7,247.8 223.3,245.5 221.2,244.2 		" />
									<polygon class="st0" points="221.7,242.6 223.7,243.9 224.4,241.6 222.3,240.3 		" />
									<polygon class="st0" points="223,238.7 225.1,240 225.7,237.7 223.6,236.4 		" />
									<polygon class="st0" points="224.1,234.6 226.2,235.9 226.8,233.6 224.8,232.3 		" />
									<polygon class="st0" points="225.2,230.7 227.3,232 227.9,229.7 225.8,228.4 		" />
									<polygon class="st0" points="226.4,226.7 228.5,228 229.1,225.7 227,224.4 		" />
									<polygon class="st0" points="227.5,222.6 229.6,223.9 230.2,221.6 228.2,220.3 		" />
									<polygon class="st0" points="229.2,216.4 228.6,218.7 230.7,220 231.3,217.7 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-5">
								<polygon class="st1" points="407,201.2 403.1,261.7 455.1,261.2 454.9,200.8 	" />
								<polygon class="st0" points="411.3,275.2 446.1,275.2 446.1,276.5 455.5,276.5 455.1,261.2 402.6,261.7 403.1,276.5 411.3,276.5 	
						 " />
								<polygon class="st0" points="455.5,214.6 455.5,214.5 455.5,200.9 455.1,200.9 455.1,200.5 407.6,200.8 406.2,215.2 406.9,215.2 
						 402.4,261.6 408.4,261.7 411.4,215.1 436.8,214.8 436.8,234 439.8,246.5 455.5,246.5 455.5,214.6 	" />
								<polygon class="st1" points="455.1,214.3 438.6,214.3 438.6,233.8 455.1,233.8 	" />
								<polygon class="st5" points="455.5,275.5 447.2,275.5 447.2,274.1 410.6,274.1 410.6,275.5 403.1,275.5 403.1,276.6 411.6,276.6 
						 411.6,275.1 446.1,275.1 446.1,276.6 455.5,276.6 	" />
								<g>
									<polygon class="st6" points="440.2,271.4 421,271.4 415.6,266.4 434.8,266.4 		" />
									<g>
										<rect x="417.1" y="267.7" class="st0" width="19.1" height="0.3" />
									</g>
									<g>

										<rect x="417.5" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 296.2183 -214.917)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="420.8" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 297.097 -217.1501)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="424.3" y="268.8" transform="matrix(0.7319 0.6815 -0.6815 0.7319 298.0198 -219.5458)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="427.9" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 299.0076 -221.9546)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="430.9" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 299.8363 -224.0604)" class="st0"
										 width="7.3" height="0.2" />
									</g>
								</g>
								<polygon class="st5" points="434.5,215.1 428.6,215.1 427.7,207.3 433.7,207.3 	" />
								<g>
									<rect x="411.4" y="214.7" class="st5" width="24.8" height="0.5" />
								</g>
								<g>
									<polygon class="st9" points="423.4,222.6 422.6,253.6 430.2,253.6 431.2,222.6 		" />
									<polygon class="st10" points="422.6,253.6 422.6,255.9 430.2,255.9 430.2,253.6 		" />
								</g>
								<polygon class="st11" points="417.6,209.8 409.4,209.8 405.6,260.5 414.2,260.5 414.2,260.5 	" />
								<g>
									<g>

										<rect x="411.8" y="218.2" transform="matrix(-0.9989 -4.590200e-002 4.590200e-002 -0.9989 815.3881 458.4101)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M414.1,221.7l-2.5-0.1l0.2-3.6l2.5,0.1L414.1,221.7z M411.9,221.3l2,0.1l0.1-3.1l-2-0.1L411.9,221.3z" />
									</g>
									<g>

										<rect x="410.6" y="232.8" transform="matrix(-0.9962 -8.729838e-002 8.729838e-002 -0.9962 801.3568 504.0325)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M412.8,236.4l-2.5-0.2l0.3-3.6l2.5,0.2L412.8,236.4z M410.6,235.9l2,0.2l0.3-3.1l-2-0.2L410.6,235.9z" />
									</g>
									<g>

										<rect x="409.7" y="249.3" transform="matrix(-0.9962 -8.729838e-002 8.729838e-002 -0.9962 798.1294 536.9324)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M411.9,252.9l-2.5-0.2l0.3-3.6l2.5,0.2L411.9,252.9z M409.7,252.5l2,0.2l0.3-3.1l-2-0.2L409.7,252.5z" />
									</g>
									<g>

										<rect x="408.2" y="226.9" transform="matrix(0.9983 5.889846e-002 -5.889846e-002 0.9983 14.0924 -23.8945)"
										 class="st12" width="8.3" height="0.5" />
									</g>
									<g>

										<rect x="407" y="242.4" transform="matrix(0.9983 5.887518e-002 -5.887518e-002 0.9983 14.9969 -23.7862)" class="st12"
										 width="8.4" height="0.5" />
									</g>
								</g>
								<g>
									<rect x="443.8" y="211.9" class="st13" width="5.7" height="3.3" />
									<polygon class="st14" points="444.1,209.6 443.8,211.9 449.5,211.9 449.1,209.6 		" />
									<path class="st14" d="M443.8,215.1l-0.3,6.6c0,0,2,4,3.1,3.9c1.1,0,2.8-3.8,2.8-3.8v-6.7L443.8,215.1L443.8,215.1z" />
									<circle class="st15" cx="446.6" cy="220" r="1.1" />
								</g>
								<polygon class="st11" points="454,260.3 446.6,260.4 446.6,249.6 454,249.6 	" />
							</g>
							<g class="content1 show1" id="svg2-6">
								<polygon class="st1" points="455.1,200.5 455.1,261.7 506.6,261.7 500.5,200.1 	" />
								<polygon class="st0" points="455.1,261.2 455.5,276.5 464.1,276.5 464.1,275.2 497.5,275.2 497.5,276.5 503.5,276.5 506.5,260.7 	
						 " />
								<polygon class="st0" points="501.4,211.3 501.4,200.1 455.1,200.5 455.5,214.5 455.5,241.5 455.1,241.3 455.1,260.3 460,260.3 
						 460,246.5 472.8,246.5 472.8,232.8 472.8,214.4 499.5,214.1 499.3,216.7 503.9,261.7 506.6,261.7 	" />
								<polygon class="st1" points="455.1,233.8 471.3,233.8 471.3,214.3 455.1,214.3 	" />
								<polygon class="st5" points="455.5,276.6 464.1,276.6 464.1,275.1 497.5,275.1 497.5,276.5 503.5,276.5 503.5,275.5 498.6,275.5 
						 498.6,274.1 463.2,274.1 463.2,275.5 455.5,275.5 	" />
								<g>
									<polygon class="st6" points="492.4,271.4 473.2,271.4 467.7,266.4 486.9,266.4 		" />
									<g>
										<rect x="469.3" y="267.7" class="st0" width="19.1" height="0.3" />
									</g>
									<g>

										<rect x="469.7" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 310.2277 -250.4659)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="473" y="268.8" transform="matrix(0.7319 0.6815 -0.6815 0.7319 311.059 -252.684)" class="st0" width="7.3"
										 height="0.2" />
									</g>
									<g>

										<rect x="476.5" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 312.0516 -255.1007)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="480" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 312.9948 -257.4974)" class="st0" width="7.3"
										 height="0.2" />
									</g>
									<g>

										<rect x="483.1" y="268.8" transform="matrix(0.7317 0.6816 -0.6816 0.7317 313.8718 -259.6192)" class="st0"
										 width="7.3" height="0.2" />
									</g>
								</g>
								<polygon class="st5" points="481.5,214.3 475.6,214.3 474.7,206.4 480.7,206.4 	" />
								<g>
									<rect x="473.3" y="214" class="st5" width="26.2" height="0.5" />
								</g>
								<g>
									<polygon class="st9" points="477.1,222.6 478.3,253.6 485.9,253.6 484.9,222.6 		" />
									<polygon class="st10" points="478.3,253.6 478.3,255.9 485.9,255.9 485.9,253.6 		" />
								</g>
								<g>
									<rect x="461.1" y="211.9" class="st13" width="5.7" height="3.3" />
									<polygon class="st14" points="461.4,209.6 461.1,211.9 466.7,211.9 466.3,209.6 		" />
									<path class="st14" d="M461.1,215.1l-0.3,6.6c0,0,2,4,3.1,3.9c1.1,0,2.8-3.8,2.8-3.8v-6.7L461.1,215.1L461.1,215.1z" />
									<circle class="st15" cx="463.9" cy="220" r="1.1" />
								</g>
								<polygon class="st11" points="463.6,260.3 456.3,260.4 456.3,249.6 463.7,249.6 	" />
							</g>
							<g class="content1 show1" id="svg2-7">
								<polygon class="st1" points="311.3,201.5 300.2,262.8 351.3,262.3 360.6,201.2 	" />
								<polygon class="st0" points="300.2,262.8 302.6,276.5 308.3,276.5 308.3,275.1 341.4,275.1 341.4,276.6 352.3,276.5 351.3,262.3 	
						 " />
								<g>
									<polygon class="st10" points="333,222 326.8,253.6 329.1,256.6 337.1,256.6 343.3,225.1 341.1,222 		" />
									<polygon class="st9" points="332.8,222 326.8,253.5 335.1,253.5 341.5,222 		" />
								</g>
								<g>

									<rect x="330.7" y="249.8" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 643.246 -25.0971)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="331.4" y="246.1" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 640.4861 -30.3416)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="332.2" y="242.3" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 637.6854 -35.6668)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="332.9" y="239" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 635.2787 -40.2409)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="333.5" y="235.7" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 632.8372 -44.8819)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="334.3" y="231.9" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 630.0787 -50.1271)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="335.1" y="228.1" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 627.278 -55.4523)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="335.8" y="224.9" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 624.8712 -60.0265)" class="st12"
									 width="2.4" height="0.8" />
								</g>
								<polygon class="st0" points="360.6,201.2 311.3,201.5 300.2,261.9 314.5,261.9 322.5,216.1 340.3,215.9 337.6,234.1 345.6,247.5 
						 353.5,247.5 	" />
								<polygon class="st11" points="324.2,212.1 316.4,211.7 306.4,261.1 315.1,261.1 	" />
								<g>
									<g>

										<rect x="317.6" y="220" transform="matrix(-0.9856 -0.1691 0.1691 -0.9856 595.4343 494.1089)" class="st11"
										 width="2.2" height="3.3" />
										<path class="st12" d="M319.7,223.7l-2.5-0.4l0.6-3.5l2.5,0.4L319.7,223.7z M317.5,223.1l2,0.3l0.5-3l-2-0.3L317.5,223.1z" />
									</g>
									<g>

										<rect x="314.6" y="234.4" transform="matrix(-0.9777 -0.2098 0.2098 -0.9777 574.7983 533.0581)" class="st11"
										 width="2.2" height="3.3" />
										<path class="st12" d="M316.5,238.1l-2.4-0.5l0.8-3.5l2.4,0.5L316.5,238.1z M314.4,237.3l1.9,0.4l0.6-3l-1.9-0.4L314.4,237.3z" />
									</g>
									<g>

										<rect x="311.6" y="250.7" transform="matrix(-0.9777 -0.2098 0.2098 -0.9777 565.5876 564.646)" class="st11"
										 width="2.2" height="3.3" />
										<path class="st12" d="M313.6,254.3l-2.4-0.5l0.8-3.5l2.4,0.5L313.6,254.3z M311.4,253.6l1.9,0.4l0.6-3l-1.9-0.4L311.4,253.6z" />
									</g>
									<g>

										<rect x="313.2" y="228.6" transform="matrix(0.9833 0.1818 -0.1818 0.9833 46.8791 -53.8669)" class="st12"
										 width="8.3" height="0.5" />
									</g>
									<g>

										<rect x="310" y="243.8" transform="matrix(0.9833 0.1818 -0.1818 0.9833 49.6118 -53.0588)" class="st12" width="8.4"
										 height="0.5" />
									</g>
								</g>
								<polygon class="st5" points="352.4,275.5 342.4,275.5 342.4,274.1 307.4,274.1 307.4,274.1 307.4,275.5 302.7,275.5 302.6,276.6 
						 308.3,276.6 308.3,275.1 341.4,275.1 341.4,276.6 352.3,276.6 	" />
								<g>
									<polygon class="st6" points="315.9,267.7 318.8,267.7 317.4,266.4 314.5,266.4 		" />
									<polygon class="st6" points="322.6,268 319.4,268 323.1,271.4 326.3,271.4 		" />
									<polygon class="st6" points="319.1,267.7 322.3,267.7 321,266.4 317.8,266.4 		" />
									<polygon class="st6" points="328.9,267.7 327.6,266.4 324.8,266.4 326.2,267.7 		" />
									<polygon class="st6" points="322.7,267.7 325.9,267.7 324.5,266.4 321.3,266.4 		" />
									<polygon class="st6" points="322.8,271.4 319.1,268 316.1,268 319.8,271.4 		" />
									<polygon class="st6" points="329.3,267.7 332,267.7 330.6,266.4 327.9,266.4 		" />
									<polygon class="st6" points="315.5,267.7 314.2,266.4 311.4,266.4 312.8,267.7 		" />
									<polygon class="st6" points="326.1,268 322.9,268 326.6,271.4 329.8,271.4 		" />
									<polygon class="st6" points="315.8,268 313.1,268 316.8,271.4 319.5,271.4 		" />
									<polygon class="st6" points="332.2,268 329.5,268 333.2,271.4 336,271.4 		" />
									<polygon class="st6" points="329.2,268 326.5,268 330.2,271.4 332.9,271.4 		" />
								</g>
								<polygon class="st5" points="341.3,208.5 335.3,208.5 340.5,215 	" />
								<polygon class="st1" points="350.8,216 347.7,234.2 354.5,234 357.1,216 	" />
								<g>

									<rect x="352.2" y="215.6" transform="matrix(-0.9995 -3.279876e-002 3.279876e-002 -0.9995 701.9674 445.5437)"
									 class="st13" width="4.9" height="2.8" />
									<polygon class="st14" points="352.6,213.5 352.2,215.5 357.1,215.7 356.9,213.6 		" />
									<path class="st14" d="M352.1,218.3l-0.4,5.8c0,0,1.6,3.5,2.6,3.5c0.9,0,2.6-3.3,2.6-3.3l0.2-5.8L352.1,218.3z" />
									<ellipse class="st15" cx="354.5" cy="222.6" rx="1" ry="1" />
								</g>
								<path class="st16" d="M337.6,234.1" />
								<g>
									<polygon class="st5" points="323.4,216.3 323.5,215.8 340,215.6 340,216.1 		" />
								</g>
								<polygon class="st11" points="352,250.2 348.6,250.2 346.8,260.7 350.6,260.7 	" />
							</g>
							<g class="content1 show1" id="svg2-8">
								<polygon class="st1" points="248.2,263.4 263.7,202.1 311.3,201.5 300.2,262.8 	" />
								<polygon class="st0" points="248.6,263.4 251.5,276.6 256.9,276.6 256.9,275.1 290.3,275.1 290.3,276.5 302.6,276.5 300.2,262.8 	
						 " />
								<polygon class="st0" points="311.4,201.5 270.2,201.8 263.7,201.8 263.5,202.4 249,261.9 263.3,261.9 266.7,247.1 266.9,247.3 
						 286.2,247.3 292.5,216.4 308.6,216.2 	" />
								<polygon class="st5" points="302.7,275.5 291.4,275.5 291.4,274.1 256,274.1 256,275.5 251.3,275.5 251.5,276.6 256.9,276.6 
						 256.9,275.1 290.3,275.1 290.3,276.6 302.6,276.6 	" />
								<g>
									<polygon class="st6" points="268.8,267.7 272,267.7 270.6,266.4 267.4,266.4 		" />
									<polygon class="st6" points="265.5,267.7 268.4,267.7 267.1,266.4 264.1,266.4 		" />
									<polygon class="st6" points="272.4,271.4 268.7,268 265.8,268 269.4,271.4 		" />
									<polygon class="st6" points="278.6,267.7 277.2,266.4 274.4,266.4 275.8,267.7 		" />
									<polygon class="st6" points="272.3,267.7 275.5,267.7 274.1,266.4 270.9,266.4 		" />
									<polygon class="st6" points="272.2,268 269,268 272.7,271.4 275.9,271.4 		" />
									<polygon class="st6" points="265.4,268 262.7,268 266.5,271.4 269.1,271.4 		" />
									<polygon class="st6" points="278.9,267.7 281.6,267.7 280.2,266.4 277.5,266.4 		" />
									<polygon class="st6" points="265.1,267.7 263.8,266.4 261,266.4 262.4,267.7 		" />
									<polygon class="st6" points="281.8,268 279.2,268 282.9,271.4 285.6,271.4 		" />
									<polygon class="st6" points="278.8,268 276.1,268 279.8,271.4 282.5,271.4 		" />
									<polygon class="st6" points="275.7,268 272.6,268 276.2,271.4 279.4,271.4 		" />
								</g>
								<polygon class="st5" points="304.2,216.4 297.9,208.5 291.9,208.5 297.9,215.9 292.8,215.9 292.8,215.9 292.3,215.9 286,247.1 
						 266.8,247.1 263.3,261 263.7,261.1 267.1,247.6 286.4,247.6 292.8,216.3 292.7,216.4 298.2,216.4 298.2,216.4 298.2,216.4 	" />
								<g>
									<polygon class="st10" points="294.3,223 288.2,254.6 290.4,257.6 298.4,257.6 304.7,226.1 302.4,223 		" />
									<polygon class="st9" points="294.2,223 288.2,254.5 296.4,254.5 302.8,223 		" />
								</g>
								<g>

									<rect x="292" y="250.9" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 597.8888 14.0231)" class="st12" width="2.4"
									 height="0.8" />

									<rect x="292.8" y="247.1" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 595.129 8.7787)" class="st12" width="2.4"
									 height="0.8" />

									<rect x="293.5" y="243.3" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 592.3282 3.4534)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="294.2" y="240" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 589.9214 -1.1208)" class="st12" width="2.4"
									 height="0.8" />

									<rect x="294.9" y="236.7" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 587.4799 -5.7617)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="295.7" y="233" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 584.7216 -11.007)" class="st12" width="2.4"
									 height="0.8" />

									<rect x="296.4" y="229.2" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 581.9208 -16.3323)" class="st12"
									 width="2.4" height="0.8" />

									<rect x="297.1" y="225.9" transform="matrix(-0.1994 0.9799 -0.9799 -0.1994 579.514 -20.9064)" class="st12"
									 width="2.4" height="0.8" />
								</g>
								<polygon class="st11" points="267.2,250.6 263.2,250.6 261.4,261.1 265.2,261.1 	" />
							</g>
							<g class="content1 show1" id="svg2-9">
								<polygon class="st0" points="359.4,275.2 393.6,275.2 393.6,276.5 403.1,276.5 402.6,261.7 351.3,262.3 352.3,276.5 359.4,276.5 	
						 " />
								<polygon class="st1" points="360.6,201.2 351.3,262.3 403.1,261.7 407.7,200.8 	" />
								<polygon class="st0" points="353.5,247.5 379.9,247.5 383.6,215.1 376.2,201.5 360.2,201.9 	" />
								<polygon class="st0" points="406.2,215.2 358,215.7 360.6,201.2 407.6,200.8 	" />
								<polygon class="st0" points="358.5,208.1 365.2,215.3 359.1,260.6 352,260.9 	" />
								<polygon class="st5" points="403.1,275.5 394.7,275.5 394.7,274.1 358.4,274.1 358.4,275.5 352.4,275.5 352.3,276.6 359.4,276.6 
						 359.4,275.1 393.6,275.1 393.6,276.6 403.1,276.6 	" />
								<g>
									<polygon class="st6" points="388.7,271.4 369.5,271.4 364.1,266.4 383.3,266.4 		" />
									<g>
										<rect x="365.6" y="267.7" class="st0" width="19.1" height="0.3" />
									</g>
									<g>

										<rect x="366" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 282.4077 -179.8205)" class="st0" width="7.3"
										 height="0.2" />
									</g>
									<g>

										<rect x="369.3" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 283.2864 -182.0537)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="372.8" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 284.2314 -184.4552)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="376.4" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 285.1746 -186.852)" class="st0"
										 width="7.3" height="0.2" />
									</g>
									<g>

										<rect x="379.4" y="268.8" transform="matrix(0.7318 0.6815 -0.6815 0.7318 286.0241 -188.9623)" class="st0"
										 width="7.3" height="0.2" />
									</g>
								</g>
								<polygon class="st5" points="394.8,215.1 388.9,215.1 388.1,207.3 394,207.3 	" />
								<polygon class="st1" points="366.8,216 364.4,234.4 371.9,234.5 374.1,216 	" />
								<g>

									<rect x="367.8" y="214.1" transform="matrix(-0.9958 -9.150054e-002 9.150054e-002 -0.9958 719.8922 464.5435)"
									 class="st13" width="5.7" height="3.2" />
									<polygon class="st14" points="368.5,211.6 367.9,213.9 373.6,214.4 373.4,212.1 		" />
									<path class="st14" d="M367.6,217.1l-0.9,6.6c0,0,1.6,4.1,2.7,4.2c1.1,0.1,3.2-3.6,3.2-3.6l0.6-6.7L367.6,217.1z" />
									<ellipse class="st15" cx="370" cy="222.2" rx="1.1" ry="1.1" />
								</g>
								<path class="st16" d="M372.2,234.5" />
								<g>
									<rect x="383.6" y="214.9" class="st5" width="21.9" height="0.5" />
								</g>
								<g>
									<polygon class="st9" points="385.4,222.6 382.9,253.6 390.4,253.6 393.2,222.6 		" />
									<polygon class="st10" points="382.9,253.6 382.9,255.9 390.4,255.9 390.4,253.6 		" />
								</g>
								<g>

									<rect x="385.7" y="249.9" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 669.9979 -113.3034)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="386" y="246.1" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 666.564 -117.7799)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="386.4" y="242.2" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 663.0789 -122.325)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="386.7" y="238.9" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 660.0831 -126.2309)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="387" y="235.5" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 657.0444 -130.193)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="387.3" y="231.7" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 653.6105 -134.6695)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="387.6" y="227.8" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 650.1256 -139.2146)"
									 class="st12" width="2.4" height="0.8" />

									<rect x="387.9" y="224.5" transform="matrix(-8.729839e-002 0.9962 -0.9962 -8.729839e-002 647.1298 -143.1205)"
									 class="st12" width="2.4" height="0.8" />
								</g>
								<polygon class="st11" points="397.8,209.9 405.9,209.8 402.1,260.5 393.6,260.7 393.6,260.7 	" />
								<g>
									<g>

										<rect x="399.8" y="218.2" transform="matrix(-0.9989 -4.590200e-002 4.590200e-002 -0.9989 791.3877 457.86)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M402.1,221.7l-2.5-0.1l0.2-3.6l2.5,0.1L402.1,221.7z M399.9,221.3l2,0.1l0.1-3.1l-2-0.1L399.9,221.3z" />
									</g>
									<g>

										<rect x="398.6" y="232.8" transform="matrix(-0.9962 -8.729838e-002 8.729838e-002 -0.9962 777.3881 502.9853)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M400.8,236.4l-2.5-0.2l0.3-3.6l2.5,0.2L400.8,236.4z M398.6,235.9l2,0.2l0.3-3.1l-2-0.2L398.6,235.9z" />
									</g>
									<g>

										<rect x="397.7" y="249.3" transform="matrix(-0.9962 -8.729838e-002 8.729838e-002 -0.9962 774.1607 535.8851)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M399.9,252.9l-2.5-0.2l0.3-3.6l2.5,0.2L399.9,252.9z M397.7,252.5l2,0.2l0.3-3.1l-2-0.2L397.7,252.5z" />
									</g>
									<g>

										<rect x="396.2" y="226.9" transform="matrix(0.9983 5.889846e-002 -5.889846e-002 0.9983 14.0715 -23.1873)"
										 class="st12" width="8.3" height="0.5" />
									</g>
									<g>

										<rect x="394.9" y="242.4" transform="matrix(0.9983 5.887518e-002 -5.887518e-002 0.9983 14.976 -23.0793)"
										 class="st12" width="8.4" height="0.5" />
									</g>
								</g>
								<polygon class="st11" points="360.6,250.2 357.2,250.2 355.4,260.7 359.2,260.7 	" />
							</g>
							<g class="content1 show1" id="svg2-10">
								<polygon class="st1" points="241.7,116 226.5,167.2 270.9,167.2 282.7,115.8 	" />
								<polygon class="st0" points="282.7,115.8 241.3,116 235.5,135.5 278.2,135.1 	" />
								<g>
									<polygon class="st17" points="257.3,121.7 262.5,130.4 279.8,130.4 274.5,121.7 		" />
									<g>
										<rect x="258.6" y="124.8" class="st0" width="17.9" height="0.3" />
									</g>
									<g>

										<rect x="263.5" y="125.9" transform="matrix(0.4624 0.8867 -0.8867 0.4624 256.1199 -170.2799)" class="st0"
										 width="9.9" height="0.2" />
									</g>
								</g>
								<polygon class="st0" points="271.1,167.3 253.3,167.3 261.6,134.8 278.4,134.6 	" />
								<polygon class="st0" points="271.6,167.2 244.7,167.2 254.5,135.2 242.3,116.8 240.2,116.8 222.1,177.1 238,191.7 239.7,187.2 
						 277.6,187 	" />
								<g>
									<g>
										<g>
											<polygon class="st3" points="243.9,176.6 252.2,187.2 263.7,187.2 254.9,175.4 244.1,176.1 234.4,176.7 234.6,177.2 				" />
										</g>
										<polygon class="st18" points="247.6,178.4 250.9,182.4 256.5,181.9 253.6,177.5 247.3,178 			" />
									</g>
									<polygon class="st3" points="237.1,176.6 236.9,175.4 238.6,175.3 238.9,176.5 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-11">
								<polygon class="st1" points="282.7,115.8 271.1,167 316.4,167.2 327.2,115.5 	" />
								<polygon class="st0" points="327.2,115.5 282.7,115.8 278.2,135.1 322.9,134.7 	" />
								<g>
									<g>
										<polygon class="st10" points="306.4,141.5 299.5,173 301.6,176 309.6,176.2 316.7,144.9 314.6,141.7 			" />
										<polygon class="st9" points="306.3,141.5 299.5,172.8 307.7,173.1 314.9,141.7 			" />
									</g>
									<g>

										<rect x="303.4" y="169.3" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 538.642 -88.742)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="304.2" y="165.6" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 536.0681 -94.151)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="305.1" y="161.8" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 533.4559 -99.6425)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="305.9" y="158.6" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 531.2118 -104.3619)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="306.6" y="155.3" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 528.934 -109.149)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="307.5" y="151.5" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 526.3599 -114.5581)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="308.4" y="147.8" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 523.7479 -120.0496)" class="st12"
										 width="2.4" height="0.8" />

										<rect x="309.1" y="144.5" transform="matrix(-0.2256 0.9742 -0.9742 -0.2256 521.5024 -124.7682)" class="st12"
										 width="2.4" height="0.8" />
									</g>
								</g>
								<polygon class="st0" points="278.4,134.6 293.8,134.3 302.8,147.8 298.4,167.1 271.1,167.3 	" />
								<g>
									<path class="st17" d="M299,121.7" />
									<g>
										<rect x="301.3" y="124.8" class="st0" width="17.9" height="0.3" />
									</g>
									<g>

										<rect x="306.3" y="125.9" transform="matrix(0.4624 0.8867 -0.8867 0.4624 279.1028 -208.1853)" class="st0"
										 width="9.9" height="0.2" />
									</g>
								</g>
								<polygon class="st0" points="315.9,167.1 271.6,167.2 277.6,187 320.4,186.7 	" />
								<g>
									<polygon class="st17" points="316.4,130.4 302,130.4 299,121.7 313.4,121.7 		" />
									<g>
										<rect x="300" y="124.4" class="st0" width="14.6" height="0.3" />
									</g>
									<g>

										<rect x="303.5" y="126" transform="matrix(0.3257 0.9455 -0.9455 0.3257 326.9763 -206.2734)" class="st0" width="9.2"
										 height="0.2" />
									</g>
								</g>
								<polygon class="st9" points="307.7,141.5 308.1,139.6 310.3,139.6 310,141.9 	" />
								<polygon class="st9" points="310.3,139.6 311.6,140.5 311.5,142.2 310,141.9 	" />
								<g>
									<g>
										<g>
											<polygon class="st3" points="285.6,176.6 290.7,186.8 302.2,186.8 296.6,175.4 285.8,176.1 276.1,176.7 276.2,177.2 				" />
										</g>
										<polygon class="st18" points="289.3,178.4 291.5,182.6 297.1,182.1 295.3,177.5 289,178 			" />
									</g>
									<polygon class="st3" points="278.8,176.6 278.6,175.4 280.2,175.3 280.6,176.5 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-12">
								<polygon class="st1" points="327.2,115.5 315.9,167.1 363.7,167.2 369.8,115.3 	" />
								<polygon class="st0" points="369.8,115.3 327.2,115.5 322.9,134.7 367.4,134.3 	" />
								<g>
									<g>
										<polygon class="st9" points="343.7,141.1 339.4,171.8 346.8,173.2 351.3,142.5 			" />
										<polygon class="st10" points="339.4,171.8 339,174.2 346.4,175.5 346.8,173.2 			" />
									</g>

									<rect x="346.7" y="140.5" transform="matrix(-0.9843 -0.1763 0.1763 -0.9843 664.6872 341.7094)" class="st9"
									 width="1.7" height="1.7" />
								</g>
								<polygon class="st0" points="363.3,167 346.2,167 350.9,134.3 367.4,134.3 	" />
								<polygon class="st0" points="363.3,167 315.9,167.1 320.4,186.7 366.8,186.4 	" />
								<g>
									<polygon class="st17" points="358.6,130.4 344.2,130.4 341.3,121.7 355.7,121.7 		" />
									<g>
										<rect x="342.2" y="124.4" class="st0" width="14.6" height="0.3" />
									</g>
									<g>

										<rect x="345.7" y="126" transform="matrix(0.3257 0.9455 -0.9455 0.3257 355.4712 -246.2258)" class="st0" width="9.2"
										 height="0.2" />
									</g>
								</g>
								<polygon class="st1" points="358.5,146.5 355.2,165.9 362.5,165.9 364.8,146.5 	" />
								<g>
									<rect x="358.5" y="146" class="st5" width="6.3" height="0.5" />
								</g>
								<g>
									<g>

										<rect x="357.1" y="143.1" transform="matrix(0.1717 0.9852 -0.9852 0.1717 439.7021 -236.3124)" class="st5"
										 width="6.5" height="0.5" />
									</g>
									<circle class="st5" cx="359.7" cy="140.1" r="0.8" />
								</g>
								<g>
									<polygon class="st3" points="337.7,175.4 327.8,176.1 322.9,176.4 322.7,175.3 321.1,175.4 321.3,176.5 318.8,176.7 319,177.2 
							 327.6,176.6 332.1,186.6 342.7,186.6 		" />
									<polygon class="st18" points="329.9,178.4 332.1,182.6 337.7,182.1 335.9,177.5 329.6,178 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-13">
								<polygon class="st1" points="412.9,115.1 410.1,167 459.1,167 457.9,114.9 	" />
								<polygon class="st0" points="457.9,114.9 412.9,115.1 411.9,134 458.3,133.6 	" />
								<polygon class="st11" points="420.1,133.3 413.1,133.3 410.7,166.3 419.3,166.3 419.3,166.3 420.8,140.9 	" />
								<g>
									<g>

										<rect x="415.5" y="141.6" transform="matrix(-0.9989 -4.590200e-002 4.590200e-002 -0.9989 826.2164 305.5092)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M417.8,145.1l-2.5-0.1l0.2-3.6l2.5,0.1L417.8,145.1z M415.5,144.8l2,0.1l0.1-3.1l-2-0.1L415.5,144.8z" />
									</g>
									<g>

										<rect x="414.2" y="156.2" transform="matrix(-0.9962 -8.729838e-002 8.729838e-002 -0.9962 815.3459 351.4867)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M416.4,159.8l-2.5-0.2l0.3-3.6l2.5,0.2L416.4,159.8z M414.2,159.4l2,0.2l0.3-3.1l-2-0.2L414.2,159.4z" />
									</g>
									<g>

										<rect x="411.9" y="150.3" transform="matrix(0.9983 5.884658e-002 -5.884658e-002 0.9983 9.5794 -24.222)" class="st12"
										 width="8.3" height="0.5" />
									</g>
								</g>
								<g>
									<g>
										<polygon class="st9" points="424.5,139.7 423.5,170.7 431,171.2 432.3,140.2 			" />
										<polygon class="st10" points="423.5,170.7 423.3,173 430.9,173.6 431,171.2 			" />
									</g>

									<rect x="427.6" y="138.6" transform="matrix(-0.9975 -7.089876e-002 7.089876e-002 -0.9975 845.8015 308.9507)"
									 class="st9" width="1.7" height="1.7" />
								</g>
								<polygon class="st0" points="459,151 444.2,151.4 444.1,167.2 442.2,167.2 442.2,133.7 458.6,133.7 	" />
								<polygon class="st0" points="459.1,166.9 410.1,166.9 410.6,186.1 459.1,185.8 	" />
								<g>
									<g>
										<polygon class="st17" points="444.6,130.4 430.2,130.4 429.2,121.7 443.7,121.7 			" />
										<g>
											<rect x="429.5" y="124.4" class="st0" width="14.6" height="0.3" />
										</g>
										<g>
											<path class="st19" d="M439.8,130.4" />
										</g>
										<g>
											<path class="st19" d="M436.8,121.7" />
										</g>
									</g>
									<g>

										<rect x="432.6" y="126" transform="matrix(0.1352 0.9908 -0.9908 0.1352 502.886 -323.9887)" class="st0" width="8.8"
										 height="0.3" />
									</g>
								</g>
								<g>

									<rect x="450.8" y="144.4" transform="matrix(2.733351e-002 0.9996 -0.9996 2.733351e-002 589.8875 -303.7789)"
									 class="st5" width="0.5" height="13.6" />
								</g>
								<polygon class="st11" points="457.5,133.2 451.1,133.2 451,125.7 457.2,125.7 	" />
								<g>
									<g>
										<g>
											<polygon class="st3" points="423.9,174.5 424,186 435.5,186 434.8,174 424.1,174 414.1,174.2 414.3,174.7 				" />
										</g>
										<polygon class="st18" points="426.7,177.7 426.8,182.5 432.4,182.4 432.4,177.4 426.7,177.3 			" />
									</g>
									<polygon class="st3" points="416.9,174.3 417,173 418.7,172.9 418.7,174.1 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-14">
								<polygon class="st1" points="369.8,115.3 363.7,167 410.1,167 412.9,115.1 	" />
								<polygon class="st0" points="412.9,115.1 369.8,115.3 367.4,134.3 411.9,134 	" />
								<polygon class="st11" points="403.5,133.4 411.1,133.1 408.5,183.9 400,184.2 400,184.2 402.6,141.1 	" />
								<g>
									<g>

										<rect x="405.3" y="141.6" transform="matrix(-0.9997 -2.320073e-002 2.320073e-002 -0.9997 809.3906 295.9339)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M407.6,145.1l-2.5-0.1l0.1-3.6l2.5,0.1L407.6,145.1z M405.4,144.8l2,0l0.1-3.1l-2,0L405.4,144.8z" />
									</g>
									<g>

										<rect x="404.4" y="156.2" transform="matrix(-0.9979 -6.460066e-002 6.460066e-002 -0.9979 799.9123 341.695)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M406.6,159.8l-2.5-0.2l0.2-3.6l2.5,0.2L406.6,159.8z M404.4,159.4l2,0.1l0.2-3.1l-2-0.1L404.4,159.4z" />
									</g>
									<g>

										<rect x="403.8" y="172.8" transform="matrix(-0.9979 -6.460066e-002 6.460066e-002 -0.9979 797.8081 374.7007)"
										 class="st11" width="2.2" height="3.3" />
										<path class="st12" d="M406.1,176.3l-2.5-0.2l0.2-3.6l2.5,0.2L406.1,176.3z M403.9,175.9l2,0.1l0.2-3.1l-2-0.1L403.9,175.9z" />
									</g>
									<g>

										<rect x="401.9" y="150.3" transform="matrix(0.9993 3.612312e-002 -3.612312e-002 0.9993 5.7023 -14.5682)"
										 class="st12" width="8.3" height="0.5" />
									</g>
									<g>

										<rect x="400.9" y="165.8" transform="matrix(0.9994 3.599386e-002 -3.599386e-002 0.9994 6.239 -14.475)" class="st12"
										 width="8.4" height="0.5" />
									</g>
								</g>
								<polygon class="st0" points="367.4,134.3 381.4,134.2 384.7,150 383.6,167 363.3,167 	" />
								<polygon class="st0" points="410.1,166.9 363.3,167 366.8,186.4 410.6,186.1 	" />
								<g>
									<g>
										<polygon class="st17" points="400.6,130.4 386.2,130.4 385.2,121.7 399.7,121.7 			" />
										<g>
											<rect x="385.5" y="124.4" class="st0" width="14.6" height="0.3" />
										</g>
										<g>
											<path class="st19" d="M395.8,130.4" />
										</g>
										<g>
											<path class="st19" d="M392.8,121.7" />
										</g>
									</g>
									<g>

										<rect x="388.6" y="126" transform="matrix(0.1351 0.9908 -0.9908 0.1351 464.8573 -280.389)" class="st0" width="8.8"
										 height="0.3" />
									</g>
								</g>
								<g>
									<rect x="374.3" y="146.3" class="st5" width="5.5" height="0.5" />
								</g>
								<polygon class="st1" points="374.3,146.7 372,165.9 378.1,165.9 379.9,146.7 	" />
								<g>
									<g>

										<rect x="373" y="143.2" transform="matrix(0.1715 0.9852 -0.9852 0.1715 453.0947 -251.9493)" class="st5" width="6.7"
										 height="0.5" />
									</g>
									<circle class="st5" cx="375.6" cy="140.1" r="0.8" />
								</g>
								<g>
									<g>
										<g>
											<polygon class="st3" points="384.3,174.5 384.4,186 395.9,186 395.3,174 384.5,174 374.5,174.2 374.7,174.7 				" />
										</g>
										<polygon class="st18" points="387.1,177.7 387.2,182.5 392.8,182.4 392.8,177.4 387.1,177.3 			" />
									</g>
									<polygon class="st3" points="377.3,174.3 377.4,173 379.1,172.9 379.1,174.1 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-15">
								<polygon class="st1" points="457.9,114.9 459.1,167 498.8,166.9 496.3,115.1 	" />
								<polygon class="st0" points="458.3,133.6 497.6,133.2 496,114.7 457.9,114.9 	" />
								<g>
									<g>
										<polygon class="st9" points="479.3,139.9 480.5,170.9 488,170.9 487.1,139.9 			" />
										<polygon class="st10" points="480.5,170.9 480.5,173.3 488.1,173.3 488,170.9 			" />
									</g>
									<rect x="482.3" y="138.6" class="st9" width="1.7" height="1.7" />
								</g>
								<polygon class="st0" points="458.6,133.7 474.4,133.7 475.1,166.3 474.2,166 473.6,151.3 459,151 	" />
								<polygon class="st0" points="499.3,166.8 459.1,166.9 459.1,185.8 498.8,185.6 	" />
								<g>
									<g>
										<polygon class="st17" points="485.5,130.4 471.1,130.4 470.1,121.7 484.5,121.7 			" />
										<g>
											<rect x="470.4" y="124.4" class="st0" width="14.6" height="0.3" />
										</g>
										<g>
											<path class="st19" d="M480.7,130.4" />
										</g>
										<g>
											<path class="st19" d="M477.7,121.7" />
										</g>
									</g>
									<g>

										<rect x="473.5" y="126" transform="matrix(0.135 0.9908 -0.9908 0.135 538.3284 -364.4587)" class="st0" width="8.8"
										 height="0.3" />
									</g>
								</g>
								<g>

									<rect x="460.5" y="150.9" transform="matrix(0.9998 2.169835e-002 -2.169835e-002 0.9998 3.3897 -10.0988)" class="st5"
									 width="13.1" height="0.5" />
								</g>
								<polygon class="st11" points="496.6,142.4 490.3,142.4 490,131.7 496.2,131.7 	" />
								<polygon class="st11" points="465.6,133.2 459.2,133.2 459.1,125.7 465.3,125.7 	" />
								<polygon class="st11" points="497.7,165.9 491.4,165.9 491.4,155 497.1,155 	" />
								<g>
									<g>
										<g>
											<polygon class="st3" points="480.8,174.5 481,186 492.5,186 491.8,174 481,174 471.1,174.2 471.2,174.7 				" />
										</g>
										<polygon class="st18" points="483.6,177.7 483.7,182.5 489.3,182.3 489.3,177.3 483.6,177.3 			" />
									</g>
									<polygon class="st3" points="473.8,174.3 473.9,173 475.6,172.9 475.6,174.1 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-16">
								<polygon class="st1" points="496,114.7 498.8,166.9 541.3,166.8 536.8,114.9 	" />
								<polygon class="st0" points="497.6,133.2 533.9,132.9 535.4,144.9 540.7,144.9 537.9,114.5 496,114.7 	" />
								<polygon class="st11" points="538.1,144.9 522.7,144.9 523.6,153.1 525.1,165.9 541.8,165.9 	" />
								<polygon class="st0" points="497.3,115.8 496.3,132.7 497.9,167.7 501.4,167.6 	" />
								<polygon class="st0" points="541.8,166.7 499.3,166.8 498.8,185.6 540.1,185.3 	" />
								<g>
									<g>
										<polygon class="st17" points="525.2,130.4 510.8,130.4 509.8,121.7 524.3,121.7 			" />
										<g>
											<rect x="510.1" y="124.4" class="st0" width="14.6" height="0.3" />
										</g>
										<g>
											<path class="st19" d="M520.4,130.4" />
										</g>
										<g>
											<path class="st19" d="M517.4,121.7" />
										</g>
									</g>
									<g>

										<rect x="513.3" y="126" transform="matrix(0.135 0.9908 -0.9908 0.135 572.713 -403.8449)" class="st0" width="8.8"
										 height="0.3" />
									</g>
								</g>
								<g>

									<rect x="511.3" y="127.3" transform="matrix(0.1391 0.9903 -0.9903 0.1391 569.0244 -399.58)" class="st0" width="6"
									 height="0.3" />
								</g>
								<g>

									<rect x="518.5" y="127.3" transform="matrix(0.1526 0.9883 -0.9883 0.1526 567.9348 -407.4239)" class="st0"
									 width="6" height="0.2" />
								</g>
								<polygon class="st20" points="522.7,144.9 520.4,160.7 520.4,166 525.1,165.9 	" />
								<polygon class="st21" points="532.3,144.9 530.8,132.9 530.8,122.2 534.4,122.2 536.8,144.9 	" />
								<g>
									<polygon class="st3" points="507.6,172.6 506.3,186 520.5,186 522.4,172.6 		" />
									<polygon class="st18" points="509.6,174.3 509,180 512.4,180 513.3,174.3 		" />
									<polygon class="st18" points="516.1,174.3 515.5,180 518.9,180 519.8,174.3 		" />
								</g>
							</g>
							<g class="content1 show1" id="svg2-17">
								<polygon class="st1" points="537.9,143.3 560,143.3 567.9,105.2 589.8,111.2 586.9,145.8 559,146.9 561.3,166.8 540.6,166.9 	" />
								<polyline class="st0" points="587.8,166.6 552.4,166.7 552.4,161.3 558,145.5 584.2,145 587.5,112.8 569,107.8 566,125.8 
						 560,143.3 552.4,143.3 557.1,123.5 562.2,123.5 564.1,101.9 569.2,85.8 596.2,93.3 590.8,173.7 587.8,166.5 	" />
								<polygon class="st0" points="587.8,166.6 541.8,166.7 540.1,185.3 579.1,185.1 	" />
								<polygon class="st22" points="538.5,122.7 544.1,123.2 540.2,138.7 	" />
								<rect x="562.6" y="158.9" class="st1" width="20.4" height="6" />
								<g>
									<polygon class="st3" points="545.1,172.1 543.8,185.5 558,185.5 559.9,172.1 		" />
									<polygon class="st18" points="547.1,173.7 546.5,179.5 549.9,179.5 550.8,173.7 		" />
									<polygon class="st18" points="553.6,173.7 553,179.5 556.4,179.5 557.3,173.7 		" />
								</g>
							</g>
							<path class="st14" d="M635.2,206.3h-39.9l-1.9-12.3l30.8-1l2.1-54.9l11.7,3.1l0.9-1.8l-44.3-11.9l2.8-35.2l-26.2-6.2l-2.2-0.5
					 l-3.9,36.9l-8,0l0,2l9.8,0l3.8-36.4l24.4,5.8l-3.9,50.9h-33.5l2.5,20.3h-17.8l-3.8-42h5.3v-2h-5.5l-0.7-7.7l-329.3,1.7l-51.3,149.9
					 l26.4,0.7l5.1-15.7h-2.6l-4.8,13.4l-21.2-0.5l20.3-59.5l30.9-0.2l-18.2,60.2l-0.1,0l-0.7,2.5l14.9-0.2l0.1-1.9l33.6-0.1l-0.2,2.2
					 l14.6-0.1v-2.1l36.9-0.1l-0.1,2.4l14.6-0.2v-2.3l36.6-0.1l-0.2,2.6l14.6-0.2v-2.5l37.9-0.2l-0.2,2.8l14.6-0.2V263l38.1-0.2l-0.2,3.1
					 l14.6-0.2v-2.9l34-0.1l-0.1,3.3l9.1-0.2l0.4,8.6h0l-0.2,3.3l14.6-0.2v-2.9l27.3-0.2l-0.1,3.2l14.6-0.2v-3.1l33.3-0.2v3.1l7.5-0.1
					 l2.9-3l-2.3-15.1l30.7,10.2l1.5-1l3-110.7l-2-0.1L635.2,206.3z M593.2,145.9l1.2-16.3l29.8,8l-2,53.5l-29.2,1l-2.2-14.5l2.2-31.7
					 L593.2,145.9z M592,146.8l-1.6,23.5l-2.9-23.5H592z M560.1,146.8h26.4l2.3,18.4l-1.5,0l0,0h-24.9L560.1,146.8z M535.5,115.6
					 l4.3,50.3h-39.9l-2.7-50.1L535.5,115.6z M495.3,115.8l2.6,50.1h-22.3l-0.8-32.7h-15.4l-0.5-17.2L495.3,115.8z M474.6,165.9h-14.3
					 l-0.8-31.7h14.4L474.6,165.9z M458.3,165.9h-15.6v-31.7h14.8L458.3,165.9z M457,115.9l0.5,17.2h-15.8v32.7h-30.4l2.7-49.8L457,115.9
					 z M411.9,116.1l-2.7,49.8H379l2.7-31.9h-13.2l2.2-17.7L411.9,116.1z M368.4,135h12.2l-2.6,30.9h-13.5L368.4,135z M362.5,165.9h-15.6
					 l4.5-30.9h15L362.5,165.9z M368.7,116.3l-2.2,17.7h-16l-4.6,31.9h-28.7l10.8-49.4L368.7,116.3z M325.9,116.4l-10.8,49.4h-27.4
					 l7-31.6h-15.4l3.9-17.7l-0.2,0L325.9,116.4z M286.7,165.9h-14.2l6.7-30.6h14.3L286.7,165.9z M270.5,165.9h-16.7l8.3-30.6h15
					 L270.5,165.9z M281.2,116.6l-3.9,17.7h-16l-8.7,31.6h-25.2l14.8-49.1L281.2,116.6z M196.4,261.3l17.8-58l48.4-0.4l-14.2,58.2
					 L196.4,261.3z M264.6,202.9l13.2-0.1l-5.7,32.6l-15.5,0L264.6,202.9z M250.3,261.1l6.1-24.7l16.6,0l5.8-33.6l31.3-0.2l-10.5,58.4
					 L250.3,261.1z M301.7,260.9l10.5-58.4l29.8-0.2l-5,32.3l17.4,0.2l-3.8,25.9L301.7,260.9z M354.5,233.8l-16.3-0.2l4.8-31.4l16.1-0.1
					 L354.5,233.8z M361.1,202.2l14.3-0.1l-3.7,32l-15.2-0.2L361.1,202.2z M352.6,260.7l3.8-25.9l16.3,0.2l3.8-33l30-0.2l-4.6,58.7
					 L352.6,260.7z M454,260.3l-50,0.2l4.6-58.7l28-0.2v32.9h17.6L454,260.3z M454.1,233.5h-16.6v-31.9l16.7-0.1L454.1,233.5z M456,260.3
					 l0.1-25.8h17.1v-32.8h-1v31.8h-16.1l0.2-32l43.3-0.3l5.9,59L456,260.3z M508.5,270.9l-1-10.8l0,0l-6-60.6l0-0.5l-286.7,2.1l1.6-5.3
					 h-2.8l-1.6,5.4l-30.8,0.2l28.8-84.6l30.2-0.1l-18.1,60.3l1.9,0.4l2.9-9.6H540l0,0.3l45.8-0.3l1.8,12.3c-2.8,3-8.2,10.9,2.6,17.6
					 l10.8,72.5L508.5,270.9z M633.5,266.9l-30.6-10.3l-4.5-29.7l35.9,10.6L633.5,266.9z M634.4,235.1L598,224.6l-2.5-16.3h39.5
					 L634.4,235.1z" />
						</svg>
					</div>
					<div class="center hide cls-third">
						<?xml version="1.0" encoding="iso-8859-1"?>
						<!-- Generator: Adobe Illustrator 21.0.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
						<svg version="1.1" id="svg3" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
						 x="0px" y="0px" viewBox="0 0 800 420" style="enable-background:new 0 0 800 420;" xml:space="preserve">
							<g>
								<polygon style="fill:#CBD5DF;" points="122.32,303.464 127.011,324.966 170.993,309.523 166.106,288.51 	" />
								<polygon style="fill:#515459;" points="165.942,289.414 153.009,305.809 140.173,309.719 134.063,322.49 170.993,309.523 	" />
								<polyline style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="166.106,287.826 170.993,309.523 127.011,324.966 
		122.32,303.464 166.106,287.826 	" />
							</g>
							<g>
								<polygon style="fill:#CBD5DF;" points="127.296,326.346 131.987,347.848 175.969,332.406 171.082,311.392 	" />
								<polygon style="fill:#515459;" points="170.918,312.296 157.985,328.692 145.149,332.601 139.039,345.372 175.969,332.406 	" />
								<polyline style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="171.082,310.708 175.969,332.406 131.987,347.848 
		127.296,326.346 171.082,310.708 	" />
							</g>
							<g>
								<g>
									<polygon style="fill:#CBD5DF;" points="112.937,251.858 122.32,303.464 166.106,287.826 155.942,237.002 		" />
									<polygon style="fill:#8D9196;" points="160.297,258.78 130.921,269.321 136.452,298.416 166.106,287.826 		" />
									<polygon style="fill:#52545A;" points="160.297,258.78 136.452,298.416 166.106,287.826 		" />
									<polygon style="fill:#AAAEB3;" points="147.145,256.55 150.403,279.42 158.874,286.457 156.137,263.391 		" />
								</g>
								<polygon style="fill:#99A8B1;" points="147.145,245.798 140.14,247.954 143.333,253.617 150.273,251.272 	" />

								<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="141.73" y1="250.773" x2="148.727"
								 y2="248.567" />

								<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="143.636" y1="246.898" x2="147.034"
								 y2="252.375" />
								<polygon style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="122.32,303.464 112.937,251.858 155.942,237.002 
		166.106,287.826 	" />
							</g>
							<polygon style="fill:#5A6169;" points="688.417,147.605 684.768,151.514 680.076,153.078 671.188,168.736 676.167,166.892 
	678.513,170.41 688.417,154.12 691.023,148.777 " />
							<g>
								<polygon style="fill:#CBD5DF;" points="157.505,237.002 163.226,264.409 190.345,254.986 184.518,228.357 	" />
								<g>
									<path style="fill:#C2C9D4;" d="M162.007,266.444l-6.375-30.198l30.022-9.93l6.522,29.568L162.007,266.444z M157.444,237.266
			l5.725,27.113l27.171-9.51l-5.821-26.495L157.444,237.266z" />
								</g>
								<polygon style="fill:#5E626A;" points="187.413,246.776 167.084,253.617 166.191,262.559 189.379,254.471 	" />
								<polygon style="fill:#13453D;" points="169.813,241.802 179.48,238.363 184.074,247.899 174.495,251.123 	" />
							</g>
							<g>
								<polygon style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="185.263,227.228 202.856,221.755 209.895,248.92 
		192.047,255.096 	" />
								<polygon style="fill:#CBD5DF;" points="185.42,227.872 203.01,222.349 209.895,248.92 192.047,255.096 	" />
								<polygon style="fill:#5E626A;" points="205.86,233.227 193.734,250.555 193.734,254.513 209.763,248.861 	" />
							</g>
							<polygon style="fill:#B8BDC2;" points="244.417,234.378 239.019,290.819 234.107,291.328 239.019,308.351 269.768,297.793 " />
							<g>
								<polygon style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="202.856,221.755 237.39,210.808 244.557,237.523 
		209.763,248.861 	" />
								<polygon style="fill:#CBD5DF;" points="202.856,221.755 236.747,211.012 244.417,237.002 209.763,248.861 	" />
								<g>
									<polygon style="fill:#99A8B1;" points="214.753,224.448 207.748,226.603 210.942,232.267 217.881,229.921 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="209.338" y1="229.422" x2="216.335"
									 y2="227.216" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="211.244" y1="225.547" x2="214.642"
									 y2="231.024" />
									<polygon style="fill:#99A8B1;" points="227.199,219.691 220.194,221.846 223.387,227.51 230.326,225.164 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="221.783" y1="224.666" x2="228.78"
									 y2="222.459" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="223.689" y1="220.79" x2="227.087"
									 y2="226.268" />
								</g>
								<polygon style="fill:#5E606A;" points="211.03,247.657 212.141,238.426 221.328,236.416 232.763,218.53 236.477,218.53 
		242.226,237.087 237.748,238.363 234.62,225.176 227.29,235.731 235.011,239.543 	" />
							</g>
							<g>
								<polygon style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="193.734,275.576 200.901,303.464 257.849,283.134 
		248.336,256.028 	" />
								<polygon style="fill:#CBD5DF;" points="194.646,276.097 201.496,302.573 257.302,283.33 247.717,256.55 	" />
								<polygon style="fill:#5E626A;" points="247.983,257.291 231.884,279.029 204.224,288.51 202.304,302.294 257.302,283.33 	" />
							</g>
							<g>
								<polygon style="fill:none;stroke:#C2C9D4;stroke-miterlimit:10;" points="200.901,303.464 205.201,320.275 239.019,308.351 
		234.106,292.235 	" />
								<polygon style="fill:#CBD5DF;" points="201.696,304.058 205.723,319.463 238.114,308.042 233.479,292.901 	" />
								<polygon style="fill:#5E626A;" points="233.536,293.089 225.734,302.732 206.578,309.314 205.057,319.797 238.114,308.141 	" />
							</g>
							<g>
								<polyline style="fill:#CBD5DF;" points="244.557,238.426 163.077,266.071 177.274,330.619 205.273,320.583 194.373,275.918 
		247.604,256.861 	" />
								<polyline style="fill:none;stroke:#C2C9D4;stroke-width:2;stroke-miterlimit:10;" points="245.665,237.002 162.075,265.059 
		176.816,332.406 205.057,321.979 193.734,275.576 249.036,255.778 	" />
								<path style="fill:#5E626A;" d="M235.696,258.78l-3.388-4.544l-53.43,18.733c0,0-4.253,4.952-2.908,10.425
		c1.344,5.473,4.993,25.281,4.993,25.281l-3.177,20.944l25.07-9.346l-10.68-45.481L235.696,258.78z" />
								<polygon style="fill:#99A8B1;" points="225.26,247.431 228.4,253.065 243.126,247.983 239.868,242.249 	" />

								<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="226.889" y1="250.354" x2="241.511"
								 y2="245.142" />

								<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="232.236" y1="244.956" x2="235.992"
								 y2="250.445" />
							</g>
							<g>
								<g>
									<polygon style="fill:#CBD5DF;" points="243.71,232.245 237.702,209.832 639.401,76.232 628.992,99.648 		" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="251.865,211.747 284.119,200.605 286.855,208.229 255.034,219.617 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="253.595" y1="215.537" x2="285.535"
									 y2="204.55" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="267.727" y1="206.268" x2="270.828"
									 y2="213.965" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="340.587,182.175 370.453,171.858 371.392,180.125 341.368,190.87 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="340.97" y1="186.432" x2="370.659"
									 y2="176.219" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="354.836" y1="177.253" x2="355.612"
									 y2="185.772" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="381.847,168.449 411.712,158.132 412.651,166.399 382.628,177.143 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="382.229" y1="172.705" x2="411.918"
									 y2="162.492" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="396.096" y1="163.527" x2="396.871"
									 y2="172.046" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="423.211,154.12 453.077,143.803 454.016,152.07 423.993,162.815 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="423.594" y1="158.376" x2="453.283"
									 y2="148.163" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="437.46" y1="149.198" x2="438.236"
									 y2="157.717" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="461.916,140.806 491.781,130.489 492.72,138.755 462.697,149.5 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="462.298" y1="145.062" x2="491.987"
									 y2="134.849" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="476.165" y1="135.883" x2="476.94"
									 y2="144.403" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="499.488,128.686 529.354,118.369 530.293,126.636 500.269,137.381 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="499.871" y1="132.943" x2="529.56"
									 y2="122.73" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="513.737" y1="123.764" x2="514.513"
									 y2="132.283" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="297.885,196.766 327.751,186.448 328.69,194.715 298.666,205.46 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="298.268" y1="201.022" x2="327.957"
									 y2="190.809" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="312.134" y1="191.843" x2="312.91"
									 y2="200.363" />
								</g>
								<polygon style="fill:#5F6876;" points="290.689,198.689 288.344,199.34 293.882,214.978 296.165,214.193 	" />
								<polygon style="fill:#5F6876;" points="334.867,183.246 332.521,183.898 334.965,200.057 337.248,199.271 	" />
								<polygon style="fill:#5F6876;" points="375.821,169.368 373.475,170.019 375.919,186.178 378.202,185.393 	" />
								<polygon style="fill:#5F6876;" points="420.224,155.098 417.879,155.749 418.234,171.294 420.518,170.508 	" />
								<polygon style="fill:#5F6876;" points="459.319,141.936 456.974,142.587 456.71,158.132 458.994,157.346 	" />
								<polygon style="fill:#5F6876;" points="497.926,128.64 495.58,129.291 495.316,144.835 497.6,144.049 	" />
								<polygon style="fill:#5F6876;" points="534.465,116.075 532.119,116.727 531.855,132.271 534.139,131.485 	" />
								<g>
									<polygon style="fill:#99A8B1;" points="553.017,110.204 551.975,117.371 558.751,116.075 559.533,109.031 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="552.533" y1="113.532" x2="559.156"
									 y2="112.428" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="556.452" y1="109.583" x2="555.427"
									 y2="116.711" />
								</g>
								<g>
									<polygon style="fill:#99A8B1;" points="563.031,107.277 561.989,114.444 568.765,113.148 569.547,106.104 		" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="562.547" y1="110.605" x2="569.17"
									 y2="109.501" />

									<line style="fill:none;stroke:#68707B;stroke-width:0.25;stroke-miterlimit:10;" x1="566.466" y1="106.656" x2="565.44"
									 y2="113.784" />
								</g>
								<polygon style="fill:#5E626A;" points="584.945,100.625 563.312,120.238 589.115,132.271 596.674,113.201 	" />
							</g>
							<g>
								<polygon style="fill:#CBD5DF;" points="586.458,96.169 595.715,114.696 615.978,105.824 605.486,89.632 	" />
								<polygon style="fill:#B8BDC2;" points="594.994,110.899 596.411,113.605 615.978,105.824 613.711,102.854 	" />
								<polygon style="fill:#5E626A;" points="605.682,94.769 600.052,111.58 616.172,106.032 	" />
								<polygon style="fill:none;stroke:#C2C9D4;stroke-width:2;stroke-miterlimit:10;" points="585.494,95.841 595.702,115.062 
		616.877,106.526 605.791,89.099 	" />
							</g>
							<polygon style="fill:#CBD5DF;" points="595.531,116.084 589.115,132.271 618.732,106.978 " />
							<polygon style="fill:#B8BDC2;" points="243.375,232.292 260.772,284.75 607.546,159.054 589.387,132.271 562.977,120.284 " />
							<line style="fill:none;stroke:#C2C9D4;stroke-width:2;stroke-miterlimit:10;" x1="133.926" y1="347.896" x2="691.069"
							 y2="145.154" />
							<polygon style="fill:#64676D;" points="264.535,299.59 259.146,284.704 605.919,159.007 589.115,132.271 618.625,107.206 
	630.972,123.25 626.115,124.679 649.121,159.583 " />
							<polygon style="fill:#B6BDC3;" points="386.256,243.234 396.288,250.183 433.251,236.757 421.886,229.867 " />
							<g>
								<polygon style="fill:#B6BDC3;" points="290.939,277.654 286.29,279.367 289.636,281.824 294.186,280.134 	" />
								<polygon style="fill:#B6BDC3;" points="283.684,280.361 279.034,282.074 282.38,284.531 286.931,282.842 	" />
								<polygon style="fill:#B6BDC3;" points="276.433,282.939 271.783,284.652 275.129,287.109 279.68,285.419 	" />
								<polygon style="fill:#B6BDC3;" points="269.702,285.532 265.053,287.245 268.399,289.702 272.95,288.013 	" />
								<polygon style="fill:#B6BDC3;" points="295.387,281.364 290.737,283.077 294.083,285.534 298.634,283.845 	" />
								<polygon style="fill:#B6BDC3;" points="288.131,284.072 283.482,285.785 286.828,288.242 291.379,286.552 	" />
								<polygon style="fill:#B6BDC3;" points="280.88,286.649 276.231,288.362 279.577,290.819 284.128,289.13 	" />
								<polygon style="fill:#B6BDC3;" points="274.15,289.243 269.501,290.956 272.847,293.413 277.398,291.723 	" />
							</g>
							<g>
								<polygon style="fill:#B6BDC3;" points="330.523,263.384 325.873,265.097 329.219,267.554 333.77,265.864 	" />
								<polygon style="fill:#B6BDC3;" points="323.267,266.092 318.618,267.805 321.964,270.262 326.515,268.572 	" />
								<polygon style="fill:#B6BDC3;" points="316.016,268.669 311.367,270.382 314.713,272.839 319.264,271.15 	" />
								<polygon style="fill:#B6BDC3;" points="309.286,271.263 304.637,272.976 307.983,275.433 312.534,273.743 	" />
								<polygon style="fill:#B6BDC3;" points="334.97,267.094 330.321,268.808 333.667,271.265 338.218,269.575 	" />
								<polygon style="fill:#B6BDC3;" points="327.715,269.802 323.066,271.515 326.412,273.972 330.963,272.283 	" />
								<polygon style="fill:#B6BDC3;" points="320.464,272.38 315.815,274.093 319.161,276.55 323.712,274.86 	" />
								<polygon style="fill:#B6BDC3;" points="313.734,274.973 309.085,276.686 312.431,279.143 316.981,277.454 	" />
							</g>
							<g>
								<polygon style="fill:#B6BDC3;" points="375.873,246.866 371.224,248.579 374.57,251.036 379.12,249.347 	" />
								<polygon style="fill:#B6BDC3;" points="368.618,249.574 363.969,251.287 367.315,253.744 371.865,252.054 	" />
								<polygon style="fill:#B6BDC3;" points="361.367,252.151 356.717,253.864 360.063,256.321 364.614,254.632 	" />
								<polygon style="fill:#B6BDC3;" points="354.636,254.745 349.987,256.458 353.333,258.915 357.884,257.225 	" />
								<polygon style="fill:#B6BDC3;" points="380.321,250.577 375.672,252.29 379.017,254.747 383.568,253.057 	" />
								<polygon style="fill:#B6BDC3;" points="373.066,253.284 368.416,254.998 371.762,257.455 376.313,255.765 	" />
								<polygon style="fill:#B6BDC3;" points="365.814,255.862 361.165,257.575 364.511,260.032 369.062,258.342 	" />
								<polygon style="fill:#B6BDC3;" points="359.084,258.456 354.435,260.169 357.781,262.626 362.332,260.936 	" />
							</g>
							<g>
								<polygon style="fill:#B6BDC3;" points="461.882,215.721 457.233,217.434 460.579,219.891 465.13,218.201 	" />
								<polygon style="fill:#B6BDC3;" points="454.627,218.429 449.978,220.142 453.324,222.599 457.874,220.909 	" />
								<polygon style="fill:#B6BDC3;" points="447.376,221.006 442.727,222.719 446.073,225.176 450.623,223.487 	" />
								<polygon style="fill:#B6BDC3;" points="440.646,223.6 435.996,225.313 439.342,227.77 443.893,226.08 	" />
								<polygon style="fill:#B6BDC3;" points="466.33,219.431 461.681,221.145 465.027,223.602 469.577,221.912 	" />
								<polygon style="fill:#B6BDC3;" points="459.075,222.139 454.425,223.852 457.771,226.309 462.322,224.62 	" />
								<polygon style="fill:#B6BDC3;" points="451.824,224.717 447.174,226.43 450.52,228.887 455.071,227.197 	" />
								<polygon style="fill:#B6BDC3;" points="445.093,227.31 440.444,229.023 443.79,231.48 448.341,229.791 	" />
							</g>
							<g>
								<polygon style="fill:#B6BDC3;" points="501.816,200.605 497.166,202.318 500.512,204.775 505.063,203.086 	" />
								<polygon style="fill:#B6BDC3;" points="494.56,203.313 489.911,205.026 493.257,207.483 497.808,205.794 	" />
								<polygon style="fill:#B6BDC3;" points="487.309,205.89 482.66,207.604 486.006,210.061 490.557,208.371 	" />
								<polygon style="fill:#B6BDC3;" points="480.579,208.484 475.93,210.197 479.276,212.654 483.827,210.965 	" />
								<polygon style="fill:#B6BDC3;" points="506.263,204.316 501.614,206.029 504.96,208.486 509.511,206.796 	" />
								<polygon style="fill:#B6BDC3;" points="499.008,207.024 494.359,208.737 497.705,211.194 502.256,209.504 	" />
								<polygon style="fill:#B6BDC3;" points="491.757,209.601 487.108,211.314 490.454,213.771 495.004,212.082 	" />
								<polygon style="fill:#B6BDC3;" points="485.027,212.195 480.378,213.908 483.724,216.365 488.274,214.675 	" />
							</g>
							<g>
								<polygon style="fill:#B6BDC3;" points="540.951,186.448 536.301,188.162 539.647,190.619 544.198,188.929 	" />
								<polygon style="fill:#B6BDC3;" points="533.695,189.156 529.046,190.869 532.392,193.326 536.943,191.637 	" />
								<polygon style="fill:#B6BDC3;" points="526.444,191.734 521.795,193.447 525.141,195.904 529.692,194.214 	" />
								<polygon style="fill:#B6BDC3;" points="519.714,194.327 515.065,196.04 518.411,198.497 522.962,196.808 	" />
								<polygon style="fill:#B6BDC3;" points="545.398,190.159 540.749,191.872 544.095,194.329 548.646,192.64 	" />
								<polygon style="fill:#B6BDC3;" points="538.143,192.867 533.494,194.58 536.84,197.037 541.391,195.347 	" />
								<polygon style="fill:#B6BDC3;" points="530.892,195.444 526.243,197.157 529.589,199.614 534.14,197.925 	" />
								<polygon style="fill:#B6BDC3;" points="524.162,198.038 519.513,199.751 522.859,202.208 527.409,200.518 	" />
							</g>
							<g>
								<polygon style="fill:none;stroke:#C2C9D4;stroke-width:2;stroke-miterlimit:10;" points="638.831,77.546 664.569,111.429 
		632.12,123.704 606.121,88.948 	" />
								<polygon style="fill:#B8BDC2;" points="618.083,103.174 628.992,99.179 643.588,117.9 632.205,122.283 	" />
								<polygon style="fill:#5E626A;" points="623.323,96.768 620.587,105.635 631.374,120.375 634.074,111.233 	" />
								<polygon style="fill:#5E626A;" points="638.014,78.945 628.992,99.179 643.588,117.9 662.223,110.647 	" />
							</g>
							<g>
								<g>
									<path style="fill:#C2C9D4;" d="M649.391,159.992l-23.275-35.312l39.298-14.645l27.769,35.526L649.391,159.992z M630.889,126.237
			l19.796,30.035l37.562-12.379l-23.872-30.132L630.889,126.237z" />
								</g>
								<polygon style="fill:#CBD5DF;" points="630.491,126.265 663.722,112.98 644.956,150.113 	" />
								<polygon style="fill:#64676D;" points="655.641,128.446 663.722,112.98 688.612,143.956 651.065,156.987 643.262,144.607 	" />
								<polygon style="fill:#C2C9D4;" points="635.671,136.333 666.458,124.173 642.024,144.835 	" />
								<polygon style="fill:#5F6976;" points="633.618,130.012 647.953,125.842 655.251,118.369 651.065,128.709 636.424,134.719 	" />
							</g>
							<g>
								<polygon style="fill:#C2C9D4;" points="131.405,348.852 146.404,363.83 676.158,166.855 678.504,170.374 683.847,168.55 
		695.576,150.077 692.848,145.524 	" />
								<g>

									<line style="fill:none;stroke:#65686E;stroke-width:0.5;stroke-miterlimit:10;" x1="409.219" y1="250.522" x2="407.406"
									 y2="264.973" />

									<line style="fill:none;stroke:#65686E;stroke-width:0.5;stroke-miterlimit:10;" x1="634.602" y1="167.997" x2="632.789"
									 y2="182.448" />
									<polygon style="fill:#B6BDC3;" points="411.966,265.088 413.91,248.372 456.915,233.712 454.425,249.298 		" />
									<polygon style="fill:#64686D;" points="438.833,239.876 454.425,249.298 456.915,233.712 		" />
									<polygon style="fill:#99A8B1;" points="367.575,266.805 366.41,275.821 401.839,262.358 402.956,253.121 		" />
									<polygon style="fill:#99A8B1;" points="324.877,282.695 323.712,291.711 359.14,278.248 360.258,269.011 		" />
									<polygon style="fill:#99A8B1;" points="279.057,300.126 277.892,309.143 313.321,295.679 314.439,286.443 		" />
									<polygon style="fill:#99A8B1;" points="550.612,200.651 549.447,209.668 584.876,196.204 585.993,186.968 		" />
									<polygon style="fill:#99A8B1;" points="507.913,216.541 506.748,225.558 542.177,212.094 543.294,202.858 		" />
									<polygon style="fill:#99A8B1;" points="462.094,233.973 460.929,242.989 496.358,229.526 497.475,220.289 		" />
								</g>
							</g>
						</svg>

					</div>
				</div>
				<div data-options="region:'south'" style="height:265px;">
					<div class="bottom">
						<div class="left-chart">
							<h3>风险分布</h3>
							<div id="blChart" class="chart-box"></div>
						</div>
						<div class="right-chart">
							<h3>风险趋势</h3>
							<div id="brChart" class="chart-box"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="tooltip" id="tooltip">
		<span class="cornerT"></span>
		<div class="topRec_List">
			 <table border="0" cellspacing="0" cellpadding="0" id="zhanshiquyuxiaogu">
			<!--	<tr>
					<th>监区</th>
					<th>风险值</th>
					<th>区域格格长</th>
					<th>安全管理员</th>
					<th>党委成员</th>
				</tr>
				<tr>
					<td><a href="javascript:void(0)" data-id="first">一监区</a></td>
					<td>920</td>
					<td>A</td>
					<td>A</td>
					<td>副监狱长:刘磊</td>
				</tr>
				<tr>
					<td>二监区</td>
					<td>920</td>
					<td>A</td>
					<td>A</td>
					<td>副监狱长:刘磊</td>
				</tr>
				<tr>
					<td>一监区</td>
					<td>920</td>
					<td>A</td>
					<td>A</td>
					<td>副监狱长:刘磊</td>
				</tr>
				<tr>
					<td>一监区</td>
					<td>920</td>
					<td>A</td>
					<td>A</td>
					<td>副监狱长:刘磊</td>
				</tr>-->
			</table> 
		</div>
		<span class="cornerY"></span>
		<span class="cornerB"></span>
		<span class="cornerB1"></span>
	</div>

	<!-- 隐藏域 -->
	<div style="display: none;">
		<!-- 根路径 -->
		<input id="rootPath" name="rootPath" value="${ctx}" />
	</div>
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
	
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
	<%-- <script src="${ctx}/static/bj-cui/js/base.js" type="text/javascript"></script> --%>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/js/callback/callback.js"></script>
	<script src="${ctx}/static/js/sgzf/base64.js"></script>
	
	<script type="text/javascript">
		/** 菜单显示方法*/
		$(".icon-htmal5icon35").click(function (e) {
			e.stopPropagation();
			$(".menu-container").toggleClass("active");
		})
		var target = $("body").not(".menu-wrapper");
		target = target.not(".icon-htmal5icon35")
		target.on("click", function (e) {
			var target = $(e.target).parents(".menu-wrapper");
			if (target.length) {
				return;
			}
			$(".menu-container").removeClass("active");
		})
		/** 鼠标移动到svg上显示提示方法*/
		var svgViewer = document.getElementById("svg");
		var old = '';

		function offsetXY(e) {
			var stateTipX = -15,
				stateTipY = -25;
			var mouseX, mouseY, tipWidth = $('.tooltip').outerWidth(),
				tipHeight = $('.tooltip').outerHeight();
			if (e && e.pageX) {
				mouseX = e.pageX;
				mouseY = e.pageY;
			} else {
				mouseX = event.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
				mouseY = event.clientY + document.body.scrollTop + document.documentElement.scrollTop;
			}
			mouseX = mouseX - tipWidth / 2 + stateTipX < 0 ? 0 : mouseX - tipWidth + stateTipX;
			mouseY = mouseY - tipHeight + stateTipY < 0 ? mouseY - stateTipY : mouseY - tipHeight + stateTipY;
			return [mouseX, mouseY];
		};
		$(".content").mouseenter(function (e) {
			var id = $(e.currentTarget).attr("id");
			console.log("id"+id);
				//topRec_List    
				$("#zhanshiquyuxiaogu").html("");
				 $.ajax({
							type : 'post',
							url : $("#rootPath").val() + "/aqfxyp/txzs/wgfb?type="+id,
							dataType : 'json',
							async:false,
							success : function(data) {
								var dada = data.data;
				                	var appoundStr="";
				                	appoundStr = appoundStr+"<tr>"+
												"<th>监区</th>"+
												"<th>风险值</th>"+
												"<th>管理理格长</th>"+
												"<th>区域格格长</th>"+
												"<th>责任格成员</th>"+
												"<th>操作</th>"+
											"</tr>";
				                	for(var i = 0;i<dada.length;i++){
										var map = dada[i];
										var zs = "";
										if(id=='32'){
											zs = "<td><a href=\"javascript:void(0)\" onclick=\"goxyb2(event)\" data-id=\"first\">"+map.name+"</a></td>";
										 }else{
											 zs = "<td><a href=\"javascript:void(0)\" onclick=\"goxyb(event)\" data-id=\"first\">"+map.name+"</a></td>";
										} 
											appoundStr = appoundStr+"<tr>"+zs+
											"<td>"+map.fxz+"</td>"+
											"<td>"+map.qyggz+"</td>"+
											"<td>"+map.aqgly+"</td>"+
											"<td>"+map.dwcy+"</td>"+
											"<td><a href=\"javascript:void(0)\" onclick=\"openCj(event, 'fxcj','"+map.code+"')\">安全管控</a></td>"+
										"</tr>";
									}
				                	$("#zhanshiquyuxiaogu").html(appoundStr);
				                	
				               
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								console.log(textStatus);
							}
						});
			if (old == id) {
				var a = svgViewer.getElementById(id);

				var show = $(".tooltip").css("display");
				var flag = a.getAttribute("stroke");
				if ((flag == null || flag == "none")) {
					a.setAttribute("stroke", "#0affe9");
				}
				if (show == "none") {
					$(".tooltip").show();
				}
				return
			}
			if (id && id !== "tooltip") {
				var a = svgViewer.getElementById(id);
				a.setAttribute("stroke", "#0affe9");
				var _offsetXY = offsetXY(e);

				$('.tooltip').css({
					left: _offsetXY[0],
					top: _offsetXY[1]
				}).show();

			}
			old = id
		})
		$(".content1").mouseenter(function (e) {
			var svgViewer1 = document.getElementById("svg2");
			var id = $(e.currentTarget).attr("id");
			var a = svgViewer1.getElementById(id);
			a.setAttribute("stroke", "#0affe9");
			console.log("id"+id);
				//topRec_List    
				$("#zhanshiquyuxiaogu").html("");
				 $.ajax({
							type : 'post',
							url : $("#rootPath").val() + "/aqfxyp/txzs/wgfb?type="+id,
							dataType : 'json',
							async:false,
							success : function(data) {
								var dada = data.data;
				                	var appoundStr="";
				                	appoundStr = appoundStr+"<tr>"+
				                	"<th>班级</th>"+
												"<th>基础格表组成员</th>"+
												"<th>值班人员</th>"+
											"</tr>";
				                	for(var i = 0;i<dada.length;i++){
										var map = dada[i];
										var zs = "";
										//if(i==0&&id=="28"){
											zs = "<td>"+map.name+"</td>";
										/* }else{
											zs = "<td>"+map.name+"</td>";
										} */
											appoundStr = appoundStr+"<tr>"+zs+
											"<td>"+map.jcgbzcy+"</td>"+
											"<td>"+map.zbry+"</td>"+
										"</tr>";
									}
				                	$("#zhanshiquyuxiaogu").html(appoundStr);
				                	
				               
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								console.log(textStatus);
							}
						});
				
			if (old == id) {
				var a = svgViewer1.getElementById(id);

				var show = $(".tooltip").css("display");
				var flag = a.getAttribute("stroke");
				if ((flag == null || flag == "none")) {
					a.setAttribute("stroke", "#0affe9");
				}
				if (show == "none") {
					$(".tooltip").show();
				}
				return
			}
			if (id && id !== "tooltip") {
				var a = svgViewer1.getElementById(id);
				a.setAttribute("stroke", "#0affe9");
				var _offsetXY = offsetXY(e);

				$('.tooltip').css({
					left: _offsetXY[0],
					top: _offsetXY[1]
				}).show();

			}
			old = id
		})
		$(".show1").mouseleave(function (e) {
			var id = $(e.currentTarget).attr("id")
			var list = document.getElementsByClassName("content1");
			for (var i = 0; i < list.length; i++) {
				list[i].setAttribute("stroke", "none")
			}
		})
		$(".content1").click(function (e) {
			var svgViewer1 = document.getElementById("svg2");
			var id = $(e.currentTarget).attr("id");
			alert(id)
		})
		$("#tooltip").on("mouseenter", function () {
			$(".tooltip").show();
		})
		$("#tooltip").on("mouseleave", function (e) {
			var id = $(e.currentTarget).attr("id");
			if (e.toElement && e.toElement.id !== "tooltip") {
				$(".tooltip").hide();
			}
		})
		// $("#svg").mouseenter(debounce(handle,100))
		$(".show").mouseleave(function (e) {
			var id = $(e.currentTarget).attr("id")
			var list = document.getElementsByClassName("content");
			for (var i = 0; i < list.length; i++) {
				list[i].setAttribute("stroke", "none")
			}
			$(".tooltip").hide();
		})
		$(".topRec_List td>a").on("click", function (e) {
			$(".tooltip").hide();
			$(".back").removeClass("hide")
			if ($(".center").hasClass("bigMore")) {
				$(".center").removeClass("bigMore").addClass("small");
				$(".center").siblings(".hide").removeClass("small")
			}
			$(".cls-second").removeClass("hide").addClass("bigMore")

		})
		$(".back").on("click", function () {
			$(".back").addClass("hide")
			$(".cls-second").removeClass("bigMore").addClass("small").addClass("hide");
			$(".cls-third").removeClass("bigMore").addClass("small").addClass("hide");
			$(".cls-first").addClass("bigMore").removeClass("small").removeClass("hide");
		})
		/**组件库初始化方法*/
		$('#layout1').layout({
			fit: true, //属性: 值
			onCreate: function () { //回调事件: 值
			}
		});
		$('#layout-child').layout({
			fit: true, //属性: 值
			onCreate: function () { //回调事件: 值

			}
		});
		$.parseDone(function () {
			$(".center:first").addClass("bigMore");
			
			jsConst.basePath = "${ctx}/";
			initUserInfo();
		})
		
		function showDqyh() {
		$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME+ "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
	}
		function goxyb(){
			$(".tooltip").hide();
			$(".back").removeClass("hide")
			if ($(".center").hasClass("bigMore")) {
				$(".center").removeClass("bigMore").addClass("small");
				$(".center").siblings(".hide").removeClass("small")
			}
			$(".cls-second").removeClass("hide").addClass("bigMore")
		}
		function goxyb2(){
			$(".tooltip").hide();
			$(".back").removeClass("hide")
			if ($(".center").hasClass("bigMore")) {
				$(".center").removeClass("bigMore").addClass("small");
				$(".center").siblings(".hide").removeClass("small")
			}
			$(".cls-third").removeClass("hide").addClass("bigMore")
			
		}
		function openZnafpt() {
			
			var url = "${ctx}/portal/bj/shouye";
			window.location.href = url;
		}
		function openCj(event, name,code){
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
			if (name == 'fxcj') {
				url = jsConst.basePath + '/aqfxyp/fxcj/toIndex';
				w = 1000;
				h = 800;
			} 
			$('#dialog').html("");
			//$('#dialog').dialog("destroy");
			$('#dialog').dialog({
				width : w,
				height : h,
				title : "风险采集",
				url : url
			});
			$("#dialog").dialog("open");
			
		}
		function openFxpgDialog(event, name,zhi) {
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
			if (name == 'fxcj') {
				url = jsConst.basePath + '/aqfxyp/fxcj/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'wwjgwh') {
				url = jsConst.basePath + '/wwjg/wwjgwh/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'sjlywh') {
				url = jsConst.basePath + '/wwjg/sjlywh/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'fxdjwh') {
				url = jsConst.basePath + '/wwjg/fxdjwh/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'qzdjwh') {
				url = jsConst.basePath + '/wwjg/qzdjwh/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'sjfwgl') {
				url = jsConst.basePath + '/wwjg/sjfwgl/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'fxdgl') {
				url = jsConst.basePath + '/wwjg/fxdgl/toIndex';
				w = 1000;
				h = 800;
			} else if (name == 'pgtjgl') {
				url = jsConst.basePath + '/wwjg/pgtjgl/toIndex';
				w = 1000;
				h = 800;
			}else if (name == 'fxgkgl') {
				url = jsConst.basePath + '/wwjg/fxgkgl/toIndex';
				w = 1000;
				h = 800;
			}
			
			$('#dialog').html("");
			//$('#dialog').dialog("destroy");
			$('#dialog').dialog({
				width : w,
				height : h,
				title : zhi,
				url : url
			});
			$("#dialog").dialog("open");
		}
		function ryglfxfx(){
			var url = "${ctx}/portal/sjfxyp/ryglfxfx";
			window.open(url, "_blank");
		}
		
		function fhjzcyz (){
			
			zxym();
		}
		$(function(){
			zxym();
		});
		
		
		function zxym(){
			
			var blChart,brChart,ltChart,pieChartList=[],barChartList=[];
			/**
			 * Description: 初始化页面底部-风险分布图表数据
			 */
			function initBottomChart(){
				blChart = echarts.init(document.getElementById('blChart'));

				var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxfbData";
			    var params = {};
				// Desc: 调用ajax获取数据库中的风险分布数据
				$.ajax({
					type : 'post',
					url : urlPath,
					data : params,
					dataType : 'json',
					success : function(data) {
		                if(data.code == 200) {
		                	var option = initBottomChartOption(data.data);
		            		blChart.setOption(option);
		            		blChart.on("click", function (param){
		            			var jQName
		            			if(param.name) {
		            				jQName = new Base64().multiEncode(param.name, 2);
		            			}
		            			$("#dialog").dialog({
		            				width : 1000, //属性
		            				height : 800, //属性
		            				title : '风险采集',
		            				modal : true, //属性
		            				autoOpen : false,
		            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?wwName=&jQName="+jQName
		            			});
		            			$("#dialog").dialog("open");
		    				})
		                } else if (data.code == 500){
		                	console.log(data.data);
		                }
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
			    
				
			}
			
			/**
			 * Description: 初始化页面底部-风险趋势图表数据
			 */
			function initBottomChart2(){
				brChart = echarts.init(document.getElementById('brChart'));
				var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxqsData";
			    var params = {};
				// Desc: 调用ajax获取数据库中的风险趋势数据
				$.ajax({
					type : 'post',
					url : urlPath,
					data : params,
					dataType : 'json',
					success : function(data) {
                        if(data.code == 200) {
                            var dataOption=data.data;  var date;
                            var option = initBottomChart2Option(dataOption);
                            brChart.setOption(option);
                            brChart.on("click", function (param){

                                for(var i=0;i<dataOption.length;i++){
                                    if(param.name==dataOption[i].name){
                                        date=dataOption[i].score;
                                    }
                                }
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName=&date="+date
                                });
                                $("#dialog").dialog("open");
                            });
                        } else if (data.code == 500){
                            console.log(data.data);
                        }
					},

					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
			}
			
			/**
			 * Description: 初始化首页面左侧风险概况图表数据
			 */
			function initLeftChart() {
				ltChart = echarts.init(document.getElementById('ltChart'));
			    var params = {};
				// Desc: 调用ajax获取数据库中的风险概况数据
		        $.ajax({
					type : 'post',
					url : $("#rootPath").val() + '/aqfxyp/txzs/searchFxgk',
					dataType : 'json',
					async:false,
					success : function(data) {
						var option = initLeftChartOption(data.data);
						ltChart.setOption(option);
				 
					}, 
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				
			}
			
			/**
			 * Description: 初始化饼图数据
			 */
			function initPies() {

				$.ajax({
					type : 'post',
					url : $("#rootPath").val() + '/aqfxyp/txzs/getPies',
					dataType : 'json',
					async:false,
					success : function(data) {
						 var dada = data.data;
						 for(var i = 0;i<dada.length;i++){
						 var wwjgname = dada[i].wwjgname;
						 var wwjgjg = dada[i].wwjgjg;
						 if(wwjgname=="人"){
						    var option = initPiesOption(wwjgjg, "人");
		            		var pieChart = echarts.init(document.getElementById("left-pie1"));
		            		pieChart.setOption(option);
		            		pieChart.on("click", function (param){
		            				wwName = new Base64().multiEncode("人", 2);
		            			$("#dialog").dialog({
		            				width : 1000, //属性
		            				height : 800, //属性
		            				title : '风险采集',
		            				modal : true, //属性
		            				autoOpen : false,
		            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
		            			});
		            			$("#dialog").dialog("open");
		            		});
		            		pieChartList.push(pieChart);
						 }
						if(wwjgname=="地"){
							var option = initPiesOption(wwjgjg, "地");
		            		var pieChart = echarts.init(document.getElementById("left-pie2"));
		            		pieChart.setOption(option);
		            		pieChartList.push(pieChart);	
		            		pieChart.on("click", function (param){
		            				wwName = new Base64().multiEncode("地", 2);
		            			$("#dialog").dialog({
		            				width : 1000, //属性
		            				height : 800, //属性
		            				title : '风险采集',
		            				modal : true, //属性
		            				autoOpen : false,
		            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
		            			});
		            			$("#dialog").dialog("open");
		            		});
						}
						if(wwjgname=="事"){
							var option = initPiesOption(wwjgjg, "事");
		            		var pieChart = echarts.init(document.getElementById("left-pie3"));
		            		pieChart.setOption(option);
		            		pieChartList.push(pieChart);
		            		pieChart.on("click", function (param){
	            				wwName = new Base64().multiEncode("事", 2);
	            			$("#dialog").dialog({
	            				width : 1000, //属性
	            				height : 800, //属性
	            				title : '风险采集',
	            				modal : true, //属性
	            				autoOpen : false,
	            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
	            			});
	            			$("#dialog").dialog("open");
	            		});
						}
						if(wwjgname=="物"){
							var option = initPiesOption(wwjgjg, "物");
		            		var pieChart = echarts.init(document.getElementById("left-pie4"));
		            		pieChart.setOption(option);
		            		pieChartList.push(pieChart);
		            		pieChart.on("click", function (param){
	            				wwName = new Base64().multiEncode("物", 2);
	            			$("#dialog").dialog({
	            				width : 1000, //属性
	            				height : 800, //属性
	            				title : '风险采集',
	            				modal : true, //属性
	            				autoOpen : false,
	            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
	            			});
	            			$("#dialog").dialog("open");
	            		});
						}
						if(wwjgname=="情"){
							var option = initPiesOption(wwjgjg, "情");
		            		var pieChart = echarts.init(document.getElementById("left-pie5"));
		            		pieChart.setOption(option);
		            		pieChartList.push(pieChart);
		            		pieChart.on("click", function (param){
	            				wwName = new Base64().multiEncode("情", 2);
	            			$("#dialog").dialog({
	            				width : 1000, //属性
	            				height : 800, //属性
	            				title : '风险采集',
	            				modal : true, //属性
	            				autoOpen : false,
	            				url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
	            			});
	            			$("#dialog").dialog("open");
	            		});
						}
						
					}
				 
					}, 
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
			}
			
			 
			function initBar() {
				 
				
				$.ajax({
					type : 'post',
					url : $("#rootPath").val() + '/aqfxyp/txzs/getBars',
					dataType : 'json',
					async:false,
					success : function(data) {
						 var dada = data.data;
						 console.log(dada);
						 for(var i = 0;i<dada.length;i++){
						 var wwjgname = dada[i].wwjgname;
						 var wwjgjg = dada[i].wwjgjg;
						 if(wwjgname=="人"){
						    var option = initBarOption(wwjgjg, "人");
		            		var barChart = echarts.init(document.getElementById("right-pie1"));
		            		barChart.setOption(option);
		            		barChartList.push(barChart);
						 }
						if(wwjgname=="地"){
							 var option = initBarOption(wwjgjg, "地");
			            		var barChart = echarts.init(document.getElementById("right-pie2"));
			            		barChart.setOption(option);
			            		barChartList.push(barChart);		 
						}
						if(wwjgname=="事"){
							 var option = initBarOption(wwjgjg, "事");
			            		var barChart = echarts.init(document.getElementById("right-pie3"));
			            		barChart.setOption(option);
			            		barChartList.push(barChart);
						}
						if(wwjgname=="物"){
							 var option = initBarOption(wwjgjg, "物");
			            		var barChart = echarts.init(document.getElementById("right-pie4"));
			            		barChart.setOption(option);
			            		barChartList.push(barChart);
						}
						if(wwjgname=="情"){
							 var option = initBarOption(wwjgjg, "情");
			            		var barChart = echarts.init(document.getElementById("right-pie5"));
			            		barChart.setOption(option);
			            		barChartList.push(barChart);
						}
						
					}
				 
					}, 
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
			}
			
			 
			// Description: 风险评估图
			initPies();
		 
			
			// Description: 风险指向图
			initBar();
			 
			initLeftChart();
			
			// Description: 风险分布图
			initBottomChart();
			
			// Description: 风险趋势图
			initBottomChart2();
			
			/**
			 * Description: 页面大小调整时，显示数据图表大小随之调整
			 */
			window.onresize = function () {
				setTimeout(function () {
					blChart.resize();
					brChart.resize();
					ltChart.resize();
					var length = pieChartList.length;
					for(var j=length; j--;) {
						pieChartList[j].resize();
						barChartList[j].resize();
					}
				},200);
			}
		}
		/**
		 * Desc:初始化风险分布图Option
		 */
		function initBottomChartOption(chartData) {
			var xData = [], yData = [], option;
			
			chartData.map(function(a, b) {
				xData.push(a.name);
				yData.push(a.value);
			});
			option = {
				backgroundColor:"transparent",
				color: ['#3398DB'],
				tooltip: {
					trigger: 'axis',
				},
				legend: {
					show: false
				},
				grid: {
					left: '0%',
					right: '0%',
					bottom: '10%',
					top: '15%',
					height: '81%',
					containLabel: true
				},
				xAxis: [{
					type: 'category',
					data: xData,
					interval:1,
					axisTick: {
						alignWithLabel: true
					},
					axisLine: {
						lineStyle: {
							color: '#0c3b71'
						}
					},
					axisLabel: {
						show: true,
						color: '#aeafb0',
						fontSize:12,
						interval:0,
						rotate:40
					}
				}],
				yAxis: [{
					type: 'value',
					splitLine: {
						show: false
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						show: false
					},
					splitArea: {
						show: true,
						areaStyle: {
							color: 'transparent'
						}
					}
				}],
				series: [{
					name: '总分',
					type: 'bar',
					barWidth: 35,
					xAxisIndex: 0,
					barCategoryGap:3,
					yAxisIndex: 0,
					itemStyle: {
						normal: {
							color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: '#01f5f7'
							},
							{
								offset: 1,
								color: '#125375'
							}])
						}
					},
					label: {
						normal: {
							show: true,
							position: 'top',
							color:"#a6a7a9",
							fontSize:12
						}
					},
					data: yData,
					zlevel: 11
				}]
			};
			return option;
		}
		/**
		 * desc: 初始化风险趋势图Option
		 */
		function initBottomChart2Option(chartData) {
			var xData = [], yData = [], option;
			
			chartData.map(function(a, b) {
				xData.push(a.name);
				yData.push(a.value);
			});
			option = {
				backgroundColor: "transparent",
				tooltip: {
				},
				grid: {
					left: '0%',
					right: '3%',
					bottom: '10%',
					top: '15%',
					height: '82%',
					containLabel: true
				},
				xAxis: [{
					type: 'category',
					interval:0,
					boundaryGap: false,
					axisLine: { //坐标轴轴线相关设置。数学上的x轴
						show: true,
						lineStyle: {
							color: '#233e64'
						}
					},
					axisLabel: { //坐标轴刻度标签的相关设置
						textStyle: {
							color: '#a6a6a7',
							margin:15,
						}
					},
					axisTick: {
						show: false
					},
					data: xData
				}],
				yAxis: [{
					type: 'value',
					splitNumber: 5,
					splitLine: {
						show: true,
						lineStyle: {
							color: '#233e64'
						}
					},
					axisLine: {
						show: false
					},
					axisLabel: {
						margin:20,
						textStyle: {
							color: '#a6a6a7'
						}
					},
					axisTick: {
						show: false
					}
				}],
				series: [{
					name: '扣分',
					type: 'line',
					smooth: false, //是否平滑曲线显示
					symbol:'circle',  // 默认是空心圆（中间是白色的），改成实心圆
					
					label: {
						normal: {
							show: true,
							position: 'top',
							distance:10,
							color:"#3deaff",
							fontSize:12
						}
					},
					lineStyle: {
						normal: {
							//  color: "#3deaff"   // 线条颜色
						}
					},
					itemStyle: {
						normal: {
							color: "#3deaff",  // 会设置点和线的颜色，所以需要下面定制 line
						}
					},
					areaStyle: { //区域填充样式
						normal: {
							//线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
							color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,  color: 'rgba(61,234,255, 0.9)'
							}, 
							{
								offset: 0.7,  color: 'rgba(61,234,255, 0.1)'
							}], false),
							shadowColor: 'rgba(53,142,215, 0.9)', //阴影颜色
							shadowBlur: 5//shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
						}
					},
					data: yData
				}]
			};
			return option;
		}
		/**
		 * desc: 初始化条形图Option
		 */
		function initBarOption(chartData) {
			var yData = [], seriesData = [], valData = [];
			var myColor = ['#33b7e0', '#33b7e0', '#33b7e0', '#33b7e0'];
			chartData.map(function(a, b) {
				yData.push(a.name);
				seriesData.push(a.value);
				valData.push(a.score);
			});
			var option = {
				backgroundColor: 'transparent',
				grid: {
					left: '30%',
					right: '17%',
					bottom: '0',
					top: '3%'
					// z: 22
				},
				xAxis: {
					show: false
				},
				yAxis: [{
					show: true,
					data: yData,
					inverse: true,
					axisLine: {
						show: false
					},
					splitLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						textStyle: {
							fontSize: 14,
							color: '#fff',
						}
					},
				}, {
					show: true,
					inverse: true,
					data: seriesData,
					axisLabel: {
						textStyle: {
							fontSize: 14,
							color: '#fff',
						},
					},
					axisLine: {
						show: false
					},
					splitLine: {
						show: false
					},
					axisTick: {
						show: false
					}
				}],
				series: [{
					name: '条',
					type: 'bar',
					yAxisIndex: 1,
					data: seriesData,
					barWidth: 6,
					barCategoryGap:2,
					itemStyle: {
						normal: {
							barBorderRadius: 20,
							color: function(params) {
								var num = myColor.length;
								return myColor[params.dataIndex % num]
							}
						}
					},
					label: {
						normal: {
							show: false
						}
					}
				}]
			};
			return option;
		}
		/**
		 * desc: 初始化风险概况图Option
		 */
		function initLeftChartOption(dada) {
			
			 var seriesData ;
		     var radarData;
		     var zf;
			/*fxgkData.map(function(a, b) {
				total += a.value;
				seriesData.push(a.value);
				var radar = {
					name:a.name,
					num:a.score
				};
				radarData.push(radar);
			});
			*/
		     radarData = dada[0];
				seriesData = dada[2];
				zf = dada[1];
			var option = {
				grid: {
					left: '-5%',
					right: '-5%',
					bottom: '0',
					top: '1%',
					// z: 22
				},
				backgroundColor: 'transparent',
				title: {
					text: zf,
					x: '42%',
					y: 'center',
					textStyle: {
						color: "#fff",
						fontSize: 40
					},
					zlevel:24
				},
				radar: [{
					title: {
						text: zf,
					},
					indicator: radarData,
					center: ['55%', '50%'],
					shape: 'polygon',
					radius: '80%',
					nameGap: 3,
					name: {
						formatter:function (value, obj) {
							var num = obj.num
							return '{value|' + value + '}'+' {num|'+ num +'}';
						},
						rich:{
							num: {
								color: "#ee8c04",
								fontSize: 12,
								// padding: [0, 2],
								align: 'center'
							},
							value: {
								color: "#c9caca",
								fontSize: 12 ,
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
			return option;
		}
		/**
		 * desc: 初始化饼图Option
		 */
		function initPiesOption(fxpgData, title) {
			var echartData = [], rate = 0;
			fxpgData.fxpgList.map(function(a, b) {
				rate += a.value;
				var pieData = {
					name:a.name,
					value:a.value
				};
				echartData.push(pieData);
			});
			var scale = 1;
			var rich = {
				rate: {
					color: "#00ffff",
					fontSize: 18 ,
					// padding: [0, 2],
					align: 'center'
				},
				total: {
					color: "#c9caca",
					fontSize: 18 ,
					align: 'center'
				}
			};
			var option = {
				grid: {
					bottom: '2%',
					top: '2%'
				},
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b}: {c}"
				},
				backgroundColor: 'transparent',
				title: {
					text:title,
					left:'center',
					top:'10%',
					padding:[24,0],
					textStyle:{
						color:'#c9caca',
						fontSize:16,
						align:'center'
					}
				},
				legend: {
					selectedMode:false,
					formatter: function(name) {
						//var total = fxpgData.score; //各科正确率总和
						// var rate = 1000;
						var averagePercent; //综合正确率
						
						return '{rate|' + (parseFloat(rate)).toFixed(2) + '}';
					},
					data: [echartData[0].name],
					// data: ['高等教育学'],
					// itemGap: 50,
					left: 'center',
					top: '45%',
					icon: 'none',
					align:'center',
					textStyle: {
						color: "#c9caca",
						fontSize: 16 * scale,
						rich: rich
					},
				},
				series: [{
					name: '扣分',
					type: 'pie',
					radius: ['86%', '71%'],
					hoverAnimation: false,
					color: ['#457ab5', '#457ab5', '#457ab5', '#457ab5', '#457ab5'],
					label:{
						normal: {
							show: false
						},
					},
					itemStyle: {
						normal: {
							borderWidth: 1,
							borderColor: '#000',
						}
					},
					data: echartData
				}]
			};
			return option;
		}
		
		
		
	</script>
</body>

</html>
