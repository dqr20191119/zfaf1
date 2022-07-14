<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:dialog id="dialogId_prisonerToday" reLoadOnOpen="true"  iframePanel="true" resizable="false" autoOpen="false" width="1000" height="500" maximizable="false"></cui:dialog>

<div class="rightDivStyle right-zb" id="offender">
	<h4 align="center" style="font-size: 20px;">当前监内押犯情况</h4>
	<center>
		<table>
			<tr>
				<td>在押罪犯：</td>
				<td><span class="spOne" id="inside">0</span> 人</td>
			</tr>
			<tr>
				<td>当日释放：</td>
				<td><span class="spOne" id="free">0</span> 人</td>
			</tr>
			<tr>
				<td>当日收押：</td>
				<td><span class="spOne" id="todayInside">0</span> 人</td>
			</tr>
			<tr>
				<td>解回再审：</td>
				<td><span class="spOne" id="repeatView">0</span> 人</td>
			</tr>
			<tr>
				<td>特许离监：</td>
				<td><span class="spOne" id="leavePrison">0</span> 人</td>
			</tr>
			<tr>
				<td>监外就医：</td>
				<td><span class="spOne" id="outHospital">0</span> 人</td>
			</tr>
		</table>
	</center>
</div>

<div class="rightDivStyle right-zb" style="display: none;" id="drptmntCountList">
	<h4 align="center">在押罪犯分布情况</h4>
	<center>
		<div class="overflow_div">
			<table></table>
		</div>
	</center>
</div>

<input type="hidden" id="thisCusNumber" />

