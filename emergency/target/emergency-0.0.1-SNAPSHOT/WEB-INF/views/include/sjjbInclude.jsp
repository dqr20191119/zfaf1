<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<li>
	<a href="javascript:openSjjbSystem();">
		数据接报
	</a>
	<iframe class="main-iframe"></iframe>
	<ul>
		<li>
			<a href="#">交接班</a>
		</li>
		<li>
			<a href="#">值班日志</a>
		</li>
		<!-- <li>
			<a href="javascript:void(0);">任务管理</a>
			<b class="leftArrow"></b>
			<iframe class="sub-iframe"></iframe>
            <ul>
				<li>
					<a href="#">任务下达</a>
				</li>
				<li>
					<a href="#">任务接收处理</a>
				</li>
				<li>
					<a href="#">任务查询</a>
				</li>
            </ul>
		</li> -->
		<li>
			<a href="#">待办事项</a>
		</li>
		<li>
			<a href="#">报表报送</a>
		</li>
		<li>
			<a href="#">通知公告</a>
		</li>
	</ul>
</li>

<script>
/**
 * 跳转至数据接报系统
 */
function openSjjbSystem() {
	var url = jsConst.basePath + "/portal/jbxt/shouye";
	window.open(url, "_blank");
}
</script>
