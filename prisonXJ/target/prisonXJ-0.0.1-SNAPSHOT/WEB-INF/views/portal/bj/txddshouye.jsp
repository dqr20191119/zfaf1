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

    <title>${map.orgName}通讯调度系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-xiugai.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
    <link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
    <style type="text/css">
        .tooltip{
            width: 600px;
        }
        
          #ypcd li{
      	font-size: 22px;
      }  
    </style>
</head>
<body>
<!-- 头部 -->
<header class="perspective">
    <img src="${ctx}/static/bj-cui/img/command/logo_zhihui_${map.orgCode}.png" alt="指挥中心logo" class="logo">
    <div class="header-content">
        <div class="header-item">
            <span class="icon iconfont icon-police2"></span>
            <span class="title" id="dqyh1">当前用户：</span>
        </div>
    </div>
     <ul id="ypcd" class="tolist home">
        <li class="tolist-item status home-page" onclick="openZnafpt()">
            	返回
        </li>
        <sec:authorize url="/zhdd/sy/txddxt/ksdj">
		<li class="tolist-item status"  onclick="openFxpgDialog(this,'realTimeTalk','可视对讲')">
			可视对讲
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/txddxt/gbkz">
		 <li class="tolist-item status"  >
			广播控制
			<ul class="tolist-menu">
				<sec:authorize url="/zhdd/sy/txddxt/gbkz/gbbf">
				<li class="tolist-menuitem" onclick="toAqfkDisplay('broadcastPlay')">广播播放</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/txddxt/gbkz/gbbfjl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,    'broadcastRecord','广播播放记录')">广播播放记录</li>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize> 
		<sec:authorize url="/zhdd/sy/txddxt/rhtx">
		<li class="tolist-item status"  >
			融合通信
			<ul class="tolist-menu">
				<sec:authorize url="/zhdd/sy/txddxt/rhtx/xxfs">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,'rcs','信息发送')">信息发送</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/txddxt/rhtx/txjl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,'search','通信记录')">通信记录</li>
				</sec:authorize>
			</ul>
		</li> 
		</sec:authorize>
    </ul> 
</header>
<!-- 内容 -->
<div class="container-box">

	
    <div id="layout1" fit="true" >
        <!-- 上半部分 chart 图 -->
         
 
		<cui:layoutRegion region="east" split="true" collapsed="false" style="width:280px;" onLoad="alarmLeave">
		
		
		</cui:layoutRegion>
	 <cui:layoutRegion region="center">
	 
		</cui:layoutRegion>
		
        
        
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none;">
    <!-- 根路径 -->
    <input id="rootPath" name="rootPath" value="${ctx}" />
</div>
<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>

<!-- 消息确认dialog -->
<cui:dialog id="confirmDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>


<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/bj-cui/js/base-xiugai.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script src="${ctx}/static/js/callback/callback.js"></script>
<script src="${ctx}/static/js/sgzf/base64.js"></script>


	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
	<%-- <script src="${ctx}/static/bj-cui/js/charts.js" type="text/javascript"></script> --%>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
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
	 
	<script src="${ctx}/static/system/videoClient.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/hz.mask.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/playbackVideo.js"></script>
	<script type="text/javascript" src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
 
 

	<script>
		jsConst.basePath = "${ctx}/";
	</script>
	<script src="${ctx}/static/system/common.js"></script>
	 
	<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
	<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
	<script src="${ctx}/static/js/map/prisonmap.js"></script>
	<!-- End add by linhe 2018-01-09 for request ajax and 3d map -->
	
	<!-- 4g执法 start-->
	<script src="${ctx}/static/js/sgzf/base64.js"></script>
	<script src="${ctx}/static/js/sgzf/es6-promise.min.js"></script>
	<script src="${ctx}/static/js/sgzf/stomp.js"></script>
	<script src="${ctx}/static/js/sgzf/tonmx_lib.min.js"></script>
	<!-- 4g执法 end--> 



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

    /**组件库初始化方法*/
    $('#layout1').layout({
        fit: true, //属性: 值
        onCreate: function () { //回调事件: 值

        }
    });
    
  

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
    
    
   $.parseDone(function () {
        $(".center:first").addClass("bigMore");
        jsConst.basePath = "${ctx}/";
        initUserInfo1();
    });
    
    function openZnafpt() {
        var url = "${ctx}/portal/zhshouye";
        window.location.href = url;
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
        
        if (name == 'realTimeTalk') {
        	url = jsConst.basePath+'/realTimeTalk/openDialog';
    	}
        if (name == 'rcs') {
        	url = jsConst.basePath+'/rcs/openDialog';
    	}
        if (name == 'search') {
        	url = jsConst.basePath+'/rcs/toIndex';
    	}
        if (name == 'broadcastRecord') {
        	 url = jsConst.basePath + '/broadcastRecord/openDialog';
    	}
        
        if (w == null || h == null) {
    		w = 1000;
    		h = 600;
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
    function toAqfkDisplay(name) {
    	var panel = $("#layout1").layout("panel", "east");
    	 if (name == "broadcastPlay") {
    		panel.panel("refresh",jsConst.basePath+"//broadcastLinkage/controlBroadcastTree");
    	}
    }
	
 
	
	function showDqyh1() {
		 var userName = "<%=user.getRealName()%>";
		 var dprtmntName  = "<%=user.getDprtmntName()%>";
		 var policeNo  = "<%=user.getPoliceNo()%>";
		 $("#dqyh1").append( "<span class=\"user\">" +userName + "</span> （" +dprtmntName + "）<br>警号：" + policeNo); 
		 return;
	}  
</script>
</body>
</html>
