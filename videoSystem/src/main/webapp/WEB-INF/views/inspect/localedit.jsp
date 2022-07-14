<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
.td_title{
	text-align: right;
	font-weight: bold;
}
.td_context{
}
</style>

<center>
	<cui:form id="formId_inspectLocalEdit">
		<table class="table table-fixed" style="width: 90%;">
			<cui:input type="hidden" required="true" id="inspectlocal_edit_inlCrteUserId" name="inlCrteUserId"></cui:input>
			<cui:input type="hidden" required="true" id="inspectlocal_edit_inlCrteUserName" name="inlCrteUserName"></cui:input>
			<tr>
				<td  width="15%" class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input  required="true" id="inspectlocal_edit_inlInspectName" name="inlInspectName" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker required="true" id="inspectlocal_edit_inlInspectTime" name="inlInspectTime" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:combotree required="true" id="inspectlocal_combotree_iprPoliceIdntys" name="iprPoliceIdntys" simpleDataEnable="true"
						url="${ctx}/common/areadevice/nullJson.json" multiple="true" allowPushParent="false" onChange="inspectlocal_edit.iprPoliceIdntysOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="inspectlocal_edit_iprPoliceNames" name="iprPoliceNames"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>好的方面：</label></td>
				<td class="td_context" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:toolbar data="toolbar_inspectlocal_good"></cui:toolbar>
					<cui:grid id="gridId_inspect_good_record" width="auto" fitStyle="fill" singleselect="true" pager="true">
						<cui:gridCols>
							<cui:gridCol name="inrTime" width="150">时间</cui:gridCol>
							<cui:gridCol name="inrAddr" width="150">地点</cui:gridCol>
							<cui:gridCol name="inrDprtmntName" width="150">所在单位</cui:gridCol>
							<cui:gridCol name="inrDprtmntId" hidden="true">所在单位id</cui:gridCol>
							<cui:gridCol name="inrRemark" width="150">备注</cui:gridCol>
							<cui:gridCol name="inrTypeIndc" hidden="true">类型</cui:gridCol>
						</cui:gridCols>
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>存在问题：</label></td>
				<td class="td_context" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:toolbar data="toolbar_inspectlocal_bad"></cui:toolbar>
					<cui:grid id="gridId_inspect_bad_record" width="auto" fitStyle="fill" singleselect="true" pager="true">
						<cui:gridCols>
							<cui:gridCol name="inrTime" width="150">时间</cui:gridCol>
							<cui:gridCol name="inrAddr" width="150">地点</cui:gridCol>
							<cui:gridCol name="inrDprtmntName" width="150">所在单位</cui:gridCol>
							<cui:gridCol name="inrDprtmntId" hidden="true">所在单位id</cui:gridCol>
							<cui:gridCol name="inrRemark" width="150">备注</cui:gridCol>
							<cui:gridCol name="inrTypeIndc" hidden="true">类型</cui:gridCol>
						</cui:gridCols>
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察记载：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspectlocal_edit_inlPrisonCase" name="inlPrisonCase"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>处理意见：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspectlocal_edit_inlSuggesition" name="inlSuggesition"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea required="true" id="inspectlocal_edit_inlProblem" name="inlProblem"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核领导：</label></td>
				<td class="td_context">
					<cui:combotree required="true" id="inspectlocal_combotree_checkPoliceIdnty" name="checkPoliceIdnty" simpleDataEnable="true"
						url="${ctx}/common/areadevice/nullJson.json" allowPushParent="false" onChange="inspectlocal_edit.checkPoliceIdntyOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="inspectlocal_edit_checkPoliceName" name="checkPoliceName"></cui:input>
				</td>
			</tr> 
		</table>
	</cui:form>
</center>
<div class="dialog-buttons">
	<cui:button label="提交" componentCls="btn-primary" onClick="inspectlocal_edit.submit()"></cui:button>
	<cui:button label="重置" onClick="inspectlocal_edit.reset()"></cui:button>
