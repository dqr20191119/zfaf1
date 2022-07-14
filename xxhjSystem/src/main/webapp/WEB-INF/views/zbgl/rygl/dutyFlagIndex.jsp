<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<%--<cui:form id="formId_duty_flag_query">
		<table class="table">
			<tr><th>班次名称 ：</th>
				<td><cui:input name="dorDutyOrderName"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>--%>
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_duty_flag" data="toolbar_duty_flag"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_duty_flag" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
	url="${ctx}/zbgl/rygl/seachDutyFlagData?cusNumber=${cusNumber}"	 rowNum="15">
		<cui:gridCols>
            <cui:gridCol name="zhzFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">指挥长最后值班人员</cui:gridCol>
            <cui:gridCol name="wzbzWFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">女值班长工作日最后值班人员</cui:gridCol>
            <cui:gridCol name="wzbzHFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">女值班长节假日最后值班人员</cui:gridCol>
			<cui:gridCol name="wzbyWFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">女值班员工作日最后值班人员</cui:gridCol>
            <cui:gridCol name="wzbyHFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">女值班员节假日最后值班人员</cui:gridCol>
            <cui:gridCol name="mzbzWFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">男值班长工作日最后值班人员</cui:gridCol>
            <cui:gridCol name="mzbzWNFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">男值班员工作日晚班最后值班人员</cui:gridCol>
            <cui:gridCol name="mzbzHFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">男值班长节假日最后值班人员</cui:gridCol>
            <cui:gridCol name="mzbyWFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">男值班员工作日最后值班人员</cui:gridCol>
            <cui:gridCol name="mzbyHFlag" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':commonbox_ry}">男值班员节假日最后值班人员</cui:gridCol>
            <cui:gridCol name="zhzZbFlagDutyDate" align="center">指挥长值中班值班长最后时间</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_duty_flag" />
	</cui:grid>
	
	<cui:dialog id="dialogId_duty_flag" autoOpen="false" resizable="false"  reLoadOnOpen="true"  autoDestroy="true" buttons="buttons_duty_flag">
	</cui:dialog>
</div>
 
<script>
    var cusNumber = jsConst.CUS_NUMBER;
    var ry_autocomplete=<%=RyCommon.getCommonbox("4300")%>
    var commonbox_ry =<%=RyCommon.getCommonbox2("4300")%>
    $.parseDone(function() {
        /*$.ajax({
            type : 'post',
            url : '${ctx}/zbgl/rygl/seachDutyFlagData',
            data: {"cusNumber":cusNumber},
            dataType : 'json',
            success : function(data) {
                $("#gridId_duty_flag").grid("reload",data);
            }
        });*/

	});
	
	var toolbar_duty_flag = [{
		"type" : "button",
		"id" : "btnId_duty_edit",
		"label" : "修改",
		"onClick" : "openDutyDailog",
		"componentCls" : "btn-primary"
	}];

	var buttons_duty_flag = [{
		text: "保存",
		id: "btnId__duty_save",
		click: function () {
			
			save();
		}        
	}, {
	    text: "取消",
	    id: "btnId__duty_cancel",
	    click: function () {
	    	
	    	$("#dialogId_duty_flag").dialog("close");
	    }            
	}];

	function openDutyDailog(event, ui) {
		
		var url;
		if(ui.id == "btnId_duty_edit") {
			
			var selarrrow = $("#gridId_duty_flag").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/rygl/todutyFlagEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_duty_flag").dialog({
			width : 1000,
			height : 500,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_duty_flag").dialog("open");
	}
	


	function save() {
		
		var validFlag = $("#formId_duty_flag_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_duty_flag_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/rygl/updateDutyFlagById',
			data: formData,
			dataType : 'json',
			success : function(data) {
				
				$.loading("hide");
				if(data.code == 200) {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_duty_flag").dialog("close");
					$("#gridId_duty_flag").grid("reload");
				} else {
					$.message({message:"操作失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
</script>