<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- 暂无，后期根据各家监狱的需求，进行开发定制版，后期有时间再加吧-->
	<sec:authorize url="/zhdd/sy/zhdd/yjgl">
	<li id="nav-yjct">
		<a href="javascript: void(0)" data-toggle="dropdown" class="dropdown-toggle">
			应急调度（旧）
		</a>
		<iframe class="main-iframe"></iframe>
		<ul>
			<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy">
			<li id="zygl">
				<a href="javascript: void(0)">
					应急资源
					<b class="leftArrow"></b>
				</a>
				<iframe class="sub-iframe"></iframe>
				<ul>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy/gzzgl">
					<li id="gzzgl" onclick="openEmergencyDialog(event,'gzzgl')">
						<a href="javascript: void(0)">工作组管理</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy/wzgl">
					<li id="wzgl" onclick="openEmergencyDialog(event,'wzgl')">
						<a href="javascript: void(0)">物资管理</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy/zjgl">
					<li id="zjgl" onclick="openEmergencyDialog(event,'zjgl')">
						<a href="javascript: void(0)">专家管理</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy/fggl">
					<li id="fggl" onclick="openEmergencyDialog(event,'fggl')">
						<a href="javascript: void(0)">法规管理</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjzy/czffgl">
					<li id="czffgl" onclick="openEmergencyDialog(event,'czffgl')">
						<a href="javascript: void(0)">处置方法管理</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
			<%--  <sec:authorize url="/zhdd/sy/afzhddpt/afzh/yjgl/yjya">
			<li id="yagl">
				<a href="javascript: void(0)">
					应急预案
					<b class="leftArrow"></b>
				</a>
				<iframe class="sub-iframe"></iframe>
				<ul>
					<sec:authorize url="/zhdd/sy/afzhddpt/afzh/yjgl/yjya/yabz">
					<li id="yabz" onclick="openEmergencyDialog(event,'yabz')">
						<a href="javascript: void(0)">预案编制</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/afzhddpt/afzh/yjgl/yjya/yzsp">
					<li id="yasp" onclick="openEmergencyDialog(event,'yasp')">
						<a href="javascript: void(0)">预案审批</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/afzhddpt/afzh/yjgl/yjya/yafb">
					<li id="yafb" onclick="openEmergencyDialog(event,'yafb')">
						<a href="javascript: void(0)">预案发布</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>   --%>
			 <sec:authorize url="/zhdd/sy/zhdd/yjgl/yjcz">
			<li id="czgl">
				<a href="javascript: void(0)">
					 应急处置
					<b class="leftArrow"></b>
				</a>
				<iframe class="sub-iframe"></iframe>
				<ul>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjcz/sjcz">
					<li id="sjcz" onclick="toEmergencyDisplay('sjcz')">
						<a href="javascript: void(0)">事件处置</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/sy/zhdd/yjgl/yjcz/czjl">
					<li id="czjl" onclick="openEmergencyDialog(event,'czjl')">
						<a href="javascript: void(0)">处置记录</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize> 
			<%-- <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/yjgl/tjtj">
			<li id="cztj" onclick="openEmergencyDialog(event,'cztj')">
				<a href="javascript: void(0)">应急统计</a>
			</li>
			</sec:authorize> --%>
		</ul>
	</li>
	</sec:authorize>
<script>
		
		function openEmergencyDialog(event,name) {
			
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
			} else if (name == "zjgl") {
				url = jsConst.basePath+'/yjct/zjgl/index';
			} else if (name == "fggl") {
				url = jsConst.basePath+'/yjct/fggl/index';
			} else if (name == "czffgl") {
				url = jsConst.basePath+'/yjct/czffgl/index';
			} else if (name == "yabz") {
				url = jsConst.basePath+'/yjct/yabz/index';
			} else if (name == "yasp") {
				url = jsConst.basePath+'/yjct/yasp/index';
			} else if (name == "yafb") {
				url = jsConst.basePath+'/yjct/yafb/index';
			} else if (name == "yljh") {
				url = jsConst.basePath+'/yjct/yljh/index';
			} else if (name == "ylsp") {
				url = jsConst.basePath+'/yjct/ylsp/index';
			} else if (name == "ylfb") {
				url = jsConst.basePath+'/yjct/ylfb/index';
			} else if (name == "zxyl") {
			
			} else if (name == "yljl") {
				url = jsConst.basePath+'/yjct/yjjl/toIndex?type=2';
			} else if (name == "yltj") {
				url = jsConst.basePath+'/yjct/yjjl/toTj?type=2';
			} else if (name == "sjcz") {
			
			} else if (name == "czjl") {
				url = jsConst.basePath+'/yjct/yjjl/toIndex?type=1';
			} else if (name == "cztj") {
				url = jsConst.basePath+'/yjct/yjjl/toTj?type=1';
			} else if (name == "xxdy") {
				url = jsConst.basePath+'/yjct/msgsubscribe/index';
			} else if(name == "yjctSszk") {
				url = jsConst.basePath+'/yjct/sszk/toIndex';
			}

//            $('#dialog').dialog("destroy");
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
		
		function toEmergencyDisplay(name) {
			var panel = $("#layout1").layout("panel", "east");
			if (name == "zxyl") {
				panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=2");
			}else if (name == "sjcz") {
				panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=1");
			}
		}
		</script>