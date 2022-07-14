<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->

<div style="background-color: #EDEEEE; width: 100%; height: 100%;">

	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="camera_regionTree" simpleDataEnable="true"  asyncAutoParam="id,name" showRootNode="true" asyncType="get" asyncEnable="true" onClick="search">
		</cui:tree>
	</div>
	<div style="margin-left: 26%; height: 100%;">
	    <!-- 自定义查询开始 -->
		<cui:form id = "form_txrh">

			<table class="table">
				<tr>
					<th>民警姓名:</th>
					<td>
						<cui:input id="s_userName" name="userName" type="text"
							placeholder=""></cui:input>
					</td>
					<td>
						<cui:button cls="cyanbtn" label="查询"
									onClick="search" componentCls="coral-btn-blue" />
						<cui:button id="resetBtn" label="重置" onClick="resetHandler_"></cui:button>
					</td>
					<td width="30%">
						
					</td>
					<td>
						<cui:button cls="cyanbtn" label="呼叫"
									onClick="f_call()" componentCls="coral-btn-green" />
					</td>
					<td>
						<cui:button cls="cyanbtn" label="挂断"
									onClick="f_hangUp()" componentCls="coral-btn-red" />
					</td>
				</tr>

			</table>

		</cui:form>
		<!-- 自定义查询结束 -->
		<cui:grid id="gridId_camera" multiselect="true" shrinkToFit="false"
				  colModel="gridColModel_camera" fitStyle="fill" datatype="json" url=""
				  pager="true"></cui:grid>
	</div>
</div>
<cui:dialog id="dialog_rcs" autoOpen="false" iframePanel="true"
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
	<!-- <div id="dailog"></div> -->

<cui:dialog id="dialog_rcs_call" autoOpen="false" iframePanel="true"
	reLoadOnOpen="true" modal="true" resizable="false">
	<div style="text-align: center; height: 100%; width: 100%">
		<cui:form id="formId_addInfo" >
			<cui:input type='hidden' name="cusNumber" id="cusNumber" />
			<cui:input type='hidden' name="sendUserName" id="sendUserName" />
			<cui:input type='hidden' name="sendUserId" id="sendUserId" />
			<cui:input type='hidden' name="jobNo" id="jobNo" />
			<cui:input type='hidden' name="userName" id="userName" />
			<cui:input type='hidden' name="cellvalue" id="cellvalue" />
		</cui:form>
			<table class="table" style="width: 98%;height: 90%;" >
				<tr><th colspan = '3'>第一步：</th></tr>
				<tr>
					<td>前缀：</td>
					<td>
						<cui:input name="policeaffair_pre" id = "policeaffair_pre"></cui:input>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>警务通：</td>
					<td>
						<cui:input name="policeaffair" id ="policeaffair"></cui:input>
					</td>
					<td>
						<cui:button label="呼叫" cls="cyanbtn" text="false" onClick="call(1)"></cui:button>
					</td>
				</tr>
				<tr><th colspan = '3'>第二步：</th></tr>
				<tr>
					<td>前缀：</td>
					<td>
						<cui:input name="telphone_pre" id = "telphone_pre"></cui:input>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>固定电话：</td>
					<td>
						<cui:input name="telphone" id = "telphone"></cui:input>
					</td>
					<td>
						<cui:button label="呼叫" cls="cyanbtn" text="false" onClick="call(2)"></cui:button>
					</td>
				</tr>
				<tr><th colspan = '3'>第三步：</th></tr>
				<tr>
					<td>前缀：</td>
					<td>
						<cui:input name="mobile_pre" id = "mobile_pre"></cui:input>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>手机：</td>
					<td>
						<cui:input name="mobile" id="mobile" ></cui:input>
					</td>
					<td>
						<cui:button label="呼叫" cls="cyanbtn" text="false" onClick="call(3)"></cui:button>
					</td>
				</tr>
			</table>
		
	</div>	
</cui:dialog>	

