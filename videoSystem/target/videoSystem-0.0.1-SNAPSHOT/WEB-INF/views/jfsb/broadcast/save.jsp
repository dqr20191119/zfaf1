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
	<cui:form id="formId_gb_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input name="bbdIdnty" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>IP端口：</th>
				<td>
					<cui:input validType="port" name="bbdPort" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="bbdName" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>安装地址：</th>
				<td>
					<cui:input name="bbdAddrs" componentCls="form-control" ></cui:input>
				</td>
			</tr>
			<tr>
				<th>品牌：</th>
				<td>
					<cui:combobox name="bbdBrand" componentCls="form-control" data="gb_brandData" required="true"></cui:combobox>
				</td>
				<th>区域：</th>
				<td>
					<cui:combotree id="combId_area" name="bbdAreaId" componentCls="form-control"  url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick_sxt"></cui:combotree>
				</td>

			</tr>
			<tr>
				<th>型号：</th>
				<td>
					<cui:input name="bbdModel" componentCls="form-control"  ></cui:input>
				</td>
				<%-- <th>所属部门：</th>
				<td>
					<cui:combobox id="combId_dprtmnt" name="bbdDprtmntId" componentCls="form-control" ></cui:combobox>
				</td> --%>
			</tr>
            <tr>
                <th>关联摄像头：</th>
                <td colspan="3">
                   <cui:autocomplete  id="comboId_glsxt" name="bbdCameraId" valueField="value" textField="text" multiple="true" postMode="value" multiLineLabel="true"  height="150" componentCls="form-control"></cui:autocomplete>
                </td>
            </tr>
			<tr>
				<th>IP地址：</th>
				<td>
					<cui:input validType="ip" name="bbdIpAddrs" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>备注：</th>
				<td>
					<cui:input name="bbdRemark" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<cui:combobox name="bbdSttsIndc" componentCls="form-control" data="gb_sttsData" required="true"></cui:combobox>
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
	$.parseDone(function() {
		// 部门数据请求
		// $("#combId_dprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		// 区域数据请求
		$("#combId_area").combotree("tree").tree("reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber +"&deviceType=0");
	});

	//发送添加请求
	function add() {
		if ($("#formId_gb_save").form("valid")) {
			var formData = $("#formId_gb_save").form("formData");
			// formData['bbdDprtmnt'] = $("#combId_dprtmnt").combobox("getText");
			formData['bbdArea'] = 	$("#combId_area").combotree("getText");
            formData["bbdCameraName"] = $("#comboId_glsxt").autocomplete("getText");
			$.ajax({
				type : 'post',
				url : '${ctx}/broadcast/save.json',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_broadcast").grid("reload");
						$("#dialogId_broadcast").dialog("close");
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
		$("#formId_gb_save").form("reset");
	}
	
	/* 区域树下拉点击事件 */
	function onAreaTreeClick_sxt(event, ui) {
        $("#comboId_glsxt").autocomplete("setValue","");
        var cameraArray = $.ajax({
            url: "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=1",
            async: false
        }).responseText;
        $("#comboId_glsxt").autocomplete( {"source":JSON.parse(cameraArray)});
        //$("#comboId_glsxt").autocomplete( {"source": "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=1"});
	}
</script>
</html>