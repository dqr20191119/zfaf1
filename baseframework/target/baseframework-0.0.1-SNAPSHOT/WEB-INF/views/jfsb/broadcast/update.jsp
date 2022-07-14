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
	<cui:form id="formId_gb_update" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input name="bbdIdnty" componentCls="form-control" required="true" readonly="true"></cui:input>
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
					<cui:input name="bbdAddrs" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>品牌：</th>
				<td>
					<cui:combobox name="bbdBrand" componentCls="form-control" data="gb_brandData" required="true"></cui:combobox>
				</td>
				<th>所属区域：</th>
				<td>
					<cui:combotree id="combId_area" componentCls="form-control" name="bbdAreaId" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick_sxt"></cui:combotree>
				</td>

			</tr>
			<tr>
				<th>型号：</th>
				<td>
					<cui:input name="bbdModel" componentCls="form-control"></cui:input>
				</td>
				<%-- <th>所属部门：</th>
				<td>
					<cui:combobox id="combId_dprtmnt" name="bbdDprtmntId" componentCls="form-control"></cui:combobox>
				</td> --%>
			</tr>
			<tr>
				<th>关联摄像头：</th>
				<td colspan="3">
                    <cui:autocomplete id="comboId_glsxt" name="dcbCameraId" valueField="value" textField="text" multiple="true" postMode="value" multiLineLabel="true"  height="150" componentCls="form-control"></cui:autocomplete>
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
		<cui:button label="提交" text="false" onClick="update"></cui:button>
		<cui:button label="重置" text="false" onClick="reset"></cui:button>
	</div>
</div>



<script>
	var info;
	$.parseDone(function() {
		// 区域数据请求
		$("#combId_area").combotree("tree").tree("reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		// 部门数据请求
		// $("#combId_dprtmnt").combobox("reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		
		setTimeout(function () {
			var url = "${ctx}/broadcast/findById.json?id=${ID}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					debugger;
					if (data.success) {
						info = data.obj;
						console.log("broadcast update.jsp parseDone info = " + JSON.stringify(info));
                        if (info.bbdAreaId) {
                            $("#comboId_glsxt").autocomplete({"source":"${ctx}/common/areadevice/findDeviceList.json?cusNumber="
                            + cusNumber
                            + "&areaId="
                            + info.bbdAreaId
                            + "&deviceType=1"});
                        }
						$("#formId_gb_update").form("load", info);
                        $("#comboId_glsxt").autocomplete("setValue", info.bbdCameraId);
                        $("#comboId_glsxt").autocomplete("setText", info.bbdCameraName);
                        $("#combId_area").combotree("setValue", info.bbdAreaId);
                        $("#combId_area").combotree("setText",info.bbdArea);
					} else {
						$.messageQueue({
							message : data.msg,
							cls : "warning",
							iframePanel : true,
							type : "info"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({
						message : textStatus,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			});
		}, 500);
	});

	//发送修改请求
	function update() {
		if ($("#formId_gb_update").form("valid")) {
			var formData = $("#formId_gb_update").form("formData");
			// formData['bbdDprtmnt'] = $("#combId_dprtmnt").combobox("getText");
			formData['bbdArea'] = $("#combId_area").combotree("getText");
            formData["bbdCameraName"] = $("#comboId_glsxt").autocomplete("getText");
			var url = '${ctx}/broadcast/update/info.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({
							message : data.msg,
							cls : "success",
							iframePanel : true,
							type : "info"
						});
						$("#gridId_broadcast").grid("reload");
						$("#dialogId_broadcast").dialog("close");
					} else {
						$.messageQueue({
							message : data.msg,
							cls : "warning",
							iframePanel : true,
							type : "info"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({
						message : XMLHttpRequest,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function reset() {
		$("#formId_gb_update").form("load", info);
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