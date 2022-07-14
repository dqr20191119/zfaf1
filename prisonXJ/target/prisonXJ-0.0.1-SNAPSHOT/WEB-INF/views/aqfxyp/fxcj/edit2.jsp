<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_lbgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="status" />
 		<table class="table">
			<tr>
				<th>风险点：</th>
				<td>
					<cui:combobox id="fxdId" name="fxdId" width="250" onChange="fxdComboboxOnChange"></cui:combobox>
			    </td>
				<th>评估条件：</th>
				<td>
					<cui:combobox id="pgtjId" name="pgtjId" width="250"></cui:combobox>
			    </td>
			</tr>
			<tr>
				<th>五维架构：</th>
				<td>
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="true"></cui:input>
			    </td>
				<th>数据范围：</th>
				<td>
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="true"></cui:input>
			    </td>
			</tr>
			<tr>
				<th>风险点：</th>
				<td colspan="3">
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="true"></cui:input>
			    </td>
			</tr>
			<tr>
				<th>事项说明：</th>
				<td colspan="3">
					<cui:textarea id="remarks" name="remarks" readonly="true" ></cui:textarea>
			    </td>
			</tr>
			<tr>
				<th>风险等级：</th>
				<td colspan="3">
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="true"></cui:input>
			    </td>
			</tr>
			<tr>
				<th>评估条件：</th>
				<td colspan="3">
					<cui:textarea id="remarks" name="remarks" readonly="true" ></cui:textarea>
			    </td>
			</tr>
			<tr>
				<th>风险数量：</th>
				<td>
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="false"></cui:input>
			    </td>
				<th>扣分值：</th>
				<td>
					<cui:input id="wwjgName" name="wwjgName" width="250" readonly="true"></cui:input>
			    </td>
			</tr>
			<tr>
         		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</th>
				<td colspan="3">
					<cui:textarea id="remarks" name="remarks" readonly="false" ></cui:textarea>
				</td>			
			</tr>
		</table>
	</cui:form>
</div>

<script>
	$.parseDone(function() {
 		var id='${id}';
		if(id) {
			loadForm("formId_lbgl_edit", "${ctx}/aqfxyp/fxcj/getById?id=" + id, function(data) {
				
			});
		}
		// Desc: 加载风险点下拉框
		loadFxdCombobox();
	});
	
	/**
	 * Description: 加载风险点下拉框
	 */
	function loadFxdCombobox() {
		var url = "${ctx}/aqfxyp/fxd/getCombobox";
		$("#fxdId").combobox("reload", url);
	}
	
	/**
	 * Description: 风险点下拉框onChange事件
	 */
	function fxdComboboxOnChange(event, ui) {
		// Desc: 加载评估条件下拉框
		loadPgtjCombobox(ui.value);
	}

	/**
	 * Description: 加载评估条件下拉框
	 */
	function loadPgtjCombobox(fxdId) {
		var url = "${ctx}/aqfxyp/pgtj/getComboboxByFxdId/" + fxdId;
		$("#pgtjId").combobox("reload", url);
	}
</script>