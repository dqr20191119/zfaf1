<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style>
#zbbpPoliceList span{
	display:inline-block;
	border:2px solid #B3D0F4;
	line-height:25px;
	width:100px;
	white-space:pre-wrap;
	text-align: left;
    padding-left: 10px;
}
</style>
<div>	
	<div style="margin: 5px 5px 5px 5px;">
		<table border="0" >
			<tr>
				<td>
					<cui:form id="formId_zbbp_edit">
						<cui:input type='hidden' name="id" id="id" />
						<cui:input type='hidden' name="dmdCusNumber" />
						<cui:input type='hidden' name="dmdStatus" />
						<cui:input type='hidden' name="param"  id="param" />                <!-- 参数 --> 
						<cui:input type='hidden' name="jobCount"  id="jobCount" />          <!-- 岗位数量 -->
						<cui:input type='hidden' name="dateDiff"  id="dateDiff" />          <!-- 与当前日期相差天数，用来判断是否可修改 -->
						<cui:input type='hidden' name="initFormData"  id="initFormData" />
						 <fieldset> 
							<table class="table" style="width:1120px; height:auto">
								<tr>
									<th>值班类别：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_CategoryName" name= "cdmCategoryId"  required="true" onChange="initDrpmntList"></cui:combobox></td>
									<th>部门名称：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_DrpmntName"  name= "dmdDprtmntId" required="true" onSelect="initModeList"></cui:combobox>
									</td>
									<th>模板名称：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_ModeName" name= "dmdModeId" required="true" onSelect="onSelectedMode"></cui:combobox>
									</td>
								</tr>
								<tr>
									<th>生效日期：</th>
									<td><cui:datepicker id="dmdStartDate"  name="dmdStartDate" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
									</td>
					         		<th>截止日期：</th>
									<td><cui:datepicker id="dmdEndDate"  name="dmdEndDate"  startDateId="dmdStartDate" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
									</td>
									<%-- <th>数据来源：</th>
									<td><cui:combobox id="dataSource"  name="dataSource" data="com_dataSource"></cui:combobox>
									</td> --%>
								</tr>
								<tr>
								<%-- <th class = "cloneType">复制方式：</th>
									<td class = "cloneType" ><cui:combobox id="cloneType"  name="cloneType" data= "com_cloneType"></cui:combobox>
									<cui:button label="复制" id="cloneBtn" componentCls="btn-primary" onClick="cloneData"/>
									</td> --%>
									<td colspan="2"> 
										&nbsp;
						 				<cui:button label="确定" id="ensure" componentCls="btn-primary" onClick="ensure('2')"/>	
						 				<%-- <cui:button label="自动排班" id="autoDuty" componentCls="btn-primary" onClick="autoDuty"/>	 --%>		
										<cui:button label="重置" id="reset" onClick="reset"></cui:button>
									</td>
								</tr>
								<tr id="tips_tr" style="display:none;">
									<td colspan="6" style="text-align: center;"><span style="color: red;">
									------------------------------ 修改的时候，以上内容不允许修改 -----------------------------------
									</span> </td>
								</tr>
							</table>
						</fieldset>
					</cui:form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<div id="policeTable" style="width: 180px; height: 400px; display: block;">
								 <fieldset> 
								 	<div padding="5px">
									 	<cui:combobox id="combobox_drpmntId" width="140" data="combobox_bm" enablePinyin="true" onChange="onSelect" ></cui:combobox>
										<div id="checkBoxButton" style="padding-top:5px;">
											<%--
											<span style="padding-right:5px;"><cui:button onClick="chkListAll" label="全选" componentCls="btn-primary"></cui:button></span>
											<span style="padding-right:5px;"><cui:button onClick="chkListClear" label="清空" componentCls="btn-primary"></cui:button></span>
											<span><cui:button onClick="chkListEnsure" label="添加" componentCls="btn-primary"></cui:button></span> 
											--%>	
										</div>
									</div>	
									<div id="policeList" style ="width: 150px;height:400px;overflow :auto;">
										<table id="zbbpPoliceList" align="center" style="margin:5px; border-collapse: separate; border-spacing: 8px;" ondrop="drop(event)" ondragover="allowDrop(event)" onClick="chkListEnsure(this)" >
										</table>
									</div>	
								 </fieldset>
					             </div>
							</td>
							<td id="dutyTable" style="vertical-align: top; padding-left: 20px;">
								<div id="dutyList" style="position:absolute; width:980px; height:500px; overflow: auto;margin:5px 5px 5px 5px">
									<table border="1"  id="dutyform" style="table-layout:fixed;width:800px;">
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
	
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	var id = '${id}';                      //模板部门表的主键
	var dmdDprtmntId = '${dmdDprtmntId}';  
	var categoryId = '${categoryId}';
	var dmdModeId = '${dmdModeId}'    //模板的主键
	var dmdStartDate = '${dmdStartDate}';
	var dmdEndDate = '${dmdEndDate}';
	var flag = false; 
	var flags = true;          /* 监区的USER_LEVEL是3,权限被控制 */
	
	$.parseDone(function() {
		
		if(USER_LEVEL != null && USER_LEVEL == '3') {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId="+drpmntId+"&param=2");
			$("#comboxId_zbbp_edit_DrpmntName").combobox("option","readonly",true);
		} else {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1");
		}
		
		if(id) {
			//修改
			$("#ensure").hide();
			$("#reset").hide();
			$(".cloneType").hide();
			$("#id").textbox("setValue",id);
			$("#formId_zbbp_edit").form("setReadOnly",true);
			$("#dmdStartDate").datepicker("setDate",new Date(Date.parse(dmdStartDate)));
			$("#dmdEndDate").datepicker("setDate",new Date(Date.parse(dmdEndDate)));
			$("#comboxId_zbbp_edit_CategoryName").combobox("setValue",categoryId);
			initDrpmntList();
			$("#tips_tr").show();
			 
		} else {
			//新增
			$("#dmdStartDate").datepicker("option","minDate", new Date());  
			//initCategory(drpmntId);
			$("#policeTable").hide();
			$("#dutyTable").hide();
			$("#tips_tr").hide();
		}
	});
	
	var com_cloneType = [
		{
			value : "0",
			text : "无",
			"selected" : true
		},{
			value : "1",
			text : "周期复制"
		}]
	             	             	
	var com_dataSource = [
		{
    		value : "0",
      		text : "手动编排",
      		"selected" : true
        },{
			value : "1",
			text : "文件导入"
        }]
	
	function initCategory(drpmntId){
		
		var id = {};
		id.dcdDprtmntId = drpmntId;
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: id,
			dataType : 'json',
			success : function(data) {
				
				if(data.length == 0) {     //登陆者部门无归属类别
					flags = false;
				} else {
					$("#comboxId_zbbp_edit_CategoryName").combobox("setValue",data[0].DCD_CATEGORY_ID);
				}
				initDrpmntList();
			},
		});
	}
	
	var selectedCategory = null;
	function initDrpmntList() {
		selectedCategory = $("#comboxId_zbbp_edit_CategoryName").combobox("getValue");
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: {
				dcdCategoryId:selectedCategory,
				param:'1'
			},
			dataType : 'json',
			success : function(data) {
				
				for (var i=0; i<data.length;i++) {
					 	
					 	for(var j in combobox_bm) {
					 		
					 		if(data[i].value == combobox_bm[j].value) {
					 			data[i].text=combobox_bm[j].text;
					 			break;
					 		}
					 	}
					 }
				
				if(dmdDprtmntId) {
					$("#comboxId_zbbp_edit_DrpmntName").combobox("loadData",combobox_bm);
					$('#comboxId_zbbp_edit_DrpmntName').combobox("setValue",dmdDprtmntId); 
				} else {
					$("#comboxId_zbbp_edit_DrpmntName").combobox("loadData",data);
					if(flags) {
						$('#comboxId_zbbp_edit_DrpmntName').combobox("setValue",drpmntId);
						flags = false;
					} 
				}
				initModeList();
			},
		});
	}
	
	var selectedDrpmntId = null;
	function initModeList() {
		
		selectedDrpmntId = $('#comboxId_zbbp_edit_DrpmntName').combobox("getValue");
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/searchAllData',
			data: {
				cdmCategoryId : selectedCategory,
			},
			dataType : 'json',
			success : function(data) {
				
				var modeData = [];
				for (var i=0; i<data.length;i++) {
					
					var list = {};
					list.value = data[i].id;
					list.text = data[i].cdmModeName;
					modeData[i] = list;
			 	}
				$("#comboxId_zbbp_edit_ModeName").combobox("loadData",modeData);
				
				if(id) {
					$('#comboxId_zbbp_edit_ModeName').combobox("setValue",dmdModeId); 
					ensure("3");
				}
			}				
		});
	}
	
	//判断自定义选择处是否修改
	function saveDutyData() {
		
		var flag = false;
		var nFormData = $("#formId_zbbp_edit").form("formData");
	
		if(nFormData.cdmCategoryId == initFormData[0] && nFormData.dmdDprtmntId == initFormData[1] && nFormData.dmdModeId == initFormData[2] && nFormData.dmdStartDate == initFormData[3] && nFormData.dmdEndDate == initFormData[4]) {
			flag = true;
		}
		return flag;
	}
	
	var initFormData;
	function ensure(param) {
		
		var validFlag  = $("#formId_zbbp_edit").form("valid");
		if(!validFlag) {
			return;
		}
		if (flag) {
			$.confirm("注意！点击后模板信息将会重置！", function(r) {
				if(r) {
					
					$("#zbbpPoliceList").empty();
					$("#policeTable").show();
					$("#dutyTable").show();

					initModeData(param);
					initPoliceList();
					
					initFormData =[];
					var formData = $("#formId_zbbp_edit").form("formData");        //判断form中填写信息是否与编排人员一致
					initFormData.push(formData.cdmCategoryId);
					initFormData.push(formData.dmdDprtmntId);
					initFormData.push(formData.dmdModeId);
					initFormData.push(formData.dmdStartDate);
					initFormData.push(formData.dmdEndDate);
				}
			})
		} else {
			
			$("#policeTable").show();
			$("#dutyTable").show();

			initModeData(param);
			initPoliceList();
			
			initFormData =[];
			var formData = $("#formId_zbbp_edit").form("formData");        //判断form中填写信息是否与编排人员一致
			initFormData.push(formData.cdmCategoryId);
			initFormData.push(formData.dmdDprtmntId);
			initFormData.push(formData.dmdModeId);
			initFormData.push(formData.dmdStartDate);
			initFormData.push(formData.dmdEndDate);
			console.log(initFormData);
		}
		flag = true;
	}
	
	function reset() {
		
		if( USER_LEVEL =='3' && USER_LEVEL != null) {
			$("#comboxId_zbbp_edit_CategoryName").combobox("clear");
			$("#comboxId_zbbp_edit_ModeName").combobox("clear");
			$("#dmdStartDate").datepicker("setValue","");
			$("#dmdEndDate").datepicker("setValue","");
			$("#dataSource").combobox("clear");
			$("#cloneType").textbox("setValue","");
		} else {
			$("#formId_zbbp_edit").form("reset");
		}
	}
	
	/* 查询模板数据 */
	var modeData = null;
	function initModeData(param) {
	
		$("#dutyform").empty();
		var selectedModeId = $("#comboxId_zbbp_edit_ModeName").combobox("getValue");   //模板的主键
			
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/getById',
			data: {
				mojCusNumber : cusNumber,
				modelId : selectedModeId,
				id : id,              //模板部门表主键
				mojStartDate : dmdStartDate,
				mojEndDate : dmdEndDate,
				param : param,
			},
			dataType : 'json',
			success : function(data) {
				
				createDutyForm(data,param);
				modeData = data;
			}
		});
	}
	
	/* 解析表格数据 */
	var order = null;
	var orderCount = null;
	var SDate = null;
	var EDate = null;
	var dateDiff = null;

	function createDutyForm(data,param) {
		
		$("#param").textbox("setValue",param);       //后台判断参数 
		
		SDate = $("#dmdStartDate").datepicker("getValue");
		EDate = $("#dmdEndDate").datepicker("getValue");
		order = (data.cdmOrderData ).split(";");
		orderCount = order.length; 
		dateDiff = getDateDiff(SDate, EDate);
		$("#dateDiff").textbox("setValue",dateDiff+1);
		
		/*根据起始日期判断是否能够修改  */
		var today = new Date();
		var todayDiff = parseInt(getDateDiff(SDate, formatterDate(today,"ymd")));
		var todayCount = parseInt(formatterDate(today,"ymd").replace(/\-/g, ""));
		var sDateCount = parseInt(SDate.replace(/\-/g, ""));
		var flag = false;
		if (todayCount > sDateCount) {
			flag = true;
		}
	    var title = "<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 班次</td><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:230px; height:80px;word-break: keep-all;white-space:nowrap;'>岗位</td>"

	    for(var i = 0; i< dateDiff + 1; i++) { 
	    	
	    	var date = new Date(Date.parse(SDate)); 
	    	var tDate = addDate("d",date,i);
	    	var aDate = formatterDate(tDate,"ymd")
			var week = getWeek(tDate);
	    	title = title+ "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >第" +(i+1)+ "天<br><span style='font-weight: bold;'>" +aDate+ "<br>" +week+ "</span></td>";
	    }
	    title = title+ "</tr>";
	    $("#dutyform").append(title); 
	    
	    var jobCount =  0;
	    if(param == '2') {    //新增编排
	    	
	   		 for(var i = 0;i < order.length; i++) {
	    	
	    		var orderID = (order[i].split("-")[0]).split("_")[0];
		    	var orderName = (order[i].split("-")[0]).split("_")[1];
		    	var orderSTime = (order[i].split("-")[0]).split("_")[2];
		    	var orderETime = (order[i].split("-")[0]).split("_")[3];
		    	var job = (order[i].split("-")[1]).split(",");
		    	jobCount = parseInt(jobCount + job.length);      //岗位数统计	
		    	
		    	for(var j = 0;j < job.length; j++) {
		    		
		    		var jobID = (job[j]).split("_")[0];  //模板详情表中的主键
		    		var jobName = (job[j]).split("_")[1];
		    		var item = "<tr>";
		    		
					if(j == 0) {
						item = item +"<td rowspan='"+job.length+"' id= '"+orderID+"' align='center' style='padding:10px 20px 15px 20px; width:auto; height:auto; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;'><span style='font-size:13px;font-weight: bold;'>" +orderName+ "</span><br>(" +orderSTime+ "~" +orderETime+ ")</td>";
					} 
					item = item + "<td align='center' class='jobTd' id= '" +jobID+ "' style='font-size:15px;padding:10px 20px 15px 20px; width:80px; height:80px; font-weight: bold; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;'>"+jobName+"</td>";

					var tdPeople = "";
					for(var k = 0; k < dateDiff + 1; k++) {
						tdPeople = tdPeople + "<td id='p"+i+"_"+j+"o"+k+"_"+k+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+""+j+""+k+"_"+k+"' class='dutyTd'></ul></td>"	

					}
					item = item + tdPeople;
					item = item + "</tr>";
					$("#dutyform").append(item);
				}
	    	}
    	} else if(param == '3') { //修改编排
    		
    		for(var i = 0; i< order.length; i++) {
    			
    	    	var orderID = (order[i].split("*")[0]).split("&")[0];
    	    	var orderName = (order[i].split("*")[0]).split("&")[1];
    	    	var orderSTime = (order[i].split("*")[0]).split("&")[2];
    	    	var orderETime = (order[i].split("*")[0]).split("&")[3];
    	    	var jobList = (order[i].split("*")[1]).split("~");
    	    	jobCount = parseInt(jobCount+jobList.length);
    	    	
    	    	for(var j = 0; j< jobList.length; j++) {
    	    	
    	    		var dutyId = (jobList[j].split("%")[0]).split("&")[0];    //DBD_DUTY_MODE_ORDER_JOB_ID
    	    		var jobName = (jobList[j].split("%")[0]).split("&")[1];
    	    		var dutyPoliceData = (jobList[j].split("%")[1]).split("@");
    	    		
    	    		var item = "<tr>";
    				if(j == 0) {
    					item = item +"<td rowspan='"+jobList.length+"' id= '"+orderID+"' align='center' style='padding:10px 20px 15px 20px; width:auto; height:auto; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;'><span style='font-size:13px;font-weight: bold;'>" +orderName+ "</span><br>(" +orderSTime+ "~" +orderETime+ ")</td>";

    				} 
    				item = item + "<td align='center' id= '"+dutyId+"' class='jobTd' class='jobTd' style='font-size:13px; padding:10px 20px 15px 20px; width:80px; height:80px; font-weight: bold; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;'>" +jobName+ "</td>";

    				var tdPeople = "";
    				for(var k = 0; k < dateDiff + 1; k++) {
    					
    					if( k < todayDiff && flag) {
        					tdPeople = tdPeople + "<td  id='p" +dutyId+ "_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4;word-break: keep-all; white-space:nowrap;'><ul id='" +dutyId+ "_" +k+ "' class='dutyTd'></ul></td>"	

    					} else {
        					tdPeople = tdPeople + "<td  id='p" +dutyId+ "_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +dutyId+ "_" +k+ "' class='dutyTd'></ul></td>"	
    					}
    				}
    				item = item + tdPeople;
    				item = item + "</tr>";
    				$("#dutyform").append(item);
    				
    				for(var k = 0; k < dateDiff + 1; k++) {
    	    	    	
    					if(dutyPoliceData[k].split("/")[0].indexOf(",") != -1) {
    						var policeIds = (dutyPoliceData[k].split("/")[0]).split(",");  //policeId
    					    var policeName = (dutyPoliceData[k].split("/")[1]).split(",");
    						
    					    for(var l = 0; l < policeIds.length; l++) {
    					    	
    					    	if( k < todayDiff && flag) {
    					    		var li = "<li id='id_p"+dutyId+"_"+k+"-"+policeIds[l]+"' style='font-size:13px;margin:8px'>" 
    						    	+policeName[l]+ "<input class='hTd' type='hidden' value='"+policeName[l]+"'/><a href='javascript:void(0);' id='p"+dutyId+"_"+k+"-"+policeIds[l]+"'></a></li>"
    	    	    	    	} else {
									var li = "<li id='id_p"+dutyId+"_"+k+"-"+policeIds[l]+"' style='font-size:13px;margin:8px'>" 
      						    	+policeName[l]+ "<input class='hTd' type='hidden' value='"+policeName[l]+"'/><a href='javascript:void(0);' id='p"+dutyId+"_"+k+"-"+policeIds[l]+"' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
    	    	    	    	}
    						    $("#p" +dutyId+ "_" +k+ " ul").append(li);         	
    						}
    					} else {
    						
    						var policeIds = dutyPoliceData[k].split("/")[0];  //policeId
    					    var policeName = dutyPoliceData[k].split("/")[1];
    						
    					    if( k < todayDiff && flag) {
    					    	var li = "<li id ='id_p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='font-size:13px;margin:8px'>" 
    							+policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='p" +dutyId+ "_" +k+ "-" +policeIds+ "'></a></li>"
    						
    					    }else {
    					    	var li = "<li id ='id_p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='font-size:13px;margin:8px'>" 
    							+policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
    					    }
    						$("#p" +dutyId+ "_" +k+ " ul").append(li);         	
    					}
   					}
   				} 
   	    	 } 
    	}
	    $("#jobCount").textbox("setValue",jobCount);
	    
	    $("#dutyList").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true,
			axis: "xy"
		});
	}	
	
	var tdColorId = null;  //选中的td的id
	function checkList(data) {
	
		if(tdColorId) {
			$("#" +tdColorId).css({"backgroundColor":""});
		} 
		tdColorId = (data.id).trim();
		$("#" +tdColorId).css({"backgroundColor":"#E0FFFF"});
		
		//每点击一次表格，首先清空人员列表
		chkListClear();
	}
	 
	function chkListClear() {
		 $("input:checkBox").removeAttr("checked");
	}
	
	function chkListAll() {
		$("input:checkBox").prop("checked","checked");	
	}
	
	/*
	* 值排班的【添加】按钮
	*/
	function chkListEnsure(e, ui) {
	
		var list = new Array();
		$("#zbbpPoliceList input[type='checkbox']:checked").each(function (i) {
			
			var id = $(this).attr("id");
			var name =  $(this).attr("name");
			list.push({"policeId" : id , "policeName" : name});			     
		});

		if(list.length > 0 && tdColorId != null ) {
			addCheckList(list,tdColorId);
		} else if (list.length == 0) {
			$.message({message:"请选择值班人员！", cls:"waring"});
		} else {
			$.message({message:"请选中添加区域！", cls:"waring"});
		}
	 }
	
	function addCheckList(list,pId) {
		for(var i = 0; i < list.length; i++) {
			
			var policeId = list[i].policeId;
			var policeName = list[i].policeName;

			var sameId = $("#id_" +pId+ "-" +policeId);
			
			if(sameId.length > 0 && sameId != '') {
				continue;
			}else {
				var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
				$("#" +pId+ " ul").append(li);
			}
		}
	}
	
	function onSelect(event, ui){
	
		initPoliceList(ui.value);
	}
	
	/* 加载选中部门民警 */
	function initPoliceList(DrpmntId) {
		
		if(DrpmntId) {
			selectedDrpmntId = DrpmntId;
		}
		$("#zbbpPoliceList").empty();
		$("#combobox_drpmntId").combobox("setValue",selectedDrpmntId);//下拉框选择
		var tab = $("#zbbpPoliceList");
		tab.css({"border-collapse":"separate","border-spacing": "8px"});
		
		$.ajax({
			type : 'post',
			/* url : '${ctx}/common/authsystem/findDeptPoliceForCombotree', */
			url:'${ctx}/zbgl/rygl/selectList.json',
			data: {
				cusNumber : cusNumber,
				/* id : selectedDrpmntId, */
				deptNumber:selectedDrpmntId
			},
			dataType : 'json',
			success : function(data) {
				
				var policeTr = null;
				for(var i = 0; i < data.length; i++) {
					policeTr = "<tr><td style='font-size:13px; width:140px; height:30px; word-break: keep-all; white-space:nowrap;'>"
							 	+ "<ul ondragstart='dragStart(this)'   ondrag='dragging(this)' draggable='true' ondrop='drop(event)' ondragover='allowDrop(event)'  id='" +data[i].id+ "' ><span>" 
							 	+ "<input class= 'checkBox' id='" +data[i].id+ "'name='" +data[i].name+ "' type='checkbox'/> &nbsp;&nbsp;&nbsp;" +data[i].name+ "</span></ul></td></tr>";
							 	
					tab.append(policeTr);
				}
				$.parser.parse(tab);
			}				
		});
		$("#policeList").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});
	}
	/**
	 * 拖动事件
	 */
	 var policeName = null;
	 var policeId = null;
	 function dragStart(data) {
		 
		 policeName = (data.innerText).trim();
		 policeId = data.id;
	}
	 
	function dragging(event) {
	  
	}
	
	function allowDrop(event) {
	    event.preventDefault();
	}
	
 	/* 判断是否重复选择警员 */
	function drop(data) {
	 
		var pId = data.id;
		var sameId = $("#id_" +pId+ "-" +policeId);
		
		if(sameId.length > 0 && sameId != '') {
			$.message({message:"重复选择！请拖拽正确的值班人员到值班表！", cls:"waring"});
		}else {
			var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
			$("#" +pId+ " ul").append(li);
		}
	} 
	
	function deleteSelPolice(data) {
		var id = data.id;
		$("#id_" +id).remove();
	}
	
	/**
	 * 获取td数量
	 */
	function getDutyTdPeopleLen() {
		
		var cloneNum = 0;
		var tdCount = 0;
		var seq = 0;
		var fSeq = 0;
		
		$("#dutyform").find("tr").each(function(i) {
			
			var seqN = 0;
			if(i == 1) {
				$(this).find(".dutyTd").each(function() {
					if($(this).find("li").html()) {
						cloneNum++;
						var liId = $(this).find("li").attr("id");
						fSeq =  parseInt(liId.split("o")[1].split("_")[0]) + 1; //第一行有效td个数
					}
				});
			}
			
			$(this).find(".dutyTd").each(function(j) {
				 if($(this).find("li").html()) {
					tdCount++;                          //算出已排班的td个数
					var liId = $(this).find("li").attr("id");
					seqN =  parseInt(liId.split("o")[1].split("_")[0]) + 1;  //每个<tr>的<td>序号
				}
			});
			seq =  seq + seqN ;
		});
		
		var jobCount = parseInt($("#jobCount").textbox("getValue"));
		var tableCount = jobCount*(dateDiff + 1);
		
		if (tdCount == tableCount) {
			
			$.message({message:"请勿重复复制！", cls:"waring"});
			
		} else if(seq == tdCount && (seq/fSeq) == jobCount) {
			
			if(cloneNum*jobCount == tdCount) {
				return cloneNum;          //已排班天数
			} else {
				$.message({message:"请将排班填写完整！", cls:"waring"}); 
			}
		} else {
			$.message({message:"请将排班填写完整！", cls:"waring"}); 
		}
	}
	
	/*复制数据  */
	function cloneData() {
		
		var cloneType = $("#cloneType").combobox("getValue");
		if (cloneType == "1") {
			
			var cloneNum = getDutyTdPeopleLen();
			
			$("#dutyform").find("tr").each(function(i) {
				
				if(i > 0) {
					
					$(this).find(".dutyTd").each(function(j) {
						
						if(j < cloneNum) {
							$(this).find("li").each(function(k) {
								
								for(var addClone = cloneNum ; addClone < dateDiff+1 ; addClone = addClone + cloneNum) {
									
									var liId = $(this).find("a").attr("id");
									var num = parseInt(liId.split("o")[1].split("_")[0]) + addClone;
									var pId = liId.split("o")[0]+ "o" +num+ "_" +num;
									var  policeName = $(this).find("input").val();
									var  policeId = liId.split("-")[1];
									var li ="<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
									
									$("#" +pId+ " ul").append(li); 
								}
							})
						}
					});
				}
			});
		}
	}

</script>