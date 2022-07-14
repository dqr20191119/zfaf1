<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->

<div style="background-color: #EDEEEE; width: 100%; height: 100%;">

	<div style="height: 100%;">
		<cui:form id="queryForm">
			<table class="table">
				<tr>
					<th>名称:</th>
					<td>
						<cui:input id="inputId_cbdName" name="cbdName" type="text" placeholder=""></cui:input>
					</td>
					<%-- 所属区域在左侧树展示 此处注释 wuzl 20180615
					<div>
						<label>所属区域:</label>
						<cui:combotree id="cbdAreaId" name="cbdAreaId" simpleDataEnable="true"
							url="${ctx}/groupManage/nullJson.json"></cui:combotree>
					</div>--%>
					<th>状态:</th>
					<td>
						<cui:combobox id="comboboxId_cbdSttsIndc" name="cbdSttsIndc" emptyText="请选择" data="cbdSttsIndc_json"></cui:combobox>
					</td>
					<td>
						<cui:button cls="cyanbtn" id="s_searchButton" label="查询" onClick="search" componentCls="coral-btn-blue" />
						<cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<!-- 自定义查询结束 -->
		<cui:grid id="gridId_camera" singleselect="true" shrinkToFit="false" colModel="gridColModel_camera" fitStyle="fill" datatype="json" url="" pager="true"></cui:grid>
	</div>
</div>
<cui:dialog id="dialogId_cameraManage" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
	var cbdSttsIndc_json =<%=CodeFacade.loadCode2Json("4.20.13")%>; 
	var dbdVideoPlayIndc_json=<%=CodeFacade.loadCode2Json("4.20.14")%>;

	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人

	$.parseDone(function() {
//		debugger;
		//$("#gridId_camera").grid("option","url","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+cusNumber);
		var areaId = '${areaId}'
		if(areaId) {
			$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber+"&cbdAreaId="+ areaId);
		}else {
			$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+ cusNumber);
		}
		//$("#cbdAreaId").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
	});

	function search() {
		var postData = {};
		var areaId = '${areaId}'
		if(areaId) {
			postData['cbdAreaId'] = areaId;
		}
		var cbdName = $('#inputId_cbdName').val();
		//var cbdAreaId=$('#cbdAreaId').combotree("getValue");
		var cbdSttsIndc = $('#comboboxId_cbdSttsIndc').combobox("getValue");

		if (cbdName != "") {
			postData['cbdName'] = cbdName;
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