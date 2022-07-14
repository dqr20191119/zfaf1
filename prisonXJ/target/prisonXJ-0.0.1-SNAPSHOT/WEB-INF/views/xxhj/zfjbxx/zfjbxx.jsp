<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	#drptmntListLoading table td {
		height: 100%;
		overflow: auto;
		padding-left: 8px;
		color:#3b89d1;
	}
	#drptmntListLoading {
		height: 360px;
		overflow: auto;
	}
</style>    
<cui:dialog id="dialogId_xjPrisonInfo" iframePanel="true" autoOpen="false" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>

<div id="rightSide" class="scorllBar"> 
	<div class="rightDivStyle right-zb" id="offender">
		<h4 align="center" style="position:relative">当前监内罪犯基本情况</h4>
		<h5 align="right"><a href="javascript:void(0);" id="callBack" onclick="callBack();">返回>></a></h5>
		<div id="offenderLoading" class="loading">
			<table>
				<tr>
					<td>实押罪犯总数：</td>
					<td> <span id="insideCurrentPrisonerCount" class="marquee-panel"></span> 人 </td>
				</tr>
				
				<tr> 
					<td>实押危安犯总数：</td>
					<td> <span id="WAFCount">0</span>人</td>
				</tr>
				
				<!-- <tr>
					<td>在岗民警与实押罪犯比：</td>
					<td> <span id="rateOfPoliceInPrisoner" class="marquee-panel">0.05%</span></td>
				</tr> -->
				
				<tr>
				 	<td>实押危安犯与总实押罪犯比：</td>
				 	<td> <span id="rateOfWAFInPrisoner" class="marquee-panel">0</span></td>
				</tr>
				
				<tr>
					<td>加戴戒具罪犯数：</td>
					 <td> <span id="GDJJCount" class="marquee-panel">0</span>人</td>
				</tr>
				
				<tr> 
					<td>关押禁闭罪犯数：</td>
					<td> <span id="GYJBCount" class="marquee-panel">0</span>人</td>
				</tr>
				
				 <tr>
				 	<td>隔离审查罪犯数：</td>
				 	<td> <span id="GLSCCount" class="marquee-panel">0</span>人</td>
				 </tr>
				
				<tr>
					<td>立案侦查罪犯数：</td>
					<td> <span id="LAZCCount" class="marquee-panel">0 </span>人</td>
				</tr>
				
				<tr>
					<td>解回重审罪犯数：</td>
					<td> <span id="JHCSCount" class="marquee-panel">0</span>人</td>
				</tr>
				
				<tr>
					<td>暂予监外执行罪犯数：</td>
					<td> <span id="ZYJWZXCount" class="marquee-panel">0</span>人</td>
				</tr>
				
				<tr>
					<td>老病残罪犯数：</td>
					<td> <span id="LBCCount" class="marquee-panel">0</span>人</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="rightDivStyle right-zb" id="drptmntList">
		<H4 align="center">各监区在押罪犯统计</H4>
		<div  id="drptmntListLoading" width="316px">
			<table></table>
		</div>
	</div>
	
</div>

