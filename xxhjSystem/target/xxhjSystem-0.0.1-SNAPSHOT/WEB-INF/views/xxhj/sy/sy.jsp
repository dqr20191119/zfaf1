<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:dialog id="dialogId_rightHomeMenu" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>

<div id="rightSideHome"> 
	<div class="dayDuty rightDivStyle right-zb" id="duty_province" style="margin-top:20px;">
		<h4 align="center" style="position:relative">今日值班</h4>
			<ul>
				<li>
					<span> 指挥长: </span>
					<div id="dutyJob" class="marquee-panel"> </div>
				</li>
				<li>
					<span> 信息研判岗：</span>
					<div id="infoJob" class="marquee-panel"> </div>
				</li>
				<li> 
					<span> 视频监控岗：</span>
					<div id="cameraJob" class="marquee-panel"> </div>
				</li>
				<li> 
					<span> 技术维护岗：</span>
					<div id="serviceJob" class="marquee-panel"> </div>
				</li>
			</ul>
	   	<!-- <div style="float: left;margin-left: 238px;margin-top: 2px;font-size: 14px;">
			<a href="javascript:void(0);" style="position:absolute;right:10px;" onClick="toDayDuty();">更多&nbsp;&nbsp;</a>
		</div> -->
	</div>
	
	<div class="dayDuty rightDivStyle right-zb" id="duty_prison">
		<h4 align="center" style="position:relative">今日值班</h4>
		<div class="loading" id="duty_prisonLoading">
			<ul>
				<li>
					<span>带班领导：<span id="ZYLD" class="marquee-panel"></span></span>
				    
				</li>
				<li>
					<span>指&nbsp;挥&nbsp;长&nbsp;：<span id="DBLD" class="marquee-panel"></span></span>
				</li>
				
				<li>
					<span>指挥中心：<span id="ZHZX" class="marquee-panel"></span></span>
				</li>
	       </ul> 
		</div>
	         
		<div style="float: left;margin-left: 238px;margin-top: 2px;font-size: 14px;display:none;">
				<a href="javascript:void(0);" style="position:absolute;right:10px;" onClick="toDayDuty();">更多&nbsp;&nbsp;</a>
		</div>
	</div> 
	            
	<div class="police rightDivStyle right-mj">
	     <h4 align="center" > 
	     	<span class="province">全疆</span> 当前在监民警人数：
	        <span id="current_insidePoliceCount"></span>
	     </h4>
	</div>
	
	<div class="offender rightDivStyle right-zb right-bar" id="divPrisoner">
	     <h4 align="center"><span class="province">全疆</span>实时押犯</h4>
	     <div class="zyfr">
	     	<span>在押犯人</span>
	     	<span id="inside">
	     		<span class="inside-detail">0</span>
	     	</span>
	     </div>
	    	<ul>
	    		<li>
	    			<span>实押危安犯总数:</span>
	    			<span id="WAFCount"></span>人
	    		</li>
	    		<li>
	    			<span>实押危安犯与总实押罪犯比:</span>
	    			<span id="rateOfWAFInPrisoner" class="marquee-panel">0</span>
	    		</li>
	    		<li>
	    			<span>加戴戒具罪犯数:</span>
	    			<span id="GDJJCount" class="marquee-panel">0</span>人
	    		</li>
	    		<li>
	    			<span>关押禁闭罪犯数:</span>
	    			<span id="GYJBCount" class="marquee-panel">0</span>人
	    		</li>
	    		<li>
	    			<span>隔离审查罪犯数:</span>
	    			 <span id="GLSCCount" class="marquee-panel">0</span>人
	    		</li>
	    		<li>
	    			<span>立案侦查罪犯数:</span>
	    			 <span id="LAZCCount" class="marquee-panel">0 </span>人
	    		</li>
	    		<li>
	    			<span>解回重审罪犯数:</span>
	    			<span id="JHCSCount" class="marquee-panel">0</span>人
	    		</li>
	    		<li>
	    			<span>暂予监外执行罪犯数:</span>
	    			<span id="ZYJWZXCount" class="marquee-panel">0</span>人
	    		</li>
	    		<li>
	    			<span>老病残罪犯数:</span>
	    			<span id="LBCCount" class="marquee-panel">0</span>人
	    		</li>
	    	</ul>

	</div>

	<div class="offender rightDivStyle right-zb right-bar" style="" id="alarmDdiv">
	  <h4 align="center" >实时警情情况</h4>
	  <!-- 警情 start -->
	  <div class="contact-area">
		  <div class="contact">
		
		    <nav class="active">
		      <a href="#" onclick="openZhxtDialog(event,'alarmRecord')" class="gmail">
		      	 <span>
		      	 	<i class="iconfont icon-iconjg"></i>
		      	 	<span>一级警情</span>
		      	 </span>
		      	 <span id="lev_1">0</span>
		        <!-- <div class="icon">
		          <img   width="48" height="48" src="${ctx }/static/resource/images/alarm/r1.jpg"/>
		        </div>
		        <div class="content">
		          <h1 style="line-height:50px;">一级警情&nbsp;&nbsp;&nbsp;&nbsp;<span id="lev_1"></span></h1>
		        </div>
		        <svg class="arrow" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 48 48"> <g class="nc-icon-wrapper" fill="#444444"> <path d="M17.17 32.92l9.17-9.17-9.17-9.17L20 11.75l12 12-12 12z"></path> </g> </svg>
		      --> </a>
		
		      <a href="#" onclick="openZhxtDialog(event,'alarmRecord')" class="facebook">
		      	 <span>
		      	 	<i class="iconfont icon-iconjg"></i>
		      	 	<span>二级警情</span>
		      	 </span>
		      	 <span id="lev_2">0</span>
		        <!--  <div class="icon">
					<img   width="48" height="48" src="${ctx }/static/resource/images/alarm/r2.jpg"/> 
				</div>
		        <div class="content">
		          <h1  style="line-height:50px;">二级警情&nbsp;&nbsp;&nbsp;&nbsp;<span id="lev_2"></span></h1>
		        </div>
		        <svg class="arrow" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 48 48"> <g class="nc-icon-wrapper" fill="#444444"> <path d="M17.17 32.92l9.17-9.17-9.17-9.17L20 11.75l12 12-12 12z"></path> </g> </svg>
		      --></a>
		      <a href="#" onclick="openZhxtDialog(event,'alarmRecord')" class="twitter">
		      	 <span>
		      	 	<i class="iconfont icon-iconjg"></i>
		      	 	<span>三级警情</span>
		      	  </span>
		      	  <span id="lev_3">0</span>
		       <!-- <div class="icon">
		          <img   width="48" height="48" src="${ctx }/static/resource/images/alarm/r3.jpg"/>
				</div>
		        <div class="content">
		          <h1  style="line-height:50px;">三级警情&nbsp;&nbsp;&nbsp;&nbsp;<span id="lev_3"></span></h1>
		        </div>
		        <svg class="arrow" xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 48 48"> <g class="nc-icon-wrapper" fill="#444444"> <path d="M17.17 32.92l9.17-9.17-9.17-9.17L20 11.75l12 12-12 12z"></path> </g> </svg>
		      --></a>
		    </nav>
		    
		  </div>
		</div>
		<!-- 警情 end -->
	  <div id="rightBar2" style="width:100%;height:10px;padding-left:20px"></div>
	</div>
	<div class="offender rightDivStyle right-zb right-bar" style="" id="ForeignCarPeople">
		<h4 align="center" ><span class="province">全疆</span>外来信息</h4>
		<ul>
			<li>
			   	<p><span class="spThr" id="nowForeignCar">0</span>(辆)</p>
			   	外来车辆
			</li>
			<li>
				<p><span class="spThr" id="nowFPerson">0</span>(人)</p>
			   	外来人员
			</li>
		</ul>
	 	<div id="rightBar2" style="width:100%;height:10px;padding-left:20px"></div>
	</div>
