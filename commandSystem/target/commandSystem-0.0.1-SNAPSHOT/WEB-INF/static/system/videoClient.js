/**
 * 视频客户端调用封装
 */
function VideoClient () {
	
}
var sysFileObj = null;
var videoClient = new VideoClient();

try {
	//sysFileObj = new ActiveXObject('Scripting.FileSystemObject');
} catch (e) {
	console.error('加载ActiveX对象失败：', e);
	//alert("加载ActiveX对象失败!");
}
VideoClient.prototype.topMost = true;			// 置顶显示
VideoClient.prototype.showTitle = true;		// 显示标题栏
VideoClient.prototype.showTools = false;		// 显示工具栏

VideoClient.prototype.maxLayout = 16;
VideoClient.prototype.curLayout = 0;// 当前画面布局(默认0没有)
VideoClient.prototype.curLayoutMap = new Object();// 当前画面布局(默认0没有)
VideoClient.prototype.curIndex = 0;// 当前选中的窗口索引
VideoClient.prototype.curPlayCameraList = {};// 当前播放的视频集合{"窗口位置索引":"视频ID"}
VideoClient.prototype.callResult = null;// 结果
VideoClient.prototype.playbackList = new Array();// 视频回放列表[{"index": "","cameraID": "","cameraName": "","startTime": "","endTime": ""},...]
VideoClient.prototype.playbackBefore = null;// 视频回放前

VideoClient.prototype.current = {};	// 当前播放的布局及摄像机
VideoClient.prototype.history = [{'layout':0, 'data': null}];	// 播放的历史记录最多记录10条

/**
 * 检测是否已打开视频
 * @returns true未打开、false已打开
 */
VideoClient.prototype.checkPlayVideo = function(){
	if (videoClient.curLayout == 0){
		_alert("请检查并确认是否已打开视频!");
		return false;
	}
	return true;
};

/**
 * 执行回调
 * @param data
 */
VideoClient.prototype.handleCallBack = function(callback, data){
	//console.log("handleCallBack callback =  " + Json.stringify(callback));
	//console.log("handleCallBack data =  " + Json.stringify(data));
	if (isNotNull(callback)){
		callback.call(this, data);
	}
};

/**
 * 客户端画面布局
 * @param layout 画面布局（1-16对应一到十六画面）
 */
VideoClient.prototype.setLayout = function(layout, callback){
	if (layout < 1) layout = 1;
	if (layout > 16) layout = 16;

	/*
	 * 这里使用两次是因为现在版本的客户端 第一次打开时是不会定位坐标的
	 */
	postTodo("setLayout", {"layout": layout, 'last': videoClient.curLayout}, function(data){
		if (data.success) {
			videoClient.curLayout = layout;
			videoClient.setFormStyle();
		} else {
			_alert(data.msg);
		}
		videoClient.handleCallBack(callback, data);
	}, false);
};

/**
 * 设置窗口属性
 */
VideoClient.prototype.setFormStyle = function(params, callback){
	if( typeof params != 'object' ){
		if( typeof params == 'function' ){
			callback = params;
		}
		this.getConfig();
		params = {
			"topMost": (this.topMost == 1),
			"showFormTitle": (this.showTitle == 1),
			"showVideoTitle": (this.showTools == 1)
		};
		
	}
	console.log(JSON.stringify(params));
	postTodo('setFormStyle', params, callback, false);
}

/**
 * 获取选中的索引
 * @param sync 是否同步：true同步(默认)、false异步
 * @param callback 回调函数, 参数data
 * @returns 同步时返回窗口索引(0-15)，异步时返回null
 */
VideoClient.prototype.getIndex = function(callback){
	postTodo("getIndex", null, function(data){
		if (data.success) {
			videoClient.curIndex = data.obj;
		}
		videoClient.handleCallBack(callback, data);
	}, false);
};

