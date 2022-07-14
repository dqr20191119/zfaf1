<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="rightSide_jnmj" class="scorllBar">
	<div class="rightDivStyle right-zb" id="prisonCount">
		<h4 align="center">
			当前监内民警人数:
			<span class="sp" id="insideCount"></span> <span class="sp">人</span>
		</h4>
	</div>
	<!-- <div class="rightDivStyle right-zb" id="prisonCount">
		<h4 align="center">监内民警数量统计</h4>
		
		<p align="center" style="font-size: 18px;">
			当前监内民警：
			<span class="sp" id="insideCount"></span> 人
		</p>
	</div> -->

	<div class="rightDivStyle right-zb" id="policeList">
		<h4 align="center">监内民警信息记录<a href="javascript:void(0);" onClick="toPoliceList();">More</a></h4>
		<div class="info_list" id="policeListTab">
			<dl>
				<dd>警号</dd>
				<dd>姓名</dd>
				<dd>进出记录</dd>
			</dl>
			<div id="maquee" class="maquee"></div>
		</div>
		<div class="notData" style="display: none;" align="center" >暂无数据!</div>
	 	<table class="rightTable" id="policeInfoTab" style="display: none; overflow: auto;" onmouseleave="idOneToIdTwo('policeInfoTab', 'policeListTab');">
			<tr>
				<td width="90" rowspan="4"><img src="" id="pbdImgDir" width="90" height="130"></td>
				<td width="58" >警号:</td>
				<td width="120" id="pbdPoliceIdnty" name="PBD_POLICE_IDNTY"></td>
			</tr>
			<tr>
				<td width="58">姓名:</td>
				<td width="120" id="pbd_police_name" name ="PBD_POLICE_NAME"></td>
			</tr>
			<tr>
				<td width="58">性别:</td>
				<td width="120" id="pbdSex"  name="PBD_SEX"></td>
			</tr>
			<tr>
				<td>部门:</td>
				<td id="pbdDrptmnt" name ="PBD_DRPTMNT"></td>
			</tr>
			<tr>
				<td>警务通号:</td>
				<td colspan="2" id="pbdPoliceCmmnct"  name ="PBD_POLICE_CMMNCT"></td>
			</tr>
			<tr>
				<td>监狱短号:</td>
				<td colspan="2" id="pbdShortPhone" name ="PBD_SHORT_PHONE"></td>
			</tr>
			<tr>
				<td width="58">对讲呼号:</td>
				<td colspan="2" id="pbdTalkNum" name ="PBD_TALK_NUM"></td>
			</tr>
			<tr>
				<td width="58">固定电话:</td>
				<td colspan="2" id="pbdFixedPhone" name ="PBD_FIXED_PHONE"></td>
			</tr>
			<tr>
				<td width="58">手机号码:</td>
				<td colspan="2" id="pbdPhone"  name ="PBD_PHONE"></td>
			</tr>
		</table>
	</div>
</div>	

<input type="hidden" id="drptmnt" value=''/> 

<cui:dialog id="dialogId_policeInPrison" autoOpen="false" reLoadOnOpen="true" iframePanel="true" resizable="false"></cui:dialog>

<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL;  
	var cusNumber = jsConst.CUS_NUMBER;  
	var drpmntId = jsConst.DEPARTMENT_ID; 
	var config; 
	//性别
    var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
    
	$.parseDone(function() {
		
		$("#rightSide_jnmj").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		//查询监狱种类
		queryDutyConfig();
		
		// 查询在监民警总人数
		queryPoliceCount();
		$("#drptmnt").val(drpmntId);
		
	});
	
	function queryDutyConfig() {
		
		var url = "${ctx}/xxhj/jnmj/queryDutyConfig?cusNumber=" + cusNumber;
		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			success : function(data) {
				// 查询在监民警总人数
				if(data.length > 0) {
					if(data.FLAG){
						config = data.FLAG;
					} else{
						config = "";
					}
				} else {
					config = "";
				}
				queryPoliceCount();
			},
			error : function(data) {
				if(data.length > 0) {
					if(data.FLAG){
						config = data.FLAG;
					} else{
						config = "";
					}
				} else {
					config = "";
				}
				queryPoliceCount();
			}
		})
	}
	
	function queryPoliceCount() {
		
		//var url = "${ctx}/xxhj/jnmj/queryInsidePoliceCount?cusNumber=" + cusNumber+"&drpmntId="+drpmntId;
		var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceCount?cusNumber=" +cusNumber+ "&drpmntId=" +drpmntId+ "&config=" +config;
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if(data) {					
					if(data != "" && data != null) {						
						var count = data[0].COUNT;
						$("#insideCount").html(count);
						queryPoliceList();
					}
				}
			}
		});
	}
	
	function queryPoliceList() {
					
		var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceInfo?config=" +config+ "&para=0&cusNumber=" + cusNumber + "&drptmntId=" +drpmntId;
		$.loading({text:"正在加载中，请稍后..."});
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				$.loading("hide");
				if(data != "" && data != null) {
					parsePoliceList(data);
					//parsePoliceList(data,policeListTab,tr);
					$(".notData").hide();
				} else {				
					
					$(".notData").show();
				}
			}
		});
	}
	
	function parsePoliceList(array) {
		
		var arryHtml = [];
		arryHtml.push("<ul class = 'police-box'>");
		for (var i = 0; i < array.length; i++) {			
			if (array[i].PBD_POLICE_IDNTY != null && array[i].PBD_POLICE_NAME != null) {
				arryHtml.push("<li><div>"+array[i].PBD_POLICE_IDNTY+"</div>");
				arryHtml.push("<div><a href='javascript:void(0);'  onClick='parsePoliceInfo("+ JSON.stringify(array[i])+ ");'>"+array[i].PBD_POLICE_NAME+"</a></div>");
				arryHtml.push("<div><a href='javascript:void(0);' onclick='jnmjPoliceInoutRecord("+ JSON.stringify(array[i].PBD_POLICE_IDNTY)+ ",this.id);' id='one_" + i + "'>查看</a></div>");
				arryHtml.push("</li>");
			}
		}
		arryHtml.push("</ul>");
		$("#maquee").html(arryHtml.join(""));
	}
	
	function parsePoliceInfo(data) {

		$("#policeListTab").hide();
		$("#policeInfoTab").show();
		
		if(data != null && data != "") {
			var policeId = "'" + data.PBD_POLICE_IDNTY + "'";
		
			$("#pbdPoliceIdnty").html(data.PBD_POLICE_IDNTY);
			$("#pbd_police_name").html(data.PBD_POLICE_NAME);
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
	
	function idOneToIdTwo(idOne, idTwo) {
		
		$("#" + idOne + "").hide();
		$("#" + idTwo + "").show();
	}
	
	function toPoliceList() {
		
		var drptmnt = $("#drptmnt").val();
		
		$("#dialogId_policeInPrison").dialog({
			width : 1000, 
			height : 800, 
			title : '民警信息 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +config+ "&drptmntId="+drptmnt+"&cusNumber=" + cusNumber,     
		});
		
		$("#dialogId_policeInPrison").dialog("open");
	}
	
	function jnmjPoliceInoutRecord(policeId) {
		
		$("#dialog").dialog({
			width : 1000, 
			height : 800, 
			title : '民警进出记录 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInoutRecord?policeId=" + policeId + "&cusNumber=" + cusNumber,
		});
		
		$("#dialog").dialog("open");
	}
</script>