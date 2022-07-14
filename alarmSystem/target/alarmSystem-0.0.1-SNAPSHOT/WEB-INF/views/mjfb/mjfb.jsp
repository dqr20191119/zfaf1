<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	.rightTable {
		text-align: center;
		font-size: 13px;
		border-collapse: collapse;
		border: solid #4592f0;
		border-width: 1px;
		margin-top: 10px;
		margin-left: 5px;
	}
	
	.rightTable td {
		border: solid #4592f0;
		border-width: 1px;
		height: 30px;
		text-align:center;
	} 

	
</style>

<div id="rightSide_jnmj" class="scorllBar">
	<div class="rightDivStyle right-zb" id="prisonCount">
		<h4 align="center">民警数量统计</h4>
			<h5 align="right"><a href="javascript:void(0);" id="callBack" onclick="callBack();">返回>></a></h5>
		<p align="center" style="font-size: 18px;">
			${areaName }当前民警：
			<span class="sp" id="insideCount"></span> 人
		</p>
	</div>

	<%-- <div class="rightDivStyle right-zb" id="policeCount">
		<h4 align="center">${areaName }民警数量分布</h4>
		<center>
			<div class="overflow_div">
				<table id="left"></table>
				<table id="right"></table>
			</div>
		</center>
	</div> --%>

	<div class="rightDivStyle right-zb" id="policeList">
		<h4 align="center">${areaName }民警信息记录</h4>
			
			<!-- <h5 align="right"><a href="javascript:void(0);" onClick="toPoliceList();">更多>></a></h5> -->
			
			<table class="rightTable" id="policeListTab">		
			</table>
			<div class="notData" style="display: none;" align="center" >暂无数据!</div>
			<cui:form id="formId_policeInfoTab">
		 	<table class="rightTable"  id="policeInfoTab" style="display: none;" onmouseleave="idOneToIdTwo('policeInfoTab', 'policeListTab');">
				<tr>
					<td width="100" rowspan="4"><img src="" id="pbdImgDir" width="100" height="130"></td>
					<td class="title" width="58" >警号：</td>
					<td width="110" class="two" id="pbdPoliceIdnty" name="DRD_PEOPLE_IDNTY"></td>
				</tr>
				<tr>
					<td class="title" >姓名：</td>
					<td class="two" id="DRD_PEOPLE_NAME" name ="DRD_PEOPLE_NAME"></td>
				</tr>
				<tr>
					<td class="title" >性别：</td>
					<td class="two"  id="pbdSex"  name="PBD_SEX" ></td>
				</tr>
				<tr>
					<td class="title">部门：</td>
					<td class="two" id="pbdDrptmnt" name ="PBD_DRPTMNT"></td>
				</tr>
				<tr>
					<td class="title">警务通号：</td>
					<td colspan="2" id="pbdPoliceCmmnct"  name ="PBD_POLICE_CMMNCT"></td>
				</tr>
				<tr>
					<td class="title">监狱短号：</td>
					<td colspan="2" id="pbdShortPhone" name ="PBD_SHORT_PHONE"></td>
				</tr>
				<tr>
					<td class="title">对讲呼号：</td>
					<td colspan="2" class="two"  id="pbdTalkNum" name ="PBD_TALK_NUM"></td>
				</tr>
				<tr>
					<td class="title">固定电话：</td>
					<td colspan="2" class="two" id="pbdFixedPhone" name ="PBD_FIXED_PHONE"></td>
				</tr>
				<tr>
					<td class="title">手机号码：</td>
					<td colspan="2" class="two"  id="pbdPhone"  name ="PBD_PHONE"></td>
				</tr>
			</table>
			</cui:form>
	</div>
</div>	

<input type="hidden" id="drptmnt" value=''/> 

<cui:dialog id="dialogId_policeInPrison" autoOpen="false" reLoadOnOpen="true" iframePanel="true" resizable="false"></cui:dialog>

