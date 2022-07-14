<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->
<div class="globalLoginLayout clearfix">

	<cui:form id="queryForm">

		<div class="globalLoginLayout">
			<div style="width:35%">
				<label>视频客户端IP:</label>
				<cui:input id="inputId_vccClientIp" type="text" 
					placeholder=""></cui:input>
			</div>
			<div class="last">
				<cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					 onClick="search" componentCls="coral-btn-blue" />
				<cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button>
			</div>
		</div>

	</cui:form>
</div>
<!-- 自定义查询结束 -->
<cui:toolbar id="toolbarId_videoClient" data="toolbarData_videoClient"
	onClick="toolbarOnClick_videoClient"></cui:toolbar>
<cui:grid id="gridId_videoClient" singleselect="true" shrinkToFit="false"
	 colModel="gridColModel_videoClient" fitStyle="fill"
	datatype="json" url="" pager="true"></cui:grid>
<cui:dialog id="dialogId_videoClientManage" autoOpen="false" iframePanel="true" 
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
$.parseDone(function(){
	$("#gridId_videoClient").grid("reload","${ctx}/sppz/videoClient/searchVideoClient.json?vccCusNumber="+cusNumber);
});


function search() {
	var postData = {};
	var vccClientIp = $('#inputId_vccClientIp').val();

	if (vccClientIp != "") {
		postData['vccClientIp'] = vccClientIp;
	}

	$('#gridId_videoClient').grid('option', 'postData', postData);
	$('#gridId_videoClient').grid('reload');
}

	var toolbarData_videoClient = [ {
	
		"id" : "create",
		"label" : "新增",
		"disabled" : "false",
		"type" : "button",
		"cls" : "",
		"icon" : ""
	}, {
		"id" : "update",
		"label" : "修改",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "",
		"icon" : ""
	}, {
		"id" : "delete",
		"label" : "删除",
		"disabled" : "false",
		"type" : "button",
		"cls" : "",
		"icon" : ""

	}];

	var toolbarOnClick_videoClient = function(event, ui) {
		var id = ui.id;
		if (id == 'create') {
			f_add();
		} else if (id == 'update') {
			f_modify();
		} else if (id == 'delete') {
			f_delete();
		} else if (id == 'import') {

		} 
	}
	function f_add() {
		$("#dialogId_videoClientManage").dialog({
			width : 300,
			height : 400,
			subTitle : '新增',
			url : '${ctx}/sppz/videoClient/new',
		});
		$("#dialogId_videoClientManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_videoClient").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_videoClientManage").dialog({
				width : 300,
				height : 400,
				subTitle : '修改',
				url : '${ctx}/sppz/videoClient/edit?id=' + selrow.toString(),
			});
			$("#dialogId_videoClientManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_videoClient").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/sppz/videoClient/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_videoClient").grid("reload");
								}else{
									$.message( {
										iframePanel:true,
								        message:data.exception.cause.message,
								        type:"danger"
								    });
								}
								
							}
						}); 
					}
					if (sure == false) {
						console.log('cancel');
					}
				}
			});
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	var gridColModel_videoClient = [ {
		label : "ID",
		name : "ID",
		key:true,
		hidden:true
	}, /* {
		label : "应用IP",
		align : "center",
		name : "VCC_APP_IP"
	}, */ {
		label : "视频客户端IP",
		align : "center",
		name : "VCC_CLIENT_IP"
	}, {
		label : "宽度",
		align : "center",
		name : "VCC_WIDTH"
	}, {
		label : "高度",
		align : "center",
		name : "VCC_HEIGHT"
	}, {
		label : "X坐标",
		align : "center",
		name : "VCC_X_CRDNT"
	}, {
		label : "Y坐标",
		align : "center",
		name : "VCC_Y_CRDNT"
	}, {
		label : "截图图片路径",
		align : "center",
		name : "VCC_IMG_PATH"
	}, {
		label : "视频录像路径",
		align : "center",
		name : "VCC_VIDEO_PATH"
	}, {
		label : "备注",
		align : "center",
		name : "VCC_REMARK"
	}, {
		label : "创建时间",
		align : "center",
		name : "VCC_CRTE_TIME"
	}, {
		label : "更新时间",
		align : "center",
		name : "VCC_UPDT_TIME"
	} ];
</script>