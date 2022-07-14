<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->

<div style="background-color: #EDEEEE; width: 100%; height: 100%;">

	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="camera_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="search">
		</cui:tree>
	</div>
	<div style="margin-left: 26%; height: 100%;">

		<cui:form id="queryForm">

			<table class="table">
				<tr>
					<th>名称:</th>
					<td>
						<cui:input id="inputId_cbdName" name="cbdName" type="text"
							placeholder=""></cui:input>
					</td>
					<%-- 所属区域在左侧树展示 此处注释 wuzl 20180615
					<div>
						<label>所属区域:</label>
						<cui:combotree id="cbdAreaId" name="cbdAreaId" simpleDataEnable="true"
							url="${ctx}/groupManage/nullJson.json"></cui:combotree>
					</div>--%>
					<th>状态:</th>
					<td>
					<cui:combobox id="comboboxId_cbdSttsIndc" name="cbdSttsIndc"
						emptyText="请选择" data="cbdSttsIndc_json"></cui:combobox>
					</td>
					<td>
						<cui:button cls="cyanbtn" id="s_searchButton" label="查询"
									onClick="search" componentCls="coral-btn-blue" />
						<cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button>
					</td>
				</tr>

			</table>

		</cui:form>
		<!-- 自定义查询结束 -->
		<cui:toolbar id="toolbarId_camera" data="toolbarData_camera"
					 onClick="toolbarOnClick_camera"></cui:toolbar>
	<%-- 	<cui:grid id="gridId_camera" singleselect="true" shrinkToFit="false"
				  colModel="gridColModel_camera" fitStyle="fill" datatype="json" url=""
				  pager="true"></cui:grid --%>
				  <cui:grid id="gridId_camera" fitStyle="fill" multiselect="true" colModel="gridColModel_camera">
			<cui:gridPager gridId="gridId_camera" />
		</cui:grid>
	</div>
