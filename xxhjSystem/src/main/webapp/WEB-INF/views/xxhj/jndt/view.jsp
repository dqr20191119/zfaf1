<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.jndt_table {
	margin: 5px 5px 20px 5px;
}

.jndt_table th {
	white-space: nowrap;
	font-weight:bold;
}

span {
	white-space: nowrap;
	text-align:left;
	font-weight:bold;
}
.jndt_table .form-control {
	width: 100%;
}
</style>
<div>
	<cui:form id="formId_jndt_view">
		<div class="jndt_table">
			<fieldset>
				<legend>登记 </legend>
								<table class="table" style="width: 98%">
					<tr>
						<th>监区：</th>
						<td><cui:combobox id="comboboxId_jndtJq" componentCls="form-control" name="parDprtmntId" required="true" onChange="initPoliceList"></cui:combobox></td>
						<th>负责民警：</th>
						<td><cui:combobox id="comboboxId_jndtFzmj" componentCls="form-control" name="parPoliceId" multiple="true" readonlyInput="false" enablePinyin="true" required="true" onChange="initBbrList"></cui:combobox></td>
						<td><cui:input id="inputId_jndtJl" name="parPoliceCount" componentCls="form-control" placeholder="请输入警力" width="150" validType = "naturalnumber" required="true"></cui:input><span>&nbsp;人</span></td>
						<th>报备时间：</th>
						<td><cui:datepicker id="jndtStartTime" componentCls="form-control" name="parStartTime" required="true" width="150" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
						</td>
					</tr>
					<tr>
						<th>报备人：</th>
						<td><cui:combobox id="comboboxId_jndtBbr_start" componentCls="form-control" data="comboboxData_jndtBbr" name="parStartReporterId" readonlyInput="false" enablePinyin="true" required="true" onChange="initStartBbr"></cui:combobox></td>
						<th>进出事由：</th>
						<td><cui:combobox id="comboboxId_jndtJcsy" componentCls="form-control" name="parOutCategory" data="comboboxData_jcsy" required="true" onChange="onJcsyChange"></cui:combobox></td>
						<td><cui:combobox id="comboboxId_jndtJcsyMore" componentCls="form-control" name="parOutReason" required="true"></cui:combobox></td>
						<td></td>
					</tr>
					</table>
					<table class="table" style="width: 98%">
					<tr>
					<td colspan="6">
						<span>危安犯：&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:input id="inputId_WAF" width="160" componentCls="form-control" validType="naturalnumber"  name="parWafCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刑事犯：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:input id="inputId_XSF" width="160" componentCls="form-control" validType="naturalnumber"  name="parXsfCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
						<span>（共&nbsp;&nbsp;</span><cui:input id="inputId_jndtZfrs" width="150" componentCls="form-control" validType="naturalnumber" name="parPrisonerCount" readonly="true"></cui:input>&nbsp;<span>人）</span></td>
					</tr>
					<tr>
						<td>
						<span>&nbsp;备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<cui:textarea id="inputId_jndtBZ" componentCls="form-control" width="900" name="parRemark"></cui:textarea></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<div id="jndt_back" class="jndt_table">
			<fieldset>
				<legend>确认结束 </legend>
				<table class='table' style="width: 98%">
					<tr>
						<td><span>报备时间：</span><cui:datepicker id="jndtBackTime" componentCls="form-control" name="parBackTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="200px" showOn="button" onChange="onBackSelect()"></cui:datepicker>
						</td>
						<td></td>
						<td><span>报备人：</span><cui:combobox id="comboboxId_jndtBbr_back" componentCls="form-control" data="comboboxData_jndtBbr" name="parBackReporterId"  width="200"  readonlyInput="false"  enablePinyin="true"  onChange="initBackBbr"></cui:combobox></td>
					</tr>
					<tr>
						<td colspan="3"><span>&nbsp;备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:textarea name="parBackRemark" componentCls="form-control" width="900"></cui:textarea></td>
					</tr>
				</table>
			</fieldset>
		</div>
	</cui:form>
	<div>
	<fieldset>
		<legend> 历史记录 </legend>
		<cui:grid id="gridId_jndt_queryH" rownumbers="true" width="auto" height="200" fitStyle="fill" onDblClickRow="getRowId"
		url="${ctx}/xxhj/jndt/searchHistoryData?relationId=${id}" rowNum="5">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="parDprtmntId" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': combobox_bm}">监区</cui:gridCol>
			<cui:gridCol name="parOutCategory" align="center" formatter="formatOutReason">进出事由</cui:gridCol>
			<cui:gridCol name="parPoliceCount" align="center">警力</cui:gridCol>
			<cui:gridCol name="parPrisonerCount" align="center">罪犯人数</cui:gridCol>
			<cui:gridCol name="parStartReporterName" align="center">报备人</cui:gridCol>
			<cui:gridCol name="parUpdtTime" align="center">更新时间</cui:gridCol>
			<cui:gridCol name="parUpdtUserName" align="center">更新人</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jndt_queryH" />
	</cui:grid>
	</fieldset>
	</div>
