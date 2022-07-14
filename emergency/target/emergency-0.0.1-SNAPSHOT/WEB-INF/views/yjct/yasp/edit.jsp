<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:form id="formId_yasp_edit">
	<table class="table">
		<tr>
			<td rowspan="2" style="vertical-align: middle; width: 100px; text-align: center;">监狱领导</td>
			<td>审批意见</td>
			<td><cui:radiolist id="radiolistId_yasp" name="ehaIsAgree" data="combobox_yjct_sfty" required="true"></cui:radiolist></td>
		</tr>
		<tr>
			<td>审批内容</td>
			<td><cui:textarea name="ehaAgreeContent" width="500" height="100"></cui:textarea></td>
 		</tr>	 
	</table>
</cui:form>

<script>
	
	$.parseDone(function() {
		
		planId = '${id}';
		if(planId) {
			loadInfo();
 		}
		 
		// $("#formId_yasp_edit_1").form("setReadOnly", false);
		// $("#radiolistId_yasp_1").radiolist("option", "required", true);		 
	});
	
	function loadInfo() {
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yasp/searchAllData',
			data: {
				ehaPhFid : planId
			},
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var spDatas = data
				if(spDatas && spDatas.length > 0) {
					for(var i = 0; i < spDatas.length; i++) {
 						$("#formId_yasp_edit").form("load", spDatas[i]);	
					}
				}								 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
</script>