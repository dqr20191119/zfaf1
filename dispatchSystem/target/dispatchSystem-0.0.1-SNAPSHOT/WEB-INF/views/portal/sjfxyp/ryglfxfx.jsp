<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@page import="com.cesgroup.framework.util.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	String userName = user.getUserName();
	String today = DateUtil.getTodayString();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- start: Meta -->
<meta charset="utf-8">
<title>人员管理风险分析</title>
<meta name="author" content="Dennis Ji">
<meta name="keyword" content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<!-- end: Meta -->
<!-- start: Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- end: Mobile Specific -->
<!-- start: CSS -->
<link id="bootstrap-style" href="${ctx}/static/sjfxyp/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/sjfxyp/css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="${ctx}/static/sjfxyp/css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="${ctx}/static/sjfxyp/css/style-responsive.css" rel="stylesheet">
<!-- end: CSS -->
<!--[if lt IE 9]>	  	
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="${ctx}/static/sjfxyp/images/favicon.ico">
<!-- end: Favicon -->
</head>
<body class="bodybgimg" style="position: relative">
	<!-- start: Header -->
	<div class="headBg">
		<div class="container-fluid">
			<div class="leftNav">
				<ul>
					<!-- <li><a href="#">狱情态势</a></li>
        <li><a href="yffx.htm">押犯分析 </a></li> -->
					<!--  <li><a href="genzong.htm" class="hover"> 系统跟踪 </a></li> -->
					<!--   <li><a href="sjyy.htm"> 数据应用 </a></li> -->
				</ul>
			</div>

			<!-- start: Header Menu -->
			<div class="nav-no-collapse">
				<div class="datewrap">
					<!-- 2018-04-23 <span class="ml15">星期一</span> -->
				</div>
				<div class="quit">
					<a href="#" onclick="guanbi()">退出</a>
				</div>
				<div class="user">
					<a href="#"><img src="${ctx}/static/sjfxyp/images/user.png"><%=userName%></a>
				</div>
				<!-- end: Header Menu -->

			</div>
		</div>
	</div>
	<!-- start: Header -->
	<div class="container-fluid-full"
		style="margin: 0 58px; margin-top: 20px;">
		<!-- end: Main Menu -->
		<div class="row-fluid">
			<div class="span5 widget bordercolor fontSize" onTablet="span5"
				onDesktop="span5" style="margin-top: 3px; height: 480px;">
				<div class="yuan_1"></div>
				<div class="yuan_2"></div>
				<div class="yuan_3"></div>
				<div class="yuan_4"></div>
				<div class="row-fluid">
					<h6>民警年龄构成</h6>
					<div class="" style="width: 500; height: 205px" id="genzhong_mkrd">
					</div>
				</div>
				<div class="row-fluid">
					<h6>民警学历构成</h6>
					<div class="" style="width: 500; height: 205px" id="genzhong_mkrdd">
					</div>
				</div>
			</div>
			<div class="span7 widget bordercolor fontSize" onTablet="span7"
				onDesktop="span7" style="margin-top: 3px; height: 480px;">
				<div class="yuan_1"></div>
				<div class="yuan_2"></div>
				<div class="yuan_3"></div>
				<div class="yuan_4"></div>
				<h6>历年民警数量</h6>
				<div class="center15" id="genzhong_xtsy"
					style="width: 900px; height: 405px"></div>
				<div class="clearfix"></div>
			</div>
			<!-- End .sparkStats -->

		</div>
		<div class="row-fluid">

			<!----->
			<div class="span4 widget bordercolor fontSize" onTablet="span4"
				onDesktop="span4" style="margin-top: 20px; margin-bottom: 16px;">
				<div class="yuan_1"></div>
				<div class="yuan_2"></div>
				<div class="yuan_3"></div>
				<div class="yuan_4"></div>
				<div class="row-fluid">
					<h6>监内民警分布</h6>
					<div class="boximg_r_img" id="genzhong_yzywsyqk"
						style="width: 500px; height: 205px; text-align: center;"></div>
				</div>
			</div>
			<!----->
			<!----->
			<div class="span4 widget bordercolor fontSize" onTablet="span4"
				onDesktop="span4" style="margin-top: 20px; margin-bottom: 10px;">
				<div class="yuan_1"></div>
				<div class="yuan_2"></div>
				<div class="yuan_3"></div>
				<div class="yuan_4"></div>
				<div class="row-fluid">
					<h6>机关民警分布</h6>
					<div class="boximg_r_img" id="genzhong_lcbjqk"
						style="width: 500px; height: 205px; text-align: center;"></div>
				</div>
			</div>
			<!----->
			<!----->
			<div class="span4 widget bordercolor fontSize" onTablet="span4"
				onDesktop="span4" style="margin-top: 20px; margin-bottom: 10px;">
				<div class="yuan_1"></div>
				<div class="yuan_2"></div>
				<div class="yuan_3"></div>
				<div class="yuan_4"></div>
				<div class="row-fluid">
					<h6>各监区警囚比</h6>
					<div class="boximg_r_img fl_t_rlist" id="genzhong_sjjhsy"
						style="width: 500px; height: 205px; text-align: center;"></div>
				</div>
			</div>
			<!----->

		</div>
	</div>
	<div class="clearfix"></div>
	<footer>
		<p>
			<span><a href="#" alt=""></a> 2019 &copy; 版权所有</span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<script src="${ctx}/static/sjfxyp/js/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/static/sjfxyp/js/bootstrap.min.js"></script>
	<!--自定义-->
	<script src="${ctx}/static/sjfxyp/js/mainSlm.js"></script>
	<!-- end: JavaScript-->
	<script src="${ctx}/static/sjfxyp/js/echarts.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var a = new Array("日", "一", "二", "三", "四", "五", "六");  
			var week = new Date().getDay();  
			var str = "星期"+ a[week];
			$('.datewrap').html("<%=today %>" + " <span class=\"ml15\">" + str + "</span>");
			//民警年龄构成
			findMjnlgc();
			//民警学历构成
			findMjxlgc();
			//历年民警数量
			findLnmjsl();
			//监内民警分布
			findJqmjsl();
			//机关民警分布
			findJgmjfb();
			//各监区警囚比
			findJqjqb();
		});

		//民警年龄构成
		function findMjnlgc() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findMjnlgc",
				dataType : 'json',
				success : function(data) {
					initMjnlgc(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//民警年龄构成
		function initMjnlgc(data) {
			var genzhong_mkrd = {

					tooltip : {
						trigger : 'axis',
						axisPointer : {
							type : 'shadow'
						}
					},

					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						top : '3%',
						containLabel : true
					},
					xAxis : {
						type : 'value',
						boundaryGap : [ 0, 0.01 ],
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					yAxis : {
						type : 'category',
						data : [ '20-25岁', '25-30岁', '30-35岁', '35-40岁', '40-45岁', '45岁以上' ],
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					series : [ {
						name : '',
						type : 'bar',
						data : data.data,
						itemStyle : {
							color : '#1E90FF'
						}
					} ]
				};

				var chartOutChar_genzhong_mkrd = null;
				chartOutChar_genzhong_mkrd = echarts.init(document.getElementById('genzhong_mkrd'));
				chartOutChar_genzhong_mkrd.setOption(genzhong_mkrd);
		};

		//民警学历构成
		function findMjxlgc() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findMjxlgc",
				dataType : 'json',
				success : function(data) {
					initMjxlgc(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//民警学历构成
		function initMjxlgc(data) {
			var genzhong_mkrdd = {

					tooltip : {
						trigger : 'axis',
						axisPointer : {
							type : 'shadow'
						}
					},

					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						top : '3%',
						containLabel : true
					},
					xAxis : {
						type : 'value',
						boundaryGap : [ 0, 0.01 ],
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					yAxis : {
						type : 'category',
						data : [ '专科以下', '高职专科', '大学本科', '研究生及以上' ],
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					series : [ {
						name : '',
						type : 'bar',
						data : data.data,
						itemStyle : {
							color : '#7FFFAA'
						}
					} ]
				};

				var chartOutChar_genzhong_mkrdd = null;
				chartOutChar_genzhong_mkrdd = echarts.init(document.getElementById('genzhong_mkrdd'));
				chartOutChar_genzhong_mkrdd.setOption(genzhong_mkrdd);
		};

		//历年民警数量
		function findLnmjsl() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findLnmjsl",
				dataType : 'json',
				success : function(data) {
					initLnmjsl(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//历年民警数量
		function initLnmjsl(data) {
			var genzhong_xtsy = {
					title : {

					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : []
					},
					toolbox : {
						show : true,
					},
					grid : {
						left : '0%',
						right : '8%',
						bottom : '0%',
						top : '5%',
						containLabel : true
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						data : [ '2013年', '2014年', '2015年', '2016年', '2017年', '2018年',
								'2019年' ],
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}

					} ],
					yAxis : [ {
						type : 'value',
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					} ],
					series : [ {
						name : '人数',
						type : 'line',
						smooth : true,
						itemStyle : {
							normal : {
								areaStyle : {
									type : 'default'
								}
							}
						},
						data : data.data,
						color : '#6495ED'

					} ]
				};
			var chartOutChar_genzhong_xtsy = null;
			chartOutChar_genzhong_xtsy = echarts.init(document.getElementById('genzhong_xtsy'));
			chartOutChar_genzhong_xtsy.setOption(genzhong_xtsy);
		};
		
		//监内民警分布
		function findJqmjsl() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findJqmjsl",
				dataType : 'json',
				success : function(data) {
					initJqmjsl(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//监内民警分布
		function initJqmjsl(data) {
			var genzhong_yzywsyqk = {
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b}: {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'center',
						data : []
					},
					series : [ {
						name : '人数',
						type : 'pie',
						radius : [ '50%', '80%' ],
						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '15',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : data.data
					} ],
					color : [ '#ADD8E6', '#00BFFF', '#CDAD00', '#4876FF', '#87CEFA',
							'#32CD32' ]
				};
				var chartOutChar_genzhong_yzywsyqk = null;
				chartOutChar_genzhong_yzywsyqk = echarts.init(document.getElementById('genzhong_yzywsyqk'));
				chartOutChar_genzhong_yzywsyqk.setOption(genzhong_yzywsyqk);
		};
		
		//机关民警分布
		function findJgmjfb() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findJgmjfb",
				dataType : 'json',
				success : function(data) {
					initJgmjfb(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//机关民警分布
		function initJgmjfb(data) {
			var genzhong_lcbjqk = {
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : []
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						top : '3%',
						containLabel : true
					},
					toolbox : {

					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : data.jgList,
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							interval : 0,
							rotate : 0,
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					yAxis : {
						type : 'value',
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					series : [ {
						name : '人数',
						type : 'line',
						stack : '人数',
						data : data.rsList,
						itemStyle : {
							color : '#FFFF00'
						}

					} ]
				};

				var chartOutChar_genzhong_lcbjqk = null;
				chartOutChar_genzhong_lcbjqk = echarts.init(document.getElementById('genzhong_lcbjqk'));
				chartOutChar_genzhong_lcbjqk.setOption(genzhong_lcbjqk);
		};

		//各监区警囚比
		function findJqjqb() {
			$.ajax({
				type : 'post',
				url : "${ctx}/sjfx/mjlzqk/findJqjqb",
				dataType : 'json',
				success : function(data) {
					initJqjqb(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		};
		
		//各监区警囚比
		function initJqjqb(data) {
			var genzhong_sjjhsy = {
					tooltip : {
						trigger : 'axis'
					},

					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						top : '3%',
						containLabel : true
					},

					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : data.jqList,
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							interval : 0,
							rotate : 0,
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					yAxis : {
						type : 'value',
						axisLine : {
							lineStyle : {
								type : 'solid',
								color : '#fff',//左边线的颜色
								width : '2'//坐标线的宽度
							}
						},
						axisLabel : {
							textStyle : {
								color : '#fff',//坐标值得具体的颜色

							}
						}
					},
					series : [ {
						name : '民警',
						type : 'line',
						stack : '人数',
						data : data.mjrsList,
						color : '#00FF00',
					}, {
						name : '罪犯',
						type : 'line',
						stack : '人数',
						data : data.zfrsList,
						color : '#FF00FF',
					}

					]
				};
				var chartOutChar_genzhong_sjjhsy = null;
				chartOutChar_genzhong_sjjhsy = echarts.init(document.getElementById('genzhong_sjjhsy'));
				chartOutChar_genzhong_sjjhsy.setOption(genzhong_sjjhsy);
		};
		
	</script>
</body>
</html>
