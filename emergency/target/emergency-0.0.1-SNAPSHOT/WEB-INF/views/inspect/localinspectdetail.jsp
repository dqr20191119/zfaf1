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
		<table class="table table-fixed" style="width: 90%;">
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
					<cui:radiolist  value="${inspectLocal.inlApprovalSttsIndc}" data="radiolistApprovalSttsIndc"  readonly="true"></cui:radiolist>
				</td>
				<td class="td_title"><label>审批领导：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspectLocal.checkPoliceName}"  ></cui:input>
				</td>
			</tr>    
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true"  componentCls="form-control">${inspectLocal.inlRemark}</cui:textarea>
				</td>
			</tr>
		</table>
</center>
<script>
	var radiolistApprovalSttsIndc=[{
		'value' : '0',
		'text' : '待审核'
	}, {
		'value' : '1',
		'text' : '不同意'
	}, {
		'value' : '2',
		'text' : '同意'
	}];
</script>