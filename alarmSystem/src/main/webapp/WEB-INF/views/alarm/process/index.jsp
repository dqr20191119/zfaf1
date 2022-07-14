<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/extraFont/jquery.mCustomScrollbar.css"  rel="stylesheet">
<link href="${ctx}/static/cui/iconfont.css"  rel="stylesheet">
<script src="${ctx}/static/cui/jquery-powerSwitch.js"></script>
<script src="${ctx}/static/cui/jquery.mCustomScrollbar.js"></script>
<script src="${ctx}/static/js/alarm/alarm.js"></script>


<!-- coral4 css start  -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/moxing.css" />
<!-- coral4 css  end  -->

<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
<!-- app css define end -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" /> 
<style type="text/css">

</style>

<div class="coral-scroll-box">
<div class="rightDivStyle right-zb zoom-box" id="alarmMsgDiv" style="display: none;">
	<h4 align="center">
		报警信息
		<img id="imgId" height="26px" width="26px" src="${ctx}/static/resource/images/sound_off.png" onclick="playMusic()" />
		<!-- <div class="setup-box" id="setup-box">
			<li class="iconfont icon-shezhi">
				<ul>
					<li data-value="alarmReceive"><a href="#">接警处置</a></li>
					<li data-value="eventHanding"><a href="#">启动预案</a></li>
					<li data-value="openAlarmCancel"><a href="#">取消报警</a></li>
				</ul>
			</li>
		</div> -->
	</h4>
	<!-- 报警信息 -->
	<cui:form id="formId_bjxx" componentCls="info-list">
		<table>
			<tr>
				<td>地点：</td>
				<td colspan="3">
					<cui:input name="ALARM_ADDRESS" width="200"></cui:input>
				</td>
			</tr>
			<tr>
				<td>报警器：</td>
				<td colspan="3">
					<cui:input name="ALERTOR_NAME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>时间：</td>
				<td colspan="3">
					<cui:input name="ARD_ALERT_TIME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>类型：</td>
				<td colspan="3">
					<cui:combobox name="ARD_TYPE_INDC" data="bjlx_data"></cui:combobox>
				</td>
				 
			</tr>
			<tr>
				 
				<td>等级：</td>
				<td colspan="3">
					<cui:combobox name="ARD_ALERT_LEVEL_INDC" data="bjdj_data"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>状态：</td>
				<td colspan="3">
					<cui:combobox id="alarmOprtnStatus" name="ARD_OPRTN_STTS_INDC" data="czzt_data"></cui:combobox>
				</td>
				 
			</tr>
			<tr>
				 
				<td>接警人：</td>
				<td colspan="3">
					 <cui:input id="alarmReceive" name="ARD_RECEIVE_ALARM_POLICE"></cui:input>
				</td>
			</tr>
			
		</table>
	</cui:form>
	<div id="divId_receive" style="display: none;">
		<cui:button componentCls="btn-success" onClick="alarmReceive" label="接警处置"></cui:button>
		<cui:button componentCls="btn-danger" onClick="eventHandingNew" label="启动预案" style="margin-left:20px"></cui:button>
		<%--<cui:button componentCls="btn-danger" onClick="eventHanding" label="启动预案（旧）" style="margin-left:40px"></cui:button>--%>
		<br><br>
		<cui:button componentCls="btn-warning" onClick="openAlarmCancel" label="取消报警" style="margin-left:0px"></cui:button>
		<cui:button componentCls="btn-warning" onClick="opentxrh(event)" label="通信融合" style="margin-left:40px"></cui:button>
		
	</div>

</div>
<div class="rightDivStyle right-zb zoom-box" id="switch" style="display:none">
	<h4 align="center">值班民警
		<a href="javascript:void(0)" onclick="openPoliceList()"><span class="moreCls more-police">更多》</span></a>
	</h4>
	<div class="zxx_test_list_list">
          <div id="caroEndless" class="caro_trigger caro_trigger2">
          	<a href="javascript:void(0)" class="caro_prev cui-icon-arrow-left3" data-rel="caroSwitchEnd"></a>
              <a href="javascript:void(0)" class="caro_next cui-icon-arrow-right3" data-rel="caroSwitchEnd"></a>
          </div>
          <div class="caro_box caro_box2">
          	<ul id="caroBoxEnd" class="caro_container caro_container2">
              	<%--  <li class="caroSwitchEnd"><img src="${ctx}/static/images/image1.png"><p>张三1</p><p>2012021</p></li>
                  <li class="caroSwitchEnd"><img src="${ctx}/static/images/image2.png"><p>张三2</p><p>2012021</p></li>
                  <li class="caroSwitchEnd"><img src="${ctx}/static/images/cloud.png"><p>张三3</p><p>2012021</p></li> --%>  
              </ul>
          </div>
    </div>