<script>

	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人
	var userNAME = jsConst.USER_NAME; 

	$.parseDone(function() {
        $("#camera_regionTree").tree( "reload", "${ctx}/common/authsystem/findAllDeptForCombotree?cusNumber=" + cusNumber + "&deviceType=0");
        $("#gridId_camera").grid("reload","${ctx}/rcs/searchRcs.json");
	});

	function search() {
		var postData = {};
		var organizeCode = '';
        //获取左侧树菜单中选中节点,单选
        var organizeCode = $("#camera_regionTree").tree("getSelectedNodes");
        if( organizeCode[0] &&  organizeCode[0].id){
        	organizeCode = organizeCode[0].id;
        }
        var userName = $('#s_userName').val();
		if (userName != "") {
			postData['userName'] = userName;
		}
		$('#gridId_camera').grid('option', 'postData', postData);
		$("#gridId_camera").grid("reload","${ctx}/rcs/searchRcs.json?organizeCode="+ organizeCode);
	}
	function resetHandler_(){
		$('#s_userName').val('');
	}
	
	function formatEvidenceRcs(cellvalue, options, rawObject) {
		return cellvalue;
		var name = rawObject.USER_NAME;
		var no = rawObject.JOB_NO;
		var html =  "<a href='javascript: void(0);' onclick='openDailog("+cellvalue+",\""+name+"\","+no+")' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		return html;	
	}
	function openDailog(cellvalue,name,no){
		
		var url = "${ctx}/rcs/dailog?cellvalue="+encodeURI(cellvalue)+"&name="+encodeURI(name)+"&no="+encodeURI(no);
		
		$("#dialog_rcs").dialog({
			width :500,
			height : 600,
			subTitle : '通讯融合',
			url : url				 
		});
		$("#dialog_rcs").dialog("reload");
		$("#dialog_rcs").dialog("open");
		
	}

	function f_call(){
		var selected = $("#gridId_camera").grid("option", "selarrrow");
		if (selected.length == 1) {
			var rowData = $('#gridId_camera').grid("getRowData", selected[0]);
			
			$('#policeaffair').val(rowData.POLICEAFFAIR);
			$('#telphone').val(rowData.TELPHONE);
			$('#mobile').val(rowData.MOBILE);
			
			$("#jobNo").textbox("setValue",rowData.JOB_NO);
			$("#userName").textbox("setValue",rowData.USER_NAME);
			$("#cusNumber").textbox("setValue",cusNumber);
			$("#sendUserName").textbox("setValue",userNAME);
			$("#sendUserId").textbox("setValue",userId); 
			
			$("#dialog_rcs_call").dialog({
				width : 500,
				height : 600,
				subTitle : '呼叫'
			});
			$("#dialog_rcs_call").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请勾选一条需要处理记录！",
				cls : "warning"
			});
		}
	}
	
	//挂断
	function f_hangUp(){
		var formData = $("#formId_addInfo").form("formData");
		var ur = '${ctx}/rcs/hangUp';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
	            if(data.code == 200){
	            	$.message( {
						iframePanel:true,
				        message:data.message,
				        type:"success"
				    });
	            }else if(data.code == 500){
	            	$.message( {
						iframePanel:true,
				        message:data.message,
				        type:"danger"
				    });
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message( {
					iframePanel:true,
			        message:textStatus,
			        type:"danger"
			    });
			}
		});
	}
	
	/**
	 * 呼叫
	 */
	function call(type) {
		
		//警务通
		if(type == 1){
			var cellvalue = $("#policeaffair_pre").val() + $("#policeaffair").val();
			$("#cellvalue").textbox("setValue",cellvalue);
			//开始呼叫
			start_call();
		}
		//固话
		else if(type == 2){
			$.confirm( {
				message:"是否本地号码？",
				okText:"是",
				cancelText:"否",
				iframePanel:true,
				callback: function(sure) {
					//本地
					if (sure == true) {
						var cellvalue = $("#telphone_pre").val() + $("#telphone").val();
						$("#cellvalue").textbox("setValue",cellvalue);
						//开始呼叫
						start_call();
					}
					//异地
					if (sure == false) {
						var cellvalue = $("#telphone_pre").val() + '0' + $("#telphone").val();
						$("#cellvalue").textbox("setValue",cellvalue);
						//开始呼叫
						start_call();
					}
				}
			});
		}
		//手机
		else if(type == 3){
			$.confirm( {
				message:"是否本地号码？",
				okText:"是",
				cancelText:"否",
				iframePanel:true,
				callback: function(sure) {
					//本地
					if (sure == true) {
						var cellvalue = $("#mobile_pre").val() + $("#mobile").val();
						$("#cellvalue").textbox("setValue",cellvalue);
						//开始呼叫
						start_call();
					}
					//异地
					if (sure == false) {
						var cellvalue = $("#mobile_pre").val() + '0' + $("#mobile").val();
						$("#cellvalue").textbox("setValue",cellvalue);
						//开始呼叫
						start_call();
					}
				}
			});
		}
		
	}
	function start_call(){
		var formData = $("#formId_addInfo").form("formData");
		var ur = '${ctx}/rcs/startCall';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
	            if(data.code == 200){
	            	$.message( {
						iframePanel:true,
				        message:data.message,
				        type:"success"
				    });
	            }else if(data.code == 500){
	            	$.message( {
						iframePanel:true,
				        message:data.message,
				        type:"danger"
				    });
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message( {
					iframePanel:true,
			        message:textStatus,
			        type:"danger"
			    });
			}
		});
	}
	
	var gridColModel_camera = [  {
		label : "警号",
		name : "JOB_NO",
		align : "center",
		width : 200
	}, {
        label : "民警姓名",
        width : 200,
        align : "center",
        name : "USER_NAME"
    },{
        label : "警务通",
        width : 200,
        align : "center",
        name : "POLICEAFFAIR",
        formatter : "formatEvidenceRcs"
    }, {
		label : "固定电话",
		width : 200,
		align : "center",
		name : "TELPHONE",
		formatter : "formatEvidenceRcs"
	}, {
		label : "手机号码",
		width : 200,
		align : "center",
		name : "MOBILE",
		formatter : "formatEvidenceRcs"
	}];
</script>