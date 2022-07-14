<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/evidence/evidence.css" />
<style>
.form-control {
	width: 100%;
}
.zjxxys{
	position: absolute;
	top: 20px;
	right: 24px;
	width: 268px;
	height: 220px;
	border: 2px dotted #AAAAAA;
	background-color: #FFF;
	box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.40); 
}
.zjxxys img{
	margin: 1px;
	width: 266px;
	height: 218px;
}
.zjxxys .play {
	width: 266px;
	height: 218px;
}
</style>
<center>
<cui:form id="formId_jdd_edit">
<cui:input  type="hidden" required="true" id="yjjdd_id" name="id" value="${model.id }"></cui:input>
<cui:input  type="hidden" required="true" id="mdoUpdtUserId" name="mdoUpdtUserId" value=""></cui:input>
<cui:input  type="hidden" required="true" id="mdoConsultStatus" name="mdoConsultStatus" value="${model.mdoConsultStatus}"></cui:input>
<div style="display:none;" >
<cui:datepicker  required="true" id="mdoUpdtTime"  name="mdoUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
<table class="table" width="90%">
		<tr>
			<td width="15%" ><label>监督单名：</label></td>
			<td width="35%" >
				<cui:input id="mdoMonitorName" name="mdoMonitorName" value="${model.mdoMonitorName }" required="true" componentCls="form-control"></cui:input>
			</td>
			<td width="50%" rowspan="5" colspan="2">
				<div class="zjxxys">
					<div class="play" title="点击播放录像文件..."></div>
					<img id="imgEvidence_edit" alt="未找到图片.."  src="">
					<!-- 图片点击次数计数 -->
					<input id="imgClickNum_edit" value="0" type="hidden"/>
					<div align="center">
					<div style="float:left"><img id="LastImg_edit" title="上一张" style="margin-top:5px;width:10px;height:15px;" src="${ctx }/static/module/evidence/img/left_blueArrowhead.png" /></div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<div style="float:right;"><img id="NextImg_edit" title="下一张" style="margin-top:5px;width:10px;height:15px;" src="${ctx }/static/module/evidence/img/blueArrowhead.png"/></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td><label>推送监狱:</label></td>
			<td>			
				<cui:combobox required="true" id="mdoNoticeCusNumber" name="mdoNoticeCusNumber" value="${model.mdoNoticeCusNumber}"  data="commboxJy" componentCls="form-control" ></cui:combobox>
			</td>
		</tr>
		<tr id="trSendToDepartment_edit">
			<td><label>推送部门:</label></td>
			<td>
				<cui:combobox  id="mdoNoticeDepartment" name="mdoNoticeDepartment" value="${model.mdoNoticeDepartment}" url="" componentCls="form-control" ></cui:combobox>
			</td>
		</tr>
		<tr>
			<td><label>记录地点：</label></td>
			<td><cui:input id="mdoAddr" name="mdoAddr" value="${model.mdoAddr}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td ><label>记录时间:</label></td>
			<td><cui:datepicker id="mdoTime" name="mdoTime" value="${model.mdoTime }" 
					componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
			</td>
		</tr>
		<tr>
			<td><label>问题描述：</label></td>
			<td><cui:textarea id="mdoProblem"  name="mdoProblem"  componentCls="form-control">${model.mdoProblem}</cui:textarea></td>
			<%--<td><cui:textarea id="mdoFeedbackmessage"  name="mdoFeedbackmessage"  componentCls="form-control">${model.mdoProblem}</cui:textarea></td>--%>
			<td><label>反馈：</label></td>
			<td><cui:textarea id="mdoFeedbackmessage"  name="mdoFeedbackmessage"  componentCls="form-control" placeholder="被督办单位填写">${model.mdoFeedbackmessage}</cui:textarea></td>

		</tr>
<%--		<tr>
			<td><label>问题反馈：</label></td>
			<td><cui:textarea id="mdoFeedbackmessage"  name="mdoFeedbackmessage"  componentCls="form-control">${model.mdoFeedbackmessage}</cui:textarea></td>
		</tr>--%>
		<tr style="display:none">
			<td><label>记录人：</label></td>
			<td><cui:input required="true" id="mdoCrteUserId" name="mdoCrteUserId" value="${model.mdoCrteUserId }" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
	</cui:form>
	<div id="buttonDiv" class="dialog-buttons" >
		<cui:button id="buttonDiv_bjzj" label="编辑证据"  onClick="edit"></cui:button>
		<cui:button label="保存"  componentCls="btn-primary" onClick="saveEdit"></cui:button>	
		<%-- <cui:button label="推送"  onClick="BtnSendClick"></cui:button> --%>
	</div>