<script type="text/javascript"> 

 	var USER_LEVEL = jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER;  
	var prisonId =  '${prisonId}';         //省局传来监狱编号
	var prisonName = '${prisonName}';
	
	$.parseDone(function() {
		
		$("#rightSide").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		}); 
		
		queryPrisonerCount();
		$("#drptmntList").hide();
	});
	
	/**
	 * 查询押犯数量统计  
	 */
	function queryPrisonerCount() {
		// alert("queryPrisonerCount USER_LEVEL = " + USER_LEVEL);
		if(USER_LEVEL == 1) {
			
			var cusNumber = prisonId;
			$("#offender h4").html(prisonName+'罪犯基本情况')
			$("#callBack").show();
			
		} else {
			$("#offender h4").show();
			$("#callBack").hide();
			var cusNumber = thisCusNumber;
		}
		
		var url = "${ctx}/xxhj/zfjbxx/queryXJPrisonerCount?cusNumber="+cusNumber+"&drpmntId=0";    //改
		
		$("#offenderLoading").loading({
	        text : "正在加载中，请稍后...",
	    });
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				$("#offenderLoading").loading("hide");
				if (data) {
					parsePrisonerCount(data);
				} else {
	
				}
			},	
		});
	}
	
	/**
	 * 显示该监狱实时押犯
	 */
	function parsePrisonerCount(countList) {
		
		var allCount; 
		var WAFCount;
		for (var i = 0; i<countList.length; i++) {
			
			var key = countList[i].LB; 
			var count =countList[i].RS; 
			// alert("parsePrisonerCount key = " + key);
			if (key == "1") {                                             //实押罪犯总数
				
				allCount = count+"";;
				if (count == 0) {
					$("#insideCurrentPrisonerCount").html(count);
				} else {
					$("#insideCurrentPrisonerCount").html("<a href='javascript:void(0);' onclick='methodControler(1,this);'>"+ count + "</a>");
				}				
			} else if (key == "2") {                                                  //实押危安犯总数
			
				WAFCount = count+"";
				if (count == 0) {
					$("#WAFCount").html(count);
				} else {
					$("#WAFCount").html("<a href='javascript:void(0);' onclick='methodControler(2,this);'>"+ count + "</a>");
				} 				
			}else if (key == "4") {                                                        //加戴戒具罪犯数
				
				$("#GDJJCount").html("<a href='javascript:void(0);' onclick='methodControler(4,this);'>"+ count + "</a>");
			
			} else if (key == "5") {                         //关押禁闭罪犯数
				
				$("#GYJBCount").html("<a href='javascript:void(0);' onclick='methodControler(5,this);'>"+ count + "</a>");

			} else if (key == "6") {                         //隔离审查罪犯数
				
				$("#GLSCCount").html("<a href='javascript:void(0);' onclick='methodControler(6,this);'>"+ count + "</a>");
					
			} else if (key == "7") {                         //立案侦查罪犯数
				
				$("#LAZCCount").html("<a href='javascript:void(0);' onclick='methodControler(7,this);'>"+ count + "</a>");
			
			} else if (key == "8") {                         //解回重审罪犯数
				
				$("#JHCSCount").html("<a href='javascript:void(0);' onclick='methodControler(8,this);'>"+ count + "</a>");
			
			} else if (key == "9") {                       //暂予监外执行罪犯数
				
				$("#ZYJWZXCount").html("<a href='javascript:void(0);' onclick='methodControler(9,this);'>"+ count + "</a>");
			
			} else if (key == "10") {                          //老病残罪犯数
				
				$("#LBCCount").html("<a href='javascript:void(0);' onclick='methodControler(10,this);'>"+ count + "</a>");
			} 
		}
		var rate = ((WAFCount/allCount)*100).toFixed(2)+"%";                      //计算危安犯与总罪犯数比例
		$("#rateOfWAFInPrisoner").html(rate );   
	}
	
	/* 方法 */
	function methodControler(type, aId) {
		// alert("methodControler type = " + type);
		setTitleName("drptmntList", aId);
		$("#drptmntList table").empty();
		$("#drptmntList").show();
		queryPrisonDrptmnt(type);                	                  
	}
	
	 /* 查询监狱监区列表 */
	function queryPrisonDrptmnt(type) {
		if(USER_LEVEL == 1) {
			var cusNumber = prisonId;
		} else {
			
			var cusNumber = thisCusNumber;
		}
		// alert("queryPrisonDrptmnt cusNumber = " + cusNumber);
		var url = "${ctx}/xxhj/jnmj/queryPrisonDepartment?cusNumber="+cusNumber; 
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			async: false,
			success : function(data) {
				
				if (data!=null && data!='') {
					
					var table=	$("#drptmntList table");
					table.empty();
					for (var i = 0;i< data.length; i++) {
						
						var drptmntName=data[i].orgName;
						var drptmntId=data[i].orgKey;
						var tr=$("<tr></tr>");
						
					    tr.append("<td align='left'  width='200'>"+drptmntName+"：</td>");
					    tr.append("<td align='left' width='80'><span  id='dpmt_"+drptmntId+"'>0</span>人</td>");
                        table.append(tr);
					}
					// 查询各监区罪犯个数
					queryPrisonDrptmntCount(type); 
				}
			}
	   });
	 }
	 
	 /* 查询监区罪犯人数 */
	 function queryPrisonDrptmntCount(type) {
		// alert("queryPrisonDrptmntCount type = " + type);
		var cusNumber;
		if(USER_LEVEL == 1) {
			cusNumber = prisonId;
		} else {
			cusNumber = thisCusNumber;
		}
		var url = "${ctx}/xxhj/zfjbxx/queryXJPrsnrCountDrptmntList"; 
		$("#drptmntListLoading").loading({ text : "正在加载中，请稍后..."});
        $.ajax({
        	type : "post",
			url : url,
			dataType : "json",
			//async: false,
			data : {
				cusNumber : cusNumber,   
				type : type                             
			},
			success : function(data) {
				$("#drptmntListLoading").loading("hide");
				if (data) {
					parseDrptmntPrisoner(type,data);
				}
			}
        });
	 }
	 /* 解析监区罪犯人数 */
	 function parseDrptmntPrisoner(type,data) {
	 // alert("parseDrptmntPrisoner type = " + type);
		 for(var i = 0;i < data.length; i++) {
			
			 var drptmntId = data[i].JQ_ID;
			 var count = data[i].RS;
			 var drptmntSpan = $("#dpmt_"+drptmntId);
			 var otherNum = 0;
			 
			 if (drptmntSpan.length > 0) {
				 
				 drptmntSpan.empty();
				 if(count == 0) {
					 drptmntSpan.html(0);
				 } else {
					 drptmntSpan.html("<a href='javascript:void(0);'onclick='toPrisonerList("+type+",\""+drptmntId+"\");'>"+count+"</a>");
				 }
			 } else {
				 otherNum += count;
			 }
		 }
		$("#drptmntListLoading").mCustomScrollbar({
				theme : "minimal-light",
				autoExpandScrollbar : true
		});
	 }
     /**
	 * 弹出罪犯历史查询页面
	 */
	 function toPrisonerList(type, areaId) {
		if(USER_LEVEL == 1) {
			var cusNumber = prisonId;
		}else {
			var cusNumber = thisCusNumber;
		}
    	// alert("${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=" + type + "&drpmntId=" + areaId + "&cusNumber=" + cusNumber);
		$("#dialogId_xjPrisonInfo").dialog({
			width : 1000,       
			height : 800, 
			title : '罪犯信息',
			url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type="                          //这个query和老系统的无关系 是我自己定义的
				+ type+ "&drpmntId="+ areaId+ "&cusNumber=" + cusNumber, 
				
		});
		$("#dialogId_xjPrisonInfo").dialog("open");
	}
    
	function callBack() {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	panel.panel("refresh", "${ctx}/xxhj/zfjbxx/pzfjbxx");
    }	
	
	function setTitleName(titleId, typeId, type) {
		
		var title = $(typeId).parent().parent().parent().children().eq(0).html().replace("：", "");
		
		if (titleId == "drptmntList") {
			
			$("#" + titleId + " H4").html("各监区"+title);
		} else {
			if (type != null) {
				
				$("#" + titleId + " h4").html(title + "分布情况");
			} else { 
				
				$("#" + titleId + " h4").html(title + "信息纪录");
			}
		}
	} 
</script >


