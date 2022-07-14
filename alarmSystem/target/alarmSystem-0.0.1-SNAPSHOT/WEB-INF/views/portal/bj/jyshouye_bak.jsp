<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

<head>
	<meta charset="utf-8">
	<title></title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/shouye.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
</head>

<body>
	
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
	
	<header class="perspective">
		<img src="${ctx}/static/bj-cui/img/header_logo.png" alt="指挥中心logo" class="logo">
		<div class="header-content">
			<div class="header-item">
				<span class="icon iconfont icon-police2"></span>
				<span class="title" id="dqyh">当前用户：</span>
			</div>
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
			<div class="header-item">
				<span class="icon iconfont icon-system-setting"></span>
			</div>
		</div>
		<ul class="tolist home">
			<li class="tolist-item status home-page">
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
			
			<li class="tolist-item status" onclick="openAqfxypSystem()">
				安全风险研判
				<!-- <ul class="tolist-menu">
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
				</ul> -->
			</li>
			
			<li class="tolist-item status">
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
					<li class="tolist-menuitem" onclick="openQd()">
						授权
					</li>
					<li class="tolist-menuitem" onclick="openRygj()">
						人员轨迹
					</li>
					<li class="tolist-menuitem" onclick="openJk()">
						监控
					</li>
					<li class="tolist-menuitem">
						移动警务
					</li>
					<li class="tolist-menuitem">
						接报系统
					</li>
					<li class="tolist-menuitem">
						智能盒子
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
			
			<li class="tolist-item status" onclick="openYysbxt()">
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
					<!-- <li class="tolist-menuitem">
						应急统计
					</li> -->
				</ul>
			</li>
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
				<%-- <ul class="tolist-menu">
					<li class="tolist-menuitem">
						信息汇聚
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem"> 
								今日值班
								<ul class="tolist-menuitem">
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
								监区数据上报
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'sblxsz')">
										上报数据类型设置
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'sjsb')">
										数据上报
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'sjhz')">
										数据汇总
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'cgsgxx')"> 
								出工/收工信息
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'mjczdj')"> 
								门禁操作登记
							</li>
							<%
								if (AuthSystemFacade.whatLevelForLoginUser() == 1) {//省局
							%>
							<li class="tolist-menuitem" onclick="toMenuDisplay('pzfjbxx')"> 
								罪犯基本信息
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('pwfsb')"> 
								物防设备
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('pjfsb')"> 
								技防设备
							</li>
							<%
								} else if (AuthSystemFacade.whatLevelForLoginUser() == 2) {//监狱级
							%>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jndt')"> 
								监内零散罪犯
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('zfjbxx')"> 
								罪犯基本信息
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('wfsb')"> 
								物防设备
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('jfsb')"> 
								技防设备
							</li>
							<%
								} else {//其他
							%>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jndt')"> 
								监内零散罪犯
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('jqzfjbxx')"> 
								监区罪犯基本信息
							</li>
							<%
								}
							%>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jhrc')"> 
								计划日程
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						安全防控
							<ul class="tolist-menuitem">
								<li class="tolist-menuitem">
									视频监控
									<ul class="tolist-menuitem">
										<li class="tolist-menuitem" onclick="toMenuDisplay('spya')">
											视频预案
										</li>
										<li class="tolist-menuitem" onclick="toMenuDisplay('splx')">
											视频轮巡
										</li>
										<li class="tolist-menuitem" onclick="toMenuDisplay('sphf')">
											视频回放
										</li>
										<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'group')">
											群组管理
										</li>
										<li class="tolist-menuitem" onclick="toMenuDisplay('offLineCamera')">
											离线摄像头
										</li>
										<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jdjc')">
											监督检查
										</li>
										<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jddlb')">
											监督单列表
										</li>
										<li class="tolist-menuitem">
											视频巡查通报
											<ul class="tolist-menuitem">
												<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-lb')">
													巡查通报列表
												</li>
												<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-sp')">
													巡查通报审批
												</li>
												<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-hz')">
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
								<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'realTimeTalk')">
									实时对讲
								</li>
								<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'screenSwitch')">
									大屏预案
								</li>
								<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'rcs')">
									融合通讯
								</li>
							</ul>
					</li>
					<li class="tolist-menuitem">
						指挥协调
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem">
								报警处置
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'alarmRecord')">
										报警记录
									</li>
									<li class="tolist-menuitem" onclick="toMenuDisplay('alarmProcessMan')">
										人工报警
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem">
								报警预案
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'alarmPlan')">
										报警预案
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'alarmType')">
										报警等级
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'flows')">
										报警流程
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem">
								事务督办
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'affairsRecord')">
										事务报备
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'affairsHandle')">
										事务处理
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'affairsfeedBack')">
										事务反馈
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'affairsOversee')">
										事务监督
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'affairsGather')">
										事务汇总
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'faultType')">
										故障类型
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'deviceRecord')">
										设备维修记录
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'sporadicFlow')">
								罪犯零星活动
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wlrc')">
								外来人车
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zfjcj')">
								罪犯出监
							</li>
							<li class="tolist-menuitem">
								视频点名
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'fqdm')">
										发起点名
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'dmls')">
										点名历史
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'rlzc')">
										人脸注册
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'dmfq')">
										点名发起
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'dmjl')">
										点名记录
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						信息研判
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'dayly')">
								信息日报
							</li>
							<li class="tolist-menuitem">
								通报整改
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'tbzg-fq')">
										发起
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'tbzg-zg')">
										整改
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'tbzg-sp')">
										审批
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'tbzg-hz')">
										汇总
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						应急处突
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem">
								资源管理
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'gzzgl')">
										工作组管理
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wzgl')">
										物资管理
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zjgl')">
										专家管理
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'fggl')">
										法规管理
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'czffgl')">
										处置方法管理
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem">
								预案管理
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'yabz')">
										预案编制
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'yasp')">
										预案审批
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'yafb')">
										预案发布
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem">
								处置管理
								<ul class="tolist-menuitem">
									<li class="tolist-menuitem" onclick="toMenuDisplay('sjcz')">
										事件处置
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'czjl')">
										处置记录
									</li>
								</ul>
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'yjctSszk')">
								实时指控
							</li>
						</ul>
					</li>
				</ul> --%>
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
						三维地图维护
						<ul>
							<li class="tolist-menuitem" onclick="toMenuDisplay('mxwh')">
								模型维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('sjwh')">
								视角维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('dwwh')">
								点位维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('bjtcwh')">
								报警图层维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('xswh')">
								巡视维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('bqwh')">
								标签维护
							</li>
							<li class="tolist-menuitem" onclick="toMenuDisplay('jsxxdwwh')">
								监舍信息点位维护
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
			</li>
		</ul>
	</header>
	<div class="container-box zhihui">
		<div id="layout1">

			<div data-options="region:'west'" style="width:345px;">
				<div class="left">
					<div class="left-top">
						<h3>今日值班</h3>
						<ul>
							<li>
								<div class="info-icon dutyIcon">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('指挥长');" />
								</div>
								<div class="dutyPerson">
									<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('指挥长');">指挥长</p>
									<p class="dutyNme" id="DBLD"></p>
								</div>
							</li>
							<li>
								<div class="info-icon dutyIcon">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('带班领导');" />
								</div>
								<div class="dutyPerson">
									<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('带班领导');">带班领导</p>
									<p class="dutyNme" id="ZYLD"></p>
								</div>
							</li>
							<li>
								<div class="info-icon dutyIcon">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('指挥中心');" />
								</div>
								<div class="dutyPerson">
									<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('指挥中心');">指挥中心</p>
									<p class="dutyNme" id="ZHZX"></p>
								</div>
							</li>
							<li>
								<div class="info-icon dutyIcon">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" style="cursor: pointer;" onClick="rcsCall('在监民警');" />
								</div>
								<div class="dutyPerson">
									<p class="dutyTitle" style="cursor: pointer;" onClick="rcsCall('在监民警');">在监民警</p>
									<p class="dutyNme" id="current_insidePoliceCount">0</p>
								</div>
							</li>
						</ul>
					</div>
					<div class="left-bottom">
						<div class="realTimeAlert">
							<h3>在册罪犯</h3>
							<input id="zczf" type="text" />
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">在押犯人</p>
										<p class="custodyNum alertNum" id="inside">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">加载戒具</p>
										<p class="custodyNum alertNum" id="GDJJCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">关押禁闭</p>
										<p class="custodyNum alertNum" id="GYJBCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">隔离审查</p>
										<p class="custodyNum alertNum" id="GLSCCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">立案侦查</p>
										<p class="custodyNum alertNum" id="LAZCCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">解回重申</p>
										<p class="custodyNum alertNum" id="JHCSCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">监外执行</p>
										<p class="custodyNum alertNum" id="ZYJWZXCount">0</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">老病残</p>
										<p class="custodyNum alertNum" id="LBCCount">0</p>
									</div>
								</li>
							</ul>
						</div>
						<div class="realTimeOffense">
							<h3>在岗民警</h3>
							<input id="zgmj" type="text" />
							<ul>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">监区</p>
										<p class="custodyNum alertNum">650</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">机关</p>
										<p class="custodyNum alertNum">1</p>
									</div>
								</li>
								<li>
									<div class="info-icon custodyIcon alermIcon">
										<span class="iconfont icon-prisoner"></span>
									</div>
									<div class="custodyPerson alertShow">
										<p class="custodyTitle alertType">其他</p>
										<p class="custodyNum alertNum">0</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div data-options="region:'east'" style="width:277px;">
				<div class="right">
					<div class="on-duty right">
						<h2>一日执勤</h2>
						<div class="tolist">
			              <div class="tolist-item status">
			                	已执行 2
			              </div>
			              <div class="tolist-item status">
			                	未执行 16
			              </div>
			              <div class="tolist-item status">
			              		异常 0
			              </div>
			            </div>
						<ul class="duty-content">
							<li class="yrzq finished">
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
									<p class="time">10:00-11:30</p>
									<p class="text">视频轮巡监督</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq finished active">
								<div class="info-icon">
									<span class="iconfont icon-mental"></span>
								</div>
								<div>
									<p class="time">13:30-14:00</p>
									<p class="text">心理矫治教育</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-knock-off"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-knock-off"></span>
								</div>
								<div>
									<p class="time">16:40-17:30</p>
									<p class="text">罪犯收工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯晚餐</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
							<li class="yrzq plan">
								<div class="info-icon">
									<span class="iconfont icon-dinner"></span>
								</div>
								<div>
									<p class="time">14:00-16:40</p>
									<p class="text">罪犯出工</p>
								</div>
								<i class="iconfont icon-xialadown"></i>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center'" class="main">
				<div id="layout-child">
					<div data-options="region:'center'" style="overflow:hidden">
						<div class="top" id="top">
							<h3>风险概况</h3>
							<div id="ltChart" class="ltChart top-box"></div>
							<div class="top-box top-right">
								<div class="todo_List">
									<ul class="theader clearfix">
										<li>填报单位</li>
										<li>故障类型</li>
										<li>故障内容</li>
										<li>事物状态</li>
										<li>填报时间</li>
									</ul>
									<ul class="tcontent">
										<li>
											<span>科技信息科</span>
											<span>门禁</span>
											<span>锁死</span>
											<span>代签收</span>
											<span>2019.01.22 10:25</span>
										</li>
										<li>
											<span>指挥中心</span>
											<span>摄像头</span>
											<span>无画面</span>
											<span>已签收</span>
											<span>2019.01.22 10:25</span>
										</li>
										<li>
											<span>科技信息科</span>
											<span>门禁</span>
											<span>锁死</span>
											<span>代签收</span>
											<span>2019.01.22 10:25</span>
										</li>
										<li>
											<span>指挥中心</span>
											<span>摄像头</span>
											<span>无画面</span>
											<span>已签收</span>
											<span>2019.01.22 10:25</span>
										</li>
										<li>
												<span>指挥中心</span>
												<span>摄像头</span>
												<span>无画面</span>
												<span>已签收</span>
												<span>2019.01.22 10:25</span>
											</li>
											<li>
												<span>科技信息科</span>
												<span>门禁</span>
												<span>锁死</span>
												<span>代签收</span>
												<span>2019.01.22 10:25</span>
											</li>
											<li>
												<span>指挥中心</span>
												<span>摄像头</span>
												<span>无画面</span>
												<span>已签收</span>
												<span>2019.01.22 10:25</span>
											</li>
									</ul>

								</div>
							</div>
						</div>
												
						<div class="center-box clearfix">
							<div class="center-left">
								<h3>生物识别<span>更多+</span></h3>
								<div class="left-box">
									<div>
									<ul id="autoScrollL">
										<li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三1</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li>
										<li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三2</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li>
										<li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三3</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li><li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三4</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li>
										<li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三5</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li>
										<li>
											<div class="sb-img">
												<img src="${ctx}/static/bj-cui/img/command/face.jpg" />
												<p>张三6</p>
											</div>
											<div class="sb-info">
												<span class="sb-title">人脸识别</span>
												<span class="sb-detail">
													<p>一监区</p>
													<p>
														<span class="iconfont icon-time"></span>
														<span>12:24:56</span>
													</p>
												</span>
											</div>
										</li>
									</ul>
								</div>
							</div>
							</div>
							<div class="center-middle">
								<h3>语音识别<span>更多+</span></h3>
								<div class="middle-box">
									<ul class="middle-scroll">
										<li>
											<div>
												<span class="voice-num"></span>
												<i>01</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>02</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div class="empty">
												<span class="voice-num"></span>
												<i>03</i>
												<span class="iconfont icon-signal"></span>

												<img src="${ctx}/static/bj-cui/img/command/desk.png" />
												<p>无</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>04</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>05</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>06</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div class="empty">
												<span class="voice-num"></span>
												<i>07</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>08</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>09</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>10</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>11</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>12</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>13</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>14</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
										<li>
											<div>
												<span class="voice-num"></span>
												<i>15</i>
												<span class="iconfont icon-signal"></span>
												<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
												<p>王二牛</p>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="center-right">
								<h3>监内动态<span>更多+</span></h3>
								<div class="right-box">
									<div>
									<ul id="autoScrollR">
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
										<li>
											<span>
												<p> 上午</p>
												<p> 08:00</p>
											</span>
											<span>一监区</span>
											<button>点名</button>
										</li>
									</ul>
								</div>
								</div>
							</div>
					
						</div>
					</div>
					<div data-options="region:'south'" style="height:130px;">
						<div class="bottom">
							<div class="real-time">
								<h2 class="real-time-title">实时警情</h2>
								<ul class="alarm">
									<li class="alarm-item">
										<img class="alarm-img" src="${ctx}/static/bj-cui/img/emergency.png" alt="一级警情">
										<div class="alarm-info">
											<p class="alarm-mount emergency" id="lev_1">0</p>
											<p class="alarm-title">一级警情</p>
										</div>
									</li>
									<li class="alarm-item">
										<img class="alarm-img" src="${ctx}/static/bj-cui/img/danger.png" alt="二级警情">
										<div class="alarm-info">
											<p class="alarm-mount danger" id="lev_2">0</p>
											<p class="alarm-title">二级警情</p>
										</div>
									</li>
									<li class="alarm-item">
										<img class="alarm-img" src="${ctx}/static/bj-cui/img/warning.png" alt="三级警情">
										<div class="alarm-info">
											<p class="alarm-mount warning" id="lev_3">0</p>
											<p class="alarm-title">三级警情</p>
										</div>
									</li>
								</ul>
								<ul class="alarm short">
									<li class="alarm-item">
										<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/watch.png" alt="事物督办">
										<div class="alarm-info">
											<p class="alarm-mount common">5</p>
											<p class="alarm-title">事物督办</p>
										</div>
									</li>
									<li class="alarm-item">
										<img class="alarm-img" src="${ctx}/static/bj-cui/img/command/device.png" alt="设备故障">
										<div class="alarm-info">
											<p class="alarm-mount common">0</p>
											<p class="alarm-title">设备故障</p>
										</div>
									</li>
								</ul>
								<ul class="alarm short">
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
											<p class="alarm-mount common" id="nowFPerson">0</p>
											<p class="alarm-title">外来人员</p>
										</div>
									</li>
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
	<script src="${ctx}/static/bj-cui/js/charts.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
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
	
	<script type="text/javascript">
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
		/**
		 * 在册罪犯
		 */
		$("#zczf").textbox({
			buttons: [{
				text: false,
				icons: "iconfont icon-sousuo",
				click: function (e, data) {
				     toPrisonerList();
				}
			}]
		})
		/**
		 * 在岗民警
		 */
		$("#zgmj").textbox({
			buttons: [{
				text: false,
				icons: "iconfont icon-sousuo",
				click: function (e, data) {
					toPoliceList2();
				}
			}]
		})
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
			// alert("autoScrollL");
			var height = $("#autoScrollL").children("li").outerHeight() + 5;
			moveLY += height;
			$("#autoScrollL")[0].style.transform = "translateY(-" + moveLY + "px)";

		}
		function autoScrollR() {
			var height = $("#autoScrollR").children("li").outerHeight() + 5;
			moveRY += height;
			$("#autoScrollR")[0].style.transform = "translateY(-" + moveRY + "px)";
		}
		
		$(function () {
			jsConst.basePath = "${ctx}/";
			
			// 初始化登录用户信息
			initUserInfo();
			videoPlanTimer.initVideoPlanTimer();
		});
		
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
			}
	        
			else if (name == 'dayly') {
				url = '${ctx}/xxyp/dayly/daylyLayout';
				w = 1000;
				h = 680;
		    }
	
	        $('#dialog').html("");
			// $('#dialog').dialog("destroy");
	        if (w == null || h == null) {
	            w = 1000;
	            h = 600;
	        }
			
	        $('#dialog').dialog({
	            width: w,
	            height: h,
	            title: $(obj).text(),
	            url: url
	        });
	        $("#dialog").dialog("open");
	        return;
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
							showTodayDutyPolice(data.ZYLD.staffName,data.DBLD.staffName,data.ZHZX.staffName)
							showInPoliceCount(data.current_insidePoliceCount);
							parsePrisonerCount(data.current_PrisonerCount);
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
			$("#lev_1").text(0);
			 $("#lev_2").text(0);
			 $("#lev_3").text(0);
			/* $.ajax({
				type : "post",
				url : "${ctx}/alarm/queryAlarmLevRecord.json?cusNumber=" + jsConst.CUS_NUMBER,
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
			}); */
		}
		
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
				url : "${ctx}/foreign/openCarInfo?flag=1"
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		}
		
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
		
		function showDqyh() {
			$("#dqyh").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
		
		
		function openAqfxypSystem() {
			
			var url = "${ctx}/portal/aqfxyp/shouye";
			window.open(url, "_blank");
		}
		function openQd() {
			var url = "http://192.168.9.33:11180/#/login?username=qd&password=qd";
			window.open(url, "_blank");
		}
		function openRygj() {
			var url = "http://192.168.9.33:11180/#/trace/index";
			window.open(url, "_blank");
		}
		function openJk() {
			var url = "http://192.168.9.33:11180/#/drawer?camera_id=6@DEFAULT";
			window.open(url, "_blank");
		}
		
		function openOldZnafpt() {
			
			var url = "${ctx}/portal/shouye";
			window.open(url, "_blank");
		}
		
		/**
		 * 查询在岗民警
		 */
		function toPoliceList2() {
			var policeIdntyOrName = $("#zgmj").textbox("getValue");
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
						url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + jsConst.DEPARTMENT_ID + "&cusNumber=" + jsConst.CUS_NUMBER + "&policeIdntyOrName=" + policeIdntyOrName
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
			var prsnrIdntyOrName = $("#zczf").textbox("getValue");
			if(jsConst.USER_LEVEL != 1){
				cusNumber = jsConst.CUS_NUMBER;
				if(jsConst.USER_LEVEL == 3) {
					prisonArea= jsConst.DEPARTMENT_ID; 
	 			}
			}
			// alert("prsnrName = " + prsnrName);
			$("#dialog").dialog({
				width : 1000,
				height : 800, 
				title : '罪犯信息',
				url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&cusNumber=" + cusNumber + "&prsnrIdntyOrName=" + prsnrIdntyOrName
			});
			$("#dialog").dialog("open");
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
		
		function openYysbxt() {
			
			var url = "${ctx}/portal/yysb/shouye";
			window.open(url, "_blank");
		}
	</script>
</body>

</html>
