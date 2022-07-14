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
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>女子监狱</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
  	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
  	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/lishijilu.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/realTimeMonitoring.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/yysb.css">
</head>

<body>
	<div class="header">
		<div class="logo">
			<img src="${ctx}/static/bj-cui/img/lishijilu/logo.png" alt="语音识别系统-女子监狱">
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
			</div>
			 --%>
		</div>
		<ul class="tolist home">
			<li class="tolist-item status home-page" onclick="openZnafpt()">
				首页
			</li>
			<li class="tolist-item status" id="monitor">
				实时监控
			</li>
			<li class="tolist-item status" id="history">
				监听查询

			</li>
			<li class="tolist-item status" id="analysis">
				敏感词统计
			</li>
		</ul>
	</div>
	<div class="container-box">
		<div id="layout1">
			<div data-options="region:'center'">
			</div>
		</div>
	</div>
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	
	<script>
		$("#layout1").layout({
			fit: true
		})
    //头部点击事件
    $(".tolist").on("click", ".tolist-item", function(e) {
      var target = $(e.target).closest("li"), id = target.attr("id");
      // alert("id = " + id);
      $(".home-page").removeClass("home-page");
      target.addClass("home-page");
      refreshCenter(id);
    })
   
    function refreshCenter(target) {
      var center = $("#layout1").layout("panel", "center");
      var requestHTML = "";
      switch(target) {
        case "monitor":
          requestHTML = "${ctx}/portal/yysb/ssjk";
          break;
        case "history":
          requestHTML = "${ctx}/portal/yysb/lsjl";
          break;
        case "analysis":
          requestHTML = "${ctx}/portal/yysb/mgcfx";
          break;
		case "realtime":
          requestHTML = "${ctx}/portal/yysb/mgcfxxx";
          break;
        default:
          break;
      }
      center.panel("refresh", requestHTML);
    }

	$(function () {
		jsConst.basePath = "${ctx}/";
		
		// 第一个菜单中的子菜单点击
		document.getElementById("monitor").click();
		
		initUserInfo();
	});
	
	function showDqyh() {
		$("#dqyh").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
	}
	function openZnafpt() {
		
		var url = "${ctx}/portal/bj/shouye";
		window.location.href = url;
	}
	</script>
</body>

</html>
