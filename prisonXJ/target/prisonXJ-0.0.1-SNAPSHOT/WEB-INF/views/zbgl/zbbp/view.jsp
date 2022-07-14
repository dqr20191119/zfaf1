<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<style>
    #noDuty p {
        color: red;
        text-align: center;

    }


</style>

<div style="height: auto;width:auto;">	
	<div style="margin: 5px 5px 5px 5px;height: auto;width:auto;">
		<cui:form id="formId_zbbp_view">
			<cui:input type='hidden' name="dmdCusNumber" />
			<cui:input type='hidden' name="dmdStatus" />
			<cui:input type='hidden' name="param"  id="param" />
			<cui:input type='hidden' name="jobCount"  id="jobCount" />
			<cui:input type='hidden' name="dateDiff"  id="dateDiff" />
				<table border="0" >
					<tr>
						<td>
							 <fieldset> 
								<table class="table" style="width:1158px;height:auto">
									<tr>
										<th>值班类别：</th>
										<td><cui:combobox id="comboxId_zbbp_view_CategoryName"  url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1"></cui:combobox></td>
										<th>部门名称：</th>
										<td><cui:combobox id="comboxId_zbbp_view_DrpmntName"  ></cui:combobox>
										</td>
										<th>模板名称：</th>
										<td><cui:combobox id="comboxId_zbbp_view_ModeName" ></cui:combobox>
										</td>
									</tr>
									<tr>
										<th>生效日期：</th>
										<td><cui:datepicker id="dmdStartDate"  name="dmdStartDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
										</td>
						         		<th>截止日期：</th>
										<td><cui:datepicker id="dmdEndTime"  name="dmdEndDate"  startDateId="dmdStartDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td>
						 	<fieldset>
								<div id="dutyList_view" style="width:1155px; height:510px;  overflow: auto; margin:5px 5px 0px 5px;" >
									<table border="2" border-color="black" id="dutyform" style="height: auto; width: auto;">
									</table>
								</div>
							</fieldset>
						</td>
					</tr>

			</table>
		</cui:form>	
	</div>
    <div id ="noDuty">
    </div>
</div>
	
