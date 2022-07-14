<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- coral4 css start  -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/moxing.css" />
<!-- coral4 css  end  -->

<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
<!-- app css define end -->

<!-- coral4 js start -->
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/bj-cui/cui/cui.js"></script>
<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js"></script>
<!-- coral4 js end -->

<!-- app js define start  -->
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<!-- app js define  end  -->

<script src="${ctx}/static/system/jsConst.js"></script>
<!-- add by tao callbackfunction 回调函数移至 callback.js -->
<script src="${ctx}/static/js/callback/callback.js"></script>

<script>
	jsConst.basePath = "${ctx}/";
</script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/static/module/video/js/videoPlanTimer.js"></script>
<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
<script src="${ctx}/static/js/map/prisonmap.js"></script>
<!-- End add by linhe 2018-01-09 for request ajax and 3d map -->

<!-- 4g执法 start-->
<script src="${ctx}/static/js/sgzf/base64.js"></script>
<script src="${ctx}/static/js/sgzf/es6-promise.min.js"></script>
<script src="${ctx}/static/js/sgzf/stomp.js"></script>
<script src="${ctx}/static/js/sgzf/tonmx_lib.min.js"></script>
<div style="height: 100%; margin: 0px 10px;">
	<%-- <cui:form id="formId_wdgz_query">
		<table class="table">
			<tr><th>名称：</th>
				<td><cui:input name="name"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form> --%>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_wdgz" data="toolbar_wdgz"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_wdgz" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/wdgz/searchData" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="ID" hidden="true">ID</cui:gridCol>
			<cui:gridCol name="BH" align="center">编号</cui:gridCol>
			<cui:gridCol name="XM"  align="center" formatter="setXmLink" >姓名</cui:gridCol>
			<cui:gridCol name="XB"  align="center" sortable="true" formatter="convertCode" revertCode="true"  formatoptions="{
			'data':commbobox_xb
			}">性别</cui:gridCol>
			<cui:gridCol name="CS_RQ"  align="center" >出生日期</cui:gridCol>
			<cui:gridCol name="JQ_ID" align="center">监区</cui:gridCol>
			<cui:gridCol name="RJ_RQ"  align="center">入监日期</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_wdgz" />
	</cui:grid>
	
	
</div>

<script>
var commbobox_xb=[{'value':'1','text':'男'},{'value':'2','text':'女'}];

	$.parseDone(function() {
		
	});
	var toolbar_wwjgwh = [ ];
	function setXmLink(celValue , opts , rowdat, _act){
		return "<a href='javascript:void(0)' onclick='openZfDk(\""+rowdat.ID+"\")'><font color=blue>" + celValue + "</font></a>"; 
	}
	function openZfDk(id){
		var url = "${ctx}/wdgz/ymzs?jbxxId="+id;
		window.open(url, "_blank");
	}
</script>