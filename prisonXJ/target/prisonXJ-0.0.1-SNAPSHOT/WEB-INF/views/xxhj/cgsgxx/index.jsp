<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height:100%;">
	<cui:form id="formId_cgsgxx_query">
		<table class="table">
			<tr>	
				<th>监区：</th>
				<td>
                    <cui:combobox id="comboboxId_query_cgsgxxJq" name="pwrDprtmntId"></cui:combobox>
<%--                    开始时间：
                    <cui:datepicker id="pwrTimeStart" name="pwrTimeStart" dateFormat="yyyy-MM-dd HH:mm:ss"
                                    width="150"></cui:datepicker>
                    至：
                    <cui:datepicker id="pwrTimeEnd" name="pwrTimeEnd" dateFormat="yyyy-MM-dd HH:mm:ss"
                                    width="150"></cui:datepicker>
                    出工状态：
                    <cui:combobox id="pwrStatus" name="pwrStatus" data="cgztData" width="100"></cui:combobox>--%>
                </td>
				<td>
	 				<cui:button label="查询" onClick="search"></cui:button>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_cgsgxx_query" data="toolbar_cgsgxx_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_cgsgxx_query" rownumbers="true" multiselect="true" width="auto" height="auto" fitStyle="fill" url="${ctx}/xxhj/cgsgxx/searchData" rowNum="20">  <%-- url="${ctx}/xxhj/cgsgxx/searchData"--%>
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="pwrDprtmntId" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': combobox_bm}">监区</cui:gridCol>
			<cui:gridCol name="pwrFactoryName" align="center">车间名称</cui:gridCol>
			<cui:gridCol name="pwrLeadPoliceName" align="center">车间负责人</cui:gridCol>
			<cui:gridCol name="pwrLocalPoliceId" align="center">联系方式</cui:gridCol>
			<cui:gridCol name="pwrPoliceCount" align="center" width="80">警力</cui:gridCol>
			<cui:gridCol name="pwrPrisonerCount" align="center" width="100">罪犯人数</cui:gridCol>
			<cui:gridCol name="pwrStartTime" align="center">出工时间</cui:gridCol>
			<cui:gridCol name="pwrBackTime" align="center">收工时间</cui:gridCol>
			<%--<cui:gridCol name="pwrUpdtUserName" align="center">填报人</cui:gridCol>--%>
			<cui:gridCol name="pwrStatus" align="center" formatter="cgFormatter">出工状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_cgsgxx_query" />
	</cui:grid>
	<cui:dialog id="dialogId_cgsgxx" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_cgsgxx">
	</cui:dialog>
	<cui:dialog id="dialogId_cgsgxx_view" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true">
	</cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	$.parseDone(function() {
		$("#comboboxId_query_cgsgxxJq").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
/*        var dt=new Date();
        dt.setHours(0);
        dt.setMinutes(0);
        dt.setSeconds(0);
        $('#pwrTimeStart').datepicker("setDate", dt);
        search();*/
	});

    var cgztData = [
        {"value":1, "text":"未完成"},
        {"value":2, "text":"已完成"}];

	var toolbar_cgsgxx_query = [{
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
        "id" : "btnId_complete",
        "label" : "收工",
        "onClick" : "completeByIds",
        //"componentCls" : "btn-primary"
    }];
	
	var buttons_cgsgxx = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	$("#dialogId_cgsgxx").dialog("close");
	    }            
	}];
	
	function search() {
		var formData = $("#formId_cgsgxx_query").form("formData");
		$("#gridId_cgsgxx_query").grid("option", "postData", formData);
		$("#gridId_cgsgxx_query").grid("reload", "${ctx}/xxhj/cgsgxx/searchData");
	}
	
	function clear() {
		$("#formId_cgsgxx_query").form("reset");
	}
	
	function openDailog(event, ui) {
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/xxhj/cgsgxx/toEdit";
			$("#dialogId_cgsgxx").dialog({
				width : 1030,
				height : 350,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_cgsgxx").dialog("open");
		} else {	
			var selarrrow = $("#gridId_cgsgxx_query").grid("option","selarrrow");
			var rowData = $("#gridId_cgsgxx_query").grid("getRowData", selarrrow[0]);
			if(selarrrow && selarrrow.length == 1 ) {
                if(rowData.pwrStatus == '未完成'){
					if (ui.id == "btnId_update") {
						url = "${ctx}/xxhj/cgsgxx/toEdit?id=" +rowData.id;
						$("#dialogId_cgsgxx").dialog({
							width : 1030,
							height : 350,
							title : ui.label,
							url : url
						});
						$("#dialogId_cgsgxx").dialog("open");
					}
                }else{
                    $.messageQueue({ message : "不能修改已完成数据", cls : "warning", iframePanel : true, type : "info" });
                    return;
                }
			} else {
				$.messageQueue({ message : "请选择一条记录！", cls : "warning", iframePanel : true, type : "info" });
				return;
			}			
		}
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_cgsgxx_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_cgsgxx_edit").form("formData");
        formData.pwrDprtmntName = $("#comboboxId_cgsgxxJq").combobox("getText");

		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/xxhj/cgsgxx/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.messageQueue({ message : "操作成功", cls : "success", iframePanel : true, type : "info" });
					$("#dialogId_cgsgxx").dialog("close");
					$("#gridId_cgsgxx_query").grid("reload");
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
		
		var selarrrow = $("#gridId_cgsgxx_query").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
							ids += selarrrow[i] + ",";
					}
					if(ids != '' && ids != null) {
						$.loading({text:"正在处理中，请稍后..."});
						
						$.ajax({
							type : 'post',
							url : '${ctx}/xxhj/cgsgxx/deleteByIds',
							data: {
								ids : ids
							},
							dataType : 'json',
							success : function(data) {
								$.loading("hide");
								
								if(data.code == "1") {
									$.message({message:"操作成功！", cls:"success"});
									$("#gridId_cgsgxx_query").grid("reload");
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

    function completeByIds() {
        var selarrrow = $("#gridId_cgsgxx_query").grid("option", "selarrrow");

        if(selarrrow && selarrrow.length > 0) {
            $.confirm("确认收工？", function(r) {
                if(r) {
                    var ids = "";
                    for(var i = 0; i < selarrrow.length; i++) {
                        ids += selarrrow[i] + ",";
                    }
                    if(ids != '' && ids != null) {
                        $.loading({text:"正在处理中，请稍后..."});

                        $.ajax({
                            type : 'post',
                            url : '${ctx}/xxhj/cgsgxx/completeByIds',
                            data: {
                                ids : ids
                            },
                            dataType : 'json',
                            success : function(data) {
                                $.loading("hide");

                                if(data.code == "1") {
                                    $.message({message:"操作成功！", cls:"success"});
                                    $("#gridId_cgsgxx_query").grid("reload");
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

    function cgFormatter(cellvalue, options, rawObject) {
        if (cellvalue == '1') {
            return "未完成";
        } else if (cellvalue == '2') {
            return "已完成";
        }else {
            return "";
        }
    }
</script>
