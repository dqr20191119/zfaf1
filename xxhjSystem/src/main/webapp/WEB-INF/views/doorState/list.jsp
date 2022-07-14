<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_gb_query">
			<table class="table">
				<tr>
					<th>房间名称：</th>
					<td>
						<cui:input name="dsdDoorName" componentCls="form-control"></cui:input>
					<th>门禁状态 ：</th>
					<td><cui:combobox id="eventState" name="dsdDoorState" data="combobox_dcbSttsIndc"></cui:combobox></td>
					</td>
					<td>
						<cui:button label="查询" onClick="gb_query"></cui:button>
						<cui:button label="重置" onClick="gb_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<%-- <cui:toolbar id="toolbarId_securityCheck" data="toolbar_securityCheckDate"></cui:toolbar> --%>
		<cui:grid id="gridId_securityCheck" fitStyle="fill" multiselect="true" colModel="gridId_securityCheck_colModelDate">
			<cui:gridPager gridId="gridId_securityCheck" />
		</cui:grid>
	</div>
</body>
<script>
	// var jsConst = window.top.jsConst;
	// var cusNumber = jsConst.CUS_NUMBER;//监狱号
    //var comboboxData_sjzt=[{value:'开',text:'开'},{value:'关',text:'关'},{value:'all',text:'全部'}];
  	//门禁状态
	var combobox_dcbSttsIndc = <%=CodeFacade.loadCode2Json("4.20.23")%>;
	$.parseDone(function() {
	    $("#eventState").combobox("setValue", "");
		var url = "${ctx}/doorstate/all/listAll.json";
		$("#gridId_securityCheck").grid("reload", url);
	});

	var gridId_securityCheck_colModelDate = [{
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		name : "dsdDoorId",
		label : "门禁编号",
		align : "center",
		width : 250
	}, {
        name : "dsdDoorName",
        label : "门禁名称",
        align : "center",
        width : 250
    }, {
		name : "dsdDoorState",
		label : "状态",
		align : "center",
		width : 250,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : combobox_dcbSttsIndc
		}
	}, {
		name : "dsdUpdtTime",
		label : "更新时间",
		align : "center",
		width : 250
	}];

	toolbar_securityCheckDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "增加",
		"onClick" : "openDailog"
	}];

	function gb_query() {
		var formData = $("#formId_gb_query").form("formData");
		$("#gridId_securityCheck").grid("option", "postData", formData);
		$("#gridId_securityCheck").grid("reload");
		//关闭当前弹窗
		$("#dialogId_securityCheck").dialog("close");
	}

	function gb_clear() {
		$("#formId_gb_query").form("clear");
	}
</script>