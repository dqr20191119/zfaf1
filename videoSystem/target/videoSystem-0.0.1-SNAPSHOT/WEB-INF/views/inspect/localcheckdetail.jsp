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
			<cui:input type="hidden" name="inlCusNumber" value="${inspectLocal.inlCusNumber}" ></cui:input>
			<cui:input type="hidden" required="true" id="inspect_edit_inlUpdtUserId" name="inlUpdtUserId"></cui:input>
			<cui:input type="hidden" required="true" id="inspect_edit_inlUpdtUserName" name="inlUpdtUserName"></cui:input>
			<cui:input type="hidden"   name="id" value="${inspectLocal.id }"></cui:input>
			<tr>
				<td width="15%" class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input    componentCls="form-control" value="${inspectLocal.inlInspectName}"   readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker readonly="true" value="${inspectLocal.inlInspectTime}" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:input  componentCls="form-control" value="${inspectLocal.iprPoliceNames}" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>好的方面：</label></td>
				<td class="td_context" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:grid id="gridId_inspect_good_record" datatype="json" url="${ctx}/inspectlocal/inspectLocalRelationListPage.json?inrTypeIndc=0&inrInspectId=${inspectLocal.id }"  width="auto" fitStyle="fill" singleselect="true" pager="true">
						<cui:gridCols>
							<cui:gridCol name="inrTime" width="150">时间</cui:gridCol>
							<cui:gridCol name="inrAddr" width="150">地点</cui:gridCol>
							<cui:gridCol name="inrDprtmntName" width="150">所在单位</cui:gridCol>
							<cui:gridCol name="inrDprtmntId" hidden="true">所在单位id</cui:gridCol>
							<cui:gridCol name="inrRemark" width="150">备注</cui:gridCol>
							<cui:gridCol name="inrTypeIndc" hidden="true">类型</cui:gridCol>
						</cui:gridCols>
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>存在问题：</label></td>
				<td class="td_context" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:grid id="gridId_inspect_bad_record" datatype="json" url="${ctx}/inspectlocal/inspectLocalRelationListPage.json?inrTypeIndc=1&inrInspectId=${inspectLocal.id }"  width="auto" fitStyle="fill" singleselect="true" pager="true">
						<cui:gridCols>
							<cui:gridCol name="inrTime" width="150">时间</cui:gridCol>
							<cui:gridCol name="inrAddr" width="150">地点</cui:gridCol>
							<cui:gridCol name="inrDprtmntName" width="150">所在单位</cui:gridCol>
							<cui:gridCol name="inrDprtmntId" hidden="true">所在单位id</cui:gridCol>
							<cui:gridCol name="inrRemark" width="150">备注</cui:gridCol>
							<cui:gridCol name="inrTypeIndc" hidden="true">类型</cui:gridCol>
						</cui:gridCols>
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察记载：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true" componentCls="form-control">${inspectLocal.inlPrisonCase}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>处理意见：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true" componentCls="form-control">${inspectLocal.inlSuggesition}</cui:textarea>
				</td>
			</tr>

			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspectLocal.inlProblem}</cui:textarea>
				</td>
			</tr>

			<tr>
				<td class="td_title"><label>审核状态：</label></td>
				<td class="td_context">
					<cui:radio id="check_inspect_inlApprovalSttsIndc2" name="inlApprovalSttsIndc" value="2" onChange='method'></cui:radio>同意
					<cui:radio id="check_inspect_inlApprovalSttsIndc1" name="inlApprovalSttsIndc" value="1" onChange='method'></cui:radio>不同意
				</td>
				<td class="td_title"><label>审批领导：</label></td>
				<td class="td_context">
					<cui:input type="hidden" name="checkPoliceIdnty" value="${inspectLocal.checkPoliceIdnty}" ></cui:input>
					<cui:input componentCls="form-control" name="checkPoliceName" value="${inspectLocal.checkPoliceName}"  readonly="true"></cui:input>
				</td>
			</tr>    
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="check_inspect_inlRemark" name="inlRemark"   componentCls="form-control" ></cui:textarea>
				</td>
			</tr>
		</table>
	</cui:form>
</center>
<div class="dialog-buttons">
	<cui:button label="提交" componentCls="btn-primary" onClick="inspect_check.submit()"></cui:button>
</div>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userName = jsConst.REAL_NAME;//当前登录人真实姓名
	var userId = jsConst.USER_ID;//当前登录人id
	
	$(function(){
		inspect_check = {
			submit:function(){
				if ($("#formId_inspectCheck").form("valid")) {
					$('#inspect_edit_inlUpdtUserId').textbox('setValue',userId);
					$('#inspect_edit_inlUpdtUserName').textbox('setValue',userName);
					var formData = $("#formId_inspectCheck").form("formData");
					$.ajax({
						type : 'post',
						url : '${ctx}/inspectlocal/inspectLocalUpdate.json',
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
								$("#localcheckdetaildialog").dialog("close");
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