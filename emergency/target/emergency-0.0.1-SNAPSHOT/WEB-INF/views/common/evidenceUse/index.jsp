<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<div>
		<p style="font-size: 15px; color: #4692f0;">已选证据</p>
		<div style="height: 210px; border-top: 2px solid #4692f0;">
			<cui:grid id="gridId_evidenceUseSelected" rownumbers="true" multiselect="false" width="auto" fitStyle="fill" frozenColumns="true" shrinkToFit="false">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true" frozen="true">id</cui:gridCol>
					<cui:gridCol name="einFileName" hidden="true" frozen="true">einFileName</cui:gridCol>
					<cui:gridCol name="einFilePath" hidden="true" frozen="true">einFilePath</cui:gridCol>
					<cui:gridCol name="einFtpPath" hidden="true" frozen="true">einFtpPath</cui:gridCol>
					<cui:gridCol name="ceuEvidenceId" hidden="true" frozen="true">id</cui:gridCol>
					<cui:gridCol name="" width="100" align="center" formatter="formatEvidenceUseSelectedOprt" frozen="true">操作</cui:gridCol>
					<cui:gridCol name="einTitle" width="300" frozen="true">标题</cui:gridCol>
					<cui:gridCol name="einCrteTime" width="150" align="center">创建时间</cui:gridCol>	
					<cui:gridCol name="einAddrs" width="200">发生地点</cui:gridCol>	
					<cui:gridCol name="einCameraName" width="200">摄像机名称</cui:gridCol>
					<cui:gridCol name="einFileTypeIndc" width="100" align="center" formatter="convertCode" revertCode="true" formatoptions="{
					'data': combobox_evidenceUse_fileType
					}">文件类型</cui:gridCol>
				</cui:gridCols>
			</cui:grid>
		</div>
	</div>
	
	<div class="dialog-buttons">
		<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateEvidenceUseSelected"></cui:button>
	</div>
	
	<div>
		<%-- <cui:form id="formId_evidenceUse_query">
			<table class="table">			
				<tr>
					<td>创建时间：</td>
					<td>
						<cui:datepicker name="einCrteStartTime" dateFormat="yyyy-MM-dd"></cui:datepicker>
						至
						<cui:datepicker name="einCrteEndTime" dateFormat="yyyy-MM-dd"></cui:datepicker>
					</td>				 
								 
					 <td>
						<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
						<cui:button label="重置" onClick="reset"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form> --%>
		<p style="font-size: 15px; color: #4692f0; margin-top: 5px;">待选证据</p>
		<div style="height: 260px; border-top: 2px solid #4692f0;">
			<cui:grid id="gridId_evidenceUse" rownumbers="true" multiselect="false" width="auto" fitStyle="fill" frozenColumns="true" shrinkToFit="false" 
				url="${ctx}/common/evidence/searchData"
				sortname="einCrteTime, desc" rowNum="5">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true" frozen="true">id</cui:gridCol>
					<cui:gridCol name="einFileName" hidden="true" frozen="true">einFileName</cui:gridCol>
					<cui:gridCol name="einFilePath" hidden="true" frozen="true">einFilePath</cui:gridCol>
					<cui:gridCol name="einFtpPath" hidden="true" frozen="true">einFtpPath</cui:gridCol>
					<cui:gridCol name="" width="100" align="center" formatter="formatEvidenceUseOprt" frozen="true">操作</cui:gridCol>
					<cui:gridCol name="einTitle" width="300" frozen="true">标题</cui:gridCol>
					<cui:gridCol name="einCrteTime" width="150" align="center">创建时间</cui:gridCol>	
					<cui:gridCol name="einAddrs" width="200">发生地点</cui:gridCol>	
					<cui:gridCol name="einCameraName" width="200">摄像机名称</cui:gridCol>
					<cui:gridCol name="einFileTypeIndc" width="100" align="center" formatter="convertCode" revertCode="true" formatoptions="{
					'data': combobox_evidenceUse_fileType
					}">文件类型</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="gridId_evidenceUse" />
			</cui:grid>
		</div>
	</div>
</div>

<div id="divId_evidenceImg" style="display: none; position: absolute; top: 60px; left: 200px; border: 1px solid red;">
	<img src="" width="300" height="300">
</div>
	
