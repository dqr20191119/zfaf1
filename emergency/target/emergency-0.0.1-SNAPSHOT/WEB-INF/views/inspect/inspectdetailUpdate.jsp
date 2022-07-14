<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 95%;
}
.td_title{
	text-align: right;
	width: 10%;
	font-weight: bold;
}
.td_context{
	width: 40%
}
</style>

<center>
	<cui:form id="formId_inspectDetail">
		<table class="table table-fixed" style="width: 100%;">
			<cui:input type="hidden" required="true" id="inspect_detail_inoUpdtUserId" name="inoUpdtUserId"></cui:input>
			<cui:input type="hidden" required="true" id="inspect_detail_inoUpdtUserName" name="inoUpdtUserName"></cui:input>
			<cui:input type="hidden"   name="id" value="${id}"></cui:input>
			<tr>
				<td class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input  required="true" id="inspect_detail_inoInspectName" name="inoInspectName" componentCls="form-control"  ></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker required="true" id="inspect_detail_inoInspectTime" name="inoInspectTime" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>通报监狱：</label></td>
				<td class="td_context">
					<cui:combobox data="inspectDetail.inoNoticeCusNmber"  required="true" id="inspect_detail_inoNoticeCusNmber" name="inoNoticeCusNmber" componentCls="form-control"  ></cui:combobox>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>制度执行：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspect_detail_inoRuleExecute" name="inoRuleExecute"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>凌晨检查：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspect_detail_inoMorningCheck" name="inoMorningCheck"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>工作要求：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspect_detail_inoSuggesition" name="inoSuggesition"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspect_detail_inoProblem" name="inoProblem"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核领导：</label></td>
				<td class="td_context">
					<cui:combogrid required="true" id="inspect_combogrid_checkPoliceNames" componentCls="form-control"></cui:combogrid>
					<cui:input type="hidden" required="true" id="inspect_detail_checkPoliceIdntys" name="checkPoliceIdntys"></cui:input>
					<cui:input type="hidden" required="true" id="inspect_detail_checkPoliceNames" name="checkPoliceNames"></cui:input>
					<cui:input type="hidden" required="true" id="inspect_detail_checkPoliceCusNumbers" name="checkPoliceCusNumbers"></cui:input>
				</td>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:input id="inspect_detail_iprPoliceNames"  componentCls="form-control" readonly="true" ></cui:input>
				</td>
			</tr> 
		</table>
	</cui:form>
</center>
<div class="dialog-buttons"  id="updateButton">
	<cui:button label="编辑" onClick="inspectDetail.submit()"></cui:button>
</div>
<script>
	var checkpolices= [{"policeName":"张三","policeNo":"163","cusNumber":3203},{"policeName":"李四","policeNo":"002","cusNumber":3203},{"policeName":"王五","policeNo":"004","cusNumber":3203},
					   {"policeName":"赵六","policeNo":"2166","cusNumber":3203}];
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userName = jsConst.USER_NAME;//当前登录人姓名
	var userId = jsConst.USER_ID;//当前登录人姓名
	$(function(){
		$('#inspect_combogrid_checkPoliceNames').combogrid({
			//url:"${ctx}/point/getAllTypeDevice?alpCusNumber="+pointInfo.cusNumber,
               valueField:'policeNo',
               textField:'policeName',
               multiple:false,
               readonlyInput:false,
               rowNum:20,
               pager:'true',
               panelHeight:200,
               panelWidth:400,
               colNames : ["姓名","编号",''],
               data:checkpolices,
               colModel : [
                   {name:"policeName",sortable:true,width:200},
                   {name:"policeNo",sortable:true,width:200},
                   {name:"cusNumber",hidden:true}
               ],
			onChange:function () {
				var selrow = $('#inspect_combogrid_checkPoliceNames').combogrid('grid').grid("option", "selrow");
				var rowData = $('#inspect_combogrid_checkPoliceNames').combogrid('grid').grid("getRowData", selrow);
				$('#inspect_detail_checkPoliceIdntys').textbox('setValue',rowData.policeNo);
				$('#inspect_detail_checkPoliceNames').textbox('setValue',rowData.policeName);
            	$('#inspect_detail_checkPoliceCusNumbers').textbox('setValue',rowData.cusNumber);
            }
		});
		
		$.ajax({
			type : "post",
			url : "${ctx}/inspect/findById.json?id=${id}",
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$('#inspect_detail_inoInspectName').textbox('setValue',data.inoInspectName);
					$('#inspect_detail_inoNoticeCusNmber').combobox('setValue',data.inoNoticeCusNmber);
					$('#inspect_detail_inoInspectTime').datepicker('setValue',data.inoInspectTime);
					$('#inspect_detail_inoRuleExecute').val(data.inoRuleExecute);
					$('#inspect_detail_inoMorningCheck').val(data.inoMorningCheck);
					$('#inspect_detail_inoSuggesition').val(data.inoSuggesition);
					$('#inspect_detail_inoProblem').val(data.inoProblem);
					$('#inspect_combogrid_checkPoliceNames').combogrid('setValue',data.checkPoliceIdntys);
					$('#inspect_detail_iprPoliceNames').textbox('setValue',data.iprPoliceNames);
				} else {
					$.message({ message : data.message, cls : "warning" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
		
		inspectDetail={
			inoNoticeCusNmber: [
			    {"text":"第一监狱","value":"001"},
			    {"text":"第二监狱","value":"002"},
			    {"text":"第三监狱","value":"003"},
			    {"text":"第四监狱","value":"004"}
			],
			submit:function(){
				if ($("#formId_inspectCheck").form("valid")) {
					$('#inspect_detail_inoUpdtUserId').textbox('setValue',userId);
					$('#inspect_detail_inoUpdtUserName').textbox('setValue',userName);
					var formData = $("#formId_inspectDetail").form("formData");
					$.ajax({
						type : 'post',
						url : '${ctx}/inspect/inspectUpdate.json',
						data : formData,
						dataType : 'json',
						success : function(data) {
							if (data.exception == undefined) {
								$.message("修改成功");
								$('#gridId_inspect_record').grid('reload');
								$("#inspectdetaildialog").dialog("close");
							} else {
								$.message({
									message : data.exception.cause.message,
									type : "danger"
								});
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(textStatus);
						}
					}); 
		
				} else {
					$.alert("请确认输入是否正确！");
				}
			}
		}
	});
	
</script>