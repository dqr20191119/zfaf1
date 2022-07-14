<%@ page language="java" contentType="text/html; chaPAR_PRISONER_COUNTet=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<style>
.jndt_count ul {
	float: left;
	padding: 10px 30px 10px 30px;
	color: #0b6e9a;
}

.jndt_count li {
	font-size: 25px;
	font-weight: normal;
}
.list li{
	font-size: 20px;
	padding:5px 0px 5px 0px;
	color:
}
</style>
<div>
	<cui:form id="formId_jndt_viewCount">
		<table class="table">
			<tr>	
				<th>类别：</th>
				<td><cui:combobox id="combobox_OutCategory" data="comboboxData_jcsy" name="parOutCategory"></cui:combobox></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div class="jndt_count">
		<fieldset>
			<legend>今日监内动态人员统计 </legend>
			<ul>
				<li>外出警力总计：<span id="wcjl" class="jndt">0</span></li>
				<div class="list" id="wcjlList"></div>
			</ul>
			<ul>
				<li>未回警力：<span id="whjl" class="jndt">0</span></li>
				<div class="list" id="whjlList"></div>
			</ul>
			<ul>
				<li>出工罪犯总计：<span id="cgzf" class="jndt">0</span></li>
				<div class="list" id="cgzfList"></div>
				
			</ul>
			<ul>
				<li>未回罪犯：<span id="whzf" class="jndt">0</span></li>
				<div class="list" id="whzfList"></div>
			</ul>
		</fieldset>
	</div>

</div>

<script type="text/javascript">
	var thisCusNumber = jsConst.CUS_NUMBER;
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
    var countData;
    
	$.parseDone(function() {
		search();
	});
	
	function search() {
		
		var outCategory = $("#combobox_OutCategory").combobox("getValue");
		$.ajax({
			async : false,
			type : 'post',
			url : '${ctx}/xxhj/jndt/countPeople',
			data : {
				cusNumber : thisCusNumber,
				parOutCategory :outCategory
			},
			dataType : 'json',
			success : function(data) {
				parseData(data);
				countData = data;
				$(".list").html('');
			},
		});
	}
	
	function parseData(data) {
		$("span.jndt").html('0');
		for(var i = 0; i < data.length; i++) {
			
			if(data[i].PAR_DPRTMNT_ID == '0') {
				
				if(data[i].LB == '1') {
					var allPoliceCount = data[i].PAR_POLICE_COUNT;
					var allPriosnerCount = data[i].PAR_PRISONER_COUNT;
					$("#wcjl").html("<a href='javascript:void(0);' onClick='queryDrpmntList(wcjl);'>" + allPoliceCount+ "</a>");
				 	$("#cgzf").html("<a href='javascript:void(0);' onClick='queryDrpmntList(cgzf);'>" + allPriosnerCount+ "</a>"); 
				} else {
					var outPoliceCount = data[i].PAR_POLICE_COUNT;
					var outPriosnerCount = data[i].PAR_PRISONER_COUNT;
					$("#whjl").html("<a href='javascript:void(0);' onClick='queryDrpmntList(whjl);' style='color:#ec1208;'>" + outPoliceCount+ "</a>");
					$("#whzf").html("<a href='javascript:void(0);' onClick='queryDrpmntList(whzf);' style='color:#ec1208;'>" + outPriosnerCount+ "</a>");
				}
			}
		}
	}

	function queryDrpmntList(id) {
		//外出警力
		var item = "<ul>";
		if(id.id == 'wcjl') {
			$("#wcjlList").html('');
			
			var wcjlList = $("#wcjlList");
			
			for(var i = 0; i < countData.length; i++) {
				if(countData[i].PAR_DPRTMNT_ID != '0' && countData[i].LB == '1' ) {
					
					var allPoliceCount = countData[i].PAR_POLICE_COUNT;
					var drpmntName = convertColVal(combobox_bm, countData[i].PAR_DPRTMNT_ID);
					item = item+ "<li>"+drpmntName+" &nbsp;:&nbsp; "+allPoliceCount+"</li>";
				}
			}
			item = item + "</ul>";
			wcjlList.append(item);
			
		} else if(id.id == 'cgzf') {
			
			$("#cgzfList").html('');
			var cgzfList = $("#cgzfList");
			
			for(var i = 0; i < countData.length; i++) {
				if(countData[i].PAR_DPRTMNT_ID != '0' && countData[i].LB == '1' ) {
					var allPrisonerCount = countData[i].PAR_PRISONER_COUNT;
					var drpmntName = convertColVal(combobox_bm, countData[i].PAR_DPRTMNT_ID);
					item = item+ "<li>"+drpmntName+"&nbsp;:&nbsp;"+allPrisonerCount+"</li>";
				}
			}
			item = item + "</ul>";
			cgzfList.append(item);
			
		} else if(id.id == 'whjl') {
			
			$("#whjlList").html('');
			var whjlList = $("#whjlList");
			
			for(var i = 0; i < countData.length; i++) {
				
				if(countData[i].PAR_DPRTMNT_ID != '0' && countData[i].LB == '0' ) {
					
					var allPoliceCount = countData[i].PAR_POLICE_COUNT;
					var drpmntName = convertColVal(combobox_bm, countData[i].PAR_DPRTMNT_ID);
					item = item+ "<li>"+drpmntName+"&nbsp;:&nbsp;"+allPoliceCount+"</li>";
				}
			}
			item = item + "</ul>";
			whjlList.append(item);
			
		}else if(id.id == 'whzf') {
			
			$("#whzfList").html('');
			var whzfList = $("#whzfList");
			for(var i = 0; i < countData.length; i++) {
				
				if(countData[i].PAR_DPRTMNT_ID != '0' && countData[i].LB == '0' ) {
					
					var allPrisonerCount = countData[i].PAR_PRISONER_COUNT;
					var drpmntName = convertColVal(combobox_bm, countData[i].PAR_DPRTMNT_ID);
					item = item+ "<li>"+drpmntName+"&nbsp;:&nbsp;"+allPrisonerCount+"</li>";
				}
			}
			item = item + "</ul>";
			whzfList.append(item);
		}
	}
	
	function clear() {
		$("#formId_jndt_viewCount").form("reset");
	}
</script>