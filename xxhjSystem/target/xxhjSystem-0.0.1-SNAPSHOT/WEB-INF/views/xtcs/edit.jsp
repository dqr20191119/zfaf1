<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<div>
	<cui:form id="formId_xtcs_edit" heightStyle="fill">
		<cui:input type='hidden' id="id" name="id" />
		<table class="table" style="width: 98%;">
			<tr>
				<th>参数编码:</th>
				<td>
					<cui:input id="csbm" name="csbm" type="text" required="true" componentCls="form-control"/>
				</td>
			</tr>
			<tr>
				<th>参数名称:</th>
				<td>
					<cui:input id="csmc" name="csmc" type="text" required="true" componentCls="form-control"/>
				</td>
			</tr>
			<tr>
				<th>参数值:</th>
				<td>
					<%--<cui:input id="csz" name="csz" type="text" required="true" componentCls="form-control"/>--%>
                    <cui:combobox id="csz" name="csz" componentCls="form-control" data="combox_csz"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>备注说明:</th>
				<td>
					<cui:textarea id="bz" name="bz" componentCls="form-control" height="130"></cui:textarea>
				</td>
			</tr>
		</table>
	</cui:form>
</div>

<script>
	$.parseDone(function() {
		// 参数数据初始化
		initXtcs();
	});


	/**
	 * 表单数据填充
	 */
	function initXtcs() {
		var id = '${id}';// 参数编号

		if(id == null || id == '') {
			$("form[id='formId_xtcs_edit']").find("#id").textbox("setValue", "");
			$("form[id='formId_xtcs_edit']").find("#csbm").textbox({disabled: false});
		} else {
			var url = "${ctx}/xtcs/queryById.json";
			var params = {};
			if(id) {
				params['id'] = id;
			}

			var callBack = function(data) {
				if (data.success) {
					fillXtcs(data.obj);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			};
			ajaxTodo(url, params, callBack);
		}
	}

	/**
	 * 填充参数数据
	 * @param zhddCommand
	 */
	function fillXtcs(xtcs) {
		var formObj = $("form[id='formId_xtcs_edit']");
		formObj.find("#id").textbox("setValue", xtcs.id);
		formObj.find("#csbm").textbox("setValue", xtcs.csbm);
		formObj.find("#csbm").textbox({disabled: true});
		formObj.find("#csmc").textbox("setValue", xtcs.csmc);
		formObj.find("#csz").combobox("setValue", xtcs.csz);
		formObj.find("#bz").textbox("setValue", xtcs.bz);
	}
</script>