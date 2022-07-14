<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>

<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd">
<li>
	<a href="javascript:void();">
		指挥调度
	</a>
	<iframe class="main-iframe"></iframe>
	<ul>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/bjcz">
		<li id="zhxt_bjcz_li">
				<a href="javascript: void(0)">
					报警处置
					<b class="leftArrow"></b>
				</a>
				<iframe class="sub-iframe"></iframe>
			<ul id="dropdown_zhxt_bjcz_li" >
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/bjcz/bjjl">
				<li id="zhxt_alarmRecord" onclick="openZhxtDialog(event,'alarmRecord')">
					<a href="javascript: void(0)">报警记录</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/bjcz/rgbj">
				<li id="zhxt_alarmProcessMan" onclick="toZhxtDisplay('alarmProcessMan')">
					<a href="javascript:void(0)">人工报警</a>
				</li>
				</sec:authorize>
			</ul> 
		</li>
		</sec:authorize>
		
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/yagl">
		<li id="zhxt_bjya_li">
			<a href="#">
				预案管理
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul id="dropdown_zhxt_bjya_li" class="dropdown-menu" style="background-color: #333; color: white;">
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/yagl/spya">
				<li id="xxgl_group" onclick="openZhxtDialog(event,'group')">
					<a href="#">视频预案</a>
				</li>
				</sec:authorize>
				
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/yagl/mjya">
      			<li id="xxgl_doorPlan" onclick="openZhxtDialog(event,'doorPlan')">
          			<a href="javascript:void(0)">门禁预案</a>
      			</li>
      			</sec:authorize>
      			<li id="xxgl_broadcastPlan" onclick="openZhxtDialog(event,'broadcastPlan')">
          			<a href="javascript:void(0)">广播预案</a>
      			</li>
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/yagl/bjya">
				<li>
					<a href="#">
						报警预案
						<b class="leftArrow"></b>
					</a>
					<iframe class="sub-iframe"></iframe>
					<ul>
						<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/yacl/bjya/bjya">
						<li id="zhxt_alarmPlan" onclick="openZhxtDialog(event,'alarmPlan')">
							<a href="javascript:void(0)">报警预案</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/yacl/bjya/bjdj">
						<li id="zhxt_alarmType" onclick="openZhxtDialog(event,'alarmType')">
							<a href="javascript:void(0)">报警等级</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/yacl/bjya/bjlc">
						<li id="zhxt_flows" onclick="openZhxtDialog(event,'flows')">
							<a href="javascript: void(0)">报警流程</a>
						</li>
						</sec:authorize>
					</ul>
				</li>
				<%-- <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/yacl/mjya">
      			<li id="xxgl_broadcastPlan" onclick="openZhxtDialog(event,'broadcastPlan')">
          			<a href="javascript:void(0)">广播预案</a>
      			</li>
      			</sec:authorize> --%>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize>
		<%-- <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/zfcjsp">
		<li id="zhxt_zfjcj" onclick="openZhxtDialog(event,'zfjcj')">
			<a href="javascript:void(0)">罪犯出监审批</a>
		</li>
		</sec:authorize> --%>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/gzwx">
		<li id="zhxt_deviceRecord" onclick="openZhxtDialog(event,'deviceRecord')">
			<a href="javascript:void(0);">故障维修</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/rwgl">
		<li id="zhxt_rwgl_li">
			<a href="javascript:void();">
				任务管理
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul id="dropdown_zhxt_rwgl_li" class="dropdown-menu" style="background-color: #333; color: white;">
				<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/rwgl/xfrw">
				<li id="zhxt_xfrw" onclick="openZhxtDialog(event,'xfrw')">
					<a href="javascript:void(0)">下发任务</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/zhdd/rwgl/jsrw">
				<li id="zhxt_jsrw" onclick="openZhxtDialog(event,'jsrw')">
					<a href="javascript:void(0)">接收任务</a>
				</li>
				</sec:authorize>
			</ul>
		</li>
		 </sec:authorize>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/jddlb">
		<li  onclick="openZhxtDialog( event, 'jddlb')">
			<a href="javascript:void(0)">监督单列表</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/spjd">
		<li>
			<a href="javascript:void(0)">视频监督
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul>
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/spjd/jdtblb">
				<li  onclick="openZhxtDialog(event, 'wldctb-lb')">
					<a href="javascript:void(0)">监督通报列表</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/spjd/jdtbsp">
				<li  onclick="openZhxtDialog(event, 'wldctb-sp')">
					<a href="javascript:void(0)">监督通报审批</a>
				</li>
				</sec:authorize>
				<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/spjd/jdtbhz">
				<li  onclick="openZhxtDialog(event, 'wldctb-hz')">
					<a href="javascript:void(0)">监督通报汇总</a>
				</li>
				</sec:authorize>
			</ul>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/zhdd/jdjc">
		<li onclick="openZhxtDialog(event, 'jdjc')">
			<a href="javascript:void(0)">监督检查</a>
		</li>
		</sec:authorize>
	</ul>
</li>
</sec:authorize>

<script>
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
//        $('#dialog').dialog("destroy");
		$('#dialog').dialog({
			width : w,
			height : h,
			title : $("#zhxt_" + name + " a").text(),
			url : url
		});
		$("#dialog").dialog("open");
		return;
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
</script>