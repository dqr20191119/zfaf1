var cameraCtrl = null;
var isInitFormStyle = false;
var isClickOpenVideo = '0';		// 是否单击打开视频，否则双击：0否、1是

/*
 * 摄像机的图标映射
 */
var cameraIconMap = {
	'1': {
		'0': 'camera_spherical_0',
		'1': 'camera_spherical_1',
		'2': 'camera_spherical_2'
	},
	'2': {
		'0': 'camera_gun_0',
		'1': 'camera_gun_1',
		'2': 'camera_gun_2'
	},
	'3': {
		'0': 'camera_hemisphere_0',
		'1': 'camera_hemisphere_1',
		'2': 'camera_hemisphere_2'
	}
};

/*
 * 摄像机状态描述
 */
var cameraSttsMap = {
	'0': '',
	'1': '【故障】',
	'2': '【离线】'
};

/*
 * 摄像机状态样式
 */
var cameraSttsCssMap = {
	'0': '',
	'1': {color:'rgb(235,0,0)'},
	'2': {color:'rgb(180,180,180)'}
};

/*
 * 摄像机状态对应颜色
 */
var cameraColorMap = {
	'0': '',
	'1': 'rgb(235,0,0)',
	'2': 'rgb(180,180,180)'
};

//区域摄像头树查询
function regionCameraSearch(e, data) {
	//回车时触发
	if(e.keyCode == 13)      
    {  
		$("#regionCameraTree").tree("filterNodesByParam", {
			name : data.value
		});  
    }  
}
//公共群组摄像头树查询
function groupCommonSearch(e, data) {
	//回车时触发
	if(e.keyCode == 13)      
    {
		$("#groupCommonTree").tree("filterNodesByParam", {
			name : data.value
		});
    }
}
//自定义群组摄像头树查询
function groupDIYSearch(e, data) {
	//回车时触发
	if(e.keyCode == 13)      
    {
		$("#groupDIYTree").tree("filterNodesByParam", {
			name : data.value
		});
    }
}
//区域摄像头树展开折叠
function regionCameraTreeExpendAll(event, ui) {
	if ("展开" == ui.label) {
		$('#regionCameraTree').tree("expandAll", true);
		$("#" + ui.id).button('option', 'label', '折叠');
	} else {
		$("#" + ui.id).button('option', 'label', '展开');
		$('#regionCameraTree').tree("expandAll", false);
	}
}
//公共群组摄像头树展开折叠
function groupCommonTreeExpendAll(event, ui) {
	if ("展开" == ui.label) {
		$('#groupCommonTree').tree("expandAll", true);
		$("#" + ui.id).button('option', 'label', '折叠');
	} else {
		$("#" + ui.id).button('option', 'label', '展开');
		$('#groupCommonTree').tree("expandAll", false);
	}
}
//自定义群组摄像头树展开折叠
function groupDIYTreeExpendAll(event, ui) {
	if ("展开" == ui.label) {
		$('#groupDIYTree').tree("expandAll", true);
		$("#" + ui.id).button('option', 'label', '折叠');
	} else {
		$("#" + ui.id).button('option', 'label', '展开');
		$('#groupDIYTree').tree("expandAll", false);
	}
}

/**
 * 初始化视频画面布局样式
 */
function initLayout(){
	var number = ['','一','二','三','四','五','六','七','八','九','十','十一','十二','十三','十四','十五','十六'];
	var temp = $('#template').html();
	var html = null;

	$('#divLayout div.inner-panel').empty();
	$('#template').remove();

	for(var i = 1; i <= 16; i++){
		html = temp.replace('{index}', i);
		html = html.replace('{index}', i);
		html = html.replace('{number}', number[i]);
		$('#divLayout div.inner-panel').append(html);
	}
	$('div.my-layout-panel:eq(' + (parseInt(videoClient.curLayout)-1) + ')').addClass('my-layout-panel-hover');
}
/**
 * 设置摄像机显示的画面布局
 */
