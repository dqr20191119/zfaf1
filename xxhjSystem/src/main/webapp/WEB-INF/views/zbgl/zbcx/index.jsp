<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	String cusNumber = user.getCusNumber();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style type="text/css">

#todayDutyDefault table {
border-bottom-width: 1px;
border-bottom-style: solid;
border-bottom-color: #F5FAFA;
width: 100%;
height: 100%;
}

#todayDutyDefault table .title span {
padding: 5px 10px;
font-size: 12px;
font-family: Verdana;
font-weight: bold;
}

#todayDutyDefault table tr:nth-child(even) {
background: #F5FAFA
}
#todayDutyDefault table tr:nth-child(odd) {
background: #FFF
}
</style>

<div id="div_zbxc" style="height: auto;width:auto; margin:3px 5px 3px 8px">	
	<table>
		<tr>
			<td>
				<cui:form id="formId_zbcx">
					<fieldset>
						<table id="table_one" class="table" style ="width:958px;height:auto; display: none">
							<tr>
								<td>当前日期：&nbsp;&nbsp;&nbsp;&nbsp;
								<cui:datepicker id ="dutyDate"  name ="dutyDate" required= "true" dateFormat="yyyy-MM-dd"></cui:datepicker>&nbsp;&nbsp;&nbsp;&nbsp;
								<%-- <td class="pbdPrsn_span">监狱名称：
								<cui:combobox id="pbdPrsn" data ="combobox_jy"></cui:combobox>			
								</td> 
								<td>--%>
								<cui:button label="查询" componentCls="btn-primary" onClick="queryDayDuty(true)"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0);" onclick="initQueryType(1);">其他方式查询</a>
								</td>
							</tr>
						</table>
						<table id="table_two" class="table" style=" width:958px; height:auto; display: none">
							<tr>
								<th>查询方式：</th>
								<td><cui:combobox id="comboboxId_queryType" name= "queryType" required= "true" emptyText="请选择" data ="comboboxData_queryType" onChange="onChangeQueryType"></cui:combobox></td>
								<th>值班类别：</th>
								<td><cui:combobox id="comboboxId_zbcx_categoryName" required= "true" name="categoryId" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" onChange="initDrpmntList"></cui:combobox></td>
								<th>部门名称：</th>
								<td><cui:combobox id="comboboxId_drpmntName"  required= "true" name="dmdDprtmntId" onChange="onChangeDrpmnt"></cui:combobox></td>
							</tr>
							<tr class="tr_one">	
								<th>值班日期：</th>
								<td><cui:combobox id="comboboxId_dutyDate" required="true"></cui:combobox></td>
								
								<td colspan="2"> 
									&nbsp;
					 				<cui:button label="确定"  componentCls="btn-primary" onClick="ensure(1)"/>				
									<cui:button label="重置"  onClick="reset"></cui:button>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="initQueryType(0);">返回今日值班</a>
								</td>
							</tr>
							<tr class ="tr_two">
								<th>值班班次：</th>
								<td><cui:combobox id="comboboxId_dutyOrder" name= "mojOrderId" url="${ctx}/zbgl/bcgl/searchAllData"></cui:combobox></td>
								<th>值班岗位：</th>
								<td><cui:combobox id="comboboxId_dutyPost" name= "mojJobId"></cui:combobox></td>
								<th>值班人员：</th>
								<td><cui:combobox id="comboboxId_dutyPolice" name= "dutyPolice" ></cui:combobox></td>
							</tr>
							<tr class="tr_two">
								<th>开始日期：</th>
								<td><cui:datepicker id ="startDate"  name ="startDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
								</td>
								<th>结束日期：</th>
								<td><cui:datepicker id ="endDate"  name ="endDate" dateFormat="yyyy-MM-dd"></cui:datepicker>
								</td>
								<td width="300" colspan="2" align="center">
									<cui:button label="确定"  componentCls="btn-primary" onClick="ensure(2)"/>				
									<cui:button label="重置"  onClick="reset"></cui:button>
									<cui:button label="导出"  onClick="downLoad"></cui:button>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="initQueryType(0);">返回今日值班</a>
								</td>
							</tr>
						</table>
					</fieldset>
				</cui:form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="todayDuty" class="table" style ="width:100%;height:100%;display: none">
					<tr>
						<td>
							<div id="todayDutyDefault" style="width:100%; height:100%;">
								 
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id ="div_dutyModelTable" style="width:955px; height:550px; overflow: auto; margin:5px 5px 0px 10px;border: #B3D0F4 2px solid;display: none">
					<table border="2"  id="dutyModelTable" style="height: auto; width: auto;border-color:#4592F0">
					
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div id ="div_gridId_zbcx" style="width:970px; height:540px; border: #B3D0F4 2px solid;display:none;">
					<cui:grid id="gridId_zbcx" rownumbers="true"  height="540" width="auto" fitStyle="fill" rowNum="15">
						<cui:gridCols>
							<cui:gridCol name="DBD_STAFF_ID" align="center">警号</cui:gridCol>
							<cui:gridCol name="DBD_STAFF_NAME" align="center">姓名</cui:gridCol>
							<cui:gridCol name="DBD_DUTY_DATE" align="center">值班日期</cui:gridCol>
							<cui:gridCol name="DBD_DUTY_DATE" align="center" formatter="formatWeek" revertCode="true" formatoptions="{
							'data': combobox_bm
							}">星期</cui:gridCol>
							<cui:gridCol name="DMD_DPRTMNT_ID" align="center" formatter="convertCode" revertCode="true" formatoptions="{
							'data': combobox_bm
							}">部门</cui:gridCol>
							<cui:gridCol name="DOR_DUTY_ORDER_NAME" align="center">班次</cui:gridCol>
							<cui:gridCol name="CDJ_JOB_NAME" align="center">岗位</cui:gridCol>
							<cui:gridCol name="DUTY_DATE" align="center">值班时间</cui:gridCol>
						</cui:gridCols>
						<cui:gridPager gridId="gridId_zbcx" />
					</cui:grid>
				</div>
			</td>
		</tr>
	</table>
