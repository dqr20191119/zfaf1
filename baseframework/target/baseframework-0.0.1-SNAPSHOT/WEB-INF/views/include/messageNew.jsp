<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>


<script type="text/javascript">
window.setTimeout(initMessage, 1000);
var combobox_index_msgType = <%=CodeFacade.loadCode2Json("4.20.37")%>;

// 消息通知框
function initMessage() {
	var url = jsConst.basePath + '/common/message/toMessageIndex';
	messageBox = $.messageBox({
		timeOut:60000,//一分钟
		componentCls:"workmessage",
		message: "",
		url: url,
		width:526,
		height:262,
		title:"消息提醒",
		reLoadOnOpen: true,
		autoDestroy: true,
		onOpen:function(e,ui){
			$("#viewMessageId").unbind("click");
		},
		onClose:function(e,ui){
			messageBox = null;
			$("#viewMessageId").bind("click",initMessage);
		}
	});
}

	/*function initMessage() {
			$.getJSON("${ctx}/common/message/findByMsgType?tt=" + getRandomId(), function(data) {
				var msg = "没有消息提醒工作！";
				var list = data;
				if(list && list.length>0) {
					msg = '<ul>';
					for(var i = 0; i < list.length; i++) {				
						msg += '<li>';
						var content = convertColVal(combobox_index_msgType, list[i].MSG_TYPE) + "，查看";
						msg += '<a title="'+content+'" href="javascript:void(0);" data-href="'+list[i].URL+'" onclick="clickContent(this);">'+common.abbreviate(content,30)+'</a>';
						msg += '<div style="float: right; margin-right: 10px;" ><a data-type="'+list[i].MSG_TYPE+'" onClick="updateRead(this);" style="cursor: pointer; color: red;">X</a></div>';
						msg += '</li>';
					}
					msg += '</ul>';
					
					messageBox = $.messageBox({
						timeOut:60000,//一分钟
						componentCls:"workmessage",
				        message:msg,
				        width:300,
				        height:170,
				        onOpen:function(e,ui){
				        	$("#viewMessageId").unbind("click");
				        },
				        onClose:function(e,ui){
				        	$("#viewMessageId").bind("click",initMessage);
				        	messageBox = null;
				        }
				    }); 
				} else {
					$("#viewMessageId").unbind("click");
					$("#viewMessageId").bind("click",initMessage);
					//$.message({message:msg, cls:"waring"});
					//toastMsg(msg,"danger","custom");
				}
			});
		}*/
		function clickContent(obj) {
			var url = $(obj).attr("data-href");
			if(url != null && url != "" && url != "null" && url != "undefined") {
				$("#dialog").dialog("option", {
					title:"办理窗口",
					width:1000,
					height:600,
					url:url,
					onLoad:function(e,ui){
					},
					onLoadError:function(e,ui){
					}
				});
				$("#dialog").dialog("open");
			}
		}
		
		function updateRead(obj) {
			var msgType = $(obj).attr("data-type");
			$.ajax({
				type : 'post',
				url : '${ctx}/common/message/updateReadByYwId',
				data: {
					msgType: msgType,
					noticeUserId: jsConst.USER_ID
				},
				dataType : 'json',
				success : function(data) {
					if(data.code == "1") {
						$(obj).parent().parent().remove();
					}			
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
</script>
