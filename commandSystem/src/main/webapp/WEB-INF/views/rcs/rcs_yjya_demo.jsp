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
						<cui:input name="cmd" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>终端标识：</th>
					<td>
						<cui:input name="terFlag" required="false"></cui:input>
					</td>
				</tr>
				<tr>
					<th>预案名称：</th>
					<td>
						<cui:input name="plan" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>梯队名称：</th>
					<td>
						<cui:input name="echelon"></cui:input>
					</td>
				</tr>
				<tr>
					<th>增补人员：</th>
					<td>
						<cui:input name="supplement"></cui:input>
					</td>
				</tr>
				<tr>
					<th>通知语音：</th>
					<td>
						<cui:input name="voiceTxt"></cui:input>
					</td>
				</tr>
				<tr>
					<th>姓名：</th>
					<td>
						<cui:input name="name"></cui:input>
					</td>
				</tr>
				<tr>
					<th>日期：</th>
					<td>
						<cui:input name="date"></cui:input>
					</td>
				</tr>
				<tr>
					<th>时间：</th>
					<td>
						<cui:input name="time"></cui:input>
					</td>
				</tr>
				<tr>
					<th>区域：</th>
					<td>
						<cui:input name="area"></cui:input>
					</td>
				</tr>
				<tr>
					<th>地点：</th>
					<td>
						<cui:input name="location"></cui:input>
					</td>
				</tr>
				<tr>
					<th>状态：</th>
					<td>
						<cui:input name="sta"></cui:input>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
			<cui:button label="提交" text="false" onClick="call"></cui:button>
			<cui:button label="重置" text="false" onClick="reset"></cui:button>
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
	var ur = '${ctx}/rcs/startYjyaDemo';
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