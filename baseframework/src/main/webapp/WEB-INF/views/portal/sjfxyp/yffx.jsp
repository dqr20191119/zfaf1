<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.framework.util.DateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
UserBean user = AuthSystemFacade.getLoginUserInfo();
String userName = user.getUserName();
String cusNumber = user.getCusNumber();
String today = DateUtil.getTodayString();

Map<String, Integer[]> thqk = new HashMap<String, Integer[]>();
thqk.put("4304", new Integer[] {2925, 11, 2914});	//永州
thqk.put("4307", new Integer[] {3448, 3277, 171});	//女子
thqk.put("4336", new Integer[] {2628, 395, 2233});	//怀化
thqk.put("4303", new Integer[] {2127, 15, 2112});	//雁南
thqk.put("4312", new Integer[] {4247, 3761, 486});	//岳阳

Map<String, Integer[]> jyhd = new HashMap<String, Integer[]>();
jyhd.put("4304", new Integer[] {2778, 21, 7});	//永州
jyhd.put("4307", new Integer[] {3448, 13, 4});	//女子
jyhd.put("4336", new Integer[] {2628, 27, 12});	//怀化
jyhd.put("4303", new Integer[] {2127, 25, 3});	//雁南
jyhd.put("4312", new Integer[] {4247, 34, 11});	//岳阳
%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- start: Meta -->
<meta charset="utf-8">
<title>民警执法实效性评估</title>
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

<body class="bodybgimg">
<!-- start: Header -->
<div class="headBg">
  <div class="container-fluid">
    <div class="leftNav">
    <!--   <ul>
        <li><a href="#">狱情态势</a></li>
        <li><a href="yffx.htm" class="hover">押犯分析 </a></li>
        <li><a href="genzong.htm"> 系统跟踪 </a></li>
        <li><a href="sjyy.htm"> 数据应用 </a></li>
      </ul> -->
    </div>
    
    <!-- start: Header Menu -->
    <div class="nav-no-collapse" >
      <div class="datewrap"><!-- 2018-04-23 <span class="ml15">星期一</span> --></div>
      <div class="quit"><a href="#" onclick="guanbi()">退出</a></div>
      <div class="user"><a href="#"><img src="${ctx}/static/sjfxyp/images/user.png"><%=userName%></a></div>
      <!-- end: Header Menu --> 
      
    </div>
  </div>
</div>
<!-- start: Header -->

