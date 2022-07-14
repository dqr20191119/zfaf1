<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<div style="height: 100%;">
	<cui:layout id="group-layout" fit="true">
		<cui:layoutRegion region='west' split="false" style="width: 220px"
			maxWidth="220" url="${ctx}/groupManage/groupTreePage"
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
						<cui:input  type="hidden" id="gmaIsLeaf" name="gmaIsLeaf" value="1"  componentCls="form-control"></cui:input>
						<cui:input  type="hidden" required="true" id="gmaCusNumber" name="gmaCusNumber" value="" componentCls="form-control"></cui:input>
							<table class="table table-fixed" style="width: 70%;">
								<tr>
									<td><label>群组名称：</label></td>
									<td><cui:input id="gmaGrpName" name="gmaGrpName"  required="true" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td><label>群组简称：</label></td>
									<td><cui:input id="gmaShortName" name="gmaShortName" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td><label>使用范围：</label></td>
									<td><cui:radiolist id="gmaUseRange" name="gmaUseRange" required="true" repeatLayout="flow" readonly="false"
											column="4"  data="gmaUseRange_json" onChange="onChangeUseRange" componentCls="form-control"></cui:radiolist></td>
								</tr>
											
								<tr>
									<td><label>父级名称：</label></td>
									<td><cui:combotree  id="gmaParentId" name="gmaParentId" url="default"  componentCls="form-control"></cui:combotree></td>
								</tr>
								<tr>
									<td><label>群组类别：</label></td>							
									<td><cui:combobox id="gmaTypeIndc" name="gmaTypeIndc" required="true" emptyText="请选择"
											componentCls="form-control" data="gmaTypeIndc_json" /></td>
								</tr>
								<tr>
									<td><label>群组类型：</label></td>
									<td><cui:combobox id="gmaSubTypeIndc" name="gmaSubTypeIndc" emptyText="请选择"
											componentCls="form-control" data="gmaSubTypeIndc_json" /></td>
								</tr>
								<tr>
									<td><label>群组用途：</label></td>
									<td><cui:radiolist id="gmaPurpose" name="gmaPurpose" required="true" repeatLayout="flow" readonly="false"
											column="4"  data="gmaPurpose_json"
											componentCls="form-control"></cui:radiolist></td>
								</tr>
								<tr>
									<td><label>图层名称：</label></td>
									<td><cui:input id="gmaMapName" name="gmaMapName" componentCls="form-control"></cui:input></td>
								</tr>
								<tr>
									<td><label>开始时间：</label></td>
									<td><cui:datepicker id="gmaStartTime" name="gmaStartTime"
											dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr>
									<td><label>结束时间：</label></td>
									<td><cui:datepicker id="gmaEndTime" name="gmaEndTime"
											startDateId="gmaStartTime" dateFormat="yyyy-MM-dd HH:mm:ss"
											componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr>
									<td><label>备注：</label></td>
									<td><cui:input id="gmaRemark" name="gmaRemark" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td><label>创建人：</label></td>
									<td><cui:input   required="true" id="gmaCrteUserId" name="gmaCrteUserId" value="" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td><label>创建时间：</label></td>
									<td><cui:datepicker  required="true" id="gmaCrteTime"  name="gmaCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker></td>
								</tr>
								<tr style="display:none">
									<td><label>更新人：</label></td>
									<td><cui:input   required="true" id="gmaUpdtUserId" name="gmaUpdtUserId" value="" componentCls="form-control"></cui:input></td>
								</tr>
								<tr style="display:none">
									<td><label>更新时间：</label></td>
									<td><cui:datepicker required="true" id="gmaUpdtTime"  name="gmaUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker></td>
								</tr>
							</table>
						</cui:form>
						<div class="dialog-buttons">
							<cui:button cls="isAdd" label="添加" onClick="f_add"></cui:button>
							<cui:button label="修改" onClick="f_edit"></cui:button>
							<cui:button label="删除" onClick="f_delete"></cui:button>
							<cui:button cls="isAdd" label="重置" onClick="resetForm"></cui:button>
						</div>
				</div>
				<div id="fragment-glsb">
					<cui:layout id="group-glsb-layout" fit="true">
		<cui:layoutRegion region='west' split="false" style="width: 220px" maxWidth="220" onResize="initTreebox">
			<div style="height:400px;overflow-y:auto">
			<cui:tree id="regionCameraTree" asyncEnable="true" keepParent="true"
				 asyncType="post" asyncUrl="" onDblClick="moveToRight"
				asyncAutoParam="id,name" rootNode="true" showRootNode="true" >
			</cui:tree>
			</div>
		</cui:layoutRegion>
		<cui:layoutRegion region='center' split="false" onLoad="" onResize="">
			<div style="height:400px;overflow-y:auto">
			
    <cui:grid id="checkedGrid" showGridHeader="false" afterSortableRows="true" colModel="gridColModel_member" width="auto" height="auto"  url="" onDblClickRow="cancelSelect">
    </cui:grid>
    
			</div>
		</cui:layoutRegion>
		
	</cui:layout> 				
					<div class="dialog-buttons">
						<cui:button label="提交" onClick="f_glsb_submit"></cui:button>
						<cui:button label="重置" onClick="f_glsb_reset"></cui:button>
					</div>
				</div>

			</cui:tabs>
		</cui:layoutRegion>
	</cui:layout>
</div>
<script>
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人

//tab第一次触发时置为true，同时加载树，此后不在重复加载
var tab_1=false;

