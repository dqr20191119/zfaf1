<!DOCTYPE html>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
UserBean user = AuthSystemFacade.getLoginUserInfo();
 String org1 = user.getOrgName();
 String orgcode1 = user.getOrgCode();
 String orgName = user.getOrgName();
 if(orgName.indexOf("北京市")!=-1){
	 orgName = orgName.replace("北京市", "");
 }


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

	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
	<link href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/bj-cui/css/svg-new.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/bj-cui/css/wangge-new.css" rel="stylesheet" type="text/css" />
  
</head>
<body>
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_edit" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<cui:dialog id="dialogId_yrzq_List" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	<div id="dialog_mjxx"></div>
	<header class="wangge">
	<jsp:include page="bjheader.jsp"></jsp:include>
    <%-- <img src="${ctx}/static/bj-cui/img/command/logo_zhihui2.png" alt="指挥中心logo" class="logo">
		<div class="header-content">
			<div class="header-item">
				<span class="icon iconfont icon-police2"></span>
				<span class="title" id="dqyh">当前用户：</span>
			</div>
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
			<div class="header-item">
				<span class="icon iconfont icon-system-setting" title="退出系统" style="cursor: pointer;" onClick="syLogout();"></span>
			</div>
		</div>
		<ul class="tolist home" style="    margin: 0 auto;    float: left;">
			<li class="tolist-item status home-page" onClick="toZhsy();">
				首页
			</li>
			
			 
			
			<li class="tolist-item status" onclick="openAqfxypSystem()">
				安全风险研判
			</li>
			
			<li class="tolist-item status">
				网格化管理
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						智能统计
						<ul>
							<li class="tolist-menuitem">
								民警信息统计
							</li>
							<li class="tolist-menuitem">
								罪犯信息统计
							</li>
							<li class="tolist-menuitem">
								警情统计
							</li>
							<li class="tolist-menuitem">
								电网状态统计
							</li>
							<li class="tolist-menuitem">
								事物督办统计
							</li>
							<li class="tolist-menuitem">
								设备统计
							</li>
							<li class="tolist-menuitem">
								外来车俩统计
							</li>
							<li class="tolist-menuitem">
								外来人员统计
							</li>
							<li class="tolist-menuitem">
								行为侦测统计
							</li>
							<li class="tolist-menuitem">
								非法手机统计
							</li>
							<li class="tolist-menuitem">
								劳动工具发放统计
							</li>
							<li class="tolist-menuitem">
								敏感词统计
							</li>
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjkgjl')">
								门禁开关记录
							</li>
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjxcjl')">
								民警巡查记录
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						一日执勤
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jhrc')">
								计划日程
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'zqgl')">
								执勤管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jndtcx')">
								监内动态查询
							</li>
							<!-- <li class="tolist-menuitem">
								监内动态统计
							</li> -->
						</ul>
					</li>
					<li class="tolist-menuitem">
						网格划分
						<ul>
							<li class="tolist-menuitem" onclick="openWg()">
								网格化管理
							</li>
							<li class="tolist-menuitem">
								安全网格
							</li>
							<li class="tolist-menuitem" onclick="openDjwg()">
								党建网格
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						值排班
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'lbgl')">
								类别管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'gwgl')">
								岗位管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'bcgl')">
								班次管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'mbsz')">
								模板设置
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbbp')">
								值班编排
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbcx')">
								值班查询
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班分析
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班日志
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								交接班
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem">
						数据接报
						<ul>
							<li class="tolist-menuitem">
								任务下发
								<ul>
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'xfrw')">
										下发任务
									</li>
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jsrw')">
										接收任务
									</li>
								</ul>
							</li> 
							<li class="tolist-menuitem">
								待办事项
							</li>
							<li class="tolist-menuitem">
								报表报送
							</li>
							<li class="tolist-menuitem">
								通知公告
							</li>
						</ul>
					</li>
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jdjc')">
						监督检查
					</li>
				</ul>
			</li>
				
			<li class="tolist-item status">
				生物识别
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						运动轨迹
						<ul class="tolist-menuitem">
							<li class="tolist-menuitem">
								依图运动轨迹
								 <ul class="tolist-menuitem">
								 	<li class="tolist-menuitem" onclick="openDlyz()">
								 		登陆认证
								 	</li>
								 	<li class="tolist-menuitem" onclick="openRygj()">
								 		人员轨迹
								 	</li>
								 	<li class="tolist-menuitem" onclick="openRlsb()">
								 		人脸识别
								 	</li>
								 </ul>
							</li>
							<li class="tolist-menuitem" onclick="openHkxt()">
								海康运动轨迹
							</li>
						</ul>
					</li>
				</ul>
			</li>
			
			<li class="tolist-item status" onclick="openZnyysb()">
				语音识别
			</li>
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
				 
			</li>
			<li class="tolist-item status">
				移动警务
				<ul class="tolist-menu">
					<li class="tolist-menuitem">
						移动终端数量统计
					</li>
					<li class="tolist-menuitem">
						移动终端状态统计
					</li>
					<li class="tolist-menuitem">
						个别谈话情况统计
					</li>
					<li class="tolist-menuitem">
						移动端点名统计
					</li>
					<li class="tolist-menuitem">
						证据采集情况统计
					</li>
					<li class="tolist-menuitem">
						移动OA使用情况统计
					</li>
				</ul>
			</li>
		</ul> --%>
  </header>
	 
 <div class="wangge-container">
    <div class="wangge-center">
      <h1 class="title"><%=orgName %>党建网格</h1>
      <div class="table-wrapper">
        <div id="grid1"></div>
      </div>
      <div class="wangge-gradient"></div>
    </div>
    <div class="wangge-bottom">
      <div class="bottom-wrapper">
        <div class="bottom-banzi">
          党委班子成员：<span class="banzi-amount"></span>人
        </div>
        <div class="bottom-other">
          <ul class="other-info">
            <!-- <li>分管部门基层党建联系点：<span class="department-amount"></span></li>
            <li>党员：<span class="dangyuan-amount"></span></li>
            <li>申请入党人员：<span class="shenqing-amount"></span></li>
            <li>党支部书记支部委员会：<span class="shuji-amount"></span></li>
            <li>入党积极分子：<span class="jijifen-amount"></span></li>
            <li>党员责任区群众：<span class="qunzhong-amount"></span></li> -->
          </ul>
        </div>
        <div class="bottom-search">
          <input type="text" id="searchBox">
        </div>
      </div>
    </div>
  </div>	 
	 



