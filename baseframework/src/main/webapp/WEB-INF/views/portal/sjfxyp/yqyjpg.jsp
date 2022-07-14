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
<title>狱情预警研判</title>
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
  <div class="row-fluid jyxaWrap"> 
 
  </div>
   <div class="row-fluid">
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>警情类型分布</h6>
      <div class="bhei_img" id="yffx_jqlxfb"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>警情民警素质</h6>
      <div class="bhei_img" id="yffx_jqmjsz"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>警情执法</h6>
      <div class="bhei_img" id="yffx_jqzf"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>狱情类型分布</h6>
      <div class="bhei_img" id="yffx_yqlxfb"> </div>
      <div class="clearfix"></div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>一级狱情关注点</h6>
      <div class="bhei_img" id="yffx_zffb"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>二级狱情关注点</h6>
      <div class="bhei_img" id="yffx_zmfb"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>三级狱情关注点</h6>
      <div class="bhei_img" id="yffx_xqfb"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>犯情类型分布</h6>
      <div class="bhei_img" id="yffx_mzfb"> </div>
      <div class="clearfix"></div>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3" style="margin-bottom:15px">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>罪犯年龄分布</h6>
      <div class="bhei_img" id="yffx_fjcy"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>民族分布</h6>
      <div class="bhei_img" id="yffx_qjdh"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>老病残分布</h6>
      <div class="bhei_img" id="yffx_lbcfb"> </div>
      <div class="clearfix"></div>
    </div>
    <div class="span3 bordercolor columWrap" onTablet="span3" onDesktop="span3">
      <div class="yuan_1"></div>
      <div class="yuan_2"></div>
      <div class="yuan_3"></div>
      <div class="yuan_4"></div>
      <h6>文化程度</h6>
      <div class="bhei_img" id="yffx_whcd"> </div>
      <div class="clearfix"></div>
    </div>
  </div>
</div>
<div class="clearfix"></div>
<div class="clearfix"></div>
<footer>
  <p> <span style="color: red;font-weight: bold;"><a href="#" alt=""></a>研判结论：通过民警的一日的执法执勤工作事务占比分析得出结论，今日各项工作开展有序，民警
按照监狱执法要求公正执法。</span> </p>
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
	yayjpg();
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


