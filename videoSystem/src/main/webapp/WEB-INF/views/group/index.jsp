<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoPlan.css" />

<style>
.form-control {
	width: 100%;
}
.yhsd{
	display:none;
}
.transfer_splitDiv {
    float:left;
    margin-top:40%;
    margin-left:10px;
}
.transfer_btn {
    padding-bottom:25px
}
</style>

<div style="height: 100%;">
	<cui:layout id="group-layout" fit="true">
		<cui:layoutRegion region='west' split="false" style="width: 300px"
			maxWidth="300" url="${ctx}/groupManage/groupTreePage"
			onResize="initTreebox">
		</cui:layoutRegion>
		<cui:layoutRegion region='center' split="false" onLoad="" onResize="">
			<cui:tabs id="tabs11" heightStyle="fill" onActivate="tabs11_onActivate">
				<ul>
					<li><a href="#fragment-tjqz">添加群组</a></li>
					<li><a href="#fragment-glsb">关联设备</a></li>
				</ul>
				<div id="fragment-tjqz" style="padding-top: 20px; height: 200px;">
					<div style="display:none">
						<cui:datepicker readonly="true"  required="true" id="grdCrteTime"  name="gmaCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
						<cui:datepicker readonly="true"  required="true" id="grdUpdtTime"  name="gmaCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
					</div>
					<cui:form id="formId_group">
						<cui:input  type="hidden" id="groupId" name="id" value=""  componentCls="form-control"></cui:input>
						<cui:input  type="hidden" id="gmaCrteDepartment" name="gmaCrteDepartment" value=""  componentCls="form-control"></cui:input>
						<cui:input  type="hidden" id="gmaIsLeaf" name="gmaIsLeaf" value="1"  componentCls="form-control"></cui:input>
						<cui:input  type="hidden" required="true" id="gmaCusNumber" name="gmaCusNumber" value="" componentCls="form-control"></cui:input>
							<table class="table table-fixed" style="width: 70%;">
								<tr>
									<td align="right"><label>群组名称：</label></td>
									<td><cui:input id="gmaGrpName" name="gmaGrpName"  required="true" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td align="right"><label>群组简称：</label></td>
									<td><cui:input id="gmaShortName" name="gmaShortName" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td align="right"><label>使用范围：</label></td>
									<td><cui:radiolist id="gmaUseRange" name="gmaUseRange" required="true" repeatLayout="flow" readonly="false"
											 column="4"  data="gmaUseRange_json" onChange="onChangeUseRange" componentCls="form-control"></cui:radiolist></td>
								</tr>
											
								<tr>
									<td align="right"><label>父级名称：</label></td>
									<td><input id="gmaParentId"/></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>群组类别：</label></td>							
									<td><cui:combobox id="gmaTypeIndc" name="gmaTypeIndc" value="1" required="true" emptyText="请选择"
											componentCls="form-control" data="gmaTypeIndc_json" /></td>
								</tr>
								<tr>
									<td align="right"><label>群组类型：</label></td>
									<td><cui:combobox id="gmaSubTypeIndc" name="gmaSubTypeIndc" emptyText="请选择"
											componentCls="form-control" data="gmaSubTypeIndc_json" onChange="onChangeGmaSubTypeIndc"/></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>群组用途：</label></td>
									<td><cui:radiolist id="gmaPurpose" name="gmaPurpose" value="0" required="true" repeatLayout="flow" readonly="false"
											column="4"  data="gmaPurpose_json"
											componentCls="form-control"></cui:radiolist></td>
								</tr>
								<tr  class="yhsd">
									<td align="right"><label>开始时间：</label></td>
									<td><cui:datepicker id="gmaStartTime" name="gmaStartTime"
											 model="timepicker" componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr class="yhsd">
									<td align="right"><label>结束时间：</label></td>
									<td><cui:datepicker id="gmaEndTime" name="gmaEndTime"
											startDateId="gmaStartTime"  model="timepicker"
											componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr>
									<td align="right"><label>排序：</label></td>
									<td><cui:input id="gmaShowSeq" name="gmaShowSeq" validType = "integer" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td align="right"><label>备注：</label></td>
									<td><cui:input id="gmaRemark" name="gmaRemark" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>创建人：</label></td>
									<td><cui:input   required="true" id="gmaCrteUserId" name="gmaCrteUserId" value="" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>创建时间：</label></td>
									<td><cui:datepicker  required="true" id="gmaCrteTime"  name="gmaCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>更新人：</label></td>
									<td><cui:input   required="true" id="gmaUpdtUserId" name="gmaUpdtUserId" value="" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td align="right"><label>更新时间：</label></td>
									<td><cui:datepicker required="true" id="gmaUpdtTime"  name="gmaUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker></td>
								</tr>
							</table>
					</cui:form>
					<div class="dialog-buttons custom-button">
						<cui:button cls="isAdd" label="添加" onClick="f_add"></cui:button>
						<cui:button label="修改" onClick="f_edit"></cui:button>
						<cui:button label="删除" onClick="f_delete"></cui:button>
						<cui:button cls="isAdd" label="重置" onClick="resetFormData"></cui:button>
					</div>
				</div>
				<div id="fragment-glsb">
					<cui:layout id="group-glsb-layout" fit="true">
						<cui:layoutRegion region='west' split="false" style="width: 400px" maxWidth="400" onResize="initTreebox">
							<center style="margin-top:10px;margin-left:10%;width:80%">
								<cui:input placeholder="Search" componentCls="form-control" onKeyPress="group_regionCameraSearch"></cui:input>
							</center>
							<cui:tree id="group_regionCameraTree" asyncEnable="true" keepParent="true"
								 asyncType="post" simpleDataEnable="true" asyncUrl="" checkable="true" 
								 onDblClick="" formatter="cameraFormatter"
								asyncAutoParam="id,name" rootNode="true" showRootNode="true" >
							</cui:tree>
						</cui:layoutRegion>
						<cui:layoutRegion region='center' split="false" onLoad="" onResize="">
							<div class="transfer_splitDiv">
						        <div class="transfer_btn">
						            <cui:button componentCls="btn-primary btn-lg" label=">" onClick="moveToRight"></cui:button>
						        </div>
						        <div>
						            <cui:button componentCls="btn-primary btn-lg" label="<" onClick="cancelSelect"></cui:button>
						        </div>
						    </div>
							<fieldset style="height:90%;width:85%;overflow-y:auto;margin:10px auto;">
							    <cui:grid id="checkedGrid" showGridHeader="true" 
							     afterSortableRows="true" colModel="gridColModel_member"  
							    rowNum="9999" multiselect="true" fitStyle="width" altRows="true"
							    height="auto" datatype="json"  onDblClickRow="">   	
							    </cui:grid>
				    		</fieldset>
				    		<div class="custom-button">
								<cui:button label="提交" onClick="f_glsb_submit"></cui:button>
								<cui:button label="重置" onClick="f_glsb_reset"></cui:button> 
							</div> 
						</cui:layoutRegion>
					</cui:layout> 				
				</div>
			</cui:tabs>
		</cui:layoutRegion>
	</cui:layout>
