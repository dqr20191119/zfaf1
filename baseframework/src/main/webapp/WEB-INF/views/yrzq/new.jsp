<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_yrzq_new" heightStyle="fill">
		<cui:input type='hidden' name="id" id="id" value="${id}"/>
 		<table class="table">
				<tr>
					<th>转班人：</th>
					<td><cui:combobox id = "zbr" name="zbr" width="280" required="true" url="${ctx}/wghgl/yrzq/getMj" multiple = "false"></cui:combobox></td> 
				</tr>
			</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 10px; text-align: center;">
		<cui:button label="保存" text="false" onClick="saveOrUpdate"></cui:button>
		<cui:button label="取消" text="false" onClick="toClose"></cui:button>
	</div>
</div>


<script>
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人id
	var userName = jsConst.REAL_NAME; //登录人姓名
	var id = '${id}';
	
function saveOrUpdate() {
		var zbrText = $('#zbr').combobox('getText');
		var validFlag = $("#formId_yrzq_new").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_yrzq_new").form("formData");
		formData["zbrText"] = zbrText;
		formData["userId"] = userId;
		formData["userName"] = userName;
		var url = '${ctx}/wghgl/yrzq/saveOrUpdateZb';
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			contentType: "application/json",
			url : url,
			dataType : 'json',
			data:JSON.stringify(formData),
			success : function(data) {
				$.loading("hide");
				if(data.success) {
					$.messageQueue({ message : "转班成功！", cls : "success", iframePanel : true, type : "info" });
				} else {
					$.messageQueue({ message : "转班失败！", cls : "warning", iframePanel : true, type : "info" });
				}	
				$("#dialogId_yrzq_edit").dialog("close");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function toClose(){
		$("#dialogId_yrzq_edit").dialog("close");
	}
</script>