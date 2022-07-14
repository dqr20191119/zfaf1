<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_gzzgl_query">
		<table class="table">			
			<tr>
				<td>工作组名称：</td>
				<td><cui:input name="wgiWorkgroupName"></cui:input></td>
			 	<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>			
	</cui:form>
 	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_gzzgl" data="toolbar_gzzgl"></cui:toolbar>			
		<cui:grid id="gridId_gzzgl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/gzzgl/searchData"
			sortname="wgiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="wgiWorkgroupName" width="200">工作组名称</cui:gridCol>
				<cui:gridCol name="wgiWorkgroupTask" width="600">工作组任务</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_gzzgl" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_gzzgl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_gzzgl">
	</cui:dialog>
</div>

<script>
	 
	var pos;
	var combobox_yjct_memberRole = <%=CodeFacade.loadCode2Json("4.20.1")%>;
	
	var toolbar_gzzgl = [{
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

	var buttons_gzzgl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_gzzgl").dialog("close");
	    }            
	}];
	
	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/gzzgl/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_gzzgl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/gzzgl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"}); 
				return;
			}			
		}

		$("#dialogId_gzzgl").dialog({
			width : 800,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_gzzgl").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_gzzgl_query").form("formData");
		$("#gridId_gzzgl").grid("option", "postData", formData);
		$("#gridId_gzzgl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_gzzgl_query").form("reset");
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_gzzgl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_gzzgl_edit").form("formData");
		var msg = validData(formData);
		if(msg != "") {
			$.message({message:msg, cls:"waring"}); 
			return;
		}
				
		// 获取成员数据
		// 这里用index而不是用i，因为i不连续，传到后台框架会补全-在list添加空数据，
		// 如gzzcyEntityList[1]而没有gzzcyEntityList[0]，此时后台list有两条数据，一条为空，所以此时另定义一个index
		var index = 0;
		for(var i = 0; i < pos; i++) {
			if($("#member_police_task_" + i).length > 0) {
				if($("#member_id_" + i).length > 0) {
					formData["gzzcyEntityList[" + index + "].id"] = $("#member_id_" + i).textbox("getText");					
					formData["gzzcyEntityList[" + index + "].wgmCusNumber"] = $("#member_cus_number_" + i).textbox("getText");
					formData["gzzcyEntityList[" + index + "].wgmWorkgroupFid"] = $("#member_workgroup_fid_" + i).textbox("getText");					
				}
				
				formData["gzzcyEntityList[" + index + "].wgmUserRole"] = $("#member_police_role_" + i).textbox("getText");
				formData["gzzcyEntityList[" + index + "].wgmPoliceId"] = $("#member_police_id_" + i).textbox("getText");
				formData["gzzcyEntityList[" + index + "].wgmPoliceName"] = $("#member_police_name_" + i).textbox("getText");
				formData["gzzcyEntityList[" + index + "].wgmRoleTask"] = $("#member_police_task_" + i).textbox("getText");
				index++;
			}			
		}		
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzgl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {	
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_gzzgl").dialog("close");
					$("#gridId_gzzgl").grid("reload");
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
	
	function validData(formData) {
		
		var msg = "";
		
		/* msg = validName(formData);
		if(msg != "") {
			return msg;
		} */
		
		if($("#tableId_gzzgl_police_1 tr").length == 0) {
			msg = "请至少选择一个组长！";
			return msg;
		}
		
		if($("#tableId_gzzgl_police_2 tr").length == 0) {
			msg = "请至少选择一个副组长！";
			return msg;
		}
		
		if($("#tableId_gzzgl_police_3 tr").length == 0) {
			msg = "请至少选择一个成员！";
			return msg;
		}
				
		return msg;
	}
	
	/* function validName(formData) {
		
		var msg = "";
		var data = {};
		data.wgiWorkgroupName = formData.wgiWorkgroupName;
		data.wgiStatus = "2";
		
		$.ajax({
			async: false,
			type : 'post',
			url : '${ctx}/yjct/gzzgl/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {				
				if(data.code == "1") {
					if(data.data && data.data.length > 0) {
						msg = "工作组名称已存在！";
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
		
		var selarrrow = $("#gridId_gzzgl").grid("option", "selarrrow");			
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
						url : '${ctx}/yjct/gzzgl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"}); 
								$("#gridId_gzzgl").grid("reload");
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