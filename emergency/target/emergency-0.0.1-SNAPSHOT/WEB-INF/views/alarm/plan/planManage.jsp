<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
</style>

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<div>
			<cui:form id="formId_query">
				<table class="table">
					<tr>
						<th>预案：</th>
						<td>
							<cui:input name="pmaPlanName" componentCls="form-control"></cui:input>
						</td>
						<td>
							<cui:button  label="查询" onClick="query"></cui:button>
							<cui:button  label="重置" onClick="reset"></cui:button>
						</td>
					</tr>
				</table>
			</cui:form>
		</div>
		<cui:toolbar id="toolbarId_plan" data="toolbar_planDate"></cui:toolbar>
		<cui:grid id="gridId_plan" fitStyle="fill" singleselect="true" colModel="gridId_plan_colModelDate">
			<cui:gridPager gridId="gridId_plan" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_plan" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
	<!-- 显示预案详情 -->
	<cui:dialog id="dialogId_plan_detail" iframePanel="true" autoOpen="false" resizable="false" autoDestroy="true" modal="true" buttons="dia_button"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var isDo = 0;//请求次数,用于判断关联项操作时异步请求是否完成
	var isDone = 0;//完成次数

	$.parseDone(function() {
		var url = "${ctx}/plan/listAll/plan.json?pmaCusNumber=" + cusNumber + "&P_orders=pma_crte_time,desc";
		$("#gridId_plan").grid("reload", url);
	});

	var dia_button = [ {
		text : "关闭",
		width : 80,
		click : function() {
			$("#dialogId_plan_detail").dialog("close");
		}
	} ];

	//ridio数据
	var radiolistdata = [ {
		value : "1",
		text : "启用"
	}, {
		value : "0",
		text : "关闭"
	}, ];
	var gridId_plan_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "PMA_PLAN_NAME",
		label : "预案名称",
		//align : "center",
		width : 209
	}, {
		name : "ITEMS",
		label : "ITEMS",
		hidden : true
	}, {
		name : "PMA_REMARK",
		label : "备注",
		//align : "center",
		width : 150
	}, {
		label : "详情",
		name : "check",
		align : "center",
		width : "75",
		formatter : "Formatter"
	}  ];

	
	function Formatter(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var result = '<a href="" style="color: #4692f0;" onclick="getPlanInfo(\''+param1.toString()+'\');return false;">查看</a>';
		return result;
	}
	
	toolbar_planDate = [ {
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
	}, {
		"type" : "button",
		"id" : "btnId_device",
		"label" : "关联设备",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_alertor",
		"label" : "关联报警器",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},
	{
		"type" : "button",
		"id" : "btnId_braodcastPlan",
		"label" : "关联广播预案",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	},
	{
		"type" : "button",
		"id" : "btnId_emerPreplan",
		"label" : "关联应急预案",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {
		case "btnId_add":
			dialog_width = "360";
			dialog_height = "460";
			url = "${ctx}/plan/openDialog/add";
			open(dialog_width, dialog_height, url, ui.label);
			break;

		case "btnId_update":

			var selrow = $("#gridId_plan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
				dialog_width = "360";
				dialog_height = "460";
				url = "${ctx}/plan/openDialog/update?id=" + rowData.ID;
				open(dialog_width, dialog_height, url, ui.label);
			} else {
				$.messageQueue({ message : "请选择要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_device":

			var selrow = $("#gridId_plan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
				var item = rowData.ITEMS;
				if (item != "") {
					dialog_width = "1000";
					dialog_height = "600";
					url = "${ctx}/plan/openDialog/device?id=" + rowData.ID;
					open(dialog_width, dialog_height, url, ui.label);
				} else {
					$.messageQueue({ message : "该报警预案未设置关联项！", cls : "warning", iframePanel : true, type : "info" });
				}

			} else {
				$.messageQueue({ message : "请选择一条记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_alertor":
			var selrow = $("#gridId_plan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
				var item = rowData.ITEMS;
				if (item != "") {
					dialog_width = "1000";
					dialog_height = "600";
					url = "${ctx}/plan/openDialog/alertor?id=" + rowData.ID + "&planName=" + rowData.PMA_PLAN_NAME;
					//在这里。用encodeURI进行两次转码。后台Action接收的时候。在进行一次。就不会出现乱码问题
					url = encodeURI(url);
					url = encodeURI(url);
					open(dialog_width, dialog_height, url, ui.label);
				} else {
					$.messageQueue({ message : "该报警预案未设置关联项！", cls : "warning", iframePanel : true, type : "info" });
				}

			} else {
				$.messageQueue({ message : "请选择一条记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		case "btnId_braodcastPlan":
			var selrow = $("#gridId_plan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
				var item = rowData.ITEMS;
				if (item != "") {
					dialog_width = "400";
					dialog_height = "300";
					url = "${ctx}/plan/openDialog/broadcastPlan?id=" + rowData.ID + "&planName=" + rowData.PMA_PLAN_NAME;
					//在这里。用encodeURI进行两次转码。后台Action接收的时候。在进行一次。就不会出现乱码问题
					url = encodeURI(url);
					url = encodeURI(url);
					open(dialog_width, dialog_height, url, ui.label);
				} else {
					$.messageQueue({ message : "该报警预案未设置关联项！", cls : "warning", iframePanel : true, type : "info" });
				}

			} else {
				$.messageQueue({ message : "请选择一条记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_emerPreplan":
			var selrow = $("#gridId_plan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
				var item = rowData.ITEMS;
				if (item != "") {
					dialog_width = "400";
					dialog_height = "300";
					url = "${ctx}/plan/openDialog/emerPlan?alarmPlanId=" + rowData.ID;
					open(dialog_width, dialog_height, url, ui.label);
				} else {
					$.messageQueue({ message : "该报警预案未设置关联项！", cls : "warning", iframePanel : true, type : "info" });
				}
			} else {
				$.messageQueue({ message : "请选择一条记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
			
		default:
			break;
		}
	}

	function query() {
		var formData = $("#formId_query").form("formData");
		$("#gridId_plan").grid("option", "postData", formData);
		$("#gridId_plan").grid("reload");
	}

	function reset() {
		$("#formId_query").form("clear");
	}

	function open(dialog_width, dialog_height, url, label) {
		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_plan").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : label,
			});
			$("#dialogId_plan").dialog("open");
		}
	}

	function remove() {
		var selrow = $("#gridId_plan").grid("option", "selrow");
		if (selrow != null) {
			var rowData = $("#gridId_plan").grid("getRowData", selrow.toString());
			$.confirm("将删除预案关联项、设备和报警器，是否删除选中的记录？", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/plan/delete/master.json?id=" + rowData.ID + "&pmaCusNumber=" + cusNumber,
						dataType : "json",
						type : "post",
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_plan").grid("reload");
								$.messageQueue({ message : "删除记录成功", cls : "success", iframePanel : true, type : "info" });
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

	//后台请求操作 关联项
	function handleItem(data, message, type) {
		isDo = isDo + 1;
		var ur = "${ctx}/plan/" + type + "/item.json";
		$.ajax({
			type : "post",
			url : ur,
			data : JSON.stringify(data),
			dataType : "json",
			contentType : 'application/json; charset=UTF-8',
			success : function(data) {
				if (data.success) {
					isDone = isDone + 1;
					var msg = "";
					switch (type) {
					case "inster":
						msg = "关联项【" + message + "】保存成功";
						break;
					case "update":
						msg = "关联项【" + message + "】修改成功";
						break;
					case "delete":
						msg = "关联项【" + message + "】和对应的【" + message + "设备】删除成功";
						break;
					default:
						break;
					}
					$.messageQueue({ message : msg, cls : "success", iframePanel : true, type : "info" });
					if (isDo == isDone) {//如果全部操作成功，刷新列表，关闭弹窗
						$("#gridId_plan").grid("reload");
						$("#dialogId_plan").dialog("close");
						isDo = 0;//请求次数,用于判断关联项操作时异步请求是否完成
						isDone = 0;//完成次数
					}
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	function getPlanInfo(planId) {
		$("#dialogId_plan_detail").empty();
		$("#dialogId_plan_detail").loading({ text : "正在请求预案详情信息，请稍后..."});
		$("#dialogId_plan_detail").dialog({
			width : 600,
			height : 500,
			title : '预案详情',
		});
		$("#dialogId_plan_detail").dialog("open");
		queryPlanDetail(planId);
	}

	function queryPlanDetail(id) {
		var url = "${ctx}/plan/findPlanById.json?id=" + id;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					_showDetail(data.obj);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	/*
	 * 显示报警预案详情
	 */
	function _showDetail(detail) {
		var alerts = detail.alerts;
		var items = detail.items;
		var broadcastPlan =detail.broadcastPlan;
		var emerPlanNames  = detail.emerPlanNames;
		var devices = null;
		var content = "<tr style=\"background-color: #F5FAFA; color: #000;\"> <th colspan=\"4\"align=\"center\">关联报警器</th></tr>"
		for (var i = 0; i < alerts.length; i++) {
			content = content + "<tr> <td colspan=\"4\"align=\"center\"> <span>" + alerts[i].aprDvcName + "</span> </td></tr> ";
		}

		if(alerts.length == 0){
			content = content + "<tr> <td colspan=\"4\"align=\"center\"> <span>【未关联报警器】 </span> </td></tr> ";
		}
		if(broadcastPlan.length>0){
			for (var i = 0; i < broadcastPlan.length; i++) {
				content = content + "<tr style=\"background-color: #F5FAFA; color: #000;\"> <th colspan=\"4\"align=\"center\">关联的广播预案</th></tr><tr> <td colspan=\"4\"align=\"center\"> <span>" + broadcastPlan[i].bprBroadcastPlanName + "</span> </td></tr> ";
			}
		}
        if(emerPlanNames.length>0){
            for (var i = 0; i < emerPlanNames.length; i++) {
                content = content + "<tr style=\"background-color: #F5FAFA; color: #000;\"> <th colspan=\"4\"align=\"center\">关联的应急预案</th></tr><tr> <td colspan=\"4\"align=\"center\"> <span>" + emerPlanNames[i] + "</span> </td></tr> ";
            }
        }

		for (var i = 0; i < items.length; i++) {
			var deviceSpan = "";
			devices = items[i].devices;
			console.info(devices);
			for (var j = 0; j < devices.length; j++) {
				deviceSpan = deviceSpan + "<span>" + codeToChinese(devices[j].pdrOutoIndc) + devices[j].pdrDvcName + "</span></br> ";
			}

			if (deviceSpan == "") {
				deviceSpan = "<span>【未关联设备】</span></br>";
			}
			content = content
					+ "<tr style=\"background-color: #F5FAFA; color: #000;\"> <th>关联类型：</th> <td> <span>"
					+ items[i].itemName + "</span> </td>"
					+ "<th>状态：</th> <td> <span>"
					+ codeToChinese_(items[i].pidSttsIndc)
					+ "</span> </td> </tr>"
					+ "<tr> <th>关联设备：</th> <td colspan=\"3\">" + deviceSpan
					+ "</td> </tr>";
		}

		detail.pmaRemark = detail.pmaRemark == null ||detail.pmaRemark == 'null' ? '' : detail.pmaRemark;
		var baseData = "<tr> <th>预案名称：</th> <td colspan=\"3\">" + detail.pmaPlanName + "</td> </tr>"
				+ "<tr> <th>备注：</th> <td colspan=\"3\">" + detail.pmaRemark 
				+ "</td> </tr> <tr> <th>创建时间：</th> <td colspan=\"3\">" + detail.pmaUpdtTime
				+ "</td> </tr>";
				
 
				
		$("#dialogId_plan_detail").append( "<table class =\"table\" style=\"margin: 3px;\" width=\"99%\" >" + baseData + content + "</table>");
		$("#dialogId_plan_detail").loading("hide");
	}

	function codeToChinese(code) {
		switch (code) {
		case "1":
			return "【自动】 ";
		default:
			return "【手动】 ";
		}
	}
	function codeToChinese_(code) {
		switch (code) {
		case "1":
			return "启用";
		default:
			return "关闭";
		}
	}
</script>