</div>

<cui:dialog id="inspectlocaldialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
	<cui:form id="formId_temp">
		<table class="table table-fixed" style="width: 90%;">
			<tr>
				<td width="15%" class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker required="true" id="inrTime" name="inrTime" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>地点：</label></td>
				<td class="td_context">
					<cui:input required="true" id="inrAddr" name="inrAddr" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>所在单位：</label></td>
				<td class="td_context">
					<cui:combobox id="inrDprtmntId" name="inrDprtmntId"  url="${ctx}/common/areadevice/nullJson.json"  componentCls="form-control" onChange="onChangeDprtmnt"></cui:combobox>
					<cui:input  id="inrDprtmntName" name="inrDprtmntName" type="hidden" value="" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context">
					<cui:textarea required="true" id="inrRemark" name="inrRemark"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
		</table>
		<div class="dialog-buttons">
			<cui:button label="提交" onClick="inspectlocal_edit.tempsavegood()" id="good_save"></cui:button>
			<cui:button label="提交" onClick="inspectlocal_edit.tempsavebad()" style="display:none" id="bad_save"></cui:button>
			<cui:button label="重置" onClick="inspectlocal_edit.tempreset()"></cui:button>
		</div>
	</cui:form>
</cui:dialog>

<script>
	
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userName = jsConst.REAL_NAME;//当前登录人真实姓名
	var userId = jsConst.USER_ID;//当前登录人id

	
	var toolbar_inspectlocal_good = [{
		"type" : "button",
		"id" : "btnId_addGood",
		"label" : "增加",
		"onClick" : "inspectlocal_edit.addGood",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_deleteGood",
		"label" : "删除",
		"onClick" : "inspectlocal_edit.delGood",
		"componentCls" : "btn-primary"
	}];
	
	var toolbar_inspectlocal_bad = [{
		"type" : "button",
		"id" : "btnId_addBad",
		"label" : "增加",
		"onClick" : "inspectlocal_edit.addBad",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_deleteBad",
		"label" : "删除",
		"onClick" : "inspectlocal_edit.delBad",
		"componentCls" : "btn-primary"
	}];
	
	function onChangeDprtmnt(e,ui){
		$("#inrDprtmntName").textbox('setValue',ui.text);
	}
	
	$.parseDone(function(){ 
		
		$('#inspectlocal_edit_inlInspectTime').datepicker('setDate', new Date());
		
		$("#inrDprtmntId").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
		
		$("#inspectlocal_combotree_checkPoliceIdnty").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		$("#inspectlocal_combotree_iprPoliceIdntys").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		 
		inspectlocal_edit = {
				
			checkPoliceIdntyOnChange: function(e,ui){
				$('#inspectlocal_edit_checkPoliceName').textbox('setValue',ui.text);
			},
			iprPoliceIdntysOnChange: function(e,ui){
				$('#inspectlocal_edit_iprPoliceNames').textbox('setValue',ui.text);
			},	
			
			reset:function(){
				$("#formId_inspectLocalEdit").form("reset");
			},
			tempreset:function(){
				$("#formId_temp").form("reset");
			},
			submit:function(){
				if ($("#formId_inspectLocalEdit").form("valid")) {
					$('#inspectlocal_edit_inlCrteUserId').textbox('setValue',userId);
					$('#inspectlocal_edit_inlCrteUserName').textbox('setValue',userName);
					var formData = $("#formId_inspectLocalEdit").form("formData");
					
					var goodData=$("#gridId_inspect_good_record").grid("getRowData");
					
					var badData=$("#gridId_inspect_bad_record").grid("getRowData");
					for(var i =0 ;i<goodData.length;i++){
						var row =goodData[i] ;
						formData["goodList["+i+"].inrTime"] = row.inrTime;
						formData["goodList["+i+"].inrAddr"] = row.inrAddr;
						formData["goodList["+i+"].inrDprtmntName"] = row.inrDprtmntName;
						formData["goodList["+i+"].inrDprtmntId"] = row.inrDprtmntId;
						formData["goodList["+i+"].inrRemark"] = row.inrRemark;
						formData["goodList["+i+"].inrTypeIndc"] = row.inrTypeIndc;
					}
					for(var i =0 ;i<badData.length;i++){
						var row =badData[i] ;
						formData["badList["+i+"].inrTime"] = row.inrTime;
						formData["badList["+i+"].inrAddr"] = row.inrAddr;
						formData["badList["+i+"].inrDprtmntName"] = row.inrDprtmntName;
						formData["badList["+i+"].inrDprtmntId"] = row.inrDprtmntId;
						formData["badList["+i+"].inrRemark"] = row.inrRemark;
						formData["badList["+i+"].inrTypeIndc"] = row.inrTypeIndc;
					}
					if (cusNumber != "") {
						formData["inlCusNumber"] = cusNumber;
					}
					
					console.info(formData);
					
					$.ajax({
						type : 'post',
						url : '${ctx}/inspectlocal/add.json',
						data : formData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								$.message({
									message : "上报成功",
									cls : "success",
									iframePanel:true
								});
								$("#dialog").dialog("close");
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									type : "danger"
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
			},
			addGood:function(){
				$('#good_save').show();
				$('#bad_save').hide();
				$("#formId_temp").form("reset");
				$("#inspectlocaldialog").dialog({
					width : 500,
					height : 350,
					subTitle : '好的方面',
				});
				$("#inspectlocaldialog").dialog("open");
			},
			tempsavegood:function(){
				if ($("#formId_temp").form("valid")) {
					var formData = $("#formId_temp").form("formData");
					formData.inrTypeIndc="0";	//好的方面
					$("#gridId_inspect_good_record").grid("addRowData",guid(),formData);
					$("#inspectlocaldialog").dialog("close");
				} else {
					$.alert({
						message:"请确认输入是否正确！",
						title:"信息提示",
						iframePanel:true
					});
				}
				
			},
			delGood:function (){
				var selrow = $('#gridId_inspect_good_record').grid("option", "selrow");
				if (selrow != null) {
					$.confirm( {
						message:"确认删除？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {
								$('#gridId_inspect_good_record').grid("delRowData",selrow.toString());
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
				} else {
					$.message({
						iframePanel:true,
						message : "请先勾选需要处理记录！",
						cls : "warning"
					});
				}
			},
			addBad:function(){
				$('#good_save').hide();
				$('#bad_save').show();
				$("#formId_temp").form("reset");
				$("#inspectlocaldialog").dialog({
					width : 500,
					height : 350,
					subTitle : '存在问题',
				});
				$("#inspectlocaldialog").dialog("open");
			},
			tempsavebad:function(){
				if ($("#formId_temp").form("valid")) {
					var formData = $("#formId_temp").form("formData");
					formData.inrTypeIndc="1";	//存在问题
					$("#gridId_inspect_bad_record").grid("addRowData",guid(),formData);
					$("#inspectlocaldialog").dialog("close");
				} else {
					$.alert({
						message:"请确认输入是否正确！",
						title:"信息提示",
						iframePanel:true
					});
				}
			},
			delBad:function(){
				var selrow = $('#gridId_inspect_bad_record').grid("option", "selrow");
				if (selrow != null) {
					$.confirm( {
						message:"确认删除？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {
								$('#gridId_inspect_bad_record').grid("delRowData",selrow.toString());
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
				} else {
					$.message({
						iframePanel:true,
						message : "请先勾选需要处理记录！",
						cls : "warning"
					});
				}
			}
		}
	});
	
	function S4() { 
		   return (((1+Math.random())*0x10000)|0).toString(16).substring(1); 
		}; 
	// 生成GUID
	function guid() { 
	   return (S4()+S4()+""+S4()+""+S4()+""+S4()+""+S4()+S4()+S4()); 
	};

</script>