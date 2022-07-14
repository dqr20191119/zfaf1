<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<div style="width: 100%;height:100%">
	<fieldset>
		<legend>报警信息</legend>
		<cui:form id="formId_event">
			<table class="table" style="width: 100%">
				<tr>
					<th>报警时间</th>
					<td>
						<cui:input name="ARD_ALERT_TIME" componentCls="form-control" readonly="true"></cui:input>
					</td>
					<th>报警地点</th>
					<td>
						<cui:input name="ALARM_ADDRESS" componentCls="form-control" readonly="true"></cui:input>
					</td>
					<th>报警类型</th>
					<td>
						<cui:combobox name="ARD_TYPE_INDC" data="bjlx_data" componentCls="form-control" readonly="true"></cui:combobox>
					</td>
				</tr>
				<tr>
					<th>报警人</th>
					<td>
						<cui:input name="ARD_ALARM_POLICE" componentCls="form-control" readonly="true"></cui:input>
					</td>
					<th>接警人</th>
					<td>
						<cui:input name="ARD_RECEIVE_ALARM_POLICE" componentCls="form-control" readonly="true"></cui:input>
					</td>
					<th>处置人</th>
					<td>
						<cui:input name="ARD_OPRTR" componentCls="form-control" readonly="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>事件性质</th>
					<td>
						<cui:input name="ARD_EVENT_TYPE" componentCls="form-control" readonly="true"></cui:input>
					</td>
					<th>处置情况</th>
					<td colspan="3">
						<cui:textarea name="ARD_OPRTN_DESC" componentCls="form-control" readonly="true"></cui:textarea>
					</td>
				</tr>
			</table>
		</cui:form>
	</fieldset>

	<fieldset>
		<legend>附件信息</legend>
		<div id="div_fjxx" style="text-align: center; height: 287px;">
			<cui:uploader id="uploaderId_zjxx" buttonText="请选择文件..." removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess"
				onInit="common.onInit" swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
		</div>
	</fieldset>
	
	<div class="dialog-buttons" style="text-align: center; margin-bottom: 0px">
		<cui:button id="btnId_zjxx" onClick="openZjxx" label="证据信息"></cui:button>
		<cui:button id="btnId_submit" onClick="submitUpload" label="完成归档"></cui:button>
		<cui:button onClick="closeDia" label="关闭"></cui:button>
	</div>
</div>

<cui:dialog id="dialogId_zjxx" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<script>
	var id = "${id}";
	var type = "${type}";
	var sDate = null;
	var eDate = null;
	$.parseDone(function() {
		if (type != "0") {
			$("#btnId_submit").hide();
			$("#btnId_zjxx").hide();
		}
		if (id != null || id != "") {
			loadAlarmMsg();
			findFiles();
		}
		$("#div_fjxx").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
	});

	//请求查询报警记录
	function loadAlarmMsg() {
		$.ajax({
			type : "post",
			url : "${ctx}/alarm/findById.json?id=" + id,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#formId_event').form("load", data.obj);//刷新表格数据
					sDate = data.obj.ARD_ALERT_TIME;
					eDate = data.obj.ARD_OPRTN_FINISH_TIME;
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
	function submitUpload() {
		var data = {};
		data.id = id;
		data.files = $("#uploaderId_zjxx_affixIds").val();
		 
		 
			/* if (data.files == undefined || data.files == "") {
				$.messageQueue({ message : "请选择附件！", cls : "warning", iframePanel : true, type : "info" });
				return;
			} */
		 
		

		$.loading({
			text : "正在处理中，请稍后..."
		});

		$.ajax({
			type : 'post',
			url : '${ctx}/alarm/save/file.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if (data.success) {
					$("#gridId_alarmRecord").grid("reload");
					$("#dialogId_alarm").dialog("close");
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	//查询证据附件
	function findFiles() {
		var data = {};
		data.id = id;
		$.ajax({
			type : 'post',
			url : '${ctx}/alarm/findAlertFile.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					common.loadAffix("uploaderId_zjxx", data.obj,type == "0" ? false : true);
					if (type != "0") {
						//$("#uploaderId_zjxx").uploader("disable", true);
					}
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	function closeDia() {
		$("#dialogId_alarm").dialog("close");
	}
	
	function openZjxx() {
		$("#dialogId_zjxx").dialog( {
			width : "900",
			height : "500",
			url : "${ctx}/alarm/openDialog/zjxx?startingDate=" + sDate  + "&endingDate=" + eDate + "&ywId=${id}",
			title : "证据信息",
		});
		$("#dialogId_zjxx").dialog("open");
	}
</script>