<script>

	var thisCusNumber = jsConst.CUS_NUMBER;
	var cusNumber = $("#thisCusNumber").val(thisCusNumber);
	
	$.parseDone(function() {

		$("#offender").show(); 
		$("#drptmntCountList").show(); 
		queryPrisonerCount();
	});
	
	/**
	 * 查询当前监狱押犯数量统计  
	 */
	var prisonerCount = null;
	function queryPrisonerCount() {
		
		var list = new Array();
		var cusNumber = $("#thisCusNumber").val();	
		list.push(cusNumber);
		
		var url = "${ctx}/xxhj/jryf/countPrisoner?cusNumber=" + list[0];
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if(data) {
					parsePrisonerCount(data);
				}
			}
		});
	}
	
	/**
	 * 解析押犯数量统计  把查到的列表解析 放入表格中
	 */
	var type = null
	function parsePrisonerCount(countList, message) {
	
		for (var i = 0; i < countList.length; i++) { 
			var key = countList[i].KEY; 
			var count = countList[i].COUNT; 
			
			if (key == "inside_prisoner_count") {
				
				if (message == null) {
					$("#inside").empty();
					$("#inside").append("<a href='javascript:void(0);' onclick='methodControler(0,this);'>"+ count + "</a>"); //0也可以点击
				} else {
					$("#inside a").html(count);
				}
			} else if (key == "free_prisoner_count") {
				
				if (message == null) {
					$("#free").empty();
					if (count == 0) {
						$("#free").html(count);
					} else {
						$("#free").append("<a href='javascript:void(0);' onclick='methodControler(1,this);'>"+ count + "</a>");
					}
				} else {
					$("#free a").html(count);
				}
			} else if (key == "todayin_prisoner_count") {
				
				if (message == null) {
					$("#todayInside").empty();
					if (count == 0) {
						$("#todayInside").html(count);
					} else {
						$("#todayInside").append("<a href='javascript:void(0);' onclick='methodControler(2,this);'>"+ count + "</a>");
					}
				} else {
					$("#todayInside a").html(count);
				}
			} else if (key == "repeatcheck_prisoner_count") {
				
				if (message == null) {
					$("#repeatView").empty();
					if (count == 0) {
						$("#repeatView").html(count);
					} else {
						$("#repeatView").append("<a href='javascript:void(0);' onclick='methodControler(3,this);'>"+ count + "</a>");
					}
				} else {
					$("#repeatView a").html(count);
				}
			} else if (key == "leave_prison_count") {
				
				if (message == null) {
					$("#leavePrison").empty();
					if (count == 0) {
						$("#leavePrison").html(count);
					} else {
						$("#leavePrison").append("<a href='javascript:void(0);' onclick='methodControler(4,this);'>"+ count + "</a>");
					}
				} else {
					$("#leavePrison a").html(count);
				}
			} else if (key == "out_hospital_count") {
				
				if (message == null) {
					$("#outHospital").empty();
					if (count == 0) {
						$("#outHospital").html(count);
					} else {
						$("#outHospital").append("<a href='javascript:void(0);' onclick='methodControler(8,this);'>"+ count + "</a>");
					}
				} else {
					$("#outHospital a").html(count);
				}
			}
		}
		
		loadDrptmnt();
		
		if (type != null) {
			
			if (type == 0) {
				$("#inside").find("a").click();
			} else if (type == 1) {
				$("#free").find("a").click();
			} else if (type == 2) {
				$("#todayInside").find("a").click();
			} else if (type == 3) {
				$("#repeatView").find("a").click();
			} else if (type == 4) {
				$("#leavePrison").find("a").click();
			} else if (type == 8) {
				$("#outHospital").find("a").click();
			}
		} else {
			
			$("#inside").find("a").click();
		}
	}
	
	/**
	 * 监狱加载数据方法控制
	 */
	function methodControler(type, aId) {

		var divId = $("#offender table a");
		var obj = $(aId);
		setTitleName("drptmntCountList", aId, 1);
		queryAreaPrisoner(type);
	}
	
	/**
	 * 加载部门信息   我drptmntCountList里面没有数据，靠这个显示数据
	 */
	function loadDrptmnt() {
		
		var cusNumber = $("#thisCusNumber").val();
		var url = "${ctx}/xxhj/jnmj/queryPrisonDepartment?cusNumber="+ cusNumber;
		$.ajax({
			async : false, //同步加载
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if (data != "" && data != null) {
					
					var table = $("#drptmntCountList table");
					table.empty();
					var num = data.length;
					for (var i = 0; i < data.length; i++) {
						var drptmntId = data[i].orgKey;
						var drptmntName = data[i].orgName;
						var tr = $("<tr></tr>");
						
						tr.append("<td><a href='javascript:void(0);' onclick='showMapData("+ drptmntId+ ")' >"
										+ drptmntName + "</a>： </td>");
						tr.append("<td><span class='spTwo' id='dpmt_" + drptmntId + "'>0</span> 人</li></td>");
						
						table.append(tr);
						num++;
					}
					
					if (num % 2 == 0) {
						var tr = $("<tr></tr>");
						tr.append("<td><a href='javascript:void(0);' onclick='showMapData(-1)' >其他</a>： </td>");
						tr.append("<td><span class='spTwo' id='other'>0</span> 人</li></td>");
						table.append(tr);
					}
				}	
			}
		});
	}
	
	/*  我 (参数：type，监所编号)，查出这种type犯人的监区分布*/
	function queryAreaPrisoner(type) {
		
		var url = " ${ctx}/xxhj/jryf/queryDprtCount";
		var type=type;
		var cusNumber = $("#thisCusNumber").val();
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			data : {
				cusNumber : cusNumber,   	//sql里会用到
				type : type               	//用来判断sql
			},
			success : function(data) {

				if (data) {
					parseDrptmntPrisoner(data, type);
				}
			}
		});
	}
	
	/**
	 * 解析各监区押犯数量
	 */
	function parseDrptmntPrisoner(countList, type) {
		
		$("#drptmntCountList").find("td").each(function() {
			$(this).find("span").html(0);
		});
		
		var otherNum = 0;

		for (var i = 0; i < countList.length; i++) {
			var areaId = countList[i].PBD_PRSN_AREA_IDNTY;//=dbd_dprtmnt_id
			var areaName = countList[i].PBD_PRSN_AREA;
			var count = countList[i].COUNT;
			var drptmntSpan = $("#dpmt_" + areaId);//我 部门编号
			
			if (drptmntSpan.length > 0) {
				
				drptmntSpan.empty();
				if (count == 0) {
					drptmntSpan.html(count);
				} else {
					drptmntSpan.append("<a href='javascript:void(0);' onclick='toPrisonerList("
									+ type+ ",\""+ areaId+ "\",this);'>"+ count + "</a>");
				}
			} else {
				
				otherNum += count;
			}
		}
		
		$("#other").empty();
		if (otherNum == 0) {
			$("#other").html(otherNum);
		} else {
			$("#other").append("<a href='javascript:void(0);' onclick='toPrisonerList("+ type + ",\"other\",this);'>" + otherNum + "</a>");
		}
	}

	function toPrisonerList(type, areaId) {
		
		var thisCusNumber = $("#thisCusNumber").val();
		
		$("#dialogId_prisonerToday").dialog("option",{
			width : 1000, 
			height : 780, 
			subTitle : '罪犯信息',
			url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=1&type=" + type + "&deptId=" + areaId + "&cusNumber=" + thisCusNumber
		});
		$("#dialogId_prisonerToday").dialog("open");
	}

	function showMapData() {
		
		$("#dialogId_prisonerToday").dialog({
			width : 1000, 
			height : 780, 
			subTitle : '3D地图',
			modal : true, 
			autoOpen : false
		});

		$("#dialogId_prisonerToday").dialog("open");
	}

	function setTitleName(titleId, typeId, type) {

		var title = $(typeId).parent().parent().parent().children().eq(0).html().split("：")[0];
		if (titleId == "prisonList") {
			
			$("#" + titleId + " H4").html("各监所" + title + "统计");
		} else {
			
			if (type != null) {
				$("#" + titleId + " h4").html(title + "分布情况");
			} else {
				$("#" + titleId + " h4").html(title + "信息纪录");
			}
		}
	}
</script>