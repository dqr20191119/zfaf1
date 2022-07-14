$(function(){
	/*var left = parseInt($(window).width()/2)-150;
	var top = parseInt($(window).height()/2)-50;
	$(".messageWin").css("margin-top",top);
	$(".messageWin").css("margin-left",left);*/
});
/**
 * 监内民警超过24小时未出监狱大门、进行弹框提醒
 * policeList [{ID:3236002,NAME:'test1'},{ID:3236002,NAME:'test2'}]
 */
function dialogInsidePolice(policeList){
	var policeStr = "";
	for(var i=0;i<policeList.length;i++){
		var policeId = policeList[i].ID == null ? "" : policeList[i].ID;
		var policeName = policeList[i].NAME == null ? "" : policeList[i].NAME;
		policeStr += "<a href='javascript:void(0);' onclick='toPoliceList(" + policeId + ");' style='font-size:16px;color:red;'> " + policeName +  " </a>&nbsp;&nbsp;";
	}
	 var msg = "<span style='font-size:14px;color: #000;'>民警&nbsp;&nbsp;" + policeStr + "在监内超过24小时未出监狱大门!</span>";
	 $.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	 });
};
/**
 * 弹出民警信息界面
 */
function toPoliceList(policeId){
	var url = "page/cds/police/police.jsp?policeId=" + policeId;
	window.top.openDialog(url, "民警信息", 1020, 625, null, null, {'top':85});
}
/**
 * 罪犯外出、进行弹框提醒
 */
function dialogOutPrisoner(type){
	 if(type == "1"){
		 var options = {
			 dataType : 1,
			 sync : true,
			 sqlId : "cds_prison_goout_info_count",
			 wid : "0",
			 data : [jsConst.CUS_NUMBER,1],
			 success : function(json){
				 if(json.success){
					 var data = json.obj.data;
					 if(window.top.isNotNull(data) && data != ""){
						 var count = data[0].COUNT;
						 if(count > 0){
							 var msg = "<span style='font-size:14px;color: #000;'>当前有 <a href='javascript:void(0);' onclick='toOutRecheck();' style='font-size:15px;color:red;'>" + count + "</a> 条罪犯出监纪录需要审核。</span>";
							 $.messager.show({
								  title: "提示",
								  width: 293,
								  height: 165,
								  msg: msg,
								  timeout: 10000,
								  showType: 'slide'
							 });
						 }
					 }
				}
			}
		};
		window.top.queryCtrl(options);
	 }else{
		 var msg = "";
		 if(type == "ok"){
			 msg = "<span style='font-size:14px;color: #000;'>当前有 <a href='javascript:void(0);' onclick='toOutHandle();' style='font-size:15px;color:red;'>1</a> 条罪犯就诊转住院纪录审批通过。</span>";
		 }else if(type == "no"){
			 msg = "<span style='font-size:14px;color: #000;'>当前有 <a href='javascript:void(0);' onclick='toOutHandle();' style='font-size:15px;color:red;'>1</a> 条罪犯就诊转住院纪录审批不通过。</span>";
		 }
		 $.messager.show({
			 title: "提示",
			 width: 293,
			 height: 165,
			 msg: msg,
			 timeout: 10000,
			 showType: 'slide'
		});
	 }
};
/**
 * 弹出罪犯外出审批界面
 */
function toOutRecheck(){
	var url = "page/cds/prisonerOut/outRecheck.jsp";
	window.top.openDialog(url, "外出审核", 1068, 691, null, null, {'top':51});
}
/**
 * 弹出罪犯外出处置界面
 */
function toOutHandle(){
	var url = "page/cds/prisonerOut/outHandle.jsp";
	window.top.openDialog(url, "外出处置", 1068, 691, null, null, {'top':51});
}

/**
 * 判断是否为整点
 */
var flag = false;
function checkIsIntegralPoint(){
	var date = new Date();
	var minutes = date.getMinutes();
	if(minutes == 0 && !flag){
		console.log(minutes);
		console.log(flag);
		flag = true;
		queryOutPrison();
	}
	if(minutes != 0 && flag){
		flag = false;
	}
	setTimeout(checkIsIntegralPoint,1000);
}
/**
 * 查询罪犯外出总数
 */
