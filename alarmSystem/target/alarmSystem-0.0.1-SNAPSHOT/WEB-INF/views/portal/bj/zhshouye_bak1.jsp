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
  <title></title>
  <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
  <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/menhu.css" />
</head>

<body>
  <div class="container">
    <header class="perspective">
      <div class="header">
        <div class="header-content">
          <div class="header-item date">
            <span class="icon iconfont icon-datepiceker"></span>
            <span class="title">${dqrq}</span>
          </div>
          <div class="header-item">
            <span class="icon iconfont icon-police2"></span>
            <span class="title" id="dqyh1">当前用户：</span>
          </div>
          <div class="header-item dropdow">
            <span class="icon iconfont icon-xialadown"></span>
          </div>
        </div>
      </div>
    </header>
    <div class="center">
      <div class="logo-wrapper">
        <img src="${ctx}/static/bj-cui/img/logo.png" alt="北京市监狱（戒毒）管理局智能安防平台 -女子监狱" class="logo">
      </div>
      <div class="nav-wrapper">
        <ul class="nav">
          <li class="nav-item law" onclick="openZhzf()">
            <div class="nav-item-wrapper">
              <div class="nav-logo">
                <span class="iconfont center-absolute icon-law"></span>
              </div>
              <div class="nav-text">
                <span class="title center-absolute">智慧执法</span>
              </div>
            </div>
          </li>
          <li class="nav-item law" onclick="openZhjy();">
            <div class="nav-item-wrapper">
              <div class="nav-logo">
                <span class="iconfont center-absolute icon-education1"></span>
              </div>
              <div class="nav-text">
                <span class="title center-absolute">智慧教育</span>
              </div>
            </div>
          </li>
          <li class="nav-item law" onclick="openZnafpt()">
            <div class="nav-item-wrapper">
              <div class="nav-logo">
                <span class="iconfont center-absolute icon-safety"></span>
              </div>
              <div class="nav-text">
                <span class="title center-absolute">智慧安防</span>
              </div>
            </div>
          </li>
          <li class="nav-item law" onclick="openZhdj();">
            <div class="nav-item-wrapper">
              <div class="nav-logo">
                <span class="iconfont center-absolute icon-team1"></span>
              </div>
              <div class="nav-text">
                <span class="title center-absolute">智慧队建</span>
              </div>
            </div>
          </li>
          <li class="nav-item law" onclick="openZhbg();">
            <div class="nav-item-wrapper">
              <div class="nav-logo">
                <span class="iconfont center-absolute icon-office"></span>
              </div>
              <div class="nav-text">
                <span class="title center-absolute">智慧办公</span>
              </div>
            </div>
          </li>
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
        </ul>
      </div>
    </div>
  </div>
  
  <script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
  <script src="${ctx}/static/system/common.js"></script>
  <script src="${ctx}/static/system/jsConst.js"></script>
  
  <script type="text/javascript">
  		$(function () {
			jsConst.basePath = "${ctx}/";

			initUserInfo1();
		});
		
		function showDqyh1() {
			$("#dqyh1").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
		
		function openZnafpt() {
			
			var url = "${ctx}/portal/bj/shouye";
			window.open(url, "_blank");
		}
		/**
		 * 打开智慧执法新页面
		 */
		function openZhzf() {
			// alert("zffff");
			var url = "http://192.168.8.242/index/html/index.html";
			window.open(url, "_blank");
		}
		/**
		 * 打开智慧教育新页面
		 */
		function openZhjy() {
			// alert("zffff");
			var url = "http://192.168.8.4:8888/jygz";
			window.open(url, "_blank");
		}
		/**
		 * 打开智慧队建新页面
		 */
		function openZhdj() {
			// alert("zffff");
			var url = "http://192.168.8.51:8089";
			window.open(url, "_blank");
		}
		/**
		 * 打开智慧办公新页面
		 */
		function openZhbg() {
			// alert("zffff");
			var url = "http://206.0.0.16";
			window.open(url, "_blank");
		}
		
  </script>
</body>

</html>
