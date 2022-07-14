<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_wwjgwh_query">
		<table class="table">
			<tr><th>名称：</th>
				<td><cui:input name="name"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_wwjgwh" data="toolbar_wwjgwh"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_wwjgwh" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/wwjg/wwjgwh/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="name" align="center">名称</cui:gridCol>
			<cui:gridCol name="code"  align="center">编码</cui:gridCol>
			<cui:gridCol name="bz" align="center">备注</cui:gridCol>
			<%-- <cui:gridCol name="pf" align="center">评分</cui:gridCol> --%>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_wwjgwh" />
	</cui:grid>
	
	<cui:dialog id="dialogId_wwjgwh" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_wwjgwh">
	</cui:dialog>
</div>

<script>


	$.parseDone(function() {
		
	});
	
	var toolbar_wwjgwh = [{
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
	}
	/*, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}*/
	];

	var buttons_wwjgwh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_wwjgwh").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/wwjg/wwjgwh/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_wwjgwh").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/wwjg/wwjgwh/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_wwjgwh").dialog({
			width : 360,
			height : 360,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_wwjgwh").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_wwjgwh_query").form("formData");
		$("#gridId_wwjgwh").grid("option", "postData", formData);
		$("#gridId_wwjgwh").grid("reload");
	}
	
	function reset() {
		
		$("#formId_wwjgwh_query").form("reset");
	}

	function saveOrUpdate() {
		
		var validFlag = $("#formId_wwjgwh_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_wwjgwh_edit").form("formData");
       console.log(formData);
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/wwjg/wwjgwh/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_wwjgwh").dialog("close");
					$("#gridId_wwjgwh").grid("reload");
					
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
		
		var selarrrow = $("#gridId_wwjgwh").grid("option", "selarrrow");	
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
						url : '${ctx}/wwjg/wwjgwh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_wwjgwh").grid("reload");
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