<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:toolbar id="toolbarId_launch" data="toolbarData_launch"
	onClick="toolbarOnClick_launch"></cui:toolbar>
<cui:grid id="gridId_launch" singleselect="true" shrinkToFit="true" 
colModel="gridColModel_launch" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="launchdetaildialog" autoOpen="false"
 iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>		
<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	
	var gridColModel_launch = [ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	}, {
		label : "通报日期",
		name : "INO_INSPECT_TIME",
		align:"center",
		width : 150
	}, {
		label : "名称",
		align:"center",
		width : 400,
		name : "INO_INSPECT_NAME"
	}, {
		label : "编辑人",
		align:"center",
		width : 150,
		name : "INO_CRTE_USER_NAME"
	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterDetail",
	} ];
	function showInspect(inspectId){
		window.open('${ctx}/xctb/show?inspectId='+inspectId);
	}
	function formatterDetail(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='showInspect(\""+rowObject.ID+"\");return false;'>详细</a></span>";
		return result;
	}
	
	var toolbarData_launch = [ {
		"id" : "launchdetail",
		"label" : "发起整改",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	}];
	
	var toolbarOnClick_launch = function(event, ui) {
		var id = ui.id;
		if (id == 'launchdetail') {
			launch.f_launchdetail();
		}
	}
	
	function queryLaunchList(cusNumber){
		var url = "${ctx}/xxyp/change/launchListPage.json";
		var data = {"inoNoticeCusNumber" : cusNumber};
		$("#gridId_launch").grid("option", "postData", data);
		$("#gridId_launch").grid("reload",url);
	}
	
	$.parseDone(function(){
		
		queryLaunchList(cusNumber);
		
		launch={
			f_launchdetail:function(){
				var selrow = $('#gridId_launch').grid("option", "selrow");//获取选中行的id
				if(selrow != null){
					$("#launchdetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '发起整改',
						url : '${ctx}/xxyp/change/launchDetailDialog?id='+selrow,
					});
					$("#launchdetaildialog").dialog("open");
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},	
		}
	});
</script>