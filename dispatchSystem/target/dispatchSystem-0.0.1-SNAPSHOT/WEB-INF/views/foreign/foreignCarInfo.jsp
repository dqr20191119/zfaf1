<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="height: 100%;">
	<cui:form id="formId_query">
		<table class="table">
			<tr>
				<th>车牌号码：</th>
				<td>
					<cui:input id="fcdCarLcnsIdnty" name="fcdCarLcnsIdnty" componentCls="form-control"></cui:input>
				</td>
				<td>
					<cui:input id="date" name="date" type="hidden"></cui:input>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_carInfoRecord" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_carInfoRecord_colModelDate">
		<cui:gridPager gridId="gridId_carInfoRecord" />
	</cui:grid>
</div>
 	<cui:dialog id="dialogId1" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
 
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

var frType_data = <%=CodeFacade.loadCode2Json("4.20.61")%>;// 车辆进出类型

$.parseDone(function() {
	var url = "${ctx}/foreign/getCarList";
	var params = {};
	
	// 车辆进出类型
	var frType = "${frType}";

	// 是否只显示当天（0：否；1：是。）
	var date = "${date}";
	
	if(date) {
		params["date"] = date;
		$("form[id='formId_query']").find("#date").textbox("setValue", date);
	}
	
	$('#gridId_carInfoRecord').grid('option', 'postData', params);
	$("#gridId_carInfoRecord").grid("reload", url);
});