function setLayout(layout){
	layout = parseInt(layout);

	$('div.my-layout-panel-hover').removeClass('my-layout-panel-hover');
	$('div.my-layout-panel:eq(' + (layout-1) + ')').addClass('my-layout-panel-hover');

	// 查询参数配置表中的[视频播放参数]
	if(jsConst.VIDEO_PLAYER_TYPE == '1') {// 视频插件方式窗口布局
		// 视频插件播放器窗口显示
		if (jsConst.MAP_TYPE == '0') {// 二维

		} else {// 三维
			$("div[id='map3d_container']").hide();
			$("div[id='signx_show']").show();
		}
		// 布局初始化
		videoPlugin.setLayout($("#signx_show"), layout);
	}else if(jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE){// 视频客户端方式窗口布局
		// 布局初始化
		videoClient.setLayout( layout );
	}
	toggleLayout('#btnLayout');
}
/**
 * 打开/隐藏布局窗口
 */
function toggleLayout(obj){
	$(obj).toggleClass('layout-set-btn-hover');
	$(obj).toggleClass('layout-set-btn-click');
	$('#divLayout').toggleClass('div-layout-open');
	setClose( $('#divLayout').hasClass('div-layout-open') );
}
/**
 * 关闭所有布局、设置面板
 */
var isClose = false;// 标识
function setClose(bl){
	setTimeout(function(){
		isClose = bl;
	}, 80);
}
/*-----------------------------假的videoClient by zk start-----------------------------*/

/*function VideoClient () {
}

VideoClient.prototype.curLayout = 0;// 当前画面布局(默认0没有)


VideoClient.prototype.setLayout = function(layout){
	if (layout < 1) layout = 1;
	if (layout > 16) layout = 16;
	
	videoClient.curLayout = layout
};

var videoClient = new VideoClient();*/
/*-----------------------------假的videoClient by zk end-----------------------------*/

/** ******************************************************************* **
 *  ********************* 摄像机列表树翻页控制对象 *********************
 ** ******************************************************************* **/ 
function CameraCtrl(){
	this.list = [];
	this.length = 0;
	this.beginIndex = 0;
	this.endIndex = 0;
	this.selectedIndex = -1;
	this.curCameras = [];
	this.autoTimerId = null;// 自动翻页的定时器ID
	this.isAuto = false;
}

/**
 * 初始化摄像机列表控制
 */
CameraCtrl.prototype.init = function (cameras) {
	console.log("-------------init------------");
	console.log(cameras);
	this.list = cameras;
	this.length = cameras.length;
	this.beginIndex = 0;
	this.endIndex = 0;
	this.selectedIndex = -1;
	this.curCameras = [];
};

/**
 * 设置布局
 * @param def
 * @returns {Number}
 */
CameraCtrl.prototype.getLayout = function (def) {
	var curLayout = 0;
	if(jsConst.VIDEO_PLAYER_TYPE == '0') {
		curLayout = parseInt(videoClient.curLayout);
	} else if(jsConst.VIDEO_PLAYER_TYPE == '1') {
		curLayout = parseInt(videoPlugin.curLayout);
	}
	return curLayout || def || 4;
};

/**
 * 设置布局
 * @param def
 * @returns {Number}
 */
CameraCtrl.prototype.getNum = function () {
	var layout = this.getLayout();
	return layout > this.length ? this.length : layout;
};
/*
 * 初始化设置
 */
function initSet(){
	$('ul.sys-set >li').click(function(){
		$(this).find('span.chkbox').toggleClass('checked');
		switch ($(this).attr('eventType')) {
			case '1': setFormStyle(); break;
			case '2': useDefAutoTime(); break;
			case '3': cameraClickAlert(); break;
		}
		setConfigs();
	});
}
/*
 * 设置窗口属性
 */
function setFormStyle(){
	//alert('topMost:'+$('#topMost >span.chkbox').hasClass('checked'));
	//alert('showFormTitle:'+ $('#showFormTitle >span.chkbox').hasClass('checked'));
	//alert('showVideoTitle:'+$('#showVideoTitle >span.chkbox').hasClass('checked'));
	videoClient.setFormStyle({
			'topMost': $('#topMost >span.chkbox').hasClass('checked'),
			'showFormTitle': $('#showFormTitle >span.chkbox').hasClass('checked'),
			'showVideoTitle': $('#showVideoTitle >span.chkbox').hasClass('checked')
	}, function(data){
		if (data && data.success) {
			isInitFormStyle = true;
		}
	});
}

/*
 * 使用固定翻页时间间隔
 */
