<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
<!--

-->
#menu a{
	font-size: 20px;
}

#menu>li>ul a{
	font-size: 20px;
}
</style>

<div class="menu-m">
	<ul class="sjdw">
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/sjdw">
		<li class="sjdw-main">
			<a href="javascript:void(0);">
				<span class="iconfont icon-shijue1"></span>
				<span class="sjdw-name">视角定位</span>
				<span class="iconfont icon-xialadown"></span>
			</a>
			<iframe class="main-iframe" style="visibility: hidden;"></iframe>
			<!-- Begin modified by linhe 2018-1-9 -->
			<ul id="viewPositionMenu">
				<!-- <li> <a href="javascript:alert();centerDisplay('provMap');">1111111ces<b class="leftArrow"></b></a>
						<iframe class="sub-iframe"></iframe>
						<ul>
							<li onclick="centerDisplay('provMap')"><a href="javascript:void(0);">省局</a></li>
							<li onclick="centerDisplay('prisMap')"><a href="javascript:void(0);">监狱</a></li>
						</ul>
					</li> -->
			</ul>
			<!-- End modified by linhe 2018-1-9 -->
		</li>
		</sec:authorize>
	</ul>

	<ul id="menu">
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/sy">
			<li id="nav-home" onclick="openZnafpt()">
				<a href="javascript:void(0)">
					返回
				</a>
			</li>
		
		</sec:authorize> 
		
		
		<!-- 安全防控 -->
		<%-- <jsp:include page="../include/spjkInclude.jsp"></jsp:include> --%>
		 
		 
		 
		<!-- 指挥协调 -->
		<%-- <jsp:include page="../include/zhxtInclude.jsp"></jsp:include> --%>
			<sec:authorize url="/zhdd/sy/spjk/splx">
				<li id="splx" onclick="toAqfkDisplay('splx')">
					<a href="#">视频轮巡</a>
				</li>
			</sec:authorize>
			
			<sec:authorize url="/zhdd/sy/spjk/hfxz">
				<li id="sphf" onclick="toAqfkDisplay('sphf')">
					<a href="#">回放下载</a>
				</li>
			</sec:authorize>
				
			<sec:authorize url="/zhdd/sy/spjk/spdy">
				<li id="spya" onclick="toAqfkDisplay('spya')">
					<a href="#">视频调阅</a>
				</li>
			</sec:authorize>
			<sec:authorize url="/zhdd/sy/spjk/spyagl">
				<li id="xxgl_group" onclick="openZhxtDialog(event,'group')">
					<a href="#">视频轮巡方案管理</a>
				</li>
			</sec:authorize>	
				
				<%-- <sec:authorize url="/zhdd/sy/spjk/lxsxt">
				<li id="offLineCamera" onclick="toAqfkDisplay('offLineCamera')">
					<a href="#">离线摄像头</a>
				</li>
				</sec:authorize> --%>
		
			<sec:authorize url="/zhdd/sy/spjk/dsqgl">
				<li id="spya">
					<a href="javascript:void(0);">电视墙管理</a>
                    <iframe class="main-iframe"></iframe>
					<ul>
					<li>
						<a href="javascript:void(0);">大屏控制</a>
						<b class="leftArrow"></b>
						<iframe class="sub-iframe"></iframe>
						<ul>
							<li id="aqfk_screenPlan" onclick="openAqfkDialog(event,'screenPlan')">
								<a href="#">大屏预案</a>
							</li>
							<%--<li id="aqfk_screenSwitch" onclick="openAqfkDialog(event,'screenSwitch')">
								<a href="javascript: void(0)">大屏切换</a>
							</li>--%>
						</ul>
					</li>
					</ul>
				</li>
			</sec:authorize>
		
		<!-- 【视屏督察- 菜单】 start -->
		<sec:authorize url="/zhdd/sy/spjk/spdc">
		<li>
		<a href="javascript:void(0);">视频督察</a>
            <iframe class="main-iframe"></iframe>
			<ul>
				<sec:authorize url="/zhdd/sy/zhdd/spdc/jdjc">
				<li onclick="openZhxtDialog(event, 'jdjc')">
					<a href="javascript:void(0)">监督检查</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/spdc/czfk">
				<li  onclick="openZhxtDialog( event, 'jddlb')">
					<a href="javascript:void(0)">处置反馈</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/spdc/dctb">
					<li>
						<a href="javascript:void(0)">督察通报
							<b class="leftArrow"></b>
						</a>
						<iframe class="sub-iframe"></iframe>
						<ul>
							<sec:authorize url="/zhdd/sy/zhdd/spdc/jctb/jdtblb">
							<li  onclick="openZhxtDialog(event, 'wldctb-lb')">
								<a href="javascript:void(0)">监督通报列表</a>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/zhdd/spdc/jctb/jdtbsp">
							<li  onclick="openZhxtDialog(event, 'wldctb-sp')">
								<a href="javascript:void(0)">监督通报审批</a>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/zhdd/spdc/jctb/jdtbhz">
							<li  onclick="openZhxtDialog(event, 'wldctb-hz')">
								<a href="javascript:void(0)">监督通报汇总</a>
							</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/spdc/rwgl">
					<li id="zhxt_rwgl_li">
						<a href="javascript:void();">
							任务管理
							<b class="leftArrow"></b>
						</a>
						<iframe class="sub-iframe"></iframe>
						<ul id="dropdown_zhxt_rwgl_li" class="dropdown-menu" style="background-color: #333; color: white;">
							<sec:authorize url="/zhdd/sy/zhdd/spdc/rwgl/xfrw">
							<li id="zhxt_xfrw" onclick="openZhxtDialog(event,'xfrw')">
								<a href="javascript:void(0)">下发任务</a>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/sy/zhdd/spdc/rwgl/jsrw">
							<li id="zhxt_jsrw" onclick="openZhxtDialog(event,'jsrw')">
								<a href="javascript:void(0)">接收任务</a>
							</li>
							</sec:authorize>
						</ul>
					</li>
		 		</sec:authorize>
			</ul>
		</li>
		</sec:authorize>
		<!-- 【视屏督察- 菜单】 end -->
		
		<!-- 【警情处置- 菜单】 start -->
		<%-- <sec:authorize url="/zhdd/sy/zhdd/jqcz">
		<li id="zhxt_bjcz_li">
				<a href="javascript: void(0)">
					警情处置
				</a>
			<ul id="dropdown_zhxt_bjcz_li" >
				<sec:authorize url="/zhdd/sy/zhdd/jqcz/bjjl">
				<li id="zhxt_alarmRecord" onclick="openZhxtDialog(event,'alarmRecord')">
					<a href="javascript: void(0)">报警记录</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/jqcz/rgbj">
				<li id="zhxt_alarmProcessMan" onclick="toZhxtDisplay('alarmProcessMan')">
					<a href="javascript:void(0)">人工报警</a>
				</li>
				</sec:authorize>
			</ul> 
		</li>
		</sec:authorize> --%>
		<!-- 【警情处置- 菜单】 end -->
		
		<!-- 【三维巡视- 菜单】 start -->
		<%-- <sec:authorize url="/zhdd/sy/zhdd/swxs">
		<li>
			<a href="javascript:void(0);">三维巡视</a>
			<ul>
				<sec:authorize url="/zhdd/sy/zhdd/swxs/swxs">
				<li id="swxs" onclick="toAqfkDisplay('swxs')">
					<a href="#">三维巡视</a>
				</li>
		 		</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/swxs/xsjl">
				<li id="aqfk_roamRecord" onclick="openAqfkDialog(event,'roamRecord')">
					<a href="javascript: void(0)">巡视记录</a>
				</li>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize> --%>
		<!-- 【三维巡视- 菜单】 end -->
		
		<!-- 【预案管理- 菜单】 start -->
		<%-- <sec:authorize url="/zhdd/sy/zhdd/yagl">
		<li id="zhxt_bjya_li">
			<a href="#">
				预案管理
			</a>
			<ul id="dropdown_zhxt_bjya_li" class="dropdown-menu" style="background-color: #333; color: white;">
				<sec:authorize url="/zhdd/sy/zhdd/yagl/spxcya">
				<li id="xxgl_group" onclick="openZhxtDialog(event,'group')">
					<a href="#">视频巡查预案</a>
				</li>
				</sec:authorize>
				
				<sec:authorize url="/zhdd/sy/zhdd/yagl/mjkzya">
      			<li id="xxgl_doorPlan" onclick="openZhxtDialog(event,'doorPlan')">
          			<a href="javascript:void(0)">门禁控制预案</a>
      			</li>
      			</sec:authorize>
      			<sec:authorize url="/zhdd/sy/zhdd/yagl/gbkzya">
      			<li id="xxgl_broadcastPlan" onclick="openZhxtDialog(event,'broadcastPlan')">
          			<a href="javascript:void(0)">广播控制预案</a>
      			</li>
      			</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/yagl/bjczya">
				<li>
					<a href="#">
						报警处置预案
						<b class="leftArrow"></b>
					</a>
					<iframe class="sub-iframe"></iframe>
					<ul>
						<sec:authorize url="/zhdd/sy/zhdd/yagl/bjczya/bjya">
						<li id="zhxt_alarmPlan" onclick="openZhxtDialog(event,'alarmPlan')">
							<a href="javascript:void(0)">报警预案</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/sy/zhdd/yagl/bjczya/bjdj">
						<li id="zhxt_alarmType" onclick="openZhxtDialog(event,'alarmType')">
							<a href="javascript:void(0)">报警等级</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/sy/zhdd/yagl/bjczya/bjlc">
						<li id="zhxt_flows" onclick="openZhxtDialog(event,'flows')">
							<a href="javascript: void(0)">报警流程</a>
						</li>
						</sec:authorize>
					</ul>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/zhdd/yagl/yjglya">
				<li id="yagl">
				<a href="javascript: void(0)">
					应急管理预案
				</a>
				<ul>
				<!-- openEmergencyDialog  openEmergencyDialog 在yjctInclude.jsp页面中  -->
					<sec:authorize url="/zhdd/sy/zhdd/yagl/yjglya/yabz">
					<li id="yabz" onclick="openEmergencyDialog(event,'yabz')">
						<a href="javascript: void(0)">预案编制</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yagl/yjglya/yasp">
					<li id="yasp" onclick="openEmergencyDialog(event,'yasp')">
						<a href="javascript: void(0)">预案审批</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yagl/yjglya/yafb">
					<li id="yafb" onclick="openEmergencyDialog(event,'yafb')">
						<a href="javascript: void(0)">预案发布</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
			</ul>
		</li>
		</sec:authorize> --%>
		
		
		<!-- 【预案管理- 菜单】 end -->
		<!-- 应急管理 -->
		<%-- <jsp:include page="../include/yjctInclude.jsp"></jsp:include> --%>
		
		 
		
		
		
		<%--  <sec:authorize url="/zhdd/sy/zhdd/aqfh">
			<li id="zhxt_wldctb-sp_"  onclick="openZhxtDialog_(event, 'wldctb-sp')">
				<a href="javascript:void(0)">
					安全复核
				</a>
				<ul>
					<sec:authorize url="/zhdd/sy/zhdd/aqfh/wlryclfh">
					<li  onclick="">
						<a href="javascript: void(0)">外来人员车辆复核</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/aqfh/zfcjfh">
					<li  onclick="">
						<a href="javascript: void(0)">罪犯出监复核</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/aqfh/wpjcfh">
					<li  onclick="">
						<a href="javascript: void(0)">物品进出复核</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/aqfh/zfldfh">
					<li  onclick="">
						<a href="javascript: void(0)">罪犯流动复核</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
		</sec:authorize> --%>
		
		 <sec:authorize url="/zhdd/sy/spjk/zjgd">
			<li id="zhxt_jddlb_"  onclick="openZhxtDialog_( event, 'jddlb')">
				<a href="javascript:void(0)">
					证据固定
				</a>
			</li>
		</sec:authorize>
		 <%--<li id="zhxt_znjklx_"  onclick="znjklx()">
				<a href="javascript:void(0)">
					智能监控轮巡
				</a>
         </li>--%>
		<!-- 系统管理 -->
		<%-- <jsp:include page="../include/xtglInclude.jsp"></jsp:include> --%>
		
		<!-- 测试菜单 -->
		<%-- <jsp:include page="../include/testInclude.jsp"></jsp:include> --%>
		
		<!-- 前置机调用Demo菜单 -->
		<%-- <jsp:include page="../include/demoInclude.jsp"></jsp:include> --%>
	</ul>
 
	<div class="header-content">
        <div class="header-item date">
          <span class="icon iconfont icon-datepiceker"></span>
          <span class="title">${dqrq}</span>
        </div>
        <div class="header-item">
          <span class="icon iconfont icon-police2"></span>
          <span class="title" id="dqyh">当前用户1${map.dprtmntCode}：</span>
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
</div>

