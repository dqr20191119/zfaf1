<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<div style="height:100%;">
			<div style="margin: 5px;">
				车牌号：
				<cui:input id="carfcdCarLcnsIdnty" name="fcdCarLcnsIdnty" width="150"></cui:input>
				车辆进入时间：
				<cui:datepicker id="fcdEnterTimeStart" name="fcdEnterTimeStart" dateFormat="yyyy-MM-dd HH:mm:ss" width="150"></cui:datepicker>
				至：
				<cui:datepicker id="fcdEnterTimeEnd" name="fcdEnterTimeEnd" dateFormat="yyyy-MM-dd HH:mm:ss" width="150"></cui:datepicker>
				车辆离开时间：
				<cui:datepicker id="fcdLeaveTimeStart" name="fcdLeaveTimeStart" dateFormat="yyyy-MM-dd HH:mm:ss" width="150"></cui:datepicker>
				至：
				<cui:datepicker id="fcdLeaveTimeEnd" name="fcdLeaveTimeEnd" dateFormat="yyyy-MM-dd HH:mm:ss" width="150"></cui:datepicker>
			</div>
			<div style="margin: 5px;">
				<cui:button label="查询" onClick="search"></cui:button>
				<cui:button label="进入登记" onClick="addIn" cls="greenbtn"></cui:button>
				<cui:button label="进入修改" onClick="updateIn" cls="cyanbtn"></cui:button>
				<cui:button label="离开登记" onClick="addOut" cls="greenbtn"></cui:button>
				<cui:button label="离开修改" onClick="updateOut" cls="cyanbtn"></cui:button>
				<cui:button label="删除" onClick="remove" cls="deleteBtn"></cui:button>
			</div>
			<div style="height: 90%;">
				<cui:grid id="gridId_foreign" fitStyle="fill" multiselect="true" colModel="gridId_foreign_colModelDate" url="${ctx}/foreignCar/findByPage">
					<cui:gridPager gridId="gridId_foreign"/>
				</cui:grid>
			</div>
		</div>
	</div>
	<cui:dialog id="dialogId_foreign" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者
	var realName = jsConst.REAL_NAME;//
	
	var gridId_foreign_colModelDate = [
		{label:"id", name:"id", width:70, key:true, hidden:true},
		{name:"fcdCarLcnsIdnty", label:"车牌号码", align:"center", width:90},
		{name:"fcdCarCmpny", label:"车辆所属单位", align:"center", width:90}
		/* {name:"FCD_ENTER_TIME",label:"进入时间",align:"center",width:90},
		{name:"FCD_CAR_LCNS_IDNTY",label:"车号 ",align:"center",width:80},
		{name:"FCD_CAR_TYPE_INDC",label:"车型",align:"center",width:80,formatter:typeFormatter},
		{name:"FCD_CARGO",label:"所载货物",align:"center",width:80},
		{name:"FCD_REASON",label:"事由",align:"center",width:90},
		{name:"FCD_DIRECTION",label:"所到地点",align:"center",width:90},
		{name:"FCD_OPRTN_IN_POLICE",label:"进入带车人",align:"center",width:80},
		{name:"FCD_CHECK_PEOPLE",label:"审批人",align:"center",width:80},
		{name:"FCD_REPORT_IN_PEOPLE",label:"进入报告人",align:"center",width:80},
		{name:"FCD_RECORD_IN_PEOPLE",label:"进入记录人",align:"center",width:80},
		{name:"FCD_LEAVE_TIME",label:"离开时间",align:"center",width:90},
		{name:"FCD_OPRTN_OUT_POLICE",label:"离开带车人",align:"center",width:80},
		{name:"FCD_REPORT_OUT_PEOPLE",label:"离开报告人",align:"center",width:80},
		{name:"FCD_RECORD_OUT_PEOPLE",label:"离开记录人",align:"center",width:80} */
	];


	//车辆类型Formatter
	function typeFormatter(cellvalue, options, rawObject){
		if(cellvalue=='1'){
			return "小型车";
		}else if(cellvalue=='2'){
	  		return "中型车";
	    }else if(cellvalue=='3'){
	    	return "大型车";
	    }else{
	    	return "";
	    }
	}
	
	function search(){
		var carfcdCarLcnsIdnty = $('#carfcdCarLcnsIdnty').textbox('getValue');
		var fcdEnterTimeStart = $('#fcdEnterTimeStart').datepicker('getValue');
		var fcdEnterTimeEnd = $('#fcdEnterTimeEnd').datepicker('getValue');
		var fcdLeaveTimeStart = $('#fcdLeaveTimeStart').datepicker('getValue');
		var fcdLeaveTimeEnd = $('#fcdLeaveTimeEnd').datepicker('getValue');
		var queryCondition = {};// 查询条件对象
		
		if (carfcdCarLcnsIdnty) {
			queryCondition["fcdCarLcnsIdnty"] = carfcdCarLcnsIdnty;
		}
		if (fcdEnterTimeStart) {
			queryCondition["fcdEnterTimeStart"] = fcdEnterTimeStart;
		}
		if (fcdEnterTimeEnd) {
			queryCondition['fcdEnterTimeEnd'] = fcdEnterTimeEnd;
		}
		if (fcdLeaveTimeStart) {
			queryCondition['fcdLeaveTimeStart'] = fcdLeaveTimeStart;
		}
		if (fcdLeaveTimeEnd) {
			queryCondition['fcdLeaveTimeEnd'] = fcdLeaveTimeEnd;
		}
		// 将查询条件对象转化为Json字符串，并赋予对象obj
		$("#gridId_foreign").grid('option', 'postData', {obj: JSON.stringify(queryCondition)});
		$("#gridId_foreign").grid('reload');
	}
	
	
	function addIn() {
		open(800, 650, "${ctx}/foreignCar/save?flag=0","增加外来进入车辆");
	}
	
	function updateIn() {
		var sel = $('#gridId_foreign').grid("option", "selarrrow");
		if (sel.length < 1) {
			$.message({
				message : "请先勾选需要编辑的记录！",
				cls : "warning"
			});
			return false;
		}
		if (sel.length > 1) {
			$.message({
				message : "请只勾选一条需要编辑的记录！",
				cls : "warning"
			});
			return false;
		}
		var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
		var	url = "${ctx}/foreignCar/save?flag=0&id=" + rowData.ID;
		open(800, 650, url, "修改进入车辆");
	}

	function addOut() {
		var sel = $('#gridId_foreign').grid("option", "selarrrow");
		if (sel.length < 1) {
			open(800, 650, "${ctx}/foreignCar/save?flag=1","外来离开车辆");
		}
		else{
			var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
			open(800, 650, "${ctx}/foreignCar/save?flag=1&id=" + rowData.ID,"外来离开车辆");
		}

	}

	function updateOut() {
		var sel = $('#gridId_foreign').grid("option", "selarrrow");
		if (sel.length < 1) {
			$.message({
				message : "请先勾选需要编辑的记录！",
				cls : "warning"
			});
			return false;
		}
		if (sel.length > 1) {
			$.message({
				message : "请只勾选一条需要编辑的记录！",
				cls : "warning"
			});
			return false;
		}
		var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
		var	url = "${ctx}/foreignCar/save?flag=1&id=" + rowData.ID;
		open(800, 650, url, "修改离开车辆");
	}

	function open(dialog_width, dialog_height, url, label) {
		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_foreign").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : label
			});
			$("#dialogId_foreign").dialog("open");
		}
	}
	
	function remove() {
		var ids = [];
		var sel = $("#gridId_foreign").grid("option", "selarrrow");
		for(var i=0;i<sel.length;i++){
			var rowData = $('#gridId_foreign').grid("getRowData", sel[i]);
	        ids.push(rowData.ID);
	    };
		if (sel.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/foreignCar/deleteByIds",
						type : "post",
						data : {"ids":ids},
						dataType : 'json',
						traditional: true,
						success : function(data) {
							if (data.success == true) {
								$("#gridId_foreign").grid("reload");
								$.message({ message : data.msg, cls : "success" });
							} else {
								$.message({ message : data.msg, cls : "warning" });
							}
						}
					});
				}
			});
		} else {
			$.message({
				message : "请选择要删除的记录",
				cls : "warning"
			});
		}
	}
	
	function updateforeignList(event, id, node){
		var url = "${ctx}/foreignCar/findByPage.json";
		$("#gridId_foreign").grid("reload", url);
	}
	

	//双击详情
	function detail(e, ui){
		var	url = "${ctx}/foreignCar/detail?id=" + ui.rowId;
		open( 800, 650, url, "外来车辆详情");
	}
	 
</script>