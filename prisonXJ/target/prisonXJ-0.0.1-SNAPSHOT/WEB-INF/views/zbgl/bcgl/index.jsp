<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_bcgl_query">
		<table class="table">
			<tr><th>班次名称 ：</th>
				<td><cui:input name="dorDutyOrderName"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_bcgl" data="toolbar_bcgl"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_bcgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/zbgl/bcgl/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dorDutyOrderName" align="center">班次名称</cui:gridCol>
			<cui:gridCol name="dorStartTime" align="center">起始时间</cui:gridCol>
			<cui:gridCol name="dorEndTime" align="center">结束时间</cui:gridCol>
			<cui:gridCol name="dorStatisticsType" align="center" formatter="convertCode" revertCode="true"  formatoptions="{'data':tjlb_data}">值班类别</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_bcgl" />
	</cui:grid>
	
	<cui:dialog id="dialogId_bcgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_bcgl">
	</cui:dialog>
</div>
 
<script>
    var tjlb_data = <%=CodeFacade.loadCode2Json("4.20.59")%>;//统计类别
	
    $.parseDone(function() {
		 
	});
	
	var toolbar_bcgl = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteById",
		"componentCls" : "btn-primary"
	}];

	var combox_dorDutyOrderName =[
		{value:"早班",text:"早班"},
		{value:"中班",text:"中班"},
		{value:"晚班",text:"晚班"},
		{value:"指挥长",text:"指挥长"}
	];
	
	var buttons_bcgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_bcgl").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/bcgl/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_bcgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/bcgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_bcgl").dialog({
			width : 360,
			height : 350,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_bcgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_bcgl_query").form("formData");
		$("#gridId_bcgl").grid("option", "postData", formData);
		$("#gridId_bcgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_bcgl_query").form("reset");
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_bcgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_bcgl_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/bcgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_bcgl").dialog("close");
					$("#gridId_bcgl").grid("reload");
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
	
	function deleteById() {
		
		var selarrrow = $("#gridId_bcgl").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length == 1) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					
					$.loading({text:"正在处理中，请稍后..."});
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/bcgl/deleteById',
						data: {
							id : selarrrow[0]
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_bcgl").grid("reload");
							} else if(data.code == "3") {
								$.message({message:"该班次已关联其他信息，无法删除！", cls:"error"});
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
			}); 		
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}
	}	
</script>