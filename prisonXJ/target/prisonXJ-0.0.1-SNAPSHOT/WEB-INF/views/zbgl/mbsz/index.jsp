<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_mbsz_query">
		<table class="table">
			<tr>
                <th>值班模板名称 ：</th>
                <td><cui:input id="cdmModeName" name="cdmModeName"></cui:input></td>
				<th>值班类别 ：</th>
				<td><cui:combobox id="combobox_CategoryName" name="cdmCategoryId" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1"></cui:combobox></td>	
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>

	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_mbsz" data="toolbar_mbsz"></cui:toolbar>	
	</div>
	
	<cui:grid id="gridId_mbsz" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cdmModeName" align="center">模板名称</cui:gridCol>
			<cui:gridCol name="dcaCategoryName" align="center">类别名称</cui:gridCol>
			<cui:gridCol name="cdmSfqy" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_sfqy
				}">是否启用</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_mbsz" />
	</cui:grid>
	
	<cui:dialog id="dialogId_mbsz" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_mbsz">
	</cui:dialog>
	<cui:dialog id="dialogId_mbsz_two" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" >
	</cui:dialog>
</div>

<script>
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var cusNumber = jsConst.CUS_NUMBER;
	var combobox_sfqy =[{"value":"0","text":"停用"},
						{"value":"1","text":"启用"}
						]
	$.parseDone(function() {
		
		if( USER_LEVEL =='3' && USER_LEVEL != null) {
			$("#combobox_CategoryName").combobox("reload","${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId="+drpmntId+"&param=2");

		} else {
			$("#combobox_CategoryName").combobox("reload","${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1");
		}
		initCategory(drpmntId);  
	});

	var toolbar_mbsz = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
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
		"id" : "btnId_search",
		"label" : "查看模板信息",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_viewModeList",
		"label" : "查看值班编排列表",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_qy",
		"label" : "启用",
		"onClick" : "updateSfqyById('1')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_ty",
		"label" : "停用",
		"onClick" : "updateSfqyById('0')",
		"componentCls" : "btn-primary"
	}];

	var buttons_mbsz = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_mbsz").dialog("close");
	    }            
	}];
	
	function openDailog(event, ui) {
		
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/zbgl/mbsz/toEdit";
			
			$("#dialogId_mbsz").dialog({
				width : 1000,
				height : 800,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_mbsz").dialog("open");
			
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow =$("#gridId_mbsz").grid("option","selarrrow");
			var rowData =$("#gridId_mbsz").grid("getRowData", selarrrow[0]);
					
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/mbsz/toEdit?id=" +rowData.id;
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}	
			
			$("#dialogId_mbsz").dialog({
				width : 1000,
				height : 800,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_mbsz").dialog("open");
			
		} else if(ui.id == "btnId_viewModeList") {	
			
			var selarrrow =$("#gridId_mbsz").grid("option","selarrrow");
			var rowData =$("#gridId_mbsz").grid("getRowData", selarrrow[0]);
					
			if(selarrrow && selarrrow.length == 1) {
				
				var categoryId = $("#combobox_CategoryName").combobox("getValue");
				url = "${ctx}/zbgl/mbsz/toViewModelList?dmdModeId=" +rowData.id+ "&categoryId=" +categoryId;
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}	
			
			$("#dialogId_mbsz_two").dialog({
				width : 1000,
				height : 800,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_mbsz_two").dialog("open");
		}else if(ui.id == "btnId_search") {	
			
			var selarrrow =$("#gridId_mbsz").grid("option","selarrrow");
			var rowData =$("#gridId_mbsz").grid("getRowData", selarrrow[0]);
					
			if(selarrrow && selarrrow.length == 1) {
				
				var categoryId = $("#combobox_CategoryName").combobox("getValue");
				url = "${ctx}/zbgl/mbsz/toViewModelInfo?id=" +rowData.id;
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}	
			
			$("#dialogId_mbsz_two").dialog({
				width : 1000,
				height : 800,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_mbsz_two").dialog("open");
		}
	}
	
	
	 function updateSfqyById(type){
		 var selarrrow =$("#gridId_mbsz").grid("option","selarrrow");
		var rowData =$("#gridId_mbsz").grid("getRowData", selarrrow[0]);
		if(selarrrow && selarrrow.length == 1) {
			url = "${ctx}/zbgl/mbsz/updateSfqyById?id=" +rowData.id+"&sfqy="+type;
			if(type==0){//停用
				$.ajax({
					type : 'post',
					url : url,
					dataType : 'json',
					success : function(data) {
						var code = data.code;
						if(code==200){
							$.message({message:data.message, cls:"success"}); 	
							//$("#gridId_mbsz").grid("reload","${ctx}/zbgl/mbsz/searchData");
							search();
						}else{
							$.message({message:data.message, cls:"error"});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.message({message:"操作失败！", cls:"error"}); 
					}
				});
				
			} else{//启用
				//先查询模板保证值班类别只有一个模板在启用
               if(rowData.cdmSfqy =="1") {
                   $.message({message:"该模板已经启用,不需要重复启用", cls:"error"});
                   return;
               }

				$.ajax({
					type : 'post',
					url : "${ctx}/zbgl/mbsz/searchSfqy?cusNumber="+cusNumber+"&id="+rowData.id,
					dataType : 'json',
					success : function(data) {
						//alert(data.code);
						var code = data.code;
						if(code==200){
							//$.message({message:data.data.message, cls:"success"}); 	
							//$("#gridId_mbsz").grid("reload","${ctx}/zbgl/mbsz/searchData");
							var mbNames = data.data;
							if(mbNames.length <= 0){
								//可以启用
								//启用模板
								$.ajax({
									type : 'post',
									url : url,
									dataType : 'json',
									success : function(data) {
										var code = data.code;
										if(code==200){
											$.message({message:data.message, cls:"success"}); 	
											//$("#gridId_mbsz").grid("reload","${ctx}/zbgl/mbsz/searchData");
											search();
										}else{
											$.message({message:data.message, cls:"error"});
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.message({message:"操作失败！", cls:"error"}); 
									}
								});
							}else{
								//不能启用 退出
								var cdmModeName ="";
								for(var k = 0 ;k < mbNames.length;k++){
                                    if(k==0){
                                        cdmModeName += mbNames[k];
                                    }else {
                                        cdmModeName +=","+mbNames[k];
                                    }
                                }
								$.message({message:"名称为:\""+cdmModeName+"\"的值班模板已经启用,请先停用该模板", cls:"error"});
								return;
							}
						}else{
							$.message({message:data.message, cls:"error"});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.message({message:"操作失败！", cls:"error"}); 
					}
				});
			} 
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}	 
	} 
	
	
	
	function search() {
		
		var formData = $("#formId_mbsz_query").form("formData");
		
		$("#gridId_mbsz").grid("option", "postData", formData);
		$("#gridId_mbsz").grid("reload","${ctx}/zbgl/mbsz/searchData");
	}
	
	function clear() {
		$("#formId_mbsz_query").form("clear");
	}
	
	function initCategory(drpmntId) {
		
		var data = {};
		data.dcdDprtmntId = drpmntId;
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId='+drpmntId,
			dataType : 'json',
			success : function(data) {
				if(data.length > 0) {
					//$("#combobox_CategoryName").combobox("setValue",data[0].DCD_CATEGORY_ID); 
					//$("#combobox_CategoryName").combobox("setText",data[0].DCA_CATEGORY_NAME);
					
				}
				search();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_mbsz_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_mbsz_edit").form("formData");
        var cdmOrderData ='';
        var cdmOrderCount = parseInt(formData.cdmOrderCount);
        
        for(var i = 1; i < cdmOrderCount + 1; i++) {
        	
        	var order = formData["cdmDutyOrderFid_" + i] + "-";
        	var positionCount = parseInt(formData["positionCount_" + i]);
        	
        	for(var j = 1; j < positionCount + 1; j++) {
        		
        		if(j < positionCount) {
        			order += formData["cdmJobFid_" + i + "_" + j] + ",";
        		} else {
        			order += formData["cdmJobFid_" + i + "_" + j];
        		}
        	}
        	
        	if(i < cdmOrderCount) {
        		cdmOrderData += order + ";";
        	} else {
        		cdmOrderData += order;
    		}
       	}
        formData.cdmOrderData = cdmOrderData;
        formData.cdmSfqy="0";
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_mbsz").dialog("close");
					$("#gridId_mbsz").grid("reload");
				} else if (data.code == "3") {
					$.message({message:"操作失败！无法修改已使用模板！", cls:"error"}); 
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
	
	function deleteById() {
		
		var selarrrow = $("#gridId_mbsz").grid("option", "selarrrow");	
		var rowData =$("#gridId_mbsz").grid("getRowData", selarrrow[0]);
		
		if(selarrrow && selarrrow.length == 1) {
			
			$.confirm("是否确认删除 [\""+ rowData.cdmModeName + "] ? <br>注意！此模板下的排班和值班民警将会一并删除", function(r) {
				if(r) {
					var id = rowData.id;
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/mbsz/deleteById',
						data: {
							id : id,
							param : '1',
						},
						dataType : 'json',
						success : function(data) {
							
							$.loading("hide");
							
							if(data.code == "1") {
								
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_mbsz").grid("reload");
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