<div class="container-fluid-full" style="margin:0 58px; margin-top:20px;">
 <%--  <div class="row-fluid jyxaWrap"> <span>监狱</span><img src="${ctx}/static/sjfxyp/images/selec01.png"> <span style="margin-left:84px;">罪犯分类</span><img src="${ctx}/static/sjfxyp/images/selec01.png"> </div>
 --%>  
  <div class="row-fluid jyxaWrap"></div>
   <div class="row-fluid jyxaWrap"></div>
 <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">谈话</div>
          <div class="syljBox">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
              </tr>
              <tr style="text-align: center;">
                <th style="text-align: center;">应谈人数</th>
                <th style="text-align: center;">已谈人数 </th>
                <th style="text-align: center;">未谈人数</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24"><%=thqk.get(cusNumber)[0] %>人</td>
                <td class="font24"><%=thqk.get(cusNumber)[1] %>人</td>
                <td class="font24"><%=thqk.get(cusNumber)[2] %>人</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="row-fluid-center">
      <div class="span12 bordercolor rcglWrap" onTablet="span3" onDesktop="span3" >
        <div class="syljTitle titleOrange">日常管理</div>
        <div class="syljBox">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>&nbsp;</td>
              <td rowspan="4"><div class="OrangeLine"></div></td>
              <td>&nbsp;</td>
              <td rowspan="4"><div class="OrangeLine"></div></td>
              <td>&nbsp;</td>
              <td rowspan="4"><div class="OrangeLine"></div></td>
              <td>&nbsp;</td>
              <td rowspan="4"><div class="OrangeLine"></div></td>
              <td>&nbsp;</td>
              <td rowspan="4"><div class="OrangeLine"></div></td>
              <td>&nbsp;</td>
            </tr>
            <tr style="text-align: center;">
              <td class="font24">交接班 </td>
              <td class="font24">点名 </td>
              <td class="font24">早操</td>
              <td class="font24">三餐</td>
              <td class="font24">出收工 </td>
              <td class="font24">监区活动</td>
            </tr>
            <tr style="text-align: center;">
              <td>正常</td>
              <td>正常</td>
              <td>正常</td>
              <td>正常</td>
              <td>正常</td>
              <td>正常</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 syljWrap2 bordercolor" onTablet="span4" onDesktop="span4" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue02">教育活动</div>
          <div class="syljBox">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
              </tr>
              <tr style="text-align: center;">
                <th style="text-align: center;">参加人数 </th>
                <th style="text-align: center;">未参加人数</th>
                <th style="text-align: center;">扰乱秩序人数</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24"><%=jyhd.get(cusNumber)[0] %>人</td>
                <td class="font24"><%=jyhd.get(cusNumber)[1] %>人</td>
                <td class="font24"><%=jyhd.get(cusNumber)[2] %>人</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 bordercolor columWrap" onTablet="span3" onDesktop="span3">
          <div class="yuan_1"></div>
          <div class="yuan_2"></div>
          <div class="yuan_3"></div>
          <div class="yuan_4"></div>
          <h6>安全排查</h6>
          <div class="bhei_img" id="yffx_zmfb"> </div>
          <div class="clearfix"></div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12 bordercolor columWrap" onTablet="span3" onDesktop="span3">
          <div class="yuan_1"></div>
          <div class="yuan_2"></div>
          <div class="yuan_3"></div>
          <div class="yuan_4"></div>
          <h6>心理矫治</h6>
          <div class="bhei_img" id="yffx_fjcy"> </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
    <div class="row-fluid-center">
      <div class="span12 bordercolor columWrap" onTablet="span3" onDesktop="span3" >
        <div class="yuan_1"></div>
        <div class="yuan_2"></div>
        <div class="yuan_3"></div>
        <div class="yuan_4"></div>
        <h6>情报搜集研判</h6>
        <div class="add_bhei_img" id="yffx_zffb"> </div>
        <div class="clearfix"></div>
      </div>
    </div>
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 bordercolor columWrap" onTablet="span3" onDesktop="span3">
          <div class="yuan_1"></div>
          <div class="yuan_2"></div>
          <div class="yuan_3"></div>
          <div class="yuan_4"></div>
          <h6>生产工具</h6>
          <div class="bhei_img" id="yffx_mzfb"></div>
          <div class="clearfix"></div>
        </div>
      </div>
      <div class="row-fluid">
        <div class="span12 bordercolor columWrap" onTablet="span3" onDesktop="span3">
          <div class="yuan_1"></div>
          <div class="yuan_2"></div>
          <div class="yuan_3"></div>
          <div class="yuan_4"></div>
          <h6>班组建设</h6>
          <div class="bhei_img" id="yffx_whcd"></div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="clearfix"></div>
<div class="clearfix"></div>
<footer>
  <p> <span  style="color: red;font-weight: bold;" ><a href="#" alt=""></a>研判结论：通过民警的一日的执法执勤工作事务完成情况分析可以得出我狱民警的执法时效性和有效性</span> </p>
</footer>

<!-- start: JavaScript--> 

<script src="${ctx}/static/sjfxyp/js/jquery-1.9.1.min.js"></script> 
<script src="${ctx}/static/sjfxyp/js/bootstrap.min.js"></script> 
<!--自定义--> 
<script src="${ctx}/static/sjfxyp/js/mainSlm.js"></script> 
<!-- end: JavaScript--> 
<script src="${ctx}/static/sjfxyp/js/echarts.min.js"></script> 
<script>
$(function(){
	var a = new Array("日", "一", "二", "三", "四", "五", "六");  
	var week = new Date().getDay();  
	var str = "星期"+ a[week];
	$('.datewrap').html("<%=today %>" + " <span class=\"ml15\">" + str + "</span>");
	zffb();
	zmfb();
	mzfb();
	fjcy();
	whcd();
});

var zffbData = {
	"4304":[{value:528, name:'正常'}, {value:45, name:'搜集不及时/不全面'}, {value:23, name:'研判不准'}, {value:32, name:'工作记录不清'}],
	"4307":[{value:738, name:'正常'}, {value:61, name:'搜集不及时/不全面'}, {value:25, name:'研判不准'}, {value:22, name:'工作记录不清'}],
	"4336":[{value:419, name:'正常'}, {value:71, name:'搜集不及时/不全面'}, {value:35, name:'研判不准'}, {value:24, name:'工作记录不清'}],
	"4303":[{value:582, name:'正常'}, {value:41, name:'搜集不及时/不全面'}, {value:29, name:'研判不准'}, {value:14, name:'工作记录不清'}],
	"4312":[{value:671, name:'正常'}, {value:31, name:'搜集不及时/不全面'}, {value:22, name:'研判不准'}, {value:24, name:'工作记录不清'}]
};