function useDefAutoTime(){
	if( $('#useDefAutoTime span.chkbox').hasClass('checked') ){
		$('#setDefAutoTime').removeClass('disabled');
		$('#txtDefAutoTime').removeAttr('disabled');
	} else {
		$('#setDefAutoTime').addClass('disabled');
		$('#txtDefAutoTime').attr('disabled', 'disabled');
	}
}
/*
 * 摄像机单/双击提示
 */
function cameraClickAlert () {
	if (isChecked('cameraOnClick', 1, 0)) {
		_alert('操作提示：<br>单击【左键】打开视频! <br>单击【右键】选择视频!');
	} else {
		_alert('操作提示：<br>【双击】左键打开视频! <br>【单击】左键选择视频!');
	}
}
/*
 * 获取CookieName
 */
function getCookieName(){
	return 'videoPlan_' + jsConst.ORG_CODE + '_' + jsConst.USER_ID;
}

/*
 * 获取Cookie值
 */
var list = null;
function getConfigs(){
	try {
		var cookieValue = window.top.getCookie( getCookieName() );
		if( cookieValue && cookieValue.length > 0 ){
			list = cookieValue.split('|');
			setChecked('useDefAutoTime', list[0]);
			setChecked('autoSetLayout', list[2]);
			setChecked('cameraOnClick', list[3]);
			
			//自动翻页时间最少为3秒 	add by zk
			if(list[1]<3){
				list[1]=3;
			}
			$('#txtDefAutoTime').val(list[1]);
			useDefAutoTime();		
			isClickOpenVideo = isChecked('cameraOnClick', 1, 0);		
		}

		// 视频客户端配置
		list = videoClient.getConfig();
		setChecked('topMost', list[0]);
		setChecked('showFormTitle', list[1]);
		setChecked('showVideoTitle', list[2]);	
	} catch (e) {
		console.error('getConfigs.error:', e);
	}
}

/*
 * 设置Cookie值
 */
function setConfigs(){
	var topMost = isChecked('topMost', 1, 0);
	var showFormTitle = isChecked('showFormTitle', 1, 0);
	var showVideoTitle = isChecked('showVideoTitle', 1, 0);
	var useDefAutoTime = isChecked('useDefAutoTime', 1, 0);
	var txtDefAutoTime = $('#txtDefAutoTime').val();
	var autoSetLayout = isChecked('autoSetLayout', 1, 0);
	var cameraOnClick = isChecked('cameraOnClick', 1, 0);	
	var cookieName = getCookieName();
	//自动翻页时间最少为3秒 	add by zk
	if(txtDefAutoTime<3){
		txtDefAutoTime=3;
	}
	var cookieValue = useDefAutoTime + '|' + txtDefAutoTime + '|' + autoSetLayout+'|'+cameraOnClick;
	var expires = 30*24*60*60*1000;

	// 设置
	isClickOpenVideo = cameraOnClick;
	window.top.setCookie( cookieName, cookieValue, expires);
	videoClient.setConfig(topMost, showFormTitle, showVideoTitle);
}

/*
 * 是否存在样式
 */
function isChecked (id, arg1, arg2) {
	if( $('#' + id + ' >span.chkbox').hasClass('checked') ){
		return arg1 == undefined ? true : arg1;
	} else {
		return arg2 == undefined ? false : arg2;
	}
}

/*
 * 设置复选框
 */
function setChecked(id, checked){
	if (checked == "1") {
		$('#' + id + ' >span.chkbox').addClass('checked');
	} else {
		$('#' + id + ' >span.chkbox').removeClass('checked');
	}
}


/*
 * 首页
 */
CameraCtrl.prototype.first = function(){
	var num = this.getNum();
	this.endIndex = num;
	this.beginIndex = 0;
	this.excute();
	stopAuto();
	hideAuto();
}

/*
 * 上一页
 */
CameraCtrl.prototype.last = function(){
	var num = this.getNum();
	this.endIndex = this.beginIndex;
	this.beginIndex -= num;

	if( this.endIndex < 1 ){
		this.beginIndex += this.length;
		this.endIndex += this.length;
	}

	this.excute();
};

/*
 * 自动翻页
 */
CameraCtrl.prototype.auto = function(sleep, time, handle){
	if( time > 0 ){
		handle.call(this, time--);
		cameraCtrl.autoTimerId = setTimeout(cameraCtrl.auto, 1000, sleep, time, handle);
	} else {
		cameraCtrl.next();
		cameraCtrl.auto(sleep, sleep, handle);
	}
};

