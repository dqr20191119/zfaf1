function commonMessage(data){
	console.log('data:'+JSON.stringify(data));
	data = JSON.parse(data);
	if(typeof data == "string"){
		data = JSON.parse(data);
	}
	//alert(jsConst.POLICE_CODE);
	//alert(JSON.stringify(data));
	if(data.fun == "queryInspectNotice" && jsConst.POLICE_CODE == data.recheckLd){
		window.top.queryInspectNotice(1);
		window.top.queryInspectNotice(2);
	}
	if(data.fun == "query962326Approval"){
		window.top.query962326(1);
	}
	if(data.fun == "query962326Assign"){
		window.top.query962326(2);
	}
	if(data.fun == "query962326Instructions"){
		window.top.query962326(3);
	}
	if(data.fun == "query962326Forwarded"){
		window.top.query962326(4);
	}
	if(data.fun == "query962326Handle"){
		window.top.query962326(5);
	}
	if(data.fun == "query962326Handle2"){
		window.top.query962326(6);
	}
	if(data.fun == "query962326Recheck"){
		window.top.query962326(7);
	}
	if(data.fun == "query962326CallBack"){
		window.top.query962326(8);
	}
	if(data.fun == "queryAffairsHandleDate"){
		window.top.queryAffairsOversee(3);
	}
	if(data.fun == "queryCircularChanged"){
		queryCircularChanged(1);
	}
	if(data.fun == "queryCircularChangedApproval"){
		queryCircularChanged(2);
	}
	if(data.fun == "parsePrisonerCount"){
		var maininfo = window.top.winMap["maininfo"];
		if(maininfo != null){
			maininfo.parsePrisonerCount(data.obj);
		}
		var rightPrisoner = window.top.winMap["rightPrisoner"];
		if(rightPrisoner != null){
			rightPrisoner.parsePrisonerCount(data.obj,"message");
		}
	}
	if(data.fun == "earlyWarn"){
		window.top.queryEarlyWarn();
	}
	if(data.fun == "gradesRespinseApproval"){
		window.top.queryZeroReport(1);
	}
}

/**
 * 根据机构号查询监狱名
 */
function queryCusName(cusNumber){
	var cusName = null;
	var options = {
		 dataType : 1,
		 sync : false,
		 sqlId : "com_organization_base_name",
		 wid : "0",
		 data : [cusNumber],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data[0];
				 cusName = data.OBD_ORGA_NAME;
			 }
		 }
	};
	window.top.queryCtrl(options);
	return cusName;
}

/**
 * 根据机构号、报警记录编号查询接警信息
 */
function queryMeetAlarm(cusNumber,recordId){
	var flag = true;
	var options = {
		 dataType : 1,
		 sync : false,
		 sqlId : "cds_meet_alarm_info",
		 wid : "0",
		 data : [cusNumber,recordId],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(data){
					 var count = data[0].COUNT;
					 if(count > 0){
						 flag = false;
					 }
				 }
			 }
		 }
	};
	window.top.queryCtrl(options);
	return flag;
}
/**
 * 省局报警
 * @param data
 */
function callbackAlarmProvinceHandle(data){
	if (data != null) {
		var alarmLevel = window.top.curAlarmLevel;
		var parseData = JSON.parse(data);
		var msg = JSON.parse(parseData).record[0];
		var level = parseInt(msg.ARD_ALARM_LEVEL);
		var recordId = msg.ARD_RECORD_ID;
		window.top.cusNumber = msg.ARD_CUS_NUMBER;
		window.top.curAlarmData = parseData;
		if(level == 1){
			window.top.curAlarmLevel = level;
			var url = "page/cds/alarm/alarmHandle.jsp?type=0";
			window.top.openRight(url);
		}else if(level == 2){
			var ss = 0;
			var int = setInterval(function(){
				ss++;
				console.log(ss + "秒");
				if(ss == 30){
					var flag = queryMeetAlarm(msg.ARD_CUS_NUMBER,msg.ARD_RECORD_ID);
					console.log("是否接警 == " + (flag ? "未接警" : "已接警"));
					if(flag){
						window.top.curAlarmLevel = level;
						var url = "page/cds/alarm/alarmHandle.jsp?type=0";
						window.top.openRight(url);
						console.log("报警已发送至省局");
					}
					clearInterval(int);
				}
			},1000);
		}
		/*else{
			var hint = "";
			window.top.handleByLevel({
		 		prov : function(){
		 			var cusName = queryCusName(msg.ARD_CUS_NUMBER);
		 			cusName = cusName ? cusName : "";
		 			hint += cusName;
				}
			})
			hint += msg.ABD_NAME+"发生报警";
			var m = "<font color='blue'><b>"+ hint +"</b></font><br><b>报警类型</b>："+msg.ARD_TYPE_INDC+"<br><b>报警等级</b>："+msg.ARD_ALERT_LEVEL_INDC;
			$.messager.show({
				title:'报警提示',
				msg:m,
				timeout:1000*15,
				showType:'slide'
			});
		}*/
	}else{
		sessionlost();
	}
}
/**
 * 监狱报警
 * @param data
 */
