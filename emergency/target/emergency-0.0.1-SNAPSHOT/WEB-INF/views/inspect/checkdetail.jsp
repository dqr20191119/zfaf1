<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.form-control {
		width: 100%;
	}
	.td_title{
		text-align: right;
		font-weight: bold;
	}
	.td_context{
	}
</style>

<center>
	<cui:form id="formId_inspectCheck">
		<table class="table table-fixed" style="width: 90%;">
			<cui:input type="hidden" name="inoCusNumber" value="${inspect.inoCusNumber}" ></cui:input>
			<cui:input type="hidden" required="true" id="inspect_edit_inoUpdtUserId" name="inoUpdtUserId"></cui:input>
			<cui:input type="hidden" required="true" id="inspect_edit_inoUpdtUserName" name="inoUpdtUserName"></cui:input>
			<cui:input type="hidden"   name="id" value="${inspect.id }"></cui:input>
			<tr>
				<td width="15%" class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input    componentCls="form-control" value="${inspect.inoInspectName}"   readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="15%" class="td_title"><label>编校：</label></td>
				<td width="35%" class="td_context">
					<cui:input  required="true" name="inoInspectBj" value="${inspect.inoInspectBj}" componentCls="form-control"></cui:input>
				</td>
				<td width="15%" class="td_title"><label>通报期数：</label></td>
				<td width="35%" class="td_context">
					<cui:input  required="true" name="inoInspectPhase" value="${inspect.inoInspectPhase}" validType="integer" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker readonly="true" value="${inspect.inoInspectTime}" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>通报监狱：</label></td>
				<td class="td_context">
					<cui:combobox url="${ctx}/common/authsystem/findAllJyForCombobox.json" componentCls="form-control" value="${inspect.inoNoticeCusNumber}" readonly="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>制度执行：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoRuleExecute}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>现场管理：</label></td>
				<td class="td_context" height="300px" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:grid id="gridId_monitor_already" colModel="gridColModel_monitor"
							  width="auto" fitStyle="fill" pager="true">
					</cui:grid>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>凌晨检查：</label></td>
				<td class="td_context"  colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoMorningCheck}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>工作要求：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoSuggesition}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoProblem}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:input  componentCls="form-control" value="${inspect.iprPoliceNames}" readonly="true"></cui:input>
				</td>
				<td class="td_title"><label>上报人员：</label></td>
				<td class="td_context">
					<cui:input  componentCls="form-control" value="${inspect.inoCrteUserName}" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核状态：</label></td>
				<td class="td_context">
						<%-- <cui:radio  required="true" id="check_inspect_inoApprovalSttsIndc2" name="inoApprovalSttsIndc" value="2" onChange='method' checked="true"></cui:radio>同意
                        <cui:radio id="check_inspect_inoApprovalSttsIndc1" name="inoApprovalSttsIndc" value="1" onChange='method'></cui:radio>不同意 --%>
					<cui:radiolist id="inoApprovalSttsIndc" name="inoApprovalSttsIndc" required="true" repeatLayout="flow" readonly="false"
								   data="inoApprovalSttsIndc_json" onChange="" componentCls="form-control"></cui:radiolist>
				</td>
				<td class="td_title"><label>审批领导：</label></td>
				<td class="td_context">
					<cui:input type="hidden" name="checkPoliceIdnty" value="${inspect.checkPoliceIdnty}" ></cui:input>
					<cui:input componentCls="form-control" name="checkPoliceName" value="${inspect.checkPoliceName}"  readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="check_inspect_inoRemark" name="inoRemark"   componentCls="form-control" ></cui:textarea>
				</td>
			</tr>
		</table>
	</cui:form>
</center>
<div class="dialog-buttons">
	<cui:button label="审核" componentCls="btn-primary" onClick="inspect_check.submit()"></cui:button>
</div>
<cui:dialog id="monitorDetailDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>
<script type="text/javascript"
		src="${ctx}/static/module/evidence/evidence.js"></script>
<script type="text/javascript"
		src="${ctx}/static/module/evidence/messenger.js"></script>
<script>
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;//监狱号
    var userName = jsConst.REAL_NAME;//当前登录人真实姓名
    var userId = jsConst.USER_ID;//当前登录人id

    var inoApprovalSttsIndc_json = [{
        value : "3",
        text : "同意",
        checked : true
    }, {
        value : "2",
        text : "不同意"
    }];
    var gridColModel_monitor=[ {
        label : "id",
        name : "ID",
        hidden : true,
        key:true
    }, {
        label : "监督单名称",
        width : 250,
        align : "center",
        name : "MDO_MONITOR_NAME"
    }, {
        label : "创建时间",
        align : "center",
        hidden : true,
        name : "MDO_CRTE_TIME"
    },{
        label : "操作",
        align:"center",
        width : 80,
        formatter:"formatterDetail",
    } ];

    function detailedMonitor(selrow){
        if (selrow != null) {
            $("#monitorDetailDialog").dialog({
                width : 700,
                height : "auto",
                subTitle : '监督单详细',
                url : '${ctx}/monitor/edit?id='+selrow.toString(),
            });
            //messenger.js
            messenger.isDetailShow=true;
            $("#monitorDetailDialog").dialog("open");
        } else {
            $.message({
                iframePanel:true,
                message : "请先勾选需要处理记录！",
                cls : "warning"
            });
        }
    }
    function formatterDetail(cellValue,options,rowObject){
        var result="<span><a href='#' style='color: #4692f0;' onclick='detailedMonitor(\""+rowObject.ID+"\");return false;'>详细</a></span>";
        return result;
    }

    $.parseDone(function(){
        $("#gridId_monitor_already").grid("reload","${ctx }/monitor/searchMonitorByInspectId.json?inspectId=${inspect.id}");
        inspect_check = {
            submit:function(){
                if ($("#formId_inspectCheck").form("valid")) {
                    $('#inspect_edit_inoUpdtUserId').textbox('setValue',userId);
                    $('#inspect_edit_inoUpdtUserName').textbox('setValue',userName);
                    var formData = $("#formId_inspectCheck").form("formData");
                    $.ajax({
                        type : 'post',
                        url : '${ctx}/inspect/inspectUpdate.json',
                        data : formData,
                        dataType : 'json',
                        success : function(data) {
                            if (data.success) {
                                $.message({
                                    message : "审批成功",
                                    cls : "success",
                                    iframePanel:true
                                });
                                $('#gridId_inspect_notice_record').grid('reload');
                                $("#checkdetaildialog").dialog("close");
                            } else {
                                $.message({
                                    iframePanel:true,
                                    message : data.msg,
                                    type : "danger"
                                });
                            }
                        },
                        error : function(XMLHttpRequest, textStatus, errorThrown) {
                            $.alert({
                                message:textStatus,
                                title:"信息提示",
                                iframePanel:true
                            });
                        }
                    });

                } else {
                    $.alert({
                        message:"请确认输入是否正确！",
                        title:"信息提示",
                        iframePanel:true
                    });
                }
            }
        }
    });

</script>