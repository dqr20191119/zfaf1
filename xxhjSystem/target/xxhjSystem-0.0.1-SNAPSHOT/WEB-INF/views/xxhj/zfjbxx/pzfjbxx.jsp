<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<style>
	#prisonerCountPrisonListLoading table td {
		padding-top: 6px;
		height: 100%;
		overflow: auto;
		padding-left: 8px;
		color:#3b89d1;
	}
	#prisonerCountPrisonListLoading {
		height: 360px;
		overflow: auto;
	}
</style>

<cui:dialog id="dialogId_XJPrisonerInfo" autoOpen="false" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>

<div id="rightSide" style="height:100%"> 
	<div class="rightDivStyle right-zb" id="offender">
	 <h4 align="center">全疆罪犯基本情况</h4>
	 	<div id="offenderLoading">
			<table>
				<tr>
					<td>实押罪犯总数：</td>
					<td> <span id="insideCurrentPrisonerCount" class="marquee-panel"></span> 人 </td>
				</tr>
				
				<tr> 
					<td>实押危安犯总数：</td>
					<td> <span id="WAFCount" class="marquee-panel"> 0</span>人</td>
				</tr>
				
				<tr>
					<td>在岗民警与实押罪犯比：</td>
					<td> <span id="rateOfPoliceInPrisoner" class="marquee-panel">0.05% </span></td>
				</tr>
				
				<tr>
				 	<td>实押危安犯与总实押罪犯比：</td>
				 	<td> <span id="rateOfWAFInPrisoner" class="marquee-panel"> 0 </span></td>
				</tr>
				
				<tr>
					<td>加戴戒具罪犯数：</td>
					 <td> <span id="GDJJCount" class="marquee-panel"> 0</span>人</td>
				</tr>
				
				<tr> 
					<td>关押禁闭罪犯数：</td>
					<td> <span id="GYJJCount" class="marquee-panel"> 0</span>人</td>
				</tr>
				
				 <tr>
				 	<td>隔离审查罪犯数：</td>
				 	<td> <span id="GLSCCount" class="marquee-panel"> 0</span>人</td>
				 </tr>
				
				<tr>
					<td>立案侦查罪犯数：</td>
					<td> <span id="LAZCCount" class="marquee-panel"> 0 </span>人</td>
				</tr>
				
				<tr>
					<td>解回重审罪犯数：</td>
					<td> <span id="JHCSCount" class="marquee-panel"> 0</span>人</td>
				</tr>
				
				<tr>
					<td>暂予监外执行罪犯数：</td>
					<td> <span id="ZYJWZXCount" class="marquee-panel">0 </span>人</td>
				</tr>
				
				<tr>
					<td>老病残罪犯数：</td>
					<td> <span id="LBCCount" class="marquee-panel">0 </span>人</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="rightDivStyle right-zb" id="prisonList">
		<H4 align="center">各监所在押罪犯统计</H4>
		<center>
			<div id="prisonerCountPrisonListLoading">
				<table></table>
			</div>
		</center>
	</div>
</div>

