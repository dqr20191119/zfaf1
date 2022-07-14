<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
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
%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- start: Meta -->
<meta charset="utf-8">
<title>设备运行情况评估</title>
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
  <div class="row-fluid jyxaWrap"></div>
   <div class="row-fluid jyxaWrap"></div>
 <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">警戒设施</div>
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
                <th style="text-align: center;">正常</th>
                <th style="text-align: center;">故障</th>
                <th style="text-align: center;">失效</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">68个</td>
                <td class="font24">3个</td>
                <td class="font24">0个</td>
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
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">门禁系统</div>
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
                <th style="text-align: center;">配置</th>
                <th style="text-align: center;">状态</th>
                <th style="text-align: center;">管理</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">狱内道路、水暖污管线</div>
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
                <th style="text-align: center;">维护状态</th>
                <th style="text-align: center;">重点目标追踪 </th>
                <th style="text-align: center;">运动目标监控</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
  <br>
  <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">狱内办公区</div>
          <div class="syljBox">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
                <td rowspan="4"><div class="blueLine"></div></td>
                <td>&nbsp;</td>
             <tr style="text-align: center;">
                <th style="text-align: center;">门禁管理</th>
                <th style="text-align: center;">警戒标识</th>
                <th style="text-align: center;">消防通道</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">生产区域</div>
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
                <th style="text-align: center;">生产区域隔离网</th>
                <th style="text-align: center;">门禁 </th>
                <th style="text-align: center;">消防、用电</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">其它重点区域</div>
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
                <th style="text-align: center;">配电室</th>
                <th style="text-align: center;">锅炉房 </th>
                <th style="text-align: center;">警械库、枪弹库</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
  
  <br>
  <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">狱内公物</div>
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
                <th style="text-align: center;">信息化设备</th>
                <th style="text-align: center;">狱内公共设施</th>
                <th style="text-align: center;">设备和物品</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">警用装备</div>
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
                <th style="text-align: center;">执法记录仪</th>
                <th style="text-align: center;">警械具 </th>
                <th style="text-align: center;">应急装备</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">物资更新</div>
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
                <th style="text-align: center;">新增</th>
                <th style="text-align: center;">变更</th>
                <th style="text-align: center;">报废</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">23</td>
                <td class="font24">0</td>
                <td class="font24">17</td>
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
  <br>
  
  <div class="row-fluid min-width-base" style="position:relative">
    <div class="row-fluid-left">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">生产物资与工具巡查</div>
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
                <th style="text-align: center;">原材料</th>
                <th style="text-align: center;">劳动工具</th>
                <th style="text-align: center;">生产废料</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">危化物品处理</div>
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
                <th style="text-align: center;">易燃</th>
                <th style="text-align: center;">易爆 </th>
                <th style="text-align: center;">剧毒</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
    <div class="row-fluid-right">
      <div class="row-fluid">
        <div class="span12 syljWrap bordercolor" onTablet="span3" onDesktop="span3" style="padding-top:0 !important;">
          <div class="syljTitle titleBlue">消防、用电及防汛物资</div>
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
                <th style="text-align: center;">消防、用电</th>
                <th style="text-align: center;">防汛设施及器材</th>
                <th style="text-align: center;">门（厅）灯</th>
              </tr>
              <tr style="text-align: center;">
                <td class="font24">正常</td>
                <td class="font24">正常</td>
                <td class="font24">正常</td>
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
</div>
<div class="clearfix"></div>
<div class="clearfix"></div>
<footer>
  <p> <span><a href="#" alt=""></a> 2019 &copy; 版权所有</span> </p>
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
	var data = (new Date()).Format("yyyy-MM-dd");
	var a = new Array("日", "一", "二", "三", "四", "五", "六");  
	var week = new Date().getDay();  
	var str = "星期"+ a[week];  
	$('.datewrap').html(""+data+" <span class=\"ml15\">"+str+"</span>");
});
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

function guanbi(){
	window.close();
}
</script>
</body>
</html>