/**
 * 播放视频
 * @param indexs 播放位置的索引集合, indexs=null或者length=0时从多画面的第一个位置开始按顺序播放
 * @param cameraIds 摄像机ID集合
 * @param layout 画面布局 参数范围[1~16], 第一次初始时layout为NULL则根据cameraIds的数量来布局
 */
VideoClient.prototype.playVideo = function(indexs, cameraIds, layout, callback){
	return this.playVideoMult(window.top.jsConst.VIDEO_CLIENT_CONNECT_TYPE, indexs, cameraIds, layout, callback);
};

/**
 * 多画面播放视频
 * @param connectType 连接模式：0直连、1流媒体
 * @param indexs 播放位置的索引集合, indexs=null或者length=0时从多画面的第一个位置开始按顺序播放
 * @param cameraIds 摄像机ID集合
 * @param layout 画面布局 参数范围[1~16], 第一次初始时layout为NULL则根据cameraIds的数量来布局
 */
VideoClient.prototype.playVideoMult = function(connectType, indexs, cameraIds, layout, callback, sync){
	// 摄像机ID必须有值
	if (isNull(cameraIds) || cameraIds.length < 1) {
		console.error("videoClient.playVideoMult --> 无效的摄像机ID集合");
		return false;
	}

	// 校验布局参数，第一次初始化时为空则根据视频数量来设置窗口布局
	if (isNull(layout) || videoClient.curLayout == 0) {
		layout = cameraIds.length;
	}

	_setHistory();

	// 判断是否设置布局
	if( layout != videoClient.curLayout ){
		// 布局，同时检测视频客户端是否非正常关闭画面
		videoClient.setLayout(layout, function (data) {
			if( data.success ){
				_openVideo(connectType, indexs, cameraIds, callback);
			}
		});
	} else {
		_openVideo(connectType, indexs, cameraIds, callback);
	}
	return true;
};

function _openVideo (connectType, indexs, cameraIds, callback) {
	/*
	 * 格式化参数
	 */
	var cameraList = [];
	var iMax = 0;
	if (isNull(indexs)){
		iMax = cameraIds.length;
		if (iMax > 16) iMax = 16;
		for(var i=0; i<iMax; i++){
			cameraList.push({
				'index': i,
				'cameraId': cameraIds[i]
			});
		}
	} else {
		iMax = indexs.length;
		if (iMax > 16) iMax = 16;
		for(var i=0; i<iMax; i++){
			cameraList.push({
				'index': indexs[i],
				'cameraId': cameraIds[i]
			});
		}
	}

	postTodo("playVideoMult", {"connectType":connectType, "list":cameraList}, function(data){
		// 记录当前正在播放的视频，用于抓图证据保全等使用
		if (data.success) {
			while(cameraList.length){
				var temp = cameraList.shift();
				videoClient.curPlayCameraList[temp['index']] = temp['cameraId'];
			}
		} else{
			_alert(data.msg);
		}
		videoClient.handleCallBack(callback, data);
	}, false);
}

/**
 * 截图
 * @param cameraId 摄像机Id
 * @param file 文件名称, 后缀jpg
 * @param path 文件路径(可选，没有默认取数据库)
 */
VideoClient.prototype.snap = function(cameraId, file, path, callback){
	if (isNull(cameraId) || cameraId.length == 0){
		console.error("videoClient.snap --> cameraId is not null or empty!");
		return false;
	}
	postTodo("snap", {"cameraId":cameraId, "file":file, "path":path}, function(data){
		videoClient.handleCallBack(callback, data);
	}, false);
	return videoClient.callResult.success;
};

/**
 * 视频回放(异步处理)
 * @param cameraID 摄像机ID
 * @param cameraName 摄像机名称
 * @param startTime 开始时间yyyy-MM-dd HH:mm:ss
 * @param endTime 结束时间yyyy-MM-dd HH:mm:ss
 */