</div>

<script type="text/javascript"
	src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<script>

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;						//监狱编号
var userId=jsConst.USER_ID;			//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID

//tab第一次触发时置为true，同时加载树，此后不在重复加载
var tab_1=false;

var gmaTypeIndc_json =<%=CodeFacade.loadCode2Json("4.20.17")%>;
var gmaSubTypeIndc_json =<%=CodeFacade.loadCode2Json("4.20.18")%>;
var gmaUseRange_json = <%=CodeFacade.loadCode2Json("4.20.19")%>;
var gmaPurpose_json =<%=CodeFacade.loadCode2Json("4.20.20")%>;
/* var gmaSubTypeIndc_json = [ {
	'value' : '1',
	'text' : '重点部位'
}, {
	'value' : '2',
	'text' : '要害时段'
}, {
	'value' : '3',
	'text' : '重点环节'
}, {
	'value' : '4',
	'text' : '预案'
},{
	'value' : '5',
	'text' : '外来人员'
}, {
	'value' : '6',
	'text' : '外来车辆'
}, {
	'value' : '7',
	'text' : '教学区'
}, {
	'value' : '8',
	'text' : '生活区'
}, {
	'value' : '9',
	'text' : '生产区'
}, {
	'value' : '10',
	'text' : '指挥中心'
}, {
	'value' : '99',
	'text' : '其它'
} ]; 

var gmaUseRange_json = [{
	value : "0",
	text : "公共预案",
	checked : true
}, {
	value : "1",
	text : "自定义预案"
}];
var gmaPurpose_json = [{
	value : "0",
	text : "常规",
	checked : true
}, {
	value : "1",
	text : "大屏"
}];*/

