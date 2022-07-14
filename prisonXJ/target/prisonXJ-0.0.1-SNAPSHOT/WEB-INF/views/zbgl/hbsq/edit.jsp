<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<cui:form id="formId_hbsq_edit">
	<table class="table" style="width: 100%">
			<cui:input type='hidden' name="id" />
			<%-- <cui:input type='hidden' id="cusNumber" name="cusNumber"/> --%>
			<tr>
				<%-- <td><label>单位:</label></td>
				<td><cui:input  id="cusNumber" name="cusNumber"   readonly="true"   ></cui:input></td> --%>
				<td><label>换班人值班班次:</label></td>
				<td><cui:combobox  id="zbOrder" name="zbOrder" data="combox_order"     required="true" ></cui:combobox></td>
				<td><label>替代人班次:</label></td>
				<td><cui:combobox  id="tdrzbOrder" name="tdrzbOrder" data="combox_order"     required="true" ></cui:combobox></td>
			</tr>
			<tr>
				<td><label>换班人原值班时间:</label></td>
				<td><cui:datepicker componentCls="form-control" id="dutyDate" name="dutyDate"   width="190px" dateFormat="yyyy-MM-dd" required="true" /></td>
				<td><label>替代人:</label></td>
				<td><%-- <cui:combobox  id="tdr" name="tdr"  data="combox_ry" required="true" ></cui:combobox> --%>
				<cui:autocomplete id="tdr" name="tdr" source="combox_ry" postMode="value" placeholder ="请输入要搜索的姓名" required="true"  ></cui:autocomplete>
				</td>
			</tr>
			<tr>
				<td><label>换班人:</label></td>
				<td><%-- <cui:combobox id="hbr" name="hbr" data="combox_ry" required="true" ></cui:combobox> --%>
				<cui:autocomplete id="hbr" name="hbr" source="combox_ry" postMode="value"  placeholder ="请输入要搜索的姓名" required="true" ></cui:autocomplete></td>
				<td><label>替代人原值班时间:</label></td>
				<td><cui:datepicker componentCls="form-control" id="hbDate" name="hbDate" dateFormat="yyyy-MM-dd" width="190px" required="true" /></td>
			</tr>	
			<tr>
				<td><label>换班理由:</label></td>
				<td colspan="4"><cui:textarea componentCls="form-control" id="hbLy" name="hbLy" required="true"  /></td>
			</tr>
			<tr>
				<td><label>备注:</label></td>
				<td colspan="4"><cui:textarea componentCls="form-control" id="remark" name="remark"  /></td>
			</tr>
			<tr>
				<td><label>附件:</label></td>
				<td colspan="4">
				<div style="border: 1px solid #c0c0c0; margin: 5px; text-align: center;">
				<h4 style="background-color: #F0F0F0; width: 100%; height: 35px; font-size: 25px; font-weight: normal;">上传附件</h4>
				<div style="text-align: center; height: 100px; overflow: auto;">
					<cui:uploader id="uploaderId_attachment" buttonText="请选择文件..."  removeCompleted="false" fileObjName="uploadFile" uploader="${ctx}/common/affix/upload" onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess"
						onInit="common.onInit" onClearQueue="f_onClearQueue" swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
				</div>
			</div>
				</td>
			</tr>
			<%-- <tr id ="zhzspyj">
				<td  style="vertical-align: middle;"><label>当班指挥长意见:</label></td>
				<td colspan="4"><cui:radiolist column="2" id="spyj" name="spyj"  data="combobox_sfty" ></cui:radiolist></td>
			</tr> --%>
			<tr>				
				<td><label>申请人:</label></td>
				<td ><cui:input componentCls="form-control" id="sqrName" name="sqrName" readonly="true"  value="${sqrName}"/></td>				
				<td><label>申请日期:</label></td>
				<td ><cui:input componentCls="form-control" id="sqDate" name="sqDate" readonly="true" value="${sqDate }" /></td>
			</tr>
		</table>
</cui:form>

<div style="margin:0 auto;text-align:center">
	<cui:button id ="saveButton"  label="保存" componentCls="btn-primary"  onClick="saveOrUpdate('1')" ></cui:button>
	 <cui:button id="tjButton" label="提交" componentCls="btn-primary"  onClick="saveOrUpdate('2')" ></cui:button>
	<%--  <cui:button id="sendProcessDoButton" label="办理"  componentCls="btn-primary" onClick="sendProcessDoButton" ></cui:button> --%>
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
			loadForm("formId_hbsq_edit", "${ctx}/zbgl/hbsq/getById?id="+id, function(data) {
				 
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
						common.loadAffix("uploaderId_attachment", data.obj, false,true);//加载附件
					}  else {
						$.message({message:"操作失败！", cls:"error"}); 
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"}); 
				}
			});
		
		}else{//新增页面
			$("#dutyDate").datepicker("option","minDate", new Date());  
			$("#hbDate").datepicker("option","minDate", new Date());  
		}
	});

	
	
function  closeDialog() {
	$("#dialogId_hbsq").dialog("close");
}	
   

	
    
    
</script>