VideoClient.prototype.playbackMult = function(cameraID, cameraName, startTime, endTime, callback){
	videoClient.getIndex(function (data) {
		if (!data.success) {
			videoClient.handleCallBack(callback, data);
			return false;
		}
		var index = data.obj;
		var args = {
				"cameraID":cameraID,
				"startTime":startTime,
				"endTime":endTime
			};
		postTodo("playbackMult", args, function(data){
			if (data.success){
				var temp = data.obj;
				// 记录当前回放的视频信息
				videoClient.playbackList.push({
					"index": index,
					"cameraID": cameraID,
					"cameraName": cameraName,
					"startTime": startTime,
					"endTime": endTime,
					"devType": temp.devType
				});
			}
			videoClient.handleCallBack(callback, data);
		}, false);
	});
};

VideoClient.prototype.playbackVideoMult = function(videoList, layout){
	var args = {
			'layout': layout,
			'videoList': videoList
	}
	console.log("视频回放列表:"+JSON.stringify(args));

	videoClient.playbackBefore = {};
	for (var i = 0; i < videoList.length; i++) {
		var info = videoList[i];
		var sTime = Date.parse(info.startTime.replace(/-/g,"/"));
		var eTime = Date.parse(info.endTime.replace(/-/g,"/"));

		// 记录回放前打开的视频
		videoClient.playbackBefore[info.index] = videoClient.curPlayCameraList[info.index];

		if( sTime > eTime ){
			_alert('画面'+ (parseInt(info.index) + 1) +'的开始时间不能大于结束时间!');
			return;
		}
	}
	
	postTodo("playbackVideoMult", args, function(data){
		if (data.success){
			var cameraList = data.obj;

			if( layout ){
				videoClient.curLayout = layout.layout;
			}

			for(var key in cameraList){
				videoClient.curPlayCameraList[key] = cameraList[key];
			}
			videoClient.playbackList = videoList;
			console.log("视频回放成功");
		} else {
			if( data.msg )
				_alert(data.msg);
		}
		//videoClient.handleCallBack(data);
	}, false);
};

/**
 * 停止视频回放
 * @param index 窗口索引，可选
 */
VideoClient.prototype.stopPlaybackMult = function(index, callback){
	if (isNull(index)) {
		videoClient.getIndex(function (data) {
			if (data.success) {
				todo(data.obj);
			} else {
				videoClient.handleCallBack(callback, data);
			}
		});
	} else {
		todo(index);
	}

	function todo (index) {
		postTodo("stopPlaybackMult", {"index": index}, function(data){
//			// 删除当前回放的视频信息
//			for(var i=0; i<videoClient.playbackList.length; i++){
//				var tempData = videoClient.playbackList[i];
//				if (tempData.index == index){
//					videoClient.playbackList.splice(i, 1); break;
//				}
//			}
//			// 还原之前的视频
//			cameraID = videoClient.curPlayCameraList[index];
//			if (isNotNull(cameraID)){
//				videoClient.playVideo([index], [cameraID], videoClient.curLayout);
//			}
			videoClient.handleCallBack(callback, data);
		},false);
	}
};


/**
 * 开始视频录像
 * @param file 保存的录像名称，后缀mp4
 * @param path 保存的路径，可选
 */
VideoClient.prototype.startRecord = function(cameraId, file, path, callback){
	if (isNull(cameraId) || cameraId.length == 0){
		console.error("videoClient.startRecord --> cameraId is not null or empty!");
		return false;
	}
	postTodo("startRecord", {"cameraId":cameraId, "file":file, "path":path}, function(data){
		videoClient.handleCallBack(callback, data);
	}, false);
};

/**
 * 停止视频录像
 * @param params 窗口索引 | {"index":"窗口索引", "fileName":"录像文件"}
 */
VideoClient.prototype.stopRecord = function(params, callback){
	if( typeof params != 'object' ){
		params = {"index": params};
	}
	postTodo("stopRecord", params, function(data){
		videoClient.handleCallBack(callback, data);
	},false);
	return true;
};

