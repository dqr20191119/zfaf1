<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	.moveLeft {
		position: absolute;
		margin-left: 30px;
		margin-top: 16px;
		width: 15px;
		height: 25px;
	}
	
	.moveRight {
		position: absolute;
		margin-left: 760px;
		margin-top: 16px;
		width: 15px;
		height: 25px;
	}
	
	.top {
		width: 760px;
		height: 40px;
		margin-left: 20px;
		margin-top: 10px;
		border: 1px solid #4692f0;
		background-color: #f2f9f9;
	}
	
	.hid {
		text-align: center;
		background: #fafafa;
		width: 143px;
		height: 32px;
		margin-left: 40px;
		margin-top: 3px;	
		border: 0px none;
		color: white;
		font-size: 10.5px;
		background: url('${ctx}/resources/common/images/top_button.png') no-repeat;
	}
	
	.selectHid {
		text-align: center;
		background: #6badfe;
		width: 130px;
		height: 20px;
		padding: 3px 0px 0px 0px;
		margin: 12px 0px 12px 20px;
		font-size: 10.5px;
		cursor: pointer;
	} 
	
	#divId_yabz_gzzleft {
		width: 180px;
		height: 410px;
		margin-top: 10px;
		margin-left: 20px;
		border: 1px solid #4692f0;
		overflow: hidden;
	}
	
	#divId_yabz_czlcleft {
		width: 180px;
		height: 394px;
		margin-top: 10px;
		margin-left: 20px;
		border: 1px solid #4692f0;
		overflow: auto;
	} 
	
	#divId_yabz_czlcright {
		width: 570px;
		height: 417px;
		margin-top: 10px;
		margin-left: 10px;
		border: 1px solid #4692f0;
	}
	
	#divId_yabz_gzzleft ul {
 		height: 400px;
	}
	
	.rightTable {
		text-align: center;
		font-size: 13px;
		border-collapse: collapse;
		border: solid #4592f0;
		border-width: 1px;
		margin-top: 10px;
		margin-left: 5px;
	}
	
	.rightTable td {
		border: solid #4592f0;
		border-width: 1px;
		height: 30px;
	}
	
	.tabhead {
		background: #cef1fa;
	}
	
	.tabContent {
		background: #edf8f8;
	}
		
	.disDescBtn {
		width: 90px;
		text-align: center;
		margin-left: 5px;
		color: rgb(30,169,236);
	}
	
	.disSelectHid { 
		text-align: center;
		width: 111px;
		height: 50px;  
		margin: 6px 0px 6px 30px;
	 	cursor: pointer; 
		color: white;
		font-size: 10.5px;
		background: url('${ctx}/resources/common/images/left_button.png') no-repeat;
	}
	
	.disSlectFont { 
 		width: 100%; 
		padding-top: 12px; 
	} 
	
	.unChecked_title {
		margin-top: 20px;
		margin-left: 65px;
		margin-right: 65px;
		border: 1px solid grey;
		text-align: center;
	}
	
	.unChecked_div {
		margin-top: 5px;
		margin-left: 20px;
		width: 250px;
		height: 280px;
		border: 1px solid grey;
		border-style: inset;
		overflow: auto;
	}
</style>

