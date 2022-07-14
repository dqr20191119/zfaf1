<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_gzzgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="wgiCusNumber" />
		<cui:input type='hidden' name="wgiStatus" />
		<table class="table">
			<tr>
				<th>组名称：</th>
				<td colspan="4"><cui:input name="wgiWorkgroupName" width="400" required="true"></cui:input></td>
			</tr>
			<tr>
				<th>组任务：</th>
				<td colspan="4"><cui:textarea name="wgiWorkgroupTask" width="400" required="true"></cui:textarea></td>
			</tr>
			<tr>
				<th>成员角色：</th>
				<td><cui:combobox id="comboboxId_gzzgl_role" data="combobox_yjct_memberRole"></cui:combobox></td>
				<th>警员：</th>
				<td><cui:combotree id="combotreeId_gzzgl_police" url="${ctx}/common/authsystem/findDeptPoliceForCombotree"></cui:combotree></td>
				<td><cui:button label="确定" onClick="selectMember"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>

<cui:accordion id="accordionId_gzzgl_police" heightStyle="fill">
	<h3>组长</h3>
	<div>
		<table id="tableId_gzzgl_police_1" class="table">
		
		</table>
	</div>
	<h3>副组长</h3>
	<div>
		<table id="tableId_gzzgl_police_2" class="table">
		
		</table>
	</div>
	<h3>组员</h3>
	<div>
		<table id="tableId_gzzgl_police_3" class="table">
		
		</table>
	</div>
</cui:accordion>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_gzzgl_edit", "${ctx}/yjct/gzzgl/getById?id=" + id, function(data) {
				
				initGzzcyInfo(data.gzzcyEntityList);
			});
		}		
		
		pos = 0;
	});
	
	function initGzzcyInfo(gzzcyEntityList) {
		
		for(var i in gzzcyEntityList) {
			var trId = "tableId_gzzgl_police_" + gzzcyEntityList[i].wgmUserRole + "_tr" + pos;
			var html = "<tr id='" + trId + "'>" +
				"<td>" + gzzcyEntityList[i].wgmPoliceName + 
				"<input class='coralui-textbox' type='hidden' id='member_id_" + pos + "' value='" + gzzcyEntityList[i].id + "' />" +	
				"<input class='coralui-textbox' type='hidden' id='member_cus_number_" + pos + "' value='" + gzzcyEntityList[i].wgmCusNumber + "' />" +	
				"<input class='coralui-textbox' type='hidden' id='member_police_id_" + pos + "' value='" + gzzcyEntityList[i].wgmPoliceId + "' />" +
				"<input class='coralui-textbox' type='hidden' id='member_police_name_" + pos + "' value='" + gzzcyEntityList[i].wgmPoliceName + "' />" +
				"<input class='coralui-textbox' type='hidden' id='member_police_role_" + pos + "' value='" + gzzcyEntityList[i].wgmUserRole + "' />" +
				"<input class='coralui-textbox' type='hidden' id='member_workgroup_fid_" + pos + "' value='" + gzzcyEntityList[i].wgmWorkgroupFid + "' />" +				
				"</td>" + 
				"<td>角色任务：</td>" +
				"<td><input class='coralui-textbox' id='member_police_task_" + pos + "' value='" + gzzcyEntityList[i].wgmRoleTask + "'/></td>" +
				"<td><a onclick='deleteMember(\"#" + trId + "\", \"#member_id_" + pos + "\")'>删除</a></td>" +
				"</tr>";
			
			$("#tableId_gzzgl_police_" + gzzcyEntityList[i].wgmUserRole).append(html);
			$.parser.parse($("#tableId_gzzgl_police_" + gzzcyEntityList[i].wgmUserRole));
			$('#accordionId_gzzgl_police').accordion("option", "active", 0);
			pos++;
		}
	}
	
	function selectMember() {
		
		var role = $("#comboboxId_gzzgl_role").combobox("getValue");
		var policeId = $("#combotreeId_gzzgl_police").combotree("getValue");
		var policeName = $("#combotreeId_gzzgl_police").combotree("getText");
		
		if(!policeId) {
			$.message({message:"请选择警员！", cls:"waring"}); 
			return;
		}
		
		// 校验是否存在
		for(var i = 0; i < pos; i++) {
			if($("#member_police_id_" + i).length > 0) {			 
				if(policeId == $("#member_police_id_" + i).textbox("getText")) {
					$.message({message:"警员已选择！", cls:"waring"}); 
					$("#combotreeId_gzzgl_police").combotree("setValue", "");
					return;
				}			 
			}			
		}	
		
		var trId = "tableId_gzzgl_police_" + role + "_tr" + pos;
		var html = "<tr id='" + trId + "'>" +
			"<td>" + policeName + "<input class='coralui-textbox' type='hidden' id='member_police_id_" + pos + "' value='" + policeId + "' />" +
			"<input class='coralui-textbox' type='hidden' id='member_police_name_" + pos + "' value='" + policeName + "' />" +
			"<input class='coralui-textbox' type='hidden' id='member_police_role_" + pos + "' value='" + role + "' />" +
			"</td>" + 
			"<td>角色任务：</td>" +
			"<td><input class='coralui-textbox' id='member_police_task_" + pos + "' /></td>" +
			"<td><a onclick='deleteMember(\"#" + trId + "\", null)'>删除</a></td>" +
			"</tr>";
			
		$("#tableId_gzzgl_police_" + role).append(html);
		$.parser.parse($("#tableId_gzzgl_police_" + role));
		$('#accordionId_gzzgl_police').accordion("option", "active", role-1);
		$("#combotreeId_gzzgl_police").combotree("setValue", "");
		pos++;
	}
	
	function deleteMember(trId, memId) {
		
		$.confirm("确认删除？", function(r) {
			if(r) {
				if(memId) {
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/yjct/gzzgl/deleteGzzcyById',
						data: {
							cyId : $(memId).textbox("getText")
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"}); 
								$(trId).remove();
							} else {
								$.message({message:"操作失败！", cls:"error"}); 
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"}); 
						}
					});
				} else {
					$(trId).remove();
				}
			}
		});
	}
</script>