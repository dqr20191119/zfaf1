<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<cui:form id="formId_hbsq_sp_edit">
	<table class="table" style="width: 100%">
			<cui:input type='hidden' name="id" />
			<cui:input type='hidden' name="cusNumber" id="cusNumber" />
			<tr>
				<%-- <td><label>单位:</label></td>
				<td> <cui:input  id="cusNumber" name="cusNumber" data="combox_dw"   readonly="true" ></cui:input> </td> --%>
				<td><label>换班人原值班班次:</label></td>
				<td><cui:combobox  id="zbOrder" name="zbOrder" data="combox_order" readonly="true"  ></cui:combobox></td>
				<td><label>替代人班次:</label></td>
				<td><cui:combobox  id="tdrzbOrder" name="tdrzbOrder" data="combox_order"    readonly="true"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>换班人原值班时间:</label></td>
				<td><cui:datepicker componentCls="form-control" id="dutyDate" name="dutyDate"  width="160px" dateFormat="yyyy-MM-dd" readonly="true" /></td>
				<td><label>替代人:</label></td>
				<td><cui:combobox  id="tdr" name="tdr"  data="combox_ry" readonly="true"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>换班人:</label></td>
				<td><cui:combobox id="hbr" name="hbr" data="combox_ry" readonly="true"></cui:combobox></td>
				<td><label>换班时间:</label></td>
				<td><cui:datepicker componentCls="form-control" id="hbDate" name="hbDate" dateFormat="yyyy-MM-dd" width="160px" readonly="true" /></td>
			</tr>	
			<tr>
				<td><label>换班理由:</label></td>
				<td colspan="4"><cui:textarea componentCls="form-control" id="hbLy" name="hbLy" readonly="true" /></td>
			</tr>
			<tr>
				<td><label>备注:</label></td>
				<td colspan="4"><cui:textarea componentCls="form-control" id="remark" name="remark" readonly="true"  /></td>
			</tr>
			<tr>
				<td><label>附件:</label></td>
				<td colspan="4">
				<div style="border: 1px solid #c0c0c0; margin: 5px; text-align: center;">
				<!-- <h4 style="background-color: #F0F0F0; width: 100%; height: 35px; font-size: 25px; font-weight: normal;">上传附件</h4> -->
					<div style="text-align: center; height: 100px; overflow: auto;">
						<cui:uploader id="uploaderId_attachment"   removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess"
							onInit="common.onInit" onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
					</div>
				</div>
				</td>
			</tr>
			<tr id ="zhzspyj">
				<td  style="vertical-align: middle;"><label>当班指挥长意见:</label></td>
				<td colspan="4"><cui:radiolist column="2" id="spyj" name="spyj"  data="combobox_sfty" required="true" ></cui:radiolist></td>
			</tr>
			<tr>				
				<td><label>申请人:</label></td>
				<td ><cui:input componentCls="form-control" id="sqrName" name="sqrName" readonly="true"  value="${sqrName}"/></td>				
				<td><label>申请日期:</label></td>
				<td ><cui:input componentCls="form-control" id="sqDate" name="sqDate" readonly="true" value="${sqDate }" /></td>
			</tr>
		</table>
</cui:form>

<div style="margin:0 auto;text-align:center">
	 <cui:button id="sendProcessDoButton" label="办理"  componentCls="btn-primary" onClick="sendProcessDoButton" ></cui:button>
	 <cui:button id="closeDoButton" label="关闭" componentCls="btn-primary" onClick="closeDialog"></cui:button>	
</div>


<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	$.parseDone(function() {
		if(USER_LEVEL!=1){
			//$("#job").combobox("option","required","false");
		}
		var id = '${id}';
		if(id) {//修改
			loadForm("formId_hbsq_sp_edit", "${ctx}/zbgl/hbsq/getById?id="+id, function(data) {
				 
			});
			//加载附件
			$.ajax({
				type : 'post',
				url : '${ctx}/xtgl/planeLayer/findFile.json',
				data: {
					id : id
				},
				dataType : 'json',
				success : function(data) {
					if(data.success) {
						common.loadAffix("uploaderId_attachment", data.obj, true,false);//加载附件
					}  else {
						$.message({message:"操作失败！", cls:"error"}); 
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"}); 
				}
			});
		}
	});

	
	
function  closeDialog() {
	$("#dialogId_hbsq_sp").dialog("close");
}	

/**
 审批办理
 */
function sendProcessDoButton() {
	$.confirm("确认是否办理？", function(r) {
		if(r) {
			var validFlag = $("#formId_hbsq_sp_edit").form("valid");
			if(!validFlag) {
				return;
			}
			var formData = $("#formId_hbsq_sp_edit").form("formData");
			var id = formData.id;
			var spyj = formData.spyj;
			//alert("id="+id+"审批意见="+spyj);
			 $.ajax({
				type : 'post',
				url : '${ctx}/zbgl/hbsq/updateZtOrSpzt',
				data: {
					id : id,
					spyj :spyj
				},
				dataType : 'json',
				success : function(data) {
					if(data.code == "200") {
						$.message({message:"操作成功！", cls:"success"});
						$("#dialogId_hbsq_sp").dialog("close");
						$("#gridId_hbsq_sp").grid("reload");
					}  else {
						$.message({message:"操作失败！", cls:"error"}); 
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"}); 
				}
			}); 
		}
	}); 
}
	
    
    
</script>
