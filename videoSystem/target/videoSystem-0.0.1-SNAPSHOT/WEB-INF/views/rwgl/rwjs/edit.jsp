<%@page import="com.cesgroup.prison.code.tool.JsonUtil"%>
<%@page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.code.tool.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 
<style>
	.selectHid {
		text-align: center;
		width: 160px;
		height: 30px;
		font-size: 11.5px;
		cursor: pointer;
		margin-top: 5px;
	} 
	
	.hid {
		text-align: center;
		padding-top: 10px;
		width: 115px;
		height: 50px;
		font-size: 11.5px;
		cursor: pointer;
		margin-top: 5px;
		margin-left: 35px;
		background: #fafafa;
		background: url('${ctx}/static/images/left_button.png') no-repeat;
	}
	
	.divClass_select {
		width: 180px;
		height: 430px;
		margin-top: 10px;
		margin-left: 20px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
	
	#divId_rwjs_gzzright table {
		width: 540px;
		border: 1px solid #ededed; 
		margin-top: 20px; 
		margin-bottom: 20px;
	}
	
	#divId_rwjs_gzzright th {
		background: #4692f0;
		text-align: center;
		border: 1px solid #ededed;
	}
	
	#divId_rwjs_gzzright td {
		border: 1px solid #ededed;
	}
	
	.disDescBtn {
		width: 100px;
		text-align: center;
		margin-right: 5px;
		color: #4692f0;
	}
	
	.unChecked_div {
		width: 180px;
		height: 300px;
		margin-left: 50px;
		margin-top: 10px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
</style>

<cui:tabs id="tabsId_rwjs_edit" heightStyle="fill">
    <div id="tabsId_rwjs_edit_1">
    	<cui:form id="formId_rwjs_edit" heightStyle="fill">
			<cui:input type='hidden' name="id" id="inputId_rwjs_planId" />
			<cui:input type='hidden' name="xfId" />
			<cui:input type='hidden' name="jyId" />
			<cui:input type='hidden' name="jqId" />
			<cui:input type='hidden' name="jsStatus" />
			<cui:input type='hidden' name="xfPolice" />
	 		<table class="table">
	 			<tr>
					<th>任务内容</th>
				</tr>
				<tr>
					<th>下发时间：</th>
					<td><cui:datepicker id = "xfTime" name="xfTime" readonly="true" width="500" dateFormat="yyyy-MM-dd"></cui:datepicker></td>		
				</tr>
				<tr>
					<th>任务标题：</th>
					<td><cui:input name="rwTitle" required="true" width="500"></cui:input></td>		
				</tr>	
				<tr>
					<th>任务内容：</th>
					<td><cui:textarea name="rwContent" width="500" required="true"></cui:textarea></td>
				</tr>
				<tr>
					<th>处理期限：</th>
					<td><cui:datepicker name="clDeadline" width="500" required="true"></cui:datepicker></td>
				</tr>
				<tr>
					<th>任务处理</th>
				</tr>
				<tr>
					<th>处理结果：</th>
					<td><cui:textarea name="clContent" width="500" required="true"></cui:textarea></td>
				</tr>
				<tr>
					<th>处理时间：</th>
					<td><cui:datepicker id="clTime" name="clTime" width="500" required="true"></cui:datepicker></td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdate"></cui:button>
			<%-- <cui:button componentCls="btn-primary" label="上报" onClick="updateStatus"></cui:button> --%>
			<cui:button componentCls="btn-primary" label="取消" onClick="closeDialog"></cui:button>
		</div>
  	</div>
</cui:tabs>

<script>
	
	$.parseDone(function() {
		
		updateFlag = false;
		var id = '${id}';
		if(id) {
			loadForm("formId_rwjs_edit", "${ctx}/rwgl/rwjs/getById?id=" + id, function(data) {		
			});
			
			updateFlag = true;
		}else{
			$("#clTime").datepicker('option','value','<%=DateUtils.getCurrentDate(false)%>');
		}
		
		
	});
</script>