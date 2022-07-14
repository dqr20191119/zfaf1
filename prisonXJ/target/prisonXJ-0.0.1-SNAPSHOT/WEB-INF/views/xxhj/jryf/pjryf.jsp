<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:dialog id="dialogId_prisonerToday" autoOpen="false" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>

<div id="rightSide_pjryf" class="scorllBar">
	<div class="rightDivStyle right-zb" id="offender">
		<h4 align="center">全疆监所押犯情况汇总</h4>
		<div class="a_btn back_btn">
			<a href="javascript:void(0);" id="callBack" onclick="callBack();">返回</a>
		</div>
		<center>
			<table>
				<tr>
					<td>在押罪犯：</td>
					<td><span class="spOne" id="inside"></span> 人</td>
				</tr>
				<tr>
					<td>当日释放：</td>
					<td><span class="spOne" id="free"></span> 人</td>
				</tr>
				<tr>
					<td>当日收押：</td>
					<td><span class="spOne" id="todayInside"></span> 人</td>
				</tr>
				<tr>
					<td>解回再审：</td>
					<td><span class="spOne" id="repeatView"></span> 人</td>
				</tr>
				<tr>
					<td>特许离监：</td>
					<td><span class="spOne" id="leavePrison"></span> 人</td>
				</tr>
				<tr>
					<td>监外就医：</td>
					<td><span class="spOne" id="outHospital"></span> 人</td>
				</tr>
			</table>
		</center>
	</div>
	<div class="rightDivStyle right-zb" id="prisonList">
		<H4 align="center">各监所在押罪犯统计</H4>
		<center>
			<div class="overflow_div">
				<table></table>
			</div>
		</center>
	</div>
	<div class="rightDivStyle right-zb" style="display: none;" id="offenderList">
		<h4 align="center">在押罪犯分布情况</h4>
		<center>
			<div class="overflow_div">
				<table></table>
			</div>
		</center>
	</div>
</div>

<input type="hidden" id="provType" />
<input type="hidden" id="level" />
<input type="hidden" id="cusName" />
<input type="hidden" id="USER_LEVEL" value='1' />
<input type="hidden" id="thisCusNumber" value='' />