<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script src="${ctx}/static/js/callback/callback.js"></script>
<script type="text/javascript" src="${ctx}/static/bj-cui/js/jyshouye.js"></script>
<script>
		jsConst.basePath = "${ctx}/";
</script>

 
<script type="text/javascript">
$.parseDone(function () {
	initGrin();
	initUserInfo();
	
});
var jyId="<%=orgcode1%>";
	var centerWidth = $(".wangge-center").width(),
	eachWidth = centerWidth / 8;
	restWidth = centerWidth / 8 * 2;
	var departmentAmount = 0,
	dangyuanAmount = 0,
	shenqingAmount = 0,
	shujiAmount = 0,
	jijifenAmount = 0,
	qunzhongAmount = 0;
	var textArr = {};
	
	function formatItem(e, ui) {
		/* console.log(ui.colModel.name);
		 if (e && ui.colModel.name == "department") {
		  departmentAmount++;
		}  */
		return "<div class='content-item " + ui.colModel.name +
		  "'><div class='left-top'></div><div class='line-wrapper'><div class='line'></div></div><div class='right-bottom'></div><div class='bottom-line'></div><span class='item-text text-content'>" +
		  e + "</span></div>"
	}
	
	function formatUl(e, ui) {
		var type = typeof (e);
		var item = type == "object" ? e : (type == "string" ? e.split(",") : []),
		  length = item.length,
		  colName = ui.colModel.name;
		if(ui.colModel.name==jyId+"_DY"||length>7){
			content = "<div class='content-item dangyuan'><div class='left-top'></div><div class='right-bottom'></div><div class='bottom-line'></div><ul class='item-list position-center'>";
			
		}else{
			content = "<div class='content-item " + colName +
			 	 "'><div class='left-top'></div><div class='right-bottom'></div><div class='bottom-line'></div><ul class='item-list position-center'>";

		}
		//content = "<div class='content-item " + colName +
	 //	 "'><div class='left-top'></div><div class='right-bottom'></div><div class='bottom-line'></div><ul class='item-list position-center'>";
	//content = "<div class='content-item dangyuan'><div class='left-top'></div><div class='right-bottom'></div><div class='bottom-line'></div><ul class='item-list position-center'>";
		 for (var i = 0; i < length; i++) {
		 /*  switch (colName) {
		    case "dangyuan":
		      dangyuanAmount++;
		      break;
		    case "shenqing":
		      shenqingAmount++;
		      break;
		    case "shuji":
		      shujiAmount++;
		      break;
		    case "jijifenzi":
		      jijifenAmount++;
		      break;
		    case "qunzhong":
		      qunzhongAmount++;
		      break;
		    default:
		      break;
		  } */
		  content += "<li><span class='text-content'>" + item[i] + "</span></li>";
	} 
	content += "</ul></div>";
	return content;
	}
	
	
	
	
	/* var gridColModel = [{
	  label: "id",
	  name: "id",
	  hidden: true,
	  sortable: false,
	  width: eachWidth
	},
	{
	  label: "<div class=\"header-item\">党委班子成员</div>",
	  name: "name",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatItem
	},
	{
	  label: "<div class=\"header-item two-lines\">分管部门<br>基层党建联系点</div>",
	  name: "department",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatItem
	},
	{
	  label: "<div class=\"header-item two-lines\">党支部书记<br>支部委员会</div>",
	  name: "shuji",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatUl
	},
	{
	  label: "<div class=\"header-item\">党员</div>",
	  name: "dangyuan",
	  sortable: false,
	  width: restWidth,
	  formatter: formatUl
	},
	{
	  label: "<div class=\"header-item\">入党积极分子</div>",
	  name: "jijifenzi",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatUl
	},
	{
	  label: "<div class=\"header-item\">申请入党人员</div>",
	  name: "shenqing",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatUl
	},
	{
	  label: "<div class=\"header-item\">党员责任区群众</div>",
	  name: "qunzhong",
	  sortable: false,
	  width: eachWidth,
	  formatter: formatUl
	}
	
	];  */
	
	var gridColModel;
	var data;
	function initGrin(){
		$.ajax({
			type : 'post',
			url : '${ctx}/djwg/rywh/getDjwg',
			dataType : 'json',
			async:false,
			success : function(data1) {
				gridColModel="";
				data="";
				$(".other-info").html("");
				
				gridColModel = data1.data.gridColModel;
				data = data1.data.zsData;
				var zhrs = data1.data.zhrs;
				$(".other-info").html(zhrs);
				
				 for(var i = 0 ;i < gridColModel.length; i++){
					var map = gridColModel[i];
					var formatcss = formatUl;
					var code = map.name;
					
					if(code==jyId+"_DWBZCY"||code==jyId+"_FGBMJCDJLXD"){
						formatcss = formatItem;
					}
					var windthcss = restWidth;
					var length = 0;
					for(var j = 0;j<data.length;j++){
						var dataList = data[j];
						var hqdLsit = dataList[code];
						var typee=typeof (hqdLsit);
						console.log(typee);
						if(typee=="object")
						length = hqdLsit.length;
						break;
					}
					if(length<=7){
						windthcss = eachWidth;
					}
					if(code=="id"){
						map.hidden = true;
					}
					map.sortable = false;
					map.width = windthcss;
					map.formatter = formatcss;
				} 
				 
				 
				/* gridColModel = [{
					  label: "id",
					  name: "id",
					  hidden: true,
					  sortable: false,
					  width: eachWidth
					},
					{
					  label: "<div class=\"header-item\">党委班子成员</div>",
					  name: "name",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatItem
					},
					{
					  label: "<div class=\"header-item two-lines\">分管部门<br>基层党建联系点</div>",
					  name: "department",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatItem
					},
					{
					  label: "<div class=\"header-item two-lines\">党支部书记<br>支部委员会</div>",
					  name: "shuji",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatUl
					},
					{
					  label: "<div class=\"header-item\">党员</div>",
					  name: "dangyuan",
					  sortable: false,
					  width: restWidth,
					  formatter: formatUl
					},
					{
					  label: "<div class=\"header-item\">入党积极分子</div>",
					  name: "jijifenzi",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatUl
					},
					{
					  label: "<div class=\"header-item\">申请入党人员</div>",
					  name: "shenqing",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatUl
					},
					{
					  label: "<div class=\"header-item\">党员责任区群众</div>",
					  name: "qunzhong",
					  sortable: false,
					  width: eachWidth,
					  formatter: formatUl
					}
					
					];  */
				/*  data =  [
				        	{
				        		"id": "local_1",
				        		"name": "刑玫<br>党委书记 | 监狱长",
				        		"department": "指挥中心党支部",
				        		"shuji": ["郑哲磊", "窦万山", "李振辉", "胡焕美", "王硕"],
				        		"dangyuan": ["郑哲磊", "李振辉", "窦万山", "赵志涛", "胡焕美", "王硕", "吉晓波", "张金宽", "齐艳宏", "王海艳", "李春燕", "张思明", "李硕", "杨国英",
				        			"张德文", "闫鹏", "于东洋", "段继祥", "高战", "张伟平", "李洋", "邢晨思", "王帅", "马元", "马连梁", "孙高洁"
				        		],
				        		"jijifenzi": ["李彦杰"],
				        		"shenqing": [],
				        		"qunzhong": ["赵强","马彦伟"]
				        	}
				        	, 
				        	{
				        		"id": "local_3",
				        		"name": "刑玫<br>党委书记 | 监狱长",
				        		"department": "三监区党支部",
				        		"shuji": ["张海娜", "安娜", "李苑", "刘冉冉", "李倩"],
				        		"dangyuan": ["张海娜", "安娜", "李苑", "刘冉冉", "李倩", "郑玉梅", "李植", "刘丽会", "吴金凤", "陈宗梅", "张海燕", "闫伟", "蔡淑红", "鲁敏",
				        			"岳明", "王嘉炜","肖语南"
				        		],
				        		"jijifenzi": ["邱迪", "沈绯绯", "于涵"],
				        		"shenqing": ["李杭琦", "李聪", "马晓荟"],
				        		"qunzhong": []
				        	}
				        	, 
				        	
				        	{
				        		"id": "local_2",
				        		"name": "李红新<br>政委",
				        		"department": "监察审计科党支部",
				        		"shuji": ["唐凤君"],
				        		"dangyuan": ["唐凤君", "孟佳", "戴春芳", "张华", "高素平", "高凯", "刘放", "赵琳"
				        		],
				        		"jijifenzi": [],
				        		"shenqing": [],
				        		"qunzhong": []
				        	}
				        	, 
				        	{
				        		"id": "local_4",
				        		"name": "李红新<br>政委",
				        		"department": "四监区党支部",
				        		"shuji": ["刘丽丽", "牛娜", "李红颖", "侯艳丽", "杜莉微"],
				        		"dangyuan": ["刘丽丽", "牛娜", "李红颖", "侯艳丽", "杜莉微", "王冰", "李萍", "杨春丽", "黄明燕", "杨丽梅", "刘飞", "魏春晶", "郭兰香", "高素梅",
				        		  "王晓梅"
				        		],
				        		"jijifenzi": ["李瑶", "胡昕"],
				        		"shenqing": ["郭颖", "郭雨芳", "秦晶晶", "王新蕊"],
				        		"qunzhong": ["王霞"]
				        	}, 
				        	{
				        		"id": "local_5",
				        		"name": "吴建华<br>副监狱长",
				        		"department": "行政科党支部",
				        		"shuji": ["李润波", "姜慧芳", "李金山"],
				        		"dangyuan": ["李润波", "姜慧芳", "李金山", "俎晓忠", "刘红宾", "付建伟", "李林海", "张秀元", "王剑斌", "李山", 
				        		  "赵晓坤"
				        		],
				        		"jijifenzi": ["吴红敏", "游良轩", "李彦卫", "王健", "高江","韩建勇","伍宏伟"],
				        		"shenqing": ["索国强", "于成洋", "张京磊"],
				        		"qunzhong": ["王晓华", "张星宇", "吴绍晨", "李莉", "宋秀云","王晨萍","赵宇鹏", "郑超", "郝剑", "孙希宁","黄亚中",
				        			"李兴华","刘燕华","李文胜","方刚","王栋","王建康","田利新","刘永胜","王青","张福亮","李晋华","胡微"]
				        	}, 
				        	{
				        		"id": "local_6",
				        		"name": "杨梅青<br>副监狱长",
				        		"department": "九监区党支部",
				        		"shuji": ["陈熹微", "张丽娟", "刘君", "岳海艳", "唐丽琴"],
				        		"dangyuan": ["陈熹微", "张丽娟", "刘君", "岳海艳", "唐丽琴", "岳海艳", "李微", "高希", "高妍", "李远芳", "魏婕",
				        			"沈晓宇", "程思思", "余赛", "孙凌然","麻莉","郝俊平"
				        		],
				        		"jijifenzi": ["郑童芳","常淮秀","张晶媛"],
				        		"shenqing": ["朱冬青"],
				        		"qunzhong": ["李莹"]
				        	}
				        	];   */
				 
				        	$("#grid1").grid({
					        	data: data,
					        	onComplete: completefun,
					        	colModel: gridColModel,
					        	datatype: "local",
					        	fitStyle: "height",
					        	shrinkToFit: false,
					        	componentCls: "wangge"
				        	
				        	})
				        	$("#searchBox").textbox({
				        		placeholder: "请输入内容",
				        		buttons: [{
					        	  text: true,
					        	  label: "搜索",
					        	  icons: "iconfont icon-sousuo",
					        	  click: function (e, data) {
					        	    var str_1 = $("#searchBox").textbox("getValue");
					        	    var textboxValue = str_1.replace(/^\s*|\s*$/g,"");
					        	    if(textboxValue.length<1){
					        	    	return;
					        	    }
					        	    var tmpArr = textArr,
					        	        length = textArr.length;
					        	    for(var k=length;k--;) {
					        	      var items = $(tmpArr[k]);
					        	      if(items.html().indexOf(textboxValue) > -1) {
					        	        items.addClass("active");
					        	      } else {
					        	        items.removeClass("active");
					        	      }
					        	    }
					        	  }
					        	}]
				        	});
				
				
				
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	
 
	function completefun(e, ui) {
	//         主列         需要合并的列
	mergeCell(jyId+"_DWBZCY", [jyId+"_DWBZCY"])
	 
	 $(".banzi-amount").text($(".content-item."+jyId+"_DWBZCY:visible").length);
	/*$(".department-amount").text(departmentAmount);
	$(".dangyuan-amount").text(dangyuanAmount);
	$(".shenqing-amount").text(shenqingAmount);
	$(".shuji-amount").text(shujiAmount);
	$(".jijifen-amount").text(jijifenAmount);
	$(".qunzhong-amount").text(qunzhongAmount); */
	if ($(".coral-grid-rows-view", $(e.target)).hasClass("ctrl-init")) {
	  $(".coral-grid-rows-view", $(e.target)).mCustomScrollbar("destroy");
	}
	$(".coral-grid-rows-view", $(e.target)).mCustomScrollbar({
	  axis: "y",
	  theme: "minimal-dark",
	  scrollbarPosition: "outside",
	});
	textArr = $(".text-content")
	};
	
	function mergeCell(primaryName, names) {
	var gridName = "grid1",
	  rowsp,
	  primaryCellName = gridName + "_" + primaryName;
	//获取所有tr（不包括隐藏行）
	var trs = $("#" + gridName).find("tbody").find("tr:gt(0)"),
	  primaryColIndex = trs.eq(0).children("[aria-describedby='" + primaryCellName + "']").index();
	
	
	$.each(names, function (ind, name) {
	  var cellName = gridName + "_" + name,
	    bg = trs.eq(0).children("[aria-describedby='" + cellName + "']"),
	    primaryCell = trs.eq(0).children("[aria-describedby='" + primaryCellName + "']");
	  index = bg.index();
	  rowsp = 1;
	  //从1开始遍历tr，和第一行td对比
	  trs.slice(1).each(function (ind2, tr) {
	    var me = $(tr).children("td").eq(index),
	      primaryColMe = $(tr).children("td").eq(primaryColIndex);
	    if (primaryCell.text() == primaryColMe.text() && bg.text() === me.text()) {
	      rowsp++;
	      me.hide();
	    } else {
	      bg.attr("rowspan", rowsp);
	      //rowSpanArr.push(rowsp)
	      bg = me;
	      primaryCell = primaryColMe;
	      rowsp = 1;
	
	    }
	    bg.attr("rowspan", rowsp);
	  });
	});
	}
	/* var data = [
	{
		"id": "local_1",
		"name": "刑玫<br>党委书记 | 监狱长",
		"department": "指挥中心党支部",
		"shuji": ["郑哲磊", "窦万山", "李振辉", "胡焕美", "王硕"],
		"dangyuan": ["郑哲磊", "李振辉", "窦万山", "赵志涛", "胡焕美", "王硕", "吉晓波", "张金宽", "齐艳宏", "王海艳", "李春燕", "张思明", "李硕", "杨国英",
			"张德文", "闫鹏", "于东洋", "段继祥", "高战", "张伟平", "李洋", "邢晨思", "王帅", "马元", "马连梁", "孙高洁"
		],
		"jijifenzi": ["李彦杰"],
		"shenqing": [],
		"qunzhong": ["赵强","马彦伟"]
	}
	, 
	{
		"id": "local_2",
		"name": "刑玫<br>党委书记 | 监狱长",
		"department": "三监区党支部",
		"shuji": ["张海娜", "安娜", "李苑", "刘冉冉", "李倩"],
		"dangyuan": ["张海娜", "安娜", "李苑", "刘冉冉", "李倩", "郑玉梅", "李植", "刘丽会", "吴金凤", "陈宗梅", "张海燕", "闫伟", "蔡淑红", "鲁敏",
			"岳明", "王嘉炜","肖语南"
		],
		"jijifenzi": ["邱迪", "沈绯绯", "于涵"],
		"shenqing": ["李杭琦", "李聪", "马晓荟"],
		"qunzhong": []
	}
	, 
	{
		"id": "local_3",
		"name": "李红新<br>政委",
		"department": "监察审计科党支部",
		"shuji": ["唐凤君"],
		"dangyuan": ["唐凤君", "孟佳", "戴春芳", "张华", "高素平", "高凯", "刘放", "赵琳"
		],
		"jijifenzi": [],
		"shenqing": [],
		"qunzhong": []
	}
	, 
	{
		"id": "local_4",
		"name": "李红新<br>政委",
		"department": "四监区党支部",
		"shuji": ["刘丽丽", "牛娜", "李红颖", "侯艳丽", "杜莉微"],
		"dangyuan": ["刘丽丽", "牛娜", "李红颖", "侯艳丽", "杜莉微", "王冰", "李萍", "杨春丽", "黄明燕", "杨丽梅", "刘飞", "魏春晶", "郭兰香", "高素梅",
		  "王晓梅"
		],
		"jijifenzi": ["李瑶", "胡昕"],
		"shenqing": ["郭颖", "郭雨芳", "秦晶晶", "王新蕊"],
		"qunzhong": ["王霞"]
	}, 
	{
		"id": "local_5",
		"name": "吴建华<br>副监狱长",
		"department": "行政科党支部",
		"shuji": ["李润波", "姜慧芳", "李金山"],
		"dangyuan": ["李润波", "姜慧芳", "李金山", "俎晓忠", "刘红宾", "付建伟", "李林海", "张秀元", "王剑斌", "李山", 
		  "赵晓坤"
		],
		"jijifenzi": ["吴红敏", "游良轩", "李彦卫", "王健", "高江","韩建勇","伍宏伟"],
		"shenqing": ["索国强", "于成洋", "张京磊"],
		"qunzhong": ["王晓华", "张星宇", "吴绍晨", "李莉", "宋秀云","王晨萍","赵宇鹏", "郑超", "郝剑", "孙希宁","黄亚中",
			"李兴华","刘燕华","李文胜","方刚","王栋","王建康","田利新","刘永胜","王青","张福亮","李晋华","胡微"]
	}, 
	{
		"id": "local_6",
		"name": "杨梅青<br>副监狱长",
		"department": "九监区党支部",
		"shuji": ["陈熹微", "张丽娟", "刘君", "岳海艳", "唐丽琴"],
		"dangyuan": ["陈熹微", "张丽娟", "刘君", "岳海艳", "唐丽琴", "岳海艳", "李微", "高希", "高妍", "李远芳", "魏婕",
			"沈晓宇", "程思思", "余赛", "孙凌然","麻莉","郝俊平"
		],
		"jijifenzi": ["郑童芳","常淮秀","张晶媛"],
		"shenqing": ["朱冬青"],
		"qunzhong": ["李莹"]
	}
	];
	$("#grid1").grid({
	data: data,
	onComplete: completefun,
	colModel: gridColModel,
	datatype: "local",
	fitStyle: "height",
	shrinkToFit: false,
	componentCls: "wangge"
	
	})
	$("#searchBox").textbox({
	placeholder: "请输入内容",
	buttons: [{
	  text: true,
	  label: "搜索",
	  icons: "iconfont icon-sousuo",
	  click: function (e, data) {
	    var textboxValue = $("#searchBox").textbox("getValue");
	    var tmpArr = textArr,
	        length = textArr.length;
	    for(var k=length;k--;) {
	      var items = $(tmpArr[k]);
	      if(items.html().indexOf(textboxValue) > -1) {
	        items.addClass("active");
	      } else {
	        items.removeClass("active");
	      }
	    }
	  }
	}]
	}); */
		 
		 
	function showDqyh() {
		$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME+ "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
	} 
	
		
	</script>
</body>

</html>