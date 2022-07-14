
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
		<sec:authorize url="/zhdd/sy">
		<li id="nav-home" onclick="openZnafpt()">
			<a href="javascript:void(0)">
				首 页
				<span class="h-arraw">
					<i></i>
				</span>
			</a>
		</li>
		</sec:authorize>
		<jsp:include page="../include/xxhjInclude.jsp"></jsp:include>
		<jsp:include page="../include/aqfkInclude.jsp"></jsp:include>
		<jsp:include page="../include/zhxtInclude.jsp"></jsp:include>

		<li id="nav-xxyp">
			<sec:authorize url="/zhdd/xxyp">
			<a href="#" data-toggle="dropdown" class="dropdown-toggle">
				信息研判
				<span class="h-arraw">
					<b class="caret"></b>
				</span>
			</a>
			<iframe class="main-iframe"></iframe>
			</sec:authorize>
			<ul>
				<sec:authorize url="/zhdd/xxyp/xxrb">
				<li id="dayly" onclick="openDialog(event,'dayly')">
					<a href="#">信息日报</a>
				</li>
				</sec:authorize>
				<li id="nav-tbzg">
					<sec:authorize url="/zhdd/xxyp/tbzg">
					<a href="#">
						通报整改
						<b class="leftArrow"></b>
					</a>
					<iframe class="sub-iframe"></iframe>
					</sec:authorize>
					<ul id="dropdown-tbzg" class="dropdown-menu" style="background-color: #333; color: white;">
						<sec:authorize url="/zhdd/xxyp/tbzg/fq">
						<li id="aqfk_tbzg-fq" onclick="openAqfkDialog(event,'tbzg-fq')">
							<a href="#">发起</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/xxyp/tbzg/zg">
						<li id="aqfk_tbzg-zg" onclick="openAqfkDialog(event,'tbzg-zg')">
							<a href="#">整改</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/xxyp/tbzg/sp">
						<li id="aqfk_tbzg-sp" onclick="openAqfkDialog(event,'tbzg-sp')">
							<a href="#">审批</a>
						</li>
						</sec:authorize>
						<sec:authorize url="/zhdd/xxyp/tbzg/hz">
						<li id="aqfk_tbzg-hz" onclick="openAqfkDialog(event,'tbzg-hz')">
							<a href="#">汇总</a>
						</li>
						</sec:authorize>
					</ul>
				</li> 
			</ul>
		</li>
		<jsp:include page="../include/yjctInclude.jsp"></jsp:include>
		<jsp:include page="../include/xtglInclude.jsp"></jsp:include>
		<!-- 风险评估菜单 -->
		<%-- <jsp:include page="../include/fxpgInclude.jsp"></jsp:include> --%>


	</ul>
	<%-- <ul id="exit-box" class="exit-box">
		<li>
			<a href="javascript:logoutX();" title="退出"><span></span></a>
		</li>
	</ul> --%>
</div>

<script type="text/javascript">
	function openZnafpt() {
		
		var url = "${ctx}/portal/bj/shouye";
		window.location.href = url;
	}
</script>