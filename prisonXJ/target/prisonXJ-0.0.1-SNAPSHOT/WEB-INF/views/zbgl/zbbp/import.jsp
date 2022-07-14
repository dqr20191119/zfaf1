<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:form id="importForm" method="" action="" heightStyle="fill">
   	<table class="table table-condensed tableNj table-bordered table-fixed">
		<tbody>		
			<tr>
				<td width="20%"><label>数据导入</label></td>
				<td width="70%">
				<cui:uploader id="uploader" buttonText="请选择文件..." fileTypeExts="*.xls;*.xlsx" removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/zbgl/zbbp/upload.json?${url}"
				onUploadSuccess="loadGrid"
					auto="false"	 onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf" onCancel="onCancel" 
				multi="false"	onSelect="onSelect" ></cui:uploader>
				</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<p style="font-size:larger;">
		<font color="red">特别说明：</font><br/>
		<font color="red">1、数据内容：</font></br>
			<font color="red">（1）当天某个岗位人员为多个,请用符号"、"隔开,如"张三、李四"</font><br/>
	</p>
</cui:form>
<%--
<div class="dialog-buttons">
	<cui:button label="确定上传文件" icons="iconOk" onClick="uploadFun" id="uploadBut" ></cui:button>
	<cui:button label="关闭" icons="iconClose" onClick="closeFun"></cui:button>
</div>--%>
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
                addLoad();
			}

		});
	}
	
	function loadGrid(e,data) {
	    //debugger;
		var callData  =data.data;
		var obj = JSON.parse(callData);
        //$.alert(JSON.stringify(obj));
        $.loading("hide");
		if(obj.code == 200) {
			$.message({message:obj.message, cls:"success"});
			$("#uploader").uploader("clear");
			$("#dialogImport").dialog("close");
            $("#dialogId_zbbp").dialog("close");
            $("#gridId_zbbp_query").grid("reload");
		}else {
			$("#uploader").uploader("clear");
			//$.message({message:data.message, cls:"error"});
            $.alert(obj.message);
		}	
	}
	
	
	//文件删除成功时触发
	function f_onClearQueue(e,ui){
		console.log("文件删除成功");
		$("#uploaderId_attachment_affixIds").val("");
	}
	
</script>