</div>
<div class="rightDivStyle right-zb zoom-box" id="switch-criminal" style="display:none">
	<h4 align="center">重点罪犯
		<a href="javascript:void(0)" onclick="openPrisonerList()"><span class="moreCls more-criminal"> 更多》</span></a>
	</h4>
	<div class="zxx_test_list_list">
          <div id="caroEndless-cri" class="caro_trigger caro_trigger2">
              <a href="javascript:void(0)" class="caro_prev cui-icon-arrow-left3" data-rel="caroSwitchEnd1"></a>
              <a href="javascript:void(0)" class="caro_next cui-icon-arrow-right3" data-rel="caroSwitchEnd1"></a>
          </div>
          <div class="caro_box caro_box2">
          	  <ul id="caroBoxEnd1" class="caro_container caro_container2">
              	  <%-- <li class="caroSwitchEnd1"><img src="${ctx}/static/images/image1.png"><p>张三1</p><p>2012021</p></li>
                  <li class="caroSwitchEnd1"><img src="${ctx}/static/images/image2.png"><p>张三2</p><p>2012021</p></li>
                  <li class="caroSwitchEnd1"><img src="${ctx}/static/images/cloud.png"><p>张三3</p><p>2012021</p></li> --%>
              </ul>
          </div>
    </div>
</div>
<div class="rightDivStyle right-zb" id="device_show" style="display: none; height: auto;">
	<!-- <H4 id="alarmPlanName" align="center" style="color: white;"></H4> -->
	<div id="divId_device" class="picfrm"></div>
</div>

<div class="rightDivStyle right-zb" id="p_alarmMsgDiv" style="display: none; color: white;">
	<H4 align="center">人工报警</H4>
	<cui:form id="formId_alarmMsg_man">
		<table class="table" style="width: 97%;margin:0 auto">
			<tr>
			
				<th style="padding: 0px">地点：</th>
				<td>
					<cui:combotree id="comboId_area_alert" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick">
					</cui:combotree>
				</td>
			</tr>

			<tr>
				<th style="padding: 0px">日期：</th>
				<td>
					<cui:datepicker id="datepId_now" name="ardAlertTime" dateFormat="yyyy-MM-dd HH:mm:ss" readonly="true" required="true"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">性质：</th>
				<td>
					<cui:combobox id="combId_event" name="ardEventId"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">报警器：</th>
				<td>
					<cui:combobox id="combId_alert" name="ardAlertorIdnty" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">报警人：</th>
				<td>
					<cui:combobox id="combId_police" name="ardAlarmPoliceId" emptyText="默认本人"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<cui:button id ="subAlarmMsg" componentCls="btn-success" onClick="subAlarmMsg" width="100" label="提交"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
</div>
</div>
<!-- 报警声音 -->
<audio id="audioId" loop="loop" src="${ctx}/static/resource/audio/alarm.mp3"></audio>

<div id = "audioId_"></div>
<!-- 取消报警弹窗 -->
<cui:dialog id="dialogId_cancelAlarm" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true">
	<div style="text-align: center; height: 100%; width: 100%">
		<cui:form id="formId_cancelAlarm" heightStyle="fill">
			<table class="table" style="width: 98%">
				<tr>
					<th>现场情况：</th>
					<td  >
						<cui:textarea  componentCls="form-control" name="ardLocalCase"></cui:textarea>
					</td>
				</tr>
				<tr>
					<th>取消原因：</th>
					<td>
						<cui:textarea name="ardRemark" componentCls="form-control" required="true"></cui:textarea>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons" style="text-align: center; margin-bottom: 0px">
			<cui:button onClick="alarmCancel" label="提交"></cui:button>
			<cui:button label="取消" onClick="close_qxbj"></cui:button>
		</div>
	</div>
