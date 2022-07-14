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
				<legend>电视墙信息</legend>
				<cui:form id="formId_xhfz" heightStyle="fill">
					<table class="table" style="width: 98%">
						<tr>
							<th>电视墙预案名称：</th>
							<td>
								<cui:input name="screenPlanName" readonly="true" value="${NAME}"></cui:input>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<cui:button onClick="saveXhfz" label="保存"></cui:button>
								<%--<cui:button onClick="resetXhfz" label="重置"></cui:button>
								<cui:button onClick="removeDpqy" label="删除"></cui:button>--%>
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
					<%--<label>窗口数量：</label>
					<cui:input id="flowNum" validType="integer" width="40" readonly="true"></cui:input>--%>
					<%--<cui:button onClick="adjustScreenWindow(1,'plus')" label="+" width="20"></cui:button>
					<cui:button onClick="adjustScreenWindow(-1,'minus')" label="-" width="20"></cui:button>
					<cui:button onClick="removeScreenWindow" label="删除"></cui:button>--%>
				</div>
				<cui:grid id="gridScreenWindow" colModel="gridScreenWindow" singleselect="true" fitStyle="fill" rowNum="999" onSelectRow="getGlCamera">
				</cui:grid>
			</fieldset>
		</div>
		<div class="div_content">
			<fieldset>
				<legend>待关联信号源</legend>
				<div style="width: 100%; text-align: left;">
					<table style="width: 98%; padding: 2px;">
						<%--<tr>
							<th>摄像头：</th>
							<td>
								<cui:input id="input_cameraName"></cui:input>
							</td>
						</tr>--%>
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
				<cui:grid id="gridScreenCamera" colModel="gridScreenCamera" rownumbers="true" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

	</div>

