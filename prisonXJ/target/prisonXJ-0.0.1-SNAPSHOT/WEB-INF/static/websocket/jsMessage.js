/**
 * WebSocket消息分发处理
 */
function jsMessage() {
}

jsMessage.webSocket=null;  //WebSocket实体对象
jsMessage.queue=null;	   //消息缓存队列
jsMessage.alarmQueue=null; //报警消息缓存队列，优先处理
jsMessage.dataMap=null;	   //数据缓存器
jsMessage.loginFlag=false; //WebSocket登录标志
jsMessage.reLoginCount=0;  //WebSocket重新连接次数
jsMessage.callbackMap=null;//回调方法存储器
jsMessage.preMsgType=null; //上一次收到的消息类型
jsMessage.preMsgTime=null; //上一次收到消息的时间

// 扩展 xie.yh 2016.05.17
var _callbackPools = {};

/**
 * WebSocket初始化
 */
jsMessage.INIT = function(orgId,userId){
	jsMessage.queue = new Array();
	jsMessage.alarmQueue = new Array();
	jsMessage.dataMap = new ces.Map();
	jsMessage.callbackMap = new ces.Map();
	jsMessage.webSocket = new ReconnectingWebSocket(messageConst.SOCKET_URL);
	jsMessage.webSocket.onmessage = function(event) {
		jsMessage.receive(event);
	};
	setTimeout(function() {
		jsMessage.LOGIN(orgId,userId);
	},messageConst.WEBSOCKET_RELOGINTIME);
};

/**
 * 用户登录
 * @param orgId  机构编号
 * @param userId 用户编号
 */
jsMessage.LOGIN = function(orgId,userId,msgId){
	if (msgId == null) {
		msgId = ces.seq.get();
	}
//	var msg='{"serviceId":"'+messageConst.WEBSOCKET_SERVICEURL+'","method":"login","cusNumber":"'+orgId+'","userId":"'+userId+'","msgId":"'+msgId+'","callback":"jsMessage.login"}';
	var msg = {
		method: 'login',
		serviceId: messageConst.WEBSOCKET_SERVICEURL,
		cusNumber: orgId,
		userId: userId,
		msgId: msgId
	};
	_callbackPools[msgId] = jsMessage.login;
	jsMessage.send(JSON.stringify(msg), true);
};

/**
 * 用户登出
 * @param orgId  机构编号
 * @param userId 用户编号
 */
jsMessage.LOGOUT = function(orgId,userId){
	var msgId = ces.seq.get();
//	var msg='{"serviceId":"'+messageConst.WEBSOCKET_SERVICEURL+'","method":"logout","cusNumber":"'+orgId+'","userId":"'+userId+'","msgId":"'+msgId+'"}';
	var msg = {
		method: 'logout',
		serviceId: messageConst.WEBSOCKET_SERVICEURL,
		cusNumber: orgId,
		userId: userId,
		msgId: msgId
	};
	jsMessage.send(JSON.stringify(msg));
};

//WebSocket消息发送
jsMessage.send = function (msg, flag) {
	if (jsMessage.loginFlag || flag) {
		var msgObj = JSON.parse(msg);
		if (msgObj.callback != null) {
			jsMessage.callbackMap.put(msgObj.msgId, msgObj.callback);
		}
		jsMessage.webSocket.send(msg);
	}else{
		$.messager.alert('系统消息','WebSocket未连接,请重新登录');
	}
};

//WebSocket消息接收
jsMessage.receive = function(event){
	console.log('服务器推送消息：' + event.data);

	var msg = JSON.parse(event.data);
	console.log("..............msgType:"+msg.msgType);
	if (msg.msgType == messageConst.WEBSOCKET_TYPE_ACK) { //消息是用户请求的反馈,则实时处理
		jsMessage.excute(msg);
	} else if(msg.msgType == messageConst.WEBSOCKET_TYPE_MSG_TALK_RESULT_STATUS ||msg.msgType == messageConst.WEBSOCKET_TYPE_MSG_BROADCAST_RESULT_STATUS||msg.msgType == messageConst.WEBSOCKET_TYPE_MSG_DOOR_RETURN_STATUS){
        jsMessage.excuteNew(msg);
    }else {  //消息是服务器推送的,则定时处理
		if (msg.msgType == messageConst.WEBSOCKET_TYPE_ALARMHANDLE) {//报警消息另外缓存，优先处理
			//北京报警的时候出现初始化页面
			console.log("报警推送");
			toDisplay('homeMenu');
			
			jsMessage.alarmQueue.push(msg);
		}else{
			jsMessage.queue.push(msg);
		}
	}
};

//WebSocket消息返回实时处理
/*jsMessage.excuteDoorCtr = function(msg){
    console.log("jsMessage.excuteDoorCtr......:"+JSON.stringify(msg));
    try {
        eval(messageConst.REFRESH_FUN_MAP.get(msg.msgType)+"("+JSON.stringify(msg.content)+")");
    } catch (e) {
        console.log('jsMessage.excuteDoorCtr：', e);
    }
};*/