function queryOutPrison(){
	var options = {
		 dataType : 1,
		 sync : true,
		 sqlId : "cds_prison_goout_info_count",
		 wid : "3",
		 data : [jsConst.CUS_NUMBER,2,1,2],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogOutControl(count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 外出监控弹框提醒
 */
function dialogOutControl(count){
	var msg = "<span style='font-size:14px;color: #000;'>当前有 <a href='javascript:void(0);' onclick='toOutControl();' style='font-size:15px;color:red;'>" + count + "</a> 条罪犯外出，请填写外出监控情况。</span>";
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出罪犯外出监控界面
 */
function toOutControl(){
	var url = "page/cds/prisonerOut/outControl.jsp";
	window.top.openDialog(url, "外出监控", 1068, 691, null, null, {'top':51});
}
/**
 * 查询未审核的远程、现场督察通报信息
 */
function queryInspectNotice(type){
	var sqlId = type == 1 ? "cds_duty_inspect_notice_count" : "cds_duty_inspect_notice_local_count";
	var options = {
		 dataType : 1,
		 sync : true,
		 sqlId : sqlId,
		 wid : "0",
		 data : [jsConst.CUS_NUMBER,0,jsConst.POLICE_CODE],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogInspectNotice(type,count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 远程、现场督察通报弹框提醒
 */
function dialogInspectNotice(type,count){
	var name = type == 1 ? "远程" : "现场";
	var msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toInspectNotice(" + type + ");' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条" + name + "督察通报未审核。</span>";
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出远程、现场督察通报审批界面
 */
function toInspectNotice(type){
	var url = null;
	if(type == 1){
		url = "page/cds/duty/dutyLogNetRecheck.jsp";
		title = "远程督查通报审核";
	}else{
		url = "page/cds/duty/dutyLogCirculateRecheck.jsp";
		title = "现场督查通报审核";
	}
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询未审批、未处理的962326受理信息
 */
function query962326(type){
	var sqlId = null;
	var status = null;
	var cusNmber = jsConst.ORG_CODE;
	if(type == 1){
		sqlId = "cds_962326_approval_count";
		status = 6;
	}else if(type == 2){
		sqlId = "cds_962326_assign_count";
		status = 5;
		cusNmber = 3200;
	}else if(type == 3){
		sqlId = "cds_962326_instructions_count";
		status = 6;
		cusNmber = 3200;
	}else if(type == 4){
		sqlId = "cds_962326_forwarded_count";
		status = 7;
		cusNmber = 3200;
	}else if(type == 5){
		sqlId = "cds_962326_handle_count";
		status = 8;
		cusNmber = 3200;
	}else if(type == 6){
		sqlId = "cds_962326_handle_count";
		status = 9;
		cusNmber = 3200;
	}else if(type == 7){
		sqlId = "cds_962326_recheck_count";
		status = 10;
		cusNmber = 3200;
	}else if(type == 8){
		sqlId = "cds_962326_callBack_count";
		status = 11;
		cusNmber = 3200;
	}
	var options = {
		 dataType : 1,
		 sqlId : sqlId,
		 wid : "0",
		 data : [cusNmber,jsConst.POLICE_CODE,status],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialog962326(type,count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 962326弹框提醒
 */
function dialog962326(type,count){
	var msg = null;
	if(type == 1){
		msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(1);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未审批。</span>";
	}else if(type == 2){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(2);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未分派。</span>";
	}else if(type == 3){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(3);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未批示。</span>";
	}else if(type == 4){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(4);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未转发。</span>";
	}else if(type == 5){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(5);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未处理。</span>";
	}else if(type == 6){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(6);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未处理完成。</span>";
	}else if(type == 7){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(7);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未审核。</span>";
	}else if(type == 8){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='to962326(8);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条962326受理信息未回电。</span>";
	}
	
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出962326审批、分派、处理、回电界面
 */
function to962326(type){
	var url = null;
	if(type == 1){
		url = "page/cds/962326Manage/962326Approval.jsp";
		title = "审批";
	}else if(type == 2){
		url = "page/cds/962326Manage/962326Assign.jsp";
		title = "分派";
	}else if(type == 3){
		url = "page/cds/962326Manage/962326Instructions.jsp";
		title = "批示";
	}else if(type == 4){
		url = "page/cds/962326Manage/962326Forwarded.jsp";
		title = "转发";
	}else if(type == 5 || type == 6){
		url = "page/cds/962326Manage/962326Handle.jsp";
		title = "处理";
	}else if(type == 7){
		url = "page/cds/962326Manage/962326Recheck.jsp";
		title = "审核";
	}else if(type == 8){
		url = "page/cds/962326Manage/962326CallBack.jsp";
		title = "回电";
	}
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询未处理、未反馈的事务督办信息
 */
function queryAffairsOversee(type){
	var sqlId = null;
	var list = new Array();
	if(type == 1){
		list.push(jsConst.ORG_CODE);
		list.push(1);
		list.push(2);
		list.push(window.top.formatterDate(new Date(),'ymdhms'));
		list.push(jsConst.ORG_CODE);
		list.push(jsConst.POLICE_CODE);
		sqlId = "cds_department_police_faultId_count";
	}else if(type == 2){
		list.push(jsConst.ORG_CODE);
		list.push(3);
		list.push(jsConst.POLICE_CODE);
		sqlId = "cds_device_maintain_info_feedBack_count";
	}else if(type == 3){
		list.push(jsConst.ORG_CODE);
		list.push(1);
		list.push(2);
		list.push(window.top.formatterDate(new Date(),'ymdhms'));
		list.push(jsConst.ORG_CODE);
		list.push(jsConst.POLICE_CODE);
		sqlId = "cds_department_police_faultId_date_count";
	}
	var options = {
		 dataType : 1,
		 sqlId : sqlId,
		 wid : "0",
		 data : list,
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 var count = data && data[0].COUNT;
				 if (count) {
					 dialogAffairsOversee(type,count);
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 事务督办弹框提醒
 */
function dialogAffairsOversee(type,count){
	var msg = null;
	if(type == 1){
		msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toAffairsOversee(1);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条事务督办信息待签收或待处理。</span>";
	}else if(type == 2){
	    msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toAffairsOversee(2);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条事务督办信息未反馈。</span>";
	}else if(type == 3){
		msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toAffairsOversee(1);' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条事务督办信息已超过维修时限，请尽快签收处理。</span>";
	}
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出事务督办处理、反馈界面
 */
function toAffairsOversee(type){
	var url = null;
	if(type == 1){
		url = "page/cds/affairsOversee/affairsHandle.jsp";
		title = "事务处理";
	}else if(type == 2){
		url = "page/cds/affairsOversee/affairsFeedBack.jsp";
		title = "事务反馈";
	}
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询已收未查看的监督单信息
 */
function querySupervise(){
	var options = {
		 dataType : 1,
		 sqlId : "cds_supervise_notQuery_count",
		 wid : "0",
		 data : [jsConst.ORG_CODE,1,0,jsConst.POLICE_CODE],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogSupervise(count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 监督单弹框提醒
 */
function dialogSupervise(count){
	var	msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toSupervise();' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条监督单信息未查看。</span>";
	
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出监督单界面
 */
function toSupervise(){
	var url = "page/cds/videoSupervise/pushEvidence.jsp?tab=3";
	var	title = "监督检查";
	window.top.openDialog(url, title, 960, 560, null, null, null);
}
/**
 * 查询待整改、待审批的通报整改信息
 */
function queryCircularChanged(type){
	var wid = null;
	var list = new Array();
	if(type == 1){
		wid = 0;
		list.push(jsConst.ORG_CODE);
		list.push(jsConst.DEPARTMENT_ID);
	}else if(type == 2){
		wid = 1;
		list.push(jsConst.ORG_CODE);
		list.push(jsConst.POLICE_CODE);
		list.push(jsConst.POLICE_CODE);
	}
	var options = {
		 dataType : 1,
		 sqlId : "cds_circular_changed_dprtmnt_count",
		 wid : wid,
		 data : list,
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogCircularChanged(type,count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 通报整改弹框提醒
 */
function dialogCircularChanged(type,count){
	var	msg = null;
	if(type == 1){
		msg = "<span style='font-size:14px;color: #000;'>" 
			+ "当前有 <a href='javascript:void(0);' onclick='toCircularChanged(" + type + ");' " 
			+ "style='font-size:15px;color:red;'>" + count + "</a> 条通报整改信息未整改。</span>";
	}else if(type == 2){
		msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toCircularChanged(" + type + ");' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条通报整改信息未审批。</span>";
	}
	
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出通报整改界面
 */
function toCircularChanged(type){
	var	title = null;
	var url = null;
	if(type == 1){
		title = "整改";
		url = "page/cds/circularChanged/changed.jsp";
	}else if(type == 2){
		title = "审批";
		url = "page/cds/circularChanged/approval.jsp";
	}
	
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询未审批的预警信息
 */
function queryEarlyWarn(){
	var options = {
		 dataType : 1,
		 sqlId : "cds_earlyWarn_approval",
		 wid : "0",
		 data : [jsConst.ORG_CODE,jsConst.POLICE_CODE],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogEarlyWarn(count);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 预警信息弹框提醒
 */
function dialogEarlyWarn(count){
	var	msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toEarlyWarn();' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条预警信息未审批。</span>";
	
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出预警审批界面
 */
function toEarlyWarn(){
	var url = "page/cds/earlyWarn/vefifyWarn.jsp";
	var	title = "预警审批";
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询未编辑、未签发的的零报告信息
 */
function queryZeroReport(status){
	var list = new Array();
	list.push(jsConst.ORG_CODE);
	list.push(status);
	var whereId = 0;
	if(status == 1){
		whereId = 1;
		list.push(jsConst.POLICE_CODE);
	}
	var options = {
		 dataType : 1,
		 sqlId : "cds_zero_report_count",
		 wid : whereId,
		 data : list,
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 var count = data[0].COUNT;
					 if(count > 0){
						 dialogZeroReport(count,status);
					 }
				 }
			}
		}
	};
	window.top.queryCtrl(options);
}
/**
 * 零报告信息弹框提醒
 */
function dialogZeroReport(count,status){
	var text = status == 0 ? "编辑" : "签发";
	var	msg = "<span style='font-size:14px;color: #000;'>" 
		    + "当前有 <a href='javascript:void(0);' onclick='toZeroReport(" + status + ");' " 
		    + "style='font-size:15px;color:red;'>" + count + "</a> 条零报告信息未" + text + "。</span>";
	
	$.messager.show({
		 title: "提示",
		 width: 293,
		 height: 165,
		 msg: msg,
		 timeout: 10000,
		 showType: 'slide'
	});
}
/**
 * 弹出零报告编辑、签发界面
 */
function toZeroReport(status){
	var url = "page/cds/gradesRespinse/sponsored.jsp";
	var	title = "编辑";
	if(status == 1){
		url = "page/cds/gradesRespinse/approval.jsp";
		title = "签发";
	}
	window.top.openDialog(url, title, 1068, 691, null, null, {'top':51});
}
/**
 * 查询用户定时任务信息
 */
var timerList = new Array();
function queryUserTimerTask(){
	for(var i=0;i<timerList.length;i++){
		clearTimeout(timerList[i]);
		console.log("清除用户定时任务--任务编号" + timerList[i]);
	}
	var list = new Array();
	list.push(jsConst.CUS_NUMBER);
	list.push(jsConst.LOGIN_USER_KEY);
	var options = {
		 dataType : 1,
		 sqlId : "cds_user_timer_task_info",
		 wid : "0",
		 oid : "0",
		 data : list,
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 for(var i=0;i<data.length;i++){
					 var nowDate = new Date();
					 var execTime = data[i].UTT_EXEC_TIME;
					 var date = formatterDate(new Date(),"ymd");
					 date = formatterStr(date + " " + dateTruncate(execTime,"hms"));
					 var time = date.getTime() - nowDate.getTime();
					 var taskType = data[i].UTT_TASK_TYPE;
					 var taskId = data[i].UTT_TASK_ID;
					 var taskName = data[i].UTT_TASK_NAME;
					 var remindTime = data[i].UTT_REMIND_TIME;
					 if(time > 0){
						 if(remindTime == 4){
							 timerTask(taskType,taskId,time);
						 }else{
							 var text = null;
							 if(taskType == 1){
								 text = '后自动打开' + taskName + '视频预案';
							 }else if(taskType == 2){
								 text = '后自动打开' + taskName + '的门禁';
							 }else if(taskType == 3){
								 text = '后自动关闭' + taskName + '的门禁';
							 }
							 var remind_ch = null;
							 var remind_mm = null;
							 if(remindTime == 1){
								 remind_ch = "10秒";
								 remind_mm = 10000;
							 }else if(remindTime == 2){
								 remind_ch = "30秒";
								 remind_mm = 30000;
							 }else if(remindTime == 3){
								 remind_ch = "1分钟";
								 remind_mm = 60000;
							 }
							 var params = {
								 dialogText : remind_ch + "" + text,
								 taskType : taskType,
								 taskId : taskId,
								 remind_mm : remind_mm,
								 timer : time-remind_mm
							 }
							 
							 console.log(JSON.stringify(params));
							 
							 var timer = setTimeout(dialogRemind(params),params.timer);
							 timerList.push(timer);
						 }
					 }
				 }
	         }
		 }
	}
	window.top.queryCtrl(options);
}
/**
 * 弹框提醒
 * @param params
 * @returns {Function}
 */
function dialogRemind(params){
	 return function(){
		 if (confirm(params.dialogText)) {
			 timerTask(params.taskType,params.taskId,params.remind_mm);
		 }
	 }
	 /*$(".messageWin").show();
	 $(".messageWin .content .text").html(remind_ch + "" + text);
	 $(".messageWin .content #timerTask").unbind("click");
	 $(".messageWin .content #timerTask").click(function(){
		 timerTask(taskType,taskId,remind_mm);
		 hideMessageWin();
	 });
	 $.messager.confirm('提示',remind_ch + "" + text,function(r){  
		 if(r){
			timerTask(taskType,taskId,remind_mm);
		 }
	 });*/
}
/**
 * 隐藏消息弹窗、移除点击事件
 */
function hideMessageWin(){
	$(".messageWin").hide();
	$(".messageWin .content #timerTask").unbind("click");
}
/**
 * 定时启动任务
 */
function timerTask(taskType,taskId,time){
	 setTimeout(function(){
		 if(taskType == 1){
			 openVideoPlan(taskId);
		 }else if(taskType == 2){
			 openDoor(taskId);
		 }else if(taskType == 3){
			 closeDoor(taskId);
		 }
	 },time);
}
/**
 * 打开视频预案
 */
function openVideoPlan(taskId){
	var list = new Array();
	list.push(jsConst.CUS_NUMBER);
	list.push(1);
	list.push(taskId);
	var options = {
		 dataType : 1,
		 sqlId : "cds_grp_rltn_dtls_all_me",
		 wid : "0",
		 data : list,
		 success : function(json){
			 var data = json.obj.data;
			 var cameraList = new Array();
			 for(var i=0;i<data.length;i++){
				 var cameraName = data[i].CAMERANAME;
				 var cameraId = data[i].CAMERAID;
				 cameraList.push({
					 "index" : i,
					 "cameraId" : cameraId
				 });
			 }
			 videoClient.playVideoHandle({
				 'subType': 2,
				 'layout': data.length,
				 'data': cameraList
			 });
		 }
	};
	window.top.queryCtrl(options);
}
/**
 * 开门
 */
function openDoor(taskId){
	var args = {
		"cusNumber" : jsConst.CUS_NUMBER,
		"parasList" : [{
			"idnty" : taskId ,
			"action" : 1
		}]
	};
	DoorCtrl.ctrlManyDoor(jsConst.basePath,args);
	window.top.jsMap["map3d"].openDoor();
}
/**
 * 关门
 */
function closeDoor(taskId){
	var args = {
		"cusNumber" : jsConst.CUS_NUMBER,
		"parasList" : [{
			"idnty" : taskId ,
			"action" : 0
		}]
	}
	DoorCtrl.ctrlManyDoor(jsConst.basePath,args);
}
/*
 * 在监车辆提醒
 */
function refresh(){
	var list = new Array();
	list.push(jsConst.CUS_NUMBER);
	list.push(jsConst.CUS_NUMBER);
	var options = {
		 dataType : 1,
		 sqlId : "cds_ForeignCarCount_InPrison",		
		 data : list,
		 success : function(json){
			 var data = json.obj.data;
			 var qlist = new Array();
			 qlist.push(jsConst.CUS_NUMBER);
			 qlist.push(jsConst.CUS_NUMBER);
			 var option = {
					 dataType : 1,
					 async : false,
					 sqlId : "cds_foreign_car_in_prison_query",
					 wid :"0",
					 data : qlist,
					 success : function(json){
						 var dat = json.obj.data;
						 if(dat.length > 0){
							 if(data[0].COUNT >dat[0].FCIP_CAR_MAX ){
								 var	msg = "<span style='font-size:14px;color: #000;'>" 
									    + "当前在监车辆有 <a href='javascript:void(0);' onclick='toForeignCar();' " 
									    + "style='font-size:15px;color:red;'>" + data[0].COUNT + "</a> 辆。</span>";							
								$.messager.show({
									 title: "提示",
									 width: 293,
									 height: 165,
									 msg: msg,
									 timeout: 10000,
									 showType: 'slide'
								});							
								toForeignCar();
							 }	 
						 }						 																
					 }
				};
				window.top.queryCtrl(option);			
		 }
	};
	window.top.queryCtrl(options);	
}
/*
 * 跳转外来车辆页面
 */
function toForeignCar(){
	 var url = "page/cds/foreigncar/rightForeignCar.jsp?args="+0+"&flage="+1;
	 window.top.openRight(url);
} 