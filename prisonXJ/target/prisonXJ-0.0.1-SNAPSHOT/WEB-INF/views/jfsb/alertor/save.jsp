<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_bjq_save" heightStyle="fill">
		<table class="table" style="width: 98%">
				<tr>
					<th>编号：</th>
					<td>
						<cui:input name="abdIdnty" componentCls="form-control" required="true"></cui:input>
					</td>
					<th>所属区域：</th>
					<td>
						<cui:combotree id="areaTreeId" name="abdAreaId"  componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick_bjq"></cui:combotree>
					</td>
				</tr>
				<tr>
					<th>类型：</th>
					<td>
						<cui:combobox id="combId_lx" name="abdTypeIndc" componentCls="form-control" data="abdTypeIndcDate" required="true" onSelect="onComboSelect"></cui:combobox>
					</td>
					<th>关联设备：</th>
					<td>
						<cui:combobox id="combId_bjq" name="abdHostIdnty" componentCls="form-control" onSelect="onHostIdntySelect" ></cui:combobox>
					</td>
				</tr>
				<tr>
					<th>名称：</th>
					<td>
						<cui:input id="nameInput" type="text" name="abdName" componentCls="form-control" required="true"></cui:input>
					</td>
					<th>监舍：</th>
					<td>
						<cui:autocomplete id="abdAddrs" name="abdAddrs" componentCls="form-control" postMode="text" ></cui:autocomplete>
					</td>
				</tr>
				<tr>
					<th>名称前缀：</th>
					<td>
						<cui:input name="abdPreName" componentCls="form-control"></cui:input>
					</td>
					<th>品牌：</th>
					<td>
						<cui:combobox name="abdBrandIndc" data="alertorBrandDate" componentCls="form-control"></cui:combobox>
					</td>
				</tr>
				<tr>
					<th>IP：</th>
					<td>
						<cui:input validType="ip" name="abdIp" componentCls="form-control"></cui:input>
					</td>
					<th>端口：</th>
					<td>
						<cui:input validType="port" name="abdPort" componentCls="form-control"></cui:input>
					</td>
				</tr>
				<tr>
					<th>状态：</th>
					<td>
						<cui:combobox name="abdSttsIndc" data="abdSttsIndcDate" componentCls="form-control" required="true"></cui:combobox>
					</td>
					<th>报警是否生效：</th>
                    <td><cui:combobox id="abdAlertorValid"  name="abdAlertorValid" emptyText="请选择" value="0" onChange="onAlertorValidChange" data="abdAlertorValid_json" componentCls="form-control"></cui:combobox></td>
				</tr>
				<tr>
					<th>报警开始时间：</th>
					<td><cui:datepicker  width="300"  readonly="true" model="timepicker" id="abdAlertorStarttime"  name="abdAlertorStarttime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
					<th>报警结束时间：</th>
					<td><cui:datepicker  width="300"  readonly="true" model="timepicker" id="abdAlertorEndtime"  name="abdAlertorEndtime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
				</tr>
				<tr>
					<th>备注：</th>
					<td>
						<cui:input name="abdRemark" componentCls="form-control"></cui:input>
					</td>
				</tr>
			</table>
		</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="add"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>
<script>

    var abdAlertorValid_json = <%=CodeFacade.loadCode2Json("4.0.1")%>;

	$.parseDone(function() {
	 	$("#areaTreeId").combotree("option", "disabled", true);
		$("#combId_bjq").combobox("option", "disabled", true);  
		$("#areaTreeId").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
	});

	function onAlertorValidChange(e,ui) {
        if(ui.value == '1'){
            $('#abdAlertorStarttime').datepicker({ required:true});
            $('#abdAlertorStarttime').datepicker({readonly:false});
            $('#abdAlertorEndtime').datepicker({ required:true});
            $('#abdAlertorEndtime').datepicker({readonly:false});
        }else{
            $('#abdAlertorStarttime').datepicker({ required:false});
            $('#abdAlertorStarttime').datepicker("setDate","");
            $('#abdAlertorStarttime').datepicker({readonly:true});
            $('#abdAlertorEndtime').datepicker({ required:false});
            $('#abdAlertorEndtime').datepicker("setDate","");
            $('#abdAlertorEndtime').datepicker({readonly:true});
        }
    }
	//发送添加请求
	function add() {
		if ($("#formId_bjq_save").form("valid")) {
			var formData = $("#formId_bjq_save").form("formData");
			formData["abdArea"] = $("#areaTreeId").combotree("getText");
			var ur = '${ctx}/alertor/save.json';
			$.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_alertor").grid("reload");
						$("#dialogId_alertor").dialog("close");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}
	}

	function clear() {
		$("#formId_bjq_save").form("reset");
	}
</script>
</html>