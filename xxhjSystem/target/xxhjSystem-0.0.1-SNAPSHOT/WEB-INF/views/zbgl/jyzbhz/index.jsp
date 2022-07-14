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
	<cui:form id="formId_zbbp_query">
		<table class="table">
			<tr>	
				<th>监狱：</th>
				<td><cui:combobox id="combobox_cusNumber" data="combox_dw" name="cusNumber" showClose="true" ></cui:combobox></td>
				<th>月份：</th>
				<td><cui:datepicker id ="combobox_zbYf" name = "zbYf" dateFormat="yyyy-MM" ></cui:datepicker>
				</td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_zbbp_query" data="toolbar_zbbp_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_zbbp_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cusNumber" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combox_dw
			}">监狱</cui:gridCol>
			<cui:gridCol name="zbYf" align="center">月份</cui:gridCol>
			<cui:gridCol name="zbDh" align="center">值班电话</cui:gridCol>
			<cui:gridCol name="cjrq" align="center">填报日期</cui:gridCol>
			<cui:gridCol name="cjrId" hidden="true">填报人id</cui:gridCol>
			<cui:gridCol name="cjrName" align="center">填报人</cui:gridCol>
			<cui:gridCol name="zt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_zt
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_zbbp_query" />
	</cui:grid>
	 <cui:dialog id="dialogId_zbbp" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_zbbp">
	</cui:dialog>
	<cui:dialog id="dialogId_zbbp_view" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_zbbp_view">
	</cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var combox_dw =<%= AuthSystemFacade.getAllJyJsonInfo()%>;
	var commonbox_ry =<%= RyCommon.getCommonbox3(request.getAttribute("cusNumber").toString())%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_zt = [{"value":"0","text":"未上报"},
					   {"value":"1","text":"已上报"},
					   {"value":"2","text":"已审核"}
						];
	
	
	$.parseDone(function() {
		
		if( USER_LEVEL =='2' && USER_LEVEL != null) {
			$("#combobox_cusNumber").combobox("setValue",cusNumber);
			$("#combobox_cusNumber").combobox("option","readonly",true);
			$("#toolbarId_zbbp_query").toolbar("hide", "btnId_hz");
		} else if(USER_LEVEL =='1') {
			//$("#combobox_cusNumber").combobox("setValue",cusNumber);
		}
		//initCategory(drpmntId);
		search();
		
	});
	
	
	
	var toolbar_zbbp_query = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "登记",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteById",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_fb",
		"label" : "上报",
		"onClick" : "updateZt('1')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_cx",
		"label" : "撤回",
		"onClick" : "updateZt('0')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_hz",
		"label" : "汇总",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
            "type" : "button",
            "id" : "btnId_import",
            "label" : "模板下载",
            "onClick" : "downloadexcel",
            "componentCls" : "btn-primary"
        },{
        "type" : "button",
        "id" : "btnId_import",
        "label" : "导入",
        "onClick" : "importExcel",
        "componentCls" : "btn-primary"
    }];
	
	var buttons_zbbp = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "关闭",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zbbp").dialog("close");
	    }            
	}];
	
	var buttons_zbbp_view = [{
	    text: "关闭",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zbbp_view").dialog("close");
	    }            
	}];
	
	function search() {
		
		var formData = {};
		formData.cusNumber = $("#combobox_cusNumber").combobox("getValue");
		formData.zbYf = $("#combobox_zbYf").datepicker("getValue");
		$("#gridId_zbbp_query").grid("option", "postData", formData);
		$("#gridId_zbbp_query").grid("reload","${ctx}/zbgl/jyzbhz/searchData"); 
	}
	
	function clear() {
			$("#formId_zbbp_query").form("reset");
	}

    //下载Excel模板文件
    function downloadexcel(){
        var url = "${ctx}/zbgl/jyzbhz/downloadexcel";
        window.location.href = url;
    }

    //文件导入
    function importExcel(){
	  var jybh =  $("#combobox_cusNumber").combobox("getValue");
      var zbyf  = $("#combobox_zbYf").datepicker("getValue");
	  if(jybh=="" || zbyf==""){
          $.message({message:"请选择监狱和月份！", cls:"waring"});
          return;
      }

	  var ur ="${ctx}/zbgl/jyzbhz/toImport?cusNumber="+jybh+"&zbyf="+zbyf;
        $("#dialogImport" ).dialog("option",{
            title:"导入窗口",
            subTitle:"信息",
            width:520,
            height:450,
            url:ur
        });
        $( "#dialogImport" ).dialog("open");
    }


	function openDailog(event, ui) {
		
		var url;

		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/jyzbhz/toEdit";
			$("#dialogId_zbbp").dialog({
				width :1200,
				height : 1000,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_zbbp").dialog("open");
			
		}else if(ui.id =="btnId_hz"){
			url ="${ctx}/zbgl/jyzbhz/toHzView"
				$("#dialogId_zbbp_view").dialog({
					width : 1200,
					height : 800,
					title : ui.label,
					url : url				 
				});
				$("#dialogId_zbbp_view").dialog("open");
		}else {	
			var selarrrow = $("#gridId_zbbp_query").grid("option","selarrrow");
			var rowData = $("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
			
			if(selarrrow && selarrrow.length == 1 ) {
				
				if(ui.id == "btnId_edit"){
					var zt = rowData.zt;
					if(zt=='1'){
						$.message({message:"该值班表已经上报,不能修改,如要修改,请先撤销！", cls:"waring"});
						return;
					}else if(zt=='2'){
						$.message({message:"该值班表已经审核不能修改！", cls:"waring"});
						return;
					}else{
						url = "${ctx}/zbgl/jyzbhz/toEdit?id=" +rowData.id+ "&cusNumber=" +rowData.cusNumber+ "&zbYf=" +rowData.zbYf+ "&zbDh="
						+rowData.zbDh+ "&cjrId=" +rowData.cjrId+ "&cjrq=" +rowData.cjrq;
				
						$("#dialogId_zbbp").dialog({
							width : 1200,
							height : 1000,
							title : ui.label,
							url : url				 
						});
						$("#dialogId_zbbp").dialog("open");
					}
					
				}else if(ui.id == "btnId_view") {
					
					url = "${ctx}/zbgl/jyzbhz/view?id=" +rowData.id+ "&cusNumber=" +rowData.cusNumber+ "&zbYf=" +rowData.zbYf+ "&zbDh="
					+rowData.zbDh+ "&cjrId=" +rowData.cjrId+ "&cjrq=" +rowData.cjrq;
					
					$("#dialogId_zbbp_view").dialog({
						width : 1200,
						height : 800,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_zbbp_view").dialog("open");
				}else if(ui.id == "btnId_export") {
					/* url = "${ctx}/zbgl/zbbp/export?id=" +rowData.id+ "&categoryId=" +rowData.cdmCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
					+rowData.dmdModeId+ "&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate;
					window.location.href = url; */
				}
				
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
	}
	
	
	
	
	function saveOrUpdate() {
		var formData = $("#formId_zbbp_edit").form("formData");
		var allRowDate = $("#grid_jyzbDetail").grid("getRowData");
		var validFlag = $("#grid_jyzbDetail").grid("valid");
		if(!validFlag) {
			return;
		}
		console.log("allRowDate="+JSON.stringify(allRowDate));
		formData.jyzbDetails = JSON.stringify(allRowDate);
		if(formData.id !=null &&formData.id !="" ){
			//修改
			formData.param = "1";
		}else{
			//新增
			formData.param = "2";
			formData.zt = '0';
			var zbYf = formData.zbYf;
			var flag = validateDutydate(zbYf);
			if(flag=="true"){
				$.message({message:"该月份已经有值班数据！", cls:"waring"});
				return;
			}
			
		}
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jyzbhz/saveOrUpdate',
			data: formData,
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						$.message({message:"操作成功！", cls:"success"}); 
						$("#dialogId_zbbp").dialog("close");
						$("#gridId_zbbp_query").grid("reload");
					}
			},
		});
		
    }
	
	/**
	 * 查询判断在此值班月份是否有值班记录
	 */
	function validateDutydate(zbYf) {
		
		var returnData = '';
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jyzbhz/checkJyzbByzbYf',
			data: {zbYf:zbYf,
				    cusNumber:cusNumber},
			dataType : 'json',
			async: false,
			success : function(data) {
				returnData = data.message;
			},
		});
		return returnData;
	}
	
	
	
	function updateZt(type){
		var msg = "是否上报该值班排班表!"
		if(type=='0'){
			msg ="是否撤回该值班排班表!"
		}
		var selarrrow = $("#gridId_zbbp_query").grid("option","selarrrow");
		var rowData = $("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
		if(selarrrow && selarrrow.length == 1){
			var zt =rowData.zt; 
			if(type =="0"){
				if(zt=='0'){
					$.message({message:"已经为未上报状态！", cls:"waring"});
					return;
				}
			}else{
				if(zt=='1'){
					$.message({message:"已经为发布状态！", cls:"waring"});
					return;
				}
			}
			$.confirm(msg, function(r) {
				if(r) {
					var id = rowData.id;        //模板部门表主键
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/jyzbhz/updateById',
						data: {
							id : id,
							zt:type
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "200") {
								$.message({message:data.message, cls:"success"});
								$("#gridId_zbbp_query").grid("reload");
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
		}else{
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}
	}
	
	
	function deleteById() {
		
		var selarrrow = $("#gridId_zbbp_query").grid("option", "selarrrow");	
		var rowData =$("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
		var flag = true;
		if(selarrrow && selarrrow.length == 1) {
			
			var zt = rowData.zt;
				if(zt=='1'){
					$.message({message:"该值班表为上报状态,不能删除.如要删除,请先撤销！", cls:"waring"});
					return;
				}
				if(zt=='2'){
					$.message({message:"该值班表已经审核,不能删除！", cls:"waring"});
					return;
				}
				$.confirm("是否确认删除 ? 注意！此编排的值班民警将会一并删除'", function(r) {
					if(r) {
						var id = rowData.id;      
						$.loading({text:"正在处理中，请稍后..."});
						$.ajax({
							type : 'post',
							url : '${ctx}/zbgl/jyzbhz/deleteById',
							data: {
								id : id,
							},
							dataType : 'json',
							success : function(data) {
								$.loading("hide");
								if(data.code == 200) {
									$.message({message:"操作成功！", cls:"success"});
									$("#gridId_zbbp_query").grid("reload");
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
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}		
	}
	
</script>