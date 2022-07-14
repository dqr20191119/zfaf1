<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
<div style="height: 100%;">
	<cui:grid id="gridId_message" fitStyle="fill" multiselect="false" colModel="gridId_message_colModelData" rownumbers="false" rownumName="序号">
		<cui:gridPager gridId="gridId_message"/>
	</cui:grid>
</div>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		// 页面数据加载
		loadPage();
	});

	var gridId_message_colModelData = [{
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		label : "url",
		name : "url",
		hidden : true
	}, {
		label : "createDate",
		name : "createDate",
		hidden : true
	}, {
		name : "content",
		label : "内容",
		align : "left",
		width : 265,
		formatter : "contentFormatter"
	}, {
		label : "操作",
		name : "operate",
		align : "center",
		width : "70",
		formatter : "operateFormatter"
	}];

	/**
	 * 页面数据加载
	 */
	function loadPage() {
		var url = "${ctx}/common/message/queryWithPage";
		var params = {};

		// 是否已读（0:未读; 1:已读）
		var isRead = "0";

		// 初始化查询条件
		if (isRead) {
			params["isRead"] = isRead;
		}

		$('#gridId_message').grid('option', 'postData', params);
		$("#gridId_message").grid("reload", url);
	}

	/**
	 * 内容栏格式化
	 */
	function contentFormatter(cellValue, options, rowObject) {
		var title = "[" + rowObject.createDate + "]" + rowObject.content;
		return "<a href='javascript: void(0);' style='color: #4692f0;' onclick='contentRedirect(\"" + rowObject.url + "\");'>" + title + "</a>";
	}

	/**
	 * 操作栏格式化
	 */
	function operateFormatter(cellValue, options, rowObject) {
		//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
		return "<button class='ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only' onClick='updateRead(\"" + rowObject.id + "\")'>忽略</button>";
	}

	/**
	 * 消息内容点击事件
	 * @param obj
	 */
	function contentRedirect(url) {
		if(url) {
			$("#dialog").dialog("option", {
				title:"办理窗口",
				width:1200,
				height:800,
				url:url,
				onLoad:function(e,ui){
				},
				onLoadError:function(e,ui){
				}
			});
			$("#dialog").dialog("open");
		}
	}

    /**
     * 更新消息读取状态
     */
	function updateRead(messageId) {
		$.ajax({
			type : 'post',
			url : '${ctx}/common/message/updateRead',
			data: {
				isRead: "1",
				ids: messageId
			},
			dataType : 'json',
			success : function(data) {
				if(data.success) {
					loadPage();
				} else {
					$.message({ iframePanel:true, message : data.msg, cls : "warning" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({ message:textStatus, title:"信息提示", iframePanel:true });
			}
		});
	}
</script>
