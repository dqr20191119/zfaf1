<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div>	
	<cui:form id="formId_mbsz_edit">
		<div style="margin: 5px 11px 5px 11px;">
	 		<table style="border: #B3D0F4 2px solid;border-collapse:separate;border-spacing:8px;background-color:#F5FAFA;width:100%;height:80">
				<tr>
					<th width="90px">值班类别：</th>
					<td width="200px"><cui:combobox id="cdmCategoryName" name="cdmCategoryId" width = "200" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" ></cui:combobox></td>
					<th width="90px">模板名称：</th>
					<td width="200px"><cui:input id="cdmModeName" name="cdmModeName" width = "200" ></cui:input></td>
					<th width="90px">排班周期：</th>
					<td width="220px"><cui:input id="cdmPeriod" name ="cdmPeriod" validType="naturalnumber" width="180" ></cui:input>&nbsp;&nbsp;&nbsp;<span style="font-weight: bold;">天</span></td>
				</tr>
				<tr>
					<th width="90px">班次数量：</th>
					<td width="200px"><cui:input id="cdmOrderCount" name ="cdmOrderCount" validType="naturalnumber" width="200"></cui:input></td>
					<td></td>	
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		
		<div id="tableOrder_view" style="margin:5px 5px 5px 5px; width: auto; height:560px; overflow :auto; ">
			<table id="tableOrder" >
					<tr>
						<td>
							<div >
								<table style="overflow: auto; ">
									<tr id="subTr">
										<td id="subTd"></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
			</table>
		</div>
	</cui:form>
</div>

<script>