function callbackAlarmHandle(data){
	if (data != null) {
		var prov = false;
		window.top.handleByLevel({
	 		prov : function(){
	 			prov = true;
			}
		});
		if(prov){
			callbackAlarmProvinceHandle(data);
			return;
		}
		var alarmLevel = window.top.curAlarmLevel;
		var parseData = JSON.parse(data);
		var msg = JSON.parse(parseData).record[0];
		var level = parseInt(msg.ARD_ALARM_LEVEL);
		var recordId = msg.ARD_RECORD_ID;
		window.top.cusNumber = msg.ARD_CUS_NUMBER;
		window.top.curAlarmData = parseData;
		if(level == 1 || level == 2){
			jsMap.mapMain.loadPrisonMap(window.top.cusNumber, function (result) {
				window.top.curAlarmLevel = level;
				var url = "page/cds/alarm/alarmHandle.jsp?type=0";
				window.top.openRight(url);
			});
		}else{
			var m = "<font color='blue'><b>"+msg.ABD_NAME+"发生报警</b></font><br><b>报警类型</b>："+msg.ARD_TYPE_INDC+"<br><b>报警等级</b>："+msg.ARD_ALERT_LEVEL_INDC;
			$.messager.show({
				title:'报警提示',
				msg:m,
				timeout:1000*15,
				showType:'slide'
			});
		}
		//showReceiveAlarm(level,recordId);
	}else{
		sessionlost();
	}
}

/**
 *  智能监控轮巡 行为侦测  返回对应的摄像头的数据
 * @param data
 */