/*
 * 停止自动翻页
 */
CameraCtrl.prototype.stop = function(){
	if( this.autoTimerId ){
		clearTimeout(this.autoTimerId);
		this.autoTimerId = null;
		this.isAuto = false;
		return true;
	} else {
		return false;
	}
};

/*
 * 下一页
 */
CameraCtrl.prototype.next = function(){
	var num = this.getNum();
	this.beginIndex = this.endIndex;
	this.endIndex += num;

	if( this.beginIndex >= this.length ){
		this.beginIndex -= this.length;
		this.endIndex -= this.length;
	}

	this.excute();
};

/*
 * 尾页
 */
CameraCtrl.prototype.end = function(){
	var num = this.getNum();
	this.endIndex = this.list.length;
	this.beginIndex = this.endIndex - num;
	this.excute();
	stopAuto();
	hideAuto();
}
/*-------------------------update by zk start-------------------------*/
//摄像头格式化
function cameraFormatter(e,node){
	console.log(node);
	if(node.node_type=="camera"){
		var statu = node.CBD_STTS_INDC;
		var type = node.CBD_TYPE_INDC;
		
		type = cameraIconMap[type];
		node.iconSkin = (type && type[statu] || '');
		node.name = node.name + (cameraSttsMap[statu] || '');
	}
	return node.name;	
}
/*
 * 翻页处理
 */
CameraCtrl.prototype.excute = function(){
	//_showWaiting('正在打开视频监控...');
	var time1 = (new Date()).getTime();
	var cameraList = [];
	var pitchValues = [];
	var node = null;

	// 清除样式
	/*while (this.curCameras.length) {
		__toggleVideo(this.curCameras.shift(), false);
	}*/
	$('#regionCameraTree').tree("cancelSelectedNode");

	// 获取下一组摄像机
	for(var i = this.beginIndex; i < this.endIndex; i++){
		if( i >= this.length ){
			node = this.list[ i - this.length ];
		} else if ( i < 0 ){
			node = this.list[ i + this.length ];
		} else {
			node = this.list[i];
		}

		this.curCameras.push(node);
		//cameraList.push(__getCameraId(node));
		cameraList.push(node.id);
		pitchValues.push({'value': node.id, 'pitch': i == this.beginIndex,'tId':node.tId});
		//__toggleVideo(node, true);	//update by zk
		$('#regionCameraTree').tree("selectNode",node,true);
	}
	// 定位	
	//__selectVideo(this.curCameras[0]);
	//cameraZTree.pitch('id', pitchValues, 45, 300);

	if(jsConst.VIDEO_PLAYER_TYPE == '0') {
		setFormStyle();

		videoClient.playVideoHandle({
			'subType': this.isAuto ? 2 : 1,
			'layout': this.getLayout(),
			'data': cameraList,
			'callback': function (data) {
				_hideWaiting();
			}
		});
	} else if(jsConst.VIDEO_PLAYER_TYPE == '1') {
		// 视频插件播放器容器显示
		if (jsConst.MAP_TYPE == '0') {// 二维

		} else {// 三维
			$("div[id='map3d_container']").hide();
			$("div[id='signx_show']").show();
		}
		videoPlugin.multiVideoPlayHandle({
			'container': $("#signx_show"),
			'subType': this.isAuto ? 2 : 1,
			'layout': this.getLayout(),
			'data': cameraList,
			'callback': function (data) {
				_hideWaiting();
				console.log("rightVideoPlan.js excute data = " + JSON.stringify(data));
			}
		});
	}
	$(".mscroll").mCustomScrollbar("scrollTo",$("#"+pitchValues[0].tId))//选中节点后滚动条定位位置；
};
/*-------------------------update by zk start-------------------------*/
/*
 * 查询匹配
 */
CameraCtrl.prototype.findOf = function (handle) {
	for (var i = 0, I = this.list.length; i < I; i++) {
		if (handle(i, this.list[i])) {
			return true;
		} 
	}
}

/*
 * 上一页
 */
function lastPage(){
	if( cameraCtrl.selectedIndex > -1 ){
		_confirm('是否从选中位置开始翻页？', function(result){
			if( result ){
				cameraCtrl.beginIndex = cameraCtrl.selectedIndex + 1;
				cameraCtrl.selectedIndex = -1;
			}
			cameraCtrl.last();
		});
	} else {
		cameraCtrl.last();
	}
	stopAuto();
	hideAuto();
}

