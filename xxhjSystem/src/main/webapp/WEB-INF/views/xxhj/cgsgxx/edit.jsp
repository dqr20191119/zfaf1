<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.cgsgxx_table {
	margin: 5px 5px 20px 5px;
}

.cgsgxx_table th {
	white-space: nowrap;
	text-align:left;
	font-weight:bold;
}

span {
	white-space: nowrap;
	text-align:left;
	font-weight:bold;
}
.cgsgxx_table .form-control {
	width: 100%;
}
</style>
<div>
	<cui:form id="formId_cgsgxx_edit">
		<cui:input type='hidden' name="id" id="id" />
		<cui:input type='hidden' name="pwrLocalPoliceName" id="NameCgsgxxXcmj" />
		<cui:input type='hidden' name="pwrLeadPoliceName" id="NameCgsgxxDbld" />

		<div class="cgsgxx_table">
			<table class="table" style="width: 98%">
				<tr>
					<th>监区：</th>
					<td><cui:combobox id="comboboxId_cgsgxxJq" componentCls="form-control" name="pwrDprtmntId" required="true" onChange="initPoliceList"></cui:combobox></td>
					<th>车间名称：</th>
					<td><cui:input id="pwrFactoryName" name="pwrFactoryName" componentCls="form-control" width="150" required="true"></cui:input></td>
					<th>车间带班领导:</th>
					<td><cui:combobox id="comboboxId_cgsgxxDbld" componentCls="form-control" name="pwrLeadPoliceId" multiple="true" readonlyInput="false" enablePinyin="true" required="true" onChange="initBbrList('Dbld')"></cui:combobox></td>
					<th>带班领导联系方式:</th>
					<%--<td><cui:combobox id="comboboxId_cgsgxxXcmj" componentCls="form-control" name="pwrLocalPoliceId" multiple="true" readonlyInput="false" enablePinyin="true" required="true" onChange="initBbrList('Xcmj')"></cui:combobox></td>--%>
					<td><cui:input id="comboboxId_cgsgxxXcmj" componentCls="form-control" name="pwrLocalPoliceId" required="true" placeholder="警务通或对讲机号"></cui:input></td>
				</tr>
				</table>
				<table class="table" style="width: 98%">
				<tr>
					<th>警力：</th>
					<td><cui:input id="comboboxId_cgsgxxXcmj_count" name="pwrPoliceCount" componentCls="form-control" width="100" required="true" onChange="countPrisoner"></cui:input></td>
					<th>出工时间：</th>
					<td><cui:datepicker id="cgsgxxStartTime" componentCls="form-control" name="pwrStartTime" required="true" width="150" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
					</td>
					<td><span>危安犯：</span><cui:input id="inputId_WAF" width="80" componentCls="form-control" validType="naturalnumber" placeholder="0" name="pwrWafCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
					<span>&nbsp;&nbsp;&nbsp;刑事犯：</span><cui:input id="inputId_XSF" width="80" componentCls="form-control" validType="naturalnumber" placeholder="0" name="pwrXsfCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
					<span>（共&nbsp;&nbsp;</span><cui:input id="inputId_cgsgxxZfrs" width="80" componentCls="form-control" validType="naturalnumber" name="pwrPrisonerCount" readonly="true" onFocus="countPrisoner"></cui:input>&nbsp;<span>人，警囚比&nbsp;:</span><span id="cgsg_jqb">&nbsp;&nbsp;</span><span>）</span></td>
<%--					<th>收工时间：&nbsp;&nbsp;</th>
					<td><cui:datepicker id="cgsgxxBackTime" startDateId="cgsgxxStartTime" componentCls="form-control" name="pwrBackTime" width="150" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
					</td>--%>
				</tr>
				</table>
				<table class="table" style="width: 98%">
				<tr><td>
					<span>&nbsp;备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<cui:textarea id="inputId_cgsgxxBZ" componentCls="form-control" width="850" name="pwrRemark"></cui:textarea></td>
				</tr>
			</table>
		</div>
	</cui:form>
