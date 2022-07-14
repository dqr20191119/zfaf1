<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control{
    width: 100%;
}
</style>

<center>
<cui:form id="formId_videoClientAdd">
<cui:input  type="hidden" required="true" id="vccCusNumber" name="vccCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="vccCrteUserId" name="vccCrteUserId" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="vccUpdtUserId" name="vccUpdtUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="vccCrteTime"  name="vccCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
<cui:datepicker  required="true" id="vccUpdtTime"  name="vccUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<%-- <tr>
			<td width="35%" ><label>应用IP：</label></td>
			<td width="55%" ><cui:input name="vccAppIp" validType = "ip" componentCls="form-control"></cui:input></td>
		</tr> --%>
		<tr>
			<td width="35%"><label>视频客户端IP：</label></td>
			<td width="55%"><cui:input name="vccClientIp" validType = "ip" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>宽度：</label></td>
			<td><cui:input name="vccWidth" validType = "integer"  required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>高度：</label></td>
			<td><cui:input name="vccHeight" validType = "integer" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>X坐标：</label></td>
			<td><cui:input name="vccXCrdnt" validType = "integer" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>Y坐标：</label></td>
			<td><cui:input name="vccYCrdnt" validType = "integer" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>截图图片路径：</label></td>
			<td><cui:input name="vccImgPath" placeholder="D:\视频截图" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>视频录像路径：</label></td>
			<td><cui:input name="vccVideoPath" placeholder="D:\视频录像" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><cui:input name="vccRemark" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="save"></cui:button>
</div>
<script>
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人

$.parseDone(function(){
	initFormData();
});
function initFormData(){
	$("#vccCusNumber").textbox("setValue",cusNumber);
	$("#vccCrteUserId").textbox("setValue",userId);
	$("#vccUpdtUserId").textbox("setValue",userId);
	$('#vccCrteTime').datepicker('setDate',new Date());
	$('#vccUpdtTime').datepicker('setDate',new Date());
}

function reset(){
	$("#formId_videoClientAdd").form("reset");
	initFormData();
}
function save(){
	if ($("#formId_videoClientAdd").form("valid")) {
		var formData = $("#formId_videoClientAdd").form("formData");
		var ur = '${ctx}/sppz/videoClient/create.json';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					$.message({
						message : "保存成功",
						cls : "success",
						iframePanel:true
					});
					$("#gridId_videoClient").grid("reload");
					$("#dialogId_videoClientManage").dialog("close");
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
				    });
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({
					message:textStatus,
					title:"信息提示",
					iframePanel:true
				});
			}
		});

	} else {
		$.alert({
			message:"请确认输入是否正确！",
			title:"信息提示",
			iframePanel:true
		});
	}
}
</script>