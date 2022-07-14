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
	<cui:form id="formId_updateInfo" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input name="dcbIdnty" componentCls="form-control" readonly="true" ></cui:input>
				</td>
				<th>所属区域：</th>
				<td>
					<cui:combotree id="combId_area" componentCls="form-control" name="dcbAreaId" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" onNodeClick="onAreaTreeClick_sxt" required="true"></cui:combotree>
				</td>
			</tr>
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="dcbName" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>品牌：</th>
				<td>
					<cui:combobox name="dcbBrandIndc" data="doorBrandDate" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>类型：</th>
				<td>
					<cui:combobox name="dcbTypeIndc" data="doorTypeDate" componentCls="form-control"></cui:combobox>
				</td>
				<th>门禁控制器：</th>
				<td>
					<cui:combobox id="combId_doorCtl" name="dcbCtrlIdnty" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>地址：</th>
				<td>
					<cui:input name="dcbAddrs" componentCls="form-control"></cui:input>
				</td>
				<th>门通道号：</th>
				<td>
					<cui:input name="dcbChannelIdnty" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>关联摄像头：</th>
				<td colspan="3">
                    <cui:autocomplete id="comboId_glsxt" name="dcbCameraId" valueField="value" textField="text" multiple="true" postMode="value" multiLineLabel="true"  height="150" componentCls="form-control"></cui:autocomplete>
				</td>
			</tr>
			<tr>
				<th>频道：</th>
				<td>
					<cui:input name="dcbChnnlAddrs" componentCls="form-control"></cui:input>
				</td>
				<th>状态：</th>
				<td>
					<cui:combobox name="dcbSttsIndc" data="doorSttsDate" componentCls="form-control" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
                <th>是否控制：</th>
                <td>
                    <cui:combobox name="dcbWhetherCtrl" data="whetherCtrlDate" componentCls="form-control"></cui:combobox>
                </td>
                <th>监门标志：</th>
                <td>
                    <cui:combobox name="dcbIsAb" data="isAbData" componentCls="form-control"></cui:combobox>
                </td>
				<%--<th>所属部门：</th>
				<td>
					<cui:combobox id="comboId_dprtmnt" name="dcbDprtmntId" componentCls="form-control"></cui:combobox>
				</td>--%>
			</tr>
			<tr>
				<th>所属部门编号：</th>
				<td>
					<%-- <cui:combobox id="comboId_dprtmnt" name="dcbDprtmntId" componentCls="form-control"></cui:combobox> --%>
					<cui:input name="dcbDprtmntId" componentCls="form-control" ></cui:input>
				</td>
				<th>所属部门名称：</th>
				<td>
					<cui:input name="dcbDprtmnt" componentCls="form-control" ></cui:input>
				</td>
			</tr>
			<tr>
				<th>所属房间编号：</th>
				<td>
					<%-- <cui:combobox id="comboId_room" name="dcbRoomId" componentCls="form-control"></cui:combobox> --%>
					<cui:input name="dcbRoomId" componentCls="form-control" ></cui:input>
				</td>
				<th>所属房间名称：</th>
				<td>
					<cui:input name="dcbRoom" componentCls="form-control" ></cui:input>
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
	$.parseDone(function() {

				$("#combId_doorCtl").combobox(
						"reload",
						"${ctx}/doorControl/seachComboData.json?cusNumber="
								+ cusNumber);
				//部门数据请求
				/*$("#comboId_dprtmnt").combobox(
						"reload",
						"${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="
								+ cusNumber);*/

				$("#combId_area").combotree("tree").tree(
						"reload",
						"${ctx}/common/areadevice/findForCombotree.json?cusNumber="
								+ cusNumber + "&deviceType=0");

				setTimeout(function () {
                    $.ajax({
                        type : "post",
                        url : "${ctx}/door/findById.json?id=${ID}",
                        dataType : "json",
                        success : function(data) {
                            if (data.success) {
                                info = data.obj;
                                if (info.dcbAreaId) {
                                    $("#comboId_glsxt").autocomplete({"source":"${ctx}/common/areadevice/findDeviceList.json?cusNumber="
                                    + cusNumber
                                    + "&areaId="
                                    + info.dcbAreaId
                                    + "&deviceType=1"});
                                }

                                $("#formId_updateInfo").form("load", info);
                                $("#comboId_glsxt").autocomplete("setValue", info.dcbCameraId);
                                $("#comboId_glsxt").autocomplete("setText", info.dcbCameraName);
                                $("#combId_area").combotree("setValue", info.dcbAreaId);
                                $("#combId_area").combotree("setText",info.dcbArea);
                            } else {
                                $.messageQueue({
                                    message : data.msg,
                                    cls : "warning",
                                    iframePanel : true,
                                    type : "info"
                                });
                            }
                        },
                        error : function(XMLHttpRequest, textStatus,
                                         errorThrown) {
                            $.messageQueue({
                                message : textStatus,
                                cls : "warning",
                                iframePanel : true,
                                type : "info"
                            });
                        }
                    });
                });
			});

	//发送修改请求
	function update() {
		if ($("#formId_updateInfo").form("valid")) {
			var formData = $("#formId_updateInfo").form("formData");
			formData["dcbArea"] = $("#combId_area").combotree("getText");
            formData["dcbCameraName"] = $("#comboId_glsxt").autocomplete("getText");
//			formData["dcbDprtmnt"] = $("#comboId_dprtmnt").combobox("getText");
			var url = '${ctx}/door/updateDoor.json?id=${ID}';
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
						$("#gridId_door").grid("reload");
						$("#dialogId_door_mjgl").dialog("close");
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
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function clear() {
		$("#formId_updateInfo").form("load", info);
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