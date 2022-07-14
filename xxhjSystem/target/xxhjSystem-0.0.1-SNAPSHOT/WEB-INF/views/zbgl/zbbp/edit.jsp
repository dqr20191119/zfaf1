<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="java.net.URLEncoder" %>
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
#noDuty p{
    color: red;
    text-align: center;

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
						<%-- <cui:input type='hidden' name="zhzjobId"  id="zhzjobId" /> --%><!--指挥长模板详情id  -->
						<cui:input type='hidden' name="param"  id="param" />                <!-- 参数 --> 
						<cui:input type='hidden' name="jobCount"  id="jobCount" />          <!-- 岗位数量 -->
						<cui:input type='hidden' name="dateDiff"  id="dateDiff" />          <!-- 与当前日期相差天数，用来判断是否可修改 -->
						<cui:input type='hidden' name="initFormData"  id="initFormData" />
						 <fieldset> 
							<table class="table" style="width:1120px; height:auto">
								<tr>
									<th>排班表名称：</th>
									<td><cui:input id="dmdName" name= "dmdName"  required="true" ></cui:input></td>
									<th>值班类别：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_CategoryName" name= "cdmCategoryId"  required="true" onChange="initDrpmntList"></cui:combobox></td>
									<th>部门名称：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_DrpmntName"  name= "dmdDprtmntId" required="true" onSelect="initModeList"></cui:combobox></td>
									<td><cui:button label="重置" id="reset" onClick="reset"></cui:button></td>
								</tr>
								<tr>
									<th>模板名称：</th>
									<td><cui:combobox id="comboxId_zbbp_edit_ModeName" name= "dmdModeId" required="true" onSelect="onSelectedMode"></cui:combobox>
									</td>
									<th>排班开始时间：</th>
									<td><cui:datepicker id="dmdStartDate"  name="dmdStartDate" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
									</td>
					         		<th>排班结束时间：</th>
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
						 				<cui:button label="手动排班" id="ensure" componentCls="btn-primary" onClick="ensure('2')"/>	
						 				 <cui:button label="自动排班" id="autoDuty" componentCls="btn-primary" onClick="ensure('4')" />
                                        <cui:button label="打印预览" id ="print"  componentCls="btn-primary" onClick="openPrint" />
                                        <cui:button label="导出值班模板" id ="export_zbmb"  componentCls="btn-primary" onClick="exportZbmb" />
                                        <cui:button label="导入值班数据" id ="import_zbmb"  componentCls="btn-primary" onClick="toImport" />
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
									 	 <cui:autocomplete id="searchPolice"  placeholder="检索警察姓名" name="searchPolice" source="commonbox_ry" postMode="value" width="140" onChange="searchPolice" ></cui:autocomplete>
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
								<div id="dutyList" style="position:absolute; width:1050px; height:500px; overflow: auto;margin:5px 5px 5px 5px">
									<table border="1"  id="dutyform" style="table-layout:fixed;width:1000px;">
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
<div >
        <div id ="noDuty" style="margin-top: 110px">
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
	var dmdName = '${dmdName}';
	var flag = false; 
	var flags = true;          /* 监区的USER_LEVEL是3,权限被控制 */
	
	$.parseDone(function() {
		
		if(USER_LEVEL != null && USER_LEVEL == '3') {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId="+drpmntId+"&param=2");
			$("#comboxId_zbbp_edit_DrpmntName").combobox("option","readonly",true);
		} else {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1");
		}

		if(USER_LEVEL ==1){
		    $("#export_zbmb").hide();
            $("#import_zbmb").hide();
        }else{
            $("#import_zbmb").show();
        }

		$("#print").hide();
		if(USER_LEVEL ==1){
			var zbJob=[{"value":"指挥长","text":"指挥长"},
				{"value":"男值班长","text":"男值班长"},
				{"value":"女值班长","text":"女值班长"},
				{"value":"男值班员","text":"男值班员"},
				{"value":"女值班员","text":"女值班员"}
				]
			$("#combobox_drpmntId").combobox("reload",zbJob);
			//$("#combobox_drpmntId").combobox("setValue","指挥长");
		}
		
		if(USER_LEVEL!=1){
			$("#autoDuty").hide();
            $("#searchPolice").autocomplete("option","readonly", true);
			$("#searchPolice").hide();
		}
		
		if(id) {
			//修改
			$("#ensure").hide();
			$("#reset").hide();
			$(".cloneType").hide();
			$("#dmdName").textbox("setValue",dmdName);
			$("#id").textbox("setValue",id);
			$("#formId_zbbp_edit").form("setReadOnly",true);
			$("#dmdStartDate").datepicker("setDate",new Date(Date.parse(dmdStartDate)));
			$("#dmdEndDate").datepicker("setDate",new Date(Date.parse(dmdEndDate)));
			$("#comboxId_zbbp_edit_CategoryName").combobox("setValue",categoryId);
			initDrpmntList();
			$("#tips_tr").show();
			 
		} else {
			//新增
			//$("#dmdStartDate").datepicker("option","minDate", new Date());
			//initCategory(drpmntId);
			$("#policeTable").hide();
			$("#dutyTable").hide();
			$("#tips_tr").hide();
			getNextMothDate();//设置开始 和结束时间
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
				cdmSfqy:'1'
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
		//debugger;
        if(USER_LEVEL =='1'){
            $("#print").show();
        }
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
		//debugger;
		$("#dutyform").empty();
		var selectedModeId = $("#comboxId_zbbp_edit_ModeName").combobox("getValue");   //模板的主键
		var mojStartDate = $("#dmdStartDate").datepicker("getValue");
		var mojEndDate = $("#dmdEndDate").datepicker("getValue");
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/getById',
			data: {
				mojCusNumber : cusNumber,
				modelId : selectedModeId,
				id : id,              //模板部门表主键
				mojStartDate : mojStartDate,
				mojEndDate : mojEndDate,
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
		//var zhzOrderId ="";//记录指挥长班次id
		$("#param").textbox("setValue",param);       //后台判断参数 
		
		SDate = $("#dmdStartDate").datepicker("getValue");
		EDate = $("#dmdEndDate").datepicker("getValue");
		if(param != "4"){//自动排班  跳过
			order = (data.cdmOrderData ).split(";");
			orderCount = order.length; 
		}
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
		
		
	    /* var title = "<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>"+
	    			"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:120px; height:60px; word-break: keep-all;white-space:nowrap;'>日期</td>"+
	    			"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>星期</td>"+
	    			"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>指挥长</td>"+
	    			"<td colspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
	    			"<span style='font-size:13px;font-weight: bold;'>早班</span><br>(8:00~16:00)"+
	    			"</td>"+
	    			"<td colspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
	    			"<span style='font-size:13px;font-weight: bold;'>中班</span><br>(16:00~23:00)"+
	    			"</td>"+
	    			"<td colspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
	    			"<span style='font-size:13px;font-weight: bold;'>晚班</span><br>(23:00~8:00)"+
	    			"</td>"+
	    			"</tr>"+
	    			"<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班长</td>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班员</td>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班长</td>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班员</td>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班长</td>"+
	    			"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 值班员</td>"+
	    			"</tr>"  */
	    	 var title ="<thead'>"	
   			  title =title+ "<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>"+
   						"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:120px; height:60px; word-break: keep-all;white-space:nowrap;'>日期</td>"+
   						"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>星期</td>";		 
   		  var jobCount =  0;
	    if(param == '2') {  //新增编排
	    	 for(var k = 0;k < order.length; k++){//班次标题
	    		var	 orderID = (order[k].split("-")[0]).split("_")[0];
			    var	 orderName = (order[k].split("-")[0]).split("_")[1];
			    var	 orderSTime = (order[k].split("-")[0]).split("_")[2];
			    var	 orderETime = (order[k].split("-")[0]).split("_")[3];
			    var jobList  = (order[k].split("-")[1]).split(",");
			    if(cusNumber=='4300'){
                    if(jobList.length==1){
                        var job_Name = (jobList[0]).split("_")[1];
                        title = title+"<td rowspan='2' id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>"+job_Name+"</td>";
                    }else{
                        title = title +  "<td colspan='2'id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
                            "<span style='font-size:13px;font-weight: bold;'>"+orderName+"</span><br>("+orderSTime+"~"+orderETime+")"+
                            "</td>";
                    }
                }else{
                    title = title +  "<td colspan='"+jobList.length+"'  id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
                        "<span style='font-size:13px;font-weight: bold;'>"+orderName+"</span><br>("+orderSTime+"~"+orderETime+")"+
                        "</td>";
                }

	    	}
	    	title = title + "</tr>";//第一行结束
	    	title =title+"<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold;'>"; //第二行开始
	    	for(var g = 0;g < order.length; g++){
	    		var job  = (order[g].split("-")[1]).split(",");
	    		jobCount = parseInt(jobCount + job.length);      //岗位数统计
	    		for(var j = 0;j < job.length; j++) {
		    		var jobID = (job[j]).split("_")[0];  //模板详情表中的主键
		    		var jobName = (job[j]).split("_")[1];
		    		//第二添加td
                    if(cusNumber=='4300'){
                        if(job.length!=1){
                            title = title+"<td align='center'id='"+jobID+"' class='jobTd' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>"+jobName+"</td>";
                        }else{
                            // $("#zhzjobId").textbox("setValue",jobID);
                            title = title+"<input type='hidden'  id='"+jobID+"' class='jobTd'/>"
                        }
                    }else{
                        title = title+"<td align='center'id='"+jobID+"' class='jobTd' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>"+jobName+"</td>";
                    }

		    	}
	    	}
	    	title = title +"</tr></thead><tbody id='dutyDate'>";//标题结束  共两行
	    	
	    	
	    $("#dutyform").append(title); 
	    
	    
	    	for(var i = 0; i< dateDiff + 1; i++) { 
		    	var date = new Date(Date.parse(SDate)); 
		    	var tDate = addDate("d",date,i);
		    	var aDate = formatterDate(tDate,"ymd")
				var week = getWeek(tDate);
		    	var item = "<tr>";
		    	item = item + "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >第" +(i+1)+ "天<br><span style='font-weight: bold;'>" +aDate+ "</span></td>"+
		    				  "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;'>" +week+ "</td>"
		    	
  				//var tdPeople = "<td id='p"+i+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+"' class='dutyTd'></ul></td>";
  				var tdPeople ="";
  				for(var j = 0;j < jobCount; j++) {
  					tdPeople = tdPeople +"<td id='p"+i+"_"+j+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+"_"+j+"' class='dutyTd'></ul></td>";
  					
  				}
				  item=item+tdPeople+"</tr></tbody>";
				  $("#dutyform").append(item); 
		    }
	    	
	   		 /* for(var i = 0;i < order.length; i++) {
	    	
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
	    	}  */
    	} else if(param == '3') { //修改编排
    		jobCount=0;
    			var dutyIds = new Array();//储存模板id
    			//var dutyPoliceData=null;
				for(var i = 0;i < order.length; i++){//班次标题
					var orderID = (order[i].split("*")[0]).split("&")[0];
	    	    	var orderName = (order[i].split("*")[0]).split("&")[1];
	    	    	var orderSTime = (order[i].split("*")[0]).split("&")[2];
	    	    	var orderETime = (order[i].split("*")[0]).split("&")[3];
	    	    	var jobList = (order[i].split("*")[1]).split("~");
	    	    	jobCount = parseInt(jobCount+jobList.length);
	    	    	    if(cusNumber=='4300'){
                            if(jobList.length==1){
                                var job_Name = (jobList[0].split("%")[0]).split("&")[1];
                                title = title+"<td rowspan='2' id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>"+job_Name+"</td>";
                            }else{
                                title = title +  "<td colspan='2'id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
                                    "<span style='font-size:13px;font-weight: bold;'>"+orderName+"</span><br>("+orderSTime+"~"+orderETime+")"+
                                    "</td>";
                            }
                        }else{
                            title = title +  "<td colspan='"+jobList.length+"'  id='"+orderID+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
                                "<span style='font-size:13px;font-weight: bold;'>"+orderName+"</span><br>("+orderSTime+"~"+orderETime+")"+
                                "</td>";
                        }

				    	}
				    	title = title + "</tr>";//第一行结束
				    	title =title+"<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>"; //第二行开始
				    	var count = 0;//计数岗位
				    	for(var g = 0;g < order.length; g++){
				    		var jobList = (order[g].split("*")[1]).split("~");
				    		for(var j = 0;j < jobList.length; j++) {
				    			var dutyId = (jobList[j].split("%")[0]).split("&")[0];    //DBD_DUTY_MODE_ORDER_JOB_ID
				    			dutyIds[count] = dutyId;
				    			count++;
			    	    		var jobName = (jobList[j].split("%")[0]).split("&")[1];
			    	    		// dutyPoliceData = (jobList[j].split("%")[1]).split("@");
					    		//第二添加td
                                if(cusNumber=='4300'){
                                    if(jobList.length!=1){
                                        title = title+"<td align='center'id='"+dutyId+"' class='jobTd' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>"+jobName+"</td>";
                                    }else{
                                        // $("#zhzjobId").textbox("setValue",jobID);
                                        title = title+"<input type='hidden'  id='"+dutyId+"' class='jobTd'/>"
                                    }
                                }else{
                                    title = title+"<td align='center'id='"+dutyId+"' class='jobTd' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>"+jobName+"</td>";
                                }

					    	}
				    	}
				    	title = title +"</tr></thead><tbody>";//标题结束  共两行
				    	
				    	
				    $("#dutyform").append(title); 
				    
				    
				    	for(var i = 0; i< dateDiff + 1; i++) { 
					    	var date = new Date(Date.parse(SDate)); 
					    	var tDate = addDate("d",date,i);
					    	var aDate = formatterDate(tDate,"ymd")
							var week = getWeek(tDate);
					    	var item = "<tr>";
					    	item = item + "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >第" +(i+1)+ "天<br><span style='font-weight: bold;'>" +aDate+ "</span></td>"+
					    				  "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;'>" +week+ "</td>"
					    	
			  				//var tdPeople = "<td id='p"+i+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+"' class='dutyTd'></ul></td>";
			  				var tdPeople ="";
			  				for(var k=0;k<jobCount;k++){
			  					if( k < todayDiff && flag) {
		        					tdPeople = tdPeople + "<td  id='p" +dutyIds[k]+"_"+i +"_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4;word-break: keep-all; white-space:nowrap;'><ul id='" +dutyIds[k]+ "_"+i+"_" +k+ "' class='dutyTd'></ul></td>"	

		    					} else {
		        					tdPeople = tdPeople + "<td  id='p" +dutyIds[k]+"_"+i +"_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +dutyIds[k]+"_"+i+ "_" +k+ "' class='dutyTd'></ul></td>"	
		    					}
			  				} 
			  				
		    				item = item + tdPeople;
							  item=item+"</tr></tbody>";
							  $("#dutyform").append(item); 
					    }
				    	
				    	var row =0;
				    	var low =0;
				    	for(var i = 0; i< order.length; i++) {
			    	    	var jobList = (order[i].split("*")[1]).split("~");
			    	    	for(var j = 0; j< jobList.length; j++) {
			    	    		//debugger;
			    	    		var dutyId = (jobList[j].split("%")[0]).split("&")[0];
			    	    		var dutyPoliceData = (jobList[j].split("%")[1]).split("@");
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
			    						    //$("#p" +dutyId+ +"_"+row+"_" +low+ " ul").append(li);
			    					    	$("#" +dutyId+"_"+row+"_" +low).append(li);
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
			    						//$("#p" +dutyId+"_"+j +"_" +k+ " ul").append(li);   
			    					    $("#" +dutyId+"_"+row+"_" +low).append(li);
			    					}
			    					 row++;
			   					}
			    				row=0;
			    				low++;
			   				} 
			   	    	 }
				    	
				    	

    		/* for(var i = 0; i< order.length; i++) {
    			
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
   	    	 } */ 
    	}else if(param == '4'){//自动排班
    		//debugger;
    		var mbxqList = 	data.mbxqList;//模板详情

            var continueDutyMessage = data.continueDutyMessage;//连续值班详情
    		//var dutyJobs = new Array();
    		jobCount = mbxqList.length;
    		for(var i = 0 ;i <mbxqList.length;i++){
    			if(i==0){//指挥长
    				title =title +"<td rowspan='2' id='"+mbxqList[i].mojOrderId+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>"+mbxqList[i].cdjJobName+"</td>"
    			}else{
    				if(mbxqList[i].mojOrderId!=mbxqList[i-1].mojOrderId){//不在同一班次
    					title =title +"<td colspan='2'id='"+mbxqList[i].mojOrderId+"' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:220px; height:80px;word-break: keep-all;white-space:nowrap;'>"+
										"<span style='font-size:13px;font-weight: bold;'>"+mbxqList[i].dorDutyOrderName+"</span><br>("+mbxqList[i].dorStartTime+"~"+mbxqList[i].dorEndTime+")"+
										"</td>"
    				}else{
    					continue;
    				}
    			}
    			
    		}
    		
    		title = title + "</tr>";//第一行结束
    		title =title+"<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>";//开始第二行
    		//添加第二行
    		for(var k = 0; k<mbxqList.length;k++){
    			//第二添加td
        		if(mbxqList[k].cdjJobName!="指挥长"){
        			title = title+"<td align='center'id='"+mbxqList[k].id+"' class='jobTd' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>"+mbxqList[k].cdjJobName+"</td>";
        		}else{
        			// $("#zhzjobId").textbox("setValue",jobID);
        			title = title+"<input type='hidden'  id='"+mbxqList[k].id+"' class='jobTd'/>"
        		}
    		}
    		title = title +"</tr></thead><tbody id='dutyDate'>";//标题结束  共两行
    		 $("#dutyform").append(title); 
    		var orderPolices = data.orderPolices;//排班人员数据
    		
    		
    		for(var i = 0; i< dateDiff + 1; i++) { 
		    	var date = new Date(Date.parse(SDate)); 
		    	var tDate = addDate("d",date,i);
		    	var aDate = formatterDate(tDate,"ymd")
				var week = getWeek(tDate);
		    	var tdPeople ="";
		    	//
		    	//var policeName =
		    	var item = "<tr>";
		    	item = item + "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >第" +(i+1)+ "天<br><span style='font-weight: bold;'>" +aDate+ "</span></td>"+
		    				  "<td td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;'>" +week+ "</td>"
		    	
  				//var tdPeople = "<td id='p"+i+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+"' class='dutyTd'></ul></td>";
  					
  				/* 	var li = "<li id ='id_p" +mbxqList[j].id+ "_" +j+ "-" +policeIds+ "' style='font-size:13px;margin:8px'>" 
								+policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='p" +mbxqList[j].id+ "_" +k+ "-" +mbxqList[j].id+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"; */
					//debugger;
				/**
				<td  id='p" +dutyId+ "_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +dutyId+ "_" +k+ "' class='dutyTd'></ul></td>
				
				*/
					var zhzId =	orderPolices[i].zhz.split("&")[0];		
					var zhzName =	orderPolices[i].zhz.split("&")[1];
					
					/*
							var li = "<li id ='id_p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='font-size:13px;margin:8px'>" 
    							+policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
					*/
  					tdPeople = tdPeople + "<td id='p" +i+ "_0'   align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_0'  class='dutyTd'><li id ='id_p" +i+ "_0-" +zhzId+ "' style='font-size:13px;margin:8px'>" 
								+zhzName+ "<input class='hTd' type='hidden' value='" +zhzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_0-" +zhzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
					
					var	zzbzId =	orderPolices[i].zzbz.split("&")[0];
					var  zzbzName = orderPolices[i].zzbz.split("&")[1];
				
					tdPeople = tdPeople + "<td id='p" +i+ "_1'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_1' class='dutyTd'><li id ='id_p" +i+ "_1-" +zzbzId+ "' style='font-size:13px;margin:8px'>" 
					+zzbzName+ "<input class='hTd' type='hidden' value='" +zzbzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_1-" +zzbzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";			
					
					
					var  zzbyId = orderPolices[i].zzby.split("&")[0];
					var zzbyName = orderPolices[i].zzby.split("&")[1];
					tdPeople = tdPeople + "<td id='p" +i+ "_2'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_2' class='dutyTd'><li id ='id_p" +i+ "_2-" +zzbyId+ "' style='font-size:13px;margin:8px'>" 
					+zzbyName+ "<input class='hTd' type='hidden' value='" +zzbyName+ "'/><a href='javascript:void(0);' id='p" +i+ "_2-" +zzbyId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";		
					
					var zhzbzId = orderPolices[i].zhzbz.split("&")[0];
					var  zhzbzName = orderPolices[i].zhzbz.split("&")[1];
					if(data.noZhzMessage !=undefined && data.noZhzMessage.length>0 ){
					    if(listIsIncludeString(data.noZhzMessage,zhzbzId,aDate)){
                            tdPeople = tdPeople + "<td id='p" +i+ "_3'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_3' class='dutyTd'><li id ='id_p" +i+ "_3-" +zhzbzId+ "' style='font-size:13px;margin:8px;color: red'>"
                                +zhzbzName+ "<input class='hTd' type='hidden' value='" +zhzbzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_3-" +zhzbzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
                        }else{
                            tdPeople = tdPeople + "<td id='p" +i+ "_3'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_3' class='dutyTd'><li id ='id_p" +i+ "_3-" +zhzbzId+ "' style='font-size:13px;margin:8px'>"
                                +zhzbzName+ "<input class='hTd' type='hidden' value='" +zhzbzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_3-" +zhzbzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
                        }
                    }else{
                        tdPeople = tdPeople + "<td id='p" +i+ "_3'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_3' class='dutyTd'><li id ='id_p" +i+ "_3-" +zhzbzId+ "' style='font-size:13px;margin:8px'>"
                            +zhzbzName+ "<input class='hTd' type='hidden' value='" +zhzbzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_3-" +zhzbzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
                    }



					var zhzbyId = orderPolices[i].zhzby.split("&")[0];
					var zhzbyName = orderPolices[i].zhzby.split("&")[1];
					
					tdPeople = tdPeople + "<td id='p" +i+ "_4'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_4' class='dutyTd'><li id ='id_p" +i+ "_4-" +zhzbyId+ "' style='font-size:13px;margin:8px'>" 
					+zhzbyName+ "<input class='hTd' type='hidden' value='" +zhzbyName+ "'/><a href='javascript:void(0);' id='p" +i+ "_4-" +zhzbyId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
					
					var wzbzId = orderPolices[i].wzbz.split("&")[0];
					var wzbzName = orderPolices[i].wzbz.split("&")[1];
					
					tdPeople = tdPeople + "<td id='p" +i+ "_5'  align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_5' class='dutyTd'><li id ='id_p" +i+ "_5-" +wzbzId+ "' style='font-size:13px;margin:8px'>" 
					+wzbzName+ "<input class='hTd' type='hidden' value='" +wzbzName+ "'/><a href='javascript:void(0);' id='p" +i+ "_5-" +wzbzId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
					
					var wzbyId = orderPolices[i].wzby.split("&")[0];
					var wzbyName = orderPolices[i].wzby.split("&")[1];
					
					tdPeople = tdPeople + "<td id='p" +i+ "_6' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +i+ "_6' class='dutyTd'><li id ='id_p" +i+ "_6-" +wzbyId+ "' style='font-size:13px;margin:8px'>" 
					+wzbyName+ "<input class='hTd' type='hidden' value='" +wzbyName+ "'/><a href='javascript:void(0);' id='p" +i+ "_6-" +wzbyId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li></ul></td>";
				  item=item+tdPeople+"</tr>";
				  $("#dutyform").append(item); 
		    }
    		
    		$("#dutyform").append("</tbody>"); 
    		
            if(continueDutyMessage !=""){
                $.alert("连续值班情况:"+continueDutyMessage);
            }

        // console.log("continueDutyMessage:"+continueDutyMessage);

    		
    	}
	    $("#jobCount").textbox("setValue",jobCount);
	    //dutyList  dutyDate
	   $("#dutyList").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true,
			axis: "xy"
		});

	   insertNoduty(data);
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
	/**
	检索民警信息
	*/
	function searchPolice(event, ui){
		var mjId = ui.item.value;
		var mjName =ui.item.label;
		$("#zbbpPoliceList").empty();
		var tab = $("#zbbpPoliceList");
		tab.css({"border-collapse":"separate","border-spacing": "8px"});
		
		var policeTr = "<tr><td style='font-size:13px; width:140px; height:30px; word-break: keep-all; white-space:nowrap;'>"
		 	+ "<ul ondragstart='dragStart(this)'   ondrag='dragging(this)' draggable='true' ondrop='drop(event)' ondragover='allowDrop(event)'  id='" +mjId+ "' ><span>" 
		 	+ "<input class= 'checkBox' id='" +mjId+ "'name='" +mjName+ "' type='checkbox'/> &nbsp;&nbsp;&nbsp;" +mjName+ "</span></ul></td></tr>";
		 	
		tab.append(policeTr);
		$.parser.parse(tab);
	}
	
	
	
	/* 加载选中部门民警 */
	function initPoliceList(DrpmntId) {
		debugger;
		var ryParam = {};
		var url="";
		ryParam["cusNumber"] =cusNumber;
		if(DrpmntId) {
			selectedDrpmntId = DrpmntId;
		}
		
		$("#zbbpPoliceList").empty();
		if(USER_LEVEL!=1){
			$("#combobox_drpmntId").combobox("setValue",selectedDrpmntId);//下拉框选择
			ryParam["id"] =selectedDrpmntId;
			url = "${ctx}/common/authsystem/findDeptPoliceForCombotree";
		}else{
			ryParam["job"] =selectedDrpmntId;
			url = "${ctx}/zbgl/rygl/selectList.json";
		}
		var tab = $("#zbbpPoliceList");
		tab.css({"border-collapse":"separate","border-spacing": "8px"});
		
		
		$.ajax({
			type : 'post',
			/* url : '${ctx}/common/authsystem/findDeptPoliceForCombotree', */
			url:url,
			data: ryParam,
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
		//dutyDate
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
		var pId = data.id;//p0_1 p1_1 最后一位表示 列  从0开始
		var col = pId.substring(pId.length-1);//列数 0 为指挥长  1.女值班长 2.女值班员 3.男值班长 4.男值班员 5.男值班长  6.男值班员
		var tempJob = "";
		if(col==0){
			tempJob = "指挥长";
		}else if(col==1){
			tempJob = "女值班长";
		}else if(col==2){
			tempJob = "女值班员";
		}else if(col==3){
			tempJob = "男值班长";
		}else if(col==4){
			tempJob = "男值班员";
		}else if(col==5){
			tempJob = "男值班长";
		}else if(col==6){
			tempJob = "男值班员";
		}
		var sameId = $("#id_" +pId+ "-" +policeId);
		if(USER_LEVEL==1){
			$.ajax({
				type : 'post',
				url : '${ctx}/zbgl/rygl/getById?id='+policeId,
				dataType : 'json',
				success : function(ryDdata) {
					var job = ryDdata.job;
					if(USER_LEVEL==1){//只有省局用户才做岗位判断
					    debugger;
						if(tempJob==""){
							
						}else{
							if(job=="男值班长" || job=="指挥长"){
								if(job.substring(1)==tempJob.substring(1)  ||job=="指挥长"){
									
								}else{
									$.message({message:"该警察不是"+tempJob+"岗位,请排到其他岗位！", cls:"waring"});
									return;
								}
							}else if(job=="男值班员"){
								if(job.substring(1)==tempJob.substring(1)){
									
								}else{
									$.message({message:"该警察不是"+tempJob+"岗位,请排到其他岗位！", cls:"waring"});
									return;
								}
							}else{
								if(job!=tempJob){
									$.message({message:"该警察不是"+tempJob+"岗位,请排到其他岗位！", cls:"waring"});
									return;
								}
							}
							
						}
					}
					
					 if(sameId.length > 0 && sameId != '') {
							$.message({message:"重复选择！请拖拽正确的值班人员到值班表！", cls:"waring"});
						}else {
							var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
							$("#" +pId+ " ul").append(li);
						} 
				}
			});
		}else{
			//debugger;
			 if(sameId.length > 0 && sameId != '') {
					$.message({message:"重复选择！请拖拽正确的值班人员到值班表！", cls:"waring"});
				}else {
					var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
					$("#" +pId+ " ul").append(li);
				} 
		}
		
		
		
		
		
		/* if(sameId.length > 0 && sameId != '') {
			$.message({message:"重复选择！请拖拽正确的值班人员到值班表！", cls:"waring"});
		}else {
			var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
			$("#" +pId+ " ul").append(li);
		} */
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
	

	function getNextMothDate(){
		var curDate = new Date();
	    var y = curDate.getFullYear();
	    var m = curDate.getMonth() + 2;//本身就得+1才等于当前月份，然而我要计算下一个月，所以直接+2
	    if( m > 12 ){
	        m = 1;
	        y++ 
	     }
	    var monthLastDay = new Date(y,m,0).getDate();
	    var syqxks = y + '-' + (m < 10 ? '0'+m : m) + '-' + '01';
	    var syqxjs = y + '-' + (m < 10 ? '0'+m : m) + '-' + (monthLastDay < 10 ? '0'+monthLastDay : monthLastDay);
	    console.log("syqxks="+syqxks+"syqxjs="+syqxjs)
	    $("#dmdStartDate").datepicker("setValue",syqxks);
	    $("#dmdEndDate").datepicker("setValue",syqxjs);
	}


	function insertNoduty(data) {
        if(data.noDuty !="" && data.noDuty !=undefined){
            var noDutyRy =data.noDuty.split(";");
            var p= "<p>本次未参与值班人员</p>";
            for (var g = 0; g < noDutyRy.length-1; g++) {
                p +="<p>"+(g+1)+"、"+noDutyRy[g]+"</p>"
            }
            $("#noDuty").append(p);
        }
    }





   function listIsIncludeString(noZhzMessage,id,date) {
	    //debugger;
	    var flag = false;
        for (var k = 0; k <noZhzMessage.length ; k++) {
            if(noZhzMessage[k].id==id && noZhzMessage[k].date==date){
                flag =true;
                break;
            }
        }
       return flag;
    }
    
    

	/**
	自动排班
	*/
	
	function autoDuty(){
		
	}

	/*
	* 导出值班模板
	*
	* */
	function exportZbmb() {
        var formData = $("#formId_zbbp_edit").form("formData");
        var validFlag  = $("#formId_zbbp_edit").form("valid");
        var data = validateDutydate(formData);
        if(data && data.length >0) {
            $.message({message:"生效日期或截止日期已存在编排记录!", cls:"waring"});
            return;
        }
        if(!validFlag) {
            return;
        }else{
            var cdmCategoryId =formData.cdmCategoryId;
            var dmdDprtmntId =formData.dmdDprtmntId;
            var dmdModeId =formData.dmdModeId;
            var dmdStartDate =formData.dmdStartDate;
            var dmdEndDate =formData.dmdEndDate;
            var url = "${ctx}/zbgl/zbbp/exportZbmbExcel?categoryId=" +cdmCategoryId+ "&dmdDprtmntId=" +dmdDprtmntId+ "&dmdModeId="
                +dmdModeId+ "&dmdStartDate=" +dmdStartDate+ "&dmdEndDate=" +dmdEndDate;
            window.location.href = url;
        }
    }



    //文件导入
    function toImport(){
        var formData = $("#formId_zbbp_edit").form("formData");
        var data = validateDutydate(formData);
        if(data && data.length >0) {
            $.message({message:"生效日期或截止日期已存在编排记录!", cls:"waring"});
            return;
        }
        var validFlag  = $("#formId_zbbp_edit").form("valid");
        if(!validFlag) {
            return;
        }else{
            var dmdName =formData.dmdName;
            var cdmCategoryId =formData.cdmCategoryId;
            var dmdDprtmntId =formData.dmdDprtmntId;
            var dmdModeId =formData.dmdModeId;
            var dmdStartDate =formData.dmdStartDate;
            var dmdEndDate =formData.dmdEndDate;
            var ur = "${ctx}/zbgl/zbbp/toImport?categoryId=" +cdmCategoryId+ "&dmdDprtmntId=" +dmdDprtmntId+ "&dmdModeId="
                +dmdModeId+ "&dmdStartDate=" +dmdStartDate+ "&dmdEndDate=" +dmdEndDate+"&dmdName=" +dmdName;
            ur =encodeURI(ur);
            ur =encodeURI(ur);
            $("#dialogImport" ).dialog("option",{
                title:"导入窗口",
                subTitle:"信息",
                width:520,
                height:450,
                url:ur
            });
            $( "#dialogImport" ).dialog("open");
        }
    }

</script>