<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>

<li>
		<a href="javascript:void();">
			信息汇聚
			<span class="h-arraw">
				<b class="caret"></b>
			</span>
		</a>
		<iframe class="main-iframe"></iframe>
	<ul>
		<li id="pjrzb">
			<a href="javascript:void(0);">
				今日值班
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul>
				<li id="xxhj_lbgl" onclick="openXxhjDialog(event, 'lbgl')">
					<a href="javascript:void(0);">类别管理</a>
				</li>
				 
				<li id="xxhj_gwgl" onclick="openXxhjDialog(event, 'gwgl')">
					<a href="javascript:void(0);">岗位管理</a>
				</li>
				
				<li id="xxhj_bcgl" onclick="openXxhjDialog(event, 'bcgl')">
					<a href="javascript:void(0);">班次管理</a>
				</li>
				
				<li id="xxhj_mbsz" onclick="openXxhjDialog(event, 'mbsz')">
					<a href="javascript:void(0);">模板设置</a>
				</li>
				 
				<li id="xxhj_zbbp" onclick="openXxhjDialog(event, 'zbbp')">
					<a href="javascript:void(0);">值班编排</a>
				</li>
				 
				<li id="xxhj_zbcx" onclick="openXxhjDialog(event, 'zbcx')">
					<a href="javascript:void(0);">值班查询</a>
				</li>
				 
				<li id="xxhj_zbfx" onclick="openXxhjDialog(event, 'zbfx')">
					<a href="javascript:void(0);">值班分析</a>
				</li>
			</ul>
		</li>

		<%--罪犯数据上报暂时不设置权限--%>
		<li id="zfsjsb">
				<a href="javascript:void(0);">
					监区数据上报
					<b class="leftArrow"></b>
				</a>
				<iframe class="sub-iframe"></iframe>
			<ul>
					<li id="xxhj_sblxsz" onclick="openXxhjDialog(event,'sblxsz')">
						<a href="javascript:void(0);">上报数据类型设置</a>
					</li>
					<li id="xxhj_sjsb" onclick="openXxhjDialog(event,'sjsb')">
						<a href="javascript:void(0);">数据上报</a>
					</li>
					<li id="xxhj_sjhz" onclick="openXxhjDialog(event,'sjhz')">
						<a href="javascript:void(0);">数据汇总</a>
					</li>
			</ul>
		</li>

			<li id="xxhj_cgsgxx" onclick="openXxhjDialog(event,'cgsgxx')">
				<a href="javascript:void(0);">出工/收工信息</a>
			</li>

			<li id="xxhj_mjczdj" onclick="openXxhjDialog(event,'mjczdj')">
				<a href="javascript:void(0);">门禁操作登记</a>
			</li>

		<%
			if (AuthSystemFacade.whatLevelForLoginUser() == 1) {
		%>
			<li id="pzfjbxx" onclick="toXxhjDisplay('pzfjbxx')">
				<a href="javascript:void(0);">罪犯基本信息</a>
			</li>
			<li id="pwfsb" onclick="toXxhjDisplay('pwfsb')">
				<a href="javascript:void(0);">物防设备</a>
			</li>
			<li id="pjfsb" onclick="toXxhjDisplay('pjfsb')">
				<a href="javascript:void(0);">技防设备</a>
			</li>
		<%
			} else if (AuthSystemFacade.whatLevelForLoginUser() == 2) {
		%>

			<li id="xxhj_jndt" onclick="openXxhjDialog(event,'jndt')">
				<a href="javascript:void(0);">监内零散罪犯</a>
			</li>
 
			<li id="zfjbxx" onclick="toXxhjDisplay('zfjbxx')">
				<a href="javascript:void(0);">罪犯基本信息</a>
			</li>
			<li id="wfsb" onclick="toXxhjDisplay('wfsb')">
				<a href="javascript:void(0);">物防设备</a>
			</li>
			<li id="jfsb" onclick="toXxhjDisplay('jfsb')">
				<a href="javascript:void(0);">技防设备</a>
			</li>
		<%
			} else {
		%>
			<li id="xxhj_jndt" onclick="openXxhjDialog(event,'jndt')">
				<a href="javascript:void(0);">监内零散罪犯</a>
			</li>
			<li id="jqzfjbxx" onclick="toXxhjDisplay('jqzfjbxx')">
				<a href="javascript:void(0);">监区罪犯基本信息</a>
			</li>
		<%
			}
		%>

			<li id="xxhj_jhrc" onclick="openXxhjDialog(event,'jhrc')">
				<a href="javascript:void(0);">计划日程</a>
			</li>
		<li id="xxhj_zfPhoto" onclick="openXxhjDialog(event, 'zfPhoto')">
			<a href="javascript: void(0)">下载罪犯照片</a>
		</li>
		<li>
			<a href="javascript:void(0);">五维维护</a>
			<b class="leftArrow"></b>
			<iframe class="sub-iframe"></iframe>
            <ul>
				<li id="xxhj_wwjgwh" onclick="openXxhjDialog(event,'wwjgwh')">
					<a href="#">五维架构维护</a>
				</li>
				<li id="xxhj_fxdjwh" onclick="openXxhjDialog(event,'fxdjwh')">
					<a href="#">风险等级维护</a>
				</li>
				<li id="xxhj_qzdjwh" onclick="openXxhjDialog(event,'qzdjwh')">
					<a href="#">权重等级维护</a>
				</li>
				<li id="xxhj_sjlywh" onclick="openXxhjDialog(event,'sjlywh')">
					<a href="#">数据来源维护</a>
				</li>
				<li id="xxhj_sjfwgl" onclick="openXxhjDialog(event,'sjfwgl')">
					<a href="#">数据范围管理</a>
				</li>
				<li id="xxhj_fxdgl" onclick="openXxhjDialog(event,'fxdgl')">
					<a href="#">风险点管理</a>
				</li>
				<li id="xxhj_pgtjgl" onclick="openXxhjDialog(event,'pgtjgl')">
					<a href="#">评估条件管理</a>
				</li>
            </ul>
        </li>

	</ul>