<cui:tabs id="tabsId_yabz_edit" heightStyle="fill">
	<ul>
       <li><a href="#tabsId_yabz_edit_1">????????????</a></li>
       <li><a href="#tabsId_yabz_edit_2">?????????</a></li>
       <li><a href="#tabsId_yabz_edit_3">????????????</a></li>    
   	</ul>
    <div id="tabsId_yabz_edit_1">
    	<cui:form id="formId_yabz_edit" heightStyle="fill">
			<cui:input type='hidden' name="id" />
			<cui:input type='hidden' name="epiCusNumber" />
			<cui:input type='hidden' name="epiPlanId" id="inputId_yabz_planId" />
			<cui:input type='hidden' name="epiPlanStatus" />
			<cui:input type='hidden' name="epiAppFlow" />	
	 		<table class="table">
				<tr>
					<th>???????????????</th>
					<td><cui:input name="epiPlanName"></cui:input></td>		
					<th>???????????????</th>
					<td><cui:combobox id="comboboxId_yabz_planType" name="epiPlanType" data="combobox_yjct_planType"></cui:combobox></td>	
				</tr>	
				<tr>
					<th>???????????????</th>
					<td colspan="3"><cui:textarea name="epiPlanDesc"></cui:textarea></td>
				</tr>			 
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdatePlanInfo"></cui:button>
		</div>
  	</div>
    <div id="tabsId_yabz_edit_2">
    	<img id="imgId_yabz_left" class="moveLeft" src="${ctx}/resources/common/images/left_move.png" />
		<img id="imgId_yabz_right" class="moveRight" src="${ctx}/resources/common/images/right_move.png" />  
		<div id="divId_yabz_gzzall" class="top"></div>
		<cui:form heightStyle="fill">
			<table>
				<tr>
					<td>
						<div id="divId_yabz_gzzleft"><ul></ul></div>
					</td>
					<td>
						<div id="divId_yabz_gzzright">
							<table id="tableId_yabz_gzzright_desc" class="rightTable">
							</table>
						</div>
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateGzzInfo"></cui:button>
		</div>
    </div>
    <div id="tabsId_yabz_edit_3">
    	<img id="imgId_yabz_left_czlc" class="moveLeft" src="${ctx}/resources/common/images/left_move.png" />
		<img id="imgId_yabz_right_czlc" class="moveRight" src="${ctx}/resources/common/images/right_move.png" />  
		<div id="divId_yabz_czlcall" class="top"></div>
		<cui:form heightStyle="fill">
			<table>
				<tr>
					<td>
						<div id="divId_yabz_czlcleft"><ul></ul></div>
						<div style="text-align: center;">
							<cui:button componentCls="coral-btn-blue" label="????????????" onClick="saveOrUpdateCzffInfo"></cui:button>
						</div>
					</td>
					<td>
						<div id="divId_yabz_czlcright">
							<table style="height: 50px; width: 570px;">
								<cui:input type='hidden' id="inputId_yabz_czlcMethodId" />
								<tr height="25px;">
									<td width="80px" align="center">???????????????</td>
									<td id="tdId_yabz_methodDesc"></td>
								</tr>
								<tr height="25px;">
									<td width="80px" align="center">???????????????</td>
									<td id="tdId_yabz_relAction"></td>
								</tr>	
							</table>	
							<div style="height: 365px; overflow: auto; border-top: 1px solid #4692f0;">
								<!-- ???????????? -->
								<div id="divId_yabz_edit_relAction_1" style="display: none; text-align: center; height: 100%;">
									<cui:form id="formId_yabz_edit_eventRecord" heightStyle="fill">
		 						 		<table class="table">
											<tr>
												<th>??????????????????</th>
												<td><cui:input name="" disabled="true"></cui:input></td>		
												<th>???????????????</th>
												<td><cui:input name="" disabled="true"></cui:input></td>	
											</tr>	
											<tr>
												<th>??????????????????</th>
												<td colspan="3"><cui:input name="" disabled="true"></cui:input></td>
											</tr>	
											<tr>
												<th>?????????????????????</th>
												<td><cui:input name="" disabled="true"></cui:input></td>		
												<th>???????????????</th>
												<td><cui:input name="" disabled="true"></cui:input></td>	
											</tr>
											<tr>
												<th>?????????????????????</th>
												<td colspan="3"><cui:input name="" disabled="true"></cui:input></td>
											</tr>		
											<tr>
												<th>?????????????????????</th>
												<td colspan="3"><cui:input name="" disabled="true"></cui:input></td>
											</tr>
											<tr>
												<th>?????????????????????</th>
												<td colspan="3"><cui:textarea name="" disabled="true"></cui:textarea></td>
											</tr>
											<tr>
												<th>?????????????????????</th>
												<td colspan="3"><cui:textarea name="" disabled="true"></cui:textarea></td>
											</tr>
										</table>
									</cui:form>
									<div style="text-align: center;">
										<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateEventRecord"></cui:button>
									</div>
								</div>
								
								<!-- ????????? -->
								<div id="divId_yabz_edit_relAction_2" style="display: none; text-align: center; height: 100%; border-top: 1px solid #4692f0;">						 
									<table>
										<tr>
										 	<td><input class="unChecked_title" value="?????????????????????"/></td>
										 	<td><input class="unChecked_title" value="?????????????????????"/></td>									 	
										</tr> 
										<tr>
											<td><div id="divId_yabz_edit_workgroup_unChecked" class="unChecked_div" ></div></td>
											<td><div id="divId_yabz_edit_workgroup_checked" class="unChecked_div" ></div></td>
										</tr>
									</table>
									<div style="text-align: center;">
										<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateWorkGroup"></cui:button>
									</div>
								</div>
								
								<!-- ???????????? -->
								<div id="divId_yabz_edit_relAction_3" style="display: none; text-align: center; height: 100%; border-top: 1px solid #4692f0;">						 
									<table>
										<tr>
										 	<td><input class="unChecked_title" value="??????????????????"/></td>
										 	<td><input class="unChecked_title" value="??????????????????"/></td>									 	
										</tr> 
										<tr>
											<td><div id="divId_yabz_edit_expert_unChecked" class="unChecked_div" ></div></td>
											<td><div id="divId_yabz_edit_expert_checked" class="unChecked_div" ></div></td>
										</tr>
									</table>
									<div style="text-align: center;">
										<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateExpert"></cui:button>
									</div>
								</div>
								
								<!-- ???????????? -->
								<div id="divId_yabz_edit_relAction_4" style="display: none; text-align: center; height: 100%; border-top: 1px solid #4692f0;">								
									<cui:form id="formId_yabz_edit_material" heightStyle="fill">
										<div style="background-color: #f2f9f9;"> 
											<table class="table">
												<tr>
													<th>???????????????</th>
													<td><cui:combobox id="comboboxId_yabz_edit_materialName" onChange="updateMaterialTotal"></cui:combobox></td>
													<th>?????????</th>
													<td><cui:input id="comboboxId_yabz_edit_materialTotal" disabled="true"></cui:input></td>
												</tr>
												<tr>
													<th>???????????????</th>
													<td><cui:input id="comboboxId_yabz_edit_materialNum" componentCls="form-control"></cui:input></td>											
													<td colspan="2"><cui:button label="??????" onClick="selectMaterial"></cui:button></td>
												</tr>
											</table>
										</div>
										<table id="tableId_yabz_edit_materialSelect">										 
										</table>
									</cui:form>
									<div style="text-align: center;">
										<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateMaterial"></cui:button>
									</div>		
								</div>
								
								<!-- ???????????? -->
								<div id="divId_yabz_edit_relAction_5" style="display: none; text-align: center; height: 100%;">								
									<cui:form id="formId_yabz_edit_law" heightStyle="fill">
										<div style="background-color: #f2f9f9; border: 1px solid #4692f0;"> 
											<table class="table">
												<tr>
													<th>???????????????</th>
													<td><cui:combobox id="comboboxId_yabz_edit_lawName" onChange="updateLawRemark"></cui:combobox></td>
													<th>?????????</th>
													<td><cui:input id="comboboxId_yabz_edit_lawRemark" disabled="true"></cui:input></td>
													<td><cui:button label="??????" onClick="selectLaw"></cui:button></td>
												</tr>
											</table>
										</div>
										<table id="tableId_yabz_edit_lawSelect">										 
										</table>
									</cui:form>
									<div style="text-align: center;">
										<cui:button componentCls="coral-btn-blue" label="??????" onClick="saveOrUpdateLaw"></cui:button>
									</div>		
								</div>							 
							</div>
						</div>					 
					</td>
				</tr>
			</table>
		</cui:form>
		<div class="dialog-buttons">
			<cui:button componentCls="coral-btn-blue" label="????????????" onClick="updateYafbsp"></cui:button>
		</div>
    </div> 		
</cui:tabs>

<script>
	
	$.parseDone(function() {
		
		updateFlag = false;
		var id = '${id}';
		if(id) {
			loadInfo(id);
			updateFlag = true;
		}	
			
		// ??????????????????
		gzzDatas = [];
		gzzLeftDatas = [];
		startIndex = 0;
		endIndex = 4;
				
		$("#imgId_yabz_left").click(function() {
			moveLeft();
		});		
		$("#imgId_yabz_right").click(function() {
			moveRight();
		});
		
		// ?????????????????????
		czlcDatas = [];
		czlcLeftDatas = [];		
		czlcStartIndex = 0;
		czlcEndIndex = 4;
				
		$("#imgId_yabz_left_czlc").click(function() {
			moveLeftCzlc();
		});
		
		$("#imgId_yabz_right_czlc").click(function() {
			moveRightCzlc();
		});
		
		// ????????????-?????????
		czlcGzzDatas = [];
		czlcGzzCheckDatas = [];
		
		// ????????????-??????
		czlcExpertDatas = [];
		czlcExpertCheckDatas = [];
		
		// ????????????-??????
		pos = 0;
		
		// ????????????-??????
		lpos = 0;
	});
</script>