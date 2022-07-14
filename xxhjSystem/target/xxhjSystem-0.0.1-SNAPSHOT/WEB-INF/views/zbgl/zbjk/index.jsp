<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>


<div style="height: 100%; margin: 0px 10px;">
	<table border="0" >
		<tr>
			<td>
				<cui:form id="formId_zbjk">
					<fieldset>
						<table id="table_two" class="table" style="width:950px; height:auto">
							<tr>
								<th>值班类别：</th>
								<td><cui:combobox id="comboboxId_zbjk_categoryName" required= "true" name="categoryId" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" onChange="initDrpmntList"></cui:combobox></td>
								<th>部门名称：</th>
								<td><cui:combobox id="comboboxId_drpmntName"  required= "true" name="dmdDprtmntId"></cui:combobox></td>
								<th>值班人员：</th>
								<td><cui:combobox id="comboboxId_dutyPolice" name= "dutyPolice" ></cui:combobox></td>
							</tr>
							<tr>
								<th>值班状态：</th>
								<td><cui:combobox id="comboboxId_dutyStatus"  required= "true" name="dutyStatus" data="comboboxData_dutyStatus"></cui:combobox></td>
								<th>值班日期：</th>
								<td><cui:datepicker id ="startDate"  name ="startDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
								&nbsp;~&nbsp;<cui:datepicker id ="endDate"  name ="endDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
								</td>
								<td colspan="2"> 
									&nbsp;&nbsp;&nbsp;
					 				<cui:button label="确定"  componentCls="btn-primary" onClick="ensure()"/>				
									<cui:button label="重置"  onClick="reset"></cui:button>&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>
					</fieldset>
				</cui:form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="duty" >
					<tr>
						<td>
							<div style="width:970px; height:auto;  overflow: auto; margin: 10px 10px 10px 10px;">
								<div class="notData" align ="center" style="display: none;">暂无数据!</div>
								<div id="dutyGraph" style="height:550px; width:933px;"></div>
								<table id="dutyList" style="height:550px; width:933px;display:none;">
									<div id ="div_gridId_zbjk" style="width:970px;height: auto;display:none">
										<cui:grid id="gridId_zbjk" rownumbers="true"  height="500" width="auto" fitStyle="fill" rowNum="15">
											<cui:gridCols>
												<cui:gridCol name="DBD_STAFF_ID" align="center">警号</cui:gridCol>
												<cui:gridCol name="DBD_STAFF_NAME" align="center">姓名</cui:gridCol>
												<cui:gridCol name="DMD_DPRTMNT_ID" align="center" formatter="convertCode" revertCode="true" formatoptions="{
													'data': combobox_bm}">部门</cui:gridCol>
												<cui:gridCol name="DBD_DUTY_DATE" align="center">值班日期</cui:gridCol>
												<cui:gridCol name="DBD_DUTY_DATE" align="center">开始时间</cui:gridCol>
												<cui:gridCol name="DBD_DUTY_DATE" align="center">结束时间</cui:gridCol>
												<cui:gridCol name="DBD_DUTY_DATE" align="center">值班状态</cui:gridCol>
											</cui:gridCols>
											<cui:gridPager gridId="gridId_zbjk" />
										</cui:grid>
									</div>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var cusNumber = jsConst.CUS_NUMBER;
	
	$.parseDone(function() {
	})
	
	function ensure() {
		
		var formData = $("#formId_zbjk").form("formData");
		formData["cusNumber"] = cusNumber;
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/zbjk/searchAllData',
			data: formData,
			dataType : 'json',
			success : function(data) {
				if(data != "" && data != null) {
					
					$("#div_gridId_zbjk").hide();
					$(".notData").hide(); 
					$("#dutyGraph").show();
					parseDutyPoliceCount(data);
					
				} else {
					
				 $("#dutyGraph").hide();
				 $(".notData").show();
				}
			}
		})
	}
	
	function reset() {
		$("#formId_zbjk").form("clear");
	}
	var comboboxData_dutyStatus = [{
			value : '0',
			text : '正常值班'
		},{
			value : '1',
			text : '迟到'
			
		},{
			value : '2',
			text : '早退'
			
		},{
			value : '3',
			text : '未值班'
	}]
	
	function initDrpmntList() {
		
		var selectedCategory = $("#comboboxId_zbjk_categoryName").combobox("getValue");
		$("#comboboxId_dutyPost").combobox( "reload", "${ctx}/zbgl/gwgl/searchAllData?cdjCategoryId="+selectedCategory);  //岗位
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: {
				dcdCategoryId:selectedCategory,
				param:'1'
			},
			dataType : 'json',
			success : function(data) {
				
				for (var i = 0; i < data.length; i++) {
					 	
					 	for(var j in combobox_bm) {
					 		if(data[i].value == combobox_bm[j].value) {
					 			
					 			data[i].text = combobox_bm[j].text;
					 			break;
					 		}
					 	}
					 }
				$("#comboboxId_drpmntName").combobox("loadData",data);
			},
		});
	}

 	

 	function loadGrid(staffId) {
 		
 		$("#dutyGraph").hide();
 		$("#dutyGraph").empty();
 		$("#div_gridId_zbjk").show();
 		
 		var formData = $("#formId_zbjk").form("formData");
 		formData["dutyPolice"] = staffId;
		formData["cusNumber"] = cusNumber;
		
		$("#gridId_zbjk").grid("option", "postData", formData);
		$("#gridId_zbjk").grid("reload","${ctx}/zbgl/zbjk/queryDutyControl"); 
 	}
	
</script>