</div>

<cui:dialog id="dialogId_staff_list" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>

<script>
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var jsConst = window.top.jsConst;
	var cusNumber = <%=cusNumber%>;
	
	$.parseDone(function() {
		initDayDuty();
	})
	
	function initDayDuty() {
		
		$("#table_two").hide();
		$("#table_one").show();
		$("#todayDuty").show();
		
		$("#dutyDate").datepicker("setDate",new Date());
		queryDayDuty();
	}
	
	/**
	 * 判断是否为空值
	 * 
	 * @param _v
	 *            待验证的值
	 */
	function isEmpty(_v) {
		if (_v == undefined)
			return true;
		if (_v == null)
			return true;
		if (_v == "undefined")
			return true;
		if (_v == "")
			return true;
		return false;
	}
	
	function queryDayDuty() {
		var date = $("#dutyDate").datepicker("getText");
		if(isEmpty(date)){
			$.messageQueue({ message : "请选择日期！", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		$("#div_zbxc").loading({ text : "正在请求 "+date+" 值班信息，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/zbcx/queryDutyCount.json',
			data : {
				cusNumber: cusNumber,
				date: date	
			},
			dataType : 'json',
			success : function(data) {
				 
				 var $divTemp = $("#todayDutyDefault");
				 $divTemp.empty();
				 debugger;
				    if(data.sjList !=null && data.sjList!=undefined){
                        var sjList = data.sjList;//省局
                        if(sjList.length > 0){
                            creatTable("省局", sjList, $divTemp, date);
                        }
                    }

                    if(data.jqList !=null && data.jqList!=undefined){
                        var jqList = data.jqList;//监区
                        if(jqList.length > 0){
                            creatTable("监区", jqList, $divTemp, date);
                        }
                    }

                    if(data.ksList !=null && data.ksList!=undefined){
                        var ksList = data.ksList;//科室
                        if(ksList.length > 0){
                            creatTable("科室", ksList, $divTemp, date);
                        }
                        if(ksList.length == 0 && jqList.length == 0 && sjList.length == 0){
                            $divTemp.append("<span>暂无数据</span>");
                        }
                    }


				 
				 $("#div_zbxc").loading("hide");
				 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	function creatTable(fieldsetName, list, $div, date) {
		 var $fieldsetTemp = $("<fieldset><legend>" + fieldsetName + "</legend></fieldset>");
		 var $tableTemp = $("<table class='table' ></table>");
		 var dayCount = 0;
		 var nightCount = 0;
		 var allDayCount = 0;
		 for (var i = 0; i < list.length; i++) {
			 //{NIGHT_COUNT=0, ALL_COUNT=2, ALLDAY_COUNT=0, CUS_NUMBER=6506, DAY_COUNT=2, DPRTMNT_NAME=监狱领导, DPRTMNT_ID=650601}
			 var $trTemp = $("<tr></tr>");
             $trTemp.append("<td class='title'><span>"+ list[i].DPRTMNT_NAME +"</span></td>");
             
             $trTemp.append("<td class='title'><span>白班</span></td>");
             var $aTemp_1 = $('<a href="javascript:void(0);" onclick="queryStaff(\''+list[i].CUS_NUMBER + '\',\'' + list[i].DPRTMNT_ID + '\',\'' + date + '\',\'1\');"></a>');
             $aTemp_1.append("<span>" + list[i].DAY_COUNT + "<span>");
             var $tdTemp_1 = $("<td>&nbsp;人</td>");
             $tdTemp_1.prepend($aTemp_1);
             $trTemp.append($tdTemp_1);
             dayCount = dayCount + parseInt(list[i].DAY_COUNT);//白班在岗警力统计
             
             
             $trTemp.append("<td class='title'><span>夜班</span></td>");
             var $aTemp_2 = $('<a href="javascript:void(0);" onclick="queryStaff(\''+list[i].CUS_NUMBER + '\',\'' + list[i].DPRTMNT_ID + '\',\'' + date + '\',\'2\');"></a>');
             $aTemp_2.append("<span>" + list[i].NIGHT_COUNT + "<span>");
             var $tdTemp_2 = $("<td>&nbsp;人</td>");
             $tdTemp_2.prepend($aTemp_2);
             $trTemp.append($tdTemp_2);
             nightCount = nightCount + parseInt(list[i].NIGHT_COUNT);//夜班在岗警力统计
             
             $trTemp.append("<td class='title'><span>全班</span></td>");
             var $aTemp_3 = $('<a href="javascript:void(0);" onclick="queryStaff(\''+list[i].CUS_NUMBER + '\',\'' + list[i].DPRTMNT_ID + '\',\'' + date + '\',\'3\');"></a>');
             $aTemp_3.append("<span>" + list[i].ALLDAY_COUNT + "<span>");
             var $tdTemp_3 = $("<td>&nbsp;人</td>");
             $tdTemp_3.prepend($aTemp_3);
             $trTemp.append($tdTemp_3);
             allDayCount = allDayCount + parseInt(list[i].ALLDAY_COUNT);//全班在岗警力统计
             
             $trTemp.appendTo($tableTemp);	
		 }
		 $trTemp_1 = $("<tr></tr>");
		 $trTemp_1.append("<td colspan=\"3\" class='title'><span>今日白班在岗警力</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + (dayCount + allDayCount) +"&nbsp;人</td>");
		 $trTemp_1.append("<td colspan=\"4\" class='title'><span>今日夜班在岗警力</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + (nightCount + allDayCount) +"&nbsp;人</td>");
		 $trTemp_1.appendTo($tableTemp);	
		 
		 $fieldsetTemp.append($tableTemp);
		 $div.append($fieldsetTemp);
	}
	
	
	function queryStaff(cusNumber_,dprtmntId_,date_,type_) {
		 $("#dialogId_staff_list").dialog({
				width : 800,
				height : 550,
				title : '值班人员信息',
				modal : true,
				autoOpen : false,
				url : "${ctx}/zbgl/zbcx/toStaffList?cusNumber=" + cusNumber_ + "&dprtmntId=" + dprtmntId_ + "&date=" + date_ +"&type=" + type_,
			});
			
			$("#dialogId_staff_list").dialog("open"); 
	}
	
	function initQueryType(param) {
		
		if(param == "1") {       /* 选择其他查询 */
			reset();
			$("#table_one").hide();
			$("#table_two").show();
			$(".tr_one").show();
			$(".tr_two").hide();
			$("#todayDuty").hide();
			
		} else if(param == "0") { /* 返回今日值班 */
			$("#div_dutyModelTable").hide();
			$("#div_gridId_zbcx").hide();
			initDayDuty();
		}
	}	
	
	function onChangeQueryType() {
		
		$("#dutytable").hide();
		var queryType = $("#comboboxId_queryType").combobox("getValue");
		
		if(queryType == "1") {
			
			$(".tr_one").show();
			$(".tr_two").hide();
			$("#gridId_zbcx").hide();
			$("#div_gridId_zbcx").hide();
			
		}else if(queryType == "2") {
			
			$(".tr_one").hide();
			$(".tr_two").show();
			$("#dutyModelTable").empty();
			$("#div_dutyModelTable").hide();
			
		}
	}
	
	function ensure(param) {
		
		var validFlag  = $("#formId_zbcx").form("valid");
		if(!validFlag) {
			return;
		}
		
		$("#dutytable").show();
		var queryType = $("#comboboxId_queryType").combobox("getValue");
		
		if(queryType == "1") {
			
			var id = $("#comboboxId_dutyDate").combobox("getValue");       //部门模板表主键
			searchModeId(id);
			$("#gridId_zbcx").hide();
			
		    $("#dutyModelTable").show();
		    $("#div_dutyModelTable").show();
		    
		} else if(queryType == "2") {
			
			$("#dutyModelTable").empty();
			$("#dutyModelTable").hide();
			$("#div_dutyModelTable").hide();
			$("#div_gridId_zbcx").show();
			$("#gridId_zbcx").show();
			
			loadGrid();
		
		}
	}
	
	function reset() {
		$("#formId_zbcx").form("clear");
	}
	
	function loadGrid() {
		
		var formData = $("#formId_zbcx").form("formData");
		formData["cusNumber"] = cusNumber;
		formData["categoryId"] = '';
		$("#gridId_zbcx").grid("option", "postData", formData);
		$("#gridId_zbcx").grid("reload","${ctx}/zbgl/zbcx/searchData"); 
	}
	
	var comboboxData_queryType = [
		{
     		 value : "1",
     		 text : "值班表查询",
     		"selected" : true
     	 },{
     		value : "2",
     		text : "列表查询"
     	 }]
	
	function initDrpmntList() {
		
		var selectedCategory = $("#comboboxId_zbcx_categoryName").combobox("getValue");
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
	
	
	function onChangeDrpmnt() {
		 
		var selectedDrpmnt = $("#comboboxId_drpmntName").combobox("getValue");
		$("#comboboxId_dutyPolice").combobox("reload","${ctx}/common/authsystem/findPoliceByDeptIdForCombobox?cusNumber="+cusNumber+"&deptId="+selectedDrpmnt);
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbbm/searchAllData',
			data: {
				dmdDprtmntId : selectedDrpmnt,
				param : "1",
			},
			dataType : 'json',
			success : function(data) {
				
				$("#comboboxId_dutyDate").combobox("loadData",data);
				$('#comboboxId_dutyDate').combobox("setValue",data[0].value); 
			}
		})
	}
	/* 查询模板主键 */
	function searchModeId(id) {
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbbm/getById',
			data: {
				id : id,              //模板部门表主键
			},
			dataType : 'json',
			success : function(data) {
				initModeData(id,data.dmdModeId);
			}
		})
	} 
	/* 查询模板数据 */
	function initModeData(id,modelId) {
		 
		$("#dutyModelTable").empty();
			
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/getById',
			data: {
				id : id,              //模板部门表主键
				modelId : modelId,
				param : "3",
			},
			dataType : 'json',
			success : function(data) {
				parseDutyTable(data);
			}
		});
	}
	
	/* 重现表格数据 */
	function parseDutyTable(data) {
		
		var SDate = data.dmdStartDate;
		var EDate = data.dmdEndDate;
		var order = (data.cdmOrderData).split(";");
		var dateDiff = getDateDiff(SDate,EDate);
	    var title = "<tr style='background-color:#337ab7;color:white;font-size:15px;font-weight: bold'><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'> 班次</td><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px;word-break: keep-all;white-space:nowrap;'>岗位</td>"

	    for(var i = 0;i < dateDiff+1; i++) {
	    	
	    	var date = new Date(Date.parse(SDate)); 
	    	var tDate = addDate("d",date,i);
	    	
	    	var ssdate = formatterDate(tDate,"ymd")
	    
			var week = getWeek(tDate);
	    	title = title+"<td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px; word-break: keep-all;white-space:nowrap;' id='title_"+i+"' >"+ssdate+"<br>"+week+"</td>";

	    }
	    
	    title = title+ "</tr>";
	    $("#dutyModelTable").append(title); 
	   
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
					tdPeople = tdPeople + "<td id='td"+dutyId+"_"+k+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' ><ul id='" +dutyId+ "_" +k+ "'></ul></td>"	

				}
				item = item + tdPeople;
				item = item + "</tr>";
					
				$("#dutyModelTable").append(item);
				
				for(var k = 0; k < dateDiff+1; k++) {
					
					if(dutyPoliceData[k].split("/")[0].indexOf(",") != -1) {
						var policeIds = (dutyPoliceData[k].split("/")[0]).split(",");  //policeId
					    var policeName =(dutyPoliceData[k].split("/")[1]).split(",");
						
					    for(var l = 0; l < policeIds.length; l++) {
						    var li = "<li id='id_p" +dutyId+ "_" +k+ "_" +policeIds[l]+ "' style='font-size:14px;margin:8px'>" +policeName[l]+ "</li>"
						    $("#td" +dutyId+ "_" +k+ " ul").append(li);         	
						}
					} else {
						var policeIds = dutyPoliceData[k].split("/")[0];  //policeId
					    var policeName =dutyPoliceData[k].split("/")[1];
						var li ="<li id='id_p" +dutyId+ "_" +k+ "_" +policeIds+ "' style='font-size:14px;margin:8px'>" +policeName+ "</li>"
						$("#td" +dutyId+ "_" +k+ " ul").append(li);         	
					}
				}
			} 
    	 }
   	 }
	/* 日期格式转换 */
	function formatWeek(cellvalue, options, rowObject) {
			
			var date = new Date(Date.parse(rowObject.DBD_DUTY_DATE));
			var value = getWeek(date);
			return value; 
		}	
</script>