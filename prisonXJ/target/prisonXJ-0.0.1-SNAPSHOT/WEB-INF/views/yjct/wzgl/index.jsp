<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_wzgl_query">
		<table class="table">
			<tr>
				<td>物资名称：</td>
				<td><cui:input name="mriMaterialName"></cui:input></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_wzgl" data="toolbar_wzgl"></cui:toolbar>			
		<cui:grid id="gridId_wzgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/wzgl/searchData" frozenColumns="true" shrinkToFit="false" 
			sortname="mriCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true" frozen="true">id</cui:gridCol>
				<cui:gridCol name="mriMaterialName" width="100" frozen="true">物资名称</cui:gridCol>
				<cui:gridCol name="mriMaterialType" width="100">物资类型</cui:gridCol>
				<cui:gridCol name="mriMaterialCompany" width="200" formatter="formatMriCompany">所属单位</cui:gridCol>
				<cui:gridCol name="mriAddress" width="200">所在位置</cui:gridCol>
				<cui:gridCol name="mriCount" width="100">数量</cui:gridCol>
				<cui:gridCol name="mriCharger" width="100">负责人</cui:gridCol>
				<cui:gridCol name="mriChargePhone" width="100">负责电话</cui:gridCol>
				<cui:gridCol name="mriUse" width="200">用途</cui:gridCol>
				<cui:gridCol name="mriSignCompany" width="200">签约单位</cui:gridCol>
				<cui:gridCol name="mriSpec" width="100">规格</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_wzgl" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_wzgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_wzgl">
	</cui:dialog>
</div>

<script>
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
	
	var toolbar_wzgl = [{
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

	var buttons_wzgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_wzgl").dialog("close");
	    }            
	}];

	
	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/wzgl/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_wzgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/wzgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_wzgl").dialog({
			width : 800,
			height : 400,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_wzgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_wzgl_query").form("formData");
		$("#gridId_wzgl").grid("option", "postData", formData);
		$("#gridId_wzgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_wzgl_query").form("reset");
	}
	
	function formatMriCompany(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_jy, cellvalue);
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_wzgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_wzgl_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/wzgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_wzgl").dialog("close");
					$("#gridId_wzgl").grid("reload");
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
	
	/* function validData(formData) {
		
		var msg = "";
		
		msg = validName(formData);
		if(msg != "") {
			return msg;
		}
				 
		return msg;
	} */
	
	/* function validName(formData) {
		
		var msg = "";
		var data = {};
		data.mriMaterialName = formData.mriMaterialName;
		data.mriStatus = "2";
			
		$.ajax({
			async: false,
			type : 'post',
			url : '${ctx}/yjct/wzgl/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {				
				if(data.code == "1") {
					if(data.data && data.data.length > 0) {
						msg = "物资名称已存在！";
					}
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
		
		return msg;
	} */
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_wzgl").grid("option", "selarrrow");			
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
						url : '${ctx}/yjct/wzgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_wzgl").grid("reload");
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