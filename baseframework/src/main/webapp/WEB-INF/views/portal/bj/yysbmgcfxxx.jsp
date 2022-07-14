<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="realTimeMonitoring">
	<div data-options="region:'west'" style="width:345px;">
		<div class="left">
			<div class="peopleInfo">
				<div class="levelEvent">重点</div>
				<div class="peopleInfoDetail">
					<div class="chargeConten">
						<img src="${ctx}/static/bj-cui/img/peoplePic.png" />
						<p class="criminalName">王二妞</p>
						<p class="charge">危害国家安全罪</p>
						<p class="crimeYear">
							<span>现刑3年</span>
							<span>|</span>
							<span>宽管</span>
						</p>
					</div>
					<div class="currentInfo">
						<h5>当前通话</h5>
						<p class="tongHuaTime">01.24 15:22:23</p>
						<div class="voice-wrapper">
							<div class="voice-one"></div>
							<div class="voice-two"></div>
							<div class="voice-three"></div>
							<div class="voice-four"></div>
							<div class="voice-five"></div>
						</div>
						<p class="familyRelationship">李四（堂弟）</p>
						<p class="callRecord">上次通话</p>
						<p class="callRecord">2019.01.24 15:22:23</p>
					</div>
					<div class="currentInfo relationship">
						<h5>社会关系</h5>
						<div class="familyRelationship">
							<p>王二（父亲）</p>
							<p>李文（母亲）</p>
						</div>
						<div class="familyRelationship marriage">
							<p>婚姻状况：未婚</p>
						</div>
						<ul>
							<li class="action"><span>申诉：</span>1次</li>
							<li class="action"><span>控诉</span>：1次</li>
							<li class="action"><span>检举</span>：无</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

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
		<div class="center-content">

		</div>
	</div>
</div>
<script>
	$("#realTimeMonitoring").layout({
		fit: true
	})
	$(".rightList").mCustomScrollbar({
		axis: "y",
		theme: "minimal-dark",
		scrollbarPosition: "outside",
	});
</script>
</body>

</html>
