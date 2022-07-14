<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    UserBean user = AuthSystemFacade.getLoginUserInfo();
    Map map = new HashMap();
    map.put("orgCode", user.getOrgCode());
    map.put("orgName", user.getOrgName());
    map.put("cusNumber", user.getCusNumber());
    map.put("userId", user.getUserId());
    map.put("userName", user.getUserName());
    map.put("realName", user.getRealName());
    map.put("policeNo", user.getPoliceNo());
    map.put("dprtmntCode", user.getDprtmntCode());
    map.put("dprtmntName", user.getDprtmntName());
    map.put("roles", user.getRoles());
    map.put("orgClassKey", user.getOrgClassKey());
    map.put("userLevel", user.getUserLevel());
    map.put("isSpecialPolice", user.getIsSpecialPolice());
    request.setAttribute("map", new JSONObject(map));
%>

<html>

<head>
    <meta charset="utf-8">

    <title>${map.orgName}安全风险研判</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style-xiugai.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
    <link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .tooltip{
            width: 600px;
        }
    </style>
</head>
<body>
<!-- 头部 -->
<header class="perspective">
    <img src="${ctx}/static/bj-cui/img/command/logo_zhihui_${map.orgCode}.png" alt="指挥中心logo" class="logo">
    <div class="header-content">
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
        </div>
         --%>
    </div>
    <ul class="tolist home">
        <li class="tolist-item status home-page" onclick="openZnafpt()">
            首页
        </li>
        <li class="tolist-item status">
            评估分级指标管理
            <ul class="tolist-menu">
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'wwjgwh','五维架构维护')">
                    五维架构维护
                </li>
                <li class="tolist-menuitem"  onclick="openFxpgDialog(event,'fxdjwh','风险等级维护')">
                    风险等级维护
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'qzdjwh','权重等级维护')">
                    权重等级维护
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'sjlywh','数据来源维护')">
                    数据来源维护
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'sjfwgl','数据范围管理')">
                    数据范围管理
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'fxdgl','风险点管理')">
                    风险点管理
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'pgtjgl','评估条件管理')">
                    评估条件管理
                </li>
                <li class="tolist-menuitem" onclick="openFxpgDialog(event,'fxgkgl','预案统一管理')">
                    预案统一管理
                </li>
            </ul>
        </li>
        <li class="tolist-item status" onclick="openFxpgDialog(event, 'fxcj','风险采集管理')">
            风险采集管理
        </li>

    </ul>
</header>
<!-- 内容 -->
<div class="container-box">
    <div id="layout1">
        <!-- 上半部分 chart 图 -->
        <div data-options="region:'north'" class="main" style="height:50%;min-height: 410px">
            <div class="bottom">
                <div class="left-chart">
                    <h3>风险分布</h3>
                    <div id="blChart" class="chart-box"></div>
                </div>
                <div class="left-top">
                    <h3>
                        风险概况
                    </h3>
                    <div id="ltChart" class="ltChart"></div>
                </div>
                <div class="right-chart">
                    <h3>风险趋势</h3>
                    <div id="brChart" class="chart-box"></div>
                </div>
            </div>
        </div>
        <!-- 下半部分 chart 图 -->
        <div data-options="region:'south'" class="main2" style="height:430px;">
            <div class="left">

                <div class="left-bottom clearfix">
                    <h3>
                        风险评估
                    </h3>
                    <div class="clearfix">
                        <ul class="left-chart-box">
                            <li>
                                <div class="bgContent">
                                    <div class="left-pie" id="left-pie1"></div>
                                    <h4>风险指向</h4>
                                    <div class="right-pie" id="right-pie1"></div>
                                </div>

                            </li>
                            <li>
                                <div class="bgContent">
                                    <div class="left-pie" id="left-pie2"></div>
                                    <h4>风险指向</h4>
                                    <div class="right-pie" id="right-pie2"></div>
                                </div>
                            </li>
                            <li>
                                <div class="bgContent">
                                    <div class="left-pie" id="left-pie3"></div>
                                    <h4>风险指向</h4>
                                    <div class="right-pie" id="right-pie3"></div>
                                </div>
                            </li>
                            <li>
                                <div class="bgContent">
                                    <div class="left-pie" id="left-pie4"></div>
                                    <h4>风险指向</h4>
                                    <div class="right-pie" id="right-pie4"></div>
                                </div>
                            </li>
                            <li>
                                <div class="bgContent">
                                    <div class="left-pie" id="left-pie5"></div>
                                    <h4>风险指向</h4>
                                    <div class="right-pie" id="right-pie5"></div>
                                </div>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none;">
    <!-- 根路径 -->
    <input id="rootPath" name="rootPath" value="${ctx}" />