</center>

<cui:dialog id="dialogId_sendToUser" title="选择推送对象"  autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="auto">

	<cui:input  texticons="" placeholder="Search" onKeyPress="userSearch"></cui:input>
	<div style="height:350px;overflow-y:scroll;">
		<cui:tree id="sendToUserTree" checkable="true" chkboxType="chkboxType" chkStyle="checkbox">
		</cui:tree>
	</div>
	<div class="dialog-buttons" >
		<cui:button label="确定"  onClick="f_save"></cui:button>
		<cui:button label="取消"  onClick="closeDialog"></cui:button>
	</div>
</cui:dialog>

<cui:dialog id="dialogId_editEiv" title="编辑"  autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="1300" height="600">
	<%-- <cui:tabs heightStyle="fill">
		<ul>
			<li><a href="#fragment_evi_ysf">已使用</a></li>
			<li><a href="#fragment_evi_wsf">未使用</a></li>	
		</ul>
		<div id="fragment_evi_wsf" style="padding-top: 5px;">
			<cui:grid id="gridId_evi_wsf" colModel="gridColModel_evi" singleselect="true" 
			fitStyle="fill" datatype="json" onDblClickRow="clickToRight"
				url="" pager="true"></cui:grid>
		</div>
		<div id="fragment_evi_ysf" style="padding-top: 5px;">
			<cui:grid id="gridId_evi_ysf" colModel="gridColModel_evi" singleselect="true" 
			fitStyle="fill" datatype="json" onDblClickRow="clickToLeft"
				url=""></cui:grid>
		</div>
	</cui:tabs> --%>
	<div style="width:100%;height:90%;">
		
		<fieldset style="margin-left:3px;float: left;width: 47%;height: 100%;">
			<legend>未使用的证据</legend>
			<cui:grid id="gridId_evi_wsf" colModel="gridColModel_evi" multiselect="true"
		fitStyle="fill" datatype="json" 
			url="" pager="true"></cui:grid>
		</fieldset>
		
		<div style="margin-left:14px;float: left;margin-top:17%;">
	        <div style="padding-bottom:25px;">
	            <cui:button componentCls="btn-primary btn-lg" label=">" onClick="moveToRight"></cui:button>
	        </div>
	        <div>
	            <cui:button componentCls="btn-primary btn-lg" label="<" onClick="moveToLeft"></cui:button>
	        </div>
	    </div> 
		
		<fieldset style="margin-left:13px;float: left;width: 47%;height: 100%;">
			<legend>已使用的证据</legend>
			<cui:grid id="gridId_evi_ysf" colModel="gridColModel_evi" 
		fitStyle="fill" datatype="json" rowNum="9999" multiselect="true"
			url=""></cui:grid>
		</fieldset>
	
	</div>
	<div class="dialog-buttons" >
		<cui:button label="确定"  onClick="editEivSubmit"></cui:button>
		<cui:button label="取消"  onClick="closeDialog"></cui:button>
	</div>
</cui:dialog>

<script>
var chkboxType = {
	'Y' : 'ps',
	'N' : 'ps'
}
var zjwjlxJson =<%=CodeFacade.loadCode2Json("4.20.32")%>;
var gridColModel_evi= [ {
	label : "id",
	name : "ID",
	width : 100,
	hidden : true,
	key:true
}, {
	label : "证据标题",
	align : "center",
	name : "EIN_TITLE"
}, {
	label : "详细描述",
	align : "center",
	name : "EIN_CONTENT_DESC"
}, {
	label : "记录时间",
	align : "center",
	name : "EIN_CRTE_TIME"
}, {
	label : "证据类型",
	hidden : true,
	align : "center",
	name : "EIN_CONTENT_TYPE_INDC"
},{
	label : "类型",
	name : "EIN_FILE_TYPE_INDC",
	align : "center",
	width : 50,
	formatter : "convertCode",
	revertCode : true,
	formatoptions : {
		'dataStructure' : 'list',
		'data':zjwjlxJson
	} 
	
},{
	label : "操作",
	align : "center",
	width : 50,
	formatter:"formatterA",
}];
function formatterA(cellValue,options,rowObject){
	var result="<span><a href='#' style='color: #4692f0;' onclick='showEvidence(\""+rowObject.EIN_FILE_TYPE_INDC+"\",\""+rowObject.ID+"\");return false;'>查看</a></span>";
	return result;
}


/**
 * 已使用的证据编号集合
 */