function callbackXwzcCamera(data) {
    if(data !=null){
        var parseData = JSON.parse(data);
        var deviceIds =[];
        console.log("智能监控 行为侦测  返回对应的摄像头的数据:"+JSON.parse(data));
        var camsraId = JSON.parse(parseData).id;

        if(camsraId !=null && camsraId !=undefined){
            deviceIds.push(camsraId);
        }
       if(deviceIds.length>0){
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
                               console.log(" callbackXwzcCamera data = " + JSON.stringify(data));
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
    }

}


/**
 * 监狱报警
 * @param data
 */
function callbackAlarmHandleEXT(data){
	
	/*
	if (data != null) {
		var prov = false;
		window.top.handleByLevel({
	 		prov : function(){
	 			prov = true;
			}
		});
		//if(prov){
		//	callbackAlarmProvinceHandle(data);
		//	return;
		//}
		//var alarmLevel = window.top.curAlarmLevel;
		var parseData = JSON.parse(data);
		var msg = JSON.parse(parseData).record[0];
		var level = parseInt(msg.ARD_ALARM_LEVEL);
		var recordId = msg.ARD_RECORD_ID;
		window.top.cusNumber = msg.ARD_CUS_NUMBER;
		window.top.curAlarmData = parseData;
		if(level == 1 || level == 2){
			jsMap.mapMain.loadPrisonMap(window.top.cusNumber, function (result) {
				window.top.curAlarmLevel = level;
				//var url = "page/cds/alarm/alarmHandle.jsp?type=0";
				//window.top.openRight(url);
				var url = jsConst.basePath+"alarmprocess/index?type=0";
				toDisplay(url);
				
			});
		}else{
			var m = "<font color='blue'><b>"+msg.ABD_NAME+"发生报警</b></font><br><b>报警类型</b>："+msg.ARD_TYPE_INDC+"<br><b>报警等级</b>："+msg.ARD_ALERT_LEVEL_INDC;
			$.messager.show({
				title:'报警提示',
				msg:m,
				timeout:1000*15,
				showType:'slide'
			});
		}
		//showReceiveAlarm(level,recordId);
	}else{
		sessionlost();
	}
	*/
	
	callbackAlarmHandleEXT1(data);
}

function initAlarmHandle(msg,relay){
	setTimeout(function() {
		try{
			var rightPage = window.top.frames["rightFrame"].window;
			rightPage.init(0,msg);
			rightPage.initData(msg,0);
		}catch(e){
			initAlarmHandle(msg,relay*2);
		}
	},relay*1000);
}
function callbackForeignCarHandle(args){
	if (args != null) {
		/*var foregincar=JSON.parse(args);
		var dateList = foregincar.obj["data"];
		var foreigncarInfo=dateList[0];
		
		var carLcns=foreigncarInfo.FCD_CAR_LCNS_IDNTY;
		var direction=foreigncarInfo.FCD_DIRECTION;
		var enterTime=foreigncarInfo.FCD_ENTER_TIME;
		var leaveTime=foreigncarInfo.FCD_LEAVE_TIME;
		if(isNotNull(leaveTime)){
			leaveTime=leaveTime.toString().replace(/(^\s*)|(\s*$)/g, "");
		}
		
		var content;
		if(isNull(leaveTime)){
			content=carLcns+" "+" 到 "+direction;
		}else if(ces.date.timeCompare(enterTime, leaveTime)==-1){
			content=carLcns+" "+"离开 "+direction;
		}else{
			content=carLcns+" "+" 到 "+direction;
		}
/!*		if("1900-01-01 00:00:00"!=leaveTime){
			content=carLcns+" "+"离开 "+direction;
		}else{
			content=carLcns+" "+" 到 "+direction;
		}*!/
		foreigncarInfo = encodeURI(encodeURI(JSON.stringify(foreigncarInfo)));
		var url="page/cds/foreigncar/rightForeignCar.jsp?args="+foreigncarInfo;
		var args1 = {
				type : 0,
				hint : content,
				src  : url
		};
		showHint(jsConst.basePath,args1);*/

		var carInfo=JSON.parse(args);
		window.top.loadCarRow(carInfo);
	}
}

function callbackCallName(args){
	if (args != null) {
		var callName=JSON.parse(args);
		//var dateList = callName.obj["data"];
		var callNameInfo=callName.obj["data"];
		//var prisonerIdnty=callNameInfo.PBD_PRSNR_IDNTY;
		var rightCalling = winMap["newRightCalling"];
		//var rightPage = window.top.frames["rightFrame"].window;
		rightCalling.queryCallingUnCount();
		rightCalling.parsePrisonerInfo(callNameInfo);
	}
}

function parseInPoliceCount(args) {
	
	//mainInfo = winMap["maininfo"];
	//if (isNotNull(mainInfo)) {
	//	mainInfo.parseInPoliceCount1(args);
	//}
	
	parseInPoliceCount1(args);
}

function parseForeignPeopleCount(args){
	/*mainInfo = winMap["maininfo"];
	if (isNotNull(mainInfo)) {
		mainInfo.parseForeignPeopleCount1(args);
	}*/
}
function parseForeignCarCount(args){
	mainInfo = winMap["maininfo"];
	if (isNotNull(mainInfo)) {
		mainInfo.parseForeignCarCount1(args);
	}
}

//罪犯外出
function callbackPersionGoout(arg){
	try{
		window.top.dialogOutPrisoner(arg);
	}catch(e){
		console.log(e);
	}
}

//罪犯外出就医
function callbackOutDoctor(arg){
	mainInfo = winMap["maininfo"];
	if (isNotNull(mainInfo)) {
		mainInfo.getOutDoctor();
	}
}

function callback24Police(args){
	var policeObj=JSON.parse(args);
	window.top.dialogInsidePolice(policeObj);
}

function prisonerOnline(args){
	var online=JSON.parse(args);
	var rightCalling = winMap["rightCalling"];
	if (isNotNull(rightCalling)) {
		rightCalling.loadRealTimePrsnerCount(online);
	}
}

//设备状态
function callbackFacility(args){	
	var arg = JSON.parse(args);
	var deviceType = arg.deviceType;
	if(deviceType==3){//门禁
		mainInfo = winMap["remoteDoorCtrl"];
		if(isNotNull(mainInfo)){
			mainInfo.updateDoorStatus(arg);
		}
	}
}
/*
 * 门禁刷卡
 */
function callbackSecurityDoor(args){
	showPoliceDetails = winMap["showPoliceDetails"];
	if(isNotNull(showPoliceDetails)){
		showPoliceDetails.loadSlotCardInfo(args);
	}
}





/**
 * 手机监测
 * @param args
 */
function callbackPhoneCheck(data){
	console.log('手机监测信息:'+JSON.stringify(data));
	data = JSON.parse(data);
	var typeName = null;
	if(data.type == "WARN_LOGIN"){
		typeName = "非法手机";
	}else if(data.type == "WARN_CALL"){
		typeName = "非法通话";
	}if(data.type == "WARN_SMS"){
		typeName = "非法短信";
	}
	var content = data.posinfo + "有" + typeName;
	var args1 = {
		type : 0,
		hint : content,
		src  : null
	};
	showHint(jsConst.basePath,args1);
}

function callbackProvinceMonitorNotice(data) {
	
	callbackProvinceMonitorNotice1(data);
}

function callbackMsgFaultMaintain(data) {
	
	callbackMsgFaultMaintain1(data);
}

function callbackMsgDoorReturnStatus(data) {
	
	callbackMsgDoorReturnStatus1(data);
}

function callbackMsgTalkReturnStatus(data) {
	
	callbackMsgTalkReturnStatus1(data);
}
//融合通信呼叫或挂断回调函数
function callbackMsgTalkResultStatus(data) {
    callbackMsgTalkResultStatus1(data);
}
//广播指令返回
function  callbackBroadcastResult(data){
    callbackBroadcastResult1(data);
}


