<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<!-- 页面元素id前缀 -->
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="multi_broadcast_play" heightStyle="fill">
		<!-- 监狱编号 -->
		<cui:input id="multiCusNumber" type="hidden"></cui:input>
		<table class="table" style="width: 98%" id="gbList">
		</table>
		<table class="table" style="width: 98%">
			<tr>
				<th>广播类型：</th>
				<td>
					<cui:combobox id="multiContentType" componentCls="form-control" data="gb_typeData" required="true" onSelect="onMultiContentTypeSelect"></cui:combobox>
				</td>
			</tr>
            <tr id="multi_tr_content_type_1" style="display: none;">
                <th>播放内容：</th>
                <td>
                	<cui:autocomplete id="multiContent1" valueField="value" textField="text" multiple="true" postMode="value" multiLineLabel="true"  height="150" componentCls="form-control"></cui:autocomplete>
                </td>
            </tr>
            <tr id="multi_tr_content_type_2" style="display: none;">
                <th>播放内容：</th>
                <td>
					<cui:textarea id="multiContent2" height="150"  componentCls="form-control"></cui:textarea>
                </td>
            </tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button id="multiOperateBroadcast" label="开始播放" text="false" onClick="multiStartPlay"></cui:button>
		<cui:button id="multiClose" label="关闭窗口" text="false" onClick="multiClose"></cui:button>
	</div>
</div>

<script>
	// 广播品牌
	var gb_brandData = <%=CodeFacade.loadCode2Json("4.20.35")%>;
	// 广播状态
	var gb_sttsData = <%=CodeFacade.loadCode2Json("4.20.12")%>;
	// 广播类型
	var gb_typeData = <%=CodeFacade.loadCode2Json("4.20.62")%>;
	//播放类型目前只有 媒体库(1 媒体库，2 文字转语音)
	//var gb_typeData = [{'value':'1','text':' 媒体库'}];
	// 广播语言选择
	var gb_languageData = <%=CodeFacade.loadCode2Json("4.20.63")%>;
	// 广播语音类型
	var gb_voiceTypeData = <%=CodeFacade.loadCode2Json("4.20.64")%>;
	// 广播播放模式
	<%-- var gb_playModeData = <%=CodeFacade.loadCode2Json("4.20.65")%>; --%>
	
	var broadcastList;
	var ppMap = {};
	var ztMap = {};
	
	$.parseDone(function() {
		for (var i = 0; i < gb_brandData.length; i++) {
			ppMap[gb_brandData[i].value] = gb_brandData[i].text;
		}
		for (var i = 0; i < gb_sttsData.length; i++) {
			ztMap[gb_sttsData[i].value] = gb_sttsData[i].text;
		}
		setTimeout(function () {
			var url = "${ctx}/broadcast/findBroadcastList.json?ids=${broadcastId}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						broadcastList = data.obj;
						$("#gbList").html("");
						for (var i = 0; i < broadcastList.length; i++) {
							var html = "";
							html = html + '<tr>';
							html = html + '<th>名称：</th>';
							html = html + '<td>';
							html = html + '<input style="height: 30px;width: 180px;" value="' + broadcastList[i].bbdName + '" readonly="readonly"/>';
							html = html + '</td>';
							html = html + '<th>品牌：</th>';
							html = html + '<td>';
							html = html + '<input style="height: 30px;width: 180px;" value="' + ppMap[broadcastList[i].bbdBrand] + '" readonly="readonly"/>';
							html = html + '</td>';
							html = html + '<th>状态：</th>';
							html = html + '<td>';
							html = html + '<input style="height: 30px;width: 180px;" value="' + ztMap[broadcastList[i].bbdSttsIndc] + '" readonly="readonly"/>';
							html = html + '</td>';
							html = html + '</tr>';
							$("#gbList").append(html);
						}
						$("#multi_broadcast_play").find("#multiCusNumber").textbox("setValue", broadcastList[0].bbdCusNumber);
                        $("#multiOperateBroadcast").button({label: '开始播放', onClick: 'multiStartPlay'});
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
			});
		}, 500);
	});
	
	//发送开始播放指令
	function multiStartPlay() {
		if ($("#multi_broadcast_play").form("valid")) {
			for (var i = 0; i < broadcastList.length; i++) {
				var formObj = $("#multi_broadcast_play");
	            // 广播主键
				var broadcastId = broadcastList[i].id;
				// 广播状态
				var broadcastStatus = broadcastList[i].bbdSttsIndc;
	            // 播放类型
	            var contentType = formObj.find("#multiContentType").combobox("getValue");
	            // 播放内容
	            var content = "";
				if(broadcastStatus != '0' ) {
					if(broadcastStatus == '1') {
						$.messageQueue({ message : "广播状态为离线，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
					} else if(broadcastStatus == '2') {
						$.messageQueue({ message : "广播状态为使用中，请检查后再试", cls : "warning", iframePanel : true, type : "info" });
					} else {
						$.messageQueue({ message : "广播状态未知，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
					}
					return;
				}

	            if(contentType == '1') {
	            	content = formObj.find("#multiContent1").autocomplete("getValue");
	            } else if(contentType == '2') {
	            	content = formObj.find("#multiContent2").textbox("getValue");
	            }
	            
	            // 参数封装
	            var paramData = {};
	            paramData["broadcastId"] = broadcastId;
	            paramData["contentType"] = contentType;
	            paramData["content"] = content;
	            
	            console.log("paramData = " + JSON.stringify(paramData));
				$.ajax({
					type : 'post',
					url : '${ctx}/broadcastRecord/startPlay.json',
					data : paramData,
					dataType : 'json',
					success : function(data) {
			            if(data.code == 200) {
							$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
					        $("#confirmDialog").dialog("close");
			            } else if(data.code == 500) {
							$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
			            }
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					}
				});  
			}
		} else {
			alert("请确认输入是否正确！");
		}
	};

	// 关闭广播对话框
	function multiClose() {
        $("#confirmDialog").dialog("close");
	};
	
	/* 广播类型选择事件 */
	function onMultiContentTypeSelect(event, ui) {
		if(ui.value == '1') {
			var cusNumber = $("#multiCusNumber").textbox("getValue");
			var audioArray = $.ajax({
				url: "${ctx}/broadcastFile/queryBroadcastFileCombobox.json?cusNumber=" + cusNumber ,
				async: false
			}).responseText;
			$("#multiContent1").autocomplete( {"source":JSON.parse(audioArray)});

			$("#multiContent1").autocomplete({required: true});
			$("#multiContent2").textbox({required: false});
			
			$("#multi_broadcast_play").find("tr[id='multi_tr_content_type_2']").hide();
			$("#multi_broadcast_play").find("tr[id='multi_tr_content_type_1']").show();
		} else {
			$("#multiContent1").autocomplete({required: false});
			$("#multiContent2").textbox({required: true});
			
			$("#multi_broadcast_play").find("tr[id='multi_tr_content_type_1']").hide();
			$("#multi_broadcast_play").find("tr[id='multi_tr_content_type_2']").show();
		}
	};
</script>
