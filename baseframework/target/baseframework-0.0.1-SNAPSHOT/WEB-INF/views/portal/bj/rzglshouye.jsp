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
<title>${map.orgName}日志管理</title>



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
								<sec:authorize url="/zhdd/sy/rzgl/rzcx">
					              <li class="system-item" onClick="openXxglDialog(event,'rzcx')">
					                <div class="system-content ">
					                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-psycho.png" alt="日志查询"><span
					                   id="rzgl_rzcx"   class="title">日志查询</span></div>
					                </div>
					              </li>
					              </sec:authorize >
					              <sec:authorize url="/zhdd/sy/rzgl/rztj">
                                <li class="system-item" onClick="openXxglDialog(event,'rztj')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-safety.png" alt="日志统计"><span
                                            id="rzgl_rztj"        class="title">日志统计</span></div>
                                    </div>
                                </li>
                                </sec:authorize>
                                <sec:authorize url="/zhdd/sy/rzgl/tbfx">
                                <li class="system-item" onClick="openXxglDialog(event,'rztb')">
                                    <div class="system-content ">
                                        <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-resource.png" alt="图表分析"><span
                                            id="rzgl_rztb"    class="title">图表分析</span></div>
                                    </div>
                                </li>
								</sec:authorize>
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

        function openXxglDialog(event, name) {
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
            } else if (name == "rzcx") {
                url = jsConst.basePath + '/xxhj/rzcx/toIndex';
            }else if (name == "rztb") {//日志图表
                url = jsConst.basePath + '/xxhj/rzcx/toIndextb';
            }else if (name == "rztj") {//日志统计
                url = jsConst.basePath + '/xxhj/rzcx/toIndextj';
            }

            $('#dialog').html("");
//        $('#dialog').dialog("destroy");
            if (w == null || h == null) {
                w = 1000;
                h = 600;
            }

            $('#dialog').dialog({
                width: w,
                height: h,
                title: $("#rzgl_" + name).text(),
                url: url
            });
            $("#dialog").dialog("open");

        }
		
        function openZnafpt() {
        	//var dprtmntCode = jsConst.DEPARTMENT_ID;
        	//var url = "${ctx}/portal/bj/shouye";
        	// 部门是武警的时候，直接跳到综合首页
        	//if(dprtmntCode == '110537') {
        		url = "${ctx}/portal/zhshouye";
        	//}
        	window.location.href = url;
        }
        
		
	</script>
</body>
</html>