//情报搜集研判
function zffb() {
	var yffx_zffb = {
			   title : {
			       x:'center'
			   },
			   tooltip : {
			       trigger: 'item',
			       formatter: "{a} <br/>{b} : {c} ({d}%)"
			   },
			   series : [{
			           name: '情报搜集研判',
			           type: 'pie',
			           radius : '90%',
			           center: ['50%', '50%'],
			           label:null,
			           data:zffbData["<%=cusNumber %>"],
			           itemStyle: {
			               emphasis: {
			                   shadowBlur: 10,
			                   shadowOffsetX: 0,
			                   shadowColor: 'rgba(0, 0, 0, 0.5)'
			               }
			           }
			       }
			   ],
			   color: ['#5CACEE','#96CDCD', '#CDAD00','#4876FF']
			};
	var chartOutChar_yffx_zffb = null;
	chartOutChar_yffx_zffb = echarts.init(document.getElementById('yffx_zffb'));
	chartOutChar_yffx_zffb.setOption(yffx_zffb);
};

var zmfbData = {
	"4304":[{value:312, name:'进出监区搜身'}, {value:217, name:'出收工搜身'}, {value:381, name:'进出生活区或重点区域安检'}, {value:71, name:'三大现场清监'}, {value:92, name:'监舍清监'}, {value:32, name:'违禁品、违规品、危险品检查'}],
	"4307":[{value:512, name:'进出监区搜身'}, {value:427, name:'出收工搜身'}, {value:261, name:'进出生活区或重点区域安检'}, {value:98, name:'三大现场清监'}, {value:84, name:'监舍清监'}, {value:27, name:'违禁品、违规品、危险品检查'}],
	"4336":[{value:397, name:'进出监区搜身'}, {value:283, name:'出收工搜身'}, {value:311, name:'进出生活区或重点区域安检'}, {value:65, name:'三大现场清监'}, {value:72, name:'监舍清监'}, {value:19, name:'违禁品、违规品、危险品检查'}],
	"4303":[{value:335, name:'进出监区搜身'}, {value:270, name:'出收工搜身'}, {value:261, name:'进出生活区或重点区域安检'}, {value:59, name:'三大现场清监'}, {value:61, name:'监舍清监'}, {value:24, name:'违禁品、违规品、危险品检查'}],
	"4312":[{value:631, name:'进出监区搜身'}, {value:372, name:'出收工搜身'}, {value:311, name:'进出生活区或重点区域安检'}, {value:129, name:'三大现场清监'}, {value:101, name:'监舍清监'}, {value:36, name:'违禁品、违规品、危险品检查'}]
};
//安全排查
function zmfb() {
	var yffx_zmfb = {
			   title : {
			       x:'center'
			   },
			   tooltip : {
			       trigger: 'item',
			       formatter: "{a} <br/>{b} : {c} ({d}%)"
			   },
			   series : [{
			           name: '安全排查',
			           type: 'pie',
			           radius : '90%',
			           center: ['50%', '50%'],
			           label:null,
			           data:zmfbData["<%=cusNumber %>"],
			           itemStyle: {
			               emphasis: {
			                   shadowBlur: 10,
			                   shadowOffsetX: 0,
			                   shadowColor: 'rgba(0, 0, 0, 0.5)'
			               }
			           }
			       }
			   ],
			   color: ['#ADD8E6','#00BFFF', '#CDAD00','#4876FF','#87CEFA','#32CD32']
			};
	var chartOutChar_yffx_zmfb= null;
	chartOutChar_yffx_zmfb = echarts.init(document.getElementById('yffx_zmfb'));
	chartOutChar_yffx_zmfb.setOption(yffx_zmfb);
};

//生产工具
var mzfbDate = {
	"4304":[{value:1, name:'一级风险'}, {value:6, name:'二级风险'}, {value:13, name:'三级风险'}, {value:11, name:'四级风险'}],
	"4307":[{value:0, name:'一级风险'}, {value:5, name:'二级风险'}, {value:11, name:'三级风险'}, {value:17, name:'四级风险'}],
	"4336":[{value:1, name:'一级风险'}, {value:3, name:'二级风险'}, {value:14, name:'三级风险'}, {value:19, name:'四级风险'}],
	"4303":[{value:0, name:'一级风险'}, {value:5, name:'二级风险'}, {value:7, name:'三级风险'}, {value:18, name:'四级风险'}],
	"4312":[{value:1, name:'一级风险'}, {value:6, name:'二级风险'}, {value:13, name:'三级风险'}, {value:17, name:'四级风险'}]
};

