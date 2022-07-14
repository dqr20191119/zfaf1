<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/evidence/evidence.css" />

<%--部门接收监督单列表，之前是推送到人，现在推送到部门 --%>
	<div id="fragment_deptysjdd" style="background-color: #EDEEEE; width: 100%; height: 100%;">
		<cui:form id="queryForm_deptysjdd">
		<table class="table">
			<tr>
				<td align="right"><label>推送时间:</label></td>
				<td><cui:datepicker id="start_deptjssj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_deptjssj" componentCls="form-control"
						startDateId="start_deptjssj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
						
				<td><cui:button cls="cyanbtn" label="查询"
					 onClick="f_search_deptysjdd" componentCls="coral-btn-blue" /></td>
				<td><cui:button label="重置" onClick="resetHandler_deptysjdd" /></td>
			</tr>
			</table></cui:form>
		<cui:toolbar id="toolbarId_deptysjdd" data="toolbarData_deptysjdd"
			onClick="toolbarOnClick_deptysjdd"></cui:toolbar>
		<cui:grid id="gridId_deptysjdd" singleselect="true"  shrinkToFit="true"
			colModel="gridColModel_deptysjdd" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div>

<cui:dialog id="dialogId_jdjc" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false"></cui:dialog>

<cui:dialog id="ysjdd_sendToDept" title="选择推送部门" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="auto">
	<cui:input  type="hidden"  id="deptysjdd_monitorId" ></cui:input>
	<center style="height:60px;overflow-y:scroll;margin-top:20px">
		<cui:combobox  id="sendToDept_DeptId" name="mdoNoticeDepartment" url="" placeholder="选择部门" componentCls="form-control" ></cui:combobox>
	</center>
	<div class="dialog-buttons" >
		<cui:button label="确定"  onClick="ysjdd_sendDept_save"></cui:button>
	</div>
</cui:dialog>

<!-- 单独显示图片或视频信息 -->
	<div id="imgView" class="video-file" style="display:none;overflow:hidden">
		<img id="imgEvi" src="" style="width:100%;height:100%;">
	</div>
	<div id="videoView" class="video-file" style="display:none;overflow:hidden">
		<div class="play" title="点击播放录像文件..."></div>
		<img id="videoEvi" src="">
	</div>
	
<script type="text/javascript"
	src="${ctx}/static/module/evidence/evidence.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/messenger.js"></script>
<script>

