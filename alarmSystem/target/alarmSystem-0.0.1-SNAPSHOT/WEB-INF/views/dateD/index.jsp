<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<cui:tabs id="tabId" heightStyle="fill">
	<ul>
		<li>
			<a href="#fragment_1">关联报警器数据批量操作</a>
		</li>
		<li>
			<a href="#fragment_2">预案批量操作</a>
		</li>
	</ul>
	<div id="fragment_1">
		<cui:form id="bjq_form">
			<table class="table">
				<tr>
					<th>监狱：</th>
					<td>
						<cui:combobox name="cusNumber" data="syjyData" onSelect="jyComSele"></cui:combobox>
					</td>
					<th>类型：</th>
					<td>
						<cui:combobox name="dvcType" data="dvcTypeData"></cui:combobox>
					</td>
					<th>地点：</th>
					<td>
						<cui:combotree name="areaId" id="comboId_area" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true">
						</cui:combotree>
					</td>
					<td>
						<cui:button label="确定" onClick="loadBjq"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
	</div>

	<div id="fragment_2" style="text-align: center; height: 100%; width: 100%">
		<cui:form id="ya_form" heightStyle="fill">
			<table class="table" style="width: 98%">
				<tr>
					<th>区域：</th>
					<td>
						<cui:combotree componentCls="form-control" name="areaId" id="comboId_area_1" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="onAreaTreeClick_data">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="400px">
						<div style="text-align: center; height: 100%; width: 100%">
							<cui:grid  id="gridId_alertor" fitStyle="fill" multiselect="true" rowNum="999" pager="true" colModel="gridId_alertor_colModelDate">
								<cui:gridPager gridId="gridId_alertor" />
							</cui:grid>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="200px">
						<div style="text-align: center; height: 100%; width: 100%">
							<cui:grid id="gridId_screenPlan" fitStyle="fill" multiselect="true" colModel="gridId_screenPlan_colModelDate">
								<cui:gridPager gridId="gridId_screenPlan" />
							</cui:grid>
						</div>
					</td>
				</tr>
				<tr>
					<th>门禁：</th>
					<td>
						<cui:combotree name="mjIds" componentCls="form-control" traversal="true" id="comboId_area_mj" multiple="true" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<th>摄像机：</th>
					<td>

						<cui:combotree name="sxjIds" componentCls="form-control" traversal="true" id="comboId_area_sxj" multiple="true" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<th>对讲：</th>
					<td>

						<cui:combotree name="djIds" componentCls="form-control" traversal="true" id="comboId_area_dj" multiple="true" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<cui:button label="确定" onClick="loadYa" width="150"></cui:button>
						<cui:button label="重置" onClick="resetYa" width="150"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
	</div>
</cui:tabs>


<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	//报警器类型
	var dvcTypeData =
<%=CodeFacade.loadCode4ComboJson("4.20.27", 3, "0")%>
	;

	var syjyData =
<%=AuthSystemFacade.getAllJyJsonInfo()%>
	; //所有监狱信息json  

	//厂家
	var dpya_changjiaData =
<%=CodeFacade.loadCode2Json("4.20.54")%>
	;
	// 状态
	var dpya_sttsData =
<%=CodeFacade.loadCode2Json("4.20.55")%>
	;
	$.parseDone(function() {
		var url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber="
				+ cusNumber + "&deviceType=0";
		$("#comboId_area").combotree("tree").tree("reload", url);
		$("#comboId_area_1").combotree("tree").tree("reload", url);

		url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber="
				+ cusNumber + "&deviceType=4"
		$("#comboId_area_mj").combotree("tree").tree("reload", url);
		url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber="
				+ cusNumber + "&deviceType=1"
		$("#comboId_area_sxj").combotree("tree").tree("reload", url);
		url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber="
				+ cusNumber + "&deviceType=2"
		$("#comboId_area_dj").combotree("tree").tree("reload", url);
	});

	function jyComSele(event, ui) {
		//alert(ui.item.value);
	}

	/* 区域树下拉点击事件 */
	function onAreaTreeClick_data(event, ui) {
		var areaId = ui.node.id;
		var url = "${ctx}/alertor/listAll.json?abdCusNumber=" + cusNumber
				+ "&abdAreaId=" + areaId + "&P_orders=abd_crte_time,desc";
		$("#gridId_alertor").grid("reload", url);

		url = "${ctx}/screenPlan/listAll.json?splCusNumber=" + cusNumber
				+ "&P_orders=spl_crte_time,desc";
		$("#gridId_screenPlan").grid("reload", url);
	}

	function loadYa() {
		var formData = $("#ya_form").form("formData");
		formData['cusNumber'] = cusNumber;
		var selected = $("#gridId_alertor").grid("option", "selarrrow");
		if (selected.length == 0) {
			$.messageQueue({
				message : "报警器未勾选",
				cls : "warning",
				iframePanel : true,
				type : "info"
			});
			return;
		}
		formData['bjqIds'] = JSON.stringify(selected);//报警器ids

		var selected_dp = $("#gridId_screenPlan").grid("option", "selarrrow");
		formData['dpIds'] = JSON.stringify(selected_dp);//大屏ids
		/* debugger; */
		$.ajax({
			type : 'post',
			url : '${ctx}/alarm/date/loadPlanData.json',
			data : formData,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.messageQueue({
						message : data.msg,
						cls : "success",
						iframePanel : true,
						type : "info"
					});
				} else {
					$.messageQueue({
						message : data.msg,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({
					message : textStatus,
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
		});
	}
	//重置按钮事件
	var resetYa = function() {
		$("#ya_form").form("reset");
	}

	function loadBjq() {
		var formData = $("#bjq_form").form("formData");
		$.ajax({
			type : 'post',
			url : '${ctx}/alarm/date/loadAlertorData.json',
			data : formData,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.messageQueue({
						message : data.msg,
						cls : "success",
						iframePanel : true,
						type : "info"
					});
				} else {
					$.messageQueue({
						message : data.msg,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({
					message : textStatus,
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
		});
	}

	var gridId_alertor_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "ABD_IDNTY",
		label : "编号"

	}, {
		name : "ABD_NAME",
		label : "名称",
		width : 250
	}, {
		name : "ABD_AREA",
		label : "所属区域"
	}, {
		name : "ABD_TYPE_INDC",
		label : "类型",
		width : 120,
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : dvcTypeData
		}
	} ];

	var gridId_screenPlan_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "SPL_PLAN_INDC",
		label : "编号"
	}, {
		name : "SPL_PLAN_NAME",
		label : "名称"
	}, {
		name : "SPL_STATUS_INDC",
		label : "状态",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : dpya_sttsData
		}
	}, {
		name : "SPL_MANUFACTURERS_ID",
		label : "厂家",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : dpya_changjiaData
		}
	} ];
</script>