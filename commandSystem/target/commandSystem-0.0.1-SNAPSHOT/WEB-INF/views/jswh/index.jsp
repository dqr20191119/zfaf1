<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_jswh_query">
		<table>
			<tr>
				<th>区域：</th>
				<td><cui:combotree id="areaTreeId" name="cpjLch"  componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" onNodeClick="onAreaTreeClick_bjq"></cui:combotree>
				</td>
				<td>
	 				&nbsp;&nbsp;&nbsp;&nbsp;<cui:button label="查询" componentCls="btn-primary" onClick="searchJswh"></cui:button>
					&nbsp;&nbsp;<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jswh" data="toolbar_jswh_query"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_jswh_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/xxhj/jswh/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cpjLch" hidden="true">区域id</cui:gridCol>
			<cui:gridCol name="cpjLc" align="center">区域</cui:gridCol>
			<cui:gridCol name="cpjJs" align="center">监舍号</cui:gridCol>
			<cui:gridCol name="cpjCameraName" align="center">主摄像头</cui:gridCol>
			<cui:gridCol name="cpjFCameraName" align="center">辅摄像头</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jswh_query" />
	</cui:grid>
	
	<cui:dialog id="dialogId_jswh_query" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_jswh">
	</cui:dialog>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	
	$.parseDone(function() {
		$("#areaTreeId").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
	});
	
	function searchJswh() {
		var formData = $("#formId_jswh_query").form("formData");
		$("#gridId_jswh_query").grid("option", "postData", formData);
		$("#gridId_jswh_query").grid("reload");
	}
	
	function clear() {
		$("#formId_jswh_query").form("reset");
	}
	
	var toolbar_jswh_query = [{
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
	
	var buttons_jswh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_jswh_query").dialog("close");
	    }            
	}];
	
	function openDailog(event, ui) {
		 
		var url;
		var dialog_w;
		var dialog_h;
		
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/xxhj/jswh/toAdd";
			dialog_w = "1000";
			dialog_h = "800";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_jswh_query").grid("option", "selarrrow");	
			var rowData = $("#gridId_jswh_query").grid("getRowData", selarrrow[0]);
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/xxhj/jswh/toEdit?id=" + selarrrow[0]+"&areaId="+rowData.cpjLch;
				dialog_w = "360";
				dialog_h = "350";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_jswh_query").dialog({
			width : dialog_w,
			height : dialog_h,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_jswh_query").dialog("open");
	}
	
	
	function saveOrUpdate() {
		
		var flag = save();   //判断是否重复
		if(flag) {
			
			var list = "";
			var saveData = flag[0];
			$.loading({text:"正在处理中，请稍后..."});
			$.ajax({
				type : 'post',
				url : '${ctx}/xxhj/jswh/saveOrUpdate',
				data: JSON.stringify(saveData),
				dataType : 'json',
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					$.loading("hide");
					
					if(data.success) {
						$.messageQueue({ message : data.obj, cls : "success", iframePanel : true, type : "info" });
						$("#dialogId_jswh_query").dialog("close");
						$("#gridId_jswh_query").grid("reload");
						
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"}); 
				}
			});
		}
	}
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_jswh_query").grid("option", "selarrrow");			
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
						url : '${ctx}/xxhj/jswh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if (data.success) {
								$.messageQueue({ message : data.obj, cls : "success", iframePanel : true, type : "info" });
								$("#gridId_jswh_query").grid("reload");
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
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