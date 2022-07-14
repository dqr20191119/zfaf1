<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->
<style>
.form-control {
	width: 100%;
}
</style>
<div style="background-color: #EDEEEE; width: 100%; height: 100%;">

	<div style="float: left; width: 30%; height: 100%;>
	<cui:form id="form_txrh">
			<table class="table">
				<tr>
					<td>
                        <cui:input id="s_userName" name="userName" type="text" placeholder="民警姓名"></cui:input>
                    </td>
					<td width="20%">
                        <cui:button cls="cyanbtn" label="查询" onClick="search"  componentCls="coral-btn-blue" />
                    </td>
				</tr>

			</table>
		</cui:form>
        <div style="float: left; width: 100%; height: 80%; overflow-y: auto">
		<cui:tree id="camera_regionTree" simpleDataEnable="true"
			asyncAutoParam="id,name" showRootNode="true" asyncType="get"
			asyncEnable="true" onClick="getMjxx">
		</cui:tree>
        </div>
	</div>
	<div style="margin-left: 31%; height: 100%;">
		<cui:form id="formId_addInfo" >
			<cui:input type='hidden' name="cusNumber" id="cusNumber" />
			<cui:input type='hidden' name="sendUserName" id="sendUserName" />
			<cui:input type='hidden' name="sendUserId" id="sendUserId" />
			<cui:input type='hidden' name="jobNo" id="jobNo" />
			<cui:input type='hidden' name="userName" id="userName" />
			<cui:input type='hidden' name="cellvalue" id="cellvalue" />
		</cui:form>
	
		<cui:form id="form_txrh2" >
			<table class="table" style="margin-top:7%;width: 100%;height: 100%">
				<tr>
					<th>民警姓名:</th>
					<td>
					<cui:input id="mjxm" name="mjxm" type="text" readonly="false"></cui:input></td>
					<td width="10%"></td>
					<td width="30%"></td>
				</tr>
				<tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
				<tr>
					<th>警务通:</th>
					<td><cui:input id="jwt" name="jwt" type="text" readonly="false"></cui:input></td>
					<td>
                        <cui:button cls="cyanbtn" label="呼叫" onClick="call(1)" componentCls="coral-btn-green" />
                    </td>
                    <td>
                        <cui:button cls="deleteBtn" label="挂断" onClick="f_hangUp"/>
                    </td>
				</tr>
				<tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
				<tr>
					<th>固定电话:</th>
					<td><cui:input id="gddh" name="gddh" type="text" readonly="false"></cui:input></td>
					<td>
                        <cui:button cls="cyanbtn" label="呼叫" onClick="call(2)" componentCls="coral-btn-green" />
                    </td>
                    <td>
                        <cui:button cls="deleteBtn" label="挂断" onClick="f_hangUp"/>
                    </td>
				</tr>
				<tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
				<tr>
					<th>手机:</th>
					<td><cui:input id="sj" name="sj" type="text" readonly="false"></cui:input></td>
					<td><cui:button cls="cyanbtn" label="呼叫" onClick="call(3)"
							componentCls="coral-btn-green" /></td>
                    <td>
                        <cui:button cls="deleteBtn" label="挂断" onClick="f_hangUp"/>
                    </td>
				</tr>
				<tr><td></td></tr>
                <tr><td></td></tr>
				<%--<tr>
					<th>多个号码:</th>
					<td><cui:input id="multiCallNum" name="multiCallNum" type="text" readonly="false"></cui:input></td>
					<td><cui:button cls="cyanbtn" label="快速呼叫" onClick="multiCall()"
									componentCls="coral-btn-green" /></td>
				</tr>--%>
				<tr><td></td></tr>
				<tr><td></td></tr>
				<%--<tr>
					<td colspan="3"><cui:textarea id='xxnr' componentCls="form-control" ></cui:textarea></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
					<cui:button cls="cyanbtn" label="发送" onClick="call(4)" componentCls="coral-btn-green" /> 
					<cui:button		cls="cyanbtn" label="重置" onClick="resetXxnr()" componentCls="coral-btn-green" /></td>
				</tr>--%>
			</table>
		</cui:form>
	</div>
</div>

