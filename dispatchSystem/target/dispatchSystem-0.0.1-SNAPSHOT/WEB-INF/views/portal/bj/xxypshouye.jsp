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

    <title>${map.orgName}信息研判</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-xiugai.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
    <link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .tooltip{
            width: 600px;
        }
      #ypcd li{
      	font-size: 21px;
      }  
      
     #ypcd>li>ul li{
     	width: 200px
     }   
    </style>
</head>
<body>
<!-- 头部 -->
<header class="perspective" >
    <img src="${ctx}/static/bj-cui/img/command/logo_zhihui_${map.orgCode}.png" style="height: 100px;width: 450px;" alt="指挥中心logo" class="logo">
    <div class="header-content">
        <div class="header-item" >
            <span class="icon iconfont icon-police2"></span>
            <span class="title" id="dqyh1">当前用户：</span>
        </div>
    </div>
    <ul id="ypcd" class="tolist home" >
        <li class="tolist-item status home-page" onclick="openZnafpt()">
            	返回
        </li>
				<sec:authorize url="/zhdd/sy/xxyp/jyaqfxyp">
					<li class="tolist-item status" onclick="openAqfxypSystem()">
						监狱安全风险研判</li>
				</sec:authorize>
				<!-- 
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfwxxpg">
					<li class="tolist-menuitem" onclick="wxxpg()">
						 罪犯危险性评估
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfswlpg">	
					<li class="tolist-menuitem" onclick="yswzfxzf()">
						罪犯食物量评估
					</li>
					</sec:authorize>
					 -->
				<sec:authorize url="/zhdd/sy/xxyp/mjlzpg">
					<li class="tolist-item status">民警履职评估
						<ul class="tolist-menu">
							<sec:authorize url="/zhdd/sy/xxyp/mjlzpg/mjzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','民警指标维护','ming.jing')">
									民警指标维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/mjlzpg/zgzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','职工指标维护','zhi.gong')">
									职工指标维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/mjlzpg/bzwryzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','编制外人员指标维护','bian.zhi.wai.he.tong.gong')">
									编制外人员指标维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/mjlzpg/mjlzpg">
								<li class="tolist-menuitem" onclick="ryglfxfx()">民警履职评估</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>

				<sec:authorize url="/zhdd/sy/xxyp/mjzfsxxpg">
					<li class="tolist-item status">民警执法实效性评估
						<ul class="tolist-menu">
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/sjldywcs">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','省局领导、业务处室关注点指标维护','shi.jv.ling.dao')">
									省局领导、业务处室关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/jqyrzbgzd">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','监区一日值班关注点指标维护','jian.qu.yi.ri.zhi.ban')">
									监区一日值班关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/zhzxgzdzb">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','指挥中心关注点指标维护','zhi.hui.zhong.xin')">
									指挥中心关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/yzglgzdzb">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','狱政管理关注点指标维护','yu.zheng.guan.li')">
									狱政管理关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/xfzxgzdzb">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','刑罚执行关注点指标维护','xing.fa.zhi.xing')">
									刑罚执行关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/jygzgzdzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','教育改造关注点指标维护','jian.yu.gai.zao')">
									教育改造关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/ldgzgzdzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','劳动改造关注点指标维护','lao.dong.gai.zao')">
									劳动改造关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/hqbzgzdzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','后勤保障关注点指标维护','hou.qin.bao.zhang')">
									后勤保障关注点指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/mjzfsxxpg/mjsxxpg">
								<li class="tolist-menuitem" onclick="ryglyffx()">民警执法实效性评估
								</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>

				<sec:authorize url="/zhdd/sy/xxyp/sbssyxqkpg">
					<li class="tolist-item status">设备设施运行情况评估
						<ul class="tolist-menu">
							<sec:authorize url="/zhdd/sy/xxyp/sbssyxqkpg/sszbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','区域指标维护','di.xie.gang')">
									设施指标维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/sbssyxqkpg/sbzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','设备指标维护','wu.xie.gang')">
									设备指标维护</li>
							</sec:authorize>
							<sec:authorize
								url="/zhdd/sy/xxyp/sbssyxqkpg/sbyxqkpg">
								<li class="tolist-menuitem" onclick="sbyxqk()">设备运行情况评估</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>
				<!-- 
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjysqkpg">	
					<li class="tolist-menuitem" onclick="yswzfx()">
						民警饮食情况评估
					</li>
					</sec:authorize>
					 -->
				<sec:authorize url="/zhdd/sy/xxyp/yqjqyp">
					<li class="tolist-item status">狱情警情研判
						<ul class="tolist-menu">
							<sec:authorize url="/zhdd/sy/xxyp/yqjqyp/yqjqzbwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'fxdgl','狱情警情指标维护','qing.xie.gang')">
									狱情警情指标维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/yqjqyp/yqjqyp">
								<li class="tolist-menuitem" onclick="yqyjpg()">狱情警情研判</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>

				<%--<sec:authorize url="/zhdd/sy/xxyp/yatstygl">
					<li class="tolist-item status"
						onclick="openFxpgDialogSy(event,'fxgkgl','预案推送统一管理 ','')">
						预案推送统一管理</li>
				</sec:authorize>--%>

				<%--<sec:authorize url="/zhdd/sy/xxyp/zfgzzlpg">
					<li class="tolist-item status">罪犯改造质量评估
						<ul class="tolist-menu">
							<sec:authorize
								url="/zhdd/sy/xxyp/zfgzzlpg/zfgzzlpgwh">
								<li class="tolist-menuitem"
									onclick="openFxpgDialogSy(event,'wdgzwh','罪犯改造质量评估维护','')">
									罪犯改造质量评估维护</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/xxyp/zfgzzlpg/zfgzzlpg">
								<li class="tolist-menuitem" onclick="openZfgzzlkp()">
									罪犯改造质量评估</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>--%>
		
		
		<%--
		<li class="tolist-item status" onclick="openFxpgDialog(this,   'zbfx')">
			值班日志
		</li>
		<li class="tolist-item status" onclick="openFxpgDialog(this,  'zbfx')">
			交接班
		</li>
		 --%>
    </ul>
