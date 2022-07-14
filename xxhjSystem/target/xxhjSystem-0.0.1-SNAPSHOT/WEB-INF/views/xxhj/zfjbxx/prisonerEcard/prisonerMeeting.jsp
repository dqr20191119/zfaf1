<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.prisonerMeetingInfo {
	float: left;
	width: 100%;
	margin-top: 15px;
	margin-bottom: 15px;
}

.prisonerMeetingInfo li {
	height: 50px;
	line-height: 70px;
	font-size: 15px;
	width: 250px;
	text-align: left;
	height: 50px;
	border-bottom: 1px solid #eeeeee;
} 

.prisonerMeetingInfo ul {
	float: left;
	text-align: left;
	width:25%;
}

.prisonerMeetingInfo .divFive {
	float: left;
	margin-top: 20px;
	margin-left: 20px;
}

.prisonerMeetingList {
	height:475px;
	width: auto;
	margin:30px 10px 0px 10px;
	padding:5px 5px 5px 5px;
	background-color: #fbfcfd;
	border:#B3D0F4 2px solid; 
}
</style>

<div class="prisonerMeeting" style="width:100%">
 	<div class="prisonerMeetingInfo">
		<ul class="ulOne">
			<li style="text-align: left;padding-left:20px;margin-right:5px">罪犯编号：<span id="pmePrsnrIdnty"></span></li>
			<li style="text-align: left;padding-left:20px;margin-right:5px">会见起始时间：<span id="pmeStartTime"></span></li>
		</ul>
		<ul class="ulTwo">
			<li style="text-align: left;padding-left:10px;margin-right:5px">罪犯姓名：<span id="pmePrsnrName"></span></li>
			<li style="text-align: left;padding-left:10px;margin-right:5px">会见结束时间：<span id="pmeEndTime"></span></li>
		</ul>
		<ul class="ulThree">
			<li style="text-align: left;padding-left:10px;margin-right:5px">会见类型：<span id="pmeTypeIndc"></span></li>
			<li style="text-align: left;padding-left:10px;margin-right:5px">会见评价：<span id="pmeComment"></span></li>
		</ul>
		<ul class="ulFour">
			<li style="text-align: left;padding-left:10px;margin-right:5px">会见人：<span id="pmeMeetingPeople"></span></li>
			<li style="text-align: left;padding-left:10px;margin-right:5px">复听人：<span id="pcmRepeatPeople"></span></li>
		</ul>
		
		<div class="divFive">
			<div style="float: left;margin-top: 15px;font-size: 15px;">备注：</div>
			<div>
				<cui:textarea id="pcmRemark" name ="pcmRemark" width="850" readonly="readonly"></cui:textarea>
			</div>
		</div>
	</div> 
</div>
<div class="prisonerMeetingList">
	<cui:grid id="gridId_prisonerMeetingList" colModel="gridColModel_prisonerMeetingList" fitStyle="fill" rowNum="15" pager="true" onComplete="loadDefaultData" url="${ctx}/xxhj/jryf/listPrisonerMeeting?pmeCusNumber=${pbdCusNumber}&&pmePrsnrIdnty=${pbdPrsnrIdnty}">
		<cui:gridPager gridId="gridId_prisonerMeetingList" />
	</cui:grid>
</div>

<script type="text/javascript">

	$.parseDone(function() {
		
		var prisonerId = "<%=request.getParameter("pbdPrsnrIdnty")%>";
		var cusNumber =  "<%=request.getParameter("pbdCusNumber")%>";
	});

	function loadDefaultData() {
		
		var rowData = $("#gridId_prisonerMeetingList").grid("getRowData",1);
		loadPrisonerMeetingInfo(rowData);
	}

	var gridColModel_prisonerMeetingList = [{
		
			label : "罪犯编号",
			name : "PME_PRSNR_IDNTY",
			hidden:true
		}, {
			label : "罪犯姓名",
			name : "PME_PRSNR_NAME"
		}, {
			label : "会见类型",
			name : "PME_TYPE_INDC"
		}, {
			label : "会见人",
			name : "PME_MEETING_PEOPLE"
		}, {
			label : "会见起始时间",
			name : "PME_START_TIME"
		}, {
			label : "会见结束时间",
			name : "PME_END_TIME"
		}, {
				label : "会见评价",
				name : "PME_COMMENT"
		}, {
			label : "复听人",
			name : "PCM_REPEAT_PEOPLE"
			}]

	function getRowId(e,ui) {
	
		var array=$("#gridId_PhoneList").grid("getRowData",ui.rowId);
		loadPrisonerMeetingInfo(array);
	}
	
	function loadPrisonerMeetingInfo(array) {
		
		$("#pmePrsnrIdnty").html(array.PME_PRSNR_IDNTY);
		$("#pmePrsnrName").html(array.PME_PRSNR_NAME);
		$("#pmeTypeIndc").html(array.PME_TYPE_INDC);
		$("#pmeMeetingPeople").html(array.PME_MEETING_PEOPLE);
		$("#pmeMeetingPeople").attr("title",array.PME_MEETING_PEOPLE);
		$("#pmeStartTime").html(array.PME_START_TIME);
		$("#pmeEndTime").html(array.PME_END_TIME);
		$("#pmeComment").html(array.PME_COMMENT);
		$("#pcmRepeatPeople").html(array.PCM_REPEAT_PEOPLE);
		$("#pcmRemark").val(array.PCM_REMARK);
	  
	}
</script>
