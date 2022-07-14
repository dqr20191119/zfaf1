<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
UserBean userInfo = AuthSystemFacade.getLoginUserInfo();
request.setAttribute("cusNumber", userInfo.getCusNumber());
%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="height:100%;">
	<cui:form id="formId_jjb_query">
		<table class="table">
			<tr>	
				<th>值班日期：</th>
				<td>
					<cui:datepicker id ="combobox_dutyDate" name ="dutyDate" dateFormat="yyyy-MM-dd" ></cui:datepicker>
				</td>
				<th>值班状态：</th>
				<td>
					<cui:combobox id ="combobox_zt" name ="zt" data="combobox_zt" ></cui:combobox>
				</td>
			</tr>
			<tr>	
				<th>值班长：</th>
				<td>
				 	<cui:input id ="combobox_zbzName" name = "zbzName" ></cui:input>
				</td>
				<th>值班员：</th>
				<td>
					<cui:input id ="combobox_zbyName" name = "zbyName" ></cui:input>
				</td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jjb_query" data="toolbar_jjb_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_jjb_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dutyDate" align="center">值班日期</cui:gridCol>
			<cui:gridCol name="dutyTime" hidden="true">值班时间</cui:gridCol>
			<cui:gridCol name="orderName" align="center">班次</cui:gridCol>
			<cui:gridCol name="zbzName" align="center">值班长</cui:gridCol>
			<cui:gridCol name="zbyName" align="center">值班员</cui:gridCol>
			<cui:gridCol name="jbTime" align="center">交班时间</cui:gridCol>
			<cui:gridCol name="zt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_zt
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jjb_query"/>
	</cui:grid>
	 <cui:dialog id="dialogId_jjb" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_jjb">
	</cui:dialog>
	<cui:dialog id="dialogId_jjb_view" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_jjb_view">
	</cui:dialog>
	
	<cui:dialog id="dialogId_jjb_detailView" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true">
	</cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var combox_dw =<%= AuthSystemFacade.getAllJyJsonInfo()%>;
	var commonbox_ry =<%= RyCommon.getCommonbox3(request.getAttribute("cusNumber").toString())%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_zt = [{"value":"0","text":"值班中"},
					   {"value":"1","text":"待值班"},
					   {"value":"2","text":"交接班"},
					   {"value":"3","text":"已完成"}
						];
	
	
	$.parseDone(function() {
		inItDutyData();
		search(); 
	});
	
	
	
	var toolbar_jjb_query = [{
		"type" : "button",
		"id" : "btnId_zb",
		"label" : "开始值班",
		"onClick" : "startZb",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_jiaob",
		"label" : "交班",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_jieb",
		"label" : "接班",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_rztx",
		"label" : "填写日志",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}]
	
	var buttons_jjb = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "关闭",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_jjb").dialog("close");
	    }            
	}];
	
	var buttons_jjb_view = [{
	    text: "关闭",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_jjb_view").dialog("close");
	    }            
	}];
	
	function inItDutyData() {
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jjb/inItDutyData',
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						if(data.message!='true'){
							$.message({message:data.message, cls:"waring"});
						}
					}
			},
		});
	}
	
	
	function search() {
		var formData = {};
		formData.cusNumber = cusNumber;
		formData.dutyDate = $("#combobox_dutyDate").datepicker("getValue");
		formData.zbzName =  $("#combobox_zbzName").val();
		formData.zbyName =  $("#combobox_zbyName").val();
		formData.zt = $("#combobox_zt").combobox("getValue");
		$("#gridId_jjb_query").grid("option", "postData", formData);
		$("#gridId_jjb_query").grid("reload","${ctx}/zbgl/jjb/searchData"); 
	}
	
	function clear() {
			$("#formId_jjb_query").form("reset");
	}
	
	function openDailog(event, ui) {
		
		var url;
			var selarrrow = $("#gridId_jjb_query").grid("option","selarrrow");
			var rowData = $("#gridId_jjb_query").grid("getRowData", selarrrow[0]);
			
			if(selarrrow && selarrrow.length == 1 ) {
				
				if(ui.id == "btnId_rztx"){
					var zt = rowData.zt;
					if(zt !='0'){
						$.message({message:"不是值班状态,不能填写日志！", cls:"waring"});
						return;
					}else if(checkIsZbry(rowData)!="true"){
						$.message({message:"不是当前班次的值班人员,不能填写值班日志！", cls:"waring"});
						return;
					}else{
						url = "${ctx}/zbgl/jjb/toEdit?id=" +rowData.id;
						$("#dialogId_jjb").dialog({
							width : 1000,
							height : 600,
							title : ui.label,
							url : url				 
						});
						$("#dialogId_jjb").dialog("open");
					}
				}else if(ui.id == "btnId_jieb") {
					
					var params = {};
					params.id = rowData.id;
					params.param = "2";
					var flag = checkIsZbz(params);
					var zt = rowData.zt;
					if(zt != '2'){
						$.message({message:"请先交班,才能接班！", cls:"waring"});
						return;
					}
					if( flag =="false"){
						$.message({message:"不是值班长,不能接班！", cls:"waring"});
						return;
					}else if(flag !="false" && flag !="true"){
						$.message({message:flag, cls:"waring"});
						return;
					}
					
					url = "${ctx}/zbgl/jjb/toJjbView?parma=2&id="+rowData.id;
					
					$("#dialogId_jjb_detailView").dialog({
						width : 1200,
						height : 800,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_jjb_detailView").dialog("open");
				}else if(ui.id =="btnId_jiaob"){
					var params = {};
					params.id = rowData.id;
					params.param = "1";
					var flag = checkIsZbz(params);
					var zt = rowData.zt;
					if(zt !='0'){
						$.message({message:"请先值班,才能交班！", cls:"waring"});
						return;
					}
					if( flag =="false"){
						$.message({message:"不是值班长,不能交班！", cls:"waring"});
						return;
					}else if(flag !="false" && flag !="true"){
						$.message({message:flag, cls:"waring"});
						return;
					}
					url ="${ctx}/zbgl/jjb/toJjbView?parma=1&id="+rowData.id;
						$("#dialogId_jjb_detailView").dialog({
							width : 1200,
							height : 800,
							title : ui.label,
							url : url				 
						});
						$("#dialogId_jjb_detailView").dialog("open");
				}else if(ui.id == "btnId_view") {
					url = "${ctx}/zbgl/jjb/toView?id="+rowData.id;
					$("#dialogId_jjb_view").dialog({
						width :1200,
						height : 800,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_jjb_view").dialog("open");
				}
				
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
	}
	
	
	
	
	function saveOrUpdate() {
		var formData = $("#formId_zbrz_edit").form("formData");
		var params = {};
		params.ywId = formData.id;
		params.time = formData.time;
		//zbDetial  ylWt  cjrName  cjrq
		params.zbDetial = formData.zbDetial;
		params.ylWt = formData.ylWt;
		params.cjrName = formData.cjrName;
		params.cjrq = formData.cjrq;
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jjb/saveOrUpdate',
			data: params,
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						$.message({message:"操作成功！", cls:"success"}); 
						$("#dialogId_jjb").dialog("close");
						$("#gridId_jjb_query").grid("reload");
					}
			}
		});
		
    }
	
	//开始值班
	function startZb() {
		var selarrrow = $("#gridId_jjb_query").grid("option","selarrrow");
		var rowData = $("#gridId_jjb_query").grid("getRowData", selarrrow[0]);
		var zt = rowData.zt;
		if(selarrrow && selarrrow.length == 1 ) {
			if(checkIsZbry(rowData)!="true"){
				$.message({message:"不是当前班次的值班人员,不能值班！", cls:"waring"});
				return;
				}else if(zt !='1'){//不是待值班状态
					$.message({message:"请选择待值班状态的记录,进行值班！", cls:"waring"});
					return;
				}else{
					var params = {};
					params.id = rowData.id;
					params.zt = "0";
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/jjb/updateById',
						data: params,
						dataType : 'json',
						async: false,
						success : function(data) {
								if(data.code==200){
									$.message({message:"值班成功！", cls:"success"});
									$("#gridId_jjb_query").grid("reload");
								}
						}
					});
				}
			
		}else{
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}	
		
	}
	
	function checkIsZbry(rowData) {
		var id = rowData.id;
		var flag = "";
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jjb/checkIsZbry',
			data: {id:id},
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						flag = data.message;//true  或者 false
					}
			}
		});
		return flag;
	}
	
	
	 function checkIsZbz(params) {
			var flag = "";
			$.ajax({
				type : 'post',
				url : '${ctx}/zbgl/jjb/checkIsZbz',
				data: params,
				dataType : 'json',
				async: false,
				success : function(data) {
						if(data.code==200){
							flag = data.message;//true  或者 false
						}
				}
			});
			return flag;
	}	
	
	
</script>