<script type="text/javascript">
function openZnafpt() {
	//var dprtmntCode = jsConst.DEPARTMENT_ID;
	//var url = "${ctx}/portal/bj/shouye";
	// 部门是武警的时候，直接跳到综合首页
	//if(dprtmntCode == '110537') {
		url = "${ctx}/portal/zhshouye";
	//}
	window.location.href = url;
}

function showDqyh() {
	$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
}

function openZhxtDialog_(event, name) {
	var event = window.event || event;
	//event.stopPropagation();
	  if ( event && event.stopPropagation ) {
		  event.stopPropagation();
		} else {
       window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
  if (name == 'jddlb') {
		url = jsConst.basePath+'/monitor/jddlb';
		w = 1000;
		h = 700;
	} else if (name == 'wldctb-sp') {
		url = jsConst.basePath+'/inspect/checkDialog';
		w = 1000;
		h = 700;
	}else if (name == 'znjklx') {
		$.alert("待开发.......");
		return;
	}  


	if (w == null || h == null) {
		w = 900;
		h = 600;
	}

    $('#dialog').html("");
//    $('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#zhxt_" + name + "_ a").text(),
		url : url
	});
	$("#dialog").dialog("open");

}

function openZhxtDialog(event, name) {
	var event = window.event || event;
	//event.stopPropagation();
	  if ( event && event.stopPropagation ) {
		  event.stopPropagation();
		} else {
       window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'alarmType') {
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
		url = jsConst.basePath + '/alarm/openDialog/record';
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
		url = jsConst.basePath + '/foreignPeopel/openDialog';
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
	}else if (name == "doorPlan") {
        url = jsConst.basePath + '/door/plan/openDialog';
    }else if (name == "broadcastPlan") {
        //url = jsConst.basePath + '/broadcast/plan/openDialog';
        url = jsConst.basePath + '/broadcastPlan/openDialog';
    }else if (name == 'group') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/groupManage/index';
	} else if (name == 'xfrw') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/rwgl/rwxf/index';
	} else if (name == 'jsrw') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/rwgl/rwjs/index';
	}
	else if (name == 'jdjc') {
		url = jsConst.basePath+'/monitor/jdjc';
		w = 1000;
		h = 700;
	}else if (name == 'jddlb') {
		url = jsConst.basePath+'/monitor/jddlb';
		w = 1000;
		h = 700;
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
	} else if (name == 'zfPhoto') {
		url = jsConst.basePath+'/zfxx/zfJbxx/openDialog';
		w = 1000;
		h = 600;
	}
	//监督检查 add by zk
	else if (name == 'jdjc') {
		url = jsConst.basePath + '/monitor/jdjc?';
		w = 1000;
		h = 700;
	}


	if (w == null || h == null) {
		w = 900;
		h = 600;
	}

    $('#dialog').html("");
