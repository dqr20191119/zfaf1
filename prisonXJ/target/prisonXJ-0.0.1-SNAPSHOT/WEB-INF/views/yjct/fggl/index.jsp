<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_fggl_query">
		<table class="table">			
			<tr>
				<td>法规名称：</td>
				<td><cui:input name="lriLawsName"></cui:input></td>				 
				<td>法规类别：</td>
				<td><cui:combobox name="lriLawsType" data="combobox_yjct_fgType"></cui:combobox></td>				 
				 <td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_fggl" data="toolbar_fggl"></cui:toolbar>			
		<cui:grid id="gridId_fggl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/fggl/searchData"
			sortname="lriCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="lriLawsName" width="150">法规名称</cui:gridCol>
				<cui:gridCol name="lriLawsType" width="150" formatter="formatLriType">法规类别</cui:gridCol>
				<cui:gridCol name="lriLawsContent" width="500">法规内容</cui:gridCol>				 
			</cui:gridCols>
			<cui:gridPager gridId="gridId_fggl" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_fggl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_fggl">
	</cui:dialog>
</div>

<script>
	 	
	var combobox_yjct_fgType = <%=CodeFacade.loadCode2Json("4.20.3")%>;
	
 	var toolbar_fggl = [{
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

	var buttons_fggl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_fggl").dialog("close");
	    }            
	}];

	
	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/fggl/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_fggl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/fggl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_fggl").dialog({
			width : 800,
			height : 400,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_fggl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_fggl_query").form("formData");
		$("#gridId_fggl").grid("option", "postData", formData);
		$("#gridId_fggl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_fggl_query").form("reset");
	}
		
	function formatLriType(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_fgType, cellvalue);	
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_fggl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_fggl_edit").form("formData");
			
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/fggl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_fggl").dialog("close");
					$("#gridId_fggl").grid("reload");
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
		
		var selarrrow = $("#gridId_fggl").grid("option", "selarrrow");			
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
						url : '${ctx}/yjct/fggl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_fggl").grid("reload");
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