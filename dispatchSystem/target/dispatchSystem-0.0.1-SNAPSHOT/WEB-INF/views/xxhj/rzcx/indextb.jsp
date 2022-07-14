<%@page import="java.util.Date"%>
<%@page import="com.cesgroup.prison.code.tool.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<%
	String date = DateUtils.formatDate(new Date());
	String date1 = DateUtils.getNextDay(date, -10);
	date1 = date1 + " 00:00:01";

%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/bj-cui/js/echarts.min.js" type="text/javascript"></script>
<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rzcx_query">
		<table class="table">
			<tr>
			
				<th>从：</th>
				<td><cui:datepicker  id="StartTime" name="StartTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>到：</th>
				<td><cui:datepicker  id="EndTime" name="EndTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>查询：</th>
				<td><cui:input id="SearchDate" type="text"  placeholder="请输入操作,IP或URL" name="SearchDate"  ></cui:input></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
			</tr>
		</table>
	</cui:form>
	
		<table>
       		<tr>
       			<td><div id="sjChart" style="width:500px;height:600px;" ></div></td>
       			<td><div id="czChart" style="width:500px;height:600px;"  ></div></td>
       		</tr>
       	</table>
	<!-- 
	<div style="height:30px;">&nbsp;</div>
		 
		<div class="container-box">
		    <div id="layout1">
		         
		        <div data-options="region:'north'" class="main" style="height:100%;min-height: 410px;margin-top: 40px;">
		            <div class="bottom">
		                <div class="left-chart">
		                    <div id="sjChart" class="chart-box"></div>
		                </div>
 		                <div class="right-chart">
		                    <div id="czChart" class="chart-box"></div>
		                </div>
		            </div>
		        </div>
			</div>
		</div> -->

<script>
	var sjChart;
	var czChart;
	$.parseDone(function() {
		$("#StartTime").datepicker('option','value','<%=date1%>');
		initSjtbChart();
		initCztbChart();
	});

	function search() {
		initSjtbChart();
		initCztbChart();
	}
	
	//时间图表
	function initSjtbChart() {
		  sjChart = echarts.init(document.getElementById('sjChart'));
		  var STime =$("#StartTime").datepicker("getValue");
		  var ETime =$("#EndTime").datepicker("getValue");
		  var SearchDate=$("#SearchDate").textbox("getValue");
          var urlPath =  "${ctx}/xxhj/rzcx/sjChart?STime="+STime+"&ETime="+ETime+"&SearchDate="+SearchDate;
          var params = {};
          // Desc: 调用ajax获取数据库 
          $.ajax({
              type : 'post',
              url : urlPath,
              data : {},
              dataType : 'json',
              success : function(data) {
                  var option;
                  if(data.code == 200) {
                	  var xData = [], seriesData = [] ;
                	  data.data.map(function(a, b) {
                		  xData.push(a.RQ);
                          seriesData.push(a.CNT);
                      });
                	  
                	  var option = {
                			  title : {
	  	             		        text: "每天次数统计",
	  	             		        left: 0,
	  	             		        top: 20,
	  	             		        textStyle: {
	  	             		        	color: '#fff',
	  	             		        	fontSize: 16,
	  	             		        	fontWeight: 'normal',
	  	             		        }
  	             		    	}, tooltip: {
  	             			        trigger: 'item',
  	             			        formatter: "{b} : {c}",
  	             			        axisPointer: {
  	             			            type: 'shadow'
  	             			        }
  	             			    },
                			    xAxis: {
                			        type: 'category',
                			        data: xData
                			    },
                			    yAxis: {
                			        type: 'value'
                			    },
                			    series: [{
                			        data: seriesData,
                			        type: 'bar'
                			    }]
                			};
                	  sjChart.setOption(option);
                  }
              }
          });
	}
	
	//操作图表
	function initCztbChart(){
		czChart = echarts.init(document.getElementById('czChart'));
		var STime =$("#StartTime").datepicker("getValue");
		var ETime =$("#EndTime").datepicker("getValue");
		var SearchDate=$("#SearchDate").textbox("getValue");
        var urlPath =  "${ctx}/xxhj/rzcx/czChart?STime="+STime+"&ETime="+ETime+"&SearchDate="+SearchDate;
        var params = {};
        // Desc: 调用ajax获取数据库 
        $.ajax({
            type : 'post',
            url : urlPath,
            data : {},
            dataType : 'json',
            success : function(data) {
                var option;
                if(data.code == 200) {
              	  var xData = [], seriesData = [] ;
              	  data.data.map(function(a, b) {
              		  xData.push(a.OP_TARGET);
                      seriesData.push(a.CNT);
                    });
              	  
              	  var option = {
	              			 title : {
	             		        text: "各操作类型次数统计",
	             		        left: 0,
	             		        top: 20,
	             		        textStyle: {
	             		        	color: '#fff',
	             		        	fontSize: 16,
	             		        	fontWeight: 'normal',
	             		        }
	             		    }, tooltip: {
  	             			        trigger: 'item',
  	             			        formatter: "{b} : {c}",
  	             			        axisPointer: {
  	             			            type: 'shadow'
  	             			        }
  	             			    },
              			    xAxis: {
              			        type: 'category',
              			        data: xData
              			    },
              			    yAxis: {
              			        type: 'value'
              			    },
              			    series: [{
              			        data: seriesData,
              			        type: 'bar'
              			    }]
              			};
              	  czChart.setOption(option);
                }
            }
        });
	}
	 
	
</script>