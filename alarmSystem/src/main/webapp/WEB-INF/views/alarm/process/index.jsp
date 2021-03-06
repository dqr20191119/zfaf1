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
		????????????
		<img id="imgId" height="26px" width="26px" src="${ctx}/static/resource/images/sound_off.png" onclick="playMusic()" />
		<!-- <div class="setup-box" id="setup-box">
			<li class="iconfont icon-shezhi">
				<ul>
					<li data-value="alarmReceive"><a href="#">????????????</a></li>
					<li data-value="eventHanding"><a href="#">????????????</a></li>
					<li data-value="openAlarmCancel"><a href="#">????????????</a></li>
				</ul>
			</li>
		</div> -->
	</h4>
	<!-- ???????????? -->
	<cui:form id="formId_bjxx" componentCls="info-list">
		<table>
			<tr>
				<td>?????????</td>
				<td colspan="3">
					<cui:input name="ALARM_ADDRESS" width="200"></cui:input>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td colspan="3">
					<cui:input name="ALERTOR_NAME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>?????????</td>
				<td colspan="3">
					<cui:input name="ARD_ALERT_TIME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>?????????</td>
				<td colspan="3">
					<cui:combobox name="ARD_TYPE_INDC" data="bjlx_data"></cui:combobox>
				</td>
				 
			</tr>
			<tr>
				 
				<td>?????????</td>
				<td colspan="3">
					<cui:combobox name="ARD_ALERT_LEVEL_INDC" data="bjdj_data"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>?????????</td>
				<td colspan="3">
					<cui:combobox id="alarmOprtnStatus" name="ARD_OPRTN_STTS_INDC" data="czzt_data"></cui:combobox>
				</td>
				 
			</tr>
			<tr>
				 
				<td>????????????</td>
				<td colspan="3">
					 <cui:input id="alarmReceive" name="ARD_RECEIVE_ALARM_POLICE"></cui:input>
				</td>
			</tr>
			
		</table>
	</cui:form>
	<div id="divId_receive" style="display: none;">
		<cui:button componentCls="btn-success" onClick="alarmReceive" label="????????????"></cui:button>
		<cui:button componentCls="btn-danger" onClick="eventHandingNew" label="????????????" style="margin-left:20px"></cui:button>
		<%--<cui:button componentCls="btn-danger" onClick="eventHanding" label="?????????????????????" style="margin-left:40px"></cui:button>--%>
		<br><br>
		<cui:button componentCls="btn-warning" onClick="openAlarmCancel" label="????????????" style="margin-left:0px"></cui:button>
		<cui:button componentCls="btn-warning" onClick="opentxrh(event)" label="????????????" style="margin-left:40px"></cui:button>
		
	</div>

</div>
<div class="rightDivStyle right-zb zoom-box" id="switch" style="display:none">
	<h4 align="center">????????????
		<a href="javascript:void(0)" onclick="openPoliceList()"><span class="moreCls more-police">?????????</span></a>
	</h4>
	<div class="zxx_test_list_list">
          <div id="caroEndless" class="caro_trigger caro_trigger2">
          	<a href="javascript:void(0)" class="caro_prev cui-icon-arrow-left3" data-rel="caroSwitchEnd"></a>
              <a href="javascript:void(0)" class="caro_next cui-icon-arrow-right3" data-rel="caroSwitchEnd"></a>
          </div>
          <div class="caro_box caro_box2">
          	<ul id="caroBoxEnd" class="caro_container caro_container2">
              	<%--  <li class="caroSwitchEnd"><img src="${ctx}/static/images/image1.png"><p>??????1</p><p>2012021</p></li>
                  <li class="caroSwitchEnd"><img src="${ctx}/static/images/image2.png"><p>??????2</p><p>2012021</p></li>
                  <li class="caroSwitchEnd"><img src="${ctx}/static/images/cloud.png"><p>??????3</p><p>2012021</p></li> --%>  
              </ul>
          </div>
    </div>
