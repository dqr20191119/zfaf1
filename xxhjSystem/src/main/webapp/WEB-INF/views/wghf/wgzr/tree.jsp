<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="F-left" style="width: 298px;background-color: #EDEEEE;overflow: scroll;height:753px ">
	<!-- 北京监狱要素树 -->
	<cui:tree id="treeId_ccodeTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="" simpleDataEnable="true"
		asyncAutoParam="id,name" onClick="nodeClick" rootNode="true" showRootNode="true" >
	</cui:tree>
</div>

<script>
/**
 * 网格化管理-网格划分-网格划分树形结构
 */
$.parseDone(function() {
	$("#treeId_ccodeTree").tree("option", "asyncUrl", "${ctx}/wghf/wgzrfp/ccodeTree");
	$("#treeId_ccodeTree").tree("reload");
	
	var panel = $('#ccode-layout').layout('panel', 'center');
});

function resetForm(){
	$("#formId_ccode").form("reset");
} 

/**
 * 单击风险要素事件
 * @param event
 * @param id
 * @param node
 */
 function nodeClick(event, id, node) {
	    //根节点的ID
	    var ROOT_ID = "root";
	    var wgid = node.code;
	    var nodeId = node.id;
	    if (nodeId == ROOT_ID) {
	        $.message("请选择子节点！");
	    } else {
	        refreshFxx(wgid);
	    }
	    console.info(nodeId);
	}

/**
 * 加载风险要素下的风险项信息
 */
function refreshFxx(wgid) {
    var panel = $("#ccode-layout").layout('panel', 'center');
    panel.panel('refresh', "${ctx}/wghf/wgzrfp/list/?wgid=" + wgid);
}

</script>