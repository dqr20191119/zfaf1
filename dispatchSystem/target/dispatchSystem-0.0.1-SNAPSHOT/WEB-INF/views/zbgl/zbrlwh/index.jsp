<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rlwh_query">
		<table class="table">
			<tr><th>开始日期 ：</th>
                <td><cui:datepicker id ="rlwh_startDate" name="startDate"  dateFormat="yyyy-MM-dd" ></cui:datepicker>
                <td>
                <th>结束日期 ：</th>
                <td><cui:datepicker id ="rlwh_endDate" name="endDate"  dateFormat="yyyy-MM-dd" ></cui:datepicker>
                <td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
            <tr>
                <th>节假日标签 ：</th>
                <td><cui:combobox  name="flag"  data="combobox_flag" showClose="true" ></cui:combobox>
            </tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_rlwh" data="toolbar_rlwh"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_rlwh" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/zbgl/zbrlwh/searchData" rowNum="31">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dutyDate" align="center">日期</cui:gridCol>
            <cui:gridCol name="weekDay" align="center">星期</cui:gridCol>
            <cui:gridCol name="flag" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_flag
			}">节假日标签</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rlwh" />
	</cui:grid>
	
	<cui:dialog id="dialogId_rlwh" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_rlwh" maximizable="true">
	</cui:dialog>

    <cui:dialog id="dialogId_rlwh_update" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_update" maximizable="true">
    </cui:dialog>
</div>

<script>

	$.parseDone(function() {
		
	});

	var combobox_flag =[{"value":"0","text":"工作日"},
        {"value":"1","text":"普通休息日"},
        {"value":"2","text":"法定节假日"}
    ];

	var toolbar_rlwh = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "toUpdate()",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}];

	var buttons_update =[{
        text: "保存",
        id: "btnId_update",
        click: function () {
            update();
        }
    }, {
        text: "取消",
        id: "btnId_cancel_update",
        click: function () {

            $("#dialogId_rlwh_update").dialog("close");
        }
    }]


	var buttons_rlwh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_rlwh").dialog("close");
	    }            
	}];


	function update() {
        var validFlag = $("#formId_rlwh_update").form("valid");
        if(!validFlag) {
            return;
        }

        var formData = $("#formId_rlwh_update").form("formData");
        $.loading({text:"正在处理中，请稍后..."});
        $.ajax({
            type : 'post',
            url : '${ctx}/zbgl/zbrlwh/updateById',
            data: formData,
            dataType : 'json',
            success : function(data) {
                $.loading("hide");
                if(data.code == "200") {
                    $.message({message:"操作成功！", cls:"success"});
                    $("#dialogId_rlwh_update").dialog("close");
                    $("#gridId_rlwh").grid("reload");

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


	function toUpdate() {
	    debugger
            var selarrrow = $("#gridId_rlwh").grid("option", "selarrrow");
            var url = "";
            if(selarrrow && selarrrow.length == 1) {
               url = "${ctx}/zbgl/zbrlwh/toUpdate?id=" + selarrrow[0];
            } else {
                $.message({message:"请选择一条记录！", cls:"waring"});
                return;
            }
        $("#dialogId_rlwh_update").dialog({
            width : 500,
            height : 300,
            title : "修改",
            url : url
        });
        $("#dialogId_rlwh_update").dialog("open");
    }


	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			url = "${ctx}/zbgl/zbrlwh/toEdit";
		}
		$("#dialogId_rlwh").dialog({
			width : 900,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rlwh").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_rlwh_query").form("formData");
		$("#gridId_rlwh").grid("option", "postData", formData);
		$("#gridId_rlwh").grid("reload");
	}
	
	function reset() {
		
		$("#formId_rlwh_query").form("reset");
	}
	
	// function formatDrptmntName(cellvalue, options, rowObject) {
	// 	var value = '';
	// 	if( rowObject.dcaDprtmntId) {
	// 		var dcaDprtmntIds = (rowObject.dcaDprtmntId).split(",");
	// 		for(var i = 0;i < dcaDprtmntIds.length; i++) {
	//
	// 			if(i == 0) {
	// 				value =convertColVal(combobox_bm, dcaDprtmntIds[i]);
	// 			}else {
	// 				value = value+ "," +convertColVal(combobox_bm, dcaDprtmntIds[i]);
	// 			}
	//     	}
	// 	}
	// 	return value;
	// }

	function saveOrUpdate() {
        var allRowDate = $("#grid_rlwhDetail").grid("getRowData");
        var validFlag = $("#grid_rlwhDetail").grid("valid");
        if(!validFlag) {
            return;
        }
        var formData ={};
        console.log("allRowDate="+JSON.stringify(allRowDate));
        formData["rlwhDatas"]=JSON.stringify(allRowDate);
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/zbrlwh/saveData',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "200") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_rlwh").dialog("close");
					$("#gridId_rlwh").grid("reload");
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
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_rlwh").grid("option", "selarrrow");	
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
						url : '${ctx}/zbgl/zbrlwh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "200") {
                                $.message({message: "操作成功！", cls: "success"});
                                $("#gridId_rlwh").grid("reload");
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