<script>
	 	
	var combobox_evidenceUse_fileType = <%=CodeFacade.loadCode2Json("4.20.45")%>;
	
	var evidenceUsePos = 0;
	
	$.parseDone(function() {
		
		// 加载已选择证据
		loadSelectedEvidence();
	});
	
	function loadSelectedEvidence() {
		
		var ceuYwId = '${ceuYwId}';
		var ceuYwType = '${ceuYwType}';
		
		$.ajax({
			type : 'post',
			url : '${ctx}/common/evidenceUse/searchAllData',
			data : {
				ceuYwId : ceuYwId,
				ceuYwType : ceuYwType
			},
			dataType : 'json',
			success : function(data) {
				
				for(var i = 0; i < data.length; i++) {
					$("#gridId_evidenceUseSelected").grid("addRowData", data[i].id, data[i]);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function formatEvidenceUseSelectedOprt(cellvalue, options, rawObject) {
		
		var html = "<a href='javascript: void(0);' onclick='showEvidence(\"" + rawObject.ceuEvidenceId + "\", \"" + rawObject.einFileTypeIndc + "\", \"" + rawObject.einFileName + "\", \"" + rawObject.einFtpPath + "\", \"" + rawObject.einFilePath + "\")' onmouseleave='hideEvidence()' style='color: #4692f0;'>预览</a>"
				+ "<a href='javascript: void(0);' onclick='deleteEvidence(\"" + rawObject.id + "\", \"" + options.rowId + "\")' style='color: #4692f0; margin-left: 5px;'>删除</a>";
		return html;	
	}
	
	function formatEvidenceUseOprt(cellvalue, options, rawObject) {
		
		var html = "<a href='javascript: void(0);' onclick='showEvidence(\"" + rawObject.id + "\", \"" + rawObject.einFileTypeIndc + "\", \"" + rawObject.einFileName + "\", \"" + encodeURIComponent(rawObject.einFtpPath) + "\", \"" + encodeURIComponent(rawObject.einFilePath) + "\")' onmouseleave='hideEvidence()' style='color: #4692f0;'>预览</a>"
				+ "<a href='javascript: void(0);' onclick='selectEvidence(\"" + rawObject.id + "\")' style='color: #4692f0; margin-left: 5px;'>选择</a>";
		return html;	
	}
	
	function deleteEvidence(id, rowId) {   
		
        $.confirm("确认删除？", function(r) {
            if(r) {	
            	
            	if(id != "") {
            		$.ajax({
			             type: "post",
			             url: "${ctx}/common/evidenceUse/deleteByIds",
			             data: {
			            	 ids: id
			             },
			             dataType: "json",
			             success: function(data) {
			            	 
			            	 if(data.code == "1") {
			 					$.message({message:"操作成功！", cls:"success"});
			 					$("#gridId_evidenceUseSelected").grid("delRowData", rowId);
			 				} else {
			 					$.message({message:"操作失败！", cls:"error"}); 
			 				}
	                     },
	                     error: function(XMLHttpRequest, textStatus, errorThrown) {						
	                    	 $.message({message:"操作失败！", cls:"error"}); 
						 }
			        }); 
            	} else {
            		
            		// 删除列表
	             	/* for(var i = 0; i < length; i++) {
	            		// selarrrow.length 每次删除的时候此值都在动态改变,selarrrow也在动态改变, 每次删除第一个OK
	            		$("#zfddSnplddEditGrid").grid("delRowData", selarrrow[0]);			            		
	            	} */
            		$("#gridId_evidenceUseSelected").grid("delRowData", rowId);
            	}				            	
            }
        });			           				
    }
	
	function selectEvidence(id) {
		
		var rowSelectedData = $("#gridId_evidenceUseSelected").grid("getRowData");
		if(rowSelectedData && rowSelectedData.length > 0) {
			for(var i = 0; i < rowSelectedData.length; i++) {
				if(id == rowSelectedData[i].ceuEvidenceId) {
					$.message({message:"证据已选择，请重新选择！", cls:"waring"});
					return;
				}
			}
		}
		
		var rowData = $("#gridId_evidenceUse").grid("getRowData", id);
		rowData.ceuEvidenceId = id;
		rowData.id = "";
		
		evidenceUsePos++;
		$("#gridId_evidenceUseSelected").grid("addRowData", evidenceUsePos, rowData);
	}
	
	function showEvidence(id, fileType, fileName, ftpPath, filePath) {
		
		if(fileType == "1") {
			
			// 截图
			$("#divId_evidenceImg img").attr("src", "${ctx}/common/evidence/download?id=" + id);
			$("#divId_evidenceImg").show();
		} else {
			
			// 录像
			var vc = window.top.videoClient;
			var file = decodeURIComponent(filePath) + fileName;
			vc.setLayout(1, function(result) {
				if(result && result.success) {
					vc.playVideoFile(0, file, decodeURIComponent(ftpPath), null, null);
				}
			});
		}
	}
	
	function hideEvidence() {
			
		$("#divId_evidenceImg img").attr("src", "");
		$("#divId_evidenceImg").hide();
	}
	
	function saveOrUpdateEvidenceUseSelected() {
		
		var data = {};
		var ceuYwId = '${ceuYwId}';
		var ceuYwType = '${ceuYwType}';
		var flag = false;
		var j = 0;
		var selectedEvidenceArray = $("#gridId_evidenceUseSelected").grid("getRowData");			
		if(selectedEvidenceArray && selectedEvidenceArray.length > 0) {
			for(var i = 0; i < selectedEvidenceArray.length; i++) {
				if(selectedEvidenceArray[i].id == "") {
					data["commonEvidenceUseEntityList[" + j + "].ceuEvidenceId"] = selectedEvidenceArray[i].ceuEvidenceId;
					data["commonEvidenceUseEntityList[" + j + "].ceuYwType"] = ceuYwType;
					data["commonEvidenceUseEntityList[" + j + "].ceuYwId"] = ceuYwId;
					
					flag = true;
					j++;
				}
			}
		}
		
		if(!flag) {
			$.message({message:"请选择证据信息！", cls:"waring"});
			return;
		}
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/common/evidenceUse/saveOrUpdate',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_common_relEvidence").dialog("close");
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
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_fggl").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
					}
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/yjct/fggl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_fggl").grid("reload");
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
			}); 		
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}	
</script>