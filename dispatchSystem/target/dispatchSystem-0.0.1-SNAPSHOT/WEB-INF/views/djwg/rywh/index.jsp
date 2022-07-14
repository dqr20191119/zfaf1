<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rywh_query">
		<table class="table">
			<tr>
				<th>用户名称：</th>
				<td><cui:input name="userName"></cui:input></td>	
				<th>所属组织：</th>
				<td><cui:input name="zzName"></cui:input></td>	
							
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_rywh" data="toolbar_rywh"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_rywh" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/djwg/rywh/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="jyName" align="center">所属监狱</cui:gridCol>
			<cui:gridCol name="zzName" align="center">组织名称</cui:gridCol>
			<cui:gridCol name="zzCode"  align="center">组织编码</cui:gridCol>
			<cui:gridCol name="userName" align="center"  >人员名称</cui:gridCol>
			<cui:gridCol name="parentCode"  align="center">父类成员</cui:gridCol>
			<cui:gridCol name="px"  align="center">排序</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rywh" />
	</cui:grid>
	
	<cui:dialog id="dialogId_rywh" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_rywh">
	</cui:dialog>
</div>

<script>


var combobox_zzjb = [{"text":"一级","value":"1"},{"text":"二级","value":"2"},{"text":"三级","value":"3"},{"text":"四级","value":"4"},
                     {"text":"五级","value":"5"},{"text":"六级","value":"6"},{"text":"七级","value":"7"}];
	$.parseDone(function() {
		
	});
	
	var toolbar_rywh = [{
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

	var buttons_rywh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_rywh").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/djwg/rywh/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_rywh").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/djwg/rywh/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_rywh").dialog({
			width : 460,
			height : 660,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rywh").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_rywh_query").form("formData");
		$("#gridId_rywh").grid("option", "postData", formData);
		$("#gridId_rywh").grid("reload");
	}
	
	function reset() {
		
		$("#formId_rywh_query").form("reset");
	}

	function saveOrUpdate() {
		
		var validFlag = $("#formId_rywh_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_rywh_edit").form("formData");
       console.log(formData);
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/djwg/rywh/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_rywh").dialog("close");
					$("#gridId_rywh").grid("reload");
					
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
		
		var selarrrow = $("#gridId_rywh").grid("option", "selarrrow");	
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
						url : '${ctx}/djwg/rywh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_rywh").grid("reload");
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