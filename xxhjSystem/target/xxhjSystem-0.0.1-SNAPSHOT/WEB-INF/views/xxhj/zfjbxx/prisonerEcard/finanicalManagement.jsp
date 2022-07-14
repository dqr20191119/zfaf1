<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.text_center {
	vertical-align: middle !important;
	font-size: 12px;
	font-weight: bold;
	}
.foot_left .account {
	
	width: 1000px;
	height: 170px;
}
.foot_left .account.p {
	float: center;
	width: 420px;
	text-align: center;
	}
.FMyangshi{ 
	width:98%;
	margin:10px auto;
	align:center;
	} 
.foot_left p{
	text-align:center;
	font-size:15px;
	width:"98%"
	}
#formId_FinanicalAccount table th {
	font-weight:normal;
	text-align:right;
	font-size:13px;
	}
.div1{ 
	width:47.5%; 
	height:auto; 
	position:absolute; 
	top:200px; 
	left:10px; 
	border: #B3D0F4 2px solid; 
	margin:5px 5px 5px 0px;
	background-color:#F5FAFA;
	} 
 .div3{ 
 	width:47.5%; 
	height:auto; 
	position:absolute; 
	top:200px; 
	left:510px; 
	border: #B3D0F4 2px solid;
	margin:5px 0px 5px 5px;
	background-color:#F5FAFA;
	}
</style>

<div class="foot_left">
	<div style="margin:0px 10px 10px 10px">
		<cui:form id="formId_FinanicalAccount">
			<table style="width: 100%; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:8px; background-color:#F5FAFA;">
				<tr>
					<td colspan="8" align="center" style="font-size: 17px; font-weight: bold;">罪犯账户信息</td>
				</tr>
				<tr>
					<th>罪犯编号：</th>
					<td ><cui:input id="PAC_PRSNR_IDNTY" name="PAC_PRSNR_IDNTY" width="120"></cui:input></td>
					<th>罪犯姓名：</th>
					<td ><cui:input id="PAC_OPEN_DATE" name="PAC_PRSNR_NAME"  width="120"></cui:input></td>
					<th>开户日期：</th>
					<td><cui:input id="PAC_OPEN_DATE" name="PAC_OPEN_DATE" width="120"></cui:input></td>
					<th>账户状态：</th>
					<td><cui:input id="PAC_STTS_INDC" name="PAC_STTS_INDC" width="120"></cui:input></td>
				</tr>
				<tr>
					<th>基本金：</th>
					<td><cui:input id="PAC_BASE_AMNT" name="PAC_BASE_AMNT" width="120"></cui:input></td>
					<th>消费金：</th>
					<td><cui:input id="PAC_CONSUME_AMNT" name="PAC_CONSUME_AMNT"  width="120"></cui:input></td>
					<th>储备金：</th>
					<td><cui:input id="PAC_STORE_AMNT" name="PAC_STORE_AMNT" width="120"></cui:input></td>
					<th>总金额：</th>
					<td><cui:input id="PAC_TOTAL_AMNT" name="PAC_TOTAL_AMNT" width="120"></cui:input></td>
				</tr>
			</table>
		</cui:form>
	</div>
	<div id="income" class="div1">
		<div class="FMyangshi">
			<p align="center" style="font-size: 17px; font-weight: bold;">罪犯收入信息</p>
		</div>
		<cui:grid id="gridId_income" colModel="gridColModel_income" fitStyle="fill" height="480" pager="true" rowNum="10"
			url="${ctx}/xxhj/zfjbxx/listPrisonerFinanical?type=1&pinCusNumber=${pbdCusNumber}&pinPrsnrIdnty=${pbdPrsnrIdnty}"></cui:grid>
	</div> 

	<div id="pay" class="div3">
		<div class="FMyangshi">
			<p align="center" style="font-size: 17px; font-weight: bold;">罪犯支出信息</p>
		</div>
		<cui:grid id="gridId_pay" colModel="gridColModel_pay" fitStyle="fill" height="480" pager="true" rowNum="10"
		url="${ctx}/xxhj/zfjbxx/listPrisonerFinanical?type=2&ppaCusNumber=${pbdCusNumber}&ppaPrsnrIdnty=${pbdPrsnrIdnty}">
		</cui:grid>
	</div> 

<!-- 	<div class="consume">
		<div class="yangshi">
			<p align="center" style="font-size:20px;">罪犯消费明细</p>
		</div>
		<div class="notData" align="center">暂无数据!</div>
		<div class="timeConsumeBox"></div>
	</div> -->
 
