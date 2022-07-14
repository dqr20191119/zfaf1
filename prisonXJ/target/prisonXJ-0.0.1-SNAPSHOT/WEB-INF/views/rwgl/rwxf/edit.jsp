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
	
	#divId_rwxf_gzzright table {
		width: 540px;
		border: 1px solid #ededed; 
		margin-top: 20px; 
		margin-bottom: 20px;
	}
	
	#divId_rwxf_gzzright th {
		background: #4692f0;
		text-align: center;
		border: 1px solid #ededed;
	}
	
	#divId_rwxf_gzzright td {
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

<cui:tabs id="tabsId_rwxf_edit" heightStyle="fill">
    <div id="tabsId_rwxf_edit_1">
    	<cui:form id="formId_rwxf_edit" heightStyle="fill">
			<cui:input type='hidden' name="id" id="inputId_rwxf_planId" />
			<cui:input type='hidden' name="fxcjId" id="fxcjId" />
	 		<table class="table">
				<tr>
					<th>下发时间：</th>
					<td><cui:datepicker id = "xfTime" name="xfTime" readonly="true" width="500" dateFormat="yyyy-MM-dd"></cui:datepicker></td>		
				</tr>
				<tr>
					<th>任务标题：</th>
					<td><cui:input id="rwTitle" name="rwTitle" required="true" width="500"></cui:input></td>		
				</tr>	
				<tr>
					<th>任务内容：</th>
					<td><cui:textarea name="rwContent" width="500" required="true"></cui:textarea></td>
				</tr>
				<tr>
					<th>接收单位：</th>
					<td><cui:combobox id = "jsDeptE" name="jsDept" width="500" required="true" url="${ctx}/rwgl/rwxf/getJsDeptForCombobox" multiple = "true"></cui:combobox></td> 
				</tr>
				<tr>
					<th>处理期限：</th>
					<td><cui:datepicker id="clDeadline" name="clDeadline" width="500" required="true"></cui:datepicker></td>
				</tr>
				
			</table>
		</cui:form>
		<div style="border: 1px solid #c0c0c0; margin: 5px; text-align: center;">
				<h4 style="background-color: #F0F0F0; width: 100%; height: 35px; font-size: 25px; font-weight: normal;">上传附件</h4>
				<div style="text-align: center; height: 365px; overflow: auto;">
					<cui:uploader id="uploaderId_attachment" buttonText="请选择文件..."  removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess"
						onInit="common.onInit" onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
				</div>
			</div>
		<div class="dialog-buttons">
			<%-- <cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdate"></cui:button> --%>
			<cui:button componentCls="btn-primary" label="下发" onClick="updateStatus"></cui:button>
			<cui:button componentCls="btn-primary" label="取消" onClick="closeDialog"></cui:button>
		</div>
  	</div>
</cui:tabs>

<script>
	
	$.parseDone(function() {
		
		updateFlag = false;
		var id = '${id}';
		var title = '${title}';
		var jhJjsj = '${jhJjsj}';
		var fxcjId = '${fxcjId}';
		if(id) {
			loadForm("formId_rwxf_edit", "${ctx}/rwgl/rwxf/getById?id=" + id, function(data) {		
			//	common.loadAffix("uploaderId_rwxf", id, false,true);
			$('#jsDeptE').combobox('setValue',data.jsDept);
			});
			
			updateFlag = true;
		}else{
			$("#xfTime").datepicker('option','value','<%=DateUtils.getCurrentDate(false)%>');
			if(fxcjId){
				$('#fxcjId').textbox("setValue",fxcjId);
			}
			if(title){
				$('#rwTitle').textbox("setValue","处理"+title+"的问题");
			}
			if(jhJjsj){
				$("#clDeadline").datepicker('option','value',jhJjsj);
			}
		}
		
		
	});
</script>