<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:tabs id="tabsId_yjjl_tj" heightStyle="fill">
	<ul>
       <li><a href="#tabsId_yjjl_tj_1">全部</a></li>
       <li><a href="#tabsId_yjjl_tj_2">按类型</a></li>
    </ul>
   	<div id="tabsId_yjjl_tj_1">
   		<cui:form id="formId_yjjl_tj_1">
	   		<table class="table">			
				<tr>
					<td>日期：</td>
					<td>
						<cui:datepicker name="ehrTimeStart" dateFormat="yyyy-MM-dd" required="true"></cui:datepicker>
						<cui:datepicker name="ehrTimeEnd" dateFormat="yyyy-MM-dd" required="true"></cui:datepicker>
					</td>				 
					<td>
						<cui:button label="查询" componentCls="btn-primary" onClick="search1"/>
						<cui:button label="重置" onClick="reset1"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<div id="divId_yjjl_tj_1"></div>
   	</div>
   	<div id="tabsId_yjjl_tj_2">
   		<cui:form id="formId_yjjl_tj_2">
	   		<table class="table">			
				<tr>
					<td>类型：</td>
					<td><cui:combobox name="epiPlanType" data="combobox_yjct_planType" required="true"></cui:combobox></td>
					<td>时间类型：</td>
					<td><cui:combobox name="timeType" data="combobox_yjct_timeType" required="true"></cui:combobox></td>
				</tr>
				<tr>	
					<td>日期：</td>
					<td colspan="2">
						<cui:datepicker name="ehrTimeStart" dateFormat="yyyy-MM-dd" required="true"></cui:datepicker>
						<cui:datepicker name="ehrTimeEnd" dateFormat="yyyy-MM-dd" required="true"></cui:datepicker>
					</td>				 
					<td>
						<cui:button label="查询" componentCls="btn-primary" onClick="search2"/>
						<cui:button label="重置" onClick="reset2"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<div id="divId_yjjl_tj_2"></div>
   	</div>
</cui:tabs>
  
<script>
	 	
 	var ehrType = "";
 	$.parseDone(function() {
 		
 		ehrType = '${type}';
	});

	function search1() {		
	
		var validFlag = $("#formId_yjjl_tj_1").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yjjl_tj_1").form("formData");
		formData.ehrType = ehrType;
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yjjl/tj',
			data: formData,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function search2() {		
	
	}	
	
	function reset1() {
		
		$("#formId_yjjl_tj_1").form("reset");
	}
	
	function reset2() {
		
		$("#formId_yjjl_tj_2").form("reset");
	}	 	
</script>