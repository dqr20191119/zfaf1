
/*
 * 打开/隐藏布局窗口
 */
var layoutBtn = null;
function toggleLayout(obj){
	layoutBtn = obj;
	$(obj).toggleClass('layout-set-btn-hover');
	$(obj).toggleClass('layout-set-btn-click');
	$('#divLayout').toggleClass('div-layout-open');
	setClose( $('#divLayout').hasClass('div-layout-open') );
}

/*
 * 关闭所有布局、设置面板
 */
var isClose = false;// 标识
function setClose(bl){
	setTimeout(function(){
		isClose = bl;
	}, 80);
}
/*
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
var deskLayout = null;
function setLayout_hf(layout){
	layout = parseInt(layout);
	deskLayout = layout;
	if(reViewMode == 0 || reViewMode == 2){
		loadLayoutTable();
	}
	
	$('div.my-layout-panel-hover').removeClass('my-layout-panel-hover');
	$('div.my-layout-panel:eq(' + (layout-1) + ')').addClass('my-layout-panel-hover');

	toggleLayout(layoutBtn);
	if(jsConst.VIDEO_PLAYER_TYPE=='1') {
		// 视频插件播放器显示
		if (jsConst.MAP_TYPE == '0') {// 二维

		} else {// 三维
			$("div[id='map3d_container']").hide();
			$("div[id='signx_show']").show();
		}
		// 布局初始化
		videoPlugin.setLayout($("#signx_show"), layout);
	} else if(jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE) {
		videoClient.setLayout( layout );
	}
}
/**
 * 加载布局画面列表
 */
function loadLayoutTable(){
	var table = $(".layout_table table");
	table.empty();
	for(var i=0;i<deskLayout;i++){
		var tr = $("<tr></tr>");
		tr.append("<td width='70'>" + (i+1) + "画面</td>");
		tr.append("<td width='200' id='date_" + (i+1) + "'></td>");
		table.append(tr);
	}
}

function _validate(stime, etime){
	if( stime.length == 0 ){
		_alert('请输入回放开始时间！');
		return false;
	}

	if( etime.length == 0 ){
		_alert('请输入回放结束时间！');
		return false;
	}

	return true;
}
/**
 * 格式化当前时间
 */
function formatterDate(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
			+ (date.getMonth() + 1);
	var hours = parseInt(date.getHours());
	if(hours < 10){
		hours = "0" + hours;
	}
	var seconds = parseInt(date.getSeconds());
	if(seconds < 10){
		seconds = "0" + seconds;
	}
	var minutes = parseInt(date.getMinutes());
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	return date.getFullYear() + '-' + month + '-' + day + " " + hours + ":" + minutes + ":" + seconds;
}

/*function _alert(msg, icon){
	$.messager.alert( msg,'提示', icon || 'info');
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

/**
 * 根据回放模式加载回放摄像机
 */
