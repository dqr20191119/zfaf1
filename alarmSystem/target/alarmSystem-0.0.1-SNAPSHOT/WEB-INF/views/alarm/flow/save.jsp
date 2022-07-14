<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<style type="text/css">
.fieldset_plan {
	border: #B3D0F4 2px solid;
}

.left input[type='text'] {
	height: 25px;
	border: 1px solid #ccc;
}

.combo-text {
	text-indent: 2px;
}

.left table {
	margin: 0px 0px 0px 2px;
	text-align: left;
	display: block;
}

.tableTwo {
	border: solid 1px #fff;
	background: #F5FAFA;
}

.tableTwo tr {
	height: 45px;
}

.tableTwo td input {
	text-indent: 2px;
}

.dialog .table {
	margin: 10px 20px;
}
</style>
<script type="text/javascript">
	$.parseDone(function() {
		//判断是否更新
		<c:if test="${id != null}">
		$("#dialogId_flow").loading({ text : "正在请求预案详情信息，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/flow/findById',
			data : {'id':'${id}'},
			dataType : 'json',
			success : function(data) {
				if(data.success = true){
					data = data.obj
					$('#id').val(data.id);
			    	$('#alarmEvent').textbox('setValue',data.hfmFlowName);
			    	$('#alarmLevel').combobox('setValue',data.hfmAlarmLevel+"");
			    	$('#showSeq').textbox('setValue',data.hfmShowSeq+"");
			    	$("#flowNum").textbox('setValue',data.hfmFlowDtlsList.length);
			    	$("#flowNum").textbox('option','readonlyInput',true);
			    	changeFlowNum(data.hfmFlowDtlsList);
				}
				$("#dialogId_flow").loading("hide");
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({ message:textStatus, title:"提示信息", iframePanel:true });
			}
		});
		</c:if>
		$("#stable .left").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
	});

	function changeFlowNum(msg) {
		$("#subTd").empty();
		var flowNum = $("#flowNum").val();
		var tab = $("<table></table>");
		var item = null;
		for (var i = 0; i < flowNum; i++) {
			item = "<table class='tableTwo' width='765' height='50'><tr>"
					+ "<th style='width: 100px' align='center'>步骤" + (i + 1)
					+ ": </th>";
			if (msg != null) {
				item = item
						+ "<td><input type='text' id='flow"+i+"' style='width:650px' value='"+msg[i].HFD_FLOW_ITEM_NAME+"'></td>";
			} else {
				item = item
						+ "<td><input type='text' id='flow"+i+"' style='width:650px'></td>";
			}

			tab.append("<tr><td>" + item + "</td></tr>");
		}
		$("#subTd").append(tab);
		$("#subTr").show();
		$("#stable").show();
		$("#trId_btn").show();
	}

	function adjust(number, type) {
		var obj = $("#flowNum");
		var val = parseFloat(obj.val()) + number;
		if (val < 0 || isNaN(val)) {
			return;
		}
		if (val == 0) {
			$("#trId_btn").hide();
		} else {
			$("#trId_btn").show();
		}
		obj.val(val);

		var len = $("#subTd").children().eq(0).length;
		if (len == 0) {
			$("#subTd").append("<table></table>");
		}
		if (type == "plus") {
			var item = "<table class='tableTwo' width='765' height='50'><tr>" 
					+ "<th style='width: 100px' align='center'>步骤" + val
					+ ": </th>" + "<td><input type='text' id='flow" + (val - 1)
					+ "' style='width:650px'></td>";
			$("#subTd").children().eq(0).append("<tr><td>" + item + "</td></tr>");
		} else {
			$("#subTd").children().eq(0).children().eq(0).children().eq(val).remove();
		}
	}

	//获取步骤
	function getFlowData() {
		var flowdata = new Array();
		var flowNum = $("#flowNum").val();
		for (var i = 0; i < flowNum; i++) {
			var flow = {};
			flow.id = i + 1;
			flow.name = $("#flow" + i).val();
			if (flow.name == null || flow.name == "") {
				$.message({
					message : "请输入步骤【 " + (i + 1) + " 】！",
					iframePanel : true
				});
				return;
			}
			flowdata.push(flow);
		}
		return flowdata;
	}
	function save() {
		var data = getFlowData();
		if(data == null)
			return;
		var showSeq = $('#showSeq').textbox('getValue');
		if(showSeq == ''){
			$.message({
				message:"请输入选择序号！",
				iframePanel:true
			});
			return;
		}
		var alarmLevel = $('#alarmLevel').combobox('getValue');
		if(alarmLevel == ''){
			$.message({
				message:"请选择报警等级！",
				iframePanel:true
			});
			return;
		}
		var alarmEvent = $('#alarmEvent').textbox('getValue');
		if(alarmEvent == ''){
			$.message({
				message:"请输入流程名称！",
				iframePanel:true
			});
			return;
		}
		var hfmFlowDtls = "";
		for(var i=0;i<data.length;i++){
			var row = data[i];
			hfmFlowDtls += row.name+";";
		}
		$('#hfmFlowDtls').val(hfmFlowDtls);
		var formData = $("#formId_save").form("formData");
		$.ajax({
			type : 'post',
			url : '${ctx}/flow/saveFlow',
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data != null){
					$.message({
						message:"操作成功",
						iframePanel:true
					});
					$("#dialogId_flow").dialog('close');
					$('#gridId_flow').grid("reload");
				}else{
					$.message({
						message:"操作失败",
						iframePanel:true
					});
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({
					message:textStatus,
					title:"提示信息",
					iframePanel:true
				});
			}
		});


	}
</script>
</head>
<body>

	<div id="save" class="dialog">
		<table class="table">
			<tr>
				<td>
					<cui:form id="formId_save">
						<table class="fieldset_plan" border="0" width="800" height="80">
							<input type="hidden" id="id" name="id" />
							<input type="hidden" id="hfmFlowDtls" name="hfmFlowDtls" />
							<tr id="setTypeTr">
								<th width="105" align="center">显示序号</th>
								<td width="60" align="left">
									<cui:input id="showSeq" name="hfmShowSeq" validType="integer" width="40"></cui:input>
								</td>
								<th width="105" align="center">报警等级</th>
								<td width="85" align="left">
									<cui:combobox id="alarmLevel" name="hfmAlarmLevel" data="levelData"></cui:combobox>
								</td>
								<th width="105" align="center">流程名称</th>
								<td width="175" align="left">
									<cui:input id="alarmEvent" name="hfmFlowName"></cui:input>
								</td>
								<th width="105" align="center">流程数量</th>
								<td width="175" align="left">
									<cui:button onClick="adjust(1,'plus')" label="+" width="20"></cui:button>
									<cui:input id="flowNum" validType="integer" width="40" onChange="changeFlowNum()"></cui:input>
									<cui:button onClick="adjust(-1,'minus')" label="-" width="20"></cui:button>
								</td>
							</tr>
						</table>
					</cui:form>
				</td>
			</tr>
			<tr>
				<td>
					<fieldset id="stable" style="display: none;" width="100%" height="auto">
						<legend>处置流程</legend>
						<div class="left" style="height: 345px; width: 100%;">
							<table>
								<tr id="subTr">
									<td id="subTd"></td>
								</tr>
								<tr id="trId_btn" style="display: none; text-align: center;">
									<td>
										<cui:button onClick="save" label="提交"></cui:button>
									</td>
								</tr>
							</table>
						</div>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>