function moveToRight(){
	var checkedNodes=$("#group_regionCameraTree").tree("getCheckedNodes");
	if(checkedNodes && checkedNodes.length>0){
		$.each(checkedNodes, function (index, node) {
			//如果是父节点说明是区域不能移动，只能移动成员
			if(!node.isParent){
				//判断选中的节点是否在右侧grid的中
				var isHave=$("#checkedGrid").grid('getInd',node.id);
				if(isHave){
					//alert("已包含此设备");
				}else{
					$("#checkedGrid").grid('addRowData',node.id,{'id':node.id,'name':node.name},'last');
				}	
			}	
        });
	}else{
		$.messageQueue({ message : "请先勾选需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
	}
}
//取消选中
function cancelSelect(){
	var selarrrow = $('#checkedGrid').grid("option", "selarrrow");
	if (selarrrow.length < 1) {
		$.messageQueue({ message : "请先勾选需要编辑的记录！", cls : "warning", iframePanel : true, type : "info" });
		return false;
	}
	for (var i=selarrrow.length;i>=0;i--){
	    $("#checkedGrid").grid('delRowData',selarrrow[i]);
	}
}
var gridColModel_member=[ {
	label : "id",
	name : "id",
	hidden : true,
}, {
	label : "名称",
	name : "name",
	align:'center'
}];

$.parseDone(function(){
	$("#gmaCusNumber").textbox("setValue",cusNumber);
	$("#gmaCrteUserId").textbox("setValue",userId);
	$("#gmaUpdtUserId").textbox("setValue",userId);
	$('#gmaCrteTime').datepicker('setDate',new Date());
	$('#gmaUpdtTime').datepicker('setDate',new Date());	
	$('#grdCrteTime').datepicker('setDate',new Date());
	$('#grdUpdtTime').datepicker('setDate',new Date());
	//公共预案
	$("#gmaParentId").combotree({
		required:true,
		simpleDataEnable:true,
		url:'',
		componentCls:"form-control",
		name:"gmaParentId"
	});
	$("#gmaParentId").combotree("tree").tree('reload','${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber='+cusNumber);
});

//切换面板
function tabs11_onActivate(){
	reViewMode =$("#tabs11").tabs("option","active");
	if(reViewMode==1){//关联设备
		if(!tab_1){
			if(jsConst.USER_LEVEL==1){
				//省局
				$("#group_regionCameraTree").tree("option","asyncUrl","${ctx}/provGroupManage/allPrisonAreaCameraTree.json");
				$("#group_regionCameraTree").tree("reload");
			}else if(jsConst.USER_LEVEL==2){
				//监狱
				$("#group_regionCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
				$("#group_regionCameraTree").tree("reload");
			}else if(jsConst.USER_LEVEL==3){
				//监区
				$("#group_regionCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
				$("#group_regionCameraTree").tree("reload");
			}
			
			$("#group_regionCameraTree").tree("option","onLoad",function(){
				$.parser.parse();
			});
			
			tab_1=true;
		}	
	}

}

//提交成员修改
function f_glsb_submit(){
	debugger;
	var  members=new Array();
	var rowDatas=$("#checkedGrid").grid('getRowData');
	console.log(rowDatas);
	var common_selectNodes= $('#groupTree').tree('getSelectedNodes');
	var diy_selectNodes= $('#DIYGroupTree').tree('getSelectedNodes');
	//选择的预案节点
	var selectNode;
	if(common_selectNodes.length>0){
		selectNode=common_selectNodes[0];
	}else if(diy_selectNodes.length>0){
		selectNode=diy_selectNodes[0];
	}else{
		$.alert({
			message:"请先勾选预案再添加设备",
			title:"信息提示",
			iframePanel:true
		});
		return;
	}
	
	
	console.log(selectNode);
	for(var i=0;i<rowDatas.length;i++){
		members.push({
			'grdGrpId':selectNode.id,
			'grdGrpName':selectNode.name,
			'grdTypeIndc':'1',
            'grdMmbrIdnty':rowDatas[i].id,
            'grdMmbrName':rowDatas[i].name,
            'grdShowSeq':i,
            'grdCusNumber':cusNumber,
            'grdCrteUserId':userId,
            'grdUpdtUserId':userId,
            'grdCrteTime': $('#grdCrteTime').datepicker("getDateValue"),
            'grdUpdtTime': $('#grdUpdtTime').datepicker("getDateValue"),
        });
	}
	
	console.log(JSON.stringify(members));
	$.ajax({
		type : 'post',
		url : '${ctx}/groupMember/batchInsert.json?grdGrpId='+selectNode.id,
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(members),
		dataType : 'json',
		success : function(data) {
			if(data.success){
				$.message({
					message : "保存成功",
					cls : "success",
					iframePanel:true
				});
				
			}else{
				$.message( {
					iframePanel:true,
			        message:data.msg,
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

function f_glsb_reset(){
	$("#checkedGrid").grid('clearGridData');
}
function onChangeGmaSubTypeIndc(e,ui){
	//要害时段，显示开始，结束时间
	if("2"==ui.value){
		$(".yhsd").show();
	}else{
		$(".yhsd").hide();
		$("#gmaStartTime").datepicker("setDate",null);
		$("#gmaEndTime").datepicker("setDate",null);
	}
}
function onChangeUseRange(event, ui){
    $("#gmaParentId").combotree("clear");
	if("1"==ui.value){//自定义预案
		$("#gmaParentId").combotree('reload','${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=1&gmaCusNumber='+cusNumber+"&gmaCrteUserId="+userId);
	}else{//公共预案
		$("#gmaParentId").combotree('reload','${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber='+cusNumber);
	}
	/*$("#gmaParentId").combotree("tree").tree("option","onLoad",function(){
		$.parser.parse();
	});*/
}
function f_add(){
	//初始化新增数据
	$("#groupId").textbox("setValue",null);
	$("#gmaIsLeaf").textbox("setValue","1");//叶子节点
	$("#gmaCusNumber").textbox("setValue",cusNumber);
	$("#gmaCrteUserId").textbox("setValue",userId);
	$("#gmaUpdtUserId").textbox("setValue",userId);
	$('#gmaCrteTime').datepicker('setDate',new Date());
	$('#gmaUpdtTime').datepicker('setDate',new Date());
	$("#gmaCrteDepartment").textbox("setValue",dprtmntIdnty);
	if ($("#formId_group").form("valid")) {
		var formData = $("#formId_group").form("formData");
		var ur = '${ctx}/groupManage/create.json';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					$.message({
						message : "保存成功",
						cls : "success",
						iframePanel:true
					});
					//初始化要害时段视频预案定时器
					if("2"==formData.gmaSubTypeIndc){
						videoPlanTimer.initVideoPlanTimer();
				}
					$("#gmaParentId").combotree("tree").tree("reload","${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
					$('#groupTree').tree("reload");
					$('#DIYGroupTree').tree("reload");
                    resetFormData();
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

	} else {
		$.alert({
			message:"请确认输入是否正确！",
			title:"信息提示",
			iframePanel:true
		});
	}
}
function f_edit(){
	var groupId=$('#groupId').textbox("getValue");
	if(groupId==""||groupId==null){
		$.alert({
			message:"请点击左侧预案，选择需要修改的预案！",
			title:"信息提示",
			iframePanel:true
		});
		return;
	}
	$('#gmaUpdtTime').datepicker('setDate',new Date());
	
	if ($("#formId_group").form("valid")) {
		var formData = $("#formId_group").form("formData");
		var ur = '${ctx}/groupManage/updatePart.json';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.success){
					$.message({
						message : "修改成功",
						cls : "success",
						iframePanel:true
					});
					//初始化要害时段视频预案定时器
					if("2"==formData.gmaSubTypeIndc){
						videoPlanTimer.initVideoPlanTimer();
					}
					$("#gmaParentId").combotree("tree").tree("reload","${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber );
					$('#groupTree').tree("reload");
					$('#DIYGroupTree').tree("reload");
                    resetFormData();
				}else{
					$.message( {
						iframePanel:true,
				        message:data.msg,
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

	} else {
		$.alert({
			message:"请确认输入是否正确！",
			title:"信息提示",
			iframePanel:true
		});
	}
}
function f_delete(){
    var formData = $("#formId_group").form("formData");
	var groupId=$('#groupId').textbox("getValue");
	if(groupId==""||groupId==null){
		$.alert({
			message:"请点击左侧预案，选择需要删除的预案！",
			title:"信息提示",
			iframePanel:true
		});
		return;
	} 
	var isLeaf=$('#gmaIsLeaf').textbox("getValue");
	if(isLeaf!="1"){
		$.alert({
			message:"请先删除子节点！",
			title:"信息提示",
			iframePanel:true
		});
		return;
	} 
	$.confirm( {
		message:"确认删除？",
		iframePanel:true,
		callback: function(sure) {
			if (sure == true) {
				$.ajax({
					type : "post",
					url : "${ctx}/groupManage/deleteGroup.json?id=" + $("#groupId").val(),
					success : function(data) {
						if(data.success){
							$.message({
								iframePanel:true,
								message : "操作成功！",
								cls : "success"
							});
							//初始化要害时段视频预案定时器
							if( "2"==formData.gmaSubTypeIndc) {
                                videoPlanTimer.initVideoPlanTimer();
                            }
							$("#gmaParentId").combotree("tree").tree("reload","${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber );
							$('#groupTree').tree("reload");
							$('#DIYGroupTree').tree("reload");
                            resetFormData();
						}else{
							$.message( {
								iframePanel:true,
						        message:data.msg,
						        type:"danger"
						    });
						}
						
					}
				});  
			}
			if (sure == false) {
				console.log('cancel');
			}
		}
	});
}
	function group_regionCameraSearch(e, data) {
		//回车时触发
		if(e.keyCode == 13)      
	    {  
			$("#group_regionCameraTree").tree("filterNodesByParam", {
				name : data.value
			});  
	    }  
	}

	function resetFormData(){
		$("#formId_group").form("reset");
        $("#gmaParentId").combotree("reload",[]);
	}
</script>