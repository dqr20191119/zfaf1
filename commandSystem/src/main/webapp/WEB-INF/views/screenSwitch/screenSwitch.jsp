<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px;">
		<cui:button onClick="switchScreen" width="80" label="切换"></cui:button>
		<cui:grid id="gridId_screenPlan" fitStyle="fill" singleselect="true" colModel="gridId_screenPlan_colModelDate">
			<cui:gridPager gridId="gridId_screenPlan" />
		</cui:grid>
	</div>
</body>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	//厂家
	var dpya_changjiaData = <%=CodeFacade.loadCode2Json("4.20.54")%>;
	// 状态
	var dpya_sttsData = <%=CodeFacade.loadCode2Json("4.20.55")%>;

	$.parseDone(function() {
		var url = "${ctx}/screenPlan/listAll.json?splCusNumber=" + cusNumber + "&P_orders=spl_crte_time,desc";
		$("#gridId_screenPlan").grid("reload", url);

	});
	var gridId_screenPlan_colModelDate = [{
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "SPL_PLAN_INDC",
		label : "预案编号",
		hidden : true
	}, {
		name : "SPL_REMARK",
		label : "位置"
	}, {
		name : "SPL_PLAN_NAME",
		label : "预案名称"
	}, {
		name : "SPL_STATUS_INDC",
		label : "状态",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_sttsData } 
	}, {
		name : "SPL_MANUFACTURERS_ID",
		label : "厂家",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_changjiaData } 
	} , {
		name : "SPL_REMARK",
		label : "备注"
	}];

	function switchScreen() {
		alert('开发中');return;
		var selrow = $("#gridId_screenPlan").grid("option", "selrow");
		if (selrow != null) {
			var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
			//alert("切换ID ：" + rowData.ID);
			if (rowData.SPL_STATUS_INDC == "1"){
				$.messageQueue({ message :  "故障，不可操作", cls : "warning", iframePanel : true, type : "info" });
			} else {
				startScreenSwitch(rowData.ID);
			}
		} else {
			$.messageQueue({ message :  "请选择大屏预案", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	//后台请求操作  
	function startScreenSwitch(screenId) {
		var data = {};
		data["screenId"] = screenId;
		var ur = "${ctx}/screenSwitch/startScreenSwitch.json";
		$.ajax({
			type : "post",
			url : ur,
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
				} else {
					$.messageQueue({ message :  data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
</script>