</div>  
   
<script type="text/javascript"> 

	var USER_LEVEL= jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER; 
	var cusNumber;

	$.parseDone(function() {
		
		queryAlarmLevRecord();
		$("#rightSideHome").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		//判断省局监狱监狱显示权限
		if(USER_LEVEL == 1) {
	        $("#duty_prison").hide();
 			$("#duty_province").show();
 			$(".externalCarPeople div").css("margin-left",30);
 			//$(".marquee-panel").css("width",189);
 			cusNumber = ''
		}else if(USER_LEVEL == 2) { 
			
			$(".province").hide()
		    $("#duty_province").hide();
			$("#duty_prison").show();
			cusNumber = thisCusNumber;
			
		} else {
			$(".province").hide()
			$(".police").hide()
			
		    $("#duty_province").hide();
			$("#duty_prison").show();
			$("#divPrisoner").hide();
			$("#divZdprisoner").hide();
			$("#ForeignCarPeople").hide();
			cusNumber = thisCusNumber;
		}
		//判断省局监狱监狱数据查询权限
		if(USER_LEVEL == 1 || USER_LEVEL == 2) {     //如果是省局、监狱用户则加载数据
			
			$("#rightSideHome").loading({ text : "正在加载中，请稍后..."});
			var url="${ctx}/xxhj/sy/countSyCount";
			$.ajax({
				type : "post",
				url : url,
				data: {
					   "cusNumber" : cusNumber,
					   "cdjJobCode" : "ZYLD/DBLD/ZHZX"
					  },  
				dataType : "json",
				success : function(data) {
					
					$("#rightSideHome").loading("hide");
					if (data != "" && data != null) {
						
						showTodayDutyPolice(data.ZYLD.staffName,data.DBLD.staffName,data.ZHZX.staffName)
						showInPoliceCount(data.current_insidePoliceCount);
						parsePrisonerCount(data.current_PrisonerCount);
						// showKeynotePrisoner(data.keynotePrisoner);
					} 
					//查询外来车辆和人
					// showPeopleAndCarCount();
				},
			});
		}else if(USER_LEVEL == 3) {//如果是监区，加载监区数据
			
		}
		
	});
	
  /* 加载实时押犯的统计数据*/	
 	function initEchart(title, data,type) {

		if(type == 1) {
			
			var myChart4 = echarts.init(document.getElementById("rightBar1"))
		} else if (type == 2) {
			
			var myChart4 = echarts.init(document.getElementById("rightBar2"))
		}
		var option1 = {
				title: {
			    },
	   		    color:[],
	   		    tooltip: {
	   		    	trigger: 'axis',
	   		        axisPointer: {
	   		            type: 'shadow'
	   		        },
	   		    formatter: '{b}:{c}人'
	   		    },
	   		    legend: {
	   		    },
	   		    grid: {
	   		    	left: '6%',
	   		        right: '10%',
	   		        bottom: '0',
	   		        top:"0",
	   		        containLabel: true
	   		    },
	   		    
	   		    xAxis: {
	   		        type: 'value',
	   		        boundaryGap: [0, 0.01],
	   		        show:false
	   		    },
	   		    
	   		    yAxis: {
	   		        type: 'category',
	   		        axisTick:false,
	   		        axisLabel:{
	   		        	textStyle:{
	   		        		color:"#fff",
	   		        		fontSize:15,
	   		        	},
	   		        	interval:0
	   		        },
	   		        data: title
	   		    }, 
	   		    series: [{
		        	name: '2017年',
		            type: 'bar',
		            data: data,
		            itemStyle: { 
						normal: { 
							color: function(params) { 
					    	//首先定义一个数组 
					    		var colorList = [ 
						    		"#ff3c00",
						    		"#c90000",
						    		"#c28000",
						    		"#018411", 
						    		"#007eff",
						    		"#00b716",
						    		"#001eff"
					    		]; 
					    	return colorList[params.dataIndex] 
					    	}, 
	                 //以下为是否显示 
			    		   label: { 
				    		   show: true ,
				    		   position:"right",
			    		   	   textStyle: {
			    			   	color:"#fff"
			    			   },
			    			   formatter: '{c}人'
	  						
		    			  } 
			    		} 
					},
			     }
		]};

		myChart4.setOption(option1);
        $(window).resize(function () {
        	myChart4.resize();
        });
       //点击数据
        myChart4.on('click',function (params) {
       	
	       	if(params.data!=0){
	       		
	       		if (params.name == "今日释放") {
		        	
		        	toPrisonerList(1);
		        	
		        } else if (params.name == "今日收押"){
		        	
		            toPrisonerList(2)
		        } else if (params.name == "解回再审"){
		        	
		            toPrisonerList(3)
		        } else if (params.name == "特许离监"){
		        	
		            toPrisonerList(4)
		        } else if (params.name == "监外就诊"){
		        	
		            toPrisonerList(5)
		        } else if (params.name == "监外住院"){
		        	
		            toPrisonerList(7)
		        } else if (params.name == "邪教罪犯"){
		        	
		            toPrisonerList(21)
		        } else if (params.name == "维族罪犯"){
		        	
		            toPrisonerList(22)
		        } else if (params.name == "外籍罪犯"){
		        	
		            toPrisonerList(31)
		        } else if (params.name == "限制减刑"){
		        	
		            toPrisonerList(23)
		        } else if (params.name == "当日列控"){
		        	
		            toPrisonerList(24)
		        } else if (params.name == "一级预警 "){
		        	
		            toPrisonerList(32)
		        } else if (params.name == "顽危罪犯 "){
		        	
		            toPrisonerList(25)
		        }       	 	 	     	 	 	     	 	 	     	 	 	
       		}
       });  
	}
 	/**
	 * 显示今日值班民警
	 */
 	function showTodayDutyPolice(ZYLD,DBLD,ZHZX) {
 		
 	 	$("#ZYLD").html(ZYLD);
 		$("#DBLD").html(DBLD);
 		$("#ZHZX").html(ZHZX);
 	}
	/**
	 * 显示当前在监民警人数
	 */
	function showInPoliceCount(data){
	
		if((typeof data) == "string") {
			data = JSON.parse(data).obj.data;
		}
		var config = data.config;
		var count = data.INSIDE_POLICE_COUNT;
		if (count == 0) {
			
			$("#current_insidePoliceCount").empty();
			$("#current_insidePoliceCount").html("<a href='javascript:void(0);' style='font-size: 20px;'>" + count + "&nbsp人</a>");
		} else {
			$("#current_insidePoliceCount").empty();
			$("#current_insidePoliceCount").html("<a href='javascript:void(0);' onclick='toPoliceList("+config+")' style='margin-right: 10px; font-size: 20px;'>" + count + "&nbsp;&nbsp;人&nbsp;&nbsp;</a>");
		}
	}
	
	/**
	 * 显示该监狱实时押犯
	 */
	function parsePrisonerCount(countList) {
		
		var allCount;                                     //罪犯总数
		var WAFCount;                                     //危安犯总数
 		for (var i = 0; i<countList.length; i++) {
			
			var key = countList[i].LB; 
			var count =countList[i].RS; 
			
			if (key == 1) {                                   //实押罪犯总数
				allCount = count+"";
				var arry = allCount.split(""),arryHtml = [];
				for(var j in arry) {
					arryHtml.push("<span class='inside-detail'>"+arry[j]+"</span>");
				}
				$("#inside").html(arryHtml.join(""));
				
			} else if (key == 2) {                           //实押危安犯总数
				
				WAFCount = count;
				$("#WAFCount").html(WAFCount);
				
			}else if (key == 4) {                            //加戴戒具罪犯数
				
				$("#GDJJCount").html(count);
			
			} else if (key == 5) {                          //关押禁闭罪犯数
				
				$("#GYJBCount").html(count);

			} else if (key == 6) {                         //隔离审查罪犯数
				
				$("#GLSCCount").html(count);
					
			} else if (key == 7) {                         //立案侦查罪犯数
				
				$("#LAZCCount").html(count);
			
			} else if (key == 8) {                         //解回重审罪犯数
				
				$("#JHCSCount").html(count);
			
			} else if (key == 9) {                       //暂予监外执行罪犯数
				
				$("#ZYJWZXCount").html(count);
			
			} else if (key == 10) {                          //老病残罪犯数
				
				$("#LBCCount").html(count);
			} 
		}
 		var rate = ((WAFCount/allCount)*100).toFixed(2)+"%";      //计算危安犯与总罪犯数比例
 		$("#rateOfWAFInPrisoner").html(rate);
	}

	/**
	 * 打开罪犯历史查询页面
	 */
	function toPrisonerList(type) {
		
		$("#dialogId_rightHomeMenu").dialog({
			width : 1000, //属性
			height : 800, //属性
			title : '罪犯信息',
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=1&type=" + type,
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}
	
	function toPoliceList(config) {
		
		if(!config){
			config = '';
		}
		var drptmntId = "";
		var cusNumber =(jsConst.CUS_NUMBER== "1100" ? "" : jsConst.CUS_NUMBER)
		
		$("#dialogId_rightHomeMenu").dialog({
			width : 1000, 
			height : 800, 
			title : '民警信息 ',
			modal : true, 
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?query=1&config="+config+"&drptmntId="+ drptmntId + "&cusNumber=" + cusNumber,
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}
	
	function toForeignCarList() {
		$("#dialogId_rightHomeMenu").dialog({
			width : 1000, //属性
			height : 800, //属性
			title : '外来车辆',
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/foreign/openCarInfo?flag=1"
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}
	
	function toForeignPeopleList() {
		$("#dialogId_rightHomeMenu").dialog({
			width : 1000, //属性
			height : 800, //属性
			title : '外来人员',
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/foreign/openPeopleInfo?flag=1"
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}
	
	function toDutyPolice() {
	
		$("#dialogId_rightHomeMenu").dialog({
			width:1000,                    //属性
		    height:800,                    //属性
		    title :'民警信息 ', 
		    modal:true,               //属性
		    autoOpen:false,  
		    url:'${ctx}/xxhj/jnmj/jnmj_policeinfo', 
		});
			$("#dialogId_rightHomeMenu").dialog("open");
	}
	
	function toInside() {
 
		$("#dialogId_rightHomeMenu").dialog({
			width:1000,                    //属性
		    height:600,                    //属性
		    title :' ', 
		    modal:true,               //属性
		    autoOpen:false,  
		    url:'${ctx}/xxhj/jnmj/jryf_prisonerinfo', 
		    });
		
		$("#dialogId_rightHomeMenu").dialog("open"); 

	}
	
	function toFree() {
		
		$("#dialogId_rightHomeMenu").dialog({
			width:1000,                    //属性
		    height:600,                    //属性
		    title :'罪犯信息 ', 
		    modal:true,               //属性
		    autoOpen:false,  
		    url:'${ctx}/xxhj/jnmj/jryf_prisonerinfo', 
		});
		$("#dialogId_rightHomeMenu").dialog("open");
	}
	/**
	 * 当前在监民警消息回调
	 */
	function parseInPoliceCount1(content) {
	
 	 	var data = JSON.parse(content).obj.data;
		var count = data[0].INSIDE_POLICE_COUNT;
		
		if(count == 0) {
			$("#current_insidePoliceCount").html(count);
		} else {
			$("#current_insidePoliceCount").empty();
			$("#current_insidePoliceCount").html("<a href='javascript:void(0);' onclick='toPoliceList()'>" + count + "</a>");
		}
	}
	
	function queryAlarmLevRecord() {
		
		$.ajax({
			type : "post",
			url : "${ctx}/alarm/queryAlarmLevRecord.json?cusNumber=" + jsConst.CUS_NUMBER,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					
					 $("#lev_1").text(data.obj.lev_1);
					 $("#lev_2").text(data.obj.lev_2);
					 $("#lev_3").text(data.obj.lev_3);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

    /**
     * 加载外来车辆外来人员数量
     */
/*    function showPeopleAndCarCount() {

        var beginTime = new Date();
        beginTime.setHours(0);
        beginTime.setMinutes(0);
        $.ajax({
            type : "post",
            url : "${ctx}/common/all/getPeopleAndCarCount?cusNumber="+jsConst.CUS_NUMBER+"&beginTime="+beginTime,
            dataType : "json",
            success : function(data) {
                console.log(data.carCount);
                if (data.carCount == 0) {
                    $("#nowForeignCar").html(data.carCount);
                } else {
                    $("#nowForeignCar").empty();
                    $("#nowForeignCar").append("<a href='javascript:void(0);'  onclick='toForeignCarList()'>"+ data.carCount + "</a>");
                }
                if (data.peopleCount == 0) {
                    $("#nowFPerson").html(data.peopleCount);
                } else {
                    $("#nowFPerson").empty();
                    $("#nowFPerson").append("<a href='javascript:void(0);'  onclick='toForeignPeopleList()'>"+ data.peopleCount + "</a>");
                }
            },
        });
    }*/
	
	/* function queryPrisonerInfo() {
			
			$.ajax({
				type : "post",
				url : "${ctx}/common/all/countPrisonInfoHz",
				dataType : "json",
				success : function(data) {
					if (data.success) {
						
						 
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			}); 
		}*/
		
	</script >
</html>
