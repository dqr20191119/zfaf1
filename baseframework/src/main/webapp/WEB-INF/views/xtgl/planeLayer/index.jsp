<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.form-control {
	width: 100%;
}
#ewtc-layout .coral-panel .coral-tree li a{
	color:#333;
}
#ewtc-layout .custom-button{
	position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    text-align:right;
    padding-right: 10px;
    height:45px;
    line-height:45px;
}
</style>

<div style="height: 100%;">
	<cui:layout id="ewtc-layout" fit="true">
		<cui:layoutRegion region='west' split="false" style="width: 220px"
			maxWidth="220"	onResize="initTreebox">
			<div class="F-left" style="overflow-y:auto; overflow-x:auto;width: 100%;background-color: #EDEEEE;">
				<!-- 区域树 -->
				<cui:tree id="areaTree1" asyncEnable="true" keepParent="true"
					 asyncType="post" asyncUrl="" simpleDataEnable="true"
					asyncAutoParam="id,name" onClick="areaTreeClick" rootNode="true" showRootNode="true" >
				</cui:tree>	
			</div>
		</cui:layoutRegion>
		<cui:layoutRegion region='center' split="false" onLoad="" onResize="">
			<cui:form id="formId_ewtc">
				<cui:input  type="hidden" id="id" name="id" value="" ></cui:input>
				<cui:input  type="hidden" required="true" id="pliCusNumber" name="pliCusNumber" value=""></cui:input>
				<table class="table table-fixed" style="width: 70%;">
					<tr>
						<td align="right"><label>图层名称：</label></td>
						<td><cui:input id="pliPlaneName" name="pliPlaneName"  required="true" componentCls="form-control"></cui:input></td>
					</tr>
					<tr>
						<td align="right"><label>关联区域：</label></td>
						<td>
							<cui:input readonly="true" required="true" id="pliAreaName" componentCls="form-control"></cui:input>
							<cui:input type="hidden" required="true" id="pliAreaId" name="pliAreaId" componentCls="form-control"></cui:input>
						</td>
					</tr>
					<tr>
						<td align="right"><label>宽度：</label></td>
						<td><cui:input required="true" id="pliWidth" name="pliWidth" validType="integer" componentCls="form-control"></cui:input></td>
					</tr>
					<tr>
						<td align="right"><label>高度：</label></td>
						<td><cui:input required="true" id="pliHeight" name="pliHeight" validType="integer" componentCls="form-control"></cui:input></td>
					</tr>
					<tr style="display:none">
						<td align="right"><label>图片地址：</label></td>
						<td><cui:input required="true" id="pliImgPath" name="pliImgPath" componentCls="form-control"></cui:input></td>
					</tr>
					
				</table>
			</cui:form>
			
			<div style="border: 1px solid #c0c0c0; margin: 5px; text-align: center;">
				<h4 style="background-color: #F0F0F0; width: 100%; height: 35px; font-size: 25px; font-weight: normal;">附件信息</h4>
				<div style="text-align: center; height: 365px; overflow: auto;">
					<cui:uploader id="uploaderId_attachment" buttonText="请选择文件..." fileTypeExts="*.jpg;*.jpeg;*.png" removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess"
						onInit="common.onInit" onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
				</div>
			</div>
			
			<div class="dialog-buttons custom-button">
				<cui:button cls="add" label="添加" onClick="f_add"></cui:button>
				<cui:button cls="edit" label="修改" onClick="f_edit"></cui:button>
				<cui:button cls="edit" label="删除" onClick="f_delete"></cui:button>
			</div>
		</cui:layoutRegion>
	</cui:layout>
</div>

<script>

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID								//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID


$.parseDone(function(){
	$("#areaTree1").tree("reload",
			"${ctx}/common/areadevice/findForCombotree.json?cusNumber="
					+ cusNumber + "&deviceType=0");
});
//文件删除成功时触发
function f_onClearQueue(e,ui){
	console.log("文件删除成功");
	$("#uploaderId_attachment_affixIds").val("");
}

