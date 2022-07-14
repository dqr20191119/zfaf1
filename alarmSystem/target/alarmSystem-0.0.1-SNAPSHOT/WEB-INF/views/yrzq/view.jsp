<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:grid id="gridId_swdb" rownumbers="true"  width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="title" align="center" formatter="formatEvidenceXwzc">名称</cui:gridCol>
			<cui:gridCol name="state" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_rwzt}">任务状态</cui:gridCol>
			<cui:gridCol name="isTimeout" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_sfcs}">是否超时</cui:gridCol>
			<cui:gridCol name="startTime" align="center">开始时间</cui:gridCol>
			<cui:gridCol name="endTime" align="center">结束时间</cui:gridCol>
			<cui:gridCol name="dataType" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_sjly}">数据来源</cui:gridCol>
			<cui:gridCol name="mark" align="center">备注</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_swdb" />
	</cui:grid>
</div>
<script>
	var comboboxData_sjly=[{value:'0',text:'本部门的计划日程'},{value:'1',text:'事务督办'},{value:'2',text:'任务下达'},{value:'3',text:'视频督察'},{value:'4',text:'设备维修'},{value:'5',text:'风险防控措施'}];//数据来源
	var comboboxData_rwzt=[{value:'0',text:'待执行'},{value:'1',text:'未完成'},{value:'2',text:'已完成'}];//任务状态
	var comboboxData_sfcs=[{value:'0',text:'未超时'},{value:'1',text:'已超时'}];//是否超时
	$.parseDone(function() {
		var dateTime = '${dateTime}';
		var departId = '${departId}';
		var state = '${state}';
		$("#gridId_swdb").grid("reload","${ctx}/wghgl/yrzq/searchSwdbList?dateTime="+dateTime+"&departId="+departId+"&state="+state); 
	});
	
	function formatEvidenceXwzc(cellvalue, options, rawObject) {
		debugger;
		var cpsLx = rawObject.cpsLx;
		var html = "";
		if(cpsLx){
			html =  "<a href='javascript: void(0);' onclick=\"opensxt('"+cpsLx+"','"+rawObject.startTime+"','"+rawObject.endTime+"')\" style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		}else{
			html =  "<span  style=' margin-left: 5px;'>"+cellvalue+"</span>";
		}
		return html;	
	}
	function opensxt(sxt,sttime,fhtime){
		var stime = sttime;
		var etime = fhtime;
		$.ajax({
			type : 'post',
			url : '${ctx}/wghgl/yrzq/openCarame.json?xlId='+sxt,
			dataType : 'json',
			success : function(data) {
				var datalength = data.length;
				if(datalength>0){
					videoClient.setLayout(datalength);
				}
				for(var i=0; i<datalength; i++){
					var cameraId = data[i].ID;
					var cameraName = data[i].CBD_NAME;
					
					if( !_validate(stime, etime) ){
						return false;
					}
					var curLayout;
					var layout;
					deskLayout = datalength;
					curLayout = videoClient.curLayout;
					layout = {
							'layout': deskLayout,
							'last': curLayout
						};
					var index = 0;
					var videoList = new Array();
					var video = null;
					if(index_2 == deskLayout){
						index_2 = 0;
					}
					//selectedStyle(tId);
					video = {
						index : index_2,
						cameraId : cameraId,
						cameraName: cameraName,
						startTime : stime,
						endTime : etime
					};
					videoList.push(video);
					index_2++;
					videoClient.playbackHandle(videoList, layout);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
		
	}
	
	</script>