var gmaTypeIndc_json = [ {
	'value' : '1',
	'text' : '摄像机'
}, {
	'value' : '2',
	'text' : '门禁'
},{
	'value' : '3',
	'text' : '广播'
}, {
	'value' : '4',
	'text' : '大屏'
} ];
var gmaSubTypeIndc_json = [ {
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
	'text' : '其它'
}, {
	'value' : '11',
	'text' : '指挥中心'
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
}];


function moveToRight(event, id, node){
	console.log(node);
	
	//如果是父节点说明是区域不能移动，只能移动成员
	if(!node.isParent){
		//判断选中的节点是否在右侧grid的中
		var isHave=$("#checkedGrid").grid('getInd',node.id);
		if(isHave){
			alert("已包含此设备");
		}else{
			$("#checkedGrid").grid('addRowData',node.id,{'id':node.id,'name':node.name},'last');
		}
		
	}	

}
var gridColModel_member=[ {
	label : "id",
	name : "id",
	hidden : true,
}, {
	label : "名称",
	name : "name",
	width : '200',
	align:'center'
}];

$.parseDone(function(){
	$("#gmaCusNumber").textbox("setValue",cusNumber);
	$("#gmaCrteUserId").textbox("setValue",userId);
	$("#gmaUpdtUserId").textbox("setValue",userId);
	
	//$("#gmaParentId").combotree("option","url","${ctx}/groupManage/getTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
	$("#gmaParentId").combotree("reload","${ctx}/groupManage/getTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
	//$("#gmaParentId").combotree("tree").tree("reload","${ctx}/groupManage/getTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
	$.parser.parse();
	
	$('#gmaCrteTime').datepicker('setDate',new Date());
	$('#gmaUpdtTime').datepicker('setDate',new Date());
	
	$('#grdCrteTime').datepicker('setDate',new Date());
	$('#grdUpdtTime').datepicker('setDate',new Date());
	
	 var node= $('#regionCameraTree').tree('getNodeByParam','id',"-1");
	console.log(node); 
});

//切换面板
function tabs11_onActivate(){
	reViewMode =$("#tabs11").tabs("option","active");
	
	if(reViewMode==1){//关联设备
		if(!tab_1){
			$("#regionCameraTree").tree("option","asyncUrl","${ctx}/jfsb/camera/findAreaCameraTree.json?cusNumber="+cusNumber);
			$("#regionCameraTree").tree("reload");
			$.parser.parse();
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
		$.alert("信息提示","请先勾选预案再添加设备",function (){
	        return;
	    });
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
		url : '${ctx}/groupMember/batchInsert?grdGrpId='+selectNode.id,
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(members),
		dataType : 'json',
		success : function(data) {
			if(data.exception==undefined){
				$.message("保存成功");
				
			}else{
				$.message( {
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
		}
	});  
}

function f_glsb_reset(){
	$("#checkedGrid").grid('clearGridData');
}
//右侧双击取消选中
function cancelSelect(e,ui){
	console.log(e);
	console.log(ui);
	$("#checkedGrid").grid('delRowData',ui.rowId);
}
function onChangeUseRange(event, ui){
	if("1"==ui.value){//自定义预案
		$('#gmaParentId').combotree('reload','${ctx}/groupManage/getTree.json?gmaUseRange=1&gmaCusNumber='+cusNumber);
	}else{//公共预案
		$('#gmaParentId').combotree('reload','${ctx}/groupManage/getTree.json?gmaUseRange=0&gmaCusNumber='+cusNumber);
	}
}
function f_add(){
	
	$("#gmaCusNumber").textbox("setValue",cusNumber);
	$("#gmaCrteUserId").textbox("setValue",userId);
	$("#gmaUpdtUserId").textbox("setValue",userId);
	$('#gmaCrteTime').datepicker('setDate',new Date());
	$('#gmaUpdtTime').datepicker('setDate',new Date());
	
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
					$.message("保存成功");
					$("#gmaParentId").combotree("reload");
					$('#groupTree').tree("reload");
					$('#DIYGroupTree').tree("reload");
				}else{
					$.message( {
				        message:data.exception.cause.message,
				        type:"danger"
				    });
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});

	} else {
		$.alert("请确认输入是否正确！");
	}
}
function f_edit(){
	
	$('#gmaUpdtTime').datepicker('setDate',new Date());
	
	if ($("#formId_group").form("valid")) {
		var formData = $("#formId_group").form("formData");
		var ur = '${ctx}/groupManage/updatePart';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					$.message("修改成功");
					$("#gmaParentId").combotree( "reload" );
					$('#groupTree').tree("reload");
					$('#DIYGroupTree').tree("reload");
				}else{
					$.message( {
				        message:data.exception.cause.message,
				        type:"danger"
				    });
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});

	} else {
		$.alert("请确认输入是否正确！");
	}
}
function f_delete(){
	$.confirm("确认删除？", "信息确认", function(test) {
		if (test == true) {
			 $.ajax({
				type : "post",
				url : "${ctx}/groupManage/deleteGroup?id=" + $("#groupId").val(),
				success : function(data) {
					if(data.exception==undefined){
						$.message({
							message : "操作成功！",
							cls : "success"
						});
						$("#gmaParentId").combotree( "reload" );
						$('#groupTree').tree("reload");
						$('#DIYGroupTree').tree("reload");
					}else{
						$.message( {
					        message:data.exception.cause.message,
					        type:"danger"
					    });
					}
					
				}
			});  
		}
		
	});
}


	function resetForm(){
		$("#formId_group").form("reset");
		$("#gmaParentId").combotree("reload","${ctx}/groupManage/getTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
	} 
</script>