</div>

<script type="text/javascript">
	var comboboxData_jndtBbr;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER;

	$.parseDone(function() {
		var id = '${id}';

		$("#comboboxId_jndtJq").combobox("reload","${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber="
						+ thisCusNumber); //加载监狱监区
		loadForm("formId_jndt_view", "${ctx}/xxhj/jndt/getById?id=" + id,
				function(data) {
					$("#formId_jndt_view").form("setReadOnly",true);

					initPoliceList(id, data);
					if(data.parLx!=null && ''!=data.parLx){
                        $.ajax({
                            type: "post",
                            url: "${ctx}/prisonPath/listAllForPrisonPathCamera?pcrPathId=" + data.parLx,
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                info = data.data;
                                var deviceIds = [];
                                for (var i = 0; i < info.length; i++) {
                                    deviceIds.push(info[i].PCR_CAMERA_ID);
                                }
                                if (deviceIds.length > 0) {
                                    videoClient.playVideoHandle({
                                        'subType': 2,
                                        'layout': deviceIds.length,
                                        'data': deviceIds,
                                        'callback': function (data) {
                                        }
                                    });
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                $.messageQueue({message: textStatus, cls: "warning", iframePanel: true, type: "info"});
                            }
                        });
					}

				});
			

	});

	/* 加载选中部门民警 */
	var policeData = null;
	function initPoliceList(id, data) {
		var jndt_Jq = $("#comboboxId_jndtJq").combobox("getValue");
		$.ajax({
			async : false,
			type : 'post',
			url : '${ctx}/common/authsystem/findPoliceByDeptIdForCombobox',
			data : {
				cusNumber : thisCusNumber,
				deptId : ''
			},
			dataType : 'json',
			success : function(data) {
				policeData = data;
				$("#comboboxId_jndtFzmj").combobox("loadData", data);
			},
		});

		if (id) {
			initBbrList();
		}
	}

	function initBbrList() {

		var policeValues = $("#comboboxId_jndtFzmj").combobox("getValues");
		$("#parPoliceName").textbox("setValue",
		$("#comboboxId_jndtFzmj").combobox("getText"));
		var policeText = $("#comboboxId_jndtFzmj").combobox("getText").split(",");

		var maps = new Array();
		for (var i = 0; i < policeValues.length; i++) {
			var map = {
				value : policeValues[i],
				text : policeText[i]
			}
			maps.push(map);
		}
		for (var i = 0; i < policeData.length; i++) {
			for(var j = 0; j < policeValues.length; j++){
				if(policeData[i].value == policeValues[j].value) {
					break;
				} else {
					if(j == policeValues.length-1){
						var map = {
								value : policeData[i].value,
								text : policeData[i].text
							}
							maps.push(map);
					}
				}
			}
			
		}
		$("#comboboxId_jndtBbr_start").combobox("reload", maps);
		$("#comboboxId_jndtBbr_back").combobox("reload", maps);
	}
	
	function initStartBbr() {
		$("#parStartReporterName").textbox("setValue",
				$("#comboboxId_jndtBbr_start").combobox("getText"));
	}
	
	function initBackBbr() {
		$("#parBackReporterName").textbox("setValue",
				$("#comboboxId_jndtBbr_back").combobox("getText"));
	}
	
	 function getRowId(e, ui) {
			
		var rowData = $("#gridId_jndt_queryH").grid("getRowData", ui.rowId);
		loadForm("formId_jndt_view", "${ctx}/xxhj/jndt/getHistoryById?id=" + rowData.id,
				function(data) {
					$("#formId_jndt_view").form("setReadOnly",true);
				});
	}
	 
	 function formatOutReason(cellvalue, options, rowObject) {
			
	    	var outCategory = convertColVal(comboboxData_jcsy, cellvalue);
	    	var outReason = rowObject.parOutReason;
			var value = outCategory+"-"+outReason;
			return value;
		}
</script>