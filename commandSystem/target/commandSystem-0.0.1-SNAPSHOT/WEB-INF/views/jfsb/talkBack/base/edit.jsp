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
	<cui:form id="formId_djfj_info"  heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input id="inputId_tbdIdnty" name="tbdIdnty" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>所属部门：</th>
				<td>
					<cui:combobox id="comboId_dprtmnt" name="tbdDprtmntId" componentCls="form-control" ></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="tbdName" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>IP：</th>
				<td>
					<cui:input validType="ip" name="tbdIpAddrs" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>名称前缀：</th>
				<td>
					<cui:input name="tbdPreName" componentCls="form-control"></cui:input>
				</td>
				<th>所属主机：</th>
				<td>
					<cui:combobox id="comboId_main" name="tbdMainIdnty" componentCls="form-control" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>地址：</th>
				<td>
					<cui:input name="tbdAddrs" componentCls="form-control"></cui:input>
				</td>
				<th>频道：</th>
				<td>
					<cui:input name="tbdChnnlAddrs" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<cui:combobox name="tbdSttsIndc" data="djfj_sttsData" componentCls="form-control" required="true"></cui:combobox>

				</td>
				<th>品牌：</th>
				<td>
					<cui:combobox name="tbdBrand" data="djfj_brandData" componentCls="form-control" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>所属区域：</th>
				<td>
					<cui:combotree id="areaTreeId" name="tbdAreaId" componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true"></cui:combotree>
				</td>
				<th>备注：</th>
				<td>
					<cui:input name="tbdRemark" componentCls="form-control"></cui:input>
				</td>
			</tr>
		</table>

	</cui:form>
	<div id="divId_btn" class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="submit"></cui:button>
		<cui:button label="重置" text="false" onClick="clearInfo"></cui:button>
	</div>
</div>



<script>
	var info;
	var type = "${type}";
	$.parseDone(function() {
		//部门数据请求
		$("#comboId_dprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		$("#areaTreeId").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#comboId_main").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&deviceType=6");

		if (type == "1") {
			if (tbdMainIdnty != "") {
				$("#comboId_main").combobox("setValue", tbdMainIdnty);
				$("#comboId_main").combobox("option", "readonly", true);
			}
		}

		if (type == "2") {
			$("#inputId_tbdIdnty").textbox("option","readonly",true);
		}
		
		if (type == "3") {
			$("#divId_btn").hide();//按钮隐藏
			$("#formId_djfj_info").form("setReadOnly", true);
		}

		if (type == "3" || type == "2") {
			var url = "${ctx}/talkBackBase/findById.json?id=${ID}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						info = data.obj;
						$("#formId_djfj_info").form("load", info);
						setTimeout(function(){
                            $("#areaTreeId").combotree("setValue",info.tbdAreaId);
                            $("#areaTreeId").combotree("setText",info.tbdArea);
                        });
                    } else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
	});

	//发送添加请求
	function submit() {
		if ($("#formId_djfj_info").form("valid")) {
			var formData = $("#formId_djfj_info").form("formData");
			formData["tbdArea"] = $("#areaTreeId").combotree("getText");
			formData["tbdDprtmnt"] = $("#comboId_dprtmnt").combobox("getText");

			var ur = "";
			if (type == "1") {
				ur = "${ctx}/talkBackBase/save.json";
			}
			if (type == "2") {
				ur = "${ctx}/talkBackBase/update/info.json?id=${ID}";
			}

			$.ajax({
					type : "post",
					url : ur,
					data : formData,
					dataType : "json",
					success : function(data) {
						if (data.success) {
							$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							var url = "${ctx}/talkBackBase/listAll.json?tseCusNumber=" + cusNumber + "&tbdMainIdnty=" + tbdMainIdnty + "&P_orders=tbd_crte_time,desc";
							$("#gridId_talkBackBase").grid("reload", url);
							$("#dialogId_talkBackBase").dialog("close");
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

	function clearInfo() {
		if (type == "2") {
			$("#formId_djfj_info").form("load", info);
		}else {
			$("#formId_djfj_info").form("reset");
		}
	}
</script>
</html>