/*
 * 下一页
 */
function nextPage(){
	if( cameraCtrl.selectedIndex > -1 ){
		_confirm('是否从选中位置开始翻页？', function(result){
			if( result ){
				cameraCtrl.endIndex = cameraCtrl.selectedIndex;
				cameraCtrl.selectedIndex = -1;
			}
			cameraCtrl.next();
		});
	} else {
		cameraCtrl.next();
	}
	stopAuto();
	hideAuto();
}

/*
 * 自定义时间
 */
function customWaitTime(time){
	try {
		if( time.length > 0 && parseInt(time) > 2){
			$('#btnCustomWaitTime').html(time + '秒')
				.unbind('click')
					.bind('click', function(){
						startAuto(time);
					});
		} else {
			$('#btnCustomWaitTime')
				.html('自定义')
					.unbind('click')
						.bind('click', function(){
							_alert('请输入自动翻页的时间间隔（最低3秒）');
						});
		}
	} catch(e){
		_alert('输入时间格式错误!','warning');
	}
}
/*
 * 收缩面板
 */
function expandCollapse(){
	$('.vedio-panel .panel-toolbar').toggle();
	$('.sperator').toggle();
	$('#divWaitTime').toggle();
}

/*
 * 开始自动翻页
 */
function startAuto (sleep) {
	var cameraNum = cameraCtrl.length;
	var layout = cameraCtrl.getLayout();
	if (cameraNum > layout) {
		if ($('#useDefAutoTime span.chkbox').hasClass('checked')) {
			sleep = parseInt( $('#txtDefAutoTime').val() );
		}
		if (sleep) {
			//如果设置了自动分页时间，则既不展开也不收缩		
			if ($('#useDefAutoTime span.chkbox').hasClass('checked')) {
				
			}else{
				//收缩
				expandCollapse();
			}
			
			$('#txtDefAutoTime').val(sleep);
			$('#btnAuto').unbind('click').bind('click',stopAuto)
				.addClass('hoving')
					.attr('title', '启动自动翻页');

			cameraCtrl.isAuto = true;
			nextPage();
			cameraCtrl.auto(sleep, sleep, function(time){
				$('#btnAuto').addClass('hoving').button("option","label",time + '秒');
			});
		} else {
			//$('#btnAuto').toggleClass('hoving');
			expandCollapse();			
		}
	} else {
		if (cameraNum < layout)
			_warn('当前摄像机数量小于'+layout+'画面无法进行轮循!',"warning","auto",$("#cameraGroup"));
		else if (cameraNum == layout)
			_warn('当前摄像机数量等于'+layout+'画面无法进行轮循!',"warning","auto",$("#cameraGroup"));
	}
}

/*
 * 停止自动翻页
 */
function stopAuto(){
	if (cameraCtrl.stop()) {
		$('#btnAuto').unbind('click').bind('click',function(){ startAuto(); })
			.attr('title', '启动自动翻页')
				.button("option","label","自动");
		hideAuto();
	}
}

/*
 * 隐藏自动翻页
 */
function hideAuto(){
	$('#btnAuto').removeClass('hoving');
	//$('#divWaitTime').hide();
}

function _confirm(msg, callback_p){
	$.confirm( {       
		message:msg,                            
		iframePanel:true,                
		callback: callback_p,
		position: {
            my: "right top",   //此为右上角
            at: "right top",
            of: window
        }
	});
	//$.messager.confirm('确认', msg, callback);
	$('div.panel.window, div.window-shadow').css({
		'left': 8,
		'width': 280
	});
	$('div.window-header').css('width', '280px');		
	$('div.window-body').css('width', '258px');
}

/*function _alert(msg, icon){
	$.messager.alert(msg,'提示',icon || 'info');
	$('div.panel.window, div.window-shadow').css({
		'left': 8,
		'width': 280
	});
	$('div.window-header').css('width', '280px');		
	$('div.window-body').css('width', '258px');
}*/
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
function _warn(msg,type,width,obj){
	var type = type || "info",
		width = width || "auto",
		obj = obj || window;
	 $.messageToast({
	        message:msg,
	        type:type,
	        width:width,
	        position:{
	        	  my: "center top+10",   //与上述类似，此为右上角
	              at: "center top",
	              of: obj
	        }
	    }); 
}
function _showWaiting (msg) {
	hzMask.show(msg);
}