</li>

<script>
	function toXxhjDisplay(name) {

		var panel = $("#layout1").layout("panel", "east");

		if (name == "pjnmj") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/pjnmj");
		} else if (name == "jnmj") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/jnmj");
		} else if (name == "jqjnmj") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jnmj/jqjnmj");
		} else if (name == "pjryf") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jryf/pjryf");
		} else if (name == "jryf") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jryf/jryf");
		} else if (name == "pwfsb") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/wfsb/pwfsb");
		} else if (name == "wfsb") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/wfsb/wfsb");
		} else if (name == "pjfsb") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jfsb/pjfsb");
		} else if (name == "jfsb") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/jfsb/jfsb");
		} else if (name == "pzfjbxx") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/pzfjbxx");
		} else if (name == "zfjbxx") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/zfjbxx");
		} else if (name == "jqzfjbxx") {
			panel.panel("refresh", jsConst.basePath + "/xxhj/zfjbxx/jqzfjbxx");
		}
	}

	function openXxhjDialog(event, name) {
		var event = window.event || event;
		//event.stopPropagation();
		if (event && event.stopPropagation) {
			event.stopPropagation();
		} else {
			window.event.cancelBubble = true;
		}
		//event.preventDefault();
		var url = "";
		if (name == 'pjcjl') {
			url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord';
		} else if (name == 'jcjl') {
			url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber=' + jsConst.CUS_NUMBER;
		} else if (name == 'jqjcjl') {
			url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber=' + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
		} else if (name == 'jndt') {
			url = jsConst.basePath + '/xxhj/jndt/toIndex';
		} else if (name == 'gwgl') {
			url = jsConst.basePath + '/zbgl/gwgl/toIndex';
		} else if (name == 'bcgl') {
			url = jsConst.basePath + '/zbgl/bcgl/toIndex';
		} else if (name == 'lbgl') {
			url = jsConst.basePath + '/zbgl/lbgl/toIndex';
		} else if (name == 'mbsz') {
			url = jsConst.basePath + '/zbgl/mbsz/toIndex';
		} else if (name == 'zbbp') {
			url = jsConst.basePath + '/zbgl/zbbp/toIndex';
		} else if (name == 'zbcx') {
			url = jsConst.basePath + '/zbgl/zbcx/toIndex';
		} else if (name == 'zbfx') {
			url = jsConst.basePath + '/zbgl/zbfx/toIndex';
		} else if (name == 'mjczdj') {
			url = jsConst.basePath + '/xxhj/mjczdj/openDialog';
		} else if (name == 'jhrc') {
			url = jsConst.basePath + '/xxhj/jhrc/toIndex';
        } else if (name == 'cgsgxx') {
            url = jsConst.basePath + '/xxhj/cgsgxx/toIndex';
		} else if (name == 'sblxsz') {
            url = jsConst.basePath + '/xxhj/zfsjsb/sblxsz/toIndex?cusNumber=' + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        } else if (name == 'sjsb') {
            url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toIndex?cusNumber=' + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        } else if (name == 'sjhz') {
            url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toSjhz?cusNumber=' + jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
        } else if (name == 'zfPhoto') {
			url = jsConst.basePath+'/zfxx/zfJbxx/openDialog';
			w = 1000;
			h = 600;
		} else if (name == 'wwjgwh') {
            url = jsConst.basePath + '/wwjg/wwjgwh/toIndex';
		} else if (name == 'sjlywh') {
            url = jsConst.basePath + '/wwjg/sjlywh/toIndex';
		} else if (name == 'fxdjwh') {
            url = jsConst.basePath + '/wwjg/fxdjwh/toIndex';
		} else if (name == 'qzdjwh') {
            url = jsConst.basePath + '/wwjg/qzdjwh/toIndex';
		} else if (name == 'sjfwgl') {
            url = jsConst.basePath + '/wwjg/sjfwgl/toIndex';
		} else if (name == 'fxdgl') {
            url = jsConst.basePath + '/wwjg/fxdgl/toIndex';
		} else if (name == 'pgtjgl') {
            url = jsConst.basePath + '/wwjg/pgtjgl/toIndex';
		}
		$('#dialog').html("");
		// $('#dialog').dialog("destroy");
		$('#dialog').dialog({
            width : 1000,
			height : 800,
			title : $("#xxhj_" + name + " a").text(),
			url : url
		});
		$("#dialog").dialog("open");
	}
</script>
