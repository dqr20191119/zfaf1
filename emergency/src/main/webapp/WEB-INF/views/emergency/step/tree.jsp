<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="F-left" style="width: 100%;height:99%;background-color: #EDEEEE;overflow-y: auto; overflow-x: hidden;">
	<!-- 应急预案树 -->
	<cui:tree id="treeId_preplanTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="" simpleDataEnable="true"
		showLine="true" asyncAutoParam="id,name" onClick="nodeClick" rootNode="true" showRootNode="true" >
	</cui:tree>
</div>

<script>
$.parseDone(function() {
	$("#treeId_preplanTree").tree("option", "asyncUrl", "${ctx}/emergency/stepManage/initTree");
	$("#treeId_preplanTree").tree("reload");

	setTimeout(function () {
		var selectedNode = $("#treeId_preplanTree").tree("getNodeByParam", "id", "root");
		$('#treeId_preplanTree').tree("selectNode", selectedNode, true);
		$('#treeId_preplanTree').tree("expandNode", selectedNode, true);

		refreshList("root");

	}, 500);
});

/**
 * 单击指令事件
 * @param event
 * @param id
 * @param node
 */
function nodeClick(event, id, node) {
    refreshList(node.id);
}

/**
 * 加载工作组信息
 */
function refreshList(nodeId) {
    var panel = $("#preplan-layout").layout('panel', 'center');
    panel.panel('refresh', "${ctx}/emergency/stepManage/toList?preplanId=" + nodeId);
}

/**
 * 刷新树
 */
function refreshTree() {
	$("#treeId_preplanTree").tree("reload", "${ctx}/emergency/stepManage/initTree");

	setTimeout(function () {
		var preplanId = $("#formId_step_query").find("#preplanId").textbox("getValue");

		var selectedNode = $("#treeId_preplanTree").tree("getNodeByParam", "id", preplanId);
		$('#treeId_preplanTree').tree("selectNode", selectedNode, true);
		$('#treeId_preplanTree').tree("expandNode", selectedNode, true);

		refreshList(preplanId);
	}, 500);
}
</script>