</div>
<script type="text/javascript">

	$.parseDone(function() {
		
	    var prisonerId = "<%=request.getParameter("pbdPrsnrIdnty")%>";
		var cusNumber =  "<%=request.getParameter("pbdCusNumber")%>";
		var url = "${ctx}/xxhj/zfjbxx/listPrisonerFinanical?type=3&pacCusNumber=${pbdCusNumber}&pacPrsnrIdnty=${pbdPrsnrIdnty}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				if (data) {
					$("#formId_FinanicalAccount").form("setReadOnly",true);
					$("#formId_FinanicalAccount").form("loadData",data.data[0]);
					
				}
			},
		});
		queryPrisonerConsume(cusNumber, prisonerId)
	});

	var gridColModel_income = [{
		
		label : "收入类型",
		name : "PIN_TYPE",
		align:"center"
	}, {
		label : "收入金额",
		name : "PIN_AMNT",
		align:"center"
	}, {
		label : "到账日期",
		name : "PIN_DATE",
		align:"center"
	} ]

	/**
	 * 查询罪犯支出信息
	 */
	var gridColModel_pay = [{
		
		label : "支出类型",
		name : "PPA_TYPE",
		align:"center"
	}, {
		label : "支出金额",
		name : "PPA_AMNT",
		align:"center"
	}, {
		label : "扣除日期",
		name : "PPA_DATE",
		align:"center"
	} ]
	/**
	 * 查询罪犯消费明细
	 */
	function queryPrisonerConsume(cusNumber, prisonerId) {

		var url = "${ctx}/xxhj/zfjbxx/listPrisonerFinanical?type=4&pcoCusNumber=${pbdCusNumber}&pcoPrsnrIdnty=${pbdPrsnrIdnty}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				data = data.data;
				if (data) {

					loadPrisonerConsume(data);
				} else {
				}
			},
		});
	}
	/**
	 * 加载罪犯消费明细
	 */
	function loadPrisonerConsume(data) {

		if (data.length == 0) {
			
			$(".foot_right .notData").show();
			return;
		} else {
			
			$(".foot_right .notData").hide();
		}
		$(".timeConsumeBox").append( "<div class='detailed'></div><div class='shadow'></div>");
		$(".detailed").append("<div class='date'></div><div class='time'><div class='time_thread'></div></div>");

		var str2 = "";
		var pcdTimeOne = null;
		var aidNum = 1;
		var priceCount = 0;

		for (var i = 0; i < data.length; i++) {
			
			if (pcdTimeOne == null || pcdTimeOne != data[i].PCO_TIME) {
				
				if (pcdTimeOne != null) {
					
					str2 += "<tr>" + "<td></td>" + "<td >总金额：</td>" + "<td>"
							+ priceCount + "</td>" + "</tr>";
					priceCount = 0;
					str2 += "</table></div></div>";
				}
                /* 第一次从这里走 */
				if (i == 0) {
					
					str2 += "<div id='a" + aidNum + "' class='time_styleOne'><div class='heading'><p>消费详单</p></div><div class='empty'></div><div class='form'><table>";
				} else {
					
					aidNum++;
					str2 += "<div id='a" + aidNum + "' class='time_styleTwo'><div class='heading'><p>消费详单</p></div><div class='empty'></div><div class='form'><table>";
				}
				str2 += "<tr class='trOne'>" + "<td width='170'>商品名称</td>"
						+ "<td width='80'>单价</td>" + "<td width='80'>数量</td>"
						+ "</tr>" + "<tr>" + "<td>" + data[i].PCO_WARE_NAME
						+ "</td>" + "<td>" + data[i].PCO_UNIT_PRICE + "</td>"
						+ "<td>" + data[i].PCO_COUNT + "</td>" + "</tr>";
						
				priceCount += (data[i].PCO_UNIT_PRICE * data[i].PCO_COUNT);
				
			} else {
				
				str2 += "<tr>" + "<td>" + data[i].PCO_WARE_NAME + "</td>"
						+ "<td>" + data[i].PCO_UNIT_PRICE + "</td>" + "<td>"
						+ data[i].PCO_COUNT + "</td>" + "</tr>";
				priceCount += (data[i].PCO_UNIT_PRICE * data[i].PCO_COUNT);
			}
			pcdTimeOne = data[i].PCO_TIME;
		}
		
		priceCount = countPrice(priceCount);
		str2 += "<tr>" + "<td></td>"
				+ "<td style='text-align: right;font-weight: bold;'>总金额：</td>"
				+ "<td>" + priceCount + "</td>" + "</tr>";
		str2 += "</table></div></div>";
		$(".shadow").append(str2);

		var pcdTimeTwo = null;
		var str = "<ul>";
		var bidNum = 0;
		
		for (var i = 0; i < data.length; i++) {

			if (pcdTimeTwo != data[i].PCO_TIME) {

				var height = $(".shadow #a" + bidNum).height();
				bidNum++;
				var time = getValue(data[i].PCO_TIME);
				
				if (i == 0) {
					
					str += "<li style='height: 103px;'>" + 33333 + "</li>";
					$(".time_thread").append("<p class='p1'></p>");
				} else {
					
					str += "<li style='height: 103px;margin-top:"
							+ (height - 83) + "px;'>" + 3333+"</li>";
					$(".time_thread").append("<p class='p1' ><img src='</p>");
				}
			}
			pcdTimeTwo = data[i].PCO_TIME;
		}
		$(".time_thread").css("height", ($(".time_thread").height() - 16));

		str += "</ul>";
		$(".date").append(str);
	}
</script>