</div>
<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>



<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
<%--<script src="${ctx}/static/bj-cui/js/base-xiugai.js" type="text/javascript"></script>--%>
<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script src="${ctx}/static/js/callback/callback.js"></script>
<script src="${ctx}/static/js/sgzf/base64.js"></script>


<script type="text/javascript">
    /** 菜单显示方法*/
    $(".icon-htmal5icon35").click(function (e) {
        e.stopPropagation();
        $(".menu-container").toggleClass("active");
    })
    var target = $("body").not(".menu-wrapper");
    target = target.not(".icon-htmal5icon35")
    target.on("click", function (e) {
        var target = $(e.target).parents(".menu-wrapper");
        if (target.length) {
            return;
        }
        $(".menu-container").removeClass("active");
    })

    /**组件库初始化方法*/
    $('#layout1').layout({
        fit: true, //属性: 值
        onCreate: function () { //回调事件: 值

        }
    });
    $('#layout-child').layout({
        fit: true, //属性: 值
        onCreate: function () { //回调事件: 值

        }
    });
   $.parseDone(function () {
        $(".center:first").addClass("bigMore");
        jsConst.basePath = "${ctx}/";
        initUserInfo();
    })
    function showDqyh() {
        $("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME+ "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
    }
    function openZnafpt() {

        var url = "${ctx}/portal/bj/shouye";
        window.location.href = url;
    }
    function openCj(event, name,code){
        var event = window.event || event;
        //event.stopPropagation();
        if (event && event.stopPropagation) {
            event.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
        //event.preventDefault();
        var url = "";
        var w = null;
        var h = null;
        if (name == 'fxcj') {
            url = jsConst.basePath + '/aqfxyp/fxcj/toIndex';
            w = 1000;
            h = 800;
        }
        $('#dialog').html("");
        //$('#dialog').dialog("destroy");
        $('#dialog').dialog({
            width : w,
            height : h,
            title : "风险采集",
            url : url
        });
        $("#dialog").dialog("open");

    }
    function openFxpgDialog(event, name,zhi) {
        var event = window.event || event;
        //event.stopPropagation();
        if (event && event.stopPropagation) {
            event.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
        //event.preventDefault();
        var url = "";
        var w = null;
        var h = null;
        if (name == 'fxcj') {
            url = jsConst.basePath + '/aqfxyp/fxcj/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'wwjgwh') {
            url = jsConst.basePath + '/wwjg/wwjgwh/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'sjlywh') {
            url = jsConst.basePath + '/wwjg/sjlywh/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'fxdjwh') {
            url = jsConst.basePath + '/wwjg/fxdjwh/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'qzdjwh') {
            url = jsConst.basePath + '/wwjg/qzdjwh/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'sjfwgl') {
            url = jsConst.basePath + '/wwjg/sjfwgl/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'fxdgl') {
            url = jsConst.basePath + '/wwjg/fxdgl/toIndex';
            w = 1000;
            h = 800;
        } else if (name == 'pgtjgl') {
            url = jsConst.basePath + '/wwjg/pgtjgl/toIndex';
            w = 1000;
            h = 800;
        }else if (name == 'fxgkgl') {
            url = jsConst.basePath + '/wwjg/fxgkgl/toIndex';
            w = 1000;
            h = 800;
        }

        $('#dialog').html("");
        //$('#dialog').dialog("destroy");
        $('#dialog').dialog({
            width : w,
            height : h,
            title : zhi,
            url : url
        });
        $("#dialog").dialog("open");
    }

    $(function(){
        zxym();
    });
    function zxym(){

        var blChart,brChart,ltChart,pieChartList=[],barChartList=[];
         /**
         * Description: 初始化页面底部-风险分布图表数据
         */
        function initBottomChart(){
            blChart = echarts.init(document.getElementById('blChart'));

            var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxfbData";
            var params = {};
            // Desc: 调用ajax获取数据库中的风险分布数据
            $.ajax({
                type : 'post',
                url : urlPath,
                data : params,
                dataType : 'json',
                success : function(data) {
                    var option;
                    if(data.code == 200) {
                        if(data.data.length>12){
                            option = initBottomChartOptionDt(data.data);
                        }else{
                            option = initBottomChartOption(data.data);
                        }
                        blChart.setOption(option);
                        blChart.on("click", function (param){
                             var jQName;
                            if(param.name) {
                                jQName = new Base64().multiEncode(param.name, 2);
                            }
                            $("#dialog").dialog({
                                width : 1000, //属性
                                height : 800, //属性
                                title : '风险采集',
                                modal : true, //属性
                                autoOpen : false,
                                url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?wwName=&jQName="+jQName
                            });
                            $("#dialog").dialog("open");
                        })
                    } else if (data.code == 500){
                        console.log(data.data);
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });


        }

        /**
         * Description: 初始化页面底部-风险趋势图表数据
         */
        function initBottomChart2(){
            brChart = echarts.init(document.getElementById('brChart'));
            var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxqsData";
            var params = {};
            // Desc: 调用ajax获取数据库中的风险趋势数据
            $.ajax({
                type : 'post',
                url : urlPath,
                data : params,
                dataType : 'json',
                success : function(data) {
                    if(data.code == 200) {
                        var dataOption=data.data;  var date;
                        var option = initBottomChart2Option(dataOption);
                       brChart.setOption(option);
                        brChart.on("click", function (param){

                           for(var i=0;i<dataOption.length;i++){
                               if(param.name==dataOption[i].name){
                                   date=dataOption[i].score;
                               }
                           }
                            $("#dialog").dialog({
                                width : 1000, //属性
                                height : 800, //属性
                                title : '风险采集',
                                modal : true, //属性
                                autoOpen : false,
                                url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName=&date="+date
                            });
                            $("#dialog").dialog("open");
                        });
                    } else if (data.code == 500){
                        console.log(data.data);
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }

        /**
         * Description: 初始化首页面左侧风险概况图表数据
         */
        function initLeftChart() {
            ltChart = echarts.init(document.getElementById('ltChart'));
            var params = {};
            // Desc: 调用ajax获取数据库中的风险概况数据
            $.ajax({
                type : 'post',
                url : $("#rootPath").val() + '/aqfxyp/txzs/searchFxgk',
                dataType : 'json',
                async:false,
                success : function(data) {
                    var option = initLeftChartOption(data.data);
                    ltChart.setOption(option);

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });

        }


        function initBar() {


            $.ajax({
                type : 'post',
                url : $("#rootPath").val() + '/aqfxyp/txzs/getBars',
                dataType : 'json',
                async:false,
                success : function(data) {
                    var dada = data.data;
                    for(var i = 0;i<dada.length;i++){
                        var wwjgname = dada[i].wwjgname;
                        var wwjgjg = dada[i].wwjgjg;
                        if(wwjgname=="人"){
                            var option = initBarOption(wwjgjg, "人");
                            var barChart = echarts.init(document.getElementById("right-pie1"));
                            barChart.setOption(option);
                            barChartList.push(barChart);
                        }
                        if(wwjgname=="地"){
                            var option = initBarOption(wwjgjg, "地");
                            var barChart = echarts.init(document.getElementById("right-pie2"));
                            barChart.setOption(option);
                            barChartList.push(barChart);
                        }
                        if(wwjgname=="事"){
                            var option = initBarOption(wwjgjg, "事");
                            var barChart = echarts.init(document.getElementById("right-pie3"));
                            barChart.setOption(option);
                            barChartList.push(barChart);
                        }
                        if(wwjgname=="物"){
                            var option = initBarOption(wwjgjg, "物");
                            var barChart = echarts.init(document.getElementById("right-pie4"));
                            barChart.setOption(option);
                            barChartList.push(barChart);
                        }
                        if(wwjgname=="情"){
                            var option = initBarOption(wwjgjg, "情");
                            var barChart = echarts.init(document.getElementById("right-pie5"));
                            barChart.setOption(option);
                            barChartList.push(barChart);
                        }

                    }

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }

        /**
         * Description: 初始化饼图数据
         */
        function initPies() {

            $.ajax({
                type : 'post',
                url : $("#rootPath").val() + '/aqfxyp/txzs/getPies',
                dataType : 'json',
                async:false,
                success : function(data) {
                    debugger;
                    var dada = data.data;
                    for(var i = 0;i<dada.length;i++){
                        var wwjgname = dada[i].wwjgname;
                        var wwjgjg = dada[i].wwjgjg;
                        if(wwjgname=="人"){
                            var option = initPiesOption(wwjgjg, "人");
                            var pieChart = echarts.init(document.getElementById("left-pie1"));
                            pieChart.setOption(option);
                            pieChart.on("click", function (param){
                                wwName = new Base64().multiEncode("人", 2);
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
                                });
                                $("#dialog").dialog("open");
                            });
                            pieChartList.push(pieChart);
                        }
                        if(wwjgname=="地"){
                            var option = initPiesOption(wwjgjg, "地");
                            var pieChart = echarts.init(document.getElementById("left-pie2"));
                            pieChart.setOption(option);
                            pieChartList.push(pieChart);
                            pieChart.on("click", function (param){
                                wwName = new Base64().multiEncode("地", 2);
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
                                });
                                $("#dialog").dialog("open");
                            });
                        }
                        if(wwjgname=="事"){
                            var option = initPiesOption(wwjgjg, "事");
                            var pieChart = echarts.init(document.getElementById("left-pie3"));
                            pieChart.setOption(option);
                            pieChartList.push(pieChart);
                            pieChart.on("click", function (param){
                                wwName = new Base64().multiEncode("事", 2);
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
                                });
                                $("#dialog").dialog("open");
                            });
                        }
                        if(wwjgname=="物"){
                            var option = initPiesOption(wwjgjg, "物");
                            var pieChart = echarts.init(document.getElementById("left-pie4"));
                            pieChart.setOption(option);
                            pieChartList.push(pieChart);
                            pieChart.on("click", function (param){
                                wwName = new Base64().multiEncode("物", 2);
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
                                });
                                $("#dialog").dialog("open");
                            });
                        }
                        if(wwjgname=="情"){
                            var option = initPiesOption(wwjgjg, "情");
                            var pieChart = echarts.init(document.getElementById("left-pie5"));
                            pieChart.setOption(option);
                            pieChartList.push(pieChart);
                            pieChart.on("click", function (param){
                                wwName = new Base64().multiEncode("情", 2);
                                $("#dialog").dialog({
                                    width : 1000, //属性
                                    height : 800, //属性
                                    title : '风险采集',
                                    modal : true, //属性
                                    autoOpen : false,
                                    url : $("#rootPath").val() + "/aqfxyp/fxcj/listNe?jQName=&wwName="+wwName
                                });
                                $("#dialog").dialog("open");
                            });
                        }

                    }

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }

        // Description: 风险评估图
        initPies();


        // Description: 风险指向图
        initBar();

        initLeftChart();

        // Description: 风险分布图
        initBottomChart();

        // Description: 风险趋势图
        initBottomChart2();

        /**
         * Description: 页面大小调整时，显示数据图表大小随之调整
         */
        window.onresize = function () {
            setTimeout(function () {
                blChart.resize();
                brChart.resize();
                ltChart.resize();
                var length = pieChartList.length;
                for(var j=length; j--;) {
                    pieChartList[j].resize();
                    barChartList[j].resize();
                }
            },200);
        }
    }
    /**
     * Desc:初始化风险分布图Option
     */
    function initBottomChartOptionDt(chartData) {
        var xData = [], yData = [], option;

        chartData.map(function(a, b) {
            xData.push(a.name);
            yData.push(a.value);
        });
        option = {
            backgroundColor: "transparent",
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
            },
            legend: {
                show: false
            },
            grid: {
                left: '5%',
                right: '1%',
                bottom: '10%',
                top: '12%',
                height: '75%',
                containLabel: true,

            },
            xAxis: [{
                type: 'category',
                data: xData,
                interval: 1,
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: '#0c3b71'
                    }
                },
                axisLabel: {
                    show: true,
                    color: '#aeafb0',
                    fontSize: 12,
                    interval: 0,
                    rotate:60
                }
            }],
            yAxis: [{
                type: 'value',

                splitLine: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: 'transparent'
                    }
                }
            }],
            series: [{
                name: '总分',
                type: 'bar',
                barWidth: 35,
                xAxisIndex: 0,
                barCategoryGap: 3,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#01f5f7'
                            },

                                {
                                    offset: 1,
                                    color: '#125375'
                                }
                            ]
                        )
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color: "#a6a7a9",
                        fontSize: 12
                    }
                },
                data: yData,
                zlevel: 11

            }],
            dataZoom: [{
                type: 'slider',
                show: true,
                start: 0,
                end: 50,
                handleSize: 8,
                textStyle: {
                    color: "#fff"
                }
            },
                {
                    type: 'inside',
                    start: 0,
                    end: 50
                },

            ]
        };

        return option;
    }

    /**
     * Desc:初始化风险分布图Option
     */
    function initBottomChartOption(chartData) {
        var xData = [], yData = [], option;

        chartData.map(function(a, b) {
            xData.push(a.name);
            yData.push(a.value);
        });
        option = {
            backgroundColor:"transparent",
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
            },
            legend: {
                show: false
            },
            grid: {
                left: '0%',
                right: '0%',
                bottom: '10%',
                top: '15%',
                height: '81%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: xData,
                interval:1,
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: '#0c3b71'
                    }
                },
                axisLabel: {
                    show: true,
                    color: '#aeafb0',
                    fontSize: 12,
                    interval: 0,//改完怎么看效果？？？
                    rotate:30
                }
            }],
            yAxis: [{
                type: 'value',
                splitLine: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: 'transparent'
                    }
                }
            }],
            series: [{
                name: '总分',
                type: 'bar',
                barWidth: 35,
                xAxisIndex: 0,
                barCategoryGap:3,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#01f5f7'
                        },
                            {
                                offset: 1,
                                color: '#125375'
                            }])
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color:"#a6a7a9",
                        fontSize:12
                    }
                },
                data: yData,
                zlevel: 11
            }]
        };
        return option;
    }

    /**
     * desc: 初始化风险趋势图Option
     */
    function initBottomChart2Option(chartData) {
        var xData = [], yData = [], option,year=[];

        chartData.map(function(a, b) {
            xData.push(a.name);
            yData.push(a.value);

        });
        option = {
            backgroundColor: "transparent",
            tooltip: {
            },
            grid: {
                left: '0%',
                right: '3%',
                bottom: '10%',
                top: '15%',
                height: '82%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                interval:0,
                boundaryGap: false,
                axisLine: { //坐标轴轴线相关设置。数学上的x轴
                    show: true,
                    lineStyle: {
                        color: '#233e64'
                    }
                },
                axisLabel: { //坐标轴刻度标签的相关设置
                    textStyle: {
                        color: '#a6a6a7',
                        margin:15,
                    }
                },
                axisTick: {
                    show: false
                },
                data: xData
            }],
            yAxis: [{
                type: 'value',
                splitNumber: 5,
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#233e64'
                    }
                },
                axisLine: {
                    show: false
                },
                axisLabel: {
                    margin:20,
                    textStyle: {
                        color: '#a6a6a7'
                    }
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                name: '扣分',
                type: 'line',
                smooth: false, //是否平滑曲线显示
                symbol:'circle',  // 默认是空心圆（中间是白色的），改成实心圆

                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        distance:10,
                        color:"#3deaff",
                        fontSize:12
                    }
                },
                lineStyle: {
                    normal: {
                        //  color: "#3deaff"   // 线条颜色
                    }
                },
                itemStyle: {
                    normal: {
                        color: "#3deaff",  // 会设置点和线的颜色，所以需要下面定制 line
                    }
                },
                areaStyle: { //区域填充样式
                    normal: {
                        //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,  color: 'rgba(61,234,255, 0.9)'
                        },
                            {
                                offset: 0.7,  color: 'rgba(61,234,255, 0.1)'
                            }], false),
                        shadowColor: 'rgba(53,142,215, 0.9)', //阴影颜色
                        shadowBlur: 5//shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
                    }
                },
                data: yData
            }]
        };
        return option;
    }
    /**
     * desc: 初始化条形图Option
     */
    function initBarOption(chartData) {
        var yData = [], seriesData = [], valData = [];
        var myColor = ['#33b7e0', '#33b7e0', '#33b7e0', '#33b7e0'];
        chartData.map(function(a, b) {
            yData.push(a.name);
            seriesData.push(a.value);
            valData.push(a.score);
        });
        var option = {
            backgroundColor: 'transparent',
            grid: {
                left: '30%',
                right: '17%',
                bottom: '0',
                top: '3%'
                // z: 22
            },
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: yData,
                inverse: true,
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        fontSize: 14,
                        color: '#fff',
                    }
                },
            }, {
                show: true,
                inverse: true,
                data: seriesData,
                axisLabel: {
                    textStyle: {
                        fontSize: 14,
                        color: '#fff',
                    },
                },
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                name: '条',
                type: 'bar',
                yAxisIndex: 1,
                data: seriesData,
                barWidth: 6,
                barCategoryGap:2,
                itemStyle: {
                    normal: {
                        barBorderRadius: 20,
                        color: function(params) {
                            var num = myColor.length;
                            return myColor[params.dataIndex % num]
                        }
                    }
                },
                label: {
                    normal: {
                        show: false
                    }
                }
            }]
        };
        return option;
    }
    /**
     * desc: 初始化风险概况图Option
     */
    function initLeftChartOption(dada) {

        var seriesData ;
        var radarData;
        var zf;
        /*fxgkData.map(function(a, b) {
         total += a.value;
         seriesData.push(a.value);
         var radar = {
         name:a.name,
         num:a.score
         };
         radarData.push(radar);
         });
         */
        radarData = dada[0];
        seriesData = dada[2];
        zf = dada[1];
        var option = {
            grid: {
                left: '-5%',
                right: '-5%',
                bottom: '0',
                top: '1%',
            },
            backgroundColor: 'transparent',
            title: {
                text: zf,
                x: '42%',
                y: 'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 60
                },
                zlevel: 24
            },
            radar: [{
                title: {
                    text: zf,
                },
                indicator: radarData,
                center: ['55%', '50%'],
                shape: 'polygon',
                radius: '80%',
                nameGap: 3,
                name: {
                    formatter: function(value, obj) {
                        var num = obj.num
                        return '{value|' + value + '}' + ' {num|' + num + '}';
                    },
                    rich: {
                        num: {
                            color: "#ee8c04",
                            fontSize: 24,
                            // padding: [0, 2],
                            align: 'center'
                        },
                        value: {
                            color: "#c9caca",
                            fontSize: 25,
                            align: 'center'
                        }
                    },
                    textStyle: {
                        color: '#72ACD1'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#cc9b06'
                    },
                    itemStyle: {
                        color: '#cc9b06'
                    }
                },
                splitArea: {
                    areaStyle: {
                        color: 'transparent'
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#cc9b06'
                    }
                }

            }],
            series: [{
                type: 'radar',
                symbolSize: 0,
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#96750b'
                        }, {
                            offset: 0.5,
                            color: '#715b0e'
                        }, {
                            offset: 1,
                            color: '#171812'
                        }], false)
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#cc9b06',
                        type: 'solid',
                        width: 1
                    }
                },
                data: [{
                    value: seriesData,
                    label: {
                        show: 'true'
                    }
                }]
            }]
        };
        return option;
    }
    /**
     * desc: 初始化饼图Option
     */
    function initPiesOption(fxpgData, title) {
        var echartData = [], rate = 0;
        fxpgData.fxpgList.map(function(a, b) {
            rate += a.value;
            var pieData = {
                name:a.name,
                value:a.value
            };
            echartData.push(pieData);
        });
        var scale = 1;
        var rich = {
            rate: {
                color: "#00ffff",
                fontSize: 18 ,
                // padding: [0, 2],
                align: 'center'
            },
            total: {
                color: "#c9caca",
                fontSize: 18 ,
                align: 'center'
            }
        };
        var option = {
            grid: {
                bottom: '2%',
                top: '2%'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c}"
            },
            backgroundColor: 'transparent',
            title: {
                text:title,
                left:'center',
                top:'10%',
                padding:[24,0],
                textStyle:{
                    color:'#c9caca',
                    fontSize:16,
                    align:'center'
                }
            },
            legend: {
                selectedMode:false,
                formatter: function(name) {
                    //var total = fxpgData.score; //各科正确率总和
                    // var rate = 1000;
                    var averagePercent; //综合正确率

                    return '{rate|' + (parseFloat(rate)).toFixed(2) + '}';
                },
                data: [echartData[0].name],
                // data: ['高等教育学'],
                // itemGap: 50,
                left: 'center',
                top: '45%',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#c9caca",
                    fontSize: 16 * scale,
                    rich: rich
                },
            },
            series: [{
                name: '扣分',
                type: 'pie',
                radius: ['86%', '71%'],
                hoverAnimation: false,
                color: ['#457ab5', '#457ab5', '#457ab5', '#457ab5', '#457ab5'],
                label:{
                    normal: {
                        show: false
                    },
                },
                itemStyle: {
                    normal: {
                        borderWidth: 1,
                        borderColor: '#000',
                    }
                },
                data: echartData
            }]
        };
        return option;
    }
    function fhjzcyz (){

        zxym();
    }
</script>
</body>
</html>