<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL; 
	var thisCusNumber =jsConst.CUS_NUMBER; 
	var prisonId = '${prisonId}';                //  省局传来监狱编号
	var prisonName = '${prisonName}';            //  省局传来监狱名称
	
	
	var cusNumber = '${cusNumber}';
	var areaId = '${areaId}';
	var areaName = '${areaName}';
	var areaLevel = '${areaLevel}';
	
	var buildingNum="";
	var floorNum="";
	var departmentId ="";
	//性别
    var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
    
    
	$.parseDone(function() {
		
		$("#rightSide_jnmj").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		switch(areaLevel)
		{
		    case '1':
		    	floorNum="";
		    	buildingNum=areaId;
		        break;
		    case '2':
		    	buildingNum="";
		    	floorNum=areaId;
		        break;
		    case '3':
		    	buildingNum="";
		    	floorNum="";
		        break;
		    default:
		      console.log("其它");
		}
		
		if(USER_LEVEL==1) {
			$("#callBack").show();
		} else{
			$("#callBack").hide();
 		} 
		
		// 查询实时民警数
		queryPoliceCount();
		
		// 按监区列出在监民警数量
		//loadDrptmnt();	
		
		queryPoliceList();
	});
	
	function queryPoliceCount() {	
		var url = "${ctx}/common/all/mjfbCount";
		$.ajax({
			type : "post",
			url : url,
			data : {
				"cusNumber":cusNumber,
				"buildingNum":buildingNum,
				"floorNum":floorNum,
				"departmentId":departmentId,
				"drdPeopleTypeIndc":"1"
			},
			dataType : "json",
			success : function(data) {		
				var count = data.COUNT;
				$("#insideCount").html(count);
			}
		});
	}
    
	function loadDrptmnt() {

		var url = "${ctx}/xxhj/jnmj/queryPrisonDepartment?cusNumber=" + cusNumber;	
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if(data != "" && data != null) {					
					var leftTbl = $("#left");
					var rightTbl = $("#right");
					
					leftTbl.empty();
					rightTbl.empty();
					
				    // 存入默认的第一监区ID
					$("#drptmnt").val(data[0].orgKey);
					
					var j = 0;
					for (var i = 0; i < data.length; i++) {	
						
						var drptmntId = data[i].orgKey;
						var drptmntName = data[i].orgName;
						var tr = $("<tr></tr>");
						var number = drptmntName.indexOf("、");
						if(number!=-1){
							drptmntName = insert_flg(drptmntName,'<br>',number);
						}
						if (j % 2 == 0) {	
							
							tr.append("<td width='50' align='right'><span title='" + data[i].orgName + "'>"+ drptmntName+ "： </span></td><td width='35' align='left'><span class='spTwo' id='dpmt_" + drptmntId + "'>0</span> 人</td>");
							leftTbl.append(tr);
						} else {							
							tr.append("<td width='50' align='right'><span title='" + drptmntName + "'>"+ drptmntName+ "： </span></td><td width='35' align='left'><span class='spTwo' id='dpmt_" + drptmntId + "'>0</span> 人</td>");
							rightTbl.append(tr);
						}
						
						j++;
					}
					var tr = $("<tr></tr>");
					
					if(j % 2 == 0) {
						
						tr.append("<td width='50' align='right'>其他：</td><td width='35' align='left'><span class='spTwo' id='other'>0</span> 人</td>");           //  其他的ID为other
						leftTbl.append(tr);
					} else {						
						tr.append("<td width='50' align='right'>其他：</td><td width='35' align='left'><span class='spTwo' id='other'>0</span> 人</td>");
						rightTbl.append(tr);
					}
					
					
					queryPoliceDrptmntCount();
				} else {
				}
			}
		});
	}
	
	//插入函数
	function insert_flg(str,flg,sn){
	    var newstr="";
	        var tmp=str.substring(0, sn);
	        newstr+=tmp+flg;
	        tmp=str.substring(sn+1, str.length);
	        newstr+=tmp;
	    return newstr;
	}
	
	function queryPoliceDrptmntCount() {
		
		var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceCount?cusNumber="+ cusNumber;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if(data != "" && data != null) {
					parsePoliceDrptmntCount(data);
				}
			}
		});
	}
	
	function parsePoliceDrptmntCount(countList) {
		
		var otherNum = 0;	
		for(var i = 0; i < countList.length; i++) {
			
			var drptmntId = countList[i].PIR_DPRTMNT_ID;
			var count = countList[i].COUNT;
			var flag = false;
			var drptmntSpan = $("#dpmt_" + drptmntId);
	
			if(drptmntSpan.length > 0) {				
				drptmntSpan.empty();
				
				if (count == 0) {					
					drptmntSpan.html(count);
				} else {					
					drptmntSpan.html("<a href='javascript:void(0);' onClick='queryPoliceList(" + drptmntId + ",this);'>" + count+ "</a>");
				}
			} else {
				otherNum += count;
			}
		}
		
		$("#other").empty();
		if(otherNum == 0) {			
			$("#other").html(otherNum);
		} else {	
			$("#other").append("<a href='javascript:void(0);' onclick='queryPoliceList(\"other\", this);'>"+ otherNum + "</a>");	
		}
	
		var obj = $("#policeCount #left").children().eq(0).children().eq(0)
				.children().eq(1).find("span").children("a");
		
 		var objValue = $("#policeCount #left").children().eq(0).children().eq(0)
				.children().eq(1).find("span").text();
	
 		if(objValue != 0) {	
			obj.click();
		} else {			
			$("#policeListTab").empty();
			var policeListTab = $("#policeListTab");
			var tr = $("<tr class='trHead'></tr>");
			tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>警号</td>");
			tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>姓名</td>");
			
			policeListTab.append(tr);
			$(".notData").show();
		}
	}

	function queryPoliceList(drptmnt, aId) {
		
		$("#drptmnt").val(drptmnt);
		drptmnt = drptmnt == "other" ? cusNumber : drptmnt;
		
		var para = drptmnt != cusNumber ? "0" : "1";			
		/* var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceInfo?para=" + para + "&cusNumber=" + cusNumber + "&drptmntId="
				+ drptmnt + "&cusNumber=" + cusNumber; */
		var url = "${ctx}/common/all/mjfbList";
		$.ajax({
			type : "post",
			url : url,
			data : {
				"cusNumber":cusNumber,
				"buildingNum":buildingNum,
				"floorNum":floorNum,
				"departmentId":departmentId,
				"drdPeopleTypeIndc":"1"
			},
			dataType : "json",
			success : function(data) {

				if(data != "" && data != null) {
					parsePoliceList(data);
					$(".notData").hide();
				} else {				
					$("#policeListTab").empty();
					var policeListTab = $("#policeListTab");
					var tr = $("<tr class='trHead'></tr>");
					tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>警号</td>");
					tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>姓名</td>");
					
					policeListTab.append(tr);
					$(".notData").show();
				}
			}
		});
	}
	
	function parsePoliceList(array) {
		$("#policeListTab").empty();
		
		var policeListTab = $("#policeListTab");
		var tr = $("<tr class='trHead'></tr>");
		tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>警号</td>");
		tr.append("<td width='150' style='font-size:15px;font-weight:bold;'>姓名</td>");	
		policeListTab.append(tr);

		for (var i = 0; i < array.length; i++) {			
			if (array[i].DRD_PEOPLE_IDNTY != null && array[i].DRD_PEOPLE_NAME != null) {				
				tr = $("<tr></tr>");
				tr.append("<td >" + array[i].DRD_PEOPLE_IDNTY + "</td>");
				tr.append("<td ><a href='javascript:void(0);'  onClick='parsePoliceInfo("
								+ JSON.stringify(array[i])+ ");'>"+ array[i].DRD_PEOPLE_NAME + "</a></td>");
				
				/* tr.append("<td><a href='javascript:void(0);' onclick='jnmjPoliceInoutRecord("
								+ array[i].DRD_PEOPLE_IDNTY+ ",this.id);' id='one_" + i + "'>查看</a></td>"); */
				policeListTab.append(tr);
			}
		}
	}
	
	function parsePoliceInfo(data) {

		$("#policeListTab").hide();
		$("#policeInfoTab").show();
		
		if(data != null && data != "") {
			var policeId = "'" + data.DRD_PEOPLE_IDNTY + "'";
		
			$("#pbdPoliceIdnty").html(data.DRD_PEOPLE_IDNTY);
			$("#DRD_PEOPLE_NAME").html(data.DRD_PEOPLE_NAME);
			$("#pbdSex").html(getSex(data.PBD_SEX.replace(/\s+/g,"")));
			$("#pbdDrptmnt").html(data.PBD_DRPTMNT);
			$("#pbdPstnName").html(data.PBD_PSTN_NAME);
			$("#pbdPoliceCmmnct").html(data.PBD_POLICE_CMMNCT);
			$("#pbdFixedPhone").html(data.PBD_FIXED_PHONE);
			$("#pbdShortPhone").html(data.PBD_SHORT_PHONE);
			$("#pbdTalkNum").html(data.PBD_TALK_NUM);
			$("#pbdPhone").html(data.PBD_PHONE);  
		}
	}
	
	function getSex(PBD_SEX){
		var sex='';
		switch(PBD_SEX) {
			
			case '0':
				sex = "未知的性别";
				break;
			case '1':
				sex = "男性";
				break;
			case '2':
				sex = "女性";
				break;
			case '9':
				sex = "未说明的性别";
				break;
	};				
		return sex;
	}
	
 	
	function callBack() {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	panel.panel("refresh", "${ctx}/xxhj/jnmj/pjnmj"); 	
    }
	
	function idOneToIdTwo(idOne, idTwo) {
		
		$("#" + idOne + "").hide();
		$("#" + idTwo + "").show();
	}
	
	function toPoliceList() {

		var drptmntId = $("#drptmnt").val();	
		$("#dialogId_policeInPrison").dialog({
			width : 1000, 
			height : 600, 
			subTitle : '民警信息 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?drptmntId=" + drptmntId + "&cusNumber=" + cusNumber
		});
		
		$("#dialogId_policeInPrison").dialog("open");
	}
	
	function jnmjPoliceInoutRecord(policeId, aId) {
		
		$("#dialog").dialog({
			width : 1000, 
			height : 600, 
			subTitle : '民警进出记录 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInoutRecord?policeId=" + policeId + "&cusNumber=" + cusNumber
		});
		
		$("#dialog").dialog("open");
	}
</script>