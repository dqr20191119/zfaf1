<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
UserBean user = AuthSystemFacade.getLoginUserInfo();
request.setAttribute("cusNumber", user.getCusNumber());
%>
<div style="height: 100%; margin: 0px 10px;">
	 <cui:form id="formId_hbsq_query">
	 <cui:input type='hidden' id ="cusNumber" name="cusNumber" />
		<table class="table">
			<tr><%-- <th>单位 ：</th>
				<td><cui:combobox  id="cusNumber"  name="cusNumber"  data="combox_dw"    componentCls="form-control"  ></cui:combobox></td> --%>
                <th>申请人 ：</th>
                <td><cui:input id="sqrName" name="sqrName" ></cui:input></td>
                <th>申请时间 ：</th>
                <td><cui:datepicker componentCls="form-control"  id="startDate" name="startDate" timeFormat="yyyy-MM-dd"></cui:datepicker></td>
                <th>至</th>
                <td><cui:datepicker componentCls="form-control"  id="endDate" name="endDate"  timeFormat="yyyy-MM-dd"></cui:datepicker></td>
			</tr>
            <tr>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
            </tr>
		</table>
	</cui:form> 
	
	 <div style="height: 40px;">
		<cui:toolbar id="toolbarId_hbsq" data="toolbar_hbsq"></cui:toolbar>
	</div> 
			
	 <cui:grid id="gridId_hbsq" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
		 >
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true" ></cui:gridCol>
			<cui:gridCol name="cusNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_dw
				}"  align="center" >单位</cui:gridCol>
			<%-- <cui:gridCol name="deptNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_bm
				}" align="center">部门</cui:gridCol> --%>
            <cui:gridCol name="zbOrder" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_order
				}" >值班班次</cui:gridCol>
            <cui:gridCol name="dutyDate" align="center">值班时间</cui:gridCol>
            <cui:gridCol name="tdr" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_ry_grid
				}" >替代人</cui:gridCol>
			<cui:gridCol name="hbr" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_ry_grid
				}"  >换班人</cui:gridCol>
			<cui:gridCol name="sqrName" align="center">申请人</cui:gridCol>
			<cui:gridCol name="sqDate" align="center" >申请时间</cui:gridCol>
			<cui:gridCol name="zt" align="center"  formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_tjzt
				}" >提交状态</cui:gridCol>
			<cui:gridCol name="spyj" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_sqyj
				}" >审批意见</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_hbsq"/>
	</cui:grid> 
		<cui:dialog id="dialogId_hbsq" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
		</cui:dialog>
