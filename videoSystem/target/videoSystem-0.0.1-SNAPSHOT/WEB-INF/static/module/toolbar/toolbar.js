/**
 *  add by zk
 */
var videoClient = window.top.videoClient;
var jsConst = window.top.jsConst;
//打开监督检查openAqfkDialog
var openJDJC='<a  href="javascript: void(0)" onclick="window.top.openAqfkDialog(null,\'jdjc\')" title="监督检查"><i class="iconfont icon-jietu"></i></a>';


/**
 * 证据保存提示
 */
function evidencePrompt(id, title){
	return;//不再使用 add by zk
	var dialogId = 'editEvidence_' + id;// 对话框ID
	var url = 'page/cds/videoSupervise/editEvidence.jsp?' + id + '&' + dialogId;
	window.top.openDialog(url, title, 650, 335, null, null, null, dialogId);
};

/**
 * 录像计时
 */
var timekeeping = {
	rid: null,// timeoutID
	fmt: function(val){ return (val < 10 ? "0": "") + val;},
	run: function(hh, mi, ss, callback){
		if( ss == 60 ){ ss = 0; mi++; }
		if( mi == 60 ){ mi = 0; hh++; }
		timekeeping.rid = setTimeout(function(){
			timekeeping.run(hh, mi, ++ss, callback);
		}, 1000);
		callback.call(this, hh, mi, ss);
	},
	start: function(callback){ timekeeping.run(0, 0, 0, callback || function(hh, mi, ss){}); },
	stop: function(){ clearTimeout( timekeeping.rid ); }
};


var _fadeOutId = null;

/**
 * 消息显示
 */
function promptMsg(msg, closeTime){
	clearTimeout(_fadeOutId);
	var msgObj = $("#message");
	if( msg.length > 0 ){
		msgObj.html(msg);
		msgObj.fadeIn(500);
		if( closeTime ){
			_fadeOutId = setTimeout(function(){
				msgObj.fadeOut(500);
			}, closeTime);
		}
	} else {
		msgObj.hide();
	}
	return msgObj;
}


//截图
function _snap(){
	if($("#icon_cut").hasClass("highlight")){
		return;
	}
	if (videoClient.checkPlayVideo()){
		addEvidence("1", function(data){
			$("#icon_cut").removeClass("highlight");
			if (data.success){
				if (window.top.snapPrompt){
					evidencePrompt(data.obj.sqno, '证据截图');
				}
				promptMsg('截图成功!'+"&nbsp;"+openJDJC, 4000);
				dakaijiandudan();
				
				
			} else {
				promptMsg('');
				_alert(data.msg);
			}
		});
		$("#icon_cut").addClass("highlight");
		promptMsg('正在截图...');
	}
}


function dakaijiandudan(){
	var url = jsConst.basePath+'/monitor/jdjc?type=1';
    $('#dialog').html("");
//    $('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : 900,
		height : 600,
		title : "监督检查",
		url : url
	});
	$("#dialog").dialog("open");
}

var _record_data = null;
function _record(){
	if (videoClient.checkPlayVideo() && _record_data == null ){
		addEvidence("2", function(data){
			if (data.success){
				_record_data = data.obj;
				$(".video-record").hide();
				$(".video-stop").show().addClass('recording');
				// 计时
				timekeeping.start(function(hh, mi, ss){
					var str = timekeeping.fmt(hh) + ":" + timekeeping.fmt(mi) + ":" + timekeeping.fmt(ss);
					promptMsg("录制中[" + str + "]...");
					videoClient.getIndex(function (data) {
						if( _record_data && !data.success  ){
							_record_data = null;
							timekeeping.stop();
							promptMsg("录像中断!", 3000).addClass('msg-error');
							$(".video-record").show();
							$(".video-stop").hide().removeClass('recording');
							_alert(data.msg);
						}
					});
				});
			} else {
				promptMsg('');
				_alert(data.msg);
			}
		});
		promptMsg('启动录像...');
	}
}
//停止录像
function _stop(){
	if( _record_data != null ){
		//证据id
		var sqno = _record_data.sqno;
		var params = {
			"index": _record_data.index,
			"uploadFile": _record_data.savePath + '\\' + _record_data.fileName,
			'orgCode': jsConst.ORG_CODE
		};
		videoClient.stopRecord(params, function(data){
			console.log("stopRecord-->" + JSON.stringify(data));
			timekeeping.stop();
			$(".video-record").show();
			$(".video-stop").hide().removeClass('recording');
			if( data.success ){
				promptMsg("录像已保存!"+"&nbsp;"+openJDJC, 4000);
				if (window.top.recordPrompt){
					evidencePrompt(sqno, '证据录像');
				}
			} else {
				promptMsg('');
				_alert(data.msg);
			}
		});
		_record_data = null;
		return sqno; //add by tao
	} else {
		// 情况一：超时的时候_record_data为空则时候需要关闭录制
		timekeeping.stop();
		videoClient.stopRecord(_recordIndex);
	}
}

