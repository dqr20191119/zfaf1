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
		<%-- <jsp:include page="../include/aqfkInclude.jsp"></jsp:include> --%>
		 
		 
		 
		<!-- 指挥协调 -->
		<%-- <jsp:include page="../include/zhxtInclude.jsp"></jsp:include> --%>
		
		
		<!-- 【视屏督察- 菜单】 start -->
		
		<!-- 【视屏督察- 菜单】 end -->
		
		<!-- 【警情处置- 菜单】 start -->
		
			
				<sec:authorize url="/zhdd/sy/jqcz/jqlb">
				<li id="zhxt_alarmRecord" onclick="openZhxtDialog(event,'alarmRecord')">
					<a href="javascript: void(0)">警情列表</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/jqcz/rgbj">
				<li id="zhxt_alarmProcessMan" onclick="toZhxtDisplay('alarmProcessMan')">
					<a href="javascript:void(0)">人工报警</a>
				</li>
				</sec:authorize>
		
	
		
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
		<sec:authorize url="/zhdd/sy/jqcz/yjcllc">
			<li id="czgl">
				<a href="javascript: void(0)">
					 应急处置流程
				</a>
                <iframe class="main-iframe"></iframe>
				<ul>

					<sec:authorize url="/zhdd/sy/jqcz/yjcllc/sjcz">
					<!-- 应急处置流程（新） -->
					<!-- 【应急处置-菜单】 start -->
					<li id="yjgl_yjcz" onclick="openYjglDialog(event, 'yjcz')">
						<a href="javascript: void(0);">应急处置</a>
					</li>
                    </sec:authorize>
					<!-- 【应急处置-菜单】 end -->

					<!-- 【应急统计-菜单】 start -->
                    <sec:authorize url="/zhdd/sy/jqcz/yjcllc/yjtj">
					<li id="yjgl_yjtj" onclick="openYjglDialog(event, 'yjtj')">
						<a href="javascript: void(0);">应急统计</a>
					</li>
                    </sec:authorize>
					<!-- 【应急统计-菜单】 end -->
					<!-- 应急处置流程（旧）停用 -->
					<%--<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjcz/sjcz">

					<li id="sjcz" onclick="toEmergencyDisplay('sjcz')">
						<a href="javascript: void(0)">事件处置</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/jqcz/yjcllc/czjl">
					<li id="czjl" onclick="openEmergencyDialog(event,'czjl')">
						<a href="javascript: void(0)">处置记录</a>
					</li>
					</sec:authorize>--%>
				</ul>
			</li>
		</sec:authorize>
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
		url = jsConst.basePath + '/monitor/jdjc';
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


function openEmergencyDialog(event,name) {
	
	var event = window.event || event;
	if(event && event.stopPropagation ) {
		  event.stopPropagation();
	} else {
       window.event.cancelBubble = true;
	}
	
	//event.preventDefault();
	var url = "";
	if (name == "gzzgl") {
		url = jsConst.basePath+'/yjct/gzzgl/index';
	} else if (name == "wzgl") {
		url = jsConst.basePath+'/yjct/wzgl/index';
	} else if (name == "zjgl") {
		url = jsConst.basePath+'/yjct/zjgl/index';
	} else if (name == "fggl") {
		url = jsConst.basePath+'/yjct/fggl/index';
	} else if (name == "czffgl") {
		url = jsConst.basePath+'/yjct/czffgl/index';
	} else if (name == "yabz") {
		url = jsConst.basePath+'/yjct/yabz/index';
	} else if (name == "yasp") {
		url = jsConst.basePath+'/yjct/yasp/index';
	} else if (name == "yafb") {
		url = jsConst.basePath+'/yjct/yafb/index';
	} else if (name == "yljh") {
		url = jsConst.basePath+'/yjct/yljh/index';
	} else if (name == "ylsp") {
		url = jsConst.basePath+'/yjct/ylsp/index';
	} else if (name == "ylfb") {
		url = jsConst.basePath+'/yjct/ylfb/index';
	} else if (name == "zxyl") {
	
	} else if (name == "yljl") {
		url = jsConst.basePath+'/yjct/yjjl/toIndex?type=2';
	} else if (name == "yltj") {
		url = jsConst.basePath+'/yjct/yjjl/toTj?type=2';
	} else if (name == "sjcz") {
	
	} else if (name == "czjl") {
		url = jsConst.basePath+'/yjct/yjjl/toIndex?type=1';
	} else if (name == "cztj") {
		url = jsConst.basePath+'/yjct/yjjl/toTj?type=1';
	} else if (name == "xxdy") {
		url = jsConst.basePath+'/yjct/msgsubscribe/index';
	} else if(name == "yjctSszk") {
		url = jsConst.basePath+'/yjct/sszk/toIndex';
	}

//    $('#dialog').dialog("destroy");
    $('#dialog').html("");
	$('#dialog').dialog({
		width : 1000,
		height : 600,
		title : $("#" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");

}

function toEmergencyDisplay(name) {
	var panel = $("#layout1").layout("panel", "east");
	if (name == "zxyl") {
		panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=2");
	}else if (name == "sjcz") {
		panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=1");
	}
}

/**
 * 应急管理（新）-弹出窗口通用方法
 * @param event
 * @param name
 */
function openYjglDialog(event, name) {
	var event = window.event || event;
	if (event && event.stopPropagation) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	var url = "";
	var w = null;
	var h = null;

	//应急资源-物资管理
	if (name == "yjzy_wzgl") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/yjct/wzgl/index';
	}
	//应急资源-专家管理
	else if (name == "yjzy_zjgl") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/yjct/zjgl/index';
	}
	//应急资源-法律法规
	else if (name == "yjzy_flfg") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/yjct/fggl/index';
	}
	//应急预案-预案管理
	else if (name == "yjya_yagl") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/emergency/preplanManage/openDialog';
	}
	//应急预案-梯队管理
	else if (name == "yjya_tdgl") {
		w = 1200;
		h = 600;
		url = jsConst.basePath+'/emergency/groupManage/openDialog';
	}
	//应急预案-处置步骤管理
	else if (name == "yjya_czbzgl") {
		w = 1200;
		h = 600;
		url = jsConst.basePath+'/emergency/stepManage/openDialog';
	}
	//应急处置
	else if (name == "yjcz") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/emergency/handle/recordManage/openDialog';
	}
	//应急统计
	else if (name == "yjtj") {
		w = 1000;
		h = 600;
		url = jsConst.basePath+'/emergency/handle/recordStatisticManage/openDialog';
	}

	$('#dialog').html("");
	if(w == null || w == '' || w == 0) {
		w = 1000;
	}
	if(h == null || h == '' || h == 0) {
		h = 600;
	}

	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#yjgl_" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");
}

</script>