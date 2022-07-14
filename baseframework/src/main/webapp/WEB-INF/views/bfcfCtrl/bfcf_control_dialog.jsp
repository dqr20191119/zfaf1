<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}

#abdType{
	font-style: normal; 
	color: #FF0000;
	font-size: 18px;
	font-weight:bold;
}

#operateBfcf{
	cursor:pointer;
	 width: 100px; height: 50px;
}

#stopBfcf{
	cursor:pointer;
	 width: 100px; height: 50px;
}

#operateBfcfYjBf{
	cursor:pointer;
	 width: 110px; height: 50px;
}

#stopBfcfYjCf{
	cursor:pointer;
	 width: 110px; height: 50px;
}

#zt{
	font-size: 18px;
	font-weight:bold;
}
</style>

<!-- 页面元素id前缀 -->
<div id="a1" style="text-align: center; height: 100%; width: 100%">
	<!-- <img alt="" src=jsConst.basePath+"/static/module/sign/png/baojingqi.png"> -->
	<cui:input id="bjqId" type="hidden" value="${bjqId }"></cui:input> 
 	<cui:form id="formId_alert_play" heightStyle="fill"> 
		<!-- 监狱编号 -->
		<%-- <cui:input id="cusNumber" type="hidden"></cui:input> --%>
		<!-- 报警器id -->
		<table class="table" style="width: 98%">
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="abdName" componentCls="form-control"></cui:input>
				</td>
				<th>布防撤防状态：</th>
				<td>
					<cui:combobox name="abdType" componentCls="form-control" data="queryAbdTypeData" ></cui:combobox>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="clearQuery"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 5px; text-align: left; height: 56px" >
		<%-- <cui:button id="operateBfcf"  text="false" onClick="startPlay"></cui:button>
		<cui:button id="stopBfcf"  text="false" onClick="stopPlay"></cui:button> --%>
		<img id="operateBfcf" onclick="startPlay()">
		<img id="operateBfcfYjBf" onclick="startPlayYjBf()">
		<img id="stopBfcf" onclick="stopPlay()">
		<img id="stopBfcfYjCf" onclick="stopPlayYjCf()">
	</div>
		<cui:grid id="gridId_alertor" fitStyle="fill"  colModel="gridId_alertor_colModelDate">
			<cui:gridPager gridId="gridId_alertor" />
	  </cui:grid>
	
</div>