var _handleHis = {};
var _recordIndex = 0;
//添加证据
function addEvidence(fileType, callback){
	videoClient.getIndex(function (data) {
		if (!data.success) {
			promptMsg('');
			_alert(data.msg);
			return false;
		}
		var index = _recordIndex = data.obj;
		var cameraId = videoClient.curPlayCameraList["" + index];
		
		//add by zk (-1代表视频窗口关闭)
		if (index == -1){
			promptMsg('');
			_alert("请检查并确认是否已打开视频!");
			return false;
		}
		
		var playbackInfo = videoClient.getPlaybackCameraInfo(index);

		if( cameraId ){
			if( typeof cameraId == 'object' ){
				cameraId = cameraId.cameraId;
			}
			cameraId += '';
		} else {
			promptMsg('');
			_alert('请选择已打开的视频，再进行此操作!');
			return false;
		}

		/*if (fileType == 2 && playbackInfo != null) {
			promptMsg('');
			if (playbackInfo.devType == 8) {
		 		_alert('暂不支持海康回放视频录像!');
		 		return false;
			}
		}*/

		// 通过上一次操作的时间间隔(默认30秒内)来设置number
		var lastHandleHis = _handleHis[cameraId];
		var number = 1;
		var thisTime = (new Date()).getTime();
		if( lastHandleHis ){
			var lastTime = lastHandleHis.handleTime;
			if( thisTime - lastTime <= 30*1000 ){
				number += lastHandleHis.number;
			}
		}

		var url = "evidence/add";
		var args = {
			args: JSON.stringify({
				'orgCode': jsConst.ORG_CODE, 
				'contentType': '1',	//证据保存的事件类型：1.监督检查(默认1)、2.报警处置 
				'cameraId': cameraId,
				'fileType': fileType,
				'userId': jsConst.USER_ID,
				'number': '_' + (number < 10 ? ('0' + number) : number)
			})
		};

		window.top.ajaxTodo(url, args, function(data){
			// 记录上一次操作的参数
			if( data.success ){
				_handleHis[cameraId] = {
					'handleTime': thisTime,
					'number': number
				};
			}
			if( typeof callback == 'function' ){
				callback.call(this, data);
			}
		});
	});
	return true;
};



/*
 * 页面销毁前执行的事件add by zk
 */
function unloadEvent () {
	//_alert("onunload");
	//videoClient.closeAllMult();

	//wq 2018-06-07 RenderControl进程销毁
	if(jsConst.MAP_TYPE != 0){
        map_3d.__g.terminate();
    }
	//待测 wq
/*    if(event.clientX>document.body.clientWidth && event.clientY<0||event.altKey){
    	if(jsConst.MAP_TYPE != 0){
        	map_3d.__g.terminate();
		}
	}*/
}



/**
 * 视频回放
 */
function playbackVideo1(){
	var cameraId = window.top.videoClient.curPlayCameraList["0"];
	var cameraName = null;
	var options = {
		 dataType : 1,
		 async : false,
		 sqlId : "query_camera_name",
		 wid : "0",
		 data : [jsConst.CUS_NUMBER,cameraId],
		 success : function(json){
			 if(json.success){
				 var data = json.obj.data;
				 if(window.top.isNotNull(data) && data != ""){
					 cameraName = data[0].CBD_NAME;
				 }
			 }
		}
	}
	window.top.queryCtrl(options);
	window.top.openRight('page/cds/video/playbackVideo.jsp?cameraName=' + encodeURI(encodeURI(cameraName)));
}

