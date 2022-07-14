<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="F-left" style="width: 100%;height:99%;background-color: #EDEEEE;overflow: scroll ">
	<!-- 风险评估要素树 -->
	<cui:tree id="treeId_fxysTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="" simpleDataEnable="true"
		asyncAutoParam="id,name" onClick="nodeClick" rootNode="true" showRootNode="true" >
	</cui:tree>
</div>

<script>
/**
 * 安全风险研判-风险采集-风险要素树形结构
 */
 var type = '${type}';
$.parseDone(function() {
	$("#treeId_fxysTree").tree("option", "asyncUrl", "${ctx}/aqfxyp/fxcj/fxysTree");
	$("#treeId_fxysTree").tree("reload");
	
	var panel = $('#fxcj-layout').layout('panel', 'center');
	if(1 == type){
		refreshFxx('WWJG_4e80808d683672980168500d840001e3');
	}
	
});

function resetForm(){
	$("#formId_fxys").form("reset");
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
    
    var nodeId = node.id;
    if (nodeId == ROOT_ID) {
        $.message("请选择子节点！");
    } else {
        refreshFxx(nodeId);
    }
    console.info(nodeId);
}

/**
 * 加载风险要素下的风险项信息
 */
function refreshFxx(fxysId) {
    var panel = $("#fxcj-layout").layout('panel', 'center');
    panel.panel('refresh', "${ctx}/aqfxyp/fxcj/list?sjfw=" + fxysId);
}

</script>