//双击区域展示关联图层
function areaTreeClick(event, id, node){
	//重置表单
	resetForm();
	//初始化上传图片id值
	$("#uploaderId_attachment_affixIds").val("");
	
	//清空图片显示
	$("div .wrapper").remove();
	
	$('#pliAreaName').textbox("setValue",node.name);
	$('#pliAreaId').textbox("setValue",node.id);
	$.ajax({
		type : 'post',
		url : '${ctx}/xtgl/planeLayer/findByPliAreaId.json',
		data : {'pliAreaId':node.id},
		dataType : 'json',
		success : function(data) {
			console.log(data);	
			if(data.exception==undefined){
				//说明存在关联图层
				if(data.length>0){
					//一个区域关联一个图层，如果存在多个取第一个
					var obj=data[0];
					console.log("存在关联图层");
					$(".add").hide();
					$(".edit").show();
					
					$('#formId_ewtc').form("load", obj);
					
					var paramData = {};
					paramData.id = obj.id;
					$.ajax({
						type : 'post',
						url : '${ctx}/xtgl/planeLayer/findFile.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								common.loadAffix("uploaderId_attachment", data.obj, false,true);
								//一个区域只能关联一个图层，关联多个只显示第一个
								if(data.obj.length>0){
									console.log(data.obj[0].id);
									//参考common.js
									var affixId = $("#uploaderId_attachment_affixIds").val();
									if(affixId){
										$("#uploaderId_attachment_affixIds").val(data.obj[0].id);
									}else{
										var affixInput = "<input type=\"hidden\" name=\"uploaderId_attachment_affixIds\" id=\"uploaderId_attachment_affixIds\"/>";
										$("#uploaderId_attachment").after(affixInput);
										$("#uploaderId_attachment_affixIds").val(data.obj[0].id);
									}
								}else{
									console.log("未绑定图片");
									//参考common.js
									var affixId = $("#uploaderId_attachment_affixIds").val();
									if(affixId){
										$("#uploaderId_attachment_affixIds").val("");
									}else{
										var affixInput = "<input type=\"hidden\" name=\"uploaderId_attachment_affixIds\" id=\"uploaderId_attachment_affixIds\"/>";
										$("#uploaderId_attachment").after(affixInput);
										$("#uploaderId_attachment_affixIds").val("");
									}
								}
								
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									cls : "warning"
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
				//不存在关联图层
				else{
					console.log("不存在关联图层");
					$(".edit").hide();
					$(".add").show();
				}
			}else{
				$.message({
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
		},error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}
function f_add(){
	$('#pliCusNumber').textbox("setValue",cusNumber);
	
	var paramData = {};
	paramData.files = $("#uploaderId_attachment_affixIds").val();
	console.log($("#uploaderId_attachment").val());
	console.log(paramData.files);
	if (paramData.files == undefined || paramData.files == "") {
		$.message({
			iframePanel:true,
			message : "请选择附件！",
			cls : "waring"
		});
		return;
	}
	
	if ($("#formId_ewtc").form("valid")) {
		var formData = $("#formId_ewtc").form("formData");
		var url = '${ctx}/xtgl/planeLayer/insert.json';
		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.success){
					//按钮处理
					$(".add").hide();
					$(".edit").show();
					
					//设置记录ID
					paramData.id=data.obj;
					
					$.ajax({
						type : 'post',
						url : '${ctx}/xtgl/planeLayer/saveFile.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$.message({
									message : "保存成功",
									cls : "success",
									iframePanel:true
								});
								//初始化上传图片id值
								$("#uploaderId_attachment_affixIds").val("");
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									cls : "warning"
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
function f_edit(){
	var paramData = {};
	paramData.id=$("#id").val();
	paramData.files = $("#uploaderId_attachment_affixIds").val();
	console.log(paramData.files);
	if (paramData.files == undefined || paramData.files == "") {
		$.message({
			iframePanel:true,
			message : "请选择附件！",
			cls : "waring"
		});
		return;
	}
	if (paramData.id == undefined || paramData.id == "") {
		$.message({
			iframePanel:true,
			message : "请选择图层！",
			cls : "waring"
		});
		return;
	}
	
	if ($("#formId_ewtc").form("valid")) {
		var formData = $("#formId_ewtc").form("formData");
		var url = '${ctx}/xtgl/planeLayer/updatePart.json';
		$.ajax({
			type : 'post',
			url : url,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.success){
					
					$.ajax({
						type : 'post',
						url : '${ctx}/xtgl/planeLayer/saveFile.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$.message({
									message : "修改成功",
									cls : "success",
									iframePanel:true
								});
								//初始化上传图片id值
								$("#uploaderId_attachment_affixIds").val("");
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									cls : "warning"
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
	var plpLayerIdnty=$("#id").val();
	if(!plpLayerIdnty){
		$.message({
			iframePanel:true,
			message : "请选择图层！",
			cls : "waring"
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
					url : "${ctx}/xtgl/planeLayer/delete.json?id=" + $("#id").val(),
					success : function(data) {
						if (data.success) {
							//删除后重置表单
							resetForm();
							//初始化上传图片id值
							$("#uploaderId_attachment_affixIds").val("");
							//清空图片显示
							$("div .wrapper").remove();
							
							//删除图层关联的点位
							$.ajax({
								type : "post",
								url : "${ctx}/xtgl/planeLayerPoint/deleteByPlpLayerIdnty.json?plpLayerIdnty=" + plpLayerIdnty,
								success : function(data) {
									if (data.success) {
										$.message({
											message : "操作成功",
											cls : "success",
											iframePanel:true
										});		
									} else {
										$.message({
											iframePanel:true,
											message : data.msg,
											cls : "warning"
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
							$.message({
								iframePanel:true,
								message : data.msg,
								cls : "warning"
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
			if (sure == false) {
				console.log('cancel');
			}
		}
	});
}
//重置
function resetForm(){
	$("#formId_ewtc").form("reset");
}
</script>