<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
    <cui:form id="formId_policeInOut_query">
	    <table class="table">
		   <tr> 
				<th>开始日期：</th>
				<td><cui:datepicker id="startTime"  name="startTime"  componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>~</td>
				<th>结束日期：</th>
				<td><cui:datepicker id="endTime"  name="endTime"  componentCls="form-control"   startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button label="查询"  onClick="search" componentCls="btn-primary"></cui:button></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button></td>
		  </tr>
	   </table>
    </cui:form> 
   
    <cui:grid id="gridId_policeInOut_record" rownumbers="true" fitStyle="fill" height="680" rowNum="20" pager="true">
	     <cui:gridCols>
					<cui:gridCol name="PIR_POLICE_IDNTY" width="150" align="center"> 警号 </cui:gridCol>
					<cui:gridCol name="PIR_POLICE_NAME" width="150" align="center"> 姓名 </cui:gridCol>
					<cui:gridCol name="PBD_DRPTMNT" width="150" align="center"> 部门 </cui:gridCol>
					<cui:gridCol name="PBD_PSTN_NAME" width="150" align="center"> 职务 </cui:gridCol>
					<cui:gridCol name="PIR_ENTER_TIME" width="150" align="center"> 进入时间 </cui:gridCol>
					<cui:gridCol name="PIR_LEAVE_TIME" width="150" align="center"> 离开时间 </cui:gridCol>
		 </cui:gridCols>
		 <cui:gridPager gridId="gridId_policeInOut_record" />
	</cui:grid>	
</div>	

<script type="text/javascript">

	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var cusNumber = "${cusNumber}";    //1.警员详情过来 传来2.xxhj过来 
	var policeId = "${policeId}";
	var drpmntId = "${drpmntId}";
	
	$.parseDone(function() {
		
		if(jsConst.USER_LEVEL != null && jsConst.USER_LEVEL == '1') {
			if(cusNumber) {
				$("#comboboxId_jy").combobox("setValue", cusNumber);
			}
		} else if(jsConst.CUS_NUMBER != null && jsConst.CUS_NUMBER !='' && jsConst.USER_LEVEL=='2') {//监狱登录
			
			$("#comboboxId_jy").combobox("setValue", jsConst.CUS_NUMBER);
			$("#comboboxId_jy").combobox("option", "readonly", true);
		}
		loadGrid();
 	});
	
	function loadGrid() {
		
		var data = {		
				cusNumber : cusNumber,
				policeId : policeId,
				drpmntId: drpmntId,
			};
		$("#gridId_policeInOut_record").grid("option", "postData", data);
	 	$("#gridId_policeInOut_record").grid("reload", "${ctx}/xxhj/jnmj/listPoliceInoutRecordInfo");
	}
	
	function search() {
		
		var formData = $("#formId_policeInOut_query").form("formData");		
		formData["policeId"] = policeId;
		//formData["cusNumber"] = cusNumber;
		formData["drpmntId"] = drpmntId;
		
		$("#gridId_policeInOut_record").grid("option", "postData", formData);
		$("#gridId_policeInOut_record").grid("reload");
	}
		
	function resetHandler() {
		
		$("#formId_policeInOut_query").form("clear");
	}
</script>