var index_2 = 0;
//手风琴激活面板
var reViewMode=0;
function loadBackHandle_camera(event, id, node){
	console.log("playbackVideo.js loadBackHandle_camera node = " + JSON.stringify(node));
	if("camera"!=node.node_type){
		return;
	}
	
	var cameraId=node.id;
	var cameraName=node.name;
	console.log(cameraId+":"+cameraName);
	reViewMode=$("#accordion_sphf").accordion("option","active");
	
	if(deskLayout == null){
		_alert('请选择布局画面!');
		return;
	}

	//var stime = $("#startTime0").datepicker("getValue");
	//var etime = $("#endTime0").datepicker("getValue");
    var stime = $("#startTime0").val();
    var etime = $("#endTime0").val();
    console.log("startTime0="+stime+"------endTime0"+etime);
	var index = 0;
	var videoList = new Array();
	var video = null;
	var curLayout = 0;

	if(jsConst.VIDEO_PLAYER_TYPE == '0') {
		curLayout = videoClient.curLayout;
	} else if (jsConst.VIDEO_PLAYER_TYPE == '1') {
		// 视频插件回放
		curLayout = videoPlugin.curLayout;
	}
	var layout = {
		'layout': deskLayout,
		'last': curLayout
	};
	if(index_2 == deskLayout){
		index_2 = 0;
	}
	if(reViewMode == 0){//单点多时段
		if( !_validate(stime, etime) ){
			return false;
		}
		//selectedStyle(tId);
		if( deskLayout == 1 ){
			$(".layout_table table #date_" + (index+1)).html(stime.split(" ")[1] + " ~ " + etime.split(" ")[1]);
			videoList.push({
				index : index,
				cameraId : cameraId,
				cameraName: cameraName,
				startTime : stime,
				endTime : etime
			});
		} else {
			stime = Date.parse(stime.replace(/-/g,"/"));
			etime = Date.parse(etime.replace(/-/g,"/"));

			var split_time = parseInt( ( etime - stime ) / deskLayout );
			var startTime = 0;
			var endTime = 0;

			for(; index < deskLayout; index++){
				startTime = formatterDate( new Date(stime) );
				stime += split_time;
				if( index + 1 == deskLayout && stime != etime){
					stime = etime;
				}
				endTime = formatterDate( new Date(stime) );
				$(".layout_table table #date_" + (index+1)).html(startTime.split(" ")[1] + " ~ " + endTime.split(" ")[1]);
				video = {
					index : index,
					cameraId : cameraId,
					cameraName: cameraName,
					startTime : startTime,
					endTime : endTime
				};
				videoList.push(video);
			}
		}
		startPlayback(videoList, layout);
	}else if(reViewMode == 1){//多点同时段
		if( !_validate(stime, etime) ){
			return false;
		}
		//selectedStyle(tId);
		video = {
			index : index_2,
			cameraId : cameraId,
			cameraName: cameraName,
			startTime : stime,
			endTime : etime
		};
		videoList.push(video);
		index_2++;
		//alert(JSON.stringify(videoList));
		startPlayback(videoList, layout);
	}else if(reViewMode == 2){//多点不同时段
		//$("#dateWin").show();
		$("#dateWin").dialog({   
			 title : "回放时间",
			 modal : true,
			 buttons: [{
	          	text: "确定",
	          	click:function() {
	          		//selectedStyle(tId);
	          		//var startDate = $("#startDate").datepicker("getValue");
	          		//var endDate = $("#endDate").datepicker("getValue");
                    var startDate = $("#startDate").val();
                    var endDate = $("#endDate").val();
                    console.log("startDate="+startDate+"-----endDate="+endDate)
	          		$(".layout_table table #date_" + (index_2+1)).html(startDate.split(" ")[1] + " ~ " + endDate.split(" ")[1]);
	          		videoList.push({
	          			index : index_2,
	          			cameraId : cameraId,
	          			cameraName: cameraName,
	          			startTime : startDate,
	          			endTime : endDate
	          		});
	          		index_2++;
					startPlayback(videoList, layout);
	          		$("#dateWin").dialog("close");
	            }
	         }]
		});
		$("#dateWin").dialog("open");
	}
}

/**
 * 停止回放(方法未使用)
 */
function stopPlayback(){
	var temp = null;
	while (videoClient.playbackList.length) {
		temp = videoClient.playbackList.shift();
		videoClient.stopPlaybackMult(temp.index, function(data){
			var cameraId = videoClient.playbackBefore[temp.index];
			if( cameraId ){
				if (typeof cameraId == 'object') {
					cameraId = cameraId.cameraId;
				}
				videoClient.playVideo(
						[temp.index],
						[cameraId],
						videoClient.curLayout,
						function(data){
							
						}
				);
			}
		});
	}
}

/**
 * 开始回放
 * @param videoList
 * @param layout
 */
function startPlayback(videoList, layout) {
	if(jsConst.VIDEO_PLAYER_TYPE == '0') {
		videoClient.playbackHandle(videoList, layout);
	} else if(jsConst.VIDEO_PLAYER_TYPE == '1') {
		// 视频插件播放器容器显示
		if (jsConst.MAP_TYPE == '0') {// 二维

		} else {// 三维
			$("div[id='map3d_container']").hide();
			$("div[id='signx_show']").show();
		}
		// 播放回放视频
		videoPlugin.multiVideoPlaybackHandle({
			'container': $("div[id='signx_show']"),
			'subType': 1,
			'layout': layout,
			'data': videoList,
			'callback': function (data) {
			}
		});
	}
}