var gridId_carInfoRecord_colModelDate = [{
	label : "id",
	name : "id",
	width : "70",
	hidden : true
}, {
	label : "车牌号码",
	name : "fcdCarLcnsIdnty",
	width : "120", 
	align : "center"
}, {
	label : "",
	name : "fcdRegisterId",
	width : "95", 
	align : "center",
	hidden : true
},  {
	label : "进出时间",
	align : "center",
	name : "frTime",
	width : "85"
}, {
	label : "操作",
	name : "operate",
	align : "center",
	width : "85",
	formatter : "operateFormatter"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_carInfoRecord').grid('option', 'postData', formData);
	$("#gridId_carInfoRecord").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("form[id='formId_query']").find("#fcdCarLcnsIdnty").textbox("setValue", "");
	// $("#formId_query").form("reset");
}

/**
 * 操作栏初始化
 */
function operateFormatter(cellValue, options, rowObject) {
	//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
/* 	var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"realTimeVideo('"+rowObject.frTime+"')\">实时画面</button>" ;
 */	var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"openImage('"+rowObject.fcdRegisterId+"')\">查看</button>" ;

	return result;
}

function openImage(id){
	$("#dialogId1").dialog({
		width : 840, //属性
		height : 440, //属性
		title : "查看",
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/foreign/openImage?id="+id
	});
	$("#dialogId1").dialog("open");
}

/**
 * 打开实时监控画面
 */
/*  var videoClient2 = window.top.videoClient;
	var deskLayout2 = null;
	var index_22 = 0; */
function realTimeVideo(frTime) {
	 $.ajax({
		type : 'post',
		url : jsConst.basePath + "groupManage/querByfrTime.json",
		data : {
			frTime:frTime,
			cusNumber:jsConst.CUS_NUMBER,
			grpName:"外来车辆监控"
		},
		dataType : 'json',
		success : function(dataca) {
			console.log(dataca);
			var jg = dataca.jg;
			var listca = dataca.sxj;
			 if(jg=="1"){
				ywjl(listca);
				
			}else{   
				dakai();
			
		 	 }	
			
			
		 },error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("" + JSON.stringify(XMLHttpRequest));
			console.log("" + textStatus);
			console.log("" + errorThrown);
		}
	});   

}
	var videoClient2 = window.top.videoClient;
	var deskLayout2 = null;
	var index_22 = 0;
	function ywjl(listca){
		console.log(listca);

		var datalength = listca.length;

		var videoList = new Array();
		var video = null;
		var curLayout;
		var layout;

		deskLayout2 = datalength;
		if(jsConst.VIDEO_PLAYER_TYPE == '0') {
			if(datalength>0){
				videoClient2.setLayout(datalength);
			}
			curLayout = videoClient2.curLayout;
		} else if (jsConst.VIDEO_PLAYER_TYPE == '1') {
			// 视频插件回放
			curLayout = videoPlugin.curLayout;
		}
		layout = {
			'layout': deskLayout2,
			'last': curLayout
		};

		for(var i=0; i<datalength; i++){
			var cameraId = listca[i].ID;
			var cameraName = listca[i].CBD_NAME;
			var stime_ = formatToDate(listca[i].STARTTIME);
			var etime_ = formatToDate(listca[i].ENDTIME);

			var stime = new Date(stime_.getTime() - 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");
			var etime = new Date(etime_.getTime() + 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");

			if( !_validate(stime, etime)){
				return false;
			}

			if(index_22 == deskLayout2){
				index_22 = 0;
			}
			video = {
				index : index_22,
				cameraId : cameraId,
				cameraName: cameraName,
				startTime : stime,
				endTime : etime
			};
			videoList.push(video);
			index_22++;
		}

		if(jsConst.VIDEO_PLAYER_TYPE=='0'){
			videoClient2.playbackHandle(videoList, layout);
		}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
			// 播放回放视频
			$("#dialogId_videoPluginDemo").dialog("close");
			$("#dialogId_videoPluginDemo").dialog({
				width: 800,
				height: 600,
				title: '视频调阅',
				onOpen: function() {
					videoPlugin.multiVideoPlaybackHandle({
						'container': $("div[id='dialogId_videoPluginDemo']"),
						'subType': 1,
						'layout': layout,
						'data': videoList,
						'callback': function (data) {
						}
					});
				},
				onClose: function () {
					videoPlugin.videoPlayerClear();
				}
			});
			$("#dialogId_videoPluginDemo").dialog("open");
		}
	}
	
	function dakai(){
		
		// debugger;
		// 查询外来车辆监控关联的视频预案
		var urlPath = jsConst.basePath + "groupManage/queryByCusNumberAndGrpName";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : {
				cusNumber:jsConst.CUS_NUMBER,
				grpName:"外来车辆监控"
			},
			dataType : 'json',
			success : function(data1) {
				// debugger;
				console.log("realTimeVideo data1 = " + JSON.stringify(data1));
				if(data1.code == 200) {
					var grpData = data1.data;
					if(jsConst.VIDEO_PLAYER_TYPE=='0'){
						var extend = {
							'isAutoLayout': true,// 是否自动布局
							'curLayout': videoClient.curLayout// 当前布局
						};
						setFormStyle();
						videoClient.playGroupHandle(jsConst.CUS_NUMBER, grpData.id, grpData.gmaGrpName, extend, function(data2) {
							console.log("realTimeVideo data1 = " + JSON.stringify(data2));
							if(data2.success) {
								var list = data2.obj;
								if(list.length == 0) {
									_alert('外来车辆监控未关联任何摄像机!');
								}
							} else {
								_alert(data2.msg, 'warning');
							}
						});
					}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
						$("#dialogId_videoPluginDemo").dialog("close");
						$("#dialogId_videoPluginDemo").dialog({
							width: 800,
							height: 600,
							title: '视频调阅',
							onOpen: function() {
								// 播放选中的摄像头实时视频
								videoPlugin.groupVideoPlayHandle({
									'container': $("div[id='dialogId_videoPluginDemo']"),
									'cusNumber': jsConst.CUS_NUMBER,
									'groupId': grpData.id,
									'groupName': grpData.gmaGrpName,
									'callback': function (data) {
									}
								});
							},
							onClose: function () {
								videoPlugin.videoPlayerClear();
							}
						});
						$("#dialogId_videoPluginDemo").dialog("open");
					}
				} else if (data.code == 500) {
					console.log(data1.data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("realTimeVideo error XMLHttpRequest" + JSON.stringify(XMLHttpRequest));
				console.log("realTimeVideo error textStatus" + textStatus);
				console.log("realTimeVideo error errorThrown" + errorThrown);
			}
		});
	}
	

/*
 * 设置窗口属性
 */
function setFormStyle(){
	videoClient.setFormStyle({
			'topMost': true,
			'showFormTitle': true,
			'showVideoTitle': true
	}, function(data) {});
}

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
</script>
