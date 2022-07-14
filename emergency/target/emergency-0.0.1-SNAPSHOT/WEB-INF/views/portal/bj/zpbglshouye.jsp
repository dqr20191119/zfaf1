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

    <title>${map.orgName}值排班管理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-xiugai.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
    <link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .tooltip{
            width: 600px;
        }
        
         #ypcd li{
      	font-size: 24px;
      }  
      
     /* #ypcd>li>ul li{
     	width: 200px
     }   */ 
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
        <sec:authorize url="/zhdd/sy/zbbq/pbcspz">
		 <li class="tolist-item status"  >
			排班参数配置
			<ul class="tolist-menu">
				<sec:authorize url="/zhdd/sy/zbbq/pbcspz/lbgl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'lbgl','类别管理')">
					类别管理
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zbbq/pbcspz/gwgl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'gwgl','岗位管理')">
					岗位管理
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zbbq/pbcspz/bcgl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'bcgl','班次管理')">
					班次管理
				</li>
				</sec:authorize>
                <sec:authorize url="/zhdd/sy/zbbq/pbcspz/zbrygl">
				 <li class="tolist-menuitem" onclick="openFxpgDialog(this,   'rygl','值班人员管理')">
                  	  值班人员管理
                </li>
                </sec:authorize>
                <sec:authorize url="/zhdd/sy/zbbq/pbcspz/zbrlwh">
                    <li class="tolist-menuitem" onclick="openFxpgDialog(this,   'zbrlwh','日历维护')">
                        日历维护
                    </li>
                </sec:authorize>
               <!--  <li class="tolist-menuitem" onclick="openFxpgDialog(this,   'pbgz')">
                	    排班规则设定
                </li>   -->
			</ul>
		</li> 
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/zbbq/zbbpgl">
		<li class="tolist-item status"  >
			值班编排管理
			<ul class="tolist-menu">
				<sec:authorize url="/zhdd/sy/zbbq/zbbpgl/mbsz">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'mbsz','模板设置')">
				模板设置
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zbbq/zbbpgl/zbbp">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'zbbp','值班编排')">
					值班编排
				</li>
				</sec:authorize>
                <sec:authorize url="/zhdd/sy/zbbq/zbbpgl/jjbgl">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,   'jjb','交接班管理')">
					交接班管理
				</li>
                </sec:authorize>
                <sec:authorize url="/zhdd/sy/zbbq/zbbpgl/hbgl">
				<li class="tolist-menuitem">换班管理
					<ul>
                        <sec:authorize url="/zhdd/sy/zbbq/zbbpgl/hbgl/hbsq">
							<li class="tolist-menuitem"
								onclick="openFxpgDialog(this,'hbsq','换班申请')">换班申请</li>
                        </sec:authorize>
                        <sec:authorize url="/zhdd/sy/zbbq/zbbpgl/hbgl/hbsp">
							<li id = "hbsp" class="tolist-menuitem"
								onclick="openFxpgDialog(this,'hbsp','换班审批')">换班审批</li>
                        </sec:authorize>
					</ul>
				</li>
                </sec:authorize>
			</ul>
		</li>
		</sec:authorize> 
		<sec:authorize url="/zhdd/sy/zbbq/pbcxfx">
		<li class="tolist-item status"  >
			排班查询分析
			<ul class="tolist-menu">
				<sec:authorize url="/zhdd/sy/zbbq/pbcxfx/pbcx">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,    'zbcx')">
					排班查询
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zbbq/pbcxfx/pbfx">
				<li class="tolist-menuitem" onclick="openFxpgDialog(this,    'zbfx')">
					排班分析
				</li>
				</sec:authorize>
                <sec:authorize url="/zhdd/sy/zbbq/pbcxfx/pbfx/zbtbhz">
				<li id="jyzbhz" class="tolist-menuitem" onclick="openFxpgDialog(this,    'jyzbhz')">
					值班填报汇总
				</li>
                </sec:authorize>
			</ul>
		</li> 
		</sec:authorize>
		
		<sec:authorize url="/zhdd/sy/zbbq/zqswgl">
			<li class="tolist-item status">执勤事务管理
				<ul class="tolist-menu">
								<sec:authorize url="/zhdd/sy/zbbq/zqswgl/jhrz">
								<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jhrc')">
									计划日程
								</li>
								</sec:authorize>
								<sec:authorize url="/zhdd/sy/zbbq/zqswgl/zqgl">
								<li class="tolist-menuitem"
								onclick="openMenuDialog(this,event,'zqgl')">执勤管理</li>
								</sec:authorize>
								<sec:authorize url="/zhdd/sy/zbbq/zqswgl/jndt">
								<li class="tolist-menuitem"
								onclick="openMenuDialog(this,event,'jndtcx')">监内动态</li>
								</sec:authorize>
					<sec:authorize url="/zhdd/sy/zbbq/zqswgl/sjjb">
						<li class="tolist-menuitem">数据接报
							<ul>
								<sec:authorize url="/zhdd/sy/zbbq/zqswgl/sjjb/rwxf">
									<li class="tolist-menuitem">任务下发
										<ul>
											<sec:authorize url="/zhdd/sy/zbbq/zqswgl/sjjb/rwxf/xfrw">
												<li class="tolist-menuitem"
													onclick="openMenuDialog(this,event,'xfrw')">下发任务</li>
											</sec:authorize>
											<sec:authorize url="/zhdd/sy/zbbq/zqswgl/sjjb/rwxf/jsrw">
												<li class="tolist-menuitem"
													onclick="openMenuDialog(this,event,'jsrw')">接收任务</li>
											</sec:authorize>
										</ul>
									</li>
								</sec:authorize>
							</ul>
						</li>
					</sec:authorize>
				</ul>
			</li>
	  </sec:authorize>
		
		
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
<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true" maximizable="true"></cui:dialog>



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
    });
    var target = $("body").not(".menu-wrapper");
    target = target.not(".icon-htmal5icon35");
    target.on("click", function (e) {
        var target = $(e.target).parents(".menu-wrapper");
        if (target.length) {
            return;
        }
        $(".menu-container").removeClass("active");
    });

    /**组件库初始化方法*/
    $('#layout1').layout({
        fit: true, //属性: 值
        onCreate: function () { //回调事件: 值

        }
    });
    
   $.parseDone(function () {
        $(".center:first").addClass("bigMore");
        debugger;
        jsConst.basePath = "http://localhost:8086/";
        showDqyh1();
        
        //判断用户是否为指挥长
      //  checkIsZhz();
        //监区用户 隐藏填报汇总菜单
        if(jsConst.USER_LEVEL =='3'){
        	 $("#jyzbhz").hide();
        }
       
    });
    
   function checkIsZhz(){
	   $.ajax({
			type : 'post',
			url : '${ctx}/zbgl/rygl/checkIsZhz',
			dataType : 'json',
			success : function(data) {
				if(data.code == "200") {
					if(data.message=="false"){//不是指挥长隐藏换班审批
						$("#hbsp").hide();
					}
				} else {
					$.message({message:"操作失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
   }
   
   
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
    	}else if (name == 'rygl') {
            url = jsConst.basePath + '/zbgl/rygl/toIndex';
            w = 1600;
    		h = 1050;
        }else if (name == 'pbgz') {
            url = jsConst.basePath + '/zbgl/pbgz/toIndex';
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
    	}else if(name=='jjb'){
    		url = jsConst.basePath + '/zbgl/jjb/toIndex';
    	}else if(name=='hbsq'){
    		url = jsConst.basePath + '/zbgl/hbsq/toIndex';
    		w = 1200;
    		h = 700;
    	}else if(name=='hbsp'){
    		url = jsConst.basePath + '/zbgl/hbsq/toHbspIndex';
    		w = 1200;
    		h = 700;
    	}else if(name=='jyzbhz'){
    		url = jsConst.basePath + '/zbgl/jyzbhz/toIndex';
    	}else if(name=='zbrlwh'){
            url = jsConst.basePath + '/zbgl/zbrlwh/toIndex';
        }
        
        if (w == null || h == null) {
    		w = 900;
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
    		url = jsConst.basePath + '/xtgl/planeLayerPoint/index';
    		w = 1400;
    		h = 620;
    	} else if (name == 'jswh') {
    		url = jsConst.basePath + '/xxhj/jswh/toIndex';
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


    } 
    
    
    
    
    
    function showDqyh1() {
		 var userName = "<%=user.getRealName()%>";
		 var dprtmntName  = "<%=user.getDprtmntName()%>";
		 var policeNo  = "<%=user.getPoliceNo()%>";
		 $("#dqyh1").append( "<span class=\"user\">" +userName + "</span> （" +dprtmntName + "）<br>警号：" + policeNo); 

	}
</script>
</body>
</html>
