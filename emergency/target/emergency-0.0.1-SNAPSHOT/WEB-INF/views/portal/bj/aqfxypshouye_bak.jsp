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
	<title>安全风险研判</title>
	
	<!-- app css define start -->
	<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
	<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
	<!-- app css define end -->

	<!-- <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_1001725_ji4zbatt03.css" /> -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" /> --%>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-new.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
</head>

<body>

<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>

<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>

<header class="perspective">
    <img src="${ctx}/static/bj-cui/img/command/logo_zhihui.png" alt="指挥中心logo" class="logo">
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
					<li class="tolist-menuitem">
						五维架构维护
					</li>
					<li class="tolist-menuitem">
						风险等级维护
					</li>
					<li class="tolist-menuitem">
						权重等级维护
					</li>
					<li class="tolist-menuitem">
						数据来源维护
					</li>
					<li class="tolist-menuitem">
						数据范围管理
					</li>
					<li class="tolist-menuitem">
						风险点管理
					</li>
					<li class="tolist-menuitem">
						评估条件管理
					</li>
				</ul>
			</li>
			<li class="tolist-item status">
				风险分析
				<ul class="tolist-menu">
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
				</ul>
			</li>
			<li class="tolist-item status">
				风险预警处置管理
			</li>
			<li class="tolist-item status">
				风险管控管理
			</li>
			<li class="tolist-item status">
				风险采集管理
			</li>
			
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
  
    <div class="container-box" style="background-image: url(${ctx}/static/bj-cui/img/bg2.jpg)!important">
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
		    <div data-options="region:'center'" class="main" >
		    	<div id="layout-child">
				    <div data-options="region:'center'" style="overflow:hidden">
						<a class="back hide"><返回</a>
						<div class="safeTooltip">
							<ul>
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
							</ul>
						</div>
						<div class="center cls-first">
							<svg version="1.1" id="svg" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" 
								 y="0px" viewBox="0 0 1280 260" style="enable-background:new 0 0 1280 420;" xml:space="preserve">
							<g>
								<g class="content show" id="1" >
									<g>
										<g>
											<g>
												<polygon style="fill:#375F91;" points="425.308,135.911 423.324,104.906 465.594,28.006 466.212,58.082 					"/>
											</g>
											<g>
												<polygon style="fill:#457AB6;" points="425.308,135.911 366.103,129.497 361.322,99.972 423.324,105.567 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="440.316,103.764 435.697,112.712 434.826,101.221 439.446,92.272 				"/>
											<path style="fill:#0B2443;" d="M435.623,113.072l-0.899-11.873l0.013-0.025l4.781-9.262l0.9,11.873l-0.013,0.025
												L435.623,113.072z M434.93,101.241l0.841,11.11l4.445-8.608l-0.842-11.11L434.93,101.241z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="447.513,89.611 442.894,98.559 442.023,87.068 446.644,78.119 				"/>
											<path style="fill:#0B2443;" d="M442.82,98.919l-0.899-11.873l0.013-0.025l4.782-9.262l0.899,11.873l-0.013,0.025L442.82,98.919z
												 M442.126,87.087l0.841,11.11l4.445-8.608l-0.841-11.11L442.126,87.087z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="453.999,76.047 449.381,84.994 448.509,73.503 453.13,64.556 				"/>
											<path style="fill:#0B2443;" d="M449.308,85.355l-0.9-11.873l0.013-0.025l4.782-9.262l0.899,11.873l-0.013,0.025L449.308,85.355z
												 M448.613,73.523l0.842,11.11l4.444-8.608l-0.841-11.11L448.613,73.523z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="462.256,61.405 457.638,70.353 456.766,58.862 461.386,49.915 				"/>
											<path style="fill:#0B2443;" d="M457.565,70.714l-0.9-11.873l0.013-0.025l4.781-9.262l0.9,11.873l-0.013,0.025L457.565,70.714z
												 M456.87,58.883l0.842,11.11l4.444-8.608l-0.842-11.11L456.87,58.883z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="437.572,87.005 432.951,95.953 432.082,84.462 436.702,75.515 				"/>
											<path style="fill:#0B2443;" d="M432.877,96.314l-0.899-11.873l0.013-0.025l4.781-9.262l0.9,11.873l-0.013,0.025L432.877,96.314z
												 M432.184,84.483l0.841,11.11l4.445-8.608l-0.842-11.11L432.184,84.483z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="444.769,72.852 440.148,81.8 439.279,70.309 443.898,61.361 				"/>
											<path style="fill:#0B2443;" d="M440.074,82.161l-0.899-11.873l0.013-0.025l4.782-9.262l0.899,11.873l-0.013,0.025
												L440.074,82.161z M439.381,70.33l0.841,11.11l4.445-8.608l-0.841-11.11L439.381,70.33z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="451.255,59.288 446.636,68.236 445.765,56.745 450.385,47.797 				"/>
											<path style="fill:#0B2443;" d="M446.562,68.597l-0.9-11.873l0.013-0.025l4.782-9.262l0.899,11.873l-0.013,0.025L446.562,68.597z
												 M445.867,56.766l0.842,11.11l4.444-8.608l-0.841-11.11L445.867,56.766z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="459.512,44.647 454.892,53.594 454.022,42.103 458.642,33.156 				"/>
											<path style="fill:#0B2443;" d="M454.819,53.955l-0.9-11.873l0.013-0.025l4.781-9.263l0.9,11.873l-0.013,0.025L454.819,53.955z
												 M454.124,42.125l0.842,11.11l4.444-8.608l-0.842-11.11L454.124,42.125z"/>
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#213F62;" points="362.366,99.411 422.87,104.718 468.867,20.506 413.67,15.552 378.405,78.652 
												374.159,78.417 				"/>
											<path style="fill:#0B2443;" d="M423.266,105.458l-62.046-5.442l12.541-22.324l4.244,0.235l35.274-63.115l56.717,5.091
												L423.266,105.458z M363.511,98.806l58.963,5.173l45.263-82.87l-53.677-4.816l-35.257,63.084l-4.247-0.235L363.511,98.806z"/>
										</g>
										<g>
											<polygon style="fill:#457AB6;" points="363.492,98.759 397.526,87.952 435.186,18.22 414.12,16.334 378.793,79.469 
												374.594,79.189 				"/>
										</g>
										<g>
											<polygon style="fill:#264B6C;" points="364.128,98.559 397.526,87.952 423.29,103.951 				"/>
										</g>
									</g>
								</g>
								<g class="content show" id="2">
									<g>
										<g>
											<g>
												<polygon style="fill:#0B2744;" points="276.37,179.548 257.431,173.463 265.405,167.102 283.85,172.657 					"/>
											</g>
											<g>
												<polygon style="fill:#4375AC;" points="276.035,222.961 258.597,216.046 257.431,173.463 276.035,179.491 					"/>
											</g>
											<g>
												<polygon style="fill:#375C8A;" points="283.85,215.161 276.035,223.019 276.035,179.548 283.85,172.565 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#0B2744;" points="276.106,179.493 275.964,179.49 276.202,172.357 276.342,172.361 				"/>
										</g>
										<g>
											<g>
												<path style="fill:#4375AC;" d="M273.606,177.79c-0.194,0-0.352-0.157-0.354-0.352l-0.045-6.7l-10.35-3.589
													c-0.117-0.041-0.205-0.142-0.231-0.263c-0.025-0.122,0.017-0.249,0.109-0.333l3.598-3.279c0.095-0.086,0.228-0.116,0.35-0.075
													l11.43,3.779c0.147,0.048,0.245,0.187,0.242,0.341l-0.079,5.251c-0.003,0.194-0.161,0.348-0.354,0.348
													c-0.001,0-0.003,0-0.006,0c-0.195-0.003-0.351-0.164-0.348-0.359l0.075-4.99l-10.987-3.632l-3.006,2.74l10.025,3.476
													c0.142,0.05,0.236,0.183,0.238,0.333l0.047,6.951C273.961,177.632,273.769,177.766,273.606,177.79z"/>
											</g>
										</g>
										<g>
											<path style="fill:#4375AC;" d="M262.974,174.306c-0.195,0-0.354-0.159-0.354-0.354v-6.878c0-0.195,0.159-0.354,0.354-0.354
												s0.354,0.159,0.354,0.354v6.878C263.328,174.147,263.17,174.306,262.974,174.306z"/>
										</g>
										<g>
											<path style="fill:#4375AC;" d="M266.572,171.322c-0.195,0-0.354-0.159-0.354-0.354v-7.202c0-0.195,0.159-0.354,0.354-0.354
												s0.354,0.159,0.354,0.354v7.202C266.926,171.164,266.767,171.322,266.572,171.322z"/>
										</g>
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#0B2744;" points="352.952,115.437 334.011,109.353 341.985,102.991 360.432,108.546 					"/>
											</g>
											<g>
												<polygon style="fill:#4375AC;" points="352.616,158.852 335.178,151.935 334.011,109.353 352.616,115.381 					"/>
											</g>
											<g>
												<polygon style="fill:#375C8A;" points="360.432,151.051 352.616,158.91 352.616,115.437 360.432,108.454 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#0B2744;" points="352.686,115.384 352.546,115.379 352.783,108.246 352.924,108.251 				"/>
										</g>
										<g>
											<g>
												<path style="fill:#4375AC;" d="M350.187,113.681c-0.194,0-0.352-0.157-0.354-0.352l-0.045-6.7l-10.35-3.589
													c-0.117-0.041-0.205-0.142-0.231-0.263c-0.025-0.122,0.017-0.249,0.109-0.333l3.599-3.279c0.095-0.086,0.228-0.116,0.35-0.075
													l11.43,3.779c0.147,0.048,0.245,0.187,0.242,0.341l-0.079,5.251c-0.003,0.194-0.161,0.348-0.354,0.348
													c-0.001,0-0.003,0-0.006,0c-0.195-0.003-0.351-0.164-0.348-0.359l0.075-4.99l-10.987-3.632l-3.006,2.74l10.025,3.476
													c0.142,0.05,0.236,0.183,0.238,0.333l0.047,6.951c0.001,0.195-0.156,0.354-0.352,0.355
													C350.188,113.681,350.188,113.681,350.187,113.681z"/>
											</g>
										</g>
										<g>
											<path style="fill:#4375AC;" d="M339.555,110.195c-0.195,0-0.354-0.159-0.354-0.354v-6.878c0-0.195,0.159-0.354,0.354-0.354
												s0.354,0.159,0.354,0.354v6.878C339.909,110.037,339.75,110.195,339.555,110.195z"/>
										</g>
										<g>
											<path style="fill:#4375AC;" d="M343.153,107.212c-0.195,0-0.354-0.159-0.354-0.354v-7.202c0-0.195,0.159-0.354,0.354-0.354
												s0.354,0.159,0.354,0.354v7.202C343.507,107.053,343.348,107.212,343.153,107.212z"/>
										</g>
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#457AB6;" points="307.211,251.934 250.126,230.616 248.888,198.063 307.847,218.733 					"/>
											</g>
											<g>
												<polygon style="fill:#375F91;" points="428.851,143.571 307.211,251.934 307.357,218.714 433.083,114.042 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="307.469,217.887 250.126,197.709 266.756,184.146 279.171,188.234 372.031,109.104 
													362.762,106.187 374.984,95.66 433.013,113.188 					"/>
												<path style="fill:#0B2443;" d="M307.612,218.682l-58.898-20.727l17.897-14.597l12.407,4.085l91.593-78.051l-9.26-2.914
													l13.471-11.603l59.645,18.018L307.612,218.682z M251.54,197.463l55.786,19.63l124.237-103.609l-56.413-17.04l-10.974,9.453
													l9.276,2.918l-94.125,80.208l-12.425-4.09L251.54,197.463z"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#264B6C;" points="292.138,185.82 321.373,198.554 318.282,178.612 				"/>
										</g>
										<g>
											<polygon style="fill:#213F62;" points="321.568,197.362 318.282,178.612 387.305,120.458 408.435,124.703 				"/>
										</g>
										<g>
											<polygon style="fill:#264B6C;" points="379.278,112.049 387.942,120.458 407.77,125.262 				"/>
										</g>
										<g>
											<polygon style="fill:#375F91;" points="321.503,198.707 321.242,198.401 407.64,125.11 407.899,125.415 				"/>
										</g>
										<g>
											
												<rect x="306.544" y="176.234" transform="matrix(0.3993 -0.9168 0.9168 0.3993 8.0672 396.6682)" style="fill:#375F91;" width="0.401" height="31.888"/>
										</g>
										<g>
											<polygon style="fill:#375F91;" points="407.685,125.444 378.834,111.882 379.004,111.518 407.855,125.08 				"/>
										</g>
										<g>
											<polygon style="fill:#457AB6;" points="292.138,185.82 318.282,178.612 387.942,120.458 378.919,111.701 				"/>
										</g>
									</g>
								</g>
								<g class="content show" id="3">
									<g>
										<g>
											<g>
												<polygon style="fill:#7DC4DD;" points="1002.018,172.569 989.48,168.189 990.282,141.63 1002.588,145.575 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="991.607,141.319 1013.231,119.697 1108.9,148.847 1084.577,178.577 998.814,150.688 
													1003.139,145.104 					"/>
												<path style="fill:#0B2443;" d="M1084.816,179.392l-87.169-28.344l4.328-5.588l-11.671-3.83l22.728-22.728l97.076,29.577
													L1084.816,179.392z M999.98,150.33l84.358,27.43l23.357-28.547l-94.264-28.721l-20.519,20.516l11.392,3.739L999.98,150.33z"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="1083.592,208.165 997.117,177.232 997.918,151.116 1085.058,179.477 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1043.357,174.92 1029.994,170.306 1030.075,163.36 1043.476,167.774 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1042.178,190.137 1028.815,185.523 1028.896,178.577 1042.297,182.991 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1063.753,181.771 1050.389,177.172 1050.469,169.999 1063.831,174.599 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1084.248,189.137 1029.991,171.324 1029.995,170.306 1084.319,188.444 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1031.606,168.567 997.38,157.25 997.387,156.2 1031.691,167.795 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1005.105,162.816 1002.936,162.022 1002.868,156.195 1005.037,156.827 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1010.754,164.568 1008.585,163.735 1008.518,157.989 1010.688,158.621 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1004.383,176.62 1002.213,175.826 1002.147,169.999 1004.315,170.63 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1010.031,178.372 1007.863,177.537 1007.796,171.791 1009.965,172.423 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1084.308,188.545 1070.906,183.925 1071.215,177.232 1084.822,181.49 					"/>
											</g>
											<g>
												<polygon style="fill:#FFFFFF;" points="1065.287,201.644 1046.906,195.085 1046.906,187.975 1065.287,194.535 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="1070.906,195.808 1037.733,184.993 1044.841,181.771 1073.598,191.325 					"/>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="996.11,163.555 990.943,161.749 992.087,161.288 996.565,162.864 					"/>
											</g>
											<g>
												<polygon style="fill:#457AB6;" points="1083.297,208.144 1084.772,179.538 1110.066,148.435 1103.848,175.448 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="995.499,170.384 991.607,168.942 991.767,162.309 995.659,163.672 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#1E3D69;" points="999.942,150.278 1037.801,134.533 1103.233,154.63 1084.319,177.562 			"/>
									</g>
									<g>
										<polygon style="fill:#1E3D69;" points="993.016,141.023 1019.466,129.331 1031.691,132.87 1004.561,144.9 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="1107.635,149.233 1103.587,154.32 1038.728,134.257 1000.031,150.265 1004.22,144.871 
											1032.373,133.112 1020.145,129.573 992.929,141.023 1013.428,120.486 			"/>
									</g>
								</g>
								<g class="content show" id="4">
									<g>
										<g>
											<polygon style="fill:#457AB6;" points="991.194,334.46 992.668,308.121 1019.226,271.486 1013.008,298.5 				"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="991.194,334.46 874.894,295.008 874.697,269.09 992.813,307.295 				"/>
										</g>
										<g>
											<polygon style="fill:#213F62;" points="875.875,269.261 901.385,242.503 912.602,246.216 917.864,239.998 1018.131,271.848 
												992.571,307.173 				"/>
											<path style="fill:#0B2443;" d="M992.84,307.998l-0.487-0.157l-117.758-38.256l26.588-27.887l11.188,3.702l5.258-6.213
												l0.446,0.143l101.193,32.144L992.84,307.998z M877.153,268.938L992.3,306.349l24.693-34.124l-98.897-31.413l-5.266,6.22
												l-11.243-3.722L877.153,268.938z"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="931.874,298.344 915.244,292.532 915.244,285.493 931.874,291.303 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="957.262,306.737 940.632,300.926 940.632,293.886 957.262,299.699 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="982.781,315.334 915.356,292.733 915.37,291.464 982.874,314.472 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="917.47,290.662 874.7,276.874 874.7,275.563 917.571,289.698 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="893.464,285.615 890.764,284.814 890.764,277.648 893.464,278.449 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="900.497,287.782 897.796,286.981 897.796,279.815 900.497,280.618 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="892.492,298.613 889.791,297.811 889.791,290.646 892.492,291.447 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="899.146,300.827 896.446,300.026 896.446,292.86 899.146,293.662 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="982.845,315.375 966.164,309.711 966.702,303.274 983.557,308.68 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="931.874,311.198 915.244,305.385 915.244,298.347 931.874,304.158 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="957.262,319.991 940.632,314.181 940.632,307.14 957.262,312.953 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="982.867,322.114 915.363,299.392 915.377,298.126 982.842,321.252 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="982.867,328.865 915.363,305.462 915.377,304.194 982.842,327.999 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="982.845,328.629 966.164,322.966 966.702,316.528 982.874,321.934 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#1E3D69;" points="877.154,268.962 907.307,256.211 918.864,257.392 930.894,256.211 1009.662,281.966 
											992.457,306.38 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="1016.942,272.363 1009.144,282.939 929.641,256.816 917.903,257.975 906.641,256.816 
											877.154,268.962 901.66,243.244 912.868,247.048 918.154,240.812 			"/>
									</g>
								</g>
								<g class="content show" id="5">
									<g>
										<polygon style="fill:#7DC4DD;" points="1118.54,135.054 1118.845,142.073 1165.451,157.421 1170.51,150.629 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1135.562,144.126 1118.356,138.873 1118.18,136.963 1135.547,142.32 			"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="1106.358,153.905 1104.76,153.905 1104.76,146.942 1106.358,147.224 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="1118.845,142.073 1106.358,154.772 1106.358,147.302 1118.54,134.819 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1160.327,175.736 1105.859,154.085 1118.785,140.657 1169.607,156.73 			"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="993.963,282.964 990.746,334.335 1045.704,352.795 1050.775,301.921 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1013.452,296.841 996.543,290.765 996.693,286.613 1013.402,292.486 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1043.881,307.691 1026.972,301.615 1027.122,297.462 1043.831,303.339 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="1050.766,301.899 1045.708,352.795 1165.1,197.855 1172,149.88 1162.142,163.293 
											1161.916,172.026 1065.402,293.037 1066.176,285.059 			"/>
									</g>
									<g>
										<g>
											<polygon style="fill:#0D2745;" points="1141.541,223.594 1141.414,226.056 1070.555,315.839 1070.755,313.307 				"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="1141.541,217.199 1141.4,219.635 1071.319,308.132 1071.109,307.964 1071.278,305.913 
																"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="1144.271,206.739 1144.145,209.203 1071.157,301.346 1071.314,299.423 1071.356,298.842 
																"/>
										</g>
										<g>
											<polygon style="fill:#0D2745;" points="1155.912,186.147 1155.785,188.597 1071.365,295.113 1071.562,292.567 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1104.762,153.051 1106.655,152.993 1010.818,269.977 1008.685,270.18 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1161.927,172.017 1163.027,172.926 1066.758,293.698 1065.665,292.602 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="1010.171,273.765 1000.827,283.936 996.533,282.364 1009.604,267.573 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="1009.615,270.091 1106.563,152.973 1135.222,150.142 1134.708,150.768 1038.275,267.968 
														"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="1009.615,270.091 1038.244,267.938 1059.523,287.23 			"/>
									</g>
									<g>
										<polygon style="fill:#213F62;" points="1161.971,172.101 1065.732,292.758 1038.407,267.907 1135.355,150.083 			"/>
									</g>
									<g>
										<g>
											<polygon style="fill:#0D2745;" points="1118.087,133.4 1172,149.853 1160.045,166.248 1158.693,165.601 1169.518,150.494 
												1118.54,135.054 1106.358,147.302 1104.76,146.942 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="1161.916,172.026 1160.28,170.699 1160.54,163.064 1162.138,163.345 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1166.967,154.074 1148.911,148.428 1148.88,146.489 1168.54,152.62 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1021.678,296.204 1018.631,295.188 1018.519,291.43 1021.566,292.448 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1144.172,145.903 1141.124,144.886 1141.013,141.128 1144.059,142.146 			"/>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="1158.574,206.415 1155.912,209.777 1155.774,202.361 1158.87,198.853 			"/>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="1057.986,336.94 1054.766,341.041 1055.726,332.126 1058.679,329.177 			"/>
									</g>
									<g>
										<polygon style="fill:#0D2745;" points="1050.481,301.346 998.307,283.636 1010.02,270.088 1062.158,287.976 			"/>
									</g>
									<g>
										<g>
											<polygon style="fill:#0D2745;" points="1050.993,302.726 993.943,283.016 1008.346,266.998 1009.696,267.645 996.426,282.375 
												1050.54,301.073 1063.125,286.325 1064.723,286.688 				"/>
										</g>
									</g>
								</g>
								<g class="content show" id="6">
									<g>
										<g>
											<g>
												<polygon style="fill:#457AB6;" points="326.737,248.623 315.702,245.082 316.225,210.761 326.737,213.831 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="383.214,276.815 317.471,254.753 317.994,220.432 384.47,240.496 					"/>
											</g>
											<g>
												<polygon style="fill:#375F91;" points="490.119,169.392 383.214,276.815 384.47,241.204 496.146,136.302 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="384.344,240.496 319.24,220.328 326.67,213.606 317.471,210.423 426.545,118.361 
													434.93,120.779 441.229,115.708 491.172,130.478 487.634,134.016 496.146,136.302 					"/>
												<path style="fill:#0B2443;" d="M384.525,241.288l-66.669-20.654l7.464-6.754l-9.245-3.199l110.305-93.1l8.394,2.422
													l6.302-5.074l51.428,15.209l-3.513,3.514l8.56,2.298L384.525,241.288z M320.625,220.023l63.539,19.683L494.74,136.651
													l-8.462-2.273l3.564-3.562l-48.459-14.331l-6.297,5.07l-8.374-2.414l-107.843,91.022l9.154,3.167L320.625,220.023z"/>
											</g>
										</g>
										<g>
											<g>
												<polygon style="fill:#7DC4DD;" points="453.211,157.865 412.808,197.183 385.677,187.536 428.043,150.41 					"/>
											</g>
											<g>
												
													<rect x="382.434" y="170.027" transform="matrix(0.7472 -0.6646 0.6646 0.7472 -9.3365 315.9106)" style="fill:#FFFFFF;" width="56.309" height="0.402"/>
											</g>
											<g>
												<polygon style="fill:#FFFFFF;" points="394.574,190.799 394.303,190.504 435.616,152.547 435.886,152.841 					"/>
											</g>
											<g>
												
													<rect x="391.957" y="173.024" transform="matrix(0.724 -0.6898 0.6898 0.724 -3.6003 337.4511)" style="fill:#FFFFFF;" width="55.869" height="0.401"/>
											</g>
											<g>
												
													<rect x="397.748" y="174.937" transform="matrix(0.714 -0.7002 0.7002 0.714 -0.8767 348.128)" style="fill:#FFFFFF;" width="55.819" height="0.401"/>
											</g>
											<g>
												<polygon style="fill:#FFFFFF;" points="410.717,196.53 410.432,196.244 450.11,156.848 450.393,157.134 					"/>
											</g>
											<g>
												
													<rect x="403.492" y="174.155" transform="matrix(0.3477 -0.9376 0.9376 0.3477 86.8004 501.3158)" style="fill:#FFFFFF;" width="0.401" height="28.241"/>
											</g>
											<g>
												
													<rect x="408.065" y="170.178" transform="matrix(0.3476 -0.9376 0.9376 0.3476 93.7885 502.8665)" style="fill:#FFFFFF;" width="0.4" height="27.713"/>
											</g>
											<g>
												
													<rect x="413.16" y="165.592" transform="matrix(0.3476 -0.9376 0.9376 0.3476 101.6193 504.5076)" style="fill:#FFFFFF;" width="0.4" height="27.272"/>
											</g>
											<g>
												<polygon style="fill:#FFFFFF;" points="430.858,179.739 405.752,170.068 405.896,169.693 431.001,179.364 					"/>
											</g>
											<g>
												
													<rect x="422.781" y="157.134" transform="matrix(0.3484 -0.9374 0.9374 0.3484 115.82 507.5724)" style="fill:#FFFFFF;" width="0.401" height="26.698"/>
											</g>
											<g>
												
													<rect x="427.51" y="152.922" transform="matrix(0.3445 -0.9388 0.9388 0.3445 124.4558 510.394)" style="fill:#FFFFFF;" width="0.402" height="26.309"/>
											</g>
											<g>
												
													<rect x="431.874" y="149.11" transform="matrix(0.3483 -0.9374 0.9374 0.3483 129.647 510.6535)" style="fill:#FFFFFF;" width="0.401" height="25.954"/>
											</g>
											<g>
												<polygon style="fill:#FFFFFF;" points="448.921,162.08 424.72,153.452 424.854,153.075 449.056,161.704 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="331.162,234.497 325.224,232.698 325.486,226.561 331.427,228.362 				"/>
											<path style="fill:#0B2443;" d="M331.257,234.631l-0.123-0.038l-6.014-1.82l0.272-6.343l0.123,0.038l6.015,1.821L331.257,234.631
												z M325.327,232.626l5.74,1.737l0.255-5.927l-5.742-1.739L325.327,232.626z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="340.723,237.758 334.784,235.959 335.047,229.822 340.986,231.62 				"/>
											<path style="fill:#0B2443;" d="M340.818,237.891l-0.123-0.038l-6.014-1.82l0.273-6.343l0.123,0.038l6.012,1.82L340.818,237.891z
												 M334.887,235.885l5.74,1.738l0.253-5.929l-5.739-1.738L334.887,235.885z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="351.004,241.398 345.062,239.601 345.325,233.462 351.265,235.263 				"/>
											<path style="fill:#0B2443;" d="M351.098,241.532l-0.123-0.038l-6.015-1.819l0.272-6.345l0.123,0.038l6.015,1.821
												L351.098,241.532z M345.167,239.527l5.742,1.737l0.253-5.929l-5.742-1.738L345.167,239.527z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="362,244.994 356.06,243.195 356.325,237.059 362.264,238.859 				"/>
											<path style="fill:#0B2443;" d="M362.095,245.127l-0.123-0.038l-6.015-1.82l0.273-6.343l0.123,0.038l6.014,1.821L362.095,245.127
												z M356.164,243.123l5.742,1.738l0.253-5.927l-5.74-1.739L356.164,243.123z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="373.054,248.184 367.114,246.386 367.376,240.249 373.316,242.049 				"/>
											<path style="fill:#0B2443;" d="M373.149,248.317l-0.123-0.038l-6.015-1.82l0.272-6.343l0.123,0.038l6.015,1.821L373.149,248.317
												z M367.217,246.312l5.742,1.738l0.253-5.927l-5.742-1.738L367.217,246.312z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="331.162,244.468 325.224,242.669 325.486,236.532 331.427,238.333 				"/>
											<path style="fill:#0B2443;" d="M331.257,244.601l-0.123-0.038l-6.014-1.82l0.272-6.343l0.123,0.038l6.015,1.821L331.257,244.601
												z M325.327,242.595l5.74,1.738l0.255-5.929l-5.742-1.738L325.327,242.595z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="340.723,247.727 334.784,245.93 335.047,239.793 340.986,241.591 				"/>
											<path style="fill:#0B2443;" d="M340.818,247.862l-0.123-0.038l-6.014-1.82l0.273-6.343l0.123,0.038l6.012,1.82L340.818,247.862z
												 M334.887,245.856l5.74,1.738l0.253-5.93l-5.739-1.738L334.887,245.856z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="351.004,251.369 345.062,249.571 345.325,243.435 351.265,245.233 				"/>
											<path style="fill:#0B2443;" d="M351.098,251.502l-0.123-0.038l-6.015-1.819l0.272-6.343l0.123,0.038l6.015,1.82L351.098,251.502
												z M345.167,249.498l5.742,1.737l0.253-5.927l-5.742-1.738L345.167,249.498z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="362,254.965 356.06,253.168 356.325,247.03 362.264,248.83 				"/>
											<path style="fill:#0B2443;" d="M362.095,255.098l-0.123-0.038l-6.015-1.819l0.273-6.345l0.123,0.038l6.014,1.821
												L362.095,255.098z M356.164,253.094l5.742,1.737l0.253-5.927l-5.74-1.739L356.164,253.094z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="373.054,258.155 367.114,256.356 367.376,250.22 373.316,252.02 				"/>
											<path style="fill:#0B2443;" d="M373.149,258.288l-0.123-0.038l-6.015-1.82l0.272-6.343l0.123,0.038l6.015,1.821L373.149,258.288
												z M367.217,256.283l5.742,1.738l0.253-5.929l-5.742-1.738L367.217,256.283z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="331.162,253.833 325.224,252.035 325.486,245.897 331.427,247.698 				"/>
											<path style="fill:#0B2443;" d="M331.257,253.967l-0.123-0.038l-6.014-1.819l0.272-6.345l0.123,0.038l6.015,1.821
												L331.257,253.967z M325.327,251.963l5.74,1.737l0.255-5.927l-5.742-1.739L325.327,251.963z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="340.723,257.094 334.784,255.295 335.047,249.158 340.986,250.958 				"/>
											<path style="fill:#0B2443;" d="M340.818,257.227l-0.123-0.038l-6.014-1.82l0.273-6.343l0.123,0.038l6.012,1.821L340.818,257.227
												z M334.887,255.221l5.74,1.738l0.253-5.929l-5.739-1.738L334.887,255.221z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="351.004,260.735 345.062,258.936 345.325,252.8 351.265,254.6 				"/>
											<path style="fill:#0B2443;" d="M351.098,260.87l-0.123-0.038l-6.015-1.82l0.272-6.343l0.123,0.038l6.015,1.821L351.098,260.87z
												 M345.167,258.864l5.742,1.738l0.253-5.929l-5.742-1.739L345.167,258.864z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="362,264.332 356.06,262.533 356.325,256.396 362.264,258.195 				"/>
											<path style="fill:#0B2443;" d="M362.095,264.466l-0.123-0.038l-6.015-1.82l0.273-6.343l0.123,0.038l6.014,1.82L362.095,264.466z
												 M356.164,262.461l5.742,1.738l0.253-5.929l-5.74-1.738L356.164,262.461z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="373.054,267.52 367.114,265.723 367.376,259.585 373.316,261.385 				"/>
											<path style="fill:#0B2443;" d="M373.149,267.655l-0.123-0.038l-6.015-1.819l0.272-6.345l0.123,0.038l6.015,1.821
												L373.149,267.655z M367.217,265.649l5.742,1.737l0.253-5.927l-5.742-1.739L367.217,265.649z"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="325.327,232.626 331.068,234.363 331.322,228.435 325.58,226.696 			"/>
										<polygon style="fill:#FFFFFF;" points="334.887,235.885 340.628,237.623 340.883,231.695 335.141,229.957 			"/>
										<polygon style="fill:#FFFFFF;" points="345.167,239.526 350.907,241.264 351.162,235.336 345.42,233.597 			"/>
										<polygon style="fill:#FFFFFF;" points="356.164,243.123 361.904,244.86 362.16,238.933 356.417,237.193 			"/>
										<polygon style="fill:#FFFFFF;" points="367.471,240.38 367.217,246.309 372.958,248.047 373.212,242.118 			"/>
										<polygon style="fill:#FFFFFF;" points="325.327,242.656 331.068,244.393 331.322,238.466 325.58,236.726 			"/>
										<polygon style="fill:#FFFFFF;" points="334.887,245.916 340.628,247.654 340.883,241.725 335.141,239.987 			"/>
										<polygon style="fill:#FFFFFF;" points="345.167,249.557 350.907,251.294 351.162,245.367 345.42,243.627 			"/>
										<polygon style="fill:#FFFFFF;" points="356.164,253.154 361.904,254.89 362.16,248.963 356.417,247.223 			"/>
										<polygon style="fill:#FFFFFF;" points="367.234,255.889 371.776,251.651 367.471,250.347 			"/>
										<polygon style="fill:#FFFFFF;" points="325.327,252.021 331.068,253.759 331.322,247.831 325.58,246.093 			"/>
										<polygon style="fill:#FFFFFF;" points="334.887,255.282 340.628,257.019 340.883,251.091 335.141,249.353 			"/>
										<polygon style="fill:#FFFFFF;" points="345.167,258.922 350.907,260.66 351.162,254.732 345.42,252.994 			"/>
										<polygon style="fill:#FFFFFF;" points="356.164,262.461 359.212,263.373 362.054,260.723 362.16,258.27 356.417,256.53 			"/>
									</g>
								</g>
								<g class="content show" id="7">
									<g>
										<g>
											<g>
												<polygon style="fill:#457AB6;" points="552.533,188.02 456.579,286.569 451.095,231.608 556.298,130.581 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="456.933,286.332 393.117,259.327 387.848,210.825 451.449,231.372 					"/>
											</g>
											<g>
												
													<rect x="442.952" y="195.4" transform="matrix(0.9056 -0.4241 0.4241 0.9056 -49.031 208.1649)" style="fill:#375F91;" width="0.402" height="37.673"/>
											</g>
											<g>
												
													<rect x="388.344" y="201.666" transform="matrix(0.8959 -0.4442 0.4442 0.8959 -47.3448 201.662)" style="fill:#375F91;" width="36.674" height="0.401"/>
											</g>
											<g>
												<g>
													<polygon style="fill:#7DC4DD;" points="473.279,233.591 468.612,238.61 468.31,227.412 472.977,222.392 						"/>
													<path style="fill:#0B2443;" d="M468.519,238.858l-0.007-0.245l-0.304-11.239l0.028-0.03l4.835-5.2l0.006,0.245l0.303,11.24
														l-0.028,0.03L468.519,238.858z M468.411,227.45l0.294,10.912l4.471-4.809l-0.294-10.913L468.411,227.45z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="486.878,219.992 482.211,225.01 481.909,213.812 486.574,208.792 						"/>
													<path style="fill:#0B2443;" d="M482.117,225.258l-0.007-0.245l-0.304-11.239l0.028-0.03l4.833-5.2l0.313,11.485l-0.028,0.03
														L482.117,225.258z M482.01,213.851l0.294,10.912l4.472-4.809l-0.296-10.913L482.01,213.851z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="499.411,207.459 494.743,212.479 494.442,201.28 499.108,196.261 						"/>
													<path style="fill:#0B2443;" d="M494.648,212.727l-0.006-0.245l-0.303-11.24l4.86-5.228l0.007,0.245l0.304,11.239
														L494.648,212.727z M494.544,201.318l0.293,10.913l4.472-4.811l-0.294-10.912L494.544,201.318z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="512.099,194.769 507.434,199.79 507.131,188.59 511.799,183.572 						"/>
													<path style="fill:#0B2443;" d="M507.341,200.039l-0.007-0.245l-0.304-11.24l4.863-5.228l0.006,0.245l0.303,11.239
														L507.341,200.039z M507.232,188.628l0.294,10.913l4.471-4.809l-0.294-10.912L507.232,188.628z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="525.167,181.812 520.497,186.832 520.349,175.138 525.014,170.119 						"/>
													<path style="fill:#0B2443;" d="M520.401,187.084l-0.153-11.985l4.864-5.231l0.156,11.983L520.401,187.084z M520.449,175.177
														l0.146,11.405l4.471-4.808l-0.147-11.403L520.449,175.177z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="537.214,169.765 532.545,174.784 532.536,162.975 537.202,157.955 						"/>
													<path style="fill:#0B2443;" d="M532.444,175.039l-0.009-12.105l4.866-5.235l0.011,12.106L532.444,175.039z M532.637,163.013
														l0.008,11.516l4.468-4.804l-0.011-11.518L532.637,163.013z"/>
												</g>
											</g>
											<g>
												<g>
													<polygon style="fill:#7DC4DD;" points="473.279,253.89 468.612,258.908 468.31,247.71 472.977,242.69 						"/>
													<path style="fill:#0B2443;" d="M468.519,259.156l-0.007-0.245l-0.304-11.239l0.028-0.03l4.835-5.2l0.006,0.245l0.303,11.24
														l-0.028,0.03L468.519,259.156z M468.411,247.749l0.294,10.912l4.471-4.809l-0.294-10.913L468.411,247.749z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="486.878,240.288 482.211,245.307 481.909,234.109 486.574,229.089 						"/>
													<path style="fill:#0B2443;" d="M482.117,245.555l-0.007-0.245l-0.304-11.239l0.028-0.028l4.833-5.2l0.313,11.485l-0.028,0.03
														L482.117,245.555z M482.01,234.149l0.294,10.912l4.472-4.809l-0.296-10.913L482.01,234.149z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="499.411,227.757 494.743,232.777 494.442,221.578 499.108,216.559 						"/>
													<path style="fill:#0B2443;" d="M494.648,233.025l-0.006-0.245l-0.303-11.24l0.028-0.03l4.832-5.198l0.007,0.245l0.304,11.239
														l-0.028,0.03L494.648,233.025z M494.544,221.616l0.293,10.913l4.472-4.811l-0.294-10.912L494.544,221.616z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="512.099,215.068 507.434,220.088 507.131,208.889 511.799,203.87 						"/>
													<path style="fill:#0B2443;" d="M507.341,220.335l-0.007-0.245l-0.304-11.24l0.028-0.03l4.835-5.198l0.006,0.245l0.303,11.239
														l-0.028,0.028L507.341,220.335z M507.232,208.927l0.294,10.913l4.471-4.811l-0.294-10.912L507.232,208.927z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="525.167,202.111 520.497,207.131 520.349,195.436 525.014,190.417 						"/>
													<path style="fill:#0B2443;" d="M520.401,207.383l-0.153-11.985l4.864-5.231l0.156,11.983L520.401,207.383z M520.449,195.476
														l0.146,11.405l4.471-4.808l-0.147-11.403L520.449,195.476z"/>
												</g>
												<g>
													<polygon style="fill:#7DC4DD;" points="537.214,190.064 532.545,195.082 532.536,183.271 537.202,178.253 						"/>
													<path style="fill:#0B2443;" d="M532.444,195.337l-0.009-12.105l4.866-5.235l0.011,12.106L532.444,195.337z M532.637,183.311
														l0.008,11.516l4.468-4.804l-0.011-11.518L532.637,183.311z"/>
												</g>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="389.043,210.413 451.155,231.287 555.167,130.953 494.63,115.708 476.24,132.4 
													484.651,135.252 422.96,192.806 413.355,189.891 					"/>
												<path style="fill:#0B2443;" d="M451.334,232.09l-63.699-21.408l25.558-21.572l9.583,2.908l60.535-56.478l-8.43-2.857
													l19.552-17.746l62.128,15.645L451.334,232.09z M390.449,210.145l60.525,20.341l102.795-99.16l-58.946-14.845l-17.228,15.638
													l8.394,2.845l-62.845,58.632l-9.628-2.921L390.449,210.145z"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="485.851,135.154 513.769,121.588 435.176,197.17 423.134,193.712 					"/>
											</g>
											<g>
												<polygon style="fill:#264B6C;" points="423.134,193.712 390.268,209.988 451.095,230.422 435.176,197.17 					"/>
											</g>
										</g>
										<g>
											<g>
												<g>
													<path style="fill:#0D2745;" d="M469.694,147.859l-33.282,31.516c-0.041-0.614-2.604-1.125-2.676-1.735
														c-2.532-21.53,14.214-32.764,32.167-32.764C467.708,144.876,467.96,147.551,469.694,147.859z"/>
												</g>
												<g id="XMLID_1_">
													<g>
														<g>
															<path style="fill:#457AB6;" d="M469.378,142.865l0.314,5.207c-24.448-0.61-32.831,10.562-35.656,19.204
																c-1.108-3.509-0.831-8.752,5.613-14.939C450.542,141.885,469.378,142.865,469.378,142.865z"/>
														</g>
														<g>
															<path style="fill:#FFFFFF;" d="M436.879,171.946l-0.461,7.423c0,0-3.231-0.72-3.766-2.308c0,0-0.388-4.414,1.384-9.787
																C434.976,170.229,436.879,171.946,436.879,171.946z"/>
														</g>
													</g>
												</g>
											</g>
											<g>
												<g>
													<polygon style="fill:#0D2745;" points="484.393,199.631 500.535,195.082 507.27,190.417 512.099,183.982 510.062,175.572 
														485.759,197.906 						"/>
												</g>
												<g id="XMLID_2_">
													<g>
														<g>
															<path style="fill:#375F91;" d="M511.312,181.791c6.411,17.422-29.537,20.862-29.537,20.862v-6.015
																c4.954,0.113,12.511-1.897,12.511-1.897C505.708,190.764,510.094,186.009,511.312,181.791z"/>
														</g>
														<g>
															<path style="fill:#FFFFFF;" d="M508.198,170.681c0,0,4.869,5.01,3.114,11.11c-0.085-0.241-0.184-0.481-0.283-0.722
																c0,0-1.769-4.84-2.831-5.081V170.681z"/>
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
													<polygon style="fill:#7DC4DD;" points="506.776,269.897 481.742,260.343 481.742,248.698 507.27,257.933 						"/>
												</g>
												<g>
													<polygon style="fill:#457AB6;" points="506.776,269.897 507.27,257.841 558.293,205.258 558.293,216.242 						"/>
												</g>
											</g>
											<g>
												<polygon style="fill:#0D2745;" points="482.72,248.534 535.086,197.906 557.424,205.446 507.181,257.34 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="547.8,227.181 545.442,229.566 545.345,226.45 547.681,223.811 				"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="517.544,253.894 510.157,261.517 510.062,258.686 517.449,251.063 				"/>
											<path style="fill:#0B2443;" d="M510.065,261.756l-0.105-3.108l0.03-0.031l7.551-7.793l0.105,3.108l-0.03,0.031L510.065,261.756z
												 M510.164,258.724l0.086,2.553l7.191-7.422l-0.086-2.553L510.164,258.724z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="528.906,242.911 521.521,250.534 521.424,247.703 528.811,240.08 				"/>
											<path style="fill:#0B2443;" d="M521.427,250.773l-0.105-3.108l0.03-0.031l7.551-7.793l0.105,3.108l-0.03,0.031L521.427,250.773z
												 M521.526,247.741l0.086,2.553l7.193-7.422l-0.086-2.553L521.526,247.741z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="541.028,230.456 533.641,238.079 533.548,235.249 540.934,227.626 				"/>
											<path style="fill:#0B2443;" d="M533.549,238.318l-0.103-3.108l0.03-0.031l7.551-7.793l0.103,3.108l-0.03,0.031L533.549,238.318z
												 M533.65,235.287l0.085,2.553l7.193-7.422l-0.085-2.553L533.65,235.287z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="556.639,214.506 549.252,222.127 549.157,219.297 556.544,211.675 				"/>
											<path style="fill:#0B2443;" d="M549.16,222.366l-0.105-3.108l0.03-0.031l7.551-7.793l0.105,3.108l-0.03,0.031L549.16,222.366z
												 M549.259,219.336l0.086,2.553l7.193-7.422l-0.086-2.553L549.259,219.336z"/>
										</g>
									</g>
								</g>
								<g class="content show" id="8">
									<g>
										<polygon style="fill:#7DC4DD;" points="357.365,399.789 293.757,375.989 289.925,327.982 355.005,349.897 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="441.938,314.476 357.365,399.789 355.005,349.897 441.037,264.552 			"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="446.852,316.498 441.938,314.476 441.047,265.307 446.256,266.905 			"/>
									</g>
									<g>
										<polygon style="fill:#213F62;" points="291.281,327.724 354.969,349.111 440.763,264.575 445.482,266.106 480.341,231.603 
											463.121,226.176 456.517,232.073 409.458,217.45 			"/>
										<path style="fill:#0B2443;" d="M355.153,349.915l-65.214-21.9l0.862-0.802l118.475-110.554l0.389,0.122l46.68,14.505l6.605-5.898
											l18.713,5.896l-35.991,35.623l-4.72-1.533L355.153,349.915z M292.621,327.434l62.163,20.874l85.791-84.533l4.717,1.53
											l33.728-33.384l-15.73-4.956l-6.602,5.895l-47.049-14.619L292.621,327.434z"/>
									</g>
									<g>
										<g>
											<g>
												<polygon style="fill:#27486F;" points="327.545,342.621 317.601,351.527 317.601,342.246 327.384,333.432 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="317.677,357.307 298.119,336.511 317.601,342.137 					"/>
											</g>
											<g>
												<polygon style="fill:#27486F;" points="334.164,392.141 321.301,405.334 316.716,355.757 330.494,343.4 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="317.588,341.364 299.55,336.191 311.154,326.036 328.653,331.567 					"/>
												<path style="fill:#0B2443;" d="M317.765,342.144l-19.646-5.633l12.869-11.264l19.057,6.025L317.765,342.144z M300.981,335.872
													l16.43,4.71l9.848-8.72l-15.941-5.038L300.981,335.872z"/>
											</g>
											<g>
												<polygon style="fill:#0B2443;" points="317.58,355.784 298.295,349.999 298.295,345.918 317.601,351.286 					"/>
											</g>
											<g>
												<polygon style="fill:#0B2443;" points="330.494,343.4 317.573,355.865 317.584,351.353 327.558,342.42 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="321.344,405.422 302.679,398.701 298.295,349.999 317.58,355.873 					"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="312.153,402.205 309.9,401.329 306.179,352.365 308.41,353.098 				"/>
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="329.57,396.967 327.733,398.849 324.067,350.033 326.008,348.321 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="478.343,284.546 446.757,316.498 445.672,266.835 481.704,231.232 			"/>
									</g>
									<g>
										<g>
											<polygon style="fill:#7DC4DD;" points="364.242,361.434 359.1,366.964 358.769,354.629 363.908,349.1 				"/>
											<path style="fill:#0B2443;" d="M359.007,367.21l-0.006-0.245l-0.333-12.375l0.028-0.028l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.03L359.007,367.21z M358.871,354.668l0.324,12.048l4.946-5.32l-0.326-12.048L358.871,354.668z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="379.219,346.455 374.08,351.983 373.746,339.65 378.888,334.12 				"/>
											<path style="fill:#0B2443;" d="M373.988,352.23l-0.007-0.245l-0.335-12.375l0.028-0.03l5.309-5.708l0.006,0.245l0.333,12.375
												l-0.028,0.03L373.988,352.23z M373.848,339.688l0.326,12.048l4.944-5.32l-0.324-12.048L373.848,339.688z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="393.024,332.653 387.883,338.182 387.551,325.848 392.691,320.318 				"/>
											<path style="fill:#0B2443;" d="M387.79,338.428l-0.007-0.245l-0.334-12.375l0.028-0.03l5.306-5.708l0.007,0.245l0.334,12.375
												l-0.028,0.03L387.79,338.428z M387.653,325.886l0.326,12.048l4.945-5.32l-0.324-12.048L387.653,325.886z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="407,318.676 401.861,324.206 401.527,311.872 406.669,306.342 				"/>
											<path style="fill:#0B2443;" d="M401.768,324.452l-0.007-0.245l-0.335-12.375l0.028-0.03l5.309-5.708l0.006,0.245l0.333,12.375
												l-0.028,0.03L401.768,324.452z M401.629,311.91l0.326,12.048l4.944-5.32l-0.324-12.048L401.629,311.91z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="421.391,304.405 416.249,309.934 415.918,297.6 421.057,292.07 				"/>
											<path style="fill:#0B2443;" d="M416.156,310.18l-0.006-0.245l-0.333-12.375l0.028-0.03l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.03L416.156,310.18z M416.02,297.638l0.324,12.048l4.947-5.32l-0.326-12.048L416.02,297.638z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="434.659,291.136 429.517,296.666 429.186,284.331 434.325,278.802 				"/>
											<path style="fill:#0B2443;" d="M429.424,296.912l-0.006-0.245l-0.333-12.375l0.028-0.03l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.03L429.424,296.912z M429.288,284.369l0.324,12.048l4.946-5.32l-0.326-12.048L429.288,284.369z"/>
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#7DC4DD;" points="366.302,383.432 361.161,388.96 360.829,376.627 365.968,371.098 				"/>
											<path style="fill:#0B2443;" d="M361.066,389.208l-0.006-0.245l-0.333-12.375l0.028-0.028l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.028L361.066,389.208z M360.93,376.666l0.324,12.048l4.946-5.32l-0.326-12.048L360.93,376.666z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="381.278,368.453 376.139,373.981 375.805,361.648 380.947,356.118 				"/>
											<path style="fill:#0B2443;" d="M376.047,374.229l-0.007-0.245l-0.335-12.375l0.028-0.03l5.309-5.708l0.006,0.245l0.333,12.375
												l-0.028,0.03L376.047,374.229z M375.907,361.686l0.326,12.048l4.944-5.32l-0.324-12.048L375.907,361.686z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="395.083,354.651 389.943,360.18 389.61,347.846 394.751,342.316 				"/>
											<path style="fill:#0B2443;" d="M389.851,360.427l-0.007-0.245l-0.334-12.375l0.028-0.028l5.306-5.708l0.007,0.245l0.334,12.375
												l-0.028,0.03L389.851,360.427z M389.712,347.884l0.324,12.048l4.945-5.32l-0.326-12.048L389.712,347.884z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="409.059,340.675 403.92,346.204 403.586,333.87 408.728,328.34 				"/>
											<path style="fill:#0B2443;" d="M403.828,346.45l-0.007-0.245l-0.335-12.375l0.028-0.028l5.309-5.708l0.006,0.245l0.333,12.375
												l-0.028,0.03L403.828,346.45z M403.688,333.908l0.326,12.048l4.944-5.32l-0.324-12.048L403.688,333.908z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="423.45,326.284 418.308,331.813 417.977,319.479 423.116,313.949 				"/>
											<path style="fill:#0B2443;" d="M418.215,332.06l-0.006-0.245l-0.333-12.375l0.028-0.03l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.03L418.215,332.06z M418.079,319.517l0.324,12.048l4.946-5.32l-0.326-12.048L418.079,319.517z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="436.719,313.015 431.577,318.545 431.246,306.21 436.385,300.681 				"/>
											<path style="fill:#0B2443;" d="M431.483,318.791l-0.006-0.245l-0.333-12.375l0.028-0.03l5.306-5.708l0.007,0.245l0.335,12.375
												l-0.028,0.03L431.483,318.791z M431.347,306.249l0.324,12.048l4.946-5.32l-0.326-12.048L431.347,306.249z"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="466.407,256.208 460.404,262.275 461.083,258.648 467.087,252.58 			"/>
										<path style="fill:#0B2443;" d="M460.244,262.578l0.746-3.978l0.021-0.023l6.234-6.299l-0.746,3.977l-0.021,0.023L460.244,262.578
											z M461.176,258.696l-0.614,3.275l5.752-5.813l0.614-3.275L461.176,258.696z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="466.407,265.24 460.404,271.309 461.083,267.682 467.087,261.614 			"/>
										<path style="fill:#0B2443;" d="M460.244,271.611l0.746-3.978l0.021-0.023l6.234-6.299l-0.746,3.978l-0.021,0.023L460.244,271.611
											z M461.176,267.73l-0.614,3.276l5.752-5.813l0.614-3.276L461.176,267.73z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="466.407,274.277 460.404,280.344 461.083,276.718 467.087,270.651 			"/>
										<path style="fill:#0B2443;" d="M460.244,280.647l0.746-3.978l0.021-0.023l6.234-6.299l-0.746,3.978l-0.021,0.023L460.244,280.647
											z M461.176,276.767l-0.614,3.275l5.752-5.813l0.614-3.275L461.176,276.767z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="466.366,283.311 460.363,289.378 461.043,285.752 467.047,279.685 			"/>
										<path style="fill:#0B2443;" d="M460.204,289.681l0.746-3.978l0.021-0.023l6.234-6.299l-0.746,3.978l-0.021,0.023L460.204,289.681
											z M461.135,285.8l-0.614,3.276l5.752-5.814l0.614-3.275L461.135,285.8z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="466.407,292.345 460.404,298.412 461.083,294.785 467.087,288.717 			"/>
										<path style="fill:#0B2443;" d="M460.244,298.715l0.746-3.977l0.021-0.023l6.234-6.299l-0.746,3.977l-0.021,0.023L460.244,298.715
											z M461.176,294.834l-0.614,3.275l5.752-5.813l0.614-3.275L461.176,294.834z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="474.96,245.123 471.217,248.589 471.722,244.352 475.462,240.881 			"/>
										<path style="fill:#0B2443;" d="M471.085,248.847l0.541-4.545l3.968-3.68l-0.539,4.547L471.085,248.847z M471.817,244.4
											l-0.468,3.93l3.516-3.257l0.467-3.933L471.817,244.4z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="474.96,254.911 471.217,258.377 471.722,254.14 475.462,250.671 			"/>
										<path style="fill:#0B2443;" d="M471.085,258.635l0.541-4.545l3.968-3.68l-0.539,4.547L471.085,258.635z M471.817,254.188
											l-0.468,3.93l3.516-3.257l0.467-3.933L471.817,254.188z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="474.96,264.05 471.217,267.519 471.722,263.279 475.462,259.811 			"/>
										<path style="fill:#0B2443;" d="M471.085,267.776l0.541-4.547l3.968-3.677l-0.539,4.545L471.085,267.776z M471.817,263.327
											l-0.468,3.933l3.516-3.259l0.467-3.93L471.817,263.327z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="474.96,273.149 471.217,276.618 471.722,272.378 475.462,268.912 			"/>
										<path style="fill:#0B2443;" d="M471.085,276.877l0.541-4.547l3.968-3.677l-0.539,4.545L471.085,276.877z M471.817,272.427
											l-0.468,3.933l3.516-3.259l0.467-3.93L471.817,272.427z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="474.96,282.702 471.217,286.171 471.722,281.931 475.462,278.465 			"/>
										<path style="fill:#0B2443;" d="M471.085,286.43l0.541-4.547l3.968-3.677l-0.539,4.545L471.085,286.43z M471.817,281.98
											l-0.468,3.933l3.516-3.259l0.467-3.93L471.817,281.98z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="454.192,264.898 450.581,268.503 450.925,264.248 454.536,260.643 			"/>
										<path style="fill:#0B2443;" d="M450.46,268.767l0.369-4.563l0.025-0.025l3.803-3.799l-0.369,4.563l-0.025,0.025L450.46,268.767z
											 M451.023,264.293l-0.32,3.946l3.391-3.385l0.32-3.946L451.023,264.293z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="454.557,274.677 450.947,278.285 451.292,274.028 454.902,270.424 			"/>
										<path style="fill:#0B2443;" d="M450.825,278.55l0.371-4.566l0.025-0.027l3.804-3.799l-0.371,4.563l-0.025,0.027L450.825,278.55z
											 M451.388,274.073l-0.32,3.949l3.391-3.388l0.32-3.946L451.388,274.073z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="454.897,283.815 451.288,287.418 451.632,283.164 455.242,279.557 			"/>
										<path style="fill:#0B2443;" d="M451.166,287.683l0.369-4.563l0.025-0.027l3.804-3.802l-0.371,4.566l-0.025,0.027L451.166,287.683
											z M451.729,283.209l-0.318,3.946l3.391-3.385l0.32-3.949L451.729,283.209z"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="455.369,292.765 451.759,296.37 452.104,292.113 455.715,288.508 			"/>
										<path style="fill:#0B2443;" d="M451.637,296.633l0.371-4.566l0.025-0.025l3.804-3.799l-0.371,4.566l-0.025,0.027L451.637,296.633
											z M452.202,292.156l-0.32,3.949l3.391-3.385l0.32-3.949L452.202,292.156z"/>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="456.188,306.959 453.215,309.967 453.37,302.528 456.588,299.202 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="292.642,327.43 343.262,309.967 432.484,225.275 409.604,218.316 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="354.777,349.169 343.069,310.025 343.454,309.909 355.16,349.053 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="446.065,266.968 432.293,225.339 432.675,225.211 446.446,266.841 			"/>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="301.069,335.827 311.713,329.539 327.369,331.894 311.33,326.823 			"/>
									</g>
								</g>
								<g class="content show" id="9">
									<g>
										<g>
											<g>
												<polygon style="fill:#27486F;" points="629.923,57.686 623.417,65.924 623.58,44.749 629.794,35.97 					"/>
											</g>
											<g>
												<polygon style="fill:#27486F;" points="631.917,69.716 623.417,75.731 624.667,55.693 632.478,45.008 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="623.701,56.874 605.767,51.743 605.767,40.466 623.701,44.253 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="623.309,43.939 607.121,40.03 614.734,33.759 628.903,36.684 					"/>
												<path style="fill:#0B2443;" d="M623.587,44.729l-17.997-4.345l8.957-7.379l15.598,3.221L623.587,44.729z M608.654,39.678
													l14.378,3.472l4.629-6.005l-12.739-2.631L608.654,39.678z"/>
											</g>
											<g>
												<polygon style="fill:#0B2443;" points="624.371,55.519 602.504,50.079 605.767,47.632 623.701,51.938 					"/>
											</g>
											<g>
												<polygon style="fill:#0B2443;" points="632.478,45.008 623.701,57.012 623.701,51.971 629.923,44.211 					"/>
											</g>
										</g>
										<g>
											<g>
												<polygon style="fill:#375F91;" points="643.308,106.75 551.358,203.533 551.358,154.705 583,125.621 577.629,124.11 
													644.884,59.775 					"/>
											</g>
											<g>
												<polygon style="fill:#7DC4DD;" points="551.358,203.533 536.445,198.598 536.497,150.478 551.358,154.705 					"/>
											</g>
											<g>
												<polygon style="fill:#213F62;" points="510.33,118.425 557.383,130.849 537.317,150.367 550.992,154.544 581.934,125.865 
													577.139,124.345 643.156,60.408 587.726,46.727 					"/>
												<path style="fill:#0B2443;" d="M551.173,155.332l-15.196-4.639l20.027-19.482l-47.087-12.433l78.61-72.824l0.368,0.091
													l56.662,13.983l-66.083,64.001l4.825,1.53L551.173,155.332z M538.657,150.043l12.153,3.711l29.762-27.584l-4.767-1.512
													l65.952-63.873L587.925,47.5l-76.18,70.573l47.018,12.415L538.657,150.043z"/>
											</g>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="571.869,146.394 566.771,152.047 567.228,148.789 572.324,143.138 				"/>
											<path style="fill:#0B2443;" d="M566.626,152.357l0.505-3.612l0.021-0.023l5.316-5.895l-0.505,3.61l-0.021,0.023L566.626,152.357
												z M567.323,148.833l-0.408,2.903l4.857-5.387l0.408-2.901L567.323,148.833z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="571.869,154.292 566.771,159.944 567.228,156.686 572.324,151.035 				"/>
											<path style="fill:#0B2443;" d="M566.626,160.254l0.505-3.612l0.021-0.023l5.316-5.895l-0.505,3.61l-0.021,0.023L566.626,160.254
												z M567.323,156.732l-0.408,2.903l4.857-5.387l0.408-2.901L567.323,156.732z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="571.869,162.189 566.771,167.842 567.228,164.584 572.324,158.933 				"/>
											<path style="fill:#0B2443;" d="M566.626,168.152l0.505-3.612l0.021-0.023l5.316-5.895l-0.505,3.61l-0.021,0.023L566.626,168.152
												z M567.323,164.629l-0.408,2.903l4.857-5.387l0.408-2.901L567.323,164.629z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="571.869,170.087 566.771,175.739 567.228,172.481 572.324,166.83 				"/>
											<path style="fill:#0B2443;" d="M566.626,176.049l0.505-3.612l0.021-0.023l5.316-5.895l-0.505,3.61l-0.021,0.023L566.626,176.049
												z M567.323,172.527l-0.408,2.903l4.857-5.387l0.408-2.901L567.323,172.527z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="571.869,177.984 566.771,183.637 567.228,180.379 572.324,174.727 				"/>
											<path style="fill:#0B2443;" d="M566.626,183.947l0.505-3.612l0.021-0.023l5.316-5.895l-0.505,3.61l-0.021,0.023L566.626,183.947
												z M567.323,180.424l-0.408,2.903l4.857-5.387l0.408-2.901L567.323,180.424z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="579.027,137.126 575.839,140.365 576.112,136.571 579.302,133.327 				"/>
											<path style="fill:#0B2443;" d="M575.718,140.63l0.296-4.103l0.027-0.027l3.381-3.438l-0.297,4.107l-0.025,0.027L575.718,140.63z
												 M576.209,136.616l-0.252,3.484l2.972-3.019l0.253-3.489L576.209,136.616z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="579.027,145.76 575.839,149 576.112,145.206 579.302,141.962 				"/>
											<path style="fill:#0B2443;" d="M575.718,149.265l0.296-4.103l0.027-0.027l3.381-3.438l-0.297,4.107l-0.025,0.027
												L575.718,149.265z M576.209,145.249l-0.252,3.484l2.972-3.019l0.253-3.489L576.209,145.249z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="579.027,154.392 575.839,157.632 576.112,153.839 579.302,150.594 				"/>
											<path style="fill:#0B2443;" d="M575.718,157.897l0.296-4.103l0.027-0.027l3.381-3.438l-0.297,4.107l-0.025,0.027
												L575.718,157.897z M576.209,153.883l-0.252,3.484l2.972-3.019l0.253-3.489L576.209,153.883z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="579.027,163.027 575.839,166.267 576.112,162.472 579.302,159.228 				"/>
											<path style="fill:#0B2443;" d="M575.718,166.531l0.296-4.103l0.027-0.027l3.381-3.438l-0.297,4.107l-0.025,0.025
												L575.718,166.531z M576.209,162.516l-0.252,3.484l2.972-3.019l0.253-3.489L576.209,162.516z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="561.345,154.636 558.279,157.994 558.413,154.193 561.479,150.834 				"/>
											<path style="fill:#0B2443;" d="M558.17,158.263l0.146-4.11l0.025-0.028l3.25-3.56l-0.146,4.11l-0.026,0.028L558.17,158.263z
												 M558.513,154.234l-0.123,3.492l2.858-3.129l0.123-3.492L558.513,154.234z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="558.406,162.421 561.337,158.947 561.353,162.75 558.421,166.224 				"/>
											<path style="fill:#0B2443;" d="M558.321,166.497l-0.014-4.113l0.024-0.028l3.108-3.683l0.016,4.113l-0.024,0.028
												L558.321,166.497z M558.507,162.458l0.013,3.494l2.733-3.238l-0.014-3.494L558.507,162.458z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="558.406,170.651 561.337,167.175 561.353,170.98 558.421,174.454 				"/>
											<path style="fill:#0B2443;" d="M558.321,174.727l-0.014-4.111l0.024-0.028l3.108-3.684l0.016,4.113l-0.024,0.028
												L558.321,174.727z M558.507,170.688l0.013,3.493l2.733-3.238l-0.014-3.494L558.507,170.688z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="558.406,178.883 561.337,175.405 561.353,179.208 558.421,182.683 				"/>
											<path style="fill:#0B2443;" d="M558.321,182.956l-0.014-4.11l0.024-0.028l3.108-3.685l0.016,4.113l-0.024,0.028L558.321,182.956
												z M558.507,178.919l0.013,3.492l2.733-3.238l-0.014-3.494L558.507,178.919z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="558.406,187.111 561.337,183.635 561.353,187.44 558.421,190.914 				"/>
											<path style="fill:#0B2443;" d="M558.321,191.187l-0.014-4.111l0.024-0.028l3.108-3.684l0.016,4.113l-0.024,0.028
												L558.321,191.187z M558.507,187.148l0.013,3.493l2.733-3.238l-0.014-3.494L558.507,187.148z"/>
										</g>
										<g>
											<polygon style="fill:#FFFFFF;" points="579.929,173.536 577.57,175.92 577.755,170.186 580.31,167.548 				"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="592.727,130.748 588.551,135.427 588.067,125.183 592.242,120.51 				"/>
											<path style="fill:#0B2443;" d="M588.463,135.676l-0.498-10.528l0.028-0.031l4.338-4.857l0.498,10.524l-0.028,0.031
												L588.463,135.676z M588.17,125.22l0.47,9.957l3.985-4.464l-0.471-9.954L588.17,125.22z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="604.006,119.468 599.832,124.147 599.348,113.905 603.522,109.23 				"/>
											<path style="fill:#0B2443;" d="M599.743,124.398l-0.498-10.528l0.028-0.031l4.336-4.857l0.498,10.524L599.743,124.398z
												 M599.45,113.94l0.47,9.957l3.983-4.464l-0.47-9.954L599.45,113.94z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="615.488,107.989 611.312,112.668 610.828,102.424 615.004,97.75 				"/>
											<path style="fill:#0B2443;" d="M611.224,112.917l-0.498-10.528l0.028-0.031l4.339-4.857l0.498,10.524l-0.028,0.031
												L611.224,112.917z M610.931,102.46l0.47,9.957l3.986-4.464l-0.47-9.954L610.931,102.46z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="625.703,97.773 621.529,102.452 621.329,92.102 625.503,87.429 				"/>
											<path style="fill:#0B2443;" d="M621.433,102.71l-0.204-10.644l0.026-0.03l4.344-4.864l0.205,10.64l-0.027,0.03L621.433,102.71z
												 M621.431,92.14l0.194,10.054l3.978-4.458l-0.194-10.051L621.431,92.14z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="636.053,87.424 631.878,92.102 631.906,81.348 636.08,76.674 				"/>
											<path style="fill:#0B2443;" d="M631.777,92.367l0.028-11.058l0.025-0.028l4.349-4.871v0.265l-0.028,10.789l-0.025,0.028
												L631.777,92.367z M632.006,81.387l-0.027,10.452l3.973-4.453l0.028-10.448L632.006,81.387z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="592.727,149.434 588.551,154.113 588.067,143.871 592.242,139.196 				"/>
											<path style="fill:#0B2443;" d="M588.463,154.364l-0.498-10.528l0.028-0.031l4.338-4.857l0.498,10.524l-0.028,0.031
												L588.463,154.364z M588.17,143.906l0.47,9.957l3.985-4.464l-0.471-9.954L588.17,143.906z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="604.006,138.154 599.832,142.833 599.348,132.591 603.522,127.916 				"/>
											<path style="fill:#0B2443;" d="M599.743,143.084l-0.498-10.528l0.028-0.031l4.336-4.857l0.498,10.524L599.743,143.084z
												 M599.45,132.626l0.47,9.957l3.983-4.464l-0.47-9.954L599.45,132.626z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="615.488,126.675 611.312,131.354 610.828,121.111 615.004,116.437 				"/>
											<path style="fill:#0B2443;" d="M611.224,131.604l-0.498-10.528l0.028-0.031l4.339-4.857l0.498,10.524l-0.028,0.031
												L611.224,131.604z M610.931,121.147l0.47,9.957l3.986-4.464l-0.47-9.954L610.931,121.147z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="625.703,116.459 621.529,121.138 621.329,110.79 625.503,106.115 				"/>
											<path style="fill:#0B2443;" d="M621.433,121.396l-0.204-10.644l0.026-0.03l4.344-4.864l0.205,10.64l-0.027,0.03L621.433,121.396
												z M621.431,110.826l0.194,10.054l3.978-4.458l-0.194-10.051L621.431,110.826z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="636.053,106.111 631.878,110.79 631.906,100.035 636.08,95.36 				"/>
											<path style="fill:#0B2443;" d="M631.777,111.053l0.028-11.058l0.025-0.028l4.349-4.871v0.263l-0.028,10.789l-0.025,0.028
												L631.777,111.053z M632.006,100.073l-0.027,10.452l3.973-4.453l0.028-10.448L632.006,100.073z"/>
										</g>
										<g>
											<polygon style="fill:#457AB6;" points="535.112,124.378 599.518,61.275 587.896,47.632 511.881,118.013 				"/>
										</g>
										<g>
											<polyline style="fill:#264B6C;" points="641.714,60.804 587.849,47.69 599.473,61.333 				"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#457AB6;" points="608.713,39.671 615.375,35.985 627.949,37.075 614.963,34.482 			"/>
									</g>
								</g>
								<g class="content show" id="10">
									<g>
										<polygon style="fill:#375F91;" points="287.084,250.677 153.194,324.799 151.099,291.009 287.972,216.823 			"/>
									</g>
									<g>
										<polygon style="fill:#7DC4DD;" points="153.194,324.799 114.066,295.77 111.354,265.637 151.099,291.009 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="165.432,318.023 153.194,324.799 151.099,290.861 163.166,284.469 			"/>
									</g>
									<g>
										<g>
											<polygon style="fill:#7DC4DD;" points="181.711,304.773 173.27,309.777 172.713,296.901 181.154,291.897 				"/>
											<path style="fill:#0B2443;" d="M173.176,309.948l-0.007-0.167l-0.56-12.936l0.052-0.031l8.585-5.089l0.007,0.167l0.56,12.934
												l-0.052,0.03L173.176,309.948z M172.815,296.957l0.548,12.65l8.244-4.888l-0.548-12.647L172.815,296.957z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="200.344,294.291 191.905,299.295 191.395,286.369 199.836,281.366 				"/>
											<path style="fill:#0B2443;" d="M191.812,299.468l-0.007-0.168l-0.512-12.985l0.051-0.031l8.585-5.089l0.007,0.167l0.511,12.984
												L191.812,299.468z M191.497,286.424l0.501,12.698l8.244-4.887l-0.5-12.697L191.497,286.424z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="220.902,282.878 212.461,287.884 211.879,275.031 220.319,270.028 				"/>
											<path style="fill:#0B2443;" d="M212.367,288.055l-0.592-13.077l0.052-0.03l8.584-5.089l0.593,13.076l-0.052,0.03
												L212.367,288.055z M211.981,275.087l0.572,12.625l8.247-4.888l-0.573-12.623L211.981,275.087z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="238.357,273.527 229.917,278.531 229.411,265.604 237.851,260.601 				"/>
											<path style="fill:#0B2443;" d="M229.824,278.704l-0.007-0.168l-0.508-12.987l0.051-0.031l8.585-5.091l0.007,0.168l0.508,12.985
												L229.824,278.704z M229.512,265.659l0.497,12.699l8.246-4.887l-0.498-12.698L229.512,265.659z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="257.346,262.345 248.905,267.349 248.288,254.531 256.73,249.528 				"/>
											<path style="fill:#0B2443;" d="M248.813,267.52l-0.008-0.167l-0.62-12.878l0.052-0.031l8.585-5.089l0.008,0.167l0.62,12.876
												l-0.052,0.031L248.813,267.52z M248.391,254.586l0.606,12.591l8.247-4.888l-0.606-12.589L248.391,254.586z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="276.924,251.667 268.484,256.672 267.82,243.899 276.263,238.896 				"/>
											<path style="fill:#0B2443;" d="M268.392,256.843l-0.008-0.166l-0.667-12.833l0.052-0.03l8.585-5.089l0.008,0.166l0.665,12.831
												l-0.052,0.03L268.392,256.843z M267.924,243.954l0.651,12.545l8.247-4.888l-0.651-12.544L267.924,243.954z"/>
										</g>
									</g>
									<g>
										<g>
											<polygon style="fill:#7DC4DD;" points="179.19,281.789 170.684,286.684 170.296,273.799 178.802,268.906 				"/>
											<path style="fill:#0B2443;" d="M170.589,286.853l-0.004-0.167l-0.391-12.944l0.052-0.03l8.652-4.976l0.004,0.167l0.389,12.943
												l-0.052,0.03L170.589,286.853z M170.398,273.855l0.382,12.657l8.308-4.781l-0.381-12.656L170.398,273.855z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="197.962,271.551 189.455,276.445 189.117,263.515 197.623,258.622 				"/>
											<path style="fill:#0B2443;" d="M189.359,276.616l-0.004-0.168l-0.34-12.991l0.052-0.03l8.653-4.978l0.004,0.168l0.34,12.99
												l-0.052,0.03L189.359,276.616z M189.217,263.572l0.333,12.704l8.309-4.779l-0.333-12.702L189.217,263.572z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="218.666,260.411 210.161,265.304 209.748,252.447 218.254,247.556 				"/>
											<path style="fill:#0B2443;" d="M210.066,265.474l-0.42-13.083l0.052-0.03l8.652-4.975l0.006,0.167l0.415,12.915l-0.052,0.03
												L210.066,265.474z M209.85,252.504l0.406,12.63l8.309-4.779l-0.405-12.629L209.85,252.504z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="236.244,251.292 227.739,256.185 227.401,243.252 235.91,238.361 				"/>
											<path style="fill:#0B2443;" d="M227.643,256.356l-0.004-0.168l-0.338-12.992l0.052-0.03l8.655-4.976l0.004,0.168l0.335,12.991
												l-0.052,0.031L227.643,256.356z M227.503,243.309l0.331,12.705l8.308-4.778l-0.328-12.704L227.503,243.309z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="255.379,240.361 246.873,245.253 246.425,232.431 254.93,227.538 				"/>
											<path style="fill:#0B2443;" d="M246.778,245.425l-0.006-0.166l-0.45-12.883l0.052-0.031l8.65-4.976l0.006,0.166l0.45,12.883
												l-0.052,0.031L246.778,245.425z M246.527,232.487l0.44,12.596l8.309-4.78l-0.44-12.596L246.527,232.487z"/>
										</g>
										<g>
											<polygon style="fill:#7DC4DD;" points="275.097,229.943 266.591,234.837 266.095,222.057 274.601,217.164 				"/>
											<path style="fill:#0B2443;" d="M266.496,235.007l-0.006-0.166L265.993,222l0.052-0.03l8.649-4.976l0.006,0.166l0.497,12.84
												l-0.052,0.03L266.496,235.007z M266.197,222.113l0.487,12.554l8.309-4.781l-0.487-12.552L266.197,222.113z"/>
										</g>
									</g>
									<g>
										<polygon style="fill:#213F62;" points="109.507,263.833 225.071,203.583 223.187,201.465 253.783,183.576 298.268,210.878 
											151.168,290.193 			"/>
										<path style="fill:#0B2443;" d="M151.14,291.007l-43.038-27.232l1.081-0.562l114.764-59.835l-1.855-2.085l31.7-18.533
											l45.881,28.159L151.14,291.007z M110.914,263.893l40.282,25.488l145.664-78.541l-43.086-26.445l-29.494,17.244l1.913,2.15
											l-0.798,0.416L110.914,263.893z"/>
									</g>
									<g>
										<polygon style="fill:#FFFFFF;" points="134.353,310.822 125.207,304.017 124.037,280.727 133.183,287.2 			"/>
									</g>
									<g>
										<polyline style="fill:#457AB6;" points="111.029,263.794 143.528,261.62 261.706,199.669 296.875,210.881 253.922,184.249 
											224.274,201.571 226.199,203.784 			"/>
									</g>
									<g>
										<polygon style="fill:#264B6C;" points="110.918,263.86 143.441,261.855 150.989,289.276 			"/>
									</g>
									<g>
										<polygon style="fill:#375F91;" points="150.795,289.329 143.335,261.672 143.721,261.568 151.182,289.224 			"/>
									</g>
									<g>
										<path style="fill:none;stroke:#375F91;stroke-width:0.2835;stroke-miterlimit:10;" d="M261.706,199.669"/>
									</g>
									<g>
										<path style="fill:none;stroke:#375F91;stroke-width:0.2835;stroke-miterlimit:10;" d="M253.922,184.249"/>
									</g>
								</g>
								<g class="content show" id="11">
									<line style="fill:none;" x1="483.374" y1="198.775" x2="508.913" y2="175.572"/>
								</g>
								<polygon style="fill:#7DC4DD;" points="310.038,349.183 298.295,345.918 298.119,336.511 	"/>
							</g>
						    </svg>

						</div>
						<div class="center hide cls-second">
						
							<svg version="1.1" id="&#x56FE;&#x5C42;_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px"
		 y="0px" viewBox="0 0 800 420" style="enable-background:new 0 0 800 420;" xml:space="preserve">
	<g>
		<polygon style="fill:#978D78;" points="513.453,172.605 531.848,170.099 540.806,134.518 562.62,140.569 559.722,175.163 
			531.848,176.194 533.87,194.944 513.453,196.225 	"/>
		<polygon style="fill:#707172;" points="525.223,172.621 529.973,152.871 535.098,152.871 536.973,131.246 542.098,115.121 
			569.098,122.621 565.598,156.871 611.598,169.121 601.598,188.121 601.348,198.371 609.848,186.371 607.098,298.121 
			596.098,309.871 579.348,304.371 562.098,194.371 525.223,195.621 525.223,190.621 530.848,174.871 557.098,174.371 
			560.348,142.121 541.848,137.121 538.848,155.121 532.848,172.621 	"/>
		<polygon style="fill:#A0977F;" points="572.914,267.264 599.37,277.17 597.136,294.194 574.786,287.069 	"/>
		<polygon style="fill:#A0977F;" points="601.598,235.716 592.12,235.716 597.136,198.371 599.266,198.371 	"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="577.348" y1="287.57" x2="607.348" y2="297.57"/>
		<line style="fill:#A0977F;" x1="595.12" y1="235.716" x2="603.62" y2="235.716"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="565.098" y1="175.163" x2="531.848" y2="175.163"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="534.348" y1="195.663" x2="531.848" y2="175.163"/>
		<polygon style="fill:#978D78;" points="598.175,187.569 601.598,188.121 601.348,198.371 597.136,198.371 	"/>
		<polygon style="fill:#A0977F;" points="565.527,173.952 564.864,185.687 564.66,222.936 596.098,221.794 598.175,179.74 	"/>
		<g>
			<path style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" d="M628.098,196.352"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="530.848" y1="153.018" x2="538.265" y2="152.852"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="538.89" y1="152.852" x2="542.723" y2="116.185"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="542.723" y1="116.185" x2="568.973" y2="122.435"/>
			<line style="fill:#FFFFFF;" x1="564.431" y1="174.018" x2="568.973" y2="122.435"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="565.886" y1="157.5" x2="610.765" y2="169.518"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="598.175" y1="166.147" x2="596.098" y2="221.435"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="566.702" y1="222.435" x2="596.098" y2="221.435"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="610.348" y1="186.685" x2="607.348" y2="297.435"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="568.41" y1="236.685" x2="608.848" y2="236.685"/>
			
				<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="571.348" y1="255.185" x2="607.098" y2="265.435"/>
		</g>
		<polygon style="fill:#707172;" points="130.15,294.413 181.9,143.913 511.65,142.663 516.65,194.788 561.9,194.538 
			577.817,303.413 568.525,314.913 476.775,314.913 476.352,305.835 149.65,305.913 	"/>
		<polygon style="fill:#978D78;" points="199.453,164.903 157.106,293.363 479.378,290.001 479.878,300.751 564.66,300.436 
			552.12,212.903 513.12,214.236 506.786,162.236 	"/>
		<polygon style="fill:#EAE9E9;" points="510.973,174.277 495.598,174.277 496.507,182.438 497.938,195.277 514.62,195.277 	"/>
		<g>
			<g>
				<polygon style="fill:#807B7C;" points="279.293,170.842 272.322,202.291 274.491,205.35 282.488,205.565 289.58,174.244 
					287.415,171.059 			"/>
				<polygon style="fill:#FCF3F7;" points="279.168,170.838 272.326,202.166 280.573,202.387 287.79,171.069 			"/>
			</g>
			<g>
				
					<rect x="276.247" y="198.654" transform="matrix(0.2256 -0.9742 0.9742 0.2256 20.9538 424.4356)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="277.11" y="194.926" transform="matrix(0.2256 -0.9742 0.9742 0.2256 25.2538 422.3899)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="277.986" y="191.142" transform="matrix(0.2256 -0.9742 0.9742 0.2256 29.619 420.313)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="278.739" y="187.891" transform="matrix(0.2256 -0.9742 0.9742 0.2256 33.3699 418.5284)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="279.503" y="184.592" transform="matrix(0.2256 -0.9742 0.9742 0.2256 37.1751 416.7181)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="280.366" y="180.864" transform="matrix(0.2256 -0.9742 0.9742 0.2256 41.475 414.6723)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="281.242" y="177.08" transform="matrix(0.2256 -0.9742 0.9742 0.2256 45.8403 412.5955)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="281.995" y="173.828" transform="matrix(0.2256 -0.9742 0.9742 0.2256 49.5912 410.8109)" style="fill:#999899;" width="2.409" height="0.767"/>
			</g>
		</g>
		<g>
			<polygon style="fill:#2E3C4A;" points="230.203,151.029 235.37,159.779 252.62,159.779 247.37,151.029 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="231.439" y1="154.276" x2="249.328" y2="154.276"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="239.057" y1="151.029" x2="243.62" y2="159.779"/>
		</g>
		<polygon style="fill:#EAE9E9;" points="376.363,162.717 384.006,162.464 381.394,213.217 372.814,213.57 372.814,213.57 
			375.461,170.483 	"/>
		<polygon style="fill:#EAE9E9;" points="392.943,162.598 385.943,162.598 382.108,213.27 390.681,213.27 390.681,213.27 
			393.611,170.2 	"/>
		<g>
			
				<rect x="388.362" y="170.962" transform="matrix(0.9989 0.0459 -0.0459 0.9989 8.3422 -17.7115)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="387.079" y="185.596" transform="matrix(0.9962 0.0873 -0.0873 0.9962 17.8391 -33.1901)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="386.184" y="202.116" transform="matrix(0.9962 0.0873 -0.0873 0.9962 19.2786 -33.0487)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="384.775" y1="179.626" x2="393.033" y2="180.113"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="383.482" y1="195.12" x2="391.878" y2="195.615"/>
		</g>
		<g>
			
				<rect x="378.164" y="170.938" transform="matrix(0.9997 0.0232 -0.0232 0.9997 4.1024 -8.7444)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="377.215" y="185.597" transform="matrix(0.9979 0.0646 -0.0646 0.9979 12.8933 -24.0595)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="376.696" y="202.134" transform="matrix(0.9979 0.0646 -0.0646 0.9979 13.961 -23.9914)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="374.737" y1="179.707" x2="383.004" y2="180.006"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="373.797" y1="195.227" x2="382.202" y2="195.53"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#FCF3F7;" points="452.12,169.277 453.33,200.277 460.879,200.277 459.919,169.277 			"/>
				<polygon style="fill:#807B7C;" points="453.33,200.277 453.33,202.636 460.917,202.636 460.879,200.277 			"/>
			</g>
			<rect x="455.152" y="167.977" style="fill:#FCF3F7;" width="1.655" height="1.655"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#FCF3F7;" points="397.347,169.006 396.356,200.014 403.885,200.549 405.127,169.559 			"/>
				<polygon style="fill:#807B7C;" points="396.356,200.014 396.189,202.367 403.756,202.905 403.885,200.549 			"/>
			</g>
			
				<rect x="400.403" y="167.98" transform="matrix(0.9975 0.0709 -0.0709 0.9975 12.9829 -28.0318)" style="fill:#FCF3F7;" width="1.655" height="1.655"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#FCF3F7;" points="316.52,170.443 312.247,201.171 319.677,202.501 324.197,171.818 			"/>
				<polygon style="fill:#807B7C;" points="312.247,201.171 311.832,203.493 319.299,204.83 319.677,202.501 			"/>
			</g>
			
				<rect x="319.575" y="169.83" transform="matrix(0.9843 0.1763 -0.1763 0.9843 35.0968 -53.8028)" style="fill:#FCF3F7;" width="1.655" height="1.655"/>
		</g>
		<g>
			<polygon style="fill:#807B7C;" points="305.822,251.311 299.697,282.936 301.947,285.936 309.947,285.936 316.197,254.436 
				313.947,251.311 		"/>
			<polygon style="fill:#FCF3F7;" points="305.697,251.311 299.697,282.811 307.947,282.811 314.322,251.311 		"/>
		</g>
		<g>
			
				<rect x="303.532" y="279.163" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -29.9586 522.4245)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="304.295" y="275.413" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -25.6737 520.1703)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="305.07" y="271.607" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -21.3237 517.8819)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="305.735" y="268.336" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -17.5859 515.9155)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="306.41" y="265.018" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -13.7941 513.9207)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="307.173" y="261.269" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -9.5092 511.6665)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="307.948" y="257.463" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -5.1592 509.3781)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="308.613" y="254.192" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -1.4214 507.4117)" style="fill:#999899;" width="2.409" height="0.767"/>
		</g>
		<polygon style="fill:#707171;" points="409.901,232.197 409.635,263.322 412.682,275.822 445.62,275.822 445.62,262.155 
			445.62,230.989 	"/>
		<rect x="411.495" y="243.597" style="fill:#9F9581;" width="32.625" height="19.5"/>
		<polygon style="fill:#707171;" points="265.454,245.162 259.087,276.662 239.806,276.662 228.829,265.162 233.871,231.162 
			251.355,231.162 	"/>
		<polygon style="fill:#707172;" points="415.026,196.503 415.026,163.003 447.214,163.003 447.964,195.628 447.089,195.378 
			446.464,180.628 432.776,180.331 433.089,196.503 431.161,196.503 430.714,180.378 417.089,180.753 416.964,196.503 	"/>
		<polygon style="fill:#707172;" points="315.12,233.181 310.453,263.472 318.453,276.806 352.786,276.806 356.453,244.472 
			350.12,231.472 	"/>
		<polygon style="fill:#707172;" points="319.054,196.338 323.786,163.621 354.244,163.56 357.588,179.338 356.495,196.338 	"/>
		<polygon style="fill:#707172;" points="226.12,196.67 234.953,162.836 266.62,163.67 275.62,177.18 271.228,196.225 	"/>
		<polygon style="fill:#707172;" points="473.332,231.254 472.203,246.014 476.762,291 479.425,291 	"/>
		<g>
			<path style="fill:#2E3C4A;" d="M271.87,151.029"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="274.189" y1="154.276" x2="292.078" y2="154.276"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="281.807" y1="151.029" x2="286.37" y2="159.779"/>
		</g>
		<polygon style="fill:#707172;" points="236.796,232.962 246.488,245.968 236.155,291.276 221.905,291.276 	"/>
		<polygon style="fill:#707172;" points="186.874,232.311 196.566,245.317 182.577,290.625 168.327,290.625 	"/>
		<polygon style="fill:#707171;" points="154.568,231.819 474.286,229.444 474.286,243.444 173.453,246.778 	"/>
		<polygon style="fill:#707172;" points="283.684,232.962 295.337,245.427 287.349,291.276 273.099,291.276 	"/>
		<polygon style="fill:#040000;" points="181.08,246.778 178.744,246.778 172.453,238.93 174.789,238.93 	"/>
		<polygon style="fill:#EAE9E9;" points="296.297,241.501 289.351,240.636 279.282,290.445 287.789,291.504 287.789,291.504 
			296.02,249.127 	"/>
		<g>
			
				<rect x="290.503" y="249.361" transform="matrix(0.9856 0.1691 -0.1691 0.9856 46.6385 -45.6905)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="287.421" y="263.723" transform="matrix(0.9777 0.2098 -0.2098 0.9777 62.1024 -54.6313)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="284.491" y="280.006" transform="matrix(0.9777 0.2098 -0.2098 0.9777 65.4534 -53.654)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="286.087" y1="257.388" x2="294.222" y2="258.892"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="282.889" y1="272.604" x2="291.159" y2="274.133"/>
		</g>
		<polygon style="fill:#707172;" points="331.397,237.436 338.019,244.605 331.944,289.924 324.897,290.243 	"/>
		<polygon style="fill:#707172;" points="380.533,235.716 375.473,289.854 381.243,290.915 384.269,244.331 	"/>
		<polygon style="fill:#707172;" points="471.953,145.77 469.12,162.02 470.536,197.903 474.286,197.903 	"/>
		<polygon style="fill:#707172;" points="432.793,272.348 427.978,270.68 427.978,289.665 432.87,289.665 	"/>
		<polygon style="fill:#FFFFFF;" points="159.036,279.475 161.684,279.475 156.595,295.142 130.15,294.413 181.428,144.475 
			510.762,142.809 515.428,194.475 560.095,194.475 577.386,303.475 478.595,303.892 478.095,291.975 165.845,293.225 
			186.47,225.225 189.22,225.225 169.22,290.6 480.345,289.475 481.345,300.225 573.845,299.725 558.595,197.225 512.845,197.475 
			508.345,144.975 182.845,146.225 133.039,292.307 154.246,292.844 	"/>
		<polygon style="fill:#2C3848;" points="171.496,201.602 188.809,152.381 211.661,152.381 212.309,160.881 197.475,161.215 
			180.663,209.102 	"/>
		<polygon style="fill:#707172;" points="212.577,147.77 194.984,206.415 210.827,221.02 212.577,216.52 551.994,214.436 
			564.66,300.436 576.66,300.436 560.66,197.103 217.077,198.02 227.577,164.02 	"/>
		<g>
			<rect x="190.519" y="155.349" style="fill:#707172;" width="19.708" height="0.25"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="196.874,206.842 194.984,206.415 213.392,144.972 215.307,145.548 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="512.853,197.225 199.707,197.225 199.707,195.225 514.324,195.225 		"/>
		</g>
		<g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="173.469" y1="203.226" x2="190.632" y2="155.45"/>
			<g>
				<polygon style="fill:#707172;" points="182.36,204.267 175.452,197.715 175.624,197.534 182.454,204.011 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="184.747,197.644 177.792,191.043 177.964,190.862 184.919,197.462 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="186.816,191.573 179.914,185.018 180.086,184.837 186.988,191.391 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="188.789,185.957 181.93,179.451 182.102,179.269 188.904,185.721 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="191.129,179.289 184.275,172.786 184.446,172.605 191.22,179.031 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="193.245,173.263 186.446,166.811 186.618,166.63 193.336,173.004 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="195.275,167.483 188.493,161.049 188.665,160.867 195.374,167.232 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="197.301,162.555 190.519,156.122 190.691,155.94 197.401,162.305 			"/>
			</g>
		</g>
		<polyline style="fill:#2C3848;" points="167.217,244.022 150.405,291.909 141.238,284.409 158.55,235.188 	"/>
		<g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="142.97" y1="286.33" x2="161.14" y2="235.752"/>
			<g>
				<polygon style="fill:#707172;" points="152.507,285.574 145.598,279.023 145.77,278.841 152.6,285.318 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="154.893,278.951 147.938,272.351 148.11,272.169 155.065,278.77 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="156.963,272.88 150.06,266.326 150.232,266.144 157.135,272.699 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="158.935,267.264 152.076,260.759 152.248,260.577 159.05,267.029 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="161.276,260.597 154.421,254.094 154.593,253.912 161.367,260.338 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="163.392,254.57 156.592,248.119 156.764,247.937 163.482,254.312 			"/>
			</g>
			<g>
				<polygon style="fill:#707172;" points="165.421,248.79 158.639,242.357 158.811,242.175 165.52,248.54 			"/>
			</g>
		</g>
		<g>
			<polygon style="fill:#707172;" points="203.352,162.02 196.57,155.586 196.742,155.404 203.451,161.769 		"/>
		</g>
		<g>
			<polygon style="fill:#707172;" points="208.005,160.978 202.392,155.655 202.564,155.474 208.36,160.97 		"/>
		</g>
		<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="255.12" y1="145.77" x2="244.132" y2="196.225"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="299.786" y1="145.77" x2="288.798" y2="196.225"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="342.453" y1="145.77" x2="336.242" y2="196.225"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="385.786" y1="145.77" x2="383.079" y2="196.225"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="430.82" y1="144.972" x2="432.17" y2="196.225"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="469.12" y1="144.972" x2="471.703" y2="195.225"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="152.62" y1="231.834" x2="474.286" y2="229.444"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="473.258" y1="228.73" x2="479.378" y2="290.001"/>
		<polygon style="fill:#707172;" points="161.684,279.475 168.075,285.874 162.745,303.077 156.595,295.142 	"/>
		<polygon style="fill:#707172;" points="189.22,225.225 192.558,230.506 187.568,230.624 	"/>
		<polygon style="fill:#978D78;" points="175.452,300.238 175.452,305.916 180.79,305.906 	"/>
		<g>
			<polygon style="fill:#FFFFFF;" points="576.398,303.626 559.722,194.475 561.9,194.538 578.374,303.324 		"/>
		</g>
		<g>
			<polygon style="fill:#2E3C4A;" points="289.245,159.779 274.828,159.779 271.87,151.069 286.286,151.069 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="272.811" y1="153.841" x2="287.375" y2="153.841"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="279.453" y1="151.069" x2="282.453" y2="159.779"/>
		</g>
		<g>
			<polygon style="fill:#2E3C4A;" points="331.5,159.779 317.084,159.779 314.125,151.069 328.542,151.069 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="315.067" y1="153.841" x2="329.631" y2="153.841"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="321.709" y1="151.069" x2="324.709" y2="159.779"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#2E3C4A;" points="373.474,159.779 359.057,159.779 358.099,151.069 372.515,151.069 			"/>
				
					<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="358.353" y1="153.841" x2="372.917" y2="153.841"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M368.682,159.779"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M365.682,151.069"/>
			</g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="365.307" y1="151.069" x2="366.495" y2="159.779"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#2E3C4A;" points="417.474,159.779 403.057,159.779 402.099,151.069 416.515,151.069 			"/>
				
					<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="402.353" y1="153.841" x2="416.917" y2="153.841"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M412.682,159.779"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M409.682,151.069"/>
			</g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="409.307" y1="151.069" x2="410.495" y2="159.779"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#2E3C4A;" points="458.332,159.779 443.916,159.779 442.957,151.069 457.374,151.069 			"/>
				
					<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="443.211" y1="153.841" x2="457.776" y2="153.841"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M453.541,159.779"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M450.541,151.069"/>
			</g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="450.166" y1="151.069" x2="451.353" y2="159.779"/>
		</g>
		<g>
			<g>
				<polygon style="fill:#2E3C4A;" points="498.082,159.779 483.666,159.779 482.707,151.069 497.124,151.069 			"/>
				
					<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="482.961" y1="153.841" x2="497.526" y2="153.841"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M493.291,159.779"/>
				<path style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" d="M490.291,151.069"/>
			</g>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="489.916" y1="151.069" x2="491.103" y2="159.779"/>
		</g>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="486.765" y1="153.841" x2="487.599" y2="159.779"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="493.953" y1="153.841" x2="494.87" y2="159.779"/>
		<g>
			<polygon style="fill:#040000;" points="149.678,305.913 147.809,304.831 174.452,304.827 174.452,299.201 175.452,300.263 
				175.452,305.916 		"/>
		</g>
		<g>
			
				<rect x="197.854" y="261.377" transform="matrix(0.2378 -0.9713 0.9713 0.2378 -80.2875 422.4371)" style="fill:#FFFFFF;" width="62.353" height="2"/>
		</g>
		<g>
			
				<rect x="247.626" y="260.953" transform="matrix(0.1767 -0.9843 0.9843 0.1767 -28.4418 489.9029)" style="fill:#FFFFFF;" width="62.013" height="2"/>
		</g>
		<g>
			
				<rect x="297.68" y="260.428" transform="matrix(0.1435 -0.9896 0.9896 0.1435 22.7687 549.1656)" style="fill:#FFFFFF;" width="61.972" height="2"/>
		</g>
		<g>
			
				<rect x="347.111" y="260.263" transform="matrix(0.0776 -0.997 0.997 0.0776 88.2159 617.8784)" style="fill:#FFFFFF;" width="61.854" height="2"/>
		</g>
		<g>
			
				<rect x="397.274" y="260.468" transform="matrix(0.0049 -1 1 0.0049 164.4238 688.1646)" style="fill:#FFFFFF;" width="61.409" height="2"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="180.078,295.142 165.165,295.266 165.847,293.221 180.263,293.122 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="228.185,295.135 213.619,295.261 213.801,293.006 228.185,293.006 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="279.475,295.124 264.91,295.261 265.092,292.799 279.475,292.799 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="330.506,295.112 315.941,295.261 316.123,292.595 330.506,292.595 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="382.756,295.092 368.191,295.261 368.373,292.236 382.756,292.236 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="435.261,295.089 420.696,295.261 420.878,292.173 435.261,292.173 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="493.011,306.711 478.446,306.884 478.628,303.796 493.011,303.796 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="534.676,306.661 520.111,306.884 520.293,302.903 534.676,302.903 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="478.991,295.089 469.12,295.261 469.243,292.173 478.991,292.173 		"/>
		</g>
		<g>
			<polygon style="fill:#FFFFFF;" points="575.48,306.305 567.945,306.437 568.069,303.349 577.817,303.413 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="188.953,305.906 180.79,305.906 179.797,304.851 187.979,304.833 187.978,303.412 
				188.952,303.412 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="229.792,305.906 211.776,305.906 211.776,304.851 228.818,304.833 228.817,303.412 
				229.791,303.412 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="281.2,305.906 263.184,305.906 263.184,304.851 280.226,304.833 280.226,303.412 
				281.199,303.412 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="332.23,305.906 314.214,305.906 314.214,304.851 331.256,304.833 331.256,303.412 
				332.229,303.412 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="384.48,305.906 366.464,305.906 366.464,304.851 383.506,304.833 383.506,303.412 
				384.479,303.412 		"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="436.986,305.906 418.97,305.906 418.97,304.851 436.012,304.833 436.012,303.412 
				436.985,303.412 		"/>
		</g>
		<g>
			<g>
				<rect x="188.953" y="303.412" style="fill:#040000;" width="22.823" height="1.074"/>
			</g>
			<g>
				<rect x="211.776" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<g>
				<rect x="229.182" y="303.412" style="fill:#040000;" width="34.003" height="1.074"/>
			</g>
			<g>
				<rect x="263.185" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<g>
				<rect x="280.224" y="303.412" style="fill:#040000;" width="34.003" height="1.074"/>
			</g>
			<g>
				<rect x="314.227" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<g>
				<rect x="332.036" y="303.412" style="fill:#040000;" width="34.431" height="1.074"/>
			</g>
			<g>
				<rect x="366.467" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<g>
				<rect x="384.539" y="303.412" style="fill:#040000;" width="34.431" height="1.074"/>
			</g>
			<g>
				<rect x="418.97" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<g>
				<rect x="491.937" y="312.341" style="fill:#040000;" width="27.05" height="1.074"/>
			</g>
			<g>
				<rect x="518.987" y="312.341" style="fill:#040000;" width="1.074" height="1.498"/>
			</g>
		</g>
		<g>
			<polygon style="fill:#040000;" points="569.745,313.415 535.213,313.415 535.213,312.341 570.603,312.341 		"/>
		</g>
		<g>
			<rect x="436.012" y="303.412" style="fill:#040000;" width="34.334" height="1.074"/>
		</g>
		<g>
			<rect x="470.345" y="303.412" style="fill:#040000;" width="1.074" height="1.498"/>
		</g>
		<g>
			<rect x="491.937" y="312.341" style="fill:#040000;" width="1.074" height="1.498"/>
		</g>
		<g>
			<rect x="534.139" y="312.341" style="fill:#040000;" width="1.074" height="1.498"/>
		</g>
		<g>
			<rect x="470.345" y="304.803" style="fill:#040000;" width="5.973" height="1.074"/>
		</g>
		<g>
			<rect x="476.762" y="313.839" style="fill:#040000;" width="16.249" height="1.074"/>
		</g>
		<g>
			<rect x="518.987" y="313.839" style="fill:#040000;" width="16.249" height="1.074"/>
		</g>
		<g>
			<polygon style="fill:#040000;" points="476.762,314.482 476.318,304.803 477.392,304.803 477.911,314.482 		"/>
		</g>
		<rect x="188.953" y="304.492" style="fill:#978D78;" width="22.823" height="1.414"/>
		<rect x="229.792" y="304.492" style="fill:#978D78;" width="33.393" height="1.414"/>
		<rect x="281.2" y="304.492" style="fill:#978D78;" width="33.393" height="1.414"/>
		<rect x="332.23" y="304.492" style="fill:#978D78;" width="34.234" height="1.414"/>
		<rect x="384.171" y="304.492" style="fill:#978D78;" width="34.8" height="1.414"/>
		<rect x="436.986" y="304.492" style="fill:#978D78;" width="33.359" height="1.414"/>
		<rect x="492.986" y="313.415" style="fill:#978D78;" width="26.001" height="1.498"/>
		<polygon style="fill:#978D78;" points="568.525,314.913 535.236,314.913 535.236,313.415 569.745,313.415 	"/>
		<g>
			<polygon style="fill:#2E3946;" points="209.948,300.751 190.781,300.751 185.332,295.774 204.498,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="186.86" y1="297.17" x2="205.976" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="188.264" y1="295.774" x2="193.608" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="191.541" y1="295.774" x2="196.885" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="195.065" y1="295.774" x2="200.409" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="198.582" y1="295.774" x2="203.926" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="201.672" y1="295.774" x2="207.016" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="258.492,300.751 239.325,300.751 233.876,295.774 253.042,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="235.404" y1="297.17" x2="254.52" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="236.808" y1="295.774" x2="242.152" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="240.085" y1="295.774" x2="245.429" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="243.609" y1="295.774" x2="248.953" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="247.126" y1="295.774" x2="252.469" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="250.216" y1="295.774" x2="255.559" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="308.867,300.751 289.7,300.751 284.251,295.774 303.417,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="285.779" y1="297.17" x2="304.895" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="287.183" y1="295.774" x2="292.527" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="290.46" y1="295.774" x2="295.804" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="293.984" y1="295.774" x2="299.328" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="297.501" y1="295.774" x2="302.844" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="300.591" y1="295.774" x2="305.934" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="361.56,300.751 342.393,300.751 336.944,295.774 356.11,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="338.472" y1="297.17" x2="357.588" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="339.876" y1="295.774" x2="345.22" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="343.153" y1="295.774" x2="348.497" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="346.677" y1="295.774" x2="352.021" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="350.194" y1="295.774" x2="355.538" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="353.284" y1="295.774" x2="358.628" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="413.06,300.751 393.893,300.751 388.444,295.774 407.61,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="389.972" y1="297.17" x2="409.088" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="391.376" y1="295.774" x2="396.72" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="394.653" y1="295.774" x2="399.997" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="398.177" y1="295.774" x2="403.521" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="401.694" y1="295.774" x2="407.038" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="404.784" y1="295.774" x2="410.128" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="465.213,300.751 446.046,300.751 440.597,295.774 459.763,295.774 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="442.125" y1="297.17" x2="461.241" y2="297.17"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="443.529" y1="295.774" x2="448.873" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="446.806" y1="295.774" x2="452.15" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="450.33" y1="295.774" x2="455.674" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="453.847" y1="295.774" x2="459.19" y2="300.751"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="456.937" y1="295.774" x2="462.28" y2="300.751"/>
		</g>
		<g>
			<polygon style="fill:#2E3946;" points="509.624,311.074 501.999,311.074 501.299,306.098 508.925,306.098 		"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="501.299" y1="307.493" x2="509.208" y2="307.493"/>
			
				<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="505.253" y1="306.098" x2="505.958" y2="311.074"/>
		</g>
		<polygon style="fill:#2E3946;" points="562.307,311.074 539.061,311.074 538.362,306.098 561.608,306.098 	"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="538.362" y1="307.493" x2="561.804" y2="307.493"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="542.316" y1="306.098" x2="543.02" y2="311.074"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="546.464" y1="306.098" x2="547.168" y2="311.074"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="550.631" y1="306.098" x2="551.336" y2="311.074"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="554.934" y1="306.098" x2="555.638" y2="311.074"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="558.429" y1="306.098" x2="559.133" y2="311.074"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="177.788" y1="230.624" x2="179.663" y2="224.66"/>
		<path style="fill:#978D78;" d="M173.453,246.778"/>
		<g>
			<polygon style="fill:#040000;" points="173.527,246.836 173.215,246.59 178.778,246.528 178.781,246.778 		"/>
		</g>
		<polygon style="fill:#040000;" points="224.538,246.469 218.599,246.469 212.309,238.622 218.247,238.622 	"/>
		<polygon style="fill:#040000;" points="277.036,245.693 271.097,245.693 264.807,237.845 270.745,237.845 	"/>
		<polygon style="fill:#040000;" points="367.694,244.472 361.756,244.472 360.931,236.625 366.87,236.625 	"/>
		<polygon style="fill:#040000;" points="407.361,244.472 401.422,244.472 400.598,236.625 406.536,236.625 	"/>
		<polygon style="fill:#040000;" points="454.352,243.597 448.413,243.597 447.589,235.75 453.527,235.75 	"/>
		<g>
			<polygon style="fill:#040000;" points="182.656,246.682 182.74,246.405 218.846,246.22 218.849,246.47 		"/>
		</g>
		<polygon style="fill:#867E80;" points="192.22,238.139 176.762,290.625 181.262,290.625 195.262,290.625 206.97,246.639 
			196.97,238.139 	"/>
		<polygon style="fill:#FCF3F5;" points="192.233,238.139 176.762,290.625 180.762,290.625 196.566,238.139 	"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="187.568" y1="253.964" x2="191.817" y2="253.912"/>
		
			<line style="fill:none;stroke:#707172;stroke-width:0.25;stroke-miterlimit:10;" x1="181.234" y1="275.121" x2="185.482" y2="275.069"/>
		<g>
			<polygon style="fill:#707172;" points="187.285,286.855 186.615,289.101 184.567,287.767 185.237,285.52 		"/>
			<polygon style="fill:#707172;" points="188.501,282.774 187.831,285.021 185.783,283.686 186.453,281.44 		"/>
			<polygon style="fill:#707172;" points="189.656,278.9 188.986,281.146 186.938,279.812 187.608,277.566 		"/>
			<polygon style="fill:#707172;" points="190.942,274.924 190.272,277.17 188.224,275.836 188.894,273.59 		"/>
			<polygon style="fill:#707172;" points="192.158,270.844 191.488,273.09 189.44,271.756 190.11,269.51 		"/>
			<polygon style="fill:#707172;" points="193.313,266.97 192.643,269.216 190.595,267.882 191.265,265.635 		"/>
			<polygon style="fill:#707172;" points="194.73,263.09 194.061,265.336 192.012,264.002 192.682,261.756 		"/>
			<polygon style="fill:#707172;" points="195.946,259.01 195.277,261.256 193.229,259.922 193.898,257.676 		"/>
			<polygon style="fill:#707172;" points="197.101,255.136 196.432,257.382 194.384,256.048 195.053,253.802 		"/>
			<polygon style="fill:#707172;" points="198.387,251.159 197.717,253.405 195.669,252.071 196.339,249.825 		"/>
			<polygon style="fill:#707172;" points="199.603,247.079 198.934,249.325 196.886,247.991 197.555,245.745 		"/>
			<polygon style="fill:#707172;" points="200.758,243.205 200.089,245.451 198.041,244.117 198.71,241.871 		"/>
		</g>
		<g>
			<polygon style="fill:#707172;" points="192.729,286.848 192.106,289.107 190.031,287.816 190.654,285.556 		"/>
			<polygon style="fill:#707172;" points="193.804,282.951 193.181,285.21 191.106,283.919 191.729,281.659 		"/>
			<polygon style="fill:#707172;" points="195.008,278.949 194.385,281.208 192.31,279.917 192.933,277.657 		"/>
			<polygon style="fill:#707172;" points="196.14,274.845 195.517,277.104 193.442,275.812 194.065,273.553 		"/>
			<polygon style="fill:#707172;" points="197.216,270.948 196.592,273.207 194.517,271.915 195.141,269.656 		"/>
			<polygon style="fill:#707172;" points="198.553,267.04 197.93,269.299 195.855,268.008 196.478,265.748 		"/>
			<polygon style="fill:#707172;" points="199.685,262.936 199.062,265.195 196.987,263.903 197.61,261.644 		"/>
			<polygon style="fill:#707172;" points="200.761,259.039 200.137,261.298 198.062,260.006 198.686,257.747 		"/>
			<polygon style="fill:#707172;" points="201.964,255.037 201.341,257.296 199.266,256.004 199.889,253.745 		"/>
			<polygon style="fill:#707172;" points="203.097,250.933 202.473,253.192 200.398,251.9 201.022,249.641 		"/>
			<polygon style="fill:#707172;" points="204.172,247.036 203.549,249.295 201.474,248.003 202.097,245.744 		"/>
		</g>
		<g>
			<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="226.12" y1="195.225" x2="234.603" y2="164.175"/>
			<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="234.603" y1="164.175" x2="266.986" y2="164.175"/>
			<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="266.986" y1="164.175" x2="260.12" y2="195.225"/>
		</g>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="347.158" y1="175.847" x2="352.625" y2="175.847"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="319.054" y1="196.338" x2="323.786" y2="163.621"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="323.755" y1="163.836" x2="353.983" y2="163.836"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="354.004" y1="163.586" x2="351.245" y2="196.225"/>
		<polygon style="fill:#A69985;" points="323.675,245.312 320.557,263.579 327.382,263.315 330.008,245.312 	"/>
		<polygon style="fill:#A69985;" points="339.676,245.312 337.245,263.757 344.743,263.836 346.957,245.312 	"/>
		<polygon style="fill:#A69985;" points="331.351,175.847 328.043,195.225 335.328,195.225 337.684,175.847 	"/>
		<polygon style="fill:#A69985;" points="347.158,176.081 344.861,195.218 351.006,195.218 352.729,176.081 	"/>
		<g>
			
				<rect x="340.61" y="243.495" transform="matrix(0.9958 0.0915 -0.0915 0.9958 23.8806 -30.41)" style="fill:#A2A3A0;" width="5.667" height="3.25"/>
			<polygon style="fill:#FFFFFF;" points="341.323,240.971 340.77,243.243 346.413,243.761 346.243,241.424 		"/>
			<path style="fill:#FFFFFF;" d="M340.473,246.479l-0.855,6.574c0,0,1.629,4.125,2.712,4.182c1.083,0.058,3.172-3.558,3.172-3.558
				l0.614-6.68L340.473,246.479z"/>
			
				<ellipse transform="matrix(0.0915 -0.9958 0.9958 0.0915 60.9265 569.9702)" style="fill:#B3B3B3;" cx="342.848" cy="251.593" rx="1.125" ry="1.125"/>
		</g>
		<g>
			
				<rect x="325.019" y="244.925" transform="matrix(0.9995 0.0328 -0.0328 0.9995 8.2549 -10.6074)" style="fill:#A2A3A0;" width="4.932" height="2.829"/>
			<polygon style="fill:#FFFFFF;" points="325.431,242.843 325.067,244.845 329.997,245.007 329.729,242.984 		"/>
			<path style="fill:#FFFFFF;" d="M324.974,247.672l-0.407,5.756c0,0,1.627,3.501,2.571,3.495c0.944-0.005,2.574-3.254,2.574-3.254
				l0.191-5.836L324.974,247.672z"/>
			
				<ellipse transform="matrix(0.0328 -0.9995 0.9995 0.0328 64.7074 570.8538)" style="fill:#B3B3B3;" cx="327.3" cy="251.994" rx="0.979" ry="0.979"/>
		</g>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="331.32" y1="175.612" x2="337.661" y2="175.612"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="415.026" y1="196.503" x2="415.026" y2="163.003"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="415.026" y1="163.003" x2="447.214" y2="163.003"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="447.214" y1="163.003" x2="447.964" y2="195.628"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="417.089" y1="180.753" x2="430.714" y2="180.378"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="433.37" y1="180.344" x2="446.464" y2="180.628"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="251.355" y1="231.162" x2="245.432" y2="265.195"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="245.432" y1="265.195" x2="228.829" y2="265.162"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="265.454" y1="245.162" x2="259.087" y2="276.662"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="239.806" y1="276.662" x2="259.087" y2="276.662"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="239.806" y1="276.662" x2="236.365" y2="290.357"/>
		<g>
			<polygon style="fill:#040000;" points="265.598,245.775 265.661,245.214 271.09,245.214 271.096,245.714 		"/>
		</g>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="315.323" y1="231.859" x2="310.453" y2="263.472"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="310.453" y1="263.472" x2="345.068" y2="263.84"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="345.068" y1="263.84" x2="348.846" y2="231.254"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="409.901" y1="230.62" x2="409.901" y2="263.322"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="409.901" y1="263.322" x2="445.62" y2="263.322"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="445.62" y1="263.322" x2="445.62" y2="230.989"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="296.297" y1="245.415" x2="312.888" y2="245.214"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="356.453" y1="244.472" x2="378.347" y2="244.472"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="384.269" y1="244.331" x2="409.088" y2="244.331"/>
		
			<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="446.201" y1="243.597" x2="472.383" y2="243.597"/>
		<g>
			<g>
				<polygon style="fill:#807B7C;" points="266.12,251.311 259.995,282.936 262.245,285.936 270.245,285.936 276.495,254.436 
					274.245,251.311 			"/>
				<polygon style="fill:#FCF3F7;" points="265.995,251.311 259.995,282.811 268.245,282.811 274.62,251.311 			"/>
			</g>
			<g>
				
					<rect x="263.83" y="279.163" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -61.7445 483.5196)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="264.593" y="275.413" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -57.4596 481.2654)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="265.368" y="271.607" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -53.1096 478.977)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="266.033" y="268.336" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -49.3718 477.0106)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="266.708" y="265.018" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -45.58 475.0158)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="267.471" y="261.269" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -41.295 472.7616)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="268.246" y="257.463" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -36.945 470.4731)" style="fill:#999899;" width="2.409" height="0.767"/>
				
					<rect x="268.911" y="254.192" transform="matrix(0.1994 -0.9799 0.9799 0.1994 -33.2072 468.5068)" style="fill:#999899;" width="2.409" height="0.767"/>
			</g>
		</g>
		<g>
			<polygon style="fill:#FCF3F7;" points="358.249,251.926 355.718,282.926 363.267,282.926 366.048,251.926 		"/>
			<polygon style="fill:#807B7C;" points="355.718,282.926 355.718,285.284 363.305,285.284 363.267,282.926 		"/>
		</g>
		<g>
			<polygon style="fill:#FCF3F7;" points="396.279,251.926 395.465,282.926 403.014,282.926 404.078,251.926 		"/>
			<polygon style="fill:#807B7C;" points="395.465,282.926 395.465,285.284 403.052,285.284 403.014,282.926 		"/>
		</g>
		<g>
			<polygon style="fill:#FCF3F7;" points="449.954,251.926 451.164,282.926 458.712,282.926 457.753,251.926 		"/>
			<polygon style="fill:#807B7C;" points="451.164,282.926 451.164,285.284 458.75,285.284 458.712,282.926 		"/>
		</g>
		<g>
			
				<rect x="358.566" y="279.246" transform="matrix(0.0873 -0.9962 0.9962 0.0873 49.7865 613.6019)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="358.9" y="275.434" transform="matrix(0.0873 -0.9962 0.9962 0.0873 53.8886 610.4561)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="359.239" y="271.565" transform="matrix(0.0873 -0.9962 0.9962 0.0873 58.0529 607.2626)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="359.531" y="268.24" transform="matrix(0.0873 -0.9962 0.9962 0.0873 61.6312 604.5184)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="359.827" y="264.867" transform="matrix(0.0873 -0.9962 0.9962 0.0873 65.2612 601.7346)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="360.161" y="261.055" transform="matrix(0.0873 -0.9962 0.9962 0.0873 69.3633 598.5888)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="360.5" y="257.186" transform="matrix(0.0873 -0.9962 0.9962 0.0873 73.5276 595.3953)" style="fill:#999899;" width="2.409" height="0.767"/>
			
				<rect x="360.792" y="253.861" transform="matrix(0.0873 -0.9962 0.9962 0.0873 77.1059 592.6511)" style="fill:#999899;" width="2.409" height="0.767"/>
		</g>
		<polygon style="fill:#EAE9E9;" points="371.118,239.254 378.765,239.176 374.998,289.856 366.412,290.014 366.412,290.014 
			370.04,246.997 	"/>
		<polygon style="fill:#EAE9E9;" points="389.281,239.176 382.281,239.176 378.446,289.847 387.019,289.847 387.019,289.847 
			389.949,246.778 	"/>
		<g>
			
				<rect x="384.7" y="247.54" transform="matrix(0.9989 0.0459 -0.0459 0.9989 11.8565 -17.4624)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="383.417" y="262.173" transform="matrix(0.9962 0.0873 -0.0873 0.9962 24.5134 -32.5776)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="382.522" y="278.693" transform="matrix(0.9962 0.0873 -0.0873 0.9962 25.9529 -32.4362)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="381.112" y1="256.203" x2="389.371" y2="256.69"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="379.82" y1="271.697" x2="388.215" y2="272.192"/>
		</g>
		<g>
			
				<rect x="372.693" y="247.54" transform="matrix(0.9989 0.0459 -0.0459 0.9989 11.8438 -16.9108)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="371.41" y="262.173" transform="matrix(0.9962 0.0873 -0.0873 0.9962 24.4675 -31.5289)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<rect x="370.515" y="278.693" transform="matrix(0.9962 0.0873 -0.0873 0.9962 25.907 -31.3875)" style="fill:#EAE9E9;stroke:#999899;stroke-width:0.25;stroke-miterlimit:10;" width="2.244" height="3.333"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="369.105" y1="256.203" x2="377.364" y2="256.69"/>
			
				<line style="fill:none;stroke:#999899;stroke-width:0.5;stroke-miterlimit:10;" x1="367.813" y1="271.697" x2="376.208" y2="272.192"/>
		</g>
		<polygon style="fill:#727071;" points="512.307,151.498 516.932,151.498 513.307,168.061 	"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="510.995" y1="151.498" x2="516.932" y2="151.498"/>
		
			<line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="569.098" y1="122.621" x2="565.098" y2="175.163"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="563.126" y1="209.104" x2="566.195" y2="164.177"/>
		<line style="fill:none;stroke:#FFFFFF;stroke-miterlimit:10;" x1="563.414" y1="204.892" x2="559.722" y2="175.163"/>
		<rect x="535.438" y="188.205" style="fill:#978D78;" width="20.409" height="6.048"/>
		<polygon style="fill:#A0977F;" points="599.37,242.546 572.881,247.155 601.598,259.366 	"/>
		<polygon style="fill:#707071;" points="495.598,174.277 493.291,190.027 493.291,195.378 497.938,195.277 	"/>
		<polygon style="fill:#BABABA;" points="505.109,174.277 503.62,162.236 503.62,151.498 507.245,151.498 509.703,174.277 	"/>
		<polygon style="fill:#FCF3F7;" points="280.557,170.875 280.953,168.902 283.2,168.902 282.828,171.236 	"/>
		<polygon style="fill:#FCF3F7;" points="283.2,168.902 284.495,169.882 284.338,171.569 282.828,171.236 	"/>
		<g>
			<rect x="433.911" y="241.231" style="fill:#A2A3A0;" width="5.667" height="3.25"/>
			<polygon style="fill:#FFFFFF;" points="434.253,238.919 433.911,241.231 439.578,241.231 439.195,238.919 		"/>
			<path style="fill:#FFFFFF;" d="M433.911,244.481l-0.25,6.625c0,0,2,3.958,3.083,3.917c1.083-0.042,2.833-3.833,2.833-3.833v-6.708
				H433.911z"/>
			<circle style="fill:#B3B3B3;" cx="436.745" cy="249.356" r="1.125"/>
		</g>
		<g>
			<rect x="416.661" y="241.231" style="fill:#A2A3A0;" width="5.667" height="3.25"/>
			<polygon style="fill:#FFFFFF;" points="417.003,238.919 416.661,241.231 422.327,241.231 421.944,238.919 		"/>
			<path style="fill:#FFFFFF;" d="M416.661,244.481l-0.25,6.625c0,0,2,3.958,3.083,3.917c1.083-0.042,2.833-3.833,2.833-3.833v-6.708
				H416.661z"/>
			<circle style="fill:#B3B3B3;" cx="419.494" cy="249.356" r="1.125"/>
		</g>
		<polygon style="fill:#EAE9E9;" points="426.837,289.669 419.508,289.695 419.508,278.951 426.898,278.951 	"/>
		<polygon style="fill:#EAE9E9;" points="436.504,289.669 429.174,289.695 429.174,278.951 436.565,278.951 	"/>
		<polygon style="fill:#EAE9E9;" points="469.503,171.72 463.12,171.747 462.865,161.004 469.068,161.004 	"/>
		<polygon style="fill:#EAE9E9;" points="438.467,162.507 432.084,162.534 431.957,155.064 438.16,155.064 	"/>
		<polygon style="fill:#EAE9E9;" points="430.342,162.507 423.959,162.534 423.832,155.064 430.035,155.064 	"/>
		<polygon style="fill:#EAE9E9;" points="471.622,195.006 464.292,195.032 464.292,184.289 471.682,184.289 	"/>
		<polygon style="fill:#EAE9E9;" points="324.897,279.547 321.432,279.547 319.682,290.014 323.504,290.014 	"/>
		<polygon style="fill:#EAE9E9;" points="239.48,279.977 236.015,279.977 234.265,290.444 238.087,290.444 	"/>
		<polygon style="fill:#EAE9E9;" points="333.482,279.547 330.017,279.547 328.267,290.014 332.089,290.014 	"/>
		<g>
			
				<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="332.676" y1="169.452" x2="333.79" y2="175.847"/>
			<circle style="fill:#040000;" cx="332.51" cy="169.441" r="0.75"/>
		</g>
		<g>
			
				<line style="fill:none;stroke:#040000;stroke-width:0.5;stroke-miterlimit:10;" x1="348.635" y1="169.452" x2="349.789" y2="176.081"/>
			<circle style="fill:#040000;" cx="348.468" cy="169.441" r="0.75"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="216.78,205.9 225.03,216.525 236.53,216.525 227.78,204.775 216.979,205.433 
						207.28,206.025 207.411,206.527 				"/>
				</g>
				<polygon style="fill:#978D78;" points="220.463,207.694 223.776,211.715 229.374,211.194 226.463,206.842 220.151,207.319 			"/>
			</g>
			<polygon style="fill:#07152D;" points="209.974,205.986 209.745,204.775 211.432,204.632 211.745,205.861 		"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="258.444,205.9 263.553,216.132 275.053,216.132 269.444,204.775 258.643,205.433 
						248.944,206.025 249.075,206.527 				"/>
				</g>
				<polygon style="fill:#978D78;" points="262.127,207.694 264.334,211.975 269.932,211.454 268.127,206.842 261.815,207.319 			"/>
			</g>
			<polygon style="fill:#07152D;" points="251.638,205.986 251.409,204.775 253.096,204.632 253.409,205.861 		"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="299.08,205.9 304.189,216.132 315.689,216.132 310.08,204.775 299.279,205.433 
						289.58,206.025 289.711,206.527 				"/>
				</g>
				<polygon style="fill:#978D78;" points="302.763,207.694 304.97,211.975 310.568,211.454 308.763,206.842 302.451,207.319 			"/>
			</g>
			<polygon style="fill:#07152D;" points="292.274,205.986 292.045,204.775 293.732,204.632 294.045,205.861 		"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="352.164,205.65 357.273,215.882 368.773,215.882 363.164,204.525 352.363,205.183 
						342.664,205.775 342.795,206.277 				"/>
				</g>
				<polygon style="fill:#978D78;" points="355.847,207.444 358.054,211.725 363.652,211.205 361.847,206.592 355.535,207.069 			"/>
			</g>
			<polygon style="fill:#07152D;" points="345.358,205.736 345.129,204.525 346.816,204.382 347.129,205.611 		"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="396.716,203.832 396.896,215.312 408.396,215.312 407.708,203.365 396.914,203.365 
						386.997,203.545 387.128,204.048 				"/>
				</g>
				<polygon style="fill:#978D78;" points="399.553,207.053 399.636,211.855 405.232,211.687 405.261,206.687 399.552,206.678 			"/>
			</g>
			<polygon style="fill:#07152D;" points="389.764,203.595 389.847,202.384 391.534,202.241 391.534,203.47 		"/>
		</g>
		<g>
			<g>
				<g>
					<polygon style="fill:#07152D;" points="453.634,203.829 453.814,215.309 465.314,215.309 464.626,203.362 453.832,203.362 
						443.915,203.542 444.046,204.045 				"/>
				</g>
				<polygon style="fill:#978D78;" points="456.471,207.05 456.554,211.852 462.15,211.684 462.179,206.684 456.47,206.675 			"/>
			</g>
			<polygon style="fill:#07152D;" points="446.682,203.592 446.765,202.381 448.452,202.238 448.452,203.467 		"/>
		</g>
		<g>
			<polygon style="fill:#07152D;" points="480.431,201.986 479.118,215.319 493.347,215.319 495.243,201.986 		"/>
			<polygon style="fill:#978D78;" points="482.453,203.592 481.87,209.336 485.286,209.336 486.203,203.592 		"/>
			<polygon style="fill:#978D78;" points="488.953,203.592 488.37,209.336 491.786,209.336 492.703,203.592 		"/>
		</g>
		<g>
			<polygon style="fill:#07152D;" points="517.932,201.457 516.62,214.79 530.848,214.79 532.745,201.457 		"/>
			<polygon style="fill:#978D78;" points="519.954,203.064 519.371,208.807 522.787,208.807 523.704,203.064 		"/>
			<polygon style="fill:#978D78;" points="526.454,203.064 525.871,208.807 529.287,208.807 530.204,203.064 		"/>
		</g>
		<path style="fill:#EAE9E9;" d="M561.786,208.243c0,0-13.604,11.201,3.097,20.018L561.786,208.243z"/>
		<path style="fill:#707172;" d="M553.696,218.252c0,0-13.604,11.201,3.097,20.018L553.696,218.252z"/>
		<polygon style="fill:#07152D;" points="557.941,255.523 562.858,249.569 565.194,258.523 559.269,263.833 	"/>
		<polygon style="fill:#07152D;" points="559.722,267.422 564.638,261.468 566.974,270.422 561.049,275.731 	"/>
		<polygon style="fill:#07152D;" points="561.568,280.797 566.484,274.843 568.82,283.797 562.895,289.106 	"/>
		<polygon style="fill:#978D78;" points="563.631,252.533 560.995,255.507 561.908,261.468 565.194,258.523 	"/>
		<polygon style="fill:#978D78;" points="565.411,264.408 562.775,267.382 563.688,273.343 566.974,270.398 	"/>
		<polygon style="fill:#978D78;" points="567.297,277.815 564.66,280.788 565.574,286.749 568.86,283.805 	"/>
		<rect x="592.12" y="178.666" style="fill:#707172;" width="1.714" height="3.667"/>
	</g>
