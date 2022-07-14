<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
<cui:form id="Job_addForm" >
	<table class="table table-condensed tableNj table-bordered "style="max-width: 1000px;">
		<tbody>
			<tr>
				<td style="width: 8%;"><label>任务名称</label></td>
				<td style="width: 8%;"><cui:input componentCls="form-control" name="name" required="true" /></td>
				<td style="width: 8%;"><label>允许并发执行</label></td>
				<td style="width: 10%;"><cui:combobox name="concurrent" componentCls="form-control" data="combobox_concurrent" required="true" /></td>
				<td style="width: 8%;"><label>任务运行时间表达式</label></td>
				<td style="width: 10%;"><cui:input componentCls="form-control" name="cronExpression" required="true" /></td>
			</tr>

			 <tr>
				<td style="width: 8%;"><label>优先级</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="priority" required="true" validType="integer" /></td>
				<td style="width: 8%;"><label>任务对应的Bean名称</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="targetBean" required="true" /></td>
				<td style="width: 8%;"><label>任务对应的方法</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="targetMethod" required="true" /></td>
			</tr>
		<%-- 	<tr>
				<td style="width: 8%;"><label>开始时间</label></td>
				<td style="width: 17%;"><cui:datepicker componentCls="form-control" id="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" name="startTime" showOn="button"/></td>
				<td style="width: 8%;"><label>结束时间</label></td>
				<td style="width: 17%;"><cui:datepicker componentCls="form-control" id="endTime" name="endTime"  showOn="button"/></td>
				<td style="width: 8%;"><label>日历名称</label></td>
				<td style="width: 17%;"><cui:input componentCls="form-control" name="calendarName" /></td>
			</tr> --%>
			<tr>
				<td style="width: 8%;"><label>任务描述</label></td>
				<td style="width: 47%;" colspan="5"><cui:textarea componentCls="form-control" name="description"></cui:textarea></td>
			</tr>
		</tbody>
	</table>
</cui:form>
</div>
<%-- <div class="dialog-buttons">
	<cui:button label="保存" icons="iconSave" onClick="saveFun"
		componentCls="coral-btn-blue"></cui:button>
	<cui:button label="关闭" icons="iconClose" onClick="closeDialog"></cui:button>
</div> --%>
<script type="text/javascript">
function saveFun() {
	if ($("#addForm").form("valid")) {
		var formData = $("#addForm").form("formData");
		// console.log("formData is " + JSON.stringify(formData));
		var ur = '${ctx}/jobs/create';
		console.log("cron url is " + ur);
		$.ajax({
			type : 'post',
			url : ur,
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
function dateChange(){
	
	var bdate = $("#startTime").datepicker("option", "value");
	var edate = $("#endTime").datepicker("option", "value");
	if (edate != "" && bdate != "" && bdate > edate) {
		$.message("任务起始日期不能大于任务结束时间");
		$("input[name='startTime']").datepicker("option", "value", edate);
	}
}

</script>
