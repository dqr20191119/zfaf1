<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
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
		margin-left: 10px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
	
	#divId_yabz_gzzright table {
		width: 720px;
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
		height: 350px;
		margin-left: 0px;
		margin-top: 0px;
		border: 1px solid #4692f0;
		padding-right: 20px;
		overflow-y: auto;
		overflow-x: hidden;
	}
	
	#divId_yabz_relAction_gzzright table {
		width: 520px;
		border: 1px solid #ededed; 
		margin-top: 20px; 
		margin-bottom: 20px;
	}
	
	#divId_yabz_relAction_gzzright th {
		background: #4692f0;
		text-align: center;
		border: 1px solid #ededed;
	}
	
	#divId_yabz_relAction_gzzright td {
		border: 1px solid #ededed;
	}
</style>

<cui:tabs id="tabsId_yabz_edit" heightStyle="fill">
	<ul>
       <li><a href="#tabsId_yabz_edit_1">基本信息</a></li>
       <li><a href="#tabsId_yabz_edit_2">工作组</a></li>
       <li><a href="#tabsId_yabz_edit_3">处置流程</a></li>    
   	</ul>
    <div id="tabsId_yabz_edit_1">
    	<cui:form id="formId_yabz_view" heightStyle="fill">
			<cui:input type='hidden' name="id" id="inputId_yabz_planId" />
			<cui:input type='hidden' name="epiCusNumber" />
			<cui:input type='hidden' name="epiPlanStatus" />
			<cui:input type='hidden' name="epiAppFlow" />	
	 		<table class="table">
				<tr>
					<th>预案名称：</th>
					<td><cui:input name="epiPlanName" readonly="true"></cui:input></td>		
					<th>预案类别：</th>
					<td><cui:combobox id="comboboxId_yabz_planType" name="epiPlanType" data="combobox_yjct_planType" readonly="true"></cui:combobox></td>	
				</tr>	
				<tr>
					<th>预案描述：</th>
					<td colspan="3"><cui:textarea name="epiPlanDesc" width="500" readonly="true"></cui:textarea></td>
				</tr>			 
			</table>
		</cui:form>
   	</div>
    <div id="tabsId_yabz_edit_2">
 		<cui:form heightStyle="fill">
			<table class="table">
				<%-- <tr style="height: 30px;">
				 	 <td style="text-align: center;"><div style="margin-left: 20px;">待关联的工作组</div></td>
				 	<td style="text-align: center; width: 200px;"><div><div></td>	
				 	<td>&nbsp;</td>									 	
				</tr> --%>
				<tr>
					<!-- <td>
						<div id="divId_yabz_gzzleft" class="divClass_select"><ul></ul></div>
					</td> -->
					<td style="text-align: center;">
						<p>应急处置工作组</p>
						<div id="divId_yabz_gzzmiddle" class="divClass_select"><ul></ul></div>
					</td>
					<td>
						<div id="divId_yabz_gzzright" style="height: 430px; margin-left: 20px; margin-top: 10px; padding-right: 30px; overflow-y: auto; overflow-x: hidden;">
							<div id="divId_yabz_gzzright_1" style="width: 720px;"></div>
							<div id="divId_yabz_gzzright_2" style="width: 720px;"></div>
							<div id="divId_yabz_gzzright_3" style="width: 720px;"></div>
							<div id="divId_yabz_gzzright_4" style="width: 720px; font-size: 13px;"></div>
						</div>
					</td>
				</tr>
			</table>
		</cui:form>
     </div>
    <div id="tabsId_yabz_edit_3">
		<table class="table">
			<%-- <tr>
			 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的处置方法</div></td> 
			 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的处置方法<div></td>	
			 	<td>&nbsp;</td>									 	
			</tr> --%>
			<tr>
				<%-- <td>
					<div id="divId_yabz_czlcleft" class="divClass_select"><ul></ul></div>						 
				</td> --%>
				<td style="text-align: center;">
					<p>应急处置流程</p>
					<div id="divId_yabz_czlcmiddle" class="divClass_select"><ul></ul></div>						 
				</td>
				<td>
					<table style="height: 50px; width: 720px; margin-left: 20px; margin-top: 20px; border-bottom: 1px solid #4692f0;">
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
					<div id="divId_yabz_czlcright" style="border: 1px solid white; height: 370px; margin-left: 20px; margin-top: 10px; padding-right: 20px; overflow-y: auto; overflow-x: hidden;">
						<!-- 事件记录 -->
						<div id="divId_yabz_edit_relAction_1" style="display: none; height: 370px;">
							<cui:form id="formId_yabz_view_sjjl" heightStyle="fill">	
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
							</cui:form>
						</div>
						
						<!-- 工作组 -->
						<div id="divId_yabz_edit_relAction_2" style="display: none; text-align: center; height: 310px;">						 
							<table class="table">
								<%-- <tr>
									 	 <td style="text-align: center;"><div style="margin-left: 20px;">待关联的工作组</div></td> 
								 	<td style="text-align: center;"><div style="margin-left: 20px;">应急处置工作组<div></td>	
								 	<td>&nbsp;</td>								 	
								</tr> --%>
								<tr>
									<%-- <td><div id="divId_yabz_edit_workgroup_unChecked" class="unChecked_div" ></div></td> --%>
									<td><div id="divId_yabz_edit_workgroup_checked" class="unChecked_div" ><ul></ul></div></td>
									<td>
										<div id="divId_yabz_relAction_gzzright" style="height: 350px; margin-left: 0px; margin-top: 0px; padding-right: 30px; overflow-y: auto; overflow-x: hidden;">
											<div id="divId_yabz_relAction_gzzright_1" style="width: 480px;"></div>
											<div id="divId_yabz_relAction_gzzright_2" style="width: 480px;"></div>
											<div id="divId_yabz_relAction_gzzright_3" style="width: 480px;"></div>
											<div id="divId_yabz_relAction_gzzright_4" style="width: 480px; font-size: 13px;"></div>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<!-- 专家协助 -->
						<div id="divId_yabz_edit_relAction_3" style="display: none; text-align: center; height: 310px;">						 
							<table class="table">
								<%-- <tr>
								 	<td style="text-align: center;"><div style="margin-left: 20px;">待关联的专家</div></td> 
								 	<td style="text-align: center;"><div style="margin-left: 20px;">已关联的专家<div></td>	
								</tr> --%>
								<tr>
									<%-- <td><div id="divId_yabz_edit_expert_unChecked" class="unChecked_div" ><ul></ul></div></td> --%>
									<td><div id="divId_yabz_edit_expert_checked" class="unChecked_div" ><ul></ul></div></td>
									<td>
										<div id="divId_yabz_relAction_zj" style="height: 300px; padding-right: 30px; overflow-y: auto; overflow-x: hidden;">
											 <cui:form id="formId_yabz_relAction_zj_edit">
												<cui:input type='hidden' name="id" />
										 		<table style="text-align: left; border-collapse: separate; border-spacing: 2px 8px;">
													<tr>
														<td nowrap="nowrap">专家名称：</td>
														<td><cui:input id="inputId_epiExpertName" name="epiExpertName" readonly="true"></cui:input></td>
														<td nowrap="nowrap">性别：</td>
														<td><cui:combobox id="inputId_epiSex" name="epiSex" data="combobox_yjct_sex" readonly="true"></cui:combobox></td>				
													</tr>
													<tr>
														<td nowrap="nowrap">所属单位：</td>
														<td><cui:combobox id="inputId_epiCompany" name="epiCompany" url="${ctx}/common/authsystem/findAllJyForCombobox" readonly="true"></cui:combobox></td>
														<td nowrap="nowrap">年龄：</td>
														<td><cui:input id="inputId_epiAge" name="epiAge" readonly="true"></cui:input></td>				
													</tr>
													<tr>
														<td nowrap="nowrap">职位：</td>
														<td><cui:input id="inputId_epiPost" name="epiPost" readonly="true"></cui:input></td>
														<td nowrap="nowrap">职能：</td>
														<td><cui:input id="inputId_epiFunction" name="epiFunction" readonly="true"></cui:input></td>		
													</tr>
													<tr>
														<td nowrap="nowrap">应急特长：</td>
														<td><cui:input id="inputId_epiSpecialty" name="epiSpecialty" readonly="true"></cui:input></td>
														<td nowrap="nowrap">类别：</td>
														<td><cui:input id="inputId_epiType" name="epiType" readonly="true"></cui:input></td>				
													</tr>
													<tr>
														<td nowrap="nowrap">电话：</td>
														<td><cui:input id="inputId_epiPhone" name="epiPhone" readonly="true"></cui:input></td>
														<td nowrap="nowrap">备注：</td>
														<td><cui:input id="inputId_epiRemark" name="epiRemark" readonly="true"></cui:input></td>				
													</tr> 
												</table>
											</cui:form>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<!-- 应急物资 -->
						<div id="divId_yabz_edit_relAction_4" style="display: none; text-align: center; height: 310px;">								
							<%-- <div style="background-color: #f2f9f9; margin-bottom: 10px;"> 
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
										<td colspan="2">&nbsp;</td>
									</tr>
								</table>
							</div> --%>
							<table id="tableId_yabz_edit_materialSelect" style="border-collapse: separate; border-spacing: 2px 2px;">										 
							</table>		
						</div>
						
						<!-- 法律法规 -->
						<div id="divId_yabz_edit_relAction_5" style="display: none; text-align: center; height: 310px;">								
							<%-- <div style="background-color: #f2f9f9; margin-bottom: 10px;"> 
								<table class="table">
									<tr>
										<td>法规名称：</td>
										<td><cui:combobox id="comboboxId_yabz_edit_lawName" onChange="updateLawRemark"></cui:combobox></td>
										<td>备注：</td>
										<td><cui:input id="comboboxId_yabz_edit_lawRemark" disabled="true"></cui:input></td>											
									</tr>
								</table>
							</div> --%>
							<table id="tableId_yabz_edit_lawSelect" style="border-collapse: separate; border-spacing: 2px 2px;">										 
							</table>	
						</div>
					</div>
					<div id="divId_yabz_edit_record" style="display: none; margin-left: 20px; margin-top: 0px;">
						<table style="text-align: center; font-size: 13px; border-collapse: collapse; border:0px solid #4692f0;">
							<tr>
								<td width="80px">过程记录：</td>
								<td> 
									<cui:input id="inputId_yabz_edit_processId" type="hidden" />
									<cui:textarea id="textareaId_yabz_edit_process" name="ehpHandleProcess" width="540" height="50"></cui:textarea>
								</td>
								<td width="140px">
									<cui:button id="tdId_yabz_edit_recordLabel" style="display: none;" componentCls="btn-primary" label="下一步" onClick="saveOrUpdateEventRecord1(0)"></cui:button>
									<cui:button id="tdId_yabz_edit_recordFinishLabel" style="display: none;" componentCls="btn-primary" label="完成" onClick="saveOrUpdateEventRecord1(1)"></cui:button>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
    </div> 		
