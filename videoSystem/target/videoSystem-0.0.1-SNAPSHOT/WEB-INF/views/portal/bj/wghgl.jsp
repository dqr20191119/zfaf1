<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
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

	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/bj-cui/css/svg-new.css" rel="stylesheet" type="text/css" />

 
  	
  	
  	
</head>
<body>
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_edit" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_List" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<div id="dialog_mjxx"></div>
	
	<header class="perspective">
	<jsp:include page="bjheader.jsp"></jsp:include>
		<%-- <img src="${ctx}/static/bj-cui/img/command/logo_zhihui2.png" alt="指挥中心logo" class="logo">
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
				<span class="icon iconfont icon-system-setting" title="退出系统" style="cursor: pointer;" onClick="syLogout();"></span>
			</div>
		</div>
		<ul class="tolist home">
			<li class="tolist-item status home-page" onClick="toZhsy();">
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
							<li class="tolist-menuitem">
								非法手机统计
							</li>
							<li class="tolist-menuitem">
								劳动工具发放统计
							</li>
							<li class="tolist-menuitem">
								敏感词统计
							</li>
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjkgjl')">
								门禁开关记录
							</li>
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjxcjl')">
								民警巡查记录
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
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jndtcx')">
								监内动态查询
							</li>
							<!-- <li class="tolist-menuitem">
								监内动态统计
							</li> -->
						</ul>
					</li>
					<li class="tolist-menuitem">
						网格划分
						<ul>
							<!-- <li class="tolist-menuitem">
								区域网格
							</li> -->
							<li class="tolist-menuitem" onclick="openWg()">
								网格化管理
							</li>
							<li class="tolist-menuitem">
								安全网格
							</li>
							<li class="tolist-menuitem" onclick="openDjwg()">
								党建网格
							</li>
						</ul>
					</li>
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
					<!-- <li class="tolist-menuitem">
						智能盒子
					</li>
					<li class="tolist-menuitem" onclick="openYthyw()">
						一体化运维
					</li>
					<li class="tolist-menuitem" onclick="openWrjfk()">
						无人机防控
					</li> -->
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jdjc')">
						监督检查
					</li>
					 
					<!-- 
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jddlb')">
						监督单列表
					</li>
					<li class="tolist-menuitem" >
						视频监督
						<ul class="tolist-menuitem" >
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-lb')">
								监督通报列表
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-sp')">
								监督通报审批
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'wldctb-hz')">
								监督通报汇总
							</li>
						</ul>
					</li> -->
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
					<!-- <li class="tolist-menuitem">
						轨迹记录
					</li>
					<li class="tolist-menuitem">
						监区点名
					</li> -->
				</ul>
			</li>
			
			<li class="tolist-item status" onclick="openZnyysb()">
				语音识别
				<!-- <ul class="tolist-menu">
					<li class="tolist-menuitem">
						实时监控
					</li>
					<li class="tolist-menuitem">
						监听查询
					</li>
					<li class="tolist-menuitem">
						敏感词统计
					</li>
				</ul> -->
			</li>
				
			<!-- <li class="tolist-item status">
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
			</li> -->
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
				<ul class="tolist-menu">
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
				</ul>
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
						
			<!-- <li class="tolist-item status">
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
			</li> -->
		</ul> --%>
	</header>
	<div class="container-box">
		<div id="layout1">
			<div data-options="region:'center'" class="main">
				<div id="layout-child">
					<div data-options="region:'center'" style="overflow:hidden">
						<ul class="tips-bottom">
						</ul>
					</div>
					<!-- <img src="./img/svg/wange_bg.png"> -->
					<div class="center cls-first">
						<?xml version="1.0" encoding="utf-8"?>
						<!-- Generator: Adobe Illustrator 17.0.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
						<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
						<svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px"
						 y="0px" width="1920px" height="1080px" viewBox="0 0 1920 1080" enable-background="new 0 0 1920 1080" xml:space="preserve">
							<g id="s22">
								<g>
									<g>
										<path opacity="0.3" fill="#00000C" enable-background="new    " d="M1278.093,530.159c0,0,53.095,13.978,61.899,17.033
				c45.25-14.923,95.377-31.873,95.377-31.873l-56.631-54.044l-95.863,31.965C1287.804,509.872,1278.093,530.159,1278.093,530.159z" />
										<path opacity="0.3" fill="#00000C" enable-background="new    " d="M1248.692,530.689l8.787-2.919
				c8.565,0.202,6.436,0.541,15.532,2.02c0.663-1.227,2.19-4.235,5.606-11.546c4.057-8.685,2.484-18.379,0.972-23.909
				l-37.808,12.607c0,0-21.322-11.171-13.865-25.385l119.479-40.324l-52.644-48.184l-70.976,21.298l4.376,7.956l13.994-3.684
				l26.432,20.647l-48.315,10.977l-21.286-18.512l11.668-5.459l-5.386-5.737l-128.103,42.346c0,0-20.708,4.661-8.369,17.072
				c12.339,12.411,86.498,81.468,86.498,81.468c26.475,12.703,64.178-9.115,64.178-9.115s15.887,29.542,54.647,15.908
				c9.976-3.509,33.878-10.491,60.057-19.103C1314.361,539.048,1248.692,530.689,1248.692,530.689z" />
									</g>
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1295.812,152.452 1413.836,243.672 1298.297,278.501 
			1286.263,265.88 1382.127,240.736 1306.283,175.456 1190.967,212.898 1255.468,273.704 1276.461,268.184 1285.812,278.747 
			1250.881,290.784 1151.372,198.205 		" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M1347.434,353.969l-65.428-54.277l148.215-45.53
			l149.648,116.406l-157.42,60.032l-58.478-40.463l-39.492,12.336c0,0,6.919-15.017,23.902-18.245
			c4.641-0.882,15.415-2.956,34.492,1.423c19.077,4.379,43.142,12.023,50.229,14.681c7.087,2.66,41.953,5.261,49.273,1.889
			c7.319-3.372,18.381-4.133,24.946-19.026l10.503-2.36c0,0-14.127-47.6-76.197-65.405l16.521-4.847l-20.401-18.001l-46.166,12.984
			l12.957,15.043c0,0,46.254-8.561,84.169,41.597c0,0-92.581-13.506-61.163,28.225c0,0,4.211,3.933,4.569,4.279
			c0.358,0.344-16.453-5.127-30.417-8.72c-13.961-3.593-71.67-23.029-83.497,19.231l-21.605-25.519l57.702-20.102L1347.434,353.969z
			" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1276.902,129.795 -71.265,595.275 -62.86,605.134 
			1289.807,142.201 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1536.277,59.093 1305.24,135.699 1295.287,125.871 
			1523.374,46.687 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="-30.146,628.227 68.941,768.176 93.695,755.555 
			79.085,736.853 68.422,741.81 12.738,662.724 54.072,645.557 108.808,719.385 146.907,698.587 67.775,591.356 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="85.549,787.895 229.322,989.736 339.246,946.423 
			210.693,780.678 167.997,795.764 228.089,873.707 182.587,893.595 118.131,808.352 130.152,802.664 111.18,776.838 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1027.906,700.206 1086.842,764.106 267.393,1101.349 
			215.414,1020.081 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1056.594,692.608 1115.466,753.343 1736.306,492.111 
			1703.466,467.438 		" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1319.419,145.838 1606.47,363.845 1668.024,346.542 
			1586.635,285.461 1550.352,300.243 1356.188,155.663 1519.01,101.811 1816.342,306.252 1709.739,357.589 1795.413,422.015 
			1739.597,444.922 1671.87,389.987 1678.115,385.645 1671.094,377.342 1687.842,370.682 1730.144,402.554 1738.477,397.115 
			1689.723,359.039 1655.231,375.544 1663.877,385.925 1646.115,394.715 1735.723,462.582 1982.355,378.59 1531.041,69.922 		" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M691.678,369.146l52.006,60.245
			c0,0,26.956,2.635,26.106-13.703l-52.409-53.907L691.678,369.146z" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M766.246,450.046l219.142,234.119l26.454-13.886
			L790.687,441.125C790.687,441.125,773.846,435.148,766.246,450.046z" />
									<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="1034.108,462.101 997.74,472.636 1152.681,625.532 
			1217.014,606.396 1173.32,566.398 1151.049,578.404 		" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M1434.01,446.369l175.211-58.666l75.036,60.042
			l-218.496,75.016l-11.859-12.908c0,0,22.239,4.157,27.723-11.987c5.484-16.146,3.262-21.809,14.796-25.513
			c0,0,39.13,13.7,63.09-6.296c23.96-19.997-22.389-42.997-50.731-35.757c-28.344,7.24-25.994,24.335-22.001,34.547
			c0,0-9.697,16.57-9.019,24.194s-17.622,15.896-32.439,13.551l-7.637-12.761l34.059-11.703L1434.01,446.369z" />
									<g>
										<path opacity="0.3" fill="#00000C" enable-background="new    " d="M94.327,582.165l99.012,134.5l58.478-30.694
				c0,0,14.613-8.025,3.802-21.176c-10.811-13.151-70.595-84.418-70.595-84.418s-11.759-16.648,21.152-28.551l-6.42-9.017
				L94.327,582.165z" />
										<path opacity="0.3" fill="#00000C" enable-background="new    " d="M287.515,730.632l-45.408,13.972l129.344,184.288
				l74.164-28.685L312.953,736.461C312.951,736.461,305.142,723.772,287.515,730.632z" />
										<path opacity="0.3" fill="#00000C" enable-background="new    " d="M426.82,812.821l-23.763-28.018l-19.944,8.113l-3.341,1.586
				c24.96,30.875,60.965,74.487,60.965,74.487s15.289,7.084,35.18-0.694c19.893-7.778,24.069-9.969,24.069-9.969l-49.087-54.949
				L426.82,812.821z" />
										<g>
											<path opacity="0.3" fill="#00000C" enable-background="new    " d="M460.54,749.656l26.755-13.883l-9.204-24.723
					c0,0-1.325-13.69-32.798-4.628s-81.741,27.988-81.741,27.988s-18.692,15.14-4.607,33.853
					c3.389,4.502,9.886,12.674,17.793,22.481l2.668-1.513l41.774-21.666l-10.299-14.04l39.796-15.028L460.54,749.656z" />
											<path opacity="0.3" fill="#00000C" enable-background="new    " d="M524.881,836.732l-35.966-96.607l-25.159,13.17
					l20.373,23.051l-28.216,13.745l1.749,8.406l31.68,1.486c0,0,18.199,13.357-1.569,27.463l22.699,27.512
					C510.469,854.951,529.894,850.197,524.881,836.732z" />
										</g>
										<polygon opacity="0.3" fill="#00000C" enable-background="new    " points="319.039,565.103 296.559,552.866 308.002,569.862 			
				" />
										<g>
											<path opacity="0.3" fill="#00000C" enable-background="new    " d="M267.359,613.382l-30.383-37.901l59.581-22.616
					l-21.218-11.549l-68.913,23.163c0,0-11.741,6.562-4.171,15.907c4.855,5.995,26.533,29.437,41.466,45.49L267.359,613.382z" />
											<path opacity="0.3" fill="#00000C" enable-background="new    " d="M376.657,596.467l-54.258-29.536l-12.549,5.674
					l22.689,33.698l-53.378,21.801l-7.482-9.333l-24.526,10.79c6.574,7.057,11.169,11.966,11.169,11.966s21.218,5.912,41.132-0.811
					c19.913-6.723,66.027-22.403,66.027-22.403S385.355,609.484,376.657,596.467z" />
										</g>
									</g>
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M702.026,403.485c0,0-10.682-10.731-32.398-2.174
			c-24.702,9.733-116.12,37.834-135.35,44.67c-3.458,1.229-5.442,4.831-4.631,8.411c0.677,2.98,2.376,6.826,6.187,11.333
			c7.364,8.707,206.37,198.425,260.822,253.56c9.181,9.296,22.66,12.869,35.251,9.379c34.053-9.44,104.079-30.492,121.681-52.109
			L702.026,403.485z" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M907.586,354.285l195.053-55.096c0,0,16.4-6.521,27.501,3.495
			c11.1,10.016,25.421,22.885,25.421,22.885s12.834,8.783-4.953,16.327c-17.786,7.544-202.722,66.724-202.722,66.724
			s-11.438,1.669-17.107-7.541C925.109,391.869,907.586,354.285,907.586,354.285z" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M971.554,409.499l12.329,47.822l310.567-107.527
			l-46.736-43.506l-59.763,21.632c0,0,14.35,5.211,0.214,10.858C1174.029,344.428,971.554,409.499,971.554,409.499z" />
									<path opacity="0.3" fill="#00000C" enable-background="new    " d="M513.572,460.934c0,0-40.39,1.221-47.764,19.654l60.363,64.925
			l-13.951,5.901l-58.226-63.561c0,0-37.512-11.921-53.074,19.333l70.433,76.209l-15.247,11.503l-128.772-65.159
			c0,0.002-18.325-10.891-32.067,5.558l140.911,83.734l32.908,9.901l69.526,208.582c0,0,27.044,7.119,28.765-12.566l-53.383-165.777
			l24.711-4.179l44.376,163.711c0,0,74.573,9.783,136.136-42.776l-100.734-114.77l20.209-9.542l106.32,112.727
			c0,0,43.353,6.646,59.548-28.137L513.572,460.934z" />
								</g>
							</g>
							<g id="s21_1_" class="tooltip-item" title="s21_1_">
								<g>
									<path fill="#B38D1B" fill-opacity="0" d="M1523.226,42.711L-115.338,589.248l333.967,566.782l1828.597-731.867L1523.226,42.711z" />
									<path fill="none" stroke="#B38D1B" stroke-miterlimit="10" d="M1523.226,42.711L-115.338,589.248l333.967,566.782
			l1828.597-731.867L1523.226,42.711z" />
								</g>
							</g>
							<g id="s20_1_" class="tooltip-item" title="s20_1_">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="374.238,928.902 114.463,555.873 1158.036,199.54 1342.56,373.849 
			877.756,541.852 1011.309,683.65 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="374.238,928.902 114.463,555.873 1158.036,199.54 
			1342.56,373.849 877.756,541.852 1011.309,683.65 		" />
									<g>
										<g>
											<polygon fill="#002E4B" points="124.008,562.286 123.952,572.289 1166.393,216.144 1166.22,207.355 				" />
											<g>
												<g>
													<polygon fill="#B2B2B2" points="125.064,561.918 124.006,562.296 124.056,553.421 125.113,553.052 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="227.44,527.645 226.383,528.023 226.433,518.79 227.491,518.42 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="341.462,489.351 340.407,489.731 340.459,480.147 341.515,479.777 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="451.169,452.643 450.112,453.023 450.263,442.186 451.318,441.818 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="569.747,412.708 568.689,413.085 568.714,402.061 569.771,401.691 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="743.446,354.373 742.389,354.751 742.547,342.147 743.605,341.777 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="860.881,315.534 859.823,315.914 859.69,302.051 860.746,301.683 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="985.845,272.923 984.789,273.3 985.051,259.144 986.109,258.776 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1048.819,251.723 1047.762,252.101 1047.897,237.634 1048.955,237.264 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="656.827,383.392 655.771,383.77 655.852,371.819 656.91,371.449 						" />
												</g>

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="124.269" y1="560.255"
												 x2="1167.817" y2="202.91" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="124.286" y1="557.116"
												 x2="1169.053" y2="199.355" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="124.308" y1="553.87" x2="1167.076"
												 y2="196.89" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#B2B2B2" points="358.869,893.642 359.834,895.024 360.327,886.484 359.3,886.039 					" />
											</g>
											<g>
												<polygon fill="#B2B2B2" points="337.786,863.799 338.749,865.181 339.245,856.641 338.216,856.196 					" />
											</g>
											<g>
												<polygon fill="#B2B2B2" points="282.531,785.057 283.494,786.438 283.875,778.269 282.846,777.824 					" />
											</g>
											<g>
												<polygon fill="#00A0E9" points="241.136,726.203 240.878,734.955 375.019,927.044 375.314,917.046 					" />
												<g>
													<polygon fill="#B2B2B2" points="313.705,829.423 314.668,830.804 315.049,822.634 314.02,822.189 						" />
												</g>

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="375.959" y1="913.061"
												 x2="246.323" y2="729.379" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="376.059" y1="909.697"
												 x2="247.863" y2="728.027" />
											</g>
										</g>
										<g>
											<polygon fill="#00A0E9" points="126.007,560.295 125.953,570.287 245.401,741.787 247.057,732.895 				" />
											<g>
												<polygon fill="#B2B2B2" points="139.992,580.204 140.944,581.595 141.435,570.595 140.41,570.141 					" />
											</g>
											<g>
												<polygon fill="#B2B2B2" points="162.099,611.562 163.051,612.952 163.644,601.583 162.621,601.128 					" />
											</g>

											<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="252.14" y1="736.233" x2="126.27"
											 y2="553.251" />

											<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="253.815" y1="735.07" x2="126.918"
											 y2="551.661" />
										</g>
										<g>
											<g>
												<g>
													<g>
														<polygon fill="#B2B2B2" points="1218.862,254.815 1219.894,256.148 1220.111,245.744 1219.063,245.35 							" />
													</g>
												</g>
												<g>
													<g>
														<polygon fill="#B2B2B2" points="1194.6,232.529 1195.63,233.861 1195.82,222.924 1194.772,222.53 							" />
													</g>
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1232.569,263.318 1167.623,203.49 1168.38,202.749 1233.324,262.578 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1238.281,264.346 1166.95,197.491 1167.707,196.751 1239.036,263.605 						" />
												</g>
											</g>
											<polygon fill="#00A0E9" points="1166.22,207.355 1166.393,216.144 1233.32,280.176 1233.579,268.326 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1247.222,297.379 1247.394,306.135 1313.54,367.505 1313.75,357.916 				" />
											<g>
												<g>
													<g>
														<polygon fill="#B2B2B2" points="1299.032,344.406 1300.064,345.739 1300.281,335.334 1299.233,334.94 							" />
													</g>
												</g>
												<g>
													<g>
														<polygon fill="#B2B2B2" points="1274.771,322.119 1275.8,323.452 1275.99,312.515 1274.942,312.121 							" />
													</g>
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1312.739,352.909 1260.177,304.413 1260.932,303.672 1313.494,352.169 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1312.631,347.417 1264.952,303.587 1265.708,302.846 1313.386,346.677 						" />
												</g>
											</g>
										</g>
										<g>
											<polygon fill="#002E4B" points="851.023,515.762 851.22,525.763 1313.932,367.142 1313.737,357.281 				" />
											<g>
												<g>
													<polygon fill="#B2B2B2" points="852.07,515.367 851.023,515.771 850.848,506.898 851.896,506.501 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="984.296,470.037 983.248,470.441 983.066,461.209 984.114,460.814 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1096.006,431.758 1094.96,432.164 1094.771,422.581 1095.817,422.187 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1208.152,393.338 1207.105,393.744 1206.916,384.162 1207.964,383.767 						" />
												</g>

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="851.232" y1="513.723"
												 x2="1313.133" y2="353.385" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="851.17" y1="510.586" x2="1313.071"
												 y2="350.247" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="851.112" y1="507.342"
												 x2="1313.008" y2="347.049" />
											</g>
										</g>
										<g>
											<polygon fill="#00A0E9" points="851.046,516.953 851.219,525.707 1005.983,685.484 1005.786,675.485 				" />
											<g>
												<g>
													<polygon fill="#B2B2B2" points="996.907,665.883 997.939,667.216 998.117,653.686 997.07,653.292 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="973.322,642.2 974.352,643.532 974.51,629.936 973.462,629.542 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="915.769,583.916 916.801,585.248 916.794,571.87 915.746,571.477 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="881.506,548.115 882.538,549.447 882.55,537.007 881.502,536.613 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="948.9,617.216 949.932,618.551 949.914,605.19 948.866,604.797 						" />
												</g>

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="1008.171" y1="669.749"
												 x2="852" y2="511.824" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="1008.193" y1="664.439"
												 x2="852.941" y2="507.173" />
											</g>
										</g>
										<g>
											<polygon fill="#002E4B" points="374.038,917.814 374.235,927.815 1009.495,683.426 1009.275,672.261 				" />
											<g>
												<g>
													<polygon fill="#B2B2B2" points="375.086,917.419 374.038,917.823 373.863,908.948 374.911,908.553 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="498.314,869.883 497.267,870.29 497.085,861.058 498.132,860.661 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="611.222,826.187 610.175,826.591 609.986,817.011 611.033,816.614 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="719.854,784.193 718.807,784.599 718.618,775.016 719.665,774.621 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="837.274,738.784 836.227,739.189 836.038,729.608 837.085,729.211 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="1009.275,672.248 1008.228,672.654 1008.011,661.656 1009.058,661.259 						" />
												</g>
												<g>
													<polygon fill="#B2B2B2" points="923.505,705.415 922.457,705.82 922.268,696.237 923.316,695.842 						" />
												</g>

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="374.249" y1="915.776"
												 x2="1008.671" y2="668.364" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="374.187" y1="912.637"
												 x2="1008.609" y2="665.224" />

												<line fill="none" stroke="#B2B2B2" stroke-width="0.3821" stroke-miterlimit="10" x1="374.127" y1="909.392"
												 x2="1008.546" y2="662.027" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s19_1_" class="tooltip-item" title="s19_1_">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1386.965,158.535 1547.146,103.506 1650.887,180.434 1487.064,238.831 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1386.965,158.535 1547.146,103.506 1650.887,180.434 
			1487.064,238.831 		" />
								</g>
							</g>
							<g id="s18_1_" class="tooltip-item" title="s18_1_">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1487.064,238.831 1650.887,180.434 1832.026,314.308 1711.502,366.014 
			1749.956,396.599 1877.705,348.212 1923.325,384.883 1750.203,447.215 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1487.064,238.831 1650.887,180.434 1832.026,314.308 
			1711.502,366.014 1749.956,396.599 1877.705,348.212 1923.325,384.883 1750.203,447.215 		" />
									<g>
										<polygon fill="#005A84" points="1903.226,370.6 1738.419,429.668 1751.775,438.789 1919.924,376.629 			" />
										<polygon fill="#32A2D5" points="1738.419,429.668 1738.291,436.09 1750.513,445.453 1920.076,384.336 1919.924,376.629 
				1751.775,438.789 			" />
									</g>
									<g>
										<g>
											<polygon fill="#9DC8E7" points="1735.63,241.135 1735.972,245.711 1707.127,251.986 1703.325,247.97 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1725.726,245.537 1736.043,243.599 1736.011,242.346 1725.596,244.365 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1744.981,252.425 1746.016,252.301 1745.48,247.785 1744.465,248.091 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="1735.972,245.711 1745.047,252.986 1744.472,248.141 1735.612,240.982 				" />
										</g>
										<g>
											<polygon fill="#004669" points="1711.859,263.471 1745.318,252.503 1735.902,244.787 1704.379,251.858 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1833.955,313.133 1835.811,341.083 1802.112,354.872 1798.768,322.534 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="1804.989,330.198 1815.757,326.572 1815.339,323.891 1804.686,327.372 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="1819.986,325.814 1830.752,322.188 1830.337,319.508 1819.683,322.987 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="1798.772,322.519 1802.11,354.872 1706.606,277.916 1702.301,247.599 1709.724,255.54 
					1710.543,261.185 1788.599,317.898 1788.821,320.894 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1745.95,251.748 1744.716,251.855 1830.456,312.817 1831.854,312.783 				" />
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="1720.017,271.245 1719.375,271.919 1722.611,274.534 1723.236,273.739 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1724.538,274.824 1723.893,275.497 1727.13,278.114 1727.755,277.319 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1729.109,278.259 1728.466,278.932 1731.703,281.547 1732.326,280.753 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1733.732,281.687 1733.089,282.362 1736.326,284.976 1736.951,284.181 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1738.001,285.17 1737.356,285.843 1740.593,288.458 1741.218,287.664 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1742.997,289.018 1742.354,289.691 1745.591,292.308 1746.216,291.513 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1747.836,292.83 1747.193,293.504 1750.43,296.119 1751.053,295.324 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1752.912,296.744 1752.267,297.419 1755.504,300.033 1756.129,299.238 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1757.816,300.421 1757.171,301.096 1760.408,303.711 1761.033,302.916 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1762.679,304.144 1762.036,304.819 1765.273,307.434 1765.896,306.638 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1767.285,307.854 1766.641,308.529 1769.876,311.144 1770.502,310.351 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1772.207,311.766 1771.564,312.439 1774.8,315.055 1775.424,314.26 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1777.452,315.915 1776.807,316.589 1780.044,319.204 1780.669,318.409 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1782.676,319.943 1782.033,320.617 1785.27,323.232 1785.893,322.437 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="1718.274,274.409 1717.631,275.083 1720.868,277.698 1721.493,276.903 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1722.795,277.988 1722.15,278.661 1725.386,281.276 1726.012,280.483 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1727.366,281.423 1726.723,282.096 1729.96,284.711 1730.583,283.917 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1731.989,284.851 1731.346,285.526 1734.582,288.14 1735.208,287.345 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1736.258,288.332 1735.613,289.007 1738.849,291.622 1739.475,290.828 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1741.253,292.182 1740.61,292.855 1743.847,295.472 1744.473,294.677 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1746.093,295.994 1745.45,296.668 1748.687,299.283 1749.31,298.488 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1751.169,299.908 1750.524,300.583 1753.761,303.197 1754.386,302.402 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1756.073,303.585 1755.428,304.26 1758.665,306.875 1759.29,306.08 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1760.936,307.308 1760.293,307.981 1763.53,310.598 1764.153,309.802 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1765.541,311.018 1764.896,311.693 1768.133,314.308 1768.758,313.513 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1770.463,314.93 1769.82,315.603 1773.057,318.217 1773.681,317.424 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1775.709,319.079 1775.064,319.753 1778.301,322.368 1778.926,321.573 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1780.932,323.107 1780.29,323.781 1783.526,326.396 1784.15,325.601 					" />
											</g>
											<polygon fill="#002E4B" points="1716.188,276.898 1715.545,277.573 1718.781,280.188 1719.405,279.392 				" />
											<polygon fill="#002E4B" points="1720.706,280.478 1720.063,281.151 1723.3,283.767 1723.923,282.972 				" />
											<polygon fill="#002E4B" points="1725.279,283.913 1724.634,284.585 1727.871,287.202 1728.496,286.407 				" />
											<polygon fill="#002E4B" points="1729.902,287.34 1729.259,288.015 1732.496,290.63 1733.119,289.837 				" />
											<polygon fill="#002E4B" points="1734.169,290.823 1733.526,291.496 1736.763,294.111 1737.386,293.318 				" />
											<polygon fill="#002E4B" points="1739.167,294.672 1738.524,295.347 1741.759,297.962 1742.384,297.166 				" />
											<g>
												<polygon fill="#002E4B" points="1744.006,298.483 1743.363,299.158 1746.599,301.773 1747.224,300.978 					" />
											</g>
											<polygon fill="#002E4B" points="1749.08,302.397 1748.437,303.072 1751.674,305.687 1752.297,304.892 				" />
											<polygon fill="#002E4B" points="1753.984,306.075 1753.342,306.75 1756.578,309.365 1757.202,308.571 				" />
											<polygon fill="#002E4B" points="1758.849,309.798 1758.204,310.472 1761.441,313.087 1762.066,312.292 				" />
											<g>
												<polygon fill="#002E4B" points="1763.453,313.51 1762.81,314.183 1766.047,316.798 1766.67,316.004 					" />
											</g>
											<polygon fill="#002E4B" points="1768.377,317.419 1767.732,318.094 1770.969,320.709 1771.594,319.914 				" />
											<polygon fill="#002E4B" points="1773.62,321.568 1772.977,322.243 1776.214,324.858 1776.838,324.063 				" />
											<g>
												<polygon fill="#002E4B" points="1778.846,325.596 1778.201,326.271 1781.438,328.886 1782.063,328.091 					" />
											</g>
											<polygon fill="#002E4B" points="1713.843,279.45 1713.2,280.123 1716.437,282.738 1717.06,281.945 				" />
											<polygon fill="#002E4B" points="1718.361,283.028 1717.719,283.703 1720.955,286.318 1721.579,285.524 				" />
											<polygon fill="#002E4B" points="1722.935,286.463 1722.29,287.138 1725.527,289.752 1726.152,288.957 				" />
											<polygon fill="#002E4B" points="1727.558,289.893 1726.915,290.565 1730.151,293.182 1730.775,292.387 				" />
											<polygon fill="#002E4B" points="1731.825,293.374 1731.182,294.048 1734.419,296.663 1735.042,295.868 				" />
											<polygon fill="#002E4B" points="1736.823,297.224 1736.179,297.897 1739.414,300.512 1740.04,299.719 				" />
											<g>
												<polygon fill="#002E4B" points="1741.662,301.036 1741.019,301.708 1744.254,304.323 1744.879,303.53 					" />
											</g>
											<polygon fill="#002E4B" points="1746.736,304.95 1746.093,305.622 1749.33,308.237 1749.953,307.444 				" />
											<polygon fill="#002E4B" points="1751.64,308.627 1750.997,309.302 1754.234,311.917 1754.857,311.122 				" />
											<polygon fill="#002E4B" points="1756.505,312.35 1755.86,313.023 1759.097,315.638 1759.722,314.844 				" />
											<g>
												<polygon fill="#002E4B" points="1761.108,316.06 1760.465,316.735 1763.702,319.35 1764.325,318.555 					" />
											</g>
											<polygon fill="#002E4B" points="1766.032,319.97 1765.388,320.644 1768.624,323.259 1769.25,322.466 				" />
											<polygon fill="#002E4B" points="1771.276,324.12 1770.633,324.793 1773.87,327.41 1774.493,326.615 				" />
											<g>
												<polygon fill="#002E4B" points="1776.502,328.149 1775.857,328.821 1779.094,331.438 1779.719,330.643 					" />
											</g>
										</g>
										<g>
											<polygon fill="#00486C" points="1830.456,312.817 1744.775,251.835 1726.172,244.941 1726.352,252.57 1817.959,320.72 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="1710.514,261.239 1797.321,327.37 1817.027,318.475 1725.879,252.175 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="1735.796,240.029 1702.298,247.582 1711.313,257.295 1712.139,256.771 1703.957,247.806 
						1735.63,241.135 1744.472,248.141 1745.48,247.785 					" />
											</g>
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1710.543,261.185 1711.502,260.199 1710.745,255.267 1709.732,255.573 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1705.887,249.93 1717.319,247.566 1717.19,246.306 1704.756,249.11 				" />
										</g>
										<g>
											<polygon fill="#0A2644" points="1817.2,316.589 1819.098,315.696 1818.883,313.25 1816.983,314.145 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1720.648,247.372 1722.641,246.967 1722.425,244.522 1720.433,244.926 				" />
										</g>
										<g>
											<polygon fill="#FFFFFF" points="1796.785,345.799 1799.188,348.212 1797.88,342.504 1795.738,340.817 				" />
										</g>
									</g>
									<g>
										<polygon fill="#32A2D5" points="1677.897,282.613 1682.28,248.903 1692.405,254.024 1692.132,290.073 			" />
										<polygon fill="#9DC8E7" points="1692.405,254.024 1692.151,289.969 1708.276,283.131 1704.365,248.468 			" />
										<polygon fill="#005A84" points="1682.11,248.903 1682.045,245.638 1692.341,250.769 1692.405,254.024 			" />
									</g>
									<polygon fill="#005A84" points="1692.405,254.024 1704.365,248.468 1703.958,246.2 1692.341,250.769 		" />
									<polygon fill="#00486C" points="1682.047,245.713 1692.601,241.767 1703.958,246.2 1692.362,251.819 		" />
									<g>
										<g enable-background="new    ">
											<g>
												<polygon fill="#9DC8E7" points="1628.593,348.83 1627.948,357.851 1614.88,361.837 1615.528,352.816 					" />
											</g>
											<g>
												<polygon fill="#32A2D5" points="1615.528,352.816 1614.88,361.837 1603.652,353.316 1604.299,344.295 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="1628.593,348.83 1615.528,352.816 1604.299,344.295 1617.365,340.309 					" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s17_1_" class="tooltip-item" title="s17_1_">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1750.509,397.27 1711.502,366.014 1832.026,314.308 1876.667,348.637 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1750.509,397.27 1711.502,366.014 1832.026,314.308 
			1876.667,348.637 		" />
									<g>
										<g>
											<g>
												<g>
													<polygon fill="#32A2D5" points="1804.62,363.931 1802.467,341.853 1783.547,322.104 1786.084,345.657 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1804.62,363.931 1849.078,345.173 1848.343,323.389 1802.317,341.164 						" />
												</g>
												<g>
													<path fill="#00A0E9" d="M1781.179,325.225l35.65-14.39l0.37-0.136l4.634,5.038l9.272-3.499l18.64,12.398l-45.724,17.805
							l-0.404,0.148L1781.179,325.225z M1830.82,313.606l-9.318,3.517l-4.641-5.043l-34.713,13.141l21.863,15.966l43.56-17.004
							L1830.82,313.606z" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1829.01,341.762 1842.782,336.302 1842.536,330.387 1828.767,335.849 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1807.449,349.269 1821.266,343.931 1820.592,338.543 1806.619,343.669 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1829.717,350.956 1843.489,345.498 1843.244,339.581 1829.474,345.041 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1808.741,359.364 1822.559,354.027 1821.885,348.637 1808.485,353.741 						" />
												</g>
											</g>
											<g>
												<polygon fill="#005A84" points="1847.709,324.238 1826.599,324.68 1816.931,326.073 1806.782,325.498 1792.598,332.801 
						1804.446,341.089 					" />
											</g>
											<g>
												<polygon fill="#00486C" points="1782.235,325.228 1792.547,332.771 1807.806,325.935 1817.707,326.501 1827.13,325.135 
						1847.659,324.209 1830.843,313.559 1821.559,317.146 1816.9,312.089 					" />
											</g>
										</g>
										<g>
											<g>
												<g>
													<polygon fill="#32A2D5" points="1765.62,379.224 1763.467,357.146 1740.57,340.153 1743.105,363.707 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1765.62,379.224 1806.1,363.223 1805.366,341.439 1763.317,356.456 						" />
												</g>
												<g>
													<polygon fill="#1F3E60" points="1804.381,341.624 1786.686,330.137 1777.391,333.646 1772.754,328.604 1741.502,340.419 
							1763.515,356.345 						" />
													<path fill="#00A0E9" d="M1740.534,340.144l32.019-12.094l0.37-0.136l4.634,5.038l9.272-3.499l18.64,12.398l-41.747,15.046
							l-0.404,0.148L1740.534,340.144z M1786.544,330.821l-9.318,3.517l-4.641-5.043l-30.116,11.401l21.243,14.947l39.583-14.247
							L1786.544,330.821z" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1786.033,359.811 1799.804,354.351 1799.559,348.437 1785.788,353.899 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1768.449,364.562 1782.266,359.224 1781.59,353.834 1767.617,358.962 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1786.74,369.006 1800.51,363.548 1800.266,357.631 1786.495,363.093 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1769.741,374.656 1783.559,369.32 1782.885,363.93 1769.485,369.033 						" />
												</g>
											</g>
											<g>
												<polygon fill="#005A84" points="1803.297,341.419 1782.187,341.858 1772.517,343.254 1762.368,342.677 1748.969,348.626 
						1763.584,355.676 					" />
											</g>
											<g>
												<polygon fill="#00486C" points="1742.519,340.811 1749.438,349.425 1763.442,343.143 1773.345,343.709 1782.766,342.345 
						1803.297,341.419 1786.481,330.769 1777.196,334.354 1772.539,329.297 					" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s16_1_" class="tooltip-item" title="s16_1_">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1284.542,271.116 1411.022,226.034 1663.836,430.872 1532.333,482.18 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1284.542,271.116 1411.022,226.034 1663.836,430.872 
			1532.333,482.18 		" />
									<g>
										<polygon fill="#005178" points="1319.31,330.423 1344.392,351.226 1417.479,327.177 1391.658,305.715 			" />

										<polygon opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
										 enable-background="new    " points="
				1328.213,331.886 1388.514,311.995 1408.626,327.518 1346.431,346.44 			" />

										<path opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
										 enable-background="new    " d="
				M1333.654,336.233c0,0,10.874-5.934,16.876,0.13c4.036,5.851-6.3,8.32-6.3,8.32" />

										<path opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
										 enable-background="new    " d="
				M1402.137,322.509c0,0-8.588,6.483-13.6,0.568s3.559-8.148,3.559-8.148" />

										<line opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
										 enable-background="new    " x1="1363.888" y1="320.751" x2="1381.399" y2="336.374" />

										<circle opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
										 enable-background="new    " cx="1371.246" cy="327.873" r="5.281" />
									</g>
									<g>
										<g>
											<polygon fill="#9DC8E7" points="1431.071,242.225 1431.104,244.563 1416.246,246.869 1414.436,244.709 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1425.662,244.201 1431.206,243.49 1431.228,242.853 1425.633,243.601 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1435.479,248.253 1436.01,248.221 1435.876,245.91 1435.35,246.034 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="1431.104,244.563 1435.496,248.541 1435.353,246.06 1431.068,242.148 				" />
										</g>
										<g>
											<polygon fill="#004669" points="1418.3,252.854 1435.649,248.303 1431.098,244.091 1414.852,246.718 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1447.745,254.364 1447.833,268.631 1430.275,274.609 1429.566,258.064 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="1432.495,262.15 1438.079,260.638 1437.95,259.263 1432.428,260.705 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="1440.254,260.382 1445.841,258.869 1445.71,257.494 1440.187,258.936 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="1429.568,258.056 1430.273,274.609 1415.185,260.036 1413.927,244.489 1417.457,248.754 
					1417.701,251.651 1424.538,255.394 1424.559,256.925 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="1417.684,251.676 1428.683,260.478 1442.166,250.395 1435.479,248.253 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="1431.191,241.668 1413.925,244.48 1417.459,248.771 1417.985,248.648 1414.763,244.645 
						1431.071,242.225 1435.353,246.06 1435.876,245.91 					" />
											</g>
										</g>
										<g>
											<polygon fill="#9DC8E7" points="1417.701,251.651 1418.219,251.178 1417.985,248.648 1417.459,248.771 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="1415.677,245.784 1421.663,244.84 1421.641,244.332 1415.127,245.333 				" />
										</g>
										<g>
											<polygon fill="#0A2644" points="1439.122,255.605 1440.114,255.21 1440.079,253.959 1439.086,254.357 				" />
										</g>
										<g>
											<polygon fill="#004669" points="1428.974,259.833 1446.917,254.216 1442.166,250.395 1422.988,255.362 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="1428.683,260.478 1447.753,254.38 1442.602,250.154 1442.166,250.395 1446.917,254.216 
						1428.974,259.833 1422.988,255.362 1422.463,255.515 					" />
											</g>
										</g>
									</g>
									<g>
										<g enable-background="new    ">
											<g>
												<polygon fill="#9DC8E7" points="1478.963,376.117 1478.318,385.136 1465.253,389.122 1465.898,380.103 					" />
											</g>
											<g>
												<polygon fill="#32A2D5" points="1465.898,380.103 1465.253,389.122 1454.024,380.602 1454.67,371.582 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="1478.963,376.117 1465.898,380.103 1454.67,371.582 1467.735,367.596 					" />
											</g>
										</g>
									</g>
									<g>
										<g enable-background="new    ">
											<g>
												<polygon fill="#9DC8E7" points="1632.534,399.616 1631.889,408.636 1618.823,412.622 1619.469,403.602 					" />
											</g>
											<g>
												<polygon fill="#32A2D5" points="1619.469,403.602 1618.823,412.622 1594.237,393.905 1594.882,384.883 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="1632.534,399.616 1619.469,403.602 1594.882,384.883 1607.948,380.898 					" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s15_1_" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1220.111,216.516 1284.542,271.116 1411.022,226.034 1345.858,173.007 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1220.111,216.516 1284.542,271.116 1411.022,226.034 
			1345.858,173.007 		" />
									<g>
										<polygon fill="#32A2D5" points="1336.261,193.159 1336.424,201.399 1346.226,209.836 1346.06,201.403 			" />
										<polygon fill="#9DC8E7" points="1346.226,209.836 1359.954,204.222 1359.789,195.859 1346.06,201.403 			" />
										<polygon fill="#005A84" points="1336.261,193.159 1345.858,191.133 1346.06,201.403 			" />
										<polygon fill="#004669" points="1345.858,191.133 1359.789,195.859 1346.06,201.403 			" />
										<polygon fill="#00486C" points="1359.789,195.859 1345.858,191.133 1347.263,187.398 			" />
										<polygon fill="#004669" points="1347.263,187.398 1336.261,193.159 1345.858,191.133 			" />
									</g>
									<g>
										<polygon fill="#9DC8E7" points="1263.641,245.538 1279.825,240.746 1279.283,232.711 1264.173,236.518 			" />
										<polygon fill="#32A2D5" points="1228.351,208.755 1228.532,217.925 1263.641,245.538 1264.173,236.518 			" />
										<polygon fill="#005A84" points="1228.351,208.755 1244.13,206.339 1266.301,225.218 1264.173,236.518 			" />
										<polygon fill="#32A2D5" points="1244.13,206.339 1245.877,203.065 1228.351,208.755 			" />
										<polygon fill="#004669" points="1264.173,236.518 1266.301,225.218 1279.283,232.711 			" />
										<polygon fill="#00486C" points="1245.877,203.065 1244.13,206.339 1266.301,225.218 1279.283,232.711 			" />
									</g>
								</g>
							</g>
							<g id="s14_1_" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1394.692,535.018 1253.132,405.537 1342.56,373.849 1487.277,499.555 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1394.692,535.018 1253.132,405.537 1342.56,373.849 
			1487.277,499.555 		" />
									<g>
										<g>
											<g enable-background="new    ">
												<g>
													<polygon fill="#32A2D5" points="1286.716,427.332 1278.89,420.371 1278.582,403.215 1286.408,410.176 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1286.408,410.176 1278.582,403.215 1291.893,400.899 1299.718,407.858 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1299.718,407.858 1300.025,425.016 1286.716,427.332 1286.408,410.176 						" />
												</g>
											</g>
										</g>
										<g>
											<g enable-background="new    ">
												<g>
													<polygon fill="#32A2D5" points="1306.17,454.067 1286.915,436.943 1286.161,394.726 1305.417,411.852 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1305.417,411.852 1286.161,394.726 1306.928,391.388 1326.183,408.514 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1326.183,408.514 1326.937,450.731 1306.17,454.067 1305.417,411.852 						" />
												</g>
											</g>
										</g>
										<g>
											<g enable-background="new    ">
												<g>
													<polygon fill="#32A2D5" points="1325.406,471.185 1306.151,454.06 1305.516,417.896 1324.772,435.022 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1324.772,435.022 1305.516,417.896 1326.283,414.559 1345.539,431.684 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1345.539,431.684 1346.173,467.848 1325.406,471.185 1324.772,435.022 						" />
												</g>
											</g>
										</g>
										<g>
											<g enable-background="new    ">
												<g>
													<polygon fill="#32A2D5" points="1344.648,488.302 1325.393,471.176 1324.639,428.959 1343.895,446.085 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1343.895,446.085 1324.639,428.959 1345.406,425.622 1364.661,442.747 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1364.661,442.747 1365.415,484.964 1344.648,488.302 1343.895,446.085 						" />
												</g>
											</g>
										</g>
										<g>
											<g enable-background="new    ">
												<g>
													<polygon fill="#32A2D5" points="1357.616,494.267 1349.79,487.308 1349.485,470.15 1357.309,477.111 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1357.309,477.111 1349.485,470.15 1362.794,467.835 1370.62,474.795 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1370.62,474.795 1370.926,491.951 1357.616,494.267 1357.309,477.111 						" />
												</g>
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#004669" points="1289.67,404.602 1289.91,408.044 1291.706,409.797 1291.453,406.206 					" />
												<polygon fill="#004669" points="1292.848,407.366 1293.089,410.806 1294.885,412.56 1294.634,408.97 					" />
												<polygon fill="#004669" points="1296.294,409.793 1296.537,413.234 1298.333,414.987 1298.08,411.396 					" />
												<polygon fill="#004669" points="1299.897,412.577 1300.137,416.018 1301.934,417.771 1301.682,414.18 					" />
												<polygon fill="#004669" points="1289.695,410.255 1289.935,413.696 1291.731,415.449 1291.48,411.858 					" />
												<polygon fill="#004669" points="1292.873,413.018 1293.115,416.458 1294.912,418.213 1294.659,414.622 					" />
												<polygon fill="#004669" points="1296.321,415.445 1296.562,418.884 1298.358,420.639 1298.107,417.048 					" />
												<polygon fill="#004669" points="1299.922,418.229 1300.164,421.669 1301.961,423.424 1301.707,419.833 					" />
												<polygon fill="#004669" points="1289.634,415.913 1289.876,419.354 1291.67,421.107 1291.419,417.518 					" />
												<polygon fill="#004669" points="1292.814,418.676 1293.054,422.116 1294.85,423.871 1294.599,420.28 					" />
												<polygon fill="#004669" points="1296.26,421.103 1296.501,424.544 1298.297,426.297 1298.045,422.706 					" />
												<polygon fill="#004669" points="1299.863,423.887 1300.103,427.329 1301.899,429.081 1301.648,425.49 					" />
												<polygon fill="#004669" points="1289.661,421.565 1289.901,425.006 1291.697,426.759 1291.446,423.168 					" />
												<polygon fill="#004669" points="1292.839,424.329 1293.08,427.768 1294.876,429.523 1294.625,425.932 					" />
												<polygon fill="#004669" points="1296.285,426.755 1296.528,430.197 1298.324,431.95 1298.071,428.359 					" />
												<polygon fill="#004669" points="1299.888,429.539 1300.129,432.981 1301.925,434.734 1301.673,431.143 					" />
											</g>
											<g>
												<polygon fill="#004669" points="1289.65,427.327 1289.892,430.767 1291.686,432.522 1291.435,428.931 					" />
												<polygon fill="#004669" points="1292.83,430.089 1293.07,433.531 1294.866,435.284 1294.615,431.693 					" />
												<polygon fill="#004669" points="1296.276,432.518 1296.516,435.957 1298.313,437.71 1298.061,434.121 					" />
												<polygon fill="#004669" points="1299.879,435.302 1300.119,438.741 1301.915,440.494 1301.664,436.905 					" />
												<polygon fill="#004669" points="1289.677,432.98 1289.917,436.419 1291.713,438.174 1291.462,434.583 					" />
												<polygon fill="#004669" points="1292.855,435.742 1293.095,439.183 1294.891,440.936 1294.64,437.345 					" />
												<polygon fill="#004669" points="1296.301,438.17 1296.543,441.61 1298.34,443.363 1298.086,439.774 					" />
												<polygon fill="#004669" points="1299.904,440.954 1300.144,444.394 1301.94,446.147 1301.689,442.558 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#004669" points="1327.286,436.419 1327.527,439.861 1329.323,441.614 1329.072,438.023 					" />
												<polygon fill="#004669" points="1330.465,439.183 1330.705,442.623 1332.501,444.377 1332.25,440.787 					" />
												<polygon fill="#004669" points="1333.911,441.61 1334.153,445.049 1335.949,446.804 1335.696,443.213 					" />
												<polygon fill="#004669" points="1337.514,444.394 1337.754,447.833 1339.55,449.588 1339.299,445.997 					" />
												<polygon fill="#004669" points="1327.312,442.072 1327.552,445.513 1329.348,447.266 1329.097,443.675 					" />
												<polygon fill="#004669" points="1330.492,444.835 1330.732,448.275 1332.528,450.028 1332.275,446.439 					" />
												<polygon fill="#004669" points="1333.938,447.262 1334.178,450.702 1335.974,452.456 1335.723,448.865 					" />
												<polygon fill="#004669" points="1337.539,450.046 1337.781,453.486 1339.577,455.241 1339.324,451.65 					" />
												<polygon fill="#004669" points="1327.25,447.73 1327.493,451.171 1329.289,452.924 1329.036,449.333 					" />
												<polygon fill="#004669" points="1330.431,450.493 1330.671,453.933 1332.467,455.688 1332.216,452.097 					" />
												<polygon fill="#004669" points="1333.877,452.92 1334.119,456.359 1335.913,458.114 1335.662,454.523 					" />
												<polygon fill="#004669" points="1337.48,455.704 1337.72,459.144 1339.516,460.898 1339.265,457.308 					" />
												<polygon fill="#004669" points="1327.277,453.382 1327.518,456.823 1329.314,458.576 1329.063,454.985 					" />
												<polygon fill="#004669" points="1330.456,456.146 1330.696,459.585 1332.492,461.34 1332.241,457.749 					" />
												<polygon fill="#004669" points="1333.904,458.572 1334.144,462.012 1335.94,463.767 1335.689,460.176 					" />
												<polygon fill="#004669" points="1337.505,461.357 1337.747,464.796 1339.541,466.551 1339.29,462.96 					" />
											</g>
											<g>
												<polygon fill="#004669" points="1327.266,459.144 1327.509,462.584 1329.305,464.339 1329.052,460.748 					" />
												<polygon fill="#004669" points="1330.447,461.906 1330.687,465.348 1332.483,467.101 1332.232,463.51 					" />
												<polygon fill="#004669" points="1333.893,464.333 1334.135,467.774 1335.929,469.527 1335.678,465.936 					" />
												<polygon fill="#004669" points="1337.495,467.117 1337.736,470.558 1339.532,472.311 1339.281,468.72 					" />
												<polygon fill="#004669" points="1327.293,464.797 1327.534,468.236 1329.33,469.991 1329.079,466.4 					" />
												<polygon fill="#004669" points="1330.472,467.559 1330.714,471 1332.508,472.753 1332.257,469.162 					" />
												<polygon fill="#004669" points="1333.92,469.985 1334.16,473.427 1335.956,475.18 1335.705,471.589 					" />
												<polygon fill="#004669" points="1337.521,472.769 1337.763,476.211 1339.557,477.964 1339.306,474.373 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#004669" points="1308.642,422.96 1308.883,426.399 1310.679,428.152 1310.428,424.563 					" />
												<polygon fill="#004669" points="1311.821,425.722 1312.063,429.163 1313.859,430.916 1313.606,427.325 					" />
												<polygon fill="#004669" points="1315.269,428.148 1315.509,431.59 1317.305,433.343 1317.054,429.752 					" />
												<polygon fill="#004669" points="1318.87,430.932 1319.112,434.374 1320.908,436.127 1320.655,432.536 					" />
												<polygon fill="#004669" points="1308.667,428.61 1308.91,432.052 1310.704,433.805 1310.453,430.216 					" />
												<polygon fill="#004669" points="1311.848,431.374 1312.088,434.814 1313.884,436.568 1313.633,432.978 					" />
												<polygon fill="#004669" points="1315.294,433.801 1315.534,437.242 1317.33,438.995 1317.079,435.404 					" />
												<polygon fill="#004669" points="1318.897,436.585 1319.137,440.026 1320.933,441.779 1320.682,438.188 					" />
												<polygon fill="#004669" points="1308.608,434.27 1308.849,437.71 1310.645,439.464 1310.394,435.874 					" />
												<polygon fill="#004669" points="1311.787,437.032 1312.027,440.473 1313.823,442.226 1313.572,438.635 					" />
												<polygon fill="#004669" points="1315.233,439.458 1315.475,442.9 1317.271,444.653 1317.018,441.062 					" />
												<polygon fill="#004669" points="1318.836,442.243 1319.076,445.684 1320.872,447.437 1320.621,443.846 					" />
												<polygon fill="#004669" points="1308.633,439.922 1308.874,443.362 1310.67,445.115 1310.419,441.526 					" />
												<polygon fill="#004669" points="1311.812,442.684 1312.054,446.126 1313.85,447.879 1313.597,444.288 					" />
												<polygon fill="#004669" points="1315.26,445.111 1315.5,448.552 1317.296,450.305 1317.045,446.714 					" />
												<polygon fill="#004669" points="1318.861,447.895 1319.103,451.337 1320.899,453.089 1320.646,449.499 					" />
											</g>
											<g>
												<polygon fill="#004669" points="1308.624,445.683 1308.865,449.125 1310.661,450.877 1310.41,447.286 					" />
												<polygon fill="#004669" points="1311.803,448.447 1312.043,451.886 1313.839,453.641 1313.588,450.05 					" />
												<polygon fill="#004669" points="1315.249,450.873 1315.491,454.313 1317.287,456.068 1317.034,452.477 					" />
												<polygon fill="#004669" points="1318.852,453.658 1319.092,457.097 1320.888,458.852 1320.637,455.261 					" />
												<polygon fill="#004669" points="1308.649,451.335 1308.89,454.777 1310.686,456.53 1310.435,452.939 					" />
												<polygon fill="#004669" points="1311.828,454.099 1312.07,457.539 1313.866,459.292 1313.613,455.703 					" />
												<polygon fill="#004669" points="1315.276,456.526 1315.516,459.965 1317.312,461.72 1317.061,458.129 					" />
												<polygon fill="#004669" points="1318.877,459.31 1319.119,462.749 1320.915,464.504 1320.662,460.913 					" />
											</g>
										</g>
										<polygon fill="#004669" points="1362.105,482.823 1362.21,488.149 1366.599,487.474 1366.495,482.18 			" />
									</g>
								</g>
							</g>
							<g id="s13_1_" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1208.152,607.322 1069.791,473.339 1253.132,405.537 1394.692,535.018 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1208.152,607.322 1069.791,473.339 1253.132,405.537 
			1394.692,535.018 		" />
									<g>
										<polygon fill="#005A84" points="1168.68,545.147 1169.963,536.608 1268.091,511.83 1268.558,518.443 			" />
										<g>
											<g>
												<polygon fill="#32A2D5" points="1218.324,499.025 1219.511,512.318 1228.723,521.77 1237.688,519.507 					" />
												<polygon fill="#32A2D5" points="1141.729,492.985 1141.946,504.02 1129.028,508.271 1127.778,497.694 					" />
												<polygon fill="#32A2D5" points="1092.89,476.757 1091.795,486.037 1168.917,557.188 1168.68,545.147 					" />
												<polygon fill="#005A84" points="1092.89,476.757 1102.955,473.882 1169.963,536.608 1168.68,545.147 					" />
												<polygon fill="#9DC8E7" points="1168.917,557.188 1269.369,523.821 1268.558,518.443 1168.68,545.147 					" />
												<polygon fill="#005A84" points="1152.735,449.749 1183.791,477.806 1223.105,464.995 1193.202,436.921 					" />
												<polygon fill="none" stroke="#00A0E9" stroke-width="0.7643" stroke-miterlimit="10" points="1152.379,449.637 
						1183.895,478.516 1223.723,465.11 1193.266,436.521 					" />
												<polygon fill="#004669" points="1187.485,443.758 1184.176,477.677 1223.105,464.995 					" />
												<polygon fill="#32A2D5" points="1154.182,453.819 1154.324,461.027 1102.35,473.857 1128.731,497.78 1183.958,481.719 					" />
												<polygon fill="#9DC8E7" points="1183.895,478.516 1184.39,503.618 1224.24,491.368 1223.732,465.593 					" />
												<polygon fill="#32A2D5" points="1184.39,503.618 1151.378,474.674 1151.69,449.518 1183.895,478.516 					" />
												<g>
													<path fill="#00A0E9" d="M1237.688,519.507l-20.806-20.844l30.063-9.1l21.146,22.267L1237.688,519.507z M1219.766,499.387
							l18.77,18.802l27.695-7.224l-19.717-19.674L1219.766,499.387z" />
												</g>
												<polygon fill="#005A84" points="1267.007,511.417 1246.88,490.223 1217.782,498.642 1238.187,518.934 					" />
												<polygon fill="#002E4B" points="1206.41,483.358 1206.754,496.743 1214.349,494.408 1213.823,480.973 					" />
												<polygon fill="#005A84" points="1115.604,486.882 1128.304,482.624 1141.749,494.004 1127.798,498.713 					" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s12_1_" class="tooltip-item" title>
								<g>
									<polygon opacity="0" fill="#B38D1B" points="1023.704,489.563 1053.379,517.5 1099.466,501.276 1069.791,473.339 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1023.704,489.563 1053.379,517.5 1099.466,501.276 
			1069.791,473.339 		" />
									<g>
										<g>
											<polygon fill="#00486C" points="1054.732,506.715 1029.397,481.562 1048.665,477.847 1074,503 				" />
											<polygon fill="#32A2D5" points="1029.397,481.562 1029.586,491.141 1054.921,516.294 1054.732,506.715 				" />
										</g>
									</g>
								</g>
							</g>
							<g id="s11" class="tooltip-item" title>
								<g>
									<polygon opacity="0" fill="#B38D1B" points="1053.379,517.5 1160.038,625.532 1208.152,607.322 1099.466,501.276 	" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1053.379,517.5 1160.038,625.532 1208.152,607.322 
		1099.466,501.276 	" />
									<g>
										<g>
											<polygon fill="#00486C" points="1054.732,506.715 1074,503 1148.719,575.947 1170.699,567.048 1202.186,594.337 
				1161.377,607.322 			" />
											<polygon fill="#32A2D5" points="1054.732,506.715 1054.921,516.294 1161.14,619.262 1161.377,607.322 			" />
											<polygon fill="#9DC8E7" points="1202.186,594.337 1201.547,606.53 1161.14,619.262 1161.377,607.322 			" />
										</g>
										<polygon fill="#002E4B" points="1099.549,559.277 1099.466,555.063 1102.488,555.003 1102.632,562.283 		" />
									</g>
								</g>
							</g>
							<g id="s10" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="880.105,542.585 977.465,507.799 1075.618,608.347 976.598,647.168 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="880.105,542.585 977.465,507.799 1075.618,608.347 
			976.598,647.168 		" />
									<g>
										<polygon fill="#002E4B" points="967.422,622.414 1080.445,581.784 1080.804,592.318 974.7,629.547 			" />
										<polygon fill="#005A84" points="1080.445,581.784 1072.529,575.871 961.936,616.631 967.171,622.422 			" />
									</g>
									<g>
										<g>
											<g>
												<g>
													<polygon fill="#9DC8E7" points="996.36,524.746 1006.419,521.025 1005.38,499.596 995.503,502.961 						" />
												</g>
												<g>
													<polygon fill="#1F3E60" points="1004.305,499.365 986.47,488.24 907.94,513.329 929.798,530.808 998.625,507.034 
							995.051,502.589 						" />
													<path fill="#00A0E9" d="M907.044,513.097l79.575-25.502l18.742,12.001l-9.366,3.263l3.576,4.447l-69.956,24.162
							L907.044,513.097z M994.105,502.319l9.142-3.187l-16.926-10.248l-77.402,24.723l21.06,16.538l67.7-23.383L994.105,502.319z" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="931.029,554.682 1000.384,528.438 999.354,507.366 929.424,531.543 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="963.022,527.251 971.578,524.557 971.371,518.494 962.82,521.484 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="946.656,533.081 957.378,529.173 957.206,523.383 946.488,527.292 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="993.725,516.917 995.464,516.244 995.432,511.54 993.691,512.081 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="989.19,518.415 990.928,517.711 990.899,513.071 989.155,513.614 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="994.511,528.051 996.25,527.377 996.218,522.672 994.477,523.215 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="989.976,529.549 991.714,528.843 991.683,524.202 989.943,524.745 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="930.162,538.852 940.912,534.925 940.565,529.528 929.644,533.165 						" />
												</g>
												<g>
													<polygon fill="#005A84" points="945.709,549.147 960.452,543.582 960.347,537.842 945.604,543.408 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="941.087,544.519 967.709,535.3 961.924,532.803 938.848,540.939 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1000.997,517.382 1005.143,515.847 1004.211,515.493 1000.619,516.83 						" />
												</g>
												<g>
													<polygon fill="#32A2D5" points="931.266,554.661 929.654,531.589 907.044,513.097 909.813,535.301 						" />
												</g>
											</g>
											<g>
												<polygon fill="#005A84" points="1001.591,522.886 1004.711,521.665 1004.485,516.313 1001.362,517.469 					" />
											</g>
										</g>
										<g>
											<polygon fill="#005A84" points="997.708,506.719 962.532,501.3 910.002,518.484 929.991,529.986 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="1003.163,499.147 977.257,496.831 967.44,499.868 993.9,502.445 				" />
										</g>
										<g>
											<polygon fill="#00486C" points="908.967,513.623 909.712,518.241 961.778,501.089 997.637,506.71 994.176,502.417 
					966.892,500.073 976.713,497.036 1003.234,499.145 986.323,488.88 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon fill="#9DC8E7" points="918.022,512.107 918.142,516.136 895.67,523.525 892.598,519.967 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="909.203,515.623 918.286,514.346 918.306,513.28 909.138,514.623 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="925.529,522.189 926.414,522.124 926.132,518.233 925.261,518.489 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="918.142,516.136 925.563,522.667 925.261,518.489 918.022,512.107 				" />
										</g>
										<g>
											<polygon fill="#004669" points="896.367,528.976 925.261,518.489 916.544,510.882 891.538,518.727 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="998.696,577.205 999.18,601.005 970.032,611.395 968.456,583.811 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="973.436,590.561 982.719,587.903 982.47,585.61 973.291,588.149 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="986.34,587.425 995.623,584.767 995.374,582.474 986.193,585.013 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="968.459,583.799 970.028,611.395 891.954,542.331 890.138,518.471 895.475,523.455 
					895.948,528.28 960.004,579.476 960.076,582.03 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="926.378,521.651 925.328,521.694 997.136,577.156 998.244,576.913 				" />
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="903.602,537.192 903.029,537.739 905.673,540.085 906.235,539.434 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="907.297,540.406 906.724,540.953 909.371,543.299 909.932,542.65 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="911.046,543.501 910.473,544.047 913.117,546.394 913.679,545.744 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="914.836,546.593 914.264,547.139 916.91,549.486 917.471,548.836 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="918.323,549.715 917.75,550.262 920.397,552.608 920.958,551.959 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="922.416,553.179 921.844,553.726 924.488,556.072 925.049,555.421 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="926.376,556.603 925.803,557.15 928.448,559.496 929.009,558.847 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="930.531,560.124 929.958,560.671 932.604,563.019 933.164,562.368 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="934.55,563.438 933.979,563.986 936.623,566.332 937.185,565.683 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="938.534,566.789 937.963,567.336 940.607,569.682 941.169,569.031 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="942.298,570.12 941.727,570.667 944.372,573.013 944.933,572.362 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="946.326,573.632 945.753,574.177 948.399,576.525 948.961,575.874 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="950.616,577.359 950.044,577.905 952.69,580.253 953.249,579.602 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="954.893,580.982 954.323,581.529 956.967,583.877 957.529,583.227 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="901.998,539.809 901.425,540.356 904.071,542.702 904.631,542.053 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="905.693,543.025 905.12,543.57 907.767,545.918 908.328,545.268 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="909.442,546.118 908.869,546.666 911.515,549.012 912.075,548.363 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="913.232,549.21 912.66,549.758 915.306,552.104 915.867,551.453 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="916.719,552.332 916.146,552.879 918.793,555.227 919.354,554.577 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="920.812,555.796 920.24,556.343 922.886,558.689 923.446,558.04 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="924.772,559.222 924.199,559.767 926.846,562.115 927.405,561.464 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="928.927,562.743 928.354,563.288 931,565.636 931.562,564.986 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="932.948,566.056 932.375,566.603 935.02,568.951 935.581,568.3 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="936.932,569.406 936.359,569.953 939.003,572.299 939.565,571.651 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="940.696,572.737 940.123,573.284 942.768,575.63 943.329,574.981 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="944.722,576.249 944.149,576.796 946.796,579.142 947.357,578.493 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="949.012,579.977 948.44,580.524 951.086,582.87 951.647,582.221 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="953.292,583.601 952.719,584.148 955.363,586.494 955.925,585.844 					" />
											</g>
											<polygon fill="#002E4B" points="900.128,541.842 899.556,542.389 902.202,544.735 902.761,544.085 				" />
											<polygon fill="#002E4B" points="903.825,545.056 903.253,545.603 905.897,547.949 906.458,547.301 				" />
											<polygon fill="#002E4B" points="907.572,548.152 906.999,548.699 909.646,551.045 910.207,550.394 				" />
											<polygon fill="#002E4B" points="911.363,551.244 910.79,551.789 913.437,554.137 913.998,553.486 				" />
											<polygon fill="#002E4B" points="914.849,554.365 914.279,554.912 916.923,557.258 917.485,556.61 				" />
											<polygon fill="#002E4B" points="918.943,557.829 918.37,558.376 921.016,560.722 921.576,560.072 				" />
											<g>
												<polygon fill="#002E4B" points="922.902,561.253 922.33,561.8 924.976,564.146 925.537,563.497 					" />
											</g>
											<polygon fill="#002E4B" points="927.057,564.774 926.484,565.322 929.131,567.669 929.692,567.019 				" />
											<polygon fill="#002E4B" points="931.078,568.089 930.505,568.636 933.15,570.982 933.711,570.333 				" />
											<polygon fill="#002E4B" points="935.062,571.439 934.489,571.986 937.134,574.332 937.695,573.682 				" />
											<g>
												<polygon fill="#002E4B" points="938.826,574.77 938.254,575.317 940.898,577.663 941.46,577.013 					" />
											</g>
											<polygon fill="#002E4B" points="942.852,578.282 942.28,578.828 944.926,581.175 945.487,580.525 				" />
											<polygon fill="#002E4B" points="947.143,582.01 946.57,582.557 949.216,584.903 949.778,584.252 				" />
											<g>
												<polygon fill="#002E4B" points="951.422,585.632 950.849,586.18 953.494,588.527 954.055,587.877 					" />
											</g>
											<polygon fill="#002E4B" points="898.037,543.915 897.465,544.462 900.111,546.81 900.673,546.16 				" />
											<polygon fill="#002E4B" points="901.734,547.131 901.162,547.679 903.808,550.025 904.368,549.374 				" />
											<polygon fill="#002E4B" points="905.481,550.225 904.909,550.772 907.555,553.12 908.116,552.469 				" />
											<polygon fill="#002E4B" points="909.274,553.317 908.701,553.864 911.346,556.21 911.907,555.561 				" />
											<polygon fill="#002E4B" points="912.761,556.44 912.188,556.986 914.834,559.334 915.394,558.683 				" />
											<polygon fill="#002E4B" points="916.852,559.902 916.279,560.45 918.926,562.797 919.487,562.147 				" />
											<g>
												<polygon fill="#002E4B" points="920.811,563.328 920.241,563.875 922.885,566.221 923.447,565.571 					" />
											</g>
											<polygon fill="#002E4B" points="924.968,566.849 924.396,567.397 927.04,569.743 927.601,569.092 				" />
											<polygon fill="#002E4B" points="928.987,570.164 928.415,570.711 931.061,573.057 931.622,572.406 				" />
											<polygon fill="#002E4B" points="932.971,573.512 932.398,574.06 935.045,576.407 935.606,575.757 				" />
											<g>
												<polygon fill="#002E4B" points="936.736,576.843 936.163,577.391 938.809,579.738 939.371,579.088 					" />
											</g>
											<polygon fill="#002E4B" points="940.763,580.355 940.191,580.903 942.835,583.249 943.397,582.6 				" />
											<polygon fill="#002E4B" points="945.054,584.083 944.481,584.63 947.125,586.978 947.687,586.328 				" />
											<g>
												<polygon fill="#002E4B" points="949.331,587.707 948.758,588.255 951.405,590.601 951.966,589.952 					" />
											</g>
										</g>
										<g>
											<polygon fill="#00486C" points="989.516,570.703 922.51,519.488 919.619,520.537 912.04,523.287 977.191,573.464 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="896.678,528.863 968.773,589.313 978.899,573.905 912.04,523.287 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="916.727,509.949 890.138,518.471 895.48,523.484 896.354,523.264 891.538,518.727 
						916.544,510.882 925.261,518.495 926.132,518.233 					" />
											</g>
										</g>
										<g>
											<polygon fill="#9DC8E7" points="895.948,528.28 896.976,529.113 896.354,523.264 895.48,523.484 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="968.773,589.313 972.759,583.247 968.367,584.265 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="896.295,520.871 905.017,518.113 904.192,517.388 895.366,520.13 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="909.274,516.752 917.996,513.994 917.17,513.268 908.344,516.01 				" />
										</g>
										<g>
											<polygon fill="#0A2644" points="984.337,579.483 985.982,578.799 985.895,576.714 984.249,577.399 				" />
										</g>
										<g>
											<polygon points="965.879,603.977 967.808,605.627 967.65,600.985 965.72,599.317 				" />
										</g>
										<g>
											<polygon fill="#004669" points="968.596,583.481 997.059,577.03 989.516,570.703 961.597,576.544 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="968.367,584.265 998.709,577.233 989.083,569.904 988.362,570.316 997.538,576.927 
						968.552,583.331 961.597,576.544 960.727,576.81 					" />
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s09" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1075.61,608.35 1111.321,646 1011.309,683.65 976.598,647.168 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1075.61,608.35 1111.321,646 1011.309,683.65 
			976.598,647.168 		" />
									<g>
										<g>
											<polygon fill="#32A2D5" points="980.231,638.381 979.805,647.571 1002.761,669.906 1002.398,659.252 				" />
											<g>
												<polygon fill="#005A84" points="980.231,638.381 1002.398,659.252 1060.52,639.533 1040.786,617.675 					" />
												<polygon fill="#9DC8E7" points="1002.769,669.624 1060.943,649.026 1060.52,639.533 1002.398,659.252 					" />
											</g>
											<polygon fill="#9DC8E7" points="1060.943,649.026 1059.737,631.313 1078.432,627.084 1079.227,643.507 				" />
											<polygon fill="#004669" points="1059.737,631.313 1044.964,613.826 1060.635,609.072 1078.432,627.084 				" />
											<polygon fill="#9DC8E7" points="1060.52,639.533 1059.737,631.313 1044.964,613.826 1045.244,622.057 				" />
										</g>
										<polygon fill="#005A84" points="1068.265,643.806 1067.955,634.698 1073.036,633.224 1073.344,642.272 			" />
									</g>
								</g>
							</g>
							<g id="s08" class="tooltip-item" title="s08">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1219.024,375.719 1091.795,260.382 1186.17,227.289 1310.645,344.157 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1219.024,375.719 1091.795,260.382 1186.17,227.289 
			1310.645,344.157 		" />
									<g>
										<g>
											<polygon fill="#32A2D5" points="1189.656,262.693 1154.199,233.571 1151.137,247.232 1189.643,281.62 				" />
											<polygon fill="#9DC8E7" points="1189.643,281.62 1217.809,276.707 1218.141,255.618 1189.656,262.693 				" />
											<polygon fill="#32A2D5" points="1218.141,255.618 1176.959,226.034 1154.199,233.571 1189.656,262.693 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="1099.358,252.242 1099.277,270.517 1115.936,283.503 1117.264,265.045 				" />
											<polygon fill="#9DC8E7" points="1115.936,283.503 1169.533,263.652 1171.194,247.021 1117.264,265.045 				" />
											<polygon fill="#32A2D5" points="1099.358,252.242 1154.299,233.571 1171.196,246.589 1117.264,265.045 				" />
										</g>
									</g>
								</g>
							</g>
							<g id="s07" class="tooltip-item" title="s07">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="730.917,385.949 1091.795,260.382 1219.024,375.719 849.206,511.54 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="730.917,385.949 1091.795,260.382 1219.024,375.719 
			849.206,511.54 		" />
									<g>
										<g>
											<polygon fill="#00486C" points="907.36,309.953 927.229,328.612 1102.637,277.62 1080.252,259.421 				" />
											<polygon fill="#09384E" points="927.447,338.301 1102.404,287.571 1102.637,277.62 927.229,328.612 				" />
											<polygon fill="none" stroke="#00A0E9" stroke-width="0.3821" stroke-miterlimit="10" points="907.36,309.953 927.229,328.612 
					1102.637,277.62 1080.252,259.421 				" />
										</g>
										<polygon fill="#09384E" points="906.68,310.427 908.17,320.193 927.42,338.302 926.976,328.621 			" />
									</g>
									<g>
										<polygon fill="#09384E" points="1006.734,404.141 1008.222,413.909 1027.474,432.017 1027.03,422.336 			" />
										<g>
											<polygon fill="#00486C" points="1006.734,404.141 1027.03,422.336 1201.209,367.293 1178.41,349.619 				" />
											<polygon fill="#09384E" points="1027.474,432.017 1201.207,377.247 1201.209,367.293 1027.03,422.336 				" />
											<polygon fill="none" stroke="#00A0E9" stroke-width="0.3821" stroke-miterlimit="10" points="1006.734,404.141 1027.03,422.336 
					1201.209,367.293 1178.41,349.619 				" />
										</g>
									</g>
									<g>
										<g>
											<polygon fill="#9DC8E7" points="877.708,311.215 877.774,315.944 842.555,330.008 840.42,323.608 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="862.936,319.205 876.241,314.597 874.984,313.536 861.54,318.116 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="888.88,326.492 890.225,326.413 889.887,320.552 888.556,320.866 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="877.792,317.139 888.923,327.22 888.559,320.933 877.696,311.016 				" />
										</g>
										<g>
											<polygon fill="#004669" points="850.372,340.267 889.31,326.619 877.774,315.944 838.32,331.047 				" />
										</g>
										<g>
											<polygon fill="#9DC8E7" points="998.825,411.652 999.043,447.815 954.541,462.965 952.747,421.028 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="960.168,431.389 974.326,427.554 973.997,424.067 959.998,427.724 				" />
										</g>
										<g>
											<polygon fill="#002E4B" points="979.836,426.907 993.994,423.072 993.664,419.584 979.666,423.241 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="952.754,421.011 954.537,462.965 839.281,349.12 838.101,323.374 843.202,327.764 
					843.941,334.976 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="890.182,325.694 888.584,325.737 984.004,401.538 985.809,401.606 				" />
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="855.247,348.805 854.365,349.622 858.333,353.246 859.199,352.269 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="860.792,353.769 859.91,354.587 863.877,358.21 864.744,357.233 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="866.417,358.551 865.535,359.369 869.503,362.992 870.369,362.015 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="872.11,363.33 871.228,364.147 875.195,367.771 876.062,366.794 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="877.338,368.149 876.456,368.966 880.425,372.589 881.29,371.613 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="883.478,373.498 882.596,374.316 886.565,377.939 887.432,376.962 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="889.419,378.786 888.538,379.604 892.504,383.227 893.371,382.251 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="895.654,384.226 894.774,385.044 898.74,388.665 899.607,387.69 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="901.688,389.348 900.808,390.165 904.774,393.788 905.642,392.812 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="907.667,394.522 906.785,395.339 910.754,398.962 911.619,397.986 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="913.314,399.662 912.432,400.482 916.399,404.103 917.266,403.128 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="919.354,405.085 918.472,405.904 922.438,409.526 923.306,408.551 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="925.79,410.841 924.908,411.659 928.875,415.282 929.742,414.305 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="932.209,416.438 931.327,417.256 935.296,420.879 936.161,419.904 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#002E4B" points="852.753,352.746 851.873,353.565 855.84,357.186 856.707,356.212 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="858.299,357.71 857.417,358.529 861.385,362.15 862.251,361.176 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="863.925,362.492 863.043,363.311 867.01,366.932 867.877,365.958 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="869.616,367.271 868.734,368.09 872.703,371.711 873.568,370.737 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="874.844,372.089 873.963,372.909 877.931,376.53 878.798,375.555 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="880.986,377.439 880.105,378.258 884.071,381.879 884.939,380.905 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="886.926,382.727 886.044,383.546 890.013,387.168 890.878,386.193 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="893.162,388.167 892.28,388.986 896.247,392.607 897.114,391.633 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="899.196,393.288 898.314,394.107 902.283,397.729 903.148,396.754 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="905.175,398.464 904.293,399.282 908.26,402.905 909.127,401.928 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="910.82,403.605 909.938,404.422 913.907,408.045 914.772,407.069 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="916.86,409.027 915.978,409.845 919.947,413.468 920.812,412.491 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="923.296,414.782 922.414,415.601 926.383,419.222 927.248,418.248 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="929.716,420.381 928.834,421.198 932.802,424.822 933.67,423.845 					" />
											</g>
											<polygon fill="#002E4B" points="849.87,355.794 848.988,356.611 852.956,360.233 853.822,359.258 				" />
											<polygon fill="#002E4B" points="855.415,360.758 854.533,361.575 858.502,365.197 859.367,364.222 				" />
											<polygon fill="#002E4B" points="861.04,365.538 860.16,366.358 864.126,369.979 864.994,369.004 				" />
											<polygon fill="#002E4B" points="866.733,370.317 865.851,371.136 869.818,374.758 870.685,373.783 				" />
											<polygon fill="#002E4B" points="871.961,375.136 871.079,375.955 875.048,379.576 875.913,378.602 				" />
											<polygon fill="#002E4B" points="878.101,380.485 877.221,381.305 881.188,384.926 882.055,383.951 				" />
											<g>
												<polygon fill="#002E4B" points="884.043,385.775 883.161,386.593 887.129,390.214 887.995,389.24 					" />
											</g>
											<polygon fill="#002E4B" points="890.279,391.213 889.397,392.033 893.363,395.654 894.231,394.679 				" />
											<polygon fill="#002E4B" points="896.313,396.334 895.431,397.154 899.397,400.775 900.265,399.801 				" />
											<polygon fill="#002E4B" points="902.29,401.511 901.41,402.328 905.377,405.951 906.244,404.975 				" />
											<g>
												<polygon fill="#002E4B" points="907.937,406.651 907.055,407.471 911.022,411.092 911.889,410.117 					" />
											</g>
											<polygon fill="#002E4B" points="913.977,412.074 913.095,412.891 917.064,416.514 917.929,415.538 				" />
											<polygon fill="#002E4B" points="920.413,417.83 919.531,418.647 923.5,422.269 924.365,421.294 				" />
											<g>
												<polygon fill="#002E4B" points="926.832,423.427 925.951,424.245 929.919,427.868 930.784,426.891 					" />
											</g>
											<polygon fill="#002E4B" points="846.65,358.899 845.768,359.716 849.737,363.339 850.604,362.363 				" />
										</g>
										<g>
											<polygon fill="#32A2D5" points="984.895,404.692 993.254,412.8 996.793,411.268 985.07,399.452 				" />
										</g>
										<g>
											<polygon fill="#00486C" points="985.022,401.576 888.661,325.717 880.225,318.212 864.494,324.937 965.968,406.218 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="850.372,340.267 952.818,424.619 968.824,406.208 872.314,331.065 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="878.008,309.801 838.101,323.374 843.208,327.809 844.695,327.22 840.42,323.608 
						877.708,311.215 888.559,320.933 889.887,320.552 					" />
											</g>
										</g>
										<g>
											<polygon fill="#9DC8E7" points="843.941,334.976 845.478,336.192 844.519,327.303 843.208,327.809 				" />
										</g>
										<g>
											<polygon fill="#00A0E9" points="844.092,325.911 857.758,321.053 856.326,320.078 842.2,324.934 				" />
										</g>
										<g>
											<polygon fill="#0A2644" points="976.963,414.8 979.48,413.797 979.39,410.628 976.875,411.634 				" />
										</g>
										<g>
											<polygon fill="#FFFFFF" points="948.381,450.858 951.291,454.155 950.049,446.696 947.42,444.357 				" />
										</g>
										<g>
											<polygon fill="#004669" points="953.118,423.204 995.928,411.492 985.022,401.576 940.005,412.634 				" />
										</g>
										<g>
											<g>
												<polygon fill="#00A0E9" points="952.818,424.619 998.843,411.694 985.94,398.911 984.836,399.52 996.722,411.275 
						953.118,423.204 940.005,412.634 938.677,413.015 					" />
											</g>
										</g>
									</g>
									<g>
										<g>
											<g>
												<g>
													<polygon fill="#9DC8E7" points="843.7,367.822 853.811,364.245 853.079,342.803 843.155,346.027 						" />
												</g>
												<g>
													<polygon fill="#1F3E60" points="852.009,342.556 834.333,331.178 755.452,355.142 777.058,372.931 846.218,350.144 
							842.708,345.648 						" />
													<path fill="#00A0E9" d="M754.476,354.848l80.016-24.312l18.569,12.267l-9.412,3.129l3.512,4.498l-70.295,23.159
							L754.476,354.848z M841.768,345.365l9.187-3.056l-16.778-10.489l-77.748,23.613l20.821,16.838l68.028-22.412L841.768,345.365z
							" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="777.947,396.819 847.673,371.572 846.944,350.486 776.673,373.66 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="810.329,369.85 818.925,367.278 818.805,361.211 810.21,364.08 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="793.882,375.445 804.659,371.689 804.572,365.897 793.797,369.654 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="841.177,359.956 842.926,359.308 842.962,354.604 841.214,355.12 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="836.621,361.389 838.369,360.708 838.406,356.07 836.655,356.586 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="841.804,371.1 843.552,370.452 843.588,365.746 841.839,366.262 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="837.248,372.531 838.996,371.85 839.033,367.212 837.284,367.728 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="777.307,380.98 788.114,377.207 787.843,371.803 776.87,375.284 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="792.706,391.496 807.527,386.142 807.506,380.402 792.674,385.306 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="788.15,386.803 812.631,378.715 809.153,375.386 785.962,383.19 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="848.441,360.525 852.609,359.05 851.684,358.68 848.074,359.968 						" />
												</g>
												<g>
													<polygon fill="#32A2D5" points="778.185,396.801 776.904,373.708 754.509,354.813 757.01,377.137 						" />
												</g>
											</g>
											<g>
												<polygon fill="#002E4B" points="848.957,366.037 852.096,364.861 851.944,359.506 848.808,360.618 					" />
											</g>
										</g>
										<g>
											<polygon fill="#005A84" points="845.306,349.816 804.781,345.141 757.156,360.076 777.262,372.112 				" />
										</g>
										<g>
											<polygon fill="#005A84" points="850.869,342.321 825,339.636 814.725,342.743 841.56,345.489 				" />
										</g>
										<g>
											<polygon fill="#00486C" points="756.475,355.45 757.156,360.076 804.781,345.141 845.235,349.806 841.835,345.464 
					814.59,342.73 824.451,339.833 850.94,342.322 834.177,331.817 				" />
										</g>
									</g>
									<g>
										<g>
											<g>
												<polygon fill="#32A2D5" points="863.94,493.962 862.71,473.432 845.867,454.478 847.401,476.388 					" />
											</g>
											<g>
												<polygon fill="#9DC8E7" points="863.94,493.962 954.491,462.851 954.564,442.644 862.596,472.788 					" />
											</g>
											<g>
												<polygon fill="#1F3E60" points="953.648,442.781 937.651,431.524 928.917,434.454 923.69,430.412 846.723,454.756 
						862.784,472.692 					" />
												<path fill="#00A0E9" d="M845.835,454.469l77.702-24.346l0.347-0.112l5.209,3.807l8.711-2.92l16.841,12.135l-91.689,30.182
						l-0.379,0.125L845.835,454.469z M937.497,432.155l-8.753,2.936l-5.217-3.813l-75.915,23.77l15.383,17.003l89.657-29.516
						L937.497,432.155z" />
											</g>
											<g>
												<polygon fill="#002E4B" points="910.078,465.625 923.025,461.042 923.005,455.555 910.056,460.135 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="890.311,472.245 903.259,467.664 903.237,462.175 890.289,466.757 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="939.985,455.585 942.087,454.952 942.065,449.365 939.963,449.997 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="934.508,457.295 936.612,456.662 936.59,451.075 934.486,451.71 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="940.781,465.716 942.885,465.081 942.864,459.496 940.76,460.129 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="935.601,467.46 937.703,466.828 937.681,461.243 935.579,461.875 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="870.391,479.056 883.38,474.59 882.941,469.573 869.816,473.841 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="910.117,475.646 923.065,471.064 923.043,465.576 910.095,470.158 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="890.351,482.579 903.298,477.999 903.277,472.509 890.329,477.091 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="870.432,489.39 883.419,484.924 882.98,479.907 870.388,484.172 					" />
											</g>
										</g>
										<g>
											<polygon fill="#005A84" points="952.649,442.552 933.075,442.23 924.069,443.186 914.686,442.301 853.356,462.62 
					862.87,472.074 				" />
										</g>
										<g>
											<polygon fill="#00486C" points="847.65,455.155 853.762,463.375 915.666,442.769 924.819,443.638 933.596,442.699 
					952.649,442.552 937.438,432.102 928.713,435.103 923.48,431.277 				" />
										</g>
									</g>
								</g>
							</g>
							<g id="s06" class="tooltip-item" title="s06">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="807.527,762.084 540.245,450.533 730.917,385.949 1011.309,683.65 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="807.527,762.084 540.245,450.533 730.917,385.949 
			1011.309,683.65 		" />
									<g>
										<g>
											<path fill="#005178" d="M894.445,637.275l-36.318-41.759c-9.501-10.924-65.206-65.405-80.316-69.945
					c-0.813-0.243-1.635-0.478-2.443-0.698c-15.626-4.213-31.624-3.987-45.049,0.637c-13.753,4.734-22.907,13.325-25.777,24.189
					c-2.98,11.274,1.021,23.967,11.258,35.739l78.466,87.725c9.508,10.926,23.064,19.444,38.176,23.983
					c0.805,0.241,1.627,0.476,2.437,0.694c15.631,4.214,31.632,3.987,45.057-0.635c13.751-4.736,22.905-13.327,25.773-24.188
					C908.69,661.745,904.688,649.051,894.445,637.275z M892.473,667.173c-1.675,8.639-8.755,15.514-19.935,19.367
					c-11.489,3.953-24.966,3.981-37.961,0.079c-11.771-3.535-22.277-10.114-29.603-18.535l-78.466-87.725
					c-7.147-8.217-10.249-17.033-8.738-24.817c1.683-8.635,8.764-15.514,19.944-19.367c11.481-3.955,24.968-3.981,37.961-0.079
					c11.763,3.533,64.427,56.082,71.743,64.497l36.318,41.759C890.882,650.572,893.991,659.389,892.473,667.173z" />
											<g>
												<g>

													<path opacity="0.3" fill="none" stroke="#B38D1B" stroke-width="0.2846" stroke-miterlimit="10"
													 enable-background="new    " d="
							M894.482,636.426l-36.879-41.303c-9.648-10.805-65.467-65.116-80.639-69.466c-0.816-0.233-1.642-0.459-2.454-0.667
							c-15.681-4.018-31.676-3.593-45.038,1.197c-13.688,4.906-22.727,13.609-25.448,24.506
							c-2.828,11.31,1.343,23.951,11.739,35.595l79.027,87.268c9.654,10.806,23.325,19.154,38.497,23.505
							c0.809,0.232,1.632,0.457,2.446,0.664c15.687,4.02,31.684,3.593,45.046-1.195c13.688-4.908,22.725-13.611,25.45-24.507
							C909.055,660.715,904.884,648.074,894.482,636.426z" />

													<path opacity="0.3" fill="none" stroke="#B38D1B" stroke-width="0.2846" stroke-miterlimit="10"
													 enable-background="new    " d="
							M894.146,642.224c-0.948-1.369-3.482-4.368-4.677-5.703l-35.417-39.662c-8.905-9.977-63.706-63.66-77.758-67.689
							c-0.762-0.219-1.544-0.43-2.317-0.628c-14.586-3.73-29.474-3.32-41.996,1.168c-12.833,4.601-21.188,12.723-23.529,22.874
							c-2.106,9.171,0.873,19.342,8.394,28.945c0.747,1.014,2.552,3.116,3.439,4.111l77.012,85.009
							c0.761,0.852,2.599,2.852,3.441,3.657c8.536,8.603,19.931,15.286,32.496,19.018c0.776,0.232,1.561,0.453,2.35,0.662
							c14.806,3.926,29.962,3.577,42.675-0.982c12.84-4.601,21.198-12.724,23.529-22.876
							C903.813,661.285,901.106,651.512,894.146,642.224z" />

													<path opacity="0.3" fill="none" stroke="#B38D1B" stroke-width="0.2846" stroke-miterlimit="10"
													 enable-background="new    " d="
							M888.984,641.929c-0.47-0.683-3.939-4.643-4.532-5.31l-33.947-38.022c-8.172-9.151-61.95-62.202-74.879-65.909
							c-0.724-0.206-1.449-0.403-2.178-0.588c-13.49-3.443-27.275-3.05-38.959,1.135c-11.975,4.296-19.649,11.839-21.6,21.245
							c-1.751,8.489,1.344,17.959,8.734,26.824c0.373,0.509,2.738,3.198,3.177,3.693l74.996,82.752
							c0.381,0.426,2.87,3.177,3.287,3.581c7.757,8.207,18.341,14.632,30.098,18.261c0.743,0.229,1.498,0.45,2.247,0.658
							c13.926,3.832,28.242,3.557,40.321-0.768c11.983-4.295,19.651-11.839,21.596-21.243
							C899.06,659.913,896.096,650.64,888.984,641.929z" />

													<path opacity="0.3" fill="none" stroke="#B38D1B" stroke-width="0.2846" stroke-miterlimit="10"
													 enable-background="new    " d="
							M883.838,641.639l-36.879-41.303c-7.431-8.323-60.183-60.742-71.993-64.128c-13.045-3.74-26.531-3.545-37.957,0.551
							c-11.127,3.992-18.113,10.957-19.68,19.613c-1.404,7.803,1.816,16.577,9.074,24.706l79.029,87.266
							c7.437,8.327,18.033,14.776,29.851,18.164c13.045,3.74,26.523,3.543,37.955-0.549c11.127-3.992,18.115-10.955,19.673-19.613
							C894.324,658.544,891.094,649.766,883.838,641.639z" />
												</g>
											</g>
										</g>
										<g>
											<polygon fill="#005178" points="558.223,457.989 599.452,497.479 723.274,453.539 679.249,413.411 				" />

											<polygon opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
											 enable-background="new    " points="
					575.148,458.864 677.052,423.441 708.003,450.388 603.554,487.004 				" />

											<path opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
											 enable-background="new    " d="
					M583.173,466.567c0,0,14.151-12.316,25.075-1.279c7.345,10.65-9.9,16.364-9.9,16.364" />

											<path opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
											 enable-background="new    " d="
					M700.391,443.761c0,0-14.175,10.616-23.296-0.149c-9.121-10.765,6.043-14.872,6.043-14.872" />

											<line opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
											 enable-background="new    " x1="632.573" y1="438.929" x2="661.521" y2="466.653" />

											<circle opacity="0.39" fill="none" stroke="#B38D1B" stroke-width="0.3821" stroke-miterlimit="10"
											 enable-background="new    " cx="645.624" cy="452.34" r="9.612" />
										</g>
									</g>
								</g>
							</g>
							<g id="s05" class="tooltip-item" title="区域2">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="595.156,841.887 343.487,516.972 540.245,450.533 807.527,762.084 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="595.156,841.887 343.487,516.972 540.245,450.533 
			807.527,762.084 		" />
									<g>
										<g>
											<g>
												<g>
													<g>
														<polygon fill="#9DC8E7" points="525.853,567.882 600.888,650.852 606.991,606.347 524.513,521.085 							" />
													</g>
												</g>
												<g>
													<polygon fill="#9DC8E7" points="600.606,650.651 653.284,630.613 659.018,591.35 606.711,606.146 						" />
												</g>
												<g>

													<rect x="613.799" y="577.088" transform="matrix(0.8894 0.4572 -0.4572 0.8894 338.7626 -215.1572)" fill="#365B8A"
													 width="0.326" height="30.643" />
												</g>
												<g>

													<rect x="629.031" y="583.25" transform="matrix(0.879 0.4769 -0.4769 0.879 356.1579 -236.4743)" fill="#365B8A"
													 width="29.829" height="0.326" />
												</g>
												<g>
													<g>
														<polygon fill="#7BBBD2" points="558.513,574.579 562.153,578.798 562.736,569.705 559.093,565.486 							" />
														<path fill="#0B2341" d="M558.429,574.608l0.584-9.126l0.011-0.2l3.796,4.394l-0.584,9.126L562.223,579L558.429,574.608z
								 M559.163,565.692l-0.566,8.861l3.489,4.043l0.566-8.861L559.163,565.692z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="538.851,553.505 542.494,557.724 542.855,548.126 539.213,543.906 							" />
														<path fill="#0B2341" d="M538.769,553.533l0.372-9.839l3.798,4.4l-0.37,9.837L538.769,553.533z M539.288,544.115l-0.355,9.361
								l3.487,4.038l0.353-9.359L539.288,544.115z" />
													</g>
												</g>
												<g>
													<g>
														<polygon fill="#7BBBD2" points="588.294,623.792 591.935,628.01 592.518,618.919 588.875,614.698 							" />
														<path fill="#0B2341" d="M588.808,614.496l-0.011,0.198l-0.584,9.126l0.022,0.024l3.772,4.372l0.013-0.198l0.584-9.126
								l-0.022-0.024L588.808,614.496z M591.868,627.806l-3.489-4.043l0.566-8.861l3.49,4.043L591.868,627.806z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="577.647,612.331 581.292,616.55 581.872,607.457 578.232,603.238 							" />
														<path fill="#0B2341" d="M578.164,603.034l-0.599,9.326l0.022,0.025l3.774,4.37l0.013-0.198l0.584-9.126l-0.022-0.025
								L578.164,603.034z M581.222,616.346l-3.491-4.043l0.568-8.861l3.49,4.043L581.222,616.346z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="567.837,601.77 571.482,605.99 572.062,596.897 568.42,592.678 							" />
														<path fill="#0B2341" d="M567.777,601.823l-0.022-0.025l0.584-9.126l0.013-0.198l3.772,4.37l0.022,0.025l-0.584,9.126
								l-0.011,0.2L567.777,601.823z M568.487,592.882l-0.566,8.861l3.491,4.043l0.566-8.861L568.487,592.882z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="557.905,591.076 561.546,595.297 562.129,586.203 558.484,581.985 							" />
														<path fill="#0B2341" d="M557.843,591.129l-0.022-0.025l0.584-9.126l0.011-0.2l3.774,4.37l0.022,0.024l-0.584,9.126
								l-0.013,0.198L557.843,591.129z M558.555,582.189l-0.566,8.861l3.489,4.043l0.566-8.861L558.555,582.189z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="547.672,580.154 551.316,584.375 551.789,574.873 548.147,570.655 							" />
														<path fill="#0B2341" d="M547.588,580.183l0.485-9.734l3.798,4.398l-0.483,9.736L547.588,580.183z M548.216,570.862
								l-0.462,9.264l3.489,4.041l0.46-9.266L548.216,570.862z" />
													</g>
													<g>
														<polygon fill="#7BBBD2" points="538.242,570.002 541.887,574.22 542.248,564.621 538.605,560.402 							" />
														<path fill="#0B2341" d="M538.16,570.03l0.372-9.839l3.798,4.4l-0.37,9.837L538.16,570.03z M538.679,560.612l-0.355,9.361
								l3.487,4.038l0.353-9.359L538.679,560.612z" />
													</g>
												</g>
												<g>
													<polygon fill="#005A84" points="631.02,575.654 582.604,527.028 589.527,524.963 575.08,510.845 525.422,521.422 
							606.951,606.085 658.059,590.98 638.915,573.572 						" />
													<path fill="#00A0E9" d="M631.195,575.017l-47.509-47.715l6.936-2.069l-15.361-15.009l-50.963,10.854l82.487,85.654
							l52.413-15.492l-20.127-18.298L631.195,575.017z M607.125,605.439l-80.577-83.673l48.353-10.3l13.533,13.226l-6.907,2.061
							l49.322,49.537l7.912-2.086l18.167,16.516L607.125,605.439z" />
												</g>
												<g>
													<polygon fill="#00486C" points="559.348,515.05 620.961,578.834 630.852,576.384 581.633,526.912 						" />
												</g>
												<g>
													<polygon fill="#004669" points="620.961,578.834 607.026,605.383 657.077,590.598 630.852,576.384 						" />
												</g>
											</g>
											<g>
												<g>
													<g>
														<path fill="#005A84" d="M597.554,535.412c-1.467-0.054-1.752,2.113-3.17,2.311l26.107,26.612
								c0.052-0.498,2.15-0.837,2.226-1.33C625.421,545.583,612.146,535.951,597.554,535.412z" />
													</g>
													<g id="XMLID_4_">
														<g>
															<g>
																<path fill="#32A2D5" d="M594.789,533.672l-0.412,4.222c19.888,0.237,26.367,9.567,28.403,16.677
										c1.005-2.817,0.937-7.089-4.115-12.309C610.128,533.441,594.789,533.672,594.789,533.672z" />
															</g>
															<g>
																<path fill="#32A2D5" d="M620.333,558.282l0.152,6.047c0,0,2.648-0.488,3.129-1.764c0,0,0.448-3.576-0.831-7.997
										C621.93,556.944,620.333,558.282,620.333,558.282z" />
															</g>
														</g>
													</g>
												</g>
												<g>
													<g>
														<polygon fill="#005A84" points="560.743,559.037 558.837,565.812 562.569,571.186 567.904,575.179 580.886,579.359 
								579.828,577.918 							" />
													</g>
													<g id="XMLID_3_">
														<g>
															<g>
																<path fill="#32A2D5" d="M559.541,564.054c-5.732,13.969,23.382,17.841,23.382,17.841l0.18-4.889
										c-4.029-0.057-10.112-1.917-10.112-1.917C563.829,571.516,560.406,567.519,559.541,564.054z" />
															</g>
															<g>
																<path fill="#005A84" d="M562.406,555.119c0,0-4.108,3.926-2.863,8.937c0.077-0.193,0.163-0.385,0.252-0.579
										c0,0,1.583-3.88,2.453-4.045L562.406,555.119z" />
															</g>
														</g>
													</g>
												</g>
											</g>
										</g>
										<g>
											<g>
												<g>
													<g>
														<polygon fill="#32A2D5" points="479.031,464.54 484.072,471.431 484.574,454.214 479.493,447.09 							" />
													</g>
													<g>
														<g>
															<polygon fill="#9DC8E7" points="477.051,474.257 483.778,479.401 483.364,463.076 477.334,454.158 								" />
														</g>
													</g>
													<g>
														<g>
															<polygon fill="#9DC8E7" points="484.112,464.065 498.843,460.432 499.18,451.267 484.49,453.807 								" />
														</g>
													</g>
													<g>
														<polygon fill="#00486C" points="484.819,453.565 498.092,450.872 492.092,445.547 480.489,447.5 							" />
														<path fill="#00A0E9" d="M479.493,447.09l12.773-2.152l7.06,6.266l-14.758,2.993L479.493,447.09z M491.916,446.154
								l-10.433,1.756l3.584,5.02l11.79-2.391L491.916,446.154z" />
													</g>
													<g>
														<polygon fill="#00A0E9" points="483.609,462.945 501.543,459.178 498.965,457.091 484.26,460.054 							" />
													</g>
													<g>
														<polygon fill="#00A0E9" points="477.334,454.158 484.107,464.179 484.258,460.081 479.435,453.587 							" />
													</g>
												</g>
												<g>
													<g>
														<polygon fill="#32A2D5" points="466.683,504.016 538.516,585.431 539.978,545.746 515.134,521.159 519.543,520.093 
								467.068,466.057 							" />
													</g>
													<g>
														<g>
															<polygon fill="#9DC8E7" points="538.516,585.431 550.785,581.868 552.184,542.756 539.978,545.746 								" />
														</g>
													</g>
													<g>
														<polygon fill="#005A84" points="574.411,517.488 535.797,526.176 551.52,542.64 540.281,545.627 515.992,521.391 
								519.935,520.298 468.195,466.356 513.655,456.897 							" />
														<path fill="#00A0E9" d="M514.893,521.1l3.968-1.099l-51.793-53.997l46.472-9.668l0.302-0.064l61.709,61.544l-38.643,8.694
								l15.694,16.434l-12.49,3.314L514.893,521.1z M534.687,525.841l38.585-8.682l-59.802-59.64l-44.152,9.185l51.689,53.888
								l-3.92,1.087l23.363,23.312l9.989-2.651L534.687,525.841z" />
													</g>
												</g>
												<g>
													<polygon fill="#005A84" points="523.557,538.377 527.531,543.125 527.258,540.463 523.284,535.717 						" />
													<path fill="#005A84" d="M523.495,538.428l-0.016-0.019l-0.302-2.949l4.144,4.95l0.016,0.019l0.302,2.951L523.495,538.428z
							 M523.393,535.972l0.244,2.371l3.787,4.523l-0.244-2.371L523.393,535.972z" />
												</g>
												<g>
													<polygon fill="#005A84" points="523.321,544.797 527.295,549.542 527.021,546.88 523.048,542.135 						" />
													<path fill="#005A84" d="M523.259,544.848l-0.016-0.019l-0.302-2.949l4.144,4.949l0.016,0.019l0.302,2.951L523.259,544.848z
							 M523.157,542.393l0.244,2.371l3.787,4.523l-0.244-2.371L523.157,542.393z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="523.085,551.214 527.058,555.961 526.785,553.3 522.812,548.554 						" />
													<path fill="#0B2341" d="M523.023,551.267l-0.016-0.019l-0.302-2.949l4.144,4.949l0.016,0.019l0.302,2.951L523.023,551.267z
							 M522.919,548.811l0.244,2.371l3.787,4.523l-0.244-2.371L522.919,548.811z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="522.847,557.633 526.822,562.381 526.547,559.719 522.576,554.973 						" />
													<path fill="#0B2341" d="M522.787,557.684l-0.016-0.019l-0.302-2.949l4.144,4.949l0.016,0.019l0.302,2.951L522.787,557.684z
							 M522.683,555.23l0.244,2.371l3.787,4.523l-0.244-2.371L522.683,555.23z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="522.611,564.053 526.584,568.798 526.311,566.136 522.338,561.391 						" />
													<path fill="#0B2341" d="M522.549,564.104l-0.016-0.019l-0.302-2.949l4.144,4.949l0.016,0.019l0.302,2.951L522.549,564.104z
							 M522.446,561.648l0.244,2.371l3.787,4.523l-0.244-2.371L522.446,561.648z" />
												</g>
												<g>
													<polygon fill="#005A84" points="518.016,530.629 520.511,533.358 520.404,530.265 517.907,527.534 						" />
													<path fill="#005A84" d="M517.956,530.684l-0.02-0.023l-0.118-3.348l2.646,2.895l0.022,0.023l0.118,3.344L517.956,530.684z
							 M517.996,527.753l0.102,2.844l2.326,2.543l-0.1-2.84L517.996,527.753z" />
												</g>
												<g>
													<polygon fill="#005A84" points="517.758,537.647 520.252,540.377 520.145,537.284 517.649,534.552 						" />
													<path fill="#005A84" d="M517.697,537.702l-0.02-0.023l-0.118-3.348l2.646,2.895l0.021,0.023l0.118,3.344L517.697,537.702z
							 M517.737,534.769l0.102,2.844l2.326,2.543l-0.1-2.84L517.737,534.769z" />
												</g>
												<g>
													<polygon fill="#005A84" points="517.501,544.664 519.995,547.391 519.886,544.3 517.39,541.567 						" />
													<path fill="#005A84" d="M517.438,544.719l-0.02-0.023l-0.118-3.348l2.646,2.895l0.022,0.023l0.118,3.344L517.438,544.719z
							 M517.478,541.788l0.102,2.844l2.326,2.543l-0.1-2.84L517.478,541.788z" />
												</g>
												<g>
													<polygon fill="#005A84" points="517.242,551.68 519.736,554.41 519.627,551.317 517.131,548.585 						" />
													<path fill="#005A84" d="M517.18,551.735l-0.02-0.021l-0.118-3.348l2.646,2.895l0.021,0.023l0.118,3.344L517.18,551.735z
							 M517.22,548.804l0.102,2.844l2.326,2.543l-0.1-2.84L517.22,548.804z" />
												</g>
												<g>
													<polygon fill="#005A84" points="531.864,545.39 534.255,548.211 534.259,545.118 531.868,542.297 						" />
													<path fill="#005A84" d="M531.802,545.443l-0.02-0.025l0.005-3.345l2.534,2.991l0.02,0.025l-0.005,3.345L531.802,545.443z
							 M531.949,542.519l-0.005,2.842l2.228,2.629l0.005-2.842L531.949,542.519z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="534.019,551.806 531.741,548.894 531.614,551.984 533.893,554.896 						" />
													<path fill="#0B2341" d="M531.55,552.035l-0.018-0.025l0.136-3.341l2.415,3.086l0.018,0.025l-0.136,3.341L531.55,552.035z
							 M531.813,549.119l-0.117,2.84l2.126,2.714l0.115-2.84L531.813,549.119z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="533.773,558.496 531.495,555.582 531.368,558.674 533.647,561.586 						" />
													<path fill="#0B2341" d="M531.302,558.725l-0.02-0.025l0.136-3.341l2.415,3.088l0.018,0.025l-0.134,3.341L531.302,558.725z
							 M531.567,555.807l-0.117,2.84l2.124,2.714l0.115-2.838L531.567,555.807z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="533.526,565.186 531.247,562.271 531.122,565.362 533.401,568.274 						" />
													<path fill="#0B2341" d="M531.056,565.411l-0.02-0.025l0.136-3.341l2.415,3.088l0.02,0.025l-0.134,3.34L531.056,565.411z
							 M531.321,562.495l-0.117,2.84l2.124,2.714l0.115-2.838L531.321,562.495z" />
												</g>
												<g>
													<polygon fill="#7BBBD2" points="533.278,571.873 531.001,568.961 530.874,572.052 533.153,574.964 						" />
													<path fill="#0B2341" d="M530.81,572.102l-0.02-0.025l0.136-3.341l2.415,3.088l0.02,0.025l-0.136,3.341L530.81,572.102z
							 M531.074,569.186l-0.117,2.84l2.124,2.714l0.115-2.838L531.074,569.186z" />
												</g>
												<g>
													<polygon fill="#FFFFFF" points="516.193,560.196 518.04,562.205 518.062,557.538 516.064,555.317 						" />
												</g>
												<g>
													<polygon fill="#005A84" points="507.072,525.036 510.327,528.963 511.027,520.652 507.774,516.729 						" />
													<path fill="#005A84" d="M507.012,525.087l-0.022-0.026l0.72-8.54l3.381,4.078l0.022,0.026l-0.72,8.542L507.012,525.087z
							 M507.837,516.933l-0.681,8.076l3.106,3.748l0.681-8.078L507.837,516.933z" />
												</g>
												<g>
													<polygon fill="#005A84" points="498.244,515.53 501.496,519.457 502.197,511.148 498.944,507.223 						" />
													<path fill="#005A84" d="M498.16,515.556l0.72-8.54l3.379,4.078l0.022,0.026l-0.72,8.542L498.16,515.556z M499.01,507.429
							l-0.679,8.076l3.104,3.748l0.681-8.078L499.01,507.429z" />
												</g>
												<g>
													<polygon fill="#005A84" points="489.256,505.856 492.511,509.783 493.21,501.472 489.955,497.549 						" />
													<path fill="#005A84" d="M489.193,505.907l-0.022-0.026l0.72-8.54l3.381,4.078l0.022,0.026l-0.72,8.542L489.193,505.907z
							 M490.019,497.755l-0.679,8.076l3.106,3.748l0.681-8.078L490.019,497.755z" />
												</g>
												<g>
													<polygon fill="#005A84" points="481.261,497.246 484.512,501.175 484.985,492.769 481.732,488.846 						" />
													<path fill="#005A84" d="M481.199,497.299l-0.022-0.025l0.486-8.642l3.385,4.084l0.02,0.025l-0.484,8.646L481.199,497.299z
							 M481.8,489.056l-0.459,8.164l3.1,3.742l0.459-8.166L481.8,489.056z" />
												</g>
												<g>
													<polygon fill="#005A84" points="473.158,488.526 476.412,492.453 476.711,483.712 473.458,479.787 						" />
													<path fill="#005A84" d="M473.096,488.577l-0.02-0.025l0.301-8.769l0.007-0.215l3.389,4.089l0.02,0.025l-0.308,8.988
							L473.096,488.577z M473.532,480.004l-0.289,8.493l3.096,3.738l0.291-8.497L473.532,480.004z" />
												</g>
												<g>
													<polygon fill="#005A84" points="506.514,540.223 509.767,544.15 510.469,535.841 507.214,531.916 						" />
													<path fill="#005A84" d="M506.452,540.276l-0.022-0.026l0.72-8.54l3.381,4.078l0.022,0.026l-0.72,8.542L506.452,540.276z
							 M507.279,532.12l-0.681,8.076l3.106,3.748l0.681-8.078L507.279,532.12z" />
												</g>
												<g>
													<polygon fill="#005A84" points="497.684,530.717 500.937,534.646 501.637,526.335 498.384,522.41 						" />
													<path fill="#005A84" d="M497.6,530.744l0.72-8.54l3.379,4.078l0.022,0.026l-0.72,8.542L497.6,530.744z M498.449,522.616
							l-0.679,8.076l3.104,3.748l0.679-8.078L498.449,522.616z" />
												</g>
												<g>
													<polygon fill="#005A84" points="488.696,521.043 491.951,524.972 492.65,516.661 489.395,512.736 						" />
													<path fill="#005A84" d="M488.633,521.096l-0.022-0.026l0.72-8.54l3.381,4.078l0.022,0.026l-0.72,8.542L488.633,521.096z
							 M489.461,512.942l-0.679,8.076l3.106,3.748l0.681-8.078L489.461,512.942z" />
												</g>
												<g>
													<polygon fill="#005A84" points="480.701,512.433 483.952,516.362 484.425,507.958 481.172,504.033 						" />
													<path fill="#005A84" d="M480.639,512.486l-0.022-0.024l0.486-8.642l3.385,4.084l0.02,0.025l-0.484,8.646L480.639,512.486z
							 M481.242,504.243l-0.459,8.164l3.1,3.742l0.459-8.166L481.242,504.243z" />
												</g>
												<g>
													<polygon fill="#005A84" points="472.598,503.713 475.851,507.642 476.151,498.899 472.898,494.974 						" />
													<path fill="#005A84" d="M472.536,503.764l-0.02-0.025l0.301-8.769l0.007-0.213l3.389,4.089l0.02,0.025l-0.308,8.988
							L472.536,503.764z M472.971,495.194l-0.289,8.493l3.096,3.738l0.291-8.497L472.971,495.194z" />
												</g>
												<g>
													<polygon fill="#00486C" points="554.092,521.584 503.635,468.368 513.489,457.626 573.164,517.107 						" />
												</g>
												<g>
													<polyline fill="#004669" points="469.355,466.72 513.527,457.675 503.67,468.415 						" />
												</g>
											</g>
											<g>
												<polygon fill="#004669" points="496.809,450.533 491.506,447.337 481.254,447.846 491.885,446.128 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#32A2D5" points="345.295,546.735 451.895,610.991 454.609,583.59 345.587,519.194 					" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="451.895,610.991 484.566,588.569 487.673,564.16 454.609,583.59 					" />
											</g>
											<g>
												<polygon fill="#365B8A" points="442.15,605.117 451.895,610.991 454.614,583.471 444.997,577.913 					" />
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="429.318,593.859 436.027,598.181 436.865,587.731 430.156,583.412 						" />
													<path fill="#005A84" d="M429.273,593.925l-0.041-0.026l0.843-10.495l0.011-0.136l6.826,4.394l0.041,0.026l-0.843,10.497
							l-0.011,0.136L429.273,593.925z M430.228,583.556l-0.824,10.263l6.554,4.221l0.824-10.265L430.228,583.556z" />
												</g>
												<g>
													<polygon fill="#005A84" points="414.487,584.782 421.196,589.103 421.996,578.613 415.287,574.293 						" />
													<path fill="#005A84" d="M414.401,584.824l0.803-10.537l0.011-0.135l6.826,4.394l0.041,0.026l-0.805,10.539l-0.011,0.136
							L414.401,584.824z M415.357,574.436l-0.787,10.304l6.553,4.219l0.787-10.306L415.357,574.436z" />
												</g>
												<g>
													<polygon fill="#005A84" points="398.12,574.891 404.831,579.212 405.688,568.784 398.979,564.464 						" />
													<path fill="#005A84" d="M398.077,574.959l-0.041-0.026l0.873-10.609l6.824,4.394l0.041,0.026l-0.873,10.611L398.077,574.959z
							 M399.049,564.607l-0.844,10.242l6.555,4.221l0.844-10.244L399.049,564.607z" />
												</g>
												<g>
													<polygon fill="#005A84" points="384.213,566.768 390.922,571.088 391.722,560.597 385.012,556.277 						" />
													<path fill="#005A84" d="M384.127,566.808l0.802-10.539l0.011-0.136l6.826,4.396l0.041,0.026l-0.801,10.541l-0.011,0.136
							L384.127,566.808z M385.083,556.42l-0.785,10.306l6.555,4.219l0.785-10.306L385.083,556.42z" />
												</g>
												<g>
													<polygon fill="#005A84" points="369.115,557.111 375.826,561.43 376.711,551.03 370,546.711 						" />
													<path fill="#005A84" d="M369.07,557.177l-0.041-0.026l0.89-10.446l0.013-0.135l6.826,4.394l0.041,0.026l-0.89,10.448
							l-0.013,0.135L369.07,557.177z M370.07,546.853l-0.869,10.214l6.555,4.221l0.869-10.216L370.07,546.853z" />
												</g>
												<g>
													<polygon fill="#005A84" points="353.522,547.845 360.231,552.167 361.155,541.804 354.442,537.485 						" />
													<path fill="#005A84" d="M353.478,547.913l-0.041-0.026l0.925-10.409l0.013-0.135l6.826,4.394l0.041,0.026l-0.927,10.411
							l-0.013,0.135L353.478,547.913z M354.512,537.627l-0.905,10.176l6.555,4.221l0.905-10.176L354.512,537.627z" />
												</g>
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="432.054,575.256 438.82,579.488 439.522,569.028 432.755,564.796 						" />
													<path fill="#005A84" d="M432.011,575.324l-0.041-0.026l0.704-10.508l0.009-0.136l6.883,4.304l0.041,0.026l-0.706,10.508
							l-0.009,0.136L432.011,575.324z M432.827,564.936l-0.688,10.276l6.609,4.135l0.69-10.276L432.827,564.936z" />
												</g>
												<g>
													<polygon fill="#005A84" points="417.103,566.371 423.872,570.605 424.534,560.105 417.768,555.873 						" />
													<path fill="#005A84" d="M417.061,566.441l-0.041-0.026l0.665-10.548l0.009-0.136l6.883,4.304l0.041,0.026l-0.665,10.547
							l-0.009,0.136L417.061,566.441z M417.84,556.016l-0.651,10.313l6.609,4.133l0.651-10.315L417.84,556.016z" />
												</g>
												<g>
													<polygon fill="#005A84" points="400.61,556.697 407.377,560.929 408.098,550.492 401.331,546.262 						" />
													<path fill="#005A84" d="M400.566,556.767l-0.041-0.026l0.724-10.483l0.009-0.136l6.883,4.303l0.041,0.026l-0.733,10.62
							L400.566,556.767z M401.401,546.402l-0.707,10.253l6.609,4.133l0.709-10.253L401.401,546.402z" />
												</g>
												<g>
													<polygon fill="#005A84" points="386.598,548.759 393.363,552.991 394.025,542.489 387.256,538.26 						" />
													<path fill="#005A84" d="M386.553,548.829l-0.041-0.026l0.661-10.549l0.009-0.136l6.885,4.304l0.041,0.026l-0.665,10.549
							l-0.009,0.136L386.553,548.829z M387.328,538.402l-0.647,10.315l6.609,4.131l0.65-10.317L387.328,538.402z" />
												</g>
												<g>
													<polygon fill="#005A84" points="371.373,539.302 378.138,543.534 378.887,533.125 372.122,528.893 						" />
													<path fill="#005A84" d="M371.329,539.372l-0.041-0.026l0.751-10.457l0.009-0.135l6.881,4.305l0.041,0.026l-0.751,10.457
							l-0.009,0.135L371.329,539.372z M372.192,529.033l-0.735,10.225l6.609,4.133l0.735-10.225L372.192,529.033z" />
												</g>
												<g>
													<polygon fill="#005A84" points="355.658,530.244 362.425,534.476 363.21,524.104 356.444,519.872 						" />
													<path fill="#005A84" d="M355.617,530.312l-0.041-0.026l0.788-10.421l0.009-0.135l6.881,4.305l0.041,0.026l-0.788,10.421
							l-0.009,0.135L355.617,530.312z M356.516,520.013l-0.772,10.187l6.609,4.135l0.772-10.189L356.516,520.013z" />
												</g>
											</g>
											<g>
												<polygon fill="#005A84" points="489.228,562.75 397.107,510.317 398.702,508.653 374.369,493.196 337.398,514.054 
						454.577,582.925 					" />
												<path fill="#00A0E9" d="M336.254,514.046l38.135-21.512l25.209,16.013l-1.57,1.639l91.482,52.069l0.862,0.49l-35.795,20.844
						L336.254,514.046z M396.823,510.814l-0.637-0.362l1.619-1.69l-23.454-14.9l-35.81,20.203l116.037,68.198l33.504-19.509
						L396.823,510.814z" />
											</g>
											<g>
												<polygon fill="#FFFFFF" points="467.626,600.196 475.263,594.937 476.912,576.045 469.286,581.032 					" />
											</g>
											<g>
												<polyline fill="#00486C" points="487.991,562.671 461.643,559.932 367.448,506.039 338.529,514.099 374.238,493.74 
						397.814,508.707 396.183,510.446 					" />
											</g>
											<g>
												<polygon fill="#004669" points="488.08,562.728 461.706,560.125 454.75,582.186 					" />
											</g>
											<g>
												<polygon fill="#00486C" points="454.906,582.233 461.8,559.979 461.489,559.883 454.596,582.137 					" />
											</g>
											<g>
												<path fill="none" stroke="#365B8A" stroke-width="0.4333" stroke-miterlimit="10" d="M367.448,506.039" />
											</g>
											<g>
												<path fill="none" stroke="#365B8A" stroke-width="0.4333" stroke-miterlimit="10" d="M374.238,493.74" />
											</g>
										</g>
										<g>
											<g>
												<g>
													<polygon fill="#32A2D5" points="617.993,668.525 627.067,665.978 627.67,638.067 619.034,640.248 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="571.245,689.746 625.341,673.786 625.942,645.874 571.313,660.191 						" />
												</g>
												<g>
													<polygon fill="#365B8A" points="487.957,588.883 571.245,689.746 571.292,660.765 492.098,561.661 						" />
												</g>
												<g>
													<polygon fill="#005A84" points="541.156,549.311 534.27,551.024 529.302,546.714 496.314,557.076 499.084,560.059 
							492.098,561.661 571.416,660.195 624.934,645.754 619.096,640.066 626.668,637.754 						" />
													<path fill="#00A0E9" d="M541.314,548.682l-6.895,1.717l-4.97-4.313l-34.208,10.674l2.751,2.962l-7.026,1.612l80.277,99.497
							l54.804-14.789l-5.863-5.714l7.61-2.322L541.314,548.682z M623.817,645.463l-52.231,14.093l-78.355-97.57l6.945-1.593
							l-2.79-3.002l31.768-10.05l4.966,4.31l6.878-1.711l84.542,87.563l-7.536,2.3L623.817,645.463z" />
												</g>
											</g>
											<g>
												<g>
													<polygon fill="#004669" points="528.895,580.315 562.753,621.858 584.628,613.489 549.215,573.765 						" />
												</g>
												<g>
													<polygon fill="#003351" points="546.218,574.86 581.398,614.83 581.61,614.581 546.428,574.61 						" />
												</g>
												<g>
													<polygon fill="#003351" points="577.457,616.315 577.671,616.069 543.099,575.649 542.885,575.895 						" />
												</g>
												<g>
													<polygon fill="#003351" points="573.458,617.67 539.576,576.762 539.359,577.003 573.238,617.912 						" />
												</g>
												<g>
													<polygon fill="#003351" points="568.572,619.566 535.165,578.202 534.941,578.441 568.348,619.804 						" />
												</g>
												<g>
													<polygon fill="#003351" points="564.442,621.286 564.667,621.049 531.397,579.428 531.172,579.666 						" />
												</g>
												<g>

													<rect x="569.821" y="603.042" transform="matrix(0.3697 0.9291 -0.9291 0.3697 930.2329 -142.2738)" fill="#003351"
													 width="0.326" height="22.965" />
												</g>
												<g>
													<polygon fill="#003351" points="576.724,607.063 576.605,606.76 555.416,604.739 555.537,605.042 						" />
												</g>
												<g>
													<polygon fill="#003351" points="572.321,603.32 572.202,603.017 551.348,600.863 551.467,601.166 						" />
												</g>
												<g>
													<polygon fill="#003351" points="547.493,597.667 567.966,599.676 567.842,599.373 547.37,597.365 						" />
												</g>
												<g>
													<polygon fill="#003351" points="564.11,596.472 563.989,596.17 543.575,593.861 543.696,594.164 						" />
												</g>
												<g>
													<polygon fill="#003351" points="560.048,593.083 559.929,592.779 539.776,590.269 539.895,590.574 						" />
												</g>
												<g>

													<rect x="535.65" y="583.269" transform="matrix(0.9288 -0.3705 0.3705 0.9288 -177.2937 243.8987)" fill="#003351"
													 width="21.107" height="0.326" />
												</g>
												<g>
													<polygon fill="#003351" points="532.465,583.659 551.976,576.176 551.859,575.872 532.348,583.356 						" />
												</g>
											</g>
											<g>
												<polygon fill="#7BBBD2" points="614.819,656.912 619.7,655.628 619.671,650.631 614.788,651.917 					" />
												<path fill="#005A84" d="M614.706,651.856l4.944-1.301l0.101-0.027l0.031,5.163l-4.942,1.299l-0.101,0.027L614.706,651.856z
						 M619.59,650.739l-4.719,1.242l0.03,4.825l4.717-1.239L619.59,650.739z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="606.951,659.275 611.832,657.991 611.802,652.995 606.921,654.279 					" />
												<path fill="#005A84" d="M606.839,654.218l4.94-1.299l0.101-0.027l0.033,5.163l-4.942,1.299l-0.101,0.027L606.839,654.218z
						 M611.72,653.102l-4.717,1.24l0.028,4.825l4.717-1.24L611.72,653.102z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="598.487,661.925 603.368,660.643 603.339,655.647 598.458,656.931 					" />
												<path fill="#005A84" d="M598.374,656.869l4.944-1.301l0.101-0.027l0.031,5.165l-4.942,1.299l-0.101,0.027L598.374,656.869z
						 M603.258,655.752l-4.719,1.241l0.028,4.825l4.719-1.239L603.258,655.752z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="589.441,664.519 594.322,663.235 594.291,658.239 589.41,659.525 					" />
												<path fill="#005A84" d="M589.328,659.462l4.942-1.301l0.101-0.027l0.033,5.163l-4.944,1.299l-0.101,0.027L589.328,659.462z
						 M594.21,658.346l-4.717,1.242l0.028,4.825l4.719-1.24L594.21,658.346z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="580.361,666.78 585.243,665.496 585.213,660.502 580.332,661.786 					" />
												<path fill="#005A84" d="M580.248,661.724l4.944-1.301l0.101-0.027l0.031,5.163l-4.944,1.299l-0.101,0.027L580.248,661.724z
						 M585.133,660.609l-4.719,1.24l0.028,4.825l4.719-1.24L585.133,660.609z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="614.52,665.015 619.401,663.731 619.372,658.735 614.489,660.021 					" />
												<path fill="#005A84" d="M614.407,659.957l4.944-1.301l0.101-0.027l0.031,5.163l-4.942,1.299l-0.101,0.027L614.407,659.957z
						 M619.292,658.842l-4.719,1.24l0.03,4.827l4.717-1.24L619.292,658.842z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="606.652,667.379 611.533,666.095 611.503,661.099 606.622,662.383 					" />
												<path fill="#005A84" d="M606.54,662.321l4.94-1.299l0.101-0.027l0.033,5.163l-4.942,1.299l-0.101,0.027L606.54,662.321z
						 M611.421,661.204l-4.717,1.24l0.028,4.827l4.717-1.24L611.421,661.204z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="598.188,670.03 603.069,668.746 603.04,663.75 598.159,665.034 					" />
												<path fill="#005A84" d="M598.075,664.973l4.944-1.299l0.101-0.027l0.031,5.163l-4.944,1.299l-0.101,0.027L598.075,664.973z
						 M602.959,663.858l-4.719,1.24l0.028,4.825l4.719-1.239L602.959,663.858z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="589.142,672.623 594.023,671.341 593.992,666.343 589.111,667.629 					" />
												<path fill="#005A84" d="M589.031,667.565l4.942-1.301l0.101-0.027l0.033,5.165l-4.942,1.299l-0.101,0.027L589.031,667.565z
						 M593.911,666.45l-4.717,1.242l0.028,4.825l4.719-1.239L593.911,666.45z" />
											</g>
											<g>
												<polygon fill="#00486C" points="580.063,674.886 584.944,673.602 584.914,668.606 580.033,669.89 					" />
												<path fill="#005A84" d="M579.951,669.828l4.944-1.301l0.101-0.027l0.031,5.163l-4.944,1.299l-0.101,0.027L579.951,669.828z
						 M584.834,668.711l-4.719,1.24l0.028,4.825l4.719-1.24L584.834,668.711z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="614.241,672.627 619.12,671.345 619.09,666.347 614.209,667.633 					" />
												<path fill="#005A84" d="M614.125,667.571l4.944-1.301l0.101-0.027l0.031,5.165l-4.942,1.299l-0.101,0.027L614.125,667.571z
						 M619.01,666.454l-4.719,1.242l0.03,4.825l4.717-1.239L619.01,666.454z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="606.372,674.99 611.253,673.706 611.222,668.71 606.341,669.996 					" />
												<path fill="#005A84" d="M606.259,669.933l4.94-1.301l0.101-0.027l0.033,5.163l-4.942,1.299l-0.101,0.027L606.259,669.933z
						 M611.141,668.818l-4.717,1.24l0.028,4.825l4.717-1.24L611.141,668.818z" />
											</g>
											<g>
												<polygon fill="#7BBBD2" points="597.906,677.642 602.79,676.358 602.76,671.362 597.877,672.648 					" />
												<path fill="#005A84" d="M597.795,672.586l4.944-1.301l0.101-0.027l0.031,5.163l-4.944,1.299l-0.101,0.027L597.795,672.586z
						 M602.678,671.469l-4.719,1.242l0.028,4.825l4.719-1.241L602.678,671.469z" />
											</g>
											<g>
												<polygon fill="#00486C" points="588.86,680.236 593.744,678.952 593.712,673.956 588.831,675.24 					" />
												<path fill="#005A84" d="M588.749,675.179l4.942-1.299l0.101-0.027l0.033,5.163l-4.944,1.299l-0.101,0.027L588.749,675.179z
						 M593.632,674.063l-4.717,1.24l0.028,4.825l4.719-1.241L593.632,674.063z" />
											</g>
											<g>
												<polygon fill="#00486C" points="579.781,682.496 584.664,681.213 584.635,676.217 579.753,677.501 					" />
												<path fill="#005A84" d="M579.67,677.44l4.944-1.301l0.101-0.027l0.031,5.165l-4.942,1.299l-0.101,0.027L579.67,677.44z
						 M584.554,676.323l-4.719,1.242l0.028,4.825l4.719-1.239L584.554,676.323z" />
											</g>
											<g>
												<polygon fill="#005A84" points="619.618,655.566 614.899,656.804 614.87,651.979 619.589,650.739 					" />
												<polygon fill="#005A84" points="611.75,657.928 607.033,659.168 607.003,654.343 611.722,653.102 					" />
												<polygon fill="#005A84" points="603.286,660.579 598.567,661.82 598.539,656.995 603.258,655.752 					" />
												<polygon fill="#005A84" points="594.24,663.174 589.523,664.412 589.491,659.587 594.212,658.346 					" />
												<polygon fill="#005A84" points="585.133,660.605 585.161,665.431 580.444,666.673 580.414,661.846 					" />
												<polygon fill="#005A84" points="619.317,663.717 614.6,664.958 614.57,660.133 619.289,658.89 					" />
												<polygon fill="#005A84" points="611.45,666.081 606.731,667.321 606.701,662.494 611.42,661.254 					" />
												<polygon fill="#005A84" points="602.985,668.733 598.268,669.971 598.238,665.146 602.957,663.906 					" />
												<polygon fill="#005A84" points="593.94,671.325 589.221,672.566 589.192,667.74 593.912,666.498 					" />
												<polygon fill="#005A84" points="584.859,673.218 581.297,669.636 584.834,668.705 					" />
												<polygon fill="#005A84" points="619.037,671.329 614.318,672.57 614.29,667.744 619.009,666.504 					" />
												<polygon fill="#005A84" points="611.168,673.693 606.451,674.933 606.421,670.108 611.14,668.867 					" />
												<polygon fill="#005A84" points="602.705,676.345 597.988,677.585 597.958,672.758 602.677,671.517 					" />
												<polygon fill="#005A84" points="593.662,678.891 591.155,679.541 588.925,677.301 588.913,675.304 593.634,674.063 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#32A2D5" points="625.147,667.672 691.99,739.182 694.545,702.654 627.18,630.712 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="654.255,593.375 615.571,603.851 610.38,598.86 596.221,602.755 623.52,631.841 
						627.401,630.738 694.597,702.016 747.003,686.542 					" />
												<path fill="#00A0E9" d="M747.406,686.142l-92.979-93.402l-0.321,0.087l-38.373,10.391l-5.191-4.992l-15.386,4.231l28.185,30.03
						l3.883-1.105l67.199,71.284l53.66-15.847L747.406,686.142z M694.773,701.372l-67.195-71.276l-3.879,1.103l-26.413-28.145
						l12.934-3.557l5.189,4.988l38.677-10.472l91.836,92.254L694.773,701.372z" />
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="684.63,707.534 688.644,712.182 689.283,702.167 685.272,697.518 						" />
													<path fill="#005A84" d="M684.568,707.587l-0.022-0.025l0.644-10.048l0.013-0.198l4.141,4.798l0.022,0.024l-0.64,10.048
							l-0.011,0.198L684.568,707.587z M685.339,697.722l-0.626,9.783l3.861,4.471l0.625-9.783L685.339,697.722z" />
												</g>
												<g>
													<polygon fill="#005A84" points="672.907,694.91 676.919,699.557 677.56,689.544 673.546,684.895 						" />
													<path fill="#005A84" d="M672.847,694.963l-0.022-0.025l0.64-10.048l0.011-0.198l4.143,4.798l0.022,0.025l-0.644,10.048
							l-0.013,0.198L672.847,694.963z M673.615,685.099l-0.625,9.783l3.859,4.472l0.626-9.783L673.615,685.099z" />
												</g>
												<g>
													<polygon fill="#005A84" points="662.101,683.279 666.113,687.927 666.754,677.912 662.74,673.263 						" />
													<path fill="#005A84" d="M662.041,683.332l-0.022-0.025l0.642-10.048l0.013-0.198l4.141,4.798l0.022,0.024l-0.642,10.048
							l-0.013,0.198L662.041,683.332z M662.807,673.467l-0.625,9.783l3.861,4.472l0.626-9.783L662.807,673.467z" />
												</g>
												<g>
													<polygon fill="#005A84" points="651.16,671.502 655.172,676.148 655.813,666.133 651.799,661.486 						" />
													<path fill="#005A84" d="M651.099,671.555l-0.022-0.025l0.64-10.048l0.011-0.2l4.143,4.798l0.022,0.024l-0.644,10.048
							l-0.013,0.198L651.099,671.555z M651.868,661.69l-0.625,9.783l3.859,4.472l0.626-9.783L651.868,661.69z" />
												</g>
												<g>
													<polygon fill="#005A84" points="639.891,659.47 643.905,664.119 644.544,654.103 640.532,649.455 						" />
													<path fill="#005A84" d="M639.829,659.523l-0.022-0.024l0.644-10.048l0.013-0.198l4.141,4.798l0.022,0.025l-0.64,10.048
							l-0.011,0.198L639.829,659.523z M640.6,649.659l-0.626,9.783l3.861,4.471l0.625-9.783L640.6,649.659z" />
												</g>
												<g>
													<polygon fill="#005A84" points="629.505,648.288 633.518,652.937 634.158,642.922 630.146,638.273 						" />
													<path fill="#005A84" d="M630.078,638.071l-0.013,0.198l-0.644,10.048l0.022,0.025l4.147,4.798l0.011-0.198l0.64-10.048
							l-0.022-0.025L630.078,638.071z M633.447,652.732l-3.861-4.471l0.626-9.783l3.859,4.471L633.447,652.732z" />
												</g>
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="682.299,725.35 686.311,729.999 686.95,719.983 682.94,715.336 						" />
													<path fill="#005A84" d="M682.235,725.403l-0.022-0.025l0.644-10.048l0.013-0.198l4.141,4.798l0.022,0.025l-0.64,10.048
							l-0.011,0.198L682.235,725.403z M683.008,715.54l-0.626,9.783l3.861,4.471l0.625-9.783L683.008,715.54z" />
												</g>
												<g>
													<polygon fill="#005A84" points="670.574,712.727 674.586,717.374 675.227,707.36 671.213,702.713 						" />
													<path fill="#005A84" d="M670.513,712.78l-0.022-0.025l0.64-10.048l0.011-0.198l4.143,4.798l0.022,0.025l-0.644,10.048
							l-0.013,0.198L670.513,712.78z M671.282,702.917l-0.625,9.783l3.859,4.471l0.626-9.783L671.282,702.917z" />
												</g>
												<g>
													<polygon fill="#005A84" points="659.768,701.097 663.78,705.744 664.421,695.73 660.409,691.081 						" />
													<path fill="#005A84" d="M659.706,701.15l-0.022-0.024l0.642-10.048l0.013-0.198l4.141,4.798l0.022,0.025l-0.642,10.048
							l-0.013,0.198L659.706,701.15z M660.478,691.285l-0.626,9.783l3.861,4.471l0.625-9.783L660.478,691.285z" />
												</g>
												<g>
													<polygon fill="#005A84" points="648.829,689.318 652.838,693.967 653.479,683.951 649.466,679.302 						" />
													<path fill="#005A84" d="M648.766,689.373l-0.022-0.025l0.64-10.048l0.011-0.2l4.143,4.798l0.022,0.024l-0.644,10.048
							l-0.013,0.198L648.766,689.373z M649.537,679.506l-0.625,9.783l3.859,4.471l0.626-9.783L649.537,679.506z" />
												</g>
												<g>
													<polygon fill="#005A84" points="637.562,677.19 641.576,681.839 642.215,671.824 638.203,667.177 						" />
													<path fill="#005A84" d="M637.5,677.243l-0.022-0.025l0.644-10.048l0.013-0.198l4.141,4.798l0.022,0.025l-0.64,10.048
							l-0.011,0.198L637.5,677.243z M638.27,667.381l-0.626,9.783l3.861,4.472l0.625-9.783L638.27,667.381z" />
												</g>
												<g>
													<polygon fill="#005A84" points="627.175,666.009 631.189,670.658 631.828,660.642 627.816,655.995 						" />
													<path fill="#005A84" d="M627.113,666.062l-0.022-0.025l0.644-10.048l0.013-0.198l4.141,4.798l0.022,0.024l-0.64,10.048
							l-0.011,0.2L627.113,666.062z M627.884,656.199l-0.626,9.783l3.861,4.471l0.625-9.783L627.884,656.199z" />
												</g>
											</g>
											<g>
												<polygon fill="#00486C" points="654.112,594.074 635.307,599.044 705.285,670.551 745.904,686.262 					" />
											</g>
											<g>
												<g>
													<polygon fill="#9DC8E7" points="691.928,739.079 743.48,725.594 748.032,686.69 694.483,702.552 						" />
												</g>
												<g>
													<g>
														<g>
															<polygon fill="#32A2D5" points="717.019,697.46 724.834,704.997 725.111,697.454 717.425,689.997 								" />
														</g>
														<g>
															<polygon fill="#9DC8E7" points="724.598,709.693 741.118,693.375 725.115,697.366 								" />
														</g>
														<g>
															<polygon fill="#32A2D5" points="710.155,737.511 720.216,748.618 725.426,708.462 714.597,698.007 								" />
														</g>
														<g>
															<polygon fill="#00486C" points="725.149,696.737 739.965,693.074 730.838,684.473 716.448,688.443 								" />
															<path fill="#00A0E9" d="M715.326,688.162l15.67-4.325l10.122,9.54l-16.136,3.989L715.326,688.162z M730.68,685.108
									l-13.107,3.618l7.742,7.383l13.495-3.336L730.68,685.108z" />
														</g>
														<g>
															<polygon fill="#00A0E9" points="724.724,708.458 740.57,704.333 740.693,701.017 724.841,704.803 								" />
														</g>
														<g>
															<polygon fill="#00A0E9" points="714.597,698.007 724.727,708.524 724.852,704.856 717.014,697.297 								" />
														</g>
														<g>
															<polygon fill="#9DC8E7" points="720.177,748.688 735.548,743.786 740.57,704.333 724.721,708.531 								" />
														</g>
													</g>
													<g>
														<polygon fill="#002E4B" points="727.743,746.35 729.601,745.705 734.091,706.021 732.258,706.55 							" />
													</g>
													<g>
														<polygon fill="#002E4B" points="713.745,741.569 715.18,743.155 719.623,703.589 718.096,702.14 							" />
													</g>
												</g>
												<g>
													<polygon fill="#004669" points="694.689,701.968 705.378,670.503 705.069,670.396 694.381,701.861 						" />
												</g>
												<g>
													<polygon fill="#005A84" points="738.741,692.732 730.278,687.303 717.483,688.747 730.67,685.106 						" />
												</g>
											</g>
										</g>
										<g>
											<g>
												<g>
													<g>
														<g>
															<polygon fill="#005A84" points="490.017,559.844 443.043,579.038 454.321,592.357 464.856,590.934 525.08,666.782 
									517.233,667.737 525.441,677.972 574.83,662.234 								" />
															<path fill="#00A0E9" d="M575.946,662.686l-50.753,15.889l-9.045-11.281l7.839-0.953l-59.37-74.798l-10.527,1.42
									l-12.14-14.333l48.3-19.4L575.946,662.686z M454.555,591.75l10.543-1.422l61.073,76.898l-7.85,0.953l7.369,9.19
									l48.024-15.588l-83.928-101.323l-45.649,18.987L454.555,591.75z" />
														</g>
														<g>
															<polygon fill="#9DC8E7" points="521.595,704.744 571.213,688.406 575.957,662.381 526.026,678.102 								" />
														</g>
														<polygon fill="#32A2D5" points="441.949,578.632 439.428,603.479 449.85,614.997 460.051,607.163 509.454,676.859 
								507.404,689.66 521.595,704.744 526.026,678.102 516.555,667.275 523.985,666.342 465.098,590.327 453.627,593.251 							
								" />
													</g>
													<g>
														<g>
															<polygon fill="#004669" points="553.057,647.656 527.88,653.873 533.082,638.306 								" />
														</g>
														<g>
															<polygon fill="#005A84" points="527.886,652.89 533.082,638.306 485.685,582.254 468.163,582.772 								" />
														</g>
														<g>
															<polygon fill="#004669" points="493.268,576.609 485.173,582.166 468.62,583.312 								" />
														</g>
														<g>
															<polygon fill="#004669" points="527.754,653.977 528.005,653.769 468.745,583.206 468.496,583.416 								" />
														</g>
														<g>

															<rect x="527.493" y="650.57" transform="matrix(0.9709 -0.2397 0.2397 0.9709 -140.2182 148.5109)" fill="#365B8A"
															 width="25.937" height="0.326" />
														</g>
														<g>
															<polygon fill="#004669" points="468.664,583.468 493.647,576.536 493.56,576.221 468.577,583.153 								" />
														</g>
														<g>
															<polygon fill="#00486C" points="553.057,647.656 533.082,638.306 485.173,582.166 493.603,576.379 								" />
														</g>
													</g>
													<g>
														<polygon fill="#005A84" points="466.818,597.573 464.376,608.332 469.165,613.927 471.326,603.149 							" />
													</g>
													<polygon fill="#005A84" points="480.875,633.472 484.278,618.714 491.385,627.846 487.982,642.792 						" />
												</g>
												<g>
													<g>
														<g>
															<polygon fill="#005A84" points="445.299,598.708 460.877,594.331 454.587,588.921 439.428,592.883 								" />
														</g>
														<g>
															<polygon fill="#9DC8E7" points="444.272,634.004 458.653,628.904 460.877,594.331 445.575,598.672 								" />
														</g>
														<g>
															<polygon fill="#32A2D5" points="438.154,627.43 444.271,634.052 445.574,598.718 439.43,592.808 								" />
														</g>
													</g>
													<g>
														<g>
															<path fill="#00486C" d="M447.6,597.363c0.157,0.006,0.291-0.117,0.298-0.276l0.238-5.445l8.52-2.607
									c0.097-0.031,0.171-0.109,0.195-0.207c0.025-0.098-0.006-0.203-0.078-0.273l-2.827-2.773
									c-0.074-0.073-0.182-0.102-0.282-0.071l-9.402,2.73c-0.12,0.035-0.204,0.144-0.208,0.271l-0.092,4.269
									c-0.003,0.157,0.121,0.287,0.276,0.294h0.004c0.159,0.003,0.289-0.123,0.294-0.282l0.089-4.058l9.038-2.623l2.362,2.317
									l-8.251,2.525c-0.116,0.037-0.197,0.142-0.204,0.263l-0.246,5.648C447.316,597.223,447.439,597.357,447.6,597.363
									L447.6,597.363z" />
														</g>
													</g>
													<g>
														<path fill="#00486C" d="M456.345,594.85c0.159,0.006,0.293-0.119,0.298-0.276l0.206-5.59
								c0.006-0.159-0.119-0.293-0.276-0.298c-0.159-0.006-0.293,0.119-0.298,0.276l-0.206,5.59
								C456.064,594.71,456.187,594.843,456.345,594.85z" />
													</g>
													<g>
														<path fill="#00486C" d="M453.511,592.317c0.159,0.006,0.293-0.119,0.298-0.276l0.216-5.855
								c0.006-0.159-0.119-0.293-0.276-0.298c-0.159-0.006-0.293,0.119-0.298,0.276l-0.217,5.855
								C453.228,592.177,453.353,592.311,453.511,592.317z" />
													</g>
												</g>
												<g>
													<g>
														<g>
															<polygon fill="#005A84" points="510.442,674.24 526.018,669.863 519.726,664.453 504.569,668.415 								" />
														</g>
														<g>
															<polygon fill="#9DC8E7" points="509.413,709.534 523.794,704.436 526.018,669.863 510.716,674.204 								" />
														</g>
														<g>
															<polygon fill="#32A2D5" points="503.295,702.96 509.412,709.582 510.715,674.25 504.571,668.34 								" />
														</g>
													</g>
													<g>
														<g>
															<path fill="#00486C" d="M512.741,672.894c0.157,0.006,0.291-0.117,0.298-0.276l0.238-5.445l8.52-2.607
									c0.097-0.031,0.171-0.109,0.195-0.207c0.025-0.098-0.006-0.203-0.078-0.273l-2.825-2.773
									c-0.074-0.073-0.182-0.102-0.281-0.071l-9.402,2.73c-0.12,0.035-0.204,0.144-0.208,0.271l-0.092,4.269
									c-0.003,0.157,0.121,0.287,0.276,0.294h0.004c0.159,0.003,0.291-0.123,0.294-0.282l0.089-4.058l9.038-2.623l2.362,2.317
									l-8.251,2.525c-0.116,0.037-0.197,0.142-0.204,0.263l-0.245,5.648C512.457,672.755,512.608,672.869,512.741,672.894z" />
														</g>
													</g>
													<g>
														<path fill="#00486C" d="M521.486,670.38c0.159,0.006,0.293-0.119,0.298-0.276l0.206-5.59
								c0.006-0.159-0.119-0.293-0.276-0.298c-0.159-0.006-0.293,0.119-0.298,0.276l-0.206,5.59
								C521.203,670.242,521.328,670.376,521.486,670.38z" />
													</g>
													<g>
														<path fill="#00486C" d="M518.652,667.848c0.159,0.006,0.293-0.119,0.298-0.276l0.216-5.855
								c0.006-0.159-0.119-0.293-0.276-0.298c-0.159-0.006-0.293,0.119-0.298,0.276l-0.216,5.855
								C518.369,667.709,518.492,667.843,518.652,667.848z" />
													</g>
												</g>
											</g>
											<g>
												<g>
													<g>
														<polygon fill="#005A84" points="502.491,643.129 500.05,653.888 504.838,659.485 506.999,648.705 							" />
													</g>
												</g>
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#32A2D5" points="525.773,676.449 526.376,707.033 560.671,787.595 560.047,755.956 					" />
												<polygon fill="#9DC8E7" points="560.047,755.956 611.725,754.937 612.272,782.709 560.671,787.595 					" />
												<polygon fill="#005A84" points="525.746,675.043 558.467,675.805 582.614,741.093 560.047,755.956 					" />
												<polygon fill="#004669" points="582.614,741.093 560.047,755.956 611.725,754.937 					" />
												<polygon fill="#004669" points="525.746,675.043 578.297,664.864 558.467,675.805 					" />
												<polygon fill="#00486C" points="578.297,664.864 558.467,675.805 582.614,741.093 611.725,754.937 					" />
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="537.858,732.479 539.858,737.496 541.452,731.719 539.451,726.701 						" />
													<path fill="#005A84" d="M537.798,732.493l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.646,5.969L537.798,732.493z
							 M539.463,726.892l-1.542,5.585l1.924,4.826l1.54-5.585L539.463,726.892z" />
												</g>
												<g>
													<polygon fill="#005A84" points="534.762,724.554 536.762,729.57 538.356,723.794 536.355,718.775 						" />
													<path fill="#005A84" d="M534.703,724.568l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.646,5.969L534.703,724.568z
							 M536.365,718.967l-1.54,5.585l1.924,4.826l1.54-5.585L536.365,718.967z" />
												</g>
												<g>
													<polygon fill="#005A84" points="532.047,716.999 534.046,722.016 535.64,716.239 533.639,711.221 						" />
													<path fill="#005A84" d="M531.988,717.014L531.982,717l1.646-5.969l2.071,5.194l0.006,0.013l-1.648,5.969L531.988,717.014z
							 M533.651,711.412l-1.54,5.585l1.924,4.826l1.542-5.585L533.651,711.412z" />
												</g>
												<g>
													<polygon fill="#005A84" points="528.348,708.719 530.346,713.738 531.942,707.96 529.941,702.943 						" />
													<path fill="#005A84" d="M528.288,708.736l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.648,5.969L528.288,708.736z
							 M529.953,703.135l-1.542,5.585l1.924,4.826l1.542-5.585L529.953,703.135z" />
												</g>
												<g>
													<polygon fill="#005A84" points="541.085,724.199 543.085,729.216 544.677,723.439 542.679,718.42 						" />
													<path fill="#005A84" d="M541.026,724.213l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.646,5.969L541.026,724.213z
							 M542.69,718.612l-1.542,5.585l1.924,4.826l1.54-5.585L542.69,718.612z" />
												</g>
												<g>
													<polygon fill="#005A84" points="537.989,716.274 539.99,721.29 541.583,715.514 539.583,710.495 						" />
													<path fill="#005A84" d="M537.932,716.288l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.646,5.969L537.932,716.288z
							 M539.594,710.687l-1.54,5.585l1.924,4.826l1.54-5.585L539.594,710.687z" />
												</g>
												<g>
													<polygon fill="#005A84" points="535.273,708.717 537.273,713.734 538.867,707.957 536.866,702.941 						" />
													<path fill="#005A84" d="M535.215,708.734l-0.006-0.015l1.646-5.969l2.071,5.194l0.006,0.013l-1.648,5.969L535.215,708.734z
							 M536.878,703.132l-1.54,5.585l1.924,4.826l1.542-5.585L536.878,703.132z" />
												</g>
												<g>
													<polygon fill="#005A84" points="531.575,700.439 533.573,705.456 535.167,699.68 533.169,694.663 						" />
													<path fill="#005A84" d="M531.516,700.454l-0.006-0.015l1.648-5.969l2.069,5.194l0.006,0.013l-1.648,5.969L531.516,700.454z
							 M533.18,694.855l-1.542,5.585l1.924,4.826l1.542-5.585L533.18,694.855z" />
												</g>
											</g>
											<g>
												<g>
													<polygon fill="#005A84" points="550.898,763.345 552.899,768.362 554.493,762.586 552.494,757.567 						" />
													<path fill="#005A84" d="M550.841,763.36l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.646,5.969L550.841,763.36z
							 M552.504,757.759l-1.542,5.585l1.924,4.826l1.54-5.585L552.504,757.759z" />
												</g>
												<g>
													<polygon fill="#005A84" points="547.804,755.42 549.803,760.437 551.397,754.66 549.396,749.642 						" />
													<path fill="#005A84" d="M547.745,755.435l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.646,5.969L547.745,755.435z
							 M549.408,749.833l-1.54,5.585l1.924,4.826l1.54-5.585L549.408,749.833z" />
												</g>
												<g>
													<polygon fill="#005A84" points="545.088,747.865 547.086,752.882 548.682,747.106 546.682,742.087 						" />
													<path fill="#005A84" d="M545.029,747.88l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.648,5.969L545.029,747.88z
							 M546.691,742.279l-1.54,5.585l1.924,4.826l1.542-5.585L546.691,742.279z" />
												</g>
												<g>
													<polygon fill="#005A84" points="541.388,739.586 543.387,744.604 544.983,738.828 542.982,733.809 						" />
													<path fill="#005A84" d="M541.329,739.602l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.648,5.969L541.329,739.602z
							 M542.994,734.001l-1.542,5.585l1.924,4.826l1.542-5.585L542.994,734.001z" />
												</g>
												<g>
													<polygon fill="#005A84" points="554.126,755.065 556.126,760.082 557.72,754.305 555.719,749.287 						" />
													<path fill="#005A84" d="M554.068,755.08l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.015l-1.646,5.969L554.068,755.08z
							 M555.731,749.478l-1.542,5.585l1.924,4.826l1.54-5.585L555.731,749.478z" />
												</g>
												<g>
													<polygon fill="#005A84" points="551.03,747.14 553.03,752.157 554.624,746.38 552.624,741.364 						" />
													<path fill="#005A84" d="M550.973,747.155l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.646,5.969L550.973,747.155z
							 M552.635,741.553l-1.54,5.585l1.924,4.826l1.54-5.585L552.635,741.553z" />
												</g>
												<g>
													<polygon fill="#005A84" points="548.315,739.583 550.314,744.602 551.91,738.824 549.909,733.807 						" />
													<path fill="#005A84" d="M548.256,739.6l-0.006-0.013l1.646-5.969l2.071,5.194l0.006,0.013l-1.648,5.969L548.256,739.6z
							 M549.921,733.999l-1.54,5.585l1.924,4.826l1.542-5.585L549.921,733.999z" />
												</g>
												<g>
													<polygon fill="#005A84" points="544.616,731.306 546.616,736.322 548.21,730.546 546.21,725.529 						" />
													<path fill="#005A84" d="M544.558,731.32l-0.006-0.013l1.648-5.969l2.069,5.194l0.006,0.013l-1.648,5.969L544.558,731.32z
							 M546.221,725.721l-1.542,5.585l1.924,4.826l1.542-5.585L546.221,725.721z" />
												</g>
											</g>
										</g>
									</g>
								</g>
							</g>
							<g id="s04" class="tooltip-item" title="消息提示">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="429.232,906.545 187.983,572.351 343.487,516.972 595.156,841.887 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="429.232,906.545 187.983,572.351 343.487,516.972 
			595.156,841.887 		" />
								</g>
							</g>
							<g id="s03" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="1218.141,255.618 1251.875,243.527 1317.456,298.913 1277.994,313.485 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="1218.141,255.618 1251.875,243.527 1317.456,298.913 
			1277.994,313.485 		" />
									<g>
										<g>
											<g>
												<g>
													<polygon fill="#32A2D5" points="1198.982,289.324 1198.785,279.107 1189.198,272.575 1189.155,281.184 						" />
												</g>
												<g>
													<polygon fill="#9DC8E7" points="1198.982,289.324 1234.096,280.92 1234.326,271.176 1198.775,278.807 						" />
												</g>
												<g>
													<polygon fill="#004669" points="1234.285,271.412 1229.953,264.732 1225.821,265.476 1224.646,263.605 1189.137,272.581 
							1198.785,279.107 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1220.667,278.715 1226.81,277.504 1227.163,275.028 1221.021,276.239 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1211.313,280.395 1217.457,279.185 1217.809,276.707 1211.666,277.918 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1201.901,282.14 1226.762,277.569 1226.82,277.122 1201.913,281.833 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1230.325,276.205 1231.314,276.059 1231.673,273.538 1230.684,273.684 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1227.741,276.615 1228.732,276.469 1229.091,273.948 1228.1,274.094 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1230.015,280.828 1231.004,280.682 1231.363,278.161 1230.374,278.307 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1227.562,281.273 1228.551,281.127 1228.912,278.605 1227.921,278.753 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1201.877,282.152 1208.031,280.995 1208.164,278.703 1201.962,279.76 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1220.021,283.239 1226.167,282.028 1226.519,279.552 1220.375,280.761 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1210.647,285.059 1216.791,283.85 1217.145,281.371 1211.001,282.582 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1201.532,284.521 1226.424,279.912 1226.483,279.466 1201.584,284.221 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1201.193,286.898 1226.121,282.048 1226.179,281.602 1201.245,286.593 						" />
												</g>
												<g>
													<polygon fill="#002E4B" points="1201.213,286.815 1207.366,285.659 1207.5,283.367 1201.538,284.458 						" />
												</g>
											</g>
											<g>
												<polygon fill="#005A84" points="1233.256,271.602 1229.005,269.601 1242.381,266.655 1236.045,260.966 1193.903,269.643 
						1198.785,279.107 					" />
											</g>
											<g>
												<polygon fill="#00486C" points="1189.148,272.588 1193.903,269.643 1194.694,271.176 					" />
											</g>
										</g>
										<g>
											<g>
												<polygon fill="#B2B2B2" points="1261.461,287.647 1262.079,288.349 1261.779,283.819 1261.184,283.638 					" />
											</g>
											<g>
												<polygon fill="#9DC8E7" points="1271.424,292.735 1272.245,305.195 1252.846,309.557 1251.875,296.789 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1256.489,298.913 1260.437,298.073 1260.408,296.602 1256.38,297.392 					" />
											</g>
											<g>
												<polygon fill="#002E4B" points="1264.174,297.645 1268.124,296.805 1268.095,295.336 1264.065,296.125 					" />
											</g>
											<g>
												<polygon fill="#00A0E9" points="1236.045,260.966 1235.486,261.081 1265.784,288.333 1266.534,288.342 					" />
											</g>
											<g>
												<polygon fill="#00486C" points="1266.051,288.351 1235.753,261.099 1232.244,266.284 1255.667,288.23 					" />
											</g>
											<g>
												<polygon fill="#004669" points="1265.662,287.989 1255.349,284.714 1247.111,291.787 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="1223.984,271.669 1247.111,291.787 1254.088,285.797 1232.781,266.845 					" />
											</g>
											<g>
												<polygon fill="#005A84" points="1251.874,296.738 1270.764,292.61 1265.628,287.955 1247.111,291.787 					" />
											</g>
											<g>
												<g>
													<polygon fill="#32A2D5" points="1251.82,297.053 1271.424,292.735 1266.534,288.342 1266.051,288.338 1270.643,292.5 
							1251.874,296.738 1251.507,296.332 1250.927,296.515 						" />
												</g>
											</g>
											<polygon fill="#32A2D5" points="1247.111,291.787 1247.406,305.936 1253.042,309.772 1252.074,296.997 				" />
										</g>
									</g>
								</g>
							</g>
							<g id="s02" class="tooltip-item" title="s02">
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="187.983,659.168 134.046,677.585 234.031,829.607 288.705,809.58 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="187.983,659.168 134.046,677.585 234.031,829.607 
			288.705,809.58 		" />
									<g>
										<g>
											<g>
												<g>
													<g>
														<polygon fill="#005A84" points="162.986,648.4 173.666,657.222 192.527,650.75 182.394,641.939 							" />
														<polygon fill="none" stroke="#00A0E9" stroke-width="0.3938" stroke-miterlimit="10" points="162.818,648.332 
								173.704,657.621 192.818,650.825 182.43,641.717 							" />
														<polygon fill="#004669" points="177.068,643.525 173.851,657.157 192.527,650.75 							" />
														<polygon fill="#9DC8E7" points="173.704,657.621 170.185,686 189.372,679.676 192.815,651.095 							" />
														<polygon fill="#32A2D5" points="170.185,686 160.076,671.139 162.494,648.253 173.704,657.621 							" />
													</g>
												</g>
											</g>
											<g>
												<g>
													<g>
														<g>
															<g>
																<polygon fill="#9DC8E7" points="208.376,721.805 232.671,713.196 191.242,664.532 174.81,669.897 									" />
															</g>
															<polygon fill="#32A2D5" points="174.644,669.677 172.73,685.117 197.303,719.521 200.593,701.914 								" />
															<g>
																<polygon fill="#9DC8E7" points="231.324,710.262 229.624,728.136 202.807,738.065 202.839,714.346 									" />
															</g>
															<polygon fill="#32A2D5" points="198.215,710.958 196.332,728.646 202.807,738.065 205.666,719.391 								" />
														</g>
														<g>
															<polygon fill="#005A84" points="178.738,681.14 178.469,685.604 180.998,689.633 181.272,685.093 								" />
														</g>
														<g>
															<polygon fill="#005A84" points="184.226,688.101 183.957,692.565 186.486,696.593 186.76,692.053 								" />
														</g>
														<g>
															<polygon fill="#005A84" points="190.574,695.751 190.305,700.215 192.834,704.243 193.108,699.703 								" />
														</g>
													</g>
													<g>
														<polygon fill="#9DC8E7" points="264.526,749.653 261.04,771.702 230.854,779.271 233.818,756.569 							" />
													</g>
													<g>
														<polygon fill="#00486C" points="271.836,756.98 224.057,698.956 210.662,694.367 210.662,694.367 255.094,756.665 							" />
													</g>
													<g>
														<polygon fill="#004669" points="271.836,756.98 253.641,754.096 238.091,764.886 							" />
													</g>
													<g>
														<polygon fill="#005A84" points="192.836,708.049 238.059,764.846 253.61,754.056 210.662,694.367 							" />
													</g>
													<g>
														<polygon fill="#9DC8E7" points="244.673,787.188 268.566,781.969 260.433,762.467 235.432,767.815 							" />
													</g>
													<polygon fill="#32A2D5" points="227.427,751.492 224.665,769.057 231.082,779.651 234.739,760.522 						" />
												</g>
											</g>
											<g>
												<g>
													<g>
														<polygon fill="#005A84" points="245.016,781.852 255.697,790.674 274.558,784.202 264.424,775.391 							" />
														<polygon fill="none" stroke="#00A0E9" stroke-width="0.3938" stroke-miterlimit="10" points="244.849,781.784 
								255.734,791.073 274.849,784.277 264.461,775.169 							" />
														<polygon fill="#004669" points="259.099,776.977 255.882,790.609 274.558,784.202 							" />
														<polygon fill="#9DC8E7" points="255.734,791.073 252.215,819.452 271.403,813.127 274.845,784.547 							" />
														<polygon fill="#32A2D5" points="252.215,819.452 242.107,804.591 244.524,781.705 255.734,791.073 							" />
													</g>
												</g>
											</g>
										</g>
										<polygon fill="#32A2D5" points="244.583,799.1 233.109,778.381 235.432,767.815 244.077,785.939 			" />
									</g>
								</g>
							</g>
							<g id="s01" class="tooltip-item" title>
								<g>
									<polygon fill="#B38D1B" fill-opacity="0" points="2.482,680.967 55.357,662.381 204.32,889.73 146.446,911.554 		" />
									<polygon fill="none" stroke="#B38D1B" stroke-miterlimit="10" points="2.482,680.967 55.357,662.381 204.32,889.73 
			146.446,911.554 		" />
								</g>
							</g>
						</svg>

					</div>
				</div>
			</div>
		</div>
	</div>
	</div>




