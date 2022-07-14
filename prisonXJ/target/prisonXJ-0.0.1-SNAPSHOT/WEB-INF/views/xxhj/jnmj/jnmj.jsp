<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	#policeListTab {
		width:100%;
		margin-top:12px;
		height:345px;
	}
	#policeInfoTab td {
		line-height:30px;
	}
</style>

<div id="rightSide_jnmj" class="scorllBar">
	<div class="rightDivStyle right-zb" id="prisonCount">
		<h4 align="center">
			当前监内民警人数:
			<span class="sp" id="insideCount">0</span> <span class="sp">人</span>
			<h5 align="right"><a href="javascript:void(0);" id="callBack" onclick="callBack();" title="返回">返回>></a></h5>
		</h4>
	</div>

	<div class="rightDivStyle right-zb" id="policeCount">
		<h4 align="center">监内民警数量分布</h4>
		<center>
			<div class="overflow_div">
				<table id="left"></table>
				<table id="right"></table>
			</div>
		</center>
	</div>

 	<div class="rightDivStyle right-zb" id="policeList">
		<h4 align="center">监内民警信息记录<a href="javascript:void(0);" onClick="toPoliceList();">更多>></a></h4>
			<div class="info_list" id="policeListTab">
				<dl>
					<dd>警号</dd>
					<dd>姓名</dd>
					<dd>进出记录</dd>
				</dl>
				<div id="maquee" class="maquee"></div>
			</div>
			<div class="notData" style="display: none;" align="center" >暂无数据!</div> 
			 	<table class="rightTable"  overflow ="auto"  id="policeInfoTab" style="display:none"  onmouseleave="idOneToIdTwo('policeInfoTab', 'policeListTab');">
					<tr>
						<td width="90" rowspan="4"><img src="" id="pbdImg" width="90" height="130"></td>
						<td width="58">警号：</td>
						<td width="120" id="pbdPoliceIdnty" name="PBD_POLICE_IDNTY"></td>
					</tr>
					<tr>
						<td width="58">姓名：</td>
						<td width="120" id="pbd_police_name" name ="PBD_POLICE_NAME"></td>
					</tr>
					<tr>
						<td width="58">性别：</td>
						<td id="pbdSex"  name="PBD_SEX" ></td>
					</tr>
					<tr>
						<td>部门：</td>
						<td id="pbdDrptmnt" name ="PBD_DRPTMNT"></td>
					</tr>
					<tr>
						<td>警务通号：</td>
						<td colspan="2" id="pbdPoliceCmmnct"  name ="PBD_POLICE_CMMNCT"></td>
					</tr>
					<tr>
						<td>监狱短号：</td>
						<td colspan="2" id="pbdShortPhone" name ="PBD_SHORT_PHONE"></td>
					</tr>
					<tr>
						<td width="58">对讲呼号：</td>
						<td colspan="2" id="pbdTalkNum" name ="PBD_TALK_NUM"></td>
					</tr>
					<tr>
						<td width="58">固定电话：</td>
						<td colspan="2" id="pbdFixedPhone" name ="PBD_FIXED_PHONE"></td>
					</tr>
					<tr>
						<td width="58">手机号码：</td>
						<td colspan="2" id="pbdPhone"  name ="PBD_PHONE"></td>
					</tr>
				</table>
		</div>
</div>	

<input type="hidden" id="drptmnt" value=''/> 

<cui:dialog id="dialogId_policeInPrison" autoOpen="false" reLoadOnOpen="true" iframePanel="true" autoDestroy="true" resizable="false"></cui:dialog>