/**
 * 关闭客户端
 */
VideoClient.prototype.closeAllMult = function(callback){
	postTodo("closeAllMult", null, function(data){
		videoClient.curLayout = 0;// 当前画面布局(默认0没有)
		videoClient.curIndex = 0;// 当前选中的窗口索引
		videoClient.curPlayCameraList = {};// 当前播放的视频集合{"窗口位置索引":"视频ID"}
		videoClient.handleCallBack(callback, data);
	}, true);
	return true;
};

/**
 * 获取回放的视频信息
 * @param index 窗口索引
 */
VideoClient.prototype.getPlaybackCameraInfo = function(index){
	var list = videoClient.playbackList;
	for(var i=0; i<list.length; i++){
		var temp = list[i];
		if (temp.index == index){
			return temp;
		}
	};
	return null;
};

/**
 * 播放视频文件
 * @param index 窗口索引
 * @param file 视频文件名称
 * @param ftpPath ftp文件路径
 * @param type 视频类型：1大华、8海康、20EDNNS
 * @param 
 */
VideoClient.prototype.playVideoFile = function(index,file,ftpPath,type,callback){
	var args = {
		"index": index,
		"file": file,
		"type": type,
		"ftpPath": ftpPath
	};
	if( this.curLayout == 0 ){
		this.setLayout(1, function (data) {
			if( data.success ){
				_playVideoFile(args, callback);
			}
		});
	} else {
		_playVideoFile(args, callback);
	}
};

function _playVideoFile (args, callback) {
	//在后台判断本地文件是否存在update by zk
	/*var file = args.file;
	delete args["download"];
	if( sysFileObj.FileExists(file) ){
		delete args["download"];
	} else {
		var folders = file.split('\\');
		var folder = folders.shift();// 获取盘符
		folders.pop();// 去掉最后的文件
		while (folders.length) {
			folder += '\\' + folders.shift();
			if( !sysFileObj.FolderExists(folder) ){
				console.log('创建远程文件下载存放目录：' + folder);
				sysFileObj.CreateFolder(folder);
			}
		}
		console.log('远程下载播放视频文件: ' + file);
	}*/

	postTodo("playVideoFile", args, function(data){
		if( !data.success && data.msg ){
			_alert(data.msg);
		}
		videoClient.handleCallBack(callback, data);
	},false);
}

/**
 * 打开摄像机群组
 */
VideoClient.prototype.playGroupVideo = function(orgId, groupId, groupName, extend, callback){
	var args = {
		"orgId":orgId,
		"groupId":groupId,
		"groupName":groupName,
		"extend": extend
	};
	postTodo("playGroup", args, function(data){
		if (data.success) {
			var list = data.obj;
			var temp = null;
			var length = list.length;
			
			if( extend.isAutoLayout ){
				videoClient.curLayout = length < videoClient.maxLayout ? length : videoClient.maxLayout;
			}

			for(var i = 0; i < videoClient.curLayout; i++){
				temp = list[i];
				videoClient.curPlayCameraList[temp.index] = temp.cameraId;
			}
		}
		videoClient.handleCallBack(callback, data);
	}, false);
};



/**
 * 获取配置
 * @returns array 数组，3个值分别为：是否置顶、是否显示标题栏、是否显示工具栏
 */
VideoClient.prototype.getConfig = function () {
	var cookieValue = getCookie( vc_getCookieName() );
	if( cookieValue && cookieValue.length > 0 ){
		var list = cookieValue.split('|');

		this.topMost = list[0];
		this.showTitle = list[1];
		this.showTools = list[2];

		return list;
	} else {
		return [this.topMost,this.showTitle,this.showTools];
	}
};

/**
 * 设置配置
 * @param topMost	是否置顶，参数值：0否|1是
 * @param showTitle 是否显示标题栏，参数值：0否|1是
 * @param showTools 是否显示工具栏，参数值：0否|1是
 */
