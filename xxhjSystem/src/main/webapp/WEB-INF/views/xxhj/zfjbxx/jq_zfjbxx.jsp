<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.rightSide  td{
	    align:left;
		border-width: 1px;
		margin-top: 10px;
		margin-left: 5px;
	}
	.drptmntList {
	margin-top: 15px;
	height: 100%;
	overflow: auto;
	padding-left: 20px;
	}
</style>
<cui:dialog id="dialogId_xjPrisonInfo" iframePanel="true" autoOpen="false" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>

<div id="rightSide" class="scorllBar"> 
	<div class="rightDivStyle right-zb" id="offender">
		<h4 align="center" style="position:relative">当前监区罪犯基本情况</h4>
		 	<center>
				<table>
					<tr>
						<td>实押罪犯总数：</td>
						<td> <span id="insideCurrentPrisonerCount" class="marquee-panel"></span> 人 </td>
					</tr>
					
					<tr> 
						<td>实押危安犯总数：</td>
						<td> <span id="WAFCount" > 0</span>人</td>
					</tr>

                    <%--wuzilong 20180714注释--%>
					<%--
					<tr>
						<td >在岗民警与实押罪犯比：</td>
						<td> <span id="rateOfPoliceInPrisoner" class="marquee-panel">0.05% </span></td>
					</tr>
					--%>
					
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
						<td >立案侦查罪犯数：</td>
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
			</center>
	</div>
	
	<div class="rightDivStyle right-zb" id="drptmntList">
		<H4 align="center">各监区在押罪犯统计</H4>
			<center>
				<div class="drptmntList">
					<table ></table>
				</div>
			</center>
	</div>
	
</div>

<script type="text/javascript"> 

 	var USER_LEVEL = jsConst.USER_LEVEL; 
	var cusNumber = jsConst.CUS_NUMBER;  
	var drpmntId = jsConst.DEPARTMENT_ID; 
	
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
		
		$("#offender h4").show();
		
		var url = "${ctx}/xxhj/zfjbxx/queryXJPrisonerCount?cusNumber="+cusNumber+"&drpmntId="+drpmntId;    //改
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
		
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
	
		for (var i = 0; i<countList.length; i++) {
			
			var key = countList[i].LB; 
			var count =countList[i].RS; 
		
			if (key == "1") {                                             //实押罪犯总数
				
				var allCount=count;
				if (count==0) {
					
				$("#insideCurrentPrisonerCount").html(count);
				} else {
				
				$("#insideCurrentPrisonerCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(1);'>"+ count + "</a>");
				}				
			} else if (key == "2") {                                                  //实押危安犯总数
			
				var WAFCount=count;
				var rate=((WAFCount/allCount)*100).toFixed(2)+"%";                      //计算危安犯与总罪犯数比例

				if (count==0) {
					
				$("#WAFCount").html(count);
				} else {
					
					$("#WAFCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(2);'>"+ count + "</a>");
					$("#rateOfWAFInPrisoner").html(rate );                            
				} 				
			}else if (key == "4") {                                                        //加戴戒具罪犯数
				
				$("#GDJJCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(4);'>"+ count + "</a>");
			
			} else if (key == "5") {                         //关押禁闭罪犯数
				
				$("#GYJJCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(5);'>"+ count + "</a>");

			} else if (key == "6") {                         //隔离审查罪犯数
				
				$("#GLSCCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(6);'>"+ count + "</a>");
					
			} else if (key == "7") {                         //立案侦查罪犯数
				
				$("#LAZCCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(7);'>"+ count + "</a>");
			
			} else if (key == "8") {                         //解回重审罪犯数
				
				$("#JHCSCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(8);'>"+ count + "</a>");
			
			} else if (key == "9") {                       //暂予监外执行罪犯数
				
				$("#ZYJWZXCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(9);'>"+ count + "</a>");
			
			} else if (key == "10") {                          //老病残罪犯数
				
				$("#LBCCount").html("<a href='javascript:void(0);' onclick='toPrisonerList(10);'>"+ count + "</a>");
			} 
		}
		
	}
	 
     /**
	 * 弹出罪犯历史查询页面
	 */
	 function toPrisonerList(type) {
    	 
		$("#dialogId_xjPrisonInfo").dialog({
			
			width : 1000,       
			height : 800, 
			title : '罪犯信息',
			url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type="                          //这个query和老系统的无关系 是我自己定义的
					+ type+ "&drpmntId="+ drpmntId+ "&cusNumber=" + cusNumber,
		});

		$("#dialogId_xjPrisonInfo").dialog("open");
	}
	
</script >


