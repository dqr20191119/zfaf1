<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_lbgl_query">
		<table class="table">
			<tr><th>类别名称 ：</th>
				<td><cui:input name="dcaCategoryName"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_lbgl" data="toolbar_lbgl"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_lbgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/zbgl/lbgl/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dcaCategoryName" align="center">类别名称</cui:gridCol>
			<cui:gridCol name="dcaDprtmntId" formatter="formatDrptmntName" align="center">部门</cui:gridCol>
			<cui:gridCol name="dcaRemark" align="center">备注</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_lbgl" />
	</cui:grid>
	
	<cui:dialog id="dialogId_lbgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_lbgl">
	</cui:dialog>
</div>

<script>

	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	$.parseDone(function() {
		
	});
	
	var toolbar_lbgl = [{
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
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}];

	var buttons_lbgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_lbgl").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/lbgl/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_lbgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/lbgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_lbgl").dialog({
			width : 360,
			height : 300,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_lbgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_lbgl_query").form("formData");
		$("#gridId_lbgl").grid("option", "postData", formData);
		$("#gridId_lbgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_lbgl_query").form("reset");
	}
	
	function formatDrptmntName(cellvalue, options, rowObject) {
		var value = '';
		if( rowObject.dcaDprtmntId) {
			var dcaDprtmntIds = (rowObject.dcaDprtmntId).split(",");
			for(var i = 0;i < dcaDprtmntIds.length; i++) {
				
				if(i == 0) {
					value =convertColVal(combobox_bm, dcaDprtmntIds[i]);
				}else {
					value = value+ "," +convertColVal(combobox_bm, dcaDprtmntIds[i]);
				}
	    	}
		}
		return value; 
	}

	function saveOrUpdate() {
		
		var validFlag = $("#formId_lbgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_lbgl_edit").form("formData");
       
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_lbgl").dialog("close");
					$("#gridId_lbgl").grid("reload");
					
				} else if(data.code == "2") {
					$.message({message:data.msg, cls:"error"}); 
					
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
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_lbgl").grid("option", "selarrrow");	
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
					}
					$.loading({text:"正在处理中，请稍后..."});
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/lbgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_lbgl").grid("reload");
							} else if(data.code == "3") {
								$.message({message:"该类别已关联其他信息，无法删除！", cls:"waring"});
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
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}	
</script>