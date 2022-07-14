<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%-- <%@ include file="/WEB-INF/layouts/base.jsp"%> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="width: 100%;">
	<ul style="margin:auto 50px;">
		<li style="margin:5px auto;">
			<label>区域名称：</label>
			<cui:combotree id="searchRegion" url="${ctx}/common/areadevice/findForCombotree.json?cusNumber=3203&deviceType=0"/>
		</li>
		<li style="margin:5px auto;">
			<label>模型名称：</label>
			<cui:input id="searchModelName" value="" />
		</li>
		<li>
			<cui:button label="确认" onClick="modelSearch.query" width="55" ></cui:button>
			<cui:button label="取消" onClick="modelSearch.cancel" width="55"></cui:button>
		</li>
	</ul>
</div>
<script>
	var modelSearch;
	$(function(){
		modelSearch = {
			//根据条件查询
			cusNumber:'3203',
			query:function(){
				modelList.$grid.grid("reload","${ctx}/model/findByPage?obj={'minCusNumber':'"+modelSearch.cusNumber+"','minAreaId':'"+$('#searchRegion').combotree('getValue')+"','minModelName':'"+$('#searchModelName').textbox('getValue')+"'}");
				$("#searchDialog").dialog('close');
			},
			cancel:function(){
				$("#searchDialog").dialog('close');
			}
		}
	});
</script>