</svg>
						</div>
						<div class="center hide cls-third">
							<svg width="100%" height="100%" version="1.1"
		xmlns="http://www.w3.org/2000/svg">

		<polygon points="220,100 300,210 170,250 123,234"
		style="fill:#cccccc;
		stroke:#000000;stroke-width:1"/>

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
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>监区</th>
						<!-- <th>风险值</th> -->
						<th>区域格格长</th>
						<th>安全管理员</th>
						<th>党委成员</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>一监区</td>
						<!-- <td>920</td> -->
						<td>刘蕊</td>
						<td>单晓玲</td>
						<td>副监狱长:刘蕊</td>
						<td>操作</td>
					</tr>
					<tr>
						<td><a href="javascript:void(0)" data-id="first">二监区</a></td>
						<!-- <td>920</td> -->
						<td>吴建华</td>
						<td>单晓玲</td>
						<td>副监狱长:吴建华</td>
						<td><a href="javascript:openOldZnafpt();">操作</a></td>
					</tr>
					<tr>
						<td>三监区</td>
						<!-- <td>920</td> -->
						<td>杨梅青</td>
						<td>马莉芳</td>
						<td>副监狱长:邢玫</td>
						<td>操作</td>
					</tr>
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
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/base.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<!-- app js define start  -->
	<script src="${ctx}/static/js/scripts/common.js"></script>
	<script src="${ctx}/static/resource/style/js/function.js"></script>
	<script src="${ctx}/static/js/scripts/prettify.js"></script>
	<!-- app js define  end  -->
	<script src="${ctx}/static/system/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/videoPlanTimer.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/js/callback/callback.js"></script>
	<jsp:include page="../../include/videoInclude.jsp"></jsp:include>
	<jsp:include page="../../include/messageInclude.jsp"></jsp:include>
	
	<script type="text/javascript">
		$(function() {
			// desc: 临时代码，演示用
			$("#showDate").hover(function() {
				$("#top1").hide();
				$("#top2").show();
			});
		});
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
		var svgViewer = document.getElementById("svg");var old='';
		function offsetXY(e) {
			var stateTipX= -15,stateTipY= -25;
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
			$(".content").mouseenter(function(e){
		 		var id = $(e.currentTarget).attr("id");
		 		if(old == id){
		 			 var a=svgViewer.getElementById(id);
		 			
				    var show= $(".tooltip").css("display");
				    var flag =  a.getAttribute("stroke");
				    if((flag == null || flag == "none")){
						a.setAttribute("stroke", "#0affe9");
					}
					if(show == "none"){
						$(".tooltip").show();			
					}
		 			return
		 		}
				if(id && id!=="tooltip"){
					
		           var a=svgViewer.getElementById(id);
				    //var txt=rect.getElementById("a237");
				    a.setAttribute("stroke", "#0affe9");
				   var _offsetXY = offsetXY(e);

					$('.tooltip').css({
						left: _offsetXY[0],
						top: _offsetXY[1]
					}).show();
					
				 }
				old = id
			})
		 $("#tooltip").on("mouseenter",function(){
		 		$(".tooltip").show();
		 })
		  $("#tooltip").on("mouseleave",function(e){
		 		var id = $(e.currentTarget).attr("id");
		 		if(e.toElement && e.toElement.id !== "tooltip"){
		 			$(".tooltip").hide();
		 		}
		 })
		 // $("#svg").mouseenter(debounce(handle,100))
		 $(".show").mouseleave(function(e){
		 	var id = $(e.currentTarget).attr("id")
		 	var list = document.getElementsByClassName("content");
			for(var i = 0; i<list.length;i++){
				list[i].setAttribute("stroke", "none")
			}
			$(".tooltip").hide();
		})
		$(".topRec_List td>a").on("click",function(e){
			$(".tooltip").hide();
			$(".back").removeClass("hide")
			if($(".center").hasClass("bigMore")){
				$(".center").removeClass("bigMore").addClass("small");
				$(".center").siblings(".hide").removeClass("small")
			}
			$(".cls-second").removeClass("hide").addClass("bigMore")
			
		})
		$(".back").on("click",function(){
			$(".cls-second").removeClass("bigMore").addClass("small").addClass("hide");
			$(".cls-first").addClass("bigMore").removeClass("small").removeClass("hide");

		})
		$('#layout1').layout({
		    fit:true,              //属性: 值
		    onCreate: function() { //回调事件: 值
		    
		    }
		});
		$('#layout-child').layout({
		    fit:true,              //属性: 值
		    onCreate: function() { //回调事件: 值
		    
		    }
		});
		$.parseDone(function(){
			jsConst.basePath = "${ctx}/";
			
			$(".center:first").addClass("bigMore");
			
			initUserInfo();
			//initPage();//初始化
			videoPlanTimer.initVideoPlanTimer();
		})
		
		function openMenuDialog(event, name) {

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
	
	        $('#dialog').html("");
			// $('#dialog').dialog("destroy");
	        if (w == null || h == null) {
	            w = 1000;
	            h = 600;
	        }
	
	        $('#dialog').dialog({
	            width: w,
	            height: h,
	            title: $("#menu_" + name).text(),
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
			$("#dqyh").append("<span class=\"user\" style='font-size: 20px'>" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}

		function openOldZnafpt() {
			
			var url = "${ctx}/portal/shouye";
			window.location.href = url;
		}
		
		/**
		 * 测试同步罪犯基本信息用到
		 */
		function synchroZfJbxx() {
			var urlPath = $("#rootPath").val() + "/zfxx/zfJbxx/synchroZfJbxx";
		    var params = {};
			// Desc: 测试同步罪犯基本信息用到
			$.ajax({
				type : 'post',
				url : urlPath,
				data : params,
				dataType : 'json',
				success : function(data) {
	                if(data.code == 200) {
	                	console.log(data.data);
	                } else if (data.code == 500){
	                	console.log(data.data);
	                }
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		}
		function openZnafpt() {
			
			var url = "${ctx}/portal/bj/shouye";
			window.location.href = url;
		}
	</script>
</body>

</html>