</div>

<script type="text/javascript">
	var comboboxData_cgsgxxBbr;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER;
	var id = '${id}';
	$.parseDone(function() {
		
		$("#comboboxId_cgsgxxJq").combobox("reload","${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber="+ thisCusNumber); //加载监狱监区
		if (id) {
			$.loading({text:"正在处理中，请稍后..."});
			loadForm("formId_cgsgxx_edit", "${ctx}/xxhj/cgsgxx/getById?id=" + id, function(data) {
				$.loading("hide");
				$("#id").textbox("setValue", id);
				initPoliceList(data);
			});
		} else {
		    if(jsConst.USER_LEVEL == 3){
                $("#comboboxId_cgsgxxJq").combobox("setValue", jsConst.DEPARTMENT_ID);
                $("#comboboxId_cgsgxxJq").combobox("option", "readonly", true);
                initPoliceList();
            }

			$("#cgsgxx_back").hide();
			$("#cgsgxxStartTime").datepicker("setDate", new Date());
			countPrisoner();
		}
	});
	
	function countPrisoner(){
		
		var waf = ($("#inputId_WAF").val() == "" ? 0 : parseInt($("#inputId_WAF").val()));
		var xsf = ($("#inputId_XSF").val() == "" ? 0 : parseInt($("#inputId_XSF").val()));
        var jl = ($("#comboboxId_cgsgxxXcmj_count").val() == "" ? 0 : parseInt($("#comboboxId_cgsgxxXcmj_count").val()));

        var prisonerCount = parseInt(waf + xsf);
        if (prisonerCount != 0 && jl != 0) {
            var jqbl = jl / prisonerCount;
            $("#cgsg_jqb").html(jqbl.toFixed(2) + "%");
        }else{
            $("#cgsg_jqb").html("&nbsp;&nbsp;");
        }
		$("#inputId_cgsgxxZfrs").textbox('setValue',prisonerCount);
		// $("#inputId_WAF").textbox('setValue',waf);
		// $("#inputId_XSF").textbox('setValue',xsf);
		
	}
	/* 加载选中部门民警 */
	var policeData = null;
	function initPoliceList(data) {
		var cgsgxx_Jq;
		
		if(id) {
			cgsgxx_Jq ='';
		} else {
			cgsgxx_Jq = $("#comboboxId_cgsgxxJq").combobox("getValue");
		}
		$.ajax({
			async : false,
			type : 'post',
			url : '${ctx}/common/authsystem/findPoliceByDeptIdForCombobox',
			data : {
				cusNumber : thisCusNumber,
				deptId : cgsgxx_Jq
			},
			dataType : 'json',
			success : function(data) {
				
				policeData = data;
				var maps = new Array();
				for (var i = 0; i < data.length+1; i++) {
					
					if(i<data.length) {
						var map = {value : data[i].value,text : data[i].text};
						maps.push(map);
					}else {
						
						var map = {value : 'QT',text : '其他'};
						maps.push(map);
					}
				}
				
				$("#comboboxId_cgsgxxDbld").combobox("loadData", maps);
				// $("#comboboxId_cgsgxxXcmj").combobox("loadData", maps);
			},
		});
		loadAllPolice();

		if (id) {
			initBbrList();
		}
	}
	
	function loadAllPolice(){

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
			},
		});
	}
	
	function initBbrList(param) {
    
		var policeValues = $("#comboboxId_cgsgxx"+param).combobox("getValues");
		
		if (policeValues.indexOf("QT") !== -1) {
			$("#comboboxId_cgsgxx"+param).combobox("loadData", policeData);
		}
		$("#NameCgsgxx"+param).textbox("setValue", $("#comboboxId_cgsgxx"+param).combobox("getText"));
	}
</script>