/*jsMessage.excuteTalk = function(msg){
    console.log("jsMessage.excuteTalk......:"+JSON.stringify(msg));
    try {
        eval(messageConst.REFRESH_FUN_MAP.get(msg.msgType)+"("+JSON.stringify(msg.content)+")");
    } catch (e) {
        console.log('jsMessage.excuteTalk：', e);
    }
};*/

//WebSocket广播消息返回实时处理
jsMessage.excuteNew = function(msg){
    console.log("jsMessage.excuteNew......:"+JSON.stringify(msg));
    try {
        eval(messageConst.REFRESH_FUN_MAP.get(msg.msgType)+"("+JSON.stringify(msg.content)+")");
    } catch (e) {
        console.log('jsMessage.excuteNew：', e);
    }
};

//WebSocket消息反馈实时处理
jsMessage.excute = function(msg){
    //console.log("jsMessage.excute......:"+msg)
	var call = null;
	var msgId = null;
	try {
		msgId = msg.msgId;
		call = _callbackPools[msgId];
		call && call(msg.content, msgId);
		delete _callbackPools[msgId];
		//eval(jsMessage.callbackMap.get(msg.msgId)+"("+JSON.stringify(msg.content)+","+msg.msgId+")");
	} catch (e) {
		console.log('jsMessage.excute：', e);
	}
};

jsMessage.login = function (content, msgId) {
	content = JSON.parse(content);
	console.log('jsMessage.login --> ' + content.msg);

	//如果WebSocket登录失败,休眠之后再尝试连接
	if (content.successFlag) {
		jsMessage.reLoginCount = 0;
		setInterval(jsMessage.handler, messageConst.WEBSOCKET_INTERVAL);
	} else {
		if (jsMessage.reLoginCount++ <= 3) {
			setTimeout(jsMessage.LOGIN, messageConst.WEBSOCKET_RELOGINTIME, content.cusNumber, content.userId, msgId);
//			setTimeout(function(){
//				jsMessage.LOGIN(content.cusNumber, content.userId, msgId);
//			}, messageConst.WEBSOCKET_RELOGINTIME);
		}
	}
};

//WebSocket推送消息分发处理
jsMessage.handler = function(){
	//报警消息优先处理
    //debugger;
	var msg = jsMessage.alarmQueue.shift() || jsMessage.queue.shift();
//	if (jsMessage.alarmQueue.length) {
//		// 报警消息有联动完成开关，联动完成之后才可以取消息
//		var status = window.top.mapRoamStatu;
//		if(status == messageConst.WEBSOCKET_MSG_OPEN){//开
//			// 删除并返回数组的第一个元素
//			msg = jsMessage.alarmQueue.shift();
//			// 将开关设置为关
//			window.top.mapRoamStatu = messageConst.WEBSOCKET_MSG_CLOSE;
//		}else if(status == messageConst.WEBSOCKET_MSG_CLOSE){//关
//			// 关，代表正处于联动执行中状态，不作处理，等下一次触发取消息
//		}
//		msg = jsMessage.alarmQueue.shift();
//	} else if(jsMessage.queue.length > 0){
//		msg = jsMessage.queue.shift();//删除并返回数组的第一个元素
//	}
    
	if (msg) {
		console.log('jsMessage.handler --> 消息处理：' + JSON.stringify(msg));
		var currentPage = $("#index_currentPage").val();
		if(currentPage==undefined)
			currentPage ='cds';
		
		var refreshArray = messageConst.REFRESH_MODUAL_MAP.get(currentPage);
		var flag = ces.array.contains(refreshArray,msg.msgType);





		if (flag) {
			//消息类型为当前展示的页面，直接刷新页面数据；
			var method = messageConst.REFRESH_FUN_MAP.get(msg.msgType);
			var diff = messageConst.WEBSOCKET_REEXCUTETIME;
			if(jsMessage.preMsgTime != null){
				diff = new Date().getTime() - jsMessage.preMsgTime.getTime();
			}
			if(jsMessage.preMsgType == msg.msgType && diff < messageConst.WEBSOCKET_REEXCUTETIME){
				//如果收到打消息类型与上次的一致，且时间少于处理时间间隔，则等待后执行
				setTimeout(function() {
					eval(method+"("+JSON.stringify(msg.content)+")");
					initMessage();//弹窗提醒待办事项 add by tao
				},messageConst.WEBSOCKET_REEXCUTETIME-diff);
			}else{
				eval(method+"("+JSON.stringify(msg.content)+")");
				 initMessage();//弹窗提醒待办事项 add by tao
			}
			
			jsMessage.preMsgType=msg.msgType; //上一次收到的消息类型
			jsMessage.preMsgTime=new Date(); //上一次收到消息的时间
		}else{
			//否则存储在缓存器中，等待目标页面展示时使用
			jsMessage.dataMap.put(msg.type, msg.content);//缓存器中仅含该类型最新消息
		}
	}
};
