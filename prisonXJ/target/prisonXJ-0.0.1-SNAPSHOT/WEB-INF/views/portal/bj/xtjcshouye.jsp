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
<title>${map.orgName}系统集成</title>



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
}
</style>
</head>

<body>

	<cui:dialog id="dialog" autoOpen="false" iframePanel="true"
		reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>

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
					<!-- 【智慧安防 _菜单】 start -->
					<sec:authorize url="/zhdd/zy">
						<div class="content">
							<ul class="system">


								<sec:authorize url="/zhdd/sy/yjxt">
									<li class="system-item" onClick="openYuwaiyajie()">
										<div class="system-content ">
											<div class="system-text">
												<img class="system-img"
													src="${ctx}/static/bj-cui/img/icons/icon-position.png"
													alt="押解监控定位系统"><span class="title">押解监控定位系统</span>
											</div>
										</div>
									</li>
								</sec:authorize>
								
								
								<sec:authorize url="/zhdd/sy/dzxgxt">
									<li class="system-item" onClick="openDzxg()">
										<div class="system-content ">
											<div class="system-text">
												<img class="system-img"
													src="${ctx}/static/bj-cui/img/icons/icon-risk.png"
													alt="电子巡更系统"><span class="title">电子巡更系统</span>
											</div>
										</div>
									</li>
								</sec:authorize >
								<sec:authorize url="/zhdd/sy/qygkxt">
								<li class="system-item" onClick="openQygk()"  >
									<div class="system-content ">
										<div class="system-text">
											<img class="system-img"
												src="${ctx}/static/bj-cui/img/icons/icon-risk.png"
												alt="区域管控系统"><span class="title">区域管控系统</span>
										</div>
									</div>
								</li>
								</sec:authorize>
								<sec:authorize url="/zhdd/sy/hkspjkpt">
								 <li class="system-item" onClick="openSpjk()">
					                <div class="system-content ">
					                  <div class="system-text">
					                  <img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-video-watch.png" alt="海康视频监控平台">
					                  <span class="title">海康视频监控平台</span></div>
					                </div>
					              </li>
					              </sec:authorize>
					              <sec:authorize url="/zhdd/sy/jtdjxt">
					              <li class="system-item" onClick="openJtdj()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-video-watch.png" alt="视频监控系统">
					                  <span class="title">监听对讲系统</span>
					                  </div>
					                </div>
					              </li>
					              </sec:authorize>
					              
					              <sec:authorize url="/zhdd/sy/gbxt">
					               <li class="system-item" onClick="openGb()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-video-watch.png" alt="视频监控系统">
					                  <span class="title">广播系统</span>
					                  </div>
					                </div>
					              </li>
					              </sec:authorize>
					              <c:if test="${jyId!='4312' }">
					              <li class="system-item" onClick="openMj()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="移动警务系统">
					                  <span class="title">门禁管理系统</span>
					                  </div>
					                </div>
					              </li>
					         
								<li class="system-item" onClick="openZjbj()" >
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-alarm.png" alt="移动警务系统"><span
					                      class="title">周界报警管理系统</span></div>
					                </div>
					            </li>
					            </c:if>

					              <li class="system-item" onClick="openHj()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-meeting.png" alt="移动警务系统"><span
					                      class="title">会见管理系统</span></div>
					                </div>
					              </li>
					              
								<c:if test="${jyId == '4304' }">
					              <li class="system-item" onClick="openHjyysbxt()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="会见语音识别系统">
					                  <span class="title">会见语音识别系统</span>
					                  </div>
					                </div>
					              </li>
					              
					              <li class="system-item" onClick="openHkrldmxt()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="海康人脸点名系统">
					                  <span class="title">海康人脸点名系统</span>
					                  </div>
					                </div>
					              </li>
					              
					              <li class="system-item" onClick="openAqgzxt()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="安全感知系统">
					                  <span class="title">安全感知系统</span>
					                  </div>
					                </div>
					              </li>
					              
					              <li class="system-item" onClick="openWlwzdgkxt()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="物联网终端管控系统">
					                  <span class="title">物联网终端管控系统</span>
					                  </div>
					                </div>
					              </li>
					              <li class="system-item" onClick="openBffwq()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="备份服务器">
					                  <span class="title">备份服务器</span>
					                  </div>
					                </div>
					              </li>
					              <li class="system-item" onClick="openMgcpt()">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="敏感词平台">
					                  <span class="title">敏感词平台</span>
					                  </div>
					                </div>
					              </li>
					            </c:if>
							</ul>
						</div>
					</sec:authorize>
					<!-- 【智慧安防 _菜单】 end -->

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

		function openZnafpt() {
			//var dprtmntCode = $("input[id='dprtmntCode']").val();
			//var url = "${ctx}/portal/bj/shouye";
			// 部门是武警的时候，直接跳到安防立体防控首页
			//if (dprtmntCode == '110537') {
				url = "${ctx}/portal/zhshouye";
			//}
				window.location.href = url;
		}

		 
		 
		/**
		 * 智能语音识别系统
		 */
		function openZnyysb() {
			var url = "http://192.168.8.82:8080/jy-yysb/cas";
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
		 * 跳转到运维管理页面
		 */
		function openYthyw() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				url = "http://34.67.120.246/#/app/signal";
			} else if ("4304" == cusNumber) {//永州监狱
				url = "http://34.202.32.12:8080";
			} else if ("4307" == cusNumber) {//女子监狱
				url = "http://34.72.4.10:8080/resource/login.html";
			} else if ("4312" == cusNumber) {//岳阳监狱
				url = "http://34.205.80.81:8080";
			} else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.69.4.10:8080/resource/login.html";
			}
			if (url) {
				window.open(url, "_blank");
			}
		}
		/**
		 * 狱外押解
		 */
		function openYuwaiyajie() {
			var url = "";
			var cusNumber = "${map.cusNumber}";
			if ("4303" == cusNumber) {//雁南
				url = "http://34.64.4.101:3000/3GEscort//LoginAction!login.action?loginName=yannan&passWord=yannan";
			} else if ("4304" == cusNumber) {//永州监狱
				url = "http://34.64.4.101:3000/3GEscort//LoginAction!login.action?loginName=yongzhou&passWord=yongzhou";
			} else if ("4307" == cusNumber) {//女子监狱
				url = "http://34.64.4.101:3000/3GEscort//LoginAction!login.action?loginName=nvzi&passWord=nvzi";
			} else if ("4312" == cusNumber) {//岳阳监狱
				url = "http://34.64.4.101:3000/3GEscort//LoginAction!login.action?loginName=yueyang&passWord=yueyang";
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.64.4.101:3000/3GEscort//LoginAction!login.action?loginName=huaihua&passWord=huaihua";
			}
			window.open(url, "_blank");
		}
		
		/**
		* 电子巡更模块
		*/
		function openDzxg(){
			var url = "";
			var cusNumber = "${map.cusNumber}";
			if ("4303" == cusNumber) {//雁南
				url = "http://34.64.4.109/enter/login.html?account=YNDDXG&password=111111&url=/patrol/patrol-task";
			} else if ("4304" == cusNumber) {//永州监狱
				url = "http://34.64.4.109/enter/login.html?account=YZDDXG&password=111111&url=/patrol/patrol-task";
			} else if ("4307" == cusNumber) {//女子监狱
				url = "http://34.64.4.109/enter/login.html?account=NZDDXG&password=111111&url=/patrol/patrol-task";
			} else if ("4312" == cusNumber) {//岳阳监狱
				url = "http://34.64.4.109/enter/login.html?account=YYDDXG&password=111111&url=/patrol/patrol-task";
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.64.4.109/enter/login.html?account=HHDDXG&password=111111&url=/patrol/patrol-task";
			}
			window.open(url, "_blank");
		}
		
		/**
		* 视频监控系统
		*/
		function openSpjk(){
			var url = "";
			var redirectUrl = "/vss/home/index.action?rurl=/video/vss_perview";
			var userName = "";
			var password = "";
			var token = "";
			var myTokenUrl = "";
			var tokenUrl = "";
			var ip = "";
			var cusNumber = "${map.cusNumber}";
			if ("4303" == cusNumber) {//雁南
				//url = "http://34.205.24.3/";
				ip = "34.205.24.3";
			
				tokenUrl = "http://34.205.24.3/home/ssoTokenKey.action";
				userName = "admin";
				password = "129C1EB98EDBD6A0C1A0316369671415";
				myTokenUrl = jsConst.basePath + 'lg/loginCtrl/getToken';
				
			
				//window.open(url, "_blank");
			} else if ("4304" == cusNumber) {//永州监狱
				//url = "http://34.205.32.1";
				ip = "34.205.32.1";
				tokenUrl = "http://34.205.32.1/home/ssoTokenKey.action";
				userName = "admin";
				password = "5E6AF8D0441C9E55A64B9492C204B4DF";
				myTokenUrl = jsConst.basePath + 'lg/loginCtrl/getToken';
			
				//window.open(url, "_blank");
			} else if ("4307" == cusNumber) {//女子监狱
				//url = "http://34.205.64.1/";
				ip = "34.205.64.1";
				tokenUrl = "http://34.205.64.1/home/ssoTokenKey.action";
				userName = "admin";
				password = "518BEDF671377055E944F0BCF5EFC005";
				myTokenUrl = jsConst.basePath + 'lg/loginCtrl/getToken';
				
				//window.open(url, "_blank");
			} else if ("4312" == cusNumber) {//岳阳监狱
				ip = "34.205.80.15";
				tokenUrl = "http://34.205.80.15/home/ssoTokenKey.action";
				userName = "admin";
				password = "B18F189B0AD3C2F343889549E13EB050";
				myTokenUrl = jsConst.basePath + 'lg/loginCtrl/getToken';
				
				//url = "http://34.205.80.15/";
				//window.open(url, "_blank");
			}else if ("4336" == cusNumber) {//怀化监狱
				//url = "https://34.69.40.77/portal";
				ip = "34.205.40.29";
				tokenUrl = "http://34.205.40.29/home/ssoTokenKey.action";
				userName = "admin";
				password = "06A43248E35685AC31B28C38C244D34A";
				myTokenUrl = jsConst.basePath + 'lg/loginCtrl/getToken';
				
 				//window.open(url, "_blank");
			}

			$.ajax({
				type : 'post',
				url : myTokenUrl,
				data : {
					'url' : tokenUrl
				},
				dataType : 'text',
				success : function(data) {
					 url = "http://"+ ip +"/home/ssoLogin.action?redirectUrl="+ redirectUrl +"&userName="+ userName +"&password="+ password +"&token=" + data;
					 window.open(url, "_blank");
				},
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert(textStatus);
				}
			});
			
		}
		
		/**
		* 监听对讲系统
		*/
		function openJtdj(){
			var url = "";
			var cusNumber = "${map.cusNumber}";
			
			// 1. 传入参数为： {"username":"test","password":"123456","isadmin":"1","display":"测试"}
			var username = "admin";     // 用户名
			var password = "admin";   // 密码
			var display = "测试";      // 显示名
			var isadmin = "1";         // 1为管理员， 0为普通用户
			var lang = "zh";           // 语言（zh/en)
		    var theme = "green";       // 显示风格(green/black/darkgreen)
		    var hidetitle = "0";       // 1:隐藏标题栏 0：显示标题栏	
			var context = "{'username':'" + username + "','password':'" + password + "','display':'" + display + "','isadmin':'" + isadmin + "','lang':'" + lang + "','theme':'" + theme + 
			 "','hidetitle':'" + hidetitle + "'}";
			
			// 2. 参数加密
			var b = new SPON_Base64();
			var encoderData = b.encode(context);
			
			if ("4303" == cusNumber) {//雁南
				url = "http://34.67.79.253/";
			} else if ("4304" == cusNumber) {//永州监狱
				url = "http://34.68.79.2:8080/#";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.72.115.22/index_empty.html?spon_token=" + encoderData;
			} else if ("4312" == cusNumber) {//岳阳监狱
				url = "http://34.74.94.11/index_empty.html?spon_token=" + encoderData;
			}else if ("4336" == cusNumber) {//怀化监狱
				//url = "http://10.43.102.1/index_empty.html?spon_token=" + encoderData;
				url = "http://34.205.40.110/index_empty.html?spon_token=" + encoderData;
			}
			window.open(url, "_blank");
		}
		

		/**
		* 广播系统
		*/
		function openGb(){
			var url = "";
			var cusNumber = "${map.cusNumber}";
			
			// 1. 传入参数为： {"username":"test","password":"123456","isadmin":"1","display":"测试"}
			var username = "admin";     // 用户名
			var password = "admin";   // 密码
			var display = "测试";      // 显示名
			var isadmin = "1";         // 1为管理员， 0为普通用户
			var lang = "zh";           // 语言（zh/en)
		    var theme = "green";       // 显示风格(green/black/darkgreen)
		    var hidetitle = "0";       // 1:隐藏标题栏 0：显示标题栏	
			var context = "{'username':'" + username + "','password':'" + password + "','display':'" + display + "','isadmin':'" + isadmin + "','lang':'" + lang + "','theme':'" + theme + 
			 "','hidetitle':'" + hidetitle + "'}";
			
			// 2. 参数加密
			var b = new SPON_Base64();
			var encoderData = b.encode(context);
			
			if ("4303" == cusNumber) {//雁南- cs系统，无法对接
				alert("请打开本地cs客户端查看系统！");
				//url = "http://34.67.4.11/index_empty.html?spon_token=" + encoderData;
			} else if ("4304" == cusNumber) {//永州监狱
				//url = "http://34.68.79.99/";
				alert("请打开本地cs客户端查看系统！");
				//url = "http://34.68.6.238:40080/pis_voice_QA/user/login"; 
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.72.115.22/index_empty.html?spon_token=" + encoderData;
			} else if ("4312" == cusNumber) {//岳阳监狱
				 //url = "http://34.74.94.11/#";
				url = "http://34.74.94.11/index_empty.html?spon_token=" + encoderData;
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.205.40.110/index_empty.html?spon_token=" + encoderData;
			}
			window.open(url, "_blank");
		}
		
		
		/**
		* 海康8700平台
		*/
		function openHk(){
			var url = "";
			var cusNumber = "${map.cusNumber}";
			if ("4303" == cusNumber) {//雁南
				url = "http://34.205.24.3/";
			} else if ("4304" == cusNumber) {//永州监狱
				url = "http://34.205.32.1/home/login";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.205.64.1/";
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://34.205.80.15/";
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.205.40.29/";
			}
			window.open(url, "_blank");
		}
		
		
		/**
		* 区域管控 -- 目前只有永州有这个 ， 控制显示
		function showQygk(){
			var cusNumber = "${map.cusNumber}";
			 if ("4304" == cusNumber) {//永州监狱
				$("#qygk_li").show();
			} else {
				$("#qygk_li").hide();
			}
		}
		*/
		
		/**
		* 区域管控 -- 目前只有永州有这个 ， 控制打开连接
		*/
		function openQygk(){
			var cusNumber = "${map.cusNumber}";
			var url = "";
			if ("4303" == cusNumber) {//雁南
				url = "http://34.67.4.12:10001/#/login?name=admin&pwd=admin";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.64.4.110/enter/login.html?account=YZDDXG&password=111111&url=/monitor/index";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.72.73.101:8888";
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://34.205.80.133:10001/#/login?name=admin&pwd=admin";
			}else if ("4336" == cusNumber) {//怀化监狱
				 url = "http://34.205.40.39:10001/#login?name=admin&pwd=admin";
			}
			window.open(url, "_blank");
		}
		
		/**
		* 目标定位系统
		*/
		function openMbdwxt() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				 url = "https://34.205.24.20/cas/login";
			} else if ("4304" == cusNumber) {//永州监狱
				// url = "http://34.64.4.109/enter/login.html?account=YZDDXG&password=111111&url=/monitor/index";
			} else if ("4307" == cusNumber) {//女子监狱
				// url = "http://34.72.73.101:8888";
			} else if ("4312" == cusNumber) {//岳阳监狱
				// url = "http://34.205.80.133:10001/#/login?name=admin&pwd=admin";
			}else if ("4336" == cusNumber) {//怀化监狱
				// url = "http://34.205.40.39:10001";
			}
			 
			if (url) {
				window.open(url, "_blank");
			}
		}
		
		/**
		* 门禁系统
		*/
		function openMj() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				 url = "http://34.67.4.10/Account/LogOn.aspx";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.68.12.180:8010/Account/LogOn.aspx";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.72.115.28";
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://34.74.66.2";
			}else if ("4336" == cusNumber) {//怀化监狱
				 url = "http://34.69.32.10:8010";
			}
			 
			if (url) {
				window.open(url, "_blank");
			}

		}
		
		/**
		* 周界报警
		*/
		function openZjbj() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				 url = "http://34.67.65.253";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.68.64.22";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://10.43.22.168";
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://34.74.65.4";
			}else if ("4336" == cusNumber) {//怀化监狱
				 url = "http://34.69.12.240";
			}
			 
			if (url) {
				window.open(url, "_blank");
			}

		}
		
		/**
		* ERP劳动项目管理系统
		*/
		function openErp() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				// url = "http://34.67.65.253";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.68.110.2:8080/user/log/loginPage.do";
			} else if ("4307" == cusNumber) {//女子监狱
				
			} else if ("4312" == cusNumber) {//岳阳监狱
				
			}else if ("4336" == cusNumber) {//怀化监狱
				
			}
			 
			if (url) {
				window.open(url, "_blank");
			}

		}
		
		/**
		* 会见管理系统
		*/
		function openHj() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				 url = "http://34.205.24.10:8080/ivms/login!jumpIndex.action";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.68.87.1:9090/ivrjailyz/index.jsp";
			} else if ("4307" == cusNumber) {//女子监狱
				 url = "http://34.72.115.44:8080/ivms/login!jumpIndex.action";
			} else if ("4312" == cusNumber) {//岳阳监狱
				url = "http://172.16.205.110:8080/ivms";
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.69.50.201/index.php";
			}
			if (url) {
				window.open(url, "_blank");
			}
		}
		
		/**
		* 狱务公开
		*/
		function openYwgk() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				 url = "http://34.211.25.10:8080/gkywgk/loginPage.page?token=random&random=-996462771&from=page&e=";
			} else if ("4304" == cusNumber) {//永州监狱
				 url = "http://34.68.102.5:8080/gkywgk/loginPage.page";
			} else if ("4307" == cusNumber) {//女子监狱
				
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://172.16.1.210:8080/gkywgk";
				
			}else if ("4336" == cusNumber) {//怀化监狱
				url = "http://34.205.40.67:8080/gkywgk/";
			}
			if (url) {
				window.open(url, "_blank");
			}
		}
		
		/**
		* 监狱多功能管理系统
		*/
		function openJydgnglxt() {
			var cusNumber = "${map.cusNumber}";
			var url;
			if ("4303" == cusNumber) {//雁南
				// url = "http://34.67.65.253";
			} else if ("4304" == cusNumber) {//永州监狱
				// url = "http://34.68.102.5:8080/gkywgk/loginPage.page";
			} else if ("4307" == cusNumber) {//女子监狱
				
			} else if ("4312" == cusNumber) {//岳阳监狱
				 url = "http://172.16.1.15/esweb";
			}else if ("4336" == cusNumber) {//怀化监狱
				
			}
			if (url) {
				window.open(url, "_blank");
			}
		}
		
		/**
		* 会见语音识别系统 
		*/
		function openHjyysbxt() {
			var cusNumber = "${map.cusNumber}";
			var url = "http://34.68.6.238:40080/pis_voice_QA/user/login";
			window.open(url, "_blank");
		}
		/**
		* 海康人脸点名系统
		*/
		function openHkrldmxt() {
			var cusNumber = "${map.cusNumber}";
			var url = "https://34.68.4.10/portal";
			window.open(url, "_blank");
		}
		/**
		* 安全感知系统
		*/
		function openAqgzxt() {
			var cusNumber = "${map.cusNumber}";
			var url = "https://34.64.5.47/apps/secvisual/login/login.html";
			window.open(url, "_blank");
		}
		
		/**
		* 物联网终端管控系统
		*/
		function openWlwzdgkxt() {
			var cusNumber = "${map.cusNumber}";
			var url = "http://34.68.4.32:9080/iotmgmt/a?login";
			window.open(url, "_blank");
		}
		/**
		* 备份服务器
		*/
		function openBffwq() {
			var cusNumber = "${map.cusNumber}";
			var url = "http://34.68.4.200/";
			window.open(url, "_blank");
		}
		/**
		* 敏感词平台
		*/
		function openMgcpt() {
			var cusNumber = "${map.cusNumber}";
			var url = "http://34.68.4.50:8083";
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
		 * 首页退出
		 */
		function syLogout() {
			$.confirm("确定要退出系统吗？", function(r) {
				if (r) {
					var ur = jsConst.basePath + 'lg/loginCtrl/logout';
					$.ajax({
						type : 'post',
						url : ur,
						data : {
							'userId' : jsConst.USER_ID
						},
						dataType : 'json',
						success : function(data) {
							var user = null;
							if (data.result) {
								/* user = data.userBean; */
								window.location.href = ssoPage();
							} else {
								alert("退出失败：" + data.respDesc);
							}
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(textStatus);
						}
					});
				}
			});
		}

		
		
		/**
		 * 值排班管理
		 */
		function openZpbglSystem() {
			var url = "${ctx}/portal/zpbgl/shouye";
			window.open(url, "_blank");
		}
		
		/**
		 * 通讯调度系统
		 */
		function openTxddxx() {
			var url = "${ctx}/portal/txdd/shouye";
			window.open(url, "_blank");
		}
		
	</script>
</body>
</html>
