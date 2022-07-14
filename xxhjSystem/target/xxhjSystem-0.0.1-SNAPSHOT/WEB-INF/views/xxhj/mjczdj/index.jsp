<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_query">
			<table class="table">
				<tr>
					<th>登记日期：</th>
					<td>
						<cui:datepicker componentCls="form-control" id="startTime" name="startTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
					</td>
					<th align="center">至</th>
					<td>
						<cui:datepicker componentCls="form-control" name="endTime" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
					</td>
					<td>
						<cui:button label="查询" onClick="query"></cui:button>
						<cui:button label="重置" onClick="reset"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar data="toolbar_mjczdjDate"></cui:toolbar>
		<cui:grid id="gridId_mjczdj" fitStyle="fill" multiselect="true" colModel="gridId_mjczdj_colModelDate">
			<cui:gridPager gridId="gridId_mjczdj" />
		</cui:grid>
	</div>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		var url = "${ctx}/xxhj/mjczdj/listAll.json?dorCusNumber=" + cusNumber;
		if(jsConst.USER_LEVEL == 3){
			url = url + "&dorDprtmntId=" + jsConst.DEPARTMENT_ID;
		}
		$("#gridId_mjczdj").grid("reload", url);
	});
	 

	var gridId_mjczdj_colModelDate = [ {
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "DOR_DPRTMNT_ID",
		label : "部门id",
		hidden : true 
	}, {
		name : "DOR_DPRTMNT",
		label : "部门"
	}, {
		name : "DOR_CRTE_TIME",
		label : "开门时间",
	}, {
		name : "DOR_CRTE_USER",
		label : "开门操作人",
	}, {
		name : "DOR_UPDT_TIME",
		label : "闭门时间",
	}, {
		name : "DOR_UPDT_USER",
		label : "闭门操作人"
	}];

	toolbar_mjczdjDate = [ {
		"type" : "button",
		"label" : "开门登记",
		"onClick" : "kmdj",
	}, {
		"type" : "button",
		"label" : "闭门登记",
		"onClick" : "bmdj",
	}, {
		"type" : "button",
		"label" : "删除",
		"onClick" : "remove",
	} ];

	function kmdj() {
		$.confirm("是否继续 “"+jsConst.DEPARTMENT_NAME+"” 开门操作", "信息确认", function(confirm) {
			if (confirm) {
				$.ajax({
					type : 'post',
					url : '${ctx}/xxhj/mjczdj/saveInfo.json',
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							$("#gridId_mjczdj").grid("reload");
						} else {
							$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					}
				});
			}
		});
	}
	
	function bmdj() {
		var selected = $("#gridId_mjczdj").grid("option", "selarrrow");
		if (selected.length != 1) {
			$.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		
		var rowData = $("#gridId_mjczdj").grid("getRowData", selected[0]);
		if (rowData.DOR_DPRTMNT_ID != jsConst.DEPARTMENT_ID && jsConst.USER_LEVEL == 3) {
			$.messageQueue({ message : "请选择一条本部门的记录", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		if (!isEmpty(rowData.DOR_UPDT_TIME)) {
			$.messageQueue({ message : "请选择一条需要闭门操作的记录", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		$.confirm("是否继续 “"+jsConst.DEPARTMENT_NAME+"” 闭门操作", "信息确认", function(confirm) {
			if (confirm) {
				$.ajax({
					type : 'post',
					url : '${ctx}/xxhj/mjczdj/updateInfo.json?id=' + rowData.ID,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							$("#gridId_mjczdj").grid("reload");
						} else {
							$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					}
				});
			}
		});
	}
	
	function remove() {
		var selected = $("#gridId_mjczdj").grid("option", "selarrrow");
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/xxhj/mjczdj/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_mjczdj").grid("reload");
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message : "请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	/**
	 * 判断是否为空值
	 */
	function isEmpty(_v) {
		if (_v == undefined)
			return true;
		if (_v == null)
			return true;
		if (_v == "undefined")
			return true;
		if (_v == "")
			return true;
		return false;
	}
	
	function query() {
		var formData = $("#formId_query").form("formData");
		$('#gridId_mjczdj').grid('option', 'postData', formData);
		$("#gridId_mjczdj").grid("reload");
	}

	//重置按钮事件
	var reset = function() {
		$("#formId_query").form("reset");
	}
</script>