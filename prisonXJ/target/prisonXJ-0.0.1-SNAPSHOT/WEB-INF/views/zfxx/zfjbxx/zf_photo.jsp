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
					<th>所在监狱：</th>
					<td>
						<cui:input id="cSzjy" name="cSzjy" required="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>所在监区：</th>
					<td>
						<cui:input id="cSzjq" name="cSzjq" required="false"></cui:input>
					</td>
				</tr>
				<tr>
					<th>同步时间：</th>
					<td>
						<cui:datepicker id="dUrlTime" name="dUrlTime" dateFormat="yyyy-MM-dd" readonly="false" showOn="button"></cui:datepicker>
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
	var cSzjy = $("form[id='formId_addInfo']").find("#cSzjy").textbox("getValue");
	var cSzjq = $("form[id='formId_addInfo']").find("#cSzjq").textbox("getValue");
	var dUrlTime = $("form[id='formId_addInfo']").find("#dUrlTime").datepicker("getValue");
	// 请求参数
    var params = {};
    if (cSzjy) {
		params["cSzjy"] = cSzjy;
	}
	if (cSzjq) {
		params["cSzjq"] = cSzjq;
	}
	if (dUrlTime) {
		params['dUrlTime'] = dUrlTime;
	}
	
	var url = '${ctx}/zfxx/zfJbxx/downloadZfPhoto';
	$.ajax({
		type : 'post',
		url : url,
		data : params,
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