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
<cui:form id="formId_videoClientEdit">
<cui:input  type="hidden" required="true" name="id" value="${model.id}" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="vccUpdtUserId" name="vccUpdtUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="vccUpdtTime"  name="vccUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<%-- <tr>
			<td width="35%" ><label>应用IP：</label></td>
			<td width="55%" ><cui:input name="vccAppIp"  validType = "ip" value="${model.vccAppIp}" componentCls="form-control"></cui:input></td>
		</tr> --%>
		<tr>
			<td width="35%"><label>视频客户端IP：</label></td>
			<td width="55%"><cui:input name="vccClientIp" validType = "ip" value="${model.vccClientIp}"  required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>宽度：</label></td>
			<td><cui:input name="vccWidth" validType = "integer" value="${model.vccWidth}"  required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>高度：</label></td>
			<td><cui:input name="vccHeight" validType = "integer" value="${model.vccHeight}" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>X坐标：</label></td>
			<td><cui:input name="vccXCrdnt" validType = "integer" value="${model.vccXCrdnt}" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>Y坐标：</label></td>
			<td><cui:input name="vccYCrdnt" validType = "integer" value="${model.vccYCrdnt}" required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>截图图片路径：</label></td>
			<td><cui:input name="vccImgPath" value="${model.vccImgPath}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>视频录像路径：</label></td>
			<td><cui:input name="vccVideoPath" value="${model.vccVideoPath}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><cui:input name="vccRemark" value="${model.vccRemark}" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="save_edit"></cui:button>
</div>
<script>
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
$.parseDone(function(){
	initFormData();
});
function initFormData(){
	//初始化页面数据
	$("#vccUpdtUserId").textbox("setValue",userId);
	$('#vccUpdtTime').datepicker('setDate',new Date());
}
function reset(){
	$("#formId_videoClientEdit").form("reset");
	initFormData();
}
function save_edit(){
	if ($("#formId_videoClientEdit").form("valid")) {
		var formData = $("#formId_videoClientEdit").form("formData");
		var ur = '${ctx}/sppz/videoClient/updatePart.json';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.success){
					$.message({
						message : "修改成功",
						cls : "success",
						iframePanel:true
					});
					$("#gridId_videoClient").grid("reload");
					$("#dialogId_videoClientManage").dialog("close");
				}else{
					$.message( {
						iframePanel:true,
				        message:data.msg,
				        type:"danger"
				    });
				}
				//console.log(data.exception);
				//console.log(data.exception.cause.message);
				
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