<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script src="${ctx}/static/js/callback/callback.js"></script>
<script type="text/javascript" src="${ctx}/static/bj-cui/js/jyshouye.js"></script>
<script>
		jsConst.basePath = "${ctx}/";
</script>

 
<script type="text/javascript">
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
			initUserInfo();
			var list = [{
				id: "s02",
				name: "北大门网格"
			}, {
				id: "s05",
				name: "综合楼网格"
			}, {
				id: "s07",
				name: "生产区网格"
			}, {
				id: "s03",
				name: "南大门网格"
			}, {
				id: "s01",
				name: "监区外停车场网格"
			}, {
				id: "s16_1_",
				name: "办公区网格"
			}, {
				id: "s11",
				name: "车队网格"
			}, {
				id: "s10",
				name: "武警分队网格"
			}, {
				id: "s15_1_",
				name: "候见室及停车场网格"
			}, {
				id: "s17_1_",
				name: "指挥中心网格"
			}, {
				id: "s19_1_",
				name: "菜园网格"
			}, {
				id: "s04",
				name: "政治改造广场网格"
			}, {
				id: "s06",
				name: "运动场网格"
			}, {
				id: "s08",
				name: "会见室网格"
			}, {
				id: "s20_1_",
				name: "围墙与内隔离网网格"
			}, {
				id: "s14_1_",
				name: "办公楼网格"
			}, {
				id: "s13_1_",
				name: "干职食堂网格"
			}, {
				id: "s12_1_",
				name: "行政库区网格"
			}, {
				id: "s21_1_",
				name: "外巡逻道及岗楼网格"
			}, {
				id: "s18_1_",
				name: "备勤楼及停车场网格"
			}, {
				id: "s09",
				name: "配电室网格"
			}]
			var all = [],
				length = list.length,
				largetId = "s21_1_",
				smallList = ["s12_1_", "s17_1_"],
				specialId = ["s11", "s12_1_"],
				largestChildren = $("#" + largetId).children();
			$(".center:first").addClass("bigMore")
			for(var b=length;b--;) {
				var temp = $("#"+list[b].id);
				all.push(temp.find("polygon[fill=none]").eq(0));
			}
			function hanldeSpecial() {
				var specialList = specialId,
					length = specialList.length;
				for (var k = length; k--;) {
					var tmp = $("#" + specialList[k]);
					tmp.children().children().eq(0).removeAttr("opacity").attr("fill-opacity", "0");
				}
			}
			hanldeSpecial();

			function setLargetSgv(show, flag) {
				largestChildren.children().removeClass("unvisible");
				if (show) {
					largestChildren.children().eq(0).attr("fill-opacity", "0.3");
					largestChildren.attr("stroke-width", "5");
				} else {
					largestChildren.children().eq(0).attr("fill-opacity", "0");
					largestChildren.attr("stroke-width", "1");
					if (!flag) {
						largestChildren.children().addClass("unvisible");
					}
				}
			}
			$(".tooltip-item").tooltip({
				content: function (e, ui) {

					return '<div class="tips-text"><img src="${ctx}/static/bj-cui/img/svg/tipsBG.png" class="default"><img src="${ctx}/static/bj-cui/img/svg/tips_down.png" class="down"><img src="${ctx}/static/bj-cui/img/svg/tips_right.png" class="right-img"><div class="text">' +
						$(this).attr("title") + '</div></div></div>';
				},
				onOpen: function () {
					for (var i = length; i--;) {
						var ele = $(all[i]);
						if (!ele.parent().parent().attr("aria-describedby")) {
							ele.addClass("unvisible");
						} else {
							ele.removeClass("unvisible");
							ele.attr("stroke-width", "5");
							ele.parent().children().eq(0).attr("fill-opacity", "0.3");
						}
					}
					setLargetSgv(false);
					if ($(this).attr("id") == largetId) {
						setLargetSgv(true);
						return;
					}

					var ele = $(".coral-tooltip");
					ele = ele.eq(ele.length - 1);
					var left = parseInt(ele.css("left"));
					var top = parseInt(ele.css("top")),
						maxTop = parseInt($(this).css("top"));
					ele.removeClass("show-down");
					ele.removeClass("show-right");
					var id = $(this).attr("id");
					if (id == "s12_1_") {
						left = left - 40;
					} else if (id == "s19_1_") {
						ele.addClass("show-down");
						left = left - 150;
						top = top + 50;
					} else if (id == "s01" || id == "s02") {
						left = left + 100;
						ele.addClass("show-right");
					} else if ( id == "s17_1_") {
						top = top -60;
						left = left + 30;
 					} else if (id=="s11") {
						left = left - 120;
						top = top - 100
					 } else if (id != "s20_1_") {
						left = left - 200;
						top = top - 60;
					}
					ele.css({
						left: left,
						top: top
					})
				},
				onClose: function () {
					for (var i = length; i--;) {
						var ele = $(all[i]);
						ele.attr("stroke-width", "1");
						ele.removeClass("unvisible");
						var parent = ele.parent();
						if(parent.attr("id") == "s07") {
							//parent.children().eq()
							return;
						}
						ele.parent().children().eq(0).attr("fill-opacity", "0");
					}
					setLargetSgv(false, true);
					addTitle(true);
				}
			})
			$("#" + largetId).tooltip("option", "position", {
				my: "left center",
				at: "left center",
				of: window
			})
			$("#s20_1_").tooltip("option", "position", {
				my: "center center",
				at: "left+200 center",
				of: window
			})
			$("#s12_1_").tooltip("option", "position", {
				my: "center-50 center-20",
				at: "left center",
				of: $("#s12_1_")
			})
			var listLength = list.length,
				ulContent = "",
				active;

			function addLi(id, name) {
				ulContent += "<li data-id='" + id + "' class='tips-item'><div class='list-text'>" + name + "</div></li>";
			}

			function addTitle(flag, initial) {
				for (var i = 0; i < listLength; i++) {
					var parents = $("#" + list[i].id);
					title = parents.attr("title");
					if (initial || !title) {
						parents.attr("title", list[i].name);
					}
					if (!flag) {
						addLi(list[i].id, list[i].name);
					}
				}
			}
			addTitle(false, true);
			$(".tips-bottom").html(ulContent);
			$(".tips-bottom").on("mouseenter", ".list-text", function (e) {
				var element = $(e.target).parents("li").attr("data-id");
				active = $("#" + element);
				active.tooltip("open");
			})
			$(".tips-bottom").on("mouseleave", ".list-text", function (e) {
				active.tooltip("close");
			})
		});
		 
		function showDqyh() {
			$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME+ "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
		
	</script>
</body>

</html>