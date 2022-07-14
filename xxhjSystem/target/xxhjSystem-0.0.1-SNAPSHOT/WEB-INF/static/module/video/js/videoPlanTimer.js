/******************************************************/
/****************要害时段——视频预案-定时打开**************/
/******************************************************/

function VideoPlanTimer(){
	this.timerMap = new Array();			// 要害时段定时器集合
}
var videoPlanTimer = new VideoPlanTimer();
/**
 * 初始化要害时段定时器
 */
VideoPlanTimer.prototype.initVideoPlanTimer = function () {
	//清空定时器
	if(this.timerMap.length>0){
		for(var index in this.timerMap){
			clearTimeout(this.timerMap[index].timerId);
		}
	}
	this.getYhsdVideoPlan(this.setVideoPlanTimer);
};

/**
 * 获取要害时段视频预案
 */
VideoPlanTimer.prototype.getYhsdVideoPlan = function (callback) {
	var cusNumber=jsConst.ORG_CODE;
	$.ajax({
		type : 'post',
		url : jsConst.basePath+"/groupManage/simpleGroupMemberTree.json",
		data : {
			/*"gmaUseRange":"0",*/
			"gmaTypeIndc":"1",
			"gmaSubTypeIndc":"2",
			"gmaCusNumber":cusNumber
			
		},
		dataType : 'json',
		success : function(data) {
			if(data.exception==undefined){
				if( typeof callback == 'function' ){
					callback.call(this, data);
				}
			}else{
				$.message( {
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
};

VideoPlanTimer.prototype.openVideoPlan= function(grpId,grdName){
	$.confirm( {
		message:"要害时段预案--"+grdName+"开始时间已到，是否打开？",
		iframePanel:true,
		callback: function(sure) {
			if (sure == true) {
				var params={
						"grpId":grpId
				}
				window.top.openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
			}
			if (sure == false) {
				console.log('cancel');
			}
		}
	});
}

/**
 * 设置要害时段定时器
 */
VideoPlanTimer.prototype.setVideoPlanTimer = function (groupMember) {
	for(var index in groupMember){
		if(groupMember[index].node_type=="group"){
			if(groupMember[index].startTime){
				var startTime=getNowFormatDate()+" "+groupMember[index].startTime;
				console.log(startTime);
				var sTime=new Date(startTime).getTime();
				var cTime=new Date().getTime();
				if(cTime<sTime){
					console.log("++++++++++++++++++");
					var interval=sTime-cTime;
					var grpId=groupMember[index].id;
					var grdName=groupMember[index].name;
					videoPlanTimer.timerMap.push({
						"grpId":grpId,
						"timerId":setTimeout(function(){
							videoPlanTimer.openVideoPlan(grpId,grdName);
						},interval)
					});
					console.log(MillisecondToTime(interval));
				}
				
			}
		}
	}
}


//把毫秒转换成时长
// 返回 1.若小于等于60秒,显示秒数
//     2.若大于1分钟小于1小时,显示分钟
//     3.若大于1小时,显示x小时x分钟
function MillisecondToTime(msd) {
    var time = parseInt(msd) / 1000;
    if (time <= 60) {
        time = time + '秒';
        return time;
    } else if (time > 60 && time < 60 * 60) {
        time = parseInt(time / 60) + "分钟";
        return time;
    } else {
        var hour = parseInt(time / 3600) + "小时";
        var minute = parseInt(parseInt(time % 3600) / 60) + "分钟";
        time = hour + minute;
        return time;
    }
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "/";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}