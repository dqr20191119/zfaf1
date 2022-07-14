<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_pgtjgl_query">
		<table class="table">
			<tr><th>评估条件：</th>
				<td><cui:input name="name"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_pgtjgl" data="toolbar_pgtjgl"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_pgtjgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/wwjg/pgtjgl/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="wwjgName" align="center">五维架构</cui:gridCol>
			<cui:gridCol name="sjfwName" align="center">数据范围</cui:gridCol>
			<cui:gridCol name="fxdjName" align="center">风险等级</cui:gridCol>
			<cui:gridCol name="fxdName" align="center">风险点</cui:gridCol>
			<cui:gridCol name="name" align="center">评估条件</cui:gridCol>
			<cui:gridCol name="kfz" align="center">分值</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_pgtjgl" />
	</cui:grid>
	
	<cui:dialog id="dialogId_pgtjgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_pgtjgl">
	</cui:dialog>
</div>

<script>


	$.parseDone(function() {
		
	});
	
	var toolbar_pgtjgl = [{
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

	var buttons_pgtjgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	$("#dialogId_pgtjgl").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/wwjg/pgtjgl/toEdit";
		}else if(ui.id == "btnId_edit") {	
			var selarrrow = $("#gridId_pgtjgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/wwjg/pgtjgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_pgtjgl").dialog({
			width : 360,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_pgtjgl").dialog("open");
	}
	
	function search() {
		var formData = $("#formId_pgtjgl_query").form("formData");
		$("#gridId_pgtjgl").grid("option", "postData", formData);
		$("#gridId_pgtjgl").grid("reload");
	}
	
	function reset() {
		$("#formId_pgtjgl_query").form("reset");
	}

	function saveOrUpdate() {
		var validFlag = $("#formId_pgtjgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_pgtjgl_edit").form("formData");
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/wwjg/pgtjgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_pgtjgl").dialog("close");
					$("#gridId_pgtjgl").grid("reload");
					
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
		var selarrrow = $("#gridId_pgtjgl").grid("option", "selarrrow");	
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
						url : '${ctx}/wwjg/pgtjgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_pgtjgl").grid("reload");
							}   else {
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