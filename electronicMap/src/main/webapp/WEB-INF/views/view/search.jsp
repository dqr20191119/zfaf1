<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%-- <%@ include file="/WEB-INF/layouts/base.jsp"%> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="width: 100%;">
	<cui:form id="searchViewForm" method="post" action="" heightStyle="fill" style="padding-top:0px;">
		<ul style="margin:auto 10px;">
			<li style="margin:5px auto;">
				<label>区域名称：</label>
				<cui:combotree id="searchRegion" url="${ctx}/common/areadevice/findForCombotree.json?cusNumber=''" name="areaId" onExpand="view3d.cls">
				</cui:combotree>
			</li>
			<li style="margin:5px auto;">
				<label>视角类型：</label>
				<cui:combobox id="searchType" name="viewType" data="view3d.viewTypeData" onChange="view3d.viewTypeChange"/>
			</li>
			<li style="margin:5px auto;">
				<label>视角属性：</label>
				<cui:combobox id="searchType" name="viewStts" data="view3d.sttsData"/>
			</li>
			<li style="margin:5px auto;">
				<label>视角名称：</label>
				<cui:input id="viewName2" name="viewName" value="" />
			</li>
			<li style="margin-top:10px;margin-left: 80px;">
				<cui:button label="确认" onClick="viewSearch.query" width="55" cls="cyanbtn" style="margin-right:20px;" ></cui:button>
				<cui:button label="取消" onClick="viewSearch.cancel" width="55" ></cui:button>
			</li>
		</ul>
	</cui:form>
</div>
<script>
$(function(){
	viewSearch = {
		$form:$("#searchViewForm"),
		init:function(){
			$.parseDone(function(){
				//初始化区域下拉树
				$("#searchRegion").combotree("tree").tree("reload",view3d.regionUrl);
			})
		},
		//根据条件查询
		query:function(){
			var formData = viewSearch.$form.form("formData");
			formData.cusNumber = jsConst.CUS_NUMBER;
			//console.log(formData);
			//viewList.$grid.grid("reload","${ctx}/view/findByPage?obj="+JSON.stringify(formData));
			$("#gridView").grid("option","postData",formData);
			$("#gridView").grid("reload",jsConst.basePath+"view/findByPage");
			viewList.$search.dialog('close');
		},
		//取消查询
		cancel:function(){
			viewList.$search.dialog('close');
		}
	}
	viewSearch.init();
});
</script>