//    $('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#zhxt_" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");

}
function toZhxtDisplay(name) {
	var panel = $("#layout1").layout("panel", "east");
	if (name == "111") {
		//	panel.panel("refresh", "${ctx}/menubar/index");
		alert("111");
	} else if (name == "alarmProcessMan") {
		panel.panel("refresh", jsConst.basePath
				+ "/alarm/handle/index?type=1");
	}else if (name == "spya") {//视频预案
		panel.panel("refresh", jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		if(jsConst.USER_LEVEL==1){//省局
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/prov/spya");
		}else{
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		}
	}
}



function openAqfkDialog(event,name) {
	var event = window.event || event;
	//event.stopPropagation();
	if ( event && event.stopPropagation ) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'jdjc') {
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
	}else if (name == 'screenPlan') {
		url = jsConst.basePath+'/screenPlan/openDialog';
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
	}else if (name == 'rcs') {
		url = jsConst.basePath+'/rcs/openDialog';
		w = 1000;
		h = 600;
	}else if (name == 'search') {
		url = jsConst.basePath+'/rcs/toIndex';
		w = 1000;
		h = 600;
	}else if (name == 'doorDemo') {
		url = jsConst.basePath+'/doorlinkage/openDoorDemoDialog';
		w = 1000;
		h = 600;
	}else if (name == 'rcsYjyaDemo') {
		url = jsConst.basePath+'/rcs/openYjyaDemoDialog';
		w = 1000;
		h = 600;
	} else if (name == 'broadcastRecord') {
        url = jsConst.basePath + '/broadcastRecord/openDialog';
		w = 1000;
		h = 600;
    } else if (name == "roamRecord") {//巡视记录
        url = jsConst.basePath + '/roamRecord/openDialog';
		w = 1000;
		h = 600;
	}

	// $('#dialog').dialog("destroy");
	$('#dialog').html("");
	if (w == null || h == null) {
		w = 800;
		h = 600;
	}

	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#aqfk_" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");

}
function toAqfkDisplay(name) {
	var panel = $("#layout1").layout("panel", "east");
	if (name == "alarmProcess") {
		panel.panel("refresh", jsConst.basePath+"/alarm/open/index?type=0");
	}else if (name == "alarmProcessMan") {
		panel.panel("refresh", jsConst.basePath+"/alarm/open/index?type=1");
	}else if (name == "spya") {//视频预案
		debugger;
		// panel.panel("refresh", jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/spya");
		console.log("aqfkInclude.jsp toAqfkDisplay spya jsConst.USER_LEVEL = " + jsConst.USER_LEVEL);
		if(jsConst.USER_LEVEL==1){//省局
			console.log("aqfkInclude.jsp toAqfkDisplay spya 刷新省局视频菜单");
			panel.panel("refresh",jsConst.basePath+"/menubar/displayRight?viewName=rightside/spjk/prov/spya");
		}else{
			console.log("aqfkInclude.jsp toAqfkDisplay spya 刷新监狱视频菜单");
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
	}else if (name == 'dsqgl'){//电视墙管理
		//panel.panel("refresh", jsConst.basePath+"/doorlinkage/index");
		$.alert("待开发...")
	}
	
	
	//视频轮巡 add by zk
	else if (name == "splx") {
		var params={
			"grpId":null
		};
		openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
	}
	// 广播播放
	else if (name == "broadcastPlay") {
		panel.panel("refresh",jsConst.basePath+"//broadcastLinkage/controlBroadcastTree");
	}
}
/**
 * 跳转到一体化运维页面
 */
function openYthyw() {
	
	var url = "http://206.0.8.223/http/tekinfo/login/login.html?callback-url=http%3A%2F%2F206.0.8.223%2Fhttp%2FiCarrier%2Findex.html";
	window.open(url, "_blank");
}
/**
 * 跳转到无人机防空页面
 */
function openWrjfk() {
	
	var url = "http://206.0.4.6/Myrtille/?__EVENTTARGET=&__EVENTARGUMENT=&server=206.0.4.5&user=administrator&password=68555587&connect=1";
	window.open(url, "_blank");
}
//携带参数打开右侧视图 add by zk
function openViewToRightAddParam(viewName, params) {
	var panel = $("#layout1").layout("panel", "east");
	panel.panel("refresh", '${ctx}/menubar/displayRightAddParam?viewName=' + viewName + '&params=' + encodeURI(JSON.stringify(params)));
}

/**
 * 狱外押解
 */
function openYuwaiyajie() {
	var url = "yuwaiyajie://";
	window.open(url, "_self");
}

/**
 * 跳转到垦华运维平台页面
 */
function openKhywpt() {
	var url = "http://210.192.10.242:9113/ssoservice/login?isAutoLogin=true&username_auto=admin&password_auto=jwcsfl3936&service=http://210.192.10.242:8890";
	window.open(url, "_blank");
}



function znjklx() {
    var deviceIds =[];
    deviceIds.push('736673dc89f811e9a0e5309c23181344');
    deviceIds.push('7366744689f811e9a0e5309c23181344');
    deviceIds.push('7366776c89f811e9a0e5309c23181344');
    deviceIds.push('736677c089f811e9a0e5309c23181344');

    if (jsConst.VIDEO_PLAYER_TYPE == '1') {
        $("#dialogId_videoPluginDemo").dialog("close");
        $("#dialogId_videoPluginDemo").dialog({
            width: 1200,
            height: 800,
            title: '视频调阅',
            onOpen: function() {


                // 播放选中的摄像头实时视频
                videoPlugin.multiVideoPlayHandle({
                    'container': $("div[id='dialogId_videoPluginDemo']"),
                    'subType': 2,
                    'layout': 4,
                    'data': deviceIds,
                    'callback': function (data) {
                        console.log("jquery-mouseSelect.js showSelDiv data = " + JSON.stringify(data));
                    }
                });
            },
            onClose: function () {
                videoPlugin.videoPlayerClear();
            }
        });
        $("#dialogId_videoPluginDemo").dialog("open");
    } else if (jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE) {
        videoClient.playVideoHandle({
            'subType': 2,
            'layout': 4,
            'data': deviceIds,
            'callback': function (data) {


            }
        });
    }

}

</script>