</div>
<div class="rightDivStyle right-zb zoom-box" id="switch-criminal" style="display:none">
	<h4 align="center">????????????
		<a href="javascript:void(0)" onclick="openPrisonerList()"><span class="moreCls more-criminal"> ?????????</span></a>
	</h4>
	<div class="zxx_test_list_list">
          <div id="caroEndless-cri" class="caro_trigger caro_trigger2">
              <a href="javascript:void(0)" class="caro_prev cui-icon-arrow-left3" data-rel="caroSwitchEnd1"></a>
              <a href="javascript:void(0)" class="caro_next cui-icon-arrow-right3" data-rel="caroSwitchEnd1"></a>
          </div>
          <div class="caro_box caro_box2">
          	  <ul id="caroBoxEnd1" class="caro_container caro_container2">
              	  <%-- <li class="caroSwitchEnd1"><img src="${ctx}/static/images/image1.png"><p>??????1</p><p>2012021</p></li>
                  <li class="caroSwitchEnd1"><img src="${ctx}/static/images/image2.png"><p>??????2</p><p>2012021</p></li>
                  <li class="caroSwitchEnd1"><img src="${ctx}/static/images/cloud.png"><p>??????3</p><p>2012021</p></li> --%>
              </ul>
          </div>
    </div>
</div>
<div class="rightDivStyle right-zb" id="device_show" style="display: none; height: auto;">
	<!-- <H4 id="alarmPlanName" align="center" style="color: white;"></H4> -->
	<div id="divId_device" class="picfrm"></div>
</div>

<div class="rightDivStyle right-zb" id="p_alarmMsgDiv" style="display: none; color: white;">
	<H4 align="center">????????????</H4>
	<cui:form id="formId_alarmMsg_man">
		<table class="table" style="width: 97%;margin:0 auto">
			<tr>
			
				<th style="padding: 0px">?????????</th>
				<td>
					<cui:combotree id="comboId_area_alert" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick">
					</cui:combotree>
				</td>
			</tr>

			<tr>
				<th style="padding: 0px">?????????</th>
				<td>
					<cui:datepicker id="datepId_now" name="ardAlertTime" dateFormat="yyyy-MM-dd HH:mm:ss" readonly="true" required="true"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">?????????</th>
				<td>
					<cui:combobox id="combId_event" name="ardEventId"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">????????????</th>
				<td>
					<cui:combobox id="combId_alert" name="ardAlertorIdnty" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th style="padding: 0px">????????????</th>
				<td>
					<cui:combobox id="combId_police" name="ardAlarmPoliceId" emptyText="????????????"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<cui:button id ="subAlarmMsg" componentCls="btn-success" onClick="subAlarmMsg" width="100" label="??????"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
</div>
</div>
<!-- ???????????? -->
<audio id="audioId" loop="loop" src="${ctx}/static/resource/audio/alarm.mp3"></audio>

<div id = "audioId_"></div>
<!-- ?????????????????? -->
<cui:dialog id="dialogId_cancelAlarm" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true">
	<div style="text-align: center; height: 100%; width: 100%">
		<cui:form id="formId_cancelAlarm" heightStyle="fill">
			<table class="table" style="width: 98%">
				<tr>
					<th>???????????????</th>
					<td  >
						<cui:textarea  componentCls="form-control" name="ardLocalCase"></cui:textarea>
					</td>
				</tr>
				<tr>
					<th>???????????????</th>
					<td>
						<cui:textarea name="ardRemark" componentCls="form-control" required="true"></cui:textarea>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons" style="text-align: center; margin-bottom: 0px">
			<cui:button onClick="alarmCancel" label="??????"></cui:button>
			<cui:button label="??????" onClick="close_qxbj"></cui:button>
		</div>
	</div>
</cui:dialog>

