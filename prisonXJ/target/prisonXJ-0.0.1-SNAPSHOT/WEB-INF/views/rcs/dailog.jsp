<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<!-- 页面元素id前缀 -->
<body style="height: 100%">
	<div style="text-align: center; height: 100%; width: 100%">
		<cui:form id="formId_addInfo" heightStyle="fill">
			<cui:input type='hidden' name="cusNumber" id="cusNumber" />
			<cui:input type='hidden' name="sendUserName" id="sendUserName" />
			<cui:input type='hidden' name="sendUserId" id="sendUserId" />
			<table class="table" style="width: 98%">
				<tr>
					<th>警号：</th>
					<td>
						<cui:input name="jobNo" id ="jobNo" readonly="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>民警姓名：</th>
					<td>
						<cui:input name="userName" id = "userName" readonly="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>号码：</th>
					<td>
						<cui:input name="cellvalue" id="cellvalue" ></cui:input>
					</td>
				</tr>
				<%-- <tr>
					<th>指令类型：</th>
					<td>
						<cui:combobox id="cmd" name= "cmd" data= "cmdType"></cui:combobox>
					</td>
				</tr>
				<tr>
					<th>消息内容：</th>
					<td>
						<cui:input name="content" required="true"></cui:input>
					</td>
				</tr> --%>
			</table>
		</cui:form>
		<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
			<cui:button label="提交" text="false" onClick="call"></cui:button>
		</div>
	</div>
</body>
<script type="text/javascript">
var cellvalue = '${cellvalue}';
var userName = '${name}';
var jobNo = '${no}';
var cusNumber = jsConst.ORG_CODE;
var userId = jsConst.USER_ID; 
var userNAME = jsConst.USER_NAME; 

$.parseDone(function() {
	$("#jobNo").textbox("setValue",jobNo);
	$("#cellvalue").textbox("setValue",cellvalue);
	$("#userName").textbox("setValue",userName);
	$("#cusNumber").textbox("setValue",cusNumber);
	$("#sendUserName").textbox("setValue",userNAME);
	$("#sendUserId").textbox("setValue",userId);
});

var cmdType = [
	{
		value : "2",
		text : "语音通知",
		"selected" : true
	},{
		value : "3",
		text : "发送短信"
	}]

/**
 * 呼叫
 */
function call() {
	var formData = $("#formId_addInfo").form("formData");
	var ur = '${ctx}/rcs/startCall';
	$.ajax({
		type : 'post',
		url : ur,
		data : formData,
		dataType : 'json',
		success : function(data) {
            if(data.code == 200){
            	$.message( {
					iframePanel:true,
			        message:data.message,
			        type:"success"
			    });
            }else if(data.code == 500){
            	$.message( {
					iframePanel:true,
			        message:data.message,
			        type:"danger"
			    });
            }
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.message( {
				iframePanel:true,
		        message:textStatus,
		        type:"danger"
		    });
		}
	});
}
</script>