function mzfb() {
	var yffx_mzfb = {
			   title : {
			       x:'center'
			   },
			   tooltip : {
			       trigger: 'item',
			       formatter: "{a} <br/>{b} : {c} ({d}%)"
			   },
			   series : [{
			           name: '生产工具',
			           type: 'pie',
			           radius : '90%',
			           center: ['50%', '50%'],
			           label:null,
			           data:mzfbDate["<%=cusNumber %>"],
			           itemStyle: {
			               emphasis: {
			                   shadowBlur: 10,
			                   shadowOffsetX: 0,
			                   shadowColor: 'rgba(0, 0, 0, 0.5)'
			               }
			           }
			       }
			   ],
			    color: ['#ADD8E6','#00BFFF', '#CDAD00','#4876FF']
			};
	var chartOutChar_yffx_mzfb= null;
	chartOutChar_yffx_mzfb = echarts.init(document.getElementById('yffx_mzfb'));
	chartOutChar_yffx_mzfb.setOption(yffx_mzfb);
};


//心理矫治
var fjcyDate = {
	"4304":[{value:52, name:'正常'}, {value:0, name:'安全防护措施不周全'}, {value:1, name:'心理咨询员配备不齐'}, {value:60, name:'活动开展次数'}, {value:7, name:'材料不全'}, {value:3, name:'重控罪犯开展咨询与干预'}],
	"4307":[{value:73, name:'正常'}, {value:0, name:'安全防护措施不周全'}, {value:2, name:'心理咨询员配备不齐'}, {value:86, name:'活动开展次数'}, {value:11, name:'材料不全'}, {value:8, name:'重控罪犯开展咨询与干预'}],
	"4336":[{value:56, name:'正常'}, {value:0, name:'安全防护措施不周全'}, {value:3, name:'心理咨询员配备不齐'}, {value:63, name:'活动开展次数'}, {value:4, name:'材料不全'}, {value:6, name:'重控罪犯开展咨询与干预'}],
	"4303":[{value:71, name:'正常'}, {value:0, name:'安全防护措施不周全'}, {value:7, name:'心理咨询员配备不齐'}, {value:85, name:'活动开展次数'}, {value:7, name:'材料不全'}, {value:11, name:'重控罪犯开展咨询与干预'}],
	"4312":[{value:97, name:'正常'}, {value:0, name:'安全防护措施不周全'}, {value:4, name:'心理咨询员配备不齐'}, {value:107, name:'活动开展次数'}, {value:6, name:'材料不全'}, {value:15, name:'重控罪犯开展咨询与干预'}]
};
function fjcy() {
	var yffx_fjcy = {
		    title : {
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [{
		            name: '心理矫治',
		            type: 'pie',
		            radius : '90%',
		            center: ['50%', '50%'],
		            label:null,
		            data:fjcyDate["<%=cusNumber %>"],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ],
		     color: ['#ADD8E6','#00BFFF', '#CDAD00','#4876FF','#87CEFA','#32CD32']
		};
	var chartOutChar_yffx_fjcy= null;
	chartOutChar_yffx_fjcy = echarts.init(document.getElementById('yffx_fjcy'));
	chartOutChar_yffx_fjcy.setOption(yffx_fjcy);
};


//班组建设
var whcdDate = {
	"4304":[{value:32, name:'已组建'}, {value:1, name:'未组建'}, {value:1, name:'组建不力'}, {value:0, name:'弄虚作假'}],
	"4307":[{value:39, name:'已组建'}, {value:0, name:'未组建'}, {value:1, name:'组建不力'}, {value:0, name:'弄虚作假'}],
	"4336":[{value:27, name:'已组建'}, {value:2, name:'未组建'}, {value:0, name:'组建不力'}, {value:0, name:'弄虚作假'}],
	"4303":[{value:26, name:'已组建'}, {value:3, name:'未组建'}, {value:0, name:'组建不力'}, {value:0, name:'弄虚作假'}],
	"4312":[{value:41, name:'已组建'}, {value:2, name:'未组建'}, {value:0, name:'组建不力'}, {value:0, name:'弄虚作假'}]
};
function whcd() {
	var yffx_whcd = {
		    title : {
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [{
		            name: '班组建设',
		            type: 'pie',
		            radius : '90%',
		            center: ['50%', '50%'],
		            label:null,
		            data:whcdDate["<%=cusNumber %>"],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ],
		     color: ['#ADD8E6','#00BFFF', '#CDAD00','#4876FF']
		};
	var chartOutChar_yffx_whcd= null;
	chartOutChar_yffx_whcd = echarts.init(document.getElementById('yffx_whcd'));
	chartOutChar_yffx_whcd.setOption(yffx_whcd);	
};

function guanbi(){
	window.close();
}
</script>
</body>
</html>