//add by zk
function _alert(msg){
	$.messageBox( {
        message:msg,
        position: {
            my: "right top",   //与上述类似，此为右上角
            at: "right top",
            of: window
        }
    });  
}


/**
 * 呼叫对讲
 *//*
function _talk(){
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/videoClient/getWinCameraID',
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				if(data.obj){
					var cameraId=data.obj;
					$.ajax({
						type : 'post',
						url : jsConst.basePath+'/realTimeTalk/talkByCameraId',
						data : {
							"cusNumber":jsConst.ORG_CODE,
							"cameraId":cameraId
						},
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$.message({
									message : "操作成功",
									cls : "success",
									iframePanel:true
								});
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									type : "danger"
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
				}else{
					$.alert({
						message:"请开启视频客户端，并选择一个摄像",
						title:"信息提示",
						iframePanel:true
					});
				}
			} else {
				$.message({
					iframePanel:true,
					message : data.msg,
					type : "danger"
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
}

*//**
 * 视频回放
 *//*
function _playback(stime,etime){
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/videoClient/getWinCameraID',
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				if(data.obj){
					var cameraId=data.obj;
					$.ajax({
						type : 'post',
						url : jsConst.basePath+'/videoClient/playbackMult',
						data : {
							"args":JSON.stringify({
								"cameraID":cameraId,
								"startTime":stime,
								"endTime":etime
							 })
						},
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$.message({
									message : "操作成功",
									cls : "success",
									iframePanel:true
								});
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									type : "danger"
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
				}else{
					$.alert({
						message:"请开启视频客户端，并选择一个摄像",
						title:"信息提示",
						iframePanel:true
					});
				}
			} else {
				$.message({
					iframePanel:true,
					message : data.msg,
					type : "danger"
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
}
*/

/**
 * getWinCameraID 被北京弄坏了，只能在此进行改造update 20190615
 * 
 * **/

/**
 * 呼叫对讲
 */
function _talk(){
	
	videoClient.getIndex(function (data) {
		if (!data.success) {
			promptMsg('');
			_alert(data.msg);
			return false;
		}
		var index = _recordIndex = data.obj;
		var cameraId = videoClient.curPlayCameraList["" + index];
		
		if (index == -1){
			promptMsg('');
			_alert("请检查并确认是否已打开视频!");
			return false;
		}

		if( cameraId ){
			if( typeof cameraId == 'object' ){
				cameraId = cameraId.cameraId;
			}
			cameraId += '';
		} else {
			promptMsg('');
			_alert('请选择已打开的视频，再进行此操作!');
			return false;
		}
		
		
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/realTimeTalk/talkByCameraId',
			data : {
				"cusNumber":jsConst.ORG_CODE,
				"cameraId":cameraId
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.message({
						message : "操作成功",
						cls : "success",
						iframePanel:true
					});
				} else {
					$.message({
						iframePanel:true,
						message : data.msg,
						type : "danger"
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
	});	
}

/**
 * 视频回放
 */
function _playback(stime,etime){
	videoClient.getIndex(function (data) {
		if (!data.success) {
			promptMsg('');
			_alert(data.msg);
			return false;
		}
		var index = _recordIndex = data.obj;
		var cameraId = videoClient.curPlayCameraList["" + index];
		
		if (index == -1){
			promptMsg('');
			_alert("请检查并确认是否已打开视频!");
			return false;
		}

		if( cameraId ){
			if( typeof cameraId == 'object' ){
				cameraId = cameraId.cameraId;
			}
			cameraId += '';
		} else {
			promptMsg('');
			_alert('请选择已打开的视频，再进行此操作!');
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/videoClient/playbackMult',
			data : {
				"args":JSON.stringify({
					"cameraID":cameraId,
					"startTime":stime,
					"endTime":etime
				 })
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.message({
						message : "操作成功",
						cls : "success",
						iframePanel:true
					});
				} else {
					$.message({
						iframePanel:true,
						message : data.msg,
						type : "danger"
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
		
	});
	
}