$.parseDone(function() {
	
	var id='${id}';
	
	if(id) {
				
		loadForm("formId_mbsz_edit", "${ctx}/zbgl/mbsz/getById?modelId="+ id+"&param=1", function(data) {

			$("#formId_mbsz_edit").form("setReadOnly", true);      //类别不可修改
			//$("#tableOrder").combobox("option", "readonly", true)
			var order = data.cdmOrderData.split(";");
			$("#cdmOrderCount").textbox("setValue",order.length);
			var orderList = new Array();
			
			for(var i = 0;i < order.length; i++) {
				 
				var postList = new Array();
				var orderId = order[i].split("-")[0];
				var job = (order[i].split("-")[1]).split(",");
				var jobLength = job.length;
				
				var tab = $("<table></table>");
				tab.css({"border-collapse":"separate","border-spacing": "5px","font-weight":"bold"});
				$("#subTd").append(tab);
				
				var item = null
					item=  ("<fieldset style='background-color:#F5FAFA'><legend> 班次信息 </legend>"+
							"<table id='orderTab_" +(i+1)+ "'><tr><td width='80'align='left'>班次名称：</td><td ><input class='coralui-combobox' id='order_"+(i+1)+"' name='cdmDutyOrderFid_"+(i+1)+"'  data-options='\"onSelect\": \"onSelect(" + (i+1) + ")\",\"width\":\"150\",\"readonly\": true' /></td>"+
							"<td width='150'align='right'>值班时间：</td><td><input class='coralui-textbox' id='orderStartTime_"+(i+1)+"' data-options='\"width\":\"150\",\"readonly\": true'/></td>"+
							"<td>~</td><td><input class='coralui-textbox' id='orderEndTime_" +(i+1)+ "' data-options='\"width\":\"150\",\"readonly\": true' /></td>"+
							"<td width='150'align='right'>岗位数量：</td><td><input class='coralui-textbox' id='positionCount_"+(i+1)+"'   value=" + jobLength + " name='positionCount_"+(i+1)+"'   data-options='\"readonly\": true,\"width\":\"150\"' /></td></tr></table></fieldset> ");
						
					tab.append("<tr><td>" +item+ "</td></tr>");
					
					$.parser.parse(tab);
					orderList.push({id : "order_" +(i+1) , value : orderId});
				
					for(var j = 0; j < job.length; j++) { 
						
						var jobId = job[j];
						var tab = $("#orderTab_" +(i+1));
						var tr = $("<tr class='position_" +(i+1)+ "'></tr>");
						tr.append("<td width='60' align='left'>岗位" +(j+1)+ ":</td><td><input class='coralui-combobox' id='post_" +(i+1)+ "_" +(j+1)+ "' name='cdmJobFid_" +(i+1)+ "_" +(j+1)+ "' value=" +jobId+ " data-options='\"width\":\"150\",\"readonly\": true' /></td>")
						tab.append(tr);
						
						tab.css({"border-collapse":"separate","border-spacing": "5px","backgroud-color":"#F5FAFA"});
						$.parser.parse(tab);
						
						postList.push({ id : "post_" +(i+1)+ "_" +(j+1) , value : jobId});
					}
					setOrderSelect(orderList);
					setJobSelect(postList);
			}  
		});
	} 
	$("#tableOrder_view").mCustomScrollbar({
		theme : "minimal-light",
		autoExpandScrollbar : true,
		axis: "y"
	});
});
	
	//根据选中班次加载班次信息
	function onSelect(i) {
		
		var data = {};
		var id = $("#order_" +i).combobox("getValue");
		data.id = id;
		
		$.loading({text:"正在加载中，请稍后..."});
		$.ajax({
			async: false,
			type : 'post',
			url : '${ctx}/zbgl/bcgl/getById',
			data: data,
			dataType : 'json',
			success : function(data) {
				
				$.loading("hide");
				if (data) {
					
					$("#orderStartTime_" +i).val(data.dorStartTime);
					$("#orderEndTime_" +i).val(data.dorEndTime);
				}  
			} 
		});
	}
	/**
	  * 查询并加载班次信息
	  */
	 function setOrderSelect(orderList) {
		
	 	for(var i = 0; i < orderList.length; i++) {
	 		
	 		var id = orderList[i].id;
			var value = orderList[i].value;
			
	 		if(value) {
	 			$("#" + id).combobox( "reload", "${ctx}/zbgl/bcgl/searchAllData");
	 			$("#" + id).combobox( "setValue", value+"");
	 			onSelect(parseInt(i)+1);
	 		}else {
				$("#" + id).combobox( "reload", "${ctx}/zbgl/bcgl/searchAllData");
	 		}
	 	}
	 }

	function addPosition(i) {
		
		$(".position_" +i).remove();
		var tab = $("#orderTab_" +i)
		var postnum = $("#positionCount_" +i).val();
		tab.css({"border-collapse":"separate","border-spacing": "5px"});
		
		var postList = new Array();
		for (var j = 0; j < postnum; j++) {
			
			var tr = $("<tr class='position_" +i+ "'></tr>");
			tr.append("<td width='60' align='left'>岗位" +(j+1)+ ":</td><td><input class='coralui-combobox' id='post_" +i+ "_" +(j+1)+ "' name='cdmJobFid_" +i+ "_" +(j+1)+ "' data-options='\"width\":\"150\"' required='true'/></td>")
			tab.append(tr);
			
			$.parser.parse(tab);
			postList.push({id : "post_" +i+ "_" +(j+1) , value : ""});
		}  
		setJobSelect(postList);
	 }
	 /**
	  * 查询并加载岗位信息
	  */
	 function setJobSelect(postList) {
		
	 	var cdjCategoryId = $("#cdmCategoryName").combobox("getValue");
	 	for(var i = 0; i < postList.length; i++) {
	 		
	 		var id = postList[i].id;
			var value = postList[i].value;
			
				if(value) {
					
					$("#" + id).combobox( "reload", "${ctx}/zbgl/gwgl/searchAllData?cdjCategoryId="+cdjCategoryId);
					$("#" + id).combobox( "setValue", value+"");
				}else{
					
					$("#" + id).combobox( "reload", "${ctx}/zbgl/gwgl/searchAllData?cdjCategoryId="+cdjCategoryId);
			}
	 	}
	 }
</script>