</div>

 <script>
    var cusNumber = jsConst.CUS_NUMBER;
    var USER_LEVEL = jsConst.USER_LEVEL;
    var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	 $.parseDone(function() {
	   
        $("#gridId_hbsq").grid("reload","${ctx}/zbgl/hbsq/searchData?cusNumber=" + cusNumber);
        $("#cusNumber").textbox("setValue",cusNumber);
	}); 
	 
	var toolbar_hbsq = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "申请",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, 
	{
        "type" : "button",
        "id" : "btnId_tj",
        "label" : "提交",
        "onClick" : "updateTj",
        "componentCls" : "btn-primary"
    },{
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteById",
		"componentCls" : "btn-primary"
	},{
        "type" : "button",
        "id" : "btnId_view",
        "label" : "查看",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    }];

	var combobox_tjzt = [
		{value:"0",text:"未提交"},
		{value:"1",text:"已提交"}];


	var combox_dw =<%= AuthSystemFacade.getHnAllJyJsonInfo()%>;
    var combox_ry_grid ;
    var combox_ry ;
	if(USER_LEVEL==1){
         combox_ry_grid =<%= RyCommon.getCommonbox2(request.getAttribute("cusNumber").toString())%>;
         combox_ry = <%= RyCommon.getCommonbox(request.getAttribute("cusNumber").toString())%>;
    }else{
         combox_ry_grid =<%= RyCommon.getCommonbox5(request.getAttribute("cusNumber").toString())%>;
         combox_ry = <%= RyCommon.getCommonbox4(request.getAttribute("cusNumber").toString())%>;
    }
    var combox_order =<%= RyCommon.getBcglCommonbox(request.getAttribute("cusNumber").toString())%>;
	
	var combobox_sfty = [
		{value:"1",text:"同意"},
		{value:"2",text:"不同意"}];

	var combobox_sqyj=[
	    {value:"0",text:"未审批"},
		{value:"1",text:"同意"},
		{value:"2",text:"不同意"}
		];
	
	//直接提交
    function updateTj(event, ui) {
		//alert(ui.id)
	 if(ui.id == "btnId_tj"){
		//
			var selarrrow = $("#gridId_hbsq").grid("option", "selarrrow");
			if(selarrrow && selarrrow.length == 1) {
				$.confirm("是否提交,提交后不能修改!", function(r) {
					var rowData  =$("#gridId_hbsq").grid("getRowData", selarrrow[0]);
					  if(rowData.zt=='1'){//提交状态不能修改
					 $.message({message:"已经是提交状态!", cls:"waring"});

				 }else{
					 var formData ={};
						formData["id"] = rowData.id;
						formData["zt"] = "1";
					 $.ajax({
							type : 'post',
							url : '${ctx}/zbgl/hbsq/updateZtOrSpzt',
							data: formData,
							dataType : 'json',
							success : function(data) {
								if(data.code == "200") {
									$.message({message:"操作成功！", cls:"success"}); 
									$("#gridId_hbsq").grid("reload");
								} else {
									$.message({message:"操作失败！", cls:"error"}); 
								}				
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								$.message({message:"操作失败！", cls:"error"}); 
							}
						});
				 } 
				});
				
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});

			}		
		//
		
	} 
} 
	
	

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/hbsq/toEdit";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_hbsq").grid("option", "selarrrow");
			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/hbsq/toEdit?id=" + selarrrow[0];
				var rowData  =$("#gridId_hbsq").grid("getRowData", selarrrow[0]);
				 if(rowData.zt=='1'){//提交状态不能修改
					 $.message({message:"已经提交不能修改!", cls:"waring"});
						return; 
				 }
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}else if(ui.id == "btnId_view") {

            var selarrrow = $("#gridId_hbsq").grid("option", "selarrrow");
            if(selarrrow && selarrrow.length == 1) {
                url = "${ctx}/zbgl/hbsq/view?id=" + selarrrow[0];
            } else {
                $.message({message:"请选择一条记录！", cls:"waring"});
                return;
            }
        }

		$("#dialogId_hbsq").dialog({
			width : 900,
			height : 700,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_hbsq").dialog("open");
	}
	
	function search() {
		var formData = $("#formId_hbsq_query").form("formData");
		$("#gridId_hbsq").grid("option", "postData", formData);
		$("#gridId_hbsq").grid("reload","${ctx}/zbgl/hbsq/searchData");
	}
	
	function reset() {
		
		$("#formId_hbsq_query").form("reset");
	}
	
	function saveOrUpdate(type) {
		
		var validFlag = $("#formId_hbsq_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_hbsq_edit").form("formData");
		//检验换班人 和替代人当天是否有值班
		var zbOrder = formData.zbOrder;
		var hbr  = formData.hbr;
		var dutyDate = formData.dutyDate;
		
		var tdr = formData.tdr;
		var tdrzbOrder  = formData.tdrzbOrder;
		var hbDate = formData.hbDate;
			$.ajax({
					type : 'post',
					url : '${ctx}/zbgl/hbsq/checkZbrIsZbbp',
					data: {
						zbrId:hbr,
						dutyDate:dutyDate,
						orderId:zbOrder,
						cusNumber:cusNumber
					},
					dataType : 'json',
					success : function(data) {
						if(data.code == "200") {
							if(data.message=="true"){
								$.ajax({
									type : 'post',
									url : '${ctx}/zbgl/hbsq/checkZbrIsZbbp',
									data: {
										zbrId:tdr,
										dutyDate:hbDate,
										orderId:tdrzbOrder,
										cusNumber:cusNumber
									},
									dataType : 'json',
									success : function(data) {
										if(data.code == "200") {
											if(data.message=="true"){
												if(type=='1'){//保存
													formData.zt = "0";
												}else{
													formData.zt = "1";//提交
												}
												//console.log($("#uploaderId_attachment").val());
												//alert($("#uploaderId_attachment_affixIds").val());
												formData.files = $("#uploaderId_attachment_affixIds").val();
												formData.spyj = "0";//未审批
												formData.cusNumber =cusNumber;
												var confirmMsg = "";
												
												if(type=='1'){
													confirmMsg ="是否保存";
												}else{
													confirmMsg ="是否提交,提交后将不能进行修改";
												}
												$.confirm(confirmMsg, function(r) {
													if(r){
														$.ajax({
															type : 'post',
															url : '${ctx}/zbgl/hbsq/saveData',
															data: formData,
															dataType : 'json',
															success : function(data) {
																$.loading("hide");
																
																if(data.code == "200") {
																	$.message({message:"操作成功！", cls:"success"}); 
																	$("#dialogId_hbsq").dialog("close");
																	$("#gridId_hbsq").grid("reload");
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
												})
											}else{
												$.message({message:"该替代人在当天没有值班！", cls:"error"}); 
											}
										} else {
											$.message({message:"操作失败!", cls:"error"}); 
										}				
									}
								});
							}else{
								$.message({message:"该换班人在当天没有值班!", cls:"error"}); 
							}
							
						}else {
							$.message({message:"操作失败!", cls:"error"}); 
						}				
					}
				});
			
			
		
		
		
	}
	
	function deleteById() {
		
		var selarrrow = $("#gridId_hbsq").grid("option", "selarrrow");
		if(selarrrow && selarrrow.length == 1) {
			
			$.confirm("确认删除？", function(r) {
				if(r) {
					var id =selarrrow[0];
					$.loading({text:"正在处理中，请稍后..."});
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/hbsq/deleteById',
						data: {
							id : id
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "200") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_hbsq").grid("reload");
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
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});

		}
	}	
	
	//文件删除成功时触发
	function f_onClearQueue(e,ui){
		console.log("文件删除成功");
		$("#uploaderId_attachment_affixIds").val("");
	}

	
</script> 