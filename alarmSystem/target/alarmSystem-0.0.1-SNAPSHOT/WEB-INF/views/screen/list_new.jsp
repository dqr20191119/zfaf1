<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_dpya_query">
		<table class="table">
			<tr>
				<th>预案名称：</th>
				<td>
					<cui:input id="screenPlanName" name="screenPlanName"></cui:input>
				</td>
				<th>状态：</th>
				<td>
					<cui:combobox name="status" data="dpya_sttsData" componentCls="form-control"></cui:combobox>
				</td>
				<td>
					<cui:button label="查询" onClick="dpya_query"></cui:button>
					<cui:button label="重置" onClick="dpya_clear"></cui:button>
				</td>
			</tr>
		</table>
		</cui:form>
		<cui:toolbar id="toolbarId_screenPlan" data="toolbar_screenPlanDate"></cui:toolbar>
		<cui:grid id="gridId_screenPlan" fitStyle="fill" singleselect="true" colModel="gridId_screenPlan_colModelDate">
			<cui:gridPager gridId="gridId_screenPlan" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_screenPlan" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber=jsConst.CUS_NUMBER;
	var userLevel = jsConst.USER_LEVEL;
	// 状态
	var dpya_sttsData = [{
	    "value":"0",
        "text":"未使用"
    },{
        "value":"1",
        "text":"使用中"
    }];
	

	$.parseDone(function() {
		var url = "${ctx}/screenPlan/pageSelectAll.json?cusNumber=" + cusNumber;
		$("#gridId_screenPlan").grid("reload", url);

	});
	var gridId_screenPlan_colModelDate = [ {
		name : "id",
		label : "id",
		key : true,
		hidden : true
	},{
        name : "tywallId",
        label : "电视墙id",
        hidden : true
    },{
        name : "screenPlanName",
        label : "预案名称",
        align:"center"
    },{
        name : "status",
        label : "状态",
        align:"center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_sttsData }
    }];

	toolbar_screenPlanDate = [/* {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "增加",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_update",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_del",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} , {
		"type" : "button",
		"id" : "btnId_setting",
		"label" : "信号分组",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}*/ {
        "type" : "button",
        "id" : "btnId_setting",
        "label" : "配置摄像头",
        "onClick" : "openDailog",
        //"componentCls" : "btn-primary"
    }, {
        "type" : "button",
        "id" : "btnId_qh",
        "label" : "电视墙预案切换",
        "onClick" : "qieHuan",
        //"componentCls" : "btn-primary"
    },{
        "type" : "button",
        "id" : "btnId_sq",
        "label" : "上墙",
        "onClick" : "shangQiang",
        //"componentCls" : "btn-primary"
    }];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {
		case "btnId_setting":
			var selrow = $("#gridId_screenPlan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
				dialog_width = "1200";
				dialog_height = "600";
                url = "${ctx}/screenPlan/openDialog/setting?id=" + rowData.id + "&name="+rowData.screenPlanName;
                url = encodeURI(url);
			} else {
				$.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_screenPlan").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_screenPlan").dialog("open");
		}
	}

    /**
     * 上墙
     */
	function shangQiang() {
        var selrow = $("#gridId_screenPlan").grid("option", "selrow");
        if(selrow !=null){
            var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
            var id = rowData.id;
            var tywallId = rowData.tywallId;
            var status = rowData.status;
            if(status=="1"){
                $.ajax({
                    type : "post",
                    url : "${ctx}/screenPlan/screenPlanSq",
                    data : {id:id,tywallId:tywallId,cusNumber:cusNumber},
                    dataType : "json",
                    success : function(data) {
                        if (data.code="200") {
                            $("#gridId_screenPlan").grid("reload");
                            $.messageQueue({ message : "操作成功", cls : "success", iframePanel : true, type : "info" });
                        } else {
                            $.messageQueue({ message : "操作失败", cls : "warning", iframePanel : true, type : "info" });
                        }
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
                    }
                });
            }else{
                $.messageQueue({ message : "该预案没有使用,如要上墙,请先切换", cls : "warning", iframePanel : true, type : "info" });
            }
        }else{
            $.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
        }
    }

    /**
     * 电视墙切换
     */
    function qieHuan() {
        var selrow = $("#gridId_screenPlan").grid("option", "selrow");
        if (selrow != null) {
            var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
            var id = rowData.id;
            var tywallId = rowData.tywallId;
            $.ajax({
                type : "post",
                url : "${ctx}/screenPlan/screenPlanQh",
                data : {id:id,tywallId:tywallId,cusNumber:cusNumber},
                dataType : "json",
                success : function(data) {
                        if (data.code="200") {
                            $("#gridId_screenPlan").grid("reload");
                            $.messageQueue({ message : "操作成功", cls : "success", iframePanel : true, type : "info" });
                        } else {
                            $.messageQueue({ message : "操作失败", cls : "warning", iframePanel : true, type : "info" });
                        }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
                }
            });
        }else{
            $.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
        }


    }
	function dpya_query() {
		var formData = $("#formId_dpya_query").form("formData");
		$("#gridId_screenPlan").grid("option", "postData", formData);
		$("#gridId_screenPlan").grid("reload");
		//关闭当前弹窗		
		$("#dialogId_screenPlan").dialog("close");
	}

	function dpya_clear() {
		$("#formId_dpya_query").form("clear");
	}
</script>