</div>
<cui:dialog id="dialogId_cameraManage" autoOpen="false" iframePanel="true"
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
	var cbdSttsIndc_json =<%=CodeFacade.loadCode2Json("4.20.13")%>; 
	var dbdVideoPlayIndc_json=<%=CodeFacade.loadCode2Json("4.20.14")%>;

	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人

	$.parseDone(function() {
//		debugger;
		//$("#gridId_camera").grid("option","url","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+cusNumber);
		var areaId='${areaId}'
		if(areaId){
			$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber+"&cbdAreaId="+ areaId);
		}else{
			$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber);
		}
		//$("#cbdAreaId").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
        $("#camera_regionTree").tree( "reload", "${ctx}/common/areadevice/findForCombotree?cusNumber=" + cusNumber + "&deviceType=0");
	});

	function search() {
		var postData = {};
		var cbdName = $('#inputId_cbdName').val();
		//var cbdAreaId=$('#cbdAreaId').combotree("getValue");
		var cbdSttsIndc = $('#comboboxId_cbdSttsIndc').combobox("getValue");

		if (cbdName != "") {
			postData['cbdName'] = cbdName;
		}
        //获取左侧树菜单中选中节点,单选
        var cbdAreaIdArr = $("#camera_regionTree").tree("getSelectedNodes");
        if( cbdAreaIdArr[0] &&  cbdAreaIdArr[0].id){
            postData['cbdAreaId'] = cbdAreaIdArr[0].id;
        }
		/*if (cbdAreaId != "") {
			postData['cbdAreaId'] = cbdAreaId;
		}*/
		if (cbdSttsIndc != "") {
			postData['cbdSttsIndc'] = cbdSttsIndc;
		}

		$('#gridId_camera').grid('option', 'postData', postData);
		$('#gridId_camera').grid('reload',"${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber);
	}

	var toolbarData_camera = [ {

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

	}, {
		"id" : "areaDelete",
		"label" : "区域删除",
		"disabled" : "false",
		"type" : "button",
		"cls" : "",
		"icon" : ""
	}];

	var toolbarOnClick_camera = function(event, ui) {
		var id = ui.id;
		if (id == 'create') {
			f_add();
		} else if (id == 'update') {
			f_modify();
		} else if (id == 'delete') {
			f_delete();
		} else if (id == 'areaDelete') {
			f_areaDelete();
		}
	}
	function f_add() {
		$("#dialogId_cameraManage").dialog({
			width : 750,
			height : 430,
			subTitle : '新增',
			url : '${ctx}/jfsb/camera/new'
		});
		$("#dialogId_cameraManage").dialog("open");
	}
	function f_modify() {
		//var selrow = $("#gridId_camera").grid("option", "selrow");
		var selected = $("#gridId_camera").grid("option", "selarrrow");
		if (selected.length == 1) {
			var rowData = $("#gridId_camera").grid("getRowData", selected[0]);
			$("#dialogId_cameraManage").dialog({
				width : 750,
				height : 430,
				subTitle : '修改',
				url : '${ctx}/jfsb/camera/edit?id=' + rowData.ID,
			});
			$("#dialogId_cameraManage").dialog("open");
		} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	function f_delete() {
		//var selrow = $("#gridId_camera").grid("option", "selrow");
		var selected = $("#gridId_camera").grid("option", "selarrrow");
		if (selected.length > 0) {
			$.confirm( {
				message:"是否确认删除选中的记录？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/jfsb/camera/deleteByIds.json",
							data:JSON.stringify(selected),	
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber);
								} else {
									$.message({
										iframePanel:true,
										message : data.msg,
										type : "danger"
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
	
	function f_areaDelete() {
		var cbdAreaId = "";
        var cbdAreaIdArr = $("#camera_regionTree").tree("getSelectedNodes");
        if( cbdAreaIdArr[0] && cbdAreaIdArr[0].id){
            cbdAreaId = cbdAreaIdArr[0].id;
        }
		var cbdAreaName = $("#cbdAreaId").combotree("getText");
		if (cbdAreaId != null && cbdAreaId !="") {
			$.confirm( {
				message:"确认删除"+cbdAreaName+"区域的所有摄像头？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/jfsb/camera/deleteByAreaId.json?cbdAreaId="
									+ cbdAreaId,
							success : function(data) {
								if (data.success) {
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber);
								} else {
									$.message({
										iframePanel:true,
										message : data.msg,
										type : "danger"
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
				message : "请选择所属区域！",
				cls : "warning"
			});
		}
	}
	var gridColModel_camera = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key : true
	}, {
		label : "摄像机名称",
		name : "CBD_NAME",
		align : "center",
		width : 200
	}, {
        label : "所属区域",
        width : 200,
        align : "center",
        name : "CBD_AREA"
    },{
        label : "IP地址列",
        width : 200,
        align : "center",
        name : "CBD_IP_ADDRS"
    },{
		label : "摄像机状态",
		name : "CBD_STTS_INDC",
		align : "center",
		width : 100,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : cbdSttsIndc_json
		}
	}, {
		label : "摄像机所在的DVR",
		width : 200,
		align : "center",
		name : "VDI_DEVICE_NAME"
	}, {
		label : "摄像机所在DVR的通道号",
		width : 200,
		align : "center",
		name : "CBD_DVR_CHNNL_IDNTY"
	}, {
		label : "摄像机所在的流媒体",
		width : 150,
		align : "center",
		name : "SSI_SERVER_NAME"
	}, {
		label : "实时播放方式",
		width : 150,
		align : "center",
		name : "CBD_VIDEO_PLAY_INDC",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : dbdVideoPlayIndc_json
		}
	}/*, {
		label : "所属部门",
		width : 200,
		align : "center",
		name : "CBD_DPRTMNT"
	} *//* , {
			label : "创建人",
			width : 100,
			name : "CBD_CRTE_USER_ID"
		}, {
			label : "创建时间",
			width : 100,
			name : "CBD_CRTE_TIME"
		}, {
			label : "更新人",
			width : 100,
			name : "CBD_UPDT_USER_ID"
		}, {
			label : "更新时间",
			width : 100,
			name : "CBD_UPDT_TIME"
		}  */];
</script>