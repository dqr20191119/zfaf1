<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:form id="importForm" method="" action="" heightStyle="fill">
   	<table class="table table-condensed tableNj table-bordered table-fixed">
		<tbody>		
			<tr>
				<td width="20%"><label>数据导入</label></td>
				<td width="70%">
				<cui:uploader id="uploader" buttonText="请选择文件..." fileTypeExts="*.xls;*.xlsx" removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/zbgl/rygl/upload?cusNumber=${cusNumber}&zbyf=${zbyf}"
				onUploadSuccess="loadGrid"
					auto="false"	 onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf" onCancel="onCancel" 
				multi="false"	onSelect="onSelect" ></cui:uploader>
				</td>
			</tr>
		</tbody>
	</table>
	<br/>
</cui:form>
<div class="dialog-buttons">
	<cui:button label="确定上传文件" icons="iconOk" onClick="uploadFun" id="uploadBut" ></cui:button>
	<cui:button label="关闭" icons="iconClose" onClick="closeFun"></cui:button>
</div>
<script type="text/javascript">
	function uploaderImportSuccess(){
		closeFun();
	}
	function closeFun(){
		$( "#dialogImport" ).dialog("close");
	}
	
	function addLoad(){
		$.loading({text:"正在处理中，请稍后..."});
	}
	
	function hideLoad(){
		$.loading("hide");
	}
	
	function onSelect(){
		$("#uploadBut").button( "enable");
	}
	function onCancel(){
		$("#uploadBut").button( "disable");
	}
	function uploadFun(){
		$.confirm("确定要导入此信息吗？",function(r){
			if(r){
				$("#uploader").uploader("upload");
			}

		});
	}
	
	function loadGrid(e,data) {
		var callData  =data.data;
		var obj = JSON.parse(callData);
		if(obj.code == 200) {
			$.message({message:obj.message, cls:"success"});
			$("#uploader").uploader("clear");
			$("#dialogImport").dialog("close");
			$("#gridId_rygl").grid("reload");
		}  else {
			$("#uploader").uploader("clear");
			$.message({message:obj.message, cls:"error"}); 
		}	
	}
	
	
	//文件删除成功时触发
	function f_onClearQueue(e,ui){
		console.log("文件删除成功");
		$("#uploaderId_attachment_affixIds").val("");
	}
	
</script>