var jsConst= window.top.jsConst;
var evidence=window.top.evidence;
var messenger=window.top.messenger;
var commboxJy ;
$.parseDone(function(){
	
	//部门已收监督单
	$("#gridId_deptysjdd").grid("reload","${ctx }/monitor/searchDeptReceivedMonitor.json");


    $.ajax({
        type : "post",
        url : "${ctx}/common/authsystem/findAllJyForCombobox.json",
        dataType : "json",
        async:false,
        success : function(data) {
            commboxJy = data;
        }
            ,
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
        }
    });



})

	var ckztJson  = [ {
		value : 0,
		text : "未查阅"
	}, {
		value : 1,
		text : "已查阅"
	} ];
	
	
	var gridColModel_deptysjdd = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	},{
		label : "监督单状态",
		name : "MOD_STTS_INDC",
		hidden : true
	},{
		label : "是否来自省局推送",
		name : "MDO_IS_FROM_PROV",
		hidden : true
	},{
		label : "查阅状态（针对省局发送的监督单，监狱级用户的查阅状态）",
		name : "MDO_CONSULT_STATUS",
		hidden : true
	}, {
		label : "监督单名称",
		width : 240,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "推送人",
		width : 100,
		align : "center",
		name : "UPDT_USER_NAME"
	}, {
		label : "推送时间",
		align : "center",
		name : "MDO_UPDT_TIME"
	}, {
		label : "查看状态",
		align : "center",
		name : "STATUS",
		formatter:"formatterMrrStatus"
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		hidden : true,
		name : "MDO_PROBLEM"
	}, {
		label : "记录时间",
		align : "center",
		hidden : true,
		name : "MDO_TIME"
	}, {
		label : "记录地点",
		width : 200,
		align : "center",
		hidden : true,
		name : "MDO_ADDR"
	},  {
		label : "接收时间",
		align : "center",
		hidden : true,
		name : "MDO_UPDT_TIME"
	}, {
		label : "接收人",
		width : 100,
		align : "center",
		hidden : true,
		name : "MRR_RCPNT_NAME"
	}, {
		label : "反馈信息",
		width : 100,
		align : "center",
		hidden : true,
		name : "MDO_FEEDBACKMESSAGE"
	}];
	
	
	function formatterMrrStatus(cellValue,options,rowObject){
		var result="";
		if(cellValue=='0'){
			result='<font color="#FF9900">未查阅</font>';
		}else if(cellValue=='1'){
			result="已查阅反馈";
		}else if(cellValue=='2'){
			result='<font color="#FF9900">待反馈</font>';
		}else if(cellValue=='已下发'){
			result='<font color="green">已下发</font>';
		}else if(cellValue=='待下发'){
			result='<font color="#FF0000">待下发</font>';
		}
		return result;
	}

	var toolbarData_deptysjdd = [{
		"id" : "detailed_deptysjdd",
		"label" : "查阅",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	} , {
		"id" : "push_fk",
		"label" : "反馈",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	}/* , {
		"id" : "push_deptysjdd",
		"label" : "下发",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	}  */];
	var toolbarOnClick_deptysjdd = function(event, ui) {
		if("detailed_deptysjdd"==ui.id){
			f_detailedDeptYsjdd();
		}else if("push_deptysjdd"==ui.id){
			f_pushDeptysjdd();
		}
		else if("push_fk"==ui.id) {
	        f_push_fkAction();
        }
	}
	
	//查询部门已收监督单
	function f_search_deptysjdd(){
		var params = {};
		var startTime=$("#start_deptjssj").datepicker("getValue");
		var endTime=$("#end_deptjssj").datepicker("getDateValue");
		if (startTime != "") {
			params['startTime'] = startTime;
		}
		if (endTime != "") {
			params['endTime'] = endTime;
		}
		$("#gridId_deptysjdd").grid("reload","${ctx }/monitor/searchDeptReceivedMonitor.json?params="+encodeURI(JSON.stringify(params)));
	}
	
	//下发到部门，针对省局推送的监督单
	/* function f_pushDeptysjdd(){
		var selrow = $("#gridId_deptysjdd").grid("option", "selrow");
		if (selrow != null){
			var selrowData = $("#gridId_deptysjdd").grid("getRowData", selrow.toString());
			if(selrowData.MDO_IS_FROM_PROV!="1"){
				$.message({
					iframePanel:true,
					message : "下发只是针对于省局推送的监督单！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MOD_STTS_INDC=="2"){
				$.message({
					iframePanel:true,
					message : "该记录已下发！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MDO_CONSULT_STATUS!="1"){
				$.message({
					iframePanel:true,
					message : "请先查阅！",
					cls : "warning"
				});
				return;
			}
			$("#sendToDept_DeptId").combobox("clear");
			$("#sendToDept_DeptId").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
			$("#ysjdd_sendToDept").dialog("open");
			$("#deptysjdd_monitorId").textbox("setValue",selrowData.ID);
			
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	} */
	
	//部门已收监督单详细
	function f_detailedDeptYsjdd(){
		var selrow = $("#gridId_deptysjdd").grid("option", "selrow");
		if (selrow != null){
			$("#dialogId_jdjc").dialog({
				width : 700,
				height : "auto",
				subTitle : '监督单详细',
				url : '${ctx}/monitor/edit?id='+selrow.toString(),
				onClose: function() {
					// 窗口关闭时，刷新监督单列表
					f_search_deptysjdd();
				}
			});
			//messenger.js
			messenger.isDetailShow=true;
			$("#dialogId_jdjc").dialog("open");
		}
/*			var selrowData = $("#gridId_deptysjdd").grid("getRowData", selrow.toString());
			//用户等级
			var USER_LEVEL = jsConst.USER_LEVEL;
			//省局
			if(USER_LEVEL=='1'){
				console.log("省局不存在已收，只会下发给监狱");
				return;
			}
			//监狱
			else if(USER_LEVEL=='2'){
				//监督单来自省局
				if(selrowData.MDO_IS_FROM_PROV=="1"){
					if(selrowData.MDO_CONSULT_STATUS=="1"){
						console.log("已查阅");
						return;
					}else{
						var list ={
			    			"mdoConsultStatus":"1",				//已查阅
			    			"id":selrow.toString(),					 //监督单ID
			    		 };
						 updateMdoStatus(list);
					}
				}
				else{
					if(selrowData.STATUS=="1"){
						console.log("已查阅");
						return;
					}
				}
			}
			//监区
			else if(USER_LEVEL=='3'){
				if(selrowData.STATUS=="1"){
					console.log("已查阅");
					return;
				}
			}
			
			
			//修改查看状态
			var list ={
	    			"mdoConsultStatus":"1",				//已查阅
	    			"id":selrow.toString(),					 //监督单ID
	    		 };
				 updateMdoStatus(list);
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}*/
	}

	function f_push_fkAction() {
        var selrow = $("#gridId_deptysjdd").grid("option", "selrow");
        if (selrow != null) {
            var selrowData = $("#gridId_deptysjdd").grid("getRowData", selrow.toString());

            if(selrowData.STATUS == "已查阅反馈"){
                $.message({
                    iframePanel: true,
                    message: "已反馈，不能重复反馈！",
                    cls: "warning"
                });
                return;
			}else if(selrowData.MDO_FEEDBACKMESSAGE == null || selrowData.MDO_FEEDBACKMESSAGE == ""){
                $.message({
                    iframePanel: true,
                    message: "请先在查阅中填写反馈信息！",
                    cls: "warning"
                });
                return;
			}

            //用户等级
            var USER_LEVEL = jsConst.USER_LEVEL;
            //省局
            if (USER_LEVEL == '1') {
                console.log("省局不存在已收，只会下发给监狱");
                return;
            }
            //监狱
            else if (USER_LEVEL == '2') {
                //监督单来自省局
                if (selrowData.MDO_IS_FROM_PROV == "1") {
                    if (selrowData.MDO_CONSULT_STATUS == "1") {
                        console.log("已查阅");
                        return;
                    } else {
                        var list = {
                            "mdoConsultStatus": "1",				//已查阅
                            "id": selrow.toString(),					 //监督单ID
                        };
                        updateMdoStatus(list);
                    }
                }
                else {
                    if (selrowData.STATUS == "1") {
                        console.log("已查阅");
                        return;
                    }
                }
            }
            //监区
            else if (USER_LEVEL == '3') {
                if (selrowData.STATUS == "1") {
                    console.log("已查阅");
                    return;
                }
            }


            //修改查看状态
			$.confirm("提交后无法对反馈信息进行修改，是否确认提交反馈？", function(r) {
				if(r) {
					var list = {
						"mdoConsultStatus": "1",				//已查阅反馈
						"id": selrow.toString(),					 //监督单ID
					};
					updateMdoStatus(list);
				}
			});
        } else {
            $.message({
                iframePanel: true,
                message: "请先勾选需要处理记录！",
                cls: "warning"
            });
        }
    }
	
	function ysjdd_sendDept_save(){
		var mdoNoticeDepartment = $("#sendToDept_DeptId").combobox("getValue");
		//监督单号
		var monitorDocId = $("#deptysjdd_monitorId").textbox("getValue");
		var list ={
				"mdoNoticeDepartment":mdoNoticeDepartment,
    			"modSttsIndc":"2",					//已下发
    			"id":monitorDocId
    		 };
		updateMdoStatus(list);
		$("#ysjdd_sendToDept").dialog("close");
	}
	
	function resetHandler_deptysjdd(){
		$('#queryForm_deptysjdd').form("reset");
	}


</script>