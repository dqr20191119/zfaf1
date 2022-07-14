//shouye.jsp 中的回调函数

/**
 * 报警回调
 * @param data
 * @returns
 */
function callbackAlarmHandleEXT1(data) {
	console.log("))))))))))))))))))))))))))");
	console.log(data);
	var alarmData = JSON.parse(data);
	var alarmRecordId_ = alarmData.id;// 报警记录id
	var alarmLev = alarmData.ardAlertLevelIndc;// 报警等级
	var cusNumber = alarmData.ardCusNumber;// 监狱号
	var cusName = null;// 监狱
	var alertorIdnty = alarmData.ardAlertorIdnty;// 报警器编号
	var is3D = jsConst.MAP_TYPE == "0" ? false : true;
	var oprtnStts = alarmData.ardOprtnSttsIndc;// 处置状态
	if ("0" == oprtnStts) {// 处置状态 0、上级处理
		toAlarmHandle(alarmRecordId_);
		return;
	}

	if ("3" == oprtnStts) {// 报警处置完成，界面刷新
		if (jsConst.ALARM_ID == alarmRecordId_) {
			toAlarmHandle(alarmRecordId_);
			return;
		}
	}

	if (alarmLev == "1") {
		if (jsConst.USER_LEVEL == 1) {
			$.ajax({
				type : "post",
				url : jsConst.basePath + "/alarm/findById.json?id=" + alarmRecordId_,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						cusName = getCusName(cusNumber);
						$.messageBox({
							message : "<span><span style='font-size:16px;color:red;>【 " + cusName + " 】<span>发生一级报警，<span style='font-size:16px;color:red;'>"
									+ jsConst.RELAY_UP_LEVEL + "</span><b>&nbsp;&nbsp;秒未处理报警！</b></span>",
							position : { my : "right top", at : "right top", of : window },
							timeOut : 1000 * 20
						});
						if (is3D) {
							jsConst.ALARM_INFO = {
								'areaId' : data.obj.AREA_ID,
								'alarmDeviceId' : data.obj.ALERTOR_ID,
								'cusNumber' : cusNumber
							}
							map.loadMapInfo(data.obj.ARD_CUS_NUMBER, cusName);// 3维地图切换
						}
						toAlarmHandle(alarmRecordId_);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else if (jsConst.USER_LEVEL == 2) {
			toAlarmHandle(alarmRecordId_);
		} else if (jsConst.USER_LEVEL == 3 && judgeUserAndDempt(cusNumber, alertorIdnty)) {
			toAlarmHandle(alarmRecordId_);
		}

	} else if (alarmLev == "2") {
		if (jsConst.USER_LEVEL == 1) {
			var t = jsConst.RELAY_UP_LEVEL + jsConst.RELAY_HINT;
			var timer = setInterval(
					function() {
						if (t >= 1) {
							if (t == 1) {
								t = 0;
								// 请求查询报警记录
								$.ajax({
									type : "post",
									url : jsConst.basePath + "/alarm/findById.json?id=" + alarmRecordId_,
									dataType : "json",
									success : function(data) {
										if (data.success) {
											if (data.obj.ARD_RECEIVE_STTS == null || data.obj.ARD_RECEIVE_STTS == "0") { 
												cusName = getCusName(cusNumber); //  接警状态 0、未接警
												$.messageBox({
													message : "<span><b>【 " + cusName + " 】发生二级报警</b><br><span style='font-size:16px;color:red;'>"
															+ jsConst.RELAY_UP_LEVEL + "</span><b>&nbsp;&nbsp;秒内未处理报警！</b></span>",
													position : { my : "right top", at : "right top", of : window },
													timeOut : 1000 * jsConst.RELAY_HINT
												});
												if (is3D) {
													jsConst.ALARM_INFO = { 
														'areaId' : data.obj.AREA_ID,
														'alarmDeviceId' : data.obj.ALERTOR_ID,
														'cusNumber' : cusNumber
													}
													map.loadMapInfo( data.obj.ARD_CUS_NUMBER, cusName);// 3维地图切换
												}
												toAlarmHandle(alarmRecordId_);
											}
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
									}
								});
							} else {
								t -= 1;
							}
						} else {
							clearInterval(timer);
						}
					}, 1 * 1000);
		} else {
			if (jsConst.USER_LEVEL == 2) {
				toAlarmHandle(alarmRecordId_);
			} else if (jsConst.USER_LEVEL == 3 && judgeUserAndDempt(cusNumber, alertorIdnty)) {
				toAlarmHandle(alarmRecordId_);
			}
		}
	} else if (alarmLev == "3") {
		if (jsConst.USER_LEVEL == 2 || (jsConst.USER_LEVEL == 3 && judgeUserAndDempt(cusNumber, alertorIdnty))) {
			var msg = " <span> 发生<span style='font-size:16px;color:red;'>&nbsp;三&nbsp;</span>级报警！ </span>&nbsp;<button onClick= \"toAlarmHandle('" + alarmRecordId_ + "')\">查看</button>";
			$.messageBox({
				message : msg,
				// position: { my: "right top", at: "right top", of: window },
				componentCls : "workmessage",
				message : msg,
				width : 300,
				height : 170,
				timeOut : 1000 * 30
			});
		}
	}

}
/**
 * 打开报警处置界面
 * @param alarmRecordId
 * @returns
 */
function toAlarmHandle(alarmRecordId) {
	var panel = $("#layout1").layout("panel", "east");
	panel.panel("refresh", jsConst.basePath + "/alarm/handle/index?type=0&id=" + alarmRecordId);
}

 
/**
 * 判断用户是否在该区域
 * @param cusNumber
 * @param alertorIdnty 报警器编号
 * @returns
 */
function judgeUserAndDempt(cusNumber, alertorIdnty) {
	var flag = false;
	var isF = null;
	var data = {};
	data["cusNumber"] = cusNumber;
	data["userId"] = jsConst.USER_ID;
	data["alertorIdnty"] = alertorIdnty;
	$.ajax({
		type : 'post',
		url : jsConst.basePath + '/alarm/handle/judge/UserAndDempt.json',
		data : data,
		dataType : 'json',
		async : false,
		success : function(data) {
			flag = true;
			if (data.success) {
				isF = data.obj;
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			flag = false;
			$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
		}
	});
	if (flag) {
		return isF;
	} else {
		return false;
	}
}

/**
 * 开关门指令返回消息回调
 * @param data
 * @returns
 */
function callbackMsgDoorReturnStatus1(data) {
	// "{\"action\":\"1\",\"brand\":\"4\",\"doorID\":\"0042\",\"doorName\":\"办公区一楼大门-1\",\"para\":\"\",\"returnValue\":\"1\",\"userID\":\"753177\"}"
	data = JSON.parse(data);
	var action = '';
	switch (data.action) {
	// 1 开门 2禁止开门 3恢复开门
	case "1":
		action = "开门";
		break;
	case "2":
		action = "禁止开门";
		break;
	case "3":
		action = "恢复开门";
		break;
	default:
		break;
	}
	var _cls = '';
	if (data.returnValue == '1') {
		_cls = 'success';
		action = action + "成功";
	} else {
		_cls = 'warning';
		action = action + "失败";
	}
	$.messageQueue({ message : data.doorName + action, cls : _cls, iframePanel : true, type : "info" });
}

/**
 * 对讲呼叫回调
 * 
 * @param data
 * @returns
 */
function callbackMsgTalkReturnStatus1(data) {
	data = JSON.parse(data);
	var _cls = '';
	if (data.sta == 1) {
		_cls = 'success';
		msg = "呼叫成功";
	} else {
		_cls = 'warning';
		msg = "呼叫失败";
	}
	$.messageQueue({ message : msg, cls : _cls, iframePanel : true, type : "info" });
}

/**
 * 融合通信呼叫或挂断回调
 * @param data
 */
function  callbackMsgTalkResultStatus1(data){
    console.log("融合通信呼叫或挂断回调数据:"+data);
    data = JSON.parse(data);
    var _cls = '';
   //挂断返回
        if(data.action=="1"){
            if (data.Result == 1) {
                _cls = 'success';
                msg = "呼叫成功";
            } else {
                _cls = 'warning';
                msg = "呼叫失败";
            }
        }else{
            if (data.Result == 1) {
                _cls = 'success';
                msg = "挂断成功";
            } else {
                _cls = 'warning';
                msg = "挂断失败";
            }
        }
    $.messageQueue({ message : msg, cls : _cls, iframePanel : true, type : "info" });
}


/**
 * 广播指令消息回调
 * @param data  后台传入的数据
 */
function  callbackBroadcastResult1(data){
    console.log("广播指令消息回调数据:"+data);
    data = JSON.parse(data);
    var _cls = '';
    //开始广播返回
    if(data.playtype=="开始广播"){
        if (data.relust == 1) {
            _cls = 'success';
            msg = "广播播放成功";
        } else {
            _cls = 'warning';
            msg = "广播播放失败";
        }
    }else{
        if (data.relust == 1) {
            _cls = 'success';
            msg = "停止广播成功";
        } else {
            _cls = 'warning';
            msg = "停止广播失败";
        }
    }
    $.messageQueue({ message : msg, cls : _cls, iframePanel : true, type : "info" });
}