</cui:tabs>

<script>
	
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;

	var gzzUncheckDatasView = [];
	var gzzCheckDatasView = [];
	
	var czlcUncheckDatasView = [];
	var czlcCheckDatasView = [];
	
	var czlcGzzDatasView = [];
	var czlcGzzCheckDatasView = [];
	
	var czlcExpertDatasView = [];
	var czlcExpertCheckDatasView = [];
	
	var posView = 0;
	var lposView = 0;
	
	var openPos;
	var recordId;
	var isDisplay;
	
	$.parseDone(function() {
		recordId = '${recordId}';
		isDisplay = '${isDisplay}';
 		var id = '${id}';
		if(id) {
			loadForm("formId_yabz_view", "${ctx}/yjct/yabz/getById?id=" + id, function(data) {				
			});
		}
		
		// 初始化工作组
		gzzUncheckDatasView = [];
		gzzCheckDatasView = [];
						 
		// 初始化处置流程
		czlcUncheckDatasView = [];
		czlcCheckDatasView = [];		
 						 
		// 初始化处置流程-工作组
		czlcGzzDatasView = [];
		czlcGzzCheckDatasView = [];
		
		// 初始化处置流程-专家
		czlcExpertDatasView = [];
		czlcExpertCheckDatasView = [];
		
		// 初始化处置流程-物资
		posView = 0;
		
		// 初始化处置流程-法规
		lposView = 0;
			
		$("#tabsId_yabz_edit").tabs({
			onActivate: function(event, ui) {
				var index = $("#tabsId_yabz_edit").tabs("option", "active");
				if(index == 1) {
					
					// 加载工作组
					// loadGzzUncheckInfoView();
					// alert("加载工作组");
					loadGzzCheckInfoView();
				} else if(index == 2) {
					
					// 加载处置方法
					// loadCzlcUncheckInfoView();
					// alert("加载处置方法");
					loadCzlcCheckInfoView();	
				}
			}
		});
				
		var type = '${type}';
		if(type && type == "1") {
			// 从审批过来
			$("#tabsId_yabz_edit").tabs("add", {
				label: "审批意见",
				href: "${ctx}/yjct/yasp/toEdit?id=" + id,
				tabId: "tabsId_yabz_edit_4"
			});
			
			$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_4");
		}
		
		if(type && type == "2") {
			// 从发布过来
			$("#tabsId_yabz_edit").tabs("add", {
				label: "审批意见",
				href: "${ctx}/yjct/yafb/toEdit?id=" + id,
				tabId: "tabsId_yabz_edit_4"
			});
			
			$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_4");
		}
		
		openPos = '${openPos}';
		if(openPos) {
			// 从执行演练和事件处置(报警处置)过来
			$("#inputId_yabz_planId").val(id);			
			$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_" + openPos);
			
			nextPos = 0;
 		}
	});
	
	/* function loadGzzUncheckInfoView() {
		
		var data = {};
		data.wgiStatus = "2";
		data.configExist = "0";
		data.epcPlanFid = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					gzzUncheckDatasView = data.data;
					initGzzUncheckListView();
				} else {
					$.message({message:"获取数据失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	function loadGzzCheckInfoView() {
		
		var data = {};
		data.epcConfigType = "2";
		data.epcPlanFid = $("#inputId_yabz_planId").val();		
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				gzzCheckDatasView = data;
				initGzzCheckListView();
			
				$("#divId_yabz_gzzmiddle ul").find("li").first().click();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadGzzcyInfoView(btn) {
		
 		$("#divId_yabz_gzzright_1").html("");
		$("#divId_yabz_gzzright_2").html("");
		$("#divId_yabz_gzzright_3").html("");
		$("#divId_yabz_gzzright_4").html("");
		
		var gzzId = $(btn).attr("id");
		var gzzTask = $(btn).attr("name");
		var data = {};
		data.wgmWorkgroupFid = gzzId;
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzcy/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
 				initGzzcyInfoView(gzzTask, data);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	/* function initGzzUncheckListView() {
		
		var gzzLeft = $("#divId_yabz_gzzleft ul");		
	    gzzLeft.empty();
	    
	    for(var i = 0; i < gzzUncheckDatasView.length; i++) {
	    	var timers = [];
			var gzzLeftTd = $("<li class='selectHid'>" + gzzUncheckDatasView[i].wgiWorkgroupName + "</li>");
			gzzLeftTd.attr({
				id : gzzUncheckDatasView[i].id,
				name : gzzUncheckDatasView[i].wgiWorkgroupTask,
				value : gzzUncheckDatasView[i].wgiWorkgroupName,
 				'sort-num': i,
			}).unbind('click').bind('click', function() {
				
				loadGzzcyInfoView(this);
				$("#divId_yabz_gzzleft ul").find("li").css("color", "black");
				$("#divId_yabz_gzzmiddle ul").find("li").css("color", "black");
				$(this).css("color", "red");
 			});
			
			gzzLeftTd.appendTo(gzzLeft);
		}	
	} */
	
	function initGzzCheckListView() {
		
	    var gzzMiddle = $("#divId_yabz_gzzmiddle ul");		
	    gzzMiddle.empty();
	    
	    for(var i = 0; i < gzzCheckDatasView.length; i++) {
	    	var timers = [];
			var gzzMiddleTd = $("<li class='selectHid'>" + gzzCheckDatasView[i].wgiWorkgroupName + "</li>");
			gzzMiddleTd.attr({
				id : gzzCheckDatasView[i].id,
				name : gzzCheckDatasView[i].wgiWorkgroupTask,
				value : gzzCheckDatasView[i].wgiWorkgroupName,
				'sort-num': i,
			}).unbind('click').bind('click', function() {
				
				loadGzzcyInfoView(this);
				$("#divId_yabz_gzzleft ul").find("li").css("color", "black");
				$("#divId_yabz_gzzmiddle ul").find("li").css("color", "black");
				$(this).css("color", "red");
			});
			
			gzzMiddleTd.appendTo(gzzMiddle);
		}	
 	}
	
	function initGzzcyInfoView(gzzTask, gzzcyDatas) {
		
		var headerTr = "<table><tr>" + 
		   		"<th width='100' nowrap='nowrap'>姓名</th>" +
			   	"<th width='100' nowrap='nowrap'>通联号码</th>" + 
			   	"<th width='520' nowrap='nowrap'>任务分工</th>" + 
			   	"</tr>";
  
	   	var zzTr = "";
	   	var fzzTr = "";
	   	var cyTr = "";
	   	for(var i = 0; i < gzzcyDatas.length; i++) {
	   		var gzzcy = gzzcyDatas[i];
	   		var role = gzzcy.wgmUserRole;

	   		if(gzzcy.pbdPhone == null) {
	   			gzzcy.pbdPhone = "";
	   		}
	   		
	   		if(role == "1") {
	   			
	   			zzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "2") {
	   			
	   			fzzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "3") {
	   			
	   			cyTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		}
	   	}
	   	
	   	$("#divId_yabz_gzzright_1").append('<div style="border-bottom: 1px solid #4692f0; width: 720px;">组长</div>' + headerTr + zzTr + '</table>');
	   	$("#divId_yabz_gzzright_2").append('<div style="border-bottom: 1px solid #4692f0; width: 720px;">副组长</div>' + headerTr + fzzTr + '</table>');
	   	$("#divId_yabz_gzzright_3").append('<div style="border-bottom: 1px solid #4692f0; width: 720px;">成员</div>' + headerTr + cyTr + '</table>');
		$("#divId_yabz_gzzright_4").append("<b>组任务：</b>" + gzzTask);
	}
	
	/* function loadCzlcUncheckInfoView() {
		
		var data = {};
		data.dmiStatus = "2";
		data.configExist = "0";
		data.epcPlanFid = $("#inputId_yabz_planId").val();
		data.dmiPlanType = $("#comboboxId_yabz_planType").combobox("getValue");
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					czlcUncheckDatasView = data.data;
					initCzlcUncheckListView();
				} else {
					$.message({message:"获取数据失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	function loadCzlcCheckInfoView() {
		
		var data = {};
		data.epcConfigType = "1";
		data.epcPlanFid = $("#inputId_yabz_planId").val();	
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				czlcCheckDatasView = data;
				console.log("czlcCheckDatasView = " + JSON.stringify(czlcCheckDatasView));
				initCzlcCheckListView();
				
				$("#divId_yabz_czlcmiddle ul").find("li").first().click();
				
				if(openPos && openPos != "") {						
					$("#divId_yabz_edit_record").show();	
				}									 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadCzlcInfoDetailView(btn) {
		
		var id = $(btn).attr("id");
		var desc = $(btn).attr("name");
		$("#tdId_yabz_methodDesc").html(desc);
	 	$("#inputId_yabz_czlcMethodId").val(id);
	 	$("#tdId_yabz_relAction").html("");
	 	
	 	if(isDisplay && isDisplay == "0") {
			$("#tdId_yabz_edit_recordLabel").hide();
			$("#tdId_yabz_edit_recordFinishLabel").hide();
		} else {
			if(id == czlcCheckDatasView[czlcCheckDatasView.length - 1].id) {
				$("#tdId_yabz_edit_recordLabel").hide();
				$("#tdId_yabz_edit_recordFinishLabel").show();
			} else {
				$("#tdId_yabz_edit_recordLabel").show();
				$("#tdId_yabz_edit_recordFinishLabel").hide();
			}
		}
	 	
	 	$.loading({text:"正在读取中，请稍后..."});
	 	
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffczx/searchAllData',
			data: {
				mraMethodFid : id
			},
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var actionData = data;
				var html = "";
				for(var i = 0; i < actionData.length; i++) {						
					for(var j = 0; j < combobox_yjct_actionType.length; j++) {
						if(actionData[i].mraRelActionType == combobox_yjct_actionType[j].value) {
							html += '<div style="float:left"><a href="javascript:void(0);" class="disDescBtn" onclick="showActionDetailView(' + actionData[i].mraRelActionType + ')">' + combobox_yjct_actionType[j].text + '</a></div>';															 				
							break;
						}
					}						
				}
				
				$("#tdId_yabz_relAction").html(html);
				showActionDetailView(actionData[0].mraRelActionType);
				
				// 加载过程信息
				loadCzlcRecordProcess();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadCzlcRecordProcess() {
		
		$("#inputId_yabz_edit_processId").val("");
		$("#textareaId_yabz_edit_process").textbox("setValue", "");
		
		var data = {};
		data.ehpHandleFid = recordId;
		data.ehpMethodFid = $("#inputId_yabz_czlcMethodId").val();

		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yjjlgc/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
 					
				if(data && data.length > 0) {
					$("#inputId_yabz_edit_processId").val(data[0].id);
					$("#textareaId_yabz_edit_process").textbox("setValue", data[0].ehpHandleProcess);
				}				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	/* function initCzlcUncheckListView() {
		
		var czlcLeft = $("#divId_yabz_czlcleft ul");		
		czlcLeft.empty();
	    
	    for(var i = 0; i < czlcUncheckDatasView.length; i++) {
	    	var timers = [];
			var czlcLeftTd = $("<li class='selectHid'>" + czlcUncheckDatasView[i].dmiMethodName + "</li>");
			czlcLeftTd.attr({
				id : czlcUncheckDatasView[i].id,
				name : czlcUncheckDatasView[i].dmiMethodDesc,
				value : czlcUncheckDatasView[i].dmiMethodName,
				'planType' : czlcUncheckDatasView[i].dmiPlanType,
 				'sort-num': i,
			}).unbind('click').bind('click', function() {
				
 				loadCzlcInfoDetailView(this);
				$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
				$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
				$(this).css("color", "red");
 			});
			
			czlcLeftTd.appendTo(czlcLeft);
		}
	} */
	
	function initCzlcCheckListView() {
		
		var czlcMiddle = $("#divId_yabz_czlcmiddle ul");		
		czlcMiddle.empty();
	    
	    for(var i = 0; i < czlcCheckDatasView.length; i++) {
	    	var timers = [];
			var czlcMiddleTd = $("<li class='hid'>" + czlcCheckDatasView[i].dmiMethodName + "</li>");
			czlcMiddleTd.attr({
				id : czlcCheckDatasView[i].id,
				name : czlcCheckDatasView[i].dmiMethodDesc,
				value : czlcCheckDatasView[i].dmiMethodName,
				'planType' : czlcCheckDatasView[i].dmiPlanType,
 				'sort-num': i,
			}).unbind('click').bind('click', function() {
				
 				loadCzlcInfoDetailView(this);
				$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
				$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
				$(this).css("color", "white");
				
				if(openPos) {
					nextPos = $(this).attr("sort-num");
		 		}
 			});
			
			czlcMiddleTd.appendTo(czlcMiddle);
		}
 	}
	
	function showActionDetailView(relActionType) {
		
		for(var i = 1; i <= 5; i++) {
			if(relActionType == i+"") {
				$("#divId_yabz_edit_relAction_" + i).show();
			} else {
				$("#divId_yabz_edit_relAction_" + i).hide();
			}			
		}
		
		if(relActionType == "2") {	
			// 加载工作组
			// loadCzlcGzzUncheckInfoView();
			loadCzlcGzzCheckInfoView();	
		} else if(relActionType == "3") {
			// 加载专家
			// loadCzlcExpertUncheckInfoView();
			loadCzlcExpertCheckInfoView();
		} else if(relActionType == "4") {
			// 加载物资
			// loadCzlcMaterialInfoView();
			loadCzlcMaterialCheckInfoView();
		} else if(relActionType == "5") {
			// 加载法规
			// loadCzlcLawInfoView();
			loadCzlcLawCheckInfoView();
		} else if(relActionType == "1") {
			// 事件记录
			if(openPos) {
				loadEventRecord();
			}
		}
	}
		
	/* function loadCzlcGzzUncheckInfoView() {
		
		$("#divId_yabz_edit_workgroup_unChecked").html("");
		
		var data = {};
		data.epcPlanFid = $("#inputId_yabz_planId").val();
 		data.epcConfigType = "2";
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();		
		data.epaRelActionType = "2";
		data.actionExist = "0";
				
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
 					
				czlcGzzDatasView = data;
				initCzlcGzzUncheckInfoView();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	
	
	function loadCzlcGzzCheckInfoView() {
		// alert("loadCzlcGzzCheckInfoView");
		$("#divId_yabz_edit_workgroup_checked ul").html("");
		
		var data = {};
		data.epcPlanFid = $("#inputId_yabz_planId").val();
 		data.epcConfigType = "2";
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();		
		data.epaRelActionType = "2";
		data.actionExist = "1";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
 					
				czlcGzzCheckDatasView = data;
				initCzlcGzzCheckInfoView();
				
				$("#divId_yabz_edit_workgroup_checked ul").find("li").first().click();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadRelActionGzzcyInfoView(btn) {
		
 		$("#divId_yabz_relAction_gzzright_1").html("");
		$("#divId_yabz_relAction_gzzright_2").html("");
		$("#divId_yabz_relAction_gzzright_3").html("");
		$("#divId_yabz_relAction_gzzright_4").html("");
		
		var gzzId = $(btn).attr("id");
		var gzzTask = $(btn).attr("name");
		var data = {};
		data.wgmWorkgroupFid = gzzId;
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzcy/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
 				initRelActionGzzcyInfoView(gzzId, gzzTask, data);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	/* function initCzlcGzzUncheckInfoView() {
		
		var workgroupUnChecked = $("#divId_yabz_edit_workgroup_unChecked");
		workgroupUnChecked.empty();
		
		for(var i = 0; i < czlcGzzDatasView.length; i++) {
			
		    unCheckTd = $("<li class='selectHid'>" + czlcGzzDatasView[i].wgiWorkgroupName + "</li>");
		    unCheckTd.attr({
				id: czlcGzzDatasView[i].id,
				value: czlcGzzDatasView[i].wgiWorkgroupName,
				'sort-num' : i
			});
		    
		    unCheckTd.appendTo(workgroupUnChecked);
		}
	} */
	
	function initCzlcGzzCheckInfoView() {

		// alert("initCzlcGzzCheckInfoView");
		var workgroupChecked = $("#divId_yabz_edit_workgroup_checked ul");
		workgroupChecked.empty();
		// alert("czlcGzzCheckDatasView.length = " + czlcGzzCheckDatasView.length);
		for(var i = 0; i < czlcGzzCheckDatasView.length; i++) {
			
 		    var checkTd = $("<li class='selectHid'>" + czlcGzzCheckDatasView[i].wgiWorkgroupName + "</li>");
		    checkTd.attr({
				id: czlcGzzCheckDatasView[i].id,
				name : czlcGzzCheckDatasView[i].wgiWorkgroupTask,
				value: czlcGzzCheckDatasView[i].wgiWorkgroupName,
				'sort-num' : i
			}).unbind('click').bind('click', function() {
				
				loadRelActionGzzcyInfoView(this);
				$("#divId_yabz_edit_workgroup_checked ul").find("li").css("color", "black");
				$(this).css("color", "red");
			});
		    
		    checkTd.appendTo(workgroupChecked);
		}
	}
	/**
	 * gzzId：工作组ID
	 * gzzTask： 工作组任务
	 * gzzcyDatas：工作组成员数据
	 */
	function initRelActionGzzcyInfoView(gzzId, gzzTask, gzzcyDatas) {
		
		var headerTr = "<table><tr>" + 
		   		"<th width='100' nowrap='nowrap'>姓名</th>" +
			   	"<th width='100' nowrap='nowrap'>通联号码</th>" + 
			   	"<th nowrap='nowrap'>任务分工</th>" + 
			   	"</tr>";
  
	   	var zzTr = "";
	   	var fzzTr = "";
	   	var cyTr = "";
	   	for(var i = 0; i < gzzcyDatas.length; i++) {
	   		var gzzcy = gzzcyDatas[i];
	   		var role = gzzcy.wgmUserRole;

	   		if(gzzcy.pbdPhone == null) {
	   			gzzcy.pbdPhone = "";
	   		}
	   		
	   		if(role == "1") {
	   			
	   			zzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "2") {
	   			
	   			fzzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "3") {
	   			
	   			cyTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		}
	   	}
	   	
	   	$("#divId_yabz_relAction_gzzright_1").append('<div style="border-bottom: 1px solid #4692f0; width: 520px; text-align: left;">组长</div>' + headerTr + zzTr + '</table>');
	   	$("#divId_yabz_relAction_gzzright_2").append('<div style="border-bottom: 1px solid #4692f0; width: 520px; text-align: left;">副组长</div>' + headerTr + fzzTr + '</table>');
	   	$("#divId_yabz_relAction_gzzright_3").append('<div style="border-bottom: 1px solid #4692f0; width: 520px; text-align: left;">成员</div>' + headerTr + cyTr + '</table>');
		$("#divId_yabz_relAction_gzzright_4").append('<div style="width: 520px; text-align: left;"><b>组任务：</b>' + gzzTask + '</div>');
		
		/** add by lincoln.cheng 20190217 通信融合应急预案呼叫工作组start */
		// 开启应急预案前处理操作
		beforeStartYjya(recordId, gzzId);
		/** add by lincoln.cheng 20190217 通信融合应急预案呼叫工作组end */
	}
	
	/* function loadCzlcExpertUncheckInfoView(id) {
		
		$("#divId_yabz_edit_expert_unChecked").html("");
		
		var data = {};
		data.epiSttsIndc = "2";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				czlcExpertDatasView = data;
				initCzlcExpertUncheckInfoView();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	function loadCzlcExpertCheckInfoView(id) {
		
		$("#divId_yabz_edit_expert_checked ul").html("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "3";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				czlcExpertCheckDatasView = data;
				initCzlcExpertCheckInfoView();
					
				$("#divId_yabz_edit_expert_checked ul").find("li").first().click();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadRelActionZjInfoView(btn) {
		
		$("#formId_yabz_relAction_zj_edit").form("reset");
		
		var zjId = $(btn).attr("id");
		var data = {};
		data.id = zjId;
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjgl/getById',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var zjData = data;
				$("#inputId_epiExpertName").textbox("setText", zjData.epiExpertName);
				$("#inputId_epiSex").combobox("setValue", zjData.epiSex);
				$("#inputId_epiCompany").combobox("setValue", zjData.epiCompany);
				$("#inputId_epiAge").textbox("setText", zjData.epiAge);
				$("#inputId_epiPost").textbox("setText", zjData.epiPost);
				$("#inputId_epiFunction").textbox("setText", zjData.epiFunction);
				$("#inputId_epiSpecialty").textbox("setText", zjData.epiSpecialty);
				$("#inputId_epiType").textbox("setText", zjData.epiType);
				$("#inputId_epiPhone").textbox("setText", zjData.epiPhone);
				$("#inputId_epiRemark").textbox("setText", zjData.epiRemark);								 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	/* function initCzlcExpertUncheckInfoView() {
		
		var expertUnChecked = $("#divId_yabz_edit_expert_unChecked");
		expertUnChecked.empty();
		
		for(var i = 0; i < czlcExpertDatasView.length; i++) {
			
		    unCheckTd = $("<li class='selectHid'>" + czlcExpertDatasView[i].epiExpertName + "</li>");
		    unCheckTd.attr({
				id: czlcExpertDatasView[i].id,
				value: czlcExpertDatasView[i].epiExpertName,
				'sort-num' : i
			});
		    
		    unCheckTd.appendTo(expertUnChecked);
		}
	} */
		
	function initCzlcExpertCheckInfoView() {
		
		var expertChecked = $("#divId_yabz_edit_expert_checked ul");
		expertChecked.empty();
		
		for(var i = 0; i < czlcExpertCheckDatasView.length; i++) {
			
		    checkTd = $("<li class='selectHid'>" + czlcExpertCheckDatasView[i].epiExpertName + "</li>");
		    checkTd.attr({
				id: czlcExpertCheckDatasView[i].id,
				value: czlcExpertCheckDatasView[i].epiExpertName,
				'sort-num' : i
			}).unbind('click').bind('click', function() {
				
				loadRelActionZjInfoView(this);
				$("#divId_yabz_edit_expert_checked ul").find("li").css("color", "black");
				$(this).css("color", "red");
			});
		    
		    checkTd.appendTo(expertChecked);
		}
	}
	
	/* function loadCzlcMaterialInfoView() {
		
		var data = {};
		data.mriStatus = "2";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/wzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				var materialDatas = data.data;
				var dataArr = [];
				for(var i = 0; i < materialDatas.length; i++) {
					var data = {};
					data.value = materialDatas[i].mriMaterialId + "_" + materialDatas[i].mriCount;
					data.text = materialDatas[i].mriMaterialName;
					dataArr[i] = data;
				}
				
				$("#comboboxId_yabz_edit_materialName").combobox("reload", dataArr);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	function loadCzlcMaterialCheckInfoView() {
		
		$("#tableId_yabz_edit_materialSelect").html("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "4";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) { 					
				$.loading("hide");
				
				var materialDatas = data;
				var dataArr = [];
				for(var i = 0; i < materialDatas.length; i++) {
					var data = {};						 
					data.id = materialDatas[i].id;
					data.name = materialDatas[i].mriMaterialName;
					data.total = materialDatas[i].mriCount;
					data.count =  materialDatas[i].epaRemark;						
					dataArr[i] = data;
				}
				
				initMaterialInfoView(dataArr);				 		
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function initMaterialInfoView(data) {
		
		for(var i = 0; i < data.length; i++) {
			var trId = "trId_yabz_edit_material_info_" + posView;
			var html = "<tr id='" + trId + "'>" +
				"<td width='80'>物资名称：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_material_name_" + posView + "' value='" + data[i].name + "' style='width: 150px; height: 22px;' />" +
				"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_material_id_" + posView + "' value='" + data[i].id + "' />" +	
	 			"</td>" + 
				"<td width='80'>总数：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_material_total_" + posView + "' value='" + data[i].total + "' style='width: 100px; height: 22px;' /></td>" +
				"<td width='80'>所需数量：</td>" +
				"<td><input id='inputId_yabz_edit_material_count_" + posView + "' value='" + data[i].count + "' style='width: 100px; height: 22px;' /></td>" +			
				"</tr>";
				
			$("#tableId_yabz_edit_materialSelect").append(html);			
			posView++;
		} 		
	}
	
	/* function loadCzlcLawInfoView() {
		
		var data = {};
		data.lpiSttsIndc = "2";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/fggl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				var lawDatas = data.data;
				var dataArr = [];
				for(var i = 0; i < lawDatas.length; i++) {
					var data = {};
					data.value = lawDatas[i].lriLawsId + "_" + lawDatas[i].lriLawsContent;
					data.text = lawDatas[i].lriLawsName;
					dataArr[i] = data;
				}
				
				$("#comboboxId_yabz_edit_lawName").combobox("reload", dataArr);			 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	} */
	
	function loadCzlcLawCheckInfoView() {
		
		$("#tableId_yabz_edit_lawSelect").html("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "5";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
 					
				var lawDatas = data;
				var dataArr = [];
				for(var i = 0; i < lawDatas.length; i++) {
					var data = {};						 
					data.id = lawDatas[i].id;
					data.name = lawDatas[i].lriLawsName;
					data.remark =  lawDatas[i].lriLawsContent;	
					dataArr[i] = data;
				}
				
				initLawInfoView(dataArr);			 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function initLawInfoView(data) {
		
		for(var i = 0; i < data.length; i++) {
			var trId = "trId_yabz_edit_law_info_" + lposView;
			var html = "<tr id='" + trId + "'>" +
				"<td width='80'>法规名称：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_law_name_" + lposView + "' value='" + data[i].name + "' style='width: 180px; height: 22px;' />" +
				"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_law_id_" + lposView + "' value='" + data[i].id + "' />" +	
	 			"</td>" + 
				"<td width='80'>备注：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_law_remark_" + lposView + "' value='" + data[i].remark + "' style='width: 350px; height: 22px;' /></td>" +			 
				"</tr>";
			
			$("#tableId_yabz_edit_lawSelect").append(html);
			lposView++;
		}		
	}

	/**
	 * 开启应急预案前的验证与处理操作
	 */
	function beforeStartYjya(yjjlId, gzzId) {
		var urlPath = '${ctx}/yjct/yjjl/queryYjjlStatusAndYjyaNameAndGzzName';
		$.ajax({
			type : 'post',
			url : urlPath,
			data : {
				"yjjlId":yjjlId,// 应急记录ID
				"gzzId":gzzId// 工作组ID
			},
			dataType : 'json',
			success : function(data) {
	            if(data.code == 200){
	                // alert("success");
	                var yjjlMap = data.data;
	                console.log("yjjlMap = " + JSON.stringify(yjjlMap));

	                if(yjjlMap != null) {
		                startYjya(yjjlMap.cusNumber, yjjlId, yjjlMap.yjjlStatus, yjjlMap.yjyaName, yjjlMap.gzzName);
	                }
	            }else if(data.code == 500){
	                // alert("error");
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}

	/**
	 * 呼叫预案工作组
	 */
	function startYjya(cusNumber, yjjlId, yjjlStatus, yjyaName, gzzName) {
		if(!cusNumber) {
			console.log("关联预案的监狱编号为空");
			return false;
		}
		if(!yjjlId) {
			console.log("关联报警记录编号为空");
			return false;
		}
		if(yjjlStatus != "1") {
			return false;
		}
		if(!yjyaName) {
			return false;
		}
		if(!gzzName) {
			return false;
		}
		var urlPath = '${ctx}/rcs/startYjya';
		$.ajax({
			type : 'post',
			url : urlPath,
			data : {
				cusNumber:cusNumber,
				cmd:"1",
				terFlag:yjjlId,
				plan:yjyaName,
				echelon:gzzName,
				supplement:"",
				voiceTxt:"",
				name:"",
				date:"",
				time:"",
				area:"",
				location:"",
				sta:""
			},
			dataType : 'json',
			success : function(data) {
	            if(data.code == 200){
	                // alert("success");
	                console.log(yjyaName + "呼叫" + gzzName + "成功！");
	            }else if(data.code == 500){
	                // alert("error");
	                console.log(yjyaName + "呼叫" + gzzName + "失败！");
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
	
	// 关联操作项-保存事件记录					start
	function saveOrUpdateEventRecord1(type) {
		
		if($("#divId_yabz_edit_relAction_1").is(":hidden") == false) {
			var edit_sjlj1 = $("#edit_sjlj1").val();
			var edit_sjlj2 = $("#edit_sjlj2").val();
			var edit_sjlj3 = $("#edit_sjlj3").val();
			var edit_sjlj4 = $("#edit_sjlj4").val();
			var edit_sjlj5 = $("#edit_sjlj5").val();
			var edit_sjlj6 = $("#edit_sjlj6").val();
			var v_str = edit_sjlj1 + "~" + edit_sjlj2 +  "~" + edit_sjlj3 +  "~" + edit_sjlj4 +  "~" + edit_sjlj5 +  "~" + edit_sjlj6;
			
			var data = {};
			data.id = $("#inputId_yabz_planId").val();
			data["yaczList[0].epaPlanFid"] = recordId;
			data["yaczList[0].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
			data["yaczList[0].epaRelActionType"] = 1;
			data["yaczList[0].epaRelActionFid"] = $("#inputId_yabz_planId").val() ;
			data["yaczList[0].epaRemark"] = v_str;
			
 			$.ajax({
				type : 'post',
				url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction1',
				data: data,
				dataType : 'json',
				success : function(data) {
  				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"});
				}
			});
		}
		
		if(type == 0 ) {
			saveRecordProcess(0);
		} else {
			saveRecordProcess(1);
		}
		
	}
	/*
	* 查询事件记录
	*/
	function loadEventRecord() {
		
		//loadForm("formId_yabz_view_sjjl", "${ctx}/yjct/yjjl/findEventRecord?id=" + recordId, function(data) {});
		
		//事件记录
		$("#divId_yabz_edit_relAction_1 input").val("");
		$("#divId_yabz_edit_relAction_1 textarea").val("");
		var data = {};
		
		if(recordId != null && recordId != null) {
			data.epaPlanFid = recordId;
		} else{
			data.epaPlanFid = $("#inputId_yabz_planId").val();
		}
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "1";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.length > 0 ) {
					var v_str = data[0].epaRemark;
					var arry = v_str.split("~");
					for(var i = 0; i < arry.length; i++) {
						$("#edit_sjlj"+(i+1)).val(arry[i]);
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	
</script>