<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人
	var userNAME = jsConst.USER_NAME;

	$.parseDone(function() {
		$("#camera_regionTree").tree(
				"reload",
				"${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber="
						+ cusNumber + "&deviceType=0");
	});

	function search(){
		var querypara = '';
        var userName = $('#s_userName').val();
		if (userName != "") {
			querypara = '&mjxm='+encodeURI(userName);
		}
		$("#camera_regionTree").tree("reload","${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber="+ cusNumber+querypara);
	}
	
	
	function getMjxx() {
		//获取左侧树菜单中选中节点,单选
		var nodeCode = $("#camera_regionTree").tree("getSelectedNodes");
		if(nodeCode[0] &&!nodeCode[0].isParent){
			if(nodeCode[0].name){$('#mjxm').val(nodeCode[0].name);}else{$('#mjxm').val('');}
			if(nodeCode[0].jwt){$('#jwt').val(nodeCode[0].jwt);}else{$('#jwt').val('');}
			if(nodeCode[0].gddh){$('#gddh').val(nodeCode[0].gddh);}else{$('#gddh').val('');}
			if(nodeCode[0].sj){$('#sj').val(nodeCode[0].sj);}else{$('#sj').val('');}
			// TODO 测试多个号码呼叫
			$('#multiCallNum').val("9013308482877,9013916245071");
			
			$("#jobNo").textbox("setValue",nodeCode[0].jh);
			$("#userName").textbox("setValue",$('#mjxm').val());
			$("#cusNumber").textbox("setValue",cusNumber);
			$("#sendUserName").textbox("setValue",userNAME);
			$("#sendUserId").textbox("setValue",userId); 
		}
	}

	function resetXxnr(){
		$('#xxnr').val('');
	}
	
	//挂断
	function f_hangUp() {
		var formData = $("#formId_addInfo").form("formData");
		var ur = '${ctx}/rcs/hangUp';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if (data.code == 200) {
                    $.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
				} else if (data.code == 500) {
                    $.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({
					iframePanel : true,
					message : textStatus,
					type : "danger"
				});
			}
		});
	}

	/**
	 * 呼叫
	 */
	function call(type) {
		
		//警务通
		if (type == 1) {
			var cellvalue = $("#jwt").val();
			$("#cellvalue").textbox("setValue", cellvalue);
			//开始呼叫
			start_call();
		}
		//固话
		else if (type == 2) {
			$.confirm({
				message : "是否本地号码？",
				okText : "是",
				cancelText : "否",
				iframePanel : true,
				callback : function(sure) {
					//本地
					if (sure == true) {
						var cellvalue = $("#gddh").val();
						$("#cellvalue").textbox("setValue", cellvalue);
						//开始呼叫
						start_call();
					}
					//异地
					if (sure == false) {
						var cellvalue = '0'+ $("#gddh").val();
						$("#cellvalue").textbox("setValue", cellvalue);
						//开始呼叫
						start_call();
					}
				}
			});
		}
		//手机
		else if (type == 3) {
			$.confirm({
				message : "是否本地号码？",
				okText : "是",
				cancelText : "否",
				iframePanel : true,
				callback : function(sure) {
					//本地
					if (sure == true) {
						var cellvalue =$("#sj").val();
						$("#cellvalue").textbox("setValue", cellvalue);
						//开始呼叫
						start_call();
					}
					//异地
					if (sure == false) {
						var cellvalue ='0'+ $("#sj").val();
						$("#cellvalue").textbox("setValue", cellvalue);
						//开始呼叫
						start_call();
					}
				}
			});
		}else if (type == 4) {//信息发送
			var cellvalue = $("#xxnr").val();alert('开发中');return;
			//$("#content").textbox("setValue", cellvalue);
			//开始呼叫
			//start_call();
		}

	}
	function start_call() {
		var cellvalue = $("#cellvalue").textbox("getValue");
		if(!cellvalue){alert('号码不正确');return;}
		$("#cellvalue").textbox("setValue", '9' + cellvalue);
		
		var formData = $("#formId_addInfo").form("formData");
		var ur = '${ctx}/rcs/startCall';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if (data.code == 200) {
                    $.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
				} else if (data.code == 500) {
                    $.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({
					iframePanel : true,
					message : textStatus,
					type : "danger"
				});
			}
		});
	}

	/**
	 * 多个号码快速呼叫
	 */
	function multiCall() {
		var multiCallNum = $("#multiCallNum").textbox("getValue");
		console.log("multiCall multiCallNum = " + multiCallNum);
		if(!multiCallNum){
			alert('号码不正确');
			return;
		}
		$("#cellvalue").textbox("setValue", multiCallNum);

		var formData = $("#formId_addInfo").form("formData");
		var ur = '${ctx}/rcs/startCall';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if (data.code == 200) {
					$.message({
						iframePanel : true,
						message : data.message,
						type : "success"
					});
				} else if (data.code == 500) {
					$.message({
						iframePanel : true,
						message : data.message,
						type : "danger"
					});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({
					iframePanel : true,
					message : textStatus,
					type : "danger"
				});
			}
		});
	}

</script>