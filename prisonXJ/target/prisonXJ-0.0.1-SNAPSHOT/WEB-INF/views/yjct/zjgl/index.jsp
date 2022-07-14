<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_zjgl_query">
		<table class="table">			
			<tr>
				<td>专家名称：</td>
				<td><cui:input name="epiExpertName"></cui:input></td>		 
				<td>应急特长：<td>
				<td><cui:input name="epiSpecialty"></cui:input></td>
			 	<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_zjgl" data="toolbar_zjgl"></cui:toolbar>			
		<cui:grid id="gridId_zjgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/zjgl/searchData" frozenColumns="true" shrinkToFit="false" 
			sortname="epiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true" frozen="true">id</cui:gridCol>
				<cui:gridCol name="epiExpertName" width="100" frozen="true">专家名称</cui:gridCol>
				<cui:gridCol name="epiCompany" width="200" formatter="formatEpiCompany">所属单位</cui:gridCol>
				<cui:gridCol name="epiPost" width="100">职位</cui:gridCol>
				<cui:gridCol name="epiSex" width="100" formatter="formatEpiSex">性别</cui:gridCol>
				<cui:gridCol name="epiAge" width="100">年龄</cui:gridCol>
				<cui:gridCol name="epiFunction" width="100">职能</cui:gridCol>
				<cui:gridCol name="epiType" width="100">类别</cui:gridCol>
				<cui:gridCol name="epiSpecialty" width="200">应急特长</cui:gridCol>
				<cui:gridCol name="epiPhone" width="100">电话</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_zjgl" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_zjgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_zjgl">
	</cui:dialog>
</div>

<script>
	 	
	var combobox_yjct_sex = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;

	var toolbar_zjgl = [{
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

	var buttons_zjgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zjgl").dialog("close");
	    }            
	}];

	
	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/zjgl/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_zjgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/zjgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		
		$("#dialogId_zjgl").dialog({
			width : 800,
			height : 400,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_zjgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_zjgl_query").form("formData");
		$("#gridId_zjgl").grid("option", "postData", formData);
		$("#gridId_zjgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_zjgl_query").form("reset");
	}
	
	function formatEpiCompany(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_jy, cellvalue);
	}
	
	function formatEpiSex(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_sex, cellvalue);
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_zjgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_zjgl_edit").form("formData");
			
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_zjgl").dialog("close");
					$("#gridId_zjgl").grid("reload");
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
		
		var selarrrow = $("#gridId_zjgl").grid("option", "selarrrow");			
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
						url : '${ctx}/yjct/zjgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"}); 
								$("#gridId_zjgl").grid("reload");
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