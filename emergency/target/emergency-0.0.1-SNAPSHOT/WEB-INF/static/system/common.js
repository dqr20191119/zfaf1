function ssoPage(){
	return  "http://34.211.0.202/portal/logout";
	//return jsConst.basePath+"lg/loginCtrl/ln";
}
function logoutX(event,name) {
	$("#logoutDialog").dialog({
		width : 300,
		height : 150
	});
	$("#logoutDialog").dialog("open");
}
function loginCancel(event,name) {
	$("#logoutDialog").dialog("close");
}
function logout(){
	var ur = jsConst.basePath+'lg/loginCtrl/logout';
	$.ajax({
		type : 'post',
		url : ur,
		data : {'userId': jsConst.USER_ID  },
		dataType : 'json',
		success : function(data) {
			var user = null;
			if (data.result) {
				/*user = data.userBean;*/
				window.location.href=ssoPage();
				
				// 关闭当前窗口
				/*window.opener = null;
				window.open('', '_self');
				window.close();*/
			}else{
				alert("退出失败："+data.respDesc);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}
// 用户等级
window.userLevel = {
	PROV: 1,
	PRIS: 2,
	AREA: 3
};
/**
 * 分级处理
 * @param =================传参方式（一）====================
 * @param prov	省局级处理方法
 * @param pris	监狱级处理方法
 * @param area	监区级处理方法（不传则默认和pris一样）
 * 
 * @param =================传参方式（二）====================
 * @param options = {	
 * 		"prov": "省局级处理方法",
 * 		"pris": "监狱级处理方法",
 * 		"area": "监区级处理方法"
 * }
 * @param options = {	
 * 		"prov": "省局级处理方法",
 * 		"pris area": "相同处理方法可合并处理,用空格隔开",
 * }
 * 
 * @param =================传参方式（三）====================
 * @param key 处理级别：prov|pris|area
 * @param fun 处理方法
 */
function handleByLevel () {
	try {
		var options = arguments[0];
		if (typeof options == 'object'){
			var _options = {};
			var _key = null;
			var _val = null;
			for (var key in options) {
				_val = options[key];
				key = key.split(' ');
				while (key.length) {
					_key = key.shift();
					if (_key) {
						_options[_key] = _val;
					}
				}
			}
			arguments[0] = _options.prov;
			arguments[1] = _options.pris;
			arguments[2] = _options.area;
		} else if (typeof options === 'string') {
			var keys = options.split(' ');
			var _key = null;
			var _options = {};
			while (keys.length) {
				_key = keys.shift();
				_options[_key] = arguments[1];
			}
			arguments[0] = _options.prov;
			arguments[1] = _options.pris;
			arguments[2] = _options.area;
		} else {
			if (arguments.length == 2) {
				arguments[2] = arguments[1];
			}
		}

		switch (jsConst.USER_LEVEL) {
			case window.userLevel.PROV: return _call(arguments[0]);
			case window.userLevel.PRIS: return _call(arguments[1]);
			case window.userLevel.AREA: return _call(arguments[2]);
		}
	} catch(e) {
		throw Error('分级处理错误：' + e.message);
	}
	return null;
}

/*
 * 分等级处理：prov省局处理，监狱监区处理
 */
function handleByLevel1 (prov, other) {
	if (jsConst.PRISON_ORG_CODE) {
		return !other || other();
	} else {
		return !prov || prov();
	}
}


/**
 * 判断是否为空
 * @param obj
 * @returns {Boolean}
 */
function isNull(obj){
	return obj == undefined || obj == null || obj == 'undefined';
}
/**
 * 判断是否不为空
 * @param obj
 * @returns {Boolean}
 */
function isNotNull(obj){
	return !window.isNull(obj);
}
function isFunction (obj) {
	return typeof obj == 'function';
}

/**
 * 同步请求
 * @param url 相对路径
 * @param args 参数
 * @param success 请求成功后的回调函数
 * @param error 请求失败后的回调函数
 * @param window 数据加载中动态效果(window.flag:true开启/false关闭;window.text:显示文字;window.body:$("body")页面body对象)
 */
function syncTodo (url, args, success, error, window) {
	_ajax(url, args, success, error, false, window);
}
/**
 * 异步请求
 * @param url 相对路径
 * @param args 参数
 * @param success 请求成功后的回调函数
 * @param error 请求失败后的回调函数
 * @param window 数据加载中动态效果(window.flag:true开启/false关闭;window.text:显示文字;window.body:$("body")页面body对象)
 */
function ajaxTodo (url, args, success, error, window) {
	_ajax(url, args, success, error, true, window);
}

/**
 * 封装的ajax请求方法
 * @param url 相对路径
 * @param args 参数
 * @param success 请求成功后的回调函数
 * @param error 请求失败后的回调函数
 * @param async 是否异步ture/false
 * @param window 数据加载中动态效果(window.flag:true开启/false关闭;window.text:显示文字;window.body:$("body")页面body对象)
 */
function _ajax (url, args, success, error, async, _window) {
	var _msMask = null;
	var _body = null;
	var _text = null;
	// 检查请求地址是否缺省根目录
	if (url.indexOf(jsConst.basePath) != 0) {
		url = jsConst.basePath + url;
	}
	$.ajax({
		'url': url,
		'data': args,
		'async': async,
		'timeout': 120 * 1000,
		'type': 'POST',
		'dataType': "json",
		'contentType': "application/x-www-form-urlencoded; charset=utf-8",
		'cache': false,
		'success': function (result) {
			success && success(result);
		},
		'error': function (request, status, e) {
			error && error(request, status, e);
		}
	});
}

/*
 * 获取Cookie值
 */
function getCookie (cookieName) {
	if (localStorage) {
		console.log('getCookie: use localStorage...');
		return localStorage.getItem(cookieName);
	} else {
		var cookies = document.cookie.split(';');
		var cookie = null;
		for(var i = 0; i < cookies.length; i++ ){
			cookie = cookies[i].trim();
			if( cookie.indexOf( cookieName ) > -1 ){
				return cookie.replace(cookieName + '=', '');
			}
		}
		return null;
	}
}

/*
 * 设置Cookie
 */
function setCookie (key, value, expires) {
	if (localStorage) {
		console.log('setCookie: use localStorage...');
		localStorage.setItem(key, value);
	} else {
		var date = new Date();
		// 设置有效时间
		date.setTime(date.getTime() + expires);
		// 保存Cookie
		document.cookie = key + '=' + value + '; expires=' + date.toUTCString();
	}
}

/**
 * 初始化jsConst用户常量
 * @param userBean
 * @returns
 */
function initJSConstUserBean(user){
	jsConst.ORG_CODE = user.orgCode;					// 机构代码
	jsConst.CUS_NAME = user.orgName;					// 机构名称
	jsConst.CUS_NUMBER = user.cusNumber;				// 机构代码
	jsConst.PRISON_ORG_CODE = user.cusNumber;			// 监狱机构代码
	jsConst.ROOT_ORGA_CODE = user.cusNumber;			// 当前选中监狱编号
    jsConst.ROOT_ORGA_NAME = user.orgName;				// 当前选中监狱名称
	jsConst.USER_ID = user.userId;						// 用户ID
	jsConst.USER_NAME = user.userName;					// 用户名
	jsConst.REAL_NAME = user.realName;					// 用户真实姓名
	jsConst.POLICE_CODE = user.policeNo; 				// 警员编号
	jsConst.DEPARTMENT_ID = user.dprtmntCode;			// 部门Code
	jsConst.DEPARTMENT_NAME = user.dprtmntName;			// 部门名称
	jsConst.ROLE_LIST = user.roles;
	jsConst.ROLE_ID = user.roles[0].type; 				//用户角色类型
	jsConst.ORG_CLASS_KEY = user.orgClassKey;					// 用户组织类别编码
	jsConst.USER_LEVEL = window.userLevel[user.userLevel];		// 用户等级
	jsConst.LOGIN_USER_KEY = user.policeNo;						// 登录用户标识(前面用userId的数据有问题的改库)
	jsConst.SPECIAL_POLICE = user.isSpecialPolice;				// 是否特警队员
}

/**
 * 初始化页面
 */
function initPage(){
	//默认初始化
}

function loadRightData() {
	
}

function queryAlarmLevRecord() {
	
}

function showPeopleAndCarCount() {
	
}

function showDqyh() {
	
}
/**
 * 获取初始化用户信息
 * @returns
 */
function initUserInfo(){
	var ur = jsConst.basePath+'lg/loginCtrl/userinfo';
	$.ajax({
		type : 'get',
		url : ur,
		dataType : 'json',
		success : function(data) {
			var user = null;
			if (data.result) {
				user = data.userBean;
				initJSConstUserBean(user);

				initVideoPlayerType();

				// alert(jsConst.USER_NAME+":获取用户信息成功");
				// window.location.href=
				initPage();
				// 初始消息
                try {
                    window.jsMessage.INIT(jsConst.CUS_NUMBER, jsConst.USER_ID);
                } catch (e) {
                }
                loadRightData();
				
				queryAlarmLevRecord();
				
				showPeopleAndCarCount();
				
				showDqyh();
			}else{
				alert("获取用户信息失败，请重新登录");
				window.location.href=ssoPage();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}

/**
 * 获取初始化用户信息
 * @returns
 */
function initUserInfo1(){
	var ur = jsConst.basePath+'lg/loginCtrl/userinfo';
	$.ajax({
		type : 'get',
		url : ur,
		dataType : 'json',
		success : function(data) {
			var user = null;
			if (data.result) {
				user = data.userBean;
				initJSConstUserBean(user);

				initVideoPlayerType();

				showDqyh1();
			}else{
				alert("获取用户信息失败，请重新登录");
				window.location.href=ssoPage();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}

/**
 * 初始化视频播放器类型
 */
function initVideoPlayerType() {
	var url = jsConst.basePath + "xtcs/queryCszByCsbm.json";
	var params = {};
	params["csbm"] = "spbfcs";

	var callBack = function(data) {
		if (data.success) {
			if(data.obj) {
				jsConst.VIDEO_PLAYER_TYPE = data.obj;
			}
		} else {
			console.error("视频播放器类型查询失败：" + data.msg);
		}
	};
	ajaxTodo(url, params, callBack);
}

/**
 * 自定义异常
 */
function CustomException(message) {
	this.message = message;
}
