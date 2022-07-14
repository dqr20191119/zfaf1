<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<div>
	<cui:form id="formId_jjb_zbrz_edit">
		<cui:input type='hidden' name="id" />
 		<table class="table" style="width: 100%">
			<tr>
				<td><label>班次：</label></td>
				<td ><cui:input id="orderName" name="orderName" readonly="true"></cui:input></td>
				<td><label>值班日期：</label></td>
				<td colspan="3"><cui:input id="dutyDate" name="dutyDate" readonly="true" ></cui:input></td>								
			</tr>
			<tr>	
				<td><label>值班长：</label></td>
				<td><cui:input  name="zbzName" readonly="true"></cui:input></td>	
				<td><label>值班员：</label></td>
				<td><cui:input  name="zbyName" readonly="true"></cui:input></td>
				<td><label>值班时间：</label></td>
				<td><cui:input  name="dutyTime" readonly="true"></cui:input></td>					
			</tr>
			<tr>
			  <td colspan="6" style="color: red">---------------------------------------------------------------------值班日志汇总----------------------------------------------------------------------------------------------</td>
			</tr>
		</table>
	</cui:form>
	<div style="width: 100%;height: 550px">
		<cui:grid id="gridId_jjb_rzhz_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20"  >
			<cui:gridCols>
				<cui:gridCol name="zbDetial" align="center" formatter="textarea" formatoptions="{'readonly' : true}" >值班记录详情</cui:gridCol>
				<cui:gridCol name="ylWt" align="center" formatter="textarea" formatoptions="{'readonly' : true}">遗留问题</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_jjb_rzhz_query" />
		</cui:grid>
	</div>
	<div style="height: 40px;margin-top: 20px">
		<cui:toolbar id="toolbarId_jjb_toolbar" data="toolbarId_jjb_toolbar" align="center"></cui:toolbar>	
	</div>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var parma = '${parma}'
	var id = '${id}';                     
	//var zbYf = '${zbYf}';  
	//var zbDh = '${zbDh}';
	$.parseDone(function() {
		if(id) {
			loadForm("formId_jjb_zbrz_edit", "${ctx}/zbgl/jjb/getById?id="+id, function(data) {
               
			});
			$("#gridId_jjb_rzhz_query").grid("reload","${ctx}/zbgl/jjb/searchRzData?id="+id); 
			if(parma=='1'){//交班 隐藏接班按钮
				$("#toolbarId_jjb_toolbar").toolbar("hide", "btnId_jieban");
			}else{//接班 隐藏交班按钮
				$("#toolbarId_jjb_toolbar").toolbar("hide", "btnId_jiaoban");
			}
			
		}
	});
	
	var toolbarId_jjb_toolbar = [{
		"type" : "button",
		"id" : "btnId_jiaoban",
		"label" : "确认交班",
		"onClick" : "updateJjb('1')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_jieban",
		"label" : "确认接班",
		"onClick" : "updateJjb('2')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_jieban",
		"label" : "关闭",
		"onClick" : "colseDialog",
		"componentCls" : "btn-primary"
	}]
	
	function updateJjb(type) {
		var params = {};
		params.id = id;
		if(type=='1'){//交班
			params.zt = "2";//交接班状态
			params.param = "1";
		}else{//接班
			params.zt = "3"; //完成状态
			params.param = "2";
		}
			$.ajax({
				type : 'post',
				url : '${ctx}/zbgl/jjb/jjbTj',
				data: params,
				dataType : 'json',
				async: false,
				success : function(data) {
						if(data.code==200){
							if(data.message != ""){
							 $.message({message:data.message, cls:"success"}); 
							}
							$.message({message:"操作成功！", cls:"success"}); 
							$("#dialogId_jjb_detailView").dialog("close");
							$("#gridId_jjb_query").grid("reload");
						}
				}
			});
	}
	


 
 function colseDialog() {
	 $("#dialogId_jjb_detailView").dialog("close");
}
</script>