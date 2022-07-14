<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<li>
	<a href="javascript:void(0);">
		风险评估
	</a>
	<iframe class="main-iframe"></iframe>
	<ul>
		<li id="fxpg_fxcj" onclick="openFxpgDialog(event, 'fxcj')">
			<a href="javascript:void(0);">风险采集</a>
		</li>
		<!-- 安全风险评估五维架构 -->
		<li>
			<a href="javascript:void(0);">评估要素</a>
			<b class="leftArrow"></b>
			<iframe class="sub-iframe"></iframe>
            <ul>
				<li id="fxpg_wwjgwh" onclick="openFxpgDialog(event,'wwjgwh')">
					<a href="#">五维架构维护</a>
				</li>
				<li id="fxpg_fxdjwh" onclick="openFxpgDialog(event,'fxdjwh')">
					<a href="#">风险等级维护</a>
				</li>
				<li id="fxpg_qzdjwh" onclick="openFxpgDialog(event,'qzdjwh')">
					<a href="#">权重等级维护</a>
				</li>
				<li id="fxpg_sjlywh" onclick="openFxpgDialog(event,'sjlywh')">
					<a href="#">数据来源维护</a>
				</li>
				<li id="fxpg_sjfwgl" onclick="openFxpgDialog(event,'sjfwgl')">
					<a href="#">数据范围管理</a>
				</li>
				<li id="fxpg_fxdgl" onclick="openFxpgDialog(event,'fxdgl')">
					<a href="#">风险点管理</a>
				</li>
				<li id="fxpg_pgtjgl" onclick="openFxpgDialog(event,'pgtjgl')">
					<a href="#">评估条件管理</a>
				</li>
				<li id="fxpg_fxgkgl" onclick="openFxpgDialog(event,'fxgkgl')">
					<a href="#">风险管控管理</a>
				</li>
            </ul>
        </li>
	</ul>
</li>

<script>
/**
 * Description: 打开风险评估对话框
 */
function openFxpgDialog(event, name) {
	var event = window.event || event;
	//event.stopPropagation();
	if (event && event.stopPropagation) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'fxcj') {
		url = jsConst.basePath + '/aqfxyp/fxcj/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'wwjgwh') {
		url = jsConst.basePath + '/wwjg/wwjgwh/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'sjlywh') {
		url = jsConst.basePath + '/wwjg/sjlywh/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'fxdjwh') {
		url = jsConst.basePath + '/wwjg/fxdjwh/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'qzdjwh') {
		url = jsConst.basePath + '/wwjg/qzdjwh/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'sjfwgl') {
		url = jsConst.basePath + '/wwjg/sjfwgl/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'fxdgl') {
		url = jsConst.basePath + '/wwjg/fxdgl/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'pgtjgl') {
		url = jsConst.basePath + '/wwjg/pgtjgl/toIndex';
		w = 1000;
		h = 800;
	}else if (name == 'fxgkgl') {
		url = jsConst.basePath + '/wwjg/fxgkgl/toIndex';
		w = 1000;
		h = 800;
	}
	
	
	$('#dialog').html("");
	//$('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#fxpg_" + name + " a").text(),
		url : url
	});
	$("#dialog").dialog("open");
}
</script>
