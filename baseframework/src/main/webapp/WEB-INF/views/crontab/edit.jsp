<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<cui:form id="addForm" method="post" action="" heightStyle="fill">
	<input type="hidden" name="id" />
	<input type="hidden" name="startTime" />
	<table class="table table-condensed tableNj table-bordered table-fixed">
		<tbody>
			<tr>
				<td style="width: 8%;"><label>任务名称</label></td>
				<td style="width: 8%;"><cui:input componentCls="form-control" name="name" required="true" /></td>
				<td style="width: 8%;"><label>允许并发执行</label></td>
				<td style="width: 10%;"><cui:combobox name="concurrent" componentCls="form-control" data="combobox_concurrent" required="true"/></td>
				<td style="width: 8%;"><label>任务运行时间表达式</label></td>
				<td style="width: 10%;"><cui:input componentCls="form-control" name="cronExpression" required="true" /></td>
			</tr>

			<tr>
				<td style="width: 8%;"><label>优先级</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="priority" required="true" validType="integer" /></td>
				<td style="width: 8%;"><label>任务对应的Bean名称</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="targetBean" required="true"/></td>
				<td style="width: 8%;"><label>任务对应的方法</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="targetMethod" required="true" /></td>
			</tr>

<%--
			<tr>
				<td style="width: 8%;"><label>开始时间</label></td>
				<td style="width: 17%;"><cui:datepicker componentCls="form-control" id="startTime" name="startTime" dateFormat="yyyy-MM-dd" onChange="dateChange" showOn="button" onKeyDown="enterGo"/></td>
				<td style="width: 8%;"><label>结束时间</label></td>
				<td style="width: 17%;"><cui:datepicker componentCls="form-control" id="endTime" name="endTime" dateFormat=" yyyy-MM-dd" onChange="dateChange" showOn="button" onKeyDown="enterGo"/></td>
				<td style="width: 8%;"><label>日历名称</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="calendarName"/></td>
			</tr>
 --%>
			<tr>
				<td style="width: 8%;"><label>任务描述</label></td>
				<td style="width: 47%;" colspan="5"><cui:textarea componentCls="form-control" name="description"></cui:textarea>
				</td>
			</tr>
		</tbody>
	</table>
</cui:form>

<div class="dialog-buttons">
	<cui:button label="保存" icons="iconSave" onClick="saveFun"
		componentCls="coral-btn-blue"></cui:button>
	<cui:button label="关闭" icons="iconClose" onClick="closeDialog"></cui:button>
</div>
<script type="text/javascript">
$(function() {
	$.ajax({
		type : "post",
		url : "${ctx}/jobs/edit?id=${id}",
		dataType : "json",
		contentType : 'application/json; charset=UTF-8',
		success : function(data) {
			$("#addForm").form("load", data.model);
		}
	});
});
function saveFun() {
	if ($("#addForm").form("valid")) {
		var formData = $("#addForm").form("formData");
		var ur = '${ctx}/jobs/update';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				$.message("保存成功");
				$("#gridJobs").grid("reload");
				$("#adddialog").dialog("close");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
			

	} else {
		$.alert("请确认输入是否正确！");
	}
};
	
</script>
