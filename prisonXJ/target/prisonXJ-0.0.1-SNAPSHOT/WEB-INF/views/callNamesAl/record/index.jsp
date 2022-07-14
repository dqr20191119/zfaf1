<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
	<cui:form id="formId_query_dmjl">
		<table class="table">
			<tr>
				<th>发起部门：</th>
				<td>
					<cui:combobox name="cnmDprtmntId" data="combobox_data_bm" componentCls="form-control"></cui:combobox>
				</td>
				<th>发起日期：</th>
				<td>
					<cui:datepicker componentCls="form-control" name="startTime" ></cui:datepicker>
				</td>
				 
				<td>
					<cui:button label="查询" onClick="query_dmjl"></cui:button>
					<cui:button label="重置" onClick="reset_dmjl"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
		<cui:grid id="gridId_dmjl" fitStyle="fill"  colModel="gridId_dmjl_colModelDate">
			<cui:gridPager gridId="gridId_dmjl" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_dmjl" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var combobox_data_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	$.parseDone(function() {
		var url = "${ctx}/callNames/master/listAll.json?cnmCusNumber=" + cusNumber;
		if(jsConst.USER_LEVEL == 3){
			url = url + "&cnmDprtmntId=" + jsConst.DEPARTMENT_ID;
		}
		
		$("#gridId_dmjl").grid("reload", url);
	});
 
	function query_dmjl() {
		var formData = $("#formId_query_dmjl").form("formData");
		$('#gridId_dmjl').grid('option', 'postData', formData);
		$("#gridId_dmjl").grid("reload");
	}

	//重置按钮事件
	var reset_dmjl = function() {
		$("#formId_query_dmjl").form("reset");
	}
	
	var gridId_dmjl_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "CNM_DPRTMNT_ID",
		label : "发起部门",
		align : "center",
		formatter : "convertCode",
        revertCode : true,
	    formatoptions : { 'data':combobox_data_bm } 
	}, {
		name : "LC",
		label : "发起位置",
		align : "center" 
	}, {
		name : "CNM_START_TIME",
		label : "发起时间",
		align : "center" 
	}, {
		name : "CNM_TIME_LAG",
		label : "点名时长（分钟）",
		align : "center" 
	}, {
		label : "详情",
		name : "check",
		align : "center",
		formatter : "FormatterDmjl"
	}];
	
	function FormatterDmjl(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"openDmxq('" + param1 + "')\">查看</button>" ;
		return result;
	}
	
	function openDmxq(recordId) {
		$("#dialogId_dmjl").dialog({
			width : 800,
			height : 600,
			url : "${ctx}/callNames/master/openDialog/record/detail?id=" + recordId,
			title : '点名详情',
		});
		$("#dialogId_dmjl").dialog("open");
	}
	 
	
</script>