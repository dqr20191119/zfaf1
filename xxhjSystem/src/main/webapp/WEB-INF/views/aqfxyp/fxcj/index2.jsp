<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_fxcj_query">
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
		<cui:toolbar id="toolbarId_fxcj" data="toolbar_fxcj"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_fxcj" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/aqfxyp/fxcj/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="pgtjId" align="center">类别名称</cui:gridCol>
			<cui:gridCol name="remarks" align="center">部门</cui:gridCol>
			<cui:gridCol name="status" align="center">备注</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_fxcj" />
	</cui:grid>
	
	<cui:dialog id="dialogId_fxcj" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_fxcj">
	</cui:dialog>
</div>

<script>
	var toolbar_fxcj = [{
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

	var buttons_fxcj = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_fxcj").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/aqfxyp/fxcj/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_fxcj").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/aqfxyp/fxcj/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_fxcj").dialog({
			width : 800,
			height : 800,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_fxcj").dialog("open");
	}
	
	function search() {
		var formData = $("#formId_fxcj_query").form("formData");
		$("#gridId_fxcj").grid("option", "postData", formData);
		$("#gridId_fxcj").grid("reload");
	}
	
	function reset() {
		$("#formId_fxcj_query").form("reset");
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_fxcj_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_fxcj_edit").form("formData");
       
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/aqfxyp/fxcj/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_fxcj").dialog("close");
					$("#gridId_fxcj").grid("reload");
					
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
		
		var selarrrow = $("#gridId_fxcj").grid("option", "selarrrow");	
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
						url : '${ctx}/aqfxyp/fxcj/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_fxcj").grid("reload");
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