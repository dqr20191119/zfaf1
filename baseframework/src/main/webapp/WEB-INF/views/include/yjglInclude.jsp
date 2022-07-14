<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<sec:authorize url="/zhdd/sy/zhdd/yjdd">
<li id="yjdd_li">
	<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
		应急调度
	</a>
	<iframe class="main-iframe"></iframe>
	<ul>
		<!-- 【应急资源-菜单】 start -->
    <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjzy">
		<li id="resource">
			<a href="javascript: void(0)">
				应急资源
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul>
                <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjzy/wzgl">
				<li id="yjgl_yjzy_wzgl" onclick="openYjglDialog(event, 'yjzy_wzgl')">
					<a href="javascript: void(0)">物资管理</a>
				</li>
                </sec:authorize>
                <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjzy/zjgl">
				<li id="yjgl_yjzy_zjgl" onclick="openYjglDialog(event, 'yjzy_zjgl')">
					<a href="javascript: void(0)">专家管理</a>
				</li>
                </sec:authorize>
                <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjzy/fggl">
				<li id="yjgl_yjzy_flfg" onclick="openYjglDialog(event, 'yjzy_flfg')">
					<a href="javascript: void(0)">法规管理</a>
				</li>
                </sec:authorize>
			</ul>
		</li>
    </sec:authorize>
		<!-- 【应急资源-菜单】 end -->

		<!-- 【应急预案-菜单】 start -->
		<%--<li id="preplan">
			<a href="javascript: void(0)">
				应急预案
				<b class="leftArrow"></b>
			</a>
			<iframe class="sub-iframe"></iframe>
			<ul>
				<li id="yjgl_yjya_yagl" onclick="openYjglDialog(event, 'yjya_yagl')">
					<a href="javascript: void(0)">预案管理</a>
				</li>
				<li id="yjgl_yjya_tdgl" onclick="openYjglDialog(event, 'yjya_tdgl')">
					<a href="javascript: void(0)">梯队管理</a>
				</li>
				<li id="yjgl_yjya_czbzgl" onclick="openYjglDialog(event, 'yjya_czbzgl')">
					<a href="javascript: void(0)">处置步骤管理</a>
				</li>
			</ul>
		</li>--%>
		<!-- 【应急预案-菜单】 end -->

		<!-- 【应急处置-菜单】 start -->
        <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjcz">
		<li id="yjgl_yjcz" onclick="openYjglDialog(event, 'yjcz')">
			<a href="javascript: void(0);">应急处置</a>
		</li>
        </sec:authorize>
		<!-- 【应急处置-菜单】 end -->

		<!-- 【应急统计-菜单】 start -->
        <sec:authorize url="/zhdd/sy/zhdd/yjdd/yjtj">
		<li id="yjgl_yjtj" onclick="openYjglDialog(event, 'yjtj')">
			<a href="javascript: void(0);">应急统计</a>
		</li>
        </sec:authorize>
		<!-- 【应急统计-菜单】 end -->
	</ul>
</li>
</sec:authorize>
<script>
	/**
	 * 应急管理-弹出窗口通用方法
 	 * @param event
	 * @param name
	 */
	function openYjglDialog(event, name) {
		var event = window.event || event;
		if (event && event.stopPropagation) {
			event.stopPropagation();
		} else {
			window.event.cancelBubble = true;
		}
		var url = "";
		var w = null;
		var h = null;

		//应急资源-物资管理
		if (name == "yjzy_wzgl") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/yjct/wzgl/index';
		}
		//应急资源-专家管理
		else if (name == "yjzy_zjgl") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/yjct/zjgl/index';
		}
		//应急资源-法律法规
		else if (name == "yjzy_flfg") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/yjct/fggl/index';
		}
		//应急预案-预案管理
		else if (name == "yjya_yagl") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/emergency/preplanManage/openDialog';
		}
		//应急预案-梯队管理
		else if (name == "yjya_tdgl") {
			w = 1200;
			h = 600;
			url = jsConst.basePath+'/emergency/groupManage/openDialog';
		}
		//应急预案-处置步骤管理
		else if (name == "yjya_czbzgl") {
			w = 1200;
			h = 600;
			url = jsConst.basePath+'/emergency/stepManage/openDialog';
		}
		//应急处置
		else if (name == "yjcz") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/emergency/handle/recordManage/openDialog';
		}
		//应急统计
		else if (name == "yjtj") {
			w = 1000;
			h = 600;
			url = jsConst.basePath+'/emergency/handle/recordStatisticManage/openDialog';
		}

		$('#dialog').html("");
		if(w == null || w == '' || w == 0) {
			w = 1000;
		}
		if(h == null || h == '' || h == 0) {
			h = 600;
		}

		$('#dialog').dialog({
			width : w,
			height : h,
			title : $("#yjgl_" + name + " a").text(),
			url : url
		});
		$("#dialog").dialog("open");
	}
</script>