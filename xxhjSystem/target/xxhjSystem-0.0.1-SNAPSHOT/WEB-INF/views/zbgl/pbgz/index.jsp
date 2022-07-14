<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_gwgl_query">
		<table class="table">
			<tr><th>岗位名称 ：</th>
				<td><cui:input name="cdjJobName"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_gwgl" data="toolbar_gwgl"></cui:toolbar>
	</div>	
			
	<cui:grid id="gridId_gwgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/zbgl/gwgl/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true" >id</cui:gridCol>
			<cui:gridCol name="cdjJobName" align="center" >岗位名称</cui:gridCol>
			<cui:gridCol name="dcaCategoryName" align="center">类别</cui:gridCol>
			<cui:gridCol name="cdjJobCode" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_jbdJobCode
				}">岗位编码</cui:gridCol>
			<cui:gridCol name="cdjRemark" align="center">备注</cui:gridCol>
			<cui:gridCol name="cdjCrteUser" align="center">创建人</cui:gridCol>
			<cui:gridCol name="cdjCrteTime" align="center">创建时间</cui:gridCol>
			<cui:gridCol name="cdjUpdtUser" align="center">更新人</cui:gridCol>
			<cui:gridCol name="cdjUpdtTime" align="center">更新时间</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_gwgl" />
	</cui:grid>
	
	<cui:dialog id="dialogId_gwgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_gwgl">
	</cui:dialog>
</div>

<script>

	$.parseDone(function() {
	});
	
	var toolbar_gwgl = [{
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
	
	var combobox_jbdJobCode = [
 		{value:"ZYLD",text:"主要领导"},
		{value:"DBLD",text:"带班领导"},
		{value:"ZBZR",text:"值班主任"},
		{value:"RJZB",text:"二级值班"},
		{value:"ZHZX",text:"指挥中心"},
		{value:"JQDB",text:"监区带班"},
		{value:"QT",text:"其他","selected":true},
	];
	
	var buttons_gwgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_gwgl").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/gwgl/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_gwgl").grid("option", "selarrrow");
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/gwgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_gwgl").dialog({
			width : 360,
			height : 350,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_gwgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_gwgl_query").form("formData");
		$("#gridId_gwgl").grid("option", "postData", formData);
		$("#gridId_gwgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_gwgl_query").form("reset");
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_gwgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_gwgl_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/gwgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_gwgl").dialog("close");
					$("#gridId_gwgl").grid("reload");
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
		
		var selarrrow = $("#gridId_gwgl").grid("option", "selarrrow");			
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
						url : '${ctx}/zbgl/gwgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_gwgl").grid("reload");
							} else if(data.code == "3") {
								$.message({message:"注意！无法删除已关联其他信息的岗位！", cls:"error"});
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