<script type="text/javascript">

	var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;    //性别
	var USER_LEVEL = jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER; 
	var prisonId = '${prisonId}';                //  省局传来监狱编号
	var prisonName = '${prisonName}';            //  省局传来监狱名称
	var cusNumber;
	var config;                                 //   监狱数据查询类别 根据CDS_CONGIF表中数据判断
    var allData;                                //   查到的监狱人数总数据
	
	$.parseDone(function() {
		
		$("#policeListTab").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		}); 
		
		if(USER_LEVEL == 1 && prisonId != null && prisonName != null) {
			
			$("#callBack").show();
			cusNumber = prisonId ;
			$("#prisonCount h4").html(prisonName)
		} else if (USER_LEVEL != 1){
			
			$("#callBack").hide();
			cusNumber = thisCusNumber;
 		}
		
		//按监区列出在监民警数量
		loadDrptmnt();	
		
		//查询监狱种类
		queryDutyConfig();
		
	});
	
	function queryDutyConfig() {
		
		var url = "${ctx}/xxhj/jnmj/queryDutyConfig?cusNumber=" + cusNumber;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				// 查询在监民警总人数
				if(data) {
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
	
	// 查询在监民警总人数     FLAG 为空  为组织；FLAG 为1 刷卡；FLAG 为2 值班
	function queryPoliceCount() {
		
		var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceCount?cusNumber=" + cusNumber+"&config="+config;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if(data) {
					if(data != "" && data != null) {
						allData = data;
						parsePoliceDrptmntCount();
					}
				}
			}
		});
	}
    
	function loadDrptmnt() {

		var url = "${ctx}/xxhj/jnmj/queryPrisonDepartment?cusNumber=" + cusNumber;	
		$("#policeCount").loading({text:"正在加载中，请稍后..."});
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if(data != "" && data != null) {	
					$("#policeCount").loading( "hide" );
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
						
						if(number != -1) {
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
				} else {
				}
			}
		});
	}
	
	//插入函数
	function insert_flg(str,flg,sn) {
		
	    var newstr="";
        var tmp=str.substring(0, sn);
        newstr+=tmp+flg;
        tmp=str.substring(sn+1, str.length);
        newstr+=tmp;
        
	    return newstr;
	}
	
	function parsePoliceDrptmntCount() {
		var countList = allData;
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
			} else if(drptmntId == '0'){
				$("#insideCount").html(count);
			} else {
				otherNum += count;
			}
		}
		
		$("#other").empty();
		if(otherNum == '0') {			
			$("#other").html(otherNum);
		} else {	
			$("#other").append("<a href='javascript:void(0);' onclick='queryPoliceList(\"other\", this);'>"+ otherNum + "</a>");	
		}
	
		var obj = $("#policeCount #left").children().eq(0).children().eq(0).children().eq(1).find("span");
		
 		if( obj.text()!= '0') {	
			obj.children("a").click();
		} else {			
			$(".notData").show();
		}
	}

	function queryPoliceList(drptmnt, aId) {
		$("#drptmnt").val(drptmnt);
		drptmnt = drptmnt == "other" ? cusNumber : drptmnt;
		
		var para = drptmnt != cusNumber ? "0" : "1";			
		var url = "${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceInfo?config=" + config + "&para=" + para + "&cusNumber=" + cusNumber + "&drptmntId="
				+ drptmnt + "&cusNumber=" + cusNumber;
		
		$.loading({text:"正在加载中，请稍后..."});
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				$.loading("hide");
				if(data != "" && data != null) {
					
					parsePoliceList(data);
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
			$("#pbdImg").attr("src","${ctx}/common/authsystem/findMjPicInfo?loginName="+data.PBD_LOGIN_NAME+"&demptId="+data.PBD_DRPTMNT_ID);
		}
	}
	
	function getSex(PBD_SEX) {
		
		var sex='';
		switch(PBD_SEX) {
			case '0':
				sex = "女性";
				break;
			case '1':
				sex = "男性";
				break;
			default:
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
			height : 800, 
			title : '民警信息 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +config+ "&drptmntId=" + drptmntId + "&cusNumber=" + cusNumber
		});
		
		$("#dialogId_policeInPrison").dialog("open");
	}
	
	function jnmjPoliceInoutRecord(policeId, aId) {
		
		$("#dialogId_policeInPrison").dialog({
			width : 1000, 
			height : 800, 
			title : '民警进出记录 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInoutRecord?policeId=" + policeId + "&cusNumber=" + cusNumber
		});
		
		$("#dialogId_policeInPrison").dialog("open");
	}
</script>