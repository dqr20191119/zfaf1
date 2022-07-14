<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>任务管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/renwuguanli.css">
</head>

<body>
	<div class="header">
		<div class="logo">
			<img src="${ctx}/static/bj-cui/img/renwuguanli/header_logo.png" alt="北京市监狱（戒毒）管理局 ">
		</div>
		<div class="header-content">
			<div class="header-item date">
				<span class="icon iconfont icon-datepiceker"></span>
				<span class="title">${dqrq}</span>
			</div>
			<div class="header-item">
				<span class="icon iconfont icon-police2"></span>
				<span class="title" id="dqyh">当前用户：</span>
			</div>
			<%--
			<div class="header-item dropdow">
				<span class="icon iconfont icon-xialadown">
					<div class="dropdown-content">
						<ul class="menu">
							<li class="menu-item">用户信息</li>
							<li class="menu-item">用户信息2</li>
						</ul>
					</div>
				</span>
			</div> --%>
		</div>
		<ul class="tolist home">
			<li class="tolist-item status" id="monitor">
				首页
			</li>
			<li class="tolist-item status" id="history">
				日常值守
			</li>
			<li class="tolist-item status" id="analysis">
				工作日志
			</li>
			<li class="tolist-item status" id="analysis">
				任务管理
			</li>
			<li class="tolist-item status" id="analysis">
				通知公告
			</li>
			<li class="tolist-item status" id="analysis">
				报表抄送
			</li>
			<li class="tolist-item status" id="analysis">
				系统维护
			</li>
		</ul>
	</div>
	<div class="container-box renwuguanli">
		<div id="layout1">
			<div data-options="region:'center'">
				<h2 class="title">任务管理></h2>
				<div class="tabs-wrapper">
					<div id="tabs12">
						<ul>
							<li><a href="#fragment-1">下发任务</a></li>
							<li><a href="#fragment-2">接收任务</a></li>
						</ul>
						<div id="fragment-1">
							<div class="form-wrapper">
								<form id="issueForm">
									<div class="form-item">
										<label>单位</label>
										<input id="unitName" name="unitName" />
									</div>
									<div class="form-item time-wrapper">
										<label>下发时间</label>
										<input id="startDate" name="startDate" />
										<span>至</span>
										<input id="endDate" name="endDate" />
									</div>
									<div class="form-item">
										<label>任务状态</label>
										<input id="mission" name="mission" />
									</div>
									<div class="form-item">
										<label>任务标题</label>
										<input id="misstionTitle" name="misstionTitle" />
									</div>
								</form>
								<div class="button-wrapper">
									<button id="queryBtn">查询</button>
									<button id="newList">新增下发任务</button>
								</div>
							</div>
							<div class="grid-wrapper">
								<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
								<div id="gridList" class="grid-content"></div>
							</div>
						</div>
						<div id="fragment-2">
							<div class="form-wrapper">
								<form id="issueForm2">
									<div class="form-item">
										<label>单位</label>
										<input id="unitName2" name="unitName2" />
									</div>
									<div class="form-item time-wrapper">
										<label>下发时间</label>
										<input id="startDate2" name="startDate2" />
										<span>至</span>
										<input id="endDate2" name="endDate2" />
									</div>
									<div class="form-item">
										<label>任务状态</label>
										<input id="mission2" name="mission2" />
									</div>
									<div class="form-item">
										<label>任务标题</label>
										<input id="misstionTitle2" name="misstionTitle2" />
									</div>
								</form>
								<div class="button-wrapper">
									<button id="queryBtn2">查询</button>
								</div>
							</div>
							<div class="grid-wrapper">
								<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
								<div id="gridList2" class="grid-content"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script src="${ctx}/static/system/common.js"></script>
		
	<script>
		$("#layout1").layout({
			fit: true
		})
		$('#tabs12').tabs({
			heightStyle: 'fill',
			onCreate: function (e, ui) {
				activeContent($(e.target));
			},
			onActivate: function (e, ui) {
				activeContent($(e.target));
			}
		});
		//根据区域初始化不同内容
		function activeContent(target) {
			var acitve = target.tabs("option", "active");
			switch (acitve) {
				case 0:
					initIssue();
					break;
				case 1:
					initAccept()
					break;
				default:
					break;
			}
		}

		function initIssue() {
			initForm1();
			initGrid1();
		}

		function initAccept() {
			initForm2();
			initGrid2();
		}

		function initForm1() {
			if ($("#issueForm").hasClass("ctrl-init")) {
				return;
			}
			$("#issueForm").form();
			//单位下拉框创建
			$("#unitName").combobox({
				data: [{
					value: "all",
					text: "全部监区"
				}, {
					value: "one",
					text: "一监区"
				}],
				value: "all"
			})
			//开始日期框
			$("#startDate").datepicker({

			})
			//结束日期框
			$("#endDate").datepicker({
				startDateId: "startDate" //限制日期起始时间
			})
			//任务状态下拉框
			$("#mission").combobox({
				data: [{
					value: "noRes",
					text: "未反馈"
				}, {
					value: "done",
					text: "已监区"
				}],
				value: "noRes"
			})
			$("#misstionTitle").textbox();
			//查询
			$("#queryBtn").button({
				onClick: function (e, ui) {
					var formData = $("#issueForm").form("formData") //获取form的所有表单数据
					alert("查询")
				},
				componentCls: "query-button",
				icons: "iconfont icon-query"
			})
			//新增下发任务
			$("#newList").button({
				onClick: function (e, ui) {
					alert("新增下发任务")
				},
				componentCls: 'new-button'
			})
		}

		//列表创建
		function initGrid1() {
			$("#gridList").grid({
				colModel: [{
						label: "id",
						name: "id",
						align: "center",
						hidden: true
					}, //id列隐藏，该列的值必须唯一，不能重复
					{
						label: "下发干警",
						align: "center",
						name: "police"
					},
					{
						label: "下发时间",
						align: "center",
						name: "datetime"
					},
					{
						label: "任务标题",
						align: "center",
						name: "misstionTitle"
					},
					{
						label: "任务内容",
						align: "center",
						name: "misstionContent"
					},
					{
						label: "接收单位",
						align: "center",
						name: "unit"
					},
					{
						label: "处理期限",
						align: "center",
						name: "deadline"
					},
					{
						label: "任务状态",
						align: "center",
						name: "status"
					},
					{
						label: "任务情况",
						align: "center",
						name: "description"
					},
					{
						label: "操作",
						align: "center",
						name: "oper",
						width: 350,
						formatter: function (e, ui) {
							//根据数据，返回不同的HTML
							var content = "<div class='oper-wrapper'>";

							if (e == "ready" || e == "notBegin") {
								content += "<span class='iconfont icon-check'></span>";
								content += "<span class='iconfont icon-txt'></span>";
							}
							if (e == "notBegin") {
								content += "<span class='iconfont icon-shanchu'></span>";
								content += "<span class='iconfont icon-guizexiafa'></span>";
							}
							content += "</div>"
							return content;
						}
					}
				],
				componentCls: "grid-list",
				height: 500,
				fitStyle: "width",
				pager: true, //设置分页
				pagerTemplate: function () { //分页条自定义模板
					return "<span class='paginator-left'>{viewrecords}</span>" +
						"<span class='paginator-center'>{first}{prev}{links}{next}{last}</span>" +
						"<span class='paginator-right'>{pginput}</span>";
				},
				onComplete: function (e, ui) {
					//滚动条美化
					if ($(".coral-grid-rows-view", $(e.target)).hasClass("mCustomScrollbar")) {
						$(".coral-grid-rows-view", $(e.target)).mCustomScrollbar("destroy");
					}
					$(".coral-grid-rows-view", $(e.target)).mCustomScrollbar({
						axis: "y",
						theme: "minimal-dark"
					})
					//绑定列表上的操作列点击事件
					$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
						var currentTarget = $(e.target);
						if (currentTarget.hasClass("icon-check")) {
							alert("查询")
						} else if (currentTarget.hasClass("icon-txt")) {
							alert("下载")
						} else if (currentTarget.hasClass("icon-shanchu")) {
							alert("删除")
						} else if (currentTarget.hasClass("icon-guizexiafa")) {
							alert("规则下发")
						}
					})
				},
				rownumName: "序号",
				rownumbers: true, //自动排序
				rownumWidth: 150,
				altRows: true,
				datatype: "local", //设置为本地数据，如果要后台请求，"local"改成"json"
				data: [{ //本地数据，后台请求用url
					id: "1",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "2",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "3",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "4",
					status: "全部反馈"
				}, {
					id: "5",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "6",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "7",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "8",
					status: "全部反馈"
				}, { //本地数据，后台请求用url
					id: "9",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "10",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "11",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "12",
					status: "全部反馈"
				}]
			})
		}

		function initForm2() {
			if ($("#issueForm2").hasClass("ctrl-init")) {
				return;
			}
			$("#issueForm2").form();
			//单位下拉框创建
			$("#unitName2").combobox({
				data: [{
					value: "all",
					text: "全部监区"
				}, {
					value: "one",
					text: "一监区"
				}],
				value: "all"
			})
			//开始日期框
			$("#startDate2").datepicker({

			})
			//结束日期框
			$("#endDate2").datepicker({
				startDateId: "startDate" //限制日期起始时间
			})
			//任务状态下拉框
			$("#mission2").combobox({
				data: [{
					value: "noRes",
					text: "待接收"
				}, {
					value: "done",
					text: "已监区"
				}],
				value: "noRes"
			})
			$("#misstionTitle2").textbox();
			//查询
			$("#queryBtn2").button({
				onClick: function (e, ui) {
					var formData = $("#issueForm2").form("formData") //获取form的所有表单数据
					alert("查询")
				},
				componentCls: "query-button",
				icons: "iconfont icon-query"
			})
		}

		function initGrid2() {
			$("#gridList2").grid({
				colModel: [{
						label: "id",
						name: "id",
						align: "center",
						hidden: true
					}, //id列隐藏，该列的值必须唯一，不能重复
					{
						label: "下发干警",
						align: "center",
						name: "police"
					},
					{
						label: "下发时间",
						align: "center",
						name: "datetime"
					},
					{
						label: "任务标题",
						align: "center",
						name: "misstionTitle"
					},
					{
						label: "任务内容",
						align: "center",
						name: "misstionContent"
					},
					{
						label: "接收单位",
						align: "center",
						name: "unit"
					},
					{
						label: "处理期限",
						align: "center",
						name: "deadline"
					},
					{
						label: "任务状态",
						align: "center",
						name: "status"
					},
					{
						label: "任务情况",
						align: "center",
						name: "description"
					},
					{
						label: "操作",
						align: "center",
						name: "oper",
						width: 350,
						formatter: function (e, ui) {
							//根据数据，返回不同的HTML
							var content = "<div class='oper-wrapper'>";

							if (e == "ready" || e == "notBegin") {
								content += "<span class='iconfont icon-check'></span>";
								content += "<span class='iconfont icon-txt'></span>";
							}
							if (e == "notBegin") {
								content += "<span class='iconfont icon-shanchu'></span>";
								content += "<span class='iconfont icon-guizexiafa'></span>";
							}
							content += "</div>"
							return content;
						}
					}
				],
				componentCls: "grid-list",
				height: 500,
				fitStyle: "width",
				pager: true, //设置分页
				pagerTemplate: function () { //分页条自定义模板
					return "<span class='paginator-left'>{viewrecords}</span>" +
						"<span class='paginator-center'>{first}{prev}{links}{next}{last}</span>" +
						"<span class='paginator-right'>{pginput}</span>";
				},
				onComplete: function (e, ui) {
					//滚动条美化
					if ($(".coral-grid-rows-view", $(e.target)).hasClass("mCustomScrollbar")) {
						$(".coral-grid-rows-view", $(e.target)).mCustomScrollbar("destroy");
					}
					$(".coral-grid-rows-view", $(e.target)).mCustomScrollbar({
						axis: "y",
						theme: "minimal-dark"
					})
					//绑定列表上的操作列点击事件
					$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
						var currentTarget = $(e.target);
						if (currentTarget.hasClass("icon-check")) {
							alert("查询")
						} else if (currentTarget.hasClass("icon-txt")) {
							alert("下载")
						} else if (currentTarget.hasClass("icon-shanchu")) {
							alert("删除")
						} else if (currentTarget.hasClass("icon-guizexiafa")) {
							alert("规则下发")
						}
					})
				},
				rownumName: "序号",
				rownumbers: true, //自动排序
				rownumWidth: 150,
				altRows: true,
				datatype: "local", //设置为本地数据，如果要后台请求，"local"改成"json"
				data: [{ //本地数据，后台请求用url
					id: "1",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "2",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "3",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "4",
					status: "全部反馈"
				}, {
					id: "5",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "6",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "7",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "8",
					status: "全部反馈"
				}, { //本地数据，后台请求用url
					id: "9",
					datetime: "张三",
					status: "待下发",
					oper: "notBegin"
				}, {
					id: "10",
					datetime: "张三",
					status: "未反馈",
					oper: "ready"
				}, {
					id: "11",
					datetime: "张三",
					status: "部分反馈"
				}, {
					id: "12",
					status: "全部反馈"
				}]
			})
		}
		
		$(function () {
			jsConst.basePath = "${ctx}/";
			
			// 初始化登录用户信息
			initUserInfo();
		});
		
		function showDqyh() {
			$("#dqyh").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
	</script>
</body>

</html>
