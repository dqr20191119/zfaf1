<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body_rlzc {
	height: 100%;
	width: 100%;
}

.div_body_rlzc .div_body_rlzc_1 {
	height: 87%;
	width: 100%;
}
</style>

<div class="div_body_rlzc">
	<div class="div_body_rlzc_1">
		<cui:uploader id="uploaderId_zpzc" buttonText="请选择文件..." removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess" onInit="common.onInit"
			swf="${ctx}/static/resource/swf/uploadify.swf" fileTypeExts="*.png;*.jpg" uploadLimit="1"></cui:uploader>
	</div>
	<span>提示：<strong style="color: red;">只可上传一张照片，注册失败请删除再操作！</strong></span>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="注册" onClick="submitUpload"></cui:button>
		<cui:button label="取消" onClick="cancel_rlzc"></cui:button>
	</div>
</div>

<script>
	$.parseDone(function() {
		findPrisonerPicFile();
	});
	
	//查询证据附件
	function findPrisonerPicFile() {
		var data = {};
		data.id = "${ID}";
		$.ajax({
			type : 'post',
			url : '${ctx}/callNames/register/findPrisonerPicFile.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					common.loadAffix("uploaderId_zpzc", data.obj);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	//提交上传
	function submitUpload(recordId) {
		var data = {};
		data.id = "${ID}";
		data.file = $("#uploaderId_zpzc_affixIds").val();

		if (data.files) {
			$.messageQueue({ message : "请选择附件！", cls : "warning", iframePanel : true, type : "info" });
			return;
		}

		$.loading({
			text : "正在处理中，请稍后..."
		});

		$.ajax({
			type : 'post',
			url : '${ctx}/callNames/register/save/file.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
					$("#gridId_rlzc").grid("reload");
					$("#dialogId_zpzc").dialog("close");
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	function cancel_rlzc() {
		$("#dialogId_zpzc").dialog("close");
	}
</script>