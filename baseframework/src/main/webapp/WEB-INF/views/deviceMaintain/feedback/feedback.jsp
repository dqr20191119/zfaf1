<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_record" heightStyle="fill">
		<table class="table table-bordered" style="width: 98%">
			<tr>
				<th>填报单位：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_DPRMNT_NAME"></cui:input>
				</td>
				<th>填报人：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_SUBMITTER"></cui:input>
				</td>
				<th>填报时间：</th>
				<td>
					<cui:datepicker componentCls="form-control" readonly="true" dateFormat="yyyy-MM-dd HH:mm:ss" name="DMA_FAULT_SUBMIT_TIME"></cui:datepicker>
				</td>

			</tr>
			<tr>
				<th>填报状态：</th>
				<td>
					<cui:combobox componentCls="form-control" readonly="true" name="DMA_STTS_INDC" data="sttsIndc"></cui:combobox>
				</td>
				<th>故障类型：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_TYPE_CH"></cui:input>
				</td>
				<th>故障内容：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_CONTENT_CH"></cui:input>
				</td>
			</tr>
			<tr>
				<th>维修时限：</th>
				<td>
					<cui:combobox componentCls="form-control" readonly="true" name="DMA_MAINTAIN_TERM" data="maintainTerm"></cui:combobox>
				</td>
				<th>故障地点：</th>
				<td colspan="3">
					<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_ADDRS"></cui:input>
				</td>
			</tr>
			<tr>
				<th>详细描述：</th>
				<td colspan="5">
					<cui:textarea componentCls="form-control" readonly="true" name="DMA_FAULT_DESC"></cui:textarea>
				</td>
			</tr>
			<tr>
				<th>维修负责人：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_MAINTAIN_PERSON"></cui:input>
				</td>
				<th>维修结果：</th>
				<td>
					<cui:input componentCls="form-control" readonly="true" name="DMA_MAINTAIN_DESC"></cui:input>
				</td>
			</tr>
			<tr>
				<th>维修情况：</th>
				<td colspan="5">
					<cui:textarea componentCls="form-control" readonly="true" name="DMA_MAINTAIN_RESULT">
					</cui:textarea>
				</td>
			</tr>

			<tr>
				<th>反馈意见：</th>
				<td colspan="5">
					<cui:textarea id="textId_idea" name="dmaDprtmntIdea" componentCls="form-control" required="true">
					</cui:textarea>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button   label="反馈" text="false" onClick="feedBack"></cui:button>
		<cui:button   label="取消" text="false" onClick="cancel"></cui:button>
	</div>
</div>


<script type="text/javascript">
	$.parseDone(function() {
		var url = "${ctx}/deviceMaintain/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$("#formId_record").form("load", data.obj);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	});

	//反馈
	function feedBack() {
		if ($("#formId_record").form("valid")) {
			var formData = {};
			formData["id"] = "${ID}";
			formData["dmaDprtmntIdea"] = $("#textId_idea").textbox("getText") ;
			var ur = "${ctx}/deviceMaintain/feedback.json";
			$.ajax({
				type : "post",
				url : ur,
				data : formData,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_affairsFeedBack").grid("reload");
						$("#dialogId_feedBack").dialog("close");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}
	}
	function cancel() {
		// 关闭当前弹窗		
		$("#dialogId_feedBack").dialog("close");
	}
</script>