</header>
<!-- 内容 -->
<div class="container-box">
    <div id="layout1">
        <!-- 上半部分 chart 图 -->
        <div data-options="region:'north'" class="main" style="height:50%;min-height: 410px">
             
        </div>
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none;">
    <!-- 根路径 -->
    <input id="rootPath" name="rootPath" value="${ctx}" />
</div>
<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>



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
jsConst.basePath = "${ctx}/";
//获取用户信息是异步的，会造成，所以这一块先进行赋值

jsConst.ORG_CODE = "<%=user.getOrgCode()%>";					// 机构代码
jsConst.CUS_NAME = "<%=user.getOrgName()%>";				// 机构名称
jsConst.CUS_NUMBER = "<%=user.getCusNumber()%>";			// 机构代码
jsConst.PRISON_ORG_CODE = "<%=user.getCusNumber()%>";			// 监狱机构代码
jsConst.ROOT_ORGA_CODE = "<%=user.getCusNumber()%>";			// 当前选中监狱编号
jsConst.ROOT_ORGA_NAME = "<%=user.getOrgName()%>";			// 当前选中监狱名称
jsConst.USER_ID = "<%=user.getUserId()%>";						// 用户ID
jsConst.USER_NAME = "<%=user.getUserName()%>";					// 用户名
jsConst.REAL_NAME = "<%=user.getRealName()%>";					// 用户真实姓名
jsConst.POLICE_CODE = "<%=user.getPoliceNo()%>";				// 警员编号
jsConst.DEPARTMENT_ID = "<%=user.getDprtmntCode()%>";			// 部门Code
jsConst.DEPARTMENT_NAME = "<%=user.getDprtmntName()%>";			// 部门名称
jsConst.ROLE_LIST = "<%=user.getRoles()%>";
jsConst.USER_LEVEL = "<%=user.getUserLevel()%>";		// 用户等级
jsConst.LOGIN_USER_KEY = "<%=user.getPoliceNo()%>";					// 登录用户标识(前面用userId的数据有问题的改库)
jsConst.SPECIAL_POLICE = "<%=user.getIsSpecialPolice()%>";				// 是否特警队员



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
    
    /**
     * 安全风险研判跳转
     */
    function openAqfxypSystem() {
    	
    	var url = "${ctx}/portal/aqfxyp/shouye";
    	window.open(url, "_self");
    }

    //危险性评估
      function wxxpg (){
    			   var url = "http://192.168.8.187/jy-wxpg/form/5be0f9483d0a86f8d24fca432843b784/insert";
    				window.open(url, "_blank");
    		   }
    //饮食物资分析罪犯
    function yswzfxzf(){
    	   var url = "http://192.168.8.188:8080/PMS/charts/criminal.html";
    		window.open(url, "_blank");
    }
    function openFxpgDialogSy(event, name,zhi,code) {
    	var event = window.event || event;
    	if (event && event.stopPropagation) {
    		event.stopPropagation();
    	} else {
    		window.event.cancelBubble = true;
    	}
    	//event.preventDefault();
    	var url = "";
    	var w = null;
    	var h = null;
    	if (name == 'fxdgl') {
    		url = jsConst.basePath + '/wwjg/fxdgl/toIndex?sjfwName='+code;
    		w = 1000;
    		h = 800;
    	}else if (name == 'fxgkgl') {
    		url = jsConst.basePath + '/wwjg/fxgkgl/toIndex';
    		w = 1000;
    		h = 800;
    	} else if (name == 'wdgzwh') {
    		url = jsConst.basePath + '/wwjg/fxgkgl/toIndexWdgz';
    		w = 1000;
    		h = 800;
    	} 
    	
    	$('#dialog').html("");
    	$('#dialog').dialog({
    		width : w,
    		height : h,
    		title : zhi,
    		url : url
    	});
    	$("#dialog").dialog("open");
    }

    //民警管理风险分析
    function ryglfxfx(){
    		var url = "${ctx}/portal/sjfxyp/ryglfxfx";
    		window.open(url, "_blank");
    	}
    	
    //民警执法实效性
    function ryglyffx(){
    	var url = "${ctx}/portal/sjfxyp/ryglyffx";
    	window.open(url, "_blank");
    }

    //设备运行情况评估
    function sbyxqk(){
    		var url = "${ctx}/portal/sjfxyp/sbyxqk";
    		window.open(url, "_blank");
    	}
    //饮食物资分析民警
    function  yswzfx (){
    	   var url = "http://192.168.8.188:8080/PMS/charts/police.html";
    		window.open(url, "_blank");
    }
    //狱情预警评估
    function yqyjpg(){
    	   var url = "${ctx}/portal/sjfxyp/yqyjpg";
    		window.open(url, "_blank");
    }
    //改造质量考评
    function openZfgzzlkp() {
    		
    		var url = "${ctx}/portal/bj/zfzlkp";
    		window.open(url, "_blank");
    	}
    
    
    
   $.parseDone(function () {
        $(".center:first").addClass("bigMore");
        jsConst.basePath = "${ctx}/";
        showDqyh1();
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
        
        if (name == 'gwgl') {
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
    
    function openMenuDialog(obj, event, name) {

    	var event = window.event || event;
    	// event.stopPropagation();
    	if (event && event.stopPropagation) {
    		event.stopPropagation();
    	} else {
    		window.event.cancelBubble = true;
    	}
    	// event.preventDefault();
    	var url = "";
    	var w = null;
    	var h = null;
    	var title = null;
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
    	} else if (name == 'ewdwwh') {// //二维图层点位维护
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
    		url = jsConst.basePath + '/monitor/jdjc';
    		w = 1000;
    		h = 700;
    	} else if (name == 'jddlb') {
    		url = jsConst.basePath + '/monitor/jddlb';
    		w = 1000;
    		h = 700;
    	} else if (name == 'realTimeTalk') {
    		url = jsConst.basePath + '/realTimeTalk/openDialog';
    		w = 1000;
    		h = 600;
    	} else if (name == 'group') {
    		w = 1200;
    		h = 600;
    		url = jsConst.basePath + '/groupManage/index';
    	} else if (name == 'screenSwitch') {
    		url = jsConst.basePath + '/screenSwitch/openDialog';
    	} else if (name == 'wldctb-bj') {
    		url = jsConst.basePath + '/inspect/editDialog';
    	} else if (name == 'wldctb-lb') {
    		url = jsConst.basePath + '/inspect/inspectListDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'wldctb-sp') {
    		url = jsConst.basePath + '/inspect/checkDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'wldctb-hz') {
    		url = jsConst.basePath + '/inspect/recordDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'bddctb-bj') {
    		url = jsConst.basePath + '/inspectlocal/editDialog';
    	} else if (name == 'bddctb-sp') {
    		url = jsConst.basePath + '/inspectlocal/checkDialog';
    	} else if (name == 'bddctb-hz') {
    		url = jsConst.basePath + '/inspectlocal/recordDialog';
    	} else if (name == 'tbzg-fq') {
    		url = jsConst.basePath + '/xxyp/change/launchDialog';
    	} else if (name == 'tbzg-zg') {
    		url = jsConst.basePath + '/xxyp/change/changeDialog';
    	} else if (name == 'tbzg-sp') {
    		url = jsConst.basePath + '/xxyp/change/checkDialog';
    	} else if (name == 'tbzg-hz') {
    		url = jsConst.basePath + '/xxyp/change/recordDialog';
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
    	} else if (name == 'excel') {// excel
    		url = jsConst.basePath + '/zbgl/kspb/toIndex';
    	} else if (name == 'pjcjl') {
    		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord';
    	} else if (name == 'jcjl') {
    		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
    				+ jsConst.CUS_NUMBER;
    	} else if (name == 'jqjcjl') {
    		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
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
    		url = jsConst.basePath + '/xxhj/zfsjsb/sblxsz/toIndex?cusNumber='
    				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
    	} else if (name == 'sjsb') {
    		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toIndex?cusNumber='
    				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
    	} else if (name == 'sjhz') {
    		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toSjhz?cusNumber='
    				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
    	} else if (name == 'jdjc') {
    		url = jsConst.basePath + '/monitor/jdjc';
    		w = 1000;
    		h = 700;
    	} else if (name == 'jddlb') {
    		url = jsConst.basePath + '/monitor/jddlb';
    		w = 1000;
    		h = 700;
    	} else if (name == 'realTimeTalk') {
    		url = jsConst.basePath + '/realTimeTalk/openDialog';
    		w = 1000;
    		h = 600;
    	} else if (name == 'group') {
    		w = 1200;
    		h = 600;
    		url = jsConst.basePath + '/groupManage/index';
    	} else if (name == 'screenSwitch') {
    		url = jsConst.basePath + '/screenSwitch/openDialog';
    	} else if (name == 'wldctb-bj') {
    		url = jsConst.basePath + '/inspect/editDialog';
    	} else if (name == 'wldctb-lb') {
    		url = jsConst.basePath + '/inspect/inspectListDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'wldctb-sp') {
    		url = jsConst.basePath + '/inspect/checkDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'wldctb-hz') {
    		url = jsConst.basePath + '/inspect/recordDialog';
    		w = 1000;
    		h = 700;
    	} else if (name == 'bddctb-bj') {
    		url = jsConst.basePath + '/inspectlocal/editDialog';
    	} else if (name == 'bddctb-sp') {
    		url = jsConst.basePath + '/inspectlocal/checkDialog';
    	} else if (name == 'bddctb-hz') {
    		url = jsConst.basePath + '/inspectlocal/recordDialog';
    	} else if (name == 'tbzg-fq') {
    		url = jsConst.basePath + '/xxyp/change/launchDialog';
    	} else if (name == 'tbzg-zg') {
    		url = jsConst.basePath + '/xxyp/change/changeDialog';
    	} else if (name == 'tbzg-sp') {
    		url = jsConst.basePath + '/xxyp/change/checkDialog';
    	} else if (name == 'tbzg-hz') {
    		url = jsConst.basePath + '/xxyp/change/recordDialog';
    	} else if (name == 'rcs') {
    		url = jsConst.basePath + '/rcs/openDialog';
    		w = 1000;
    		h = 600;
    	} else if (name == 'alarmType') {
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
    		url = jsConst.basePath + "/alarm/openDialog/record?DpName=''";
    	} else if (name == 'alarmRecord1') {
    		w = 1200;
    		h = 800;
    		title = "一级警情";
    		url = jsConst.basePath + '/alarm/openDialog/record?type=1';
    	} else if (name == 'alarmRecord2') {
    		w = 1200;
    		h = 800;
    		title = "二级警情";
    		url = jsConst.basePath + '/alarm/openDialog/record?type=2';
    	} else if (name == 'alarmRecord3') {
    		w = 1200;
    		h = 800;
    		title = "三级警情";
    		url = jsConst.basePath + '/alarm/openDialog/record?type=3';
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
    		title = "外来人员";
    		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=0';
    	} else if (name == 'wlry1') {
    		w = 1200;
    		h = 800;
    		title = "外来人员";
    		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=1';
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
    	} else if (name == "gzzgl") {
    		url = jsConst.basePath + '/yjct/gzzgl/index';
    	} else if (name == "wzgl") {
    		url = jsConst.basePath + '/yjct/wzgl/index';
    	} else if (name == "zjgl") {
    		url = jsConst.basePath + '/yjct/zjgl/index';
    	} else if (name == "fggl") {
    		url = jsConst.basePath + '/yjct/fggl/index';
    	} else if (name == "czffgl") {
    		url = jsConst.basePath + '/yjct/czffgl/index';
    	} else if (name == "yabz") {
    		url = jsConst.basePath + '/yjct/yabz/index';
    	} else if (name == "yasp") {
    		url = jsConst.basePath + '/yjct/yasp/index';
    	} else if (name == "yafb") {
    		url = jsConst.basePath + '/yjct/yafb/index';
    	} else if (name == "yljh") {
    		url = jsConst.basePath + '/yjct/yljh/index';
    	} else if (name == "ylsp") {
    		url = jsConst.basePath + '/yjct/ylsp/index';
    	} else if (name == "ylfb") {
    		url = jsConst.basePath + '/yjct/ylfb/index';
    	} else if (name == "zxyl") {

    	} else if (name == "yljl") {
    		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=2';
    	} else if (name == "yltj") {
    		url = jsConst.basePath + '/yjct/yjjl/toTj?type=2';
    	} else if (name == "czjl") {
    		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=1';
    	} else if (name == "cztj") {
    		url = jsConst.basePath + '/yjct/yjjl/toTj?type=1';
    	} else if (name == "xxdy") {
    		url = jsConst.basePath + '/yjct/msgsubscribe/index';
    	} else if (name == "yjctSszk") {
    		url = jsConst.basePath + '/yjct/sszk/toIndex';
    	} else if (name == 'yrzq') {
    		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=yzx';
    	} else if (name == 'zqgl') {
    		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=zqgl';
    	} else if (name == 'swdbgd') {
    		w = 1200;
    		h = 680;
    		url = jsConst.basePath + '/rwgl/rwjs/openDialog/index?type=swdb';
    	} else if (name == 'dayly') {
    		url = '${ctx}/xxyp/dayly/daylyLayout';
    		w = 1000;
    		h = 680;
    	} else if (name == 'xfrw') {
    		w = 1200;
    		h = 680;
    		url = jsConst.basePath + '/rwgl/rwxf/index';
    	} else if (name == 'jsrw') {
    		w = 1200;
    		h = 680;
    		url = jsConst.basePath + '/rwgl/rwjs/index';
    	} else if (name == 'jndtcx') {
    		url = jsConst.basePath + '/wghgl/yrzq/toList';
    	} else if (name == 'ccode') {// // 网格划分 网格管理分配格长
    		w = 1200;
    		h = 680;
    		url = jsConst.basePath + '/wghf/wgzrfp/toIndex';
    	} else if (name == 'xwzc') {
            w = 1200;
            h = 680;
    		title = "行为侦测";
    		url = jsConst.basePath + '/xwzc/toIndex?type=1';
    	} else if (name == 'xwzc1') {
    		url = jsConst.basePath + "/xwzc/toIndex?type=''";
    	} else if (name == 'mjkgjl') {
    		url = jsConst.basePath + '/xxhj/mjkgjl/toIndex';
    	} else if (name == 'swsb') {
    		// 生物识别
    		url = jsConst.basePath + '/policeLocation/openSwsbCountDialog';
    	} else if (name == "znys") {
    		// 智能钥匙
    		title = "智能钥匙";
    		url = jsConst.basePath + '/xxhj/znys/toIndex';
    	} else if (name == "mjxcjl") {
    		// 民警巡查记录
    		url = jsConst.basePath + '/xxhj/patrol/mjxcjl/toIndex';
    	} else if (name == "qjjcjl") {
    		// 清监检查
    		url = jsConst.basePath + '/wghgl/yrzq/qjjc/toIndex';
    	} else if (name == "xqdjlb") {
    		// 心情登记
    		url = jsConst.basePath + '/wghgl/yrzq/xqdjjl/toIndex';
    	} else if (name == "rlsb") {
    		url = jsConst.basePath + '/rlsb/toIndex';
    	} else if (name == "zfxsdm") {
    		// 罪犯巡视点名
    		url = jsConst.basePath + '/zfxx/zfXsdm/toIndex';
    	} else if (name == "zfzwdm") {
    		// 罪犯早晚点名
    		url = jsConst.basePath + '/zfxx/zfZwdm/toIndex';
    	} else if (name == 'mjkq') {
    		url = jsConst.basePath + '/xxhj/mjkq/toIndex';
    		// 民警考勤查询
    	} else if (name == 'wxxpg1') {
    		url = jsConst.basePath + '/aqfxyp/wxpg/toIndex?zt=0';
    		// 危险性评估
    	}else if (name == 'djwgzzwh') {
    		//党建网格组织维护
    		url = jsConst.basePath + '/djwg/zzwh/toIndex';
    	}else if (name == 'djwgcywh') {
    		//党建网格成员维护
    		url = jsConst.basePath + '/djwg/rywh/toIndex';
    	} else if (name == 'jctj') {
    		// 进出统计
    		url = jsConst.basePath + '/xxhj/jndt/jctj';
    	} else if (name == 'zfdmDetails') {
    		// 湖南罪犯点名详情
    		url = jsConst.basePath + '/zfxx/zfdm/detail';
    	} 
    	if (w == null || h == null) {
    		w = 1000;
    		h = 600;
    	}
    	if (title == null) {
    		title = $(obj).text();
    	}
    	$('#dialog').html("");
    	// $('#dialog').dialog("destroy");
    	$('#dialog').dialog({
    		width : w,
    		height : h,
    		title : title,
    		url : url
    	});
    	$("#dialog").dialog("open");
    	return;

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