<!-- ?????????????????? -->
<cui:dialog id="dialogId_prisoner_list" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
<!-- ???????????? -->
<cui:dialog id="dialogId_police_list" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
<!-- ?????????????????? -->
<cui:dialog id="dialogId_jszfxx" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false"  autoDestroy="true"></cui:dialog>
<!-- ???????????? -->
<cui:dialog id="dialogId_bjcz" autoOpen="false" resizable="false" reLoadOnOpen="true" iframePanel="true"></cui:dialog>
 <!-- ?????????????????? -->
<cui:dialog id="dialogId_zbmjxx" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" autoDestroy="true"></cui:dialog>
<!--  ????????????  -->
<cui:dialog id="plbfDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<!-- ?????????????????? -->
<cui:dialog id="dialogId_viewEmerPreplan" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"></cui:dialog>

<script> 
	
	var type = "${type}";
	var jsConst = window.top.jsConst;
	var cusNumber = null;
	var dprtmntId = null;
	var cellNum = null;
	var alarmRecordId = "${id}";//????????????ID
	var receiveFlag = null;//????????????
	var areaId = null; //????????????
	var areaName = null; //????????????
	var alarmDeviceId = null;//?????????id
	var alarmPlanId = null;//????????????i
	var alarmAddress = null;//????????????
	var czzt_data = <%=CodeFacade.loadCode2Json("4.20.26")%>;//????????????
	var bjdj_data = <%=CodeFacade.loadCode2Json("4.20.25")%>;//????????????
	var bjlx_data = <%=CodeFacade.loadCode2Json("4.20.27")%>;//????????????
	var is3D = jsConst.MAP_TYPE == "0" ? false : true;
	var isSJUser = jsConst.USER_LEVEL == 1 ? true : false;/* ???????????????????????? */
	var isList = "${isList}" == "0" ? false : true; //?????????????????????????????????
	/*?????????????????????*/
	$.parseDone(function() {
		if (type == "0") {
			if (isEmpty(alarmRecordId)) {
				$.messageQueue({ message : "??????????????????????????????????????????", cls : "warning", iframePanel : true, type : "info" });
				return;
			}
			$("#alarmMsgDiv").show();//show ????????????????????????
			$('#formId_bjxx').form("setIsLabel", true);
			loadAlarmMsg(alarmRecordId);//??????????????????
			return;
		}
		if (type == "1") {
			$("#p_alarmMsgDiv").show();//show??????????????????
			//??????????????????
			$("#comboId_area_alert").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0");
			//???????????????????????????
			$("#datepId_now").datepicker("setDate", new Date());
			//????????????
			$("#combId_event").combobox( "reload", "${ctx}/flow/seachComboData.json?cusNumber=" + jsConst.CUS_NUMBER);
		}

	});
    
	
	//???????????????????????? , ??????????????????  
	function loadAlarmMsg(id) {
		var url = "${ctx}/alarm/findById.json?id=" + id;
		var callBack = function(data) {
			if (data.success) {
				fillAlarmMessage(data.obj); // ??????????????????
				alarmAddress = data.obj.ALARM_ADDRESS;
				dprtmntId = data.obj.DPRTMNT_ID;
				cusNumber = data.obj.ARD_CUS_NUMBER;
				cellNum = data.obj.CELL_NUM;
				areaId = data.obj.AREA_ID;//??????id
				areaName = data.obj.AREA;//??????NAME
				alarmDeviceId = data.obj.ALERTOR_ID;
				receiveFlag = data.obj.ARD_RECEIVE_STTS == "1" ? true : false;
				judge(data.obj.ARD_ALERT_LEVEL_INDC);//?????????????????????????????????????????????
				if (data.obj.ARD_OPRTN_STTS_INDC != "3") {//????????????????????????????????????????????????????????????
					showAlarmLayer(data.obj.ARD_CUS_NUMBER);
					if (!isList) {
						jsConst.ALARM_ID = id; //????????????????????????????????????????????? websocket ??????????????????????????????
						//embed ????????????(video, audio, flash, activex)????????????????????????DOM????????????????????????????????????????????????????????????????????? src ?????????
						var box = document.getElementById('audioId_') ;
						var str = "<embed  src=\"${ctx}/alarm/handle/getAlarmSound?deviceId="+alarmDeviceId +"&cusNumber="+cusNumber+"\" />"; 
						box.innerHTML = str;
						setTimeout(function(){
							alermMusic(true);//??????????????????????????????????????????????????????
						}, alarmAddress.length / 2 * 1000);
					}
				}
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, null, callBack);
	}

	//?????????????????????????????????????????????????????????
	function judge(alarmLevel) {
		// alert("alarmSystem judge alarmLevel = " + alarmLevel);
		// alert("alarmSystem judge receiveFlag = " + receiveFlag);
		if (!receiveFlag) {
			setTimeout(function(){
				showReceiveAlarm(alarmLevel);
			},jsConst.RELAY_HINT * 1000); // ?????? jsConst.RELAY_HINT ??????????????????
		}
	}

	// ??????????????????
	function fillAlarmMessage(msg) {
		console.log("================================ fillAlarmMessage msg is ================================ " + JSON.stringify(msg));

		$('#formId_bjxx').form("load", msg);//????????????????????????
		
		//????????????????????????3??????????????? ???????????????????????????????????????????????????????????????
		if (msg.ARD_OPRTN_STTS_INDC != "3") {
			$("#divId_receive").show();
		} else {
			$("#divId_receive").hide();
			return;
		}
		 
		queryCellPrisoner(msg.ARD_CUS_NUMBER, msg.AREA_ID, msg.CELL_NUM);//??????????????????
		queryPoliceList(msg.ARD_CUS_NUMBER, msg.DPRTMNT_ID);//??????????????????
		//????????????????????????????????????????????????????????????
		var dvcIdnty = msg.ARD_ALERTOR_IDNTY; //??????id
		if ( isNotEmpty(dvcIdnty)) {
			loadAlarmPlan(msg.ARD_CUS_NUMBER, dvcIdnty);
		}

	}

	function alarmReceive() {
		changereceiveFlag();//?????????????????????
		 
		$("#dialogId_bjcz").dialog({
			width : 800,
			height : 800,
			url : "${ctx}/alarm/handle/openDialog/receive?id=" + alarmRecordId,
			title : "????????????",
		});

		$("#dialogId_bjcz").dialog("open");

	}

	//?????????????????????
	function changereceiveFlag() {
		if (!receiveFlag) {
			var data = {};
			data["id"] = alarmRecordId;
			data["ardReceiveStts"] = "1";//??????
			
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

	//??????????????????
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
					//toDisplay('homeMenu');//??????????????????????????????
					$("#subAlarmMsg").hide();
				} else {
					$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			};
			ajaxTodo(url, formData, callBack);
			
		} else {
			$.messageQueue({ message : "??????????????????????????????", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	/*
	 * ????????????
	 */
	function eventHanding() {
		if (confirm('????????????????????????????????????????????????????????????????????????????????????????????????')) {
			if ( isNotEmpty(alarmPlanId) ) {
				changereceiveFlag();//?????????????????????
				// TODO ??????????????????????????????
				/**
				 * update by lincoln.cheng 2019.10.08 old start
				 */
				//?????????????????? ????????????????????????????????????ID????????????????????????
				$("#layout1").layout("panel", "east").panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=3&ehrAlarmPlanFid=" + alarmPlanId + "&ehrAddressFid=" + areaId + "&alarmRecordId=" + alarmRecordId);
				/** update by lincoln.cheng 2019.10.08 old end */
				/**
				 * update by lincoln.cheng 2019.10.08 new start
				 */
				//????????????????????????
				/*$("#dialogId_viewEmerPreplan").dialog({
                    width : 1065,
                    height : 600,
                    url : "$1{ctx}/emergency/preplanStart/openDialog?alarmRecordId=" + alarmRecordId,
                    title : "????????????",
                });
                $("#dialogId_viewEmerPreplan").dialog("open");*/
				/** update by lincoln.cheng 2019.10.08 new end */
			} else {
				alert("??????????????????????????????");
			}
		}
	}

	/**
	 * ??????????????? TODO
	 */
	function eventHandingNew() {
		if (confirm('????????????????????????????????????????????????????????????????????????????????????????????????')) {
			if (isNotEmpty(alarmPlanId)) {
				changereceiveFlag();//?????????????????????

				// TODO ??????????????????????????????
				/**
				 * update by lincoln.cheng 2019.10.08 old start
				 */
				//?????????????????? ????????????????????????????????????ID????????????????????????
				// $("#layout1").layout("panel", "east").panel( "refresh", "${ctx}/yjct/yjjl/toZxjl?type=3&ehrAlarmPlanFid=" + alarmPlanId + "&ehrAddressFid=" + areaId + "&alarmRecordId=" + alarmRecordId);
				/** update by lincoln.cheng 2019.10.08 old end */
				/**
				 * update by lincoln.cheng 2019.10.08 new start
				 */
				// ???????????????????????????????????????
				var url = "${ctx}/plan/queryAlarmEmerPlanRltnByAlarmPlanId.json";
				var params = {};
				params['alarmPlanId'] = alarmPlanId;

				var callBack = function(data) {
					if (data.success) {
						if(data.obj && data.obj.emerPlanId) {
							// ??????????????????
							quickStartPreplan(data.obj.emerPlanId);
						} else {
							// ????????????????????????
							$("#dialogId_viewEmerPreplan").dialog({
								width: 1065,
								height: 600,
								url: "${ctx}/emergency/preplanStart/openDialog?alarmPlanId=" + alarmPlanId + "&alarmRecordId=" + alarmRecordId,
								title: "????????????",
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
				alert("??????????????????????????????");
			}
		}
	}

	/* ??????????????????????????? */
	function onAreaTreeClick(event, ui) {
		$("#combId_alert").combobox( "reload", "${ctx}/alertor/seachComboData.json?cusNumber=" + jsConst.CUS_NUMBER + "&areaId=" + ui.node.id);
		$("#combId_police").combobox( "reload", "${ctx}/common/authsystem/findPoliceByAreaIdForCombobox.json?cusNumber=" + jsConst.CUS_NUMBER + "&areaId=" + ui.node.id);
	}

	//3????????????????????????????????????????????? ???2???????????????
	function showAlarmLayer(cus) {
		if (areaId != null && areaId != "") {
			toastMsg(areaName +'????????????',"danger","custom");
			if(is3D && jsConst.ALARM_INFO == null){
				//?????? ????????????
				map.showHideByAreaId('', areaId, 1);
				//?????????????????????????????????????????????
				map.getAllPointByGrandAndType(cus, areaId, '', 1);
				if (isNotEmpty(alarmDeviceId)) {
					//?????????????????? ?????????????????????????????????????????????????????????????????????
					map.createRenderPolygonAndSetAdminCamera(alarmDeviceId, true, areaId, cus);
				}
			}else{
				//2???????????????	
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

	/*  ???????????????????????????????????????  
	 flag:0??????,1???????????? */
	function stopAlarm(flag) {
		$("#divId_receive").hide();
		
		if (flag == 0) {
			$("#alarmOprtnStatus").combobox("setValue", "3");
		} else {
			$("#alarmOprtnStatus").combobox("setValue", "0");//????????????  0???????????????
		}
		
		if(is3D){
			map.removeAlarm();//????????????
		}else{
			$.sign_show.stopAlarm(alarmDeviceId);
		}
	
		alermMusic(false);
		var alarmReceive = $("#alarmReceive").textbox("getText");
		if (isEmpty(alarmReceive)) {
			$("#alarmReceive").textbox("setText", jsConst.REAL_NAME);
		}
	}

	//????????????????????????
	var sound = null;
	function playMusic() {
		alermMusic(sound);
		sound = !sound;
	}
	
	//?????????,????????????
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
	
	

	//????????????????????????
	function openAlarmCancel() {
		$("#dialogId_cancelAlarm").dialog({
			width : 500,
			height : 300,
			title : "????????????"
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
			title : "????????????",
			url : url
		});
		$("#dialog").dialog("open");
	}
	
	
	//????????????????????????
	function close_qxbj() {
		$("#dialogId_cancelAlarm").dialog("close");
	}

	//????????????????????????
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
						stopAlarm(0);//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
						stopReceiveAlarm();
					} else {
						$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				};
				ajaxTodo(url, data, callBack);
				
			} else {
				$.messageQueue({ message : "???????????????????????????", cls : "warning", iframePanel : true, type : "info" });
			}
		} else {
			$.messageQueue({ message : "?????????????????????????????????", cls : "warning", iframePanel : true, type : "info" });
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
			title : "????????????",
			url : url,
			});  
		$("#dialogId_prisoner_list").dialog("open");  
	}
	
	function openPoliceList() {
		$("#dialogId_police_list").dialog({
			width :  800,
			height : 600,
			title : "????????????",
			url : "${ctx}/xxhj/jnmj/openPoliceList?pbdDrptmntId=" + dprtmntId + "&pbdcusNumber=" + cusNumber,
			});  
		$("#dialogId_police_list").dialog("open");  
	}
	
	
	var si = null;
	var ram = null;
	//????????????????????????  alarmLevel????????????
	function showReceiveAlarm(alarmLevel){
		// alert("alarmSystem showReceiveAlarm alarmLevel = " + alarmLevel);
		if (isSJUser || isList || alarmLevel == "1" || alarmLevel == "3"){
			return;
		}
		var cusName = '??????????????????';
		var msg = null;
		msg = "<span id='alarmMsg'><span id='alarmTimeCount' style='font-size:16px;color:red;'>"+jsConst.RELAY_UP_LEVEL+"</span><b>&nbsp;&nbsp;???????????????,</b><br><b>??????????????????????????????"+cusName+"???</b></span>";
		ram = $.messageBox( {
			       message: msg,
			       position: { my: "right top", at: "right top", of: window },
			       timeOut : 1000 * (jsConst.RELAY_UP_LEVEL + 10)
			    });  

		si = setInterval(function(){
			if(parseInt($("#alarmTimeCount").text()) >= 1){
				if(parseInt($("#alarmTimeCount").text()) == 1){
					$("#alarmTimeCount").text("0");
					//????????????????????????
					
					var url = "${ctx}/alarm/findById.json?id=" + alarmRecordId;
					var callBack = function(resDate) {
						if (resDate.success) {
							 if(resDate.obj.ARD_RECEIVE_STTS == null || resDate.obj.ARD_RECEIVE_STTS == "0"){//????????????  0????????????
								 $("#alarmMsg").empty();
								 $("#alarmMsg").append("<b>????????????"+cusName+"???</b>");	
							 } else {
								 $("#alarmMsg").empty();
								 $("#alarmMsg").append("<b>??????????????????</b>");	
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
		$("#alarmMsg").append("<b>????????????????????????</b>");	
	}

	/**
	 * ??????????????????????????????????????????????????????
	 */
	function quickStartPreplan(preplanId) {
		// ????????????????????????
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
				// ????????????????????????
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
	 * ??????????????????
	 * @param event
	 * @param ui
	 */
	function startEmerHandle(emerHandleRecord) {
		// ????????????????????????
		// handleType???1-???????????????2-???????????????3-???????????????4-??????????????????(????????????????????????3???4???1???2???????????????)
		var url = "${ctx}/emergency/handle/recordManage/handlePanel?emerHandleRecordId=" + emerHandleRecord.id + "&handleType=3";
		$("#layout1").layout("panel", "east").panel("refresh", url);
	}
</script>


