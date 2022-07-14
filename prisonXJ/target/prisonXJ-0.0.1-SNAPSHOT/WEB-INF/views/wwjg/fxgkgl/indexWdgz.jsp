<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<div style="height: 40px;">
		<cui:toolbar id="wdgzwhToolbar" data="wdgzwhToolbarData"></cui:toolbar>	
	</div>	
		
	<cui:grid id="wdgzwhGrid" rownumbers="true" multiselect="true" width="auto" fitStyle="fill">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="gzlx" width="50" align="center">改造类型</cui:gridCol>
			<cui:gridCol name="fzlx" width="50" align="center">得分类型</cui:gridCol>
			<cui:gridCol name="gznr" width="200" align="center">改造内容</cui:gridCol>
			<cui:gridCol name="fz" width="50" align="center">分值</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="wdgzwhGrid" />
	</cui:grid>
	
	<cui:dialog id="wdgzwhDialog" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_wwjgwh">
		<cui:form id="wdgzwhForm">
			<cui:input type='hidden' name="id" />
	 		<table class="table">
				<tr>
					<th>改造类型：</th>
					<td>
					<cui:combobox name="gzlx" id="gzlx" required="true" width="350" data="gzlxData"></cui:combobox>
					</td>				
				</tr>
				<tr>	
					<th>得分类型：</th>
					<td>
					<cui:combobox name="fzlx" id="fzlx" required="true" width="350" data="fzlxData"></cui:combobox>
				    </td>
				</tr>
				<tr>	
					<th>改造内容：</th>
					<td>
					<cui:textarea  required="true" name="gznr" width="350"></cui:textarea>
				    </td>
				</tr>
				<tr>	
					<th>分值：</th>
					<td>
					<cui:input  name="fz" required="true"  width="350"/>
				    </td>
				</tr>
			</table>
		</cui:form>
	</cui:dialog>
</div>

<script>
	var gzlxData=[{value:'政治改造',text:'政治改造'},{value:'劳动改造',text:'劳动改造'},{value:'监管改造',text:'监管改造'},{value:'教育改造',text:'教育改造'},{value:'文化改造',text:'文化改造'}];
	var fzlxData=[{value:'基础分',text:'基础分'},{value:'奖励分',text:'奖励分'}];
	
	$.parseDone(function() {
		$("#wdgzwhGrid").grid("reload", "${ctx}/wwjg/wdgzwh/search");
	});
	
	var wdgzwhToolbarData = [{
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
		"onClick" : "deleteWdgzwh",
		"componentCls" : "btn-primary"
	}];

	var buttons_wwjgwh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveWdgzwh();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#wdgzwhDialog").dialog("close");
	    }            
	}];

	function openDailog(event, ui) {
		if(ui.id == "btnId_add") {
			openWdgzwh();
		} else if(ui.id == "btnId_edit") {
			var selarrrow = $("#wdgzwhGrid").grid("option", "selarrrow");	
			if(selarrrow && selarrrow.length == 1) {
				openWdgzwh();
				$.ajax({
					type : 'post',
					url : '${ctx}/wwjg/wdgzwh/findWdgzwh',
					data: {
						id:selarrrow[0]
					},
					dataType : 'json',
					success : function(data) {
						$("#wdgzwhForm").form("load", data.data);
					}
				});
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}
		} else if(ui.id == "btnId_remove") {	
			deleteWdgzwh();	
		}
	};
	
	function openWdgzwh() {
		$("#wdgzwhDialog").dialog({
			width : 500,
			height : 400,
			title : '罪犯改造质量评估维护'			 
		});
		$("#wdgzwhForm").form("clear");
		$("#wdgzwhDialog").dialog("open");
	};
	
	function saveWdgzwh() {
		
		var validFlag = $("#wdgzwhForm").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#wdgzwhForm").form("formData");
		$.ajax({
			type : 'post',
			url : '${ctx}/wwjg/wdgzwh/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$("#wdgzwhDialog").dialog("close");
				$.message({message:"操作成功！", cls:"success"}); 
				$("#wdgzwhGrid").grid("reload");		
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#wdgzwhDialog").dialog("close");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	};
	
	function deleteWdgzwh() {
		
		var selarrrow = $("#wdgzwhGrid").grid("option", "selarrrow");	
		if(selarrrow && selarrrow.length == 1) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					$.ajax({
						type : 'post',
						url : '${ctx}/wwjg/wdgzwh/deleteWdgzwh',
						data: {
							id : selarrrow[0]
						},
						dataType : 'json',
						success : function(data) {
							$("#wdgzwhDialog").dialog("close");
							$.message({message:"操作成功！", cls:"success"}); 
							$("#wdgzwhGrid").grid("reload");					
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.message({message:"操作失败！", cls:"error"}); 
						}
					});
				}
			}); 
		} else {
			$.message({message:"请选择一条要删除的记录！", cls:"waring"});
			return;
		}
	};
</script>