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
		<table id="table_fqzg" class="table table-fixed" style="width: 90%;">
			<tr>
				<td width="15%" class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input readonly="true"   componentCls="form-control" value="${inspect.inoInspectName}"  ></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker readonly="true"  value="${inspect.inoInspectTime}" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>通报监狱：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.inoNoticeCusNumber}" ></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>制度执行：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true"  componentCls="form-control" >${inspect.inoRuleExecute}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>凌晨检查：</label></td>
				<td class="td_context"  colspan="3">
					<cui:textarea readonly="true" componentCls="form-control" >${inspect.inoMorningCheck}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>工作要求：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoSuggesition}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true" componentCls="form-control" >${inspect.inoProblem}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.iprPoliceNames}" ></cui:input>
				</td>
				<td class="td_title"><label>上报人员：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.inoCrteUserName}" ></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核状态：</label></td>
				<td class="td_context">
					<cui:radiolist readonly="true" value="${inspect.inoApprovalSttsIndc}" data="radiolistApprovalSttsIndc"></cui:radiolist>
				</td>
				<td class="td_title"><label>审批领导：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.checkPoliceName}"  ></cui:input>
				</td>
			</tr>    
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true"  componentCls="form-control">${inspect.inoRemark}</cui:textarea>
				</td>
			</tr>
			
			<tr>
				<td class="td_title"><label>整改部门：</label></td>
				<td class="td_context" colspan="3">
					<cui:combobox id="ccdDprtmntIdnty" name="ccdDprtmntIdnty" multiple="true" 
					 url="${ctx}/common/areadevice/nullJson.json"  componentCls="form-control"
					  onSelect="onSelectDprtmnt" onUnSelect="onUnSelectDprtmnt"></cui:combobox>
				</td>
			</tr>
		</table>
</center>
<div class="dialog-buttons">
	<cui:button label="提交" componentCls="btn-primary" onClick="launchDetail.submit"></cui:button>
</div>
<script>
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE;							//监狱编号
	var userId=jsConst.USER_ID;								//登录人
	
	//0未提交 ，1已提交、待审核 ， 2不同意  ， 3 同意
	var radiolistApprovalSttsIndc=[{
		'value' : '2',
		'text' : '<font color="#FF0000">不同意</font>'
	}, {
		'value' : '3',
		'text' : '<font color="green">同意</font>'
	}];
	
	function onSelectDprtmnt(e,ui){
		//因为onUnSelect事件不管用，所以在此处处理
		if($("#trId_"+ui.value).length > 0){
			$("#trId_"+ui.value).remove();
			return;
		}

		var tr="<tr id='trId_"+ui.value+"'>"+
					"<td class='td_title'><label  id='ccdDprtmntName_"+ui.value+"'>"+ui.text+"</label></td>"+
					"<td class='td_context' colspan='3'>"+
						"<textarea id='ccdChangedContent_"+ui.value+"' style='height:80px;width:100%'></textarea>"+
					"</td>"+
			  "</tr>";
		$("#table_fqzg").append(tr);
	}
	function onUnSelectDprtmnt(e,ui){
		console.log("bingMeiShenMoLuanYong");
	}
	function queryLaunchList(){
		var url = "${ctx}/xxyp/change/launchListPage.json";
		var data = {"inoNoticeCusNumber" : cusNumber};
		$("#gridId_launch").grid("option", "postData", data);
		$("#gridId_launch").grid("reload",url);
	}
	$.parseDone(function(){
		$("#ccdDprtmntIdnty").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
		
		launchDetail={
				submit:function(){
					//验证
					if(!launchDetail.valida()){
						return;
					}
					
					//整改部门信息
					var changeDepartments=new Array();
					var departmentIds=$("#ccdDprtmntIdnty").combobox("getValues");
					
					var changeData={
						"cchCusNumber":cusNumber,
						"cchInspectId":"${inspect.id}",
						"cchStatus":'1',
						"cchChangedContent":null
					}
					$.ajax({
						type : 'post',
						url : '${ctx}/xxyp/change/add.json',
						data : changeData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								var changedId=data.obj;
								for(var i=0;i<departmentIds.length;i++){
									changeDepartments.push({
										'ccdCusNumber':cusNumber,
										'ccdChangedId':changedId,
										'ccdDprtmntIdnty':departmentIds[i],
										'ccdDprtmntName':$("#ccdDprtmntName_"+departmentIds[i]).text(),
										'ccdChangedContent':$("#ccdChangedContent_"+departmentIds[i]).val()
									});
								}
								
								$.ajax({
									type : 'post',
									url : '${ctx}/xxyp/change/batchInsertChaDep.json',
									contentType: "application/json; charset=utf-8",
									data : JSON.stringify(changeDepartments),
									dataType : 'json',
									success : function(data) {
										if(data.success){
											$.message({
												message : "操作成功",
												cls : "success",
												iframePanel:true
											});
											queryLaunchList();
											$("#launchdetaildialog").dialog("close");
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
				},
				valida:function(){
					var departmentIds=$("#ccdDprtmntIdnty").combobox("getValues");
					if(!departmentIds.length>0){
						$.alert({
							message:"请选择整改部门",
							title:"信息提示",
							iframePanel:true
						});
						return false;				
					}
					
					for(var i=0;i<departmentIds.length;i++){
						if($("#ccdChangedContent_"+departmentIds[i]).val()==""){
							$.alert({
								message:"请填写整改内容",
								title:"信息提示",
								iframePanel:true
							});
							return false;	
						}
					}
					return true;
				}
			
		}
	});
</script>