<script>

	var USER_LEVEL=jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER;
	
	$.parseDone(function() {
		
		if(USER_LEVEL==1){
			
			loadProvMethod();
		} else if(USER_LEVEL==2){
			
			var cusName='当前监内押犯情况';
			loadPrisMethod(thisCusNumber, cusName);
		}
	});
	
	//省局方法
	function loadProvMethod() {
		
		$("#rightSide_pjryf").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		$("#callBack").hide();
		$("#offender").show(); 
		$("#prisonList").show(); 
		$("#level").val(1);       //省局权限的双重判断
		
		queryPrisonerCount();
	}
	
	//监狱方法
	function loadPrisMethod(cusNumber, cusName) {

	    if(cusNumber) {
			$("#thisCusNumber").val(cusNumber);
			$("#offender h4").html(cusName);
			if(USER_LEVEL==2 ){
				$("#callBack").hide();
			} else {
				$("#callBack").show();
			}
	    } 
	    
		$("#prisonList").hide();
		$("#offender").show();
		$("#offender").find("td").each(function() {
	       $(this).find("span").html('');
        });
		
		$("#offenderList").show();
		$("#level").val(2);
		
		queryPrisonerCount();
	}
	
	function callBack() {                             //省局从监狱界面回到全疆界面
		
		$("#offender h4").html("全疆监狱押犯情况汇总");
		$("#thisCusNumber").val('');
		$("#callBack").hide();
		$("#offenderList").hide();
		$("#offender").show();
		$("#prisonList").show();
		
		$("#level").val(1);                         //省局要看到监狱列表
		if(prisonerCount) {
			$("#prisonList H4").val(title);         //  把最后一次查到的prisonList存放进来 
			parsePrisonerCount(prisonerCount);
		} else {
			queryPrisonerCount();
		}
	}
	
	/**
	 * 查询押犯数量统计  
	 */
	var prisonerCount = null;
	function queryPrisonerCount() {
	
		var list = new Array();
		var cusNumber = $("#thisCusNumber").val();
		
		if(cusNumber==1100){
			cusNumber='';
		}
		
		list.push(cusNumber);
		var url = "${ctx}/xxhj/jryf/countPrisoner?cusNumber=" + list[0];   
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				if(data) {
					if ($("#level").val() == 1) {
						prisonerCount = data;                    // 省局 把查到的各种类型罪犯数量存放
					}

					parsePrisonerCount(data);
				}
			}
		});
	}
	
	/**
	 * 解析押犯数量统计  把查到的列表解析 放入表格中
	 */
	function parsePrisonerCount(countList, message) {
		
		for(var i = 0; i < countList.length; i++) { 
			var key = countList[i].KEY; 
			var count = countList[i].COUNT; 
			
			if(key == "inside_prisoner_count") {
				
				if (message == null) {
					$("#inside").empty();
					$("#inside").append( "<a href='javascript:void(0);' onclick='methodControler(0,this);'>"+ count + "</a>"); 
				} else {
					$("#inside a").html(count);
				}
			} else if(key == "free_prisoner_count") {
				
				if(message == null) {
					$("#free").empty();
					if(count == 0) {
						$("#free").html(count);
					} else {
						$("#free").append( "<a href='javascript:void(0);' onclick='methodControler(1,this);'>"+ count + "</a>");
					}
				} else {
					$("#free a").html(count);
				}
			} else if(key == "todayin_prisoner_count") {           //今日收押
				
				if(message == null) {
					$("#todayInside").empty();
					if (count == 0) {
						$("#todayInside").html(count);
					} else {
						$("#todayInside").append("<a href='javascript:void(0);' onclick='methodControler(2,this);'>"+ count + "</a>");
					}
				} else {
					$("#todayInside a").html(count);
			  	}
			} else if(key == "repeatcheck_prisoner_count") {     //解回再审
				
				if (message == null) {
					$("#repeatView").empty();
					if (count == 0) {
						$("#repeatView").html(count);
					} else {
						$("#repeatView").append( "<a href='javascript:void(0);' onclick='methodControler(3,this);'>"+ count + "</a>");
					}
				} else {
					$("#repeatView a").html(count);
				}
			} else if(key == "leave_prison_count") {           //特许离监
				
				if(message == null) {
					$("#leavePrison").empty();
					if (count == 0) {
						$("#leavePrison").html(count);
					} else {
						$("#leavePrison").append( "<a href='javascript:void(0);' onclick='methodControler(4,this);'>"+ count + "</a>");
					}
				} else {
					$("#leavePrison a").html(count);
				}
			} else if(key == "out_hospital_count") {            //外出就医   
				
				if (message == null) {
					$("#outHospital").empty();
					if (count == 0) {
						$("#outHospital").html(count);
					} else {
						$("#outHospital").append( "<a href='javascript:void(0);' onclick='methodControler(8,this);'>" + count + "</a>");
					}
				} else {
					$("#outHospital a").html(count);
				}
			}
		}
		
		if(message == null) {
			var level = parseInt($("#level").val());
			if(level != 1) {             
				loadDrptmnt();
			}

			var USER_LEVEL = $("#USER_LEVEL").val(); //省局的等级登陆 
			
			if(USER_LEVEL == 1) {
				var type = $("#provType").val();
				
				if(type == 0) {
					$("#inside").find("a").click();
				} else if(type == 1) {
					$("#free").find("a").click();
				} else if(type == 2) {
					$("#todayInside").find("a").click();
				} else if(type == 3) {
					$("#repeatView").find("a").click();
				} else if(type == 4) {
					$("#leavePrison").find("a").click();
				} else if(type == 8) {
					$("#outHospital").find("a").click();
				}
			} else {
				$("#inside").find("a").click();
			}
		}
	}
	
	/**
	 * 省局、监狱加载数据方法控制
	 */
	var title = null
	function methodControler(type, aId) {
		
		var divId = $("#offender table a");
		var obj = $(aId);
		var level = parseInt($("#level").val());
		
		if(jsConst.USER_LEVEL == 1 && level == 1) {
			
			$("#provType").val(type);                           //如果是省局 就列出所有监狱列表
			setTitleName("prisonList", aId);
			var title = $("#prisonList  H4").html();
			
			if(provType != type) {                            //和上次查询数据相同  直接去下面loadPrisonList(prisonData);
			    provType = type;                                 //记住上一次省局的type
				queryPrisonPrisoner(type);                       //查询人数一次
			} else {
				loadPrisonList(prisonData); 
			}
		} else {
			setTitleName("offenderList", aId, 1);
			queryAreaPrisoner(type);
		}
	}
	
	/**
	 * 查询各监狱押犯类型罪犯数量
	 */
	var prisonData = null;
	var provType = null;
	function queryPrisonPrisoner(type) {
		
		var list = new Array();
		var url = "${ctx}/xxhj/jryf/queryPrisonCount";
		var type = type; //押犯类型
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			data : {
				type : type,
			},
			success : function(data) {
				
				if(data) {
					prisonData = data;
					loadPrisonList(data);
					
				}
			}
		});
	}

	/**
	 * 加载全疆监狱列表  	
	 */
	//  显示出每个监狱的数据
	function loadPrisonList(data) {
	
		var table = $("#prisonList table");
		table.empty();
		
		for (var i = 0; i < data.length; i++) {
			var tr = $("<tr></tr>");
			tr.append("<td class='one'>" + data[i].OBD_ORGA_NAME + "：</td>");
			tr.append("<td><a href='javascript:void(0);' class='spTwo' onclick='loadPrisMethod(" + data[i].OBD_ORGA_IDNTY+ ",\""+ data[i].OBD_ORGA_NAME+ "\");'>"
							+ data[i].COUNT+ "</a> 人</td>");
			table.append(tr);
		}
	}
	
	/**
	 * 加载部门信息   我offenderList里面没有数据，靠这个显示数据
	 */
	function loadDrptmnt() {
		
		var cusNumber = $("#thisCusNumber").val();
		var url = "${ctx}/xxhj/jnm/queryPrisonDepartment?cusNumber="+ cusNumber;
		
		$.ajax({
			async : false,
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
                  
				if (data != "" && data != null) {
					var table = $("#offenderList table");
					table.empty();
					
					var num = data.length;
					for(var i = 0; i < data.length; i++) {
						var drptmntId = data[i].orgKey;
						var drptmntName = data[i].orgName;
						var tr = $("<tr></tr>");
						
						tr.append("<td><a href='javascript:void(0);' onclick='showMapData("+ drptmntId+ ")' >" + drptmntName + "</a>： </td>");
						tr.append("<td><span class='spTwo' id='dpmt_" + drptmntId + "'></span> 人</li></td>");
						
						table.append(tr);
						num++;
					}
					
					if(num % 2 == 0) {
						
						var tr = $("<tr></tr>");
						tr.append("<td><a href='javascript:void(0);' onclick='showMapData(-1)' >其他</a>： </td>");
						tr.append("<td><span class='spTwo' id='other'></span> 人</li></td>");
						
						table.append(tr);
					}
				}
			}
		});
	}
	
	/*  我 (参数：type，监所编号)，查出这种type犯人的监区分布人数*/
	function queryAreaPrisoner(type) {
		
		var url = " ${ctx}/xxhj/jryf/queryDprtCount";
		var type=type;
		var cusNumber = $("#thisCusNumber").val();
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			data : {
				cusNumber : cusNumber,   //sql里会用到
				type : type              //用来判断sql
			},
			success : function(data) {
				
				if (data) {
					parseAreaPrisoner(data, type);
				}
			}
		});
	}
	
	/**
	 * 解析各监区押犯数量
	 */
	function parseAreaPrisoner(countList, type) {
		
		$("#offenderList").find("td").each(function() {
			$(this).find("span").html('');
		});
		
		var otherNum = 0;

		for(var i = 0; i < countList.length; i++) {
			
			var areaId = countList[i].PBD_PRSN_AREA_IDNTY;//=dbd_dprtmnt_id
			var areaName = countList[i].PBD_PRSN_AREA;
			var count = countList[i].COUNT;
			var drptmntSpan = $("#dpmt_" + areaId);//我 部门编号
			
			if(drptmntSpan.length > 0) {
				drptmntSpan.empty();
				
				if (count == 0) {
					drptmntSpan.html(count);
				} else {
					drptmntSpan.append("<a href='javascript:void(0);' onclick='toPrisonerList("+ type+ ",\""
									+ areaId+ "\",this);'>"+ count + "</a>");
				}
			} else {        //如果后来查到的监区不在我们列出的监区就把人加一起放在"其他"
				otherNum += count;
			}
		}
		
		$("#other").empty();
		
		if (otherNum == 0) {
			$("#other").html(0);
		} else {
			$("#other").append("<a href='javascript:void(0);' onclick='toPrisonerList("+ type + ",\"other\",this);'>" + otherNum + "</a>");
		}
	}

	/**
	 * 弹出罪犯历史查询页面
	 */
	function toPrisonerList(type, areaId) {
		
		var thisCusNumber = $("#thisCusNumber").val();
		
		$("#dialogId_prisonerToday").dialog({
			width : 1000, //属性
			height : 780, //属性
			subTitle : '罪犯信息',
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/xxhj/jryf/jryfPrisonerinfo?query=1&type="
					+ type
					+ "&deptId="
					+ areaId
					+ "&cusNumber=" + thisCusNumber,
		});

		$("#dialogId_prisonerToday").dialog("open");
	}

	function showMapData() {
		
		$("#dialogId_prisonerToday").dialog({
			width : 1000, //属性
			height : 780, //属性
			subTitle : '3D地图',
			modal : true, //属性
			autoOpen : false,
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
