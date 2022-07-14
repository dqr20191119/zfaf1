<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:form id="formId_yjjl_report" heightStyle="fill">
  	<table class="table" style="width: 100%;">
  		<tr height="80">
  			<th>证据信息：</th> 
  			<td>
  				<cui:button componentCls="btn-primary" label="查看" onClick="yjctRelEvidence"></cui:button>
  			</td> 			 
  		</tr>
  		<tr height="80">
  			<th>人员登记表：</th> 
  			<td>
  				<cui:uploader id="yjjlReportRydjbUploadId" fileObjName="uploadFile" 
					uploader="${ctx}/common/affix/upload" formData="{'fileType':'3'}" 
					onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess" onInit="common.onInit"				  
					swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
  			</td>	
  		</tr>
  		<tr height="80">
  			<th>经验总结：</th> 
  			<td>
  				<cui:textarea name="ehrExperience" width="700" required="true"></cui:textarea>
  			</td>	
  		</tr>
	</table>
</cui:form>
<div class="dialog-buttons">
	<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateEventReport"></cui:button>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${recordId}';	
		loadForm("formId_yjjl_report", "${ctx}/yjct/yjjl/getById?id=" + id, function(data) {
			
			common.loadAffix("yjjlReportRydjbUploadId", data.rydjbAffixIdList, false);
		});
 	});
	
	function yjctRelEvidence() {
		
		var ceuYwId = '${recordId}';
		var ceuYwType = "3";
		common.relEvidence(ceuYwId, ceuYwType);
	}
	
	function saveOrUpdateEventReport() {
		
		var validFlag = $("#formId_yjjl_report").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yjjl_report").form("formData");
		formData.id = '${recordId}';		
		formData.rydjbAffixIds = $("#yjjlReportRydjbUploadId_affixIds").val();
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yjjl/saveOrUpdateEventReport',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {		
					
					var type = '${type}';
					if(type == "1") {
						$("#dialogId_yjjl_zxjl").dialog("close");
					} else if(type == "2") {
						$("#dialogId_yjjl").dialog("close");
						$("#gridId_yjjl").grid("reload");
					}
					
					$.confirm({
						message : "是否需要修订该预案？", 
						iconCls : "icon-question",
						title : "提示信息",
						iframePanel : true,
						callback : function(r) {
							if(r) {
							
								$.loading({text:"正在处理中，请稍后..."});
							
								$.ajax({
									type : 'post',
									url : '${ctx}/yjct/yabz/updatePlanStatus',
									data: {
										id : '${ehrEmPlanFid}',
										epiPlanStatus: "6"
									},
									dataType : 'json',
									success : function(data) {
										$.loading("hide");
										
										if(data.code == "1") {
											$.message({message:"操作成功！", cls:"success"});
										} else {
											$.message({message:"操作失败！", cls:"error"});
										}				
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.loading("hide");
										$.message({message:"操作失败！", cls:"error"});
									}
								});
							}
						}
					});
 				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
</script>