</div>
<script type="text/javascript"> 

	var USER_LEVEL= jsConst.USER_LEVEL; 
	
	var thisCusNumber = jsConst.CUS_NUMBER; 
	
	$.parseDone(function() {
		
		$("#rightSide").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		}); 
		
		queryPrisonerCount();
		$("#prisonList").hide();
	});
	/**
	 * 查询押犯数量统计  
	 */
	function queryPrisonerCount() {
		
		var url = "${ctx}/xxhj/zfjbxx/queryXJPrisonerCount?cusNumber=0&drpmntId=0" ;    //新的sql里面要求查全疆人数要求cusNumber=0
		$("#offenderLoading").loading({text:"正在加载中，请稍后..."});
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
	 * 显示实时押犯
	 */
	function parsePrisonerCount(countList) {
		alert("parsePrisonerCount");
	
		for (var i = 0; i<countList.length; i++) {
			
			var key = countList[i].LB; 
			var count =countList[i].RS; 
			alert("key = " + key);
		
			if (key == "1") {                                             //实押罪犯总数
				
				var allCount=count;
				if (count==0){
					
				$("#insideCurrentPrisonerCount").html(count);
				} else {
				
				$("#insideCurrentPrisonerCount").html("<a href='javascript:void(0);' onclick='methodControler(1,this);'>"+ count + "</a>");
				}
				
			} else if (key == "2") {                                                  //实押危安犯总数
				
				var WAFCount=count;
				var rate=(WAFCount/allCount).toFixed(4)*100+"%";                      //计算危安犯与总罪犯数比例
				
				if (count==0){
					
					$("#WAFCount").html(count);
				} else {
					
					$("#WAFCount").html("<a href='javascript:void(0);' onclick='methodControler(2,this);'>"+ count + "</a>");
					$("#rateOfWAFInPrisoner").html("<a href='javascript:void(0);' onclick='methodControler(3,this);'>"+ rate + "</a>");                            
				} 
				
			}else if (key == "4") {                                                        //加戴戒具罪犯数
				
				$("#GDJJCount").html("<a href='javascript:void(0);' onclick='methodControler(4,this);'>"+ count + "</a>");
			
			} else if (key == "5") {                         //关押禁闭罪犯数
				
				$("#GYJJCount").html("<a href='javascript:void(0);' onclick='methodControler(5,this);'>"+ count + "</a>");

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
	}
	/* 方法 */
	function methodControler(type, aId) {
		alert("methodControler type is " + type);
		var obj = $(aId);
		setTitleName("prisonList", aId);
		var title = $("#prisonList  H4").html();
		$("#prisonList").show();
		
		 if (type==3) {
			 
		//实押危安犯与总实押罪犯比
			queryPrisonPrisonerRate();
		} else {
			
			queryPrisonPrisoner(type);
		}
	}

	 /* 查询监狱列表 */
	var allPrisonerList = null; 
	var WAFPrisonerList = null;
	
	function queryPrisonPrisoner(type,query) {

		var url="${ctx}/xxhj/zfjbxx/queryXJPrsnrCountPrisonList?type="+type;
		$("#prisonerCountPrisonListLoading").loading({text:"正在加载中，请稍后..."});
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					$("#prisonerCountPrisonListLoading").loading("hide");
					
					if (data) {
						if (type == 1) {
							allPrisonerList = data;
						} else if (type == 2) {
							WAFPrisonerList = data;
						}
					} 
					if(query == null) {
						loadPrisonList(data);
					}else {
						queryPrisonPrisonerRate();
					}
				},
		   });
	 }
	
	//计算实押危安犯与总实押罪犯比
	 function queryPrisonPrisonerRate() {

	    var list=[];

		if (allPrisonerList != null && WAFPrisonerList != null) {
			
			for (var i=0;i<allPrisonerList.length;i++) {
				
				var data = {};
				
				for(var j=0;j<WAFPrisonerList.length;j++) {
					
					if (allPrisonerList[i].JY_ID == WAFPrisonerList[j].JY_ID) {
						
						data.RS = ((WAFPrisonerList[j].RS/allPrisonerList[i].RS)*100).toFixed(2)+"%";
						data.JY_ID = allPrisonerList[i].JY_ID;
						data.prisonName = allPrisonerList[i].prisonName; 
						
						list[i] = data;
					}
				} 
			}
			loadPrisonList(list);
			
		} else if (allPrisonerList == null &&  WAFPrisonerList != null) {
			
			queryPrisonPrisoner(1,1);
			
		} else  {
			
			queryPrisonPrisoner(2,1);
		}
	} 
	
     /* 加载全疆监狱列表  */	 
	function loadPrisonList(data) {
    	 
		var table=$("#prisonList table" );
	
		table.empty();
		
		for (var i=0;i<data.length;i++) {
			
			var tr=$("<tr></tr>");
			
			tr.append("<td align='left' width='180'>" +data[i].prisonName+ "：</td>");   //监狱名称
			var aItem = "<a href='javascript:void(0);' onclick='toPrisonList("+data[i].JY_ID+",\""+data[i].prisonName+"\")'; >"+data[i].RS+"</a>"
			tr.append("<td align='left' width='80'>"+aItem+"人</td>");            //罪犯人数
			
			table.append(tr);
		}
		$("#prisonerCountPrisonListLoading").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
	});
	}
     
	function setTitleName(titleId, typeId, type) {
	
		var title = $(typeId).parent().parent().parent().children().eq(0).html().split("：")[0];
	
		if (titleId == "prisonList") {
			
			$("#" + titleId + " H4").html("各监所"+title);
		} else {
			
			if (type != null) {
				
				$("#" + titleId + " h4").html(title + "分布情况");
			} else {
				
				$("#" + titleId + " h4").html(title + "信息纪录");
			}
		}
	}
	
    /* 显示监狱信息页面 */
    function toPrisonList(prisonId,prisonName) {
    	alert("prisonId = " + prisonId);
    	alert("prisonName = " + prisonName);
    	var panel = $("#layout1").layout("panel", "east");
    	prisonName = encodeURIComponent(encodeURIComponent(prisonName));
    	alert("encodeURIComponent prisonName = " + prisonName);
    	panel.panel("refresh", "${ctx}/xxhj/zfjbxx/zfjbxx?prisonId="+prisonId+"&prisonName="+prisonName);
    }
     
</script >