function yayjpg(){

	//罪犯分布
	 var yffx_jqlxfb = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '警情类型分布',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:0, name:'一级'},
	                {value:1, name:'二级'},
	                {value:4, name:'三级'},
					 {value:18, name:'四级'} 
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#5CACEE','#96CDCD',
	     '#CDAD00','#4876FF']
	};
	 var chartOutChar_yffx_jqlxfb = null;
		chartOutChar_yffx_jqlxfb = echarts.init(document.getElementById('yffx_jqlxfb'));
		chartOutChar_yffx_jqlxfb.setOption(yffx_jqlxfb);
	 
	 var yffx_jqmjsz = {
			    title : {
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    
			    series : [
			        {
			            name: '警情民警素质占比/%',
			            type: 'pie',
			            radius : '90%',
			            center: ['50%', '50%'],
			            label:null,
			            data:[
			                {value:15, name:'民警素质'},
			                {value:80, name:'民警缺乏危机意识'},
			                {value:5, name:'警情工作数据报送不及时'}

			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ],
			     color: ['#ADD8E6','#00BFFF',
			     '#CDAD00']
			};
	
	 var chartOutChar_yffx_jqmjsz = null;
	chartOutChar_yffx_jqmjsz = echarts.init(document.getElementById('yffx_jqmjsz'));
	chartOutChar_yffx_jqmjsz.setOption(yffx_jqmjsz);
	 
	
	
	 //刑期分布
	 var yffx_jqzf = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '警情执法占比',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:30, name:'执法保障'},
	                {value:10, name:'执法监督'},
	                {value:60, name:'执法责任'}

	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA','#32CD32','#1E90FF']
	};
	 var chartOutChar_yffx_jqzf= null;
		chartOutChar_yffx_jqzf = echarts.init(document.getElementById('yffx_jqzf'));
		chartOutChar_yffx_jqzf.setOption(yffx_jqzf);
	 
		
		
		 var yffx_yqlxfb = {
			    title : {
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    
			    series : [
			        {
			            name: '狱情类型分布',
			            type: 'pie',
			            radius : '90%',
			            center: ['50%', '50%'],
			            label:null,
			            data:[
			                {value:0, name:'一级'},
			                {value:1, name:'二级'},
			                {value:4, name:'三级'},
							 {value:18, name:'四级'} 
			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ],
			     color: ['#5CACEE','#96CDCD',
			     '#CDAD00','#4876FF']
			};
			 var chartOutChar_yffx_yqlxfb = null;
				chartOutChar_yffx_yqlxfb = echarts.init(document.getElementById('yffx_yqlxfb'));
				chartOutChar_yffx_yqlxfb.setOption(yffx_yqlxfb);
		
		
	 //罪犯分布
	 var yffx_zffb = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '关注点',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:2, name:'暴狱、骚乱'},
	                {value:2, name:'造成人员死亡'},
	                {value:2, name:'劫持人质'},
					 {value:3, name:'造成重大损失'},
					 {value:1, name:'与狱外人员勾结实施重大犯罪'},
					 {value:5, name:'发生安全生产事故'},
					 {value:6, name:'私藏危险物品'},
					 {value:1, name:' 自然灾害、意外事件'},
					 {value:1, name:'其他事件'},
					
					 
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#5CACEE','#96CDCD',
	     '#CDAD00','#4876FF','#4876FF','#87CEFA','#32CD32','#1E90FF','#6495ED']
	};

	 var chartOutChar_yffx_zffb = null;
	chartOutChar_yffx_zffb = echarts.init(document.getElementById('yffx_zffb'));
	chartOutChar_yffx_zffb.setOption(yffx_zffb);
	 
	 //罪名分布
	 var yffx_zmfb = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '关注点',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:55, name:'狱内又犯罪'},
	                {value:10, name:'罪犯企图自杀、绝食、自杀未遂、自伤自残'},
	                {value:24, name:'罪犯意外受轻伤以上伤害'},
					 {value:14, name:'侵犯公民人身权利、民主权利罪'},
					 {value:34, name:'罪犯私藏违禁品'} ,
					 {value:4, name:'社会人员在监狱周边聚集滋事'} ,
					 {value:7, name:'危害国防利益罪'}, 
					 {value:9, name:'其它严重影响监狱安全稳定或损害监狱形象'} ,
					 {value:30, name:'集体闹事或群殴的'}  

	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA','#32CD32','#1E90FF','#6495ED','#9370DB']
	};

	 var chartOutChar_yffx_zmfb= null;
	chartOutChar_yffx_zmfb = echarts.init(document.getElementById('yffx_zmfb'));
	chartOutChar_yffx_zmfb.setOption(yffx_zmfb);
	 
	 
	 
	 
	 
	 //刑期分布
	 var yffx_xqfb = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '关注点',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:5, name:'罪犯近期主要思想动态'},
	                {value:10, name:'罪犯受行政处罚的情况及本周扣减分的主要情况'},
	                {value:7, name:'重控罪犯出现异常情况'},
					{value:1, name:'狱侦情报搜集及情况分析'},
					{value:4, name:'其它影响监管安全稳定和损害监狱形象的情况'}

	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA']
	};

	 var chartOutChar_yffx_xqfb= null;
	chartOutChar_yffx_xqfb = echarts.init(document.getElementById('yffx_xqfb'));
	chartOutChar_yffx_xqfb.setOption(yffx_xqfb);
	 
	 
	 
	 var yffx_mzfb = {
			    title : {
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    
			    series : [
			        {
			            name: '犯情类型分布',
			            type: 'pie',
			            radius : '90%',
			            center: ['50%', '50%'],
			            label:null,
			            data:[
			                {value:1, name:'一级'},
			                {value:2, name:'二级'},
			                {value:1, name:'三级'},
							 {value:18, name:'四级'} 
			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ],
			     color: ['#5CACEE','#96CDCD',
			     '#CDAD00','#4876FF']
			};
			 var chartOutChar_yffx_mzfb = null;
				chartOutChar_yffx_mzfb = echarts.init(document.getElementById('yffx_mzfb'));
				chartOutChar_yffx_mzfb.setOption(yffx_mzfb);
		
		 
	 
	 
	 //分级初遇
	 var yffx_fjcy= {
	     
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	         
	    },
		
	    calculable: true,
	    series: [
	        {
	            name:'罪犯文化程度分布',
	            type:'funnel',
	            left: '10%',
	            top: 5,
	            bottom: 5,
	            width: '80%',
	            min: 0,
	            max: 100,
	            minSize: '0%',
	            maxSize: '100%',
	            sort: 'descending',
	            gap: 2,
	            label: {
	                normal: {
	                    show: true,
	                    position: 'inside'
	                },
	                emphasis: {
	                    textStyle: {
	                        fontSize: 20
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    length: 10,
	                    lineStyle: {
	                        width: 1,
	                        type: 'solid'
	                    }
	                }
	            },
	            itemStyle: {
	                normal: {
	                    borderColor: '#fff',
	                    borderWidth: 1
	                }
	            },
	            data: [
	                {value: 3, name: '60岁以上'},
	                {value: 30, name: '50-60岁'},
	                {value: 10, name: '40-50岁'},
	                {value: 40, name: '30-40岁'},
	                {value: 20, name: '20-30岁'},
	                {value: 10, name: '20岁以下'}
	            ]
	        }
	    ],
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA']
	};

	 
	 var chartOutChar_yffx_fjcy= null;
	chartOutChar_yffx_fjcy = echarts.init(document.getElementById('yffx_fjcy'));
	chartOutChar_yffx_fjcy.setOption(yffx_fjcy);
	 

	 
	 
	 
	 //去极端化
	 var yffx_qjdh = {
			 title : {
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    
			    series : [
			        {
			            name: '民族分布',
			            type: 'pie',
			            radius : '90%',
			            center: ['50%', '50%'],
			            label:null,
			            data:[
			                {value:45, name:'汉族'},
			                {value:1, name:'维吾尔族'},
			                {value:7, name:'白族'},
							{value:1, name:'苗族'},
							{value:4, name:'彝族'},
							{value:7, name:'黎族'} ,
							{value:4, name:'蒙古族'} 

			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ],
			     color: ['#ADD8E6','#00BFFF',
			     '#CDAD00','#4876FF','#87CEFA','#32CD32','#1E90FF']
			};

	 var chartOutChar_yffx_qjdh= null;
	chartOutChar_yffx_qjdh = echarts.init(document.getElementById('yffx_qjdh'));
	chartOutChar_yffx_qjdh.setOption(yffx_qjdh);
	 
	 
	 //老病残分布
	 var yffx_lbcfb = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '老病残分布',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:5, name:'一监'},
	                {value:1, name:'二监'},
	                {value:7, name:'三监'},
	                {value:7, name:'四监'},
	                {value:7, name:'五监'},
	                {value:7, name:'六监'},
	                {value:7, name:'三监'}

	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA','#32CD32']
	};

	 var chartOutChar_yffx_lbcfb= null;
	chartOutChar_yffx_lbcfb = echarts.init(document.getElementById('yffx_lbcfb'));
	chartOutChar_yffx_lbcfb.setOption(yffx_lbcfb);
	 
	 //文化程度 
	var yffx_whcd = {
	     
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        top:'3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01],
			axisLine: {
	                    lineStyle: {
	                        type: 'solid',
	                        color: '#fff',//左边线的颜色
	                        width:'2'//坐标线的宽度
	                    }
	                },
	                axisLabel: {
	                    textStyle: {
	                        color: '#fff',//坐标值得具体的颜色
	 
	                    }
	                }
	    },
	    yAxis: {
	        type: 'category',
	        data: ['文盲','小学','初中','高中','专科','本科'],
			axisLine: {
	                    lineStyle: {
	                        type: 'solid',
	                        color: '#fff',//左边线的颜色
	                        width:'2'//坐标线的宽度
	                    }
	                },
	                axisLabel: {
	                    textStyle: {
	                        color: '#fff',//坐标值得具体的颜色
	 
	                    }
	                }
	    },
	    series: [
	        {
	            name: '文化程度 ',
	            type: 'bar',
	            data: [12, 40, 50, 30, 20, 10],
	            itemStyle:{color:'#1E90FF'}
	        } 
	    ]
	};

	  var chartOutChar_yffx_whcd= null;
	chartOutChar_yffx_whcd= echarts.init(document.getElementById('yffx_whcd'));
	chartOutChar_yffx_whcd.setOption(yffx_whcd);
	 
} 
</script>
</body>
</html>