</cui:dialog>

<!-- 罪犯列表弹窗 -->
<cui:dialog id="dialogId_prisoner_list" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
<!-- 民警列表 -->
<cui:dialog id="dialogId_police_list" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
<!-- 罪犯详细信息 -->
<cui:dialog id="dialogId_jszfxx" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false"  autoDestroy="true"></cui:dialog>
<!-- 报警处置 -->
<cui:dialog id="dialogId_bjcz" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
 <!-- 民警详细信息 -->
<cui:dialog id="dialogId_zbmjxx" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" autoDestroy="true"></cui:dialog>
<!--  打开广播  -->
<cui:dialog id="plbfDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<!-- 应急预案浏览 -->
<cui:dialog id="dialogId_viewEmerPreplan" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"></cui:dialog>

<script> 
	
	var type = "${type}";
	var jsConst = window.top.jsConst;
	var cusNumber = null;
	var dprtmntId = null;
	var cellNum = null;
	var alarmRecordId = "${id}";//报警记录ID
	var receiveFlag = null;//是否接警
	var areaId = null; //区域编号
	var areaName = null; //区域名称
	var alarmDeviceId = null;//报警器id
	var alarmPlanId = null;//报警预案i
	var alarmAddress = null;//报警地点
	var czzt_data = <%=CodeFacade.loadCode2Json("4.20.26")%>;//处置状态
	var bjdj_data = <%=CodeFacade.loadCode2Json("4.20.25")%>;//报警等级
	var bjlx_data = <%=CodeFacade.loadCode2Json("4.20.27")%>;//报警类型
	var is3D = jsConst.MAP_TYPE == "0" ? false : true;
	var isSJUser = jsConst.USER_LEVEL == 1 ? true : false;/* 判断是否省局用户 */
	var isList = "${isList}" == "0" ? false : true; //是否从报警列表跳转过来
	/*页面加载初始化*/
	$.parseDone(function() {
		if (type == "0") {
			if (isEmpty(alarmRecordId)) {
				$.messageQueue({ message : "【传参错误】报警记录无法查询", cls : "warning", iframePanel : true, type : "info" });
				return;
			}
			$("#alarmMsgDiv").show();//show 报警消息展示界面
			$('#formId_bjxx').form("setIsLabel", true);
			loadAlarmMsg(alarmRecordId);//加载报警信息
			return;
		}
		if (type == "1") {
			$("#p_alarmMsgDiv").show();//show人工报警界面
			//加载区域信息
			$("#comboId_area_alert").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0");
			//报警时间取当前时间
			$("#datepId_now").datepicker("setDate", new Date());
			//报警性质
			$("#combId_event").combobox( "reload", "${ctx}/flow/seachComboData.json?cusNumber=" + jsConst.CUS_NUMBER);
		}

	});
    
	
	//加载当前报警信息 , 填充报警信息  
	function loadAlarmMsg(id) {
		var url = "${ctx}/alarm/findById.json?id=" + id;
		var callBack = function(data) {
			if (data.success) {
				fillAlarmMessage(data.obj); // 填充报警信息
				alarmAddress = data.obj.ALARM_ADDRESS;
				dprtmntId = data.obj.DPRTMNT_ID;
				cusNumber = data.obj.ARD_CUS_NUMBER;
				cellNum = data.obj.CELL_NUM;
				areaId = data.obj.AREA_ID;//区域id
				areaName = data.obj.AREA;//区域NAME
				alarmDeviceId = data.obj.ALERTOR_ID;
				receiveFlag = data.obj.ARD_RECEIVE_STTS == "1" ? true : false;
				judge(data.obj.ARD_ALERT_LEVEL_INDC);//判断是否报警弹窗，是否上报省局
				if (data.obj.ARD_OPRTN_STTS_INDC != "3") {//操作状态不是已完成才会加载图层，报警声响
					showAlarmLayer(data.obj.ARD_CUS_NUMBER);
					if (!isList) {
						jsConst.ALARM_ID = id; //全局变量，判断处置完成后，接到 websocket 消息是否刷新处置界面
						//embed 因为数据(video, audio, flash, activex)的特殊性，跟一般DOM处理上有所不同，在载入后浏览器会拒绝改变它们的 src 属性。
						var box = document.getElementById('audioId_') ;
						var str = "<embed  src=\"${ctx}/alarm/handle/getAlarmSound?deviceId="+alarmDeviceId +"&cusNumber="+cusNumber+"\" />"; 
						box.innerHTML = str;
						setTimeout(function(){
							alermMusic(true);//报警记录列表进入处置界面，报警声不响
						}, alarmAddress.length / 2 * 1000);
					}
				}
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, null, callBack);
	}

	//判断是否需要提示报警，是否需要提交省局
	function judge(alarmLevel) {
		// alert("alarmSystem judge alarmLevel = " + alarmLevel);
		// alert("alarmSystem judge receiveFlag = " + receiveFlag);
		if (!receiveFlag) {
			setTimeout(function(){
				showReceiveAlarm(alarmLevel);
			},jsConst.RELAY_HINT * 1000); // 延迟 jsConst.RELAY_HINT 进入倒计时窗
		}
	}

	// 填充报警信息
	function fillAlarmMessage(msg) {
		console.log("================================ fillAlarmMessage msg is ================================ " + JSON.stringify(msg));

		$('#formId_bjxx').form("load", msg);//刷新报警信息数据
		
		//如果处置状态不是3、已完成， 显示接警和启动预案按钮，否则不渲染预案信息
		if (msg.ARD_OPRTN_STTS_INDC != "3") {
			$("#divId_receive").show();
		} else {
			$("#divId_receive").hide();
			return;
		}
		 
		queryCellPrisoner(msg.ARD_CUS_NUMBER, msg.AREA_ID, msg.CELL_NUM);//查询罪犯信息
		queryPoliceList(msg.ARD_CUS_NUMBER, msg.DPRTMNT_ID);//查询民警信息
		//如果关联了报警预案，就加载预案关联项信息
		var dvcIdnty = msg.ARD_ALERTOR_IDNTY; //设备id
		if ( isNotEmpty(dvcIdnty)) {
			loadAlarmPlan(msg.ARD_CUS_NUMBER, dvcIdnty);
		}

	}

	function alarmReceive() {
		changereceiveFlag();//改变接警的状态
		 
		$("#dialogId_bjcz").dialog({
			width : 800,
			height : 800,
			url : "${ctx}/alarm/handle/openDialog/receive?id=" + alarmRecordId,
			title : "接警处置",
		});

		$("#dialogId_bjcz").dialog("open");

	}

	//改变接警的状态
	function changereceiveFlag() {
		if (!receiveFlag) {
			var data = {};
			data["id"] = alarmRecordId;
			data["ardReceiveStts"] = "1";//接警
			
			var url = '${ctx}/alarm/update/record.json';
			var callBack = function(resDate) {
				if (resDate.success) {
					receiveFlag = true;
				} else {
					$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			};
			ajaxTodo(url, data, callBack);
		}

	}

	//人工报警提交
	function subAlarmMsg() {
		if ($("#formId_alarmMsg_man").form("valid")) {
			var formData = $("#formId_alarmMsg_man").form("formData");
			alarmPolice = $("#combId_police").combobox("getText");
			if (formData["ardAlarmPoliceId"] != "" && alarmPolice != "") {
				formData["ardAlarmPolice"] = alarmPolice;
			} else {
				formData["ardAlarmPoliceId"] = jsConst.USER_ID;
				formData["ardAlarmPolice"] = jsConst.REAL_NAME;
			}
			if(!isEmpty(formData["ardEventId"])) {
				formData["ardEventType"] = $("#combId_event").combobox("getText");
			}
			var url = '${ctx}/alarm/handle/manualAlarm.json';
			
			var callBack = function(resDate) {
				if (resDate.success) {
					$.messageQueue({ message : resDate.msg, cls : "success", iframePanel : true, type : "info" });
					//toDisplay('homeMenu');//人工报警界面转到主页
					$("#subAlarmMsg").hide();
				} else {
					$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			};
			ajaxTodo(url, formData, callBack);
			
		} else {
			$.messageQueue({ message : "请确认输入是否正确！", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	/*
	 * 启动预案
	 */
	function eventHanding() {
		if (confirm('当监狱发生重特大监管安全报警时，方可启动预案，是否确认启动预案？')) {
			if ( isNotEmpty(alarmPlanId) ) {
				changereceiveFlag();//改变接警的状态
				// TODO 启动预案按钮执行后续
				/**
				 * update by lincoln.cheng 2019.10.08 old start
				 */
				//调用菜单栏上 应急处突的链接并加入报警ID和应急预案的类型
				$("#layout1").layout("panel", "east").panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=3&ehrAlarmPlanFid=" + alarmPlanId + "&ehrAddressFid=" + areaId + "&alarmRecordId=" + alarmRecordId);
				/** update by lincoln.cheng 2019.10.08 old end */
				/**
				 * update by lincoln.cheng 2019.10.08 new start
				 */
				//打开启动预案页面
				/*$("#dialogId_viewEmerPreplan").dialog({
                    width : 1065,
                    height : 600,
                    url : "$1{ctx}/emergency/preplanStart/openDialog?alarmRecordId=" + alarmRecordId,
                    title : "启动预案",
                });
                $("#dialogId_viewEmerPreplan").dialog("open");*/
				/** update by lincoln.cheng 2019.10.08 new end */
			} else {
				alert("未查询到报警预案信息");
			}
		}
	}

	/**
	 * 新启动预案 TODO
	 */
	function eventHandingNew() {
		if (confirm('当监狱发生重特大监管安全报警时，方可启动预案，是否确认启动预案？')) {
			if (isNotEmpty(alarmPlanId)) {
				changereceiveFlag();//改变接警的状态

				// TODO 启动预案按钮执行后续
				/**
				 * update by lincoln.cheng 2019.10.08 old start
				 */
				//调用菜单栏上 应急处突的链接并加入报警ID和应急预案的类型
				// $("#layout1").layout("panel", "east").panel( "refresh", "${ctx}/yjct/yjjl/toZxjl?type=3&ehrAlarmPlanFid=" + alarmPlanId + "&ehrAddressFid=" + areaId + "&alarmRecordId=" + alarmRecordId);
				/** update by lincoln.cheng 2019.10.08 old end */
				/**
				 * update by lincoln.cheng 2019.10.08 new start
				 */
				// 查询报警预案关联的应急预案
				var url = "${ctx}/plan/queryAlarmEmerPlanRltnByAlarmPlanId.json";
				var params = {};
				params['alarmPlanId'] = alarmPlanId;

				var callBack = function(data) {
					if (data.success) {
						if(data.obj && data.obj.emerPlanId) {
							// 直接启动预案
							quickStartPreplan(data.obj.emerPlanId);
						} else {
							// 打开预案选择页面
							$("#dialogId_viewEmerPreplan").dialog({
								width: 1065,
								height: 600,
								url: "${ctx}/emergency/preplanStart/openDialog?alarmPlanId=" + alarmPlanId + "&alarmRecordId=" + alarmRecordId,
								title: "预案浏览",
							});
							$("#dialogId_viewEmerPreplan").dialog("open");
						}
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				};
				ajaxTodo(url, params, callBack);
				/** update by lincoln.cheng 2019.10.08 new end */
			} else {
				alert("未查询到报警预案信息");
			}
		}
	}

	/* 区域树下拉点击事件 */
	function onAreaTreeClick(event, ui) {
		$("#combId_alert").combobox( "reload", "${ctx}/alertor/seachComboData.json?cusNumber=" + jsConst.CUS_NUMBER + "&areaId=" + ui.node.id);
		$("#combId_police").combobox( "reload", "${ctx}/common/authsystem/findPoliceByAreaIdForCombobox.json?cusNumber=" + jsConst.CUS_NUMBER + "&areaId=" + ui.node.id);
	}

	//3维地图时显示报警图层，定位视角 ；2维地图地位
	function showAlarmLayer(cus) {
		if (areaId != null && areaId != "") {
			toastMsg(areaName +'发生报警',"danger","custom");
			if(is3D && jsConst.ALARM_INFO == null){
				//区域 垂直视角
				map.showHideByAreaId('', areaId, 1);
				//显示当前区域以及其子节点的设备
				map.getAllPointByGrandAndType(cus, areaId, '', 1);
				if (isNotEmpty(alarmDeviceId)) {
					//加载报警图层 显示报警图层设别编号，闪烁，区域编号，监狱编号
					map.createRenderPolygonAndSetAdminCamera(alarmDeviceId, true, areaId, cus);
				}
			}else{
				//2维地图定位	
				var loadAlarmPoint = function callbackFunction(){
					if (isNotEmpty(alarmDeviceId)) {
						//var startAlarm_  = function(){
						//	$.sign_show.startAlarm(alarmDeviceId);
						//}
						//setTimeout(startAlarm_,3000);
						$.sign_show.startAlarm(alarmDeviceId);
					}
				}
				planeViewPosition(areaId,areaName,jsConst.USER_LEVEL,loadAlarmPoint);
			}
		} 

	}

	/*  报警事件处理完成，删除图层  
	 flag:0提交,1上级处置 */
	function stopAlarm(flag) {
		$("#divId_receive").hide();
		
		if (flag == 0) {
			$("#alarmOprtnStatus").combobox("setValue", "3");
		} else {
			$("#alarmOprtnStatus").combobox("setValue", "0");//处置状态  0、上级处置
		}
		
		if(is3D){
			map.removeAlarm();//删除图层
		}else{
			$.sign_show.stopAlarm(alarmDeviceId);
		}
	
		alermMusic(false);
		var alarmReceive = $("#alarmReceive").textbox("getText");
		if (isEmpty(alarmReceive)) {
			$("#alarmReceive").textbox("setText", jsConst.REAL_NAME);
		}
	}

	//报警图标点击事件
	var sound = null;
	function playMusic() {
		alermMusic(sound);
		sound = !sound;
	}
	
	//报警声,报警图标
	function alermMusic(flag) {
		if(flag){
			$("#imgId").attr("src", "${ctx}/static/resource/images/sound_on.png");
			document.getElementById("audioId").play();
		}else{
			$("#imgId").attr("src", "${ctx}/static/resource/images/sound_off.png");
			document.getElementById("audioId").pause();
		}
		sound = flag;
	}
	
	

	//打开取消报警弹窗
	function openAlarmCancel() {
		$("#dialogId_cancelAlarm").dialog({
			width : 500,
			height : 300,
			title : "取消报警"
		})
		$("#dialogId_cancelAlarm").dialog("open");
	}
	
	function opentxrh(event){
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
		var url = jsConst.basePath+'/rcs/openDialog';
		$('#dialog').html("");
		if (w == null || h == null) {
			w = 800;
			h = 600;
		}

		$('#dialog').dialog({
			width : w,
			height : h,
			title : "通信融合",
			url : url
		});
		$("#dialog").dialog("open");
	}
	
	
	//关闭取消报警窗口
	function close_qxbj() {
		$("#dialogId_cancelAlarm").dialog("close");
	}

	//发生取消报警请求
	function alarmCancel() {
		if ( !receiveFlag ) { 
			
			if ($("#formId_cancelAlarm").form("valid")) {
				var data = $("#formId_cancelAlarm").form("formData");
				data["id"] = alarmRecordId;
				var url = '${ctx}/alarm/handle/cancel.json';
				var callBack = function(resDate) {
					if (resDate.success) {
						close_qxbj();
						$.messageQueue({ message : resDate.msg, cls : "success", iframePanel : true, type : "info" });
						stopAlarm(0);//界面：报警图层停止闪烁，处置状态改为处置完成，接警人改成当前操作者，隐藏操作按钮
						stopReceiveAlarm();
					} else {
						$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				};
				ajaxTodo(url, data, callBack);
				
			} else {
				$.messageQueue({ message : "请填写取消报警原因", cls : "warning", iframePanel : true, type : "info" });
			}
		} else {
			$.messageQueue({ message : "已经接警，报警无法取消", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	
	function openPrisonerList() {
		var url = "${ctx}/xxhj/zfjbxx/jsPrisonerInfo?cusNumber=" + cusNumber + "&lch=" + areaId;
		if(isNotEmpty(cellNum)){
			url = url + "&jsh=" + cellNum;
		}
		$("#dialogId_prisoner_list").dialog({
			width : 800,
			height : 600,
			title : "罪犯列表",
			url : url,
			});  
		$("#dialogId_prisoner_list").dialog("open");  
	}
	
	function openPoliceList() {
		$("#dialogId_police_list").dialog({
			width :  800,
			height : 600,
			title : "民警列表",
			url : "${ctx}/xxhj/jnmj/openPoliceList?pbdDrptmntId=" + dprtmntId + "&pbdcusNumber=" + cusNumber,
			});  
		$("#dialogId_police_list").dialog("open");  
	}
	
	
	var si = null;
	var ram = null;
	//弹出接警倒计时框  alarmLevel报警等级
	function showReceiveAlarm(alarmLevel){
		// alert("alarmSystem showReceiveAlarm alarmLevel = " + alarmLevel);
		if (isSJUser || isList || alarmLevel == "1" || alarmLevel == "3"){
			return;
		}
		var cusName = '省局指挥中心';
		var msg = null;
		msg = "<span id='alarmMsg'><span id='alarmTimeCount' style='font-size:16px;color:red;'>"+jsConst.RELAY_UP_LEVEL+"</span><b>&nbsp;&nbsp;秒后未接警,</b><br><b>将作为违规事件上报至"+cusName+"。</b></span>";
		ram = $.messageBox( {
			       message: msg,
			       position: { my: "right top", at: "right top", of: window },
			       timeOut : 1000 * (jsConst.RELAY_UP_LEVEL + 10)
			    });  

		si = setInterval(function(){
			if(parseInt($("#alarmTimeCount").text()) >= 1){
				if(parseInt($("#alarmTimeCount").text()) == 1){
					$("#alarmTimeCount").text("0");
					//请求查询报警记录
					
					var url = "${ctx}/alarm/findById.json?id=" + alarmRecordId;
					var callBack = function(resDate) {
						if (resDate.success) {
							 if(resDate.obj.ARD_RECEIVE_STTS == null || resDate.obj.ARD_RECEIVE_STTS == "0"){//接警状态  0、未接警
								 $("#alarmMsg").empty();
								 $("#alarmMsg").append("<b>已上报至"+cusName+"。</b>");	
							 } else {
								 $("#alarmMsg").empty();
								 $("#alarmMsg").append("<b>报警已处置。</b>");	
							 }
						}
					};
					ajaxTodo(url, null, callBack);
				}else{
					$("#alarmTimeCount").text($("#alarmTimeCount").text()-1);
					if(receiveFlag){
						stopReceiveAlarm();
					}
				}
			}else{
				clearInterval(si);
			}
		},1 * 1000);
	}
	function stopReceiveAlarm(){
		clearInterval(si);
		$("#alarmMsg").empty();
		$("#alarmMsg").append("<b>报警已开始处置。</b>");	
	}

	/**
	 * 根据报警预案关联的应急预案，快速启动
	 */
	function quickStartPreplan(preplanId) {
		// 新增报警处置记录
		var url = "${ctx}/emergency/handle/recordManage/saveOrUpdate.json";
		var params = {};
		params["alarmPlanId"] = alarmPlanId;
		params["alarmRecordId"] = alarmRecordId;
		if(preplanId) {
			params["preplanId"] = preplanId;
		}
		var callBack = function (data) {
			console.log("process index.jsp quickStartPreplan data = " + JSON.stringify(data));
			if (data.success) {
				// 打开报警处置窗口
				startEmerHandle(data.obj);
			} else {
				console.log("process index.jsp quickStartPreplan data failed");
				// $.loading("hide");
				$.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
			}
		};
		ajaxTodo(url, params, callBack);
	}

	/**
	 * 开始应急处置
	 * @param event
	 * @param ui
	 */
	function startEmerHandle(emerHandleRecord) {
		// 刷新右侧处置面板
		// handleType：1-事件处置，2-事件演练，3-报警处置，4-处置记录处置(现在使用中的只有3和4，1和2类型已停用)
		var url = "${ctx}/emergency/handle/recordManage/handlePanel?emerHandleRecordId=" + emerHandleRecord.id + "&handleType=3";
		$("#layout1").layout("panel", "east").panel("refresh", url);
	}
</script>


