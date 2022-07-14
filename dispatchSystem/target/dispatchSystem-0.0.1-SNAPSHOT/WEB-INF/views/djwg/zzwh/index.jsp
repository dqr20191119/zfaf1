<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_zzwh_query">
		<table class="table">
			<tr><th>组织名称：</th>
				<td><cui:input name="zzName"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_zzwh" data="toolbar_zzwh"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_zzwh" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/djwg/zzwh/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="jyName" align="center">所属监狱</cui:gridCol>
			<cui:gridCol name="zzName" align="center">组织名称</cui:gridCol>
			<cui:gridCol name="zzCode"  align="center">组织编码</cui:gridCol>
			<cui:gridCol name="zzJb" align="center" formatter = "convertCode" revertCode = "true" formatoptions="{
						'data': combobox_zzjb
						}">组织级别</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_zzwh" />
	</cui:grid>
	
	<cui:dialog id="dialogId_zzwh" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_zzwh">
	</cui:dialog>
</div>

<script>


var combobox_zzjb = [{"text":"一级","value":"1"},{"text":"二级","value":"2"},{"text":"三级","value":"3"},{"text":"四级","value":"4"},
                     {"text":"五级","value":"5"},{"text":"六级","value":"6"},{"text":"七级","value":"7"}];
	$.parseDone(function() {
		
	});
	
	var toolbar_zzwh = [{
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

	var buttons_zzwh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zzwh").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/djwg/zzwh/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_zzwh").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/djwg/zzwh/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_zzwh").dialog({
			width : 360,
			height : 360,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_zzwh").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_zzwh_query").form("formData");
		$("#gridId_zzwh").grid("option", "postData", formData);
		$("#gridId_zzwh").grid("reload");
	}
	
	function reset() {
		
		$("#formId_zzwh_query").form("reset");
	}

	function saveOrUpdate() {
		
		var validFlag = $("#formId_zzwh_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_zzwh_edit").form("formData");
       console.log(formData);
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/djwg/zzwh/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_zzwh").dialog("close");
					$("#gridId_zzwh").grid("reload");
					
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
		
		var selarrrow = $("#gridId_zzwh").grid("option", "selarrrow");	
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
						url : '${ctx}/djwg/zzwh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_zzwh").grid("reload");
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