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
	<cui:form id="formId_broadcast_play" heightStyle="fill">
		<!-- 监狱编号 -->
		<cui:input id="cusNumber" type="hidden"></cui:input>
		<!-- 广播设备编号 -->
		<cui:input id="broadcastId" type="hidden"></cui:input>
		<!-- 广播设备最近的播放记录编号 -->
		<cui:input id="latestRecordId" type="hidden"></cui:input>
		<table class="table" style="width: 98%">
			<tr>
				<th>品牌：</th>
				<td>
					<cui:combobox id="broadcastBrand" componentCls="form-control" data="gb_brandData" readonly="true"></cui:combobox>
				</td>
				<th>状态：</th>
				<td>
					<cui:combobox id="broadcastStatus" componentCls="form-control" data="gb_sttsData" readonly="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>广播类型：</th>
				<td>
					<cui:combobox id="contentType" componentCls="form-control" data="gb_typeData" required="true" onSelect="onContentTypeSelect"></cui:combobox>
				</td>
			</tr>
            <tr id="tr_content_type_1" style="display: none;">
                <th>播放内容：</th>
                <td colspan="s3">
                	<cui:autocomplete id="content1" valueField="value" textField="text" multiple="true" postMode="value"
                	 multiLineLabel="true"  height="150" componentCls="form-control"></cui:autocomplete>
                </td>
            </tr>
            <tr id="tr_content_type_2" style="display: none;">
                <th>播放内容：</th>
                <td colspan="3">
					<cui:textarea id="content2" height="150"  componentCls="form-control"></cui:textarea>
                </td>
            </tr>
			<%-- <tr id="tr_content_type_2" style="display: none;">
				<th>语言选择：</th>
				<td>
					<cui:combobox id="language" componentCls="form-control" data="gb_languageData"></cui:combobox>
				</td>
				<th>语音类型：</th>
				<td>
					<cui:combobox id="voiceType" componentCls="form-control" data="gb_voiceTypeData"></cui:combobox>
				</td>
			</tr> --%>
			<%-- <tr>
				<th>播放模式：</th>
				<td>
					<cui:combobox id="playMode" componentCls="form-control" data="gb_playModeData"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>循环次数：</th>
				<td>
					<cui:input id="cycCount" type="text" componentCls="form-control"></cui:input>
				</td>
				<th>播放时长：</th>
				<td>
					<cui:input id="cycTime" type="text" componentCls="form-control"></cui:input>
				</td>
			</tr> --%>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button id="operateBroadcast" label="开始播放" text="false" onClick="startPlay"></cui:button>
		<cui:button id="stopBroadcast" label="直接停止播放" text="false" onClick="stopPlay"></cui:button>
		<%-- <cui:button id="close" label="关闭窗口" text="false" onClick="close"></cui:button> --%>
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
	
	$.parseDone(function() {
		setTimeout(function () {
			var url = "${ctx}/broadcast/queryBroadcastDtoById.json?id=${broadcastId}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						var info = data.obj;
						$("#formId_broadcast_play").find("#cusNumber").textbox("setValue", info.bbdCusNumber);
                        $("#formId_broadcast_play").find("#broadcastId").textbox("setValue", info.id);
                        $("#formId_broadcast_play").find("#broadcastStatus").combobox("setValue", info.bbdSttsIndc);
                        $("#formId_broadcast_play").find("#broadcastBrand").combobox("setValue", info.bbdBrand);

                        if(info.bbdSttsIndc == '2') {
                			//$("#operateBroadcast").button({label: '停止播放', onClick: 'stopPlay'});
                            $("#formId_broadcast_play").find("#contentType").combobox({required: false});


                			// 将最近的播放记录数据显示
							if(info.bbdLatestRecord) {
								var bbdLatestRecord = info.bbdLatestRecord;
                                $("#formId_broadcast_play").find("#contentType").combobox("setValue", bbdLatestRecord.contentType);

								if(bbdLatestRecord.contentType == '1') {
									$("#formId_broadcast_play").find("#latestRecordId").textbox("setValue", bbdLatestRecord.id);
									$("#formId_broadcast_play").find("#content1").autocomplete("setValue", bbdLatestRecord.content);
									$("#formId_broadcast_play").find("#content1").autocomplete("setText", bbdLatestRecord.contentMappingName);
                                    $("#formId_broadcast_play").find("#content2").autocomplete({required: false});

									// 隐藏文本域
                                    $("#formId_broadcast_play").find("tr[id='tr_content_type_2']").hide();
                                    // 显示选择框
                                    $("#formId_broadcast_play").find("tr[id='tr_content_type_1']").show();
								} else if(bbdLatestRecord.contentType == '2') {
									$("#formId_broadcast_play").find("#content2").textbox("setValue", bbdLatestRecord.content);
                                    $("#formId_broadcast_play").find("#content2").textbox({required: false});

									// 隐藏选择框
                                    $("#formId_broadcast_play").find("tr[id='tr_content_type_1']").hide();
                                    // 显示文本域
                                    $("#formId_broadcast_play").find("tr[id='tr_content_type_2']").show();
								}
							}
                            $("#formId_broadcast_play").find("#contentType").combobox({readonly: true});
                            $("#formId_broadcast_play").find("#content1").autocomplete({disabled: true});
                            $("#formId_broadcast_play").find("#content2").textbox({disabled: true});
                        } else {
                			//$("#operateBroadcast").button({label: '开始播放', onClick: 'startPlay'});
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
			});
		}, 500);
	});
	
	//发送停止播放指令
	function stopPlay() {
		if ($("#formId_broadcast_play").form("valid")) {
			var formObj = $("#formId_broadcast_play");
			// 广播主键
			var broadcastId = formObj.find("#broadcastId").textbox("getValue");
			// 广播播放记录主键
			var broadcastRecordId = formObj.find("#latestRecordId").textbox("getValue");
			// 广播状态
			var broadcastStatus = formObj.find("#broadcastStatus").combobox("getValue");
			/* if(broadcastStatus != '2' ) {
				if(broadcastStatus == '0') {
					$.messageQueue({ message : "广播状态为空闲，无需停止", cls : "warning", iframePanel : true, type : "info" });
				} else if(broadcastStatus == '1') {
					$.messageQueue({ message : "广播状态为离线，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
				} else {
					$.messageQueue({ message : "广播状态未知，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
				}
				return;
			} */

			// 参数封装
			var paramData = {};
			paramData["id"] = broadcastRecordId;
			paramData["broadcastId"] = broadcastId;

			console.log("paramData = " + JSON.stringify(paramData));

			$.ajax({
				type : 'post',
				url : '${ctx}/broadcastRecord/stopPlay.json',
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
		} else {
			alert("请确认输入是否正确！");
		}
	}

	//发送开始播放指令
	function startPlay() {
		if ($("#formId_broadcast_play").form("valid")) {
			var formObj = $("#formId_broadcast_play");
            // 广播主键
			var broadcastId = formObj.find("#broadcastId").textbox("getValue");
			// 广播状态
			var broadcastStatus = formObj.find("#broadcastStatus").combobox("getValue");
            // 播放类型
            var contentType = formObj.find("#contentType").combobox("getValue");
            // 播放内容
            var content = "";
            // 语言
            // var language = "";
            // 语音类型
            // var voiceType = "";
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
            	content = formObj.find("#content1").autocomplete("getValue");
            } else if(contentType == '2') {
            	content = formObj.find("#content2").textbox("getValue");
            	// language = formObj.find("#language").combobox("getValue");
            	// voiceType = formObj.find("#voiceType").combobox("getValue");
            }
            // 播放模式(1:顺序播放; 2: 循环播放; 3:随机播放)
            // var playMode = formObj.find("#playMode").combobox("getValue");
            // 循环播放次数(0:无限次; 其它数字表示循环多少次)
            // var cycCount = formObj.find("#cycCount").textbox("getValue");
            // 循环播放时长(CYC_COUNT=0有效，单位为秒)
            // var cycTime = formObj.find("#cycTime").textbox("getValue");
            
            // 参数封装
            var paramData = {};
            paramData["broadcastId"] = broadcastId;
            paramData["contentType"] = contentType;
            paramData["content"] = content;
			// if(contentType == '2') {
            //     paramData["language"] = language;
            //     paramData["voiceType"] = voiceType;
            // }
            // paramData["playMode"] = playMode;
            // paramData["cycCount"] = cycCount;
            // paramData["cycTime"] = cycTime;
            
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
		} else {
			alert("请确认输入是否正确！");
		}
	}

	// 强制停止播放
	function forceStopPlay() {
		alert("强制停止");
	}
	
	// 关闭广播对话框
	function close() {
        $("#confirmDialog").dialog("close");
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
			
			$("#formId_broadcast_play").find("tr[id='tr_content_type_2']").hide();
			$("#formId_broadcast_play").find("tr[id='tr_content_type_1']").show();
		} else {
			$("#content1").autocomplete({required: false});
			$("#content2").textbox({required: true});
			
			$("#formId_broadcast_play").find("tr[id='tr_content_type_1']").hide();
			$("#formId_broadcast_play").find("tr[id='tr_content_type_2']").show();
		}
	}
</script>
