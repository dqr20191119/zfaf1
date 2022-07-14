<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height:100%;">
	<cui:form id="formId_jndt_query">
		<table class="table">
			<tr>	
				<th>监区：</th>
				<td><cui:combobox id="comboboxId_query_jndtJq" name="parDprtmntId"></cui:combobox></td>
				<td>
	 				<cui:button label="查询" onClick="search"></cui:button>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jndt_query" data="toolbar_jndt_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_jndt_query" rownumbers="true" multiselect="true" width="auto" height="auto" fitStyle="fill" url="${ctx}/xxhj/jndt/searchData" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="parDprtmntId" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': combobox_bm}">监区</cui:gridCol>
			<cui:gridCol name="parOutCategory" align="center" formatter="formatOutReason">事由</cui:gridCol>
			<cui:gridCol name="parPoliceName" align="center">负责人</cui:gridCol>
			<cui:gridCol name="parPoliceCount" align="center">警力</cui:gridCol>
			<cui:gridCol name="parPrisonerCount" align="center">罪犯人数</cui:gridCol>
			<cui:gridCol name="parStartTime" align="center">流程发起时间</cui:gridCol>
			<%--<cui:gridCol name="parStartReporterName" align="center">发起报备人</cui:gridCol>--%>
			<cui:gridCol name="parBackTime" align="center">流程结束时间</cui:gridCol>
			<%--<cui:gridCol name="parBackReporterName" align="center">结束报备人</cui:gridCol>--%>
			<cui:gridCol name="parStatus" align="center" formatter="jndtFormatter">流程状态</cui:gridCol>

		</cui:gridCols>
		<cui:gridPager gridId="gridId_jndt_query" />
	</cui:grid>
	<cui:dialog id="dialogId_jndt" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_jndt">
	</cui:dialog>
	<cui:dialog id="dialogId_jndt_view" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true">
	</cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var comboboxData_jcsy = [
		{value:"JJ",text:"接见"},                   
		{value:"CFCG",text:"厂房出工"},   
		{value:"LSCG",text:"临时出工"},
		{value:"FF",text:"放风"},
		{value:"JNJY",text:"监内就医"},
		{value:"WC",text:"外出"},
		{value:"SY",text:"收押"},
		{value:"SF",text:"释放"},
		{value:"QT",text:"其他"}];
	
	$.parseDone(function() {
		$("#comboboxId_query_jndtJq").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
	});
	
	
	var toolbar_jndt_query = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增登记",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_update",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_count",
		"label" : "统计详情",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},{
        "type" : "button",
        "id" : "btnId_complete",
        "label" : "结束",
        "onClick" : "completeJndtByIds",
    }]
	
	var buttons_jndt = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	$("#dialogId_jndt").dialog("close");
	    }            
	}];
	
	function formatOutReason(cellvalue, options, rowObject) {
		
    	var outCategory = convertColVal(comboboxData_jcsy, cellvalue);
    	var outReason = rowObject.parOutReason;
		var value = outCategory+"-"+outReason;
		return value;
	}
	
	function search() {
		var formData = $("#formId_jndt_query").form("formData");
		$("#gridId_jndt_query").grid("option", "postData", formData);
		$("#gridId_jndt_query").grid("reload");
	}
	
	function clear() {
		$("#formId_jndt_query").form("reset");
	}
	
	function openDailog(event, ui) {
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/xxhj/jndt/toEdit";
			$("#dialogId_jndt").dialog({
				width : 1030,
				height : 450,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_jndt").dialog("open");
		} else if(ui.id == "btnId_count") {
			
			url = "${ctx}/xxhj/jndt/toViewCount";
			
			$("#dialogId_jndt_view").dialog({
				width : 1030,
				height : 400,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_jndt_view").dialog("open");
			
		} else {	
			var selarrrow = $("#gridId_jndt_query").grid("option","selarrrow");
			var rowData = $("#gridId_jndt_query").grid("getRowData", selarrrow[0]);
			if(selarrrow && selarrrow.length == 1 ) {
				
				if (ui.id == "btnId_update") {
					url = "${ctx}/xxhj/jndt/toEdit?id=" +rowData.id;
					$("#dialogId_jndt").dialog({
						width : 1030,
						// height : 650,
						height : 450,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_jndt").dialog("open");
				} else if(ui.id == "btnId_view") {
					
					url = "${ctx}/xxhj/jndt/toView?id=" +rowData.id;
					
					$("#dialogId_jndt_view").dialog({
						width : 1030,
						height : 850,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_jndt_view").dialog("open");
					
				} 
				
			} else {
				$.messageQueue({ message : "请选择一条记录！", cls : "warning", iframePanel : true, type : "info" });
				return;
			}			
		}
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_jndt_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_jndt_edit").form("formData");
		formData.parDprtmntName = $("#comboboxId_jndtJq").combobox("getText");

		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/xxhj/jndt/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.messageQueue({ message : "操作成功", cls : "success", iframePanel : true, type : "info" });
					$("#dialogId_jndt").dialog("close");
					$("#gridId_jndt_query").grid("reload");
				} else if(data.code == "2") {
					$.messageQueue({ message : data.msg, cls : "error", iframePanel : true, type : "info" });
				} else {
					$.messageQueue({ message : "操作失败", cls : "error", iframePanel : true, type : "info" });
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.messageQueue({ message : "操作失败", cls : "error", iframePanel : true, type : "info" });
			}
		});
	}
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_jndt_query").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						var RowData = $("#gridId_jndt_query").grid("getRowData", selarrrow[i]);
						if(RowData.parBackTime || RowData.parBackReporterName) {
							$.message({message:"注意！无法删除流程已完成记录！", cls:"waring"});
							ids = '';
							break;
						} else {
							ids += selarrrow[i] + ",";
						}
					}
					if(ids != '' && ids != null) {
						$.loading({text:"正在处理中，请稍后..."});
						
						$.ajax({
							type : 'post',
							url : '${ctx}/xxhj/jndt/deleteByIds',
							data: {
								ids : ids
							},
							dataType : 'json',
							success : function(data) {
								$.loading("hide");
								
								if(data.code == "1") {
									$.message({message:"操作成功！", cls:"success"});
									$("#gridId_jndt_query").grid("reload");
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
					
				}
			}); 		
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}

	function completeJndtByIds(){
        var selarrrow = $("#gridId_jndt_query").grid("option", "selarrrow");

        if(selarrrow && selarrrow.length > 0) {
            $.confirm("确认结束？", function(r) {
                if(r) {
                    var ids = "";
                    for(var i = 0; i < selarrrow.length; i++) {
                        ids += selarrrow[i] + ",";
                    }
                    if(ids != '' && ids != null) {
                        $.loading({text:"正在处理中，请稍后..."});

                        $.ajax({
                            type : 'post',
                            url : '${ctx}/xxhj/jndt/completeJndtByIds',
                            data: {
                                ids : ids
                            },
                            dataType : 'json',
                            success : function(data) {
                                $.loading("hide");

                                if(data.code == "1") {
                                    $.message({message:"操作成功！", cls:"success"});
                                    $("#gridId_jndt_query").grid("reload");
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

                }
            });
        } else {
            $.message({message:"请选择记录！", cls:"waring"});
            return;
        }
	}

    function jndtFormatter(cellvalue, options, rawObject) {
        if (cellvalue == '1') {
            return "未结束";
        } else if (cellvalue == '2') {
            return "已结束";
        }else {
            return "";
        }
    }
</script>