VideoClient.prototype.setConfig = function (topMost, showTitle, showTools) {
	var cookieName = vc_getCookieName();
	var cookieValue = topMost + '|' + showTitle + '|' + showTools;
	var expires = 30*24*60*60*1000;

	setCookie( cookieName, cookieValue, expires);
}

/*
 * 播放视频监控操作
 * @param options 参数对象
 * options = {
 * 		"subType":"操作子类型：1手动、2轮循(自动 add by zk)、3群组",
 * 		"layout":"布局",
 * 		"data":"数据",
 * 		"callback":"回调"
 * }
 */
VideoClient.prototype.playUUID = null;
VideoClient.prototype.playVideoHandle = function(options){
	this.playUUID = (new Date()).getTime();
	var data = options.data;
	var layout = options.layout;
	var callback = options.callback;

	if( isNull(data) || data.length == 0){
		console.error("videoClient.playVideoMult --> 无效的摄像机ID集合");
		return false;
	}

	// 校验布局参数，第一次初始化时为空则根据视频数量来设置窗口布局
	if (isNull(layout) || videoClient.curLayout == 0) {
		layout = data.length;
	}
	
	_setHistory();

	// 判断是否设置布局
	if( layout != videoClient.curLayout ){
		// 布局，同时检测视频客户端是否非正常关闭画面
		videoClient.setLayout(layout, function (data) {
			if (data.success) {
				_playVideoHandle(options, callback);
			} else {
				videoClient.handleCallBack(callback, data);
			}
		});
	} else {
		_playVideoHandle(options, callback);
	}
	return this.playUUID;
};

function _playVideoHandle (options, callback) {
	var data = options.data;
	var temp = null;
	var index = 0;
	var cameraList = [];
	for(var i = 0; i < data.length; i++){
		temp = data[i];
		if( typeof temp == 'object' ){
			if( isNull(temp.index) || temp.index.length == 0 ){
				temp['index'] = index++;
			}
			cameraList[i] = temp;
		} else {
			cameraList[i] = {
				'index': index++,
				'cameraId': temp
			}
		}
	}

	_videoHandle({
		'handleType': 1,
		'type': "1",
		'subType': options.subType,
		'forPlay': options.forPlay,
		'cameraList': cameraList
	}, function (data) {
		// 记录当前正在播放的视频，用于抓图证据保全等使用
		if (data.success) {
			while(cameraList.length){
				var temp = cameraList.shift();
				videoClient.curPlayCameraList[temp['index']] = temp['cameraId'];
			}
		} else
			_alert(data.msg);
		videoClient.handleCallBack(callback, data);
	});
}

/*
 * 打开视频预案
 */
VideoClient.prototype.playGroupHandle = function(orgId, groupId, groupName, extend, callback){
	_setHistory();
	_videoHandle({
		'orgId': orgId,
		'handleType': 2,
		'type': 1,
		'subType': 3,
		'groupId':groupId,
		'groupName':groupName,
		'extend': extend
	}, function(data){
		//console.log("playGroupHandle data = " + JSON.stringify(data));
		if (data.success) {
			var list = data.obj;
			var temp = null;
			var length = list.length;

			if( extend.isAutoLayout ){
				videoClient.curLayout = length < videoClient.maxLayout ? length : videoClient.maxLayout;
			}

			for(var i = 0; i < videoClient.curLayout; i++){
				temp = list[i];
				videoClient.curPlayCameraList[temp.index] = temp.cameraId;
			}
		}
		videoClient.handleCallBack(callback, data);
	});
};

/*
 * 回放视频
 */
