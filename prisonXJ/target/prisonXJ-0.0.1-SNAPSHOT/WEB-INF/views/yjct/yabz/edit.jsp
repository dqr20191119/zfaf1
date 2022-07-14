<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	.selectHid {
		text-align: center;
		width: 160px;
		height: 30px;
		font-size: 11.5px;
		cursor: pointer;
		margin-top: 5px;
	} 
	
	.hid {
		text-align: center;
		padding-top: 10px;
		width: 115px;
		height: 50px;
		font-size: 11.5px;
		cursor: pointer;
		margin-top: 5px;
		margin-left: 35px;
		background: #fafafa;
		background: url('${ctx}/static/emergency/style/images/left_button.png') no-repeat;
	}
	
	.divClass_select {
		width: 180px;
		height: 430px;
		margin-top: 10px;
		margin-left: 20px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
	
	#divId_yabz_gzzright table {
		width: 540px;
		border: 1px solid #ededed; 
		margin-top: 20px; 
		margin-bottom: 20px;
	}
	
	#divId_yabz_gzzright th {
		background: #4692f0;
		text-align: center;
		border: 1px solid #ededed;
	}
	
	#divId_yabz_gzzright td {
		border: 1px solid #ededed;
	}
	
	.disDescBtn {
		width: 100px;
		text-align: center;
		margin-right: 5px;
		color: #4692f0;
	}
	
	.unChecked_div {
		width: 180px;
		height: 300px;
		margin-left: 50px;
		margin-top: 10px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
</style>

<cui:tabs id="tabsId_yabz_edit" heightStyle="fill">
	<ul>
       <li><a href="#tabsId_yabz_edit_1">基本信息</a></li>
       <li><a href="#tabsId_yabz_edit_2">工作组</a></li>
       <li><a href="#tabsId_yabz_edit_3">处置流程</a></li>    
   	</ul>
    <div id="tabsId_yabz_edit_1">
    	<cui:form id="formId_yabz_edit" heightStyle="fill">
			<cui:input type='hidden' name="id" id="inputId_yabz_planId" />
			<cui:input type='hidden' name="epiCusNumber" />
			<cui:input type='hidden' name="epiPlanStatus" />
			<cui:input type='hidden' name="epiAppFlow" />	
	 		<table class="table">
				<tr>
					<th>预案名称：</th>
					<td><cui:input name="epiPlanName" required="true"></cui:input></td>		
					<th>预案类别：</th>
					<td><cui:combobox id="comboboxId_yabz_planType" name="epiPlanType" data="combobox_yjct_planType" required="true"></cui:combobox></td>	
				</tr>	
				<tr>
					<th>预案描述：</th>
					<td colspan="3"><cui:textarea name="epiPlanDesc" width="500" required="true"></cui:textarea></td>
				</tr>			 
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdatePlanInfo"></cui:button>
		</div>
  	</div>
    <div id="tabsId_yabz_edit_2">
 		<cui:form heightStyle="fill">
			<table style="height: 100%;">
				<tr>
				 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的工作组</div></td>
				 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的工作组<div></td>	
				 	<td>&nbsp;</td>									 	
				</tr> 
				<tr>
					<td>
						<div id="divId_yabz_gzzleft" class="divClass_select"><ul></ul></div>
					</td>
					<td>
						<div id="divId_yabz_gzzmiddle" class="divClass_select"><ul></ul></div>
					</td>
					<td>
						<div id="divId_yabz_gzzright" style="height: 430px; margin-left: 20px; margin-top: 10px; padding-right: 30px; overflow-y: auto; overflow-x: hidden;">
							<div id="divId_yabz_gzzright_1" style="width: 540px;"></div>
							<div id="divId_yabz_gzzright_2" style="width: 540px;"></div>
							<div id="divId_yabz_gzzright_3" style="width: 540px;"></div>
							<div id="divId_yabz_gzzright_4" style="width: 540px;"></div>
						</div>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateGzzInfo"></cui:button>
		</div>
    </div>
    <div id="tabsId_yabz_edit_3">
		<cui:form heightStyle="fill">
			<table style="height: 100%;">
				<tr>
				 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的处置方法</div></td>
				 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的处置方法<div></td>	
				 	<td>&nbsp;</td>									 	
				</tr> 
				<tr>
					<td>
						<div id="divId_yabz_czlcleft" class="divClass_select"><ul></ul></div>						 
					</td>
					<td>
						<div id="divId_yabz_czlcmiddle" class="divClass_select"><ul></ul></div>						 
					</td>
					<td>
						<table style="height: 50px; width: 540px; margin-left: 20px; margin-top: 10px; border-bottom: 1px solid #4692f0;">
							<cui:input type='hidden' id="inputId_yabz_czlcMethodId" />
							<tr height="25px;">
								<td width="80px" align="center">方法描述：</td>
								<td id="tdId_yabz_methodDesc"></td>
							</tr>
							<tr height="25px;">
								<td width="80px" align="center">关联操作：</td>
								<td id="tdId_yabz_relAction"></td>
							</tr>	
						</table>
						<div id="divId_yabz_czlcright" style="height: 370px; margin-left: 20px; margin-top: 10px; padding-right: 30px; overflow-y: auto; overflow-x: hidden;">
							<!-- 事件记录 -->
							<div id="divId_yabz_edit_relAction_1" style="display: none; height: 100%;">
 						 		<table class="table">
									<tr>
										<td nowrap="nowrap">报告人姓名：</td>
										<td><cui:input name="" id="edit_sjlj1"   ></cui:input></td>	
									</tr>
									<tr>
										<td nowrap="nowrap">事件发生时间：</td>
										<td><cui:input name="" id="edit_sjlj2"   ></cui:input></td>
									</tr>	
									<tr>		
										<td nowrap="nowrap">事件性质：</td>
										<td><cui:input name=""  id="edit_sjlj3" ></cui:input></td>	
									</tr>
									<tr>
										<td nowrap="nowrap">事件发生地点：</td>
										<td><cui:input name="" id="edit_sjlj4" ></cui:input></td>
									</tr>		
									<tr>
										<td nowrap="nowrap">事件发生单位：</td>
										<td><cui:input name="" id="edit_sjlj5" ></cui:input></td>
									</tr>
									<tr>
										<td nowrap="nowrap">现场情况：</td>
										<td><cui:textarea name="" width="300"  id="edit_sjlj6" ></cui:textarea></td>
									</tr>
								</table>
								<div style="text-align: center;">
									<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateEventRecord"></cui:button>
								</div>
							</div>
							
							<!-- 工作组 -->
							<div id="divId_yabz_edit_relAction_2" style="display: none; text-align: center; height: 100%;">						 
								<table>
									<tr>
 									 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的工作组</div></td>
									 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的工作组<div></td>								 	
									</tr> 
									<tr>
										<td><div id="divId_yabz_edit_workgroup_unChecked" class="unChecked_div" ><ul></ul></div></td>
										<td><div id="divId_yabz_edit_workgroup_checked" class="unChecked_div" ><ul></ul></div></td>
									</tr>
								</table>
								<div style="text-align: center;">
									<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateWorkGroup"></cui:button>
								</div>
							</div>
							
							<!-- 专家协助 -->
							<div id="divId_yabz_edit_relAction_3" style="display: none; text-align: center; height: 100%;">						 
								<table>
									<tr>
									 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的专家</div></td>
									 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的专家<div></td>	
									</tr>
									<tr>
										<td><div id="divId_yabz_edit_expert_unChecked" class="unChecked_div" ><ul></ul></div></td>
										<td><div id="divId_yabz_edit_expert_checked" class="unChecked_div" ><ul></ul></div></td>
									</tr>
								</table>
								<div style="text-align: center;">
									<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateExpert"></cui:button>
								</div>
							</div>
							
							<!-- 应急物资 -->
							<div id="divId_yabz_edit_relAction_4" style="display: none; text-align: center; height: 100%;">								
								<div style="background-color: #f2f9f9; margin-bottom: 10px;"> 
									<table class="table">
										<tr>
											<td>物资名称：</td>
											<td><cui:combobox id="comboboxId_yabz_edit_materialName" onChange="updateMaterialTotal"></cui:combobox></td>
											<td>总数：</td>
											<td><cui:input id="comboboxId_yabz_edit_materialTotal" disabled="true"></cui:input></td>
										</tr>
										<tr>
											<td>所需数量：</td>
											<td><cui:input id="comboboxId_yabz_edit_materialNum" componentCls="form-control"></cui:input></td>											
											<td colspan="2"><cui:button label="添加" onClick="selectMaterial"></cui:button></td>
										</tr>
									</table>
								</div>
								<table id="tableId_yabz_edit_materialSelect" style="border-collapse: separate; border-spacing: 2px 2px;">										 
								</table>
								<div style="text-align: center;">
									<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateMaterial"></cui:button>
								</div>		
							</div>
							
							<!-- 法律法规 -->
							<div id="divId_yabz_edit_relAction_5" style="display: none; text-align: center; height: 100%;">								
								<div style="background-color: #f2f9f9; margin-bottom: 10px;"> 
									<table class="table">
										<tr>
											<td>法规名称：</td>
											<td><cui:combobox id="comboboxId_yabz_edit_lawName" onChange="updateLawRemark"></cui:combobox></td>
											<td>备注：</td>
											<td><cui:input id="comboboxId_yabz_edit_lawRemark" disabled="true"></cui:input></td>											
										</tr>
										<tr>
											<td><cui:button label="添加" onClick="selectLaw"></cui:button></td>
											<td colspan="3">&nbsp;</td>
										</tr>
									</table>
								</div>
								<table id="tableId_yabz_edit_lawSelect" style="border-collapse: separate; border-spacing: 2px 2px;">										 
								</table>
								<div style="text-align: center;">
									<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdateLaw"></cui:button>
								</div>		
							</div>
						</div>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="btn-primary" label="保存流程" onClick="saveOrUpdateCzffInfo"></cui:button>
		</div>
    </div> 		
</cui:tabs>

<script>
	
	$.parseDone(function() {
		
		updateFlag = false;
		var id = '${id}';
		if(id) {
			loadForm("formId_yabz_edit", "${ctx}/yjct/yabz/getById?id=" + id, function(data) {				
			});
			
			updateFlag = true;
		}
		
		// 初始化工作组
		gzzUncheckDatas = [];
		gzzCheckDatas = [];
						 
		// 初始化处置流程
		czlcUncheckDatas = [];
		czlcCheckDatas = [];	
		
		if(updateFlag) {
			$("#tabsId_yabz_edit").tabs({
				onActivate: function(event, ui) {
					var index = $("#tabsId_yabz_edit").tabs("option", "active");
					if(index == 1) {
						
						// 加载工作组
						loadGzzCheckInfo();
						loadGzzUncheckInfo();						
					} else if(index == 2) {
						
						// 加载处置方法
						loadCzlcCheckInfo();
						loadCzlcUncheckInfo();						
					}
				}
			});
		}
	});
</script>