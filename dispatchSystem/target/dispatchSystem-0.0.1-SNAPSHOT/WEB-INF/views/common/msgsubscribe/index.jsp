<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%">
	<table class="table">
		<tr>
		 	<td colspan="3" style="text-align: left; padding-left: 30px;">
		 		消息列表：
		 		<cui:combobox id="comboboxId_msg" width="300" multiple="true" data="combobox_common_msgList" onChange="initSelectedMsg"></cui:combobox>
		 	</td>	
		</tr> 
		<tr>
		 	<td style="text-align: center;">
		 		<div id="divId_selectedMsg" style="margin-left: 20px;">
		 			已订阅消息
		 			<ul style="font-size: 12px; width: 250px; height: 450px; border: 1px solid #4692f0; overflow-x: hidden; overflow-y: auto;"></ul>
		 		</div>
		 	</td>
		 	<td style="text-align: center;">
		 		<div id="divId_selectedUser" style="margin-left: 20px;">
		 			已订阅用户
		 			<cui:input type="hidden" id="input_selectedMsgId" />
		 			<ul style="width: 250px; height: 450px; border: 1px solid #4692f0; overflow-x: hidden; overflow-y: auto;"></ul>
		 		<div>
		 	</td>	
		 	<td style="text-align: center;">
		 		<div style="margin-left: 20px;">
		 			用户列表
		 			<ul style="width: 250px; height: 450px; border: 1px solid #4692f0; overflow-x: hidden; overflow-y: auto;">
						<cui:tree id="treeId_user" asyncEnable="true" asyncAutoParam="id,name" asyncUrl="${ctx}/common/authsystem/findDeptPoliceForCombotree" onDblClick="selectUser"></cui:tree>		 				
		 			</ul>
		 		<div>
		 	</td>									 	
		</tr> 
	</table>	
	<div class="dialog-buttons">
		<cui:button componentCls="btn-primary" label="保存" onClick="saveOrUpdate"></cui:button>
	</div> 
</div>
		
<script>

	var combobox_common_msgList = <%=CodeFacade.loadCode2Json("4.20.37")%>;
	var selectedData = {};
	
	$.parseDone(function() {
		
		loadMsgsubscribe();
	});
	
	function loadMsgsubscribe() {
		
		$.ajax({
			type : 'post',
			url : '${ctx}/common/msgsubscribe/searchAllData',
			dataType : 'json',
			success : function(data) {
				
				var selectedMsgIds = "";
				for(var i = 0; i < data.length; i++) {
					selectedMsgIds += data[i].msdMsgIdnty + ",";
					
					selectedData[data[i].msdMsgIdnty] = [];
					var msgUserIdArr = data[i].msdUserId.split(",");
					var msdUserNameArr = data[i].msdUserName.split(",");
					for(var j = 0; j < msgUserIdArr.length; j++) {
						var item = {};
						item.value = msgUserIdArr[j];
						item.text = msdUserNameArr[j];
						selectedData[data[i].msdMsgIdnty].push(item);
					}					
				}
				
				$("#comboboxId_msg").combobox("setValue", selectedMsgIds.substring(0, selectedMsgIds.length - 1));	
				initSelectedMsg();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function initSelectedMsg(event, ui) {
		
		var selectedMsgIds = $("#comboboxId_msg").combobox("getValues");
		var selectedMsgText = $("#comboboxId_msg").combobox("getText");
		
		var msgUl = $("#divId_selectedMsg ul");	
		msgUl.empty();
		
		if(selectedMsgIds && selectedMsgIds != "") {			
			var selectedMsgIdArr = selectedMsgIds.toString().split(",");
			var selectedMsgTextArr = selectedMsgText.toString().split(",");
			
			var selectedDataTemp = selectedData;
			selectedData = {};
			
			for(var i = 0; i < selectedMsgIdArr.length; i++) {				
				var msgLi = $("<li style='height: 25px; cursor: pointer;'>" + selectedMsgTextArr[i] + "</li>");
				
				msgLi.attr({
					id : selectedMsgIdArr[i]
				}).unbind('click').bind('click', function() {
					
					$("#input_selectedMsgId").val($(this).attr("id"));
					$("#divId_selectedUser ul").empty();
					$("#divId_selectedMsg ul").find("li").css("color", "black");
					$(this).css("color", "red");
					
					if(selectedData[$(this).attr("id")]) {
						initUser(selectedData[$(this).attr("id")]);
					}
		 		});
				
				msgLi.appendTo(msgUl);
				
				selectedData[selectedMsgIdArr[i]] = [];
				if(selectedDataTemp[selectedMsgIdArr[i]]) {					
					selectedData[selectedMsgIdArr[i]] = selectedDataTemp[selectedMsgIdArr[i]];
				}
			} 
		}	
	}	
	
	function selectUser(event, id, node) {
		
		if(node.isParent) {
			return;
		}
		
		var selectedMsgId = $("#input_selectedMsgId").val();
		if(selectedMsgId && selectedMsgId != "") {
			
			// 校验人员是否重复
			for(var i = 0; i < selectedData[selectedMsgId].length; i++) {
				if(selectedData[selectedMsgId][i].value == node.id) {
					$.message({message:"人员已选择，请重新选择！", cls:"waring"});
					return;
				}
			}
			
			var data = [];
			data[0] = {};
			data[0].value = node.id;
			data[0].text = node.name;
			initUser(data);
 
			selectedData[selectedMsgId].push(data[0]);
		} else {
			$.message({message:"请先选择已订阅消息！", cls:"waring"});
		}
	}
	
	function initUser(data) {
		
		var userUl = $("#divId_selectedUser ul");	
		for(var i = 0; i < data.length; i++) {						 
			var userLi = $("<li style='height: 25px; cursor: pointer;'>" + data[i].text + "</li>");
			
			userLi.attr({
				id : data[i].value
			}).unbind('dblclick').bind('dblclick', function() {
				
					$(this).remove();
					
					var selectedMsgId = $("#input_selectedMsgId").val();
					for(var i = 0; i < selectedData[selectedMsgId].length; i++) {
						if(selectedData[selectedMsgId][i].value == $(this).attr("id")) {
							selectedData[selectedMsgId].splice(i, 1);
							break;
						}
					}
	 		});			
			userLi.appendTo(userUl);
		}		
	}
	
	function saveOrUpdate() {
		
		if(!(JSON.stringify(selectedData) == "{}")) {	
			var data = {};
			var pos = 0;
			for(var prop in selectedData) {
				if(selectedData[prop].length > 0) {
					for(var i = 0; i < selectedData[prop].length; i++) {
						data["msgsubscribeList[" + pos + "].msdMsgIdnty"] = prop;
						data["msgsubscribeList[" + pos + "].msdUserId"] = selectedData[prop][i].value;
						pos++;
					}					
				} else {
					$.message({message:"存在消息没有订阅用户！", cls:"waring"});
					return;
				}
			}
						
			$.loading({text:"正在处理中，请稍后..."});
			
			$.ajax({
				type : 'post',
				url : '${ctx}/common/msgsubscribe/saveOrUpdate',
				data: data,
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					
					if(data.code == "1") {
						$.message({message:"操作成功！", cls:"success"});						
						$("#dialog").dialog("close");							
					} else {
						$.message({message:"操作失败！", cls:"error"});
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"});
				}
			});
		} else {
			$.message({message:"请选择消息！", cls:"waring"});
		}
	}
</script>