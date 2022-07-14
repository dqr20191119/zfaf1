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
	<title>值班登记</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/zhibandengji.css">
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
	<div class="container-box zhibandengji">
		<div id="layout1">
			<div data-options="region:'center'">
				<div class="zhibandengji-left">
					<div class="people-wrapper">
						<h2 class="title">值班登记</h2>
						<p class="duty-time">值班时间</p>
						<p class="duty-time">2018-04-25 06:00-2018-04-25 21:00</p>
						<ul class="people-list">
							<li class="list-item">
								<div class="list-img">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" alt="领导头像">
								</div>
								<div class="name-wrapper">
									<p class="list-title">带班领导</p>
									<p class="list-name">张警官</p>
								</div>
							</li>
							<li class="list-item">
								<div class="list-img">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" alt="领导头像">
								</div>
								<div class="name-wrapper">
									<p class="list-title">指挥长</p>
									<p class="list-name">李警官</p>
								</div>

							</li>
							<li class="list-item name-scroll">
								<div class="list-img">
									<img src="${ctx}/static/bj-cui/img/command/leader.png" alt="领导头像">
								</div>
								<div class="name-wrapper">
									<p class="list-title">值班人员</p>
									<ul class="list-name">
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
										<li><a>王警官</a></li>
									</ul>
								</div>
							</li>
						</ul>
						<div class="people-action">
							<button id="handOver">交班</button>
							<button id="succession">接班</button>
						</div>
						<div class="duty-login-wrapper">
							<button id="dutyLogin">值班人员登记</button>
						</div>
					</div>
					<div class="issue-wrapper">
						<h2 class="title">重要事项</h2>
						<p class="more">更多+</p>
						<ul class="issue-list">
							<li class="list-item">
								<p class="content">本单位未处理且为重要的值班记录内容...</p>
								<p class="time">(2018-04-01 10:00)</p>
							</li>
							<li class="list-item">
								<p class="content">本单位未处理且为重要的值班记录内容...</p>
								<p class="time">(2018-04-01 10:00)</p>
							</li>
							<li class="list-item">
								<p class="content">本单位未处理且为重要的值班记录内容...</p>
								<p class="time">(2018-04-01 10:00)</p>
							</li>
							<li class="list-item">
								<p class="content">本单位未处理且为重要的值班记录内容...</p>
								<p class="time">(2018-04-01 10:00)</p>
							</li>
							<li class="list-item">
								<p class="content">本单位未处理且为重要的值班记录内容...</p>
								<p class="time">(2018-04-01 10:00)</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="zhibandengji-right">
					<div class="unit-wrapper">
						<label class="title">单位选择</label>
						<input id="unitSelect">
						<div class="grid-wrapper">
							<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
							<div id="unitGrid" class="grid-content"></div>
						</div>
					</div>
					<div class="log-wrapper">
						<h2 class="title">值班日志</h2>
						<button id="newLog" class="">新增日志</button>
						<div class="grid-wrapper">
							<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
							<div id="logGrid" class="grid-content"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="handOverDialog">
			<div class="row form-row">
				<div class="col-md-4">
					<label>交班日期</label>
				</div>
				<div class="col-md-8">
					<input id="changeDate">
				</div>
			</div>
			<div class="row form-row">
				<div class="col-md-4">
					<label>操作人</label>
				</div>
				<div class="col-md-8">
					<input id="changeMember">
				</div>
			</div>
		</div>
		<div id="successionDialog">
			<div class="row form-row">
				<div class="col-md-4">
					<label>代班干警</label>
				</div>
				<div class="col-md-8">
					<input id="substitute">
				</div>
			</div>
			<div class="row form-row">
				<div class="col-md-4">
					<label>换班的干警为</label>
				</div>
				<div class="col-md-8">
					<input id="relief">
				</div>
			</div>
			<div class="row form-row">
				<div class="col-md-4">
					<label>备注</label>
				</div>
				<div class="col-md-8">
					<textarea id="remark"></textarea>
				</div>
			</div>
		</div>
		<div id="logDialog">
			<div class="log-form">
				<h2 class="title">事件内容</h2>
				<div class="row form-row">
					<div class="col-md-2">
						<label>发生时间</label>
					</div>
					<div class="col-md-4">
						<input id="happenTime">
					</div>
					<div class="col-md-2">
						<label>记录时间</label>
					</div>
					<div class="col-md-4">
						<input id="logTime">
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-2">
						<label>事件描述</label>
					</div>
					<div class="col-md-10">
						<textarea id="logDes"></textarea>
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-2">
						<label>事件程度</label>
					</div>
					<div class="col-md-4">
						<input id="logStatus">
					</div>
					<div class="col-md-2">
						<label>事件来源</label>
					</div>
					<div class="col-md-4">
						<input id="logOri">
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-4">
						<button id="fileButton">上传时间发生附件</button>
					</div>
				</div>
				<div class="row form-row">
					<div class="log-grid">
						<div class="grid-wrapper">
							<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
							<div id="eventGrid" class="grid-content"></div>
						</div>
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-2">
						<label>事件状态</label>
					</div>
					<div class="col-md-4">
						<input id="eventStatus">
					</div>
				</div>
				<h2 class="title">处理结果</h2>
				<div class="row form-row">
					<div class="col-md-2">
						<label>处理描述</label>
					</div>
					<div class="col-md-10">
						<textarea id="dealDes"></textarea>
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-2">
						<label>处理时间</label>
					</div>
					<div class="col-md-4">
						<input id="dealTime">
					</div>
					<div class="col-md-2">
						<label>处理人</label>
					</div>
					<div class="col-md-4">
						<input id="dealMember">
					</div>
				</div>
				<div class="row form-row">
					<div class="col-md-4">
						<button id="dealButton">上传事件处理附件</button>
					</div>
				</div>
				<div class="row form-row">
					<div class="log-grid">
						<div class="grid-wrapper">
							<img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
							<div id="dealGrid" class="grid-content"></div>
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
			$("#handOver").button({
				onClick: function () {
					openOver();
				}
			})
			$("#succession").button({
				onClick: function () {
					openSuc();
				}
			})
			$("#dutyLogin").button({
				onClick: function () {
					alert("值班人员登记")
				},
				componentCls: "duty-login"
			})
			$("#unitSelect").combobox({
				data: [{
					value: "bj",
					text: "北京市监狱管理局"
				}, {
					value: "bj2",
					text: "管理局2"
				}],
				value: "bj"
			})
			initGrid1();
			initGrid2();
			$("#newLog").button({
				onClick: function () {
					openNew();
				},
				componentCls: "log-button"
			})
			$("#handOverDialog").dialog({
				width: 600,
				height: 300,
				resizable: false,
				componentCls: "custom dark", //对话框风格，引入dialog.css后需要加上此属性
				autoOpen: false,
				onOpen: function () {
					initHandOverForm();
				},
				buttons: [{
					text: "保存",
					id: "saveId",
					click: function () {
						alert("save");
					}
				}, {
					text: "取消",
					id: "cancelId",
					click: function () {
						$("#handOverDialog").dialog("close");
					}
				}]
			})
			$("#successionDialog").dialog({
				width: 600,
				height: 400,
				resizable: false,
				componentCls: "custom dark", //对话框风格，引入dialog.css后需要加上此属性
				autoOpen: false,
				onOpen: function () {
					initSucForm();
				},
				buttons: [{
					text: "保存",
					id: "saveId",
					click: function () {
						alert("save");
					}
				}, {
					text: "取消",
					id: "cancelId",
					click: function () {
						$("#successionDialog").dialog("close");
					}
				}]
			})
			$("#logDialog").dialog({
				width: 800,
				height: "90%",
				resizable: false,
				componentCls: "custom dark", //对话框风格，引入dialog.css后需要加上此属性
				autoOpen: false,
				onOpen: function () {
					initLogForm();
				},
				buttons: [{
					text: "保存",
					id: "saveId",
					click: function () {
						alert("save");
					}
				}, {
					text: "取消",
					id: "cancelId",
					click: function () {
						$("#logDialog").dialog("close");
					}
				}]
			})

			function initHandOverForm() {
				if ($("#changeDate").hasClass("ctrl-init")) {
					return;
				}
				$("#changeDate").datepicker({
					componentCls: "form-control"
				});
				$("#changeMember").combobox({
					data: [{
						value: "zhang",
						text: "张警官"
					}, {
						value: "li",
						text: "李警官"
					}],
					componentCls: "form-control"
				})
			}

			function initSucForm() {
				if ($("#substitute").hasClass("ctrl-init")) {
					return;
				}
				$("#substitute").radiolist({
					data: [{
						value: "yes",
						text: "是"
					}, {
						value: "no",
						text: "否"
					}],
					componentCls: "form-control"
				});
				$("#relief").combobox({
					data: [{
						value: "zhang",
						text: "张警官"
					}, {
						value: "li",
						text: "李警官"
					}],
					componentCls: "form-control"
				})
				$("#remark").textbox({
					componentCls: "form-control"
				});
			}

			function initLogForm() {
				if ($("#happenTime").hasClass("ctrl-init")) {
					return;
				}
				$("#happenTime").datepicker({
					componentCls: "form-control"
				})
				$("#logTime").datepicker({
					componentCls: "form-control",
					value: "2019-01-03",
					isLabel: true
				})
				$("#logDes").textbox({
					componentCls: "form-control"
				})
				$("#logStatus").radiolist({
					data: [{
						value: "common",
						text: "一般"
					}, {
						value: "serious",
						text: "重要"
					}]
				})
				$("#logOri").combobox({
					componentCls: "form-control",
					data: [{
						value: "video",
						text: "视频巡查"
					}]
				})
				$("#fileButton").button({
					onClick: function () {
						alert("上传");
					}
				})
				initGrid3();
				$("#eventStatus").radiolist({
					data: [{
						value: "todo",
						text: "待处理"
					}, {
						value: "finished",
						text: "已处理"
					}]
				});
				$("#dealDes").textbox({
					componentCls: "form-control"
				})
				$("#dealTime").datepicker({
					componentCls: "form-control"
				})
				$("#dealMember").combobox({
					data: [{
						value: "zhang",
						text: "张警官"
					}, {
						value: "li",
						text: "李警官"
					}],
					componentCls: "form-control"
				})
				$("#dealButton").button({
					onClick: function () {
						alert("上传");
					}
				})
				initGrid4();
				addScroll($("#logDialog"));
			}

			function openOver() {
				$("#handOverDialog").dialog("open");
			}

			function openSuc() {
				$("#successionDialog").dialog("open");
			}

			function openNew() {
				$("#logDialog").dialog("open");
			}
			addScroll($(".issue-list"));
			addScroll($(".name-scroll .list-name"));

			function addScroll(target) {
				if (target.hasClass("mCustomScrollbar")) {
					target.mCustomScrollbar("destroy");
				}
				target.mCustomScrollbar({
					axis: "y",
					theme: "minimal-dark",
					scrollbarPosition: "outside",
				});
			}
			//列表创建
			function initGrid1() {
				$("#unitGrid").grid({
					colModel: [{
							label: "id",
							name: "id",
							align: "center",
							hidden: true
						},
						{
							label: "值班时间",
							align: "center",
							name: "time",
							width: 400
						},
						{
							label: "带班领导",
							align: "center",
							name: "datetime"
						},
						{
							label: "指挥长",
							align: "center",
							name: "misstionTitle"
						},
						{
							label: "值班人员",
							align: "center",
							name: "member",
							width: 350
						},
						{
							label: "操作",
							align: "center",
							name: "oper",
							width: 300,
							formatter: function (e, ui) {
								//根据数据，返回不同的HTML
								var content = "<div class='oper-wrapper'>";
								content += "<span class='iconfont icon-check'>查看</span>";
								content += "<span class='iconfont icon-txt'>修改</span>";
								content += "</div>"
								return content;
							}
						}
					],
					componentCls: "grid-list",
					height: 280,
					rownumbers: true,
					fitStyle: "width",
					pager: true, //设置分页
					pagerTemplate: function () {
						return "<span class='paginator-left'>{viewrecords}</span>" +
							"<span class='paginator-center'>{first}{prev}{links}{next}{last}</span>" +
							"<span class='paginator-right'>{pginput}</span>";
					},
					onComplete: function (e, ui) {
						addScroll($(".coral-grid-rows-view", $(e.target)));
						//绑定列表上的操作列点击事件
						$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
							var currentTarget = $(e.target);
							if (currentTarget.hasClass("icon-check")) {
								alert("查看")
							} else if (currentTarget.hasClass("icon-txt")) {
								alert("修改")
							}
						})
					},
					altRows: true,
					datatype: "local",
					data: [{
						id: "1",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员5"
					}, {
						id: "2",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "3",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "4",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "6",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "7",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}]
				})
			}

			function initGrid2() {
				$("#logGrid").grid({
					colModel: [{
							label: "id",
							name: "id",
							align: "center",
							hidden: true
						},
						{
							label: "发生时间",
							align: "center",
							name: "time",
							width: 400
						},
						{
							label: "事件内容",
							align: "center",
							name: "datetime"
						},
						{
							label: "事件程度",
							align: "center",
							name: "misstionTitle"
						},
						{
							label: "信息来源",
							align: "center",
							name: "member",
							width: 350
						},
						{
							label: "操作",
							align: "center",
							name: "oper",
							width: 350,
							formatter: function (e, ui) {
								//根据数据，返回不同的HTML
								var content = "<div class='oper-wrapper'>";
								content += "<span class='iconfont icon-check'>查看</span>";
								content += "<span class='iconfont icon-txt'>修改</span>";
								content += "<span class='iconfont icon-shanchu'>删除</span>";
								content += "</div>"
								return content;
							}
						}
					],
					componentCls: "grid-list",
					height: 350,
					rownumbers: true,
					fitStyle: "width",
					pager: true, //设置分页
					pagerTemplate: function () {
						return "<span class='paginator-left'>{viewrecords}</span>" +
							"<span class='paginator-center'>{first}{prev}{links}{next}{last}</span>" +
							"<span class='paginator-right'>{pginput}</span>";
					},
					onComplete: function (e, ui) {
						addScroll($(".coral-grid-rows-view", $(e.target)));
						//绑定列表上的操作列点击事件
						$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
							var currentTarget = $(e.target);
							if (currentTarget.hasClass("icon-check")) {
								alert("查看")
							} else if (currentTarget.hasClass("icon-txt")) {
								alert("修改")
							} else if (currentTarget.hasClass("icon-shanchu")) {
								alert("删除")
							}
						})
					},
					altRows: true,
					datatype: "local",
					data: [{
						id: "1",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员5"
					}, {
						id: "2",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "3",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "4",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "6",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}, {
						id: "7",
						time: "2018-04-25 06:00-2018-04-25 21:00",
						member: "人员3、人员4、人员5"
					}]
				})
			}

			function initGrid3() {
				$("#eventGrid").grid({
					colModel: [{
							label: "id",
							name: "id",
							align: "center",
							hidden: true
						},
						{
							label: "文件名称",
							align: "center",
							name: "fileName",
							width: 400
						},
						{
							label: "操作",
							align: "center",
							name: "oper",
							width: 350,
							formatter: function (e, ui) {
								//根据数据，返回不同的HTML
								var content = "<div class='oper-wrapper'>";
								content += "<span class='iconfont icon-check'>查看</span>";
								content += "<span class='iconfont icon-guizexiafa'>下载</span>";
								content += "<span class='iconfont icon-shanchu'>删除</span>";
								content += "</div>"
								return content;
							}
						}
					],
					componentCls: "grid-list",
					height: 180,
					rownumbers: true,
					rownum: -1,
					fitStyle: "width",
					onComplete: function (e, ui) {
						addScroll($(".coral-grid-rows-view", $(e.target)));
						//绑定列表上的操作列点击事件
						$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
							var currentTarget = $(e.target);
							if (currentTarget.hasClass("icon-check")) {
								alert("查看")
							} else if (currentTarget.hasClass("icon-txt")) {
								alert("下载")
							} else if (currentTarget.hasClass("icon-shanchu")) {
								alert("删除")
							}
						})
					},
					altRows: true,
					datatype: "local",
					data: [{
						id: "1",
						fileName: "123"
					}, {
						id: "2",
						fileName: "2018-04-25 06:00-2018-04-25 21:00"
					}, {
						id: "3",
						fileName: "2018-04-25 06:00-2018-04-25 21:00"
					}]
				})
			}
			function initGrid4() {
				$("#dealGrid").grid({
					colModel: [{
							label: "id",
							name: "id",
							align: "center",
							hidden: true
						},
						{
							label: "文件名称",
							align: "center",
							name: "fileName",
							width: 400
						},
						{
							label: "操作",
							align: "center",
							name: "oper",
							width: 350,
							formatter: function (e, ui) {
								//根据数据，返回不同的HTML
								var content = "<div class='oper-wrapper'>";
								content += "<span class='iconfont icon-check'>查看</span>";
								content += "<span class='iconfont icon-guizexiafa'>下载</span>";
								content += "<span class='iconfont icon-shanchu'>删除</span>";
								content += "</div>"
								return content;
							}
						}
					],
					componentCls: "grid-list",
					height: 180,
					rownumbers: true,
					rownum: -1,
					fitStyle: "width",
					onComplete: function (e, ui) {
						addScroll($(".coral-grid-rows-view", $(e.target)));
						//绑定列表上的操作列点击事件
						$(".oper-wrapper", $(e.target)).off("click").on("click", ".iconfont", function (e) {
							var currentTarget = $(e.target);
							if (currentTarget.hasClass("icon-check")) {
								alert("查看")
							} else if (currentTarget.hasClass("icon-txt")) {
								alert("下载")
							} else if (currentTarget.hasClass("icon-shanchu")) {
								alert("删除")
							}
						})
					},
					altRows: true,
					datatype: "local",
					data: [{
						id: "1",
						fileName: "123"
					}, {
						id: "2",
						fileName: "2018-04-25 06:00-2018-04-25 21:00"
					}, {
						id: "3",
						fileName: "2018-04-25 06:00-2018-04-25 21:00"
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
