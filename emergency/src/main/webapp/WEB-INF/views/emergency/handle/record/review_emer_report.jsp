<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/emergency/style/report.css" rel="stylesheet" type="text/css"/>

<div id="divId_emerReport">
	<table class="table-report">
		<caption>应急处置报告</caption>
		<tr>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">报警地点</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="alarmAreaName" class="span-content"></span>
			</td>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">报警时间</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="alarmTime" class="span-content"></span>
			</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">接警人</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="receiveAlarmPolice" class="span-content"></span>
			</td>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">接警时间</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="receiveAlarmTime" class="span-content"></span>
			</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">预案名称</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="preplanName" class="span-content">${emerHandleRecord.preplanName}</span>
			</td>
			<td style="width: 100px; height: 40px;">
				<span class="span-title">启动时间</span>
			</td>
			<td style="width: 200px; height: 40px;">
				<span id="preplanStartTime" class="span-content">
					<fmt:formatDate value="${emerHandleRecord.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</span>
			</td>
		</tr>
		<c:forEach var="handleStep" items="${emerHandleRecord.handleStepList}" varStatus="status1">
			<tr>
				<td class="bgcolor-red" style="height: 40px;" colspan="4"><span id="stepName" class="span-title">${handleStep.stepName}</span></td>
			</tr>
			<c:forEach var="handleGroup" items="${handleStep.handleGroupList}" varStatus="status2">
				<tr>
					<td><span id="groupName" class="span-title">${handleGroup.groupName}</span></td>
					<td colspan="3" style="height: 40px;">
						<table class="table-report-nest">
							<c:forEach var="handleMember" items="${handleGroup.handleMemberList}" varStatus="status3">
								<tr style="height: 40px;">
									<td style="width: 200px;"><span id="memberName" class="span-content">${handleMember.memberName}</span></td>
									<td style="width: 100px;"><span id="callNo" class="span-content">${handleMember.callNo}</span></td>
									<td style="width: 200px;"><span id="callResult" class="span-content">${handleMember.callResult}</span></td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td height="100"><span class="span-title">过程记录</span></td>
				<td colspan="3"><span id="handleContent" class="span-content">${handleStep.handleContent}</span></td>
			</tr>
		</c:forEach>
		<tr>
			<td height="120">
				<span class="span-title">经验总结</span>
			</td>
			<td colspan="3">
				<cui:form id="formId_review_report" heightStyle="fill">
					<cui:textarea id="experience" name="experience" width="700" required="true">${emerHandleRecord.experience}</cui:textarea>
				</cui:form>
			</td>
		</tr>
	</table>
	<div style="height: 20px;"></div>
</div>

<!-- 隐藏域 -->
<div id="divId_hiddenAttr" style="display: none;">
	<!-- 报警记录编号 -->
	<input type="hidden" id="alarmRecordId" value="${alarmRecordId}" />
</div>

<script>
	$.parseDone(function() {
		// 初始化报警记录
		initAlarmRecord();
	});

	/**
	 * 初始化报警记录信息
	 */
	function initAlarmRecord() {
		// 报警记录编号
		var alarmRecordId = $("#divId_hiddenAttr").find("input[id='alarmRecordId']").val();

		var url = "${ctx}/alarm/findById.json";
		var params = {};
		if(alarmRecordId) {
			params["id"] = alarmRecordId;
		}

		var callBack = function(data) {
			if (data.success) {
				// 填充报警记录信息
				fillAlarmRecord(data.obj);
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, params, callBack);
	}

	/**
	 * 填充报警记录信息
	 */
	function fillAlarmRecord(alarmRecord) {
		console.log("alarmRecord = " + JSON.stringify(alarmRecord));
		if(alarmRecord) {
			$("#divId_emerReport").find("table[class='table-report']").find("span[id='alarmAreaName']").text(alarmRecord.ALARM_ADDRESS);
			$("#divId_emerReport").find("table[class='table-report']").find("span[id='alarmTime']").text(alarmRecord.ARD_ALERT_TIME);
			$("#divId_emerReport").find("table[class='table-report']").find("span[id='receiveAlarmPolice']").text(alarmRecord.ARD_RECEIVE_ALARM_POLICE);
			$("#divId_emerReport").find("table[class='table-report']").find("span[id='receiveAlarmTime']").text(alarmRecord.ARD_RECEIVE_TIME);
		}
	}

	/**
	 * 导出报告
	 */
	function exportReportDocument() {
		var selarrrow = $("#gridId_yjjl").grid("option", "selarrrow");
		window.location.href ="${ctx}/yjct/yjjl/exprotWord?recordId=" +selarrrow[0];
	}
</script>
