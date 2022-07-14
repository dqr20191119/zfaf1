<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.prisonerPhoneInfo {
	float: left;
	width: 100%;
	margin-top: 15px;
	margin-bottom: 15px;
}

.prisonerPhoneInfo li {
	height: 50px;
	line-height: 70px;
	font-size: 15px;
	width: 250px;
	text-align: left;
	height: 50px;
	border-bottom: 1px solid #eeeeee;
}

.prisonerPhoneInfo ul {
	float: left;
	text-align: left;
}

.prisonerPhoneInfo .divFive {
	float: left;
	margin-top: 20px;
	margin-left: 20px;
}

.prisonerPhoneList {
	height:425px;
	width: auto;
	margin:30px 10px 0px 10px;
	padding:5px 5px 5px 5px;
	background-color: #fbfcfd;
	border:#B3D0F4 2px solid; 
}
</style>

<div>
	<div id="qqdh" class="prisonerPhoneInfo">
		<ul class="ulOne">
			<li style="text-align: left;padding-left:20px">罪犯编号：<span id="pphPrsnrIdnty"></span></li>
			<li style="text-align: left;padding-left:20px">亲属姓名：<span id="pphRelativeName"></span></li>
			<li style="text-align: left;padding-left:20px">起始时间：<span id="pphStartTime"></span></li>
		</ul>
	
		<ul class="ulTwo">
			<li style="text-align: left;padding-left:20px">罪犯姓名：<span id="pphPrsnrName"></span></li>
			<li style="text-align: left;padding-left:20px">亲属关系：<span id="pphRelation"></span></li>
			<li style="text-align: left;padding-left:20px">结束时间：<span id="pphEndTime"></span></li>
		</ul>
		<ul class="ulThree">
			<li style="text-align: left;padding-left:20px">拨打电话：<span id="pphPhone"></span></li>
			<li style="text-align: left;padding-left:20px">监听人：<span id="pphListenPeople"></span></li>
			<li style="text-align: left;padding-left:20px">评价：<span id="pphComment"></span></li>
		</ul>
		<ul class="ulFour">
			<li style="text-align: left;padding-left:20px">保存类型：<span id="pphTypeIndc"></span></li>
			<li style="text-align: left;padding-left:20px">复听次数：<span id="pphListenCount"></span></li>
			<li style="text-align: left;padding-left:20px">评价人：<span id="pphCommentPeople"></span></li>
		</ul>
		<div class="divFive">
			<div style="float: left;margin-top: 15px;font-size: 15px;">备注：</div>
			<div class="textarea">
				<cui:textarea id="pphRemark" name ="pphRemark" width="850" readonly="readonly"></cui:textarea>
			</div>
		</div>
	</div>
</div>	

<div class="prisonerPhoneList">
	<cui:grid id="gridId_PhoneList" singleselect="true" colModel="gridColModel_prisonerPhoneList" fitStyle="fill" pager="true" rowNum="15" onDblClickRow="getRowId"  onComplete="loadDefaultData"
			url="${ctx}/xxhj/zfjbxx/listPrisonerPhone?pphCusNumber=${pbdCusNumber}&&pphPrsnrIdnty=${pbdPrsnrIdnty}">
		<cui:gridPager gridId="gridId_PhoneList" />
	</cui:grid>
</div>
<script type="text/javascript">
	$.parseDone(function() {
		
		var prisonerId = "<%=request.getParameter("pbdPrsnrIdnty")%>";
		var cusNumber =  "<%=request.getParameter("pbdCusNumber")%>";
		
	});

	function loadDefaultData() {
		
		var rowData = $("#gridId_PhoneList").grid("getRowData",1);
		loadPrisonerPhoneInfo(rowData);
		
	}

	var gridColModel_prisonerPhoneList = [{
			label : "罪犯编号",
			name : "PPH_PRSNR_IDNTY",
			hidden:true
		}, {
			label : "罪犯姓名",
			name : "PPH_PRSNR_NAME"
		}, {
			label : "亲属类型",
			name : "PPH_TYPE_INDC"
		}, {
			label : "亲属姓名",
			name : "PPH_RELATIVE_NAME"
		},  {
			label : "起始时间",
			name : "PPH_START_TIME"
		}, {
			label : "结束时间",
			name : "PPH_END_TIME"
		}, {
			label : "评价",
			name : "PPH_COMMENT"
		}, {
			label : "监听人",
			name : "PPH_LISTEN_PEOPLE"
		}]
 
	 function getRowId(e,ui) {
		
		var array=$("#gridId_PhoneList").grid("getRowData",ui.rowId);
		loadPrisonerPhoneInfo(array)
	}
	
	function loadPrisonerPhoneInfo(array) {	
			
			$("#pphPrsnrIdnty").text(array.PPH_PRSNR_IDNTY);
			$("#pphPrsnrName").text(array.PPH_PRSNR_NAME);
			$("#pphTypeIndc").text(array.PPH_TYPE_INDC);
			$("#pphPhone").text(array.PPH_PHONE);
			$("#pphRelativeName").text(array.PPH_RELATIVE_NAME);
			$("#pphRelation").text(array.PPH_RELATION);
			$("#pphListenPeople").text(array.PPH_LISTEN_PEOPLE);
			$("#pphListenCount").text(array.PPH_LISTEN_COUNT);
			$("#pphStartTime").text(array.PPH_START_TIME);
			$("#pphEndTime").text(array.PPH_END_TIME);
			$("#pphComment").text(array.PPH_COMMENT);
			$("#pphCommentPeople").text(array.PPH_COMMENT_PEOPLE);
			$("#pphRemark").val(array.PPH_REMARK);
		}
	 
</script>
