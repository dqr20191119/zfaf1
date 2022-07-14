(function (global) {
	
	global.jsConst = {
		'STATIC_RESOURCE_ADDR': '/resource',	// 静态资源服务器地址
		'ROOT_ORGA_CODE': '',	// 当前地图显示机构号
		'ROOT_ORGA_NAME': '',	// 当前地图显示机构名称
		//用户信息
		'PRISON_ORG_CODE': '',	// 监狱机构号（省局通过地图进入各个监狱时存放该监狱机构号）
		'PRISON_MAP_MODEL': '',	// 监狱地图模式（省局登录是决定各监狱地图模式的标识）
		'CUS_NUMBER': '',		// 机构编号（同ORG_CODE，省局用户此字段为空）
		'ORG_CODE': '',			// 机构编号（同CUS_NUMBER）
		'CUS_NAME': '',			// 机构名称
		'USER_ID': '',			// 用户编号
		'USER_NAME': '',		// 用户名
		'REAL_NAME': '',		// 用户真实姓名
		'ROLE_LIST': null,		// 角色集合
		'ROLE_ID': '',			// 角色编号集，用,分隔（已作废）
		'ROLE_NAME': '',		// 角色名称集，用,分隔（已作废）
		'POLICE_CODE': '', 		// 警员编号
		'DEPARTMENT_ID': '',	// 部门ID
		'DEPARTMENT_NAME': '',	// 部门名称
		'DEPARTMENT_KEYS': '',	// 部门编号集合
		'ORG_CLASS_KEY': '',	// 组织类别ID
		'USER_LEVEL': -1,		// 用户等级
		'SPECIAL_POLICE': '0',	// 特警队员：0否、1是

		
		'LOGIN_USER_KEY': null,	// 登录用户标识：用于关联用户（民警）信息
		'playFlash': false,		// 是否播放flash
		'mapsLogin': false,		// 地图登录
		'MAP_MODEL': true,		// 地图模式：true同时使用二维、三维地图，false只使用二维地图
		'SDK_2D': null,			// 二维地图SDK类型：arcgis
		'SDK_3D': null,			// 三维地图SDK类型：wjh

		'basePath': '',						// 项目根路径
		'prisonConfig': {},		// 地图配置
		'map3dPath': '10.58.6.105:8080',	// 三维地图服务地址
		'arcgisPath': '10.58.6.219:8080/Prison/component/arcgis/3.9/',				// weijinghang地图JS地址
		'arcgisServiceBasePath': 'http://10.58.223.1:6080/arcgis/rest/services/',	// weijinghang地图服务根地址
		'arcgisServicePath': '',			// weijinghang 地图服务地址
		'SYS_AUTO_CREATE': '系统自动生成',	// 描述
		'RELAY_HINT': 5,					//接警延迟提醒时间
		'RELAY_UP_LEVEL': 180,				//允许接警时间
		'PRISON_GOOUT_H': 0,				//罪犯外出就医人数
		'VIDEO_CLIENT_CONNECT_TYPE': 1,		// 视频客户端连接类型：0直连、1流媒体(默认)
		'VIDEO_STREAM_TYPE': 1,				// 视频码流类型：0主码流、1辅码流(默认)
		'SOCKET_URL': '',					// WebSocket地址
		'WEBSOCKET_SERVICE_URL': 'LoginService',	// WebSocket服务地址
		'LOAD_DEVICE_TYPE':'2',	//摄像头加载方式：1，加载模型 2，加载图片 add by linhe 2018-1-16
		'MAP_TYPE':'0'	,		//地图类型：0、仅二维；1、仅三维；3、三维二维同时使用，且2D附在3D上面  add by zk 2018-03-16
		'ALARM_INFO': null ,    //临时使用，三维地图切换参数
		'ALARM_ID': null,     //临时使用，报警处置界面进入时存放报警记录id
		'VIDEO_PLAYER_TYPE': '0'	// 视频播放器类型(0:视频客户端; 1:视频插件.)
	};
}(this));
function initJsConst(remoteIp){
	
};