<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号


	$.parseDone(function() {
		$('#operateBfcf').attr("src",jsConst.basePath+"/static/module/sign/png/bf_tb.png");
		$('#operateBfcfYjBf').attr("src",jsConst.basePath+"/static/module/sign/png/yjbf.png");
		$('#stopBfcf').attr("src",jsConst.basePath+"/static/module/sign/png/cf_tb.png");
		$('#stopBfcfYjCf').attr("src",jsConst.basePath+"/static/module/sign/png/yjcf.png");
		//setInterval(function () {
			/* var url = "${ctx}/alertor/findById?id=${bjqId}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var info = data.obj; */
						/* $("#formId_alert_play").find("#cusNumber").textbox("setValue", info.bbdCusNumber); */
                        /* $("#formId_alert_play").find("#bjqId").textbox("setValue", info.id);
						if(info.abdType==null||info.abdType==0||info.abdType==""){
							$("#formId_alert_play").find("#abdType").combobox("setValue", "已撤防");
						}else if(info.abdType==1){
							$("#formId_alert_play").find("#abdType").combobox("setValue", "已布防");
						}else if(info.abdType==2){
							$("#formId_alert_play").find("#abdType").combobox("setValue", "周界布防");
						}else {
							$("#formId_alert_play").find("#abdType").combobox("setValue", "未定义");
						}
                       
					} else {
						$.messageQueue({
							message : data.msg,
							cls : "warning",
							iframePanel : true,
							type : "info"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({
						message : textStatus,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			});  */
		//}, 2000);
		var url = "${ctx}/alertor/listAll.json?abdCusNumber=" + cusNumber+"&abdTypeIndc=3";
		$("#gridId_alertor").grid("reload", url);
	});
	//报警器类型
	var abdTypeIndcDate = <%=CodeFacade.loadCode4ComboJson("4.20.27", 3, "0")%>;
	//报警器状态
	var abdSttsIndcDate = <%=CodeFacade.loadCode2Json("4.20.55")%>;
	var gridId_alertor_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "ABD_IDNTY",
		label : "编号",
		hidden : true
	}, {
		name : "ABD_NAME",
		label : "名称",
		align : "center",
		width : 200
	}, {
		name : "ABD_STTS_INDC",
		label : "状态",
		align : "center",
		width : 90,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':abdSttsIndcDate} 
	}, /* {
		name : "ABD_ADDRS",
		label : "报警器地址"
	},  {
		name : "ABD_IP",
		label : "报警器IP"
	}, *//*  {
		name : "ABD_AREA",
		label : "所属区域"
	}, *//*  {
		name : "ABD_PRE_NAME",
		label : "报警器名称前缀"
	}, */ {
		name : "ABD_TYPE_INDC",
		label : "类型",
		width : 100,
		align : "center",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':abdTypeIndcDate} 
	},  {
		name : "ABD_TYPE",
		label : "布防撤防状态",
		align : "center",
		width : 100,
		formatter : "convertCode",
        revertCode : true,
		formatoptions : { 'data':abdTypeData} 
	}/*,  {
		name : "ABD_HOST_IDNTY",
		label : "主机编号"
	},*/ /* {
		name : "ABD_BRAND_INDC",
		label : "品牌",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':alertorBrandDate} 
	}, {
		name : "ABD_REMARK",
		label : "备注"
	}  */];
	
	
	var abdTypeData =[
		{"value":"0","text":"撤防"},
		{"value":"1","text":"已布防"},
		{"value":"2","text":"周界布防"},
		{"value":"3","text":"未定义"}]
	
	var queryAbdTypeData =[
		{"value":"0","text":"撤防"},
		{"value":"1","text":"已布防"},
		{"value":"2","text":"周界布防"}]
	
	
	//发布一键撤防指令
	function stopPlayYjCf() {
		if ($("#formId_alert_play").form("valid")) {
			var formObj = $("#formId_alert_play");
            // 报警主键
			var bjqId =$("#bjqId").textbox("getValue");
            
			var abdType =formObj.find("#abdType").combobox("getValue");
				$.confirm({
					message:"请确认是否将全部防区撤防?",
					iframePanel:true,
					callback: function(sure) {
						if (sure == true) {
							 var paramData = {};
				            paramData["bjqId"] = bjqId;
				            paramData["type"] = "4";//一键撤防
				            
				           console.log("paramData = " + JSON.stringify(paramData)); 
				           $.ajax({
								type : 'post',
								url : '${ctx}/bfcf/startBfCf.json',
								data : paramData,
								dataType : 'json',
								success : function(data) {
						            if(data.code == 200) {
										$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
										$.ajax({
				 							type : 'get',
				 							url : "${ctx}/alertor/findById?id="+bjqId,
				 							dataType : 'json',
				 							success : function(data) {
				 								if (data.success) {
				 									var da = data.obj;
				 									if(da.abdType==null||da.abdType==0||da.abdType==""){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已撤防");
				 									}else if(info.abdType==1){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已布防");
				 									}else if(info.abdType==2){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "周界布防");
				 									}else {
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "未定义");
				 									}
				 								  }
				 								
				 							},
				 							error : function(XMLHttpRequest, textStatus, errorThrown) {
				 								$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				 							}
				 						});
								       
						            } else if(data.code == 500) {
										$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
						            }
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
								}
							});   
							   
							}
						} 
				});
			
			 
		
		}	
		
	}
	
	//发布撤防指令
	function stopPlay() {
		if ($("#formId_alert_play").form("valid")) {
			var formObj = $("#formId_alert_play");
            // 报警主键
			var bjqId =$("#bjqId").textbox("getValue");
            
			var abdType =formObj.find("#abdType").combobox("getValue");
			if(abdType=="已撤防"){
				$.alert("已是撤防状态,如要布防,请操作布防!");
			}else{
				$.confirm({
					message:"确认是否撤防?",
					iframePanel:true,
					callback: function(sure) {
						if (sure == true) {
							 var paramData = {};
				            paramData["bjqId"] = bjqId;
				            paramData["type"] = "2";//撤防
				            
				           console.log("paramData = " + JSON.stringify(paramData)); 
				           $.ajax({
								type : 'post',
								url : '${ctx}/bfcf/startBfCf.json',
								data : paramData,
								dataType : 'json',
								success : function(data) {
						            if(data.code == 200) {
										$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
										$.ajax({
				 							type : 'get',
				 							url : "${ctx}/alertor/findById?id="+bjqId,
				 							dataType : 'json',
				 							success : function(data) {
				 								if (data.success) {
				 									var da = data.obj;
				 									if(da.abdType==null||da.abdType==0||da.abdType==""){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已撤防");
				 									}else if(info.abdType==1){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已布防");
				 									}else if(info.abdType==2){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "周界布防");
				 									}else {
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "未定义");
				 									}
				 								  }
				 								
				 							},
				 							error : function(XMLHttpRequest, textStatus, errorThrown) {
				 								$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				 							}
				 						});
								       
						            } else if(data.code == 500) {
										$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
						            }
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
								}
							});   
							   
							}
						} 
				});
			}
			 
		
		}	
		
	}

	//发送布防指令
	function startPlay() {
		if ($("#formId_alert_play").form("valid")) {
			var formObj = $("#formId_alert_play");
            // 报警主键
			var bjqId =$("#bjqId").textbox("getValue");
          
			var abdType =formObj.find("#abdType").combobox("getValue");
			if(abdType=="已布防"){
				$.alert("已是布防状态,如要撤防,请操作撤防!");
			}else{
				$.confirm({
	 				message:"确认是否布防?",
	 				iframePanel:true,
	 				callback: function(sure) {
	 					if (sure == true) {
	 						 var paramData = {};
	 			            paramData["bjqId"] = bjqId;
	 			            paramData["type"] = "1";//布防
	 			            
	 			           console.log("paramData = " + JSON.stringify(paramData));
	 			          $.ajax({
								type : 'post',
								url : '${ctx}/bfcf/startBfCf.json',
								data : paramData,
								dataType : 'json',
								success : function(data) {
						            if(data.code == 200) {
										$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
										$.ajax({
										 			 							type : 'get',
										 			 							url : "${ctx}/alertor/findById?id="+bjqId,
										 			 							dataType : 'json',
										 			 							success : function(data) {
										 			 								if (data.success) {
										 			 									var da = data.obj;
										 			 									if(da.abdType==null||da.abdType==0||da.abdType==""){
										 			 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已撤防");
										 			 									}else if(info.abdType==1){
										 			 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已布防");
										 			 									}else if(info.abdType==2){
										 			 										$("#formId_alert_play").find("#abdType").combobox("setValue", "周界布防");
										 			 									}else {
										 			 										$("#formId_alert_play").find("#abdType").combobox("setValue", "未定义");
										 			 									}
										 			 								}else{
										 			 									$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
										 			 								}
										 			 							},
										 			 							error : function(XMLHttpRequest, textStatus, errorThrown) {
										 			 								$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
										 			 							}
										 			 						});
								       
						            } else if(data.code == 500) {
										$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
						            }
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
								}
							});
	 						
	 					} 
	 					if (sure == false) {
	 						console.log('cancel');
	 					}
					}
	 			});
			}
			
		}
			
	}

	//发布一键布防指令
	function startPlayYjBf() {
		if ($("#formId_alert_play").form("valid")) {
			var formObj = $("#formId_alert_play");
            // 报警主键
			var bjqId =$("#bjqId").textbox("getValue");
            
			var abdType =formObj.find("#abdType").combobox("getValue");
				$.confirm({
					message:"谨慎操作,请确认是否将全部防区布防?",
					iframePanel:true,
					callback: function(sure) {
						if (sure == true) {
							 var paramData = {};
				            paramData["bjqId"] = bjqId;
				            paramData["type"] = "3";//一键撤防
				            
				           console.log("paramData = " + JSON.stringify(paramData)); 
				           $.ajax({
								type : 'post',
								url : '${ctx}/bfcf/startBfCf.json',
								data : paramData,
								dataType : 'json',
								success : function(data) {
						            if(data.code == 200) {
										$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
										$.ajax({
				 							type : 'get',
				 							url : "${ctx}/alertor/findById?id="+bjqId,
				 							dataType : 'json',
				 							success : function(data) {
				 								if (data.success) {
				 									var da = data.obj;
				 									if(da.abdType==null||da.abdType==0||da.abdType==""){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已撤防");
				 									}else if(info.abdType==1){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "已布防");
				 									}else if(info.abdType==2){
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "周界布防");
				 									}else {
				 										$("#formId_alert_play").find("#abdType").combobox("setValue", "未定义");
				 									}
				 								  }
				 								
				 							},
				 							error : function(XMLHttpRequest, textStatus, errorThrown) {
				 								$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				 							}
				 						});
								       
						            } else if(data.code == 500) {
										$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
						            }
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
								}
							});   
							   
							}
						} 
				});
			
			 
		
		}	
		
	}
	
	// 关闭对话框
	function close() {
        $("#confirmDialog").dialog("close");
	}
	
	function query() {
		var formData = $("#formId_alert_play").form("formData");
		$("#gridId_alertor").grid("option", "postData", formData);
		$("#gridId_alertor").grid("reload");
	}

	function clearQuery() {
		$("#formId_alert_play").form("clear");
	}
	
	/* 广播类型选择事件 */
	function onContentTypeSelect(event, ui) {
		if(ui.value == '1') {
			// $("#broadcastContent1").autocomplete("setValue", "");
			// $("#broadcastContent2").textarea("setValue", "");
			var cusNumber = $("#cusNumber").textbox("getValue");
			var audioArray = $.ajax({
				url: "${ctx}/broadcastFile/queryBroadcastFileCombobox.json?cusNumber=" + cusNumber ,
				async: false
			}).responseText;
			$("#content1").autocomplete( {"source":JSON.parse(audioArray)});

			$("#content1").autocomplete({required: true});
			$("#content2").textbox({required: false});
			
			$("#formId_alert_play").find("tr[id='tr_content_type_2']").hide();
			$("#formId_alert_play").find("tr[id='tr_content_type_1']").show();
		} else {
			$("#content1").autocomplete({required: false});
			$("#content2").textbox({required: true});
			
			$("#formId_alert_play").find("tr[id='tr_content_type_1']").hide();
			$("#formId_alert_play").find("tr[id='tr_content_type_2']").show();
		}
	}
</script>