<script>
	var id='${id}';                 //模板部门表的主键
	var dmdDprtmntId = '${dmdDprtmntId}';
	var categoryId = '${categoryId}';
	var dmdModeId = '${dmdModeId}'    //模板的主键
	var dmdStartDate ='${dmdStartDate}';
	var dmdEndDate = '${dmdEndDate}';
	
	$.parseDone(function() {
		
		$("#formId_zbbp_view").form("setReadOnly",true);
		$("#dmdStartDate").datepicker("setDate",new Date(Date.parse(dmdStartDate)));
		$("#dmdEndTime").datepicker("setDate",new Date(Date.parse(dmdEndDate)));
		
		$("#comboxId_zbbp_view_CategoryName").combobox("setValue",categoryId);
		initDrpmntList();
	});
	
	function initDrpmntList() {
		
			$("#comboxId_zbbp_view_DrpmntName").combobox("loadData",combobox_bm);
			$('#comboxId_zbbp_view_DrpmntName').combobox("setValue",dmdDprtmntId); 
			
			initModeList();
	}
			
	function initModeList() {
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/searchAllData',
			data: {
				cdmCategoryId : categoryId,
			},
			dataType : 'json',
			success : function(data) {
				
				var modeList = [];
				for (var i = 0; i < data.length; i++) {
					
					var list = {};
					list.value = data[i].id;
					list.text = data[i].cdmModeName;
					modeList[i] = list;
			 	}
				$("#comboxId_zbbp_view_ModeName").combobox("loadData",modeList);
				$('#comboxId_zbbp_view_ModeName').combobox("setValue",dmdModeId);
				initModeData();
			}				
		});
	}
	
	/* 查询模板数据 */
	function initModeData() {
		 
		$("#dutyform").empty();
		var selectedModeId = $("#comboxId_zbbp_view_ModeName").combobox("getValue");
			
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/getById',
			data: {
				mojCusNumber: cusNumber,
				modelId : selectedModeId,  //模板主键
				id : id,              //模板部门表主键
				mojStartDate : dmdStartDate,
				mojEndDate : dmdEndDate,
				param : "3",
			},
			dataType : 'json',
			success : function(data) {
				parseDutyForm(data);
			}
		});
	}
	
	/* 重现表格数据 */
	function parseDutyForm(data,para) {
		
		var SDate = $("#dmdStartDate").datepicker("getValue");
		var EDate = $("#dmdEndTime").datepicker("getValue");
		var order = (data.cdmOrderData).split(";");
		var dateDiff = getDateDiff(SDate,EDate);
		
		 var title = "<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'>"+
			"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:120px; height:60px; word-break: keep-all;white-space:nowrap;'>日期</td>"+
			"<td rowspan='2' align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:100px; height:80px;word-break: keep-all;white-space:nowrap;'>星期</td>";		 
		
			
			
		
			var dutyIds = new Array();//储存模板id
			for(var i = 0;i < order.length; i++){//班次标题
				var orderID = (order[i].split("*")[0]).split("&")[0];
    	    	var orderName = (order[i].split("*")[0]).split("&")[1];
    	    	var orderSTime = (order[i].split("*")[0]).split("&")[2];
    	    	var orderETime = (order[i].split("*")[0]).split("&")[3];
    	    	var jobList = (order[i].split("*")[1]).split("~");
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
                                if(jobName!="指挥长"){
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
			    	title = title +"</tr>";//标题结束  共两行
			    	
			    	
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
		  				for(var k=0;k<dutyIds.length;k++){
	        					tdPeople = tdPeople + "<td  id='p" +dutyIds[k]+"_"+i +"_" +k+ "' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='" +dutyIds[k]+"_"+i+ "_" +k+ "' class='dutyTd'></ul></td>"	
		  				} 
		  				
	    				item = item + tdPeople;
					    item=item+"</tr>";
						  $("#dutyform").append(item); 
				    }
			    	
			    	var row =0;
			    	var low =0;
			    	for(var i = 0; i< order.length; i++) {
		    	    	var jobList = (order[i].split("*")[1]).split("~");
		    	    	for(var j = 0; j< jobList.length; j++) {
		    	    		var dutyId = (jobList[j].split("%")[0]).split("&")[0];
		    	    		var dutyPoliceData = (jobList[j].split("%")[1]).split("@");
		    				for(var k = 0; k < dateDiff + 1; k++) {
		    					if(dutyPoliceData[k].split("/")[0].indexOf(",") != -1) {
		    						var policeIds = (dutyPoliceData[k].split("/")[0]).split(",");  //policeId
		    					    var policeName = (dutyPoliceData[k].split("/")[1]).split(",");
		    						
		    					    for(var l = 0; l < policeIds.length; l++) {
		    					    	
		    					    		var li = "<li id='id_p"+dutyId+"_"+k+"-"+policeIds[l]+"' style='font-size:13px;margin:8px'>" 
		    						    	+policeName[l]+ "<input class='hTd' type='hidden' value='"+policeName[l]+"'/><a href='javascript:void(0);' id='p"+dutyId+"_"+k+"-"+policeIds[l]+"'></a></li>"
		    						    //$("#p" +dutyId+ +"_"+row+"_" +low+ " ul").append(li);
		    					    	$("#" +dutyId+"_"+row+"_" +low).append(li);
		    						}
		    					   
		    					} else {
		    						
		    						var policeIds = dutyPoliceData[k].split("/")[0];  //policeId
		    					    var policeName = dutyPoliceData[k].split("/")[1];
		    					    	var li = "<li id ='id_p" +dutyId+ "_" +k+ "-" +policeIds+ "' style='font-size:13px;margin:8px'>" 
		    							+policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='p" +dutyId+ "_" +k+ "-" +policeIds+ "'></a></li>"
		    						//$("#p" +dutyId+"_"+j +"_" +k+ " ul").append(li);   
		    					    $("#" +dutyId+"_"+row+"_" +low).append(li);
		    					}
		    					 row++;
		   					}
		    				row=0;
		    				low++;
		   				} 
		   	    	 }	
			
		
		
		/* var title = "<tr style='background-color:#337ab7;color:white;font-size:15px;font-weight: bold'><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 班次</td><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px;word-break: keep-all;white-space:nowrap;'>岗位</td>"

	    for(var i = 0;i < dateDiff+1; i++){
	    	
	    	var date = new Date(Date.parse(SDate)); 
	    	var tDate = addDate("d",date,i);
	    	
	    	var ssdate = formatterDate(tDate,"ymd")
	    
			var week = getWeek(tDate);
	    	title = title+"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >"+ssdate+"<br>"+week+"</td>";
	    }
	    
	    title = title+ "</tr>";
	    $("#dutyform").append(title); 
	   
	    for(var i = 0; i < order.length; i++) {
	  	  
	    	var orderID = (order[i].split("*")[0]).split("&")[0];
	    	var orderName = (order[i].split("*")[0]).split("&")[1];
	    	var orderSTime = (order[i].split("*")[0]).split("&")[2];
	    	var orderETime = (order[i].split("*")[0]).split("&")[3];
	    	var jobList = (order[i].split("*")[1]).split("~");
	 
	    	
	    	for(var j = 0;j < jobList.length; j++) {
	    		
	    		var dutyId = (jobList[j].split("%")[0]).split("&")[0];    //DBD_DUTY_MODE_ORDER_JOB_ID
	    		var jobName = (jobList[j].split("%")[0]).split("&")[1];
	    		var dutyPoliceData = (jobList[j].split("%")[1]).split("@");
	    		var item = "<tr>";
	    		
				if(j == 0) {
					item = item +"<td rowspan='"+jobList.length+"' id= '"+orderID+"' align='center' style='padding:10px 20px 15px 20px; width:auto; height:auto; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;'><span style='font-size:15px;font-weight: bold;'>" +orderName+ "</span><br>(" +orderSTime+ "~" +orderETime+ ")</td>";

				} 
				item = item + "<td  align='center'  id= '"+dutyId+"' style='font-size:15px;padding:10px 20px 15px 20px; width:80px; height:80px; font-weight: bold; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' >" +jobName+ "</td>";

				var tdPeople = "";
				for(var k = 0; k < dateDiff+1; k++) {
					tdPeople = tdPeople + "<td class='jobTd' id='td"+dutyId+"_"+k+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' ><ul id='" +dutyId+ "_" +k+ "'></ul></td>"	

				}
				item = item + tdPeople;
				item = item + "</tr>";
					
				$("#dutyform").append(item);
				
				for(var k = 0; k < dateDiff+1; k++) {
					
					if(dutyPoliceData[k].split("/")[0].indexOf(",") != -1) {
						var policeIds = (dutyPoliceData[k].split("/")[0]).split(",");  //policeId
					    var policeName =(dutyPoliceData[k].split("/")[1]).split(",");
						
					    for(var l = 0; l < policeIds.length; l++){
						    var li = "<li id='id_p" +dutyId+ "_" +k+ "_" +policeIds[l]+ "' style='font-size:14px;margin:8px'><span>" +policeName[l]+ "</span></li>"
						    $("#td" +dutyId+ "_" +k+ " ul").append(li);         	
						}
					} else {
						var policeIds = dutyPoliceData[k].split("/")[0];  //policeId
					    var policeName = dutyPoliceData[k].split("/")[1];
						var li ="<li id='id_p" +dutyId+ "_" +k+ "_" +policeIds+ "' style='font-size:14px;margin:8px'><span>" +policeName+ "</span></li>"
						$("#td" +dutyId+ "_" +k+ " ul").append(li);         	
					}
				}
			} 
    	 } */
	    $("#dutyList_view").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true,
			axis: "xy"
		});

	    if(data.noDuty !="" && data.noDuty !=undefined){
            var noDutyRy =data.noDuty.split(";");
            var p= "<p>本次未参与值班人员</p>";
            for (var g = 0; g < noDutyRy.length-1; g++) {
                p +="<p>"+(g+1)+"、"+noDutyRy[g]+"</p>"
            }
            $("#noDuty").append(p);
        }

   	 }			

</script>