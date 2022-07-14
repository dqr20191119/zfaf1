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
	text-align:left;
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
	<cui:form id="formId_jndt_edit">
		<cui:input type='hidden' name="id" id="id" />
		<cui:input type='hidden' name="parPoliceName" id="parPoliceName" />
		<cui:input type='hidden' name="parStartReporterName" id="parStartReporterName" />
		<cui:input type='hidden' name="parBackReporterName" id="parBackReporterName" />

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
						<th>放风时间：</th>
						<td><cui:datepicker id="jndtStartTime" componentCls="form-control" name="parStartTime" required="true" width="150" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
						</td>
					</tr>
					<tr>
						<th>报备人：</th>
						<td><cui:combobox id="comboboxId_jndtBbr_start" componentCls="form-control" data="comboboxData_jndtBbr" name="parStartReporterId" readonlyInput="false" enablePinyin="true" required="true" onChange="initStartBbr"></cui:combobox></td>
						<th>进出事由：</th>
						<td><cui:combobox id="comboboxId_jndtJcsy" componentCls="form-control" name="parOutCategory" data="comboboxData_jcsy" required="true" onChange="onJcsyChange" value="FF"></cui:combobox></td>
						<td><cui:combobox id="comboboxId_jndtJcsyMore" componentCls="form-control" name="parOutReason" required="true" data="comboboxData_jcsy_2j" value="放风"></cui:combobox></td>
<%--						<th>路线选择：</th>
						<td><cui:combobox id="comboboxId_jndtLx" name="parLx" width="145"
										  required="false"></cui:combobox></td>--%>
						<td></td>
					</tr>
					</table>
					<table class="table" style="width: 98%">
					<tr>
					<td colspan="6">
						<span>危安犯：&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:input id="inputId_WAF" width="160" componentCls="form-control" validType="naturalnumber" placeholder="0" name="parWafCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刑事犯：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:input id="inputId_XSF" width="160" componentCls="form-control" validType="naturalnumber" placeholder="0" name="parXsfCount" onChange="countPrisoner"></cui:input><span>&nbsp;人</span>
						<span>（共&nbsp;&nbsp;</span><cui:input id="inputId_jndtZfrs" width="150" componentCls="form-control" validType="naturalnumber" name="parPrisonerCount" readonly="true" onFocus="countPrisoner"></cui:input>&nbsp;<span>人，警囚比&nbsp;:</span><span id="jqb">&nbsp;&nbsp;</span><span>）</span></td>
					</tr>
					<tr>
						<td>
						<span>&nbsp;备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<cui:textarea id="inputId_jndtBZ" componentCls="form-control" width="850" name="parRemark"></cui:textarea></td>
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
						<td colspan="3"><span>&nbsp;备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><cui:textarea name="parBackRemark" componentCls="form-control" width="850"></cui:textarea></td>
					</tr>
				</table>
			</fieldset>
		</div>
	</cui:form>
</div>