var evidenceSqnoList_edit = new Array();
/**
 * 证据类型集合
 */
var varfileTypeList_edit = new Array(); 

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人

var messenger=window.top.messenger;
var evidence=window.top.evidence;
function initSendToUserTree(){
	$.ajax({
		type : 'post',
		url : "${ctx }/common/authsystem/findSyncDeptPoliceForCombotree.json?cusNumber="+cusNumber,
		dataType : 'json',
		success : function(data) {
			
			if(data.exception==undefined){
				$("#sendToUserTree").tree("reload",data);

			}else{
				$.message( {
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}

$.parseDone(function(){
	
	//初始化页面数据
	$("#mdoUpdtUserId").textbox("setValue",userId);
	$('#mdoUpdtTime').datepicker('setDate',new Date());
	$("#imgClickNum_edit").val(0); //重置图片点击次数计数
	$('#LastImg_edit').unbind('click');
	$('#NextImg_edit').unbind('click');
	
	//初始化接收人树（同步树）
	//initSendToUserTree();
	$("#mdoNoticeDepartment").combobox("reload",{
		url:"${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber,
		onLoad: function(){
			$("#mdoNoticeDepartment").combobox("setValue",('${model.mdoNoticeDepartment }').toString());
		}
	});
	
	//若非省局登录则设置为本监狱
	if(jsConst.USER_LEVEL!=1){
		$("#mdoNoticeCusNumber").combobox("option","readonly",true);
		loadReceived();
	}else{
		$("#trSendToDepartment_edit").hide();
		$("#txtSendToUser_edit").textbox("option","required",false);
		$("#mdoIsFromProv").textbox("setValue","1");	//来自省局
	}

	console.log("aqfk monitor edit.jsp parseDone evidence = " + evidence);
	if(evidence){
		/**
		 * 修改已建监督单时是否编辑过证据
		 */
		evidence.isEditEvidence = false;
		/**
		 * 修改已建监督单时是否编辑过接收人
		 */
		evidence.isEditRecipient = false;
	}
	
	loadEvidence();
	
	//详情展示
	console.log("aqfk monitor edit.jsp parseDone messenger.isDetailShow = " + messenger.isDetailShow);
	if(messenger.isDetailShow){
		$("#formId_jdd_edit").form("setIsLabel","isLabel");
		$("#txtSendToUser_edit").textbox("option","onClick","");
		// 监督单状态
		var mdoConsultStatus = "${model.mdoConsultStatus}";
		if(mdoConsultStatus != '1' && (jsConst.USER_LEVEL == 2 || jsConst.USER_LEVEL == 3)){
            $("#buttonDiv_bjzj").hide();
            $("#mdoFeedbackmessage").textbox("option", "isLabel", false);
        }else {
            $("#buttonDiv").hide();
        }
		return;
	}else{
        $("#mdoFeedbackmessage").textbox("option", "readonly", true);
    }
	$("#gridId_evi_wsf").grid("reload","${ctx }/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
	$("#gridId_evi_ysf").grid("reload","${ctx}/monitor/gridSearchRelationMonEvi.json?cusNumber="+jsConst.ORG_CODE+"&monitorSqno=${model.id}");
	
})
//设置证据的分页显示
function setEvidenceShow(evidenceL){
	evidenceSqnoList_edit.splice(0,evidenceSqnoList_edit.length);
	varfileTypeList_edit.splice(0,varfileTypeList_edit.length);
	
	if(evidenceL && evidenceL.length>0){
		for(var i=0;i<evidenceL.length;i++){
			if(evidenceL[i] && evidenceL[i].ID && evidenceL[i]['EIN_FILE_TYPE_INDC']){
				evidenceSqnoList_edit.push(evidenceL[i].ID);
				varfileTypeList_edit.push(evidenceL[i]['EIN_FILE_TYPE_INDC']);
			}	
		}
		if(evidenceL[0]){
			// 类型1图片则显示图片，类型2视频则显示视频播放按钮
			if (evidenceL[0].EIN_FILE_TYPE_INDC == 1){
				getImg("#imgEvidence_edit", evidenceSqnoList_edit[0]);
			} 
			else if (evidenceL[0].EIN_FILE_TYPE_INDC == 2){
				setVideoImg("#imgEvidence_edit");
				filePlay(evidenceSqnoList_edit[0]);
			} 
			//图片滚动查看点击事件
			lastNextClick(evidenceSqnoList_edit,varfileTypeList_edit,1);
		}
	}	
	else{
		$("#imgEvidence_edit").attr('src',"");
		$("#LastImg_edit").hide();
		$("#NextImg_edit").hide();
		$('div.play').hide();
	}
}

//加载关联接收人信息
function loadReceived(){
	
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/monitor/searchRelationMonRec?cusNumber='+jsConst.ORG_CODE+'&monitorSqno=${model.id}',
		
		dataType : 'json',
		success : function(data) {
			if(data.exception==undefined){
				console.log(data);
				var userL=data.mapList;
				//input显示的值
				var sendUserText="";
				
				for(var i=0;i<userL.length;i++){
					if(i==0){
			    		sendUserText=userL[0].MRR_RCPNT_NAME;
			    	}else{
			    		sendUserText=sendUserText+","+userL[i].MRR_RCPNT_NAME;
			    	}
					i++;
				}
				
		
				$("#txtSendToUser_edit").textbox("setValue",sendUserText); 
			}else{
				$.message( {
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}

//加载关联证据信息
function loadEvidence(){
	
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/monitor/searchRelationMonEvi?monitorSqno=${model.id}',
		
		dataType : 'json',
		success : function(data) {
			if(data.exception==undefined){
				var evidenceL=data.mapList;
				setEvidenceShow(evidenceL);
				
			}else{
				$.message( {
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}
//编辑证据
function edit(){
	$("#dialogId_editEiv").dialog("open");
}
//推送对象
function openUserDialog(){
	$("#dialogId_sendToUser").dialog("open");
}
//发送对象查询
function userSearch(e, data) {
	//回车时触发
	if(e.keyCode == 13)      
    {
		$("#sendToUserTree").tree("filterNodesByParam", {
			name : data.value
		});
    }	
}
//保存发送对象
function f_save(){
	var nodes=$("#sendToUserTree").tree("getCheckedNodes");
	//input显示的值
	var sendUserText="";

	for(var i=0;i<nodes.length;i++){
		if(!nodes[i].isParent){
			var number = nodes[i].id;
	    	var name = nodes[i].name;
	    	evidence.sendToUserList[number] = name;
	    	if(i==0){
	    		sendUserText=name;
	    	}else{
	    		sendUserText=sendUserText+","+name;
	    	}
	    	
		}
	}
	$("#txtSendToUser_edit").textbox("setValue",sendUserText);
	
	$("#dialogId_sendToUser").dialog("close");
}
var $gridLeft = $("#gridId_evi_wsf"),
	$gridRight = $("#gridId_evi_ysf");
//新增证据
function clickToRight(e,ui){
	var rowData = $gridLeft.grid("getRowData",ui.rowId);
    $gridRight.grid("addRowData",rowData.id,rowData);
	$gridLeft.grid("delRowData",ui.rowId);
	evidence.isEditEvidence = true;
}
//删除已选证据
function clickToLeft(e,ui){
	var rowData = $gridRight.grid("getRowData",ui.rowId);
    $gridLeft.grid("addRowData",rowData.id,rowData);
	$gridRight.grid("delRowData",ui.rowId);
	evidence.isEditEvidence = true;
}
//新增证据
function moveToRight(){
	var selarrrow = $gridLeft.grid("option", "selarrrow");
	if (selarrrow.length < 1) {
		$.messageQueue({ message : "请先勾选需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
		return false;
	}
	for (var i=selarrrow.length-1;i>=0;i--){
		var rowData = $gridLeft.grid("getRowData",selarrrow[i]);
	    $gridRight.grid("addRowData",rowData.id,rowData);
		$gridLeft.grid('delRowData',selarrrow[i]);
		evidence.isEditEvidence = true;
	}
}
//删除已选证据
function moveToLeft(){
	var selarrrow = $gridRight.grid("option", "selarrrow");
	if (selarrrow.length < 1) {
		$.messageQueue({ message : "请先勾选需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
		return false;
	}
	for (var i=selarrrow.length;i>=0;i--){
		var rowData = $gridRight.grid("getRowData",selarrrow[i]);
	    $gridLeft.grid("addRowData",rowData.id,rowData);
		$gridRight.grid('delRowData',selarrrow[i]);
		evidence.isEditEvidence = true;
	}
}
//编辑证据提交
function editEivSubmit(){
	var allUseEvi=$gridRight.grid("getRowData");
	console.log(allUseEvi);
	setEvidenceShow(allUseEvi);
	
	//选择证据信息的ID(evidence.js)
	evidence.evidenceSqnoList=evidenceSqnoList_edit;
	updateEvidence();
	$("#dialogId_editEiv").dialog("close");
}
</script>