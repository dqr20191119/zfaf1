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
	<cui:form id="formId_djzj_save" heightStyle="fill">
		<table class="table"style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input id="tseIdnty" name="tseIdnty" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>IP：</th>
				<td>
					<cui:input id="tseIp" validType="ip" name="tseIp" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="tseName" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>序列号：</th>
				<td>
					<cui:input name="tseSn" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>品牌：</th>
				<td>
					<cui:combobox name="tseBrand" componentCls="form-control" data="djzj_brandData" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<%--<th>所属部门：</th>
				<td>
					<cui:combobox id="comboId_dprtmnt" name="tseDprtmntId" componentCls="form-control"></cui:combobox>
				</td>--%>
				<th>状态：</th>
				<td>
					<cui:combobox name="tseSttsIndc" componentCls="form-control" data="djzj_sttsData" required="true"></cui:combobox>
				</td>
				<th>所属区域：</th>
				<td>
					<cui:combotree id="areaTreeId" componentCls="form-control" name="tseAreaId" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true"></cui:combotree>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<cui:input name="tseRemark" componentCls="form-control"></cui:input>
				</td>
				<th>上级主机：</th>
				<td>
					<cui:combobox id="parent" name="tseParentId" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>电脑IP：</th>
				<td colspan="3" style="text-align: left;">
					<cui:input width="600" name="tsePcIp" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>说明：</th>
				<td colspan="3" style="text-align: left;">
				填写电脑IP时多个地址之间请使用逗号","分隔，并且以逗号","开始和结束，例如：<span style="color:red;"><strong>,192.168.0.1,192.168.0.2,</strong></span>
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
		//部门数据请求
		//$("#comboId_dprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		$("#areaTreeId").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#parent").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&deviceType=6");
	});

	//发送添加请求
	function add() {
		if ($("#formId_djzj_save").form("valid")) {
			var formData = $("#formId_djzj_save").form("formData");
			formData["tseArea"] = $("#areaTreeId").combotree("getText");
			//formData["tseDprtmnt"] = $("#comboId_dprtmnt").combobox("getText");
            //校验电脑IP是否符合格式
            if(formData["tsePcIp"] && !checkPcIp(formData["tsePcIp"]) ){
                $.messageQueue({ message : "电脑IP输入格式有误!", cls : "warning", iframePanel : true, type : "info" });
			    return;
			}
			var ur = '${ctx}/talkBackServer/save.json';
            $.loading({text:"正在处理中，请稍后..."});
            $.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
                    $.loading("hide");
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_talkBackServer").grid("reload");
						// $("#dialogId_talkBackServer").dialog("close");
                        $("#tseIdnty").textbox("setText", "");

					} else {
                        $.loading("hide");
                        $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.loading("hide");
                    $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function clear() {
		$("#formId_djzj_save").form("reset");
	}

    function checkPcIp(ipstr){
        var reg = /^,((\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5]),)+$/;
        return reg.test(ipstr);
    }

</script>
</html>