<script type="text/javascript">
	var comboboxData_jndtBbr;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER;
	var id = '${id}';
    var comboboxData_jcsy_2j = [{value:"放风",text:"放风"}];

        $.parseDone(function() {
            $("#jndt_back").hide();


            $("#comboboxId_jndtJq").combobox("reload","${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber="+ thisCusNumber); //加载监狱监区
		if (id) {
			$.loading({text:"正在处理中，请稍后..."});
			loadForm("formId_jndt_edit", "${ctx}/xxhj/jndt/getById?id=" + id, function(data) {
				$.loading("hide");
				$("#id").textbox("setValue", id);
                // $("#jndt_back").hide();
                initPoliceList(data);
/*				if(data.parBackTime == null || data.parBackReporterId == ""){
                    $("#jndtBackTime").datepicker("setDate", new Date());
                    $("#comboboxId_jndtBbr_back").combobox("setValue", jsConst.USER_ID);
				}*/
			});
		} else {
            if(jsConst.USER_LEVEL == 3){
                $("#comboboxId_jndtJq").combobox("setValue", jsConst.DEPARTMENT_ID);
                $("#comboboxId_jndtJq").combobox("option", "readonly", true);
            	initPoliceList();
            }

			// $("#jndt_back").hide();
			$("#jndtStartTime").datepicker("setDate", new Date());
			countPrisoner();
		}

        $("#comboboxId_jndtLx").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + thisCusNumber);


    });
	
	function countPrisoner(){
		
		var waf = ($("#inputId_WAF").val() == "" ? 0 : parseInt($("#inputId_WAF").val()));
		var xsf = ($("#inputId_XSF").val() == "" ? 0 : parseInt($("#inputId_XSF").val()));
		var jl = ($("#inputId_jndtJl").val() == "" ? 0 : parseInt($("#inputId_jndtJl").val()));
		var prisonerCount = parseInt(waf + xsf);
        if (prisonerCount != 0 && jl != 0) {
			var jqbl = jl / prisonerCount;
			$("#jqb").html(jqbl.toFixed(2) + "%");
        }else{
            $("#jqb").html("&nbsp;&nbsp;");
		}
		$("#inputId_jndtZfrs").textbox('setValue',prisonerCount);
		$("#inputId_WAF").textbox('setValue',waf);
		$("#inputId_XSF").textbox('setValue',xsf);
	}
	
	/* 加载选中部门民警 */
	var policeData = null;
	function initPoliceList(data) {
		var jndt_Jq;
		
		if(id) {
			jndt_Jq ='';
		} else {
			jndt_Jq = $("#comboboxId_jndtJq").combobox("getValue");
		}
		$.ajax({
			async : false,
			type : 'post',
			url : '${ctx}/common/authsystem/findPoliceByDeptIdForCombobox',
			data : {
				cusNumber : thisCusNumber,
				deptId : jndt_Jq
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
				
				$("#comboboxId_jndtFzmj").combobox("loadData", maps);
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
	
	function initBbrList() {

		var policeValues = $("#comboboxId_jndtFzmj").combobox("getValues");
        $("#comboboxId_jndtBbr_start").combobox("setValues", policeValues);
        initStartBbr();

		if (policeValues.indexOf("QT") !== -1) {
			$("#comboboxId_jndtFzmj").combobox("loadData", policeData);
		} else {
			
			$("#parPoliceName").textbox("setValue", $("#comboboxId_jndtFzmj").combobox("getText"));
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
				for(var j = 0; j < policeValues.length; j++) {

					if(policeData[i].value == policeValues[j]) {
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
	}
	
	function initStartBbr() {
		$("#parStartReporterName").textbox("setValue", $("#comboboxId_jndtBbr_start").combobox("getText"));
	}
	
	function initBackBbr() {
		$("#parBackReporterName").textbox("setValue", $("#comboboxId_jndtBbr_back").combobox("getText"));
		$("#jndtBackTime").datepicker("option","required",true);
		
	}
	
	function onJcsyChange(e, ui){
	    if ( ui.value == "JJ") {
	    	$("#comboboxId_jndtJcsyMore").combobox("reload", 
	            [{"value":"其他","text":"其他"}]);
	    	
		} else if( ui.value == "CFCG") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
	            [{"value":"其他","text":"其他"}]);
	        
	    } else if( ui.value == "LSCG") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"绿化","text":"绿化"},
		             {"value":"打扫卫生","text":"打扫卫生"},
		             {"value":"其他","text":"其他"}]);
		} else if( ui.value == "JNJY") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"监内就诊",text:"监内就诊"},
		     	     {"value":"监内住院",text:"监内住院"},
		             {"value":"其他","text":"其他"}]);
		} else if( ui.value == "FF") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"放风",text:"放风"}]);
		} else if( ui.value == "WC") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"监外就诊",text:"监外就诊"},
		     	     {"value":"监外住院",text:"监外住院"},
		     	     {"value":"离监探亲 ",text:"离监探亲 "},
		     	     {"value":"特许离监探亲",text:"特许离监探亲"},
		             {"value":"其他","text":"其他"}]);
		} else if( ui.value == "SF") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"刑满释放",text:"刑满释放"},
		     	     {"value":"中法开庭",text:"中法开庭"},
		     	     {"value":"调动",text:"调动"},
		     	     {"value":"外出就医",text:"外出就医"},
		             {"value":"其他","text":"其他"}]);
		} else if( ui.value == "SY") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload", 
		            [{"value":"调入",text:"调入"},
		             {"value":"解回",text:"解回"},
		     	     {"value":"新收押",text:"新收押"},
		             {"value":"其他","text":"其他"}]);
		} else if( ui.value == "QT") {
	        $("#comboboxId_jndtJcsyMore").combobox("reload",
		            [{"value":"量体温",text:"量体温"},
		     	     {"value":"倒垃圾",text:"倒垃圾"},
		             {"value":"打饭","text":"打饭"},
		             {"value":"其他","text":"其他"}]);
		}
	}
	
	function onBackSelect() {
		$("#comboboxId_jndtBbr_back").combobox("option","required",true);
	}
</script>