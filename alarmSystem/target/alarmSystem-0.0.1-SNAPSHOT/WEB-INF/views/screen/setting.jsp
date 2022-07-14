<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body_xhfz {
	height: 100%;
	width: 100%;
	text-align: center;
}

.div_content {
	float: left;
	width: 29%;
	height: 100%;
	text-align: center;
}

.div_content_1 {
	float: left;
	width: 28%;
	height: 100%;
	text-align: center;
}

.div_content_2 {
	float: left;
	width: 18%;
	height: 100%;
	text-align: center;
}

.div_content_3 {
	float: left;
	width: 25%;
	height: 100%;
	text-align: center;
}

fieldset {
	height: 96.5%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<div class="div_body_xhfz">
		<div class="div_content_1">
			<fieldset>
				<legend>大屏分组</legend>
				<cui:form id="formId_xhfz" heightStyle="fill">
					<table class="table" style="width: 98%">
						<tr>
							<th>分组：</th>
							<td>
								<cui:combobox id="combo_screenArea" name="id" onSelect="onXhfzSelect"></cui:combobox>
							</td>
						</tr>
						<tr>
							<th>分组名称：</th>
							<td>
								<cui:input name="sprScreenAreaName" required="true" value="${NAME}"></cui:input>
							</td>
						</tr>
						<tr>
							<th>时间间隔：</th>
							<td>
								<cui:input name="sprTimeLag" validType="integer" required="true" value="30"></cui:input>
							</td>
						</tr>
						<tr>
							<th>是否轮巡：</th>
							<td>
								<cui:combobox name="sprIsRound" data="roundsttsData" required="true" value="1"></cui:combobox>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<cui:button onClick="saveXhfz" label="保存"></cui:button>
								<cui:button onClick="resetXhfz" label="重置"></cui:button>
								<cui:button onClick="removeDpqy" label="删除"></cui:button>
							</td>
						</tr>
					</table>
				</cui:form>
			</fieldset>
		</div>
		<div class="div_content_2">
			<fieldset>
				<legend>关联窗口号</legend>
				<div style="width: 100%; text-align: left;">
					<label>窗口数量：</label>
					<cui:input id="flowNum" validType="integer" width="40" readonly="true"></cui:input>
					<cui:button onClick="adjustScreenWindow(1,'plus')" label="+" width="20"></cui:button>
					<cui:button onClick="adjustScreenWindow(-1,'minus')" label="-" width="20"></cui:button>
					<cui:button onClick="removeScreenWindow" label="删除"></cui:button>
				</div>
				<cui:grid id="gridScreenWindow" colModel="gridScreenWindow" multiselect="true" fitStyle="fill" rowNum="999" onLoad="gridScreenWindowOnLoad">
				</cui:grid>
			</fieldset>
		</div>
		<div class="div_content">
			<fieldset>
				<legend>待关联信号源</legend>
				<div style="width: 100%; text-align: left;">
					<table style="width: 98%; padding: 2px;">
						<tr>
							<th>摄像头：</th>
							<td>
								<cui:input id="input_cameraName"></cui:input>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<th>区域：</th>
							<td>
								<cui:combotree id="combotree_camera" allowPushParent="false" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name">
								</cui:combotree>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<td colspan="2" style="text-align: center;">
								<cui:button onClick="queryCamera" label="查询"></cui:button>
								<cui:button onClick="resetCamera" label="重置"></cui:button>
								<cui:button onClick="toGridScreenCamera" label="关联"></cui:button>
							</td>
						</tr>
					</table>
				</div>

				<cui:grid id="gridCamera" colModel="gridCamera" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

		<div class="div_content_3">
			<fieldset>
				<legend>已关联信号源</legend>
				<div style="width: 100%; text-align: left;">
					<cui:button onClick="cancelRltn" label="取消关联"></cui:button>
				</div>
				<cui:grid id="gridScreenCamera" colModel="gridScreenCamera" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

	</div>

</div>
<script>
	//轮巡
	var roundsttsData = <%=CodeFacade.loadCode2Json("4.0.1")%>;

	$.parseDone(function() {
		$("#combotree_camera").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#combo_screenArea").combobox( "reload", "${ctx}/screenPlan/seachComboData/xhfz.json?cusNumber=" + cusNumber + "&planId=${ID}");
	});

	function onXhfzSelect(event, ui) {
		$.ajax({
			type : "post",
			url : "${ctx}/screenPlan/findById/xhfz.json?id=" + ui.item.value,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_xhfz").form("load", info);
					queryXhfz("1", info.id);
					queryXhfz("2", info.id);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	function queryXhfz(type, id) {
		switch (type) {
		case "1":
			var url = "${ctx}/screenPlan/listAll/xhfz.json?cusNumber=" + cusNumber + "&screenPlanId=${ID}&type=1&screenAreaId=" + id + "&P_orders=swr_crte_time,desc";
			$("#gridScreenWindow").grid("reload", url);
			break;
		case "2":
			var url = "${ctx}/screenPlan/listAll/xhfz.json?cusNumber=" + cusNumber + "&screenPlanId=${ID}&type=2&screenAreaId=" + id;
			$("#gridScreenCamera").grid("reload", url);
			break;
		default:
			break;
		}
	}

	function gridScreenWindowOnLoad() {
		$("#flowNum").val($("#gridScreenWindow").grid("getRowData").length);
	}

	//重置
	function resetXhfz() {
		$("#formId_xhfz").form("reset");
		$("#combotree_camera").combotree("setText","");
		$("#input_cameraName").textbox("setText", "");
		$("#gridScreenCamera").grid("clearGridData");
		$("#gridCamera").grid("clearGridData");
		$("#gridScreenWindow").grid("clearGridData");
		$("#flowNum").val(0);
	}

	function adjustScreenWindow(number, type) {
		var obj = $("#flowNum");
		var val = parseFloat(obj.val()) + number;
		if (val < 0 || isNaN(val)) {
			return;
		}
		var screenWindowData = $("#gridScreenWindow").grid("getRowData");
		var len = screenWindowData.length;
		if (type == "plus") {
			obj.val(val);
			var obj = new Object();
			obj.SWR_SCREEN_PLAN_ID = "${ID}";
			obj.SWR_SEQ_NUM = len + 1;
			$("#gridScreenWindow").grid("addRowData", len + 1, obj);
		} else {
			var data = $("#gridScreenWindow").grid("getRowData", len);
			if (data.ID == "") {
				$("#gridScreenWindow").grid("delRowData", len);
				obj.val(val);
			}
		}
	}

	//区域控件下拉树的点击事件
	function gridCamera_xhfz(event, ui) {
		var node = ui.node;
		if (node.isParent == false) {
			var url = "${ctx}/screenPlan/listAllForSx.json?cusNumber=" + cusNumber + "&areaId=" + node.id + "&screenPlanId=${ID}";
			$("#gridCamera").grid("reload", url);
		}
	}
	
	function queryCamera() {
		var areaId = $("#combotree_camera").combotree("getValue");
		var cameraName = $("#input_cameraName").textbox("getText");
		var formData = {};
		formData['cusNumber'] = cusNumber;
		formData['screenPlanId'] = '${ID}';
		if( areaId != ""){
			formData['areaId'] = areaId;
		}
		if( cameraName != ""){
			formData['cameraName'] = cameraName;
		}
		$("#gridCamera").grid("option", "postData", formData);
		var url = "${ctx}/screenPlan/listAllForSx";
		$("#gridCamera").grid("reload", url);
	}
	
	function resetCamera() {
		$("#input_cameraName").textbox("setText", "");
		$("#combotree_camera").combotree("setText","");
		$("#gridCamera").grid("clearGridData");
	}

	function toGridScreenCamera() {
		var selected = $("#gridCamera").grid("option", "selarrrow");
		if (selected.length < 0) {
			$.messageQueue({ message : "请选择需要关联的摄像头", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		for (var i = 0; i < selected.length; i++) {
			var cameraData = $("#gridCamera").grid("getRowData", selected[i]);
			var screenCameraDate = $("#gridScreenCamera").grid("getRowData");
			var len = screenCameraDate.length;
			for (var j = 0; j < screenCameraDate.length; j++) {
				if (screenCameraDate[j].SCR_CAMERA_ID == cameraData.ID) {
					return;
				}
			}
			var obj = new Object();
			obj.SCR_SCREEN_PLAN_ID = "${ID}";
			obj.SCR_SEQ_NUM = len + 1;
			obj.SCR_CAMERA_ID = cameraData.ID;
			obj.SCR_CAMERA_NAME = cameraData.DVC_NAME;
			$("#gridScreenCamera").grid("addRowData", len + 1, obj);
		}
		for (var i = selected.length; i > 0; i--) {
			$("#gridCamera").grid("delRowData", selected[i - 1]);
		}
	}

	//信号源取消关联，已经存库的删除操作，否则退回待关联列表
	function cancelRltn() {
		var selected = $("#gridScreenCamera").grid("option", "selarrrow");
		var ids = [];
		for (var i = selected.length; i > 0; i--) {
			var screenCameraData = $("#gridScreenCamera").grid("getRowData", selected[i - 1]);
			if (screenCameraData.ID == "") {
				$("#gridScreenCamera").grid("delRowData", selected[i - 1]);
				var cameraData = $("#gridCamera").grid("getRowData");
				var len = cameraData.length;
				var obj = new Object();
				obj.ID = screenCameraData.SCR_CAMERA_ID;
				obj.DVC_NAME = screenCameraData.SCR_CAMERA_NAME;
				$("#gridCamera").grid("addRowData", len + 1, obj);
			} else {
				ids.push(screenCameraData.ID);
			}
		}
		if (ids.length > 0) {
			removeXhfz("2", ids);//删除大屏区域关联信号源
		}
	}

	//删除关联窗口
	function removeScreenWindow() {
		var selected = $("#gridScreenWindow").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			removeXhfz("1", selected);
		} else {
			$.messageQueue({ message : "请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	function saveXhfz() {
		if ($("#formId_xhfz").form("valid")) {
			var xhfzMap = {};
			
			var screenArea = $("#formId_xhfz").form("formData");
			screenArea['screenPlanId'] = "${ID}";
			xhfzMap['screenArea'] = JSON.stringify(screenArea);

			
			
			var screenWindow = $("#gridScreenWindow").grid("getRowData");
			if (screenWindow.length == 0) {
				alert("大屏窗口未关联！");
				return;
			} 
			for (var i = 0; i < screenWindow.length; i++) {
				if (screenWindow[i].SWR_SCREEN_WINDOW_NUM == "") {
					alert("大屏窗口未填写！");
					return;
				}
			}
			xhfzMap['screenWindow'] = JSON.stringify(screenWindow);
			
			var screenCamera = $("#gridScreenCamera").grid("getRowData");
			if (screenCamera != 0) {
				xhfzMap['screenCamera'] = JSON.stringify(screenCamera);
			}
		
			$.ajax({
				type : "post",
				url : "${ctx}/screenPlan/save/xhfz.json",
				data : JSON.stringify(xhfzMap),
				dataType : "json",
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#combo_screenArea").combobox("reload");
						resetXhfz();//重置
						/* $("#gridScreenWindow").grid("reload");
						$("#gridScreenCamera").grid("reload"); */
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}
	}

	//删除大屏区域及关联信号源和窗口
	function removeDpqy() {
		var screenAreaId = $("#combo_screenArea").combobox("getValue");
		if (screenAreaId == "") {
			$.messageQueue({ message : "请选择要删除的大屏区域", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		$.confirm("是否删除选中的区域【" + $("#combo_screenArea").combobox("getText") + "】", "信息确认", function(confirm) {
			if (confirm) {
				$.ajax({
					url : "${ctx}/screenPlan/deleteXhfz/dpqy.json?screenAreaId=" + screenAreaId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if (data.success) {
							$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							$("#combo_screenArea").combobox("reload");
							resetXhfz();//重置
						} else {
							$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info"});
						}
					}
				});
			}
		});
	}

	function removeXhfz(type, data_) {
		if (data_.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/screenPlan/deleteXhfz.json?type=" + type,
						dataType : "json",
						type : "post",
						data : JSON.stringify(data_),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
								if (type == "1") {
									$("#gridScreenWindow").grid("reload");
								} else {
									$("#gridScreenCamera").grid("reload");
								}
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
	
	var gridScreenArea = [ {
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "SPR_SCREEN_PLAN_ID",
		label : "大屏预案id",
		hidden : true
	}, {
		name : "SPR_SCREEN_AREA_NAME",
		label : "区域",
		width : 280,
	}, {
		name : "SPR_IS_ROUND",
		label : "轮询",
		formatter : "checkbox",
		formatoptions : {
			value : '1:0'
		},
		align : "center",
	}, {
		name : "SPR_TIME_LAG",
		label : "间隔（s）",
		align : "center",
		formatter : "text"
	} ];

	var gridScreenWindow = [ {
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "SWR_SCREEN_PLAN_ID",
		label : "大屏预案id",
		hidden : true
	}, {
		name : "SWR_SCREEN_AREA_ID",
		label : "大屏区域id",
		hidden : true
	}, {
		name : "SWR_SEQ_NUM",
		label : "排序",
		hidden : true
	}, {
		name : "SWR_SCREEN_WINDOW_NUM",
		label : "窗口号",
		formatter : "text"
	} ];

	var gridScreenCamera = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "SCR_SCREEN_PLAN_ID",
		label : "大屏预案id",
		hidden : true
	}, {
		name : "SCR_SCREEN_AREA_ID",
		label : "大屏区域id",
		hidden : true
	}, {
		name : "SCR_SEQ_NUM",
		label : "排序",
		width : 40,
		formatter : "text"
	}, {
		name : "SCR_CAMERA_ID",
		label : "摄像头id",
		hidden : true
	}, {
		name : "SCR_CAMERA_NAME",
		label : "摄像头"
	} ];

	var gridCamera = [ {
		name : "ID",
		hidden : true
	}, {
		name : "DVC_NAME",
		label : "摄像头",
	} ];
</script>