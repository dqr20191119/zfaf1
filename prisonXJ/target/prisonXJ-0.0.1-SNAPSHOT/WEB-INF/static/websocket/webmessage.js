/**
 * 历史消息处理
 * Web消息对象
 */
function WebMessage(){
	this.webSocket = null; 		// websocket对象
	this.onPools = {};			// 事件订阅池
	this.msgPools = new Array();// 消息池
	this.locked = false;		// 消息处理锁
	this.handleInterval = 20;	// 消息处理间隔
}

/**
 * 初始化消息对象
 */
WebMessage.prototype.init = function (url) {
	var webMessage = this;
	
	// 创建socket对象
	this.webSocket = new ReconnectingWebSocket(url);

	// 事件订阅
	this.webSocket.on('onopen', 'login', function(event){
		webMessage.login();
	});
	this.webSocket.on('onmessage', 'webmessage', function (event) {
		var data = event.data;
		console.log('webmessage.receive: ' + data);

		if( data && data.length ){
			webMessage.msgPools.push( JSON.parse(data) );
			webMessage.handle();
		}
	});
};

/**
 * 用户登录
 */
WebMessage.prototype.login = function () {
	this.send({
		"serviceId": jsConst.WEBSOCKET_SERVICE_URL,
		"method": "login",
		"cusNumber": jsConst.ORG_CODE,
		"userId": jsConst.LOGIN_USER_KEY,
		"msgId": jsConst.LOGIN_USER_KEY + '' + (new Date()).getTime()
	});
};

/**
 * 用户注销
 */
WebMessage.prototype.logout = function () {
	this.send({
		"serviceId": jsConst.WEBSOCKET_SERVICE_URL,
		"method": "logout",
		"cusNumber": jsConst.ORG_CODE,
		"userId": jsConst.LOGIN_USER_KEY,
		"msgId": jsConst.LOGIN_USER_KEY + '' + (new Date()).getTime()
	});
};

/**
 * 消息发送
 */
WebMessage.prototype.send = function (data) {
	try{
		data = JSON.stringify(data);
		console.log('webmessage.send: ' + data);
		return this.webSocket.send( data );
	} catch (e) {
		console.error('webmessage.send: ' + e);
		return false;
	}
};

/**
 * 消息订阅
 * @param msgType	消息类型
 * @param name		订阅者
 * @param fn		处理函数，返回参数data
 */
WebMessage.prototype.on = function (msgType, name, fn) {
	try {
		if( this.onPools[msgType] == undefined ){
			this.onPools[msgType] = {};
			this.onPools[msgType][name] = fn;
		} else {
			if( this.onPools[msgType][name] == undefined ){
				this.onPools[msgType][name] = fn;
			} else {
				console.warn('webmessage.on: [', msgType, ']的消息订阅成功，订阅者[', name, ']已存在!');
				return false;
			}
		}
		console.log('webmessage.on: [', msgType, ']的消息订阅成功，订阅者[', name, ']');
		return true;
	} catch (e) {
		console.error('webmessage.on: 消息订阅错误，消息类型[', msgType, '], 订阅者[', name, ']');
	}

};

/**
 * 取消订阅
 * @param msgType	消息类型
 * @param name		订阅者
 */
WebMessage.prototype.off = function (msgType, name) {
	try {
		if( this.onPools[msgType] ){
			delete this.onPools[msgType][name];
			console.log('webmessage.off: 订阅者[', name, ']已取消[', msgType, ']消息的订阅!');
			return true;
		}
	} catch(e) {
		console.error('webmessage.off: 取消订阅错误：',e,'消息类型[',msgType,']，订阅者[', name, ']');
		return false;
	}
}

/**
 * 消息处理
 */
WebMessage.prototype.handle = function (){
	if( !this.locked ){
		// 消息处理时锁定，防止并发
		this.locked = true;
		this._execute();
	}
}

/**
 * 轮循处理消息(不提供给外部使用)
 */
WebMessage.prototype._execute = function(){
	if( this.locked ){
		var message = this.msgPools.shift();			// 消息
		var subscibers = this.onPools[message.msgType];	// 消息订阅者集合
		var hasSubsciber = false;						// 是否有订阅者

		if( subscibers ){
			for(var key in subscibers ){
				hasSubsciber = true;
				// 采用延迟操作来并发处理同一条消息
				setTimeout(function(fn, msg){
					try {
						fn (msg);
					} catch (e) {
						console.error('webmessage.handle: ' + e);
					}
				}, 10, subscibers[key], message);
			}
		}

		// 没有被订阅的消息暂时不做处理
		if( !hasSubsciber ){
			console.warn('webmessage.handle: [' + message.msgType + ']的消息没有被订阅!');
		}

		// 消息处理完之后解锁
		if( this.msgPools.length == 0 ){
			this.locked = false;
		} else {
			// 消息处理间隔控制
			setTimeout(this._execute, this.handleInterval);
		}
	}
}