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
		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex';
	} else if (name == 'wlry1') {
		w = 1200;
		h = 800;
		title = "外来人员";
		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?type=1';
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

function openFxpgDialogSy(event, name, zhi, code) {
	var event = window.event || event;
	if (event && event.stopPropagation) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	// event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'fxdgl') {
		url = jsConst.basePath + '/wwjg/fxdgl/toIndex?sjfwName=' + code;
		w = 1000;
		h = 800;
	} else if (name == 'fxgkgl') {
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