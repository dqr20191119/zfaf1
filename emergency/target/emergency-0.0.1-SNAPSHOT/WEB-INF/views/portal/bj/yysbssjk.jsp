<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="ssjk">
	<div data-options="region:'east'" style="width:277px;">
		<div class="right">
			<div class="on-duty right">
				<h2>敏感词提醒</h2>
				<ul class="rightList">
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>
					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>

					<li class="wordList">
						<div class="wordListTop">
							<p>
								<span class="eventIcon"></span>
								<span class="eventName">逃跑</span>
							</p>
							<p>
								<span class="quName">第一监区</span>
								<span class="quName">王二妞</span>
							</p>

						</div>
						<div class="wordListBtm">
							<span class="iconfont icon-time"></span>
							<span>2019.01.24 15:22:23</span>
						</div>
					</li>

				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" class="main">
		<div class="center">
			<h3>语音识别</h3>
			<div class="middle-box clearfix">
				<ul class="middle-scroll clearfix">
					<li>
						<div>
							<span class="voice-num"></span>
							<i>01</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>02</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div class="empty">
							<span class="voice-num"></span>
							<i>03</i>
							<span class="iconfont icon-signal"></span>

							<img src="${ctx}/static/bj-cui/img/command/desk.png" />
							<p>无</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>04</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>05</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>06</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div class="empty">
							<span class="voice-num"></span>
							<i>07</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/desk.png" />
							<p>无</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>08</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>09</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>10</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>11</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>12</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>13</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>14</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/img/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
					<li>
						<div>
							<span class="voice-num"></span>
							<i>15</i>
							<span class="iconfont icon-signal"></span>
							<img src="${ctx}/static/bj-cui/command/crimer.png" />
							<p>王二牛</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<script>
	$("#ssjk").layout({
		fit: true
	})
	$(".middle-scroll").mCustomScrollbar({
		axis: "y",
		theme: "minimal-dark",
		scrollbarPosition: "outside",
	});
	$(".rightList").mCustomScrollbar({
		axis: "y",
		theme: "minimal-dark",
		scrollbarPosition: "outside",
	});
</script>
</body>

</html>
