
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="menu-m">
	<ul class="sjdw">
		<li class="sjdw-main">
			<a href="javascript:void(0);">
				
				<span class="h-arraw sjdw-arrow">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe" style="visibility: hidden;"></iframe>
			<!-- Begin modified by linhe 2018-1-9 -->
			<ul id="viewPositionMenu">
				<!-- <li> <a href="javascript:alert();centerDisplay('provMap');">1111111ces<b class="leftArrow"></b></a>
						<iframe class="sub-iframe"></iframe>
						<ul>
							<li onclick="centerDisplay('provMap')"><a href="javascript:void(0);">省局</a></li>
							<li onclick="centerDisplay('prisMap')"><a href="javascript:void(0);">监狱</a></li>
						</ul>
					</li> -->
			</ul>
			<!-- End modified by linhe 2018-1-9 -->
		</li>
	</ul>

	<ul id="menu">
		<li id="nav-jzhgl">
			<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
				精准化管理
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			<ul>
				<li onclick="openMenuDialog(event, 'jzhglZntj')">
					<a href="javascript: void(0)">智能统计</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglWghf')">
					<a href="javascript: void(0)">网格划分</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglZbdw')">
					<a href="javascript: void(0)">坐标定位</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglWpgl')">
					<a href="javascript: void(0)">物品管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglSwdb')">
					<a href="javascript: void(0)">事务督办</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglZhpj')">
					<a href="javascript: void(0)">综合评价</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglZjbg')">
					<a href="javascript: void(0)">总结报告</a>
				</li>
				<li onclick="openMenuDialog(event, 'jzhglWttjhz')">
					<a href="javascript: void(0)">问题统计汇总</a>
				</li>
			</ul>
		</li>
		
		<li id="nav-qygk">
			<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
				区域管控
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			<ul>
				<li onclick="openMenuDialog(event, 'qygkJsrldm')">
					<a href="javascript: void(0)">监室人脸点名</a>
				</li>
				<li onclick="openMenuDialog(event, 'qygkRltzjs')">
					<a href="javascript: void(0)">人脸特征检索</a>
				</li>
				<li onclick="openMenuDialog(event, 'qygkZnxwfx')">
					<a href="javascript: void(0)">智能行为分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'qygkRltj')">
					<a href="javascript: void(0)">人力统计</a>
				</li>
			</ul>
		</li>
		
		<li id="nav-fqznyp">
			<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
				犯情智能研判
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			<ul>
				<li onclick="openMenuDialog(event, 'fqznypZnyyzxxt')">
					<a href="javascript: void(0)">智能语音转写系统</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypFzgzyy')">
					<a href="javascript: void(0)">辅助改造应用</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypThjkgl')">
					<a href="javascript: void(0)">通话监控管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypThxxzcgl')">
					<a href="javascript: void(0)">通话信息侦查管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypZfmgcfx')">
					<a href="javascript: void(0)">罪犯敏感词分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypZfrcfx')">
					<a href="javascript: void(0)">罪犯热词分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypRdzs')">
					<a href="javascript: void(0)">热度指数</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypFqglfx')">
					<a href="javascript: void(0)">犯情关联分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypZdrqhx')">
					<a href="javascript: void(0)">重点人群画像</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypGjcss')">
					<a href="javascript: void(0)">关键词搜索</a>
				</li>
				<li onclick="openMenuDialog(event, 'fqznypDwtj')">
					<a href="javascript: void(0)">多维统计</a>
				</li>
			</ul>
		</li>
		
		<li id="nav-aqfxgk">
			<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
				安全风险管控
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			<ul>
				<li onclick="openMenuDialog(event, 'aqfxgkMbgl')">
					<a href="javascript: void(0)">目标管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkPgfjzbgl')">
					<a href="javascript: void(0)">评估分级指标管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkRyglfxfx')">
					<a href="javascript: void(0)">人员管理风险分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkDyglfxfx')">
					<a href="javascript: void(0)">地域管理风险分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkSxglfxfx')">
					<a href="javascript: void(0)">事项管理风险分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkWlglfxfx')">
					<a href="javascript: void(0)">物力管理风险分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkQbglfxfx')">
					<a href="javascript: void(0)">情报管理风险分析</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkFxyjczgl')">
					<a href="javascript: void(0)">风险预警处置管理</a>
				</li>
				<li onclick="openMenuDialog(event, 'aqfxgkFxpgff')">
					<a href="javascript: void(0)">分析评估方法</a>
				</li>
			</ul>
		</li>
		
		<jsp:include page="../include/aqfkInclude.jsp"></jsp:include>
		
		<li id="nav-yjgl">
			<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
				应急管理
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			<ul>
				<li onclick="openMenuDialog(event, 'yjglYjdd')">
					<a href="javascript: void(0)">应急调度</a>
				</li>
				<!-- <li onclick="openMenuDialog(event, 'yjglYjtj')">
					<a href="javascript: void(0)">应急统计</a>
				</li> -->
			</ul>
		</li>
		
		<jsp:include page="../include/xtglInclude.jsp"></jsp:include>
	</ul>
</div>

<script>

	function openMenuDialog(event, name) {
			
		var event = window.event || event;
		if(event && event.stopPropagation ) {
			  event.stopPropagation();
	   	} else {
	          window.event.cancelBubble = true;
	   	}
		
		//event.preventDefault();
		var url = "";
		if (name == "gzzgl") {
			url = jsConst.basePath+'/yjct/gzzgl/index';
		} else if (name == "wzgl") {
			url = jsConst.basePath+'/yjct/wzgl/index';
		}
	
		// $('#dialog').dialog("destroy");
	    $('#dialog').html("");
		$('#dialog').dialog({
			width : 1000,
			height : 600,
			title : $("#" + name + " a").text(),
			url : url
		});
		$("#dialog").dialog("open");
		return;
	}
</script>