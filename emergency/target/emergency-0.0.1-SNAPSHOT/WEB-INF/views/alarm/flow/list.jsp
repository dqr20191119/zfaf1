<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<div style="position:relative;"> 
			<cui:form id="formId_query">
				<label>流程名称：</label>
				<cui:input  name="hfmFlowName" />
				<label>流程类型：</label>
				<cui:combobox id="hfmTypeIndc" name="hfmTypeIndc" data="typeData" />
				<cui:button label="查询" onClick="query"></cui:button>
				<cui:button label="重置" onClick="bjlc_clear"></cui:button>
			</cui:form>
		</div>
		
		<cui:toolbar data="toolbar_localDate"></cui:toolbar>
		<cui:grid id="gridId_flow" fitStyle="fill" multiselect="true" colModel="gridId_flow_colModelDate" >
			<cui:gridPager gridId="gridId_flow" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_flow" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	var typeData = [ { text : "标准流程", value : "0" },  { text : "定制流程", value : "1" } ];
	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;
	//报警等级
	var levelData =  <%=CodeFacade.loadCode2Json("4.20.25")%>;
	
	$.parseDone(function() {
		var url = "${ctx}/flow/findByPage.json";
		if(jsConst.USER_LEVEL != 1){
			$("#hfmTypeIndc").combobox( "setValue", "1");
			url = url + "?hfmCusNumber=" + cusNumber + "&hfmTypeIndc=1";
		}
		$("#gridId_flow").grid("reload", url);
	});

	var gridId_flow_colModelDate = [ { label : "id", name : "ID", 	width :  70  , key :true,hidden:true},
		{name:"HFM_SHOW_SEQ",label:"序号",align:"center",width:85},
		{name:"HFM_CUS_NUMBER",label:"监狱",align:"center",width:110,formatter : "convertCode",  revertCode : true, formatoptions : { 'data':combobox_jy } },
		{name:"HFM_FLOW_NAME",label:"流程名称",align:"center",width:135},
		{name:"HFM_TYPE_INDC",label:"流程类型",align:"center",width:94,formatter:typeFormatter},
		{name:"HFM_ALARM_LEVEL",label:"报警等级",align:"center",width:94,formatter : "convertCode",  revertCode : true, formatoptions : { 'data':levelData } }, 
		{label : "详情", name : "check", align : "center", width : "75", formatter : "Formatter" }];

		
		function Formatter(cellValue, options, rowObject) {
			var param1 = rowObject.ID;
			var result = '<a href="" style="color: #4692f0;" onclick="detail(\''+param1.toString()+'\');return false;">查看</a>';
			return result;
		}
	
	var toolbar_localDate = [ {
		"type" : "button",
		"id" : "toolbarIcon",
		"label" : "增加",
		"onClick" : "add",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "toolbarIcon",
		"label" : "修改",
		"onClick" : "update",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "toolbarIcon",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} ];
	
	//流程类型Formatter
	function typeFormatter(cellvalue, options, rawObject){
		if(cellvalue==0){
			return "标准流程";
		}else if(cellvalue==1){
	  		return "定制流程";
	    }else{
	    	return "";
	    }
	}
	
	function query() {
		var formData = $("#formId_query").form("formData");
		$("#gridId_flow").grid("option", "postData", formData);
		$("#gridId_flow").grid("reload","${ctx}/flow/findByPage.json");
	}
	
	function bjlc_clear() {
		$("#formId_query").form("reset");
		if(jsConst.USER_LEVEL != 1){
			$("#hfmTypeIndc").combobox( "setValue", "1"); 
		}  
		
	}
	function add() {
		open(  "${ctx}/flow/save","增加流程");
	}
	
	function update() {
		var sel = $('#gridId_flow').grid("option", "selarrrow");
		if (sel.length < 1) {
			$.messageQueue({ message : "请先勾选需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
			return false;
		}
		if (sel.length > 1) {
			$.messageQueue({ message : "请只勾选一条需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
			return false;
		}
		var rowData = $('#gridId_flow').grid("getRowData", sel[0]);
		//省局用户判断
		if (jsConst.USER_LEVEL == 1 && rowData.HFM_TYPE_INDC == "定制流程") {
			$.messageQueue({ message : "当前用户只能修改标准流程！", cls : "warning", iframePanel : true, type : "info" });
			return false;
		}
		if (jsConst.USER_LEVEL != 1 && rowData.HFM_TYPE_INDC == "标准流程") {
			$.messageQueue({ message : "当前用户只能修改定制流程！", cls : "warning", iframePanel : true, type : "info" });
			return false;
		}
		var	url = "${ctx}/flow/save?id=" + rowData.ID;
		open( url, "修改流程");
	}

	function open( url, label) {
		$("#dialogId_flow").dialog({
			width : 860,
			height : 625,
			url : url,
			title : label,
		});
		$("#dialogId_flow").dialog("open");
	}
	
	function remove() {
		var ids = [];
		var sel = $("#gridId_flow").grid("option", "selarrrow");
		for(var i=0;i<sel.length;i++){
			var rowData = $('#gridId_flow').grid("getRowData", sel[i]);
			if (jsConst.USER_LEVEL == 1 && rowData.HFM_TYPE_INDC == "定制流程") {
				$.messageQueue({ message : "当前用户只能删除标准流程！", cls : "warning", iframePanel : true, type : "info" });
				return false;
			}
			if (jsConst.USER_LEVEL != 1 && rowData.HFM_TYPE_INDC == "标准流程") {
				$.messageQueue({ message : "当前用户只能删除定制流程！", cls : "warning", iframePanel : true, type : "info" });
				return false;
			}
	        ids.push(rowData.ID);
	    };
		if (sel.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/flow/deleteByIds",
						type : "post",
						data : {"obj":JSON.stringify(ids)},
						dataType : 'json',
						success : function(data) {
							if (data.success == true) {
								$("#gridId_flow").grid("reload");
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message : "请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	function detail(planId){
		var	url = "${ctx}/flow/detail?id=" + planId;
		open(  url, "流程详情");
	}
</script>