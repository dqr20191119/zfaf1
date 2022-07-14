<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<!-- 事务报备 -->
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_swbb" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>填报单位</th>
				<td>
					<cui:input id="tbdw" componentCls="form-control" isLabel="true"></cui:input>
				</td>
				<th>填报时间</th>
				<td>
					<cui:datepicker id="tbsj" name="dmaFaultSubmitTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" isLabel="true">
					</cui:datepicker>
				</td>
			</tr>
			<tr>
				<th>维修时限</th>
				<td>
					<cui:combobox name="dmaMaintainTerm" enableSearch="true" data="dmaMaintainTerm_data" componentCls="form-control" required="true"></cui:combobox>
				</td>
				<th>故障类型</th>
				<td>
					<cui:combobox id="gzlx" name="dmaFaultType" enableSearch="true" onSelect="onFaultTypeSelect" componentCls="form-control" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				
				<th>故障内容</th>
				<td>
					<cui:combobox id="gznr" name="dmaFaultContent" enableSearch="true" onSelect="onFaultContentSelect" componentCls="form-control" required="true"></cui:combobox>
				</td>
				<th>故障地点</th>
				<td>
					<cui:input name="dmaFaultAddrs" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>详细描述</th>
				<td colspan="5">
					<cui:textarea name="dmaFaultDesc" componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<th>维修部门</th>
				<td colspan="5">
					<cui:input id="wxbm" componentCls="form-control" isLabel="true">
					</cui:input>
				</td>
			</tr>
			<tr>
				<th>协助部门</th>
				<td colspan="5">
					<cui:input id="xzbm" componentCls="form-control" isLabel="true">
					</cui:input>
				</td>
			</tr>

		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button   label="提交" onClick="addRecord"></cui:button>
		<cui:button   label="关闭" onClick="close"></cui:button>
	</div>
</div>



<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE;//监狱号

	$.parseDone(function() {
		var time = new Date();
		$('#tbsj').datepicker("setDate", new Date());//填报日期时间初始化（当前时间）
		$("#gzlx").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		$("#tbdw").textbox("setText", jsConst.DEPARTMENT_NAME)
	});
	
	//维修时限
	var dmaMaintainTerm_data = <%=CodeFacade.loadCode2Json("4.20.29")%>;

	//维修类型选中事件
	function onFaultTypeSelect(event, ui) {
		//维修内容请求
		$("#gznr").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=2&sttsIndc=1&faultId=" + ui.item.value);
	}
	//维修内容选中事件
	function onFaultContentSelect(event, ui) {
		//维修部门及协助部门关联查询
		var url = "${ctx}/deviceFaultType/findDprtmnt.json?cusNumber=" + cusNumber + "&faultId=" + ui.item.value;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(resData) {
				if(resData.success){
					$("#wxbm").textbox("setText", resData.obj.FDR_MAINTAIN_DPRTMNT);
					$("#xzbm").textbox("setText", resData.obj.FDR_HELP_DPRTMNT);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	//发送添加报备请求
	function addRecord() {
		if ($("#formId_swbb").form("valid")) {
			var formData = $("#formId_swbb").form("formData");
			$.ajax({
				type : 'post',
				url : '${ctx}/deviceMaintain/save.json',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#dialog").dialog("close");
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
	function close() {
		$("#dialog").dialog("close");
	}
</script>
</html>