</div>
<script>

	$.parseDone(function() {
	    if(userLevel !="1"){
            $("#combotree_camera").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
        }else{
            $("#combotree_camera").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?deviceType=0");
        }

        var url = "${ctx}/screenPlan/getWindowByScreenPlanId?cusNumber=" + cusNumber+"&screenPlanId=${id}";
        $("#gridScreenWindow").grid("reload", url);
	});

	/**
     * 加载已经关联的摄像头
     *
     *
     * */
	function getGlCamera(e,ui) {
        console.log("ui="+ui)
       var rowData = $("#gridScreenWindow").grid("getRowData",ui.rowId);
       var windowId = rowData.windowId;
        $.ajax({
            type : "post",
            url : "${ctx}/screenPlan/getByScreenPlanWindowCameraEntity?screenPlanId=${id}&windowId="+windowId+"&cusNumber="+cusNumber,
            dataType : "json",
            success : function(data) {
                if (data.code=="200") {
                    $("#gridScreenCamera").grid("reload",data.data)
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
            }
        });
    }
	
	
	



	//重置
	/*function resetXhfz() {
		$("#formId_xhfz").form("reset");
		$("#combotree_camera").combotree("setText","");
		$("#input_cameraName").textbox("setText", "");
		$("#gridScreenCamera").grid("clearGridData");
		$("#gridCamera").grid("clearGridData");
		$("#gridScreenWindow").grid("clearGridData");
		$("#flowNum").val(0);
	}*/


	//区域控件下拉树的点击事件
	function gridCamera_xhfz(event, ui) {
	    var  selrow = $("#gridScreenWindow").grid("option","selrow");
	    if(selrow !=null){
            var rowData = $("#gridScreenWindow").grid("getRowData", selrow.toString());
            var windowId = rowData.windowId;
            var node = ui.node;
            if (node.isParent == false) {
                var url = "${ctx}/screenPlan/listAllForSxNew?cusNumber=" + cusNumber + "&areaId=" + node.id + "&screenPlanId=${id}&windowId="+windowId;
                $("#gridCamera").grid("reload", url);
            }
        }else{
            $.messageQueue({ message : "请选择一条窗口的记录", cls : "warning", iframePanel : true, type : "info" });
        }
	}
	
	function queryCamera() {
        var selrow = $("#gridScreenWindow").grid("option", "selrow");
        if(selrow !=null){
            var rowData = $("#gridScreenWindow").grid("getRowData", selrow.toString());
            var windowId = rowData.windowId;
            var areaId = $("#combotree_camera").combotree("getValue");
            //var cameraName = $("#input_cameraName").textbox("getText");
            var formData = {};
            formData['windowId']=windowId;
            formData['cusNumber'] = cusNumber;
            formData['screenPlanId'] = '${id}';
            if( areaId != ""){
                formData['areaId'] = areaId;
            }
            $("#gridCamera").grid("option", "postData", formData);
            var url = "${ctx}/screenPlan/listAllForSxNew";
            $("#gridCamera").grid("reload", url);
        }else{
            $.messageQueue({ message : "请选择一条窗口的记录", cls : "warning", iframePanel : true, type : "info" });
        }

	}
	
	function resetCamera() {
		//$("#input_cameraName").textbox("setText", "");
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
				if (screenCameraDate[j].cameraId == cameraData.ID) {
					return;
				}
			}
			var obj = new Object();
			obj.screenPlanId = "${id}";
			obj.cameraId = cameraData.ID;
			obj.cameraName = cameraData.DVC_NAME;
			obj.cameraPlatformIdnty =cameraData.DVC_PLATFORM_IDNTY;
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
			if (screenCameraData.id == "") {
				$("#gridScreenCamera").grid("delRowData", selected[i - 1]);
				var cameraData = $("#gridCamera").grid("getRowData");
				var len = cameraData.length;
				var obj = new Object();
				obj.ID = screenCameraData.cameraId;
				obj.DVC_NAME = screenCameraData.cameraName;
				obj.DVC_PLATFORM_IDNTY =screenCameraData.cameraPlatformIdnty;
				$("#gridCamera").grid("addRowData", len + 1, obj);
			}else {
				ids.push(screenCameraData.id);
			}
		}

	}


	function saveXhfz() {
        var selrow = $("#gridScreenWindow").grid("option", "selrow");
		if (selrow!=null) {
			var xhfzMap = {};
            var rowData = $("#gridScreenWindow").grid("getRowData", selrow.toString());
            var windowId = rowData.windowId;
            xhfzMap['screenPlanId'] = "${id}";
			xhfzMap['windowId'] =rowData.windowId;
			console.log("windowId="+selrow.windowId);
			var screenCamera = $("#gridScreenCamera").grid("getRowData");
			if (screenCamera != 0) {
				xhfzMap['screenCamera'] = JSON.stringify(screenCamera);
			}
		
			$.ajax({
				type : "post",
				url : "${ctx}/screenPlan/saveNew",
				data : JSON.stringify(xhfzMap),
				dataType : "json",
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					if (data.code = "200") {
						$.messageQueue({ message :"保存成功", cls : "success", iframePanel : true, type : "info" });
						//$("#combo_screenArea").combobox("reload");
						//resetXhfz();//重置
						/* $("#gridScreenWindow").grid("reload");
						$("#gridScreenCamera").grid("reload"); */

					} else {
						$.messageQueue({ message : "保存失败", cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
            $.messageQueue({ message : "请选择一条窗口的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}


	


	var gridScreenWindow = [{
		name : "id",
		key : true,
		hidden : true
	},{
		name : "windowId",
		label : "窗口号",
        align:"center",
	} ];

	var gridScreenCamera = [ {
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		name : "cameraId",
		label : "摄像头id",
		hidden : true
	},{
        name : "cameraPlatformIdnty",
        label : "摄像头所在平台索引id",
        hidden : true
    },{
		name : "cameraName",
		label : "摄像头",
        align:"center"
	} ];

	var gridCamera = [ {
		name : "ID",
		hidden : true
	}, {
		name : "DVC_NAME",
		label : "摄像头",
        align:"center"
	},{
        name : "DVC_PLATFORM_IDNTY",
        label : "摄像平台所在索引",
        hidden : true
    }];
</script>