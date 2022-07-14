<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	#prisonList.right-zb td {
	    font-size: 14px;
	    height: 25px;
	    display: table-cell;
	    color: #3d86cc;
	    }
	 #statistics.rightDivStyle li {
	    padding: 0 26px;
	    font-size: 20px;
	    margin-top: 2px;
	}
	#policeCountPrisonList {
		height: 640px;
		overflow: auto;
	}
	#policeCountPrisonList table td{
		padding-top: 6px;
		padding-left: 30px;
		color: #3b89d1;
	}
</style>

<div id="rightSide_pjnmj" style="height:100%">
    <div class="rightDivStyle right-zb" id="statistics">
		<h4 align="center">全疆监内民警数量统计</h4>
	    <center>
		    <ul align="center">
			<li>当前监内民警：<span class="spOne" id="insideCount"></span> 人
		    </ul>
	    </center>
	</div>
	
   	<div class="rightDivStyle right-zb" id="prisonList">
	 	<h4 align="center">各监狱监内民警统计</h4>
	  	<div id="policeCountPrisonList">
	  		<table></table>
	  	</div>
   	</div>
</div>

<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL;  
	var thisCusNumber = jsConst.CUS_NUMBER; 

	$.parseDone(function() {
		
		$("#rightSide_pjnmj").mCustomScrollbar({
			theme: "minimal-light",
			autoExpandScrollbar: true
		});
		
		loadProvMethod();
	});

	function loadProvMethod() {
		
		// 统计在监民警数量
		queryPoliceCount();
		
		// 按监狱统计在监民警数量
		loadPrisonList();		
	};
	
	function queryPoliceCount() {

		var url = "${ctx}/xxhj/jnmj/queryInsidePoliceCount.json";
		$.ajax({
			type : "post",
			url : url,
			success : function(data) {

				if (data) {			
					if (data != "" && data != null) {						
						var count = data[0].INSIDE_POLICE_COUNT;
						$("#insideCount").html(count);
					}
				}
			}
		});
	}

	function loadPrisonList() {
		
		var url = "${ctx}/xxhj/jnmj/queryInsidePoliceCountByPrison";				
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if (data != "" && data != null) {					
					var table = $("#prisonList table");
					table.empty();

					for (var i = 0; i < data.length; i++) {
						var tr = $("<tr> </tr>");
						tr.append("<td>" + data[i].OBD_ORGA_NAME + "：</td>");                  
						tr.append("<td> <a href='javascript:void(0);' class='spTwo' onclick='toPrisonList(" + data[i].OBD_ORGA_IDNTY+ ",\""+ data[i].OBD_ORGA_NAME+ "\");'>"
										+ data[i].COUNT+ "</a> 人</td>");
						table.append(tr);
					}
				}
			}
		});
	}
	
    function toPrisonList(prisonId, prisonName) {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	prisonName= encodeURIComponent(encodeURIComponent(prisonName));
    	panel.panel("refresh", "${ctx}/xxhj/jnmj/jnmj?prisonId=" + prisonId + "&prisonName=" + prisonName);
    }
</script>