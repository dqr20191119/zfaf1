<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div class="PanelConent coral-noscroll">
	<%--begin search form --%>
	<div class="row padding10" id="searchForm">
		<cui:form id="queryForm">
			<div class="col-md-12">
				<label>任务名称:</label>
				<cui:input id="name" type="text" placeholder="任务名称"
						   onEnter="search"></cui:input>
				<label>任务状态:</label>
				<cui:combobox  id="jobStatus"
							   enableSearch="true"  data="combobox_status">

				</cui:combobox>
				<cui:button id="s_userSearchButton" label="查询"  cls="deleteBtn" componentCls="btn-primary"
							name="se" onClick="search"   />
				<cui:button id="resetBtn" label="重置" cls="deleteBtn" onClick="resetHandler"></cui:button>
			</div>
		</cui:form>
	</div>
	<%--begin toolbar --%>
	<div class="row" >
		<div class="clearfix col-md-6">
			<div class="floatLeft2">
				<%--<div id="toolbar"></div>--%>

					<cui:button id="addBtn" componentCls="btn-primary" label="新增" onClick="f_add"></cui:button>
					<cui:button id="deleteBtn" label="删除" onClick="f_delete"> </cui:button>
			</div>
		</div>
	</div>
	<%--end toolbar--%>
	<%--end search form--%>
	<%--grid--%>
	<cui:grid id="gridJobs" rownumbers="false" singleselect="true" height="350"
			  sortname="id" sortorder="asc" url="${ctx}/jobs/search"
			  fitStyle="fill" onDblClickRow="onDblClickRow" width="auto"
			  rowNum="10">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="name" width="40">任务名称</cui:gridCol>
			<cui:gridCol name="concurrent" width="40" formatter="convertCode"
						 formatoptions="{'data':combobox_concurrent}">允许并发执行</cui:gridCol>
			<cui:gridCol name="status" width="40" formatter="convertCode"
						 formatoptions="{'data':combobox_status}">任务状态</cui:gridCol>
			<cui:gridCol name="cronExpression" width="40">任务运行时间表达式</cui:gridCol>
			<cui:gridCol name="description" width="40">任务描述</cui:gridCol>
			<cui:gridCol name="targetBean" width="40">任务对应的Bean名称</cui:gridCol>
			<cui:gridCol name="targetMethod" width="40">任务对应的方法</cui:gridCol>
			<cui:gridCol name="priority" width="40">优先级</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridJobs" />
	</cui:grid>
	<%--end grid--%>
	
<cui:dialog id="adddialog" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_job">
</cui:dialog>
</div>
<script>
var buttons_job = [{
	text: "保存",
	id: "btnId_save",
	click: function () {
		
		saveOrUpdate();
	}        
}, {
    text: "取消",
    id: "btnId_cancel",
    click: function () {
    	
    	$("#adddialog").dialog("close");
    }            
}];

function saveOrUpdate() {
	if ($("#Job_addForm").form("valid")) {
		var formData = $("#Job_addForm").form("formData");
		$.ajax({
			type : 'post',
			url :'${ctx}/jobs/create',
			data : formData,
			dataType : 'json',
			success : function(data) {
				$.message("保存成功");
				$("#adddialog").dialog("close");
				$("#gridJobs").grid("reload");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("XMLHttpRequest = " +JSON.stringify(XMLHttpRequest));
				console.log("errorThrown = " +errorThrown);
				console.log("textStatus = " +textStatus);
				alert(textStatus);
			}
		});
	} else {
		$.alert("请确认输入是否正确！");
	}
	
}


	function f_add() {
		$("#adddialog").dialog("option", {
			width : 660,
			height : 500,
			title : "新增",
			subTitle : "任务信息",
			asyncType : "get",
			url : "${ctx}/crontab/add"
		});
		$("#adddialog").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridJobs").grid("option", "selrow");
		if (selrow != null) {
			$("#adddialog").dialog("option", {
				title : "修改",
				subTitle : "任务信息",
				url : "${ctx}/crontab/edit?id=" + selrow.toString(),
				asyncType : "get"

			});
			$("#adddialog").dialog("open");
		} else {
			$.message({
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}

	}
	function f_op(op) {
		var selrow = $("#gridJobs").grid("option", "selrow");
		var postData = {"id":selrow};
		if (selrow != null) {
			$.ajax({
				type : "post",
				url : "${ctx}/jobs/" + op,
				dataType  : "json",
				data:postData,
				success : function(data) {
					$.message({
						message : "操作成功！",
						cls : "success"
					});

					$("#gridJobs").grid("reload");
				}
			});
		} else {
			$.message({
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}

	}
	function f_delete() {
		var selrow = $("#gridJobs").grid("option", "selrow");
		if (selrow != "") {
			$.confirm("确认是否删除？", function(r) {
				if (r) {
					$.ajax({
						type : "post",
						url : "${ctx}/jobs/destory?id=" + selrow.toString(),
						success : function(data) {
							$.message({
								message : "操作成功！",
								cls : "success"
							});

							$("#gridJobs").grid("reload");
						}
					});
				}
			});
		} else {
			$.message({
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}

	function resetHandler() {
		$("#queryForm").form("clear", {
			excluded : [ "readonly" ]
		});
	}
	function search() {
		var postData = {};
		var jobName = $('#name').val();
		var jobStatus = $('#jobStatus').combobox("getValue");
		if (jobName ) {
			postData['Q_LIKE_name'] = jobName;
		}

		$('#gridJobs').grid('option', 'postData', postData);
		$('#gridJobs').grid('reload');
	}
	function f_show(id) {
		$("#adddialog").dialog("option", {
			title : "查看",
			subTitle : "部门信息",
			asyncType : "get",
			url : "${ctx}/jobs/show?id=" + id
		});
		$("#adddialog").dialog("open");
	}
	function onDblClickRow(e, ui) {
		var selrow = ui.rowId;
		if (selrow != null) {
			$("#adddialog").dialog("option", {
				title : "查看",
				subTitle : "任务信息",
				asyncType : "get",
				url : "${ctx}/jobs/show?id=" + selrow.toString()
			});
			$("#adddialog").dialog("open");
		} else {
			$.message({
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function formatOperation(cellvalue, options, rawObject) {
		return result = "<a  href='#' onclick ='f_show("
				+ rawObject.id
				+ ")'title='查看' class='print'><div class='img'></div><span>查看</span></a>";
	}
</script>