function _hideWaiting () {
	hzMask.hide();
}

/*----------------update by zk start--------------------*/
/*
 * 设置翻页开始的索引
 */
function __setIndex (zNode) {
	cameraCtrl.findOf(function (i, node) {
		if (node.id == zNode.id) {
			cameraCtrl.selectedIndex = i;
			return true;
		}
	});
}
/*
 * 新的打开视频逻辑
 */
function __openVideo(treeNode) {
	var dvcId = treeNode.id;
	// console.log("rightVideoPlan.js __openVideo treeNode = " + JSON.stringify(treeNode));
	console.log("rightVideoPlan.js __openVideo dvcId = " + dvcId);
	if (dvcId) {
		if(jsConst.VIDEO_PLAYER_TYPE=='1'){
			// 视频插件播放器容器显示
			if (jsConst.MAP_TYPE == '0') {// 二维

			} else {// 三维
				$("div[id='map3d_container']").hide();
				$("div[id='signx_show']").show();
			}
			// 下一个播放器index
			var index = videoPlugin.getNextIndexOfVideoWindow();

			// 播放选中的摄像头实时视频
			videoPlugin.multiVideoPlayHandle({
				'container': $("#signx_show"),
				'subType': 1,
				'layout': videoPlugin.curLayout,
				'data': [{
					'index': index,
					'cameraId': dvcId
				}],
				'callback': function (data) {
					console.log("rightVideoPlan.js __openVideo data = " + JSON.stringify(data));
				}
			});
		}else if(jsConst.VIDEO_PLAYER_TYPE=='0' || !jsConst.VIDEO_PLAYER_TYPE) {
			videoClient.getIndex(function (data) {
				if (data && data.success) {
					var index = data.obj;
					//index为-1说明视频窗口关闭,此时设置为0,即第一个index---add by zk
					if(index == -1){
						index=0;
					}

					// 还原上一个窗口索引位置的摄像机
					//lihh
					//__toggleVideo(videoClient.curPlayCameraList[index], false);

					// 设置窗口属性
					setFormStyle();

					// 打开视频
					videoClient.playVideoHandle({
						'subType': 1,
						'layout': videoClient.curLayout,
						'data': [{
							'index': index,
							'cameraId': dvcId
						}],
						'callback': function (data) {
							//__toggleVideo(treeNode, true);
						}
					});
					cameraCtrl.selectedIndex = -1;
				} else {
					alert(data.msg);
				}
			});
		}
	}
}
function openGroup(node){
	var cusNumber = node.cusNumber;
	var orgCode = node.cusNumber;
	var grpId = node.id;
	var isAutoLayout = $('#autoSetLayout span.chkbox').hasClass('checked');
	
	//若不是自动布局先设置布局add by zk
	if(!isAutoLayout){
		if(videoClient.curLayout==0){
			_alert('请先设置布局!');
			return false;
		}
	}	
	
	var extend = {
		'isAutoLayout': isAutoLayout,// 是否自动布局
		'curLayout': videoClient.curLayout// 当前布局
	};
	//alert("cusNumber = " + cusNumber);
	//alert("extend = " + JSON.stringify(extend));
	//alert("grpId = " + grpId);
	//alert("name = " + node.name);
	setFormStyle();
	videoClient.playGroupHandle( 
			cusNumber || window.top.jsConst.CUS_NUMBER,
			grpId, 
			node.name, 
			extend, 
			function(data){
				if( data.success ){
					var list = data.obj;
					//console.log("openGroup list = " + JSON.stringify(list));
					if( list.length == 0 ){
						_alert('本级预案未关联任何摄像机!');
					} else {
						var showConfirm = isAutoLayout 
										? list.length > videoClient.maxLayout 
										: list.length > videoClient.curLayout;

						if( showConfirm ){
							if(orgCode != 1100){
								_confirm('当前预案视频数量过多，是否进行视频轮巡?', function(result){
									if( result ){
										var params={
												"grpId":grpId
										}
										//window.top.openRight('page/cds/video/rightVideoFor.jsp?grpId=' + grpId);
										window.top.openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
									}
								});
							}else{
								_confirm('当前预案视频数量过多，是否进行视频轮巡?', function(result){
									if( result ){
									
										var params={
												"grpId":grpId,
												"cusNumber":cusNumber
										}
										
										//window.top.openRight('page/cds/video/rightVideoFor.jsp?grpId=' + grpId + "&cusNumber=" + cusNumber);
										window.top.openViewToRightAddParam('rightside/spjk/rightVideoFor',params);
									}
								});
							}
						}
					}
				} else {
					_alert(data.msg, 'warning');
				}
	});
}
function openGroup_bak(node){
	var cusNumber = node.attributes.ORGID;
	var orgCode = node.orgCode;
	var grpId = node.id;
	var isAutoLayout = $('#autoSetLayout span.chkbox').hasClass('checked');
	var extend = {
		'isAutoLayout': isAutoLayout,// 是否自动布局
		'curLayout': videoClient.curLayout// 当前布局
	};

	setFormStyle();
	videoClient.playGroupHandle( 
			cusNumber || window.top.jsConst.CUS_NUMBER,
			grpId, 
			node.text, 
			extend, 
			function(data){
				if( data.success ){
					var list = data.obj;
					if( list.length == 0 ){
						_alert('本级预案未关联任何摄像机!');
					} else {
						var showConfirm = isAutoLayout 
										? list.length > videoClient.maxLayout 
										: list.length > videoClient.curLayout;

						if( showConfirm ){
							if(orgCode != 1100){
								_confirm('当前预案视频数量过多，是否进行视频轮巡?', function(result){
									if( result ){
										window.top.openRight('page/cds/video/rightVideoFor.jsp?grpId=' + grpId);
									}
								});
							}else{
								_confirm('当前预案视频数量过多，是否进行视频轮巡?', function(result){
									if( result ){
										window.top.openRight('page/cds/video/rightVideoFor.jsp?grpId=' + grpId + "&cusNumber=" + cusNumber);
									}
								});
							}
						}
					}
				} else {
					_alert(data.msg, 'warning');
				}
	});
}