VideoClient.prototype.playbackHandle = function(videoList, layout){
	_setHistory();
	videoClient.playbackBefore = {};

	for (var i = 0; i < videoList.length; i++) {
		var info = videoList[i];
		var sTime = Date.parse(info.startTime.replace(/-/g,"/"));
		var eTime = Date.parse(info.endTime.replace(/-/g,"/"));

		// 记录回放前打开的视频
		videoClient.playbackBefore[info.index] = videoClient.curPlayCameraList[info.index];

		if( sTime > eTime ){
			_alert('画面'+ (parseInt(info.index) + 1) +'的开始时间不能大于结束时间!');
			return;
		}
	}

	_videoHandle({
		'handleType': 3,
		'type': 2,
		'subType': 1,
		'layout': layout,
		'cameraList': videoList
	}, function(data){
		if (data.success){
			var cameraList = data.obj;

			if( layout ){
				videoClient.curLayout = layout.layout;
			}

			for(var key in cameraList){
				videoClient.curPlayCameraList[key] = cameraList[key];
			}
			videoClient.playbackList = data.obj;
			console.log("视频回放成功");
		} else {
			if( data.msg )
				_alert(data.msg);
		}
		//videoClient.handleCallBack(data);
	});
};

/*
 * 回退到上一次打开（临时）
 */
VideoClient.prototype.goback = function () {
	var layout = this.history[0].layout;
	var data = this.history[0].data;
	var indexs = [];
	var cameras = [];

	console.log('history=', JSON.stringify(this.history));
	if (data&&JSON.stringify(data)!="{}") {
		for(var i in data) {
			indexs.push(i);
			cameras.push(data[i]);
		}
	} else {
		console.log('当前无播放历史!');
		_alert('当前无播放历史!');
		return;
	}

	this.playVideo(indexs, cameras, layout);
}

/*
 * 停止轮循（专门给地图框选使用）
 */
VideoClient.prototype.stopForPlay = function () {
	
};


/*
 * 视频操作 
 */
function _videoHandle (params, callback) {
	params['cusNumber'] = params.orgId || jsConst.ORG_CODE;
	params['userId'] = jsConst.USER_ID;
	params['userName'] = jsConst.REAL_NAME;
	postTodo('videoHandle', params, callback, false);
}

function vc_getCookieName () {
	return 'videoclient_config_' + jsConst.ORG_CODE + '_' + jsConst.USER_ID;
}

/**
 * POST请求
 * @param method 方法
 * @param params 参数
 * @param callback 回调
 * @param sync 是否同步：true同步(默认)、false异步
 */
function postTodo (method, params, callback, sync) {
	if (isNull(sync)) sync = true;

	// 参数检查
	var handle = window.top[sync ? 'syncTodo' : 'ajaxTodo'];
	var stopFor = videoClient.stopForPlay;
	var forPlay = false;
	var args = null;

	if (params) {
		args = {'userId':jsConst.USER_ID, 'args':JSON.stringify(params)};
		forPlay = params.forPlay;
		console.log('请求视频客户端 --> 方法<', method, '>, 参数：<', JSON.stringify(args), '>');
	}

	// 轮循标识不为true停止轮循
	forPlay || (stopFor && stopFor())

	console.log('---请求后台videoClient/' + method+",args:"+args);
	// 请求服务器
	handle('videoClient/' + method, args, function (result) {
		console.log('视频客户端响应 --> ', JSON.stringify(result), '>');
		videoClient.callResult = result;
		callback && callback.call(this, result);

		if (result && !result.success) {
			//_alert(result.msg);
		}
	});
}

function isNull(obj){
	return obj == undefined || obj == null;
}

function isNotNull(obj){
	return !isNull(obj);
}

function fileExists(file){
	console.log("检查文件是否存在" + file);
	var fbl = sysFileObj.FileExists(file);
	console.log(fbl);
	return fbl;
}


function _setHistory () {
	videoClient.history[0].layout = videoClient.curLayout;
	videoClient.history[0].data = JSON.parse(JSON.stringify(videoClient.curPlayCameraList));
}

//add by zk
function _alert(msg){
	$.messageBox( {
		iframePanel:true,
        message:msg,
        position: {
            my: "right top",   //与上述类似，此为右上角
            at: "right top",
            of: window
        }
    });  
}