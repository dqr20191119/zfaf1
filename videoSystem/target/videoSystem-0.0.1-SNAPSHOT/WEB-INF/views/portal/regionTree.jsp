<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="F-left" style="width: 100%;">
	<!-- 区域信息 -->
	模型区域：
	<cui:tree id="map_region_tree" asyncEnable="true" keepParent="true"  style="height:150px;"
		 asyncType="post" asyncUrl="${ctx}/region/getRegionTree"
		asyncAutoParam="id,name" onClick="als" onDblClick="nodeInfo" rootNode="true" showRootNode="true" >
	</cui:tree>

</div>

<script>
	
</script>