/*
 * 初始化摄像机树
 */
function initCameraTree (cameraList) {
	console.log("initCameraTree");
	// 初始化摄像机控制对象
	cameraCtrl = new CameraCtrl();
	cameraCtrl.init(cameraList);
}
/**
 * 初始化菜单、过渡效果设置等按钮事件
 */
function initBtnEvent(){
	// 自动翻页事件
	$('#btnAuto').bind('click', function(){ startAuto(); }).attr('title', '启动自动翻页');
	$('#btnCustomWaitTime').bind('click', function(){
			_warn('请输入自动翻页的时间间隔(最低3秒)',"warning",280,$("#cameraGroup"));
	});
	$(".sperator-icon").bind("click",function(){
		expandCollapse();
	})
	// 自动翻页默认时间
	$('#txtDefAutoTime').bind({
		'keydown': function(){
			var code = event.keyCode;
			var timerId = $(this).attr('timerid');
			if( timerId ){
				clearTimeout(timerId);
			}
			if( !(code > 47 && code < 58) && !(code > 95 && code < 106) && code != 8 ){
				return false;
			}
		},
		'keyup': function(){
			var val = this.value;
			var obj = this;
			var timerId = setTimeout(function(){
				if( val.length > 0 ){
					if( parseInt(val) < 3 ){
						obj.value = 3;
						_alert('固定时间间隔最低3秒!');
					} else {
						setConfigs();
					}
				} else {
					obj.value = 3;
					_alert('固定时间间隔不能为空，且最低3秒!');
				}
			}, 1000);
			$(this).attr('timerid', timerId);
		}
	});

	// 自动翻页自定义时间
	$('#txtCustomWaitTime').bind({
		'keydown': function(){
			var code = event.keyCode;
			if( !(code > 47 && code < 58) && !(code > 95 && code < 106) && code != 8 ){
				return false;
			}
		},
		'keyup': function(){
			customWaitTime(this.value);
		},
		'focus': function(){
			if( $(this).hasClass('remark') ){
				$(this).removeClass('remark').val('');
			}
		},
		/*可以用placeholder属性代替
		'blur': function(){
			if( this.value.length == 0 ){
				$(this).addClass('remark').val('自定义时间（最低3秒）');
			}
		}*/
	});
}
/*----------------update by zk end--------------------*/