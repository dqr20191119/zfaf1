<!DOCTYPE html>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${map.orgName}综合管控平台 </title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/menhu_new.css" />
	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css"> --%>
	
	
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/mhsy.css" /> --%>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/zfxx_dialog.css">
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/gerenminjing.css">
  	
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/menhu_new.css" />
<style type="text/css">
.title{
font-size: 18px;
}
</style>
</head>

<body>

	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
	<div class="container menhu">
		<header class="perspective">
			<div class="header">
				<div class="header-content">
					<div class="header-item date">
						<span class="icon iconfont icon-datepiceker"></span> <span
							class="title">${dqrq}</span>
					</div>
					<div class="header-item">
						<span class="icon iconfont icon-police2"></span> <span
							class="title" id="dqyh1">当前用户：</span>
					</div>
					<div class="header-item dropdow">
						<span class="icon iconfont icon-xialadown"></span>
					</div>
					<div class="header-item">
						<span class="icon iconfont icon-system-setting" title="退出系统" style="cursor: pointer;" onClick="syLogout();"></span>
					</div>
				</div>
			</div>
		</header>
		<div class="center">
			<div class="logo-wrapper">
				<img src="${ctx}/static/bj-cui/img/menhu/logo_${map.orgCode}.png"
					alt="${map.orgName}智能安防平台 " class="logo">
			</div>
			<div class="nav-wrapper">
				<ul class="nav" style="height: 540px;">
				
				<sec:authorize url="/zhdd/zy/zhzf">
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-law"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">智慧执法</span>
							</div>
						</div>
					</li>
				</sec:authorize>
				
				<sec:authorize url="/zhdd/zy/zhjy">
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-education1"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">智慧教育</span>
							</div>
						</div>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf">
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-safety"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">智慧安防</span>
							</div>
						</div>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhdj">
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-team1"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">智慧队建</span>
							</div>
						</div>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhbz">
					
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-office"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">智慧保障</span>
							</div>
						</div>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/dpksh">
					<li class="nav-item">
						<div class="nav-item-wrapper">
							<div class="nav-logo">
								<span class="iconfont center-absolute icon-screen"></span>
							</div>
							<div class="nav-text">
								<span class="title center-absolute">大屏可视化</span>
							</div>
						</div>
					</li>
					</sec:authorize>
				</ul>
			</div>
			<div class="content-wrapper menhu-subsystem unvisible">
				<div class="back">
					<span id="back"><span class="icon-xiazai6 iconfont"></span>返回</span>
				</div>
				<div class="content-slide">
					
					<!-- 【智慧执法 _菜单】 start -->
					<sec:authorize url="/zhdd/zy/zhzf">
					<div class="content">
						<ul class="system">
							
							<sec:authorize url="/zhdd/zy/zhzf/ydjwxt">
							<li class="system-item" onclick="openHyMjzfpt('jygz')">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-mobile.png"
											alt="移动警务系统"><span class="title">移动警务系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/ynzcxt">
							 <li class="system-item">
				                <div class="system-content " onclick="openHyMjzfpt('ynzc')">
				                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-letter.png" alt="狱内侦查系统"><span
				                      class="title">狱内侦查系统</span></div>
				                </div>
				              </li>
				             </sec:authorize> 
				              <sec:authorize url="/zhdd/zy/zhzf/sjswh">
							  <li class="system-item" onclick="openHyMjzfpt('szswh')">
				                <div class="system-content ">
				                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-lightning.png" alt="移动警务系统"><span
				                      class="title">数字审委会</span></div>
				                </div>
				              </li>
				              </sec:authorize>
			                <sec:authorize url="/zhdd/zy/zhzf/mjzfpt">
							<li class="system-item" onclick="openHyMjzfpt('home')">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-work.png"
											alt="民警执法平台"><span class="title">民警执法平台</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/xfzxywxt">
							<li class="system-item" onclick="openHyMjzfpt('xfzx')">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-punish.png"
											alt="移动警务系统"><span class="title">刑罚执行业务系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/zfylxxglxt">
							<li class="system-item" onClick="openZfylxxglxt()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-cure.png"
											alt="移动警务系统"><span class="title">罪犯医疗信息管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/zjyp">
							<li class="system-item" onclick="openZZYP()">
				                <div class="system-content ">
				                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-watch.png" alt="移动警务系统"><span
				                      class="title">证据云盘</span></div>
				                </div>
				            </li>
				            </sec:authorize>
				            <sec:authorize url="/zhdd/zy/zhzf/jfkhxt">
							<li class="system-item" onclick="openHyMjzfpt('jfkh')">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-test.png"
											alt="移动警务系统"><span class="title">计分考核系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/dzwpcghzwxt">
							<li class="system-item" onclick="openShopping()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-shopping.png"
											alt="移动警务系统"><span class="title">大宗物品采购和帐务系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/zfdzdaglxt">
							<li class="system-item" onClick="openZfdzdagl()">
								<div class="system-content  ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-identity.png"
											alt="移动警务系统"><span class="title">罪犯电子档案管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/ywgkxt">
							<li class="system-item" onclick="openHyMjzfpt('ywgk')">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-public.png"
											alt="移动警务系统"><span class="title">狱务公开系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/ldgzzhglxt">
							<li class="system-item">
								<div class="system-content  disabled">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-labour.png"
											alt="移动警务系统"><span class="title">劳动改造综合管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhzf/gzzlkp">
				            <li class="system-item" onclick="openZfgzzlkp()">
				              <div class="system-content ">
				                <div class="system-text">
				               	  <img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-certification.png" alt="改造质量考评"><span class="title">改造质量考评</span>
				                </div>
				              </div>
				            </li>
				            </sec:authorize>
						</ul>
					</div>
					</sec:authorize>
					<!-- 【智慧执法 _菜单】 end -->
					
					
					<!-- 【智慧教育 _菜单】 start -->
					<sec:authorize url="/zhdd/zy/zhjy">
					<div class="content">
						<ul class="system">
							<sec:authorize url="/zhdd/zy/zhjy/xszx">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-sudent.png"
											alt="移动警务系统"><span class="title">新生在线</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhjy/zfxlcpxt">
							<li class="system-item" onClick="openJygzglxt()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-psycho.png"
											alt="移动警务系统"><span class="title">犯罪心理测评系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhjy/jygzglxt">
							<li class="system-item" onClick="openJygzglxt()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-teach.png"
											alt="移动警务系统"><span class="title">教育改造管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
						</ul>
					</div>
					</sec:authorize>
					<!-- 【智慧教育 _菜单】 end -->
										
					<!-- 【智慧安防 _菜单】 start -->
					<sec:authorize url="/zhdd/zy/zhaf">
					<div class="content">
						<ul class="system">
							
							<sec:authorize url="/zhdd/zy/zhaf/znafpt">
							<li class="system-item" onclick="openZnafpt()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-certification.png"
											alt="智能安防平台"><span class="title">智能安防平台</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							
							<sec:authorize url="/zhdd/zy/zhaf/zbcx">
							<li class="system-item" onclick="openMenuDialog(this, event, 'zbcx')" >
								<div class="system-content">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-certificate.png"
											alt="智能安防平台"><span class="title">值班查询</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/zbpb">
							<li class="system-item" onclick="openMenuDialog(this, event, 'zbbp')" >
								<div class="system-content">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-certificate.png"
											alt="智能安防平台"><span class="title">值班排班</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<%-- <li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-certificate.png"
											alt="移动警务系统"><span class="title">安全认证支撑系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-backup.png"
											alt="移动警务系统"><span class="title">容灾备份系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-resource.png"
											alt="移动警务系统"><span class="title">物力资源智能管控系统</span>
									</div>
								</div>
							</li> --%>
							<sec:authorize url="/zhdd/zy/zhaf/yjjkdwxt">
							<li class="system-item" onClick="openYuwaiyajie()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-position.png"
											alt="移动警务系统"><span class="title">押解监控定位系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<%-- <li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-electricity.png"
											alt="移动警务系统"><span class="title">监狱围墙电网管理系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-alarm.png"
											alt="移动警务系统"><span class="title">周界报警管理系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-phone.png"
											alt="移动警务系统"><span class="title">手机管控系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-gis.png"
											alt="移动警务系统"><span class="title">GIS引擎</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-access.png"
											alt="移动警务系统"><span class="title">门禁管理系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-evaluation.png"
											alt="移动警务系统"><span class="title">罪犯危险性评估预警系统</span>
									</div>
								</div>
							</li> --%>
							
							<sec:authorize url="/zhdd/zy/zhaf/wrjfkxt">
							<li class="system-item" onClick="openWrjfk()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-drone.png"
											alt="移动警务系统"><span class="title">无人机防控系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<%-- <li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-patrol.png"
											alt="移动警务系统"><span class="title">智能巡更系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-control.png"
											alt="移动警务系统"><span class="title">指挥调度一体化系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-area.png"
											alt="移动警务系统"><span class="title">区域管控系统</span>
									</div>
								</div>
							</li> --%>
							
							<sec:authorize url="/zhdd/zy/zhaf/yjzhddxt">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-emergency.png"
											alt="移动警务系统"><span class="title">应急指挥调度系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znyysbxt">
							<li class="system-item" onClick="openZnyysb()">
								<div class="system-content">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-voice.png"
											alt="移动警务系统"><span class="title">智能语音识别系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<%-- <li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-monitor.png"
											alt="移动警务系统"><span class="title">视频监控智能分析系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-name.png"
											alt="移动警务系统"><span class="title">视频点名系统</span>
									</div>
								</div>
							</li>
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-video-watch.png"
											alt="移动警务系统"><span class="title">视频监控系统</span>
									</div>
								</div>
							</li> --%>
							
							<sec:authorize url="/zhdd/zy/zhaf/jyaqfxypxt">
							<li class="system-item" onClick="openAqfxypSystem()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-risk.png"
											alt="移动警务系统"><span class="title">监狱安全风险研判系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/ythywglxt">
							<li class="system-item" onClick="openYthyw()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-maintenance.png"
											alt="移动警务系统"><span class="title">一体化运维管理系统</span>
									</div>
								</div>
							</li>
							<%--<li class="system-item" onClick="openKhywpt()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-maintenance.png"
											alt="移动警务系统"><span class="title">垦华运维平台</span>
									</div>
								</div>
							</li>--%>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/mbdwxt">
								<li class="system-item" onClick="openMbdwxt()">
									<div class="system-content ">
										<div class="system-text">
											<img class="system-img"
												 src="${ctx}/static/bj-cui/img/icons/icon-risk.png"
												 alt="移动警务系统"><span class="title">目标定位系统</span>
										</div>
									</div>
								</li>
							</sec:authorize>
						</ul>
					</div>
					</sec:authorize>
					<!-- 【智慧安防 _菜单】 end -->
					
					<!-- 【智慧队建 _菜单】 start -->
					<sec:authorize url="/zhdd/zy/zhdj">
					<div class="content">
						<ul class="system">
							<sec:authorize url="/zhdd/zy/zhdj/mjjxkhxt">
							<li class="system-item" onClick="openMjjxkh()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-exam.png"
											alt="民警绩效考核系统"><span class="title">民警绩效考核系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhdj/mjjypxglxt">
							<li class="system-item" onClick="openWNXY()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-training.png"
											alt="民警绩效考核系统"><span class="title">民警教育培训管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhdj/dwglxxxt">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-manage.png"
											alt="移动警务系统"><span class="title">队伍管理信息系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
						</ul>
					</div>
					</sec:authorize>
					<!-- 【智慧队建 _菜单】 end -->
					
					<!-- 【智慧保障 _菜单】 start -->
					<sec:authorize url="/zhdd/zy/zhbz">
					<div class="content">
						<ul class="system">
							<sec:authorize url="/zhdd/zy/zhbz/jycldwglxt">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-gps.png"
											alt="移动警务系统"><span class="title">警用车辆定位管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhbz/jfglxt">
							<li class="system-item" onclick="openMJBF()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-clothes.png"
											alt="移动警务系统"><span class="title">警服管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhbz/jwzbxxglxt">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-equipment.png"
											alt="移动警务系统"><span class="title">警务装备信息管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhbz/jjglxt">
							<li class="system-item" onclick="openJJGLXT()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-teamwork.png"
											alt="移动警务系统"><span class="title">基建管理系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhbz/bjsjjgljbgnw">
							<li class="system-item" onClick="openJyjbgnw()">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-intranet.png"
											alt="移动警务系统"><span class="title">北京市监狱管理局办公内网</span>
									</div>
								</div>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhbz/bjsjygljgzxxcjxt">
							<li class="system-item">
								<div class="system-content ">
									<div class="system-text">
										<img class="system-img"
											src="${ctx}/static/bj-cui/img/icons/icon-cadre.png"
											alt="移动警务系统"><span class="title">北京市监狱管理局干职信息采集系统</span>
									</div>
								</div>
							</li>
							</sec:authorize>
						</ul>
					</div>
					</sec:authorize>
					<!-- 【智慧保障 _菜单】 end -->
					
					<div class="content">
						<ul class="system">
						<%-- 
							<li class="system-item">
				              <div class="system-content ">
				                <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-certificate.png" alt="移动警务系统"><span class="title">数字证书认证系统</span></div>
				              </div>
				            </li> 
			            --%>
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
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/system/common.js"></script>
	
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/zfxx.js" type="text/javascript"></script>
	<!-- app js define start  -->
	<script src="${ctx}/static/js/scripts/common.js"></script>
	<script src="${ctx}/static/resource/style/js/function.js"></script>
	<script src="${ctx}/static/js/scripts/prettify.js"></script>
	<!-- app js define  end  -->
	<script type="text/javascript" src="${ctx}/static/bj-cui/js/jyshouye.js"></script>
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
	
	<script type="text/javascript">
	$(function() {
		var mma = ${map};
		initJSConstUserBean(mma);
	});
	</script>

	<script>
		

		var allLiTarget = $(".menhu .nav-item");
		var length = allLiTarget.length;
		var width = allLiTarget.eq(0).outerWidth();
		var original = [ 0 ];
		for (var i = 1; i < length; i++) {
			var tagret = allLiTarget.eq(i);
			tagret.css("left", width * i);
			original.push(width * i);
		}
		$(".nav-wrapper")
				.on(
						"click",
						".nav-item",
						function(e) {
							function showSub() {

								$(".menhu-subsystem").show();
								$(".menhu .nav")
										.fadeOut(
												"normal",
												function() {
													$(".logo-wrapper")
															.addClass("little");
													$(".menhu-subsystem")
															.removeClass(
																	"unvisible");
													addScroll($(
															".menhu-subsystem .content .system")
															.eq(index));
													setTimeout(function() {
														$(".back").addClass(
																"visible");
													}, 1000)
												});
							}

							function animateLeft(targetA) {
								$(targetA).css({
									"position" : "absolute"
								})
								$(targetA).animate({
									"left" : -500
								}, 1000)
							}

							function animateRight(targetB) {
								$(targetB).css({
									"position" : "absolute"
								})
								$(targetB).animate({
									"left" : 2000
								}, 1000)
							}
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
							showSub();
							return;

						})
		$("#back").click(function() {
			$(".menhu-subsystem").addClass("unvisible");
			$(".back").removeClass("visible");
			setTimeout(function() {
				$(".logo-wrapper").removeClass("little");
				$(".menhu-subsystem").hide();
				$(".menhu .nav").fadeIn("normal");
				var allLength = original.length, tmp = allLiTarget;
				for (var l = 0; l < allLength; l++) {
					tmp.eq(l).animate({
						"left" : original[l]
					}, 1000)
				}
			}, 500)

		})

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
		$(window).resize(function() {
			var allLength = original.length, tmp = allLiTarget;
			for (var l = allLength; l--;) {
				tmp.eq(l).css({
					"float" : "left",
					"position" : "static"
				})
			}

		})

		$(function() {
			jsConst.basePath = "${ctx}/";

			initUserInfo1();
		});
		 
		function showDqyh1() {
			$("#dqyh1").append(
					"<span class=\"user\">" + '<%=user.getRealName()%>' + "</span> （"
							+ '<%=user.getDprtmntName()%>'+ "）<br>警号："
							+ '<%=user.getPoliceNo()%>');
		}

		function openZnafpt() {
			var dprtmntCode = $("input[id='dprtmntCode']").val();
			var url = "${ctx}/portal/bj/shouye";
			// 部门是武警的时候，直接跳到安防立体防控首页
			if(dprtmntCode == '110537') {
				url = "${ctx}/portal/shouye";
			}
			window.open(url, "_blank");
		}

		/**
		 * 打开民警绩效考核系统
		 */
		function openMjjxkh() {
			var url = "http://206.0.0.13:8080/mjjxkh/2019Tabel.html";
			window.open(url, "_blank");
		}
		/**
		 * 罪犯电子档案管理系统
		 */
		function openZfdzdagl() {
			var url = "http://192.168.8.248:7001/receiveticket.jsp?userid=5be09735b05ca0a42fc1c342a50b610b";
			window.open(url, "_blank");
		}
		/**
		 * 智能语音识别系统
		 */
		function openZnyysb() {
			var url = "http://192.168.8.82:8080/jy-yysb/cas";
			window.open(url, "_blank");
		}
		/**
		 * 教育改造系统
		 */
		function openJygzglxt() {
			var url = "http://192.168.8.4:8888/jygz/summer/common/login.do?action=login&j_username=jgc&j_password=123";
			window.open(url, "_blank");
		}
		/**
		 * 罪犯医疗信息管理系统
		 */
		function openZfylxxglxt() {
			var url = "http://192.168.9.18:18080/BAApp/sso/ssoCtrl.htm?BLHMI=sso&userName=jld&password=000000";
			window.open(url, "_blank");
		}
		/**
		 * 北京市监狱管理局办公内网
		 */
		function openJyjbgnw() {
			var url = "http://192.168.8.200";
			window.open(url, "_blank");
		}

		//大宗物品采购和帐务系统
		function openShopping() {

			var url = "http://192.168.8.188:8080/PMS/grantLogin?username=zhpt&password=zhpt";
			window.open(url, "_blank");
		}

		//危险性评估
		function openWXXPG() {

			var url = "http://192.168.8.187/jy-wxpg/form/5be0f9483d0a86f8d24fca432843b784/insert";
			window.open(url, "_blank");
		}

		//证据云盘
		function openZZYP() {

			var url = "http://192.168.8.187/jy-zjbq/form/0e38e72812cd898154343831c4c56801/insert?mkItem=jqVerticalBoxd68cd";
			window.open(url, "_blank");
		}

		//民警网络学院
		function openWNXY() {

			var url = "http://192.168.8.36:8080/pedu/";
			window.open(url, "_blank");
		}
		//基建管理
		function openJJGLXT() {

			var url = "http://192.168.8.182/jjgl/form/3c8418a9aa7b30f8b04de4b765244fed/insert";
			window.open(url, "_blank");
		}
		//民警被服
		function openMJBF() {

			var url = "http://192.168.8.120:9080/qgbzgl/grzxfw/setZdryGrsg.jsp?sfz=410521197308198082";
			window.open(url, "_blank");
		}
		/**
		 * 监狱安全风险研判系统
		 */
		function openAqfxypSystem() {
			
			var url = "${ctx}/portal/aqfxyp/shouye";
			window.open(url, "_blank");
		}
		/**
		 * 跳转到一体化运维页面
		 */
		function openYthyw() {
			var cusNumber="${map.cusNumber}";
            var url;
			if("1105"==cusNumber){
                 url = "http://206.0.8.223/http/tekinfo/login/login.html?callback-url=http%3A%2F%2F206.0.8.223%2Fhttp%2FiCarrier%2Findex.html";

            }else if("1149"==cusNumber){
                 url = "http://210.192.10.242:9113/ssoservice/login?isAutoLogin=true&username_auto=admin&password_auto=jwcsfl3936&service=http://210.192.10.242:8890";

            }else if ("1103"==cusNumber){
                url="http://213.0.9.101/http/iCarrier/index.html";
            }else if ("1142"==cusNumber){
                url="http://210.96.47.250:8080/ipower-bj/";
            }
            if(url){
                window.open(url, "_blank");
            }



        }
		/**
		 * 狱外押解
		 */
		function openYuwaiyajie() {
			var url = "yuwaiyajie://";
			window.open(url, "_self");
		}
		/**
		 * 跳转到无人机防空页面
		 */
		function openWrjfk() {
			
			var url = "http://206.0.4.6/Myrtille/?__EVENTTARGET=&__EVENTARGUMENT=&server=206.0.4.5&user=administrator&password=68555587&connect=1";
			window.open(url, "_blank");
		}
		/**
		 * 改造质量考评
		 */
		function openZfgzzlkp() {
			
			var url = "${ctx}/portal/bj/zfzlkp";
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
		 * 华宇民警执法平台相关链接
		 */
		function openHyMjzfpt(project) {
			var cusNumber = $("#cusNumber").val();
			var userName = "";
			if(cusNumber == '1103') {
				userName = "1105235";// 延庆 卓文俊
			} else if(cusNumber == '1105') {
				userName = "admin";// 女子监狱 管理员
			} else if(cusNumber == '1142') {
				userName = "1109623";// 柳林 赵川
			} else if(cusNumber == '1145') {
				userName = "1108812";// 前进 蔡长海
			} else if(cusNumber == '1146') {
				userName = "1114572";// 潮白 王晓泉
			} else if(cusNumber == '1149') {
				userName = "1109006";// 垦华 纪文超
			}
			if(userName) {
				var url = "http://192.168.8.242:7777/jy-api/zfxx/login?user=" + userName + "&project=" + project;
				window.open(url, "_blank");
			} else {
				alert("请联系管理员配置链接地址");
			}
		}
		/**
		 * 跳转到垦华运维平台页面
		 */
		function openKhywpt() {
			var url = "http://210.192.10.242:9113/ssoservice/login?isAutoLogin=true&username_auto=admin&password_auto=jwcsfl3936&service=http://210.192.10.242:8890";
			window.open(url, "_blank");
		}

        function openMbdwxt(){
            var cusNumber="${map.cusNumber}";
            var url;
            if("1105"==cusNumber){

            }else if("1149"==cusNumber){//垦华
                url="http://210.192.80.100:8086/Login.aspx";
            }else if ("1103"==cusNumber){//延慶
                url="http://219.0.0.229:8180/localsense";
            }else if ("1142"==cusNumber){

            }
            if(url){
                window.open(url, "_blank");
            }


		}
	</script>
</body>
</html>
