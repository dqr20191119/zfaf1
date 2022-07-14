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
	<cui:form id="formId_bjq_update" heightStyle="fill">
		<table class="table" style="width: 98%">
				<tr>
					<th>编号：</th>
					<td>
						<cui:input name="abdIdnty" componentCls="form-control"  readonly="true"></cui:input>
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
					<td><cui:combobox id="abdAlertorValid"  name="abdAlertorValid" emptyText="请选择"  onChange="onAlertorValidChange" data="abdAlertorValid_json" componentCls="form-control"></cui:combobox></td>
				</tr>
                <tr>
                    <th>报警开始时间：</th>
                    <td><cui:datepicker  width="300"  model="timepicker" id="abdAlertorStarttime"  name="abdAlertorStarttime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
                    <th>报警结束时间：</th>
                    <td><cui:datepicker  width="300" model="timepicker" id="abdAlertorEndtime"  name="abdAlertorEndtime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
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
		<cui:button label="提交" text="false" onClick="update"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>



<script>
	var info;
    var abdAlertorValid_json = <%=CodeFacade.loadCode2Json("4.0.1")%>;
	$.parseDone(function() {
		$("#areaTreeId").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		var url = "${ctx}/alertor/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#combId_bjq").combobox("option", "disabled", true);
					if(info.abdTypeIndc == "6"){
						$("#combId_bjq").combobox("option", "disabled", false);
						$("#combId_bjq").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + info.abdCusNumber + "&areaId=" + info.abdAreaId + "&deviceType=1");
					}
					if(info.abdTypeIndc == "9"){
						$("#combId_bjq").combobox("option", "disabled", false);
						$("#combId_bjq").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + info.abdCusNumber + "&areaId=" + info.abdAreaId + "&deviceType=2");
					}
					$("#formId_bjq_update").form("load", info);
					if(info.abdAreaId != undefined && info.abdAreaId != 'undefined' && info.abdAreaId != '' && info.abdAreaId != null){
						$("#areaTreeId").combotree("setText",info.abdArea);
					}
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
                onAlertorValidChange({},{value:info.abdAlertorValid});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
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

	//发送修改请求
	function update() {
		if ($("#formId_bjq_update").form("valid")) {
			var formData = $("#formId_bjq_update").form("formData");
			formData["abdArea"] = $("#areaTreeId").combotree("getText");
			var url = '${ctx}/alertor/update/info.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
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
		$("#formId_bjq_update").form("load", info);
	}
</script>
</html>