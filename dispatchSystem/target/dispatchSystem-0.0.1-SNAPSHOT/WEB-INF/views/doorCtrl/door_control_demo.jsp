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
			<table class="table" style="width: 98%">
				<tr>
					<th>cusNumber：</th>
					<td>
						<cui:input name="cusNumber" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>指令类型：</th>
					<td>
						<cui:input name="action" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>门禁厂商：</th>
					<td>
						<cui:input name="brand" required="false"></cui:input>
					</td>
				</tr>
				<tr>
					<th>门禁编号：</th>
					<td>
						<cui:input name="doorID" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>门禁名称：</th>
					<td>
						<cui:input name="doorName"></cui:input>
					</td>
				</tr>
				<tr>
					<th>门禁类型：</th>
					<td>
						<cui:input name="doorType"></cui:input>
					</td>
				</tr>
				<tr>
					<th>监区编号：</th>
					<td>
						<cui:input name="deptId"></cui:input>
					</td>
				</tr>
				<tr>
					<th>监舍编号：</th>
					<td>
						<cui:input name="roomId"></cui:input>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
			<cui:button label="提交" text="false" onClick="call"></cui:button>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	
});

/**
 * 呼叫
 */
function call() {
	var formData = $("#formId_addInfo").form("formData");
	var ur = '${ctx}/doorlinkage/openDoorDemo';
	$.ajax({
		type : 'post',
		url : ur,
		data : formData,
		dataType : 'json',
		success : function(data) {
            if(data.code == 200){
                alert("success");
            }else if(data.code == 500){
                alert("error");
            }
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}
</script>